package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wlrss.oldmarket.config.AlipayConfig;
import com.wlrss.oldmarket.entity.*;

import com.wlrss.oldmarket.mapper.UserMapper;
import com.wlrss.oldmarket.service.GoodsMessageService;
import com.wlrss.oldmarket.service.OrdersDetailService;
import com.wlrss.oldmarket.service.impl.GoodsServiceImpl;
import com.wlrss.oldmarket.service.impl.ShoppingCartServiceImpl;
import com.wlrss.oldmarket.service.impl.UserServiceImpl;
import com.wlrss.oldmarket.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@Controller
public class CartController {

    private final Logger  LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private  GoodsServiceImpl goodsService;

    @Autowired
    private OrdersDetailService ordersDetailService;

    @Autowired
    private GoodsMessageService goodsMessageService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/cart")
    public String toIndex(HttpServletRequest request, HttpServletResponse response, Model  model){
        String email = (String) request.getSession().getAttribute("email");
        String key = shoppingCartService.getKey(request,response,email);
        ShoppingCart cacheCart = shoppingCartService.mergeCart(key,email);
        //查询出每个商品的信息
        List<Goods> goods = new ArrayList<>();
        for (int i = 0 ; i<cacheCart.getCartItems().size(); i++){
            //根据商品id查询
            Goods goodsById = goodsService.findGoodsById(cacheCart.getCartItems().get(i).getGoodsid());
            goodsById.setNums(cacheCart.getCartItems().get(i).getNums());
            goods.add(goodsById);
        }
        model.addAttribute("cartItems",goods);
//        if (cacheCart.getCartItems().size() > 0){
//            model.addAttribute("cartCacheItems",cacheCart.getCartItems().get(0).getNums());
//        }else {
//            model.addAttribute("cartCacheItems",null);
//        }
        return "cart";
    }

    @RequestMapping("/add")
    public  void     add(HttpServletResponse response, HttpServletRequest request, CartItem cartItem){
        System.out.println(cartItem.getGoodsid());
        String email =(String) request.getSession().getAttribute("email");
        int userId = userService.findUserIdByGoodsId(cartItem.getGoodsid());
        cartItem.setNums(1).setAddtime(new Date()).setUserid(userId);
        shoppingCartService.addCart(response,request,email,cartItem);
       // return "redirect:/cart";
    }

    @PostMapping("/remove")
    public void  remove(HttpServletRequest request,HttpServletResponse response,CartItem cartItem){
        String email =(String) request.getSession().getAttribute("email");
        int userId = userService.findUserIdByGoodsId(cartItem.getGoodsid());
        cartItem.setNums(1).setAddtime(new Date()).setUserid(userId);
        shoppingCartService.removeCart(response,request,email,cartItem);
    }


    @RequestMapping(value = "/settlement")
    @ResponseBody
    public JsonData settlement(String goodsId, String goodsNums, HttpSession session){
        JSONArray IdArr =  JSONArray.parseArray(goodsId);
        JSONArray NumsArr =  JSONArray.parseArray(goodsNums);
        //goods集合
        List<Goods> goods = new ArrayList<>();
        //orderDetails集合
        List<OrderDetails> orderDetails = new ArrayList<>();

        IdArr.forEach(id->{
            //根据id查询商品加入集合
            Goods good = goodsService.findGoodsByCartId((int) id);
            goods.add(good);

            //设置订单明细goodsId
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setGoodsid((int)id);
            orderDetails.add(orderDetail);
        });

        //设置商品数量
        AtomicInteger i  = new AtomicInteger();
       goods.forEach(g->{
           g.setNums((int)NumsArr.get(i.getAndIncrement()));
       });
        //查询最大的 订单id
        int max = shoppingCartService.getMaxOrdersId() + 1;
        //goodsDetails设置数量
        AtomicInteger j = new AtomicInteger();
        orderDetails.forEach(o -> {
            o.setNums((int)NumsArr.get(j.getAndIncrement()));
           o.setOrdersid(max);
            //将订单信息加入mysql
            shoppingCartService.addOrderDetails(o);
        });
        Integer id;
        try {
             id = ordersDetailService.findUserIdByEmail((String) session.getAttribute("email"));
        }catch (RuntimeException e){
            session.setAttribute("msg","请先登录在付款");
            return  new JsonData(null,300,null);
        }
        //查询地址表
        List<Address> addresses = shoppingCartService.findAddressById(id);

        if (session.getAttribute("email")==null){
            return  new JsonData(null,300,null);
        }else if (addresses.size()==0){
            session.setAttribute("msg","请设置一个默认地址");
            return  new JsonData(null,400,null);
        } else {
            //下订单成功
            session.setAttribute("goods",goods);
            session.setAttribute("orderDetails",orderDetails);
            session.setAttribute("addresses",addresses);
            //添加订单 信息
            Orders orders = new Orders();
            orders.setDateDown(new Date()).setOrderno(new Date().getTime());
            //计算订单总价
            AtomicReference<Double> totalMoney = new AtomicReference<>((double) 0);
            goods.forEach(g->{
                totalMoney.updateAndGet(v -> new Double((double) (v + g.getPrice() * g.getNums())));
            });
            orders.setMoney(totalMoney.get());
            orders.setStatus("5");
            System.out.println(orders);
            session.setAttribute("orders",orders);
            return  new JsonData(null,200,null);
        }
    }

    @PostMapping(value = "/addAddress")
    public JsonData  addAddress(String accept , String acceptphone , String address,HttpSession  session){
        System.out.println("========"+address);
        String email = (String) session.getAttribute("email");
        int id = goodsMessageService.getUserIdByEmail(email);
        Address addAddress = new Address();
        addAddress.setIsdefault(1).setAccept(accept).setAcceptphone(acceptphone).setAddress(address).setUserid(id);
       JsonData jsonData = new JsonData();
        try {
            shoppingCartService.addAddress(addAddress);
            jsonData.setCode(200);
            return  jsonData;
        }catch (RuntimeException e){
            jsonData.setCode(400);
            return  jsonData;
        }
    }

    @RequestMapping("/addOrder")
    public void addOrder(HttpServletResponse resp,Double money ,  Long orderNo , Date date , String status,HttpSession session,HttpServletRequest req) throws IOException {
        int id = goodsMessageService.getUserIdByEmail((String) session.getAttribute("email"));
        Orders orders = new Orders();

        AtomicReference<Double> m = new AtomicReference<>(money);

        orders.setUserid(id).setStatus("5").setDateDown(date).setMoney(m.get()).setOrderno(orderNo);

        session.setAttribute("type","order");
        session.setAttribute("myOrder",orders);


        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商品价格总额

        String pay = money.toString();

        String total_amount = pay;
        session.setAttribute("moe", total_amount);

        //商品名称

        String subject = new String("订单支付");


        //商品描述，可以为空
        String body = "";

        //填充业务参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setGoodsType("1");
        model.setOutTradeNo((int) Math.ceil(Math.random() * 100000000) + "");
        model.setTotalAmount(total_amount);
        model.setSubject(subject);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        alipayRequest.setBizModel(model);

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(form);//直接将完整的表单html输出到页面
        resp.getWriter().flush();
        resp.getWriter().close();

    }


    @RequestMapping("/success")
    public  String success(HttpSession session){
        String type = (String) session.getAttribute("type");
        String email = (String) session.getAttribute("email");
        int userId = ordersDetailService.findUserIdByEmail(email);
        User user = userMapper.selectById(userId);
        if (type.equals("VIP1")){
            user.setVip("1");
            userMapper.updateById(user);
        }else if (type.equals("VIP2")){
            user.setVip("2");
            userMapper.updateById(user);
        }else if (type.equals("VIP3")){
            user.setVip("3");
            userMapper.updateById(user);
        }else if (type.equals("order")){
            Orders myOrder = (Orders)session.getAttribute("myOrder");
            shoppingCartService.addOrder(myOrder);
            myOrder.setStatus("4");
            shoppingCartService.updateOrders(myOrder);
            redisUtil.del("CACHE_SHOPPINGCART");
        }
        return  "order-success";
    }

}

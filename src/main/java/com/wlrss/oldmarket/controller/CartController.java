package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.wlrss.oldmarket.entity.*;

import com.wlrss.oldmarket.service.OrdersDetailService;
import com.wlrss.oldmarket.service.impl.GoodsServiceImpl;
import com.wlrss.oldmarket.service.impl.ShoppingCartServiceImpl;
import com.wlrss.oldmarket.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


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
    public String settlement(Model model, String goodsId, String goodsNums, HttpSession session){
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
        int id = ordersDetailService.findUserIdByEmail((String) session.getAttribute("email"));
        //查询地址表
        List<Address> addresses = shoppingCartService.findAddressById(id);

        if (addresses.size()==0){
            model.addAttribute("msg","请设置一个默认地址");
            return "dash-profile.html";
        }else {
            model.addAttribute("goods",goods);
            model.addAttribute("orderDetails",orderDetails);
            model.addAttribute("addresses",addresses);
            return "payment-info.html";
        }
    }
}

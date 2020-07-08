package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.CartItem;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.ShoppingCart;

import com.wlrss.oldmarket.service.impl.GoodsServiceImpl;
import com.wlrss.oldmarket.service.impl.ShoppingCartServiceImpl;
import com.wlrss.oldmarket.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/shopping")
public class CartController {

    private final Logger  LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    GoodsServiceImpl goodsService;


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

    @PostMapping("/add")
    public  void     add(HttpServletResponse response, HttpServletRequest request, CartItem cartItem){
        System.out.println(cartItem.getGoodsid());
        String email =(String) request.getSession().getAttribute("email");
        int userId = userService.findUserIdByGoodsId(cartItem.getGoodsid());
        cartItem.setNums(1).setAddtime(new Date()).setUserid(userId);
        shoppingCartService.addCart(response,request,email,cartItem);
       // return "redirect:/shopping/cart";
    }

    @PostMapping("/remove")
    public void  remove(HttpServletRequest request,HttpServletResponse response,CartItem cartItem){
        String email =(String) request.getSession().getAttribute("email");
        int userId = userService.findUserIdByGoodsId(cartItem.getGoodsid());
        cartItem.setNums(1).setAddtime(new Date()).setUserid(userId);
        shoppingCartService.removeCart(response,request,email,cartItem);
    }

    @RequestMapping("/settlement")
    public String settlement(){
        return null;
    }
}

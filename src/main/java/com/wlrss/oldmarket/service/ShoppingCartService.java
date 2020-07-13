package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.CartItem;
import com.wlrss.oldmarket.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ShoppingCartService {

    /**
     * 获得用户key
     *
     * 1.用户未登录情况下第一次进入购物车  -> 生成key 保存至cookie中
     * 2.用户未登录情况下第n进入购物车    -> 从cookie中取出key
     * 3.用户登录情况下                  -> 根据用户code生成key
     * 4.用户登录情况下并且cookie中存在key-> 从cookie取的的key从缓存取得购物车 合并至
     *  用户code生成key的购物车中去 ，这样后面才能根据用户code 取得正确的购物车
     * @param request
     * @param response
     * @param email
     * @return
     */
    String getKey(HttpServletRequest request , HttpServletResponse response, String email);


    /**
     * 合并购物车
     * @param tempKey
     * @param email
     * @return
     */
    ShoppingCart mergeCart(String tempKey,String email);

    /**
     * 添加购物车
     * @param response
     * @param request
     * @param email 用户登录邮箱
     * @param item  添加购物车商品 信息 商品id
     */
    void addCart(HttpServletResponse response , HttpServletRequest request, String email , CartItem item);

    /**
     * 移除购物车
     * @param response
     * @param request
     * @param email
     * @param item
     */
    void removeCart(HttpServletResponse response , HttpServletRequest request, String email , CartItem item);
}

package com.wlrss.oldmarket.service.impl;

import com.wlrss.oldmarket.entity.CartItem;
import com.wlrss.oldmarket.entity.ShoppingCart;
import com.wlrss.oldmarket.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;


    /**
     * 获得用户key
     *
     * 1.用户未登录情况下第一次进入购物车  -> 生成key 保存至cookie中
     * 2.用户未登录情况下第n进入购物车    -> 从cookie中取出key
     * 3.用户登录情况下                  -> 根据用户code生成key
     * 4.用户登录情况下并且cookie中存在key-> 从cookie取的的key从缓存取得购物车 合并至
     *  用户code生成key的购物车中去 ，这样后面才能根据用户code 取得正确的购物车
     * @param req
     * @param resp
     * @param email
     * @return
     */
    @Override
    public String getKey(HttpServletRequest req, HttpServletResponse resp, String email) {

        String key = null;  //最终返回的key
        String tempKey = ""; //用来存储cookie中的临时key,

        Cookie cartCookie = WebUtils.getCookie(req, "shoopingCart");
        if(cartCookie!=null){
            //获取Cookie中的key
            key = cartCookie.getValue();
            tempKey = cartCookie.getValue();
        }
        if(StringUtils.isBlank(key)){
            key = ShoppingCart.unLoginKeyPrefix + UUID.randomUUID();
            if (email!=null)
                key = ShoppingCart.LoginKeyPrefix + email;
            Cookie cookie = new Cookie("shoopingCart",key);
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            resp.addCookie(cookie);
        }else if (StringUtils.isNotBlank(key) && email!=null){//⑵
            key = ShoppingCart.LoginKeyPrefix + email;
            if (tempKey.startsWith(ShoppingCart.unLoginKeyPrefix)){//⑴
                //1.满足cookie中取得的key 为未登录时的key
                //2.满足当前用户已经登录
                //3.合并未登录时用户所添加的购物车商品⑷
                mergeCart(tempKey,email);//⑶
            }
        }
        return key;
    }

    /**
     * 合并购物车 返回最终购物车
     * @param tempKey
     * @param email
     * @return
     */
    @Override
    public ShoppingCart mergeCart(String tempKey, String email) {
        ShoppingCart loginCart = null;
        String loginKey = null;
        //从redis中取出购物车数据
        HashOperations<String, String, ShoppingCart> vos = redisTemplate.opsForHash();
        ShoppingCart unLoginCart = vos.get("CACHE_SHOPPINGCART", tempKey);
        if(unLoginCart == null){
            unLoginCart = new ShoppingCart(tempKey);
        }
        if (email != null && tempKey.startsWith(ShoppingCart.unLoginKeyPrefix)){
            //如果用户登录 并且 当恰是未登录 的key
            loginKey  =  ShoppingCart.LoginKeyPrefix + email;
            loginCart = mergeCart(loginKey,email);
            if (null != unLoginCart.getCartItems()){

                if (null != loginCart.getCartItems()){
                    //购物车合并
                    for (CartItem cv : unLoginCart.getCartItems()){
                        long count = loginCart.getCartItems().stream().filter(it->it.getGoodsid().equals(cv.getGoodsid())).count();
                        if(count == 0 ){//没有重复的商品 则直接将商品加入购物车
                            loginCart.getCartItems().add(cv);
                        }else if(count == 1){//出现重复商品 修改数量
                            CartItem c = loginCart.getCartItems().stream().filter(it->it.getGoodsid().equals(cv.getGoodsid())).findFirst().orElse(null);
                            c.setNums(c.getNums()+1);
                        }
                    }
                }else {
                    //如果 当前登录用户的购物车为空  将未登录时的购物车合并
                    loginCart.setCartItems(unLoginCart.getCartItems());
                }
                unLoginCart = loginCart;
                //删除临时key
                vos.delete("CACHE_SHOPPINGCART",tempKey);
                //放入合并后的数据
                vos.put("CACHE_SHOPPINGCART",loginKey,unLoginCart);
            }
        }
        return unLoginCart;
    }

    /**
     *  添加购物车
     * @param response
     * @param request
     * @param email 用户登录邮箱
     * @param item  添加购物车商品 信息 商品id
     */
    @Override
    public void addCart(HttpServletResponse response, HttpServletRequest request, String email, CartItem item) {
        //得到最终key
        String key  = getKey(request,response,email);
        //根据key取得最终购物车对象
        ShoppingCart cacheCart = mergeCart(key,email);
        if (StringUtils.isNotBlank(item.getGoodsid())&& item.getNums()>0){
            long count = 0 ;
            if (null != cacheCart.getCartItems()){
                count = cacheCart.getCartItems().stream().filter(it ->it.getGoodsid().equals(item.getGoodsid()) ).count();
            }
            if (count==0){
                //没有直接加
                cacheCart.getCartItems().add(item);
            }else {
                //存在加数量
                CartItem c = cacheCart.getCartItems().stream().filter(it ->it.getGoodsid() .equals(item.getGoodsid())).findFirst().orElse(null);
                c.setNums(c.getNums()+item.getNums());
            }
        }
        //将合并的数据放入 loginKey
        HashOperations<String,String,ShoppingCart> vos = redisTemplate.opsForHash();
        vos.put("CACHE_SHOPPINGCART",key, cacheCart);
    }

    /**
     * 移除购物车
     * @param response
     * @param request
     * @param email
     * @param item
     */
    @Override
    public void removeCart(HttpServletResponse response, HttpServletRequest request, String email, CartItem item) {
        String key = getKey(request, response, email);
        ShoppingCart cacheCart = mergeCart(key, email);
        if (cacheCart != null && cacheCart.getCartItems() != null && cacheCart.getCartItems().size() > 0) {//⑴
            //
            long count = cacheCart.getCartItems().stream().filter(it -> it.getGoodsid().equals(item.getGoodsid())).count();
            if (count == 1) {//⑵
                CartItem ci = cacheCart.getCartItems().stream().filter(it -> it.getGoodsid().equals(item.getGoodsid())).findFirst().orElse(null);
                if (ci.getNums() > item.getNums()) {//⑶
                    ci.setNums(ci.getNums() - item.getNums());
                } else if (ci.getNums() <= item.getNums()) {
                    cacheCart.getCartItems().remove(ci);
                }
                //1.满足缓存购物车中必须有商品才能减购物车
                //2.满足缓存购物车中有该商品才能减购物车
                //3.判断此次要减数量是否大于缓存购物车中数量 进行移除还是数量相减操作
            }
            HashOperations<String,String,ShoppingCart> vos = redisTemplate.opsForHash();
            vos.put("CACHE_SHOPPINGCART",key, cacheCart);
        }
    }

}

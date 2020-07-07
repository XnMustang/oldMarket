package com.wlrss.oldmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *购物车对象 一个购物车由n个CartItem组成
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart implements Serializable {
    public static final String unLoginKeyPrefix="TMP_";
    public static final String LoginKeyPrefix="USER_";
    private String key="";
    private List<CartItem> cartItems = new ArrayList<>();


    public ShoppingCart(String tempKey) {
        this.key = tempKey;
    }


}

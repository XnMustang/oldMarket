package com.wlrss.oldmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class CartItem implements Serializable {

    /**
     * 购物车
     */
    private Integer nums;
    private Integer userid;
    private String goodsid;
    private Date addtime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return
                Objects.equals(goodsid, cartItem.goodsid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsid);
    }
}

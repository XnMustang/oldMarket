package com.wlrss.oldmarket.mapper;

import com.wlrss.oldmarket.entity.vo.MyOrders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 查看我的订单中的商品订单记录
 */

@Component
public interface OrderDetailMapper {

    /**
     * 通过id三表联查 返回当前用户所有订单记录
     * @param id
     * @return
     */
    List<MyOrders> findAllOrdersDetail(@Param("id") Integer id);

}

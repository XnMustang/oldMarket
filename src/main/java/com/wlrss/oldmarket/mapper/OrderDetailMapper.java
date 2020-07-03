package com.wlrss.oldmarket.mapper;

import com.wlrss.oldmarket.entity.User;
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
     * @param userId
     * @return
     */
    List<MyOrders> findAllOrdersDetail(@Param("userId") int userId);


    /**
     *  根据不同条件查询当前用户的所有订单
     * @param userId                    查看哪个用户的订单明细
     * @param orderNameOrderNum         商品名称或者订单编号查询
     * @param flag                      标志位,动态SQL判断输入的是订单编号还是商品名称
     * @return
     */
    List<MyOrders> findAllOptionCondition(int userId,String orderNameOrderNum,boolean flag);

    /**
     * 根据session中的邮箱获取到当前的用户id。用来查他自己的所有邮箱
     * @param email
     * @return
     */
    int findUserIdByEmail(@Param("email") String email);

    /**
     * 获取订单的状态
     * @param optionValue
     * @return
     */
    List<MyOrders> findStatus(int userId,String optionValue);
}

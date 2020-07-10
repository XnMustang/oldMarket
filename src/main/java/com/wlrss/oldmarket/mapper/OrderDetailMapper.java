package com.wlrss.oldmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.Orders;
import com.wlrss.oldmarket.entity.vo.MyOrders;
import com.wlrss.oldmarket.entity.vo.OrderDateilUserVo;
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

    /**
     * 根据时间查询订单
     * @param time1
     * @param time2
     * @return
     */
    List<MyOrders> findTimeOrder(int userId,String time1, String time2);

    /**
     * 根据商品id查询用户和商品信息
     * @param goodId
     * @return
     */
    List<OrderDateilUserVo> queryOrderDetail(Integer goodId);
}

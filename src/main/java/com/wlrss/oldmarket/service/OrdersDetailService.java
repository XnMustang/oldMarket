package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyOrders;
import com.wlrss.oldmarket.entity.vo.OrderDateilUserVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单明细
 */
public interface OrdersDetailService {

    //查询所有买的商品的订单信息
     List<MyOrders> findAllOrdersDetail(Integer id);


    /**
     * 根据不同条件查询当前用户所有订单
     * @param userId                查看哪个用户的订单明细
     * @param orderNameOrderNum     根据订单或者商品名称查询订单
     * @param flag                  标志位,动态SQL判断输入的是订单编号还是商品名称
     * @return
     */
     List<MyOrders> findAllOptionCondition(int userId,String orderNameOrderNum,boolean flag);

    /**
     * 根据session中的邮箱获取到当前的用户id。用来查他自己的所有邮箱
     * @param email
     * @return
     */
    int findUserIdByEmail(String email);


    /**
     * 根据订单状态查询
     * @param optionValue
     * @return
     */
    List<MyOrders> findStatus(int userId,String optionValue);


    /**
     * 根据时间查询订单
     * @param time1     开始时间
     * @param time2     结束时间
     * @return
     */
    List<MyOrders> findTimeOrder(int userId,String time1, String time2);


    /**
     * 根据商品的id 联合用户表 查询出双方信息展示
     * @param goodId    商品id
     * @return
     */
    List<OrderDateilUserVo> queryOrderDetail(Integer goodId);

    /**
     * 执行商品添加
     * @param goodsId       商品id
     * @param goodsName     名称
     * @param goodsPrice    价格
     * @param goodsDescribed 描述
     * @param imgName       图片
     * @param userId        谁添加的
     * @param goodsTime     添加时间
     * @param status        状态
     * @param nums          数量
     * @return
     */
    int addGoods(int goodsId, String goodsName, BigDecimal goodsPrice, String goodsDescribed, String imgName, int userId, Date goodsTime,String messageGoods, int status, int nums);

}

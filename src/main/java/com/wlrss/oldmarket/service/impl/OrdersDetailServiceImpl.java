package com.wlrss.oldmarket.service.impl;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.vo.OrderDateilUserVo;
import com.wlrss.oldmarket.mapper.OrderDetailMapper;
import com.wlrss.oldmarket.service.OrdersDetailService;
import com.wlrss.oldmarket.entity.vo.MyOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<MyOrders> findAllOrdersDetail(Integer userId) {
        return orderDetailMapper.findAllOrdersDetail(userId);
    }

    /**
     *
     * @param userId                查看哪个用户的订单明细
     * @param orderNameOrderNum     根据订单或者商品名称查询订单
     * @param flag                  标志位,动态SQL判断输入的是订单编号还是商品名称
     * @return
     */
    @Override
    public List<MyOrders> findAllOptionCondition(int userId,String orderNameOrderNum,boolean flag) {
        //判断输入的是订单还是商品名称，执行不同的SQL
        for(int i = orderNameOrderNum.length();--i>= 0;){
            int chr = orderNameOrderNum.charAt(i);
            if(chr < 48 || chr > 57) {
                flag = false;   //字符
            }else {
                flag = true;    //数字
            }
        }
        System.out.println("false字符，true数字："+flag);
        return orderDetailMapper.findAllOptionCondition(userId,orderNameOrderNum,flag);
    }

    /**
     * 根据邮箱获取id
     * @param email
     * @return
     */
    @Override
    public int findUserIdByEmail(String email) {
        return orderDetailMapper.findUserIdByEmail(email);
    }

    /**
     * 根据状态查询当前用户订单
     * @param optionValue   订单的交易状态
     * @return
     */
    @Override
    public List<MyOrders> findStatus(int userId,String optionValue) {
        return orderDetailMapper.findStatus(userId,optionValue);
    }

    /**
     * 根据时间查询当用户的订单
     * @param time1     开始时间
     * @param time2     结束时间
     * @return
     */
    @Override
    public List<MyOrders> findTimeOrder(int userId,String time1, String time2) {
        return orderDetailMapper.findTimeOrder(userId,time1,time2);
    }

    /**
     * 根据商品的id 联合用户表 查询出双方信息展示
     * @param goodId    商品id
     * @return
     */
    @Override
    public  List<OrderDateilUserVo> queryOrderDetail(Integer goodId) {
        return orderDetailMapper.queryOrderDetail(goodId);
    }
}

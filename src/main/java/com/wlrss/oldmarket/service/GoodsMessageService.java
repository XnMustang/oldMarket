package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.vo.AcceptMsgInfoVo;
import com.wlrss.oldmarket.entity.vo.MessageInfoVo;

import java.util.Date;
import java.util.List;

/**
 * 留言信息
 */

public interface GoodsMessageService {

    /**
     * 根据存在session中的Email查询出用户id  存放到留言表中的发送人
     * @param email 邮箱
     * @return      姓名
     */
    int getUserIdByEmail(String email);

    /**
     * 获取到这个商品属于哪个商家，这个商家就是接收留言的人
     * @param goodsId   商品id
     * @return
     */
    int getAcceptMsgByGoodsId(Integer goodsId);

    /**
     * 插入留言信息到留言数据表中
     * @param userId        留言人id
     * @param messageInfo   留言内容
     * @param date          留言时间
     * @param goodsId       对哪个商品留言
     * @param infoState     是否已读
     * @return
     */
    int addMessage(int userId, String messageInfo, Date date, Integer goodsId,int acceptMsgId, int infoState);

    /**
     * 根据接收人id查询出谁给我留言了
     * @param userId
     * @return
     */
    List<MessageInfoVo> findHowMessageByAcceptPerson(int userId);

    /**
     * 查询出右侧详细留言信息的上部分（发送者信息）
     * @param sendPerson    留言人id
     * @param userId        接收人id
     * @return
     */
    MessageInfoVo findMsgTopSendPerson(Integer sendPerson, int userId,Integer goodsid);

    /**
     * 查询出右侧详细信息的下部分（接收者信息）
     * @param sendPerson
     * @param userId
     * @return
     */
    AcceptMsgInfoVo findMsgTopAcceptPerson(Integer sendPerson, int userId,Integer goodsid);
}

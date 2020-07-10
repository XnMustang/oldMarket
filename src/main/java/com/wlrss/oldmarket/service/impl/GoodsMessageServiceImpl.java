package com.wlrss.oldmarket.service.impl;

import com.wlrss.oldmarket.entity.vo.AcceptMsgInfoVo;
import com.wlrss.oldmarket.entity.vo.MessageInfoVo;
import com.wlrss.oldmarket.mapper.GoodsMessageMapper;
import com.wlrss.oldmarket.service.GoodsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsMessageServiceImpl implements GoodsMessageService {

    @Autowired
    GoodsMessageMapper goodsMessageMapper;

    /**
     * 根据Email查询出当前登录的用户id
     * @param email 邮箱
     * @return
     */
    @Override
    public int getUserIdByEmail(String email) {
        return goodsMessageMapper.getUserNameByEmail(email);
    }

    /**
     * 根据商品id查询属于哪个商家，这个商家就是接收留言的人
     * @param goodsId   商品id
     * @return
     */
    @Override
    public int getAcceptMsgByGoodsId(Integer goodsId) {
        return goodsMessageMapper.getAcceptMsgByGoodsId(goodsId);
    }

    /**
     * 插入留言信息到留言表
     * @param userId        留言人id
     * @param messageInfo   留言内容
     * @param date          留言时间
     * @param goodsId       对哪个商品留言
     * @param infoState     是否已读
     * @return
     */
    @Override
    public int addMessage(int userId, String messageInfo, Date date, Integer goodsId,int acceptMsgId,int infoState) {
        return goodsMessageMapper.addMessage(userId,messageInfo,date,goodsId,acceptMsgId,infoState);
    }

    /**
     * 根据接收人id查询出谁给我留言了
     * @param userId
     * @return
     */
    @Override
    public List<MessageInfoVo> findHowMessageByAcceptPerson(int userId) {
        return goodsMessageMapper.findHowMessageByAcceptPerson(userId);
    }

    /**
     * 查询出右侧上方留言者信息显示
     * @param sendPerson    留言人id
     * @param userId        接收者id
     * @return
     */
    @Override
    public MessageInfoVo findMsgTopSendPerson(Integer sendPerson, int userId,Integer goodsid) {
        return goodsMessageMapper.findMsgTopSendPerson(sendPerson,userId,goodsid);
    }

    /**
     * 查询出右侧下方留言者信息显示
     * @param sendPerson
     * @param userId
     * @return
     */
    @Override
    public AcceptMsgInfoVo findMsgTopAcceptPerson(Integer sendPerson, int userId,Integer goodsid) {
        return goodsMessageMapper.findMsgTopAcceptPerson(sendPerson,userId,goodsid);
    }
}

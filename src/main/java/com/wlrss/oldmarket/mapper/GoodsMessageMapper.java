package com.wlrss.oldmarket.mapper;


import com.wlrss.oldmarket.entity.vo.AcceptMsgInfoVo;
import com.wlrss.oldmarket.entity.vo.MessageInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository

public interface GoodsMessageMapper {

    /**
     * 根据session中的邮箱获取当前登录的用户名
     * @param email
     * @return
     */
    int getUserNameByEmail(String email);

    /**
     * 根据商品id查询属于哪个商家，这个商家就是接收留言的人
     * @param goodsId   商品id
     * @return
     */
    int getAcceptMsgByGoodsId(Integer goodsId);

    /**
     * 插入留言信息到留言表
     * @param userId        留言人id
     * @param messageInfo   留言内容
     * @param date          留言时间
     * @param goodsId       对哪个商品留言
     * @param infoState     是否已读
     * @return
     */
    int addMessage(int userId, String messageInfo, Date date, Integer goodsId,int acceptMsgId,int infoState);

    /**
     * 根据接收人id查询出谁给我留言了
     * @param userId
     * @return
     */
    List<MessageInfoVo> findHowMessageByAcceptPerson(int userId);

    /**
     * 查询出右侧上方留言者信息显示
     * @param sendPerson    留言人id
     * @param userId        接收人id
     * @return
     */
    MessageInfoVo findMsgTopSendPerson(Integer sendPerson, int userId);

    /**
     * 查询出右侧下方留言者信息显示
     * @param sendPerson
     * @param userId
     * @return
     */
    AcceptMsgInfoVo findMsgTopAcceptPerson(Integer sendPerson, int userId);
}

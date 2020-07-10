package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.vo.AcceptMsgInfoVo;
import com.wlrss.oldmarket.entity.vo.MessageInfoVo;
import com.wlrss.oldmarket.service.GoodsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 接收用户在商品中的留言信息
 */
@Controller
@RequestMapping("/goodsMessage")
public class GoodsMessageController {

    @Autowired
    GoodsMessageService goodsMessageService;

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(String messageInfo, Integer goodsId,HttpSession session){

        System.out.println("接收到用户给商品的留言信息：" + messageInfo+"==============");
        /**
         * 留言分析：
         *      需要传过来的数据：
         *          留言人姓名，头像，根据id查询
         *          留言内容
         *          留言哪个商品
         *          留言时间
         */
        //获取到当前谁登录的，这条留言就是他的身份发的
        String email = (String) session.getAttribute("email");
        int userId = goodsMessageService.getUserIdByEmail(email);
        System.out.println("当前登录的用户id为：" + userId+",商品的id为：" + goodsId);

        //获取到这个商品属于哪个商家，他就是接收留言的人
        int acceptMsgId = goodsMessageService.getAcceptMsgByGoodsId(goodsId);
        System.out.println("这件商品属于商家：" + acceptMsgId);

        /**
         * 留言信息插入到留言表中
         *      插入的信息有：
         *      send_person发送人id
         *      msg_content发送内容
         *      msg_content_time发送内容时间
         *      goodsid对哪个商品发送
         *      accept_person接收人
         *      info_state是否已读状态  1未读 0已读
         */
        int infoState = 1;  //未读
        int addResult = goodsMessageService.addMessage(userId, messageInfo, new Date(), goodsId, acceptMsgId, infoState);

        if(addResult > 0){
            System.out.println("留言插入成功！");
        }else {
            System.out.println("插入失败！");
        }

        return messageInfo;
    }

    /**
     * 导航栏查看当前登录用户的留言信息(左侧)
     * @param session
     * @return
     */
    @RequestMapping("/showMsgInfoLeft")
    @ResponseBody
    public List<MessageInfoVo> showMsgInfoLeft(HttpSession session){
        //获取当前登录人的id
        int userId = getUserIdByEmail(session);

        //默认以接收留言人的身份查询（accept_person）  查询出都是谁给我留言了，根据当前登录id，也就是接收人
        List<MessageInfoVo> messageInfos = goodsMessageService.findHowMessageByAcceptPerson(userId);

        System.out.println(messageInfos+"-----------");
        System.out.println("查看都是谁给我留言了：-----------------------------");
        for (MessageInfoVo messageInfo : messageInfos) {
            System.out.println(messageInfo);
        }
        return messageInfos;
    }

    /**
     * 导航栏查看当前登录用户的留言信息(右侧上面)
     * 参数1：留言人id
     */
    @RequestMapping("/showMsgInfoRightTop")
    @ResponseBody
    public MessageInfoVo showMsgInfoRightTop(Integer sendPerson, HttpSession session){
        //获取当前登录人的id
        int userId = getUserIdByEmail(session);
        System.out.println("用户id:--"+userId);
        System.out.println("留言者id：" + sendPerson);

        //现在还有问题，思考是否需要返回一个集合的问题（一个人留了多条，现在为了测试返回一条）
        MessageInfoVo messageInfo =  goodsMessageService.findMsgTopSendPerson(sendPerson,userId);
        System.out.println("留言者信息：========");
        System.out.println(messageInfo);

        return messageInfo;
    }

    /**
     * 导航栏查看当前登录用户的留言信息(右侧下面)
     * @param sendPerson  接收人id
     * @param session
     * @return
     */
    @RequestMapping("/sendMsgLeftRightBottom")
    @ResponseBody
    public AcceptMsgInfoVo sendMsgLeftRightBottom(Integer sendPerson,HttpSession session){
        //获取当前登录人的id
        int userId = getUserIdByEmail(session);
        System.out.println("用户id:++"+userId);
        System.out.println("接收者id：" + sendPerson);

        AcceptMsgInfoVo acceptMsgInfo = goodsMessageService.findMsgTopAcceptPerson(sendPerson,userId);
        System.out.println("接收者信息+++++++++：" + acceptMsgInfo);

        return acceptMsgInfo;
    }


    /**
     * 公共方法：获取用户id
     * @param session
     * @return
     */
    public int getUserIdByEmail(HttpSession session){
        //获取当前登录人的id
        String email = (String) session.getAttribute("email");
        int userId = goodsMessageService.getUserIdByEmail(email);
        return userId;
    }

}

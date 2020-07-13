package com.wlrss.oldmarket.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wlrss.oldmarket.config.AlipayConfig;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.log.MyLog;
import com.wlrss.oldmarket.mapper.OrderDetailMapper;
import com.wlrss.oldmarket.mapper.UserMapper;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author jamesBond
 * @createTime 2020/7/4/004 10:08
 */
@RestController
@RequestMapping("/alipay")
public class AlipayController {
    /**
     * 支付接口，跳转到支付界面，支付成功后 回调接口
     *
     * @param httpResponse
     * @throws IOException
     */
    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/pay")
    @MyLog("付款购买成功")
    public void pay(HttpServletResponse httpResponse, HttpSession session) throws IOException {


        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        session.setAttribute("type","VIP1");
        //商品价格总额

        String total_amount = "99";
        session.setAttribute("moe", total_amount);

        //商品名称

        String subject = new String("普通会员服务");

        //商品描述，可以为空
        String body = "";

        //填充业务参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setGoodsType("1");
        model.setOutTradeNo((int) Math.ceil(Math.random() * 100000000) + "");
        model.setTotalAmount(total_amount);
        model.setSubject(subject);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        alipayRequest.setBizModel(model);


        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @GetMapping("/pay1")
    public void pay1(HttpServletResponse httpResponse, HttpSession session) throws IOException {


        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        session.setAttribute("type","VIP2");
        //商品价格总额

        String total_amount = "299";
        session.setAttribute("moe", total_amount);

        //商品名称

        String subject = new String("高级会员服务");

        //商品描述，可以为空
        String body = "";

        //填充业务参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setGoodsType("1");
        model.setOutTradeNo((int) Math.ceil(Math.random() * 100000000) + "");
        model.setTotalAmount(total_amount);
        model.setSubject(subject);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        alipayRequest.setBizModel(model);


        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @GetMapping("/pay2")
    public void pay2(HttpServletResponse httpResponse, HttpSession session) throws IOException {


        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        session.setAttribute("type","VIP3");
        //商品价格总额

        String total_amount = "699";
        session.setAttribute("moe", total_amount);

        //商品名称

        String subject = new String("尊贵会员服务");

        //商品描述，可以为空
        String body = "";

        //填充业务参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setGoodsType("1");
        model.setOutTradeNo((int) Math.ceil(Math.random() * 100000000) + "");
        model.setTotalAmount(total_amount);
        model.setSubject(subject);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        alipayRequest.setBizModel(model);


        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    /**
     * 回调地址
     *
     * @return
     */
    @GetMapping("/success")
    public String success() {

        return "支付成功";
    }
}

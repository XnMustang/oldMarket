package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.Address;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.log.MyLog;
import com.wlrss.oldmarket.service.AddressService;
import com.wlrss.oldmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    UserService userService;

    //修改默认收货人
    @MyLog("修改了默认收货人")
    @RequestMapping("/updateAccept")
    public String updateAcceptAddress(Integer acceptid,Integer userid){
        System.out.println("修改为默认的收货人id=="+acceptid);
        //找到当前默认的收货地址(根据状态为1找默认,会有问题,其他用户的默认也会找到)
        Address address=addressService.findDefaultAccept(userid);
        //根据当前默认地址的id,修改当前默认地址状态为0
        addressService.updateAccept(address.getAcceptid());
        //要成为默认的状态改为1
        addressService.updateAccept(acceptid);

        return "dash-profile";
    }

    //根据ID删除收货人信息
    @MyLog("删除了一条收货人信息")
    @RequestMapping("/deleteAcceptById")
    public String deleteAcceptById(Integer acceptid){
        addressService.deleteAcceptByid(acceptid);
        return "dash-profile";
    }

    //添加新地址
    @MyLog("添加了收货新地址")
    @RequestMapping("/addAccept")
    public String addAccept(String acceptname, String acceptphone, String address, HttpSession session){
        System.out.println("acceptname="+acceptname+"|"+"{acceptphone="+acceptphone+"|"+"{address="+address);
        String email= (String) session.getAttribute("email");
        User user=userService.findUserByEmail(email);

        Address a= new Address();
        a.setAccept(acceptname).setAcceptphone(acceptphone).setUserid(user.getId()).setAddress(address).setIsdefault(0);
        addressService.addAcceptAddress(a);
        return "redirect:/dash-profile.html";
    }
}

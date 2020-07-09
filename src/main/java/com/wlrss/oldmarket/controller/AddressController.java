package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.Address;
import com.wlrss.oldmarket.entity.User;
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
    @RequestMapping("/updateAccept")
    public String updateAcceptAddress(Integer acceptid){
        System.out.println("修改为默认的收货人id=="+acceptid);
        Address address=addressService.findDefaultAccept();

        addressService.updateAccept(address.getAcceptid());

        addressService.updateAccept(acceptid);

        return "dash-profile";
    }

    //根据ID删除收货人信息
    @RequestMapping("/deleteAcceptById")
    public String deleteAcceptById(Integer acceptid){
        addressService.deleteAcceptByid(acceptid);
        return "dash-profile";
    }

    //添加新地址
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

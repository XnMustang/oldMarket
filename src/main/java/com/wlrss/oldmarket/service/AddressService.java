package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.Address;

public interface AddressService {
    //根据acceptid修改默认收货人
    void updateAccept(Integer acceptid);

    //查找默认收货人
    Address findDefaultAccept();

    //根据acceptid删除收货人信息
    void deleteAcceptByid(Integer acceptid);

    //添加地址
    void addAcceptAddress(Address address);


}

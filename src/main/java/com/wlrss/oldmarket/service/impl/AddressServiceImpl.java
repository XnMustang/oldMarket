package com.wlrss.oldmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.Address;
import com.wlrss.oldmarket.mapper.AddressMapper;
import com.wlrss.oldmarket.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;

    //修改默认收货人地址
    @Override
    public void updateAccept(Integer acceptid) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("acceptid", acceptid);
        Address address = addressMapper.selectOne(queryWrapper);
        if (address.getIsdefault() == 1) {
            address.setIsdefault(0);
        } else {
            address.setIsdefault(1);
        }
        System.out.println(address);
        addressMapper.updateById(address);
    }

    // 查找默认收货人地址
    @Override
    public Address findDefaultAccept() {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isdefault",1);
        Address address=addressMapper.selectOne(queryWrapper);
        return address;
    }

    @Override
    public void deleteAcceptByid(Integer id) {
        addressMapper.deleteById(id);
    }

    @Override
    public void addAcceptAddress(Address address) {
        addressMapper.insert(address);
    }

}

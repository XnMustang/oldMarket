package com.wlrss.oldmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlrss.oldmarket.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddressMapper extends BaseMapper<Address> {
}

package com.wlrss.oldmarket.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jamesBond
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT `user`.id,username,email,`user`.phone,accept,acceptid,address,isdefault,acceptphone,gender,qq,google,intro FROM `user` INNER JOIN address ON `user`.id=address.userid WHERE `user`.email=#{email} ORDER BY isdefault DESC,acceptid asc")
    List<MyUser> findMyUserByEmail(@Param("email") String email);

    
}

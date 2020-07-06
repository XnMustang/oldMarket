package com.wlrss.oldmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlrss.oldmarket.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 导航栏中最新商品的搜索显示
 */

@Repository
@Mapper
public interface ItemsMapper extends BaseMapper<Goods> {

}

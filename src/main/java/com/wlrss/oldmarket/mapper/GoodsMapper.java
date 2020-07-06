package com.wlrss.oldmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.vo.MyNewGoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 查询所有商品按照时间倒序
     * @return
     */
    List<MyNewGoodsVo> queryAllGoods(String queryGoods);
}

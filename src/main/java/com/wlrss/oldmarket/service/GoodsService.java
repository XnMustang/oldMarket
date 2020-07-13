package com.wlrss.oldmarket.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.vo.MyNewGoodsVo;

import java.util.List;

public interface GoodsService{

    List<MyNewGoodsVo> queryAllGoods(String keyword,String type);

    Goods findGoodsById(String goodsid);
}

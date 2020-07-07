package com.wlrss.oldmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.vo.MyNewGoodsVo;
import com.wlrss.oldmarket.mapper.GoodsMapper;
import com.wlrss.oldmarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<MyNewGoodsVo> queryAllGoods(String queryGoods) {
        return goodsMapper.queryAllGoods(queryGoods);
    }


    @Override
    public Goods findGoodsById(String goodsid) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",goodsid);
        Goods goods = goodsMapper.selectOne(queryWrapper);
        return  goods;
    }
}

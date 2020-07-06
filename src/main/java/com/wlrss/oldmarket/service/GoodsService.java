package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.vo.MyNewGoodsVo;

import java.util.List;

public interface GoodsService {

    List<MyNewGoodsVo> queryAllGoods(String queryGoods);

}

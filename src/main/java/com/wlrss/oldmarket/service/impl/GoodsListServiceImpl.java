package com.wlrss.oldmarket.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlrss.oldmarket.entity.vo.GoodsList;
import com.wlrss.oldmarket.mapper.GoodsListMapper;
import com.wlrss.oldmarket.service.GoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/8/008 10:22
 */
@Service
public class GoodsListServiceImpl implements GoodsListService {

    @Autowired
    GoodsListMapper goodsListMapper;

    @Override
    public IPage<GoodsList> findAll(Page<GoodsList> page, Integer state) {
        return goodsListMapper.findAll(page,state);
    }
}

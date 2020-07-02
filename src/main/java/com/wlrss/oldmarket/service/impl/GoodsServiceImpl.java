package com.wlrss.oldmarket.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.mapper.GoodsMapper;
import com.wlrss.oldmarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * 商品实现
 *
 * @author jamesBond
 * @createTime 2020/7/1/001 14:24
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 查找全部
     *
     * @return
     */
    @Override
    public IPage<Goods> findAll() {
        //参数1为当前页 默认值是1    参数2是每页显示记录数 默认值是10
        IPage<Goods> page = new Page<>(1, 2);
        return goodsMapper.selectPage(page, null);
    }

    /**
     * 增加商品
     *
     * @param goods
     * @return
     */
    @Override
    public int insertGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    /**
     * 修改商品
     *
     * @param goods
     * @return
     */
    @Override
    public int updateGoods(Goods goods) {
        return goodsMapper.updateById(goods);
    }

    /**
     * 删除商品
     *
     * @param id 根据id删除
     * @return
     */
    @Override
    public int deleteGoods(int id) {
        return goodsMapper.deleteById(id);
    }


}

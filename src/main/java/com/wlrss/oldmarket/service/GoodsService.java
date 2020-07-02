package com.wlrss.oldmarket.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlrss.oldmarket.entity.Goods;

import java.util.List;

/**
 * 商品service
 *
 * @author jamesBond
 */
public interface GoodsService {

    /**
     * 查找全部
     *
     * @return
     */
    IPage<Goods> findAll();

    /**
     * 增加商品
     *
     * @param goods
     * @return
     */
    int insertGoods(Goods goods);

    /**
     * 修改商品
     *
     * @param goods
     * @return
     */
    int updateGoods(Goods goods);

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    int deleteGoods(int id);
}

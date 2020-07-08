package com.wlrss.oldmarket.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlrss.oldmarket.entity.vo.GoodsList;

import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/8/008 10:22
 */
public interface GoodsListService {

    IPage<GoodsList> findAll(Page<GoodsList> page, Integer state);
}

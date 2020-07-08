package com.wlrss.oldmarket.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlrss.oldmarket.entity.vo.GoodsList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/8/008 10:18
 */
@Repository
public interface GoodsListMapper {

    IPage<GoodsList> findAll(Page<GoodsList> page,Integer state);
}

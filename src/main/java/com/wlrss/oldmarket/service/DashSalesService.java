package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.vo.DashSales;

import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/7/007 14:24
 */
public interface DashSalesService {
    List<DashSales> findAll();

    List<DashSales> findAllByDateUpDesc();

    List<DashSales> findAllByDateDownDesc();

    List<DashSales> findAllByPriceDesc();

    List<DashSales> findAllByPriceAsc();

    List<DashSales> findAllByName(String goodsname);

}

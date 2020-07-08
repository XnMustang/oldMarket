package com.wlrss.oldmarket.service.impl;

import com.wlrss.oldmarket.entity.vo.DashSales;
import com.wlrss.oldmarket.mapper.DashSalesMapper;
import com.wlrss.oldmarket.service.DashSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/7/007 14:25
 */
@Service
public class DashSalesServiceImpl implements DashSalesService {

    @Autowired
    DashSalesMapper dashSalesMapper;

    @Override
    public List<DashSales> findAll() {
        return dashSalesMapper.findAll();
    }

    @Override
    public List<DashSales> findAllByDateUpDesc() {

        return dashSalesMapper.findAllByDateUpDesc();
    }

    @Override
    public List<DashSales> findAllByDateDownDesc() {
        return dashSalesMapper.findAllByDateDownDesc();
    }

    @Override
    public List<DashSales> findAllByPriceDesc() {
        return dashSalesMapper.findAllByPriceDesc();
    }

    @Override
    public List<DashSales> findAllByPriceAsc() {
        return dashSalesMapper.findAllByPriceAsc();
    }

    @Override
    public List<DashSales> findAllByName(String goodsname) {
        return dashSalesMapper.findAllByName(goodsname);
    }
}

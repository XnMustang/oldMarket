package com.wlrss.oldmarket.mapper;

import com.wlrss.oldmarket.entity.vo.DashSales;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/7/007 14:22
 */
@Repository
public interface DashSalesMapper {

    List<DashSales> findAll();

    List<DashSales> findAllByDateUpDesc();

    List<DashSales> findAllByDateDownDesc();

    List<DashSales> findAllByPriceDesc();

    List<DashSales> findAllByPriceAsc();

    List<DashSales> findAllByName(String goodsname);
}

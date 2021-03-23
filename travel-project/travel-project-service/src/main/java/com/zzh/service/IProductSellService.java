package com.zzh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzh.entity.ProductSell;
import com.zzh.vo.PriceCalendar;

import java.util.List;

/**
 *  服务类
 */
public interface IProductSellService extends IService<ProductSell> {

    public List<PriceCalendar> getPriCal(String pid);

}

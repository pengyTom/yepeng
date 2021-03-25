package com.zzh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzh.dao.ProductSellMapper;
import com.zzh.entity.ProductSell;
import com.zzh.service.IProductSellService;
import com.zzh.vo.PriceCalendar;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  服务实现类
 */
@Service
public class ProductSellServiceImpl extends ServiceImpl<ProductSellMapper, ProductSell> implements IProductSellService {

    @Override
    public List<PriceCalendar> getPriCal(String pid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.eq("pid",pid);
        //库存大于0
        entityWrapper.gt("stock",0);
        //日期排序
        entityWrapper.orderBy("start_date",true);
        List<ProductSell> productSellList=selectList(entityWrapper);
        System.out.println("before splicing:"+productSellList);
        return splicing(productSellList);
    }

    //拼装返回vo 前端进行数据展示的数据
    private List<PriceCalendar> splicing(List<ProductSell> productSellList){
        List<PriceCalendar> priceCalendars=new ArrayList<>();
        for (ProductSell productSell:productSellList){
            PriceCalendar priceCalendar=new PriceCalendar(productSell.getStartDate(),productSell.getpPrice(),productSell.getId());
            priceCalendar.setDate(new Date());
            priceCalendars.add(priceCalendar);
        }
        System.out.println("after splicing:"+priceCalendars);
        return priceCalendars;
    }

}

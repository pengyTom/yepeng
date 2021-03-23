package com.zzh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzh.common.ServerResponse;
import com.zzh.entity.ProductOrder;
import com.zzh.entity.ProductSell;

import java.util.List;

/**
 *  服务类
 */
public interface IProductOrderService extends IService<ProductOrder> {

    public ServerResponse createOrder(ProductOrder productOrder, ProductSell productSell )throws Exception;

    public List myOrderList(String uid);
}

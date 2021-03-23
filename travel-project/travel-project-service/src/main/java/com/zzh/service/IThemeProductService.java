package com.zzh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzh.entity.ThemeProduct;

import java.util.List;

/**
 *  服务类
 */
public interface IThemeProductService extends IService<ThemeProduct> {

    public List<ThemeProduct>selectByPid(String pid);

}

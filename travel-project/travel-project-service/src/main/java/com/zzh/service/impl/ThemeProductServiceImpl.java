package com.zzh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzh.dao.ThemeProductMapper;
import com.zzh.entity.ThemeProduct;
import com.zzh.service.IThemeProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 */
@Service
public class ThemeProductServiceImpl extends ServiceImpl<ThemeProductMapper, ThemeProduct> implements IThemeProductService {

    @Override
    public List<ThemeProduct> selectByPid(String pid) {

        return selectList(new EntityWrapper<ThemeProduct>().eq("product_id",pid));
    }
}

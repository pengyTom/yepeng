package com.zzh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzh.dao.AreaMapper;
import com.zzh.entity.Area;
import com.zzh.service.IAreaService;
import org.springframework.stereotype.Service;

/**
 * 地区码表 服务实现类
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Override
    public Integer selectIdByAreaName(String areaName) {
        //条件构造器--sql语句雏形
        EntityWrapper<Area> entityWrapper=new EntityWrapper();
        //拼接where条件,映射对应的column
        entityWrapper.eq("areaName",areaName);
        Area area=selectOne(entityWrapper);
        if (null==area){
            return null;
        }
        return area.getAreaId();
    }
}

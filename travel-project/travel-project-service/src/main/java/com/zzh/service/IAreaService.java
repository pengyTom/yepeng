package com.zzh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzh.entity.Area;

/**
 * 地区码表 服务类
 */
public interface IAreaService extends IService<Area> {

    /**
     * 根据码表中地区名查询地区编码
     * @param areaName
     * @return
     */
    public Integer selectIdByAreaName(String areaName);

}

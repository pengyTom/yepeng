package com.zzh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzh.entity.Theme;

import java.util.List;
import java.util.Map;

/**
 *  服务类
 */
public interface IThemeService extends IService<Theme> {

    public String selectIdByName(String themeName);

    public List<Map<String,String>> listorderBythemeName();

}

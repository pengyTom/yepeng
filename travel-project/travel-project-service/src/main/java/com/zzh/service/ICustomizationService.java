package com.zzh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzh.entity.Customization;

import java.util.List;

/**
 *  个性化服务类
 */
public interface ICustomizationService extends IService<Customization> {


    public List<Customization> selectListByUid(String uid);

}

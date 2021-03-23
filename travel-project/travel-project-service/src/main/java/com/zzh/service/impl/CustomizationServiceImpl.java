package com.zzh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzh.dao.CustomizationMapper;
import com.zzh.entity.Customization;
import com.zzh.service.ICustomizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *  个性化定制服务出现
 *  生成对应的实例对象，然后对这个实体对象进行操作，实体对象映射了对应的表，从而达到操作表的效果
 */
@Service
public class CustomizationServiceImpl extends ServiceImpl<CustomizationMapper, Customization> implements ICustomizationService {

    @Override
    public List<Customization> selectListByUid(String uid) {
        //根据用户Id查询列表
       return selectList(new EntityWrapper<Customization>().eq("uid",uid));
    }
}

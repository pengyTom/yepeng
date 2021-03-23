package com.zzh.controller;


import com.zzh.entity.ThemeProduct;
import com.zzh.service.IThemeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *  前端控制器
 */
@Controller
@RequestMapping("/themeProduct")
public class ThemeProductController {

    @Autowired
    private IThemeProductService themeProductService;

    @ResponseBody
    @RequestMapping("/list/{pid}")
    public List<ThemeProduct> selectByPid(@PathVariable String pid){
        return themeProductService.selectByPid(pid);
    }

}


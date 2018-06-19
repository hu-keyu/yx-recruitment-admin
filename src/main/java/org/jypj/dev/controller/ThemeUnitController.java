package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jypj.dev.entity.ThemeUnit;
import javax.annotation.Resource;
import org.jypj.dev.service.ThemeUnitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ThemeUnit控制器
 * 招聘主题上报单位
 * @author
 *
 */
@Controller
@RequestMapping("/dg/themeUnit")
public class ThemeUnitController {
	
    @Resource 
    private ThemeUnitService themeUnitService;
    

    

}
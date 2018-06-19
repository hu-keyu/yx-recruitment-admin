package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jypj.dev.entity.StudentEduInfo;
import javax.annotation.Resource;
import org.jypj.dev.service.StudentEduInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * StudentEduInfo控制器
 * 考生学历信息表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/studentEduInfo")
public class StudentEduInfoController {
	
    @Resource 
    private StudentEduInfoService studentEduInfoService;
    

    

}
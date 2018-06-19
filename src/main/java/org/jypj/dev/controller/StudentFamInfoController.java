package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jypj.dev.entity.StudentFamInfo;
import javax.annotation.Resource;
import org.jypj.dev.service.StudentFamInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * StudentFamInfo控制器
 * 学生家庭情况信息
 * @author
 *
 */
@Controller
@RequestMapping("/dg/studentFamInfo")
public class StudentFamInfoController {
	
    @Resource 
    private StudentFamInfoService studentFamInfoService;
    

    

}
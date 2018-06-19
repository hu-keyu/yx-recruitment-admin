package org.jypj.dev.controller;

import javax.annotation.Resource;

import org.jypj.dev.service.GradeAdjustLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * GradeAdjustLog控制器
 * @author ChenYu
 *
 */
@Controller
@RequestMapping("/dg/gradeAdjustLog")
public class GradeAdjustLogController {
	
    @Resource 
    private GradeAdjustLogService gradeAdjustLogService;
    
    @RequestMapping(value="toGradeAdjustLogList")
    public String toGradeAdjustLogList(){
    	return "";
    }
    
}
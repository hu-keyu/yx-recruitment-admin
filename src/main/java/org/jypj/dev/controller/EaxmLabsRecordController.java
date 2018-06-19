package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jypj.dev.entity.EaxmLabsRecord;
import javax.annotation.Resource;
import org.jypj.dev.service.EaxmLabsRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * EaxmLabsRecord控制器
 * 试室分配考生记录表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/eaxmLabsRecord")
public class EaxmLabsRecordController {
	
    @Resource 
    private EaxmLabsRecordService eaxmLabsRecordService;
    

    

}
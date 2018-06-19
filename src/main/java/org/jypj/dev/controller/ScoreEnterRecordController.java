package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jypj.dev.entity.ScoreEnterRecord;
import javax.annotation.Resource;
import org.jypj.dev.service.ScoreEnterRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ScoreEnterRecord控制器
 * 入围情况记录表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreEnterRecord")
public class ScoreEnterRecordController {
	
    @Resource 
    private ScoreEnterRecordService scoreEnterRecordService;
    

    

}
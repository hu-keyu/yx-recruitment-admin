package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jypj.dev.entity.Attachement;
import javax.annotation.Resource;
import org.jypj.dev.service.AttachementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Attachement控制器
 * 上传附件信息表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/attachement")
public class AttachementController {
	
    @Resource 
    private AttachementService attachementService;
    

    

}
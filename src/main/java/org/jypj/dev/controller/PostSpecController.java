package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jypj.dev.entity.PostSpec;
import javax.annotation.Resource;
import org.jypj.dev.service.PostSpecService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * PostSpec控制器
 * 岗位专业
 * @author
 *
 */
@Controller
@RequestMapping("/dg/postSpec")
public class PostSpecController {
	
    @Resource 
    private PostSpecService postSpecService;
    

    

}
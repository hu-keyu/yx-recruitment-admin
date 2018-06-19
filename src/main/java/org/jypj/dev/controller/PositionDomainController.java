package org.jypj.dev.controller;

import javax.annotation.Resource;

import org.jypj.dev.service.PositionDomainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PositionDomain控制器
 * 学校岗位专业要求表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/positionDomain")
public class PositionDomainController {
	
    @Resource 
    private PositionDomainService positionDomainService;
    

    

}
package org.jypj.dev.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Information;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.PositionDomain;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.Specialty;
import org.jypj.dev.entity.StudentEduInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.service.AttachementService;
import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.service.InformationService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PositionDomainService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.SecurityQueInfoService;
import org.jypj.dev.service.SpecialtyService;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.service.StudentEduInfoService;
import org.jypj.dev.service.StudentFamInfoService;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.StringUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 考生报考自动审核控制器
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/dg/ApplyRule")
public class ApplyRuleController {
    @Resource
    private StudentApplyInfoService studentApplyInfoService;
    
    @Resource
    private StudentInfoService studentInfoService;
    
    @Resource
    private StudentFamInfoService studentFamInfoService;
    
    @Resource
    private StudentEduInfoService studentEduInfoService;
    
    @Resource
    private SecurityQueInfoService securityQueInfoService;

    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private ThemeService themeService;
    
    @Resource
    private AttachementService attachementService;
    
    @Resource
    private SpecialtyService specialService;
    
    @Resource
    private NoticeService noticeService;
    
    @Resource
    private PostsetService postsetService;
    
    @Resource
    private PositionService positionService;
    
    @Resource
    private PositionDomainService positionDomainService;
    
    @Resource
    private InformationService informationService;
    
    /**
     * 招聘主题id
     */
    private String recruitId = "";

    /**
     * 考生id
     */
    private String sid = "";
    
    
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
    
    
   

}

package org.jypj.dev.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jypj.dev.entity.Theme;
import org.jypj.dev.service.ExamSubjectInfoService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.service.VolunteersService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 考生报考自动审核控制器
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/dg/volunteerStatistics")
public class VolunteerController {
    @Resource
    private ExamSubjectInfoService examSubjectInfoService;
    @Resource
    private ThemeService themeService;
    @Resource
    private VolunteersService volunteersService;
    
    @RequestMapping("toVolunteerStatistics")
    public String toVolunteerStatistics(Model model, HttpSession session) {
        // 年月日
        List<String> nyrdics = examSubjectInfoService.queryThemeDate();
        model.addAttribute("nyrdics", nyrdics);
        // 招聘项目
        List<Theme> themelist = themeService.selectAllByTheme(new Theme());
        model.addAttribute("themelist", themelist);
        return "statistics/volunteer_statistics.vm";
    }
    
    @ResponseBody
    @RequestMapping(value="volunteerStatisticsSearch",method=RequestMethod.POST)
    public String volunteerStatisticsSearch(Page page){
        Map<String, Object> condition = page.getCondition();
        if(condition.get("schoolName") != null && !"".equals(condition.get("schoolName").toString())) {
            condition.put("schoolName", condition.get("schoolName").toString().replaceAll(" ", ""));
        }
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        page = volunteersService.searchVolunteerPageByMap(page, condition);
        jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        return JSONObject.toJSON(jsonMap).toString();
    }
    
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }

}

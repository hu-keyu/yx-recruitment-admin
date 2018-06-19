package org.jypj.dev.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.entity.Information;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.InformationService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PlanApplyService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
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
 * Information控制器
 * @author QiCai
 *
 */
@Controller
@RequestMapping("/dg/information")
public class InformationController {
	
    @Resource 
    private InformationService informationService;
    @Resource 
    private PlanApplyService planApplyService;
    @Resource 
    private ThemeService themeService;
    @Resource
    private PositionService positionService;
    @Resource
    private NoticeService noticeService;
    
    
    @RequestMapping(value="informationList")
    public String informationList(HttpSession session,Model model){
    	User user=(User)session.getAttribute("user");
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("schoolId", user.getSchoolId());//当前登录用户学校ID
    	queryParameter.put("isPublish", "1");//招聘公告已发布
    	queryParameter.put("status", "2");//招聘计划已审核
    	queryParameter.put("themeStatus", "1");//招聘主题已发布
    	List<String> years=planApplyService.getPlanApplyYears(queryParameter);//当前登录用户学校ID
    	model.addAttribute("years", years);
    	return "/information/information_list.vm";
    }
    
    @ResponseBody
    @RequestMapping(value="getThemes",method=RequestMethod.POST)
    public List<Theme> getThemes(String year,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("schoolId", user.getSchoolId());//当前登录用户学校ID
    	queryParameter.put("isPublish", "1");//招聘主题已发布
    	queryParameter.put("status", "2");//招聘计划已审核
    	queryParameter.put("year", year);//招聘主题已发布
    	List<Theme> themes=themeService.selectAllByMap(queryParameter);
    	return themes;
    }
    
    @RequestMapping(value="gotoInformation")
    public String gotoInformation(HttpSession session,String id,String projectId,String type,Model model){
    	User user=(User)session.getAttribute("user");
    	Information information=new Information();
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	if(StringUtils.isNotBlank(id)){
    		information=informationService.selectInformationById(id);
    	}else{
    		queryParameter.put("informationPositionId", "informationPositionId");
    	}
    	if(StringUtils.isNotBlank(projectId)){
    		information.setProjectId(projectId);
    	}
    	if(StringUtils.isNotBlank(type)){
    		information.setType(type);
    	}
    	queryParameter.put("pstatus", "1");//启用的岗位
    	queryParameter.put("astatus", "2");//已审核
    	queryParameter.put("isPublish", "1");//已发布
    	queryParameter.put("projectId", projectId);
    	queryParameter.put("schoolId", user.getSchoolId());//当前登录用户学校ID
    	List<Position> positions=positionService.queryValidPosition(queryParameter);
    	Theme theme=themeService.selectThemeById(information.getProjectId());
    	
    	Notice notice=new Notice();
    	notice.setThemeId(information.getProjectId());
    	Notice themeNotice=noticeService.selectObjectByNotice(notice);
    	model.addAttribute("theme", theme);
    	model.addAttribute("positions", positions);
    	model.addAttribute("themeNotice", themeNotice);
    	model.addAttribute("information", information);
    	return "/information/information_input.vm";
    }
    
    @ResponseBody
    @RequestMapping(value="saveInformation",method=RequestMethod.POST)
    public JSONObject savePosition(Information information,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		informationService.saveInformation(information,user,jsonMap);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	return jsonMap;
    }
    
    @ResponseBody
    @RequestMapping(value="updateInformation",method=RequestMethod.POST)
    public JSONObject updateInformation(Information information,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		informationService.updateInformationOper(information,user,jsonMap);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
			e.printStackTrace();
		}
    	return jsonMap;
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllInformation",method=RequestMethod.POST)
	public String selectAllInformation(Page page,HttpSession session) {
    	User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		page = informationService.selectOnePageByMap(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @ResponseBody
	@RequestMapping(value="deleteInformation",method=RequestMethod.POST)
    public JSONObject deleteInformation(String chk){
    	JSONObject jsonMap=new JSONObject();
    	try {
    		informationService.deleteInformationOper(chk,jsonMap);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	return jsonMap;
    }
    
    @ResponseBody
	@RequestMapping(value="publishInformation",method=RequestMethod.POST)
    public JSONObject publishInformation(String id,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		informationService.publishInformationOper(id,jsonMap,user);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	return jsonMap;
    }
    
    @ResponseBody
	@RequestMapping(value="checkInformation",method=RequestMethod.POST)
    public List<Information> checkInformation(String projectId,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	Information information=new Information();
    	information.setType("2");
    	information.setSchoolId(user.getSchoolId());
    	if(StringUtils.isNotBlank(projectId)){
    		information.setProjectId(projectId);
    	}
    	return informationService.selectAllByInformation(information);
    }
    
    @InitBinder  
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
}
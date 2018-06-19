package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterStudyService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreEnterStudy控制器
 * 考察入围表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreEnterStudy")
public class ScoreEnterStudyController {
	
    @Resource 
    private ScoreEnterStudyService scoreEnterStudyService;
    @Resource
	private ThemeService themeService;
    @Resource
   	private NoticeService noticeService;
    
    //考察入围名单列表
    @ResponseBody
    @RequestMapping(value="enterStudyList",method=RequestMethod.POST)
    public String enterStudyList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Integer step=0;//9、发布体检成绩并生成了考察的名单 10、发布考察名单 11、发布考察成绩
    	Integer isScore=9;//成绩是否发布
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	if(StringUtils.isNotEmpty(type)&&type.equals("5")){
    		step=themeService.selectStep((String)condition.get("projectId"));//项目进行的环节
    		//if(step>=9){
				condition.put("isEnter", "1");//1、入围2、未入围
				page=scoreEnterStudyService.selectStudyEnterPageByMap(page, condition);
    		//}	
			Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
			if(notice!=null){
				isScore=notice.getLookScorePublish();
			}	
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("step", step);
        jsonMap.put("isScore", isScore);
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //调整名单列表
    @ResponseBody
    @RequestMapping(value="ajustStudyList",method=RequestMethod.POST)
    public String ajustStudyList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	if(StringUtils.isNotEmpty(type)&&type.equals("5")){
			condition.put("isEnter", "2");//1、入围2、未入围
			page=scoreEnterStudyService.selectStudyEnterPageByMap(page, condition);
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("ispublish", "1");//已发布
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //调入当前名单和删除名单
    @ResponseBody
    @RequestMapping(value="updateStudyEnter",method=RequestMethod.POST)
    public JSONObject updateStudyEnter(String flag,String chk,String projectId,String positionid,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try{
    		scoreEnterStudyService.enterStudylist(flag, chk, projectId, positionid, user, jsonMap);
	    	jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
			return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			return jsonMap;
		}
    }
    
    //发布名单
    @ResponseBody
    @RequestMapping(value="publishStudyList",method=RequestMethod.POST)
    public JSONObject publishStudyList(HttpSession session,Page page){
    	User user=(User)session.getAttribute("user");
    	Map<String, Object> condition =page.getCondition();
    	JSONObject jsonMap=new JSONObject();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	if(user == null){
 			jsonMap.put("flag", "false");
    		jsonMap.put("msg", "获取用户数据为空！");
 		}
    	Integer step=themeService.selectStep((String)condition.get("projectId"));//9、发布体检成绩并生成了考察的名单 10、发布考察名单 11、发布考察成绩
    	try {
    		if(step==9){
				String msg=scoreEnterStudyService.addBatchStudy(condition, page,user);
				if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("false")){
					 jsonMap.put("flag", "false");
		    		 jsonMap.put("msg", "入围名单为空！");
				}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("success")){
					 jsonMap.put("flag", "success");
			    	 jsonMap.put("msg", "名单发布成功！");
				}
    		}else{
    			jsonMap.put("flag", "error");
    			jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
    //考察成绩查询
    @ResponseBody
    @RequestMapping(value="enterStudySearch",method=RequestMethod.POST)
    public String enterStudySearch(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Integer listPub=9;//名单是否发布
    	Integer isScore=9;//成绩是否发布
    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
    	if(notice!=null){
    		listPub=notice.getLookListPublish();//名单发布后才会显示成绩查询中的考察名单
    		isScore=notice.getLookScorePublish();
    	}	
    	if(StringUtils.isNotEmpty(type)&&type.equals("5")&&listPub==1){
			condition.put("isEnter", "1");//1、入围2、未入围
			page=scoreEnterStudyService.selectStudyEnterPageByMap(page, condition);
    	}else{
    		condition.put("isEnter", "1111");//不查成绩
			page=scoreEnterStudyService.selectStudyEnterPageByMap(page, condition);
    	}
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("isScore", isScore);
		return JSONObject.toJSON(jsonMap).toString();
    }
}
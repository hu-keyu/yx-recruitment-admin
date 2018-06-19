package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterWritienService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreEnterWritien控制器
 * 统一笔试入围表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreEnterWritien")
public class ScoreEnterWritienController {
	
    @Resource 
    private ScoreEnterWritienService scoreEnterWritienService;
    @Resource
	private ThemeService themeService;
    @Resource
   	private NoticeService noticeService;
    
    //统一笔试入围名单列表
    @ResponseBody
    @RequestMapping(value="enterWritienList",method=RequestMethod.POST)
    public String enterWritienList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	Integer step=0;//3、发布面试成绩并生成了统一笔试的名单4、发布笔试名单 5、发布笔试成绩
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
    		jsonMap.put("flag", "false");
   		 	jsonMap.put("msg", "招聘项目ID为空！");
    	}
    	Integer ispublish=9;//名单是否发布
    	Integer isScore=9;//成绩是否发布
    	if(StringUtils.isNotEmpty(type)&&type.equals("2")){
			step=themeService.selectStep((String)condition.get("projectId"));//项目进行的环节
			//if(step>=3){
				condition.put("isEnter", "1");//1、入围2、未入围
				page=scoreEnterWritienService.selectWritienEnterPageByMap(page, condition);
				Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
				if(notice!=null){
					ispublish=notice.getWrittenListPublish();
					isScore=notice.getWrittenScorePublish();
				}	
			//}	
    	}
    	jsonMap.put("ispublish", ispublish);
    	jsonMap.put("isScore", isScore);
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("step", step);
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //调整名单列表
    @ResponseBody
    @RequestMapping(value="ajustList",method=RequestMethod.POST)
    public String ajustList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
    		jsonMap.put("flag", "false");
   		 	jsonMap.put("msg", "招聘项目ID为空！");
    	}
    	if(StringUtils.isNotEmpty(type)&&type.equals("2")){
			condition.put("isEnter", "2");//1、入围2、未入围
			page=scoreEnterWritienService.selectWritienEnterPageByMap(page, condition);
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("ispublish", "1");//已发布
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //调入当前名单和删除名单
    @ResponseBody
    @RequestMapping(value="updateEnter",method=RequestMethod.POST)
    public JSONObject updateEnter(String flag,String chk,String projectId,String positionid,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try{
			scoreEnterWritienService.enterlist(flag, chk, projectId, positionid, user, jsonMap);
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
    @RequestMapping(value="publishList",method=RequestMethod.POST)
    public JSONObject publishList(HttpSession session,Page page){
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
    	Integer step=themeService.selectStep((String)condition.get("projectId"));//3、发布面试成绩并生成了统一笔试的名单4、发布笔试名单 5、发布笔试成绩
    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
    	try {
    		if(step>=3){
    			Integer writienList=9;
    			if(notice!=null){
    				writienList=notice.getWrittenListPublish();
    			}	
	    		if(writienList==0){
					String msg=scoreEnterWritienService.addBatchWritien(condition, page,user);
					if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("false")){
						 jsonMap.put("flag", "error");
			    		 jsonMap.put("msg", "入围名单为空！");
					}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("success")){
						 jsonMap.put("flag", "success");
				    	 jsonMap.put("msg", "名单发布成功！");
					}
	    		}else{
	    			jsonMap.put("flag", "error");
	    			jsonMap.put("msg", "名单已发布！操作失败，请稍后重试！");
	    		}
    		}else{
				jsonMap.put("flag", "error");
				jsonMap.put("msg", "操作流程步骤不对，请稍后重试！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
    //统一笔试成绩查询
    @ResponseBody
    @RequestMapping(value="writienSearch",method=RequestMethod.POST)
    public String writienSearch(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
    	Integer listPub=9;//名单是否发布
    	Integer isScore=9;//成绩是否发布
    	if(notice!=null){
    		listPub=notice.getWrittenListPublish();//名单发布后才会显示成绩查询中的统一笔试名单
    		isScore=notice.getWrittenScorePublish();
    	}	
    	if(StringUtils.isNotEmpty(type)&&type.equals("2")&&listPub==1){
    		condition.put("isEnter", "1");//1、入围2、未入围
			page=scoreEnterWritienService.selectWritienEnterPageByMap(page, condition);
    	}else{
    		condition.put("isEnter", "1111");//不显示成绩
			page=scoreEnterWritienService.selectWritienEnterPageByMap(page, condition);
    	}
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("isScore", isScore);
		return JSONObject.toJSON(jsonMap).toString();
    }
    
}
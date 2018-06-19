package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterPhysicalService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreEnterPhysical控制器
 * 体检入围表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreEnterPhysical")
public class ScoreEnterPhysicalController {
	
    @Resource 
    private ScoreEnterPhysicalService scoreEnterPhysicalService;
    @Resource
	private ThemeService themeService;
    @Resource
   	private NoticeService noticeService;
    
    //体检入围名单列表
    @ResponseBody
    @RequestMapping(value="enterPhysicalList",method=RequestMethod.POST)
    public String enterPhysicalList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	Integer step=0;//7、发布试讲成绩并生成了体检的名单8、发布体检名单 9、发布体检成绩
    	Integer isScore=9;//成绩是否发布
    	if(StringUtils.isNotEmpty(type)&&type.equals("4")){
			step=themeService.selectStep((String)condition.get("projectId"));//项目进行的环节
			//if(step>=7){
			Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
			if(notice!=null){
				isScore=notice.getBodyexamScorePublish();
			}
			condition.put("isEnter", "1");//1、入围2、未入围
			page=scoreEnterPhysicalService.selectPhysicalEnterPageByMap(page, condition);
			//}	
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
    @RequestMapping(value="ajustPhysicalList",method=RequestMethod.POST)
    public String ajustPhysicalList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	if(StringUtils.isNotEmpty(type)&&type.equals("4")){
			condition.put("isEnter", "2");//1、入围2、未入围
			page=scoreEnterPhysicalService.selectPhysicalEnterPageByMap(page, condition);
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("ispublish", "1");//已发布
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //调入当前名单和删除名单
    @ResponseBody
    @RequestMapping(value="updatePhysicalEnter",method=RequestMethod.POST)
    public JSONObject updatePhysicalEnter(String flag,String chk,String projectId,String positionid,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try{
    		scoreEnterPhysicalService.enterPhysicallist(flag, chk, projectId, positionid, user, jsonMap);
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
    @RequestMapping(value="publishPhysicalList",method=RequestMethod.POST)
    public JSONObject publishPhysicalList(HttpSession session,Page page){
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
    	Integer step=themeService.selectStep((String)condition.get("projectId"));//7、发布试讲成绩并生成了体检的名单8、发布体检名单 9、发布体检成绩
    	try {
    		if(step==7){
				String msg=scoreEnterPhysicalService.addBatchPhysical(condition, page,user);
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
    
    //体检成绩查询
    @ResponseBody
    @RequestMapping(value="enterPhysicalSearch",method=RequestMethod.POST)
    public String enterPhysicalSearch(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Integer listPub=9;//名单是否发布
    	Integer isScore=9;//成绩是否发布
    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
    	if(notice!=null){
    		listPub=notice.getBodyexamListPublish();//名单发布后才会显示成绩查询中的体检名单
    		isScore=notice.getBodyexamScorePublish();
    	}
    	if(StringUtils.isNotEmpty(type)&&type.equals("4")&&listPub==1){
			condition.put("isEnter", "1");//1、入围2、未入围
			page=scoreEnterPhysicalService.selectPhysicalEnterPageByMap(page, condition);
    	}else{
    		condition.put("isEnter", "1111");//不能查到数据
    		page=scoreEnterPhysicalService.selectPhysicalEnterPageByMap(page, condition);
    	}
    	
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("isScore", isScore);
		return JSONObject.toJSON(jsonMap).toString();
    }
}
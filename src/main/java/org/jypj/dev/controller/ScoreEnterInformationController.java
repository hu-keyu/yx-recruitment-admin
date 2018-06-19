package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Grade;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.ScoreEnterInformation;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jypj.dev.service.ExamSubjectInfoService;
import org.jypj.dev.service.GradeService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.ScoreEnterInformationService;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreEnterInformation控制器
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreEnterInformation")
public class ScoreEnterInformationController {
	
    @Resource 
    private ScoreEnterInformationService scoreEnterInformationService;
    @Resource
	private ThemeService themeService;
    @Resource
   	private GradeService gradeService;
    @Resource
   	private PositionService positionService;
    @Resource
   	private StudentApplyInfoService studentApplyInfoService;
    @Resource
   	private ExamSubjectInfoService examSubjectInfoService;
    @Resource
   	private NoticeService noticeService;
    
    @RequestMapping(value="toScoreInformationList")
    public String toExamItemsInfoList(Model model){
    	// 年月日
    	List<String> nyrdics=examSubjectInfoService.queryThemeDate();
		model.addAttribute("nyrdics", nyrdics);

		// 考试类型
		List<Dictionary> kslxdics = DictionaryCache.getDictionaryByCode("kslx");
		model.addAttribute("kslxdics", kslxdics);

		// 招聘项目
		List<Theme> themelist = themeService.selectAllByTheme(new Theme());
		model.addAttribute("themelist", themelist);
    	return "/scoreinformation/score_info_list.vm";
    }
    
    //名单列表
    @ResponseBody
    @RequestMapping(value="scoreInformationList",method=RequestMethod.POST)
    public String scoreInformationList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	//考试类型为"1"是进入单位面试名单
    	Integer step=0;
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isNotEmpty(type)&&type.equals("1")){
    		step=themeService.selectStep((String)condition.get("projectId"));//1、报名完成2、发布面试名单3、面试成绩发布
    		if(step==0){
    			condition.put("status", "6");//6、简历审核通过状态
	    		Integer count=studentApplyInfoService.selectStatus(condition);
	    		if(count>0){
	    			page=studentApplyInfoService.selectPageEnter(page, condition);//查询进入单位面试环节的学生
	    			jsonMap.put("step", step);//已发布
	    		}else{
	    			 jsonMap.put("step", null);//已发布
	    			 jsonMap.put("msg", "暂无面试名单");
	    		}
    		/*}else if(step==2){
    				condition.put("isEnter", "1");//1、入围2、未入围
    				page = scoreEnterInformationService.selectEnterPageByMap(page, condition);
    				jsonMap.put("step", step);//已发布*/ 
    		}else if(step>=2){
    				page = scoreEnterInformationService.selectGradePageByMap(page, condition);
    				jsonMap.put("step", step);//已发布
    		}else{
    			jsonMap.put("step", "null");//已发布
    			jsonMap.put("msg", "暂无数据！");
    		}
    	}
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
    }
    
  //名单列表
    @ResponseBody
    @RequestMapping(value="scoreEnter",method=RequestMethod.POST)
    public String scoreEnter(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
		page = scoreEnterInformationService.selectOnePageByMap(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("typestatus", "12");
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //根据年份查询招聘项目
    @ResponseBody
    @RequestMapping(value="selectProject")
    public String selectProject(String ryn){
    	JSONObject jo=new JSONObject();
    	 try {
    		 if(StringUtils.isBlank(ryn)){
    			 jo.put("flag", false);
    			 jo.put("msg", "年份为空！");
    		 }else{
		    	 //List<Theme> themeList=themeService.selectAllByYear(ryn);
		    	 Map<String, Object> map = new HashMap<String, Object>();
		    	 map.put("status", "1");
			 	 map.put("year", ryn);
			 	 List<Theme> themeList = themeService.selectAllByYearMap(map);
		    	 jo.put("flag", true);
		         jo.put("themeList",themeList);
    		 } 
	     } catch (Exception e) {
	         e.printStackTrace();
	         jo.put("flag", false);
	         jo.put("msg", "年份为空！");
	     }
    	 return jo.toJSONString();
    }
    
  //根据招聘项目查询岗位
    @ResponseBody
    @RequestMapping(value="selectPosition")
    public String selectPosition(String themeid,String year){
    	JSONObject jo=new JSONObject();
    	 try {
    		 if(StringUtils.isBlank(year)){
    			 jo.put("flag", false);
    			 jo.put("msg", "年份为空！");
    			 return jo.toJSONString();
    		 }
    		 if(StringUtils.isBlank(themeid)){
    			 jo.put("flag", false);
    			 jo.put("msg", "招聘项目ID为空！");
    			 return jo.toJSONString();
    		 }
	    	 List<Position> positionList=positionService.selectByPosition(year, themeid);
	    	 jo.put("flag", true);
	         jo.put("positionList",positionList);
	         return jo.toJSONString();
	     } catch (Exception e) {
	         e.printStackTrace();
	         jo.put("flag", false);
	         jo.put("positionList", "positionList");
	         return jo.toJSONString();
	     }
    }
    
  //发布名单
    @ResponseBody
    @RequestMapping(value="publishorAjustList")
    public JSONObject publishorAjustList(HttpSession session,Page page){
    	User user=(User)session.getAttribute("user");
    	Map<String, Object> condition =page.getCondition();
    	JSONObject jsonMap=new JSONObject();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
    		jsonMap.put("flag", "false");
   		 	jsonMap.put("msg", "招聘项目ID为空！");
    	}
    	if(user == null){
 			jsonMap.put("flag", "false");
    		jsonMap.put("msg", "获取用户数据为空！");
 		}
    	try {
    	    Integer step=themeService.selectStep((String)condition.get("projectId"));//1、报名完成2、发布面试名单3、面试成绩发布
    	    if(step==0){
    	    	//查询名单是否审核完
    	    	Integer count=studentApplyInfoService.selectListAuditorCount((String)condition.get("projectId"));
    	    	if(count==0){
	    	    	String msg=scoreEnterInformationService.addBatchScore(condition, page,user,jsonMap);
					if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("false")){
						 jsonMap.put("flag", "false");
			    		 jsonMap.put("msg", "入围名单为空！");
					}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("success")){
						 jsonMap.put("flag", "success");
				    	 jsonMap.put("msg", "名单发布成功！");
					}
    	    	}else{
    	    		jsonMap.put("flag", "false");
		    		jsonMap.put("msg", "简历名单未审核完成，审核完后才能发布名单！"); 
    	    	}
    	    }else{
    	    	jsonMap.put("flag", "error");
    			jsonMap.put("msg", "操作失败，请稍后重试！");
    	    }
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	return jsonMap;
    }
    
    @RequestMapping(value="toScoreInformationInput")
    public String toScoreInformationInput(Model model){
    	// 年月日
    	List<String> nyrdics=examSubjectInfoService.queryThemeDate();
		model.addAttribute("nyrdics", nyrdics);
		
		// 考试类型
		List<Dictionary> kslxdics = DictionaryCache.getDictionaryByCode("kslx");
		model.addAttribute("kslxdics", kslxdics);
		
		// 招聘项目
		List<Theme> themelist = themeService.selectAllByTheme(new Theme());
		model.addAttribute("themelist", themelist);
		
//		ScoreSelectEntersVo  scoreSelectEntersVo=new ScoreSelectEntersVo();
//		model.addAttribute("scoreSelectEntersVo", scoreSelectEntersVo);
    	return "/scoreinformation/score_info_input.vm";
    }
    
    //成绩录入
    @ResponseBody
    @RequestMapping(value="saveScoreInfo")
    public JSONObject saveScoreInfo(StudentInfo studentInfo,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		List<Grade> grades = studentInfo.getGrades();
    		if (grades != null) {
				for (Iterator<Grade> it = grades.iterator(); it.hasNext();) {
					Grade grade = it.next();
					if (StringUtils.isBlank(grade.getGrade())) {
						it.remove();
						continue;
					}
					grade.setProjectId("37960E8D8EDA4A3A849D3894769C7D09");//项目ID
					grade.setType("2");
				}
				gradeService.saveGradesList(grades);
    		}	
    		//scoreEnterInformationService.saveScoreEnterInformation(scoreEnterInformation);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
  //成绩录入
    @ResponseBody
    @RequestMapping(value="enrolmentScore")
    public JSONObject enrolmentScore(ScoreEnterInformation scoreEnterInformation,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		scoreEnterInformation.setCreateUser(user.getId());
    		scoreEnterInformation.setModifyUser(user.getId());
    		scoreEnterInformation.setItemsId("F371DEB7FA934CA5B7549E0DD368A915");
    		
    		scoreEnterInformationService.saveScoreEnterInformation(scoreEnterInformation);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
    //成绩查询
    @RequestMapping(value="toscoreInformationSearch")
    public String toscoreInformationSearch(Model model){
    	// 年月日
    	List<String> nyrdics=examSubjectInfoService.queryThemeDate();
		model.addAttribute("nyrdics", nyrdics);

		// 考试类型
		List<Dictionary> kslxdics = DictionaryCache.getDictionaryByCode("kslx");
		model.addAttribute("kslxdics", kslxdics);

		// 招聘项目
		List<Theme> themelist = themeService.selectAllByTheme(new Theme());
		model.addAttribute("themelist", themelist);
    	return "/scoreinformation/score_info_search.vm";
    }
    
  //名单列表
    @ResponseBody
    @RequestMapping(value="scoreInformationSearch",method=RequestMethod.POST)
    public String scoreInformationSearch(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	//考试类型为"1"是进入单位面试名单
    	Integer step=0;
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
//    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
//    	Integer listPub=notice.getInterviewListPublish();//名单发布后才会显示成绩查询中的单位面试名单
    	if(StringUtils.isNotEmpty(type)&&type.equals("1")){
    		page = scoreEnterInformationService.searchsGradePageByMap(page, condition);
    	}
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("step", step);//已发布
		return JSONObject.toJSON(jsonMap).toString();
    }
    
}
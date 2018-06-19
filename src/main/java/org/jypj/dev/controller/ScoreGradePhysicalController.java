package org.jypj.dev.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jypj.dev.entity.ScoreGradePhysical;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jypj.dev.service.ScoreGradePhysicalService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.service.impl.ScoreGradePhysicalServiceImpl;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreGradePhysical控制器
 * 体检成绩表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreGradePhysical")
public class ScoreGradePhysicalController {
	
    @Resource 
    private ScoreGradePhysicalService scoreGradePhysicalService;
    @Resource
   	private ThemeService themeService;
    private static Logger Log_=Logger.getLogger(ScoreGradePhysicalServiceImpl.class);
    //体检成绩列表
    @ResponseBody
    @RequestMapping(value="gradesPhysicalList",method=RequestMethod.POST)
    public String gradesPhysicalList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Integer step=0;
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	step=themeService.selectStep((String)condition.get("projectId"));//7、发布试讲成绩并生成了体检的名单8、发布体检名单 9、发布体检成绩
    	if(StringUtils.isNotEmpty(type)&&type.equals("4")){
    		//if(step>=8){
    			page=scoreGradePhysicalService.selectOnePageByMap(page, condition);
    		//}
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("step", step);
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //保存成绩
    @ResponseBody
    @RequestMapping(value="savePhysicalScoreInfo")
    public JSONObject savePhysicalScoreInfo(StudentInfo studentInfo,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		List<ScoreGradePhysical> gradePhysicalsList = studentInfo.getGradePhysicals();
			scoreGradePhysicalService.updatePhysicalList(gradePhysicalsList,user,jsonMap);
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
    
    //发布成绩
    @ResponseBody
    @RequestMapping(value="publishPhysicalGrade",method=RequestMethod.POST)
    public JSONObject publishPhysicalGrade(HttpSession session,Page page){
		User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		JSONObject jsonMap=new JSONObject();
		if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
		Integer step=themeService.selectStep((String)condition.get("projectId"));//7、发布试讲成绩并生成了体检的名单8、发布体检名单 9、发布体检成绩
		try {
			if(step==8){
				scoreGradePhysicalService.publishBatchPhysical(condition, page, user,jsonMap);
				jsonMap.put("flag", "success");
				jsonMap.put("msg", "成绩发布成功！");
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
    
    //取消发布成绩
    @ResponseBody
    @RequestMapping(value="celpublishPhysicals",method=RequestMethod.POST)
    public JSONObject celpublishPhysicals(HttpSession session,Page page){
		Map<String, Object> condition =page.getCondition();
		String projectId=(String)condition.get("projectId");
		JSONObject jsonMap=new JSONObject();
		if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
		Integer step=8;//设置招聘流程进行到的环节
		try {
			String msg=scoreGradePhysicalService.celpublishPhysical(projectId, step);
			if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("false")){
				 jsonMap.put("flag", "false");
	    		 jsonMap.put("msg", "取消发布成绩失败！");
			}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("success")){
				 jsonMap.put("flag", "success");
		    	 jsonMap.put("msg", "取消发布成绩成功！");
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
    
    @ResponseBody
	@RequestMapping(value="parseInterviewTemplate",produces="text/html;charset=UTF-8")
	public String parseExcel(@RequestParam("file") CommonsMultipartFile file,String projectId,
			String testType,HttpSession session) throws IOException{
    	JSONObject jsonMap=new JSONObject();
		User user=(User)session.getAttribute("user");
		try {
			if(StringUtils.isNotBlank(projectId)&&StringUtils.isNotBlank(testType)){
				scoreGradePhysicalService.saveImportScore(file,projectId,testType,user,jsonMap);
				jsonMap.put("flag", "success");
				jsonMap.put("msg", "导入文件成功！");
			}else{
				jsonMap.put("flag", "error");
				jsonMap.put("msg", "项目或考试类型为空！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			Log_.info(e.getMessage());
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	 return jsonMap.toJSONString();
	}
}
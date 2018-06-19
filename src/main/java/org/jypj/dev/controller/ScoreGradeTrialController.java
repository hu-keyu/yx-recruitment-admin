package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreGradeTrial;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.ScoreGradeTrialService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreGradeTrial控制器
 * 统一试讲成绩表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreGradeTrial")
public class ScoreGradeTrialController {
	
    @Resource 
    private ScoreGradeTrialService scoreGradeTrialService;
    @Resource
   	private ThemeService themeService;
    @Resource 
    private ScoreEnterTrialService scoreEnterTrialService;
    @Resource
  	private NoticeService noticeService ;
    
    //统一试讲成绩列表
    @ResponseBody
    @RequestMapping(value="gradesTrialList",method=RequestMethod.POST)
    public String gradesTrialList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Integer step=0;
    	Integer status=0;//1、成绩没发布2、成绩发布3、名单只发布一个学科4、都没发布
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	/*condition.put("subjectType", "0");//普通科
    	Integer comsize=scoreGradeTrialService.selectCountByMap(condition);//查询普通科
    	condition.put("subjectType", "1");//艺术科
    	Integer artsize=scoreGradeTrialService.selectCountByMap(condition);//查询艺术科
    	 */    	
    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
    	//成绩和名单是否发布
    	if(StringUtils.isNotEmpty(type)&&type.equals("3")){
    		step=themeService.selectStep((String)condition.get("projectId"));//6、发布试讲名单 5、发布试讲成绩
    		if(step>=3){
    			if(notice!=null){
	    			Integer trialListPub=notice.getLectureListPublishNor();
	    			Integer trialListArt=notice.getLectureListPublishArt();
	    			Integer trialScore=notice.getLectureScorePublishNor();
	    			Integer trialScoreArt=notice.getLectureScorePublishArt();
	    			if(trialListPub==1&&trialListArt==1){//名单都发布
	    				if(trialScore!=1&&trialScoreArt!=1){
	    					status=1;//成绩没发布
	    				}else if(trialScore==1&&trialScoreArt==1){
		    				status=2;//成绩发布
		    			}
	    			}else if(trialListPub==1&&trialListArt!=1){//普通科发布名单
	    				status=3;
	    			}else if(trialListPub!=1&&trialListArt==1){//艺术科发布名单
	    				status=3;
	    			}else if(trialListPub!=1&&trialListArt!=1){//都没发布名单
	    				status=4;
	    			}
    			}	
    			page=scoreGradeTrialService.selectOnePageByMap(page, condition);
    			/*condition.put("scoreStatus", "1");//0、未发布1、发布
				condition.put("subjectType", "0");//普通科
				Integer countCommon=scoreGradeTrialService.selectCountByMap(condition);
				
				condition.put("subjectType", "1");//艺术科
				Integer countArt=scoreGradeTrialService.selectCountByMap(condition);
				
				if(comsize>0&&artsize>0){
					if(countCommon>0&&countArt==0){
						status= 1;//普通科发布
						condition.put("isScore", "0");//0、未发布1、发布
						page=scoreGradeTrialService.selectOnePageByMap(page, condition);
					}else if(countCommon==0&&countArt>0){
						status= 2;//艺术科发布
						condition.put("isScore", "0");//0、未发布1、发布
						page=scoreGradeTrialService.selectOnePageByMap(page, condition);
					}else if(countCommon==0&&countArt==0){
						status= 3;//未发布（两个学科）
						page=scoreGradeTrialService.selectOnePageByMap(page, condition);
					}else if(countCommon>0&&countArt>0){
						status= 4;//已发布（两个学科）
						page=scoreGradeTrialService.selectOnePageByMap(page, condition);
					}
				}else if(comsize>0&&artsize==0)	{
					if(countCommon>0){
						status= 7;//已发布（两个学科）(针对只有一个学科的)普通科
						page=scoreGradeTrialService.selectOnePageByMap(page, condition);
					}else if(countCommon==0){
						status= 5;//未发布（普通科未发布，艺术科没有考生）
						page=scoreGradeTrialService.selectOnePageByMap(page, condition);
					}
				}else if(comsize==0&&artsize>0)	{
					if(countArt>0){
						status= 8;//已发布（两个学科）(针对只有一个学科的)艺术科
						page=scoreGradeTrialService.selectOnePageByMap(page, condition);
					}else if(countArt==0){
						status= 6;//未发布（艺术科未发布，普通科没有考生）
						page=scoreGradeTrialService.selectOnePageByMap(page, condition);
					}
				}*/
    		}
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("step", step);
        jsonMap.put("status", status);
		return JSONObject.toJSON(jsonMap).toString();
    }
    
  //保存成绩
    @ResponseBody
    @RequestMapping(value="saveTrialScoreInfo")
    public JSONObject saveTrialScoreInfo(StudentInfo studentInfo,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		List<ScoreGradeTrial> gradeTrialsList = studentInfo.getGradeTrials();
			scoreGradeTrialService.updateTrialList(gradeTrialsList,user,jsonMap);
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
    @RequestMapping(value="publishTrials",method=RequestMethod.POST)
    public JSONObject publishTrials(HttpSession session,Page page,String flag){
		User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		JSONObject jsonMap=new JSONObject();
		if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
		/*if(StringUtils.isBlank(flag)){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "发布标识为空！");
		}*/
		try {
			int count=scoreEnterTrialService.selectEntersCount(condition);//名单入围总人数
			condition.put("flag", 1);
			int labcount=scoreEnterTrialService.selectLabsCount(condition);//试室发布总数
			Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
			if(notice!=null){
				//成绩和名单是否发布
				Integer trialListPub=notice.getLectureListPublishNor();
				Integer trialListArt=notice.getLectureListPublishArt();
				Integer trialScore=notice.getLectureScorePublishNor();
				Integer trialScoreArt=notice.getLectureScorePublishArt();
				Integer writienScore=notice.getWrittenScorePublish();
				if(labcount>0&&count==labcount){
					if(writienScore==1){
						if(trialListPub==1&&trialListArt==1&&trialScore!=1&&trialScoreArt!=1){
							scoreGradeTrialService.publishBatchTrial(condition, page, user,jsonMap,flag);
							jsonMap.put("flag", "success");
							jsonMap.put("msg", "成绩发布成功！");
						}else if(trialListPub!=1||trialListArt!=1){
							jsonMap.put("flag", "error");
							jsonMap.put("msg", "试讲名单发布后才能发布试讲成绩！");
						}else if(trialScore==1||trialScoreArt==1){
							jsonMap.put("flag", "error");
							jsonMap.put("msg", "试讲成绩已发布！");
						}else{
							jsonMap.put("flag", "error");
							jsonMap.put("msg", "操作有误，请稍后重试！");
						}
					}else{
						jsonMap.put("flag", "error");
						jsonMap.put("msg", "笔试成绩发布才能发布试讲成绩！");
					}
				}else{
					jsonMap.put("flag", "error");
					jsonMap.put("msg", "试讲的试室发布完后才能发布成绩，请先分配完试室！");
				}
			}else{
				jsonMap.put("flag", "error");
				jsonMap.put("msg", "通知表为空，请先填写通知表！");
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
    @RequestMapping(value="celpublishTrials",method=RequestMethod.POST)
    public JSONObject celpublishWritien(HttpSession session,Page page,String flag){
    	User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		String projectId=(String)condition.get("projectId");
		JSONObject jsonMap=new JSONObject();
		if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
		/*if(StringUtils.isBlank(flag)){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "发布标识为空！");
		}*/
		Integer step=6;//招聘流程进行到的环节
		try {
			String msg=scoreGradeTrialService.celpublishTrial(projectId, step,flag,condition,user);
			if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("false")){
				 jsonMap.put("flag", "false");
	    		 jsonMap.put("msg", "取消发布成绩失败！");
			}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("success")){
				 jsonMap.put("flag", "success");
		    	 jsonMap.put("msg", "取消发布成绩成功！");
			}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("phynull")){
				 jsonMap.put("flag", "error");
		    	 jsonMap.put("msg", "取消发布成绩失败，体检名单为空！");
			}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("scorenull")){
				 jsonMap.put("flag", "error");
		    	 jsonMap.put("msg", "取消发布成绩失败，没有成绩数据！");
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
}
package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreEnterTrial控制器
 * 统一试讲入围表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreEnterTrial")
public class ScoreEnterTrialController {
	
    @Resource 
    private ScoreEnterTrialService scoreEnterTrialService;
    @Resource
	private ThemeService themeService;
    @Resource
   	private NoticeService noticeService;
    
    //统一试讲入围名单列表
    @ResponseBody
    @RequestMapping(value="enterTrialList",method=RequestMethod.POST)
    public String enterTrialList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	Integer isScore=9;//成绩发布状态
    	Integer step=0;//5、发布笔试成绩并生成了统一试讲的名单6、发布试讲名单 7、发布试讲成绩
    	Integer status=0;
    	condition.put("subjectType", "0");//普通科
    	Integer comsize=scoreEnterTrialService.selectCountByMap(condition);//查询普通科
    	condition.put("subjectType", "1");//艺术科
    	Integer artsize=scoreEnterTrialService.selectCountByMap(condition);//查询普通科
    	
    	if(StringUtils.isNotEmpty(type)&&type.equals("3")){
    		step=themeService.selectStep((String)condition.get("projectId"));//项目进行的环节
			if(step>=3){
				condition.put("listStatus", "2");//1、未发布2、已发布
				condition.put("subjectType", "0");//普通科
				Integer countCommon=scoreEnterTrialService.selectCountByMap(condition);
				
				condition.put("subjectType", "1");//艺术科
				Integer countArt=scoreEnterTrialService.selectCountByMap(condition);
				
				condition.put("isEnter", "1");//1、入围2、未入围
				if(comsize>0&&artsize>0){
					if(countCommon>0&&countArt==0){
						status= 1;//普通科发布
						condition.put("isList", "1");//1、未发布2、已发布
						page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
					}else if(countCommon==0&&countArt>0){
						status= 2;//艺术科发布
						condition.put("isList", "1");//1、未发布2、已发布
						page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
					}else if(countCommon==0&&countArt==0){
						status= 3;//未发布（两个学科）
						page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
					}else if(countCommon>0&&countArt>0){
						status= 4;//已发布（两个学科）
						page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
					}
				}else if(comsize>0&&artsize==0)	{
					if(countCommon>0){
						status= 4;//已发布（两个学科）
						page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
					}else if(countCommon==0){
						status= 3;//未发布（两个学科）
						page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
					}
						
				}else if(comsize==0&&artsize>0)	{
					if(countArt>0){
						status= 4;//已发布（两个学科）
						page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
					}else if(countArt==0){
						status= 3;//未发布（两个学科）
						page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
					}
				}
			}	
			Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
			if(notice!=null){
				Integer commIsPub=notice.getLectureScorePublishNor();
				Integer artIsPub=notice.getLectureListPublishArt();
				if(commIsPub==1&&artIsPub==1){
					isScore=1;
				}
			}	
    	}
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("step", step);
        jsonMap.put("status", status);
        jsonMap.put("isScore", isScore);
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //调整名单列表
    @ResponseBody
    @RequestMapping(value="ajustTrialList",method=RequestMethod.POST)
    public String ajustTrialList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	if(StringUtils.isNotEmpty(type)&&type.equals("3")){
			condition.put("isEnter", "2");//1、入围2、未入围
			condition.put("isList", "1");//1、未发布2、已发布
			page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("ispublish", "1");//已发布
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //调入当前名单和删除名单
    @ResponseBody
    @RequestMapping(value="updateTrialEnter",method=RequestMethod.POST)
    public JSONObject updateTrialEnter(String flag,String chk,String projectId,String positionid,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try{
			scoreEnterTrialService.enterTriaList(flag, chk, projectId, positionid, user, jsonMap);
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
    @RequestMapping(value="pubCommonOrArtList",method=RequestMethod.POST)
    public JSONObject pubCommonOrArtList(HttpSession session,Page page,String flag){
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
    	Integer step=themeService.selectStep((String)condition.get("projectId"));//5、发布笔试成绩并生成了统一试讲的名单6、发布试讲名单 7、发布试讲成绩
    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
    	try {
    		Integer commIsPub=9;
    		Integer artIsPub=9;
    		//flag:1、普通科2、艺术科
    		if(notice!=null){
	    		commIsPub=notice.getLectureListPublishNor();//普通科是否发布：0否1是
	    		artIsPub=notice.getLectureListPublishArt();//艺术科是否发布：0否1是
    		}	
    		if(step>=3){
	    		if((flag.equals("1")&&commIsPub==0)||(flag.equals("2")&&artIsPub==0)){
					String msg=scoreEnterTrialService.addBatchTrials(condition, page, user, flag);
					if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("false")){
						 jsonMap.put("flag", "false");
			    		 jsonMap.put("msg", "入围名单为空！");
					}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("success")){
						 jsonMap.put("flag", "success");
				    	 jsonMap.put("msg", "名单发布成功！");
					}
	    		}else{
	    			jsonMap.put("flag", "error");
	    			jsonMap.put("msg", "名单已发布！操作失败，请稍后重试！");
	    		}
    		}	
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }

  //统一试讲成绩查询
    @ResponseBody
    @RequestMapping(value="enterTrialSearch",method=RequestMethod.POST)
    public String enterTrialSearch(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	//查询发布了的名单
    	if(StringUtils.isNotEmpty(type)&&type.equals("3")){
    		condition.put("isList", "2");//1、未发布2、已发布
    		condition.put("isEnter", "1");//1、入围2、未入围
			page=scoreEnterTrialService.selectTrialEnterPage(page, condition);
		}
    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
    	Integer isScore=9;//成绩是否发布
    	if(notice!=null){
    		if(notice.getLectureScorePublishNor()==1&&notice.getLectureScorePublishArt()==1){
    			isScore=1;
    		}
    	}	
    	jsonMap.put("isScore", isScore);
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //综合成绩查询
    @ResponseBody
    @RequestMapping(value="synthesizeSearch",method=RequestMethod.POST)
    public String synthesizeSearch(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	Integer listPub=9;
    	Integer listPubNor=9;
    	Integer listPubArt=9;
    	Integer isScore=9;//成绩是否发布
    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
    	if(notice!=null){
	    	listPub=notice.getWrittenListPublish();//统一笔试名单
	    	listPubNor=notice.getLectureListPublishNor();//统一试讲普通科名单
	    	listPubArt=notice.getLectureListPublishArt();//统一试讲艺术科名单
	    	Integer written=notice.getWrittenScorePublish();//笔试成绩是否发布
	    	//试讲成绩是否发布
	    	Integer comm=notice.getLectureScorePublishNor();
	    	Integer art=notice.getLectureScorePublishArt();
	    	if(written==1&&comm==1&&art==1){
    			isScore=1;//笔试和试讲成绩都发布了
    		}else if(written==1&&comm!=1&&art!=1){
    			isScore=2;//笔试发布，试讲没发布
    		}else if(written!=1&&comm==1&&art==1){
    			isScore=3;//试讲成绩发布,笔试没有发布
    		}else if(written!=1&&comm!=1&&art!=1){
    			isScore=4;//笔试和试讲成绩都没发布
    		}
    	}	
    	if(listPub==1&&listPubNor==1&&listPubArt==1){//所有名单都发布后才会显示综合成绩
	    	if(StringUtils.isNotEmpty(type)&&type.equals("7")){
	    		condition.put("isEnter", "1");//1、入围2、未入围
				page=scoreEnterTrialService.selectSynthesizePageByMap(page, condition);
	    	}
    	}else{
    		condition.put("isEnter", "1111");//不显示成绩	
    		page=scoreEnterTrialService.selectSynthesizePageByMap(page, condition);
    	}	
    	jsonMap.put("isScore", isScore);
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
    }
    
}
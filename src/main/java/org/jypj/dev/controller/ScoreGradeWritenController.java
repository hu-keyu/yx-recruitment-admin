package org.jypj.dev.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreGradeWriten;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.ExamSubjectInfoService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterPhysicalService;
import org.jypj.dev.service.ScoreEnterStudyService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.ScoreEnterWritienService;
import org.jypj.dev.service.ScoreGradeWritenService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreGradeWriten控制器
 * 统一笔试成绩表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreGradeWriten")
public class ScoreGradeWritenController {
	
    @Resource 
    private ScoreGradeWritenService scoreGradeWritenService;
    @Resource
   	private ThemeService themeService;
    @Resource
   	private ScoreEnterWritienService scoreEnterWritienService ;
    @Resource
   	private ScoreEnterTrialService   scoreEnterTrialService;
    @Resource
   	private ScoreEnterPhysicalService scoreEnterPhysicalService;
    @Resource
   	private ScoreEnterStudyService scoreEnterStudyService;
    @Resource
   	private NoticeService noticeService;
    @Resource
   	private ExamSubjectInfoService examSubjectInfoService;
    
    
    //统一笔试成绩列表
    @ResponseBody
    @RequestMapping(value="gradesWritienList",method=RequestMethod.POST)
    public String gradesWritienList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Integer step=0;
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	step=themeService.selectStep((String)condition.get("projectId"));//3、发布面试成绩并生成了统一笔试的名单4、发布笔试名单 5、发布笔试成绩
    	//if(step>=4){
	    	if(StringUtils.isNotEmpty(type)&&type.equals("2")){
	    		page=scoreGradeWritenService.selectOnePageByMap(page, condition);
	    	}
	    	Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
	    	if(notice!=null){
	    		jsonMap.put("isscore", notice.getWrittenScorePublish());
	    	}else{
	    		jsonMap.put("isscore", null);
	    	}
    	//}
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("step", step);
		return JSONObject.toJSON(jsonMap).toString();
    }
    
	//保存成绩
    @ResponseBody
    @RequestMapping(value="saveScoreInfo")
    public JSONObject saveScoreInfo(StudentInfo studentInfo,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		List<ScoreGradeWriten> gradeWritensList = studentInfo.getGradeWritens();
    		scoreGradeWritenService.updateWritenList(gradeWritensList,user,jsonMap);
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
    @RequestMapping(value="publishWritienGrade",method=RequestMethod.POST)
    public JSONObject publishWritienGrade(HttpSession session,Page page){
		User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		JSONObject jsonMap=new JSONObject();
		if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
		Integer step=themeService.selectStep((String)condition.get("projectId"));//3、发布面试成绩并生成了统一笔试的名单4、发布笔试名单 5、发布笔试成绩
		Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
		int labcount=scoreGradeWritenService.selectLabsCount(condition);//试室发布后的人数
		int count=scoreGradeWritenService.selectGradeWritienCount(condition);//成绩表的人数
		try {
			if(step>=4){
				if(labcount==count){
					if(notice!=null){
						Integer writienList=notice.getWrittenListPublish();
						Integer writienScore=notice.getWrittenScorePublish();
			    		if(writienList==1&&writienScore!=1){
							scoreGradeWritenService.publishBatchWritien(condition, page, user,jsonMap);
							jsonMap.put("flag", "success");
							jsonMap.put("msg", "成绩发布成功！");
						}else if(writienList==0&&writienScore==0){
							jsonMap.put("flag", "error");
							jsonMap.put("msg", "笔试名单发布后才能发布成绩，请稍后重试！");
						}else if(writienList==1&&writienScore==1){
							jsonMap.put("flag", "error");
							jsonMap.put("msg", "笔试成绩已发布，请稍后重试！");
						}else{
							jsonMap.put("flag", "error");
							jsonMap.put("msg", "操作有误，请稍后重试！");
						}
					}else{
						jsonMap.put("flag", "error");
						jsonMap.put("msg", "通知表为空，请先填写通知表！");
					}
				}else{
					jsonMap.put("flag", "error");
					jsonMap.put("msg", "笔试的试室发布完后才能发布成绩，请先分配完试室！");
				}
			}else{
				jsonMap.put("flag", "error");
				jsonMap.put("msg", "操作流程步骤不对，请稍后重试！");
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
    @RequestMapping(value="celpublishWritien",method=RequestMethod.POST)
    public JSONObject celpublishWritien(HttpSession session,Page page){
		Map<String, Object> condition =page.getCondition();
		JSONObject jsonMap=new JSONObject();
		if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
		String projectId=(String)condition.get("projectId");
		Integer step=4;//招聘流程进行到的环节
		Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
		try {
			if(notice!=null){
				Integer trailScoreArt=notice.getLectureScorePublishArt();
				Integer trailScoreComm=notice.getLectureScorePublishNor();
				if(trailScoreArt!=1&&trailScoreComm!=1){
					String msg=scoreGradeWritenService.celpublishWritien(projectId, step);
					if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("false")){
						 jsonMap.put("flag", "false");
			    		 jsonMap.put("msg", "取消发布成绩失败！");
					}else if(StringUtils.isNotEmpty(msg)&&msg.equalsIgnoreCase("success")){
						 jsonMap.put("flag", "success");
				    	 jsonMap.put("msg", "取消发布成绩成功！");
					}
				}else{
					jsonMap.put("flag", "false");
		    		jsonMap.put("msg", "取消发布成绩失败,请先取消发布试讲成绩！");
				}
			}else{
				jsonMap.put("flag", "false");
	    		jsonMap.put("msg", "通知表为空，请先填写通知表！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
		return jsonMap;
    }
    
    @RequestMapping(value="importScore")
    public String importScore(Model model){
    	// 年月日
    	List<String> nyrdics=examSubjectInfoService.queryThemeDate();
		model.addAttribute("nyrdics", nyrdics);

    	return "/scoreinformation/score_import.vm";
    }
    
    //查询考试类型
    @ResponseBody
    @RequestMapping(value="selectTestType")
    public String selectTestType(String themeid){
    	JSONObject jo=new JSONObject();
    	 try {
    		 if(StringUtils.isBlank(themeid)){
    			 jo.put("flag", false);
    			 jo.put("msg", "招聘项目为空！");
    			 return jo.toJSONString();
    		 }else{
    			 List<Dictionary> typeList = DictionaryCache.getDictionaryByCode("kslx");
		    	 jo.put("flag", true);
		         jo.put("typeList",typeList);
		         return jo.toJSONString();
    		 } 
	     } catch (Exception e) {
	         e.printStackTrace();
	         jo.put("flag", false);
	         jo.put("msg", "操作失败，请重试！");
	         return jo.toJSONString();
	     }
    }
    
    //查询通知表，判断是否发布名单
    @ResponseBody
    @RequestMapping(value="selectNotices")
    public String selectNotices(String themeid){
    	JSONObject jo=new JSONObject();
    	 try {
    		 if(StringUtils.isBlank(themeid)){
    			 jo.put("flag", false);
    			 jo.put("msg", "招聘项目为空！");
    			 return jo.toJSONString();
    		 }else{
    			 Notice notice=noticeService.selectObjectByThemeId(themeid);
    			 List<Notice> noticeList=new ArrayList<Notice>();
    			 noticeList.add(notice);
		    	 jo.put("flag", true);
		         jo.put("noticeList",noticeList);
		         return jo.toJSONString();
    		 } 
	     } catch (Exception e) {
	         e.printStackTrace();
	         jo.put("flag", false);
	         jo.put("msg", "操作失败，请重试！");
	         return jo.toJSONString();
	     }
    }
    @RequestMapping(value="templateList")
    public String templateList(String projectId,String testType,Model model){
    	model.addAttribute("projectId", projectId);
    	model.addAttribute("testType", testType);
    	return "/scoreinformation/score_template_list.vm";
    }
    
    //导出模板
    @RequestMapping(value="exportInterviewTemplate")
    public void exportInterviewTemplate(String projectId,String testType,String chkname,
    		String chkpos,HttpServletResponse response,HttpSession session,HttpServletRequest request){
    	Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("scoreNotNull", "score");
		if(StringUtils.isNotBlank(projectId)){
			condition.put("projectId", projectId);
		}
		if(StringUtils.isNotBlank(testType)){
			condition.put("testType", testType);
		}
		String title="";
		String sheetName="";
		List<ScoreEntersOutVo> scoreEntersOutVos=new ArrayList<ScoreEntersOutVo>();
    	if(testType.equals("2")){
    		title="统一笔试成绩信息";
   		 	sheetName="统一笔试";
	   		condition.put("isEnter", "1");//1、入围
	 		condition.put("freeStudent", "0");//0、否
	 		condition.put("professionalCourse", "0");//0、否
   		 	scoreEntersOutVos =scoreEnterWritienService.selectAllWritien(condition);
    	}else if(testType.equals("3")){
    		title="统一试讲成绩信息";
   		 	sheetName="统一试讲";
   		 	condition.put("isEnter", "1");//1、入围
   		    condition.put("liststatus", "2");//2、已发布
   		 	scoreEntersOutVos=scoreEnterTrialService.selectAllTrial(condition);
    	}else if(testType.equals("4")){
    		title="体检成绩信息";
   		 	sheetName="体检";
   		 	condition.put("isEnter", "1");//1、入围
		 	scoreEntersOutVos=scoreEnterPhysicalService.selectAllPhysical(condition);
    	}else if(testType.equals("5")){
    		title="考察成绩信息";
   		 	sheetName="考察";
   		 	condition.put("isEnter", "1");//1、入围
		 	scoreEntersOutVos=scoreEnterStudyService.selectAllStudy(condition);
    	}
		 
    	ExcelUtils.setResponseHeader(request,response,sheetName+".xls");
		HSSFWorkbook wb = new HSSFWorkbook();//声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);//生成一个表格
		String[] headers = new String []{ "准考证号", "姓名" ,"岗位名称","成绩"};
    	if(testType.equals("2")){
    		if(StringUtils.isNotBlank(chkname) && "1".equals(chkname) && StringUtils.isBlank(chkpos)){
    			headers = new String []{ "准考证号","姓名","成绩"};
    		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isBlank(chkname)){
    			headers = new String []{ "准考证号","岗位名称" ,"成绩"};
    		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isNotBlank(chkname)&& "1".equals(chkname)){
    			headers = new String []{ "准考证号","姓名","岗位名称" ,"成绩"};
    		}else if(StringUtils.isBlank(chkpos) && StringUtils.isBlank(chkname)){
    			headers = new String []{ "准考证号","成绩"};
    		}
    	}else if(testType.equals("3")){
    		if(StringUtils.isNotBlank(chkname) && "1".equals(chkname) && StringUtils.isBlank(chkpos)){
    			headers = new String []{ "准考证号","姓名","成绩"};
    		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isBlank(chkname)){
    			headers = new String []{ "准考证号","岗位名称" ,"成绩"};
    		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isNotBlank(chkname)&& "1".equals(chkname)){
    			headers = new String []{ "准考证号","姓名","岗位名称" ,"成绩"};
    		}else if(StringUtils.isBlank(chkpos) && StringUtils.isBlank(chkname)){
    			headers = new String []{ "准考证号","成绩"};
    		}
    	}else if(testType.equals("4")){
    		if(StringUtils.isNotBlank(chkname) && "1".equals(chkname) && StringUtils.isBlank(chkpos)){
	 			headers = new String []{ "准考证号","姓名","是否通过"};
	 			if(scoreEntersOutVos.size()>0){
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 2, 2);
	 			}	
	 		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isBlank(chkname)){
	 			headers = new String []{ "准考证号","岗位名称","是否通过"};
	 			if(scoreEntersOutVos.size()>0){
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 2, 2);
	 			}	
	 		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isNotBlank(chkname)&& "1".equals(chkname)){
	 			headers = new String []{ "准考证号","姓名","岗位名称","是否通过"};
	 			if(scoreEntersOutVos.size()>0){
	 				String[] textlist=new String[]{"是","否"};
	 				sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 3, 3);
	 			}
	 		}else if(StringUtils.isBlank(chkpos) && StringUtils.isBlank(chkname)){
	 			headers = new String []{ "准考证号","是否通过"};
	 			if(scoreEntersOutVos.size()>0){
	 				String[] textlist=new String[]{"是","否"};
	 				sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 1, 1);
	 			}	
	 		}

    	}else if(testType.equals("5")){
    		if(StringUtils.isNotBlank(chkname) && "1".equals(chkname) && StringUtils.isBlank(chkpos)){
	 			headers = new String []{ "准考证号","姓名","是否通过"};
				String[] textlist=new String[]{"是","否"};
				if(scoreEntersOutVos.size()>0){
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 2, 2);
				}	
	 		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isBlank(chkname)){
	 			headers = new String []{ "准考证号","岗位名称","是否通过"};
	 			if(scoreEntersOutVos.size()>0){
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 2, 2);
	 			}	
	 		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isNotBlank(chkname)&& "1".equals(chkname)){
	 			headers = new String []{ "准考证号","姓名","岗位名称","是否通过"};
	 			if(scoreEntersOutVos.size()>0){
		 			String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 3, 3);
	 			}	
	 		}else if(StringUtils.isBlank(chkpos) && StringUtils.isBlank(chkname)){
	 			headers = new String []{ "准考证号","是否通过"};
	 			if(scoreEntersOutVos.size()>0){
	 				String[] textlist=new String[]{"是","否"};
	 				sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 1, 1);
	 			}	
	 		}
    	}
		
		HSSFRow row=ExcelUtils.setHSSFHeader(wb, sheet, title, headers);
        HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
        for(int i=0;i<scoreEntersOutVos.size();i++){
        	ScoreEntersOutVo scoreEntersOutVo=scoreEntersOutVos.get(i);
        	row = sheet.createRow(i+2);
        	
        	int cellIndex=0;
        	HSSFCell hc1 = row.createCell(cellIndex++);
        	hc1.setCellStyle(contentStyle);
    		HSSFRichTextString ticketNum = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getTicketNum() : "");
    		hc1.setCellValue(ticketNum);
    		
    		if(StringUtils.isNotBlank(chkname) && "1".equals(chkname) && StringUtils.isBlank(chkpos)){
    			HSSFCell hc2 = row.createCell(cellIndex++);
        		hc2.setCellStyle(contentStyle);
        		HSSFRichTextString studentName = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getStudentName() : "");
        		hc2.setCellValue(studentName);
        		
        		HSSFCell hc3 = row.createCell(cellIndex++);
        		hc3.setCellStyle(contentStyle);
    		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isBlank(chkname)){
        		HSSFCell hc2 = row.createCell(cellIndex++);
        		hc2.setCellStyle(contentStyle);
        		HSSFRichTextString postName = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getPostName() : "");
        		hc2.setCellValue(postName);
        		
        		HSSFCell hc3 = row.createCell(cellIndex++);
        		hc3.setCellStyle(contentStyle);
    		}else if(StringUtils.isNotBlank(chkpos) && "1".equals(chkpos) && StringUtils.isNotBlank(chkname)&& "1".equals(chkname)){
    			HSSFCell hc2 = row.createCell(cellIndex++);
        		hc2.setCellStyle(contentStyle);
        		HSSFRichTextString studentName = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getStudentName() : "");
        		hc2.setCellValue(studentName);
        		
        		HSSFCell hc3 = row.createCell(cellIndex++);
        		hc3.setCellStyle(contentStyle);
        		HSSFRichTextString postName = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getPostName() : "");
        		hc3.setCellValue(postName);
        		
        		HSSFCell hc4 = row.createCell(cellIndex++);
        		hc4.setCellStyle(contentStyle);
    		}else if(StringUtils.isBlank(chkpos) && StringUtils.isBlank(chkname)){
        		HSSFCell hc2 = row.createCell(cellIndex++);
        		hc2.setCellStyle(contentStyle);
    		}
    		
        }
        OutputStream out=null;
        try {
			out = response.getOutputStream();
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}
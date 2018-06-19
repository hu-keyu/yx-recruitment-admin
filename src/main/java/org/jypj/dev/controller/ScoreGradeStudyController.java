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
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreGradeStudy;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterInformationService;
import org.jypj.dev.service.ScoreEnterNoticeService;
import org.jypj.dev.service.ScoreEnterPhysicalService;
import org.jypj.dev.service.ScoreEnterStudyService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.ScoreEnterWritienService;
import org.jypj.dev.service.ScoreGradeStudyService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ScoreGradeStudy控制器
 * 考察成绩表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreGradeStudy")
public class ScoreGradeStudyController {
	
    @Resource 
    private ScoreGradeStudyService scoreGradeStudyService;
    @Resource
   	private ThemeService themeService;
    @Resource
   	private ScoreEnterPhysicalService scoreEnterPhysicalService;
    @Resource
   	private ScoreEnterInformationService scoreEnterInformationService;
    @Resource
   	private ScoreEnterWritienService scoreEnterWritienService ;
    @Resource
   	private ScoreEnterTrialService scoreEnterTrialService;
    @Resource
   	private ScoreEnterStudyService scoreEnterStudyService;
    @Resource
   	private ScoreEnterNoticeService scoreEnterNoticeService;
    @Resource
   	private NoticeService noticeService;
   	
    //考察成绩列表
    @ResponseBody
    @RequestMapping(value="gradesStudyList",method=RequestMethod.POST)
    public String gradesStudyList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Integer step=0;
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	step=themeService.selectStep((String)condition.get("projectId"));//9、发布体检成绩并生成了考察的名单10、发布考察名单 11、发布考察成绩
    	if(StringUtils.isNotEmpty(type)&&type.equals("5")){
    		//if(step>=10){
    			page=scoreGradeStudyService.selectOnePageByMap(page, condition);
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
    @RequestMapping(value="saveStudyScoreInfo")
    public JSONObject saveStudyScoreInfo(StudentInfo studentInfo,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		List<ScoreGradeStudy> gradeStudysList = studentInfo.getGradeStudies();
			scoreGradeStudyService.updateStudyList(gradeStudysList,user,jsonMap);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
   //发布成绩
    @ResponseBody
    @RequestMapping(value="publishStudyGrade",method=RequestMethod.POST)
    public JSONObject publishStudyGrade(HttpSession session,Page page){
		User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		JSONObject jsonMap=new JSONObject();
		if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
		Integer step=themeService.selectStep((String)condition.get("projectId"));//9、发布体检成绩并生成了考察的名单10、发布考察名单 11、发布考察成绩
		try {
			if(step==10){
				scoreGradeStudyService.publishBatchStudy(condition, page, user,jsonMap);
				jsonMap.put("flag", "success");
				jsonMap.put("msg", "成绩发布成功！");
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
    
    //取消发布成绩
    @ResponseBody
    @RequestMapping(value="celpublishStudys",method=RequestMethod.POST)
    public JSONObject celpublishStudys(HttpSession session,Page page){
		Map<String, Object> condition =page.getCondition();
		String projectId=(String)condition.get("projectId");
		JSONObject jsonMap=new JSONObject();
		if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
		Integer step=10;//设置招聘流程进行到的环节
		try {
			String msg=scoreGradeStudyService.celpublishStudy(projectId, step);
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
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
		return jsonMap;
    }
    
    //导出成绩查询的结果（所有岗位）
    @RequestMapping(value="exportExcels")
    public void exportExcels(String testType,String projectId,HttpServletResponse response,HttpSession session,HttpServletRequest request){
    	Map<String, Object> condition = new HashMap<String, Object>();
    	Map<String, List<ScoreEntersOutVo>> scoMap=new HashMap<String, List<ScoreEntersOutVo>>();
		if(StringUtils.isNotBlank(projectId)){
			condition.put("projectId", projectId);
		}
		//List<ScoreEntersOutVo> postVO=new ArrayList<ScoreEntersOutVo>();//查询岗位id和岗位名
		List<ScoreEntersOutVo> enterOutVos=new ArrayList<ScoreEntersOutVo>();
		if(StringUtils.isNotBlank(testType)){
			condition.put("testType", testType);
		}
		Notice notice=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
		String title="";
		String sheetName="";
		Integer listPub=9;
		Integer listPubNor=9;
     	Integer listPubArt=9;
     	Integer isScore=9;//成绩是否发布
		if(StringUtils.isNotBlank(testType)&&testType.equals("1")){
    		title="单位面试阶段成绩";
   		 	sheetName="单位面试";
	   		condition.put("isEnter", "1");//1、入围
	   		enterOutVos=scoreEnterInformationService.selectExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("2")){
    		title="统一笔试阶段成绩";
   		 	sheetName="统一笔试";
   		 	if(notice!=null){
   		 		listPub=notice.getWrittenListPublish();//名单发布后才会显示成绩查询中的统一笔试名单
   		 		isScore=notice.getWrittenScorePublish();
   		 	}	
   		 	if(listPub==1){
   		 		condition.put("isEnter", "1");//1、入围
   		 	}else{
   		 		condition.put("isEnter", "1111");//不显示成绩	
   		 	}	
	 		condition.put("freeStudent", "0");//0、否
	 		condition.put("professionalCourse", "0");//0、否
	 		enterOutVos=scoreEnterWritienService.selectExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("3")){
    		title="统一试讲阶段成绩";
   		 	sheetName="统一试讲";
   		 	if(notice!=null){
   		 		if(notice.getLectureScorePublishNor()==1&&notice.getLectureScorePublishArt()==1){
   		 			isScore=1;
   		 		}
   		 	}
   		 	condition.put("isList", "2");//1、未发布2、已发布
   		 	condition.put("isEnter", "1");//1、入围
   		 	enterOutVos=scoreEnterTrialService.selectExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("4")){
    		title="体检阶段成绩";
   		 	sheetName="体检";
   		 	if(notice!=null){
   		 		listPub=notice.getBodyexamListPublish();//名单发布后才会显示成绩查询中的体检名单
   		 		isScore=notice.getBodyexamScorePublish();
   		 	}	
   		 	if(listPub==1){
   		 		condition.put("isEnter", "1");//1、入围
   		 	}else{
   		 		condition.put("isEnter", "1111");//不能查到数据
   		 	}
   		 	//postVO=scoreEnterPhysicalService.selectPostCount(condition);
   		 	enterOutVos=scoreEnterPhysicalService.selectExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("5")){
    		title="考察成绩";
   		 	sheetName="考察";
   		 	if(notice!=null){
   		 		listPub=notice.getLookListPublish();//名单发布后才会显示成绩查询中的考察名单
   		 		isScore=notice.getLookScorePublish();
   		 	}	
   		 	if(listPub==1){
   		 		condition.put("isEnter", "1");//1、入围
   		 	}else{
   		 		condition.put("isEnter", "1111");//不查成绩
   		 	}
   		 	enterOutVos=scoreEnterStudyService.selectExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("7")){
    		title="综合成绩";
   		 	sheetName="综合查询";
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
	     		condition.put("isEnter", "1");//1、入围
	     	}else{
	     		condition.put("isEnter", "1111");//不显示成绩	
	     	}	
   		 	enterOutVos=scoreEnterNoticeService.selectExportByMap(condition);
    	}
		ExcelUtils.setResponseHeader(request,response,sheetName+".xls");
		HSSFWorkbook wb = new HSSFWorkbook();//声明一个工作薄
        
        
        for (int i=0;i<enterOutVos.size();i++) {
        	ScoreEntersOutVo scoVo=enterOutVos.get(i);
        	String postId=scoVo.getPostId();
        	String postName=scoVo.getPostName();
        	//System.out.println(scoMap.containsKey(postId+","+postName));
        	List<ScoreEntersOutVo> list=null;
        	if(scoMap.containsKey(postId+";"+postName)){
        		list=scoMap.get(postId+";"+postName);
        		list.add(scoVo);
        	}else{
        		list=new ArrayList<ScoreEntersOutVo>();
        		list.add(scoVo);
        		scoMap.put(postId+";"+postName,list);
        		
        	}
		}
        HSSFSheet sheet = wb.createSheet(sheetName);//生成一个表格
        Integer j=0;
		HSSFCellStyle titleStyle = ExcelUtils.setTitleStyle(wb,15);
		String[] titleS =new String[]{title};
		HSSFRow row=null;
		if(StringUtils.isNotBlank(testType)&&!testType.equals("7")&&!testType.equals("1")){
			row=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleStyle,titleS,0,4);
		}else if(StringUtils.isNotBlank(testType)&&testType.equals("1")){//单位面试
			row=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleStyle,titleS,0,5);	
		}else if(StringUtils.isNotBlank(testType)&&testType.equals("7")){//综合
			row=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleStyle,titleS,0,6);
		}
		HSSFCellStyle titleName = ExcelUtils.setTitleStyle(wb,13);
		String[] headers = new String []{ "准考证号", "姓名" ,"性别","成绩", "报考单位"};
		if(StringUtils.isNotBlank(testType)&&!testType.equals("7")&&!testType.equals("1")){
			headers = new String []{ "准考证号", "姓名" ,"性别","成绩", "报考单位"};
		}else if(StringUtils.isNotBlank(testType)&&testType.equals("1")){
			headers = new String []{ "准考证号", "姓名" ,"性别","成绩","是否录取","报考单位"};
		}else if(StringUtils.isNotBlank(testType)&&testType.equals("7")){
			headers = new String []{ "准考证号", "姓名" ,"性别","统一笔试成绩","统一试讲成绩","综合成绩", "报考单位"};
		}
        for(Map.Entry<String, List<ScoreEntersOutVo>> entry: scoMap.entrySet()) {
        	String idName=entry.getKey();
        	String name="";
        	if(StringUtils.isNotEmpty(idName)){
        		String id=idName.substring(0,32);
        		name=idName.substring(33,idName.length());
        	}
        	String[] names = new String []{name};
        	j++;
        	HSSFRow rows=null;
        	if(StringUtils.isNotBlank(testType)&&!testType.equals("7")){
        		rows=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleName,names,j,4);//第二层标题
    		}else if(StringUtils.isNotBlank(testType)&&testType.equals("7")){//综合
    			rows=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleName,names,j,6);//第二层标题
    		}
        	
        	j++;
        	HSSFRow rowsNew=null;
        	if(StringUtils.isNotBlank(testType)&&!testType.equals("7")){
        		rowsNew=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleName,headers,j,0);//第三层
    		}else if(StringUtils.isNotBlank(testType)&&testType.equals("7")){//综合
    			rowsNew=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleName,headers,j,0);//第三层
    		}
        	
            HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
        	for (int i=0;i<entry.getValue().size();i++) {
        		ScoreEntersOutVo scoreEntersOutVo=entry.getValue().get(i);
				j++;
				row = sheet.createRow(j);
	        	int cellIndex=0;
	        	HSSFCell hc1 = row.createCell(cellIndex++);
	        	hc1.setCellStyle(contentStyle);
	    		HSSFRichTextString ticketNum = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getTicketNum() : "");
	    		hc1.setCellValue(ticketNum);
	    		
	    		HSSFCell hc2 = row.createCell(cellIndex++);
	    		hc2.setCellStyle(contentStyle);
	    		HSSFRichTextString studentName = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getStudentName() : "");
	    		hc2.setCellValue(studentName);
	    		
	    		HSSFCell hc3 = row.createCell(cellIndex++);
	    		hc3.setCellStyle(contentStyle);
	    		String sexs=scoreEntersOutVo.getSex();
	    		if(sexs.equals("1")){
	    			sexs="男";
	    		}else{
	    			sexs="女";
	    		}
	    		HSSFRichTextString sex = new HSSFRichTextString(scoreEntersOutVo != null ? sexs : "");
	    		hc3.setCellValue(sex);
	    		
	    		if(StringUtils.isNotBlank(testType)&&!testType.equals("7")&&!testType.equals("1")){
	    			HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		String grade="";
		    		if(testType.equals("2")||testType.equals("3")){
		    			if(isScore==1){
		    				grade=scoreEntersOutVo.getScore().toString();
		    			}else{
		    				grade="0";
		    			}
		    		}else if(testType.equals("4")||testType.equals("5")){
		    			if(isScore==1){
			    			if(scoreEntersOutVo.getIsPass().equals("1")){
			    				grade="通过";
			    			}else{
			    				grade="未通过";
			    			}
		    			}else{
		    				grade="";
		    			}	
		    		}
		    		HSSFRichTextString score = new HSSFRichTextString(scoreEntersOutVo != null ? grade : "");
		    		hc4.setCellValue(score);
		    		
		    		HSSFCell hc5 = row.createCell(cellIndex++);
		    		hc5.setCellStyle(contentStyle);
		    		HSSFRichTextString schoolUnit = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getSchool() : "");
		    		hc5.setCellValue(schoolUnit);
	    		}else if(StringUtils.isNotBlank(testType)&&testType.equals("1")){
	    			String grade="";
	    			String isPass="";
	    			if(StringUtils.isNotBlank(scoreEntersOutVo.getPhone())){
	    				if(scoreEntersOutVo.getPhone().equals("1")){
			    			grade=scoreEntersOutVo.getScore().toString();
			    			if(scoreEntersOutVo.getIsPass().equals("1")){
			    				isPass="是";
			    			}else{
			    				isPass="否";
			    			}
	    				}else{
	    					grade="0";
		    				isPass="";
	    				}
	    			}else{
	    				grade="0";
	    				isPass="";
	    			}
	    			HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		HSSFRichTextString score = new HSSFRichTextString(scoreEntersOutVo != null ? grade : "");
		    		hc4.setCellValue(score);
		    		
	    			HSSFCell hc5 = row.createCell(cellIndex++);
		    		hc5.setCellStyle(contentStyle);
		    		HSSFRichTextString isEmploy = new HSSFRichTextString(scoreEntersOutVo != null ? isPass : "");
		    		hc5.setCellValue(isEmploy);
		    		
		    		HSSFCell hc6 = row.createCell(cellIndex++);
		    		hc6.setCellStyle(contentStyle);
		    		HSSFRichTextString schoolUnit = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getSchool() : "");
		    		hc6.setCellValue(schoolUnit);
	    		
	    		}else if(StringUtils.isNotBlank(testType)&&testType.equals("7")){
	    			//笔试
	    			HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		String scoreVo="";
		    		if(isScore==1||isScore==2){
		    			scoreVo=scoreEntersOutVo.getScore();
		    		}else{
		    			scoreVo="0";
		    		}
		    		HSSFRichTextString score = new HSSFRichTextString(scoreEntersOutVo != null ? scoreVo : "");
		    		hc4.setCellValue(score);
		    		//试讲
		    		HSSFCell hc5 = row.createCell(cellIndex++);
		    		hc5.setCellStyle(contentStyle);
		    		String offerVo="";
		    		if(isScore==1||isScore==3){
		    			offerVo=scoreEntersOutVo.getOffer();
		    		}else{
		    			offerVo="0";
		    		}
		    		HSSFRichTextString offer = new HSSFRichTextString(scoreEntersOutVo != null ? offerVo : "");
		    		hc5.setCellValue(offer);
		    		//综合
		    		HSSFCell hc6 = row.createCell(cellIndex++);
		    		hc6.setCellStyle(contentStyle);
		    		String scys="";//总成绩
		    		String flags="";//同分标识
		    		if(isScore==1){
			    		if(StringUtils.isNotBlank(scoreEntersOutVo.getPhone())){
			    			flags=scoreEntersOutVo.getPhone();
			    		}
			    		if(StringUtils.isNotBlank(scoreEntersOutVo.getScyscore())){
			    			scys=scoreEntersOutVo.getScyscore();
			    		}
		    		}else{
		    			scys="0";
		    		}
		    		HSSFRichTextString scyscore = new HSSFRichTextString(scoreEntersOutVo != null ? scys+flags: "");
		    		hc6.setCellValue(scyscore);
		    		
		    		HSSFCell hc7 = row.createCell(cellIndex++);
		    		hc7.setCellStyle(contentStyle);
		    		HSSFRichTextString schoolUnit = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getSchool() : "");
		    		hc7.setCellValue(schoolUnit);
	    		}
	    		
	    		
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

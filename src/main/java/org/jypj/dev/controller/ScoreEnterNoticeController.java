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
import org.jypj.dev.entity.User;
import org.jypj.dev.service.ScoreEnterInformationService;
import org.jypj.dev.service.ScoreEnterNoticeService;
import org.jypj.dev.service.ScoreEnterPhysicalService;
import org.jypj.dev.service.ScoreEnterStudyService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.ScoreEnterWritienService;
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
 * ScoreEnterNotice控制器
 * 公示表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/scoreEnterNotice")
public class ScoreEnterNoticeController {
	
    @Resource 
    private ScoreEnterNoticeService scoreEnterNoticeService;
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
    
    //公示入围名单列表
    @ResponseBody
    @RequestMapping(value="enterNoticeList",method=RequestMethod.POST)
    public String enterNoticeList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Integer step=0;//10.考察名单发布， 11.考察成绩发布， 12.公示名单
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	if(StringUtils.isNotEmpty(type)&&type.equals("6")){
    		step=themeService.selectStep((String)condition.get("projectId"));//项目进行的环节
			condition.put("isEnter", "1");//1、入围2、未入围
			page=scoreEnterNoticeService.selectOnePageByMap(page, condition);
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("step", step);
		return JSONObject.toJSON(jsonMap).toString();
    }

    //调整名单列表
    @ResponseBody
    @RequestMapping(value="ajustNoticeList",method=RequestMethod.POST)
    public String ajustStudyList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	String type=(String) condition.get("testType");//考试类型
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			 jsonMap.put("flag", "error");
			 jsonMap.put("msg", "招聘项目ID为空！");
		}
    	if(StringUtils.isNotEmpty(type)&&type.equals("6")){
			condition.put("isEnter", "2");//1、入围2、未入围
			page=scoreEnterNoticeService.selectOnePageByMap(page, condition);
    	}
    	
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("ispublish", "1");//已发布
		return JSONObject.toJSON(jsonMap).toString();
    }
    
  //调入当前名单和删除名单
    @ResponseBody
    @RequestMapping(value="updateNoticeEnter",method=RequestMethod.POST)
    public JSONObject updateNoticeEnter(String flag,String chk,String projectId,String positionid,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try{
    		scoreEnterNoticeService.enterNoticelist(flag, chk, projectId, positionid, user, jsonMap);
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
    @RequestMapping(value="publishNoticeList",method=RequestMethod.POST)
    public JSONObject publishNoticeList(HttpSession session,Page page){
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
    	Integer step=themeService.selectStep((String)condition.get("projectId"));//10.考察名单发布， 11.考察成绩发布， 12.公示名单
    	try {
    		if(step==11){
				String msg=scoreEnterNoticeService.addBatchNotice(condition, page,user);
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

  //导出入围名单的结果（所有岗位）
    @RequestMapping(value="exportListExcels")
    public void exportListExcels(String testType,String projectId,HttpServletResponse response,HttpSession session,HttpServletRequest request){
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
		String title="";
		String sheetName="";
		if(StringUtils.isNotBlank(testType)&&testType.equals("1")){
    		title="单位面试阶段入选名单";
   		 	sheetName="单位面试";
   		 	Integer step=0;
   		 	step=themeService.selectStep((String)condition.get("projectId"));//1、报名完成2、发布面试名单3、面试成绩发布
   		 	if(step<2){
   		 		condition.put("status", "6");//6、简历审核通过状态
   		 		enterOutVos=scoreEnterInformationService.listExportByMap(condition);
   		 	}else{
   		 		condition.put("isEnter", "1");//1、入围
   		 		enterOutVos=scoreEnterInformationService.selectListExportByMap(condition);
   		 	}
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("2")){
    		title="统一笔试阶段入选名单";
   		 	sheetName="统一笔试";
	   		condition.put("isEnter", "1");//1、入围
	 		enterOutVos=scoreEnterWritienService.selectListExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("3")){
    		title="统一试讲阶段入选名单";
   		 	sheetName="统一试讲";
   		 	condition.put("isEnter", "1");//1、入围
   		 	enterOutVos=scoreEnterTrialService.selectListExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("4")){
    		title="体检阶段入选名单";
   		 	sheetName="体检";
   		 	condition.put("isEnter", "1");//1、入围
   		 	//postVO=scoreEnterPhysicalService.selectPostCount(condition);
   		 	enterOutVos=scoreEnterPhysicalService.selectListExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("5")){
    		title="考察入选名单";
   		 	sheetName="考察";
   		 	condition.put("isEnter", "1");//1、入围
   		 	enterOutVos=scoreEnterStudyService.selectListExportByMap(condition);
    	}else if(StringUtils.isNotBlank(testType)&&testType.equals("6")){
    		title="公示阶段入选名单";
   		 	sheetName="公示";
   		 	condition.put("isEnter", "1");//1、入围
   		 	enterOutVos=scoreEnterNoticeService.selectListExportByMap(condition);
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
		if(StringUtils.isNotBlank(testType)&&!testType.equals("7")){
			row=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleStyle,titleS,0,4);
		}else if(StringUtils.isNotBlank(testType)&&testType.equals("7")){//综合
			row=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleStyle,titleS,0,6);
		}
		HSSFCellStyle titleName = ExcelUtils.setTitleStyle(wb,13);
		String[] headers = new String []{ "准考证号", "姓名" ,"性别","身份证号", "报考学校"};
		
        for(Map.Entry<String, List<ScoreEntersOutVo>> entry: scoMap.entrySet()) {
        	String idName=entry.getKey();
        	String name="";
        	if(StringUtils.isNotEmpty(idName)){
        		String id=idName.substring(0,32);
        		name=idName.substring(33,idName.length());
        	}
        	String[] names = new String []{name};
        	j++;
        	HSSFRow rows=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleName,names,j,4);//第二层标题
        	j++;
        	HSSFRow	rowsNew=ExcelUtils.setHSSFHeaderRow(wb, sheet,titleName,headers,j,0);//第三层
        	
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
	    		
    			HSSFCell hc4 = row.createCell(cellIndex++);
	    		hc4.setCellStyle(contentStyle);
	    		HSSFRichTextString score = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getIdCard() : "");
	    		hc4.setCellValue(score);
	    		
	    		HSSFCell hc5 = row.createCell(cellIndex++);
	    		hc5.setCellStyle(contentStyle);
	    		HSSFRichTextString schoolUnit = new HSSFRichTextString(scoreEntersOutVo != null ? scoreEntersOutVo.getSchool() : "");
	    		hc5.setCellValue(schoolUnit);
	    		
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
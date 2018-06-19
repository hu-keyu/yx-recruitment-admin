package org.jypj.dev.controller;

import java.io.IOException;
import java.io.OutputStream;
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
import org.jypj.dev.entity.Grade;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.GradeService;
import org.jypj.dev.service.PlanApplyService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.ScoreEnterInformationService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * Grade控制器
 * @author QiCai
 *
 */
@Controller
@RequestMapping("/dg/grade")
public class GradeController {
	
    @Resource 
    private GradeService gradeService;
    @Resource 
    private PlanApplyService planApplyService;
    @Resource
    private PositionService positionService;
    @Resource
    private ScoreEnterInformationService scoreEnterInformationService;
    @Resource
    private ThemeService themeService;
    
    @RequestMapping(value="togradeList")
    public String toGradeList(HttpSession session,Model model){
    	User user=(User)session.getAttribute("user");
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("schoolId", user.getSchoolId());//当前登录用户学校ID
    	queryParameter.put("isPublish", "1");//招聘公告已发布
    	queryParameter.put("status", "2");//招聘计划已审核
    	queryParameter.put("themeStatus", "1");//招聘主题已发布
    	List<String> years=planApplyService.getPlanApplyYears(queryParameter);//当前登录用户学校ID
    	model.addAttribute("years", years);
    	return "/schoolgrade/grade_list.vm";
    }
    
    @ResponseBody
    @RequestMapping(value="getValidPosition",method=RequestMethod.POST)
    public List<Position> validPosition(String projectId,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("pstatus", "1");//启用的岗位
    	queryParameter.put("astatus", "2");//已审核
    	queryParameter.put("isPublish", "1");//已发布
    	queryParameter.put("projectId", projectId);
    	queryParameter.put("schoolId", user.getSchoolId());//当前登录用户学校ID
    	List<Position> positions=positionService.queryValidPosition(queryParameter);
    	return positions;
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllSchoolGrade",method=RequestMethod.POST)
	public String selectAllSchoolGrade(Page page,HttpSession session) {
    	User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		page = scoreEnterInformationService.selectPageByMap(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @ResponseBody
	@RequestMapping(value="saveGrade",method=RequestMethod.POST)
    public JSONObject saveGrade(PlanApply planApply,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
		User user=(User)session.getAttribute("user");
		try {
			gradeService.saveGrade(planApply,user,jsonMap);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "成绩保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	return jsonMap;
    }
    
    @RequestMapping(value="importGrade")
    public String importGrade(String projectId,Model model){
    	model.addAttribute("projectId", projectId);
    	return "/schoolgrade/grade_import.vm";
    }
    
    @RequestMapping(value="templateList")
    public String templateList(String projectId,Model model){
    	model.addAttribute("projectId", projectId);
    	return "/schoolgrade/template_list.vm";
    }
    
    @RequestMapping(value="exportInterviewTemplate")
    public void exportInterviewTemplate(String employ,String projectId,HttpServletResponse response,HttpServletRequest request,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		condition.put("scoreNotNull", "score");
		if(StringUtils.isNotBlank(projectId)){
			condition.put("projectId", projectId);
		}
		List<ScoreEntersOutVo> scoreEntersOutVos=scoreEnterInformationService.selectByMap(condition);
    	
		String title="面试成绩信息";
		String sheetName="面试成绩导入模板";
		ExcelUtils.setResponseHeader(request,response,sheetName+".xls");
		HSSFWorkbook wb = new HSSFWorkbook();//声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);//生成一个表格
		String[] headers = new String []{ "准考证号", "姓名" , "报考岗位","成绩"};
		if(StringUtils.isNotBlank(employ) && "1".equals(employ)){
			headers = new String []{ "准考证号","姓名" , "报考岗位","成绩" ,"是否录取"};
			String[] textlist=new String[]{"是","否"};
			sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, scoreEntersOutVos.size()+1, 4, 4);
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
    		
    		HSSFCell hc5 = row.createCell(cellIndex++);
    		hc5.setCellStyle(contentStyle);
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
	
    @ResponseBody
    @RequestMapping(value="parseInterviewTemplate",produces="text/html;charset=UTF-8")
	public String parseExcel(@RequestParam("file") CommonsMultipartFile file,String projectId,HttpSession session,HttpServletResponse response) throws IOException{
    	JSONObject jsonMap=new JSONObject();
		User user=(User)session.getAttribute("user");
		try {
			gradeService.saveImportGrade(file,projectId,user,jsonMap);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "导入文件成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
		return jsonMap.toJSONString();
	}
    
    @ResponseBody
	@RequestMapping(value="publishGrade")
	public JSONObject publishGrade(String projectId,HttpSession session) throws IOException{
    	JSONObject jsonMap=new JSONObject();
		User user=(User)session.getAttribute("user");
		try {
			gradeService.savePublishGrade(projectId,user,jsonMap);
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
    
    @ResponseBody
	@RequestMapping(value="cancelPublishGrade")
	public JSONObject cancelPublishGrade(String projectId,HttpSession session) throws IOException{
    	JSONObject jsonMap=new JSONObject();
		User user=(User)session.getAttribute("user");
		try {
			gradeService.saveCancelPublishGrade(projectId,user,jsonMap);
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
    
    @ResponseBody
	@RequestMapping(value="queryHasNoGrade")
    public List<Grade> queryHasNoGrade(String projectId,HttpSession session){
    	User user=(User)session.getAttribute("user");	
    	Map<String ,Object> map=new HashMap<String ,Object>(); 
    	map.put("projectId", projectId);
    	map.put("schoolId", user.getSchoolId());
    	return gradeService.queryHasNoGrade(map);
    }
    
    @ResponseBody
	@RequestMapping(value="getPublishCount")
    public Theme getPublishCount(String projectId,HttpSession session){
    	User user=(User)session.getAttribute("user");	
    	Map<String ,Object> map=new HashMap<String ,Object>(); 
    	map.put("projectId", projectId);
    	map.put("schoolId", user.getSchoolId());
    	return themeService.queryTheme(map);
    }
}
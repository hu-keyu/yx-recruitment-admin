package org.jypj.dev.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.jypj.dev.service.ApplicantsService;
import org.jypj.dev.service.InformationService;
import org.jypj.dev.service.PlanApplyService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ApplicantsVo;
import org.jypj.dev.vo.PositionVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 查询统计控制层
 */
@Controller
@RequestMapping("/dg/statistics")
public class StatisticsController {
	@Resource
	private ApplicantsService applicantsService;
	@Resource
	private PlanApplyService planApplyService;
	@Resource
	private InformationService informationService;

	@Resource 
	private PositionService positionService;
	
	/**
	 * 岗位统计
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("toJob")
	public String toJob(Model model, HttpSession session) {
		Map<String, Object> queryParameter = new HashMap<String, Object>();
		queryParameter.put("isPublish", "1");// 招聘公告已发布
		queryParameter.put("status", "2");// 招聘计划已审核
		queryParameter.put("themeStatus", "1");// 招聘主题已发布
		List<String> years = planApplyService.getPlanApplyYears(queryParameter);// 当前登录用户学校ID
		model.addAttribute("years", years);
		return "statistics/applicants.vm";
	}

	/**
	 * 比例统计
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("toRation")
	public String toRation(Model model, HttpSession session) {
		Map<String, Object> queryParameter = new HashMap<String, Object>();
		queryParameter.put("isPublish", "1");// 招聘公告已发布
		queryParameter.put("status", "2");// 招聘计划已审核
		queryParameter.put("themeStatus", "1");// 招聘主题已发布
		List<String> years = planApplyService.getPlanApplyYears(queryParameter);// 当前登录用户学校ID
		model.addAttribute("years", years);
		return "statistics/ration.vm";
	}

	/**
	 * 比例统计
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("toPeopleCount")
	public String toPeopleCount(Model model, HttpSession session) {
		Map<String, Object> queryParameter = new HashMap<String, Object>();
		queryParameter.put("isPublish", "1");// 招聘公告已发布
		queryParameter.put("status", "2");// 招聘计划已审核
		queryParameter.put("themeStatus", "1");// 招聘主题已发布
		List<String> years = planApplyService.getPlanApplyYears(queryParameter);// 当前登录用户学校ID
		model.addAttribute("years", years);
		return "statistics/people_count.vm";
	}

	@RequestMapping("selectRationList")
	@ResponseBody
	public List<ApplicantsVo> selectRationList(ApplicantsVo applicantsVo) {
		return applicantsService.selectAllByItemsId(applicantsVo);
	}

	@RequestMapping("selectApplicants")
	@ResponseBody
	public List<ApplicantsVo> selectApplicants(ApplicantsVo applicantsVo) {
		return applicantsService.selectAllByApplicantsVo(applicantsVo);
	}

	@RequestMapping("selectPeopleCount")
	@ResponseBody
	public List<ApplicantsVo> selectPeopleCount(ApplicantsVo applicantsVo) {
		return applicantsService.selectPeopleCountByItemsId(applicantsVo);
	}

	@RequestMapping(value = "exportApplicants")
	public void exportApplicants(ApplicantsVo applicantsVo, HttpServletResponse response, HttpSession session,HttpServletRequest request) {
		List<ApplicantsVo> applicantsVos = applicantsService.selectAllByApplicantsVo(applicantsVo);
		DecimalFormat df = new DecimalFormat("#.##");
		String title = "报考人数统计";
		String sheetName = "报考人数统计";
		ExcelUtils.setResponseHeader(request,response, sheetName + ".xls");
		HSSFWorkbook wb = new HSSFWorkbook();// 声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);// 生成一个表格
		String[] headers = new String[] { "岗位名称", "岗位报考人数", "男(人数)", "男(占总人数比例)", "女(人数)", "女(占总人数比例)", "研究生(人数)",
				"研究生(占总人数比例)" };
		HSSFRow row = setHSSFHeader(wb, sheet, title, headers);
		HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
		for (int i = 0; i < applicantsVos.size(); i++) {
			ApplicantsVo app = applicantsVos.get(i);
			row = sheet.createRow(i + 4);

			int cellIndex = 0;
			HSSFCell hc1 = row.createCell(cellIndex++);
			hc1.setCellStyle(contentStyle);
			HSSFRichTextString postName = new HSSFRichTextString(app != null ? app.getPostName() : "");
			hc1.setCellValue(postName);

			HSSFCell hc2 = row.createCell(cellIndex++);
			hc2.setCellStyle(contentStyle);
			HSSFRichTextString totalCount = new HSSFRichTextString(app != null ? app.getTotalCount().toString() : "0");
			hc2.setCellValue(totalCount);

			HSSFCell hc3 = row.createCell(cellIndex++);
			hc3.setCellStyle(contentStyle);
			HSSFRichTextString menCount = new HSSFRichTextString(app != null ? app.getMenCount().toString() : "0");
			hc3.setCellValue(menCount);

			HSSFCell hc4 = row.createCell(cellIndex++);
			hc4.setCellStyle(contentStyle);
			HSSFRichTextString menScale = new HSSFRichTextString(
					app != null && app.getMenCount() != 0 && app.getTotalCount() != 0 ? df.format(app.getMenCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc4.setCellValue(menScale);

			HSSFCell hc5 = row.createCell(cellIndex++);
			hc5.setCellStyle(contentStyle);
			HSSFRichTextString womenCount = new HSSFRichTextString(app != null ? app.getWomenCount().toString() : "0");
			hc5.setCellValue(womenCount);

			HSSFCell hc6 = row.createCell(cellIndex++);
			hc6.setCellStyle(contentStyle);
			HSSFRichTextString womenScale = new HSSFRichTextString(
					app != null && app.getWomenCount() != 0 && app.getTotalCount() != 0 ? df.format(app.getWomenCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc6.setCellValue(womenScale);

			HSSFCell hc7 = row.createCell(cellIndex++);
			hc7.setCellStyle(contentStyle);
			HSSFRichTextString graduateCount = new HSSFRichTextString(
					app != null ? app.getGraduateCount().toString() : "0");
			hc7.setCellValue(graduateCount);

			HSSFCell hc8 = row.createCell(cellIndex++);
			hc8.setCellStyle(contentStyle);
			HSSFRichTextString graduateScale = new HSSFRichTextString(
					app != null && app.getGraduateCount() != 0 && app.getTotalCount() != 0 ? df.format(app.getGraduateCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc8.setCellValue(graduateScale);
		}
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	@RequestMapping(value = "exportPeopleCount")
	public void exportPeopleCount(ApplicantsVo applicantsVo, HttpServletResponse response, HttpSession session,HttpServletRequest request) {
		DecimalFormat df = new DecimalFormat("#.#");
		List<ApplicantsVo> applicantsVos = applicantsService.selectPeopleCountByItemsId(applicantsVo);
		String title = "招聘人数统计表";
		String sheetName = "招聘人数统计表";
		ExcelUtils.setResponseHeader(request,response, sheetName + ".xls");
		HSSFWorkbook wb = new HSSFWorkbook();// 声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);// 生成一个表格
		String[] headers = new String[] { "序号","岗位名称", "岗位数", "报考人数", "报考比例", "面试人数", "面试比例", "进入统一笔试人数",
				"进入统一试讲人数","备注" };
		HSSFRow row = setHSSFHeader(wb, sheet, title, headers);
		HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
		for (int i = 0; i < applicantsVos.size(); i++) {
			ApplicantsVo app = applicantsVos.get(i);
			if(app!=null){
				row = sheet.createRow(i + 4);

				int cellIndex = 0;
				HSSFCell hc1 = row.createCell(cellIndex++);
				hc1.setCellStyle(contentStyle);
				HSSFRichTextString index = new HSSFRichTextString(String.valueOf(i+1));
				hc1.setCellValue(index);

				HSSFCell hc2 = row.createCell(cellIndex++);
				hc2.setCellStyle(contentStyle);
				HSSFRichTextString postName = new HSSFRichTextString(app.getPostName());
				hc2.setCellValue(postName);

				HSSFCell hc3 = row.createCell(cellIndex++);
				hc3.setCellStyle(contentStyle);
				HSSFRichTextString jobCount = new HSSFRichTextString(app.getJobCount().toString());
				hc3.setCellValue(jobCount);

				HSSFCell hc4 = row.createCell(cellIndex++);
				hc4.setCellStyle(contentStyle);
				HSSFRichTextString stuCount = new HSSFRichTextString(app.getStuCount().toString());
				hc4.setCellValue(stuCount);

				/*HSSFCell hc5 = row.createCell(cellIndex++);
				hc5.setCellStyle(contentStyle);
				HSSFRichTextString jobStu = new HSSFRichTextString((app.getJobCount().toString())+":"+(app.getStuCount().toString()));
				hc5.setCellValue(jobStu);*/
				
				HSSFCell hc5 = row.createCell(cellIndex++);
				hc5.setCellStyle(contentStyle);
				HSSFRichTextString jobStu = new HSSFRichTextString((app.getJobCount()!=null&&app.getJobCount()!=0)?("1:"+df.format(app.getStuCount().doubleValue()/app.getJobCount())):("0:"+app.getStuCount()));
				hc5.setCellValue(jobStu);
				

				HSSFCell hc6 = row.createCell(cellIndex++);
				hc6.setCellStyle(contentStyle);
				HSSFRichTextString interviewCount = new HSSFRichTextString(app.getInterviewCount().toString());
				hc6.setCellValue(interviewCount);

				/*HSSFCell hc7 = row.createCell(cellIndex++);
				hc7.setCellStyle(contentStyle);
				HSSFRichTextString jobInterview = new HSSFRichTextString((app.getJobCount().toString())+":"+(app.getInterviewCount().toString()));
				hc7.setCellValue(jobInterview);*/
				
				HSSFCell hc7 = row.createCell(cellIndex++);
				hc7.setCellStyle(contentStyle);
				HSSFRichTextString jobInterview = new HSSFRichTextString((app.getJobCount()!=null&&app.getJobCount()!=0)?("1:"+df.format(app.getInterviewCount().doubleValue()/app.getJobCount())):("0:"+app.getInterviewCount()));
				hc7.setCellValue(jobInterview);

				HSSFCell hc8 = row.createCell(cellIndex++);
				hc8.setCellStyle(contentStyle);
				HSSFRichTextString writtenCount = new HSSFRichTextString(app.getWrittenCount().toString());
				hc8.setCellValue(writtenCount);

				HSSFCell hc9 = row.createCell(cellIndex++);
				hc9.setCellStyle(contentStyle);
				HSSFRichTextString trialCount = new HSSFRichTextString(app.getTrialCount().toString());
				hc9.setCellValue(trialCount);

				HSSFCell hc10 = row.createCell(cellIndex++);
				hc10.setCellStyle(contentStyle);
				hc10.setCellValue(new HSSFRichTextString(""));
			}
		}
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "exportRation")
	public void exportRation(ApplicantsVo applicantsVo, HttpServletResponse response, HttpSession session,HttpServletRequest request) {
		List<ApplicantsVo> applicantsVos = applicantsService.selectAllByItemsId(applicantsVo);
		DecimalFormat df = new DecimalFormat("#.##");
		String title = "招聘比例统计表";
		String sheetName = "招聘比例统计表";
		ExcelUtils.setResponseHeader(request,response, sheetName + ".xls");
		HSSFWorkbook wb = new HSSFWorkbook();// 声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);// 生成一个表格
		HSSFRow row = applicantsService.setRationHeader(wb, sheet, title);
		HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
		for (int i = 0; i < applicantsVos.size(); i++) {
			ApplicantsVo app = applicantsVos.get(i);
			row = sheet.createRow(i + 5);

			int cellIndex = 0;
			HSSFCell hc1 = row.createCell(cellIndex++);
			hc1.setCellStyle(contentStyle);
			HSSFRichTextString itemsName = new HSSFRichTextString(app != null ? app.getItemsName() : "");
			hc1.setCellValue(itemsName);

			HSSFCell hc2 = row.createCell(cellIndex++);
			hc2.setCellStyle(contentStyle);
			HSSFRichTextString totalCount = new HSSFRichTextString(app != null ? app.getTotalCount().toString() : "0");
			hc2.setCellValue(totalCount);

			HSSFCell hc3 = row.createCell(cellIndex++);
			hc3.setCellStyle(contentStyle);
			HSSFRichTextString menCount = new HSSFRichTextString(app != null ? app.getMenCount().toString() : "0");
			hc3.setCellValue(menCount);

			HSSFCell hc4 = row.createCell(cellIndex++);
			hc4.setCellStyle(contentStyle);
			HSSFRichTextString menScale = new HSSFRichTextString(
					app != null && app.getTotalCount() != null && app.getTotalCount() != 0
							? df.format(app.getMenCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc4.setCellValue(menScale);

			HSSFCell hc5 = row.createCell(cellIndex++);
			hc5.setCellStyle(contentStyle);
			HSSFRichTextString womenCount = new HSSFRichTextString(app != null ? app.getWomenCount().toString() : "0");
			hc5.setCellValue(womenCount);

			HSSFCell hc6 = row.createCell(cellIndex++);
			hc6.setCellStyle(contentStyle);
			HSSFRichTextString womenScale = new HSSFRichTextString(
					app != null && app.getTotalCount() != null && app.getTotalCount() != 0
							? df.format(app.getWomenCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc6.setCellValue(womenScale);

			HSSFCell hc7 = row.createCell(cellIndex++);
			hc7.setCellStyle(contentStyle);
			HSSFRichTextString graduateCount = new HSSFRichTextString(
					app != null ? app.getGraduateCount().toString() : "0");
			hc7.setCellValue(graduateCount);

			HSSFCell hc8 = row.createCell(cellIndex++);
			hc8.setCellStyle(contentStyle);
			HSSFRichTextString graduateScale = new HSSFRichTextString(
					app != null && app.getTotalCount() != null && app.getTotalCount() != 0
							? df.format(app.getGraduateCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc8.setCellValue(graduateScale);

			HSSFCell hc9 = row.createCell(cellIndex++);
			hc9.setCellStyle(contentStyle);
			HSSFRichTextString bachelorCount = new HSSFRichTextString(
					app != null ? app.getBachelorCount().toString() : "0");
			hc9.setCellValue(bachelorCount);

			HSSFCell hc10 = row.createCell(cellIndex++);
			hc10.setCellStyle(contentStyle);
			HSSFRichTextString bachelorScale = new HSSFRichTextString(
					app != null && app.getTotalCount() != null && app.getTotalCount() != 0
							? df.format(app.getBachelorCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc10.setCellValue(bachelorScale);

			HSSFCell hc11 = row.createCell(cellIndex++);
			hc11.setCellStyle(contentStyle);
			HSSFRichTextString collegeCount = new HSSFRichTextString(
					app != null ? app.getCollegeCount().toString() : "0");
			hc11.setCellValue(collegeCount);

			HSSFCell hc12 = row.createCell(cellIndex++);
			hc12.setCellStyle(contentStyle);
			HSSFRichTextString collegeScale = new HSSFRichTextString(
					app != null && app.getTotalCount() != null && app.getTotalCount() != 0
							? df.format(app.getCollegeCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc12.setCellValue(collegeScale);

			HSSFCell hc13 = row.createCell(cellIndex++);
			hc13.setCellStyle(contentStyle);
			HSSFRichTextString secondaryCount = new HSSFRichTextString(
					app != null ? app.getSecondaryCount().toString() : "0");
			hc13.setCellValue(secondaryCount);

			HSSFCell hc14 = row.createCell(cellIndex++);
			hc14.setCellStyle(contentStyle);
			HSSFRichTextString secondaryScale = new HSSFRichTextString(
					app != null && app.getTotalCount() != null && app.getTotalCount() != 0
							? df.format(app.getSecondaryCount().doubleValue() * 100 / app.getTotalCount()) + "%" : "0%");
			hc14.setCellValue(secondaryScale);

			HSSFCell hc15 = row.createCell(cellIndex++);
			hc15.setCellStyle(contentStyle);
			hc15.setCellValue(new HSSFRichTextString(""));
		}
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置标题头内容
	 * 
	 * @param wb
	 * @param sheet
	 * @param title
	 * @param headers
	 */
	private static HSSFRow setHSSFHeader(HSSFWorkbook wb, HSSFSheet sheet, String title, String[] headers) {
		// 设置表格默认列宽、行高
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeightInPoints(15);
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, headers.length - 1));
		HSSFCellStyle titleStyle = ExcelUtils.setTitleStyle(wb, 15);
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) (25 * 25));
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(titleStyle);

		HSSFCellStyle headerStyle = ExcelUtils.setThStyle(wb, 12);
		row = sheet.createRow(3);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell hc = row.createCell(i);
			hc.setCellStyle(headerStyle);
			hc.setCellValue(headers[i]);
		}
		return row;
	}

	@RequestMapping(value = "interview")
	public String interviewList(Model model) {
		Map<String, Object> queryParameter = new HashMap<String, Object>();
		queryParameter.put("isPublish", "1");// 招聘公告已发布
		queryParameter.put("status", "2");// 招聘计划已审核
		queryParameter.put("themeStatus", "1");// 招聘主题已发布
		List<String> years = planApplyService.getPlanApplyYears(queryParameter);// 当前登录用户学校ID
		model.addAttribute("years", years);
		return "/statistics/interview_list.vm";
	}

	@ResponseBody
	@RequestMapping(value = "selectAllInformation", method = RequestMethod.POST)
	public String selectAllInformation(Page page) {
		Map<String, Object> condition = page.getCondition();
		page = informationService.selectOnePageByMap(page, condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
		jsonMap.put("rows", page.getResult());
		jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @RequestMapping(value="position")
    public String positionVoSubject(Model model){
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("isPublish", "1");//招聘公告已发布
    	queryParameter.put("status", "2");//招聘计划已审核
    	queryParameter.put("themeStatus", "1");//招聘主题已发布
    	List<String> years=planApplyService.getPlanApplyYears(queryParameter);//当前登录用户学校ID
    	model.addAttribute("years", years);
    	return "/statistics/positionVoSubject_list.vm";
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllPositionVoSubject")
	public String selectPositionVoSubjectList(Page page) {
		Map<String, Object> condition =page.getCondition();
		page = positionService.getPositionVoSubjectList(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
	    jsonMap.put("rows", page.getResult());
	    jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @RequestMapping(value="positionUnit")
    public String positionVoUnit(Model model){
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("isPublish", "1");//招聘公告已发布
    	queryParameter.put("status", "2");//招聘计划已审核
    	queryParameter.put("themeStatus", "1");//招聘主题已发布
    	List<String> years=planApplyService.getPlanApplyYears(queryParameter);//当前登录用户学校ID
    	model.addAttribute("years", years);
    	return "/statistics/positionVoUnit_list.vm";
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllPositionVoUnit")
	public String selectPositionVoUnitList(Page page) {
		Map<String, Object> condition =page.getCondition();
		page = positionService.getPositionVoUnitList(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
	    jsonMap.put("rows", page.getResult());
	    jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @RequestMapping(value="exportPositionVoSubject")
    public void exportPositionVoSubject(String projectId,HttpServletResponse response,HttpServletRequest request){
    	Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(projectId)){
			condition.put("projectId", projectId);
		}
		List<PositionVo> PositionVos=positionService.queryPositionVoSubjectList(condition);
		String title="东莞市教育局系统面向社会公开招聘办公教职员岗位表1";
		String sheetName="学科分岗位导出";
		ExcelUtils.setResponseHeader(request,response,sheetName+".xls");
		HSSFWorkbook wb = new HSSFWorkbook();//声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);//生成一个表格
		HSSFRow row=this.setHSSFTitle(wb, sheet, title, 13);
		//合并单元格 四个参数分别为：开始行结束行，开始列结束列
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 5));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 9));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 10, 10));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
		String[] header1 = new String []{ "序号", "岗位名称" , "学科代码","招聘人员","招聘对象", "条件","其他条件","岗位类别","备注"};
		String[] header2 = new String []{ "毕业生","社会人员","年龄","学历（含第一学历和最高学历）","学位","专业"};
		this.setHSSFHeader1(wb, sheet, header1);
		this.setHSSFHeader2(wb, sheet, header2);
        HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
        for(int i=0;i<PositionVos.size();i++){
        	PositionVo positionVo=PositionVos.get(i);
        	row = sheet.createRow(i+3);
        	
        	int cellIndex=0;
        	HSSFCell hc1 = row.createCell(cellIndex++);
        	hc1.setCellStyle(contentStyle);
    		HSSFRichTextString index = new HSSFRichTextString(String.valueOf(i+1));
    		hc1.setCellValue(index);
    		
    		HSSFCell hc2 = row.createCell(cellIndex++);
    		hc2.setCellStyle(contentStyle);
    		HSSFRichTextString postName = new HSSFRichTextString(positionVo !=null ? positionVo.getPostName() : "");
    		hc2.setCellValue(postName);
    		
    		HSSFCell hc3 = row.createCell(cellIndex++);
    		hc3.setCellStyle(contentStyle);
    		HSSFRichTextString subject = new HSSFRichTextString(positionVo != null ? positionVo.getSubject(): "");
    		hc3.setCellValue(subject);
    		
    		HSSFCell hc4 = row.createCell(cellIndex++);
    		hc4.setCellStyle(contentStyle);
    		HSSFRichTextString approveCount = new HSSFRichTextString(positionVo != null ? positionVo.getApproveCount(): "");
    		hc4.setCellValue(approveCount);
    		
    		HSSFCell hc5 = row.createCell(cellIndex++);
    		hc5.setCellStyle(contentStyle);
    		HSSFRichTextString graduate = new HSSFRichTextString(positionVo != null ? positionVo.getGraduate(): "");
    		hc5.setCellValue(graduate);
    		
    		HSSFCell hc6 = row.createCell(cellIndex++);
    		hc6.setCellStyle(contentStyle);
    		HSSFRichTextString social = new HSSFRichTextString(positionVo != null ? positionVo.getSocial(): "");
    		hc6.setCellValue(social);
    		
    		HSSFCell hc7 = row.createCell(cellIndex++);
    		hc7.setCellStyle(contentStyle);
    		HSSFRichTextString age = new HSSFRichTextString(positionVo != null ? positionVo.getAge(): "");
    		hc7.setCellValue(age);
    		
    		HSSFCell hc8 = row.createCell(cellIndex++);
    		hc8.setCellStyle(contentStyle);
    		HSSFRichTextString education = new HSSFRichTextString(positionVo != null ? positionVo.getEducation(): "");
    		hc8.setCellValue(education);
    		
    		HSSFCell hc9 = row.createCell(cellIndex++);
    		hc9.setCellStyle(contentStyle);
    		HSSFRichTextString degree = new HSSFRichTextString(positionVo != null ? positionVo.getDegree(): "");
    		hc9.setCellValue(degree);
    		
    		HSSFCell hc10 = row.createCell(cellIndex++);
    		hc10.setCellStyle(contentStyle);
    		HSSFRichTextString profession = new HSSFRichTextString(positionVo != null ? positionVo.getProfession(): "");
    		hc10.setCellValue(profession);
    		
    		HSSFCell hc11 = row.createCell(cellIndex++);
    		hc11.setCellStyle(contentStyle);
    		HSSFRichTextString otherLimit = new HSSFRichTextString(positionVo != null ? positionVo.getOtherLimit(): "");
    		hc11.setCellValue(otherLimit);
    		
    		HSSFCell hc12 = row.createCell(cellIndex++);
    		hc12.setCellStyle(contentStyle);
    		HSSFRichTextString category = new HSSFRichTextString(positionVo != null ? positionVo.getCategory(): "");
    		hc12.setCellValue(category);
    		
    		HSSFCell hc13 = row.createCell(cellIndex++);
    		hc13.setCellStyle(contentStyle);
    		HSSFRichTextString remark = new HSSFRichTextString(positionVo != null ? positionVo.getRemark(): "");
    		hc13.setCellValue(remark);
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
    
    /**
     * 设置标题头内容
     * @param wb
     * @param sheet
     * @param title
     * @param headerLength
     */
    private HSSFRow setHSSFTitle(HSSFWorkbook wb,HSSFSheet sheet,String title,int headerLength){
    	//设置表格默认列宽、行高
    	sheet.setDefaultColumnWidth(15);
    	sheet.setDefaultRowHeightInPoints(15);
    	//合并单元格 四个参数分别为：开始行开始列，结束行结束列 
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headerLength-1));
		HSSFCellStyle titleStyle = ExcelUtils.setTitleStyle(wb,15);
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) (25 * 25));
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(title);  
        cell.setCellStyle(titleStyle);
        return row;
    }
    
    private void setHSSFHeader1(HSSFWorkbook wb,HSSFSheet sheet,String[] headers){
    	HSSFCellStyle headerStyle = ExcelUtils.setTitleStyle(wb,12);
    	HSSFRow row = sheet.createRow(1);
    	for(int i = 0; i < 5; i++){
        	HSSFCell hc = row.createCell(i);
        	hc.setCellStyle(headerStyle);
        	hc.setCellValue(headers[i]);
        }
    	
    	HSSFCell hc = row.createCell(6);
    	hc.setCellStyle(headerStyle);
    	hc.setCellValue(headers[5]);
    	
    	for(int i = 6; i < 9; i++){
        	HSSFCell hc2 = row.createCell(i+4);
        	hc2.setCellStyle(headerStyle);
        	hc2.setCellValue(headers[i]);
        }
    }
    
    private void setHSSFHeader2(HSSFWorkbook wb,HSSFSheet sheet,String[] headers){
    	HSSFCellStyle headerStyle = ExcelUtils.setTitleStyle(wb,12);
    	HSSFRow row = sheet.createRow(2);
        for(int i = 0; i < headers.length; i++){
        	HSSFCell hc = row.createCell(i+4);
        	hc.setCellStyle(headerStyle);
        	hc.setCellValue(headers[i]);
        }
    }
    
    @RequestMapping(value="exportPositionVoUnit")
    public void exportPositionVoUnit(String projectId,HttpServletResponse response,HttpServletRequest request){
    	Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(projectId)){
			condition.put("projectId", projectId);
		}
		List<PositionVo> PositionVos=positionService.queryPositionVoUnitList(condition);
		String title="东莞市教育局系统面向社会公开招聘办公教职员岗位表2";
		String sheetName="单位分岗位";
		ExcelUtils.setResponseHeader(request,response,sheetName+".xls");
		HSSFWorkbook wb = new HSSFWorkbook();//声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);//生成一个表格
		HSSFRow row=this.setHSSFTitle(wb, sheet, title, 14);
		//合并单元格 四个参数分别为：开始行结束行，开始列结束列
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 6));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 10));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
		String[] header1 = new String []{ "序号","单位名称","岗位名称" , "学科代码","招聘人员","招聘对象", "条件","其他条件","岗位类别","备注"};
		String[] header2 = new String []{ "毕业生","社会人员","年龄","学历（含第一学历和最高学历）","学位","专业"};
		this.setHSSFHeader3(wb, sheet, header1);
		this.setHSSFHeader4(wb, sheet, header2);
        HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
        for(int i=0;i<PositionVos.size();i++){
        	PositionVo positionVo=PositionVos.get(i);
        	row = sheet.createRow(i+3);
        	
        	int cellIndex=0;
        	HSSFCell hc0 = row.createCell(cellIndex++);
        	hc0.setCellStyle(contentStyle);
    		HSSFRichTextString index = new HSSFRichTextString(String.valueOf(i+1));
    		hc0.setCellValue(index);
    		
    		HSSFCell hc1 = row.createCell(cellIndex++);
    		hc1.setCellStyle(contentStyle);
    		HSSFRichTextString schoolName = new HSSFRichTextString(positionVo !=null ? positionVo.getSchoolName() : "");
    		hc1.setCellValue(schoolName);
    		
    		HSSFCell hc2 = row.createCell(cellIndex++);
    		hc2.setCellStyle(contentStyle);
    		HSSFRichTextString postName = new HSSFRichTextString(positionVo !=null ? positionVo.getPostName() : "");
    		hc2.setCellValue(postName);
    		
    		HSSFCell hc3 = row.createCell(cellIndex++);
    		hc3.setCellStyle(contentStyle);
    		HSSFRichTextString subject = new HSSFRichTextString(positionVo != null ? positionVo.getSubject(): "");
    		hc3.setCellValue(subject);
    		
    		HSSFCell hc4 = row.createCell(cellIndex++);
    		hc4.setCellStyle(contentStyle);
    		HSSFRichTextString approveCount = new HSSFRichTextString(positionVo != null ? positionVo.getApproveCount(): "");
    		hc4.setCellValue(approveCount);
    		
    		HSSFCell hc5 = row.createCell(cellIndex++);
    		hc5.setCellStyle(contentStyle);
    		HSSFRichTextString graduate = new HSSFRichTextString(positionVo != null ? positionVo.getGraduate(): "");
    		hc5.setCellValue(graduate);
    		
    		HSSFCell hc6 = row.createCell(cellIndex++);
    		hc6.setCellStyle(contentStyle);
    		HSSFRichTextString social = new HSSFRichTextString(positionVo != null ? positionVo.getSocial(): "");
    		hc6.setCellValue(social);
    		
    		HSSFCell hc7 = row.createCell(cellIndex++);
    		hc7.setCellStyle(contentStyle);
    		HSSFRichTextString age = new HSSFRichTextString(positionVo != null ? positionVo.getAge(): "");
    		hc7.setCellValue(age);
    		
    		HSSFCell hc8 = row.createCell(cellIndex++);
    		hc8.setCellStyle(contentStyle);
    		HSSFRichTextString education = new HSSFRichTextString(positionVo != null ? positionVo.getEducation(): "");
    		hc8.setCellValue(education);
    		
    		HSSFCell hc9 = row.createCell(cellIndex++);
    		hc9.setCellStyle(contentStyle);
    		HSSFRichTextString degree = new HSSFRichTextString(positionVo != null ? positionVo.getDegree(): "");
    		hc9.setCellValue(degree);
    		
    		HSSFCell hc10 = row.createCell(cellIndex++);
    		hc10.setCellStyle(contentStyle);
    		HSSFRichTextString profession = new HSSFRichTextString(positionVo != null ? positionVo.getProfession(): "");
    		hc10.setCellValue(profession);
    		
    		HSSFCell hc11 = row.createCell(cellIndex++);
    		hc11.setCellStyle(contentStyle);
    		HSSFRichTextString otherLimit = new HSSFRichTextString(positionVo != null ? positionVo.getOtherLimit(): "");
    		hc11.setCellValue(otherLimit);
    		
    		HSSFCell hc12 = row.createCell(cellIndex++);
    		hc12.setCellStyle(contentStyle);
    		HSSFRichTextString category = new HSSFRichTextString(positionVo != null ? positionVo.getCategory(): "");
    		hc12.setCellValue(category);
    		
    		HSSFCell hc13 = row.createCell(cellIndex++);
    		hc13.setCellStyle(contentStyle);
    		HSSFRichTextString remark = new HSSFRichTextString(positionVo != null ? positionVo.getRemark(): "");
    		hc13.setCellValue(remark);
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
    
    private void setHSSFHeader3(HSSFWorkbook wb,HSSFSheet sheet,String[] headers){
    	HSSFCellStyle headerStyle = ExcelUtils.setTitleStyle(wb,12);
    	HSSFRow row = sheet.createRow(1);
    	for(int i = 0; i < 5; i++){
        	HSSFCell hc = row.createCell(i);
        	hc.setCellStyle(headerStyle);
        	hc.setCellValue(headers[i]);
        }
    	
    	HSSFCell hc = row.createCell(5);
    	hc.setCellStyle(headerStyle);
    	hc.setCellValue(headers[5]);
    	
    	hc = row.createCell(7);
    	hc.setCellStyle(headerStyle);
    	hc.setCellValue(headers[6]);
    	
    	for(int i = 7; i < 10; i++){
        	HSSFCell hc2 = row.createCell(i+4);
        	hc2.setCellStyle(headerStyle);
        	hc2.setCellValue(headers[i]);
        }
    }
    
    private void setHSSFHeader4(HSSFWorkbook wb,HSSFSheet sheet,String[] headers){
    	HSSFCellStyle headerStyle = ExcelUtils.setTitleStyle(wb,12);
    	HSSFRow row = sheet.createRow(2);
        for(int i = 0; i < headers.length; i++){
        	HSSFCell hc = row.createCell(i+5);
        	hc.setCellStyle(headerStyle);
        	hc.setCellValue(headers[i]);
        }
    }
}
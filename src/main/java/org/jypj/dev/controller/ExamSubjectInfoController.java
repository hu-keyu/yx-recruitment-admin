package org.jypj.dev.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.EaxmLabsInfo;
import org.jypj.dev.entity.ExamItemsInfo;
import org.jypj.dev.entity.ExamLectureGroup;
import org.jypj.dev.entity.ExamSubjectInfo;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.service.EaxmLabsInfoService;
import org.jypj.dev.service.EaxmLabsRecordService;
import org.jypj.dev.service.ExamItemsInfoService;
import org.jypj.dev.service.ExamLectureGroupService;
import org.jypj.dev.service.ExamSubjectInfoService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.ExcelHelper;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.vo.AdjustStudentRoomInfoVo;
import org.jypj.dev.vo.ClassRoomIReportVo;
import org.jypj.dev.vo.SecondLayOutRoomVo;
import org.jypj.dev.vo.StudentLectureVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * ExamSubjectInfo控制器
 * 
 * @author ChenYu
 *
 */
@Controller
@RequestMapping("dg/examSubjectInfo")
public class ExamSubjectInfoController {
	@Resource
	private ExamItemsInfoService examItemsInfoService;
	
	@Resource
	private NoticeService  noticeService;

	@Resource
	private ThemeService themeService;

	@Resource
	private ExamSubjectInfoService examSubjectInfoService;

	@Resource
	private EaxmLabsRecordService eaxmLabsRecordService;

	@Resource
	private EaxmLabsInfoService eaxmLabsInfoService;

	@Resource
	private StudentInfoService studentInfoService;

	@Resource
	private StudentApplyInfoService studentApplayService;

	@Resource
	private PostsetService postsetService;

	@Resource
	private ScoreEnterTrialService scoreEnterTrialService;

	@Resource
	private ExamLectureGroupService examLectureGroupService;

	@Resource
	private DictionaryService dictionaryService;

	@RequestMapping(value = "toExamSubjectInfoList")
	public String toExamSubjectInfoList() {

		return "";
	}

	@RequestMapping("toExamSubjectInfo")
	public String toExamSubjectInfo(Model m) {
		// 考试类型
		List<Dictionary> kslxdics = DictionaryCache.getDictionaryByCode("kslx");
		List<Dictionary> dataList=new ArrayList<Dictionary>();
		for(Dictionary d:kslxdics)
		{
			if("2".equals(d.getValue())||"3".equals(d.getValue()))
			{
				dataList.add(d);
			}
		}
		m.addAttribute("kslxdics", dataList);
		m.addAttribute("dates",new Date()) ;
		m.addAttribute("dateList",examSubjectInfoService.queryThemeDate());
		return "kwgl/roomLayout_list.vm";
	}

	/**
	 * 进入试室调整页面
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("toAdjustExamSubjectInfo")
	public String toAdjustExamSubjectInfo(Model m, HttpServletRequest request) {
		// 考试类型
		List<Dictionary> kslxdics = DictionaryCache.getDictionaryByCode("kslx");
		List<Dictionary> dataList=new ArrayList<Dictionary>();
		for(Dictionary d:kslxdics)
		{
			if("2".equals(d.getValue())||"3".equals(d.getValue()))
			{
				dataList.add(d);
			}
		}
		m.addAttribute("kslxdics", dataList);
		m.addAttribute("dateList",examSubjectInfoService.queryThemeDate());
		return "kwgl/roomAdjust_list.vm";
	}

	/**
	 * 查询项目对应的考点
	 * 
	 * @return
	 */
	@RequestMapping("selectThemeRoom")
	@ResponseBody
	public List<ExamItemsInfo> selectThemeRoom(ExamItemsInfo e, HttpServletRequest request) {
		String type = request.getParameter("type");
		if (type!=""&&type != null) {
			Dictionary d = dictionaryService.selectDictionaryById(type);
			e.setType(d.getValue());
		}
		if(request.getParameter("bsType")!=null)
		{
			e.setType(request.getParameter("bsType"));
		}
		e.setItemsId(request.getParameter("itemsId"));
	    e.setDeleteStatus("2");
		List<ExamItemsInfo> dataList = examItemsInfoService.selectAllByExamItemsInfo(e);
		return dataList;
	}

	/**
	 * 获取附加室试信息
	 * 
	 * @return
	 */
	@RequestMapping("selectSecondRoom")
	@ResponseBody
	public List<EaxmLabsInfo> selectSecondRoom(HttpServletRequest reqeust) {
		String ryn = reqeust.getParameter("ryn");
		String itemsId = reqeust.getParameter("itemsId");
		String kaodian = reqeust.getParameter("kaodian");
		EaxmLabsInfo queryParam = new EaxmLabsInfo();
		queryParam.setEmpItemsId(itemsId);
		queryParam.setTestId(kaodian);
		queryParam.setNyr(ryn);
		queryParam.setIsAdd("1");
		List<EaxmLabsInfo> dataList = eaxmLabsInfoService.selectAllByEaxmLabsInfo(queryParam);
		return dataList;
	}

	/**
	 * 查询某个试室分配的学生列表
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("queryRoomStudent")
	@ResponseBody
	public List<AdjustStudentRoomInfoVo> queryRoomStudent(HttpServletRequest reqeust) {
		String itemsId = reqeust.getParameter("itemsId");
		String labsid = reqeust.getParameter("labsid");
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("itemsId", itemsId);
		queryMap.put("labsid", labsid);
		List<AdjustStudentRoomInfoVo> dataList = examSubjectInfoService.querySecondLayoutRoom(queryMap);
		List<AdjustStudentRoomInfoVo> tempList = new ArrayList<AdjustStudentRoomInfoVo>();
		for (AdjustStudentRoomInfoVo vo : dataList) {
			if (vo.getSutid().contains(",")) {
				tempList.add(vo);
			}
		}
		return dataList;
	}

	/**
	 * 编排非附加试室岗位对应的房间
	 * 
	 * @return
	 */
	@RequestMapping("addGanWeiRoom")
	@ResponseBody
	public Map<String, Object> addGanWeiRoom(HttpServletRequest reqeust) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ryn = reqeust.getParameter("ryn");
		String itemsId = reqeust.getParameter("itemsId");
		String type = reqeust.getParameter("type");
		String kaodian = reqeust.getParameter("kaodian");
		map.put("ryn", ryn);
		map.put("itemsId", itemsId);
		map.put("type", type);
		map.put("kaodian", kaodian);

		 //查询主题
		Theme theme = themeService.selectThemeById(itemsId);
		if (theme.getStep() == 1 || theme.getStep() == 2 || theme.getStep() == 3) {
			map.put("msg", "还没进入发布笔试流程!");
			return map;
		}
		Map<String, Object> returnMap = examSubjectInfoService.addGanWeiRoom(map);
		// 查询非附加试室总数
		ExamSubjectInfo queryInfo = new ExamSubjectInfo();
		queryInfo.setEmpItemsId(itemsId);
		queryInfo.setTestId(kaodian);
		queryInfo.setNyr(ryn);
		queryInfo.setIsAdd("2");
		List<ExamSubjectInfo> d1List = examSubjectInfoService.selectAllByExamSubjectInfo(queryInfo);
		// 查询附加试室总数
		queryInfo.setEmpItemsId(itemsId);
		queryInfo.setTestId(kaodian);
		queryInfo.setNyr(ryn);
		queryInfo.setIsAdd("1");
		List<ExamSubjectInfo> d2List = examSubjectInfoService.selectAllByExamSubjectInfo(queryInfo);
		int noLayoutNum = 0;
		for (ExamSubjectInfo e : d1List) {
			noLayoutNum = noLayoutNum + Integer.parseInt(e.getLabsAmount());
		}
		int totalNum = noLayoutNum + d2List.size();
		returnMap.put("layoutNum", d2List.size());
		returnMap.put("totalNum", totalNum);
		returnMap.put("msg", "success");
		return returnMap;
	}

	/**
	 * 进入手动调整附加试室页面
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("toSecondLayoutRoom")
	public String toSecondLayoutRoom(HttpServletRequest reqeust) {

		return "kwgl/editSencodLayoutRoom.vm";
	}

	/**
	 * 获取编辑具体数据
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("getSelectEditData")
	@ResponseBody
	public Map<String, Object> getSelectEditData(HttpServletRequest reqeust) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("ryn", reqeust.getParameter("ryn"));
		return examSubjectInfoService.getSelectEditData(param);
	}

	/**
	 * 获取编辑具体数据
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("checkIsEdit")
	@ResponseBody
	public Map<String, Object> checkIsEdit(HttpServletRequest reqeust) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		int num=examSubjectInfoService.querySecondKaoDianNum(param);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (num > 0) {
			returnMap.put("msg", "试室编排已发布，不能手动调整!");
		} else {
			returnMap.put("msg", "");
		}
		return returnMap;
	}

	/**
	 * 编排附加试室岗位对应的房间
	 * 
	 * @return
	 */
	@RequestMapping("addSecondGanWeiRoom")
	@ResponseBody
	public Map<String, String> addSecondGanWeiRoom(HttpServletRequest reqeust) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("paramJson", reqeust.getParameter("paramJson"));
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		try {
			String mes = examSubjectInfoService.addSecondGanWeiRoom(param);
			if (mes == "") {
				map.put("msg", "success");
			} else {
				map.put("msg", mes);
			}
		} catch (Exception e) {
			map.put("msg", "error");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 删除附加试室岗位对应的房间
	 * 
	 * @return
	 */
	@RequestMapping("delSecondGanWeiRoom")
	@ResponseBody
	public Map<String, Object> delSecondGanWeiRoom(HttpServletRequest reqeust) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("ganweiid", reqeust.getParameter("ganweiid"));
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("addrooms", reqeust.getParameter("addrooms"));
		
		param.put("ryn", reqeust.getParameter("ryn"));
		try {
			List<SecondLayOutRoomVo> list = examSubjectInfoService.delSecondGanWeiRoom(param);
			map.put("msg", list);
		} catch (Exception e) {
			map.put("msg", "error");
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("showRoomAndStudentResult")
	@ResponseBody
	public Map<String, Object> showRoomAndStudentResult(HttpServletRequest reqeust) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("ryn", reqeust.getParameter("ryn"));
		param.put("type", reqeust.getParameter("type"));
		try {
			String mes = examSubjectInfoService.addRoomAndStudentResult(param);
			if ("".equals(mes)) {
				map.put("msg", "success");
			} else {
				map.put("msg", mes);
			}
		} catch (Exception e) {
			map.put("msg", "error");
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("batchAdjust")
	@ResponseBody
	public Map<String, Object> batchAdjust(HttpServletRequest reqeust) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("sutidStr", reqeust.getParameter("sutidStr"));
		param.put("toLabsid", reqeust.getParameter("toLabsid"));
		param.put("fromLabsid", reqeust.getParameter("fromLabsid"));

		// 查询主题
		Theme theme = themeService.selectThemeById(reqeust.getParameter("itemsId"));
		if (theme.getStep() == 1 || theme.getStep() == 2 || theme.getStep() == 3) {
			map.put("msg", "还没进入发笔试流程!");
			return map;
		}
		//通过公共时间判断
		Notice notice=noticeService.selectObjectByThemeId(reqeust.getParameter("itemsId"));
		if(notice.getWrittenStart().getTime()<new Date().getTime())
		{
			map.put("msg", "笔试流程已结束!");
			return map;
		}
		try {
			String mes = examSubjectInfoService.updateBatchAdjust(param);
			if (mes == "") {
				map.put("msg", "success");
			} else {
				map.put("msg", mes);
			}
		} catch (Exception e) {
			map.put("msg", "error");
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("toQueryLayoutDetail")
	public String toQueryLayoutDetail(HttpServletRequest reqeust) {

		return "kwgl/roomLayout_detail.vm";
	}

	@RequestMapping("queryLayoutDetail")
	@ResponseBody
	public Map<String, Object> queryLayoutDetail(HttpServletRequest reqeust) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("labid", reqeust.getParameter("labid"));
		return examSubjectInfoService.queryLayoutDetail(param);
	}

	@RequestMapping(value = "exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		User user=(User)request.getSession().getAttribute("user");
		String str = "";
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", request.getParameter("itemsId"));
		param.put("labsid", request.getParameter("labid"));
		String type = request.getParameter("type");
		// 查询考点
		ExamItemsInfo iteminfo = examItemsInfoService.selectExamItemsInfoById(request.getParameter("kaodian"));
		// 查询试室
		EaxmLabsInfo lbsinfo = eaxmLabsInfoService.selectEaxmLabsInfoById(request.getParameter("labid"));
		List<AdjustStudentRoomInfoVo> dataList = examSubjectInfoService.querySecondLayoutRoom(param);
		Collections.sort(dataList, new Comparator<AdjustStudentRoomInfoVo>() {
			@Override
			public int compare(AdjustStudentRoomInfoVo b1, AdjustStudentRoomInfoVo b2) {
				if (Integer.parseInt(b1.getSeatNum()) > Integer.parseInt(b2.getSeatNum())) {
					return 1;
				} else {
					return -1;
				}
			}

		});
		WritableWorkbook wwb = null;// Excel数据组装
		String usersTempPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
				+ "template.xls";
		String createPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
				+ "template_write_"+user.getId()+".xls";
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(usersTempPath));
			 WritableCellFormat normalFormat = new WritableCellFormat(); 
			 normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
	                    jxl.format.Colour.BLACK);
			wwb = Workbook.createWorkbook(new File(createPath), workbook);// 拷贝模板，把拷贝的模板临时放一个路径下
			WritableSheet sheet = wwb.getSheet(0);
			// 排列第一列
			boolean isoperate = this.exportSeatTable(sheet, dataList, 7, 3, 0, type,normalFormat);
			if (isoperate) {
				isoperate = this.exportSeatTable(sheet, dataList, 8, 3, 3, type,normalFormat);
				if (isoperate) {
					isoperate = this.exportSeatTable(sheet, dataList, 8, 3, 6, type,normalFormat);
					if (isoperate) {
						isoperate = this.exportSeatTable(sheet, dataList, 7, 3, 9, type,normalFormat);
					}
				}
			}
			sheet.mergeCells(0, 0, 10, 2);
			WritableFont wf = new WritableFont(WritableFont.TAHOMA, 15, WritableFont.BOLD);
			Label label = new Label(0, 0, iteminfo.getTestName() + "-" + lbsinfo.getLabsName() + "试室");
			WritableCellFormat cellFormat = new WritableCellFormat(wf);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			label.setCellFormat(cellFormat);
			sheet.addCell(label);
			if (type.equals("1")) {
				str = "座位表门贴";
			} else {
				str = "桌贴";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭Excel工作薄对象
			if (wwb != null) {
				try {
					wwb.write();
					wwb.close();
					ExcelHelper.outputExcel(response,
							iteminfo.getTestName() + "-" + lbsinfo.getLabsName() + "试室-" + str, createPath);
					new File(createPath).delete();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (workbook != null) {
				workbook.close();
			}
		}
	}

	/**
	 * 
	 * @param sheet
	 * @param dataList
	 * @param j
	 * @param m
	 * @param n
	 * @return
	 */
	private boolean exportSeatTable(WritableSheet sheet, List<AdjustStudentRoomInfoVo> dataList, int j, int m, int n,
			String type, WritableCellFormat normalFormat) {
		boolean flag = true;
		try {
			List<AdjustStudentRoomInfoVo> tempList = new ArrayList<AdjustStudentRoomInfoVo>();
			if (j > dataList.size()) {
				tempList.addAll(dataList.subList(0, dataList.size()));
				flag = false;
			} else {
				tempList.addAll(dataList.subList(0, j));
				dataList.subList(0, j).clear();

			}
			if ("1".equals(type)) {
				for (int i = 0; i < tempList.size(); i++) {
					AdjustStudentRoomInfoVo vo = tempList.get(i);
					sheet.addCell(new Label((n), m, "姓名",normalFormat));
					sheet.addCell(new Label((n + 1), m, "准考证号",normalFormat));
					m++;
					sheet.addCell(new Label((n), m, vo.getName(),normalFormat));
					sheet.addCell(new Label((n + 1), m, vo.getTestCardNum(),normalFormat));
					m++;
					sheet.addCell(new Label((n), m, "性别",normalFormat));
					sheet.addCell(new Label((n + 1), m, "座位号",normalFormat));
					m++;
					sheet.addCell(new Label((n), m, vo.getSex(),normalFormat));
					sheet.addCell(new Label((n + 1), m, vo.getSeatNum(),normalFormat));
					m = m + 2;
				}
			} else {
				for (int i = 0; i < tempList.size(); i++) {
					AdjustStudentRoomInfoVo vo = tempList.get(i);
					sheet.addCell(new Label((n), m, "姓名",normalFormat));
					sheet.addCell(new Label((n + 1), m, "准考证号",normalFormat));
					m++;
					sheet.addCell(new Label((n), m, vo.getName(),normalFormat));
					sheet.addCell(new Label((n + 1), m, vo.getTestCardNum(),normalFormat));
					m++;
					sheet.addCell(new Label((n), m, "性别",normalFormat));
					sheet.addCell(new Label((n + 1), m, "身份证号",normalFormat));
					m++;
					sheet.addCell(new Label((n), m, vo.getSex(),normalFormat));
					sheet.addCell(new Label((n + 1), m, vo.getCard(),normalFormat));
					m++;
					sheet.addCell(new Label((n), m, "座位号",normalFormat));
					sheet.addCell(new Label((n + 1), m, vo.getSeatNum(),normalFormat));
					m = m + 2;
				}
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {

			e.printStackTrace();
		}
		return flag;
	}

	@RequestMapping("selectLabs")
	@ResponseBody
	public List<EaxmLabsInfo> selectLabs(HttpServletRequest reqeust) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		EaxmLabsInfo queryLab = new EaxmLabsInfo();
		queryLab.setEmpItemsId(param.get("itemsId"));
		queryLab.setTestId(param.get("kaodian"));
		List<EaxmLabsInfo> lablist = eaxmLabsInfoService.selectAllByEaxmLabsInfo(queryLab);
		Collections.sort(lablist, new Comparator<EaxmLabsInfo>() {
			@Override
			public int compare(EaxmLabsInfo b1, EaxmLabsInfo b2) {
				if (Integer.parseInt(b1.getLabsNum()) > Integer.parseInt(b2.getLabsNum())) {
					return 1;
				} else {
					return -1;
				}
			}

		});
		return lablist;
	}

	@RequestMapping("toQueryRoomLayout")
	public String toQueryRoomLayout(Model m) {

		// 年月日
		m.addAttribute("dateList",examSubjectInfoService.queryThemeDate());
		return "kwgl/queryRoomLayout.vm";
	}

	@RequestMapping("selectSubject")
	@ResponseBody
	public List<Theme> selectSubject(Model m, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "1");
		map.put("year", request.getParameter("nyr"));
		List<Theme> list = themeService.selectAllByYearMap(map);
		return list;
	}

	@RequestMapping("toRoomLocation")
	public String toRoomLocation(Model m) {

		return "kwgl/roomLocation.vm";
	}

	@RequestMapping("queryRoomLayoutByType")
	public String queryRoomLayoutByType(HttpServletRequest request, Model m) {
		// 查询所有岗位
		String type = request.getParameter("type");
		List<Postset> postList = postsetService.selectAllPostset();
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", request.getParameter("itemsId"));
		param.put("kaodian", request.getParameter("kaodian"));
		EaxmLabsInfo queryLab = new EaxmLabsInfo();
		queryLab.setEmpItemsId(param.get("itemsId"));
		queryLab.setTestId(param.get("kaodian"));
		Map<String, String> postMap = new HashMap<String, String>();
		for (Postset p : postList) {
			postMap.put(p.getId(), p.getPostName());
		}
		if (type.equals("1")) {
			List<EaxmLabsInfo> lablist = eaxmLabsInfoService.selectAllByEaxmLabsInfo(queryLab);
			Collections.sort(lablist, new Comparator<EaxmLabsInfo>() {
				@Override
				public int compare(EaxmLabsInfo b1, EaxmLabsInfo b2) {
					if (Integer.parseInt(b1.getLabsNum()) > Integer.parseInt(b2.getLabsNum())) {
						return 1;
					} else {
						return -1;
					}
				}
			});
			for (EaxmLabsInfo lb : lablist) {
				if (lb.getPostId().contains(",")) {
					String str[] = lb.getPostId().split(",");
					String strs = "";
					for (String s : str) {
						strs = strs + postMap.get(s) + " ";
						lb.setPostId(strs);
					}
				} else {
					lb.setPostId(postMap.get(lb.getPostId()));
				}
			}
			m.addAttribute("lbsList", lablist);
			return "kwgl/roomLocation.vm";
		} else if (type.equals("2")) {
			
	
			
			// 查询附加试室总数
			ExamSubjectInfo queryInfo = new ExamSubjectInfo();
			queryInfo.setEmpItemsId(request.getParameter("itemsId"));
			queryInfo.setTestId(request.getParameter("kaodian"));
			queryInfo.setNyr(request.getParameter("ryn"));
			queryInfo.setIsAdd("1");
			List<ExamSubjectInfo> d2List = examSubjectInfoService.selectAllByExamSubjectInfo(queryInfo);
			Map<String, String> dataMap = new LinkedHashMap<String, String>();
			for (ExamSubjectInfo e2 : d2List) {
				dataMap.put(e2.getPostId(), e2.getAddroomdes());
			}
			// 查询
			List<EaxmLabsInfo> lablist = eaxmLabsInfoService.selectAllByEaxmLabsInfo(queryLab);
			Collections.sort(lablist, new Comparator<EaxmLabsInfo>() {

				@Override
				public int compare(EaxmLabsInfo b1, EaxmLabsInfo b2) {
					if (Integer.parseInt(b1.getLabsNum()) > Integer.parseInt(b2.getLabsNum())) {
						return 1;
					} else {
						return -1;
					}
				}
			});
			for (EaxmLabsInfo info : lablist) {
				if (info.getIsAdd().equals("1")) {
					if (dataMap.containsKey(info.getPostId())) {
						String numStr = "";
						String value = dataMap.get(info.getPostId());
						String[] strs = value.split(",");
						for (String s : strs) {
							if (s != "") {
								String vostr[] = s.split("-");
								numStr = numStr + postMap.get(vostr[0]) + "(" + vostr[1] + ") ";
							}

						}
						info.setLabsRealnum(numStr);
					}
				} else {
					info.setLabsRealnum(postMap.get(info.getPostId()) + "(" + info.getLabsRealnum() + ")");
				}
			}
			m.addAttribute("lbsList", lablist);
			//查询试室是否发布
			EaxmLabsInfo query=new EaxmLabsInfo();
			query.setEmpItemsId(request.getParameter("itemsId"));
			query.setTestId(request.getParameter("kaodian"));
			List<EaxmLabsInfo> labsList=eaxmLabsInfoService.selectAllByEaxmLabsInfo(query);
			if(labsList!=null&&labsList.size()>0)
			{
				m.addAttribute("labsNum", labsList.size());
			}
			return "kwgl/subjectLocation.vm";
		} else if (type.equals("3")) {
			// 查询学科字典
			List<Dictionary> dicsList = DictionaryCache.getDictionaryByCode("xklb");
			Map<String, String> dicsMap = new HashMap<String, String>();
			for (Dictionary d : dicsList) {
				dicsMap.put(d.getId(), d.getText());
			}
			Map<String, String> pageNumMap = new LinkedHashMap<String, String>();
			ExamSubjectInfo queryInfo = new ExamSubjectInfo();
			queryInfo.setEmpItemsId(request.getParameter("itemsId"));
			queryInfo.setTestId(request.getParameter("kaodian"));
			queryInfo.setNyr(request.getParameter("ryn"));
			queryInfo.setIsAdd("2");
			List<ExamSubjectInfo> d1List = examSubjectInfoService.selectAllByExamSubjectInfo(queryInfo);
			queryInfo.setIsAdd("1");
			List<ExamSubjectInfo> d2List = examSubjectInfoService.selectAllByExamSubjectInfo(queryInfo);
			// 按岗位分组
			Map<String, List<ExamSubjectInfo>> dataMap = new LinkedHashMap<String, List<ExamSubjectInfo>>();
			Set<ExamSubjectInfo> set = new HashSet<ExamSubjectInfo>();
			List<ExamSubjectInfo> tempList = null;
			for (ExamSubjectInfo s : d1List) {
				if (set.add(s)) {
					tempList = new ArrayList<ExamSubjectInfo>();
					tempList.add(s);
					dataMap.put(s.getSubjectId(), tempList);
				} else {

					if (dataMap.containsKey(s.getSubjectId())) {
						tempList = dataMap.get(s.getSubjectId());
						tempList.add(s);
						dataMap.put(s.getSubjectId(), tempList);
					}
				}
			}
			for (Map.Entry<String, List<ExamSubjectInfo>> entry : dataMap.entrySet()) {
				String key = entry.getKey();
				List<ExamSubjectInfo> tList = entry.getValue();
				int num = 0;
				for (ExamSubjectInfo e : tList) {
					num = num + Integer.parseInt(e.getTestAnmount());
				}
				pageNumMap.put(key, num + "");
			}
			for (ExamSubjectInfo info : d2List) {
				String vo[] = info.getSubjectId().split(",");
				for (String s : vo) {
					if (pageNumMap.containsKey(s)) {
						int num = Integer.parseInt(pageNumMap.get(s));
						pageNumMap.put(s, (num + 1) + "");
					}
				}
			}
			// map循环
			Map<String,String> showMap=new HashMap<String,String>();
			String showStr = "";
			for (Map.Entry<String, String> entry : pageNumMap.entrySet()) {
				String key = entry.getKey();
				int num = Integer.parseInt(entry.getValue());
				showStr = showStr + dicsMap.get(key) + "(" + (num + 1) + "), ";
				showMap.put(dicsMap.get(key), (num+1)+"");
			}
			ExamItemsInfo itemInfo = examItemsInfoService.selectExamItemsInfoById(request.getParameter("kaodian"));
			//查询试室是否发布
			EaxmLabsInfo query=new EaxmLabsInfo();
			query.setEmpItemsId(request.getParameter("itemsId"));
			query.setTestId(request.getParameter("kaodian"));
			List<EaxmLabsInfo> labsList=eaxmLabsInfoService.selectAllByEaxmLabsInfo(query);
			if(labsList!=null&&labsList.size()>0)
			{
				m.addAttribute("labsNum", labsList.size());
			}
			m.addAttribute("showStr", showStr == null ? "" : showStr);
			m.addAttribute("kaodian", itemInfo.getTestName() == null ? "" : itemInfo.getTestName());
			m.addAttribute("subjectSize",showMap.size()+1);
			m.addAttribute("showMap", showMap);
			return "kwgl/showSubjectNum.vm";
		} else if (type.equals("4") || type.equals("6") || type.equals("7")) {
			List<String> arrayStrList = new ArrayList<String>(Arrays.asList("01", "02", "03", "04", "05", "06", "07",
					"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
					"24", "25", "26", "27", "28", "29", "30"));
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("labsid", request.getParameter("labid"));
			queryMap.put("itemsId", request.getParameter("itemsId"));
			List<AdjustStudentRoomInfoVo> dataList = examSubjectInfoService.querySecondLayoutRoom(queryMap);
			m.addAttribute("kaodianNum", dataList.size());
			ExamItemsInfo itemInfo = examItemsInfoService.selectExamItemsInfoById(request.getParameter("kaodian"));
			m.addAttribute("kaodianName", itemInfo.getTestName() == null ? "" : itemInfo.getTestName());
			EaxmLabsInfo eaxmLabsInfo = eaxmLabsInfoService.selectEaxmLabsInfoById(request.getParameter("labid"));
			m.addAttribute("labname", eaxmLabsInfo.getLabsName() == null ? "" : eaxmLabsInfo.getLabsName());
			// 设置时间
			SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat myFmt2 = new SimpleDateFormat("HH:mm:ss");
			String timeStr = "";
			if (eaxmLabsInfo.getStartTime() != null && eaxmLabsInfo.getEndTime() != null) {
				timeStr = myFmt1.format(eaxmLabsInfo.getStartTime()) + " " + myFmt2.format(eaxmLabsInfo.getStartTime())
						+ "-" + myFmt2.format(eaxmLabsInfo.getEndTime());
			}
			m.addAttribute("timeStr", timeStr);
			m.addAttribute("person", eaxmLabsInfo.getMangerPerson() == null ? "" : eaxmLabsInfo.getMangerPerson());
			for (AdjustStudentRoomInfoVo vo : dataList) {
				arrayStrList.remove(vo.getSeatNum());
			}
			// 补缺数据
			for (String s : arrayStrList) {
				AdjustStudentRoomInfoVo vo = new AdjustStudentRoomInfoVo();
				vo.setSeatNum(s);
				vo.setTestCardNum("");
				vo.setName("");
				dataList.add(vo);
			}
			// 得到所有试室信息
			Collections.sort(dataList, new Comparator<AdjustStudentRoomInfoVo>() {
				@Override
				public int compare(AdjustStudentRoomInfoVo b1, AdjustStudentRoomInfoVo b2) {
					if (Integer.parseInt(b1.getSeatNum()) > Integer.parseInt(b2.getSeatNum())) {
						return 1;
					} else {
						return -1;
					}
				}
			});
			List<AdjustStudentRoomInfoVo> tempList = new ArrayList<AdjustStudentRoomInfoVo>();
			for (int j = 0; j < 8; j++) {
				this.setTable(j, tempList, dataList);
			}
			Map<String, List<AdjustStudentRoomInfoVo>> dataMap = new LinkedHashMap<String, List<AdjustStudentRoomInfoVo>>();
			for (int i = 0; i < 8; i++) {
				if (tempList.size() > 0) {
					List<AdjustStudentRoomInfoVo> partList = new ArrayList<AdjustStudentRoomInfoVo>();
					if (i == 7) {
						partList.addAll(tempList.subList(0, 2));
					} else {
						partList.addAll(tempList.subList(0, 4));
					}
					dataMap.put(i + "", partList);
					if (i == 7) {
						tempList.subList(0, 2).clear();
					} else {
						tempList.subList(0, 4).clear();
					}
				}
			}
			m.addAttribute("imageUrl", FtpUploadUtil.getFileServer());
			m.addAttribute("type", type);
			m.addAttribute("dataMap", dataMap);
			return "kwgl/tableRoom.vm";
		} else {
			EaxmLabsInfo eaxmLabsInfo = eaxmLabsInfoService.selectEaxmLabsInfoById(request.getParameter("labid"));
			m.addAttribute("labname", eaxmLabsInfo.getLabsName());
			return "kwgl/roomNum.vm";
		}
	}

	/**
	 * excel
	 * 
	 * @param j
	 * @param tempList
	 * @param dataList
	 */
	private void setTable(int j, List<AdjustStudentRoomInfoVo> tempList, List<AdjustStudentRoomInfoVo> dataList) {
		if (j == 7) {
			tempList.add(dataList.get(j + 7));
			tempList.add(dataList.get(j + 15));
		} else {
			tempList.add(dataList.get(j));
			tempList.add(dataList.get(j + 7));
			tempList.add(dataList.get(j + 15));
			tempList.add(dataList.get(j + 23));
		}
	}

	/**
	 * 查询试讲安排
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("queryTrialArrange")
	@ResponseBody
	public Map<String, Object> queryTrialArrange(HttpServletRequest reqeust) {
		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");   
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("ganweiId", reqeust.getParameter("ganweiId"));
		param.put("ganweis", this.getExamItemsInfoByGanWei(reqeust.getParameter("kaodian")));//
		Map<String,Object> resultMap=new HashMap<String,Object>();
		try {
			resultMap= examSubjectInfoService.updateAndqueryTrialArrange(param);
			ExamItemsInfo examItemsInfo=examItemsInfoService.selectExamItemsInfoById(reqeust.getParameter("kaodian"));
			resultMap.put("initData",format.format(examItemsInfo.getStartTime()));
			return resultMap;

		} catch (Exception e) {
			map.put("msg", "error");
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 查询试讲左边数据
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("queryTrialLeftData")
	@ResponseBody
	public List<AdjustStudentRoomInfoVo> queryTrialLeftData(HttpServletRequest reqeust) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("ganweiId", reqeust.getParameter("ganweiId"));
		param.put("ganweis", this.getExamItemsInfoByGanWei(reqeust.getParameter("kaodian")));
		param.put("isgroup", "null");
		try {
			List<AdjustStudentRoomInfoVo> list= examSubjectInfoService.queryLectureGanWeiSchoolNum(param);
			return list;
		} catch (Exception e) {
			map.put("msg", "error");
			e.printStackTrace();
		}
		return new ArrayList<AdjustStudentRoomInfoVo>();
	}


	/**
	 * 查询试讲岗位
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("queryLectureGanWei")
	@ResponseBody
	public Map<String, Object> queryLectureGanWei(HttpServletRequest reqeust) {
		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");   
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("ganweis", this.getExamItemsInfoByGanWei(reqeust.getParameter("kaodian")));//
		// 查询主题
		Theme theme = themeService.selectThemeById(reqeust.getParameter("itemsId"));
		if (theme.getStep() == 1 || theme.getStep() == 2) {
			result.put("msg", "还没进入发布试讲流程!");
			return result;
		}
		List<Postset> dataList = examSubjectInfoService.queryLectureGanWei(param);
		ExamItemsInfo examItemsInfo=examItemsInfoService.selectExamItemsInfoById(reqeust.getParameter("kaodian"));
		result.put("data", dataList);
		result.put("initData",format.format(examItemsInfo.getStartTime()));
		result.put("msg", "success");
		return result;
	}

	/**
	 * 存储试讲安排
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("saveTrialArrange")
	@ResponseBody
	public Map<String, Object> saveTrialArrange(HttpServletRequest reqeust) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("groupNum", reqeust.getParameter("groupNum"));
		param.put("amschoolId", reqeust.getParameter("amschoolId"));
		param.put("pmschoolId", reqeust.getParameter("pmschoolId"));
		param.put("ganweiId", reqeust.getParameter("ganweiId"));
		param.put("groupName", reqeust.getParameter("groupName"));
		param.put("pmDate", reqeust.getParameter("pmDate"));
		param.put("amDate", reqeust.getParameter("amDate"));
		param.put("leftschoolId", reqeust.getParameter("leftschoolId"));
		param.put("ganweis", this.getExamItemsInfoByGanWei(reqeust.getParameter("kaodian")));//
		return examSubjectInfoService.saveTrialArrange(param);
	}

	/**
	 * 组号改了请求数据
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("queryTrialArrangeByGroup")
	@ResponseBody
	public Map<String, Object> queryTrialArrangeByGroup(HttpServletRequest reqeust) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("groupNum", reqeust.getParameter("groupNum"));
		param.put("ganweiId", reqeust.getParameter("ganweiId"));
		param.put("ganweis", this.getExamItemsInfoByGanWei(reqeust.getParameter("kaodian")));//
		return examSubjectInfoService.queryTrialArrangeByGroup(param);
	}

	/**
	 * 发布
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("showNotice")
	@ResponseBody
	public Map<String, Object> showNotice(HttpServletRequest reqeust) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("groupNum", reqeust.getParameter("groupNum"));
		param.put("ganweiId", reqeust.getParameter("ganweiId"));
		param.put("ganweis", this.getExamItemsInfoByGanWei(reqeust.getParameter("kaodian")));//
		String mes = examSubjectInfoService.showNotice(param);
		if (mes == "") {
			resultMap.put("msg", "success");
		} else {
			resultMap.put("msg", mes);
		}
		return resultMap;
	}
	
	
	/**
	 * 发布
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("isexportLecture")
	@ResponseBody
	public Map<String, Object> isexportLecture(HttpServletRequest reqeust) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", reqeust.getParameter("itemsId"));
		param.put("kaodian", reqeust.getParameter("kaodian"));
		param.put("groupNum", reqeust.getParameter("groupNum"));
		param.put("ganweiId", reqeust.getParameter("ganweiId"));
		param.put("ganweis", this.getExamItemsInfoByGanWei(reqeust.getParameter("kaodian")));//
		ExamLectureGroup numGroup = examLectureGroupService.selectExamLectureGroupById(param.get("groupNum"));
		ExamLectureGroup examLectureGroup = new ExamLectureGroup();
		examLectureGroup.setSubjectId(param.get("itemsId"));
		examLectureGroup.setKaodian(param.get("kaodian"));
		examLectureGroup.setGanweiid(param.get("ganweiId"));
		examLectureGroup.setGroupNumber(numGroup.getGroupNumber());
		List<ExamLectureGroup> list = examLectureGroupService.selectAllByExamLectureGroup(examLectureGroup);
		if (list.size() > 0) {
			ExamLectureGroup group1 = list.get(0);
			if (group1.getIsShow().equals("1")) {
				resultMap.put("msg", "success");
			} else {
				resultMap.put("msg", "fail");
			}
		}
		return resultMap;
	}

	@RequestMapping(value = "exportLecture")
	public void exportLecture(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> qparam=new HashMap<String,String>();
		qparam.put("itemsId", request.getParameter("itemsId"));
		qparam.put("kaodian", request.getParameter("kaodian"));
		qparam.put("ganweiId", request.getParameter("ganweiId"));
		qparam.put("ganweis", this.getExamItemsInfoByGanWei(request.getParameter("kaodian")));
		List<AdjustStudentRoomInfoVo> dlist= examSubjectInfoService.querySchoolApproveGanWeiCount(qparam);
        Map<String,Integer> dataMap=new HashMap<String,Integer>();
		for(AdjustStudentRoomInfoVo vo:dlist)
        {
			dataMap.put(vo.getSchoolName()+"-"+request.getParameter("ganweiId"), vo.getGanweiNum());
        }
		List<Postset> postList = postsetService.selectAllPostset();
		Map<String, String> postMap = new HashMap<String, String>();
		for (Postset p : postList) {
			postMap.put(p.getId(), p.getPostName());
		}
		User user=(User)request.getSession().getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> param = new HashMap<String, String>();
		param.put("itemsId", request.getParameter("itemsId"));
		param.put("kaodian", request.getParameter("kaodian"));
		param.put("groupNum", request.getParameter("groupNum"));
		param.put("ganweiId", request.getParameter("ganweiId"));
		ExamLectureGroup numGroup = examLectureGroupService.selectExamLectureGroupById(param.get("groupNum"));
		ExamLectureGroup examLectureGroup = new ExamLectureGroup();
		examLectureGroup.setSubjectId(param.get("itemsId"));
		examLectureGroup.setKaodian(param.get("kaodian"));
		examLectureGroup.setGanweiid(param.get("ganweiId"));
		String ganweiName=postMap.get(param.get("ganweiId"));
		examLectureGroup.setGroupNumber(numGroup.getGroupNumber());
		List<ExamLectureGroup> list = examLectureGroupService.selectAllByExamLectureGroup(examLectureGroup);
		ExamLectureGroup amGroup = new ExamLectureGroup();
		ExamLectureGroup pmGroup = new ExamLectureGroup();
		param.put("ganweis", this.getExamItemsInfoByGanWei(request.getParameter("kaodian")));
		ExamItemsInfo itemInfo = examItemsInfoService.selectExamItemsInfoById(param.get("kaodian"));
		String type=request.getParameter("type");

		if (list.size() > 0) {
			Collections.sort(list, new Comparator<ExamLectureGroup>() {
				@Override
				public int compare(ExamLectureGroup b1, ExamLectureGroup b2) {
					if (Integer.parseInt(b1.getPeriod()) > Integer.parseInt(b2.getPeriod())) {
						return 1;
					} else {
						return -1;
					}
				}

			});
			amGroup = list.get(0);
			pmGroup = list.get(1);
		}
		WritableWorkbook wwb = null;// Excel数据组装
		String usersTempPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
				+ "template.xls";
		String createPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
				+ "template_lecture_+"+user.getId()+".xls";
		Workbook workbook = null;
		String str="";
		try {
			 WritableCellFormat normalFormat = new WritableCellFormat(); 
			 normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
	                    jxl.format.Colour.BLACK);
			String amdataStr = "";
			if (amGroup.getStartDate() != null) {
				amdataStr = sdf.format(amGroup.getStartDate());
			}
			String pmdataStr = "";
			if (pmGroup.getStartDate() != null) {
				pmdataStr = sdf.format(pmGroup.getStartDate());
			}
			workbook = Workbook.getWorkbook(new File(usersTempPath));
			wwb = Workbook.createWorkbook(new File(createPath), workbook);// 拷贝模板，把拷贝的模板临时放一个路径下
			WritableSheet sheet = wwb.getSheet(0);
			
			if("1".equals(type))
			{
				sheet.addCell(new Label((0), 3, "序号",normalFormat));
				sheet.addCell(new Label((1), 3, "岗位名称",normalFormat));
				sheet.addCell(new Label((2), 3, "招聘单位",normalFormat));
				sheet.addCell(new Label((3), 3, "岗位数量",normalFormat));
				sheet.addCell(new Label((4), 3, "准考证号",normalFormat));
				sheet.addCell(new Label((5), 3, "姓名",normalFormat));
				sheet.addCell(new Label((6), 3, "性别",normalFormat));
				sheet.addCell(new Label((7), 3, "身份证号",normalFormat));
				sheet.addCell(new Label((8), 3, "联系电话",normalFormat));
			}else
			{
				sheet.addCell(new Label((0), 3, "序号",normalFormat));
				sheet.addCell(new Label((1), 3, "岗位名称",normalFormat));
				sheet.addCell(new Label((2), 3, "准考证号",normalFormat));
				sheet.addCell(new Label((3), 3, "姓名",normalFormat));
				sheet.addCell(new Label((4), 3, "性别",normalFormat));
				sheet.addCell(new Label((5), 3, "身份证号",normalFormat));
				sheet.addCell(new Label((6), 3, "联系电话",normalFormat));
			}
			
			if("1".equals(type))
			{
			sheet.addCell(new Label((9), 3, "考生签名（签到时间）",normalFormat));
			sheet.addCell(new Label((10), 3, "备考组号",normalFormat));
			sheet.addCell(new Label((11), 3, "考试组号",normalFormat));
			sheet.addCell(new Label((12), 3, "抽签号",normalFormat));
			sheet.addCell(new Label((13), 3, "时间",normalFormat));
			}
			param.put("isgroup", "notnull");
			param.put("groupid", amGroup.getId());
			List<StudentLectureVo> amList = examSubjectInfoService.queryLectureStudentBySchoolIdExcel(param);

			param.put("groupid", pmGroup.getId());
			List<StudentLectureVo> pmList = examSubjectInfoService.queryLectureStudentBySchoolIdExcel(param);
			int i = 6;
			int num = 0;
			int k;
			if (amList.size() > 0) {
				for (StudentLectureVo vo : amList) {
					
					
					if("1".equals(type))
					{
					
					sheet.addCell(new Label((0), i, (num + 1) + "",normalFormat));
					sheet.addCell(new Label((1), i, vo.getPostName(),normalFormat));
					sheet.addCell(new Label((2), i, vo.getSchoolName(),normalFormat));
					Integer numStr=dataMap.get(vo.getSchoolName()+"-"+vo.getPostId())==null?0:dataMap.get(vo.getSchoolName()+"-"+vo.getPostId());
					sheet.addCell(new Label((3), i, numStr+"",normalFormat));
					sheet.addCell(new Label((4), i, vo.getTestNumber(),normalFormat));
					sheet.addCell(new Label((5), i, vo.getName(),normalFormat));
					sheet.addCell(new Label((6), i,  vo.getSex().equals("1")?"男":"女",normalFormat));
					sheet.addCell(new Label((7), i, vo.getIdCard(),normalFormat));
					sheet.addCell(new Label((8), i, vo.getPhone(),normalFormat));
					}else
					{
						sheet.addCell(new Label((0), i, (num + 1) + "",normalFormat));
						sheet.addCell(new Label((1), i, vo.getPostName(),normalFormat));
						sheet.addCell(new Label((2), i, vo.getTestNumber(),normalFormat));
						sheet.addCell(new Label((3), i, vo.getName(),normalFormat));
						sheet.addCell(new Label((4), i, vo.getSex().equals("1")?"男":"女",normalFormat));
						sheet.addCell(new Label((5), i, vo.getIdCard(),normalFormat));
						sheet.addCell(new Label((6), i, vo.getPhone(),normalFormat));
					}
					
					if("1".equals(type))
					{
						sheet.addCell(new Label((9), i, " ",normalFormat));
						sheet.addCell(new Label((10), i, " ",normalFormat));
						sheet.addCell(new Label((11), i, amGroup.getGroupName()+"",normalFormat));
						sheet.addCell(new Label((12), i, " ",normalFormat));
						sheet.addCell(new Label((13), i, "上午",normalFormat));
					}
				
					i++;
					num++;
				}
			}
			k = i;
			i = i + 2;
			if (pmList.size() > 0) {
				for (StudentLectureVo vo : pmList) {
					
					if("1".equals(type))
					{
					sheet.addCell(new Label((0), i, (num + 1) + "",normalFormat));
					sheet.addCell(new Label((1), i, vo.getPostName(),normalFormat));
					sheet.addCell(new Label((2), i, vo.getSchoolName(),normalFormat));
					Integer numStr=dataMap.get(vo.getSchoolName()+"-"+vo.getPostId())==null?0:dataMap.get(vo.getSchoolName()+"-"+vo.getPostId());
					sheet.addCell(new Label((3), i, numStr+"",normalFormat));
					sheet.addCell(new Label((4), i, vo.getTestNumber(),normalFormat));
					sheet.addCell(new Label((5), i, vo.getName(),normalFormat));
					sheet.addCell(new Label((6), i, vo.getSex().equals("1")?"男":"女",normalFormat));
					sheet.addCell(new Label((7), i, vo.getIdCard(),normalFormat));
					sheet.addCell(new Label((8), i, vo.getPhone(),normalFormat));
					}else
					{
						sheet.addCell(new Label((0), i, (num + 1) + "",normalFormat));
						sheet.addCell(new Label((1), i, vo.getPostName(),normalFormat));
						sheet.addCell(new Label((2), i, vo.getTestNumber(),normalFormat));
						sheet.addCell(new Label((3), i, vo.getName(),normalFormat));
						sheet.addCell(new Label((4), i,  vo.getSex().equals("1")?"男":"女",normalFormat));
						sheet.addCell(new Label((5), i, vo.getIdCard(),normalFormat));
						sheet.addCell(new Label((6), i, vo.getPhone(),normalFormat));
					}
					if("1".equals(type))
					{
						sheet.addCell(new Label((9), i, " ",normalFormat));
						sheet.addCell(new Label((10), i, " ",normalFormat));
						sheet.addCell(new Label((11), i, pmGroup.getGroupName()+"",normalFormat));
						sheet.addCell(new Label((12), i, " ",normalFormat));
						sheet.addCell(new Label((13), i, "下午",normalFormat));
					}
				
					i++;
					num++;
				}
			}
			if("1".equals(type))
			{
				sheet.mergeCells(0, 0, 13, 2);
				if (amList.size() > 0) {
					sheet.mergeCells(0, 4, 13, 5);
				}
				if (pmList.size() > 0) {
					sheet.mergeCells(0, k, 13, k + 1);
				}
			}else
			{
				sheet.mergeCells(0, 0, 6, 2);
				if (amList.size() > 0) {
					sheet.mergeCells(0, 4, 6, 5);
				}
				if (pmList.size() > 0) {
					sheet.mergeCells(0, k, 6, k + 1);
				}
			}
			if("2".equals(type))
			{
				str="(门贴)";
			}
			WritableFont wf = new WritableFont(WritableFont.TAHOMA, 15, WritableFont.BOLD);
			Label label = new Label(0, 0, itemInfo.getTestName() +" ("+ganweiName+") " +  amGroup.getGroupName()+str);
			Label labe1 = new Label(0, 4, amdataStr+"上午 ");
			Label labe2 = new Label(0, k,  pmdataStr+"下午 ");
			WritableCellFormat cellFormat = new WritableCellFormat(wf);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			label.setCellFormat(cellFormat);
			labe1.setCellFormat(cellFormat);
			labe2.setCellFormat(cellFormat);
			sheet.addCell(label);
			if (amList.size() > 0) {
				sheet.addCell(labe1);
			}
			if (pmList.size() > 0) {
				sheet.addCell(labe2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭Excel工作薄对象
			if (wwb != null) {
				try {
					wwb.write();
					wwb.close();
					ExcelHelper.outputExcel(response, itemInfo.getTestName() +" ("+ganweiName+") " + amGroup.getGroupName() +str, createPath);
					new File(createPath).delete();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (workbook != null) {
				workbook.close();
			}
		}
	}
	
	
	/**
	 * 进入试室打印
	 * 
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("PrintRoom")
	public String PrintRoom(HttpServletRequest request, Model m) {
		String type = request.getParameter("type");
		List<String> arrayStrList = new ArrayList<String>(Arrays.asList("01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30"));
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("labsid", request.getParameter("labid"));
		queryMap.put("itemsId", request.getParameter("itemsId"));
		List<AdjustStudentRoomInfoVo> dataList = examSubjectInfoService.querySecondLayoutRoom(queryMap);
		m.addAttribute("kaodianNum", dataList.size());
		ExamItemsInfo itemInfo = examItemsInfoService.selectExamItemsInfoById(request.getParameter("kaodian"));
		m.addAttribute("kaodianName", itemInfo.getTestName() == null ? "" : itemInfo.getTestName());
		EaxmLabsInfo eaxmLabsInfo = eaxmLabsInfoService.selectEaxmLabsInfoById(request.getParameter("labid"));
		m.addAttribute("labname", eaxmLabsInfo.getLabsName() == null ? "" : eaxmLabsInfo.getLabsName());
		// 设置时间
		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		m.addAttribute("startTime",
				eaxmLabsInfo.getStartTime() == null ? "" : myFmt1.format(eaxmLabsInfo.getStartTime()));
		m.addAttribute("endTime",
				eaxmLabsInfo.getEndTime() == null ? "" : myFmt1.format(eaxmLabsInfo.getEndTime()));
		m.addAttribute("person", eaxmLabsInfo.getMangerPerson() == null ? "" : eaxmLabsInfo.getMangerPerson());
		for (AdjustStudentRoomInfoVo vo : dataList) {
			arrayStrList.remove(vo.getSeatNum());
		}
		// 补缺数据
		for (String s : arrayStrList) {
			AdjustStudentRoomInfoVo vo = new AdjustStudentRoomInfoVo();
			vo.setSeatNum(s);
			vo.setTestCardNum("");
			vo.setName("");
			dataList.add(vo);
		}
		// 得到所有试室信息
		Collections.sort(dataList, new Comparator<AdjustStudentRoomInfoVo>() {
			@Override
			public int compare(AdjustStudentRoomInfoVo b1, AdjustStudentRoomInfoVo b2) {
				if (Integer.parseInt(b1.getSeatNum()) > Integer.parseInt(b2.getSeatNum())) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		
		if ("1".equals(type)||"5".equals(type)) {
			List<AdjustStudentRoomInfoVo> tempList = new ArrayList<AdjustStudentRoomInfoVo>();
			for (int j = 0; j < 8; j++) {
				this.setTable(j, tempList, dataList);
			}
			Map<String, List<AdjustStudentRoomInfoVo>> dataMap = new LinkedHashMap<String, List<AdjustStudentRoomInfoVo>>();
			for (int i = 0; i < 8; i++) {
				if (tempList.size() > 0) {
					List<AdjustStudentRoomInfoVo> partList = new ArrayList<AdjustStudentRoomInfoVo>();
					if (i == 7) {
						partList.addAll(tempList.subList(0, 2));
					} else {
						partList.addAll(tempList.subList(0, 4));
					}
					dataMap.put(i + "", partList);
					if (i == 7) {
						tempList.subList(0, 2).clear();
					} else {
						tempList.subList(0, 4).clear();
					}
				}
			}
			m.addAttribute("dates", new Date());
			m.addAttribute("imageUrl", FtpUploadUtil.getFileServer());
			m.addAttribute("dataMap", dataMap);
			if("5".equals(type))
			{
				return "kwgl/sureSeatRoom.vm";
			}else
			{
				return "kwgl/sureRoom.vm";
			}
		} else if("2".equals(type)){
			try {
				ClassRoomIReportVo classRoomIReportVo = new ClassRoomIReportVo();
				classRoomIReportVo.setTitle(itemInfo.getTestName()+"-"+eaxmLabsInfo.getLabsName()+"试室");
				classRoomIReportVo.setCity("东莞市");
				classRoomIReportVo.setRegion("东莞市");
				classRoomIReportVo.setStartDate(myFmt1.format(eaxmLabsInfo.getStartTime()));
				classRoomIReportVo.setEndDate(myFmt1.format(eaxmLabsInfo.getEndTime()));
				classRoomIReportVo.setExaminationCount(eaxmLabsInfo.getLabsRealnum());
				Class clazz = classRoomIReportVo.getClass(); 
				Map<String,List<String>> dataMap=new LinkedHashMap<String,List<String>>();
			    for(int i=0;i<30;i++)
			    {
			    	List<String> list=new ArrayList<String>();
			    	list.add("setName"+(i+1));
			    	list.add("setPhone"+(i+1));
			    	list.add("setImage"+(i+1));
			    	dataMap.put(i+1+"", list);
			    }
			    for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {  
			    	List<String> methodList=entry.getValue();
			    	String key=entry.getKey();
			    	Method method=null;
			    	for(int k=0;k<methodList.size();k++)
			    	{
			    		String name=methodList.get(k);
			    		method=clazz.getDeclaredMethod(name, String.class);
			    		if(k==0)
			    		{
			    			method.invoke(classRoomIReportVo, dataList.get(Integer.parseInt(key)-1).getName());
			    		}
			    		if(k==1)
			    		{
			    			method.invoke(classRoomIReportVo, dataList.get(Integer.parseInt(key)-1).getTestCardNum()==null?"":dataList.get(Integer.parseInt(key)-1).getTestCardNum());
			    		}
			    		if(k==2)
			    		{
			    			method.invoke(classRoomIReportVo,  dataList.get(Integer.parseInt(key)-1).getImageStr()==null?FtpUploadUtil.getDefaultImage():dataList.get(Integer.parseInt(key)-1).getImageStr());
			    		}
			    	}
		        } 
				// 报表数据源
				JRDataSource jrDataSource = new JRBeanCollectionDataSource(ClassRoomIReportVo.getList(classRoomIReportVo));
				// 动态指定报表模板url
				m.addAttribute("url", "/report/classRoom.jasper");
				m.addAttribute("format", "pdf");// 报表格式
				m.addAttribute("jrMainDataSource", jrDataSource);
				return "iReportView";// 对应jasper-defs.xml中的bean id
			} catch (SecurityException e) {
			
				e.printStackTrace();
			} catch (IllegalAccessException e) {
			
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
			
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
			
				e.printStackTrace();
			}
		}else if("4".equals(type))
		{
			List<AdjustStudentRoomInfoVo> tempList = new ArrayList<AdjustStudentRoomInfoVo>();
			for (int j = 0; j < 8; j++) {
				this.setTable(j, tempList, dataList);
			}
			Map<String, List<AdjustStudentRoomInfoVo>> dataMap = new LinkedHashMap<String, List<AdjustStudentRoomInfoVo>>();
			for (int i = 0; i < 8; i++) {
				if (tempList.size() > 0) {
					List<AdjustStudentRoomInfoVo> partList = new ArrayList<AdjustStudentRoomInfoVo>();
					if (i == 7) {
						partList.addAll(tempList.subList(0, 2));
					} else {
						partList.addAll(tempList.subList(0, 4));
					}
					dataMap.put(i + "", partList);
					if (i == 7) {
						tempList.subList(0, 2).clear();
					} else {
						tempList.subList(0, 4).clear();
					}
				}
			}
			m.addAttribute("imageUrl", FtpUploadUtil.getFileServer());
			m.addAttribute("dataMap", dataMap);
			m.addAttribute("type", 7);
			return "kwgl/tablePrint.vm";
		}else if("3".equals(type))
		{
			return "kwgl/printRoomNum.vm"; 
		}
			
		return null;
	}

	/**
	 * 获取sql
	 * 
	 * @param str
	 * @return
	 */
	private String getInSQL(String str) {
		String[] strs = str.split(",");
		String totalStr = "(";
		for (int i = 0; i < strs.length; i++) {
			if (i != 0) {
				totalStr = totalStr + "','" + strs[i];
			} else {
				totalStr = totalStr + "'" + strs[i];
			}
		}
		totalStr = totalStr + "')";
		return totalStr;
	}

	/**
	 * 进入试室确认打印页面
	 * 
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("toSureLayoutRoom")
	public String toSureLayoutRoom(Model m, HttpServletRequest request) {
		m.addAttribute("dateList",examSubjectInfoService.queryThemeDate());
		// 考试类型
		List<Dictionary> kslxdics = DictionaryCache.getDictionaryByCode("kslx");
		List<Dictionary> dataList=new ArrayList<Dictionary>();
		for(Dictionary d:kslxdics)
		{
			if("2".equals(d.getValue())||"3".equals(d.getValue()))
			{
				dataList.add(d);
			}
		}
		m.addAttribute("kslxdics", dataList);
		return "kwgl/sureRoomLayout.vm";
	}

	/**
	 * 进入试室确认打印页面
	 * 
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("suerLabsInfo")
	@ResponseBody
	public Map<String, Object> suerLabsInfo(Model m, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String personName = request.getParameter("personName");
			String labid = request.getParameter("labid");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			EaxmLabsInfo info = eaxmLabsInfoService.selectEaxmLabsInfoById(labid);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (info != null) {
				info.setMangerPerson(personName);
				info.setStartTime(sdf.parse(startDate));
				info.setEndTime(sdf.parse(endDate));
				eaxmLabsInfoService.updateEaxmLabsInfo(info);
				resultMap.put("msg", "success");
			} else {
				resultMap.put("msg", "确认失败");
			}
		} catch (ParseException e) {

			resultMap.put("msg", "确认失败");
		}
		return resultMap;
	}

	/**
	 * 获取岗位现在id
	 * 
	 * @return
	 */
	private String getExamItemsInfoByGanWei(String id) {
		ExamItemsInfo info = examItemsInfoService.selectExamItemsInfoById(id);
		return this.getInSQL(info.getPermisGw());
	}

	@RequestMapping("toinitSuccess")
	public String toinitSuccess(Model m) {

		return "kwgl/initSuccess.vm";
	}
}
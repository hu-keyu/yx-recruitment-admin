package org.jypj.dev.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.dao.PostsetDao;
import org.jypj.dev.entity.*;
import org.jypj.dev.service.*;
import org.jypj.dev.util.ExcelHelper;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.jypj.dev.vo.ScoreEntersOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/**
 * 招聘人员审核
 *
 * Created by ZCJ on 2016/10/26.
 */
@Controller
@RequestMapping("/dg/personCheck")
public class PersonCheckController {

    @Autowired
    private StudentInfoService studentInfoService;
    @Resource
    private StudentFamInfoService studentFamInfoService;
    @Resource
    private StudentEduInfoService studentEduInfoService;
    @Autowired
    private PositionService positionService;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private AttachementService attachementService;
    @Resource
    private ThemeService themeService;
    @Resource
    private StudentApplyInfoService studentApplyInfoService;
    @Resource
    private PositionDomainService positionDomainService;
    @Resource
    private AuditReasonService auditReasonService;
    @Resource 
    private ScoreEnterWritienService scoreEnterWritienService;
    @Resource
    private ScoreEnterTrialService scoreEnterTrialService ;
    @Resource
    private NoticeService noticeService ;
    @Resource
    private PostsetDao postsetDao;

    @RequestMapping("/personCheckIndex")
    public String personCheckIndex(Model model,HttpSession session){
        User user = (User)session.getAttribute("user") ;
        List<String> years =  themeService.selectYears() ;
        model.addAttribute("years",years) ;
        AuditReason auditReason=new AuditReason();
        auditReason.setOwnerid(user.getOrginId());//当前登录用户学校ID
        auditReason.setType("2");
        List<AuditReason> auditReasons=auditReasonService.selectAllByAuditReason(auditReason);
        model.addAttribute("auditReasons", auditReasons);
        return "/recruit/person_check_list.vm" ;
    }

    @RequestMapping("/personChecked")
    public String personUncheck(Model model){
        List<String> years =  themeService.selectYears() ;
        model.addAttribute("years",years) ;
        return "/recruit/person_checked.vm" ;
    }

    @RequestMapping("/schoolView")
    public String personSchoolView(Model model){
        List<String> years =  themeService.selectYears() ;
        model.addAttribute("years",years) ;
        return "/recruit/person_school_view.vm" ;
    }

    @RequestMapping("/personSchoolCheck")
    public String personSchoolCheck(Model model){
        List<String> years =  themeService.selectYears() ;
        model.addAttribute("years",years) ;
        return "/recruit/person_school_check.vm" ;
    }


    /**
     * 招聘人员审核中的未审核列表查询
     * @param page
     * @return
     */
    @RequestMapping(value="/selectPersonUncheck",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectPersonUncheck(Page page) {
        Map<String, Object> condition =page.getCondition();
        page = studentInfoService.selectPersonUncheck(page,condition);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage()) ;
        return jsonMap;
    }

    /**
     * 招聘人员审核中的已审核列表查询
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectPersonChecked",method= RequestMethod.POST)
    public String selectPersonChecked(Page page) {
        Map<String, Object> condition =page.getCondition();
        page = studentInfoService.selectPersonChecked(page,condition);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage()) ;
        return JSONObject.toJSON(jsonMap).toString();
    }

    /**
     * 招聘人员审核中的学校审核列表查询
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectSchoolCheckedSituation",method= RequestMethod.POST)
    public String selectSchoolCheckedSituation(Page page) {
        Map<String, Object> condition =page.getCondition();
        page = studentInfoService.selectSchoolCheckedSituation(page,condition);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage()) ;
        return JSONObject.toJSON(jsonMap).toString();
    }

    /**
     * 招聘人员审核中的学校面试情况列表查询
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectSchoolInterviewSituation",method= RequestMethod.POST)
    public String selectSchoolInterviewSituation(Page page) {
        Map<String, Object> condition =page.getCondition();
        page = studentInfoService.selectSchoolInterviewSituation(page,condition);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage()) ;
        return JSONObject.toJSON(jsonMap).toString();
    }


    @ResponseBody
    @RequestMapping(value="/personCheck",method=RequestMethod.POST)
    public JSONObject checkInterview(String chk,String projectId,String reason,String ispass,HttpSession session){
        JSONObject jsonMap=new JSONObject();
        User user=(User)session.getAttribute("user");
        try {
            studentInfoService.personCheck(chk,projectId,reason,ispass,user,jsonMap);
            jsonMap.put("flag", "success");
            jsonMap.put("msg", "操作成功！");
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("flag", "error");
            return jsonMap;
        }
    }


    //撤销审核
    @ResponseBody
    @RequestMapping(value="checkCancel",method=RequestMethod.POST)
    public JSONObject checkCancel(String chk,String projectId,String status,String reason,HttpSession session){
        JSONObject jsonMap=new JSONObject();
        User user=(User)session.getAttribute("user");
        try {
            studentInfoService.checkCancel(chk,projectId,user,status,reason,jsonMap);
            jsonMap.put("flag", "success");
            jsonMap.put("msg", "操作成功！");
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("flag", "error");
            return jsonMap;
        }
    }


    @RequestMapping(value = "/personCheckPage")
    public String personCheckPage(Model model, String studentId,String recruitId,String flag,HttpSession session) {
    	User user=(User)session.getAttribute("user");
    	Theme theme = themeService.selectThemeById(recruitId);
        model.addAttribute("theme", theme);
        //修改信息
        if (StringUtil.isNotEmpty(studentId)) {
            StudentInfo studentInfo = studentInfoService.selectStudentInfoById(studentId);
            StudentFamInfo studentFamInfo = new StudentFamInfo();
            studentFamInfo.setEmployItemsId(recruitId);
            studentFamInfo.setStudentId(studentId);
            List<StudentFamInfo> studentFamList = studentFamInfoService.selectAllByStudentFamInfo(studentFamInfo);
            studentInfo.setStudentFamInfoList(studentFamList);
            
           //获取头像
            Attachement attachement = attachementService.selectAttachementById(studentInfo.getPhotoAttId());
            model.addAttribute("attachement", attachement);
            
            StudentEduInfo studentEduInfo = new StudentEduInfo();
            studentEduInfo.setStudentId(studentId);
            studentEduInfo.setEmployItemsId(recruitId);
            List<StudentEduInfo> studentEduList = studentEduInfoService.selectAllByStudentEduInfo(studentEduInfo);
            
            List<StudentEduInfo> tempList = new ArrayList<StudentEduInfo>();
            for (int i = 0;i < 5;i ++) {
                StudentEduInfo temp = new StudentEduInfo();
                temp.setStudentId(studentId);
                temp.setEmployItemsId(recruitId);
                tempList.add(temp);
            }
            
            //转化日期格式
            String tempStr = "";
	        for (StudentEduInfo stuEduInfo : studentEduList) {
	            if (stuEduInfo.getEduGraduateTime() == null) {
	                continue;
	            }
	            tempStr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(stuEduInfo.getEduGraduateTime());
	            if (StringUtil.isEmpty(tempStr)) {
	                continue;
	            }
	            stuEduInfo.setEduGraduateTimeStr(tempStr);
	        }
            
	        int count = 0;
            for (StudentEduInfo s : studentEduList) {
                tempList.set((Integer.parseInt(s.getEduCode()) - 1) < 0 ? 0 : Integer.parseInt(s.getEduCode()) - 1, s);
                if (s.getIsSimilarTerm() != null && s.getIsSimilarTerm().equals("1")) {
                    count ++;
                }
            }
            studentInfo.setHasSimilar(count > 0 ? "1" : "0");
            studentInfo.setStudentEduInfoList(tempList);
            model.addAttribute("studentInfo", studentInfo);
        }
        
        //民族
        List<Dictionary> mzs = DictionaryCache.getDictionaryByCode("mzdm");
        model.addAttribute("mzs", mzs);
        
        //学位
        List<Dictionary> xws = DictionaryCache.getDictionaryByCode("xwlx");
        model.addAttribute("xws", xws);
        
        //教育类型
        List<Dictionary> jys = DictionaryCache.getDictionaryByCode("jylx");
        model.addAttribute("jys", jys);
        
        //政治面貌 
        List<Dictionary> zzmms = DictionaryCache.getDictionaryByCode("zzmm");
        model.addAttribute("zzmms", zzmms);
        
        //资格证种类
        List<Dictionary> jszgzls = DictionaryCache.getDictionaryByCode("jszgzl");
        model.addAttribute("jszgzls", jszgzls);
        
        //专业技术资格
        List<Dictionary> zyjszgs = DictionaryCache.getDictionaryByCode("zyjszg");
        model.addAttribute("zyjszgs", zyjszgs);
        
        //荣誉称号
        List<Dictionary> rychs = DictionaryCache.getDictionaryByCode("rych");
        model.addAttribute("rychs", rychs);
        
        //考生类型
        List<Dictionary> kslxs = DictionaryCache.getDictionaryByCode("bylx");
        model.addAttribute("kslxs", kslxs);
        model.addAttribute("recruitItemId", recruitId);
        
        //家庭关系
        List<Dictionary> jtcygxs = DictionaryCache.getDictionaryByCode("jtcygx");
        model.addAttribute("jtcygxs", jtcygxs);
        
        /** 考生端附件 **/
        //根据id查找申请信息
        StudentApplyInfo studentApplyInfo = new StudentApplyInfo();
        studentApplyInfo.setStudentId(studentId);
        studentApplyInfo.setEmployItemsId(recruitId);
        studentApplyInfo = studentApplyInfoService.selectObjectByStudentApplyInfo(studentApplyInfo);
        //在这里把招聘单位设置进去
        Dictionary di = dictionaryService.selectDictionaryById(studentApplyInfo.getApplyDepId()) ;
        model.addAttribute("schoolName", di.getText()) ;
        
        
        //查询岗位,获取限制条件
        Position position = new Position();
        position.setStationId(studentApplyInfo.getApplyJobId());//岗位id
        position.setSchoolId(studentApplyInfo.getApplyDepId());//学校id
        position.setProjectId(studentApplyInfo.getEmployItemsId());//招聘主题id
        position.setStatus("1");
        position = positionService.selectObjectByPosition(position);
        if (position != null) {
          //岗位对应的专业限制
            PositionDomain positionDomain = new PositionDomain();
            positionDomain.setSchoolId(studentApplyInfo.getApplyDepId());
            positionDomain.setPositionId(studentApplyInfo.getApplyJobId());
            positionDomain.setPlanApplyId(position.getPlanApplyId());
            List<PositionDomain> positionDomains = positionDomainService.selectAllByPositionDomain(positionDomain);
            if (positionDomains != null && positionDomains.size() > 0) {
                position.setPositionDomains(positionDomains);
            }
            
            String posCondition = position.generateCondition();
            studentApplyInfo.setPosCondition(posCondition);
        }
        Dictionary dic = dictionaryService.selectDictionaryById(studentApplyInfo.getApplyDepId());
        if (dic != null) {
            studentApplyInfo.setApplyDeptName(dic.getText() + "(" + dic.getCode() + ")");
        }
        
        //身份证
        if (StringUtil.isNotEmpty(studentApplyInfo.getIdcardAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getIdcardAttId());
            if (att != null) {
                studentApplyInfo.setIdcardRealName(att.getRealName());
                studentApplyInfo.setIdcardUrlPath(att.getPath());
            }
        }
        
        //教师资格证或教育学、心理学、普通话成绩单
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerAchAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getCerAchAttId());
            if (att != null) {
                studentApplyInfo.setCerAchRealName(att.getRealName());
                studentApplyInfo.setCerAchUrlPath(att.getPath());
                model.addAttribute("cerAchIsImage", FtpUploadUtil.isImage(att.getRealName()));
            }
        }
        
        //毕业证书或者就业推荐表
        if (StringUtil.isNotEmpty(studentApplyInfo.getGraRecomAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getGraRecomAttId());
            if (att != null) {
                studentApplyInfo.setGraRecomRealName(att.getRealName());
                studentApplyInfo.setGraRecomUrlPath(att.getPath());
                model.addAttribute("graRecomIsImage", FtpUploadUtil.isImage(att.getRealName()));
            }
        }
        
        //学历鉴定证明
        if (StringUtil.isNotEmpty(studentApplyInfo.getAcaQuaAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getAcaQuaAttId());
            if (att != null) {
                studentApplyInfo.setAcaQuaRealName(att.getRealName());
                studentApplyInfo.setAcaQuaUrlPath(att.getPath());
            }
        }
        
        //学位证书
        if (StringUtil.isNotEmpty(studentApplyInfo.getBacAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getBacAttId());
            if (att != null) {
            	studentApplyInfo.setBacRealName(att.getRealName());
                studentApplyInfo.setBacUrlPath(att.getPath());
            }
        }
        
        //学位鉴定证明
        if (StringUtil.isNotEmpty(studentApplyInfo.getBacQuaAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getBacQuaAttId());
            if (att != null) {
                studentApplyInfo.setBacQuaRealName(att.getRealName());
                studentApplyInfo.setBacQuaUrlPath(att.getPath());
            }
        }
        
        //计划生育证明
        if (StringUtil.isNotEmpty(studentApplyInfo.getFamPlanAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getFamPlanAttId());
            if (att != null) {
                studentApplyInfo.setFamPlanRealName(att.getRealName());
                studentApplyInfo.setFamPlanUrlPath(att.getPath());
            }
        }
        
        //个人学习和工作情况总结
        if (StringUtil.isNotEmpty(studentApplyInfo.getStudyWorkAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getStudyWorkAttId());
            if (att != null) {
                studentApplyInfo.setStudyWorkRealName(att.getRealName());
                studentApplyInfo.setStudyWorkUrlPath(att.getPath());
                model.addAttribute("studyWorkIsImage", FtpUploadUtil.isImage(att.getRealName()));
            }
        }
        
        //注意：请上传5-8分钟与申请岗位相关的教学视频，
        if (StringUtil.isNotEmpty(studentApplyInfo.getTeaVideoAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getTeaVideoAttId());
            if (att != null) {
                studentApplyInfo.setTeaVideoRealName(att.getRealName());
                studentApplyInfo.setTeaVideoUrlPath(att.getPath());
            }
        }
        
        //暂缓就业协议书（已办理暂缓就业手续的毕业生必须提供）
        if (StringUtil.isNotEmpty(studentApplyInfo.getSuspendEmpAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getSuspendEmpAttId());
            if (att != null) {
                studentApplyInfo.setSuspendEmpRealName(att.getRealName());
                studentApplyInfo.setSuspendEmpUrlPath(att.getPath());
            }
        }
        
        //出国留学
        if (StringUtil.isNotEmpty(studentApplyInfo.getAbroadStudyAttId())) {
            Attachement att = attachementService.selectAttachementById(studentApplyInfo.getAbroadStudyAttId());
            if (att != null) {
                studentApplyInfo.setAbroadStudyRealName(att.getRealName());
                studentApplyInfo.setAbroadStudyUrlPath(att.getPath());
            }
        }
        
     // 毕业成绩单
        if (StringUtil.isNotEmpty(studentApplyInfo.getTranscriptAttId())) {
            Attachement att = attachementService
                    .selectAttachementById(studentApplyInfo.getTranscriptAttId());
            if (att != null) {
                studentApplyInfo.setTranscriptRealName(att.getRealName());
                studentApplyInfo.setTranscriptUrlPath(att.getPath());
            }
        } 
        
        // 心理学成绩
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerPsyAttId())) {
            Attachement att = attachementService
                    .selectAttachementById(studentApplyInfo.getCerPsyAttId());
            if (att != null) {
                studentApplyInfo.setCerPsyRealName(att.getRealName());
                studentApplyInfo.setCerPsyUrlPath(att.getPath());
            }
        } 
        
        // 普通话成绩
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerManAttId())) {
            Attachement att = attachementService
                    .selectAttachementById(studentApplyInfo.getCerManAttId());
            if (att != null) {
                studentApplyInfo.setCerManRealName(att.getRealName());
                studentApplyInfo.setCerManUrlPath(att.getPath());
            }
        } 
        
        // 教育学成绩
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerPedAttId())) {
            Attachement att = attachementService
                    .selectAttachementById(studentApplyInfo.getCerPedAttId());
            if (att != null) {
                studentApplyInfo.setCerPedRealName(att.getRealName());
                studentApplyInfo.setCerPedUrlPath(att.getPath());
            }
        } 
        
        //教育教学能力测试
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerAbiAttId())) {
            Attachement att = attachementService
                    .selectAttachementById(studentApplyInfo.getCerAbiAttId());
            if (att != null) {
                studentApplyInfo.setCerAbiRealName(att.getRealName());
                studentApplyInfo.setCerAbiUrlPath(att.getPath());
            }
        } 
        
        // 教育实习证明
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerShipAttId())) {
            Attachement att = attachementService
                    .selectAttachementById(studentApplyInfo.getCerShipAttId());
            if (att != null) {
                studentApplyInfo.setCerShipRealName(att.getRealName());
                studentApplyInfo.setCerShipUrlPath(att.getPath());
            }
        }
        
        //岗位名称及学科
        String applyJobId=studentApplyInfo.getApplyJobId();
        Postset postset=postsetDao.selectPostsetById(applyJobId);
        model.addAttribute("postset", postset);
        model.addAttribute("studentApplyInfo", studentApplyInfo);
        
        //审核原因
        AuditReason auditReason=new AuditReason();
    	auditReason.setOwnerid(user.getOrginId());//当前登录用户组织id
    	auditReason.setType("2");
    	List<AuditReason> auditReasons=auditReasonService.selectAllByAuditReason(auditReason);
    	model.addAttribute("flag", flag);
    	model.addAttribute("auditReasons", auditReasons);
        return "/recruit/person_docheck.vm";
    }
    
    
    //导出 考生资料
    @RequestMapping("/personDataPage")
    public String personData(Model model){
    	List<String> years =  themeService.selectYears() ;
        model.addAttribute("years",years) ;
        List<Postset> posts = postsetDao.selectAllPostset() ;
        model.addAttribute("posts", posts) ;
        return "/recruit/person_data.vm" ;
    }
    
    
    @ResponseBody
    @RequestMapping(value="/selectPersonData",method= RequestMethod.POST)
    public String selectPersonData(Page page) {
        Map<String, Object> condition =page.getCondition();
        page = studentInfoService.selectPersonData(page,condition);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage()) ;
        return JSONObject.toJSON(jsonMap).toString();
    }
    
    
  //导出功能
	@RequestMapping("/exportPersonData")
    public void exportPersonData(String projectId,String name,String postId,Page page,HttpServletRequest request,HttpSession session,HttpServletResponse response){
		Map<String, Object> condition =new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(name)){
			try {
				name =  URLDecoder.decode(name,"UTF-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//name = new String(request.getParameter("name").getBytes("ISO8859-1")) ;
		condition.put("projectId", projectId) ;
    	condition.put("name", name) ;
    	condition.put("postId", postId) ;
        page = studentInfoService.selectPersonData(page,condition);
        List<StudentInfo> list = (List<StudentInfo>)page.getResult() ;
         
        User user = (User)session.getAttribute("user") ;
 		WritableWorkbook wwb = null;// Excel数据组装
 		String usersTempPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
				+ "template.xls";
		String createPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
				+ "exportPersonData"+user.getUserId()+".xls";
 		Workbook workbook = null;
 		try {
 			workbook = Workbook.getWorkbook(new File(usersTempPath));
 			wwb = Workbook.createWorkbook(new File(createPath), workbook);// 拷贝模板，把拷贝的模板临时放一个路径下
 			WritableSheet sheet = wwb.getSheet(0);
 			sheet.setRowView(0, 500, false); //设置行高
 			//合并单元格，第一个参数：要合并的单元格最左上角的列号，第二个参数：要合并的单元格最左上角的行号，
 			//第三个参数：要合并的单元格最右角的列号，第四个参数：要合并的单元格最右下角的行号，
 			WritableCellFormat cellFormat1 = new WritableCellFormat(); 
 			//设置文字居中对齐方式;  
 	        cellFormat1.setAlignment(Alignment.CENTRE);  
 	        //设置垂直居中;  
 	        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
 			sheet.mergeCells(0, 0, 6, 0);
 			sheet.addCell(new Label(0,0,"考生资料",cellFormat1));
 			sheet.setColumnView(0, 20);
 			sheet.setColumnView(1, 10);
 			sheet.setColumnView(2, 25);
 			sheet.setColumnView(3, 20);
 			sheet.setColumnView(4, 40);
 			sheet.setColumnView(5, 40);
 			sheet.setColumnView(6, 10);
 			sheet.addCell(new Label(0, 1, "姓名"));
 			sheet.addCell(new Label(1, 1, "性别"));
 			sheet.addCell(new Label(2, 1, "身份证号"));
 			sheet.addCell(new Label(3, 1, "手机号"));
 			sheet.addCell(new Label(4, 1, "单位名称"));
 			sheet.addCell(new Label(5, 1, "申请岗位"));
 			sheet.addCell(new Label(6, 1, "面试成绩"));
 			for (int i = 0; i < list.size(); i++) {
 				StudentInfo stu = list.get(i);
 				sheet.addCell(new Label(0, i + 2, stu.getName()));
 				if("1".equals(stu.getSex())){
 					sheet.addCell(new Label(1, i + 2, "男"));
 				}else{
 					sheet.addCell(new Label(1, i + 2, "女"));
 				}
 				sheet.addCell(new Label(2, i + 2, stu.getIdentityCard()));
 				sheet.addCell(new Label(3, i + 2, stu.getPhoneNumber()));
 				sheet.addCell(new Label(4, i + 2, stu.getSchoolName()));
 				sheet.addCell(new Label(5, i + 2, stu.getPostName()));
 				sheet.addCell(new Label(6, i + 2, stu.getViewGrade()));
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			// 关闭Excel工作薄对象
 			if (wwb != null) {
 				try {
 					wwb.write();
 					wwb.close();
 					ExcelHelper.outputExcel(response, "考生资料", createPath);
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
    
    
    //导出笔试成绩
    @RequestMapping("/personScorePage")
    public String personScorePage(Model model){
    	List<String> years =  themeService.selectYears() ;
        model.addAttribute("years",years) ;
        return "/recruit/score_info_search.vm" ;
    }
    
    
    @ResponseBody
    @RequestMapping(value="/personWrittenScore",method= RequestMethod.POST)
    public String personScore(Page page) {
    	Map<String, Object> condition =page.getCondition();
    	//String type=(String) condition.get("testType");//考试类型
        Notice notice = noticeService.selectObjectByThemeId((String)condition.get("projectId")) ;
        Integer listpub = notice.getWrittenListPublish() ;
        if(listpub==1){
            condition.put("isEnter","1") ;
        }else{
            condition.put("isEnter","1111") ;
        }
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
		page=scoreEnterWritienService.selectWritienScore(page, condition);
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        jsonMap.put("notice",notice) ;
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    
    //导出笔试成绩
  	@RequestMapping("/exportPersonWrittenScore")
      public void exportPersonWrittenScore(String projectId,Page page,HttpServletRequest request,HttpSession session,HttpServletResponse response){
      	 Map<String, Object> condition = new HashMap<String, Object>() ;
      	 condition.put("projectId", projectId) ;
        Notice notice = noticeService.selectObjectByThemeId(projectId) ;
        Integer listpub = notice.getWrittenListPublish() ;
        if(listpub==1){
            condition.put("isEnter","1") ;
        }else{
            condition.put("isEnter","1111") ;
        }
         List<ScoreEntersOutVo> list = scoreEnterWritienService.selectWritienScore(condition);
         Map<String, List<ScoreEntersOutVo>> dataMap = new HashMap<String, List<ScoreEntersOutVo>>();
 		 Set<ScoreEntersOutVo> set = new HashSet<ScoreEntersOutVo>();
 		 List<ScoreEntersOutVo> tempList = null;
 		 for (ScoreEntersOutVo s : list) {

 			if (set.add(s)) {
 				tempList = new ArrayList<ScoreEntersOutVo>();
 				tempList.add(s);
 				dataMap.put(s.getPostName(), tempList);
 			} else {

 				if (dataMap.containsKey(s.getPostName())) {
 					tempList = dataMap.get(s.getPostName());
 					tempList.add(s);
 					dataMap.put(s.getPostName(), tempList);
 				}
 			}
 		 }
           
         User user = (User)session.getAttribute("user") ;
   		 WritableWorkbook wwb = null;// Excel数据组装
   		 String usersTempPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
  				+ "template.xls";
  		 String createPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
  				+ "exportPersonLectureScore"+user.getUserId()+".xls";
   		 Workbook workbook = null;
   		 try {
   			workbook = Workbook.getWorkbook(new File(usersTempPath));
   			wwb = Workbook.createWorkbook(new File(createPath), workbook);// 拷贝模板，把拷贝的模板临时放一个路径下
   			int k = 0;
   			for (String key : dataMap.keySet()) {  
   				WritableSheet sheet = null ;
   				if(k<3){
   					sheet = wwb.getSheet(k);
   				}else{
   					sheet = wwb.createSheet(key,k);
   				}
	   			sheet.setName(key);
	   			k++;
	   			sheet.setRowView(0, 500, false); //设置行高
	   			//合并单元格，第一个参数：要合并的单元格最左上角的列号，第二个参数：要合并的单元格最左上角的行号，
	   			//第三个参数：要合并的单元格最右角的列号，第四个参数：要合并的单元格最右下角的行号，
	   			WritableCellFormat cellFormat1 = new WritableCellFormat(); 
	   			//设置文字居中对齐方式;  
	   	        cellFormat1.setAlignment(Alignment.CENTRE);  
	   	        //设置垂直居中;  
	   	        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
	   			sheet.mergeCells(0, 0, 6, 0);
	   			sheet.addCell(new Label(0,0,key,cellFormat1));
	   			sheet.setColumnView(0, 20);
	   			sheet.setColumnView(1, 10);
	   			sheet.setColumnView(2, 25);
	   			sheet.setColumnView(3, 20);
	   			sheet.setColumnView(4, 40);
	   			sheet.setColumnView(5, 40);
	   			sheet.setColumnView(6, 10);
	   			sheet.setColumnView(7, 10);
	   			sheet.setColumnView(8, 10);
	   			sheet.addCell(new Label(0, 1, "姓名"));
	   			sheet.addCell(new Label(1, 1, "身份证号"));
	   			sheet.addCell(new Label(2, 1, "手机号"));
	   			sheet.addCell(new Label(3, 1, "申请岗位"));
	   			sheet.addCell(new Label(4, 1, "单位名称"));
	   			sheet.addCell(new Label(5, 1, "考试时间"));
	   			sheet.addCell(new Label(6, 1, "笔试成绩"));
	   			List<ScoreEntersOutVo> svlist = dataMap.get(key) ;
	   			for (int i = 0; i < svlist.size(); i++) {
	   				ScoreEntersOutVo sv = svlist.get(i);
	   				sheet.addCell(new Label(0, i + 2, sv.getStudentName()));
	   				sheet.addCell(new Label(1, i + 2, sv.getIdCard()));
	   				sheet.addCell(new Label(2, i + 2, sv.getPhone()));
	   				sheet.addCell(new Label(3, i + 2, sv.getPostName()));
	   				sheet.addCell(new Label(4, i + 2, sv.getSchool()));
	   				sheet.addCell(new Label(5, i + 2, sv.getWrittenTime()));
	   				if(1==notice.getWrittenScorePublish()){
                        sheet.addCell(new Label(6, i + 2, sv.getScore()));
                    }else{
                        sheet.addCell(new Label(6, i + 2, "0"));
                    }
	   			}
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   		} finally {
   			// 关闭Excel工作薄对象
   			if (wwb != null) {
   				try {
   					wwb.write();
   					wwb.close();
   					ExcelHelper.outputExcel(response, "笔试成绩导出", createPath);
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
    
    
    
    //导出试讲成绩
    @RequestMapping("/personLecturePage")
    public String personLecturePage(Model model){
    	List<String> years =  themeService.selectYears() ;
        model.addAttribute("years",years) ;
        return "/recruit/score_info_search.vm" ;
    }
    
    
    @ResponseBody
    @RequestMapping(value="/personLectureScore",method= RequestMethod.POST)
    public String personLecture(Page page) {
    	Map<String, Object> condition =page.getCondition();
       	Map<String, Object> jsonMap = new HashMap<String, Object>();
		page=scoreEnterTrialService.selectPersonLecture(page, condition);
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
        Notice notice = noticeService.selectObjectByThemeId((String)condition.get("projectId")) ;
        jsonMap.put("notice",notice) ;
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    //导出试讲成绩
  	@RequestMapping("/exportPersonLectureScore")
      public void exportPersonLectureScore(String projectId,Page page,HttpServletRequest request,HttpSession session,HttpServletResponse response){
      	 Map<String, Object> condition = new HashMap<String, Object>() ;
      	 condition.put("projectId", projectId) ;
         List<ScoreEntersOutVo> list = scoreEnterTrialService.selectPersonLecture(condition);
         Notice notice = noticeService.selectObjectByThemeId(projectId) ;
         Map<String, List<ScoreEntersOutVo>> dataMap = new HashMap<String, List<ScoreEntersOutVo>>();
 		 Set<ScoreEntersOutVo> set = new HashSet<ScoreEntersOutVo>();
 		 List<ScoreEntersOutVo> tempList = null;
 		 for (ScoreEntersOutVo s : list) {

 			if (set.add(s)) {
 				tempList = new ArrayList<ScoreEntersOutVo>();
 				tempList.add(s);
 				dataMap.put(s.getPostName(), tempList);
 			} else {

 				if (dataMap.containsKey(s.getPostName())) {
 					tempList = dataMap.get(s.getPostName());
 					tempList.add(s);
 					dataMap.put(s.getPostName(), tempList);
 				}
 			}
 		 }
           
         User user = (User)session.getAttribute("user") ;
   		 WritableWorkbook wwb = null;// Excel数据组装
   		 String usersTempPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
  				+ "template.xls";
  		 String createPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
  				+ "exportPersonLectureScore"+user.getUserId()+".xls";
   		 Workbook workbook = null;
   		 try {
   			workbook = Workbook.getWorkbook(new File(usersTempPath));
   			wwb = Workbook.createWorkbook(new File(createPath), workbook);// 拷贝模板，把拷贝的模板临时放一个路径下
   			int k = 0;
   			for (String key : dataMap.keySet()) {  
   				WritableSheet sheet = null ;
   				if(k<3){
   					sheet = wwb.getSheet(k);
   				}else{
   					sheet = wwb.createSheet(key,k);
   				}
	   			sheet.setName(key);
	   			k++;
	   			sheet.setRowView(0, 500, false); //设置行高
	   			//合并单元格，第一个参数：要合并的单元格最左上角的列号，第二个参数：要合并的单元格最左上角的行号，
	   			//第三个参数：要合并的单元格最右角的列号，第四个参数：要合并的单元格最右下角的行号，
	   			WritableCellFormat cellFormat1 = new WritableCellFormat(); 
	   			//设置文字居中对齐方式;  
	   	        cellFormat1.setAlignment(Alignment.CENTRE);  
	   	        //设置垂直居中;  
	   	        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
	   			sheet.mergeCells(0, 0, 8, 0);
	   			sheet.addCell(new Label(0,0,key,cellFormat1));
	   			sheet.setColumnView(0, 20);
	   			sheet.setColumnView(1, 10);
	   			sheet.setColumnView(2, 25);
	   			sheet.setColumnView(3, 20);
	   			sheet.setColumnView(4, 40);
	   			sheet.setColumnView(5, 40);
	   			sheet.setColumnView(6, 10);
	   			sheet.setColumnView(7, 10);
	   			sheet.setColumnView(8, 10);
	   			sheet.addCell(new Label(0, 1, "姓名"));
	   			sheet.addCell(new Label(1, 1, "身份证号"));
	   			sheet.addCell(new Label(2, 1, "手机号"));
	   			sheet.addCell(new Label(3, 1, "申请岗位"));
	   			sheet.addCell(new Label(4, 1, "单位名称"));
	   			sheet.addCell(new Label(5, 1, "考试时间"));
	   			sheet.addCell(new Label(6, 1, "笔试成绩"));
	   			sheet.addCell(new Label(7, 1, "试讲成绩"));
	   			sheet.addCell(new Label(8, 1, "综合成绩"));
	   			List<ScoreEntersOutVo> svlist = dataMap.get(key) ;
	   			for (int i = 0; i < svlist.size(); i++) {
	   				ScoreEntersOutVo sv = svlist.get(i);
	   				sheet.addCell(new Label(0, i + 2, sv.getStudentName()));
	   				sheet.addCell(new Label(1, i + 2, sv.getIdCard()));
	   				sheet.addCell(new Label(2, i + 2, sv.getPhone()));
	   				sheet.addCell(new Label(3, i + 2, sv.getPostName()));
	   				sheet.addCell(new Label(4, i + 2, sv.getSchool()));
	   				sheet.addCell(new Label(5, i + 2, sv.getLectureTime()));
	   				if(1==notice.getWrittenScorePublish()){
                        sheet.addCell(new Label(6, i + 2, sv.getScore()));
                    }else{
                        sheet.addCell(new Label(6, i + 2, "0"));
                    }
                    if(1==notice.getLectureScorePublishArt()&&1==notice.getLectureScorePublishNor()){
                        sheet.addCell(new Label(7, i + 2, sv.getOffer()));
                        sheet.addCell(new Label(8, i + 2, sv.getScyscore()));
                    }else{
                        sheet.addCell(new Label(7, i + 2, "0"));
                        sheet.addCell(new Label(8, i + 2, "0"));
                    }
	   			}
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   		} finally {
   			// 关闭Excel工作薄对象
   			if (wwb != null) {
   				try {
   					wwb.write();
   					wwb.close();
   					ExcelHelper.outputExcel(response, "试讲情况导出", createPath);
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
    
    
    //导出功能
	@RequestMapping("/exportSchoolCheckedSituation")
    public void exportSchoolCheckedSituation(String projectId,String name,String status,Page page,HttpServletRequest request,HttpSession session,HttpServletResponse response){
    	 Map<String, Object> condition = new HashMap<String, Object>() ;
    	 condition.put("projectId", projectId) ;
    	 condition.put("name", name) ;
    	 condition.put("applyStatus",status) ;
         page = studentInfoService.selectSchoolCheckedSituation(page,condition);
         List<StudentInfo> list = (List<StudentInfo>)page.getResult() ;
         
         
        User user = (User)session.getAttribute("user") ;
 		WritableWorkbook wwb = null;// Excel数据组装
 		String usersTempPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
				+ "template.xls";
		String createPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
				+ "exportSchoolCheckedSituation_"+user.getUserId()+".xls";
 		Workbook workbook = null;
 		try {
 			workbook = Workbook.getWorkbook(new File(usersTempPath));
 			wwb = Workbook.createWorkbook(new File(createPath), workbook);// 拷贝模板，把拷贝的模板临时放一个路径下
 			WritableSheet sheet = wwb.getSheet(0);
 			sheet.setRowView(0, 500, false); //设置行高
 			//合并单元格，第一个参数：要合并的单元格最左上角的列号，第二个参数：要合并的单元格最左上角的行号，
 			//第三个参数：要合并的单元格最右角的列号，第四个参数：要合并的单元格最右下角的行号，
 			WritableCellFormat cellFormat1 = new WritableCellFormat(); 
 			//设置文字居中对齐方式;  
 	        cellFormat1.setAlignment(Alignment.CENTRE);  
 	        //设置垂直居中;  
 	        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
 			sheet.mergeCells(0, 0, 6, 0);
 			sheet.addCell(new Label(0,0,"学校审核情况",cellFormat1));
 			sheet.setColumnView(0, 20);
 			sheet.setColumnView(1, 10);
 			sheet.setColumnView(2, 25);
 			sheet.setColumnView(3, 20);
 			sheet.setColumnView(4, 40);
 			sheet.setColumnView(5, 40);
 			sheet.setColumnView(6, 10);
 			sheet.addCell(new Label(0, 1, "姓名"));
 			sheet.addCell(new Label(1, 1, "性别"));
 			sheet.addCell(new Label(2, 1, "身份证号"));
 			sheet.addCell(new Label(3, 1, "手机号"));
 			sheet.addCell(new Label(4, 1, "单位名称"));
 			sheet.addCell(new Label(5, 1, "申请岗位"));
 			sheet.addCell(new Label(6, 1, "状态"));
 			for (int i = 0; i < list.size(); i++) {
 				StudentInfo stu = list.get(i);
 				sheet.addCell(new Label(0, i + 2, stu.getName()));
 				if("1".equals(stu.getSex())){
 					sheet.addCell(new Label(1, i + 2, "男"));
 				}else{
 					sheet.addCell(new Label(1, i + 2, "女"));
 				}
 				sheet.addCell(new Label(2, i + 2, stu.getIdentityCard()));
 				sheet.addCell(new Label(3, i + 2, stu.getPhoneNumber()));
 				sheet.addCell(new Label(4, i + 2, stu.getSchoolName()));
 				sheet.addCell(new Label(5, i + 2, stu.getPostName()));
 				sheet.addCell(new Label(6, i + 2, stu.getStatustext()));
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			// 关闭Excel工作薄对象
 			if (wwb != null) {
 				try {
 					wwb.write();
 					wwb.close();
 					ExcelHelper.outputExcel(response, "学校审核情况", createPath);
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
	
	
	
	//导出功能
		@RequestMapping("/exportSchoolInterviewSituation")
	    public void exportSchoolInterviewSituation(String projectId,String name,int status,Page page,HttpServletRequest request,HttpSession session,HttpServletResponse response){
			 Map<String, Object> condition =new HashMap<String, Object>();
			 condition.put("projectId", projectId) ;
	    	 condition.put("name", name) ;
	    	 condition.put("status",status) ;
	         page = studentInfoService.selectSchoolInterviewSituation(page,condition);
	         List<StudentInfo> list = (List<StudentInfo>)page.getResult() ;
	         
	        User user = (User)session.getAttribute("user") ;
	 		WritableWorkbook wwb = null;// Excel数据组装
	 		String usersTempPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
					+ "template.xls";
			String createPath = request.getSession().getServletContext().getRealPath("/template/") + File.separator
					+ "exportSchoolCheckedSituation_"+user.getUserId()+".xls";
	 		Workbook workbook = null;
	 		try {
	 			workbook = Workbook.getWorkbook(new File(usersTempPath));
	 			wwb = Workbook.createWorkbook(new File(createPath), workbook);// 拷贝模板，把拷贝的模板临时放一个路径下
	 			WritableSheet sheet = wwb.getSheet(0);
	 			sheet.setRowView(0, 500, false); //设置行高
	 			//合并单元格，第一个参数：要合并的单元格最左上角的列号，第二个参数：要合并的单元格最左上角的行号，
	 			//第三个参数：要合并的单元格最右角的列号，第四个参数：要合并的单元格最右下角的行号，
	 			WritableCellFormat cellFormat1 = new WritableCellFormat(); 
	 			//设置文字居中对齐方式;  
	 	        cellFormat1.setAlignment(Alignment.CENTRE);  
	 	        //设置垂直居中;  
	 	        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
	 			sheet.mergeCells(0, 0, 7, 0);
	 			sheet.addCell(new Label(0,0,"学校面试情况",cellFormat1));
	 			sheet.setColumnView(0, 20);
	 			sheet.setColumnView(1, 10);
	 			sheet.setColumnView(2, 10);
	 			sheet.setColumnView(3, 25);
	 			sheet.setColumnView(4, 20);
	 			sheet.setColumnView(5, 40);
	 			sheet.setColumnView(6, 40);
	 			sheet.setColumnView(7, 10);
	 			sheet.addCell(new Label(0, 1, "姓名"));
	 			sheet.addCell(new Label(1, 1, "性别"));
	 			sheet.addCell(new Label(2, 1, "年龄"));
	 			sheet.addCell(new Label(3, 1, "身份证号"));
	 			sheet.addCell(new Label(4, 1, "手机号"));
	 			sheet.addCell(new Label(5, 1, "单位名称"));
	 			sheet.addCell(new Label(6, 1, "申请岗位"));
	 			sheet.addCell(new Label(7, 1, "状态"));
	 			for (int i = 0; i < list.size(); i++) {
	 				StudentInfo stu = list.get(i);
	 				sheet.addCell(new Label(0, i + 2, stu.getName()));
	 				if("1".equals(stu.getSex())){
	 					sheet.addCell(new Label(1, i + 2, "男"));
	 				}else{
	 					sheet.addCell(new Label(1, i + 2, "女"));
	 				}
	 				sheet.addCell(new Label(2, i + 2, stu.getAge()));
	 				sheet.addCell(new Label(3, i + 2, stu.getIdentityCard()));
	 				sheet.addCell(new Label(4, i + 2, stu.getPhoneNumber()));
	 				sheet.addCell(new Label(5, i + 2, stu.getSchoolName()));
	 				sheet.addCell(new Label(6, i + 2, stu.getPostName()));
	 				sheet.addCell(new Label(7, i + 2, stu.getStatustext()));
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		} finally {
	 			// 关闭Excel工作薄对象
	 			if (wwb != null) {
	 				try {
	 					wwb.write();
	 					wwb.close();
	 					ExcelHelper.outputExcel(response, "学校面试情况", createPath);
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
		
		
		



}

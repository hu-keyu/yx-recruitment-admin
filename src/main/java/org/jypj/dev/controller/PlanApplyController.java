package org.jypj.dev.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.AuditReason;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.PositionDomain;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentEduInfo;
import org.jypj.dev.entity.StudentFamInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.AttachementService;
import org.jypj.dev.service.AuditReasonService;
import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PlanApplyService;
import org.jypj.dev.service.PositionDomainService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.service.StudentEduInfoService;
import org.jypj.dev.service.StudentFamInfoService;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * PlanApply控制器
 * @author QiCai
 *
 */
@Controller
@RequestMapping("/dg/planApply")
public class PlanApplyController {
	
    @Resource 
    private PlanApplyService planApplyService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private StudentInfoService studentInfoService;
    @Resource
    private StudentFamInfoService studentFamInfoService;
    @Resource
    private StudentEduInfoService studentEduInfoService;
    @Resource
    private ThemeService themeService;
    @Resource
    private StudentApplyInfoService studentApplyInfoService;
    @Resource
    private PositionDomainService positionDomainService;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private AttachementService attachementService;
    @Resource
    private AuditReasonService auditReasonService;
    @Resource
    private PostsetService postsetService;
    @Resource
    private NoticeService noticeService;

	/*--------------add by zhangcunjun  start---------------------*/

	@RequestMapping("/checkIndex")
	public String gotoCheckIndex(Model model){
		List<String> years = themeService.selectYears() ;
        model.addAttribute("years", years);
		return "/recruit/plan_list.vm" ;
	}

	@RequestMapping("/checkPage")
	public String gotoCheckPage(Model model,String planApplyId){
		PlanApply planApply = planApplyService.selectById(planApplyId) ;
		Position position=new Position();
		position.setStatus("1");//只显示启用的岗位
		if(StringUtils.isNotBlank(planApplyId)){
			position.setPlanApplyId(planApplyId);
		}
		List<Position> positions=positionService.selectAllByPosition(position);
		model.addAttribute("positions",positions) ;
		model.addAttribute("planApply",planApply) ;
		return "/recruit/plan_check.vm" ;
	}

	@RequestMapping("/checkCountPage")
	public String gotoCheckCountPage(){
		return "/recruit/plan_count.vm" ;
	}

	@RequestMapping("/selectPlanForCheck")
	@ResponseBody
	public Map<String,Object> selectPlanForCheck(Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> condition =page.getCondition();
		page = planApplyService.selectPlanForCheck(page,condition.get("projectId").toString());
		map.put("total", page.getTotalRows());
		map.put("rows", page.getResult());
		map.put("currentPage", page.getCurrentPage()) ;
		return map ;
	}

	@RequestMapping("/planApplyCheck")
    @ResponseBody
	public Result planApplyCheck(PlanApply planApply) {
        Result result = new Result();
        List<Position> positionList = planApply.getPositionList();
        PlanApply p = planApplyService.selectById(planApply.getId());
        if ("0".equals(p.getStatus())) {
            result.setMsg("此招聘计划已撤销不能审核！");
            result.setSuccess(false);
            return result ;
        }else{
            try {
                planApply.setStatus("2");
                positionService.checkBatch(positionList);
                planApplyService.updatePlanApplyByField(planApply);
                result.setMsg("保存成功！");
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.setMsg("审批失败！");
                result.setSuccess(false);
                return result;
            }
        }
	}

	/*--------------add by zhangcunjun  end---------------------*/

    @RequestMapping(value="planApplyList")
    public String toPlanApplyList(HttpSession session,Model model){
    	User user=(User)session.getAttribute("user");
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("schoolId", user.getSchoolId());//当前登录用户学校ID
    	queryParameter.put("themeStatus", "1");//招聘主题已发布
    	List<String> years=planApplyService.getPlanApplyYears(queryParameter);
    	model.addAttribute("years", years);
    	model.addAttribute("nowDate", new Date());
    	return "/teacher/planApply_list.vm";
    }
    
    @RequestMapping(value="gotoPlanApply")
    public String gotoPlanApply(Model model,String themeId,String planApplyId,String flag){
    	Position position=new Position(); 
    	position.setStatus("1");//只显示启用的岗位
    	if(StringUtils.isNotBlank(themeId)){
    		position.setProjectId(themeId);
    	}
    	if(StringUtils.isNotBlank(planApplyId)){
    		position.setPlanApplyId(planApplyId);
    	}
    	List<Position> positions=positionService.selectAllByPosition(position);
    	PlanApply PlanApply=planApplyService.selectPlanApplyById(planApplyId);
    	model.addAttribute("flag", flag);
    	model.addAttribute("themeId", themeId);
    	model.addAttribute("positions", positions);
    	model.addAttribute("planApplyId", planApplyId);
    	model.addAttribute("PlanApply", PlanApply);
    	return "/teacher/planApply_input.vm";
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllPlanApply",method=RequestMethod.POST)
	public String selectAllPlanApply(Page page,HttpSession session) {
    	User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		page = planApplyService.selectOnePageByMap(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @ResponseBody
	@RequestMapping(value="postReport",method=RequestMethod.POST)
    public JSONObject postReport(String planApplyId,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try {
			planApplyService.saveReport(planApplyId, user, jsonMap);
			jsonMap.put("flag", "success");
    		jsonMap.put("msg", "申报信息上报成功！");
    		return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
    		return jsonMap;
		}
    }
    
    @ResponseBody
	@RequestMapping(value="postRecall",method=RequestMethod.POST)
    public JSONObject postRecall(String planApplyId,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try {
			planApplyService.saveRecall(planApplyId,user,jsonMap);
			jsonMap.put("flag", "success");
    		jsonMap.put("msg", "申报信息撤回成功！");
    		return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
    		return jsonMap;
		}
    }
    
    @RequestMapping(value="auditInterview")
    public String auditInterview(Model model,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("schoolId", user.getSchoolId());//当前登录用户学校ID
    	queryParameter.put("isPublish", "1");//招聘公告已发布
    	queryParameter.put("status", "2");//招聘计划已审核
    	queryParameter.put("themeStatus", "1");//招聘主题已发布
    	List<String> years=planApplyService.getPlanApplyYears(queryParameter);
    	
    	AuditReason auditReason=new AuditReason();
    	auditReason.setOwnerid(user.getSchoolId());//当前登录用户学校ID
    	auditReason.setType("1");
    	List<AuditReason> auditReasons=auditReasonService.selectAllByAuditReason(auditReason);
    	
    	model.addAttribute("years", years);
    	model.addAttribute("auditReasons", auditReasons);
    	return "/audit/auditInterview_no_list.vm";
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllNoAuditInterview",method=RequestMethod.POST)
	public String selectAllAuditInterview(Page page,HttpSession session) {
    	User user=(User)session.getAttribute("user");
    	Map<String, Object> condition =page.getCondition();
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		page = studentInfoService.queryNoAuditList(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @RequestMapping(value="auditInterviewPass")
    public String auditInterviewPass(Model model,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	Map<String,Object> queryParameter=new HashMap<String,Object>();
    	queryParameter.put("schoolId", user.getSchoolId());//当前登录用户学校ID
    	queryParameter.put("isPublish", "1");//招聘公告已发布
    	queryParameter.put("status", "2");//招聘计划已审核
    	queryParameter.put("themeStatus", "1");//招聘主题已发布
    	List<String> years=planApplyService.getPlanApplyYears(queryParameter);
    	model.addAttribute("years", years);
    	return "/audit/auditInterview_pass_list.vm";
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllAuditInterviewPass",method=RequestMethod.POST)
	public String selectAllAuditInterviewPass(Page page,HttpSession session) {
    	User user=(User)session.getAttribute("user");
    	Map<String, Object> condition =page.getCondition();
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		page = studentInfoService.queryAuditList(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @ResponseBody
	@RequestMapping(value="checkInterview",method=RequestMethod.POST)
    public JSONObject checkInterview(String chk,String projectId,String reason,String ispass,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try {
    		planApplyService.checkInterview(chk,projectId,reason,ispass,user,jsonMap);
			jsonMap.put("flag", "success");
    		jsonMap.put("msg", "操作成功！");
    		return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
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
    		planApplyService.checkCancel(chk,projectId,user,status,reason,jsonMap);
			jsonMap.put("flag", "success");
    		jsonMap.put("msg", "操作成功！");
    		return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
    		return jsonMap;
		}
    }
    
    @RequestMapping(value = "register")
    public String userProfile(Model model, String studentId,String recruitId,String flag,HttpSession session) {
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
        Postset postset=postsetService.selectPostsetById(applyJobId);
        model.addAttribute("postset", postset);
        model.addAttribute("studentApplyInfo", studentApplyInfo);
        
        //审核原因
        AuditReason auditReason=new AuditReason();
    	auditReason.setOwnerid(user.getSchoolId());//当前登录用户学校ID
    	auditReason.setType("1");
    	List<AuditReason> auditReasons=auditReasonService.selectAllByAuditReason(auditReason);
    	model.addAttribute("flag", flag);
    	model.addAttribute("auditReasons", auditReasons);
        return "/audit/auditInterview_input.vm";
    }
    
    @ResponseBody
    @RequestMapping(value = "getProject")
    public Theme getProject(String projectId){
    	return themeService.selectThemeById(projectId);
    }
    
    @RequestMapping(value = "viewProgress")
    public String viewProgress(String studentId,String projectId,HttpSession session,Model model){
    	User user=(User)session.getAttribute("user");
    	Notice notice=noticeService.selectObjectByThemeId(projectId);
    	StudentApplyInfo studentApplyInfo =new StudentApplyInfo();
    	studentApplyInfo.setEmployItemsId(projectId);
    	studentApplyInfo.setStudentId(studentId);
    	studentApplyInfo.setApplyDepId(user.getSchoolId());
    	studentApplyInfo=studentApplyInfoService.selectObjectByStudentApplyInfo(studentApplyInfo);
    	model.addAttribute("notice", notice);
    	model.addAttribute("studentApplyInfo", studentApplyInfo);
    	return "/audit/view_progress.vm";
    }
    
    @RequestMapping(value="downloadFile")
    public void downloadFile(String fileId,HttpServletResponse response,HttpServletRequest request){
    	Attachement attachement=attachementService.selectAttachementById(fileId);
    	if(attachement !=null ){
    		String fileName=attachement.getRealName();
    		ExcelUtils.setResponseHeader(request,response,fileName);
    		InputStream is=FtpUploadUtil.getFtpFileInputStream(attachement.getPath());
    		OutputStream os =null;
    		try {
    			int length=0;
    			is = new BufferedInputStream(is);
    			byte[] bt = new byte[512*1024];
    			os=new BufferedOutputStream(response.getOutputStream());
    			while((length=is.read(bt)) != -1){
    				os.write(bt,0,length);
    			}
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally{
    			try {
    				is.close();
    				os.close();  
    				os.flush();
    			} catch (IOException e) {
    				e.printStackTrace();
    			} 
    		}
    	}
    }
}
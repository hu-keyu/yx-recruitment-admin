package org.jypj.dev.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.SecurityQueInfo;
import org.jypj.dev.entity.Specialty;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentEduInfo;
import org.jypj.dev.entity.StudentFamInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jypj.dev.service.ActionNoticeService;
import org.jypj.dev.service.AttachementService;
import org.jypj.dev.service.AuditReasonService;
import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.service.GradeService;
import org.jypj.dev.service.InformationService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PositionDomainService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.SecurityQueInfoService;
import org.jypj.dev.service.SpecialtyService;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.service.StudentEduInfoService;
import org.jypj.dev.service.StudentFamInfoService;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.MD5Utils;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.RandomPasswd;
import org.jypj.dev.util.StringUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * StudentInfo控制器
 * 考生基本信息表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/studentInfo")
public class StudentInfoController {
	
    @Resource
    private StudentApplyInfoService studentApplyInfoService;
    
    @Resource
    private StudentInfoService studentInfoService;
    
    @Resource
    private StudentFamInfoService studentFamInfoService;
    
    @Resource
    private StudentEduInfoService studentEduInfoService;
    
    @Resource
    private SecurityQueInfoService securityQueInfoService;

    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private ThemeService themeService;
    
    @Resource
    private AttachementService attachementService;
    
    @Resource
    private SpecialtyService specialService;
    
    @Resource
    private NoticeService noticeService;
    
    @Resource
    private PostsetService postsetService;
    
    @Resource
    private PositionService positionService;
    
    @Resource
    private PositionDomainService positionDomainService;
    
    @Resource
    private InformationService informationService;
    
    @Resource
    private ActionNoticeService actionNoticeService;
    
    @Resource
    private AuditReasonService auditReasonService;
    
    @Resource 
    private GradeService gradeService;

    @RequestMapping("getDic")
    @ResponseBody
    public String getDic(String code) {
        JSONObject jo = new JSONObject();
        try {
            List<Dictionary> dics = DictionaryCache.getDictionaryByCode(code);
            jo.put("flag", true);
            jo.put("msg",dics);
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "");
            return jo.toJSONString();
        }
    }
    
    
    
    @RequestMapping("forgetPass")
    @ResponseBody  
    public String forgetPass(@RequestBody List<SecurityQueInfo> secretList, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        String sid = "";
        if (secretList == null || secretList.size() == 0) {
            jo.put("flag", false);
            jo.put("msg", "请输入密保问题答案！");
            return jo.toJSONString();
        }
        
        sid = secretList.get(0).getStudentId();
        try {
            for (SecurityQueInfo sq : secretList) {
                if (sq.getAnswer().equals("")) {
                    jo.put("flag", false);
                    jo.put("msg", "密保答案为空，请正确回答密保问题！");
                    return jo.toJSONString();
                }
                sq = securityQueInfoService.selectObjectBySecurityQueInfo(sq);
                if (sq == null) {
                    jo.put("flag", false);
                    jo.put("msg", "请正确回答密保问题！");
                    return jo.toJSONString();
                }
            }
            jo.put("flag", true);
            jo.put("msg", "密保验证成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "设置出现异常！");
            return jo.toJSONString();
        }
    }
    
    @RequestMapping(value = "getForgetpass")
    public String getForgetpass(Model model, HttpServletRequest request) {
        return "/student/forgetpass.vm";
    }
    
    
    @RequestMapping(value = "verifyforgetSecret")
    public String verifyforgetSecret(String sid, Model model, HttpServletRequest request) {
        if (StringUtil.isEmpty(sid)) {
            return "/student/index.vm";
        }
        SecurityQueInfo sqi = new SecurityQueInfo();
        sqi.setStudentId(sid);
        List<SecurityQueInfo> sqiList = securityQueInfoService.selectAllBySecurityQueInfo(sqi);
        model.addAttribute("sqiList", sqiList);
        return "/student/verifySecretProtect.vm";
    }  
    
    @RequestMapping("saveForgetPass")
    @ResponseBody  
    public String saveForgetPass(HttpServletRequest request,String newpassConf,String newpass, String sid) {
        JSONObject jo = new JSONObject();
        if (StringUtil.isEmpty(newpass)) {
            jo.put("flag", false);
            jo.put("msg", "密码不能为空！");
            return jo.toJSONString();
        }
        
        if (StringUtil.isEmpty(sid)) {
            jo.put("flag", false);
            jo.put("msg", "请输入登录账号！");
            return jo.toJSONString();
        }
        
        if (!newpassConf.equals(newpass)) {
            jo.put("flag", false);
            jo.put("msg", "两次密码不一致！");
            return jo.toJSONString();
        }
        
        try {
            StudentInfo si = new StudentInfo();
            si.setId(sid);
            si.setPassword(MD5Utils.md5Encrypt(newpass));
            si.setCtime(null);
            si.setMtime(new Date());
            studentInfoService.updateStudentInfoByField(si);
            jo.put("flag", true);
            jo.put("msg", "密码重置成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "修改出现异常！");
            return jo.toJSONString();
        }
    }
    
    @RequestMapping(value="selectProfess")
    public String selectProfess(Model model, String stuEduId, String xllx){
        if (StringUtil.isNotEmpty(stuEduId)) {
            StudentEduInfo studentEdu = studentEduInfoService.selectStudentEduInfoById(stuEduId);
            String chosedProfess = studentEdu.getEduProfessionCode() == null ? "" : studentEdu.getEduProfessionCode() + studentEdu.getEduProfessionName() == null ? "" : studentEdu.getEduProfessionName();
            model.addAttribute("chosedProfess", chosedProfess);
            String isSimilar = studentEdu.getIsSimilarTerm() == null ? "" : studentEdu.getIsSimilarTerm();
            model.addAttribute("isSimilar", isSimilar);
            String similarCode = studentEdu.getSimilarTermCode() == null ? "" : studentEdu.getSimilarTermCode();
            model.addAttribute("similarCode", similarCode);
        }
        List<Specialty> speciaFs = specialService.findListByStorey(1);//一级学科
        List<Specialty> speciaSs = specialService.findListByStorey(2);//二级学科
        List<Dictionary> zylxs = new ArrayList<Dictionary>();
        List<Dictionary> xllxs = DictionaryCache.getDictionaryByCode("zylx");//专业类型
        if (StringUtil.isEmpty(xllx)) {
            zylxs = xllxs;
        } else {
            for (Dictionary dic : xllxs) {
                if (dic.getValue().equals(String.valueOf((4 - Integer.parseInt(xllx))))) {
                    zylxs.add(dic);
                    break;
                }
            }
        }
        
        model.addAttribute("zylxs", zylxs);
        model.addAttribute("speciaFs", speciaFs);
        model.addAttribute("speciaSs", speciaSs);
        model.addAttribute("zylx", xllx);
        return "/student/profess_select.vm";
    }
    
    @RequestMapping("queryProfess")
    @ResponseBody
    public String queryProfess(Specialty specialty, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            //关键字获取专业
            String keyProfess = specialty.getKeyProfess();
            map.put("keyProfess", keyProfess);
            map.put("storey", "3");
            map.put("type", specialty.getZylx());
            List<Specialty> speciaList = specialService.selectByKeyword(map);
            jo.put("flag", true);
            jo.put("speciaList",speciaList);
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("speciaList", "");
            return jo.toJSONString();
        }
    }
    
    @RequestMapping("getAllprofess")
    @ResponseBody
    public String getAllprofess(String sProfess) {
        JSONObject jo = new JSONObject();
        Specialty specialty = new Specialty();
        try {
            specialty.setPid(sProfess);
            specialty.setStorey(3);//专业
            List<Specialty> speciaList = specialService.selectAllBySpecialty(specialty);
            jo.put("flag", true);
            jo.put("speciaList",speciaList);
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("speciaList", "");
            return jo.toJSONString();
        }
    }
    
    
    @RequestMapping("getFprofess")
    @ResponseBody
    public String getFprofess(String zylx) {
        JSONObject jo = new JSONObject();
        Specialty specialty = new Specialty();
        try {
            specialty.setType(zylx);
            specialty.setStorey(1);//一级学科
            List<Specialty> speciaList = specialService.selectAllBySpecialty(specialty);
            jo.put("flag", true);
            jo.put("speciaList",speciaList);
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("speciaList", "");
            return jo.toJSONString();
        }
    }
    
    @RequestMapping("getSprofess")
    @ResponseBody
    public String getSprofess(String fProfess) {
        JSONObject jo = new JSONObject();
        Specialty specialty = new Specialty();
        try {
            specialty.setPid(fProfess);
            specialty.setStorey(2);//二级学科
            List<Specialty> speciaList = specialService.selectAllBySpecialty(specialty);
            jo.put("flag", true);
            jo.put("speciaList",speciaList);
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("speciaList", "");
            return jo.toJSONString();
        }
    }
    
    
    //公告是否暂停
    public JSONObject isStopNotice(String recruitId) {
        JSONObject jo = new JSONObject();
        Notice notice = noticeService.selectObjectByThemeId(recruitId);
        if (notice.getStop() == 0) {
            jo.put("flag", false);
            jo.put("title", notice.getStopTitle());
            jo.put("msg", notice.getStopContent());
        } else {
            jo.put("flag", true);
            jo.put("title", "");
            jo.put("msg", "公告已经开放！");
        }
        return jo;
    }
    
    
    @RequestMapping("applyProfileSave")
    @ResponseBody
    public String applyProfileSave(StudentApplyInfo studentApplyInfo, HttpServletRequest request,
            HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        if (StringUtil.isEmpty(studentApplyInfo.getEmployItemsId())) {
            jo.put("flag", false);
            jo.put("msg", "未找到招聘项目！");
            return jo.toJSONString();
        }
        
        if (StringUtil.isEmpty(studentApplyInfo.getStudentId())) {
            jo.put("flag", false);
            jo.put("msg", "未找到当前应聘的考生！");
            return jo.toJSONString();
        }
        
        if (StringUtil.isEmpty(studentApplyInfo.getApplyJobId())) {
            jo.put("flag", false);
            jo.put("msg", "未找到招聘岗位！");
            return jo.toJSONString();
        }
        
        if (StringUtil.isEmpty(studentApplyInfo.getApplyDepId())) {
            jo.put("flag", false);
            jo.put("msg", "未找到招聘部门！");
            return jo.toJSONString();
        }
        
        //判断公告是否暂停
        jo = isStopNotice(studentApplyInfo.getEmployItemsId());
        if (jo == null || !jo.getBooleanValue("flag")) {
            return jo.toJSONString();
        }
        
        //修改之前先要检查考生的申请状态  根据状态判断是否能够修改
        Integer checkCt = studentApplyInfoService.checkApplyStatus(studentApplyInfo);
        if (checkCt != null && checkCt > 0) {
            jo.put("flag", false);
            jo.put("msg", "您的申请处于待审状态，无法进行修改！");
            return jo.toJSONString();
        }
        
        try {
            //保存附件的相关信息
            attachementService.saveByStudentApply(studentApplyInfo);
            //修改更新
            studentApplyInfo.setApplyStatus("1");//未提交
            if (StringUtil.isNotEmpty(studentApplyInfo.getId())) {
                studentApplyInfo.setModifyUser(studentApplyInfo.getStudentId());
                studentApplyInfo.setMtime(new Date());
                studentApplyInfo.setCtime(null);
                studentApplyInfoService.updateStudentApplyInfoByField(studentApplyInfo);
            } else {
                studentApplyInfoService.deleteAndSave(studentApplyInfo);
            }
            jo.put("flag", true);
            jo.put("msg", "保存成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "保存出现异常！");
            return jo.toJSONString();
        }
    }
    
    @RequestMapping("deleteFam")
    @ResponseBody
    public String deleteFam(String id) {
        JSONObject jo = new JSONObject();
        try {
            if (StringUtil.isEmpty(id)) {
                jo.put("flag", true);
                jo.put("msg", "id为空！");
                return jo.toJSONString();
            }
            studentFamInfoService.deleteStudentFamInfoById(id);
            jo.put("flag", true);
            jo.put("msg", "删除成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "验证出现异常！");
            return jo.toJSONString();
        }
    }
    
    @RequestMapping("applyProfileSubmit")
    @ResponseBody
    public String applyProfileSubmit(StudentApplyInfo studentApplyInfo, HttpServletRequest request,
            HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        if (StringUtil.isEmpty(studentApplyInfo.getEmployItemsId())) {
            jo.put("flag", false);
            jo.put("msg", "未找到招聘项目！");
            return jo.toJSONString();
        }
        
        if (StringUtil.isEmpty(studentApplyInfo.getStudentId())) {
            jo.put("flag", false);
            jo.put("msg", "未找到当前应聘的考生！");
            return jo.toJSONString();
        }
        
        if (StringUtil.isEmpty(studentApplyInfo.getApplyJobId())) {
            jo.put("flag", false);
            jo.put("msg", "未找到招聘岗位！");
            return jo.toJSONString();
        }
        
        if (StringUtil.isEmpty(studentApplyInfo.getApplyDepId())) {
            jo.put("flag", false);
            jo.put("msg", "未找到招聘部门！");
            return jo.toJSONString();
        }
        
        //判断公告是否暂停
        jo = isStopNotice(studentApplyInfo.getEmployItemsId());
        if (jo == null || !jo.getBooleanValue("flag")) {
            return jo.toJSONString();
        }
        
        //修改之前先要检查考生的申请状态  根据状态判断是否能够修改
        Integer checkCt = studentApplyInfoService.checkApplyStatus(studentApplyInfo);
        if (checkCt != null && checkCt > 0) {
            jo.put("flag", false);
            jo.put("msg", "您的申请处于待审状态，无法进行修改！");
            return jo.toJSONString();
        }
        
        try {
            //保存附件的相关信息
            attachementService.saveByStudentApply(studentApplyInfo);
            
            StudentEduInfo sei = new StudentEduInfo();
            sei.setStudentId(studentApplyInfo.getStudentId());
            sei.setEmployItemsId(studentApplyInfo.getEmployItemsId());
            List<StudentEduInfo> eduInfoList = studentEduInfoService.selectAllByStudentEduInfo(sei);
            
            //学历信息未找到，送到学校审
            if (eduInfoList == null || eduInfoList.size() == 0) {
                studentApplyInfo.setApplyStatus("3");
            } else {
                for (StudentEduInfo se : eduInfoList) {
                    if (se.getIsSimilarTerm().equals("1")) {
                        //教育局资料审核
                        studentApplyInfo.setApplyStatus("2");
                        break;
                    } else {
                        studentApplyInfo.setApplyStatus("3");
                    }
                }
            }
            
            //修改更新
            if (StringUtil.isNotEmpty(studentApplyInfo.getId())) {
                studentApplyInfo.setModifyUser(studentApplyInfo.getStudentId());
                studentApplyInfo.setMtime(new Date());
                studentApplyInfo.setCtime(null);
                studentApplyInfo.setSubmitTime(new Date());
                studentApplyInfoService.updateStudentApplyInfoByField(studentApplyInfo);
            } else {
                studentApplyInfo.setSubmitTime(new Date());
                studentApplyInfoService.deleteAndSave(studentApplyInfo);
            }
            jo.put("flag", true);
            jo.put("msg", "提交成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "提交出现异常！");
            return jo.toJSONString();
        }
    }
    
    @RequestMapping("/timingexcel")
    public String downLoadTimingexcel(){
    	return "redirect:"+FtpUploadUtil.getFileServer()+"doc/%E4%B8%9C%E8%8E%9E%E5%B8%82%E6%95%99%E8%82%B2%E5%B1%80%E5%85%AC%E5%BC%80%E6%8B%9B%E8%81%982017%E5%B9%B4%E5%85%AC%E5%8A%9E%E6%99%AE%E9%80%9A%E4%B8%AD%E5%B0%8F%E5%AD%A6%EF%BC%88%E9%9D%A2%E5%90%91%E6%AF%95%E4%B8%9A%E7%94%9F%EF%BC%89%E5%92%8C%E4%B8%AD%E8%81%8C%E4%B8%93%E4%B8%9A%E8%AF%BE%E6%95%99%E5%B8%88%E6%97%B6%E9%97%B4%E5%AE%89%E6%8E%92%E8%A1%A8%20(2016.11.9).xlsx";
    }
    
    //重置密码
  	@RequestMapping("/resetPasswd")
  	@ResponseBody
  	public Result resetPasswd(String id){
  		Result r = new Result() ;
  		String newPasswd = RandomPasswd.getStringRandom(6) ;
  		try {
  			studentInfoService.resetPasswd(id,newPasswd,MD5Utils.md5Encrypt(newPasswd)) ;
  			r.setSuccess(true);
  			r.setMsg(newPasswd);
  			return r ;
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  			r.setMsg("重置密码失败，请重试！");
  			r.setSuccess(false);
  			return r ;
  		}
  	}

  	/**
     * 招聘人员审核中的已审核列表查询
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectStudentInfo",method= RequestMethod.POST)
    public String selectStudentInfo(Page page) {
        Map<String, Object> condition =page.getCondition();
        page = studentInfoService.selectStudentInfo(page,condition);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage()) ;
        return JSONObject.toJSON(jsonMap).toString();
    }
    
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
    

}
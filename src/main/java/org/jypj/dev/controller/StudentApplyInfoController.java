package org.jypj.dev.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.AuditReason;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.ExamLectureGroup;
import org.jypj.dev.entity.Grade;
import org.jypj.dev.entity.Information;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.PositionDomain;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.ScoreEnterPhysical;
import org.jypj.dev.entity.ScoreEnterTrial;
import org.jypj.dev.entity.ScoreGradePhysical;
import org.jypj.dev.entity.ScoreGradeStudy;
import org.jypj.dev.entity.ScoreGradeTrial;
import org.jypj.dev.entity.ScoreGradeWriten;
import org.jypj.dev.entity.SecurityQueInfo;
import org.jypj.dev.entity.Specialty;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentEduInfo;
import org.jypj.dev.entity.StudentFamInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.intercept.SessionListener;
import org.jypj.dev.service.ActionNoticeService;
import org.jypj.dev.service.AttachementService;
import org.jypj.dev.service.AuditReasonService;
import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.service.ExamLectureGroupService;
import org.jypj.dev.service.GradeService;
import org.jypj.dev.service.InformationService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PositionDomainService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.ScoreEnterPhysicalService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.ScoreGradePhysicalService;
import org.jypj.dev.service.ScoreGradeStudyService;
import org.jypj.dev.service.ScoreGradeTrialService;
import org.jypj.dev.service.ScoreGradeWritenService;
import org.jypj.dev.service.SecurityQueInfoService;
import org.jypj.dev.service.SpecialtyService;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.service.StudentEduInfoService;
import org.jypj.dev.service.StudentFamInfoService;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.MD5Utils;
import org.jypj.dev.util.StringUtil;
import org.jypj.dev.util.ValidateCode;
import org.jypj.dev.vo.InterviewNoticeVo;
import org.jypj.dev.vo.TalkNoticeVo;
import org.jypj.dev.vo.WrittenNoticeVo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * StudentApplyInfo控制器 考生报考信息表
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/dg/studentApplyInfo")
public class StudentApplyInfoController {
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
    
    @Resource
    private ScoreGradeWritenService scoreGradeWrittenService;
    
    @Resource
    private ScoreGradeTrialService scoreGradeTrialService;
    
    @Resource
    private ScoreEnterTrialService scoreEnterTrialService;
    
    @Resource
    private ScoreGradePhysicalService scoreGradePhysicalService;
    
    @Resource
    private ScoreGradeStudyService scoreGradeStudyService;
    
    @Resource
    private ScoreEnterPhysicalService scoreEnterPhysicalService;
    
    @Resource
    private ExamLectureGroupService examLectureGroupService;
    

    @RequestMapping(value = "/getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage imageBuf = ValidateCode.getCode(request);
        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(imageBuf, "jpeg", sos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "index")
    public String index(Model model) {
        // 获取招聘公告
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isPublish", "1");
        List<Notice> notices = noticeService.selectEffectNotice(map);
        Theme theme = new Theme();
        List<Theme> themeList = new ArrayList<Theme>();
        for (Notice n : notices) {
            theme = themeService.selectThemeById(n.getThemeId());
            if (theme != null) {
                if (theme.getTheme().length() > 50) {
                    theme.setNoticeSubstr(theme.getTheme().substring(0, 30) + "...");
                } else {
                    theme.setNoticeSubstr(theme.getTheme());
                }
                themeList.add(theme);
            }
        }

        model.addAttribute("themeList", themeList);
        return "/student/index.vm";
    }

    @RequestMapping(value = "login")
    public String login(String recruitItemId, Model model, HttpServletRequest request) {
        if (StringUtil.isEmpty(recruitItemId)) {
            model.addAttribute("msg", "招聘主题id为空！");
            return "/student/index.vm";
        }
        request.getSession().setAttribute("recruitId", recruitItemId);
        // 招聘主题名称
        Theme theme = themeService.selectThemeById(recruitItemId);
        request.getSession().setAttribute("recruitName", theme.getTheme());
        Notice notice = noticeService.selectObjectByThemeId(theme.getId());
        model.addAttribute("notice", notice);
        return "/student/login.vm";
    }

    @RequestMapping("verifySignUp")
    @ResponseBody
    public String verifySignUp(String recId, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("isPublish", "1");
            map.put("themeId", recId);
            List<Notice> notices = noticeService.selectEffectNotice(map);
            if (notices != null && notices.size() > 0) {
                jo.put("flag", true);
                jo.put("title", "");
                jo.put("msg", "恭喜，可以报名！");
               /* Notice notice = notices.get(0);
                if (notice.getStop() == 0) {
                    jo.put("flag", false);
                    jo.put("title", notice.getStopTitle());
                    jo.put("msg", notice.getStopContent());
                } else {
                    jo.put("flag", true);
                    jo.put("title", "");
                    jo.put("msg", "恭喜，可以报名！");
                }*/
            } else {
                jo.put("flag", false);
                jo.put("title", "");
                jo.put("msg", "没有找到公告！");
            }
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("title", "");
            jo.put("msg", "验证出现异常！");
            return jo.toJSONString();
        }
    }

    // 是否到了注册时间
    public boolean isRegisterTime(HttpServletRequest request) {
        Date now = new Date();
        String recruitId = request.getSession().getAttribute("recruitId").toString();
        Notice notice = noticeService.selectObjectByThemeId(recruitId);
        if (notice == null) {
            return false;
        }
        Date start = notice.getRegisterStart();
        Date end = notice.getRegisterEnd();
        if (now.getTime() < start.getTime() || now.getTime() > end.getTime()) {
            return false;
        }
        return isOverRegisterTime(request);
    }
    
    //验证招聘流程的步骤，是否已经过了注册环节
    public boolean isOverRegister(HttpServletRequest request) {
        String recruitId = request.getSession().getAttribute("recruitId").toString();
        Theme theme = themeService.selectThemeById(recruitId);
        if (theme == null) {
            return false;
        }
        //过了注册环节
        if (theme.getStep() > 1) {
            return false;
        }
        return true;
    }

    //验证招聘流程的步骤，是否已经过了注册时间
    public boolean isOverRegisterTime(HttpServletRequest request) {
        String recruitId = request.getSession().getAttribute("recruitId").toString();
        Notice notice = noticeService.selectObjectByThemeId(recruitId);
        if(notice!=null&&new Date().before(notice.getRegisterEnd())){
        	return true;
        }else{
            return false;
        }
    }
    
    //公告是否暂停
    public JSONObject isStopNotice(HttpServletRequest request) {
        String recruitId = request.getSession().getAttribute("recruitId").toString();
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

    
    
    @RequestMapping("verifyRegister")
    @ResponseBody
    public String verifyRegister(HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            if (!isRegisterTime(request)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String recruitId = request.getSession().getAttribute("recruitId").toString();
                Notice notice = noticeService.selectObjectByThemeId(recruitId);
                Date start = notice.getRegisterStart();
                Date end = notice.getRegisterEnd();
                jo.put("flag", false);
                jo.put("msg", "不在注册报名日期内，无法报名！报名开始日期：" + sdf.format(start) + "，报名结束日期："
                        + sdf.format(end));
                return jo.toJSONString();
            }
            
            jo = isStopNotice(request);
            //公告已经暂停
            if (!jo.getBooleanValue("flag")) {
                return jo.toJSONString();
            }
            
            /*if(!isOverRegister(request)) {
                jo.put("flag", false);
                jo.put("msg", "整个招聘流程已经过了注册环节，不能进行注册！");
                return jo.toJSONString();
            }*/
            
            if(!isOverRegisterTime(request)){
                jo.put("flag", false);
                jo.put("msg", "已经过了注册时间，不能进行报名！");
                return jo.toJSONString();
            }
            jo.put("flag", true);
            jo.put("msg", "恭喜，可以报名！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("title", "");
            jo.put("msg", "验证出现异常！");
            return jo.toJSONString();
        }
    }


    @RequestMapping("editProfile")
    @ResponseBody
    public String editProfile(StudentInfo studentInfo, HttpServletRequest request,
            HttpServletResponse response) {
    	if(StringUtil.isNotEmpty(studentInfo.getIdentityCard())){
        	studentInfo.setIdentityCard(studentInfo.getIdentityCard().toUpperCase());
    	}
        String realPath = request.getSession().getServletContext().getRealPath("/") + "upload";
        JSONObject jo = new JSONObject();
        String studentId = "";
        studentId = studentInfo.getId();
        if (StringUtil.isEmpty(studentId)) {
            jo.put("flag", false);
            jo.put("msg", "考生Id为空，无法进行更新！");
            return jo.toJSONString();
        }
        //判断公告是否暂停
        jo = isStopNotice(request);
        if (jo == null || !jo.getBooleanValue("flag")) {
            return jo.toJSONString();
        }
        StudentApplyInfo sai = new StudentApplyInfo();
        sai.setStudentId(studentId);
        sai.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        //修改之前先要检查考生的申请状态  根据状态判断是否能够修改
        Integer checkCt = studentApplyInfoService.checkApplyStatus(sai);
        if (checkCt != null && checkCt > 0) {
            jo.put("flag", false);
            jo.put("msg", "您的申请处于待审状态，无法进行修改！");
            return jo.toJSONString();
        }
        
        // 限制文件类型
        List<String> fileExtensions = new ArrayList<String>() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            {
                add("gif");
                add("png");
                add("jpg");
                add("jpeg");
            }
        };

        MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile originalFile =
                (CommonsMultipartFile) multiPartRequest.getFile("avatar");
        if (StringUtil.isNotEmpty(studentInfo.getPhotoAttId())) {
            if (originalFile != null && !originalFile.isEmpty()) {
                String fileName = originalFile.getOriginalFilename();
                if (originalFile.getSize() > 1024 * 300) {
                    jo.put("flag", false);
                    jo.put("msg", "上传文件大小不能超过300k!");
                    return jo.toJSONString();
                }
                
                //增加文件名长度限制
                if(fileName.length()>20){
                    jo.put("flag", false);
                    jo.put("msg", "文件名最大长度为20,请更改文件名后上传！");
                    return jo.toJSONString();
                }

                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (!fileExtensions.contains(fileExt)) {
                    jo.put("flag", false);
                    jo.put("msg", "上传文件大小格式 不对，只能上传png,jpeg,jpg,gif");
                    return jo.toJSONString();
                }

                String filePath = "";
                filePath = studentId + "." + fileExt;
                realPath += File.separator + filePath;
                try {
                    originalFile.getFileItem().write(new File(realPath));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 从本地写入文件服务器,以UID前两位作为一层目录
                FtpUploadUtil.upload(realPath, "/" + studentId.substring(0, 2) + "/",
                        studentId + "." + fileExt);
                Attachement atta =
                        attachementService.selectAttachementById(studentInfo.getPhotoAttId());
                if (atta != null) {
                    atta.setMtime(new Date());
                    atta.setModifyUser(studentId);
                    atta.setPath(FtpUploadUtil.getFileServer() + studentId.substring(0, 2) + "/"
                            + studentId + "." + fileExt);
                    atta.setRealName(fileName);
                    attachementService.updateAttachement(atta);
                } else {
                    atta = new Attachement();
                    String attId = UUID.randomUUID().toString().replaceAll("-", "");
                    atta.setId(attId);
                    atta.setPath(FtpUploadUtil.getFileServer() + studentId.substring(0, 2) + "/"
                            + studentId + "." + fileExt);
                    atta.setRealName(fileName);
                    atta.setFileType(fileExt);
                    atta.setUploadDate(new Date());
                    atta.setUploadObject(studentInfo.getStudentType());
                    attachementService.saveAttachement(atta);
                    // 更新student
                    studentInfo.setPhotoAttId(attId);
                    attachementService.updateAttachement(atta);
                }
            } else if (originalFile != null && StringUtils.isNotEmpty(originalFile.getOriginalFilename())){
                jo.put("flag", false);
                jo.put("msg", "请上传照片！");
                return jo.toJSONString();
            }
        } 

        try {
            List<StudentFamInfo> studentFamInfoList = studentInfo.getStudentFamInfoList();
            studentFamInfoService.deleteAndSave(studentFamInfoList, studentId,
                    request.getSession().getAttribute("recruitId").toString());

            List<StudentEduInfo> studentEduInfoList = studentInfo.getStudentEduInfoList();
            try {
                studentEduInfoService.deleteAndSave(studentEduInfoList, studentId,
                        request.getSession().getAttribute("recruitId").toString());
            } catch (RuntimeException e) {
                jo.put("flag", false);
                jo.put("msg", "更新学历出现异常！");
                return jo.toJSONString();
            }
            StringBuilder sb = new StringBuilder("");
            if (studentInfo.getHonorCodes() != null && studentInfo.getHonorCodes().length != 0) {
                for (String s : studentInfo.getHonorCodes()) {
                    sb.append(s).append(",");
                }
                studentInfo.setHonorCode(sb.substring(0, sb.length() - 1));
            } else if (studentInfo.getStudentType().equals("2")) {
                jo.put("flag", false);
                jo.put("msg", "请选择荣誉称号！");
                return jo.toJSONString();
            }

            if (studentInfo.getExpertiseCode().equals("0")) {
                studentInfo.setTeachingSubject("无");
            }

            // 保存用户基本信息
            studentInfo.setCtime(null);
            studentInfo.setMtime(new Date());
            studentInfo.setModifyUser(studentId);
            studentInfoService.updateStudentInfoByField(studentInfo);
            jo.put("flag", true);
            jo.put("msg", "更新成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "更新出现异常！");
            return jo.toJSONString();
        }
    }

    @RequestMapping("saveProfile")
    @ResponseBody
    public String saveProfile(StudentInfo studentInfo, HttpServletRequest request,
            HttpServletResponse response) {
        String realPath = request.getSession().getServletContext().getRealPath("/") + "upload";
        JSONObject jo = new JSONObject();
        String studentId = "";
        String photoAttId = "";
        // 先判断身份证号是否已经存在于数据库,要根据招聘主题id
        if (StringUtil.isNotEmpty(studentInfo.getIdentityCard())) {
            studentInfo.setIdentityCard(studentInfo.getIdentityCard().toUpperCase());
        } else {
            jo.put("flag", false);
            jo.put("msg", "身份证号码不能为空!");
            return jo.toJSONString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("IdCard", studentInfo.getIdentityCard());
        map.put("recruitId", request.getSession().getAttribute("recruitId").toString());
        Integer isExistIdCard = studentInfoService.isExistIdCard(map);
        if (isExistIdCard > 0) {
            jo.put("flag", false);
            jo.put("msg", "身份证号码已经存在!");
            return jo.toJSONString();
        }

        // 限制文件类型
        List<String> fileExtensions = new ArrayList<String>() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            {
                add("gif");
                add("png");
                add("jpg");
                add("jpeg");
            }
        };

        MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile originalFile =
                (CommonsMultipartFile) multiPartRequest.getFile("avatar");
        if (originalFile != null && !originalFile.isEmpty()) {
            String fileName = originalFile.getOriginalFilename();
            if (originalFile.getSize() > 1024 * 300) {
                jo.put("flag", false);
                jo.put("msg", "上传文件大小不能超过300k!");
                return jo.toJSONString();
            }
            
            //增加文件名长度限制
            if(fileName.length()>20){
                jo.put("flag", false);
                jo.put("msg", "文件名最大长度为20,请更改文件名后上传！");
                return jo.toJSONString();
            }

            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!fileExtensions.contains(fileExt)) {
                jo.put("flag", false);
                jo.put("msg", "上传文件大小格式 不对，只能上传png,jpeg,jpg,gif");
                return jo.toJSONString();
            }

            String filePath = "";
            if (StringUtil.isNotEmpty(studentInfo.getId())) {
                studentId = studentInfo.getId();
            } else {
                studentId = UUID.randomUUID().toString().replaceAll("-", "");
            }
            filePath = studentId + "." + fileExt;
            realPath += File.separator + filePath;
            try {
                originalFile.getFileItem().write(new File(realPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 从本地写入文件服务器
            FtpUploadUtil.upload(realPath, "/" + studentId.substring(0, 2) + "/",
                    studentId + "." + fileExt);

            if (StringUtil.isNotEmpty(studentInfo.getPhotoAttId())) {
                Attachement atta =
                        attachementService.selectAttachementById(studentInfo.getPhotoAttId());
                if (atta != null) {
                    photoAttId = studentInfo.getPhotoAttId();
                    atta.setMtime(new Date());
                    atta.setModifyUser(studentId);
                    atta.setPath(FtpUploadUtil.getFileServer() + studentId.substring(0, 2) + "/"
                            + studentId + "." + fileExt);
                    atta.setRealName(fileName);
                    attachementService.updateAttachement(atta);
                } else {
                    jo.put("flag", false);
                    jo.put("msg", "学生信息中的附件在附件表中未找到！");
                    return jo.toJSONString();
                }
            } else {
                Attachement atta = new Attachement();
                photoAttId = UUID.randomUUID().toString().replaceAll("-", "");
                atta.setId(photoAttId);
                atta.setPath(FtpUploadUtil.getFileServer() + studentId.substring(0, 2) + "/"
                        + studentId + "." + fileExt);
                atta.setRealName(fileName);
                atta.setCtime(new Date());
                atta.setCreateUser(studentId);
                atta.setUploadObject(studentInfo.getStudentType());// 考生类型
                atta.setStudentId(studentId);
                atta.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
                atta.setFileType(fileExt);
                attachementService.saveAttachement(atta);
            }
        } else {
            jo.put("flag", false);
            jo.put("msg", "请上传照片！");
            return jo.toJSONString();
        }
        try {
            List<StudentFamInfo> studentFamInfoList = studentInfo.getStudentFamInfoList();
            // 保存家庭信息
            if (studentFamInfoList != null) {
                for (StudentFamInfo studentFamInfo : studentFamInfoList) {
                    studentFamInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    studentFamInfo.setStudentId(studentId);
                    studentFamInfo.setEmployItemsId(
                            request.getSession().getAttribute("recruitId").toString());
                    studentFamInfo.setCreateUser(studentId);
                    studentFamInfo.setCtime(new Date());
                    studentFamInfoService.saveStudentFamInfo(studentFamInfo);
                }
            }

            List<StudentEduInfo> studentEduInfoList = studentInfo.getStudentEduInfoList();
            // 保存学历信息
            for (StudentEduInfo studentEduInfo : studentEduInfoList) {
                // 由于前端不好获取code，故直接存取名称
                if (StringUtil.isEmpty(studentEduInfo.getEduSchoolName())) {
                    continue;
                }
                studentEduInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                studentEduInfo.setStudentId(studentId);
                studentEduInfo.setEmployItemsId(
                        request.getSession().getAttribute("recruitId").toString());
                studentEduInfo.setCreateUser(studentId);
                studentEduInfo.setCtime(new Date());
                studentEduInfoService.saveStudentEduInfo(studentEduInfo);
            }

            // 保存用户基本信息
            studentInfo.setId(studentId);
            studentInfo.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
            studentInfo.setPhotoAttId(photoAttId);
            studentInfo.setPassword(MD5Utils.md5Encrypt(studentInfo.getPassword()));
            studentInfo.setFreeStudent("0");// 东莞免费师范生
            studentInfo.setProfessionalCourse("0");// 是否报考中职专业课岗位
            
            // 社会人士，增加荣誉称号
            StringBuilder sb = new StringBuilder("");
            if (studentInfo.getHonorCodes() != null && studentInfo.getHonorCodes().length != 0) {
                for (String s : studentInfo.getHonorCodes()) {
                    sb.append(s).append(",");
                }
                studentInfo.setHonorCode(sb.substring(0, sb.length() - 1));
            } else if (studentInfo.getStudentType().equals("2")) {
                jo.put("flag", false);
                jo.put("msg", "请选择荣誉称号！");
                return jo.toJSONString();
            }

            if (studentInfo.getExpertiseCode().equals("0")) {
                studentInfo.setTeachingSubject("无");
            }

            /*
             * StringBuilder sb = getHonorCode(studentInfo , request); if
             * (StringUtil.isNotEmpty(sb.toString())) { studentInfo.setHonorCode(sb.substring(0,
             * sb.length() - 1)); }
             */
            studentInfo.setCreateUser(studentId);
            studentInfoService.saveStudentInfo(studentInfo);
            request.getSession().setAttribute("sid", studentInfo.getId());
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



    public StringBuilder getHonorCode(StudentInfo studentInfo, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        if (studentInfo.getStudentType().equals("2")) {
            if (StringUtil.isNotEmpty(request.getParameter("rych0"))) {
                sb.append(request.getParameter("rych0")).append(",");
            }
            if (StringUtil.isNotEmpty(request.getParameter("rych1"))) {
                sb.append(request.getParameter("rych1")).append(",");
            }
            if (StringUtil.isNotEmpty(request.getParameter("rych2"))) {
                sb.append(request.getParameter("rych2")).append(",");
            }
            if (StringUtil.isNotEmpty(request.getParameter("rych3"))) {
                sb.append(request.getParameter("rych3")).append(",");
            }
            if (StringUtil.isNotEmpty(request.getParameter("rych4"))) {
                sb.append(request.getParameter("rych4")).append(",");
            }
            if (StringUtil.isNotEmpty(request.getParameter("rych5"))) {
                sb.append(request.getParameter("rych5")).append(",");
            }
        }
        return sb;
    }


    @RequestMapping(value = "choosePosition")
    public String choosePosition(Model model, HttpServletRequest request) {
        String sid = request.getSession().getAttribute("sid").toString();
        StudentInfo sInfo = studentInfoService.selectStudentInfoById(sid);
        model.addAttribute("sInfo", sInfo);
        // 根据主题获取岗位列表
        List<Position> positions = positionService
                .selectPostByRecruitId(request.getSession().getAttribute("recruitId").toString());
        List<Position> newPositions = new ArrayList<Position>();
        // 过滤掉重复的岗位
        List<String> positionIds = new ArrayList<String>();
        for (Position p : positions) {
            if (!positionIds.contains(p.getStationId())) {
                positionIds.add(p.getStationId());
                newPositions.add(p);
            }
        }

        model.addAttribute("positions", newPositions);
        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        model.addAttribute("recruitId", request.getSession().getAttribute("recruitId").toString());
        return "/student/choosePosition.vm";
    }


    @RequestMapping(value = "admissionTicket")
    public String admissionTicket(HttpServletRequest request, Model model) {
        String sid = request.getSession().getAttribute("sid").toString();
        StudentInfo sInfo = studentInfoService.selectStudentInfoById(sid);
        StudentApplyInfo sApplyInfo = new StudentApplyInfo();
        sApplyInfo.setStudentId(sid);
        // 增加招聘主题id
        sApplyInfo.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        sApplyInfo = studentApplyInfoService.selectObjectByStudentApplyInfo(sApplyInfo);

        if (sInfo == null || sApplyInfo == null) {
            model.addAttribute("applyStatus", "0");
        } else {
            Postset ps = postsetService.selectPostsetById(sApplyInfo.getApplyJobId());
            sApplyInfo.setApplyJobCode(ps.getPostCode());
            sApplyInfo.setApplyJobName(ps.getPostName());
            model.addAttribute("applyStatus",
                    sApplyInfo.getApplyStatus() == null ? "0" : sApplyInfo.getApplyStatus());
        }

        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        
        // 图片附件
        Attachement att = attachementService.selectAttachementById(sInfo.getPhotoAttId());
        model.addAttribute("sInfo", sInfo);
        model.addAttribute("sApplyInfo", sApplyInfo);
        model.addAttribute("att", att);
        model.addAttribute("photo", request.getSession().getAttribute("photo"));
        model.addAttribute("studentName", request.getSession().getAttribute("studentName"));
        return "/student/admissionTicket.vm";
    }

    @RequestMapping(value = "applyPosition")
    public String applyPosition(HttpServletRequest request, Model model) {
        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        String sid = request.getSession().getAttribute("sid").toString();
        String applyStatus = "";
        String noticeMsg = "";
        // 考生基本信息
        StudentInfo si = studentInfoService.selectStudentInfoById(sid);
        // 考生申报信息
        StudentApplyInfo sai = new StudentApplyInfo();
        sai.setStudentId(sid);
        sai.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        sai = studentApplyInfoService.selectObjectByStudentApplyInfo(sai);
        String reason = "";
        // 考生只填写了基本资料，未进行报考
        if (sai == null) {
            applyStatus = "0";
        } else {
            // 岗位信息
            Position position = new Position();
            position.setSchoolId(sai.getApplyDepId());// 单位
            position.setProjectId(request.getSession().getAttribute("recruitId").toString());// 主题
            position.setStationId(sai.getApplyJobId());
            position.setStatus("1");
            position = positionService.selectObjectByPosition(position);

            if (position != null) {
                // 岗位限制专业信息
                PositionDomain positionDomain = new PositionDomain();
                positionDomain.setSchoolId(sai.getApplyDepId());
                positionDomain.setPositionId(sai.getApplyJobId());
                positionDomain.setPlanApplyId(position.getPlanApplyId());
                List<PositionDomain> positionDomains =
                        positionDomainService.selectAllByPositionDomain(positionDomain);
                if (positionDomains != null && positionDomains.size() > 0) {
                    position.setPositionDomains(positionDomains);
                }

                // 生成专业限制条件
                position.setPosCondition(position.generateCondition());
                Postset postset = postsetService.selectPostsetById(position.getStationId());
                // 简介
                position.setPostSummary(postset.getSummary());
                position.setPostName(postset.getPostName());
                // 学科
                Dictionary dic = new Dictionary();
                dic.setCode("xklb");
                dic.setValue(postset.getSubject());
                dic = dictionaryService.selectObjectByDictionary(dic);
                // 学科名称
                position.setSubjectText(dic.getText());
                // 单位名称
                dic = new Dictionary();
                dic.setId(sai.getApplyDepId());;
                dic = dictionaryService.selectObjectByDictionary(dic);
                position.setSchoolName(dic.getText());

                // 获取考生的个人附件资料
                // 身份证
                if (StringUtil.isNotEmpty(sai.getIdcardAttId())) {
                    Attachement attIdCard =
                            attachementService.selectAttachementById(sai.getIdcardAttId());
                    model.addAttribute("attIdCard", attIdCard);
                }

                // 教师资格证
                if (StringUtil.isNotEmpty(sai.getCerAchAttId())) {
                    Attachement attCerAchAtt =
                            attachementService.selectAttachementById(sai.getCerAchAttId());
                    attCerAchAtt.setIsImg(attCerAchAtt.isImgOrFile());
                    model.addAttribute("attCerAchAtt", attCerAchAtt);
                }

                // 毕业证书或者就业推荐表
                if (StringUtil.isNotEmpty(sai.getGraRecomAttId())) {
                    Attachement attGra =
                            attachementService.selectAttachementById(sai.getGraRecomAttId());
                    attGra.setIsImg(attGra.isImgOrFile());
                    model.addAttribute("attGra", attGra);
                }

                // 学位证书
                if (StringUtil.isNotEmpty(sai.getBacAttId())) {
                    Attachement attBac =
                            attachementService.selectAttachementById(sai.getBacAttId());
                    model.addAttribute("attBac", attBac);
                }

                // 学历鉴定
                if (StringUtil.isNotEmpty(sai.getAcaQuaAttId())) {
                    Attachement attAcaQua =
                            attachementService.selectAttachementById(sai.getAcaQuaAttId());
                    model.addAttribute("attAcaQua", attAcaQua);
                }

                // 计划生育证明
                if (StringUtil.isNotEmpty(sai.getFamPlanAttId())) {
                    Attachement attFam =
                            attachementService.selectAttachementById(sai.getFamPlanAttId());
                    model.addAttribute("attFam", attFam);
                }

                // 学位鉴定证明
                if (StringUtil.isNotEmpty(sai.getBacQuaAttId())) {
                    Attachement attBacQua =
                            attachementService.selectAttachementById(sai.getBacQuaAttId());
                    model.addAttribute("attBacQua", attBacQua);
                }

                // 个人学习情况
                if (StringUtil.isNotEmpty(sai.getStudyWorkAttId())) {
                    Attachement attStudyWork =
                            attachementService.selectAttachementById(sai.getStudyWorkAttId());
                    attStudyWork.setIsImg(attStudyWork.isImgOrFile());
                    model.addAttribute("attStudyWork", attStudyWork);
                }

                // 教学视频
                if (StringUtil.isNotEmpty(sai.getTeaVideoAttId())) {
                    Attachement attTeaVideo =
                            attachementService.selectAttachementById(sai.getTeaVideoAttId());
                    model.addAttribute("attTeaVideo", attTeaVideo);
                }

                // 暂缓就业
                if (StringUtil.isNotEmpty(sai.getSuspendEmpAttId())) {
                    Attachement attSuspendEm =
                            attachementService.selectAttachementById(sai.getSuspendEmpAttId());
                    model.addAttribute("attSuspendEm", attSuspendEm);
                }

                // 出国留学
                if (StringUtil.isNotEmpty(sai.getAbroadStudyAttId())) {
                    Attachement attAbroad =
                            attachementService.selectAttachementById(sai.getAbroadStudyAttId());
                    model.addAttribute("attAbroad", attAbroad);
                }

                // 有相近专业的，需要上传毕业成绩单
                if (StringUtil.isNotEmpty(sai.getTranscriptAttId())) {
                    Attachement attTranscript =
                            attachementService.selectAttachementById(sai.getTranscriptAttId());
                    model.addAttribute("attTranscript", attTranscript);
                }

                // 教育学成绩
                if (StringUtil.isNotEmpty(sai.getCerPedAttId())) {
                    Attachement attCerped =
                            attachementService.selectAttachementById(sai.getCerPedAttId());
                    model.addAttribute("attCerped", attCerped);
                }

                // 心理学成绩
                if (StringUtil.isNotEmpty(sai.getCerPsyAttId())) {
                    Attachement attCerpsy =
                            attachementService.selectAttachementById(sai.getCerPsyAttId());
                    model.addAttribute("attCerpsy", attCerpsy);
                }

                // 普通话成绩
                if (StringUtil.isNotEmpty(sai.getCerManAttId())) {
                    Attachement attCerMan =
                            attachementService.selectAttachementById(sai.getCerManAttId());
                    model.addAttribute("attCerMan", attCerMan);
                }

                // 教育实习证明
                if (StringUtil.isNotEmpty(sai.getCerShipAttId())) {
                    Attachement attCerShip =
                            attachementService.selectAttachementById(sai.getCerShipAttId());
                    model.addAttribute("attCerShip", attCerShip);
                }

                // 教育能力测试
                if (StringUtil.isNotEmpty(sai.getCerAbiAttId())) {
                    Attachement attCerAbi =
                            attachementService.selectAttachementById(sai.getCerAbiAttId());
                    model.addAttribute("attCerAbi", attCerAbi);
                }

                model.addAttribute("sai", sai);
                model.addAttribute("position", position);

                applyStatus = sai.getApplyStatus() == null ? "0" : sai.getApplyStatus();
                
            }
            
            if (StringUtil.isNotEmpty(sai.getApplyAuditCode())) {
                AuditReason auditReason =
                        auditReasonService.selectAuditReasonById(sai.getApplyAuditCode());
                reason = auditReason == null ? "" : "<h3 class='mt10'>理由：</h3><h3 class='break_word mt10'><span style='font-weight: 500;'>"  + auditReason.getReason() +"</span></h3>";
            }
        }
        

        switch (applyStatus) {
        case "0":
            noticeMsg = "<h3>温馨提示：<span style='font-weight: 500;'>您还未提交岗位申请。</span></h3>";
            break;
        case "1":
            noticeMsg = "<h3>温馨提示：<span style='font-weight: 500;'>您申请的还未提交，可以修改。</span></h3>";
            break;
        case "2":
            noticeMsg = "<h3>温馨提示：<span style='font-weight: 500;'>您的申请信息包括相近专业，教育局正在审核中...</span></h3>";
            break;
        case "3":
            noticeMsg = "<h3>温馨提示：<span style='font-weight: 500;'>您的申请已经提交，学校正在审核中...</span></h3>";
            break;
        case "4":
            noticeMsg = "<h3>温馨提示：<span style='font-weight: 500;'>您基础资料中的照片不符合要求，请重新上传，并提交申请。</span></h3>";
            noticeMsg += reason;
            break;
        case "5":
            noticeMsg = "<h3>温馨提示：<span style='font-weight: 500;'>您的申请审核不通过。</span></h3>";
            noticeMsg += reason;
            break;
    }


        // 审核状态
        model.addAttribute("applyStatus", applyStatus);
        model.addAttribute("noticeMsg", noticeMsg);

        StudentEduInfo sei = new StudentEduInfo();
        sei.setStudentId(sid);
        sei.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        List<StudentEduInfo> seiList = studentEduInfoService.selectAllByStudentEduInfo(sei);
        int count = 0;
        for (StudentEduInfo s : seiList) {
            if (s.getIsSimilarTerm() != null && s.getIsSimilarTerm().equals("1")) {
                count++;
            }
        }
        si.setHasSimilar(count > 0 ? "1" : "0");// 是否有相近专业

        model.addAttribute("si", si);
        model.addAttribute("photo", request.getSession().getAttribute("photo"));
        model.addAttribute("studentName", request.getSession().getAttribute("studentName"));
        return "/student/applyPosition.vm";
    }

    @RequestMapping(value = "profileConfirm")
    public String profileConfirm(HttpServletRequest request, String type, Model model) {
        StudentApplyInfo sai = new StudentApplyInfo();
        sai.setStudentId(request.getSession().getAttribute("sid").toString());
        sai.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        List<StudentApplyInfo> saiList = studentApplyInfoService.selectAllByStudentApplyInfo(sai);

        StudentInfo si = studentInfoService
                .selectStudentInfoById(request.getSession().getAttribute("sid").toString());
        if (si == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }

        model.addAttribute("si", si);
        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);

        List<Information> infoList = new ArrayList<Information>();
        for (StudentApplyInfo s : saiList) {
            // 拉取学校端的资料确认数据
            Information info = new Information();
            info.setType(type);
            info.setStatus("1");
            info.setProjectId(s.getEmployItemsId());
            if ("1".equals(type)) {
                info.setPositionId(s.getApplyJobId());
            }
            info.setSchoolId(s.getApplyDepId());
            info = informationService.selectObjectByInformation(info);
            Dictionary dicSchool = dictionaryService.selectDictionaryById(s.getApplyDepId());
            // 招聘名称
            info.setTheme(theme.getTheme());
            // 学校名称
            info.setSchoolName(dicSchool == null ? "" : dicSchool.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            info.setStartDateStr(
                    sdf.format(info.getStartDate() == null ? now : info.getStartDate()));
            info.setEndDateStr(sdf.format(info.getEndDate() == null ? now : info.getEndDate()));
            // 岗位名称
            Postset ps = postsetService.selectPostsetById(s.getApplyJobId());
            info.setPostName(ps == null ? "" : ps.getPostName());
            infoList.add(info);
        }

        model.addAttribute("infoList", infoList);
        model.addAttribute("photo", request.getSession().getAttribute("photo"));
        model.addAttribute("studentName", request.getSession().getAttribute("studentName"));
        if (type.equals("1")) {
            return "/student/profileInterview.vm";
        } else {
            return "/student/profileConfirm.vm";
        }

    }

    @RequestMapping(value = "recruitProgress")
    public String recruitProgress(HttpServletRequest request, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder noticeMsg = new StringBuilder();
        String applyStatus = "";
        StudentInfo si = studentInfoService
                .selectStudentInfoById(request.getSession().getAttribute("sid").toString());
        if (si == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        model.addAttribute("si", si);
        model.addAttribute("photo", request.getSession().getAttribute("photo"));
        model.addAttribute("studentName", request.getSession().getAttribute("studentName"));

        // 查询当前主题的总进度
        Notice notice = new Notice();
        notice.setIsPublish(1);
        notice.setThemeId(request.getSession().getAttribute("recruitId").toString());
        notice = noticeService.selectObjectByNotice(notice);

        if (notice.getRegisterStart() != null && notice.getRegisterEnd() != null) {
            notice.setRegisterTimeStr(sdf.format(notice.getRegisterStart()) + "——"
                    + sdf.format(notice.getRegisterEnd()));
        }

        if (notice.getInterviewStart() != null && notice.getInterviewEnd() != null) {
            notice.setInterviewTimeStr(sdf.format(notice.getInterviewStart()) + "——"
                    + sdf.format(notice.getInterviewEnd()));
        }

        if (notice.getWrittenStart() != null && notice.getWrittenEnd() != null) {
            notice.setWrittenTimeStr(sdf.format(notice.getWrittenStart()) + "——"
                    + sdf.format(notice.getWrittenEnd()));
        }

        if (notice.getLectureStart() != null && notice.getLectureEnd() != null) {
            notice.setTalkTimeStr(sdf.format(notice.getLectureStart()) + "——"
                    + sdf.format(notice.getLectureEnd()));
        }

        if (notice.getBodyexamStart() != null && notice.getBodyexamEnd() != null) {
            notice.setBodyexamTimeStr(sdf.format(notice.getBodyexamStart()) + "——"
                    + sdf.format(notice.getBodyexamEnd()));
        }

        if (notice.getLookStart() != null && notice.getLookEnd() != null) {
            notice.setLookTimeStr(
                    sdf.format(notice.getLookStart()) + "——" + sdf.format(notice.getLookEnd()));
        }

        if (notice.getShowStart() != null && notice.getShowEnd() != null) {
            notice.setShowTimeStr(
                    sdf.format(notice.getShowStart()) + "——" + sdf.format(notice.getShowEnd()));
        }
        model.addAttribute("notice", notice);

        // 当前考生的简历状态
        StudentApplyInfo sai = new StudentApplyInfo();
        sai.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        sai.setStudentId(request.getSession().getAttribute("sid").toString());
        sai = studentApplyInfoService.selectObjectByStudentApplyInfo(sai);
        applyStatus = sai == null ? "0" : sai.getApplyStatus();
        
        
        model.addAttribute("applyStatus", applyStatus);
        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        
        ScoreGradePhysical sgp = new ScoreGradePhysical();
        ScoreGradeStudy sgs = new ScoreGradeStudy();
        switch (applyStatus) {
            case "0":
                noticeMsg.append("您还未提交岗位申请。");
                model.addAttribute("sai", sai);
                model.addAttribute("noticeMsg", noticeMsg);
                return "/student/recruitProgress.vm";
            case "1":
                noticeMsg.append("您的申请还未提交，可以修改。");
                break;
            case "2":
                noticeMsg.append("您的申请信息包括相近专业，教育局正在审核中...");
                break;
            case "3":
                noticeMsg.append("您的申请已经提交，学校正在审核中...");
                break;
            case "4":
                noticeMsg.append("您的图像照片不符合要求，被退回，请修改基础资料。");
                break;
            case "5":
                noticeMsg.append("您的申请审核不通过。");
                if (StringUtil.isNotEmpty(sai.getApplyAuditCode())) {
                    AuditReason auditReason =
                            auditReasonService.selectAuditReasonById(sai.getApplyAuditCode());
                    noticeMsg.append("理由：").append(auditReason == null ? "" : auditReason.getReason());
                }
                break;
            case "6":
                noticeMsg.append("您的报考资格初审已通过，招聘单位正在审议进入面试名单，敬请在规定时间内留意面试名单发布");
                break;
            case "7":
                ScoreEnterTrial setl = new ScoreEnterTrial();
                setl.setItemsId(sai.getEmployItemsId());
                setl.setStudentId(sai.getStudentId());
                setl.setSchoolId(sai.getApplyDepId());
                setl.setPostId(sai.getApplyJobId());
                setl.setIsEnter("1");
                setl.setListPublishStatus("2");
                setl = scoreEnterTrialService.selectObjectByScoreEnterTrial(setl);
                //判断当前状态下，是进入试讲还是没有进入试讲，因为进入试讲和没进入试讲都不会更新这个字段
                ScoreEnterTrial setl2 = new ScoreEnterTrial();
                Postset ps = postsetService.selectPostsetById(sai.getApplyJobId());
                setl2.setItemsId(sai.getEmployItemsId());
                setl2.setPostId(sai.getApplyJobId());
                setl2.setSubjectType(ps.getIsArt().toString());//普通科 艺术科
                Integer countOfEnterTrial = scoreEnterTrialService.selectCountOfEnterTrial(setl2);
                //查询统计进入笔试环节的人数，来判断现在是发布了笔试名单
                Integer countOfEnterWritten = studentApplyInfoService.selectCountOfEnterWritten(sai);
                
                if (theme.getStep() >= 4) {
                    //笔试名单或者试讲名单发布
                    Grade gradeInter = gradeService.queryScore(sai,"1");
                    int isEnterTrial = 1,isEnterWritten = 1;
                    if (setl == null) {
                        //没有入围试讲
                        isEnterTrial = 0;
                        //noticeMsg.append("您没有入围试讲环节，").append(gradeInter == null ? "面试成绩没有找到" : "面试成绩为：" + gradeInter.getGrade() + "。如果对结果有异议，请联系教育局。");
                    }
                    
                    if (countOfEnterWritten != null && countOfEnterWritten > 0) {
                        //已经发布了笔试名单，但是你的状态还是7，没有入围笔试
                        isEnterWritten = 0;
                    }
                    
                    if ((isEnterTrial ^ isEnterWritten) == 1) {
                        if (isEnterWritten == 1 && isEnterTrial == 0) {
                            noticeMsg.append("您没有入围试讲环节，并且笔试名单没有发布，").append(gradeInter == null ? "面试成绩没有找到" : "面试成绩为：" + gradeInter.getGrade() + "。");
                        } else {
                            noticeMsg.append("您入围了试讲环节，但是没有入围笔试环节，").append(gradeInter == null ? "面试成绩没有找到" : "面试成绩为：" + gradeInter.getGrade() + "。");
                        }
                    } else {
                        if (countOfEnterTrial == null || countOfEnterTrial == 0) {
                            //现在确实没有发布试讲名单
                            //判断当前的岗位是否为中职专业课
                            if (si.getProfessionalCourse().equals("1")) {
                                noticeMsg.append("此次招聘岗位不设置笔试，并且试讲名单还没发布，").append(gradeInter == null ? "面试成绩没有找到" : "面试成绩为：" + gradeInter.getGrade() + "。");
                            } else {
                                noticeMsg.append("您没有入围笔试环节，并且试讲名单还没发布，").append(gradeInter == null ? "面试成绩没有找到" : "面试成绩为：" + gradeInter.getGrade() + "。");
                            }
                        } else {
                            noticeMsg.append("您没有入围笔试和试讲环节，").append(gradeInter == null ? "面试成绩没有找到" : "面试成绩为：" + gradeInter.getGrade() + "。");
                        }
                        
                    }
                } else {
                    noticeMsg.append("您已入围招聘单位面试环节，请打印准考证和面试通知单，按要求准时参加面试");
                }
                break;
            case "8":
                // 查询面试成绩
                Grade gradeInter = gradeService.queryScore(sai,"1");
                if (gradeInter == null) {
                    noticeMsg.append("您已入围笔试环节，请打印准考证和笔试通知单，按要求准时参加笔试。");
                } else {
                    noticeMsg.append("您的面试成绩为:").append(gradeInter.getGrade()).append("，并且已入围笔试环节，请打印准考证和笔试通知单，按要求准时参加笔试。");
                }
                break;
            case "10":
                //招聘进度到了考察环节，也需要查询体检没有通过的考 生
                if (theme.getStep() >= 10 ) {
                    sgp = scoreGradePhysicalService.selectPhysicalResult(sai);
                    if (sgp == null) {
                        noticeMsg.append("您的体检结果未找到，没有入围考察。");
                    } else {
                        noticeMsg.append("您的体检")
                        .append(sgp.getResult().equals("1") ? "通过，" : "不通过，").append("没有入围考察。");
                    }
                } else {
                    noticeMsg.append("您已进入体检环节，请耐心等待结果。");
                }
                break;
            case "11":
                //招聘进度到了公示环节，也需要查询考察没有通过的考生
                if (theme.getStep() >= 12 ) {
                    noticeMsg.append("您的考察结果不通过。");
                } else {
                    sgp = scoreGradePhysicalService.selectPhysicalResult(sai);
                    if (sgp == null) {
                        noticeMsg.append("您已经进入考察阶段，但是体检成绩还未发布。");
                    } else {
                        noticeMsg.append("您的体检")
                        .append(sgp.getResult().equals("1") ? "通过，" : "不通过，").append("并且已经进入考察阶段。");
                    }
                }
                break;
            case "12":
                sgs = scoreGradeStudyService.selectStudyResult(sai);
                if (sgs == null) {
                    noticeMsg.append("您已进入公示阶段，但是考察结果还未发布。");
                } else {
                    noticeMsg.append("您的考察结果：")
                    .append(sgs.getResult().equals("1") ? "通过，" : "不通过，").append("并且已经进入公示阶段。");
                }
                break;
        }
        
        //单独处理是否入围试讲环节
        ScoreEnterTrial set = scoreEnterTrialService.selectEnterObject(sai);
        if (set != null && Integer.parseInt(applyStatus) < 10 && theme.getStep() < 8) {
            noticeMsg = new StringBuilder();
            //还未发布笔试名单
            Grade gradeInter = gradeService.queryScore(sai,"1");
            if (Integer.parseInt(applyStatus) < 8) {
                if (gradeInter == null) {
                    noticeMsg.append("您已入围试讲环节，请打印准考证和试讲通知单，按要求准时参加试讲");
                } else {
                    noticeMsg.append("您的面试成绩为:").append(gradeInter.getGrade()).append("，并且已入围试讲环节，请打印准考证和试讲通知单，按要求准时参加试讲。");
                }
            } else if (Integer.parseInt(applyStatus) < 10){
                //入围笔试和试讲环节，1、可能是按照正常流程进入的；2、可能是被调整进入的。都需要查询面试成绩
                if (gradeInter == null) {
                    noticeMsg.append("您已入围笔试和试讲环节，请打印准考证和对应的通知单，按要求准时参加考试");
                } else {
                    noticeMsg.append("您的面试成绩为:").append(gradeInter.getGrade()).append("，并且已入围笔试和试讲环节，请打印准考证和对应的通知单，按要求准时参加考试");
                }
                
            }
        }
        
        //进入体检环节查成绩
        if (Integer.parseInt(applyStatus) == 10 && (theme.getStep() == 8 ||theme.getStep() == 9)) {
            ScoreGradeWriten sgw = scoreGradeWrittenService.selectGradeWritten(sai);
            if (sgw != null) {
                noticeMsg.append("您的笔试成绩为：" + sgw.getGrade());
            }
            ScoreGradeTrial sgt = scoreGradeTrialService.selectScoreGradeTrial(sai);
            if (sgt != null) {
                if (sgw != null) {
                    //增加综合成绩
                    noticeMsg.append("，试讲成绩为：" + sgt.getGrade()).append(sgt.getRemark() == null ? "，综合成绩（如果岗位属中职专业岗位，试讲成绩即综合成绩）为：0" : "，综合成绩（如果岗位属中职专业岗位，试讲成绩即综合成绩）为：" + sgt.getRemark()).append("。");
                } else {
                    noticeMsg.append("您的试讲成绩为：" + sgt.getGrade());
                }
            }
        }
        
        //如果总进度到了体检环节或者发布体检结果，则需要查询没有入围体检环节的考生，即综合成绩没有通过的或者从体检入围名单剔除的
        if (theme.getStep() == 8 || theme.getStep() == 9) {
            ScoreEnterPhysical sep = new ScoreEnterPhysical();
            sep.setStudentId(sai.getStudentId());
            sep.setItemsId(sai.getEmployItemsId());
            sep.setPostId(sai.getApplyJobId());
            sep.setSchoolId(sai.getApplyDepId());
            sep = scoreEnterPhysicalService.selectObjectByScoreEnterPhysical(sep);
            if ((sep == null) || (sep != null && "2".equals(sep.getIsEnter()))) {
                noticeMsg = new StringBuilder();
                ScoreGradeWriten sgw = scoreGradeWrittenService.selectGradeWritten(sai);
                ScoreGradeTrial sgt = scoreGradeTrialService.selectScoreGradeTrial(sai);
                
                String zhcj = (sgw == null || sgt == null) ? "0" : sgt.getRemark();
                if ("1".equals(si.getProfessionalCourse())) {
                    //如果是中职专业课岗位，试讲成绩就是综合成绩
                    zhcj = sgt == null ? "0" : sgt.getRemark();
                }
                noticeMsg.append("您没有入围体检环节，综合成绩（如果岗位属中职专业岗位，试讲成绩即综合成绩）").append("为：" + zhcj + "，笔试成绩为：").append(sgw == null ? "0" : sgw.getGrade()).append("，试讲成绩为：")
                .append(sgt == null ? "0" : sgt.getGrade()).append("。");
            }
        }
        
        
        //发布考察名单或者发布考察成绩，但没有入围考察环节
        if (theme.getStep() == 10 || theme.getStep() == 11) {
            if (Integer.parseInt(applyStatus) < 10) {
                noticeMsg = new StringBuilder();
                noticeMsg.append("您没有入围考察环节。");
            }
        }
        
        //发布公示名单
        if (theme.getStep() == 12) {
            if (Integer.parseInt(applyStatus) < 12) {
                noticeMsg = new StringBuilder();
                //查询考察结果是否通过
                sgs = scoreGradeStudyService.selectStudyResult(sai);
                if (sgs == null) {
                    noticeMsg.append("您没有入围公示环节。");
                } else {
                    noticeMsg.append("您的考察结果：")
                    .append(sgs.getResult().equals("1") ? "通过，" : "不通过，").append("没有入围公示环节。");
                }
                
                
            }
        }
        
        
        model.addAttribute("sai", sai);
        model.addAttribute("noticeMsg", noticeMsg);
        return "/student/recruitProgress.vm";
    }


    @RequestMapping(value = "interviewNotice")
    public String interviewNotice(HttpServletRequest request, Model model) {
        StudentInfo si = studentInfoService
                .selectStudentInfoById(request.getSession().getAttribute("sid").toString());
        if (si == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        model.addAttribute("si", si);
        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        // 面试通知书生成，面试名单发布之后
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sid", request.getSession().getAttribute("sid").toString());
        map.put("recruitId", request.getSession().getAttribute("recruitId").toString());
        List<InterviewNoticeVo> interList = actionNoticeService.getInterviewNotices(map);
        for (InterviewNoticeVo inter : interList) {
            inter.setRecruitId(request.getSession().getAttribute("recruitId").toString());
        }
        model.addAttribute("interList", interList);
        model.addAttribute("photo", request.getSession().getAttribute("photo"));
        model.addAttribute("studentName", request.getSession().getAttribute("studentName"));
        return "/student/interviewNotice.vm";
    }

    @RequestMapping(value = "writtenNotice")
    public String writtenNotice(HttpServletRequest request, Model model) {
        StudentInfo si = studentInfoService
                .selectStudentInfoById(request.getSession().getAttribute("sid").toString());
        if (si == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        model.addAttribute("si", si);
        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        // 获取申请状态
        StudentApplyInfo sai = new StudentApplyInfo();
        sai.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        sai.setStudentId(si.getId());
        sai = studentApplyInfoService.selectObjectByStudentApplyInfo(sai);
        model.addAttribute("applyStatus", sai == null ? "0" : sai.getApplyStatus());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sid", request.getSession().getAttribute("sid").toString());
        map.put("recruitId", request.getSession().getAttribute("recruitId").toString());
        List<WrittenNoticeVo> writtenList = actionNoticeService.getWrittenNotices(map);
        
        if ((writtenList == null || writtenList.size() == 0) && sai != null) {
            //如果没有找到笔试通知单，那么有两种情况：1、确实没有发布笔试入围名单；2、已经过了发布笔试入围名单这一步，您没有入围
            Integer countOfEnterWritten = studentApplyInfoService.selectCountOfEnterWritten(sai);
            if (countOfEnterWritten != null && countOfEnterWritten > 0) {
                model.addAttribute("isOverWritten", "1");
            } else {
                model.addAttribute("isOverWritten", "0");
            }
            
            //查询考生申报的岗位是否是中职专业课的
            Postset ps = postsetService.selectPostsetById(sai.getApplyJobId());
            if (ps != null && "1".equals(ps.getIszz())) {
                model.addAttribute("iszz", "1");
            } else {
                model.addAttribute("iszz", "0");
            }
        }
        
        
        
        model.addAttribute("writtenList", writtenList);
        model.addAttribute("photo", request.getSession().getAttribute("photo"));
        model.addAttribute("studentName", request.getSession().getAttribute("studentName"));
        return "/student/writtenNotice.vm";
    }

    @RequestMapping(value = "talkNotice")
    public String talkNotice(HttpServletRequest request, Model model) {
        StudentInfo si = studentInfoService
                .selectStudentInfoById(request.getSession().getAttribute("sid").toString());
        if (si == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        model.addAttribute("si", si);
        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        model.addAttribute("photo", request.getSession().getAttribute("photo"));
        model.addAttribute("studentName", request.getSession().getAttribute("studentName"));
        // 获取申请状态
        StudentApplyInfo sai = new StudentApplyInfo();
        sai.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        sai.setStudentId(si.getId());
        sai = studentApplyInfoService.selectObjectByStudentApplyInfo(sai);
        model.addAttribute("applyStatus", sai == null ? "0" : sai.getApplyStatus());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sid", request.getSession().getAttribute("sid").toString());
        map.put("recruitId", request.getSession().getAttribute("recruitId").toString());
        map.put("type", "1");
        TalkNoticeVo talk = actionNoticeService.getTalkNotices(map);
        List<TalkNoticeVo> talkList = new ArrayList<TalkNoticeVo>();
        if (talk != null) {
            talkList.add(talk);
        } else if (sai != null){
            Postset ps = postsetService.selectPostsetById(sai.getApplyJobId());
            //如果没有入围试讲
            ScoreEnterTrial setl = new ScoreEnterTrial();
            setl.setItemsId(sai.getEmployItemsId());
            setl.setStudentId(sai.getStudentId());
            setl.setSchoolId(sai.getApplyDepId());
            setl.setPostId(sai.getApplyJobId());
            //艺术科  普通科
            setl.setSubjectType(ps.getIsArt().toString());
            //判断当前是已经发布入围 名单了，还是没有到发布入围名单这一步
            Integer countOfEnter = scoreEnterTrialService.selectCountOfEnterTrial(setl);
            if (countOfEnter != null && countOfEnter > 0) {
                model.addAttribute("isOverTrial", "1");
            } else {
                model.addAttribute("isOverTrial", "0");
            }
            
            setl.setIsEnter("1");
            setl.setListPublishStatus("2");
            setl = scoreEnterTrialService.selectObjectByScoreEnterTrial(setl);
            if (setl == null) {
                model.addAttribute("isEnterTrial", "0");
                model.addAttribute("isPublishKd", "0");
            } else {
                //或者已经入围试讲，但是考点还未发布
                model.addAttribute("isEnterTrial", "1");
                model.addAttribute("isPublishKd", "0");
            }
        } else {
            model.addAttribute("isEnterTrial", "0");
            model.addAttribute("isPublishKd", "0");
            model.addAttribute("isOverTrial", "0");
        }

        model.addAttribute("talkList", talkList);
        return "/student/talkNotice.vm";
    }

    @RequestMapping(value = "setSecretProtect")
    public String setSecretProtect(Model model, HttpServletRequest request) {
        // 获取字典中的密保问题
        List<Dictionary> dicMb = DictionaryCache.getDictionaryByCode("mbwt");
        model.addAttribute("dicMb", dicMb);
        return "/student/setSecretProtect.vm";
    }

    @RequestMapping(value = "verifySecretProtect")
    public String verifySecretProtect(String sid, Model model, HttpServletRequest request) {
        String studentId = request.getSession().getAttribute("sid").toString();
        SecurityQueInfo sqi = new SecurityQueInfo();
        sqi.setStudentId(studentId);
        List<SecurityQueInfo> sqiList = securityQueInfoService.selectAllBySecurityQueInfo(sqi);
        model.addAttribute("sqiList", sqiList);
        return "/student/verifySecretProtect.vm";
    }

    @RequestMapping(value = "updateSecret")
    public String updateSecret(String sid, Model model, HttpServletRequest request) {
        String studentId = request.getSession().getAttribute("sid").toString();
        SecurityQueInfo sqi = new SecurityQueInfo();
        sqi.setStudentId(studentId);
        List<SecurityQueInfo> sqiList = securityQueInfoService.selectAllBySecurityQueInfo(sqi);
        model.addAttribute("sqiList", sqiList);
        return "/student/updateSecret.vm";
    }

    @RequestMapping(value = "resetpass")
    public String resetpass(Model model, HttpServletRequest request) {
        String studentId = request.getSession().getAttribute("sid").toString();
        SecurityQueInfo sqi = new SecurityQueInfo();
        sqi.setStudentId(studentId);
        return "/student/resetpass.vm";
    }

    @RequestMapping("saveSecret")
    @ResponseBody
    public String saveSecret(@RequestBody List<SecurityQueInfo> secretList,
            HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        String sid = request.getSession().getAttribute("sid").toString();
        try {
            for (SecurityQueInfo sq : secretList) {
                if (sq.getAnswer().equals("")) {
                    continue;
                }

                sq.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                sq.setStudentId(sid);
                sq.setCreateUser(sid);
                sq.setCtime(new Date());
                securityQueInfoService.saveSecurityQueInfo(sq);
            }
            jo.put("flag", true);
            jo.put("msg", "设置成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "设置出现异常！");
            return jo.toJSONString();
        }
    }


    @RequestMapping("answerSecret")
    @ResponseBody
    public String answerSecret(@RequestBody List<SecurityQueInfo> secretList,
            HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        String sid = request.getSession().getAttribute("sid").toString();
        if (secretList == null || secretList.size() == 0) {
            jo.put("flag", false);
            jo.put("msg", "请输入密保问题答案！");
            return jo.toJSONString();
        }

        try {
            for (SecurityQueInfo sq : secretList) {
                if (sq.getAnswer().equals("")) {
                    jo.put("flag", false);
                    jo.put("msg", "密保答案为空，请正确回答密保问题！");
                    return jo.toJSONString();
                }
                sq.setStudentId(sid);
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

    @RequestMapping("editSecret")
    @ResponseBody
    public String editSecret(@RequestBody List<SecurityQueInfo> secretList,
            HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        String sid = request.getSession().getAttribute("sid").toString();
        if (secretList == null || secretList.size() == 0) {
            jo.put("flag", false);
            jo.put("msg", "请输入密保问题答案！");
            return jo.toJSONString();
        }

        sid = request.getSession().getAttribute("sid").toString();
        try {
            for (SecurityQueInfo s : secretList) {
                s.setStudentId(sid);
            }
            map.put("secretList", secretList);
            securityQueInfoService.updateMultiSecret(map);
            jo.put("flag", true);
            jo.put("msg", "修改成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "设置出现异常！");
            return jo.toJSONString();
        }
    }

    @RequestMapping("saveResetPass")
    @ResponseBody
    public String saveResetPass(HttpServletRequest request, String newpass, String oldpass) {
        JSONObject jo = new JSONObject();
        if (StringUtil.isEmpty(newpass)) {
            jo.put("flag", false);
            jo.put("msg", "密码不能为空！");
            return jo.toJSONString();
        }

        if (StringUtil.isEmpty(oldpass)) {
            jo.put("flag", false);
            jo.put("msg", "密码不能为空！");
            return jo.toJSONString();
        }

        String sid = request.getSession().getAttribute("sid").toString();
        try {
            // 查询旧密码是否错误
            StudentInfo si = new StudentInfo();
            si.setId(sid);
            si.setPassword(MD5Utils.md5Encrypt(oldpass));
            si = studentInfoService.selectObjectByStudentInfo(si);
            if (si == null) {
                jo.put("flag", false);
                jo.put("msg", "原密码输入错误！");
                return jo.toJSONString();
            }
            si = new StudentInfo();
            si.setId(sid);
            si.setPassword(MD5Utils.md5Encrypt(newpass));
            si.setCtime(null);
            si.setMtime(new Date());
            studentInfoService.updateStudentInfoByField(si);
            jo.put("flag", true);
            jo.put("msg", "密码修改成功！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "修改出现异常！");
            return jo.toJSONString();
        }
    }


    @RequestMapping("applyProfileEdit")
    public String applyProfileEdit(Model model, HttpServletRequest request) {
        String sid = request.getSession().getAttribute("sid").toString();
        // 根据id查找申请信息
        StudentApplyInfo studentApplyInfo = new StudentApplyInfo();
        studentApplyInfo.setStudentId(sid);
        studentApplyInfo
                .setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        studentApplyInfo = studentApplyInfoService.selectObjectByStudentApplyInfo(studentApplyInfo);

        StudentInfo sInfo = studentInfoService.selectStudentInfoById(sid);
        if (sInfo == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        StudentEduInfo sei = new StudentEduInfo();
        sei.setStudentId(sid);
        sei.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        List<StudentEduInfo> seiList = studentEduInfoService.selectAllByStudentEduInfo(sei);
        int count = 0;
        for (StudentEduInfo s : seiList) {
            if (s.getIsSimilarTerm() != null && s.getIsSimilarTerm().equals("1")) {
                count++;
            }
        }
        sInfo.setHasSimilar(count > 0 ? "1" : "0");
        model.addAttribute("sInfo", sInfo);

        if (studentApplyInfo == null) {
            return "redirect:applySubmit";
        }
        // 查询岗位,获取限制条件
        Position position = new Position();
        position.setStationId(studentApplyInfo.getApplyJobId());// 岗位id
        position.setSchoolId(studentApplyInfo.getApplyDepId());// 学校id
        position.setProjectId(studentApplyInfo.getEmployItemsId());// 招聘主题id
        position = positionService.selectObjectByPosition(position);
        if (position != null) {
            // 岗位对应的专业限制
            PositionDomain positionDomain = new PositionDomain();
            positionDomain.setSchoolId(studentApplyInfo.getApplyDepId());
            positionDomain.setPositionId(studentApplyInfo.getApplyJobId());
            positionDomain.setPlanApplyId(position.getPlanApplyId());
            List<PositionDomain> positionDomains =
                    positionDomainService.selectAllByPositionDomain(positionDomain);
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


        // 身份证
        if (StringUtil.isNotEmpty(studentApplyInfo.getIdcardAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getIdcardAttId());
            if (att != null) {
                studentApplyInfo.setIdcardRealName(att.getRealName());
                studentApplyInfo.setIdcardUrlPath(att.getPath());
            }
        }

        // 教师资格证或教育学、心理学、普通话成绩单
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerAchAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getCerAchAttId());
            if (att != null) {
                studentApplyInfo.setCerAchRealName(att.getRealName());
                studentApplyInfo.setCerAchUrlPath(att.getPath());
            }
        }
        // 毕业证书或者就业推荐表
        if (StringUtil.isNotEmpty(studentApplyInfo.getGraRecomAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getGraRecomAttId());
            if (att != null) {
                studentApplyInfo.setGraRecomRealName(att.getRealName());
                studentApplyInfo.setGraRecomUrlPath(att.getPath());
            }
        }
        // 学历鉴定证明
        if (StringUtil.isNotEmpty(studentApplyInfo.getAcaQuaAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getAcaQuaAttId());
            if (att != null) {
                studentApplyInfo.setAcaQuaRealName(att.getRealName());
                studentApplyInfo.setAcaQuaUrlPath(att.getPath());
            }
        }
        // 学位证书
        if (StringUtil.isNotEmpty(studentApplyInfo.getBacAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getBacAttId());
            if (att != null) {
                studentApplyInfo.setBacRealName(att.getRealName());
                studentApplyInfo.setBacUrlPath(att.getPath());
            }
        }
        // 学位鉴定证明
        if (StringUtil.isNotEmpty(studentApplyInfo.getBacQuaAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getBacQuaAttId());
            if (att != null) {
                studentApplyInfo.setBacQuaRealName(att.getRealName());
                studentApplyInfo.setBacQuaUrlPath(att.getPath());
            }
        }

        // 计划生育证明
        if (StringUtil.isNotEmpty(studentApplyInfo.getFamPlanAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getFamPlanAttId());
            if (att != null) {
                studentApplyInfo.setFamPlanRealName(att.getRealName());
                studentApplyInfo.setFamPlanUrlPath(att.getPath());
            }
        }
        // 个人学习和工作情况总结
        if (StringUtil.isNotEmpty(studentApplyInfo.getStudyWorkAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getStudyWorkAttId());
            if (att != null) {
                studentApplyInfo.setStudyWorkRealName(att.getRealName());
                studentApplyInfo.setStudyWorkUrlPath(att.getPath());
            }
        }
        // 注意：请上传5-8分钟与申请岗位相关的教学视频，
        if (StringUtil.isNotEmpty(studentApplyInfo.getTeaVideoAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getTeaVideoAttId());
            if (att != null) {
                studentApplyInfo.setTeaVideoRealName(att.getRealName());
                studentApplyInfo.setTeaVideoUrlPath(att.getPath());
            }
        }

        // 暂缓就业协议书（已办理暂缓就业手续的毕业生必须提供）
        if (StringUtil.isNotEmpty(studentApplyInfo.getSuspendEmpAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getSuspendEmpAttId());
            if (att != null) {
                studentApplyInfo.setSuspendEmpRealName(att.getRealName());
                studentApplyInfo.setSuspendEmpUrlPath(att.getPath());
            }
        }

        // 出国留学
        if (StringUtil.isNotEmpty(studentApplyInfo.getAbroadStudyAttId())) {
            Attachement att = attachementService
                    .selectAttachementById(studentApplyInfo.getAbroadStudyAttId());
            if (att != null) {
                studentApplyInfo.setAbroadStudyRealName(att.getRealName());
                studentApplyInfo.setAbroadStudyUrlPath(att.getPath());
            }
        }
        // 毕业成绩单
        if (StringUtil.isNotEmpty(studentApplyInfo.getTranscriptAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getTranscriptAttId());
            if (att != null) {
                studentApplyInfo.setTranscriptRealName(att.getRealName());
                studentApplyInfo.setTranscriptUrlPath(att.getPath());
            }
        }

        // 心理学成绩
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerPsyAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getCerPsyAttId());
            if (att != null) {
                studentApplyInfo.setCerPsyRealName(att.getRealName());
                studentApplyInfo.setCerPsyUrlPath(att.getPath());
            }
        }

        // 普通话成绩
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerManAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getCerManAttId());
            if (att != null) {
                studentApplyInfo.setCerManRealName(att.getRealName());
                studentApplyInfo.setCerManUrlPath(att.getPath());
            }
        }

        // 教育学成绩
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerPedAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getCerPedAttId());
            if (att != null) {
                studentApplyInfo.setCerPedRealName(att.getRealName());
                studentApplyInfo.setCerPedUrlPath(att.getPath());
            }
        }

        // 教育教学能力测试
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerAbiAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getCerAbiAttId());
            if (att != null) {
                studentApplyInfo.setCerAbiRealName(att.getRealName());
                studentApplyInfo.setCerAbiUrlPath(att.getPath());
            }
        }

        // 教育实习证明
        if (StringUtil.isNotEmpty(studentApplyInfo.getCerShipAttId())) {
            Attachement att =
                    attachementService.selectAttachementById(studentApplyInfo.getCerShipAttId());
            if (att != null) {
                studentApplyInfo.setCerShipRealName(att.getRealName());
                studentApplyInfo.setCerShipUrlPath(att.getPath());
            }
        }

        model.addAttribute("studentApplyInfo", studentApplyInfo);
        model.addAttribute("isEdit", "1");
        model.addAttribute("applyStatus",
                studentApplyInfo == null ? "1" : studentApplyInfo.getApplyStatus());
        return "/student/applySubmit.vm";
    }
    
    @RequestMapping(value = "applySubmit")
    public String applySubmit(Model model, String recruitJobId, String recruitDeptId,
            HttpServletRequest request) {
        StudentApplyInfo studentApplyInfo = new StudentApplyInfo();
        if (StringUtil.isEmpty(recruitJobId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (StringUtil.isEmpty(recruitDeptId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        String sid = request.getSession().getAttribute("sid").toString();
        StudentInfo sInfo = studentInfoService.selectStudentInfoById(sid);
        if (sInfo == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        
        //判断公告是否暂停
        JSONObject jo = isStopNotice(request);
        if (jo == null || !jo.getBooleanValue("flag")) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        
        //是否过了注册报名的时间
        /*if(!isOverRegister(request)) {
            return "redirect:/dg/studentApplyInfo/index";
        }*/
        
        if(!isOverRegisterTime(request)){
        	return "redirect:/dg/studentApplyInfo/index";
        }
        StudentEduInfo sei = new StudentEduInfo();
        sei.setStudentId(sid);
        sei.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        List<StudentEduInfo> seiList = studentEduInfoService.selectAllByStudentEduInfo(sei);
        model.addAttribute("seInfo", getXlHighest(seiList));
        int count = 0;
        for (StudentEduInfo s : seiList) {
            if (s.getIsSimilarTerm() != null && s.getIsSimilarTerm().equals("1")) {
                count++;
            }
        }
        sInfo.setHasSimilar(count > 0 ? "1" : "0");
        model.addAttribute("sInfo", sInfo);


        studentApplyInfo.setStudentId(sid);
        studentApplyInfo
                .setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        studentApplyInfo.setApplyJobId(recruitJobId);
        studentApplyInfo.setApplyDepId(recruitDeptId);

        // 查询岗位,获取限制条件
        Position position = new Position();
        position.setStationId(recruitJobId);// 岗位id
        position.setSchoolId(recruitDeptId);// 学校id
        position.setProjectId(request.getSession().getAttribute("recruitId").toString());// 招聘主题id
        position = positionService.selectObjectByPosition(position);
        if (position != null) {
            // 岗位对应的专业限制
            PositionDomain positionDomain = new PositionDomain();
            positionDomain.setSchoolId(recruitDeptId);
            positionDomain.setPositionId(recruitJobId);
            positionDomain.setPlanApplyId(position.getPlanApplyId());
            List<PositionDomain> positionDomains =
                    positionDomainService.selectAllByPositionDomain(positionDomain);
            if (positionDomains != null && positionDomains.size() > 0) {
                position.setPositionDomains(positionDomains);
            }
            String posCondition = position.generateCondition();
            studentApplyInfo.setPosCondition(posCondition);
        }

        // 岗位名称
        Postset ps = postsetService.selectPostsetById(recruitJobId);
        if (ps != null) {
            studentApplyInfo.setApplyJobName(ps.getPostName());
        }

        Dictionary dic = dictionaryService.selectDictionaryById(recruitDeptId);
        if (dic != null) {
            studentApplyInfo.setApplyDeptName(dic.getText());
        }
        model.addAttribute("studentApplyInfo", studentApplyInfo);
        model.addAttribute("isEdit", "0");
        model.addAttribute("applyStatus", 1);
        return "/student/applySubmit.vm";
    }



    @RequestMapping("verifyCode")
    @ResponseBody
    public String verifyCode(String schoolId, String verifycode) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("schoolId", schoolId);
            map.put("verifycode", verifycode);
            jo.put("flag", true);
            Integer isPass = studentApplyInfoService.verifyCode(map);
            jo.put("msg", isPass > 0 ? "验证通过！" : "验证不通过！");
            return jo.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "验证出现异常！");
            return jo.toJSONString();
        }
    }

    @RequestMapping("getRecruitDept")
    @ResponseBody
    public String getRecruitDept(String positionId, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (StringUtil.isEmpty(positionId)) {
                jo.put("flag", false);
                jo.put("msg", "请选择岗位");
                return jo.toString();
            }

            // 招聘主题
            map.put("recruitId", request.getSession().getAttribute("recruitId").toString());
            // 招聘岗位
            map.put("positionId", positionId);
            List<Position> positionList = positionService.selectOptionalPosition(map);
            // 从list中获取单位，和岗位限制条件
            if (positionList != null && positionList.size() > 0) {
                for (Position p : positionList) {
                    String posCondition = "";
                    Dictionary dic = dictionaryService.selectDictionaryById(p.getSchoolId());
                    if (dic == null) {
                        continue;
                    }
                    p.setSchoolName(dic.getText());

                    // 获取每一条记录对应的限制条件
                    if (p != null) {
                        // 岗位对应的专业限制
                        PositionDomain positionDomain = new PositionDomain();
                        positionDomain.setSchoolId(p.getSchoolId());
                        positionDomain.setPositionId(positionId);
                        positionDomain.setPlanApplyId(p.getPlanApplyId());
                        List<PositionDomain> positionDomains =
                                positionDomainService.selectAllByPositionDomain(positionDomain);
                        if (positionDomains != null && positionDomains.size() > 0) {
                            p.setPositionDomains(positionDomains);
                        }
                        posCondition = p.generateCondition();
                    }
                    p.setPosCondition(posCondition);
                    // 获取申报该岗位的考生,已经提交资料的考生
                    map.put("schoolId", p.getSchoolId());
                    Integer count = studentApplyInfoService.appliedStudent(map);
                    p.setApplyCount(count == null ? "0" : count.toString());

                    // 查询面试信息确认
                    Information info = new Information();
                    info.setProjectId(p.getProjectId());
                    info.setPositionId(p.getStationId());
                    info.setSchoolId(p.getSchoolId());
                    info.setType("1");
                    info.setStatus("1");// 找已发布的信息
                    Information info_inter = informationService.selectObjectByInformation(info);

                    // 查询现场资料确认
                    info.setPositionId("");
                    info.setType("2");
                    Information info_place = informationService.selectObjectByInformation(info);
                    List<Information> infoList = new ArrayList<Information>();
                    infoList.add(info_place);
                    infoList.add(info_inter);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (infoList != null && infoList.size() > 0) {
                        for (Information inf : infoList) {
                            if (inf != null) {
                                if (inf.getType().equals("1")) {
                                    p.setInterviewTime(sdf.format(inf.getStartDate())
                                            + "<br/>至<br/>" + sdf.format(inf.getEndDate()));
                                    p.setInterviewSite(inf.getSite() == null ? " " : inf.getSite());
                                }

                                if (inf.getType().equals("2")) {
                                    p.setProfileSubmitTime(sdf.format(inf.getStartDate())
                                            + "<br/>至<br/>" + sdf.format(inf.getEndDate()));
                                    p.setProfileSubmitSite(
                                            inf.getSite() == null ? " " : inf.getSite());
                                }
                            }
                        }
                    }
                }
                jo.put("flag", true);
                jo.put("msg", positionList);
                return jo.toJSONString();
            } else {
                jo.put("flag", false);
                jo.put("msg", "该岗位下没有找到招聘单位！");
                return jo.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("flag", false);
            jo.put("msg", "数据异常");
            return jo.toJSONString();
        }

    }

    @RequestMapping(value = "loginVerify")
    @ResponseBody
    public String login(StudentInfo studentInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();
        if (StringUtil.isEmpty(studentInfo.getIdentityCard())
                || StringUtil.isEmpty(studentInfo.getPassword())) {
            jo.put("flag", false);
            jo.put("msg", "用户名或密码不能为空！");
            return jo.toJSONString();
        }

        StudentInfo si = new StudentInfo();
        si.setIdentityCard(studentInfo.getIdentityCard().toUpperCase());
        si.setPassword(MD5Utils.md5Encrypt(studentInfo.getPassword()));
        si.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        StudentInfo sInfo = studentInfoService.selectObjectByStudentInfo(si);
        if (sInfo == null) {
            jo.put("flag", false);
            jo.put("msg", "用户名或密码不正确！");
            return jo.toJSONString();
        }

        if (null != SessionListener.sessionMap.get(sInfo.getId())
                && SessionListener.sessionMap.get(sInfo.getId()) != session) {
            SessionListener.forceLogoutUser(sInfo.getId());
            SessionListener.sessionMap.put(sInfo.getId(), session);
        } else {
            SessionListener.sessionMap.put(sInfo.getId(), session);
        }

        jo.put("flag", true);
        jo.put("msg", "成功登录！");
        jo.put("sInfo", sInfo);
        request.getSession().setAttribute("sid", sInfo.getId());
        return jo.toJSONString();
    }

    @RequestMapping(value = "isExistSid")
    @ResponseBody
    public String isExistSid(String identityCard, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        if (StringUtil.isEmpty(identityCard)) {
            jo.put("flag", false);
            jo.put("msg", "请先输入身份证号！");
            return jo.toJSONString();
        }

        StudentInfo si = new StudentInfo();
        si.setIdentityCard(identityCard);
        si.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        si.setPassword(null);
        StudentInfo sInfo = studentInfoService.selectObjectByStudentInfo(si);
        if (sInfo == null) {
            jo.put("flag", false);
            jo.put("msg", "您的账号不存在！");
            return jo.toJSONString();
        }

        SecurityQueInfo sqi = new SecurityQueInfo();
        sqi.setStudentId(sInfo.getId());
        List<SecurityQueInfo> sqiList = securityQueInfoService.selectAllBySecurityQueInfo(sqi);
        if (sqiList == null || sqiList.size() == 0) {
            jo.put("flag", false);
            jo.put("msg", "您忘记了设置密保，请联系管理员重置密码！");
            return jo.toJSONString();
        }
        jo.put("flag", true);
        jo.put("msg", "成功登录！");
        jo.put("sid", sInfo.getId());
        return jo.toJSONString();
    }


    @RequestMapping(value = "register")
    public String userProfile(Model model, HttpServletRequest request, String type) {
        if (StringUtil.isEmpty(type)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        // 没有到报名时间的话，直接跳出
        if (!isRegisterTime(request)) {
            return "redirect:/dg/studentApplyInfo/index";
        }

        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        // 民族
        List<Dictionary> mzs = DictionaryCache.getDictionaryByCode("mzdm");
        model.addAttribute("mzs", mzs);

        // 学位
        List<Dictionary> xws = DictionaryCache.getDictionaryByCode("xwlx");
        model.addAttribute("xws", xws);

        // 教育类型
        List<Dictionary> jys = DictionaryCache.getDictionaryByCode("jylx");
        model.addAttribute("jys", jys);

        // 政治面貌
        List<Dictionary> zzmms = DictionaryCache.getDictionaryByCode("zzmm");
        model.addAttribute("zzmms", zzmms);

        // 资格证种类
        List<Dictionary> jszgzls = DictionaryCache.getDictionaryByCode("jszgzl");
        model.addAttribute("jszgzls", jszgzls);

        // 专业技术资格
        List<Dictionary> zyjszgs = DictionaryCache.getDictionaryByCode("zyjszg");
        model.addAttribute("zyjszgs", zyjszgs);

        // 荣誉称号
        List<Dictionary> rychs = DictionaryCache.getDictionaryByCode("rych");
        model.addAttribute("rychs", rychs);

        // 考生类型
        model.addAttribute("recruitItemId",
                request.getSession().getAttribute("recruitId").toString());
        
        if (type.equals("1")) {
            return "/student/registration.vm";
        } else {
            return "/student/registrationNonGra.vm";
        }

    }

    @RequestMapping(value = "registerIndex")
    public String registerIndex(Model model, HttpServletRequest request,
            HttpServletResponse response) {
        String studentId = request.getSession().getAttribute("sid").toString();
        Theme theme = themeService
                .selectThemeById(request.getSession().getAttribute("recruitId").toString());
        model.addAttribute("theme", theme);
        StudentInfo studentInfo = studentInfoService.selectStudentInfoById(studentId);

        if (studentInfo != null) {
            // 修改信息
            StudentFamInfo studentFamInfo = new StudentFamInfo();
            studentFamInfo.setStudentId(studentId);
            List<StudentFamInfo> studentFamList =
                    studentFamInfoService.selectAllByStudentFamInfo(studentFamInfo);
            studentInfo.setStudentFamInfoList(studentFamList);

            // 获取头像
            Attachement attachement =
                    attachementService.selectAttachementById(studentInfo.getPhotoAttId());
            if (attachement != null) {
                model.addAttribute("attachement", attachement);
                request.getSession().setAttribute("photo", attachement.getPath());
            }

            StudentEduInfo studentEduInfo = new StudentEduInfo();
            studentEduInfo.setStudentId(studentId);
            List<StudentEduInfo> studentEduList =
                    studentEduInfoService.selectAllByStudentEduInfo(studentEduInfo);

            List<StudentEduInfo> tempList = new ArrayList<StudentEduInfo>();
            for (int i = 0; i < 5; i++) {
                StudentEduInfo temp = new StudentEduInfo();
                temp.setStudentId(studentId);
                temp.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
                tempList.add(temp);
            }

            for (StudentEduInfo s : studentEduList) {
                tempList.set((Integer.parseInt(s.getEduCode()) - 1) < 0
                        ? 0
                        : Integer.parseInt(s.getEduCode()) - 1, s);
            }

            studentInfo.setStudentEduInfoList(tempList);
            // 转化日期格式
            String tempStr = "";
            for (StudentEduInfo stuEduInfo : studentEduList) {
                if (stuEduInfo.getEduGraduateTime() == null) {
                    continue;
                }
                tempStr = DateFormat.getDateInstance(DateFormat.MEDIUM)
                        .format(stuEduInfo.getEduGraduateTime());
                if (StringUtil.isEmpty(tempStr)) {
                    continue;
                }
                stuEduInfo.setEduGraduateTimeStr(tempStr);
            }
            try{
	            // 出生日期
	            tempStr =
	                    DateFormat.getDateInstance(DateFormat.MEDIUM).format(studentInfo.getBirthday());
	            studentInfo.setBirthdayStr(tempStr);
            }catch(Exception e){
            	e.printStackTrace();
            	studentInfo.setBirthdayStr("");
            }
            model.addAttribute("studentInfo", studentInfo);
            request.getSession().setAttribute("studentName", studentInfo.getName());

            // 获取简历审核状态
            StudentApplyInfo studentApplyInfo = new StudentApplyInfo();
            studentApplyInfo
                    .setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
            studentApplyInfo.setStudentId(studentId);
            studentApplyInfo =
                    studentApplyInfoService.selectObjectByStudentApplyInfo(studentApplyInfo);
            model.addAttribute("applyStatus",
                    studentApplyInfo == null ? "0" : studentApplyInfo.getApplyStatus());

            // 是否设置了密保
            List<SecurityQueInfo> sqiList = isSetSecretPro(studentId);
            if (sqiList == null || sqiList.size() == 0) {
                model.addAttribute("setSecuriy", "0");
            } else {
                model.addAttribute("setSecuriy", "1");
            }
        }

        // 民族
        List<Dictionary> mzs = DictionaryCache.getDictionaryByCode("mzdm");
        model.addAttribute("mzs", mzs);

        // 学位
        List<Dictionary> xws = DictionaryCache.getDictionaryByCode("xwlx");
        model.addAttribute("xws", xws);

        // 教育类型
        List<Dictionary> jys = DictionaryCache.getDictionaryByCode("jylx");
        model.addAttribute("jys", jys);

        // 政治面貌
        List<Dictionary> zzmms = DictionaryCache.getDictionaryByCode("zzmm");
        model.addAttribute("zzmms", zzmms);

        // 资格证种类
        List<Dictionary> jszgzls = DictionaryCache.getDictionaryByCode("jszgzl");
        model.addAttribute("jszgzls", jszgzls);

        // 专业技术资格
        List<Dictionary> zyjszgs = DictionaryCache.getDictionaryByCode("zyjszg");
        model.addAttribute("zyjszgs", zyjszgs);

        // 荣誉称号
        List<Dictionary> rychs = DictionaryCache.getDictionaryByCode("rych");
        model.addAttribute("rychs", rychs);

        // 考生类型
        List<Dictionary> kslxs = DictionaryCache.getDictionaryByCode("bylx");
        model.addAttribute("kslxs", kslxs);

        // 家庭关系
        List<Dictionary> jtcygxs = DictionaryCache.getDictionaryByCode("jtcygx");
        model.addAttribute("jtcygxs", jtcygxs);
        model.addAttribute("recruitItemId",
                request.getSession().getAttribute("recruitId").toString());
        response.setHeader("Access-Control-Allow-Origin", "*");
        //判断公告是否暂停
        JSONObject jo = isStopNotice(request);
        if (jo == null || !jo.getBooleanValue("flag")) {
            model.addAttribute("isStopNotice", "1");
        } else {
            model.addAttribute("isStopNotice", "0");
        }
        
        // 毕业生
        if (studentInfo.getStudentType() != null && studentInfo.getStudentType().equals("1")) {
            return "/student/registrationIndex.vm";
        } else {
            // 拆份荣誉编码
            ArrayList<String> list = new ArrayList<String>() {
                {
                    add("0");
                    add("0");
                    add("0");
                    add("0");
                    add("0");
                    add("0");
                }
            };
            if (StringUtil.isNotEmpty(studentInfo.getHonorCode())) {
                String[] strs = studentInfo.getHonorCode().split(",");
                for (String s : strs) {
                    list.set(Integer.parseInt(s), s);
                }
                setHonorCode(list, studentInfo);
            }
            return "/student/registrationNonGraIndex.vm";
        }
    }


    public void setHonorCode(List<String> list, StudentInfo si) {
        si.setRych0(list.get(0));
        si.setRych1(list.get(1));
        si.setRych2(list.get(2));
        si.setRych3(list.get(3));
        si.setRych4(list.get(4));
        si.setRych5(list.get(5));
    }
    
    /**
     * 是否设置了密保
     * @param sid
     * @return
     */
    public List<SecurityQueInfo>  isSetSecretPro(String sid) {
        SecurityQueInfo sqi = new SecurityQueInfo();
        sqi.setStudentId(sid);
        List<SecurityQueInfo> sqiList = securityQueInfoService.selectAllBySecurityQueInfo(sqi);
        return sqiList;
    }

    /**
     * 
     * @param schoolId
     * @param verifycode 区分毕业生 社会人士
     * @return
     */
    @RequestMapping("verifyApply")
    @ResponseBody
    public String verifyApply(String recruitJobId, String recruitDeptId,
            HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        String sid = request.getSession().getAttribute("sid").toString();
        if (StringUtil.isEmpty(sid)) {
            jo.put("code", "-1");
            jo.put("msg", "当前session过期或者您的账号在别处登录，请重新登录");
            return jo.toJSONString();
        }
        StudentInfo si = studentInfoService.selectStudentInfoById(sid);

        if (si == null) {
            jo.put("code", "-1");
            jo.put("msg", "当前session过期或者您的账号在别处登录，请重新登录");
            return jo.toJSONString();
        }
        //判断公告是否暂停
        jo = isStopNotice(request);
        if (jo == null || !jo.getBooleanValue("flag")) {
            return jo.toJSONString();
        }
        
        //是否过了注册报名的时间
        /*if(!isOverRegister(request)) {
            jo.put("code", "-1");
            jo.put("msg", "整个招聘流程已经过了注册报名环节，不能进行报名！");
            return jo.toJSONString();
        }*/

        if(!isOverRegisterTime(request)){
            jo.put("flag", false);
            jo.put("msg", "已经过了注册时间，不能进行报名！");
            return jo.toJSONString();
        }
        //修改之前先要检查考生的申请状态  根据状态判断是否能够修改
        StudentApplyInfo sai = new StudentApplyInfo();
        sai.setStudentId(sid);
        sai.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
        Integer checkCt = studentApplyInfoService.checkApplyStatus(sai);
        if (checkCt != null && checkCt > 0) {
            jo.put("flag", false);
            jo.put("msg", "您的申请处于待审状态，无法进行报名！");
            return jo.toJSONString();
        }
        

        if (si.getStudentType().equals("1")) {
            // 毕业生
            String jsonStr = verifyApplyForGra(recruitJobId, recruitDeptId, request, si);
            return jsonStr;
        } else if (si.getStudentType().equals("2")) {
            // 社会人士
            String jsonStr = verifyApplyForNonGra(recruitJobId, recruitDeptId, request, si);
            return jsonStr;
        } else {
            jo.put("code", "-1");
            jo.put("msg", "当前session过期或者您的账号在别处登录，请重新登录");
            return jo.toJSONString();
        }
    }


    /**
     * 校验毕业生
     * 
     * @param recruitJobId
     * @param recruitDeptId
     * @param request
     * @return
     */
    public String verifyApplyForGra(String recruitJobId, String recruitDeptId,
            HttpServletRequest request,StudentInfo si) {
        JSONObject jo = new JSONObject();
        if (StringUtil.isEmpty(recruitJobId) || StringUtil.isEmpty(recruitDeptId)) {
            jo.put("code", "-1");
            jo.put("msg", "请选择应聘岗位");
            return jo.toJSONString();
        }
        // 中职专业课类别的岗位
        String sid = request.getSession().getAttribute("sid").toString();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sid", sid);
        map.put("recruitId", request.getSession().getAttribute("recruitId").toString());
        map.put("schoolId", recruitDeptId);

        /*
         * 对于考生来说，一个招聘主题只能申报一个学校， 两种情况下： 1、考生未提交申请资料； 2、学校审核不通过，而且是非照片资料不通过
         */
        Integer isApplySchool = studentInfoService.isApplySchool(map);
        if (isApplySchool != null && isApplySchool > 0) {
            jo.put("code", "2000");
            jo.put("msg", "当前的招聘主题，您已经申报了其他的学校，不能申报当前学校了！");
            return jo.toJSONString();
        }

        Postset postset = postsetService.selectPostsetById(recruitJobId);
        if (postset == null) {
            jo.put("code", "-1");
            jo.put("msg", "选择的岗位不存在！");
            return jo.toJSONString();
        }

        StudentInfo studentInfo = studentInfoService.selectStudentInfoById(sid);

        // 查询现场资料确认、面试信息确认
        Information info = new Information();
        info.setProjectId(request.getSession().getAttribute("recruitId").toString());
        info.setPositionId(recruitJobId);
        info.setSchoolId(recruitDeptId);
        info.setType("1");
        info.setStatus("1");// 已发布
        Information infoInter = informationService.selectObjectByInformation(info);

        if (infoInter == null) {
            jo.put("code", "0008");
            jo.put("msg", "学校端没有完成面试资料和现场资料确认，不能报名！");
            return jo.toJSONString();
        }

        info.setPositionId("");
        info.setType("2");
        Information infoPlace = informationService.selectObjectByInformation(info);

        if (infoPlace == null) {
            jo.put("code", "0008");
            jo.put("msg", "学校端没有完成面试资料和现场资料确认，不能报名！");
            return jo.toJSONString();
        }

        // 全部报考人员必须是全日制本科学士及以上
        StudentEduInfo studentEduInfo = new StudentEduInfo();
        studentEduInfo.setStudentId(sid);
        List<StudentEduInfo> studentEduInfos =
                studentEduInfoService.selectAllByStudentEduInfo(studentEduInfo);
        // 查询出本科和研究生学历
        List<StudentEduInfo> studentEduNeed = new ArrayList<StudentEduInfo>();
        for (StudentEduInfo sei : studentEduInfos) {
            if (sei.getEduCode() != null && Integer.parseInt(sei.getEduCode()) > 2) {
                studentEduNeed.add(sei);
            }
        }
        
        if(StringUtil.isEmpty(si.getCertiType())||"0".equals(si.getCertiType())){
        	if(StringUtil.isEmpty(si.getTeachingAbilityScore())&&"0".equals(si.getInternshipCertification())){
        		jo.put("code", "0015");
                jo.put("msg", "您尚未通过相应的教育教学能力测试或尚未取得教育实习证明！");
                return jo.toJSONString();
        	}
        }
        
        /************************************************** 本科以上学历判断 *************************************************************/
        if (studentEduNeed == null || studentEduNeed.size() == 0) {
            jo.put("code", "0002");
            jo.put("msg", "全部报考人员必须是全日制本科学士及以上！");
            return jo.toJSONString();
        }

        // 用于计算毕业年份
        List<StudentEduInfo> graYearEdu = new ArrayList<StudentEduInfo>();
        for (StudentEduInfo s : studentEduNeed) {
            graYearEdu.add(s);
        }


        /**************************** 学士学位以上判断 *********************/
        // 已有学历信息中进一步筛选满足学位要求的(不满足放宽条件至少要学士学位)
        List<StudentEduInfo> tmpList = new ArrayList<>();
        for (StudentEduInfo sei : studentEduNeed) {
            if (sei.getEduBachelorCode() != null
                    && Integer.parseInt(sei.getEduBachelorCode()) >= 2) {
                tmpList.add(sei);
            }
        }
        studentEduNeed = tmpList;
        if (studentEduNeed.size() == 0) {
            jo.put("code", "0002");
            jo.put("msg", "全部报考人员必须是全日制本科学士及以上！");
            return jo.toJSONString();
        }
        /**************************** 学士学位以上判断 end *********************/
        /**************************** 全日制以上判断 *********************/
        tmpList = new ArrayList<>();
        for (StudentEduInfo sei : studentEduNeed) {
            if ((sei.getEduType() != null && sei.getEduType().equals("2"))) {
                tmpList.add(sei);
            }
        }
        studentEduNeed = tmpList;
        if (studentEduNeed.size() == 0) {
            jo.put("code", "0002");
            jo.put("msg", "全部报考人员必须是全日制本科学士及以上！");
            return jo.toJSONString();
        }
        /**************************** 全日制以上判断end *********************/
        // 过滤掉有相近专业的，只要有相近专业，就需要送往教育局审核
        List<StudentEduInfo> hasNotSimilarProList = new ArrayList<StudentEduInfo>();
        boolean isSimilar = false;
        for (StudentEduInfo sei : studentEduNeed) {
            // 先判断有没有专业
            if (StringUtil.isNotEmpty(sei.getEduSchoolName())
                    && sei.getEduProfessionCode() == null) {
                jo.put("code", "0013");
                jo.put("msg", "您有专业没有填写，不能参与报考！");
                return jo.toJSONString();
            }

            // 有相近专业
            if (sei.getIsSimilarTerm() != null && sei.getIsSimilarTerm().equals("1")) {
                isSimilar = true;
                break;
            }
            if (sei.getIsSimilarTerm() != null && sei.getIsSimilarTerm().equals("0")) {
                hasNotSimilarProList.add(sei);
            }
        }

        if (isSimilar) {
            hasNotSimilarProList = new ArrayList<StudentEduInfo>();
        }

        Position position = new Position();
        position.setStationId(recruitJobId);// 岗位id
        position.setSchoolId(recruitDeptId);// 学校id
        position.setProjectId(request.getSession().getAttribute("recruitId").toString());// 招聘主题id
        position = positionService.selectObjectByPosition(position);
        // 用于记录专业满足条件的学历信息
        List<StudentEduInfo> seiNeedForProfess = new ArrayList<StudentEduInfo>();
        if (hasNotSimilarProList != null && hasNotSimilarProList.size() > 0) {
            /************************************************** 学校端的专业限制 *************************************************************/
            if (position != null && position.getIsLimitProfession() != null
                    && position.getIsLimitProfession().equals("1")) {
                PositionDomain pd = new PositionDomain();
                pd.setPlanApplyId(position.getPlanApplyId());// 招聘计划
                pd.setPositionId(position.getStationId());
                pd.setSchoolId(position.getSchoolId());
                StringBuilder sb = new StringBuilder();
                List<PositionDomain> pdList = positionDomainService.selectAllByPositionDomain(pd);// 查询指定条件下的所有限制专业
                List<Specialty> speciaList = new ArrayList<Specialty>();
                for (PositionDomain domain : pdList) {
                    // 专业 7位
                    if (domain.getDomainId().length() == 7) {
                        sb.append(domain.getDomainId()).append(" ");
                    }

                    // 二级学科 5位
                    if (domain.getDomainId().length() == 5) {
                        map.put("keySpecial", domain.getDomainId());
                        map.put("keyLength", "7");
                        speciaList = specialService.getSpecialListByLv(map);
                    }

                    // 一级学科 3位
                    if (domain.getDomainId().length() == 3) {
                        map.put("keySpecial", domain.getDomainId());
                        map.put("keyLength", "7");
                        speciaList = specialService.getSpecialListByLv(map);
                    }

                    for (Specialty special : speciaList) {
                        sb.append(special.getCode()).append(" ");
                    }

                }

                int professCount = 0;
                for (StudentEduInfo sei : hasNotSimilarProList) {
                    // 只处理没有相近专业的
                    if (sb.indexOf(sei.getEduProfessionCode()) == -1) {
                        professCount++;
                    } else {
                        seiNeedForProfess.add(sei);
                    }
                }


                if (professCount == hasNotSimilarProList.size()) {
                    jo.put("code", "0006");
                    jo.put("msg", "您的学历满足全日制本科学士及以上的要求，但是该学历对应的专业不满足限制条件！");
                    return jo.toJSONString();
                }
            } else {
                seiNeedForProfess = studentEduNeed;
            }

            StudentEduInfo seiXlhighestNeedForPro = getXlHighest(seiNeedForProfess);
            /************************************************** 学历限制 *************************************************************/
            // 岗位的学历限制条件
            // 学历
            if (position != null && position.getIsLimitEducation() != null
                    && position.getIsLimitEducation().equals("1")) {
                String limitEducation = position.getLimitEducation();// 2
                String limitEducationCond = position.getLimitEducationConditition();
                Dictionary dicXl = new Dictionary();
                dicXl.setCode("xllx");
                dicXl.setValue(limitEducation);
                dicXl = dictionaryService.selectObjectByDictionary(dicXl);
                if (limitEducationCond.equals(">=") && seiXlhighestNeedForPro != null
                        && Integer.parseInt(seiXlhighestNeedForPro.getEduCode()) < Integer
                                .parseInt(limitEducation)) {
                    jo.put("code", "0004");
                    jo.put("msg", "您的专业符合要求，但是您的岗位限制的学历在" + dicXl.getText() + "及以上！");
                    return jo.toJSONString();
                }

                // limitEducation以下
                if (limitEducationCond.equals("<=")
                        && Integer.parseInt(seiXlhighestNeedForPro.getEduCode()) > Integer
                                .parseInt(limitEducation)) {
                    jo.put("code", "0005");
                    jo.put("msg", "您的专业符合要求，岗位限制的学历在" + dicXl.getText() + "及以下！");
                    return jo.toJSONString();
                }

            }

            /************************************************** 教育类型限制 *************************************************************/
            if (position != null && position.getIsLimitEdu() != null
                    && position.getIsLimitEdu().equals("1")) {
                String limitEdu = position.getLimitEdu();// 2 全日制 1非全日制
                String limitEduCond = position.getLimitEduConditition();
                Dictionary dicXl = new Dictionary();
                dicXl.setCode("jylx");
                dicXl.setValue(limitEdu);
                dicXl = dictionaryService.selectObjectByDictionary(dicXl);

                int highLimitEdu = 0, lowLimitEdu = 0;
                for (StudentEduInfo sei : seiNeedForProfess) {
                    // 专业满足的情况下，要判断对应的教育类型有没有
                    if (StringUtil.isNotEmpty(sei.getEduSchoolName()) && sei.getEduType() == null) {
                        jo.put("code", "0015");
                        jo.put("msg", "您的专业符合要求，但是对应的教育类型没有填写！");
                        return jo.toJSONString();
                    }

                    if (limitEduCond.equals(">=")
                            && Integer.parseInt(sei.getEduType()) < Integer.parseInt(limitEdu)) {
                        highLimitEdu++;
                    }

                    if (limitEduCond.equals("<=")
                            && Integer.parseInt(sei.getEduType()) > Integer.parseInt(limitEdu)) {
                        lowLimitEdu++;
                    }
                }

                if (seiNeedForProfess.size() == highLimitEdu) {
                    jo.put("code", "0004");
                    jo.put("msg", "您的专业符合要求，岗位限制的教育类型在" + dicXl.getText() + "及以上！");
                    return jo.toJSONString();
                }

                if (seiNeedForProfess.size() == lowLimitEdu) {
                    jo.put("code", "0005");
                    jo.put("msg", "您的专业符合要求，岗位限制的教育类型在" + dicXl.getText() + "及以下！");
                    return jo.toJSONString();
                }
            }
            /************************************************** 学位限制 *************************************************************/
            if (position != null && position.getIsLimitDegree() != null
                    && position.getIsLimitDegree().equals("1")) {
                String limitDeg = position.getLimitDegree();// 1、无 2、学士 3、硕士 4、博士
                String limitDegCond = position.getLimitDegreeConditition();
                Dictionary dicXl = new Dictionary();
                dicXl.setCode("xwlx");
                dicXl.setValue(limitDeg);
                dicXl = dictionaryService.selectObjectByDictionary(dicXl);

                int highDegCount = 0, lowDegCount = 0;
                for (StudentEduInfo sei : seiNeedForProfess) {
                    // 专业满足的情况下，要判断对应的学位有没有
                    if (StringUtil.isNotEmpty(sei.getEduSchoolName())
                            && sei.getEduBachelorCode() == null) {
                        jo.put("code", "0014");
                        jo.put("msg", "您的专业符合要求，但是对应的学位没有填写！");
                        return jo.toJSONString();
                    }

                    if (sei.getEduBachelorCode() != null && limitDegCond.equals(">=") && Integer
                            .parseInt(sei.getEduBachelorCode()) < Integer.parseInt(limitDeg)) {
                        highDegCount++;
                    }
                    if (sei.getEduBachelorCode() != null && limitDegCond.equals("<=") && Integer
                            .parseInt(sei.getEduBachelorCode()) > Integer.parseInt(limitDeg)) {
                        lowDegCount++;
                    }

                }

                if (seiNeedForProfess.size() == highDegCount) {
                    jo.put("code", "0004");
                    jo.put("msg", "您的专业符合要求，岗位限制的学位在" + dicXl.getText() + "及以上！");
                    return jo.toJSONString();
                }

                if (seiNeedForProfess.size() == lowDegCount) {
                    jo.put("code", "0005");
                    jo.put("msg", "您的专业符合要求，岗位限制的学位在" + dicXl.getText() + "及以下！");
                    return jo.toJSONString();
                }
            }

        }

        /************************************************** 毕业时间判断 *************************************************************/
        StudentEduInfo seiXlhighest = getXlHighest(graYearEdu);
        Integer gradYear = 0;// 毕业年份
        if (seiXlhighest.getEduGraduateTime() != null) {
            Calendar ca = Calendar.getInstance();
            ca.setTime(seiXlhighest.getEduGraduateTime());
            gradYear = ca.get(Calendar.YEAR);
        } else {
            gradYear = 0;
        }

        // 毕业时间不符合毕业生的要求，（2017、2016、2015年毕业办理暂缓就业）
        if (studentInfo.getStudentType().equals("1")) {
            Calendar ca = Calendar.getInstance();
            Integer curYear = ca.get(Calendar.YEAR);// 2016
            if (gradYear > (curYear + 1) || gradYear < (curYear - 1)) {
                jo.put("code", "0003");
                jo.put("msg", "您的毕业时间不符合要求（2017年毕业或者2016年毕业或者2015年毕业并办理暂缓就业的）！");
                return jo.toJSONString();
            }
        }

        /************************************************** 学校端的招聘对象限制 *************************************************************/
        if (position != null && position.getIsLimitRecruit() != null
                && position.getIsLimitRecruit().equals("1") && position.getLimitRecruit() != null) {
            if (!studentInfo.getStudentType().equals(position.getLimitRecruit())
                    && (position.getLimitRecruit().equals("1")
                            || position.getLimitRecruit().equals("2"))) {
                jo.put("code", "0010");
                jo.put("msg", "您不符合招聘对象限制！");
                return jo.toJSONString();
            }
        }

        /************************************************** 年龄限制 *************************************************************/
        if (position != null && position.getIsLimitAge() != null
                && position.getIsLimitAge().equals("1")) {
            if (position.getLimitAge() != null && studentInfo.getBirthday() != null) {
                Integer age = position.getLimitAge().intValue();
                Integer myAge = getAgeByBirthday(studentInfo.getBirthday());
                System.out.println(myAge);
                if (myAge == -1) {
                    jo.put("code", "0011");
                    jo.put("msg", "您的出生日期竟然比现在日期还要晚！");
                    return jo.toJSONString();
                }
            }
        }

        /**
         * 不论是否是哪一次报名，都需要通过系统的自动审核
         */
        map.put("recruitJobId", recruitJobId);
        Integer isApply = studentInfoService.isApplyPosition(map);
        if (isApply != null && isApply > 0) {
            jo.put("code", "1000");
            jo.put("msg", "恭喜你，通过系统自动审核,并且当前岗位您已经申报过了！");
            return jo.toJSONString();
        }

        jo.put("code", "0000");
        jo.put("msg", "恭喜你，通过系统自动审核！");
        return jo.toJSONString();
    }

    /**
	* 校验社会人士
	* @param recruitJobId 岗位ID
	* @param recruitDeptId 单位ID
	* @param request
	* @param si 学生信息
	* @return
	*/
	public String verifyApplyForNonGra(String recruitJobId, String recruitDeptId, HttpServletRequest request, StudentInfo studentInfo) {
		JSONObject jo = new JSONObject();
		if (studentInfo == null) {
		    jo.put("code", "-1");
		jo.put("msg", "考生信息不存在，请重新登录！");
		    return jo.toJSONString();
		}
		if (StringUtil.isEmpty(recruitJobId) || StringUtil.isEmpty(recruitDeptId)) {
			jo.put("code", "-1");
			jo.put("msg", "请选择应聘岗位");
			return jo.toJSONString();
		}
		//获取岗位信息
		Postset postset = postsetService.selectPostsetById(recruitJobId);
		if (postset == null) {
			jo.put("code", "-1");
			jo.put("msg", "选择的岗位不存在！");
			return jo.toJSONString();
		}
		
		// 查询现场资料确认、面试信息确认
		Information info = new Information();
		info.setProjectId(request.getSession().getAttribute("recruitId").toString());
		info.setPositionId(recruitJobId);
		info.setSchoolId(recruitDeptId);
		info.setType("1");
		info.setStatus("1");//已发布
		Information infoInter = informationService.selectObjectByInformation(info);

		if (infoInter == null) {
			jo.put("code", "0008");
			jo.put("msg", "学校端没有完成面试资料和现场资料确认，不能报名！");
			return jo.toJSONString();
		}

		info.setPositionId("");
		info.setType("2");
		Information infoPlace = informationService.selectObjectByInformation(info);

		if (infoPlace == null) {
			jo.put("code", "0008");
			jo.put("msg", "学校端没有完成面试资料和现场资料确认，不能报名！");
			return jo.toJSONString();
		}
		String sid = studentInfo.getId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("recruitId", request.getSession().getAttribute("recruitId").toString());
		map.put("schoolId", recruitDeptId);
		//验证是否已经申报了当前主题的其他学校
		/*
		 * 对于考生来说，一个招聘主题只能申报一个学校， 两种情况下： 1、考生未提交申请资料； 2、学校审核不通过，而且是非照片资料不通过
		 */
		Integer isApplySchool = studentInfoService.isApplySchool(map);
		if (isApplySchool != null && isApplySchool > 0) {
			jo.put("code", "2000");
			jo.put("msg", "当前的招聘主题，您已经申报了其他的学校，不能申报当前学校了！");
			return jo.toJSONString();
		}

/************************************************** 非中职专业课的报考对象必须是毕业生 *************************************************************/
		if (postset.getIszz() != null && postset.getIszz().equals("0")) {
			jo.put("code", "0001");
			jo.put("msg", "当前岗位属于非中职专业课的，报考对象必须是毕业生！");
			return jo.toJSONString();
		}
/************************************中职专业课处理******************************************/
		//获取考生填报的所有学历信息
		StudentEduInfo studentEduInfo = new StudentEduInfo();
		studentEduInfo.setStudentId(sid);
		studentEduInfo.setEmployItemsId(request.getSession().getAttribute("recruitId").toString());
		List<StudentEduInfo> studentEduInfos = studentEduInfoService.selectAllByStudentEduInfo(studentEduInfo);
		if(studentEduInfos==null||studentEduInfos.size()==0){
			jo.put("code", "0001");
			jo.put("msg", "请填写至少一项学历信息！");
			return jo.toJSONString();
		}
/**********************************放宽条件判断begin********************************************/
		//设定默认不满足放宽条件
		boolean needNewXl = false;
		//荣誉称号满足放宽条件
		if(studentInfo.getHonorCode() != null && (studentInfo.getHonorCode().indexOf("1") > -1	|| studentInfo.getHonorCode().indexOf("2") > -1	|| studentInfo.getHonorCode().indexOf("3") > -1	|| studentInfo.getHonorCode().indexOf("4") > -1)){
			needNewXl = true;
		//荣誉称号不满足,但是是合同制教师,也设为满足放宽条件
		}else if(studentInfo.getContractTeacher()!=null&&"1".equals(studentInfo.getContractTeacher())){//合同制教师
		    needNewXl = true;
		}else{
			needNewXl = false;
		}
/**********************************放宽条件判断end******************************************************/
/************************************************** 系统自动审核学历判断 *************************************************************/
		// 查询出满足系统自动审核的学历信息
		List<StudentEduInfo> studentEduNeed = new ArrayList<>();
		if(needNewXl){//满足放宽条件的,查询出大专以上学历
			//XXX 先不做判断
			studentEduNeed=studentEduInfos;
			/*for (StudentEduInfo sei : studentEduInfos) {
				if (sei.getEduCode() != null && Integer.parseInt(sei.getEduCode()) > 1) {
					studentEduNeed.add(sei);
				}
			}
			if(studentEduNeed.size() == 0){
				jo.put("code", "0002");
		        jo.put("msg", "满足放宽条件的考生至少需要大专学历！");
		        return jo.toJSONString();
			}*/
			/**************************** 学士学位以上判断 *********************/
			//XXX 先不做判断
			//List<StudentEduInfo> tmpList=new ArrayList<>();
			//已有学历信息中进一步筛选满足学位要求的(满足放宽条件的大专没有学位要求)
			//TODO 大专没有学位
			/**************************** 学士学位以上判断end *********************/
			/**************************** 全日制以上判断 *********************/
			//XXX 先不做判断
			/*tmpList=new ArrayList<>();
			for (StudentEduInfo sei : studentEduNeed) {
			    if ((sei.getEduType() != null && sei.getEduType().equals("2"))) {
			    	tmpList.add(sei);
			    }
			}
			studentEduNeed=tmpList;
			if(studentEduNeed.size()==0){
				jo.put("code", "0002");
		        jo.put("msg", "满足放宽条件,但是大专以上学历中不满足全日制要求！");
		        return jo.toJSONString();
			}*/
			/**************************** 全日制以上判断end *********************/

			/************************************************** 学校端的招聘对象限制(社会人士/毕业生) *************************************************************/
			Position position = new Position();
			position.setStationId(recruitJobId);// 岗位id
			position.setSchoolId(recruitDeptId);// 学校id
			position.setProjectId(request.getSession().getAttribute("recruitId").toString());// 招聘主题id
			position = positionService.selectObjectByPosition(position);
			if (position!=null&&position.getIsLimitRecruit()!= null&&position.getIsLimitRecruit().equals("1")&&position.getLimitRecruit()!=null&&!"0".equals(position.getLimitRecruit())) {
			if (!studentInfo.getStudentType().equals(position.getLimitRecruit())) {
					jo.put("code", "0010");
					jo.put("msg", "您不符合招聘对象限制！");
			        return jo.toJSONString();
			    }
			}
			/*****************************招聘对象限制end*************************************/
			/************************************************** 是否东莞莞免费师范生 *************************************************************/
			if (position != null && position.getIsEducationStudent() != null
			        && position.getIsEducationStudent().equals("1")
			&& (studentInfo.getFreeStudent() == null
			        || studentInfo.getFreeStudent().equals("0"))) {
			jo.put("code", "0009");
			jo.put("msg", "该岗位限定东莞免费师范生才能报考！");
			    return jo.toJSONString();
			}
			/*****************************是否东莞莞免费师范生end************************/
			/***************************** 年龄限制 *******************************/
			if (position != null && position.getIsLimitAge() != null && position.getIsLimitAge().equals("1")) {
				if (position.getLimitAge() != null && studentInfo.getBirthday() != null) {
				    Integer age = position.getLimitAge().intValue();
				    Integer myAge = getAgeByBirthday(studentInfo.getBirthday());
				    System.out.println(myAge);
				    if (myAge == -1) {
				    	jo.put("code", "0011");
				    	jo.put("msg", "您的出生日期竟然比现在日期还要晚！");
				    	return jo.toJSONString();
					}
					//社会人士需要检查年龄
					if (studentInfo.getStudentType() != null && studentInfo.getStudentType().equals("2")) {
						if (position.getLimitAgeConditition() != null && position.getLimitAgeConditition().equals(">=") && myAge < age) {
							jo.put("code", "0012");
							jo.put("msg", "该岗位限制的年龄在" + age + "以上！");
						    return jo.toJSONString(); 
						}
					
						if (position.getLimitAgeConditition() != null && position.getLimitAgeConditition().equals("<=") && myAge > age) {
							jo.put("code", "0013");
							jo.put("msg", "该岗位限制的年龄在" + age + "以下！");
					        return jo.toJSONString(); 
						}
					}
				}
			}
			/***********************年龄限制end******************************/
			/**************************** 校验成功 *********************/
			Map<String, Object> searchMap = new HashMap<String, Object>();
			searchMap.put("sid", sid);
			searchMap.put("recruitId", request.getSession().getAttribute("recruitId").toString());
			searchMap.put("schoolId", recruitDeptId);
			searchMap.put("recruitJobId", recruitJobId);
			Integer applyCount = studentInfoService.isApplyPosition(searchMap);
			
			if (applyCount != null && applyCount > 0) {
			    jo.put("code", "1000");
			    jo.put("msg", "恭喜你，通过系统自动审核,并且当前岗位您已经申报过了！");
			    return jo.toJSONString();
			}
			jo.put("code", "0000");
			jo.put("msg", "恭喜你，通过系统自动审核！");
		    return jo.toJSONString();
			/**************************** 校验成功end *********************/
			
		}else{
			//不满足放宽条件的,查询出本科以上学历
			for (StudentEduInfo sei : studentEduInfos) {
				if (sei.getEduCode() != null && Integer.parseInt(sei.getEduCode()) > 2) {
					studentEduNeed.add(sei);
				}
			}
			if(studentEduNeed.size() == 0){
				jo.put("code", "0002");
				jo.put("msg", "全部报考人员必须是全日制本科学士及以上！");
			    return jo.toJSONString();
			}
			/**************************** 学士学位以上判断 *********************/
			//已有学历信息中进一步筛选满足学位要求的(不满足放宽条件至少要学士学位)
			List<StudentEduInfo> tmpList=new ArrayList<>();
			for (StudentEduInfo sei : studentEduNeed) {
				if (sei.getEduBachelorCode() != null && Integer.parseInt(sei.getEduBachelorCode())>=2) {
					tmpList.add(sei);
			    }
			}
			studentEduNeed=tmpList;
			if(studentEduNeed.size()==0){
				jo.put("code", "0002");
				jo.put("msg", "全部报考人员必须是全日制本科学士及以上！");
			    return jo.toJSONString();
			}
			/**************************** 学士学位以上判断 end*********************/
			/**************************** 全日制以上判断 *********************/
			tmpList=new ArrayList<>();
			for (StudentEduInfo sei : studentEduNeed) {
			    if ((sei.getEduType() != null && sei.getEduType().equals("2"))) {
			    	tmpList.add(sei);
			    }
			}
			studentEduNeed=tmpList;
			if(studentEduNeed.size()==0){
				jo.put("code", "0002");
		        jo.put("msg", "全部报考人员必须是全日制本科学士及以上！");
		        return jo.toJSONString();
			}
			/**************************** 全日制以上判断end *********************/
		}
/*************************************自动审核结束,以下为学校审核*************************************************************/
		//过滤掉有相近专业的，只要有相近专业，就需要送往教育局审核
		List<StudentEduInfo> hasNotSimilarProList = new ArrayList<StudentEduInfo>();
		boolean isSimilar = false;
		for (StudentEduInfo sei : studentEduNeed) {
			//先判断有没有专业
			if (StringUtil.isNotEmpty(sei.getEduSchoolName()) && sei.getEduProfessionCode() == null) {
				jo.put("code", "0013");
				jo.put("msg", "您有专业没有填写，不能参与报考！");
				return jo.toJSONString();
			}
			//有相近专业
			if (sei.getIsSimilarTerm() != null && sei.getIsSimilarTerm().equals("1")) {
				isSimilar = true;
				break;
			}
			if (sei.getIsSimilarTerm() != null && sei.getIsSimilarTerm().equals("0")) {
				hasNotSimilarProList.add(sei);
			}
		}
		if (isSimilar) {
			hasNotSimilarProList = new ArrayList<StudentEduInfo>();
		}

		Position position = new Position();
		position.setStationId(recruitJobId);// 岗位id
		position.setSchoolId(recruitDeptId);// 学校id
		position.setProjectId(request.getSession().getAttribute("recruitId").toString());// 招聘主题id
		position = positionService.selectObjectByPosition(position);
		//用于记录专业满足条件的学历信息
		List<StudentEduInfo> seiNeedForProfess = new ArrayList<StudentEduInfo>();
		if (hasNotSimilarProList != null && hasNotSimilarProList.size() > 0) {
			/************************************************** 学校端的专业限制 *************************************************************/
			if (position != null && position.getIsLimitProfession() != null
					&& position.getIsLimitProfession().equals("1")) {
		PositionDomain pd = new PositionDomain();
		pd.setPlanApplyId(position.getPlanApplyId());// 招聘计划
		pd.setPositionId(position.getStationId());
		pd.setSchoolId(position.getSchoolId());
		StringBuilder sb = new StringBuilder();
		List<PositionDomain> pdList = positionDomainService.selectAllByPositionDomain(pd);// 查询指定条件下的所有限制专业
		List<Specialty> speciaList = new ArrayList<Specialty>();
		for (PositionDomain domain : pdList) {
		    // 专业 7位
		if (domain.getDomainId().length() == 7) {
		    sb.append(domain.getDomainId()).append(" ");
		}
		
		// 二级学科 5位
		if (domain.getDomainId().length() == 5) {
		    map.put("keySpecial", domain.getDomainId());
		map.put("keyLength", "7");
		    speciaList = specialService.getSpecialListByLv(map);
		}
		
		// 一级学科 3位
		if (domain.getDomainId().length() == 3) {
		    map.put("keySpecial", domain.getDomainId());
		map.put("keyLength", "7");
		    speciaList = specialService.getSpecialListByLv(map);
		}
		
		for (Specialty special : speciaList) {
		    sb.append(special.getCode()).append(" ");
		        }
		
		    }
		
		   
		    int professCount = 0;
		    for (StudentEduInfo sei : hasNotSimilarProList) {
		        // 只处理没有相近专业的
		    if(sb.indexOf(sei.getEduProfessionCode()) == -1) {
		        professCount++;
		    } else {
		        seiNeedForProfess.add(sei);
		    }
		}
		
		if (professCount == hasNotSimilarProList.size()) {
		    jo.put("code", "0006");
		    jo.put("msg", "您的学历满足全日制本科学士及以上的要求，但是该学历对应的专业不满足限制条件！");
		        return jo.toJSONString();
		    }
		}else{
		    seiNeedForProfess=studentEduNeed;
		}
		
		StudentEduInfo seiXlhighestNeedForPro = getXlHighest(seiNeedForProfess);
		
		/************************************************** 学历限制 *************************************************************/
		// 岗位的学历限制条件
		// 学历
		if (position != null && position.getIsLimitEducation() != null
		        && position.getIsLimitEducation().equals("1")) {
		String limitEducation = position.getLimitEducation();// 2
		String limitEducationCond = position.getLimitEducationConditition();
		Dictionary dicXl = new Dictionary();
		dicXl.setCode("xllx");
		dicXl.setValue(limitEducation);
		dicXl = dictionaryService.selectObjectByDictionary(dicXl);
		if (limitEducationCond.equals(">=") && seiXlhighestNeedForPro != null && Integer
		    .parseInt(seiXlhighestNeedForPro.getEduCode()) < Integer.parseInt(limitEducation)) {
		jo.put("code", "0004");
		jo.put("msg", "您的专业符合要求，但是您的岗位限制的学历在" + dicXl.getText() + "及以上！");
		    return jo.toJSONString();
		}
		
		// limitEducation以下
		if (limitEducationCond.equals("<=") && Integer
		    .parseInt(seiXlhighestNeedForPro.getEduCode()) > Integer.parseInt(limitEducation)) {
		jo.put("code", "0005");
		jo.put("msg", "您的专业符合要求，岗位限制的学历在" + dicXl.getText() + "及以下！");
		        return jo.toJSONString();
		    }
		
		}
		
		/************************************************** 教育类型限制 *************************************************************/
		if (position != null && position.getIsLimitEdu() != null
		        && position.getIsLimitEdu().equals("1")) {
		String limitEdu = position.getLimitEdu();// 2 全日制 1非全日制
		String limitEduCond = position.getLimitEduConditition();
		Dictionary dicXl = new Dictionary();
		dicXl.setCode("jylx");
		dicXl.setValue(limitEdu);
		dicXl = dictionaryService.selectObjectByDictionary(dicXl);
		
		int highLimitEdu = 0, lowLimitEdu = 0;
		for (StudentEduInfo sei : seiNeedForProfess) {
		    //专业满足的情况下，要判断对应的教育类型有没有
		if (StringUtil.isNotEmpty(sei.getEduSchoolName()) && sei.getEduType() == null) {
		    jo.put("code", "0015");
		jo.put("msg", "您的专业符合要求，但是对应的教育类型没有填写！");
		    return jo.toJSONString();
		}
		
		if (limitEduCond.equals(">=")
		        && Integer.parseInt(sei.getEduType()) < Integer.parseInt(limitEdu)) {
		    highLimitEdu++;
		}
		
		if (limitEduCond.equals("<=")
		            && Integer.parseInt(sei.getEduType()) > Integer.parseInt(limitEdu)) {
		        lowLimitEdu++;
		    }
		}
		
		if (seiNeedForProfess.size() == highLimitEdu) {
		    jo.put("code", "0004");
		jo.put("msg", "您的专业符合要求，岗位限制的教育类型在" + dicXl.getText() + "及以上！");
		    return jo.toJSONString();
		}
		
		if (seiNeedForProfess.size() == lowLimitEdu) {
		    jo.put("code", "0005");
		jo.put("msg", "您的专业符合要求，岗位限制的教育类型在" + dicXl.getText() + "及以下！");
		        return jo.toJSONString();
		    }
		}
		
		/************************************************** 学位限制 *************************************************************/
		if (position != null && position.getIsLimitDegree() != null
		        && position.getIsLimitDegree().equals("1")) {
		String limitDeg = position.getLimitDegree();// 1、无 2、学士 3、硕士 4、博士
		String limitDegCond = position.getLimitDegreeConditition();
		Dictionary dicXl = new Dictionary();
		dicXl.setCode("xwlx");
		dicXl.setValue(limitDeg);
		dicXl = dictionaryService.selectObjectByDictionary(dicXl);
		
		int highDegCount = 0, lowDegCount = 0;
		for (StudentEduInfo sei : seiNeedForProfess) {
		    //专业满足的情况下，要判断对应的学位有没有
		if (StringUtil.isNotEmpty(sei.getEduSchoolName()) && sei.getEduBachelorCode() == null) {
		    jo.put("code", "0014");
		jo.put("msg", "您的专业符合要求，但是对应的学位没有填写！");
		    return jo.toJSONString();
		}
		
		if (sei.getEduBachelorCode() != null && limitDegCond.equals(">=") && Integer
		        .parseInt(sei.getEduBachelorCode()) < Integer.parseInt(limitDeg)) {
		    highDegCount++;
		}
		if (sei.getEduBachelorCode() != null && limitDegCond.equals("<=") && Integer
		            .parseInt(sei.getEduBachelorCode()) > Integer.parseInt(limitDeg)) {
		        lowDegCount++;
		    }
		
		}
		
		if (seiNeedForProfess.size() == highDegCount) {
		    jo.put("code", "0004");
		jo.put("msg", "您的专业符合要求，岗位限制的学位在" + dicXl.getText() + "及以上！");
		    return jo.toJSONString();
		}
		
		if (seiNeedForProfess.size() == lowDegCount) {
		    jo.put("code", "0005");
		jo.put("msg", "您的专业符合要求，岗位限制的学位在" + dicXl.getText() + "及以下！");
		                return jo.toJSONString();
		            }
		        }
		        
		    }
		   
		    
		    /************************************************** 毕业时间判断 *************************************************************/
		StudentEduInfo seiXlhighest = getXlHighest(studentEduNeed);
		Integer gradYear = 0;// 毕业年份
		if (seiXlhighest.getEduGraduateTime() != null) {
		    Calendar ca = Calendar.getInstance();
		    ca.setTime(seiXlhighest.getEduGraduateTime());
		    gradYear = ca.get(Calendar.YEAR);
		} else {
		    gradYear = 0;
		}
		
		// 毕业时间不符合毕业生的要求，（2017、2016、2015年毕业办理暂缓就业）
		if (studentInfo.getStudentType().equals("1")) {
		Calendar ca = Calendar.getInstance();
		Integer curYear = ca.get(Calendar.YEAR);// 2016
		if (gradYear > (curYear + 1) || gradYear < (curYear - 1)) {
		    jo.put("code", "0003");
		jo.put("msg", "您的毕业时间不符合要求（2017年毕业或者2016年毕业或者2015年毕业并办理暂缓就业的）！");
		        return jo.toJSONString();
		    }
		}
		
		/************************************************** 学校端的招聘对象限制 *************************************************************/
		if (position != null && position.getIsLimitRecruit() != null
		        && position.getIsLimitRecruit().equals("1") && position.getLimitRecruit() != null) {
		if (!studentInfo.getStudentType().equals(position.getLimitRecruit())
		        && (position.getLimitRecruit().equals("1")
		|| position.getLimitRecruit().equals("2"))) {
		jo.put("code", "0010");
		jo.put("msg", "您不符合招聘对象限制！");
		        return jo.toJSONString();
		    }
		
		}
		/************************************************** 是否东莞莞免费师范生 *************************************************************/
		if (position != null && position.getIsEducationStudent() != null
		        && position.getIsEducationStudent().equals("1")
		&& (studentInfo.getFreeStudent() == null
		        || studentInfo.getFreeStudent().equals("0"))) {
		jo.put("code", "0009");
		jo.put("msg", "该岗位限定东莞免费师范生才能报考！");
		    return jo.toJSONString();
		}
		
		/************************************************** 专业技术资格种类与岗位学科对应   只有社会人士才走这个*************************************************************/
		/*Postset ps = postsetService.selectPostsetById(position.getStationId());
		if (ps != null && studentInfo.getStudentType().equals("2")) {
		String xklb = ps.getSubject();
		map.put("gwxk", xklb);
		map.put("jszg", studentInfo.getExpertiseCode());
		Integer find = studentInfoService.selectExpertSubject(map);
		if (find == 0) {
		    jo.put("code", "0007");
		jo.put("msg", "您的专业技术资格种类与岗位学科不对应！");
		        return jo.toJSONString();
		    }
		
		}*/
		/************************************************** 年龄限制 *************************************************************/
		if (position != null && position.getIsLimitAge() != null
		        && position.getIsLimitAge().equals("1")) {
		if (position.getLimitAge() != null && studentInfo.getBirthday() != null) {
		    Integer age = position.getLimitAge().intValue();
		    Integer myAge = getAgeByBirthday(studentInfo.getBirthday());
		    System.out.println(myAge);
		    if (myAge == -1) {
		        jo.put("code", "0011");
		jo.put("msg", "您的出生日期竟然比现在日期还要晚！");
		    return jo.toJSONString();
		}
		//社会人士需要检查年龄
		if (studentInfo.getStudentType() != null && studentInfo.getStudentType().equals("2")) {
		if (position.getLimitAgeConditition() != null && position.getLimitAgeConditition().equals(">=")
		    && myAge < age) {
		jo.put("code", "0012");
		jo.put("msg", "该岗位限制的年龄在" + age + "以上！");
		    return jo.toJSONString(); 
		}
		
		if (position.getLimitAgeConditition() != null && position.getLimitAgeConditition().equals("<=")
		    && myAge > age) {
		jo.put("code", "0013");
		jo.put("msg", "该岗位限制的年龄在" + age + "以下！");
		                return jo.toJSONString(); 
		            }
		        }
		    }
		}
		    
		// 达到指定层次的教师资格证
		/**
		 * 不论是否是哪一次报名，都需要通过系统的自动审核
		 */
		map.put("recruitJobId", recruitJobId);
		Integer isApply = studentInfoService.isApplyPosition(map);
		
		if (isApply != null && isApply > 0) {
		    jo.put("code", "1000");
		jo.put("msg", "恭喜你，通过系统自动审核,并且当前岗位您已经申报过了！");
		    return jo.toJSONString();
		}
		
		jo.put("code", "0000");
		jo.put("msg", "恭喜你，通过系统自动审核！");
		    return jo.toJSONString();
	}
    


    /**
     * 根据用户生日计算年龄
     */
    public Integer getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            return -1;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }


    /**
     * 获取学历最高的学历信息
     * 
     * @param seiList
     * @return
     */
    public StudentEduInfo getXlHighest(List<StudentEduInfo> seiList) {
        Integer temp = 0;
        StudentEduInfo tempEdu = null;
        for (StudentEduInfo sei : seiList) {
            if (Integer.parseInt(sei.getEduCode()) > temp) {
                temp = Integer.parseInt(sei.getEduCode());
                tempEdu = sei;
            }
        }
        return tempEdu;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

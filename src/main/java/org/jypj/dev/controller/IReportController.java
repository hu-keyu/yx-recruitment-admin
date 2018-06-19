package org.jypj.dev.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.service.ActionNoticeService;
import org.jypj.dev.service.AttachementService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.util.StringUtil;
import org.jypj.dev.vo.AdmissTicketVo;
import org.jypj.dev.vo.ClassRoomIReportVo;
import org.jypj.dev.vo.InterviewNoticeVo;
import org.jypj.dev.vo.TalkNoticeVo;
import org.jypj.dev.vo.WrittenNoticeVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * IReport报表控制器
 *
 */
@Controller
@RequestMapping("/dg/ireport")
public class IReportController {
    
    @Resource 
    private StudentInfoService studentInfoService;
    
    @Resource 
    private ActionNoticeService actionNoticeService;
    
    @Resource
    private StudentApplyInfoService studentApplyInfoService;
    
    @Resource
    private PostsetService postsetService;
    
    @Resource
    private AttachementService attachementService;
    
    
	@RequestMapping(value="printInterview")
	public String printInterview(Model model, HttpServletRequest request, String studentApplyId, String recruitId){
        if (StringUtil.isEmpty(studentApplyId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (request.getSession().getAttribute("sid") == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (StringUtil.isEmpty(recruitId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sid", request.getSession().getAttribute("sid").toString());
        map.put("recruitId", recruitId);
        List<InterviewNoticeVo> interList = actionNoticeService.getInterviewNotices(map);
        List<InterviewNoticeVo> items = new ArrayList<InterviewNoticeVo>();
        for (InterviewNoticeVo in : interList) {
            if (studentApplyId.equals(in.getStudentApplyId())) {
                items.add(in);
                break;
            }
        }
		
		//报表数据源  
	    JRDataSource jrDataSource = new JRBeanCollectionDataSource(items);
	    //动态指定报表模板url  
	    model.addAttribute("url", "/report/interview.jasper");
	    model.addAttribute("format", "pdf");//报表格式  
	    model.addAttribute("jrMainDataSource", jrDataSource);        
	    return "iReportView";//对应jasper-defs.xml中的bean id 
	}
    
    
    @RequestMapping(value="printTicket")
    public String printTicket(Model model, HttpServletRequest request, String studentApplyId, String recruitId){
        if (StringUtil.isEmpty(studentApplyId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (request.getSession().getAttribute("sid") == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (StringUtil.isEmpty(recruitId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        
        List<AdmissTicketVo> items = new ArrayList<AdmissTicketVo>();
        StudentInfo sInfo = studentInfoService.selectStudentInfoById(request.getSession().getAttribute("sid").toString());
        StudentApplyInfo sApplyInfo = new StudentApplyInfo();
        sApplyInfo.setStudentId(request.getSession().getAttribute("sid").toString());
        //增加招聘主题id
        sApplyInfo.setEmployItemsId(recruitId);
        sApplyInfo = studentApplyInfoService.selectObjectByStudentApplyInfo(sApplyInfo);
        
        if (sInfo == null || sApplyInfo == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        
        Attachement att = attachementService.selectAttachementById(sInfo.getPhotoAttId());
        Postset ps = postsetService.selectPostsetById(sApplyInfo.getApplyJobId());
        
        AdmissTicketVo atv = new AdmissTicketVo();
        atv.setStudentName(sInfo.getName());
        atv.setSex(sInfo.getSex().equals("1") ? "男" : "女");
        atv.setStudentPhoto(att == null ? "" : att.getPath());
        atv.setIdCard(sInfo.getIdentityCard());
        atv.setTicketNum(sInfo.getTicketnum());
        atv.setPostName(ps == null ? "" : ps.getPostName());
        atv.setPostCode(ps == null ? "" : ps.getPostCode());
        atv.setNoticeItem(sApplyInfo.getTicketNoticeItem());
        
        items.add(atv);
        //报表数据源  
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(items);
        //动态指定报表模板url  
        model.addAttribute("url", "/report/admissionTicket.jasper");
        model.addAttribute("format", "pdf");//报表格式  
        model.addAttribute("jrMainDataSource", jrDataSource);        
        return "iReportView";//对应jasper-defs.xml中的bean id 
    }
    
    @RequestMapping(value="printWritten")
    public String printWritten(Model model, HttpServletRequest request, String studentApplyId, String recruitId){
        if (StringUtil.isEmpty(studentApplyId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (request.getSession().getAttribute("sid") == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (StringUtil.isEmpty(recruitId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sid", request.getSession().getAttribute("sid").toString());
        map.put("recruitId", recruitId);
        List<WrittenNoticeVo> writeList = actionNoticeService.getWrittenNotices(map);
        List<WrittenNoticeVo> items = new ArrayList<WrittenNoticeVo>();
        for (WrittenNoticeVo wv : writeList) {
            if (studentApplyId.equals(wv.getStudentApplyId())) {
                items.add(wv);
                break;
            }
        }
        
        //报表数据源  
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(items);
        //动态指定报表模板url  
        model.addAttribute("url", "/report/written.jasper");
        model.addAttribute("format", "pdf");//报表格式  
        model.addAttribute("jrMainDataSource", jrDataSource);        
        return "iReportView";//对应jasper-defs.xml中的bean id 
    }
    
    @RequestMapping(value="printTalk")
    public String printTalk(Model model, HttpServletRequest request, String studentApplyId, String recruitId){
        if (StringUtil.isEmpty(studentApplyId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (request.getSession().getAttribute("sid") == null) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        if (StringUtil.isEmpty(recruitId)) {
            return "redirect:/dg/studentApplyInfo/index";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sid", request.getSession().getAttribute("sid").toString());
        map.put("recruitId", recruitId);
        TalkNoticeVo talk = actionNoticeService.getTalkNotices(map);
        List<TalkNoticeVo> items = new ArrayList<TalkNoticeVo>();
        if (studentApplyId.equals(talk.getStudentApplyId())) {
            items.add(talk);
        }
        
        //报表数据源  
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(items);
        //动态指定报表模板url  
        model.addAttribute("url", "/report/talk.jasper");
        model.addAttribute("format", "pdf");//报表格式  
        model.addAttribute("jrMainDataSource", jrDataSource);        
        return "iReportView";//对应jasper-defs.xml中的bean id 
    }
}

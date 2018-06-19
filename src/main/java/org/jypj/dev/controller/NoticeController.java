package org.jypj.dev.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.AuditReason;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.service.AuditReasonService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PlanApplyService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.PropertiesUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Notice控制器
 * 招聘公告
 * @author
 *
 */
@Controller
@RequestMapping("/dg/notice")
public class NoticeController {
	
    @Resource 
    private NoticeService noticeService;

    @Resource
    private ThemeService themeService ;

    @Resource
    private PositionService positionService ;

    @Resource
    private AuditReasonService auditReasonService ;
    
    @Resource
    private PlanApplyService planApplyService ;
    

    @RequestMapping("/index")
    public String index(Model model){
        List<String> years = themeService.selectYears() ;
        model.addAttribute("years", years);
        return "/recruit/notice_list.vm" ;
    }

    @RequestMapping("/addPage")
    public String addPage(Model model){
        List<Theme> themeList = themeService.selectThemeNotUsed() ;
        model.addAttribute("themeList",themeList) ;
        return "/recruit/notice_add.vm" ;
    }

    @RequestMapping("/editPage")
    public String editPage(Model model,String id){
        Notice notice = noticeService.selectNoticeById(id) ;
        List<Theme> themeList = new ArrayList<Theme>() ;
        Theme theme = themeService.selectThemeById(notice.getThemeId()) ;
        themeList.add(theme) ;
        List<Position> positions = positionService.selectPostByPorjectId(theme.getId()) ;
        model.addAttribute("themeList",themeList) ;
        model.addAttribute("notice",notice) ;
        model.addAttribute("positions",positions) ;
        return "/recruit/notice_edit.vm" ;
    }

    @RequestMapping("/attentionPage")
    public String attentionPage(Model model){
        List<AuditReason> reasons = auditReasonService.selectAllAttention() ;
        model.addAttribute("reasons",reasons) ;
        return "/recruit/attention_list.vm" ;
    }

    @RequestMapping("/attentionEditPage")
    public String attentionEditPage(Model model,String id){
        AuditReason attention = auditReasonService.selectAuditReasonById(id) ;
        model.addAttribute("attention",attention) ;
        return "/recruit/attention_edit.vm" ;
    }

    @RequestMapping("/editAttention")
    @ResponseBody
    public Result editAttention(AuditReason auditReason){
        Result r = new Result() ;
        try {
            auditReasonService.updateAuditReasonByField(auditReason) ;
            r.setMsg("修改成功！");
            r.setSuccess(true);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            r.setMsg("修改失败！");
            r.setSuccess(false);
            return r ;
        }
    }

    /**
     * 列表分页
     * @param pageSize
     * @param currentPage
     * @return List<Theme>
     */
    @RequestMapping("dataGrid")
    @ResponseBody
    public Map<String,Object> dataGrid(int pageSize, int currentPage,String year,String theme){
        Page page = new Page(pageSize,currentPage) ;
        Map<String,Object> condition = new HashMap<String,Object>() ;
        if(StringUtils.isNotEmpty(year)){
            condition.put("year",year);
        }
        if(StringUtils.isNotEmpty(theme)){
            condition.put("theme",theme);
        }
        page =  noticeService.selectOnePageByMap(page,condition) ;
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("total", page.getTotalRows());
        map.put("rows", page.getResult());
        map.put("currentPage", page.getCurrentPage()) ;
        map.put("noticeUrl",FtpUploadUtil.getStudentOutUrl()) ;
        return map ;
    }


    /**
     * 添加和修改
     * @return Result
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Notice notice){
        Result r = new Result() ;
        if(StringUtils.isEmpty(notice.getThemeId())){
        	r.setSuccess(false);
        	r.setMsg("请选择一个招聘项目！");
        	return r ;
        }
        //是否有招聘计划已被审核,如果没有则不能够添加公告
        List<PlanApply> chlist = planApplyService.selectCheckedByprojectId(notice.getThemeId()) ;
        
        //是否有招聘计划已上报但没有被审核，如果有则不能添加公告
        List<PlanApply> unlist = planApplyService.selectUncheckedByprojectId(notice.getThemeId()) ;
        try {
        	if(chlist.size()==0){
        		//r.setMsg("添加失败，该项目没有审核任何招聘计划！");
        		r.setMsg("新增失败！尚未有学校进行申报");
        		r.setSuccess(false);
        	}else if(unlist.size()>0){
        		r.setMsg("添加失败，该主题有招聘计划未经审核！");
        		r.setSuccess(false);
        	}else{
        		if(StringUtils.isNotEmpty(notice.getId())){
            		noticeService.saveNoticeByField(notice);
                    r.setSuccess(true);
                    if(StringUtils.isNotEmpty(notice.getId())){
                        r.setMsg("修改成功!");
                    }else{
                        r.setMsg("添加成功！");
                    }
            	}else{
            		Notice n = noticeService.selectObjectByThemeId(notice.getThemeId()) ;
            		if(n!=null){
            			r.setMsg("此招聘项目已经发布！");
            			r.setSuccess(false);
            		}else{
            			noticeService.saveNoticeByField(notice);
                        r.setSuccess(true);
                        if(StringUtils.isNotEmpty(notice.getId())){
                            r.setMsg("修改成功!");
                        }else{
                            r.setMsg("添加成功！");
                        }
            		}
            	}
        	}
            return r ;
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            if(StringUtils.isNotEmpty(notice.getId())){
                r.setMsg("修改失败!");
            }else{
                r.setMsg("添加失败!");
            }
            return r ;
        }
    }

    /**
     *修改
     * @param notice
     * @return Result
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Notice notice){
        Result r = new Result() ;
        try {
            noticeService.updateNoticeByField(notice);
            r.setSuccess(true);
            r.setMsg("修改成功！");
            return r ;
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsg("修改失败！");
            return r ;
        }
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(String id){
        Result r = new Result() ;
        try {
            noticeService.deleteNoticeById(id) ;
            r.setSuccess(true);
            r.setMsg("删除成功！");
            return r ;
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsg("删除失败！");
            return r ;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
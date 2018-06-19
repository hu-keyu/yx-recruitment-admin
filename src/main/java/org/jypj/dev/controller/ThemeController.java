package org.jypj.dev.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.service.PlanApplyService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Theme控制器
 * 招聘主题
 * @author
 *
 */
@Controller
@RequestMapping("/dg/theme")
public class ThemeController {
	
    @Resource 
    private ThemeService themeService;

    @Resource
    private PlanApplyService planApplyService ;

    @Resource
    private DictionaryService dictionaryService ;


    @RequestMapping("/index")
    public String index(Model model){
        //年份
        List<String> years = themeService.selectYears() ;
        model.addAttribute("years", years);
        return "/recruit/theme_list.vm" ;
    }

    @RequestMapping("/addPage")
    public String addPage(Model model){
        model.addAttribute("dates",new Date()) ;
        return "/recruit/theme_add.vm" ;
    }

    @RequestMapping("/unitChoose")
    public String UnitChoose(){
        return "/recruit/unit_choose.vm" ;
    }

    @RequestMapping("/getUnit")
    @ResponseBody
    public List<Dictionary> getUnit(String name){
        if(StringUtils.isNotEmpty(name)){
            List<Dictionary> list = dictionaryService.searchByCodeAndText("dwdm",name) ;
            return list ;
        }else{
            List<Dictionary> list =   DictionaryCache.getDictionaryByCode("dwdm");//单位代码;
            return list ;
        }
    }

    @RequestMapping("/editPage")
    public String editPage(String id ,Model model){
        Theme theme = themeService.selectThemeById(id) ;
        model.addAttribute(theme) ;
        //通过招聘主题id查询到选择的单位id
        List<PlanApply> planlist = planApplyService.findPlanApplyListByThemeId(id) ;
        List<String> eduIdlist = new ArrayList<String>();
        for(PlanApply p : planlist){
            eduIdlist.add(p.getSchoolId()) ;
        }
        List<Dictionary> schoollist =   DictionaryCache.getDictionaryByCode("dwdm");//单位代码;
        List<String> eduNameList = new ArrayList<String>() ;
        for(Dictionary d : schoollist){
            if(eduIdlist.contains(d.getId())){
                eduNameList.add(d.getText()) ;
            }
        }
        String eduNames = "" ;
        for(String s : eduNameList){
            eduNames = eduNames+s+",";
        }
        String eduIds = "" ;
        for (String s : eduIdlist){
            eduIds = eduIds+s+"," ;
        }
        if(eduIds.length()>0){
            eduIds = eduIds.substring(0,eduIds.length()-1) ;
        }
        model.addAttribute("eduNames",eduNames) ;
        model.addAttribute("eduIds",eduIds) ;
        model.addAttribute("eduIdlist",eduIdlist) ;
        model.addAttribute("dates",new Date()) ;
        return "/recruit/theme_edit.vm" ;
    }

    @RequestMapping("/viewPage")
    public String viewPage(String id ,Model model){
        Theme theme = themeService.selectThemeById(id) ;
        model.addAttribute(theme) ;
        //通过招聘主题id查询到选择的单位id
        List<PlanApply> planlist = planApplyService.findPlanApplyListByThemeId(id) ;
        List<String> eduIds = new ArrayList<String>();
        for(PlanApply p : planlist){
            eduIds.add(p.getSchoolId()) ;
        }
        List<Dictionary> schoollist =  DictionaryCache.getDictionaryByCode("dwdm");//单位代码;
        List<String> eduNameList = new ArrayList<String>() ;
        for(Dictionary d : schoollist){
            if(eduIds.contains(d.getId())){
                eduNameList.add(d.getText()) ;
            }
        }
        String eduNames = "" ;
        for(String s : eduNameList){
            eduNames = eduNames+s+",";
        }
        model.addAttribute("eduNames",eduNames) ;
        return "/recruit/theme_view.vm" ;
    }



    /**
     * 列表分页
     * @param page
     * @return List<Theme>
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Map<String,Object> dataGrid(Page page,String year,String theme){
        Map<String,Object> condition = new HashMap<String,Object>() ;
        if(StringUtils.isNotEmpty(year)){
            condition.put("year",year) ;
        }
        if(StringUtils.isNotEmpty(theme)){
            condition.put("theme",theme) ;
        }
        page = themeService.selectOnePageByMap(page,condition) ;
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("total", page.getTotalRows());
        map.put("rows", page.getResult());
        map.put("currentPage", page.getCurrentPage()) ;
        return map ;
    }


    /**
     * 添加
     * @return Result
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Theme theme, HttpSession session){
        User user = (User)session.getAttribute("user") ;
        Result r = new Result() ;
        try {
            themeService.saveThemeByField(theme,user);
            r.setSuccess(true);
            if(theme.getStatus()==0){
                r.setMsg("保存成功！");
            }else{
                r.setMsg("发布成功！");
            }
            return r ;
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            if(theme.getStatus()==0){
                r.setMsg("保存失败！");
            }else{
                r.setMsg("发布失败！");
            }
            return r ;
        }
    }

    /**
     *修改
     * @param theme
     * @return Result
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Theme theme){
        Result r = new Result() ;
        try {
            themeService.updateThemeByField(theme);
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
            themeService.deleteThemeById(id) ;
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

    /**
     *
     * @return
     */
    @RequestMapping("/publish")
    @ResponseBody
    public Result publish(Theme theme){
        Result r = new Result() ;
        theme.setStatus(1);
        try {
            themeService.updateThemeByField(theme) ;
            r.setSuccess(true);
            r.setMsg("发布成功！");
            return r ;
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsg("发布失败！");
            return r ;
        }
    }

    @RequestMapping("/selectAllByYear")
    @ResponseBody
    public List<Theme> selectAllByYear(String year){
        return themeService.selectAllByYear(year) ;
    }

    /**
     * 查询此表中所有的年份
     * @return
     */
    @RequestMapping("/selectYears")
    @ResponseBody
    public List<String> selectYears(){
        return themeService.selectYears() ;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }



}
package org.jypj.dev.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Specialty;
import javax.annotation.Resource;

import org.jypj.dev.service.PositionDomainService;
import org.jypj.dev.service.PostSpecService;
import org.jypj.dev.service.SpecialtyService;
import org.jypj.dev.service.StudentEduInfoService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Specialty控制器
 * 招聘专业
 * @author
 *
 */
@Controller
@RequestMapping("/dg/specialty")
public class SpecialtyController {
	
    @Resource 
    private SpecialtyService specialtyService;

    @Resource
    private PostSpecService postSpecService ;

    @Resource
    private StudentEduInfoService studentEduInfoService ;

    @Resource
    private PositionDomainService positionDomainService ;

    @RequestMapping("/index")
    public String index(){
        return "/recruit/specialty_list.vm" ;
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "/recruit/specialty_add.vm" ;
    }
    
    @RequestMapping("/editPage")
    public String editPage(Model model,String id){
    	Specialty sp = specialtyService.selectSpecialtyById(id) ;
    	String code = sp.getCode() ;
    	String suffix = code.substring(code.length()-2,code.length());
    	String prefix = code.substring(0,code.length()-2) ;
    	sp.setSuffix(suffix);
    	sp.setPrefix(prefix);
    	model.addAttribute("specialty",sp) ;
    	return "/recruit/specialty_edit.vm" ;
    }

    /**
     * 列表分页
     * @param pageSize
     * @param currentPage
     * @return List<Theme>
     */
    @RequestMapping("dataGrid")
    @ResponseBody
    public Map<String,Object> dataGrid(int pageSize, int currentPage,String type,String firstSbjCode,String secondSbjCode ,String name){
        Page page = new Page(pageSize,currentPage) ;
        Map<String,Object> condition = new HashMap<String,Object>() ;
        condition.put("type", type) ;
        condition.put("firstSbjCode", firstSbjCode) ;
        condition.put("secondSbjCode", secondSbjCode) ;
        condition.put("name", name) ;
        page =  specialtyService.selectOnePageByMap(page,condition) ;
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("total", page.getTotalRows());
        map.put("rows", page.getResult());
        map.put("currentPage", page.getCurrentPage()) ;
        map.put("storey", page.getStorey()) ;
        return map ;
    }


    /**
     * 添加
     * @return Result
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Specialty specialty){
        return specialtyService.saveSpecialtyByField(specialty);
    }

    /**
     *修改
     * @param specialty
     * @return Result
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Specialty specialty){
           return specialtyService.updateSpecialtyByField(specialty);
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
            specialtyService.deleteSpecialtyById(id) ;
            r.setSuccess(true);
            r.setMsg("删除成功！");
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsg("删除失败！");
        }
        return r ;
    }


    /**
     * 根据id批量删除
     * @param ids
     * @return Result
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(String ids){
        Result r = new Result() ;
        try{
            List<String> idlist = Arrays.asList(ids.split(",")) ;
            for(String s : idlist){
                Specialty sp = specialtyService.selectSpecialtyById(s) ;
                int num = postSpecService.selectCountBySpecialty(sp.getCode()) ;
                int num1 = studentEduInfoService.selectCountBySpecialty(sp.getCode()) ;
                int num2 = positionDomainService.selectCountBySpecialty(sp.getCode()) ;
                if(num>0||num1>0||num2>0){
                    r.setMsg(sp.getName()+"专业已经被使用不能删除！");
                    r.setSuccess(false);
                    return r ;
                }
            }
            specialtyService.deleteBatch(ids);
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
     * 查询所有的一级学科或者二级学科
     * @param storey
     * @return
     */
    @RequestMapping("/findListByStorey")
    @ResponseBody
    public List<Specialty> findListByStorey(int storey){
        return  specialtyService.findListByStorey(storey) ;
    }


    /**
     * 名称模糊搜索
     * @return
     */
    @RequestMapping("/selectByName")
    @ResponseBody
    public List<Specialty> selectByName(String name){
        return specialtyService.selectByName(name) ;
    }

    
    @ResponseBody
    @RequestMapping("/selectSecondByFirstSbjCode")
    public List<Specialty> selectSecondByFirstSbjCode(String code){
    	return specialtyService.selectSecondByFirstSbjCode(code) ;
    }

    /**
     *通过类型查出一级学科
     * @param type
     * @return
     */
    @RequestMapping("/selectFirstByType")
    @ResponseBody
    public List<Specialty> selectFirstByType(String type){
    	return specialtyService.selectFirstByType(type) ;
    }


}
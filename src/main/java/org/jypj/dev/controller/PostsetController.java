package org.jypj.dev.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Postset;
import javax.annotation.Resource;

import org.jypj.dev.entity.Specialty;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Postset控制器
 * 招聘岗位
 * @author
 *
 */
@Controller
@RequestMapping("/dg/postset")
public class PostsetController {
	
    @Resource 
    private PostsetService postsetService;

    @Resource
    private StudentApplyInfoService studentApplyInfoService ;

    @Resource
    private PositionService positionService ;

    @RequestMapping("/index")
    public String index(){
        return "/recruit/postset_list.vm" ;
    }

    @RequestMapping("/chooseSpecialty")
    public String chooseSpecialty(Model model){
        List<Dictionary> zylxs = DictionaryCache.getDictionaryByCode("zylx");//专业类型
        model.addAttribute("zylxs", zylxs);
        return "/recruit/specialty_choose.vm";
    }

    @RequestMapping("/addPage")
    public String addPage(Model model){
        List<Dictionary> xllxDics = DictionaryCache.getDictionaryByCode("xllx");//学历
        List<Dictionary> jylxDics = DictionaryCache.getDictionaryByCode("jylx");//教育类型
        List<Dictionary> xwlxDics = DictionaryCache.getDictionaryByCode("xwlx");//学位
        List<Dictionary> bylxDics = DictionaryCache.getDictionaryByCode("bylx");//招聘对象
        List<Dictionary> gwlbDics = DictionaryCache.getDictionaryByCode("gwlb");//岗位类别
        List<Dictionary> xklbDics = DictionaryCache.getDictionaryByCode("xklb");//学科类别
        model.addAttribute("xllxDics", xllxDics);
        model.addAttribute("jylxDics", jylxDics);
        model.addAttribute("xwlxDics", xwlxDics);
        model.addAttribute("bylxDics", bylxDics);
        model.addAttribute("gwlbDics", gwlbDics);
        model.addAttribute("xklbDics", xklbDics);
        return "/recruit/postset_add.vm" ;
    }

    @RequestMapping("/editPage")
    public String editPage(Model model,String id){
        List<Dictionary> xllxDics = DictionaryCache.getDictionaryByCode("xllx");//学历
        List<Dictionary> jylxDics = DictionaryCache.getDictionaryByCode("jylx");//教育类型
        List<Dictionary> xwlxDics = DictionaryCache.getDictionaryByCode("xwlx");//学位
        List<Dictionary> bylxDics = DictionaryCache.getDictionaryByCode("bylx");//招聘对象
        List<Dictionary> gwlbDics = DictionaryCache.getDictionaryByCode("gwlb");//岗位类别
        List<Dictionary> xklbDics = DictionaryCache.getDictionaryByCode("xklb");//学科类别
        model.addAttribute("xllxDics", xllxDics);
        model.addAttribute("jylxDics", jylxDics);
        model.addAttribute("xwlxDics", xwlxDics);
        model.addAttribute("bylxDics", bylxDics);
        model.addAttribute("gwlbDics", gwlbDics);
        model.addAttribute("xklbDics", xklbDics);
        String[] ids = id.split(",") ;
        Postset postset = postsetService.selectPostsetById(ids[0]) ;
        Map<String,String> map = new HashMap<String,String>() ;
        map=getSpecialty(postset.getSpecialtys());
        postset.setLimitProfession(map.get("code"));
        postset.setLimitProfessionText(map.get("name"));
        model.addAttribute("postset",postset) ;
        return "/recruit/postset_edit.vm" ;
    }


    private Map<String,String> getSpecialty(List<Specialty> specialtys){
        Map<String ,String> dataMap=new HashMap<String ,String>();
        StringBuffer codeBuf=new StringBuffer();
        StringBuffer nameBuf=new StringBuffer();
        if(specialtys!=null && !specialtys.isEmpty()){
            for(int i=0;i<specialtys.size();i++){
                Specialty specialty=specialtys.get(i);
                if(i == specialtys.size()-1 ){
                    codeBuf.append(specialty.getCode());
                    nameBuf.append(specialty.getCode()+specialty.getName());
                }else{
                    codeBuf.append(specialty.getCode()+",");
                    nameBuf.append(specialty.getCode()+specialty.getName()+",");
                }
            }
        }
        dataMap.put("code",codeBuf.toString());
        dataMap.put("name", nameBuf.toString());
        return dataMap;
    }


    /**
     * 列表分页
     * @param pageSize
     * @param currentPage
     * @return List<Theme>
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Map<String,Object> dataGrid(int pageSize, int currentPage,String postName){
        Page page = new Page(pageSize,currentPage) ;
        Map<String,Object> condition = new HashMap<String,Object>() ;
        if(StringUtils.isNotEmpty(postName)){
            //condition.put("postName","%"+postName+"%") ;
            condition.put("postName",postName) ;
        }
        page =  postsetService.selectOnePageByMap(page,condition) ;
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("total", page.getTotalRows());
        map.put("rows", page.getResult());
        map.put("currentPage", page.getCurrentPage());
        return map ;
    }


    /**
     * 添加
     * @return Result
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Postset postset){
        Result r = new Result() ;
        try {
            r = postsetService.savePostsetByField(postset,r);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            if(StringUtils.isNotEmpty(postset.getId())) {
                r.setMsg("添加失败");
            }else{
                r.setMsg("修改失败！");
            }
        }
        return r ;
    }

    /**
     *修改
     * @param postset
     * @return Result
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Postset postset){
        Result r = new Result() ;
        try {
            postsetService.updatePostset(postset);
            r.setSuccess(true);
            r.setMsg("修改成功！");
        }catch (Exception e){
            r.setSuccess(false);
            r.setMsg("修改失败！");
        }
        return r ;
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
            postsetService.deletePostsetById(id) ;
            r.setSuccess(true);
            r.setMsg("删除成功！");
        }catch (Exception e){
            r.setSuccess(false);
            r.setMsg("删除失败！");
        }
        return r ;
    }

    /**
     *
     * @param ids
     * @return Result
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(String ids){
        Result r = new Result() ;
        try {
            List<String> idlist = Arrays.asList(ids.split(",")) ;
            for(String s : idlist){
                int num = studentApplyInfoService.selectByApplyJobId(s) ;
                //int num1 = positionService.selectByApplyJobId(s) ;
                if(num>0){
                    Postset p = postsetService.selectPostsetById(s) ;
                    r.setMsg(p.getPostName()+"岗位已被系统使用，不能删除！");
                    r.setSuccess(false);
                    return r ;
                }
            }
            postsetService.deleteBatch(ids) ;
            r.setSuccess(true);
            r.setMsg("删除成功！");
        }catch (Exception e){
            r.setSuccess(false);
            r.setMsg("删除失败！");
        }
        return r ;
    }

    @RequestMapping("/validPostset")
    @ResponseBody
    public Result validPostset(String id ,String code ,String name){
        return postsetService.validPostset(id,code,name) ;
    }

}
package org.jypj.dev.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.EaxmLabsInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.EaxmLabsInfoService;
import org.jypj.dev.service.ExamItemsInfoService;
import org.jypj.dev.service.ExamSubjectInfoService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * EaxmLabsInfo控制器
 * 试室信息表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/eaxmLabsInfo")
public class EaxmLabsInfoController {
	
    @Resource 
    private EaxmLabsInfoService eaxmLabsInfoService;
    @Resource
	private ExamItemsInfoService examItemsInfoService;
    @Resource
	private ExamSubjectInfoService examSubjectInfoService;
	
    @RequestMapping(value="eaxmLabs")
    public String eaxmLabs(Model model){
    	// 年月日
		//List<Dictionary> nyrdics = DictionaryCache.getDictionaryByCode("nyr");
		List<String> nyrdics=examSubjectInfoService.queryThemeDate();
		model.addAttribute("nyrdics", nyrdics);
    	return "/examination/items_info_labs.vm";
    }
    
    @ResponseBody
    @RequestMapping(value="examLabsList",method=RequestMethod.POST)
    public String examLabsList(Page page){
    	Map<String, Object> condition =page.getCondition();
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	page=eaxmLabsInfoService.selectOnePageByMap(page, condition);
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
    }
    
    @RequestMapping(value="updateInfo")
    public String updateInfo(Model model,String themeid,String id,String itemsid){
    	EaxmLabsInfo eaxmLabsInfo=null;
    	eaxmLabsInfo=eaxmLabsInfoService.selectEaxmLabsInfoById(id);
    	model.addAttribute("themeid", themeid);
    	model.addAttribute("itemsid", itemsid);
    	model.addAttribute("eaxmLabsInfo", eaxmLabsInfo);
    	return "/examination/items_labs_input.vm";
    }
    
    @ResponseBody
    @RequestMapping(value="updateLabs")
    public JSONObject updateLabs(EaxmLabsInfo eaxmLabsInfo,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try {
    		if(StringUtils.isNotBlank(eaxmLabsInfo.getLabsNum())
    				&&StringUtils.isNotBlank(eaxmLabsInfo.getTestId())
    				&&StringUtils.isNotBlank(eaxmLabsInfo.getId())){
    			Integer count=eaxmLabsInfoService.selectLabNums(eaxmLabsInfo.getTestId(), eaxmLabsInfo.getLabsNum(),eaxmLabsInfo.getId());
    			Integer count1=eaxmLabsInfoService.selectLabName(eaxmLabsInfo.getTestId(), eaxmLabsInfo.getLabsName(),eaxmLabsInfo.getId());
    			Integer count2=eaxmLabsInfoService.selectLabAddress(eaxmLabsInfo.getTestId(), eaxmLabsInfo.getLabsAddr(),eaxmLabsInfo.getId());
    			if(count>0)
    			{
    				jsonMap.put("flag", "error");
    				jsonMap.put("msg", "试室号重复！");
    				return jsonMap;
    			}
    			if(count1>0)
    			{
    				jsonMap.put("flag", "error");
    				jsonMap.put("msg", "试室名重复！");
    				return jsonMap;
    			}
    			if(count2>0)
    			{
    				jsonMap.put("flag", "error");
    				jsonMap.put("msg", "试室地址重复！");
    				return jsonMap;
    			}
	    	   eaxmLabsInfo.setModifyUser(user.getId());
	    	   eaxmLabsInfo.setMtime(new Date());
	    	   eaxmLabsInfoService.updateEaxmLabsInfoByField(eaxmLabsInfo);
	    	   jsonMap.put("flag", "success");
	    	   jsonMap.put("msg", "操作成功！");
    		}
    	}catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
    @ResponseBody
    @RequestMapping(value="verifyLabs")
    public JSONObject verifyLabs(EaxmLabsInfo eaxmLabsInfo,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	try {
    		if(StringUtils.isNotBlank(eaxmLabsInfo.getLabsNum())
    				&&StringUtils.isNotBlank(eaxmLabsInfo.getTestId())
    				&&StringUtils.isNotBlank(eaxmLabsInfo.getId())){
    			Integer count=eaxmLabsInfoService.selectLabNums(eaxmLabsInfo.getTestId(), eaxmLabsInfo.getLabsNum(),eaxmLabsInfo.getId());
    			if(count>0){
    				jsonMap.put("flag", "error");
    				jsonMap.put("msg", "试室号重复！");
    				return jsonMap;
    			}else{
    				Integer count1=eaxmLabsInfoService.selectLabName(eaxmLabsInfo.getTestId(), eaxmLabsInfo.getLabsName(),eaxmLabsInfo.getId());
        			if(count1>0){
        				jsonMap.put("flag", "error");
        				jsonMap.put("msg", "试室名称重复！");
        				return jsonMap;
        			}else{
        				
        				Integer count2=eaxmLabsInfoService.selectLabAddress(eaxmLabsInfo.getTestId(), eaxmLabsInfo.getLabsAddr(),eaxmLabsInfo.getId());
        				if(count2>0)
        				{
        					jsonMap.put("flag", "error");
            				jsonMap.put("msg", "试室地址重复！");
            				return jsonMap;
        				}else
        				{
        					
        	    			jsonMap.put("flag", "success");
        	    			jsonMap.put("msg", "操作成功！");
        	    			return jsonMap;
        				}
        			
        			}
    			}
    			
    		}else{
    			jsonMap.put("flag", "error");
				jsonMap.put("msg", "试室号为空！");
				return jsonMap;
    		}
    	}catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
    @ResponseBody
    @RequestMapping(value="getLabs")
    public EaxmLabsInfo getLabs(Model model,String id){
    	EaxmLabsInfo eaxmLabsInfo=eaxmLabsInfoService.selectEaxmLabsInfoById(id);
    	return eaxmLabsInfo;
    }
}
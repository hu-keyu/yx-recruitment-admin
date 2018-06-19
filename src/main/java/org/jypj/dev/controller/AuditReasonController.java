package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.entity.AuditReason;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.AuditReasonService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * AuditReason控制器
 * @author QiCai
 *
 */
@Controller
@RequestMapping("/dg/auditReason")
public class AuditReasonController {
	
    @Resource 
    private AuditReasonService auditReasonService;
    
    @RequestMapping(value="reasonList")
    public String reasonList(Model model){
    	return "/audit/reason_list.vm";
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllReason",method=RequestMethod.POST)
	public String selectAllReason(Page page,HttpSession session) {
    	User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		if(user.getSchoolId()==null){ //教育局
			condition.put("ownerId", user.getOrginId());
			condition.put("type", "2");
		}else{
			condition.put("ownerId", user.getSchoolId());//当前登录用户学校ID
			condition.put("type", "1");//类型是学校的
		}
		page = auditReasonService.selectOnePageByMap(page, condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @RequestMapping(value="gotoReason")
    public String gotoReason(String id,Model model){
    	AuditReason auditReason=new AuditReason();
    	if(StringUtils.isNotBlank(id)){
    		auditReason=auditReasonService.selectAuditReasonById(id);
    	}
    	model.addAttribute("auditReason", auditReason);
    	return "/audit/reason_input.vm";
    }
    
    @ResponseBody
    @RequestMapping(value="saveReason",method=RequestMethod.POST)
    public JSONObject saveReason(AuditReason auditReason,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		auditReasonService.saveReasonOper(auditReason,user);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	return jsonMap;
    }
    
    @ResponseBody
    @RequestMapping(value="updateReason",method=RequestMethod.POST)
    public JSONObject updateReason(AuditReason auditReason,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		auditReasonService.updateReasonOper(auditReason,user);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	return jsonMap;
    }
    
    @ResponseBody
    @RequestMapping(value="deleteReason",method=RequestMethod.POST)
    public JSONObject deleteReason(String id,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	try {
    		auditReasonService.deleteAuditReason(id,jsonMap);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
		}
    	return jsonMap;
    }

	/**
	 * 教育局端的注意事项维护放到了原因维护表中，reason
	 * @return
     */

	public List<AuditReason> selectAllAttention(){
		return null ;
	}
}
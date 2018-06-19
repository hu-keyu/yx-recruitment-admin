package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.PositionDomain;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.Specialty;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.SpecialtyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * Position控制器
 * @author QiCai
 *
 */
@Controller
@RequestMapping("/dg/position")
public class PositionController {
	
    @Resource
    private PositionService positionService;
    @Resource
    private PostsetService PostsetService;
    @Resource
    private SpecialtyService specialService;
    
    @RequestMapping(value="addposition")
    public String addposition(Model model,String themeId,String planApplyId,String id,String flag){
    	Position position=null;
    	if(StringUtils.isNotBlank(id)){
    		position=positionService.selectPositionById(id);
    		Map<String,String> dataMap=getPositionDomain(position.getPositionDomains());
    		position.setLimitProfession(dataMap.get("code"));
    		position.setLimitProfessionText(dataMap.get("name"));
    	}else{
    		position=new Position();
    	}
    	Map<String ,Object> queryParameter=new HashMap<String ,Object>();
    	if(StringUtils.isNotBlank(flag) && "add".equals(flag)){
    		if(StringUtils.isNotBlank(themeId)){
    			queryParameter.put("projectId", themeId);
    		}
    		if(StringUtils.isNotBlank(planApplyId)){
    			queryParameter.put("planApplyId", planApplyId);
    		}
    	}
    	List<Postset> postsets=PostsetService.selectAllByMap(queryParameter);
    	List<Dictionary> xllxDics = DictionaryCache.getDictionaryByCode("xllx");//学历
    	List<Dictionary> jylxDics = DictionaryCache.getDictionaryByCode("jylx");//教育类型
    	List<Dictionary> xwlxDics = DictionaryCache.getDictionaryByCode("xwlx");//学位
    	List<Dictionary> bylxDics = DictionaryCache.getDictionaryByCode("bylx");//招聘对象
    	model.addAttribute("flag", flag);
    	model.addAttribute("themeId", themeId);
    	model.addAttribute("xllxDics", xllxDics);
    	model.addAttribute("jylxDics", jylxDics);
    	model.addAttribute("xwlxDics", xwlxDics);
    	model.addAttribute("bylxDics", bylxDics);
    	model.addAttribute("position", position);
    	model.addAttribute("postsets", postsets);
    	model.addAttribute("planApplyId", planApplyId);
    	return "/teacher/position_input.vm";
    }
    
    private Map<String,String> getPositionDomain(List<PositionDomain> positionDomains){
    	Map<String ,String> dataMap=new HashMap<String ,String>();
    	StringBuffer domainCodeBuf=new StringBuffer();
    	StringBuffer domainNameBuf=new StringBuffer();
    	if(positionDomains!=null && !positionDomains.isEmpty()){
    		for(int i=0;i<positionDomains.size();i++){
    			PositionDomain positionDomain=positionDomains.get(i);
    			if(i == positionDomains.size()-1 ){
    				domainCodeBuf.append(positionDomain.getDomainId());
    				domainNameBuf.append(positionDomain.getDomainName());
    			}else{
    				domainCodeBuf.append(positionDomain.getDomainId()+",");
    				domainNameBuf.append(positionDomain.getDomainName()+",");
    			}
    		}
    	}
    	dataMap.put("code",domainCodeBuf.toString());
    	dataMap.put("name", domainNameBuf.toString());
    	return dataMap;
    }
    
    @ResponseBody
    @RequestMapping(value="savePosition",method=RequestMethod.POST)
    public JSONObject savePosition(Position position,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
			positionService.savePositionOper(position,user,jsonMap);
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
    @RequestMapping(value="delPosition",method=RequestMethod.POST)
    public JSONObject delPosition(String id){
    	JSONObject jsonMap=new JSONObject();
    	try {
			positionService.deletePositionOper(id);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
    @ResponseBody
    @RequestMapping(value="updatePosition",method=RequestMethod.POST)
    public JSONObject updatePosition(Position position,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	JSONObject jsonMap=new JSONObject();
    	try {
    		positionService.updatePositionOper(position, user);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
    	return jsonMap;
    }
    
    @ResponseBody
    @RequestMapping(value="initPosition",method=RequestMethod.POST)
    public List<Postset> initPosition(String themeId,String planApplyId){
    	Map<String ,Object> queryParameter=new HashMap<String ,Object>();
    	if(StringUtils.isNotBlank(themeId)){
    		queryParameter.put("projectId", themeId);
    	}
    	if(StringUtils.isNotBlank(planApplyId)){
    		queryParameter.put("planApplyId", planApplyId);
    	}
    	List<Postset> postsets=PostsetService.selectAllByMap(queryParameter);
    	Map<String,String> dataMap=null;
    	for(Postset postset : postsets){
    		dataMap=getSpecialty(postset.getSpecialtys());
    		postset.setLimitProfession(dataMap.get("code"));
    		postset.setLimitProfessionText(dataMap.get("name"));
    	}
    	return postsets;
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
    
    @RequestMapping(value="managerPosition")
    public String managerPosition(Model model,String projectId,String planApplyId,HttpSession session){
    	User user=(User)session.getAttribute("user");
    	Map<String,Object> map =new HashMap<String,Object>();
    	if(StringUtils.isNotBlank(projectId)){
    		map.put("projectId", projectId);
    	}
    	if(StringUtils.isNotBlank(planApplyId)){
    		map.put("planApplyId", planApplyId);
    	}
    	map.put("schoolId", user.getSchoolId());
    	List<Position> positions=positionService.selectAllByMap(map);
    	model.addAttribute("positions", positions);
    	return "/teacher/position_manager.vm";
    }
    
    @ResponseBody
    @RequestMapping(value="cancelPosition",method=RequestMethod.POST)
    public JSONObject cancelPosition(String id,String projectId,String stationId,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try {
			positionService.cancelPosition(id,projectId,stationId,user,jsonMap);//注意学校ID在Service中写死了
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "岗位操作成功！");
		} catch (Exception e) {
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
			e.printStackTrace();
		}
    	return jsonMap;
    }
    
    @ResponseBody
    @RequestMapping(value="enabledPosition",method=RequestMethod.POST)
    public JSONObject enabledPosition(String id,String projectId,HttpSession session){
    	JSONObject jsonMap=new JSONObject();
    	User user=(User)session.getAttribute("user");
    	try {
			positionService.enabledPosition(id,projectId,user,jsonMap);
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "岗位操作成功！");
		} catch (Exception e) {
			jsonMap.put("flag", "error");
			if(StringUtils.isBlank((String)jsonMap.get("msg"))){
				jsonMap.put("msg", "操作失败，请稍后重试！");
			}
			e.printStackTrace();
		}
    	return jsonMap;
    }
    
    @RequestMapping(value="professSelect")
    public String professSelect(Model model){
        List<Dictionary> zylxs = DictionaryCache.getDictionaryByCode("zylx");//专业类型
        model.addAttribute("zylxs", zylxs);
    	return "/teacher/profess_select.vm";
    }

	@RequestMapping("/seclectPositionApplyCount")
	@ResponseBody
	public List<Position> seclectPositionApplyCount(String projectId){
		return positionService.selectPostByPorjectId(projectId) ;
	}
}
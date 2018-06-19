package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.InformationDao;
import org.jypj.dev.entity.Information;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.InformationService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service("informationService")
public class InformationServiceImpl implements InformationService {
	
    @Resource 
    private InformationDao informationDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param information
	 * @return 保存后的对象包括ID
	 */	
	public int saveInformationByField(Information information){
	
		return informationDao.saveInformationByField(information);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param information 
	 * @return 保存后的对象包括ID
	 */	
	public int saveInformation(Information information,User user, JSONObject jsonMap){
		information.setCreateuser(user.getId());
		information.setModifyuser(user.getId());
		information.setSchoolId(user.getSchoolId());
		
		String msg="现场资料信息已添加，不能重复添加，请刷新页面重试！";
		Information info=new Information();
		String type=information.getType();
		info.setType(type);
		info.setProjectId(information.getProjectId());
		info.setSchoolId(information.getSchoolId());
		if("1".equals(type)){
			info.setPositionId(information.getPositionId());
			msg="该岗位的单位面试信息已添加，不能重复添加，请刷新页面重试！";
		}
		Information informationServer=informationDao.selectObjectByInformation(info);
		if(informationServer != null){
			jsonMap.put("msg", msg);
			throw new RuntimeException(msg);
		}
		return informationDao.saveInformation(information);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteInformationById(String id){
    
    	return informationDao.deleteInformationById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteInformationByObject(Information information){
    
    	return informationDao.deleteInformationByObject(information);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param information 
	 * @return 保存后的对象包括ID
	 */	
    public int updateInformationByField(Information information){
    
    	return informationDao.updateInformationByField(information);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param information 
	 * @return 保存后的对象包括ID
	 */	
    public int updateInformation(Information information){
    
    	return informationDao.updateInformation(information);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Information 
	 */	
    public Information selectInformationById(String id){
    
    	return informationDao.selectInformationById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Information>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Information> informations =informationDao.selectOnePageByMap(page,map);
	     	if(informations!=null&&informations.size()>0){
	     		page.setResult(informations);
	     	}else{
	     		page.setResult(new ArrayList<Information>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param information  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByInformation(Page page,Information information){
 		 List<Information> informations =informationDao.selectOnePageByInformation(page,information);
	     	if(informations!=null&&informations.size()>0){
	     		page.setResult(informations);
	     	}else{
	     		page.setResult(new ArrayList<Information>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Information>
	 */	
     public List<Information> selectAllByMap(Map<String,Object> map){
     	return informationDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Information>
	 */	
     public List<Information> selectAllByInformation(Information information){
     
    	 return informationDao.selectAllByInformation(information);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Information
	 */	
     public Information selectObjectByMap(Map<String,Object> map){
     
    	 return informationDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Information
	 */	
     public Information selectObjectByInformation(Information information){
     
     	return informationDao.selectObjectByInformation(information);
     }

	@Override
	public void updateInformationOper(Information information, User user, JSONObject jsonMap) throws Exception {
		Information informationServer=informationDao.selectInformationById(information.getId());
		if("1".equals(informationServer.getStatus())){
			jsonMap.put("msg", "已发的面试资料信息不可在修改，请确认！");
			throw new Exception("已发的面试资料信息不可在修改，请确认！");
		}
		information.setModifyuser(user.getId());
		this.updateInformation(information);
	}

	@Override
	public void deleteInformationOper(String chk, JSONObject jsonMap) {
		if(StringUtils.isNotBlank(chk)){
			List<String> ids=Arrays.asList(chk.split(","));
			informationDao.deleteList(ids);
		}
	}

	@Override
	public void publishInformationOper(String id, JSONObject jsonMap,User user) throws Exception {
		Information information=informationDao.selectInformationById(id);
		if("1".equals(information.getStatus())){
			jsonMap.put("msg", "该资料面试信息已发布，请确认！");
			throw new Exception("该资料面试信息已发布，请确认！");
		}
		information.setModifyuser(user.getId());
		information.setStatus("1");
		this.updateInformation(information);
	}
}
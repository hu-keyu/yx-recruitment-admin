package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.dao.AuditReasonDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.entity.AuditReason;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.AuditReasonService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service("auditReasonService")
public class AuditReasonServiceImpl implements AuditReasonService {
	
    @Resource 
    private AuditReasonDao auditReasonDao;
    @Resource
    private StudentApplyInfoDao studentApplyInfoDao;
    
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param auditReason
	 * @return 保存后的对象包括ID
	 */	
	public int saveAuditReasonByField(AuditReason auditReason){
	
		return auditReasonDao.saveAuditReasonByField(auditReason);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param auditReason 
	 * @return 保存后的对象包括ID
	 */	
	public int saveAuditReason(AuditReason auditReason){
	
		return auditReasonDao.saveAuditReason(auditReason);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteAuditReasonById(String id){
    
    	return auditReasonDao.deleteAuditReasonById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteAuditReasonByObject(AuditReason auditReason){
    
    	return auditReasonDao.deleteAuditReasonByObject(auditReason);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param auditReason 
	 * @return 保存后的对象包括ID
	 */	
    public int updateAuditReasonByField(AuditReason auditReason){
    
    	return auditReasonDao.updateAuditReasonByField(auditReason);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param auditReason 
	 * @return 保存后的对象包括ID
	 */	
    public int updateAuditReason(AuditReason auditReason){
    
    	return auditReasonDao.updateAuditReason(auditReason);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return AuditReason 
	 */	
    public AuditReason selectAuditReasonById(String id){
    
    	return auditReasonDao.selectAuditReasonById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<AuditReason>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<AuditReason> auditReasons =auditReasonDao.selectOnePageByMap(page,map);
	     	if(auditReasons!=null&&auditReasons.size()>0){
	     		page.setResult(auditReasons);
	     	}else{
	     		page.setResult(new ArrayList<AuditReason>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param auditReason  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByAuditReason(Page page,AuditReason auditReason){
 		 List<AuditReason> auditReasons =auditReasonDao.selectOnePageByAuditReason(page,auditReason);
	     	if(auditReasons!=null&&auditReasons.size()>0){
	     		page.setResult(auditReasons);
	     	}else{
	     		page.setResult(new ArrayList<AuditReason>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<AuditReason>
	 */	
     public List<AuditReason> selectAllByMap(Map<String,Object> map){
     	return auditReasonDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<AuditReason>
	 */	
     public List<AuditReason> selectAllByAuditReason(AuditReason auditReason){
     
    	 return auditReasonDao.selectAllByAuditReason(auditReason);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  AuditReason
	 */	
     public AuditReason selectObjectByMap(Map<String,Object> map){
     
    	 return auditReasonDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  AuditReason
	 */	
     public AuditReason selectObjectByAuditReason(AuditReason auditReason){
     
     	return auditReasonDao.selectObjectByAuditReason(auditReason);
     }

	@Override
	public void saveReasonOper(AuditReason auditReason, User user) {
		if(user.getSchoolId()==null){//教育局
			auditReason.setType("2");
			auditReason.setOwnerid(user.getOrginId());
		}else{ //学校
			auditReason.setType("1");
			auditReason.setOwnerid(user.getSchoolId());
		}
		//学校ID或教育局ID
		auditReason.setCreateuser(user.getId());
		auditReason.setModifyuser(user.getId());
		this.saveAuditReason(auditReason);
	}

	@Override
	public void updateReasonOper(AuditReason auditReason, User user) {
		auditReason.setModifyuser(user.getId());
		this.updateAuditReasonByField(auditReason);
	}

	@Override
	public List<AuditReason> selectAllAttention() {
		return auditReasonDao.selectAllAttention();
	}

	@Override
	public void deleteAuditReason(String id,JSONObject jsonMap) {
		StudentApplyInfo studentApplyInfo=new StudentApplyInfo();
		studentApplyInfo.setApplyAuditCode(id);
		List<StudentApplyInfo> studentApplyInfos=studentApplyInfoDao.selectAllByStudentApplyInfo(studentApplyInfo);
		if(!studentApplyInfos.isEmpty()){
			jsonMap.put("msg", "选中的原因已被使用，无法删除，操作失败！");
			throw new RuntimeException("选中的原因已被使用，无法删除，操作失败！");
		}
		this.deleteAuditReasonById(id);
	}
}
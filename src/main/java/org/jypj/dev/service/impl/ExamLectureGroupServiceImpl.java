package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.dao.ExamLectureGroupDao;
import org.jypj.dev.entity.ExamLectureGroup;
import org.jypj.dev.service.ExamLectureGroupService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Service;

@Service("examLectureGroupService")
public class ExamLectureGroupServiceImpl implements ExamLectureGroupService {
	
    @Resource 
    private ExamLectureGroupDao examLectureGroupDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param examLectureGroup
	 * @return 保存后的对象包括ID
	 */	
	public int saveExamLectureGroupByField(ExamLectureGroup examLectureGroup){
	
		return examLectureGroupDao.saveExamLectureGroupByField(examLectureGroup);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param examLectureGroup 
	 * @return 保存后的对象包括ID
	 */	
	public int saveExamLectureGroup(ExamLectureGroup examLectureGroup){
	
		return examLectureGroupDao.saveExamLectureGroup(examLectureGroup);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteExamLectureGroupById(String id){
    
    	return examLectureGroupDao.deleteExamLectureGroupById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteExamLectureGroupByObject(ExamLectureGroup examLectureGroup){
    
    	return examLectureGroupDao.deleteExamLectureGroupByObject(examLectureGroup);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param examLectureGroup 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamLectureGroupByField(ExamLectureGroup examLectureGroup){
    
    	return examLectureGroupDao.updateExamLectureGroupByField(examLectureGroup);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param examLectureGroup 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamLectureGroup(ExamLectureGroup examLectureGroup){
    
    	return examLectureGroupDao.updateExamLectureGroup(examLectureGroup);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ExamLectureGroup 
	 */	
    public ExamLectureGroup selectExamLectureGroupById(String id){
    
    	return examLectureGroupDao.selectExamLectureGroupById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ExamLectureGroup>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ExamLectureGroup> examLectureGroups =examLectureGroupDao.selectOnePageByMap(page,map);
	     	if(examLectureGroups!=null&&examLectureGroups.size()>0){
	     		page.setResult(examLectureGroups);
	     	}else{
	     		page.setResult(new ArrayList<ExamLectureGroup>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param examLectureGroup  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByExamLectureGroup(Page page,ExamLectureGroup examLectureGroup){
 		 List<ExamLectureGroup> examLectureGroups =examLectureGroupDao.selectOnePageByExamLectureGroup(page,examLectureGroup);
	     	if(examLectureGroups!=null&&examLectureGroups.size()>0){
	     		page.setResult(examLectureGroups);
	     	}else{
	     		page.setResult(new ArrayList<ExamLectureGroup>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamLectureGroup>
	 */	
     public List<ExamLectureGroup> selectAllByMap(Map<String,Object> map){
     	return examLectureGroupDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamLectureGroup>
	 */	
     public List<ExamLectureGroup> selectAllByExamLectureGroup(ExamLectureGroup examLectureGroup){
     
    	 return examLectureGroupDao.selectAllByExamLectureGroup(examLectureGroup);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamLectureGroup
	 */	
     public ExamLectureGroup selectObjectByMap(Map<String,Object> map){
     
    	 return examLectureGroupDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamLectureGroup
	 */	
     public ExamLectureGroup selectObjectByExamLectureGroup(ExamLectureGroup examLectureGroup){
     
     	return examLectureGroupDao.selectObjectByExamLectureGroup(examLectureGroup);
     }
}
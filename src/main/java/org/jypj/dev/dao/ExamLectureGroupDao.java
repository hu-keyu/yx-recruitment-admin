package org.jypj.dev.dao;


import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ExamLectureGroup;
import org.jypj.dev.util.Page;

public interface ExamLectureGroupDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param examLectureGroup
	 * @return 保存后的对象包括ID
	 */	
	public int saveExamLectureGroupByField(ExamLectureGroup examLectureGroup);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param examLectureGroup 
	 * @return 保存后的对象包括ID
	 */	
	public int saveExamLectureGroup(ExamLectureGroup examLectureGroup);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteExamLectureGroupById(String id);
    
   	/**
	 * 根据对象删除
	 * @param examLectureGroup
	 * @return 
	 */	
    public int deleteExamLectureGroupByObject(ExamLectureGroup examLectureGroup);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param examLectureGroup 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamLectureGroupByField(ExamLectureGroup examLectureGroup);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param examLectureGroup 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamLectureGroup(ExamLectureGroup examLectureGroup);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ExamLectureGroup 
	 */	
    public ExamLectureGroup selectExamLectureGroupById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ExamLectureGroup>
	 */	
     public List<ExamLectureGroup> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param examLectureGroup 查询对象
	 * @return List<ExamLectureGroup>
	 */	
     public List<ExamLectureGroup> selectOnePageByExamLectureGroup(Page page,ExamLectureGroup examLectureGroup);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamLectureGroup>
	 */	
     public List<ExamLectureGroup> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamLectureGroup>
	 */	
     public List<ExamLectureGroup> selectAllByExamLectureGroup(ExamLectureGroup examLectureGroup);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamLectureGroup
	 */	
     public ExamLectureGroup selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamLectureGroup
	 */	
     public ExamLectureGroup selectObjectByExamLectureGroup(ExamLectureGroup examLectureGroup);
}

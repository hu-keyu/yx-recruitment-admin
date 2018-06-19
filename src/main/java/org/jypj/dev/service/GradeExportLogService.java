package org.jypj.dev.service;

import org.jypj.dev.entity.GradeExportLog;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

public interface GradeExportLogService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param gradeExportLog
	 * @return 
	 */	
	public int saveGradeExportLogByField(GradeExportLog gradeExportLog);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param gradeExportLog 
	 * @return 
	 */	
	public int saveGradeExportLog(GradeExportLog gradeExportLog);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteGradeExportLogById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteGradeExportLogByObject(GradeExportLog gradeExportLog);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param gradeExportLog 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeExportLogByField(GradeExportLog gradeExportLog);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param gradeExportLog 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeExportLog(GradeExportLog gradeExportLog);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return GradeExportLog 
	 */	
    public GradeExportLog selectGradeExportLogById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<GradeExportLog>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByGradeExportLog(Page page,GradeExportLog gradeExportLog);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<GradeExportLog>
	 */	
     public List<GradeExportLog> selectAllByMap(Map<String,Object> map);
     
     
     /**
 	 * 分页查询 包含条件（教育局端错误文件）
 	 * @param page 分页对象
 	 * @param map  查询条件  
 	 * @return  List<GradeExportLog>
 	 */	
      public Page selectScorePageByMap(Page page,Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<GradeExportLog>
	 */	
     public List<GradeExportLog> selectAllByGradeExportLog(GradeExportLog gradeExportLog);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  GradeExportLog
	 */	
     public GradeExportLog selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  GradeExportLog
	 */	
     public GradeExportLog selectObjectByGradeExportLog(GradeExportLog gradeExportLog);

}

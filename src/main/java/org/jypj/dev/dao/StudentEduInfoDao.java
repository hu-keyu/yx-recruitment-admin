package org.jypj.dev.dao;

import org.jypj.dev.entity.StudentEduInfo;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* StudentEduInfodao数据接口层
* 考生学历信息表
* @author
*
*/


public interface StudentEduInfoDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param studentEduInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentEduInfoByField(StudentEduInfo studentEduInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param studentEduInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentEduInfo(StudentEduInfo studentEduInfo);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteStudentEduInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param studentEduInfo
	 * @return 
	 */	
    public int deleteStudentEduInfoByObject(StudentEduInfo studentEduInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param studentEduInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentEduInfoByField(StudentEduInfo studentEduInfo);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param studentEduInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentEduInfo(StudentEduInfo studentEduInfo);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return StudentEduInfo 
	 */	
    public StudentEduInfo selectStudentEduInfoById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<StudentEduInfo>
	 */	
     public List<StudentEduInfo> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param studentEduInfo 查询对象
	 * @return List<StudentEduInfo>
	 */	
     public List<StudentEduInfo> selectOnePageByStudentEduInfo(Page page,StudentEduInfo studentEduInfo);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentEduInfo>
	 */	
     public List<StudentEduInfo> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentEduInfo>
	 */	
     public List<StudentEduInfo> selectAllByStudentEduInfo(StudentEduInfo studentEduInfo);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentEduInfo
	 */	
     public StudentEduInfo selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentEduInfo
	 */	
     public StudentEduInfo selectObjectByStudentEduInfo(StudentEduInfo studentEduInfo);

	/**
	 * 通过专业名称查找，是否已经使用
	 * @param specialty
	 * @return
	 */
    int selectCountBySpecialty(String specialty);
}

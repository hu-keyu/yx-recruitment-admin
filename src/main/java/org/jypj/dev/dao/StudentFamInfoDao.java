package org.jypj.dev.dao;

import org.jypj.dev.entity.StudentFamInfo;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* StudentFamInfodao数据接口层
* 学生家庭情况信息
* @author
*
*/


public interface StudentFamInfoDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param studentFamInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentFamInfoByField(StudentFamInfo studentFamInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param studentFamInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentFamInfo(StudentFamInfo studentFamInfo);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteStudentFamInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param studentFamInfo
	 * @return 
	 */	
    public int deleteStudentFamInfoByObject(StudentFamInfo studentFamInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param studentFamInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentFamInfoByField(StudentFamInfo studentFamInfo);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param studentFamInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentFamInfo(StudentFamInfo studentFamInfo);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return StudentFamInfo 
	 */	
    public StudentFamInfo selectStudentFamInfoById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<StudentFamInfo>
	 */	
     public List<StudentFamInfo> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param studentFamInfo 查询对象
	 * @return List<StudentFamInfo>
	 */	
     public List<StudentFamInfo> selectOnePageByStudentFamInfo(Page page,StudentFamInfo studentFamInfo);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentFamInfo>
	 */	
     public List<StudentFamInfo> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentFamInfo>
	 */	
     public List<StudentFamInfo> selectAllByStudentFamInfo(StudentFamInfo studentFamInfo);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentFamInfo
	 */	
     public StudentFamInfo selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentFamInfo
	 */	
     public StudentFamInfo selectObjectByStudentFamInfo(StudentFamInfo studentFamInfo);
}

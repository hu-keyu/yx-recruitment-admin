package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* StudentInfodao数据接口层
* 考生基本信息表
* @author
*
*/


public interface StudentInfoDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param studentInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentInfoByField(StudentInfo studentInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param studentInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentInfo(StudentInfo studentInfo);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteStudentInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param studentInfo
	 * @return 
	 */	
    public int deleteStudentInfoByObject(StudentInfo studentInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param studentInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentInfoByField(StudentInfo studentInfo);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param studentInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentInfo(StudentInfo studentInfo);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return StudentInfo 
	 */	
    public StudentInfo selectStudentInfoById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<StudentInfo>
	 */	
     public List<StudentInfo> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param studentInfo 查询对象
	 * @return List<StudentInfo>
	 */	
     public List<StudentInfo> selectOnePageByStudentInfo(Page page,StudentInfo studentInfo);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentInfo>
	 */	
     public List<StudentInfo> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentInfo>
	 */	
     public List<StudentInfo> selectAllByStudentInfo(StudentInfo studentInfo);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentInfo
	 */	
     public StudentInfo selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentInfo
	 */	
     public StudentInfo selectObjectByStudentInfo(StudentInfo studentInfo);
     
     /**
      * 查找指定的技术资格与岗位学科的对应关系
      * @return
      */
     public Integer selectExpertSubject(Map<String,Object> map);
     
     /**
      * 查询未审核列表
      * @return
      */
     public List<StudentInfo> queryNoAuditList(Page page,Map<String,Object> map);
     
     /**
      * 查询已审核列表
      * @return
      */
     public List<StudentInfo> queryAuditList(Page page,Map<String,Object> map);
     
     /**
      * 判断身份证号是否存在
      * @param map
      * @return
      */
     public Integer isExistIdCard(Map<String,Object> map);
     
     /**
      * 批量更新学生表
      * @param studentInfos
      */
     public void updateList(List<StudentInfo> studentInfos);
     
     /**
      * 查询最大的准考证号
      * @param ticketNum
      * @return
      */
     public String getMaxTicketNum(@Param("ticketNum") String ticketNum,@Param("projectId")String projectId);

	/**
	 * 教育局端招聘人员审核查询未审核人员
	 * @param page
	 * @param map
	 * @return
	 */
	List<StudentInfo> selectPersonUncheck(Page page, Map<String, Object> map);

	/**
	 * 教育局端招聘人员审核查询已经审核人员
	 * @param page
	 * @param map
	 * @return
	 */
	List<StudentInfo> selectPersonChecked(Page page, Map<String, Object> map);

	/**
	 * 学校审核情况列表查询
	 * @param page
	 * @param map
     * @return
     */
	List<StudentInfo> selectSchoolCheckedSituation(Page page, Map<String, Object> map);

	/**
	 * 学校面试情况列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	List<StudentInfo> selectSchoolInterviewSituation(Page page, Map<String, Object> map);
	
	/**
	 * 是否申报岗位
	 */
	public Integer isApplyPosition(Map<String,Object> map);
	
	/**
	 * 是否申报其他学校
	 * @param map
	 * @return
	 */
	public Integer isApplySchool(Map<String,Object> map);

	/**
	 * 查询考生资料
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<StudentInfo> selectPersonData(Page page, Map<String, Object> condition);

	/**
	 * 考生端重置密码
	 * @param map
	 */
	public void resetPasswd(Map<String, String> map);

	/**
	 * 查询考生基本信息列表
	 * @param page
	 * @param map
	 * @return
	 */
	public List<StudentInfo> selectStudentInfo(Page page, Map<String, Object> map);

}

package org.jypj.dev.service;

import com.alibaba.fastjson.JSONObject;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* StudentInfo业务接口层
* 考生基本信息表
* @author
*
*/

public interface StudentInfoService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param studentInfo
	 * @return 
	 */	
	public int saveStudentInfoByField(StudentInfo studentInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param studentInfo 
	 * @return 
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
	 * @param id 主键ID
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
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByStudentInfo(Page page,StudentInfo studentInfo);
    
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
      * 查找是否存在指定的专业技术资格与岗位学科的对应 关系
      * @param map
      * @return
      */
     public Integer selectExpertSubject(Map<String,Object> map) ;
     
     /**
      * 查询未审核列表
      * @return
      */
     public Page queryNoAuditList(Page page,Map<String,Object> map);
     
     /**
      * 查询已审核列表
      * @return
      */
     public Page queryAuditList(Page page,Map<String,Object> map);
     
     /**
      * 判断身份证号是否存在
      * @param map
      * @return
      */
     public Integer isExistIdCard(Map<String,Object> map);

	/**
	 * 教育局端招聘人员审核查询未审核人员
	 * @param page
	 * @param map
     * @return
     */
	public Page selectPersonUncheck(Page page,Map<String,Object> map) ;

	/**
	 * 教育局端招聘人员审核查询已经审核人员
	 * @param page
	 * @param map
	 * @return
	 */
	public Page selectPersonChecked(Page page,Map<String,Object> map) ;

	/**
	 * 学校审核情况
	 * @param page
	 * @param map
     * @return
     */
	Page selectSchoolCheckedSituation(Page page, Map<String, Object> map) ;

	/**
	 * 学校面试情况
	 * @param page
	 * @param map
	 * @return
	 */
	Page selectSchoolInterviewSituation(Page page, Map<String, Object> map) ;

	/**
	 * 面试人员审核
	 * @param chks
	 * @param projectId
	 * @param reason
	 * @param isPass
	 * @param user
	 * @param jsonMap
	 */
	public void personCheck(String chks, String projectId, String reason, String isPass, User user, JSONObject jsonMap) throws Exception;
	
	/**
	 * 查询是否申报该岗位
	 * @param map
	 * @return
	 */
	public Integer isApplyPosition(Map<String,Object> map);
	
	/**
	 * 查询考生是否报考非当前学校的单位
	 * @param map
	 * @return
	 */
	public Integer isApplySchool(Map<String,Object> map);

	/**
	 * 撤销审核
	 * @param chk
	 * @param projectId
	 * @param user
	 * @param status
	 * @param jsonMap
	 * @throws Exception
	 */
	public void checkCancel(String chk, String projectId, User user, String status, String reason,JSONObject jsonMap) throws Exception;

	/**
	 * 查询考生资料
	 * @param page
	 * @param condition
	 * @return
	 */
	public Page selectPersonData(Page page, Map<String, Object> condition);

	/**
	 * 重置考生密码
	 * @param id
	 * @param newPasswd
	 * @param md5Encrypt
	 */
	public void resetPasswd(String id, String newPasswd, String md5Encrypt);

	/**
	 * 查询考生基本信息列表
	 * @param page
	 * @param condition
	 * @return
	 */
	public Page selectStudentInfo(Page page, Map<String, Object> condition);
}



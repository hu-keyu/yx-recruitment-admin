package org.jypj.dev.service;

import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import java.util.List;
import java.util.Map;

/**
* StudentApplyInfo业务接口层
* 考生报考信息表
* @author
*
*/

public interface StudentApplyInfoService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param studentApplyInfo
	 * @return 
	 */	
	public int saveStudentApplyInfoByField(StudentApplyInfo studentApplyInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param studentApplyInfo 
	 * @return 
	 */	
	public int saveStudentApplyInfo(StudentApplyInfo studentApplyInfo);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteStudentApplyInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteStudentApplyInfoByObject(StudentApplyInfo studentApplyInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param studentApplyInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentApplyInfoByField(StudentApplyInfo studentApplyInfo);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param studentApplyInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentApplyInfo(StudentApplyInfo studentApplyInfo);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return StudentApplyInfo 
	 */	
    public StudentApplyInfo selectStudentApplyInfoById(String id);
    
    /**
     * 查询学生资料是否审核通过
    * @param map
    * @return
    */
    public Integer selectStatus(Map<String, Object> map);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<StudentApplyInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByStudentApplyInfo(Page page,StudentApplyInfo studentApplyInfo);
    
     /**
      *根据岗位ID查询面试入围名单
     * @param page
     * @param map
     * @return
     */
     public Page selectPageEnter(Page page,Map<String,Object> map);
     
     /**
      * 查询要发布的面试名单的学生
     * @param map
     * @return
     */
     public List<ScoreEntersOutVo> selectAllpublish(Map<String,Object> map);
     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentApplyInfo>
	 */	
     public List<StudentApplyInfo> selectAllByMap(Map<String,Object> map);
     
     /**
      * 查询简历是否审核完（发布面试名单专用）
     * @param itemId
     * @return
     */
     public Integer selectListAuditorCount(String itemId);
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentApplyInfo>
	 */	
     public List<StudentApplyInfo> selectAllByStudentApplyInfo(StudentApplyInfo studentApplyInfo);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentApplyInfo
	 */	
     public StudentApplyInfo selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentApplyInfo
	 */	
     public StudentApplyInfo selectObjectByStudentApplyInfo(StudentApplyInfo studentApplyInfo);
     
     /**
     * 验证考生口令
     * @param map  验证条件
     * @return  Integer
     */ 
     public Integer verifyCode(Map<String,Object> map);
     
     /**
      * 获取已经报考当前岗位的人数
      * @param map
      * @return
      */
     public Integer appliedStudent(Map<String,Object> map);
     
     /**
      * 根据学生id删除申请信息
      * @param sid
      */
     public void deleteAllObjectBySid(String sid);
     
     /**
      * 保存之前要先删除已经有的保存了的申报岗位
      * @param sai
      */
     public void deleteAndSave(StudentApplyInfo sai);
     
     /**
      * 检查考生的报考状态
      * @param sai
      * @return
      */
     public Integer checkApplyStatus(StudentApplyInfo sai);
     
     /**
      * 根据招聘项目和岗位以及申请状态（>= 8）的数量判断是否发布笔试入围名单
      * @param sai
      * @return
      */
     public Integer selectCountOfEnterWritten(StudentApplyInfo sai);

	/**
	 *根据岗位id查询对象，用来判断该岗位
	 * @param s
	 * @return
	 */
	int selectByApplyJobId(String s);
}

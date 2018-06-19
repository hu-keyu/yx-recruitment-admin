package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ExamSubjectInfo;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.AdjustStudentRoomInfoVo;
import org.jypj.dev.vo.SecondLayOutRoomVo;
import org.jypj.dev.vo.StudentLectureVo;

public interface ExamSubjectInfoService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param examSubjectInfo
	 * @return 
	 */	
	public int saveExamSubjectInfoByField(ExamSubjectInfo examSubjectInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param examSubjectInfo 
	 * @return 
	 */	
	public int saveExamSubjectInfo(ExamSubjectInfo examSubjectInfo);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteExamSubjectInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteExamSubjectInfoByObject(ExamSubjectInfo examSubjectInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param examSubjectInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamSubjectInfoByField(ExamSubjectInfo examSubjectInfo);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param examSubjectInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamSubjectInfo(ExamSubjectInfo examSubjectInfo);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ExamSubjectInfo 
	 */	
    public ExamSubjectInfo selectExamSubjectInfoById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ExamSubjectInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByExamSubjectInfo(Page page,ExamSubjectInfo examSubjectInfo);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamSubjectInfo>
	 */	
     public List<ExamSubjectInfo> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamSubjectInfo>
	 */	
     public List<ExamSubjectInfo> selectAllByExamSubjectInfo(ExamSubjectInfo examSubjectInfo);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamSubjectInfo
	 */	
     public ExamSubjectInfo selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamSubjectInfo
	 */	
     public ExamSubjectInfo selectObjectByExamSubjectInfo(ExamSubjectInfo examSubjectInfo);
    
     /**
      * 添加非附加试室的编排
      * @param map
      * @return
      */
     public Map<String,Object> addGanWeiRoom(Map<String,Object> map);
     
     /**
      * 添加附加试室编排
      * @param paramJson
      */
     public String addSecondGanWeiRoom(Map<String,String> map);
     
     
     /**
      * 删除附加试室
      * @param map
      * @return
      */
     public List<SecondLayOutRoomVo> delSecondGanWeiRoom(Map<String,String> map);
     
     
     /**
      * 获取编辑数据
      * @param map
      * @return
      */
     public Map<String,Object> getSelectEditData(Map<String,String> map);
     
    
     /**
      * 分配试室级学生对应关系
      */
     public String addRoomAndStudentResult(Map<String,String> map);
     
     
     /**
      * 查询某个附加试室的学生
      * @param map
      * @return
      */
     public List<AdjustStudentRoomInfoVo> querySecondLayoutRoom(Map<String,String> map);
     
     
     /**
      * 调整附加试室
      * @param map
      * @return
      */
     public String updateBatchAdjust(Map<String,String> map);
     
   
     /**
      * 附加试室调整详情
      * @param map
      */
     public Map<String,Object> queryLayoutDetail(Map<String,String> map);
     
    
     /**
      * 查询试讲安排
      * @param map
      * @return
      */
     public Map<String,Object> updateAndqueryTrialArrange(Map<String,String> map);
     
     /**
      * 查询试讲岗位
      * @param map
      * @return
      */
     public List<Postset> queryLectureGanWei(Map<String,String> map);
     
     
     /**
      * 存储试讲
      * @param map
      */
     public Map<String,Object> saveTrialArrange(Map<String,String> map);
     
     
     /**
      * 组号改变，改变右边数据
      * @return
      */
     public Map<String,Object> queryTrialArrangeByGroup(Map<String,String> map);
     
     /**
      * 发布
      * @param map
      */
     public String showNotice(Map<String,String> map);
     
     
     /**
      * 查询试讲导出excel信息
      * @param map
      * @return
      */
     public List<StudentLectureVo> queryLectureStudentBySchoolIdExcel(Map<String,String> map);
     
     /**
      * 查询主题日期列表
      * @return
      */
     public List<String> queryThemeDate();
     
     /**
      * 查询某个考点是否生成学生与考试对应关系
      * @param map
      * @return
      */
     public int querySecondKaoDianNum(Map<String,String> map);
     
     
     /**
      *查询学校对应的岗位
      * @param map
      * @return
      */
     public List<AdjustStudentRoomInfoVo>  queryLectureGanWeiSchoolNum(Map<String,String> map);
     
     
     /**
      * 查询某个学校招聘的岗位
      * @param map
      * @return
      */
     public List<AdjustStudentRoomInfoVo> querySchoolApproveGanWeiCount(Map<String,String> map);
     
}

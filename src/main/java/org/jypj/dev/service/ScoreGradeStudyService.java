package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ScoreGradeStudy;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;

import com.alibaba.fastjson.JSONObject;

/**
* ScoreGradeStudy业务接口层
* 考察成绩表
* @author
*
*/

public interface ScoreGradeStudyService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeStudy
	 * @return 
	 */	
	public int saveScoreGradeStudyByField(ScoreGradeStudy scoreGradeStudy);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeStudy 
	 * @return 
	 */	
	public int saveScoreGradeStudy(ScoreGradeStudy scoreGradeStudy);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeStudyById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreGradeStudyByObject(ScoreGradeStudy scoreGradeStudy);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeStudyByField(ScoreGradeStudy scoreGradeStudy);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeStudy(ScoreGradeStudy scoreGradeStudy);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeStudy 
	 */	
    public ScoreGradeStudy selectScoreGradeStudyById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
     
     
     /**
      * 批量更新数据
     * @param list
     */
    public void updateStudyList(List<ScoreGradeStudy> list,User user, JSONObject jsonMap)  throws Exception;
    
    /**
     * 发布成绩并生成进入考察的入围名单
     * @param list
    */
    public void publishBatchStudy(Map<String, Object> condition,Page page,User user,JSONObject jsonMap) throws Exception ;
    
    /**
     * 取消发布成绩
     * @param project
     * @param step
     * @return
     */
    public String celpublishStudy(String projectId,Integer step)throws Exception ;
     
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreGradeStudy(Page page,ScoreGradeStudy scoreGradeStudy);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public List<ScoreGradeStudy> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public List<ScoreGradeStudy> selectAllByScoreGradeStudy(ScoreGradeStudy scoreGradeStudy);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeStudy
	 */	
     public ScoreGradeStudy selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeStudy
	 */	
     public ScoreGradeStudy selectObjectByScoreGradeStudy(ScoreGradeStudy scoreGradeStudy);
     
     /**
      * 根据考生的申报信息查询考察结果
      * @param sai
      * @return
      */
     public ScoreGradeStudy selectStudyResult(StudentApplyInfo sai);

}

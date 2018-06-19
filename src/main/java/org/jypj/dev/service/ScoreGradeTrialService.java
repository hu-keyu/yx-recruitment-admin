package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ScoreGradeTrial;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;

import com.alibaba.fastjson.JSONObject;

/**
* ScoreGradeTrial业务接口层
* 统一试讲成绩表
* @author
*
*/

public interface ScoreGradeTrialService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeTrial
	 * @return 
	 */	
	public int saveScoreGradeTrialByField(ScoreGradeTrial scoreGradeTrial);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeTrial 
	 * @return 
	 */	
	public int saveScoreGradeTrial(ScoreGradeTrial scoreGradeTrial);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeTrialById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreGradeTrialByObject(ScoreGradeTrial scoreGradeTrial);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeTrialByField(ScoreGradeTrial scoreGradeTrial);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeTrial(ScoreGradeTrial scoreGradeTrial);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeTrial 
	 */	
    public ScoreGradeTrial selectScoreGradeTrialById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
     
     /**
   	 * 按条件查询总数
   	 * @param map  查询条件  
   	 * @return  Integer
   	 */	
      public Integer selectCountByMap(Map<String,Object> map);
     
     /**
      * 批量更新数据
      * @param studentApplyInfos
      */
     public void updateTrialList(List<ScoreGradeTrial> list,User user, JSONObject jsonMap) throws Exception ;
     
     /**
      * 发布成绩并生成进入体检的入围名单
      * @param list
     */
     public void publishBatchTrial(Map<String, Object> condition,Page page,User user,JSONObject jsonMap,String flag) throws Exception ;
     
     
     /**
      * 取消发布成绩
      * @param project
      * @param step
      * @return
      */
     public String celpublishTrial(String projectId,Integer step,String flag,Map<String, Object> condition,User user)throws Exception ;
     
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreGradeTrial(Page page,ScoreGradeTrial scoreGradeTrial);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public List<ScoreGradeTrial> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public List<ScoreGradeTrial> selectAllByScoreGradeTrial(ScoreGradeTrial scoreGradeTrial);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeTrial
	 */	
     public ScoreGradeTrial selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeTrial
	 */	
     public ScoreGradeTrial selectObjectByScoreGradeTrial(ScoreGradeTrial scoreGradeTrial);
     
     /**
      * 根据申请情况获取考生试讲成绩
      * @param sai
      * @return
      */
     public ScoreGradeTrial selectScoreGradeTrial(StudentApplyInfo sai);

}

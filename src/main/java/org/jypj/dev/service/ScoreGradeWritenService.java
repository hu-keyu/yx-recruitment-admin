package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ScoreGradeWriten;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;

import com.alibaba.fastjson.JSONObject;

/**
* ScoreGradeWriten业务接口层
* 统一笔试成绩表
* @author
*
*/

public interface ScoreGradeWritenService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeWriten
	 * @return 
	 */	
	public int saveScoreGradeWritenByField(ScoreGradeWriten scoreGradeWriten);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeWriten 
	 * @return 
	 */	
	public int saveScoreGradeWriten(ScoreGradeWriten scoreGradeWriten);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeWritenById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreGradeWritenByObject(ScoreGradeWriten scoreGradeWriten);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeWriten 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeWritenByField(ScoreGradeWriten scoreGradeWriten);
    
    /**
     * 批量更新数据
     * @param studentApplyInfos
     */
    public void updateWritenList(List<ScoreGradeWriten> list,User user, JSONObject jsonMap)  throws Exception ;
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeWriten 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeWriten(ScoreGradeWriten scoreGradeWriten);
    
    /**
     * 发布成绩并生成进入统一试讲的入围名单
     * @param list
     */
    public void publishBatchWritien(Map<String, Object> condition,Page page,User user,JSONObject jsonMap)throws Exception ;
    
    /**
     * 取消发布成绩
     * @param project
     * @param step
     * @return
     */
    public String celpublishWritien(String projectId,Integer step)throws Exception ;
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeWriten 
	 */	
    public ScoreGradeWriten selectScoreGradeWritenById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreGradeWriten(Page page,ScoreGradeWriten scoreGradeWriten);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public List<ScoreGradeWriten> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public List<ScoreGradeWriten> selectAllByScoreGradeWriten(ScoreGradeWriten scoreGradeWriten);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeWriten
	 */	
     public ScoreGradeWriten selectObjectByMap(Map<String,Object> map);
     
     /**
      * 查询笔试的试室是否发布完成
     * @param map
     * @return
     */
     public Integer selectLabsCount(Map<String,Object> map);
     
     /**
      * 查询进入笔试的总人数
      * @param map
      * @return
      */
     public Integer selectGradeWritienCount(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeWriten
	 */	
     public ScoreGradeWriten selectObjectByScoreGradeWriten(ScoreGradeWriten scoreGradeWriten);
     
     /**
      * 根据考生的报考情况查询笔试成绩
      * @param sai
      * @return
      */
     public ScoreGradeWriten selectGradeWritten(StudentApplyInfo sai);

}

package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ScoreGradePhysical;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
* ScoreGradePhysical业务接口层
* 体检成绩表
* @author
*
*/

public interface ScoreGradePhysicalService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradePhysical
	 * @return 
	 */	
	public int saveScoreGradePhysicalByField(ScoreGradePhysical scoreGradePhysical);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradePhysical 
	 * @return 
	 */	
	public int saveScoreGradePhysical(ScoreGradePhysical scoreGradePhysical);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradePhysicalById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreGradePhysicalByObject(ScoreGradePhysical scoreGradePhysical);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradePhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradePhysicalByField(ScoreGradePhysical scoreGradePhysical);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradePhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradePhysical(ScoreGradePhysical scoreGradePhysical);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradePhysical 
	 */	
    public ScoreGradePhysical selectScoreGradePhysicalById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
     
     /**
      * 批量更新数据
      * @param studentApplyInfos
      */
     public void updatePhysicalList(List<ScoreGradePhysical> list,User user, JSONObject jsonMap)  throws Exception ;
     
     /**
      * 发布成绩并生成进入考察的入围名单
      * @param list
     */
     public void publishBatchPhysical(Map<String, Object> condition,Page page,User user,JSONObject jsonMap) throws Exception ;
     
     /**
      * 取消发布成绩
      * @param project
      * @param step
      * @return
      */
     public String celpublishPhysical(String projectId,Integer step)throws Exception ;
    		 
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreGradePhysical(Page page,ScoreGradePhysical scoreGradePhysical);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public List<ScoreGradePhysical> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public List<ScoreGradePhysical> selectAllByScoreGradePhysical(ScoreGradePhysical scoreGradePhysical);
     
     /**
      * 导入面试成绩
      * @param file
      * @param projectId
      * @param user
      * @param jsonMap
      */
	 public void saveImportScore(CommonsMultipartFile file, String projectId, String testType, User user, JSONObject jsonMap) throws Exception;
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradePhysical
	 */	
     public ScoreGradePhysical selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradePhysical
	 */	
     public ScoreGradePhysical selectObjectByScoreGradePhysical(ScoreGradePhysical scoreGradePhysical);
     
     /**
      * 根据指定的实体获取体检结果
      * @param scoreGradePhysical
      * @return
      */
     public ScoreGradePhysical selectPhysicalResult(StudentApplyInfo sai);
     

}

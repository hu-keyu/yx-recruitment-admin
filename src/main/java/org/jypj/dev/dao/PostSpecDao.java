package org.jypj.dev.dao;

import org.jypj.dev.entity.PostSpec;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* PostSpecdao数据接口层
* 岗位专业
* @author
*
*/


public interface PostSpecDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param postSpec
	 * @return 保存后的对象包括ID
	 */	
	public int savePostSpecByField(PostSpec postSpec);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param postSpec 
	 * @return 保存后的对象包括ID
	 */	
	public int savePostSpec(PostSpec postSpec);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePostSpecById(String id);
    
   	/**
	 * 根据对象删除
	 * @param postSpec
	 * @return 
	 */	
    public int deletePostSpecByObject(PostSpec postSpec);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param postSpec 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePostSpecByField(PostSpec postSpec);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param postSpec 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePostSpec(PostSpec postSpec);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return PostSpec 
	 */	
    public PostSpec selectPostSpecById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<PostSpec>
	 */	
     public List<PostSpec> selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param postSpec 查询对象
	 * @return List<PostSpec>
	 */	
     public List<PostSpec> selectOnePageByPostSpec(Page page, PostSpec postSpec);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PostSpec>
	 */	
     public List<PostSpec> selectAllByMap(Map<String, Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PostSpec>
	 */	
     public List<PostSpec> selectAllByPostSpec(PostSpec postSpec);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PostSpec
	 */	
     public PostSpec selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PostSpec
	 */	
     public PostSpec selectObjectByPostSpec(PostSpec postSpec);

	/**
	 * 批量添加
	 * @param list
     */
	void saveBatch(List<PostSpec> list);

	/**
	 * 根据postsetid删把这一批全部删除掉
	 * @param postsetId
	 */
	void deleteByPostsetId(String postsetId);

	/**
	 * 通过专业名称查找，是否已经使用
	 * @param specialty
	 * @return
	 */
	int selectCountBySpecialty(String specialty) ;
}

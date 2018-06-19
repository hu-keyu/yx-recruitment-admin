package org.jypj.dev.dao;

import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* Postsetdao数据接口层
* 招聘岗位
* @author
*
*/


public interface PostsetDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param postset
	 * @return 保存后的对象包括ID
	 */	
	public int savePostsetByField(Postset postset);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param postset 
	 * @return 保存后的对象包括ID
	 */	
	public int savePostset(Postset postset);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePostsetById(String id);
    
   	/**
	 * 根据对象删除
	 * @param postset
	 * @return 
	 */	
    public int deletePostsetByObject(Postset postset);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param postset 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePostsetByField(Postset postset);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param postset 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePostset(Postset postset);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Postset 
	 */	
    public Postset selectPostsetById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Postset>
	 */	
     public List<Postset> selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param postset 查询对象
	 * @return List<Postset>
	 */	
     public List<Postset> selectOnePageByPostset(Page page, Postset postset);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Postset>
	 */	
     public List<Postset> selectAllByMap(Map<String, Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Postset>
	 */	
     public List<Postset> selectAllByPostset(Postset postset);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Postset
	 */	
     public Postset selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Postset
	 */	
     public Postset selectObjectByPostset(Postset postset);

	/**
	 * 根据id批量删除
	 * @param idlist
	 */
	void deleteBatch(List<String> idlist);
	
	
	/**
	 * 通过岗位id，查询学科id
	 * @param map
	 */
	List<String> selectXKIDByGanweiId(Map<String,Object> map);

	/**
	 *通过code查找
	 * @param code
	 * @return
     */
	List<Postset> selectByCode(String code) ;

	/**
	 *通过name查找
	 * @param name
	 * @return
     */
	List<Postset> selectByName(String name) ;

	/**
	 *通过code和id查找
	 * @param map
	 * @return
	 */
	Postset selectByCodeAndId(Map<String,String> map) ;

	/**
	 *通过name和id查找
	 * @param map
	 * @return
	 */
	Postset selectByNameAndId(Map<String,String> map) ;


	/**
	 * 查询所有岗位
	 * @return
	 */
	List<Postset> selectAllPostset();
}

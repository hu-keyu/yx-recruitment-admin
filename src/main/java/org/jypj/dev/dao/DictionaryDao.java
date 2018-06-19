package org.jypj.dev.dao;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.util.Page;

public interface DictionaryDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param dictionary
	 * @return 保存后的对象包括ID
	 */	
	public int saveDictionaryByField(Dictionary dictionary);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param dictionary 
	 * @return 保存后的对象包括ID
	 */	
	public int saveDictionary(Dictionary dictionary);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteDictionaryById(String id);
    
   	/**
	 * 根据对象删除
	 * @param dictionary
	 * @return 
	 */	
    public int deleteDictionaryByObject(Dictionary dictionary);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param dictionary 
	 * @return 保存后的对象包括ID
	 */	
    public int updateDictionaryByField(Dictionary dictionary);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param dictionary 
	 * @return 保存后的对象包括ID
	 */	
    public int updateDictionary(Dictionary dictionary);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Dictionary 
	 */	
    public Dictionary selectDictionaryById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Dictionary>
	 */	
     public List<Dictionary> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param dictionary 查询对象
	 * @return List<Dictionary>
	 */	
     public List<Dictionary> selectOnePageByDictionary(Page page,Dictionary dictionary);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Dictionary>
	 */	
     public List<Dictionary> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Dictionary>
	 */	
     public List<Dictionary> selectAllByDictionary(Dictionary dictionary);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Dictionary
	 */	
     public Dictionary selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Dictionary
	 */	
     public Dictionary selectObjectByDictionary(Dictionary dictionary);

	/**
	 * 按条件
	 * @param map
     * @return list
     */
	List<Dictionary> searchByCodeAndText(Map<String,String> map);
}

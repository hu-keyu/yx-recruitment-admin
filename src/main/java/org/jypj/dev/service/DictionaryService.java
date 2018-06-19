package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.util.Page;

public interface DictionaryService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param dictionary
	 * @return 
	 */	
	public int saveDictionaryByField(Dictionary dictionary);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param dictionary 
	 * @return 
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
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteDictionaryByObject(Dictionary dictionary);
    
    /**
     * 批量删除
     * @return ids id列表
     */
    public int deleteAllByIds(String ids);
    
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
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByDictionary(Page page,Dictionary dictionary);
    
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
	 * 通过code和text模糊搜索
	 * @param dwdm
	 * @param name
     * @return
     */
	List<Dictionary> searchByCodeAndText(String code, String name);
}

package org.jypj.dev.dao;

import org.jypj.dev.entity.ThemeUnit;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* ThemeUnitdao数据接口层
* 招聘主题上报单位
* @author
*
*/


public interface ThemeUnitDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param themeUnit
	 * @return 保存后的对象包括ID
	 */	
	public int saveThemeUnitByField(ThemeUnit themeUnit);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param themeUnit 
	 * @return 保存后的对象包括ID
	 */	
	public int saveThemeUnit(ThemeUnit themeUnit);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteThemeUnitById(String id);
    
   	/**
	 * 根据对象删除
	 * @param themeUnit
	 * @return 
	 */	
    public int deleteThemeUnitByObject(ThemeUnit themeUnit);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param themeUnit 
	 * @return 保存后的对象包括ID
	 */	
    public int updateThemeUnitByField(ThemeUnit themeUnit);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param themeUnit 
	 * @return 保存后的对象包括ID
	 */	
    public int updateThemeUnit(ThemeUnit themeUnit);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ThemeUnit 
	 */	
    public ThemeUnit selectThemeUnitById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ThemeUnit>
	 */	
     public List<ThemeUnit> selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param themeUnit 查询对象
	 * @return List<ThemeUnit>
	 */	
     public List<ThemeUnit> selectOnePageByThemeUnit(Page page, ThemeUnit themeUnit);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ThemeUnit>
	 */	
     public List<ThemeUnit> selectAllByMap(Map<String, Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ThemeUnit>
	 */	
     public List<ThemeUnit> selectAllByThemeUnit(ThemeUnit themeUnit);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ThemeUnit
	 */	
     public ThemeUnit selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ThemeUnit
	 */	
     public ThemeUnit selectObjectByThemeUnit(ThemeUnit themeUnit);
}

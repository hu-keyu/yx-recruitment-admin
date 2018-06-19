package org.jypj.dev.dao;

import org.jypj.dev.entity.Menu;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* Menudao数据接口层
* 菜单表
* @author
*
*/


public interface MenuDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param menu
	 * @return 保存后的对象包括ID
	 */	
	public int saveMenuByField(Menu menu);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param menu 
	 * @return 保存后的对象包括ID
	 */	
	public int saveMenu(Menu menu);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteMenuById(String id);
    
   	/**
	 * 根据对象删除
	 * @param menu
	 * @return 
	 */	
    public int deleteMenuByObject(Menu menu);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param menu 
	 * @return 保存后的对象包括ID
	 */	
    public int updateMenuByField(Menu menu);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param menu 
	 * @return 保存后的对象包括ID
	 */	
    public int updateMenu(Menu menu);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Menu 
	 */	
    public Menu selectMenuById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Menu>
	 */	
     public List<Menu> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param menu 查询对象
	 * @return List<Menu>
	 */	
     public List<Menu> selectOnePageByMenu(Page page,Menu menu);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Menu>
	 */	
     public List<Menu> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Menu>
	 */	
     public List<Menu> selectAllByMenu(Menu menu);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Menu
	 */	
     public Menu selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Menu
	 */	
     public Menu selectObjectByMenu(Menu menu);
     
     
     /**
      *  查询菜单根据角色id进行分组
     * @param map
     * @return
     */
    public List<Menu> selectMenuGroupbyRole(Map<String,Object> map);
}

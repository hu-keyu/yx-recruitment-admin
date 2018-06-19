package org.jypj.dev.dao;

import org.jypj.dev.entity.Theme;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* Themedao数据接口层
* 招聘主题
* @author
*
*/


public interface ThemeDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param theme
	 * @return 保存后的对象包括ID
	 */	
	public int saveThemeByField(Theme theme);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param theme 
	 * @return 保存后的对象包括ID
	 */	
	public int saveTheme(Theme theme);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteThemeById(String id);
    
   	/**
	 * 根据对象删除
	 * @param theme
	 * @return 
	 */	
    public int deleteThemeByObject(Theme theme);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param theme 
	 * @return 保存后的对象包括ID
	 */	
    public int updateThemeByField(Theme theme);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param theme 
	 * @return 保存后的对象包括ID
	 */	
    public int updateTheme(Theme theme);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Theme 
	 */	
    public Theme selectThemeById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Theme>
	 */	
     public List<Theme> selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param theme 查询对象
	 * @return List<Theme>
	 */	
     public List<Theme> selectOnePageByTheme(Page page, Theme theme);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Theme>
	 */	
     public List<Theme> selectAllByMap(Map<String, Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Theme>
	 */	
     public List<Theme> selectAllByTheme(Theme theme);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Theme
	 */	
     public Theme selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Theme
	 */	
     public Theme selectObjectByTheme(Theme theme);

	/**
	 * 查询指定年份的招聘主题，如果没穿这个参数则查询当前年份的招聘主题
	 */
	List<Theme> selectAllByYear(String year);

	/**
	 *  查询notice表中没有用过的招聘主题
	 * @return
	 */
	List<Theme> selectThemeNotUsed();

	/**
	 * 更新步骤
	 * @param map
     */
	void updateStep(Map<String,Object> map);

	/**
	 * 查询某个招聘流程的步骤
	 * @param themeId
	 * @return
     */
	int selectStep(String themeId);
	
	/**
	 * 查询招聘主题列表
	 * @param map
	 * @return
	 */
	List<Theme> selectAllByYearMap(Map<String,Object> map);

	/**
	 * 获取此表中所有的年份
	 * @return
     */
	List<String> selectYears();
	
	/**
	 * 统计学校是否发布成绩、有多少学校已发布成绩、共有多少个学校审核资料审核通过、并查出招聘主题的步骤
	 * @param map
	 * @author QICAI
	 * @return
	 */
	public Theme queryTheme(Map<String,Object> map);
}

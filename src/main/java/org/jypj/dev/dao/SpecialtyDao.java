package org.jypj.dev.dao;

import org.jypj.dev.entity.Specialty;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* Specialtydao数据接口层
* 招聘专业
* @author
*
*/


public interface SpecialtyDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param specialty
	 * @return 保存后的对象包括ID
	 */	
	public int saveSpecialtyByField(Specialty specialty);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param specialty 
	 * @return 保存后的对象包括ID
	 */	
	public int saveSpecialty(Specialty specialty);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteSpecialtyById(String id);
    
   	/**
	 * 根据对象删除
	 * @param specialty
	 * @return 
	 */	
    public int deleteSpecialtyByObject(Specialty specialty);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param specialty 
	 * @return 保存后的对象包括ID
	 */	
    public int updateSpecialtyByField(Specialty specialty);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param specialty 
	 * @return 保存后的对象包括ID
	 */	
    public int updateSpecialty(Specialty specialty);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Specialty 
	 */	
    public Specialty selectSpecialtyById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Specialty>
	 */	
     public List<Specialty> selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param specialty 查询对象
	 * @return List<Specialty>
	 */	
     public List<Specialty> selectOnePageBySpecialty(Page page, Specialty specialty);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Specialty>
	 */	
     public List<Specialty> selectAllByMap(Map<String, Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Specialty>
	 */	
     public List<Specialty> selectAllBySpecialty(Specialty specialty);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Specialty
	 */	
     public Specialty selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Specialty
	 */	
     public Specialty selectObjectBySpecialty(Specialty specialty);

	/**
	 * 根据id批量删除
	 * @param idlist
	 */
	void deleteBatch(List<String> idlist);

	/**
	 * 查询所有的一级学科或者二级学科
	 * @param storey
	 * @return
	 */
	List<Specialty> findListByStorey(int storey);
	
	/**
	 * 根据关键字获取专业
	 * @param map
	 * @return
	 */
	List<Specialty> selectByKeyword(Map<String, Object> map);
	
	/**
	 * 获取专业，根据层级
	 * @param map
	 * @return
	 */
	List<Specialty> getSpecialListByLv(Map<String, Object> map);

	List<Specialty> selectByName(String name);

	/**
	 * 
	 * @param code
	 * @param storey
	 * @return
	 */
	public List<Specialty> selectByStoreyAndCode(Map<String,Object> map);

	/**
     *通过类型查出一级学科
     * @param type
     * @return
     */
	public List<Specialty> selectFirstByType(String type);

	
}

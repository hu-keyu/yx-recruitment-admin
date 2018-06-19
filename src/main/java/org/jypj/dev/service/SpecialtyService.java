package org.jypj.dev.service;

import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Specialty;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* Specialty业务接口层
* 招聘专业
* @author
*
*/

public interface SpecialtyService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param specialty
	 * @return 
	 */	
	public Result saveSpecialtyByField(Specialty specialty);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param specialty 
	 * @return 
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
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteSpecialtyByObject(Specialty specialty);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param specialty 
	 * @return 保存后的对象包括ID
	 */	
    public Result updateSpecialtyByField(Specialty specialty);
    
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
     public Page selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageBySpecialty(Page page, Specialty specialty);
    
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
	 * @param ids
     */
	void deleteBatch(String ids);

	/**
	 * 查询所有的一级学科或者二级学科
	 * @param storey
	 * @return
     */
	List<Specialty> findListByStorey(int storey);
	
	/**
	 * 根据关键字查询专业
	 * @param map
	 * @return
	 */
	List<Specialty> selectByKeyword(Map<String, Object> map);
	
	/**
	 * 根据层级获取专业，比如根据以及学科，或者二级学科
	 * @param map
	 * @return
	 */
	List<Specialty> getSpecialListByLv(Map<String, Object> map);

	List<Specialty> selectByName(String name);
	
	/**
	 * 通过code查询某一层下面有没有内容，可以查一级下面有没有二级，二级下面有没有三级，一级下面有没有三级（因为用模糊搜索）
	 * @param code
	 * @param storey
	 * @return
	 */
	List<Specialty> selectByStoreyAndCode(String code,int storey) ;

	/**
	 * 通过一级code查询到该一级下面的所有二级
	 * @param code
	 * @return
	 */
	public List<Specialty> selectSecondByFirstSbjCode(String code);

	 /**
     *通过类型查出一级学科
     * @param type
     * @return
     */
	public List<Specialty> selectFirstByType(String type);
}

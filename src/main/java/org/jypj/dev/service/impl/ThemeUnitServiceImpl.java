package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.ThemeUnit;
import org.jypj.dev.dao.ThemeUnitDao;
import org.jypj.dev.service.ThemeUnitService;
import org.jypj.dev.util.Page;

/**
* ThemeUnit业务接口实现层
* 招聘主题上报单位
* @author
*
*/

@Service("themeUnitService")
public class ThemeUnitServiceImpl implements ThemeUnitService {
	
    @Resource 
    private ThemeUnitDao themeUnitDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param themeUnit
	 * @return 保存后的对象包括ID
	 */	
	public int saveThemeUnitByField(ThemeUnit themeUnit){
	
		return themeUnitDao.saveThemeUnitByField(themeUnit);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param themeUnit 
	 * @return 保存后的对象包括ID
	 */	
	public int saveThemeUnit(ThemeUnit themeUnit){
	
		return themeUnitDao.saveThemeUnit(themeUnit);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteThemeUnitById(String id){
    
    	return themeUnitDao.deleteThemeUnitById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteThemeUnitByObject(ThemeUnit themeUnit){
    
    	return themeUnitDao.deleteThemeUnitByObject(themeUnit);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param themeUnit 
	 * @return 保存后的对象包括ID
	 */	
    public int updateThemeUnitByField(ThemeUnit themeUnit){
    
    	return themeUnitDao.updateThemeUnitByField(themeUnit);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param themeUnit 
	 * @return 保存后的对象包括ID
	 */	
    public int updateThemeUnit(ThemeUnit themeUnit){
    
    	return themeUnitDao.updateThemeUnit(themeUnit);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ThemeUnit 
	 */	
    public ThemeUnit selectThemeUnitById(String id){
    
    	return themeUnitDao.selectThemeUnitById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ThemeUnit>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ThemeUnit> themeUnits =themeUnitDao.selectOnePageByMap(page,map);
	     	if(themeUnits!=null&&themeUnits.size()>0){
	     		page.setResult(themeUnits);
	     	}else{
	     		page.setResult(new ArrayList<ThemeUnit>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param themeUnit  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByThemeUnit(Page page,ThemeUnit themeUnit){
 		 List<ThemeUnit> themeUnits =themeUnitDao.selectOnePageByThemeUnit(page,themeUnit);
	     	if(themeUnits!=null&&themeUnits.size()>0){
	     		page.setResult(themeUnits);
	     	}else{
	     		page.setResult(new ArrayList<ThemeUnit>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ThemeUnit>
	 */	
     public List<ThemeUnit> selectAllByMap(Map<String,Object> map){
     	return themeUnitDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ThemeUnit>
	 */	
     public List<ThemeUnit> selectAllByThemeUnit(ThemeUnit themeUnit){
     
    	 return themeUnitDao.selectAllByThemeUnit(themeUnit);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ThemeUnit
	 */	
     public ThemeUnit selectObjectByMap(Map<String,Object> map){
     
    	 return themeUnitDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ThemeUnit
	 */	
     public ThemeUnit selectObjectByThemeUnit(ThemeUnit themeUnit){
     
     	return themeUnitDao.selectObjectByThemeUnit(themeUnit);
     }
}
package org.jypj.dev.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.dao.ThemeDao;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.PlanApplyService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Service;

/**
* Theme业务接口实现层
* 招聘主题
* @author
*
*/

@Service("themeService")
public class ThemeServiceImpl implements ThemeService {
	
    @Resource 
    private ThemeDao themeDao;

	@Resource
	private PlanApplyService planApplyService ;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param theme
	 * @return 保存后的对象包括ID
	 */	
	public void saveThemeByField(Theme theme,User user){
		List<PlanApply> planList = new ArrayList<PlanApply>() ;
		if(StringUtils.isNotEmpty(theme.getId())){
			theme.setModifytime(new Date());
			planApplyService.deleteByProjectId(theme.getId()) ;
			themeDao.updateThemeByField(theme) ;
		}else{
			String uuid = UUID.randomUUID().toString().replaceAll("-","").toUpperCase() ;
			theme.setId(uuid);
			themeDao.saveThemeByField(theme);
		}
		if(theme.getEduIds().length()>0){
			String[] idArr = theme.getEduIds().split(",") ;
			for(String s : idArr){
				PlanApply p = new PlanApply() ;
				p.setProjectId(theme.getId());
				p.setStatus("0");
				p.setSchoolId(s);
				p.setProjectPublishWork(user.getOrginId());
				planList.add(p) ;
			}
			planApplyService.saveBatch(planList);
		}

	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param theme 
	 * @return 保存后的对象包括ID
	 */	
	public int saveTheme(Theme theme){
	
		return themeDao.saveTheme(theme);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteThemeById(String id){
    
    	return themeDao.deleteThemeById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteThemeByObject(Theme theme){
    
    	return themeDao.deleteThemeByObject(theme);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param theme 
	 * @return 保存后的对象包括ID
	 */	
    public int updateThemeByField(Theme theme){
    
    	return themeDao.updateThemeByField(theme);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param theme 
	 * @return 保存后的对象包括ID
	 */	
    public int updateTheme(Theme theme){
    
    	return themeDao.updateTheme(theme);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Theme 
	 */	
    public Theme selectThemeById(String id){
    
    	return themeDao.selectThemeById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Theme>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Theme> themes =themeDao.selectOnePageByMap(page,map);
		 for(Theme t : themes){
			 List<PlanApply> planlist = planApplyService.findPlanApplyListByThemeId(t.getId()) ;
			 List<String> eduIds = new ArrayList<String>();
			 for(PlanApply p : planlist){
				 eduIds.add(p.getSchoolId()) ;
			 }
			 List<Dictionary> schoollist =  DictionaryCache.getDictionaryByCode("dwdm");//单位代码;
			 List<String> eduNameList = new ArrayList<String>() ;
			 for(org.jypj.dev.entity.Dictionary d : schoollist){
				 if(eduIds.contains(d.getId())){
					 eduNameList.add(d.getText()) ;
				 }
			 }
			 String eduNames = "" ;
			 for(String s : eduNameList){
				 eduNames = eduNames+s+",";
			 }
			 t.setEduNames(eduNames);
		 }
	     	if(themes!=null&&themes.size()>0){
	     		page.setResult(themes);
	     	}else{
	     		page.setResult(new ArrayList<Theme>());
	     	}
	     	return page;
     }

     /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param theme  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByTheme(Page page,Theme theme){
 		 List<Theme> themes =themeDao.selectOnePageByTheme(page,theme);
	     	if(themes!=null&&themes.size()>0){
	     		page.setResult(themes);
	     	}else{
	     		page.setResult(new ArrayList<Theme>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Theme>
	 */	
     public List<Theme> selectAllByMap(Map<String,Object> map){
     	return themeDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Theme>
	 */	
     public List<Theme> selectAllByTheme(Theme theme){
     
    	 return themeDao.selectAllByTheme(theme);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Theme
	 */	
     public Theme selectObjectByMap(Map<String,Object> map){
     
    	 return themeDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Theme
	 */	
     public Theme selectObjectByTheme(Theme theme){

     	return themeDao.selectObjectByTheme(theme);
     }

	@Override
	public List<Theme> selectAllByYear(String year) {
		if(StringUtils.isEmpty(year)){
			year = new SimpleDateFormat("yyyy").format(new Date()) ;
		}
		 return themeDao.selectAllByYear(year) ;
	}

	@Override
	public List<Theme> selectThemeNotUsed() {
		return themeDao.selectThemeNotUsed();
	}

	@Override
	public void updateStep(String themeId,int step) {
		Map<String,Object> map = new HashMap<String, Object>() ;
		map.put("num",step) ;
		map.put("themeId",themeId) ;
		themeDao.updateStep(map) ;
	}

	@Override
	public int selectStep(String themeId) {
		return themeDao.selectStep(themeId);
	}

	@Override
	public List<Theme> selectAllByYearMap(Map<String, Object> map) {
		return themeDao.selectAllByYearMap(map);
	}

	@Override
	public List<String> selectYears() {
		return themeDao.selectYears();
	}

	@Override
	public Theme queryTheme(Map<String, Object> map) {
		return themeDao.queryTheme(map);
	}
}
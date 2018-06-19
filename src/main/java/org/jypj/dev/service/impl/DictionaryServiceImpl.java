package org.jypj.dev.service.impl;


import java.util.*;

import javax.annotation.Resource;

import org.jypj.dev.dao.DictionaryDao;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.springframework.stereotype.Service;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
	
    @Resource 
    private DictionaryDao dictionaryDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param dictionary
	 * @return 保存后的对象包括ID
	 */	
	public int saveDictionaryByField(Dictionary dictionary){
	
		return dictionaryDao.saveDictionaryByField(dictionary);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param dictionary 
	 * @return 保存后的对象包括ID
	 */	
	public int saveDictionary(Dictionary dictionary){
		String id = UUID.randomUUID().toString().replace("-", "");
		dictionary.setId(id);
		return dictionaryDao.saveDictionary(dictionary);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteDictionaryById(String id){
    
    	return dictionaryDao.deleteDictionaryById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteDictionaryByObject(Dictionary dictionary){
    
    	return dictionaryDao.deleteDictionaryByObject(dictionary);
    }
    /**
     * 批量删除
     */
	public int deleteAllByIds(String ids) {
		int code =0;
		try {
			if(StringUtil.isNotEmpty(ids)){
				String[] idArr = ids.split(",");
				for(String id:idArr){
					dictionaryDao.deleteDictionaryById(id);
				}
			}
		} catch (Exception e) {
			code=-1;
			e.printStackTrace();
		}
		return code;
	}
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param dictionary 
	 * @return 保存后的对象包括ID
	 */	
    public int updateDictionaryByField(Dictionary dictionary){
    
    	return dictionaryDao.updateDictionaryByField(dictionary);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param dictionary 
	 * @return 保存后的对象包括ID
	 */	
    public int updateDictionary(Dictionary dictionary){
    
    	return dictionaryDao.updateDictionary(dictionary);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Dictionary 
	 */	
    public Dictionary selectDictionaryById(String id){
    
    	return dictionaryDao.selectDictionaryById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Dictionary>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Dictionary> dictionarys =dictionaryDao.selectOnePageByMap(page,map);
	     	if(dictionarys!=null&&dictionarys.size()>0){
	     		page.setResult(dictionarys);
	     	}else{
	     		page.setResult(new ArrayList<Dictionary>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param dictionary  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByDictionary(Page page,Dictionary dictionary){
 		 List<Dictionary> dictionarys =dictionaryDao.selectOnePageByDictionary(page,dictionary);
	     	if(dictionarys!=null&&dictionarys.size()>0){
	     		page.setResult(dictionarys);
	     	}else{
	     		page.setResult(new ArrayList<Dictionary>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Dictionary>
	 */	
     public List<Dictionary> selectAllByMap(Map<String,Object> map){
     	return dictionaryDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Dictionary>
	 */	
     public List<Dictionary> selectAllByDictionary(Dictionary dictionary){
     
    	 return dictionaryDao.selectAllByDictionary(dictionary);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Dictionary
	 */	
     public Dictionary selectObjectByMap(Map<String,Object> map){
     
    	 return dictionaryDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Dictionary
	 */	
     public Dictionary selectObjectByDictionary(Dictionary dictionary){
     
     	return dictionaryDao.selectObjectByDictionary(dictionary);
     }

	@Override
	public List<Dictionary> searchByCodeAndText(String code, String name) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put("code",code) ;
		map.put("text",name) ;
		return dictionaryDao.searchByCodeAndText(map);
	}

}
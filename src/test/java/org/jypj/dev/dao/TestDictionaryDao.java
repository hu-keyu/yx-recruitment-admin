package org.jypj.dev.dao;

import java.util.List;

import org.jypj.dev.entity.Dictionary;

public interface TestDictionaryDao {
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Dictionary>
	 */	
     public List<Dictionary> selectAllByDictionary(Dictionary dictionary);
     /**
 	 * 按条件查询全部的
 	 * @param map  查询条件  
 	 * @return  List<Dictionary>
 	 */	
      public List<Dictionary> selectAllByCode(String code);
}

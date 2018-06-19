package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.EaxmLabsRecord;
import org.jypj.dev.dao.EaxmLabsRecordDao;
import org.jypj.dev.service.EaxmLabsRecordService;
import org.jypj.dev.util.Page;

/**
* EaxmLabsRecord业务接口实现层
* 试室分配考生记录表
* @author
*
*/

@Service("eaxmLabsRecordService")
public class EaxmLabsRecordServiceImpl implements EaxmLabsRecordService {
	
    @Resource 
    private EaxmLabsRecordDao eaxmLabsRecordDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param eaxmLabsRecord
	 * @return 保存后的对象包括ID
	 */	
	public int saveEaxmLabsRecordByField(EaxmLabsRecord eaxmLabsRecord){
	
		return eaxmLabsRecordDao.saveEaxmLabsRecordByField(eaxmLabsRecord);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param eaxmLabsRecord 
	 * @return 保存后的对象包括ID
	 */	
	public int saveEaxmLabsRecord(EaxmLabsRecord eaxmLabsRecord){
	
		return eaxmLabsRecordDao.saveEaxmLabsRecord(eaxmLabsRecord);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteEaxmLabsRecordById(String id){
    
    	return eaxmLabsRecordDao.deleteEaxmLabsRecordById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteEaxmLabsRecordByObject(EaxmLabsRecord eaxmLabsRecord){
    
    	return eaxmLabsRecordDao.deleteEaxmLabsRecordByObject(eaxmLabsRecord);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param eaxmLabsRecord 
	 * @return 保存后的对象包括ID
	 */	
    public int updateEaxmLabsRecordByField(EaxmLabsRecord eaxmLabsRecord){
    
    	return eaxmLabsRecordDao.updateEaxmLabsRecordByField(eaxmLabsRecord);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param eaxmLabsRecord 
	 * @return 保存后的对象包括ID
	 */	
    public int updateEaxmLabsRecord(EaxmLabsRecord eaxmLabsRecord){
    
    	return eaxmLabsRecordDao.updateEaxmLabsRecord(eaxmLabsRecord);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return EaxmLabsRecord 
	 */	
    public EaxmLabsRecord selectEaxmLabsRecordById(String id){
    
    	return eaxmLabsRecordDao.selectEaxmLabsRecordById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<EaxmLabsRecord>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<EaxmLabsRecord> eaxmLabsRecords =eaxmLabsRecordDao.selectOnePageByMap(page,map);
	     	if(eaxmLabsRecords!=null&&eaxmLabsRecords.size()>0){
	     		page.setResult(eaxmLabsRecords);
	     	}else{
	     		page.setResult(new ArrayList<EaxmLabsRecord>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param eaxmLabsRecord  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByEaxmLabsRecord(Page page,EaxmLabsRecord eaxmLabsRecord){
 		 List<EaxmLabsRecord> eaxmLabsRecords =eaxmLabsRecordDao.selectOnePageByEaxmLabsRecord(page,eaxmLabsRecord);
	     	if(eaxmLabsRecords!=null&&eaxmLabsRecords.size()>0){
	     		page.setResult(eaxmLabsRecords);
	     	}else{
	     		page.setResult(new ArrayList<EaxmLabsRecord>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<EaxmLabsRecord>
	 */	
     public List<EaxmLabsRecord> selectAllByMap(Map<String,Object> map){
     	return eaxmLabsRecordDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<EaxmLabsRecord>
	 */	
     public List<EaxmLabsRecord> selectAllByEaxmLabsRecord(EaxmLabsRecord eaxmLabsRecord){
     
    	 return eaxmLabsRecordDao.selectAllByEaxmLabsRecord(eaxmLabsRecord);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  EaxmLabsRecord
	 */	
     public EaxmLabsRecord selectObjectByMap(Map<String,Object> map){
     
    	 return eaxmLabsRecordDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  EaxmLabsRecord
	 */	
     public EaxmLabsRecord selectObjectByEaxmLabsRecord(EaxmLabsRecord eaxmLabsRecord){
     
     	return eaxmLabsRecordDao.selectObjectByEaxmLabsRecord(eaxmLabsRecord);
     }
}
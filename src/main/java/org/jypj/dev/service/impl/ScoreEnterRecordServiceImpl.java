package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.ScoreEnterRecord;
import org.jypj.dev.dao.ScoreEnterRecordDao;
import org.jypj.dev.service.ScoreEnterRecordService;
import org.jypj.dev.util.Page;

/**
* ScoreEnterRecord业务接口实现层
* 入围情况记录表
* @author
*
*/

@Service("scoreEnterRecordService")
public class ScoreEnterRecordServiceImpl implements ScoreEnterRecordService {
	
    @Resource 
    private ScoreEnterRecordDao scoreEnterRecordDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterRecord
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterRecordByField(ScoreEnterRecord scoreEnterRecord){
	
		return scoreEnterRecordDao.saveScoreEnterRecordByField(scoreEnterRecord);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterRecord 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterRecord(ScoreEnterRecord scoreEnterRecord){
	
		return scoreEnterRecordDao.saveScoreEnterRecord(scoreEnterRecord);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterRecordById(String id){
    
    	return scoreEnterRecordDao.deleteScoreEnterRecordById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterRecordByObject(ScoreEnterRecord scoreEnterRecord){
    
    	return scoreEnterRecordDao.deleteScoreEnterRecordByObject(scoreEnterRecord);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterRecord 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterRecordByField(ScoreEnterRecord scoreEnterRecord){
    
    	return scoreEnterRecordDao.updateScoreEnterRecordByField(scoreEnterRecord);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterRecord 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterRecord(ScoreEnterRecord scoreEnterRecord){
    
    	return scoreEnterRecordDao.updateScoreEnterRecord(scoreEnterRecord);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterRecord 
	 */	
    public ScoreEnterRecord selectScoreEnterRecordById(String id){
    
    	return scoreEnterRecordDao.selectScoreEnterRecordById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterRecord>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreEnterRecord> scoreEnterRecords =scoreEnterRecordDao.selectOnePageByMap(page,map);
	     	if(scoreEnterRecords!=null&&scoreEnterRecords.size()>0){
	     		page.setResult(scoreEnterRecords);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterRecord>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterRecord  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterRecord(Page page,ScoreEnterRecord scoreEnterRecord){
 		 List<ScoreEnterRecord> scoreEnterRecords =scoreEnterRecordDao.selectOnePageByScoreEnterRecord(page,scoreEnterRecord);
	     	if(scoreEnterRecords!=null&&scoreEnterRecords.size()>0){
	     		page.setResult(scoreEnterRecords);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterRecord>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterRecord>
	 */	
     public List<ScoreEnterRecord> selectAllByMap(Map<String,Object> map){
     	return scoreEnterRecordDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterRecord>
	 */	
     public List<ScoreEnterRecord> selectAllByScoreEnterRecord(ScoreEnterRecord scoreEnterRecord){
     
    	 return scoreEnterRecordDao.selectAllByScoreEnterRecord(scoreEnterRecord);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterRecord
	 */	
     public ScoreEnterRecord selectObjectByMap(Map<String,Object> map){
     
    	 return scoreEnterRecordDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterRecord
	 */	
     public ScoreEnterRecord selectObjectByScoreEnterRecord(ScoreEnterRecord scoreEnterRecord){
     
     	return scoreEnterRecordDao.selectObjectByScoreEnterRecord(scoreEnterRecord);
     }
}
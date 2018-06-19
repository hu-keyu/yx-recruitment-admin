package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.dao.GradeAdjustLogDao;
import org.jypj.dev.entity.GradeAdjustLog;
import org.jypj.dev.service.GradeAdjustLogService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Service;

@Service("gradeAdjustLogService")
public class GradeAdjustLogServiceImpl implements GradeAdjustLogService {
	
    @Resource 
    private GradeAdjustLogDao gradeAdjustLogDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param gradeAdjustLog
	 * @return 保存后的对象包括ID
	 */	
	public int saveGradeAdjustLogByField(GradeAdjustLog gradeAdjustLog){
	
		return gradeAdjustLogDao.saveGradeAdjustLogByField(gradeAdjustLog);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param gradeAdjustLog 
	 * @return 保存后的对象包括ID
	 */	
	public int saveGradeAdjustLog(GradeAdjustLog gradeAdjustLog){
	
		return gradeAdjustLogDao.saveGradeAdjustLog(gradeAdjustLog);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteGradeAdjustLogById(String id){
    
    	return gradeAdjustLogDao.deleteGradeAdjustLogById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteGradeAdjustLogByObject(GradeAdjustLog gradeAdjustLog){
    
    	return gradeAdjustLogDao.deleteGradeAdjustLogByObject(gradeAdjustLog);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param gradeAdjustLog 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeAdjustLogByField(GradeAdjustLog gradeAdjustLog){
    
    	return gradeAdjustLogDao.updateGradeAdjustLogByField(gradeAdjustLog);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param gradeAdjustLog 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeAdjustLog(GradeAdjustLog gradeAdjustLog){
    
    	return gradeAdjustLogDao.updateGradeAdjustLog(gradeAdjustLog);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return GradeAdjustLog 
	 */	
    public GradeAdjustLog selectGradeAdjustLogById(String id){
    
    	return gradeAdjustLogDao.selectGradeAdjustLogById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<GradeAdjustLog>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<GradeAdjustLog> gradeAdjustLogs =gradeAdjustLogDao.selectOnePageByMap(page,map);
	     	if(gradeAdjustLogs!=null&&gradeAdjustLogs.size()>0){
	     		page.setResult(gradeAdjustLogs);
	     	}else{
	     		page.setResult(new ArrayList<GradeAdjustLog>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param gradeAdjustLog  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByGradeAdjustLog(Page page,GradeAdjustLog gradeAdjustLog){
 		 List<GradeAdjustLog> gradeAdjustLogs =gradeAdjustLogDao.selectOnePageByGradeAdjustLog(page,gradeAdjustLog);
	     	if(gradeAdjustLogs!=null&&gradeAdjustLogs.size()>0){
	     		page.setResult(gradeAdjustLogs);
	     	}else{
	     		page.setResult(new ArrayList<GradeAdjustLog>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<GradeAdjustLog>
	 */	
     public List<GradeAdjustLog> selectAllByMap(Map<String,Object> map){
     	return gradeAdjustLogDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<GradeAdjustLog>
	 */	
     public List<GradeAdjustLog> selectAllByGradeAdjustLog(GradeAdjustLog gradeAdjustLog){
     
    	 return gradeAdjustLogDao.selectAllByGradeAdjustLog(gradeAdjustLog);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  GradeAdjustLog
	 */	
     public GradeAdjustLog selectObjectByMap(Map<String,Object> map){
     
    	 return gradeAdjustLogDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  GradeAdjustLog
	 */	
     public GradeAdjustLog selectObjectByGradeAdjustLog(GradeAdjustLog gradeAdjustLog){
     
     	return gradeAdjustLogDao.selectObjectByGradeAdjustLog(gradeAdjustLog);
     }
}
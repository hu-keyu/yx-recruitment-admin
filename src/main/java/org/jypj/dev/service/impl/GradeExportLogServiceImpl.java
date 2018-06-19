package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.dao.GradeExportLogDao;
import org.jypj.dev.entity.GradeExportLog;
import org.jypj.dev.service.GradeExportLogService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Service;

@Service("gradeExportLogService")
public class GradeExportLogServiceImpl implements GradeExportLogService {
	
    @Resource 
    private GradeExportLogDao gradeExportLogDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param gradeExportLog
	 * @return 保存后的对象包括ID
	 */	
	public int saveGradeExportLogByField(GradeExportLog gradeExportLog){
	
		return gradeExportLogDao.saveGradeExportLogByField(gradeExportLog);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param gradeExportLog 
	 * @return 保存后的对象包括ID
	 */	
	public int saveGradeExportLog(GradeExportLog gradeExportLog){
	
		return gradeExportLogDao.saveGradeExportLog(gradeExportLog);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteGradeExportLogById(String id){
    
    	return gradeExportLogDao.deleteGradeExportLogById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteGradeExportLogByObject(GradeExportLog gradeExportLog){
    
    	return gradeExportLogDao.deleteGradeExportLogByObject(gradeExportLog);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param gradeExportLog 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeExportLogByField(GradeExportLog gradeExportLog){
    
    	return gradeExportLogDao.updateGradeExportLogByField(gradeExportLog);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param gradeExportLog 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeExportLog(GradeExportLog gradeExportLog){
    
    	return gradeExportLogDao.updateGradeExportLog(gradeExportLog);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return GradeExportLog 
	 */	
    public GradeExportLog selectGradeExportLogById(String id){
    
    	return gradeExportLogDao.selectGradeExportLogById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<GradeExportLog>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<GradeExportLog> gradeExportLogs =gradeExportLogDao.selectOnePageByMap(page,map);
	     	if(gradeExportLogs!=null&&gradeExportLogs.size()>0){
	     		page.setResult(gradeExportLogs);
	     	}else{
	     		page.setResult(new ArrayList<GradeExportLog>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param gradeExportLog  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByGradeExportLog(Page page,GradeExportLog gradeExportLog){
 		 List<GradeExportLog> gradeExportLogs =gradeExportLogDao.selectOnePageByGradeExportLog(page,gradeExportLog);
	     	if(gradeExportLogs!=null&&gradeExportLogs.size()>0){
	     		page.setResult(gradeExportLogs);
	     	}else{
	     		page.setResult(new ArrayList<GradeExportLog>());
	     	}
	     	return page;
     }
     
     /**
 	 * 分页查询 包含条件（教育局端错误文件）
 	 * @param page 分页对象
 	 * @param map  查询条件  
 	 * @return  List<GradeExportLog>
 	 */	
     @Override
 	 public Page selectScorePageByMap(Page page, Map<String, Object> map) {
    	 List<GradeExportLog> gradeExportLogs =gradeExportLogDao.selectScorePageByMap(page, map);
	     	if(gradeExportLogs!=null&&gradeExportLogs.size()>0){
	     		page.setResult(gradeExportLogs);
	     	}else{
	     		page.setResult(new ArrayList<GradeExportLog>());
	     	}
	     return page;
 	 }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<GradeExportLog>
	 */	
     public List<GradeExportLog> selectAllByMap(Map<String,Object> map){
     	return gradeExportLogDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<GradeExportLog>
	 */	
     public List<GradeExportLog> selectAllByGradeExportLog(GradeExportLog gradeExportLog){
     
    	 return gradeExportLogDao.selectAllByGradeExportLog(gradeExportLog);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  GradeExportLog
	 */	
     public GradeExportLog selectObjectByMap(Map<String,Object> map){
     
    	 return gradeExportLogDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  GradeExportLog
	 */	
     public GradeExportLog selectObjectByGradeExportLog(GradeExportLog gradeExportLog){
     
     	return gradeExportLogDao.selectObjectByGradeExportLog(gradeExportLog);
     }

}
package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.jypj.dev.dao.StudentEduInfoDao;
import org.jypj.dev.entity.StudentEduInfo;
import org.jypj.dev.service.StudentEduInfoService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.springframework.stereotype.Service;

/**
* StudentEduInfo业务接口实现层
* 考生学历信息表
* @author
*
*/

@Service("studentEduInfoService")
public class StudentEduInfoServiceImpl implements StudentEduInfoService {
	
    @Resource 
    private StudentEduInfoDao studentEduInfoDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param studentEduInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentEduInfoByField(StudentEduInfo studentEduInfo){
	
		return studentEduInfoDao.saveStudentEduInfoByField(studentEduInfo);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param studentEduInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentEduInfo(StudentEduInfo studentEduInfo){
	
		return studentEduInfoDao.saveStudentEduInfo(studentEduInfo);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteStudentEduInfoById(String id){
    
    	return studentEduInfoDao.deleteStudentEduInfoById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteStudentEduInfoByObject(StudentEduInfo studentEduInfo){
    
    	return studentEduInfoDao.deleteStudentEduInfoByObject(studentEduInfo);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param studentEduInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentEduInfoByField(StudentEduInfo studentEduInfo){
    
    	return studentEduInfoDao.updateStudentEduInfoByField(studentEduInfo);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param studentEduInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentEduInfo(StudentEduInfo studentEduInfo){
    
    	return studentEduInfoDao.updateStudentEduInfo(studentEduInfo);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return StudentEduInfo 
	 */	
    public StudentEduInfo selectStudentEduInfoById(String id){
    
    	return studentEduInfoDao.selectStudentEduInfoById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<StudentEduInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<StudentEduInfo> studentEduInfos =studentEduInfoDao.selectOnePageByMap(page,map);
	     	if(studentEduInfos!=null&&studentEduInfos.size()>0){
	     		page.setResult(studentEduInfos);
	     	}else{
	     		page.setResult(new ArrayList<StudentEduInfo>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param studentEduInfo  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByStudentEduInfo(Page page,StudentEduInfo studentEduInfo){
 		 List<StudentEduInfo> studentEduInfos =studentEduInfoDao.selectOnePageByStudentEduInfo(page,studentEduInfo);
	     	if(studentEduInfos!=null&&studentEduInfos.size()>0){
	     		page.setResult(studentEduInfos);
	     	}else{
	     		page.setResult(new ArrayList<StudentEduInfo>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentEduInfo>
	 */	
     public List<StudentEduInfo> selectAllByMap(Map<String,Object> map){
     	return studentEduInfoDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentEduInfo>
	 */	
     public List<StudentEduInfo> selectAllByStudentEduInfo(StudentEduInfo studentEduInfo){
     
    	 return studentEduInfoDao.selectAllByStudentEduInfo(studentEduInfo);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentEduInfo
	 */	
     public StudentEduInfo selectObjectByMap(Map<String,Object> map){
     
    	 return studentEduInfoDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentEduInfo
	 */	
     public StudentEduInfo selectObjectByStudentEduInfo(StudentEduInfo studentEduInfo){
     
     	return studentEduInfoDao.selectObjectByStudentEduInfo(studentEduInfo);
     }

	@Override
	public List<StudentEduInfo> selectForEduCheck() {
		return null;
	}

    @Override
    public int deleteAndSave(List<StudentEduInfo> studentEduInfoList, String sid, String recruitId) {
    	if(StringUtil.isEmpty(sid)||StringUtil.isEmpty(recruitId)){
    		throw new RuntimeException();
    	}
    	StudentEduInfo studentEduInfo=new StudentEduInfo();
    	studentEduInfo.setStudentId(sid);
    	studentEduInfo.setEmployItemsId(recruitId);
    	int x=studentEduInfoDao.deleteStudentEduInfoByObject(studentEduInfo);
    	
    	int y=0;
    	for(StudentEduInfo s:studentEduInfoList){
    		if(StringUtil.isNotEmpty(s.getEduSchoolName())){
                s.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                //s.setCtime(new Date());
                s.setCreateUser(sid);
                s.setModifyUser(sid);
                //s.setMtime(new Date());
                y+=studentEduInfoDao.saveStudentEduInfo(s);
    		}
    	}
    	return x+y;
    }

	@Override
	public int selectCountBySpecialty(String specialty) {
		return studentEduInfoDao.selectCountBySpecialty(specialty);
	}
}
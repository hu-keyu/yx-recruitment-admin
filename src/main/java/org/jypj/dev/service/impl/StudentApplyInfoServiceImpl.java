package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.dao.PostsetDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.dao.StudentInfoDao;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.service.StudentApplyInfoService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;
import org.springframework.stereotype.Service;

/**
* StudentApplyInfo业务接口实现层
* 考生报考信息表
* @author
*
*/

@Service("studentApplyInfoService")
public class StudentApplyInfoServiceImpl implements StudentApplyInfoService {
	
    @Resource 
    private StudentApplyInfoDao studentApplyInfoDao;
    
    @Resource
    private PostsetDao postsetDao;
    
    @Resource
    private StudentInfoDao studentInfoDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param studentApplyInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentApplyInfoByField(StudentApplyInfo studentApplyInfo){
	
		return studentApplyInfoDao.saveStudentApplyInfoByField(studentApplyInfo);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param studentApplyInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentApplyInfo(StudentApplyInfo studentApplyInfo){
	
		return studentApplyInfoDao.saveStudentApplyInfo(studentApplyInfo);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteStudentApplyInfoById(String id){
    
    	return studentApplyInfoDao.deleteStudentApplyInfoById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteStudentApplyInfoByObject(StudentApplyInfo studentApplyInfo){
    
    	return studentApplyInfoDao.deleteStudentApplyInfoByObject(studentApplyInfo);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param studentApplyInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentApplyInfoByField(StudentApplyInfo studentApplyInfo){
    
    	return studentApplyInfoDao.updateStudentApplyInfoByField(studentApplyInfo);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param studentApplyInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentApplyInfo(StudentApplyInfo studentApplyInfo){
    
    	return studentApplyInfoDao.updateStudentApplyInfo(studentApplyInfo);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return StudentApplyInfo 
	 */	
    public StudentApplyInfo selectStudentApplyInfoById(String id){
    
    	return studentApplyInfoDao.selectStudentApplyInfoById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<StudentApplyInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<StudentApplyInfo> studentApplyInfos =studentApplyInfoDao.selectOnePageByMap(page,map);
	     	if(studentApplyInfos!=null&&studentApplyInfos.size()>0){
	     		page.setResult(studentApplyInfos);
	     	}else{
	     		page.setResult(new ArrayList<StudentApplyInfo>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param studentApplyInfo  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByStudentApplyInfo(Page page,StudentApplyInfo studentApplyInfo){
 		 List<StudentApplyInfo> studentApplyInfos =studentApplyInfoDao.selectOnePageByStudentApplyInfo(page,studentApplyInfo);
	     	if(studentApplyInfos!=null&&studentApplyInfos.size()>0){
	     		page.setResult(studentApplyInfos);
	     	}else{
	     		page.setResult(new ArrayList<StudentApplyInfo>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentApplyInfo>
	 */	
     public List<StudentApplyInfo> selectAllByMap(Map<String,Object> map){
     	return studentApplyInfoDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentApplyInfo>
	 */	
     public List<StudentApplyInfo> selectAllByStudentApplyInfo(StudentApplyInfo studentApplyInfo){
     
    	 return studentApplyInfoDao.selectAllByStudentApplyInfo(studentApplyInfo);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentApplyInfo
	 */	
     public StudentApplyInfo selectObjectByMap(Map<String,Object> map){
     
    	 return studentApplyInfoDao.selectObjectByMap(map);
     }
     
     @Override
 	public Integer selectStatus(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return studentApplyInfoDao.selectStatus(map);
 	}
     

 	@Override
 	public Integer selectListAuditorCount(String itemId) {
 		// TODO Auto-generated method stub
 		return studentApplyInfoDao.selectListAuditorCount(itemId);
 	}
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentApplyInfo
	 */	
     public StudentApplyInfo selectObjectByStudentApplyInfo(StudentApplyInfo studentApplyInfo){
     
     	return studentApplyInfoDao.selectObjectByStudentApplyInfo(studentApplyInfo);
     }

    @Override
 	public Page selectPageEnter(Page page, Map<String, Object> map) {
 		List<ScoreEntersOutVo> studentApplyInfos =studentApplyInfoDao.selectPageEnter(page, map);
     	if(studentApplyInfos!=null&&studentApplyInfos.size()>0){
     		page.setResult(studentApplyInfos);
     	}else{
     		page.setResult(new ArrayList<StudentApplyInfo>());
     	}
     	return page;
 	}

 	@Override
 	public List<ScoreEntersOutVo> selectAllpublish(Map<String, Object> map) {
 		
 		return studentApplyInfoDao.selectAllpublish(map);
 	}
     
    @Override
    public Integer verifyCode(Map<String, Object> map) {
        return studentApplyInfoDao.verifyCode(map);
    }

    @Override
    public Integer appliedStudent(Map<String, Object> map) {
        return studentApplyInfoDao.appliedStudent(map);
    }

    @Override
    public void deleteAllObjectBySid(String sid) {
        studentApplyInfoDao.deleteAllObjectBySid(sid);
    }

    @Override
    public void deleteAndSave(StudentApplyInfo sai) {
        StudentApplyInfo temp = new StudentApplyInfo();
        temp.setEmployItemsId(sai.getEmployItemsId());
        temp.setStudentId(sai.getStudentId());
        //对于重新选择的岗位，必须先判断这个考生对应这个招聘主题是否有未提交的申请，有的话，先删除掉
        List<StudentApplyInfo> saiList = studentApplyInfoDao.selectAllByStudentApplyInfo(temp);
        for (StudentApplyInfo s : saiList) {
            studentApplyInfoDao.deleteStudentApplyInfoByObject(s);
        }
        sai.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        sai.setCreateUser(sai.getStudentId());
        sai.setCtime(new Date());
        studentApplyInfoDao.saveStudentApplyInfo(sai);
        
        //判断申报的岗位是否是中职专业课的，更新studen_info
        if (StringUtils.isNotEmpty(sai.getApplyJobId())) {
            Postset ps = postsetDao.selectPostsetById(sai.getApplyJobId());
            if (ps != null && "1".equals(ps.getIszz())) {
                //中职专业课
                StudentInfo si = new StudentInfo();
                si.setId(sai.getStudentId());
                si.setProfessionalCourse("1");
                studentInfoDao.updateStudentInfoByField(si);
            }
        }
    }

    @Override
    public Integer checkApplyStatus(StudentApplyInfo sai) {
        return studentApplyInfoDao.checkApplyStatus(sai);
    }

    @Override
    public Integer selectCountOfEnterWritten(StudentApplyInfo sai) {
        return studentApplyInfoDao.selectCountOfEnterWritten(sai);
    }

	@Override
	public int selectByApplyJobId(String s) {
		return studentApplyInfoDao.selectByApplyJobId(s);
	}
}
package org.jypj.dev.service.impl;


import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.dao.StudentInfoDao;
import org.jypj.dev.dao.ThemeDao;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.util.Page;

/**
* StudentInfo业务接口实现层
* 考生基本信息表
* @author
*
*/

@Service("studentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService {
	
    @Resource 
    private StudentInfoDao studentInfoDao;
	@Resource
	private StudentApplyInfoDao studentApplyInfoDao;
	@Resource
    private ThemeDao themeDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param studentInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentInfoByField(StudentInfo studentInfo){
	
		return studentInfoDao.saveStudentInfoByField(studentInfo);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param studentInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveStudentInfo(StudentInfo studentInfo){
	
		return studentInfoDao.saveStudentInfo(studentInfo);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteStudentInfoById(String id){
    
    	return studentInfoDao.deleteStudentInfoById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteStudentInfoByObject(StudentInfo studentInfo){
    
    	return studentInfoDao.deleteStudentInfoByObject(studentInfo);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param studentInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentInfoByField(StudentInfo studentInfo){
    
    	return studentInfoDao.updateStudentInfoByField(studentInfo);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param studentInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateStudentInfo(StudentInfo studentInfo){
    
    	return studentInfoDao.updateStudentInfo(studentInfo);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return StudentInfo 
	 */	
    public StudentInfo selectStudentInfoById(String id){
    
    	return studentInfoDao.selectStudentInfoById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<StudentInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<StudentInfo> studentInfos =studentInfoDao.selectOnePageByMap(page,map);
	     	if(studentInfos!=null&&studentInfos.size()>0){
	     		page.setResult(studentInfos);
	     	}else{
	     		page.setResult(new ArrayList<StudentInfo>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param studentInfo  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByStudentInfo(Page page,StudentInfo studentInfo){
 		 List<StudentInfo> studentInfos =studentInfoDao.selectOnePageByStudentInfo(page,studentInfo);
	     	if(studentInfos!=null&&studentInfos.size()>0){
	     		page.setResult(studentInfos);
	     	}else{
	     		page.setResult(new ArrayList<StudentInfo>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentInfo>
	 */	
     public List<StudentInfo> selectAllByMap(Map<String,Object> map){
     	return studentInfoDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<StudentInfo>
	 */	
     public List<StudentInfo> selectAllByStudentInfo(StudentInfo studentInfo){
     
    	 return studentInfoDao.selectAllByStudentInfo(studentInfo);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentInfo
	 */	
     public StudentInfo selectObjectByMap(Map<String,Object> map){
     
    	 return studentInfoDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  StudentInfo
	 */	
     public StudentInfo selectObjectByStudentInfo(StudentInfo studentInfo){
     
     	return studentInfoDao.selectObjectByStudentInfo(studentInfo);
     }

    @Override
    public Integer selectExpertSubject(Map<String, Object> map) {
        return studentInfoDao.selectExpertSubject(map);
    }

	@Override
	public Page queryNoAuditList(Page page, Map<String, Object> map) {
		List<StudentInfo> studentInfos =studentInfoDao.queryNoAuditList(page,map);
     	if(studentInfos!=null && studentInfos.size()>0){
     		page.setResult(studentInfos);
     	}else{
     		page.setResult(new ArrayList<StudentInfo>());
     	}
     	return page;
	}

	@Override
	public Page queryAuditList(Page page, Map<String, Object> map) {
		List<StudentInfo> studentInfos =studentInfoDao.queryAuditList(page,map);
     	if(studentInfos!=null && studentInfos.size()>0){
     		page.setResult(studentInfos);
     	}else{
     		page.setResult(new ArrayList<StudentInfo>());
     	}
		return page;
	}

    @Override
    public Integer isExistIdCard(Map<String, Object> map) {
        return studentInfoDao.isExistIdCard(map);
    }

	@Override
	public Page selectPersonUncheck(Page page, Map<String, Object> map) {
		List<StudentInfo> studentInfos =studentInfoDao.selectPersonUncheck(page,map);
		if(studentInfos!=null && studentInfos.size()>0){
			page.setResult(studentInfos);
		}else{
			page.setResult(new ArrayList<StudentInfo>());
		}
		return page;
	}

	@Override
	public Page selectPersonChecked(Page page, Map<String, Object> map) {
		List<StudentInfo> studentInfos =studentInfoDao.selectPersonChecked(page,map);
		if(studentInfos!=null && studentInfos.size()>0){
			page.setResult(studentInfos);
		}else{
			page.setResult(new ArrayList<StudentInfo>());
		}
		return page;
	}

	@Override
	public Page selectSchoolCheckedSituation(Page page, Map<String, Object> map) {
		List<StudentInfo> studentInfos =studentInfoDao.selectSchoolCheckedSituation(page,map);
		if(studentInfos!=null && studentInfos.size()>0){
			page.setResult(studentInfos);
		}else{
			page.setResult(new ArrayList<StudentInfo>());
		}
		return page;
	}

	@Override
	public Page selectSchoolInterviewSituation(Page page, Map<String, Object> map) {
		List<StudentInfo> studentInfos =studentInfoDao.selectSchoolInterviewSituation(page,map);
		if(studentInfos!=null && studentInfos.size()>0){
			page.setResult(studentInfos);
		}else{
			page.setResult(new ArrayList<StudentInfo>());
		}
		return page;
	}

	@Override
	public void personCheck(String chks, String projectId, String reasonId, String isPass, User user, JSONObject jsonMap) throws Exception {
		if(StringUtils.isBlank(chks)){
			jsonMap.put("msg", "请选中记录后再做操作，请确认！");
			throw new Exception("请选中记录后再做操作，请确认");
		}
		Theme theme=themeDao.selectThemeById(projectId);
    	if(theme !=null ){
    		if(theme.getStep()>1){
    			jsonMap.put("msg", "教育局面试名单发布后不可进行审核操作！");
    			throw new Exception("教育局面试名单发布后不可进行审核操作！");
    		}
    	}
		String [] studentIds=chks.split(",");
		Map<String ,Object> queryParameter=new HashMap<String ,Object>();
		queryParameter.put("studentIds", studentIds);
		queryParameter.put("employItemsId", projectId);
		List<StudentApplyInfo> studentApplyInfos =studentApplyInfoDao.selectAllByMap(queryParameter);
		if(studentApplyInfos !=null && !studentApplyInfos.isEmpty()){
			for(StudentApplyInfo studentApplyInfo : studentApplyInfos){
				if(!"2".equals(studentApplyInfo.getApplyStatus())){
					jsonMap.put("msg", "只有未审核的数据才能审核，请确认！");
					throw new Exception("只有未审核的数据才能审核，请确认！");
				}
				if("1".equals(isPass)){//审核通过
					studentApplyInfo.setApplyStatus("3");
				}else{//审核不通过
					studentApplyInfo.setApplyStatus("5");
					studentApplyInfo.setApplyAuditCode(reasonId);
				}
				studentApplyInfo.setAudittype("1");
				studentApplyInfo.setModifyUser(user.getId());
			}
			studentApplyInfoDao.updateList(studentApplyInfos);
		}
	}



    @Override
    public Integer isApplyPosition(Map<String, Object> map) {
        return studentInfoDao.isApplyPosition(map);
    }

    @Override
    public Integer isApplySchool(Map<String, Object> map) {
        return studentInfoDao.isApplySchool(map);
    }

	@Override
	public void checkCancel(String chk, String projectId, User user, String status,String reason,JSONObject jsonMap) throws Exception {
		if(StringUtils.isBlank(chk)){
			jsonMap.put("msg", "请选中记录后再做操作，请确认！");
			throw new Exception("请选中记录后再做操作，请确认");
		}
		if(StringUtils.isBlank(status)){
			jsonMap.put("msg", "参数status为空，请稍后再试！");
			throw new Exception("参数status为空，请稍后再试！");
		}
		Theme theme=themeDao.selectThemeById(projectId);
    	if(theme !=null ){
    		if(theme.getStep()>1){
    			jsonMap.put("msg", "教育局面试名单发布后不可进行审核操作！");
    			throw new Exception("教育局面试名单发布后不可进行审核操作！");
    		}
    	}
		Map<String ,Object> queryParameter=new HashMap<String ,Object>();
		String [] studentIds=chk.split(",");
		queryParameter.put("studentIds", studentIds);
		queryParameter.put("employItemsId", projectId);
		queryParameter.put("schoolId", user.getSchoolId());
		List<StudentApplyInfo> studentApplyInfos =studentApplyInfoDao.selectAllByMap(queryParameter);
		if(studentApplyInfos !=null && !studentApplyInfos.isEmpty()){
			for(StudentApplyInfo studentApplyInfo : studentApplyInfos){
				if("5".equals(status)){//退回申请操作
					if(!"3".equals(studentApplyInfo.getApplyStatus())){
						jsonMap.put("msg", "学校审核过后的数据不能退回申请，请确认！");
						throw new Exception("学校审核过后的数据不能退回申请，请确认！");
					}
				}
				if("4".equals(status)){//照片退回操作
					if(!"3".equals(studentApplyInfo.getApplyStatus())){
						jsonMap.put("msg", "学校审核过后的数据不能照片退回，请确认！");
						throw new Exception("学校审核过后的数据不能照片退回，请确认！");
					}
				}
				studentApplyInfo.setApplyAuditCode(reason);
				studentApplyInfo.setAudittype("1");
				studentApplyInfo.setApplyStatus(status);
				studentApplyInfo.setModifyUser(user.getId());
			}
			studentApplyInfoDao.updateList(studentApplyInfos);
		}
		queryParameter.clear();
		queryParameter.put("ids", studentIds);
		queryParameter.put("employItemsId", projectId);
		queryParameter.put("applyDepId", user.getSchoolId());
		List<StudentInfo> studentInfos=studentInfoDao.selectAllByMap(queryParameter);
		if(studentInfos !=null && !studentInfos.isEmpty()){
			for(StudentInfo studentInfo : studentInfos){
				studentInfo.setTicketnum(null);
				studentInfo.setModifyUser(user.getId());
			}
			studentInfoDao.updateList(studentInfos);
		}
	}

	@Override
	public Page selectPersonData(Page page, Map<String, Object> condition) {
		List<StudentInfo> studentInfos =studentInfoDao.selectPersonData(page,condition);
		if(studentInfos!=null && studentInfos.size()>0){
			page.setResult(studentInfos);
		}else{
			page.setResult(new ArrayList<StudentInfo>());
		}
		return page;
	}
	
	@Override
	public void resetPasswd(String id,String newPasswd, String md5Encrypt) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String,String>() ;
		map.put("id", id) ;
		map.put("newPasswd", newPasswd) ;
		map.put("encrypt", md5Encrypt) ;
		studentInfoDao.resetPasswd(map) ;
	}
	
	@Override
	public Page selectStudentInfo(Page page, Map<String, Object> map) {
		List<StudentInfo> studentInfos =studentInfoDao.selectStudentInfo(page,map);
		if(studentInfos!=null && studentInfos.size()>0){
			page.setResult(studentInfos);
		}else{
			page.setResult(new ArrayList<StudentInfo>());
		}
		return page;
	}
}
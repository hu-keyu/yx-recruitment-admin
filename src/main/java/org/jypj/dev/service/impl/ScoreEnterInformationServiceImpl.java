package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import org.jypj.dev.entity.Grade;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreEnterInformation;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.GradeDao;
import org.jypj.dev.dao.ScoreEnterInformationDao;
import org.jypj.dev.dao.ScoreEnterWritienDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterInformationService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

/**
* ScoreEnterInformation业务接口实现层
* 
* @author
*
*/

@Service("scoreEnterInformationService")
public class ScoreEnterInformationServiceImpl implements ScoreEnterInformationService {
	
    @Resource 
    private ScoreEnterInformationDao scoreEnterInformationDao;
    @Resource
	private StudentApplyInfoDao studentApplyInfoDao;
    @Resource
   	private GradeDao gradeDao;
    @Resource
   	private ScoreEnterWritienDao scoreEnterWritienDao;
    @Resource
  	private ThemeService themeService;
    @Resource
  	private NoticeService noticeService ;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterInformation
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterInformationByField(ScoreEnterInformation scoreEnterInformation){
	
		return scoreEnterInformationDao.saveScoreEnterInformationByField(scoreEnterInformation);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterInformation 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterInformation(ScoreEnterInformation scoreEnterInformation){
	
		return scoreEnterInformationDao.saveScoreEnterInformation(scoreEnterInformation);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterInformationById(String id){
    
    	return scoreEnterInformationDao.deleteScoreEnterInformationById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterInformationByObject(ScoreEnterInformation scoreEnterInformation){
    
    	return scoreEnterInformationDao.deleteScoreEnterInformationByObject(scoreEnterInformation);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterInformation 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterInformationByField(ScoreEnterInformation scoreEnterInformation){
    
    	return scoreEnterInformationDao.updateScoreEnterInformationByField(scoreEnterInformation);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterInformation 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterInformation(ScoreEnterInformation scoreEnterInformation){
    
    	return scoreEnterInformationDao.updateScoreEnterInformation(scoreEnterInformation);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterInformation 
	 */	
    public ScoreEnterInformation selectScoreEnterInformationById(String id){
    
    	return scoreEnterInformationDao.selectScoreEnterInformationById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterInformation>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreEnterInformation> scoreEnterInformations =scoreEnterInformationDao.selectOnePageByMap(page,map);
	     	if(scoreEnterInformations!=null&&scoreEnterInformations.size()>0){
	     		page.setResult(scoreEnterInformations);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterInformation>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterInformation  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterInformation(Page page,ScoreEnterInformation scoreEnterInformation){
 		 List<ScoreEnterInformation> scoreEnterInformations =scoreEnterInformationDao.selectOnePageByScoreEnterInformation(page,scoreEnterInformation);
	     	if(scoreEnterInformations!=null&&scoreEnterInformations.size()>0){
	     		page.setResult(scoreEnterInformations);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterInformation>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterInformation>
	 */	
     public List<ScoreEnterInformation> selectAllByMap(Map<String,Object> map){
     	return scoreEnterInformationDao.selectAllByMap(map);
     }
     
     
     @Override
 	 public String addBatchScore(Map<String, Object> condition,Page page,User user, JSONObject jsonMap) throws Exception {
    	condition =page.getCondition();
    	condition.put("status", "6");
    	List<ScoreEntersOutVo> scoreList=studentApplyInfoDao.selectAllpublish(condition);
		String flag="";
		List<ScoreEnterInformation> enterList =new ArrayList<ScoreEnterInformation>();
		//List<ScoreEnterWritien> enterList =new ArrayList<ScoreEnterWritien>();
		List<StudentApplyInfo> applyList =new ArrayList<StudentApplyInfo>();
		List<Grade> gradeList =new ArrayList<Grade>();//*****
		if(scoreList.size()>0){
			for (ScoreEntersOutVo scoreAll : scoreList) {
				if(StringUtils.isBlank(scoreAll.getStudentId())){
					jsonMap.put("msg", "获取学生ID为空，操作失败，请确认！");
					throw new Exception("获取学生ID为空，操作失败，请确认！");
				}else if(StringUtils.isBlank(scoreAll.getProjectId())){
					jsonMap.put("msg", "获取项目ID为空，操作失败，请确认！");
					throw new Exception("获取项目ID为空，操作失败，请确认！");
				}else if(StringUtils.isBlank(scoreAll.getPostId())){
					jsonMap.put("msg", "获取岗位ID为空，操作失败，请确认！");
					throw new Exception("获取岗位ID为空，操作失败，请确认！");
				}else if(StringUtils.isBlank(scoreAll.getApplyId())){
					jsonMap.put("msg", "获取报考信息ID为空，操作失败，请确认！");
					throw new Exception("获取报考信息ID为空，操作失败，请确认！");
				}else if(StringUtils.isBlank(scoreAll.getSchoolId())){
					jsonMap.put("msg", "获取学校ID为空，操作失败，请确认！");
					throw new Exception("获取学校ID为空，操作失败，请确认！");
				}else if(StringUtils.isBlank(scoreAll.getIdCard())){
					jsonMap.put("msg", "获取用户身份证号为空，操作失败，请确认！");
					throw new Exception("获取用户身份证号为空，操作失败，请确认！");
				}
				ScoreEnterInformation scor=new ScoreEnterInformation();//面试入围名单表
				StudentApplyInfo studentApplyInfo=new StudentApplyInfo();//学生报考信息表
				/*ScoreEnterWritien scoreEnterWritien=new ScoreEnterWritien();
				scoreEnterWritien.setSchoolId(scoreAll.getSchoolId());
				scoreEnterWritien.setItemsId(scoreAll.getProjectId());
				scoreEnterWritien.setPostId(scoreAll.getPostId());
				scoreEnterWritien.setStudentId(scoreAll.getStudentId());
				scoreEnterWritien.setCreateUser("进入笔试名单");
				scoreEnterWritien.setListPublishStatus("1");//未发布
				scoreEnterWritien.setScorePublishStatus("2");//已发布
				
				enterList.add(scoreEnterWritien);*/
				Grade grade=new Grade();
				scor.setStudentId(scoreAll.getStudentId());
				scor.setItemsId(scoreAll.getProjectId());
				scor.setListPublishStatus("2");//已发布
				scor.setPostId(scoreAll.getPostId());
				scor.setCreateUser(user.getId());
				scor.setIsEnter("1");//1、入围2、未入围
				scor.setType((String)condition.get("testType"));
				scor.setScorePublishStatus(null);
				scor.setSubjectType(null);
				scor.setSchoolId(scoreAll.getSchoolId());
				enterList.add(scor);
				studentApplyInfo.setId(scoreAll.getApplyId());
				studentApplyInfo.setApplyStatus("7");//进入面试环节
				studentApplyInfo.setModifyUser(user.getId());
				applyList.add(studentApplyInfo);
				grade.setStudentId(scoreAll.getStudentId());//*****
				grade.setGrade("0");
				grade.setListPublishStatus("1");//0、未发布1、已发布
				grade.setProjectId(scoreAll.getProjectId());
				grade.setCreatetime(new Date());
				grade.setPositionId(scoreAll.getPostId());
				grade.setCreateuser(user.getId());
				grade.setSchoolId(scoreAll.getSchoolId());
				grade.setScorePublishStatus("0");//0、未发布1、发布
				grade.setType("1");
				grade.setIsEmploy("0");
				grade.setModifyuser(user.getId());
				grade.setRemark("0");
				gradeList.add(grade);
				
			}
			// for 循环入围名单
			// 批量存储入围名单
			int pointsDataLimit = 1000;// 限制条数
			Integer size = enterList.size();
			if (pointsDataLimit < size) {
				int part = size / pointsDataLimit;// 分批数
				for (int i = 0; i < part; i++) {
					List<ScoreEnterInformation> listPage = enterList.subList(0, pointsDataLimit);
					//List<ScoreEnterWritien> listPageWritien = enterList.subList(0, pointsDataLimit);
					List<StudentApplyInfo> listPageApply = applyList.subList(0, pointsDataLimit);
					List<Grade> listPageGrade = gradeList.subList(0, pointsDataLimit);//*****
					// 存储
					scoreEnterInformationDao.addBatchScore(listPage);
					//scoreEnterWritienDao.addBatchWritien(listPageWritien);
					studentApplyInfoDao.updateList(listPageApply);
					gradeDao.saveGradesList(listPageGrade);//*****
					enterList.subList(0, pointsDataLimit).clear();
					applyList.subList(0, pointsDataLimit).clear();
					gradeList.subList(0, pointsDataLimit).clear();//*****
				}
				// 存储
				if(enterList.size()>0){
					scoreEnterInformationDao.addBatchScore(enterList);
					//scoreEnterWritienDao.addBatchWritien(enterList);
					studentApplyInfoDao.updateList(applyList);;
					gradeDao.saveGradesList(gradeList);//*****
				}
			} else {
				scoreEnterInformationDao.addBatchScore(enterList);
				//scoreEnterWritienDao.addBatchWritien(enterList);
				studentApplyInfoDao.updateList(applyList);
				gradeDao.saveGradesList(gradeList);//*****
			}
			flag="success";
			//面试名单发布
			themeService.updateStep((String)condition.get("projectId"), 2);//更新主题表的流程
			//公告表修改
			Notice notice=new Notice();
			notice.setInterviewListPublish(1);//面试名单发布
			notice.setThemeId((String)condition.get("projectId"));
			noticeService.updateNoticeByFieldAndTheme(notice);
		}else{
			flag="false";
		}
    	return flag;
 	 }
      

 	@Override
 	public Page selectEnterPageByMap(Page page, Map<String, Object> map) {
 		List<ScoreEnterInformation> scoreEnterInformations =scoreEnterInformationDao.selectEnterPageByMap(page, map);
     	if(scoreEnterInformations!=null&&scoreEnterInformations.size()>0){
     		page.setResult(scoreEnterInformations);
     	}else{
     		page.setResult(new ArrayList<ScoreEnterInformation>());
     	}
     	return page;
 	}
 	
 	@Override
	public Page selectGradePageByMap(Page page, Map<String, Object> map) {
 		List<ScoreEnterInformation> scoreEnterInformations =scoreEnterInformationDao.selectGradePageByMap(page, map);
     	if(scoreEnterInformations!=null&&scoreEnterInformations.size()>0){
     		page.setResult(scoreEnterInformations);
     	}else{
     		page.setResult(new ArrayList<ScoreEnterInformation>());
     	}
     	return page;
	}
 	
 	@Override
	public Page searchsGradePageByMap(Page page, Map<String, Object> map) {
 		List<ScoreEnterInformation> scoreEnterInformations =scoreEnterInformationDao.searchsGradePageByMap(page, map);
     	if(scoreEnterInformations!=null&&scoreEnterInformations.size()>0){
     		page.setResult(scoreEnterInformations);
     	}else{
     		page.setResult(new ArrayList<ScoreEnterInformation>());
     	}
     	return page;
	}
     
 	@Override
	public List<ScoreEntersOutVo> selectExportByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreEnterInformationDao.selectExportByMap(map);
	}
 	
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterInformation>
	 */	
     public List<ScoreEnterInformation> selectAllByScoreEnterInformation(ScoreEnterInformation scoreEnterInformation){
     
    	 return scoreEnterInformationDao.selectAllByScoreEnterInformation(scoreEnterInformation);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterInformation
	 */	
     public ScoreEnterInformation selectObjectByMap(Map<String,Object> map){
     
    	 return scoreEnterInformationDao.selectObjectByMap(map);
     }
     

 	 @Override
 	 public List<ScoreEntersOutVo> listExportByMap(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreEnterInformationDao.listExportByMap(map);
 	 }
 	 
 	@Override
	public List<ScoreEntersOutVo> selectListExportByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreEnterInformationDao.selectListExportByMap(map);
	}
 	
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterInformation
	 */	
     public ScoreEnterInformation selectObjectByScoreEnterInformation(ScoreEnterInformation scoreEnterInformation){
     
     	return scoreEnterInformationDao.selectObjectByScoreEnterInformation(scoreEnterInformation);
     }

	@Override
	public Page selectPageByMap(Page page, Map<String, Object> map) {
		List<ScoreEntersOutVo> scoreEntersOutVos =scoreEnterInformationDao.selectPageByMap(page,map);
     	if(scoreEntersOutVos!=null&&scoreEntersOutVos.size()>0){
     		page.setResult(scoreEntersOutVos);
     	}else{
     		page.setResult(new ArrayList<ScoreEnterInformation>());
     	}
     	return page;
	}
	
	@Override
	public List<ScoreEntersOutVo> selectByMap(Map<String, Object> map) {
		return scoreEnterInformationDao.selectPageByMap(map);
	}

}
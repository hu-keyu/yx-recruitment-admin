package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreEnterRecord;
import org.jypj.dev.entity.ScoreEnterStudy;
import org.jypj.dev.entity.ScoreGradeStudy;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.ScoreEnterRecordDao;
import org.jypj.dev.dao.ScoreEnterStudyDao;
import org.jypj.dev.dao.ScoreGradeStudyDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterStudyService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

/**
* ScoreEnterStudy业务接口实现层
* 考察入围表
* @author
*
*/

@Service("scoreEnterStudyService")
public class ScoreEnterStudyServiceImpl implements ScoreEnterStudyService {
	
    @Resource 
    private ScoreEnterStudyDao scoreEnterStudyDao;
    @Resource
  	private ScoreGradeStudyDao scoreGradeStudyDao ;
    @Resource
  	private StudentApplyInfoDao studentApplyInfoDao;
    @Resource
  	private ThemeService themeService;
    @Resource
  	private NoticeService noticeService ;
    @Resource
  	private ScoreEnterRecordDao scoreEnterRecordDao;
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterStudy
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterStudyByField(ScoreEnterStudy scoreEnterStudy){
	
		return scoreEnterStudyDao.saveScoreEnterStudyByField(scoreEnterStudy);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterStudy 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterStudy(ScoreEnterStudy scoreEnterStudy){
	
		return scoreEnterStudyDao.saveScoreEnterStudy(scoreEnterStudy);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterStudyById(String id){
    
    	return scoreEnterStudyDao.deleteScoreEnterStudyById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterStudyByObject(ScoreEnterStudy scoreEnterStudy){
    
    	return scoreEnterStudyDao.deleteScoreEnterStudyByObject(scoreEnterStudy);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterStudyByField(ScoreEnterStudy scoreEnterStudy){
    
    	return scoreEnterStudyDao.updateScoreEnterStudyByField(scoreEnterStudy);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterStudy(ScoreEnterStudy scoreEnterStudy){
    
    	return scoreEnterStudyDao.updateScoreEnterStudy(scoreEnterStudy);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterStudy 
	 */	
    public ScoreEnterStudy selectScoreEnterStudyById(String id){
    
    	return scoreEnterStudyDao.selectScoreEnterStudyById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreEnterStudy> scoreEnterStudys =scoreEnterStudyDao.selectOnePageByMap(page,map);
	     	if(scoreEnterStudys!=null&&scoreEnterStudys.size()>0){
	     		page.setResult(scoreEnterStudys);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterStudy>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterStudy  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterStudy(Page page,ScoreEnterStudy scoreEnterStudy){
 		 List<ScoreEnterStudy> scoreEnterStudys =scoreEnterStudyDao.selectOnePageByScoreEnterStudy(page,scoreEnterStudy);
	     	if(scoreEnterStudys!=null&&scoreEnterStudys.size()>0){
	     		page.setResult(scoreEnterStudys);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterStudy>());
	     	}
	     	return page;
     }
    
     /**
 	 * 查询进入考察的入围名单
 	 * @param page 分页对象
 	 * @param map  查询条件  
 	 * @return  List<ScoreEnterPhysical>
 	 */	
 	@Override
 	public Page selectStudyEnterPageByMap(Page page, Map<String, Object> map) {
 		List<ScoreEnterStudy> scoreEnterStudys =scoreEnterStudyDao.selectStudyEnterPageByMap(page, map);
     	if(scoreEnterStudys!=null&&scoreEnterStudys.size()>0){
     		page.setResult(scoreEnterStudys);
     	}else{
     		page.setResult(new ArrayList<ScoreEnterStudy>());
     	}
     	return page;
 	}
     
 	@Override
 	public void enterStudylist(String flag, String chks, String projectId, String positionid, User user,
 			JSONObject jsonMap) throws Exception {
    	if(StringUtils.isBlank(chks)){
 			jsonMap.put("msg", "请选中记录后再做操作，请确认！");
     		throw new Exception("请选中记录后再做操作，请确认");
 		}
    	if(StringUtils.isBlank(flag)){
			jsonMap.put("msg", "操作标识为空，请确认！");
    		throw new Exception("操作标识为空，请确认");
		}
    	if(StringUtils.isBlank(projectId)){
			jsonMap.put("msg", "招聘项目ID为空，请确认！");
    		throw new Exception("招聘项目ID为空，请确认");
		}
    	if(StringUtils.isBlank(positionid)){
			jsonMap.put("msg", "招聘岗位ID为空，请确认！");
    		throw new Exception("招聘岗位ID为空，请确认");
		}
    	if(user == null){
			jsonMap.put("msg", "获取用户数据为空，操作失败，请确认！");
			throw new Exception("获取用户数据为空，操作失败，请确认！");
		}
     	String [] studentIds=chks.split(",");
 		Map<String ,Object> queryParameter=new HashMap<String ,Object>();
 		queryParameter.put("studentIds", studentIds);
 		queryParameter.put("projectId", projectId);
 		queryParameter.put("positionid", positionid);
 		List<ScoreEnterStudy> scoreEnterStudyList= scoreEnterStudyDao.selectAllByMap(queryParameter);
 		List<ScoreEnterRecord> scoreEnterRecordList=new ArrayList<ScoreEnterRecord>();
 		if(scoreEnterStudyList !=null && !scoreEnterStudyList.isEmpty()){
 			if(StringUtils.isNotEmpty(flag)&&flag.equals("1")){//调整到入围名单中
 				for(ScoreEnterStudy scoreEnterStudy : scoreEnterStudyList){
 					scoreEnterStudy.setIsEnter("1");//1、入围 2、未入围
 					scoreEnterStudy.setModifyUser(user.getId());
 					//往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterStudy.getItemsId());
					scoreEnterRecord.setIsEnter("2");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterStudy.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterStudy.getStudentId());
					scoreEnterRecord.setType("5");//考察
					scoreEnterRecord.setUpdateReason("将未入围的考生调整到入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
 				}
 				scoreEnterStudyDao.updateStudyEnterList(scoreEnterStudyList);
 				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
 			}else if(StringUtils.isNotEmpty(flag)&&flag.equals("2")){//调整到未入围的名单中
 				for(ScoreEnterStudy scoreEnterStudy : scoreEnterStudyList){
 					scoreEnterStudy.setIsEnter("2");//1、入围 2、未入围
 					scoreEnterStudy.setModifyUser(user.getId());
 					 //往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterStudy.getItemsId());
					scoreEnterRecord.setIsEnter("1");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterStudy.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterStudy.getStudentId());
					scoreEnterRecord.setType("5");//考察
					scoreEnterRecord.setUpdateReason("将入围的考生调整到未入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
 				}
 				scoreEnterStudyDao.updateStudyEnterList(scoreEnterStudyList);
 				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
 			}
 		}else{
			jsonMap.put("msg", "入围名单为空，请确认！");
    		throw new Exception("入围名单为空，请确认");
		}	
 		
 	}
 	
 	/**
     * 批量插入进入考察名单
     * @param list
    */ 
	@Override
	public String addBatchStudy(Map<String, Object> condition, Page page, User user) {
		condition =page.getCondition();
 		condition.put("isEnter", "1");//1、入围
 		List<ScoreEntersOutVo> scoreList =scoreEnterStudyDao.selectAllStudy(condition);
 		String flag="";
 		List<ScoreGradeStudy> gradeList =new ArrayList<ScoreGradeStudy>();
 		List<StudentApplyInfo> applyList =new ArrayList<StudentApplyInfo>();
 		if(scoreList.size()>0){
 			for (ScoreEntersOutVo scoreAll : scoreList) {
 				ScoreGradeStudy scoreGradeStudy=new ScoreGradeStudy();//考察成绩表
 				scoreGradeStudy.setStudentId(scoreAll.getStudentId());
 				scoreGradeStudy.setResult("2");//默認不通過
 				scoreGradeStudy.setListPublishStatus("1");//0、未发布1、已发布
 				scoreGradeStudy.setProjectId(scoreAll.getProjectId());
 				scoreGradeStudy.setCreatetime(new Date());
 				scoreGradeStudy.setPositionId(scoreAll.getPostId());
 				scoreGradeStudy.setCreateuser(user.getId());
 				scoreGradeStudy.setSchoolId(scoreAll.getSchoolId());
 				scoreGradeStudy.setScorePublishStatus("0");//0、未发布1、发布
 				scoreGradeStudy.setType((String)condition.get("testType"));
 				scoreGradeStudy.setModifyuser(user.getId());
 				gradeList.add(scoreGradeStudy);
 				StudentApplyInfo studentApplyInfo=new StudentApplyInfo();//学生报考信息表
 				studentApplyInfo.setId(scoreAll.getApplyId());
 				studentApplyInfo.setApplyStatus("11");//11、发布考察名单，并进入考察阶段；
 				studentApplyInfo.setModifyUser(user.getId());
 				applyList.add(studentApplyInfo);
 			}
 		
 			// for 循环入围名单
 			// 批量存储入围名单
 			int pointsDataLimit = 1000;// 限制条数
 			Integer size = gradeList.size();
 			if (pointsDataLimit < size) {
 				int part = size / pointsDataLimit;// 分批数
 				for (int i = 0; i < part; i++) {
 					List<ScoreGradeStudy> listPageGrade = gradeList.subList(0, pointsDataLimit);
 					List<StudentApplyInfo> listPageApply = applyList.subList(0, pointsDataLimit);
 					// 存储
 					scoreGradeStudyDao.saveStudyGradesList(listPageGrade);
 					studentApplyInfoDao.updateList(listPageApply);
 					applyList.subList(0, pointsDataLimit).clear();
 					gradeList.subList(0, pointsDataLimit).clear();
 				}
 				if(gradeList.size()>0){
	 				// 存储
	 				studentApplyInfoDao.updateList(applyList);;
	 				scoreGradeStudyDao.saveStudyGradesList(gradeList);
 				}	
 			} else {
 				studentApplyInfoDao.updateList(applyList);
 				scoreGradeStudyDao.saveStudyGradesList(gradeList);
 			}
 			flag="success";
 			//考察名单发布
 			themeService.updateStep((String)condition.get("projectId"), 10);//更新主题表的流程
 			
 			//公告表修改
			Notice noticeTwo=new Notice();
			noticeTwo.setLookListPublish(1);//考察名单发布
			noticeTwo.setThemeId((String)condition.get("projectId"));
			noticeService.updateNoticeByFieldAndTheme(noticeTwo);
 		}else{
 			flag="false";
 		}
 		return flag;
	}
 	
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectAllByMap(Map<String,Object> map){
     	return scoreEnterStudyDao.selectAllByMap(map);
     }
     

 	@Override
 	public List<ScoreEntersOutVo> selectExportByMap(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreEnterStudyDao.selectExportByMap(map);
 	}

      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectAllByScoreEnterStudy(ScoreEnterStudy scoreEnterStudy){
     
    	 return scoreEnterStudyDao.selectAllByScoreEnterStudy(scoreEnterStudy);
     }
		

 	@Override
 	public List<ScoreEntersOutVo> selectListExportByMap(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreEnterStudyDao.selectListExportByMap(map);
 	}
     
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterStudy
	 */	
     public ScoreEnterStudy selectObjectByMap(Map<String,Object> map){
     
    	 return scoreEnterStudyDao.selectObjectByMap(map);
     }
     
     @Override
 	 public List<ScoreEntersOutVo> selectAllStudy(Map<String, Object> map) {
 		return scoreEnterStudyDao.selectAllStudy(map);
 	 }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterStudy
	 */	
     public ScoreEnterStudy selectObjectByScoreEnterStudy(ScoreEnterStudy scoreEnterStudy){
     
     	return scoreEnterStudyDao.selectObjectByScoreEnterStudy(scoreEnterStudy);
     }

}
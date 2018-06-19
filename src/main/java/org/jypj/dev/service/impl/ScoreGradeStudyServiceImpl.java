package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import org.jypj.dev.entity.GradeAdjustLog;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreEnterNotice;
import org.jypj.dev.entity.ScoreGradeStudy;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.GradeAdjustLogDao;
import org.jypj.dev.dao.ScoreEnterNoticeDao;
import org.jypj.dev.dao.ScoreGradeStudyDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreGradeStudyService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;

/**
* ScoreGradeStudy业务接口实现层
* 考察成绩表
* @author
*
*/

@Service("scoreGradeStudyService")
public class ScoreGradeStudyServiceImpl implements ScoreGradeStudyService {
	
    @Resource 
    private ScoreGradeStudyDao scoreGradeStudyDao;
    @Resource 
    private ScoreEnterNoticeDao scoreEnterNoticeDao ;
    @Resource 
    private ThemeService themeService;
    @Resource
  	private NoticeService noticeService ;
    @Resource
    private GradeAdjustLogDao gradeAdjustLogDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeStudy
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeStudyByField(ScoreGradeStudy scoreGradeStudy){
	
		return scoreGradeStudyDao.saveScoreGradeStudyByField(scoreGradeStudy);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeStudy 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeStudy(ScoreGradeStudy scoreGradeStudy){
	
		return scoreGradeStudyDao.saveScoreGradeStudy(scoreGradeStudy);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeStudyById(String id){
    
    	return scoreGradeStudyDao.deleteScoreGradeStudyById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreGradeStudyByObject(ScoreGradeStudy scoreGradeStudy){
    
    	return scoreGradeStudyDao.deleteScoreGradeStudyByObject(scoreGradeStudy);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeStudyByField(ScoreGradeStudy scoreGradeStudy){
    
    	return scoreGradeStudyDao.updateScoreGradeStudyByField(scoreGradeStudy);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeStudy(ScoreGradeStudy scoreGradeStudy){
    
    	return scoreGradeStudyDao.updateScoreGradeStudy(scoreGradeStudy);
    }
    
    @Override
	public void updateStudyList(List<ScoreGradeStudy> gradeStudysList,User user, JSONObject jsonMap)  throws Exception {
    	if (gradeStudysList.size()==0 || gradeStudysList == null) {
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "要修改的数据为空，请稍后重试！");
		} 
    	List<String> idList=new ArrayList<String>();//获取ID的集合
    	for (ScoreGradeStudy studyList : gradeStudysList) {
			if(StringUtils.isBlank(studyList.getId())){
				jsonMap.put("msg", "保存的数据ID不能为空，请稍后再试！");
				throw new Exception("保存的数据ID不能为空，请稍后再试！");
			}
			if(StringUtils.isBlank(studyList.getResult())){
				jsonMap.put("msg", "考察成绩不能为空，请确认！");
				throw new Exception("考察成绩不能为空，请确认！");
			}
			idList.add(studyList.getId());
		}
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", idList);
		List<ScoreGradeStudy> oldScore=scoreGradeStudyDao.selectStudyByMap(map);//之前的分数
		List<ScoreGradeStudy> compareScore=this.getDifferScore(oldScore, gradeStudysList);//比较后的
		if(compareScore !=null && !compareScore.isEmpty() ){
			for(ScoreGradeStudy study : compareScore){
				study.setModifyuser(user.getId());
			}
			this.saveScoreAdjustLog(compareScore, user);
			scoreGradeStudyDao.updateStudyList(compareScore);
		}	
    }
    
    /**
 	 * 批量保存调整成绩日志表
 	 * @param differGrade
 	 * @param user
 	 */
 	private void saveScoreAdjustLog(List<ScoreGradeStudy> differScore,User user){
 		List<GradeAdjustLog> gradeAdjustLogs=new ArrayList<GradeAdjustLog>();
 		GradeAdjustLog gradeAdjustLog=null;
 		for(ScoreGradeStudy score : differScore){
 			ScoreGradeStudy scoreGradeStudy=scoreGradeStudyDao.selectScoreGradeStudyById(score.getId());
 			gradeAdjustLog=new GradeAdjustLog();
 			gradeAdjustLog.setProjectId(scoreGradeStudy.getProjectId());
 			gradeAdjustLog.setPositionId(scoreGradeStudy.getPositionId());
 			gradeAdjustLog.setStudentId(score.getStudentId());
 			gradeAdjustLog.setType("5");//考察
 			gradeAdjustLog.setCreateuser(user.getId());
 			gradeAdjustLog.setModifyuser(user.getId());
 			gradeAdjustLog.setInitGrade(scoreGradeStudy.getResult());//上一次的成绩
 			gradeAdjustLog.setGradeAfter(score.getResult());//新的成绩
 			gradeAdjustLog.setSchoolId(scoreGradeStudy.getSchoolId());
 			gradeAdjustLogs.add(gradeAdjustLog);
 		}
 		gradeAdjustLogDao.batchSaveList(gradeAdjustLogs);
 	}
 	
 	/**
 	 * 获取需要更改的成绩数据
 	 * @param gradePres
 	 * @param gradeNews
 	 * @return
 	 */
 	private List<ScoreGradeStudy> getDifferScore(List<ScoreGradeStudy> scoreOlds,List<ScoreGradeStudy> scoreNews){
 		List<ScoreGradeStudy> data=new ArrayList<ScoreGradeStudy>();
 		for(ScoreGradeStudy scoreOld : scoreOlds){
 			for(ScoreGradeStudy scoreNew : scoreNews){
 				if(scoreOld.getId().equals(scoreNew.getId())&& (!scoreNew.getResult().equals(scoreOld.getResult()))){
 					data.add(scoreNew);
 					break;
 				}
 			}
 		}
 		return data;
 	}
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeStudy 
	 */	
    public ScoreGradeStudy selectScoreGradeStudyById(String id){
    
    	return scoreGradeStudyDao.selectScoreGradeStudyById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreGradeStudy> scoreGradeStudys =scoreGradeStudyDao.selectOnePageByMap(page,map);
	     	if(scoreGradeStudys!=null&&scoreGradeStudys.size()>0){
	     		page.setResult(scoreGradeStudys);
	     	}else{
	     		page.setResult(new ArrayList<ScoreGradeStudy>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreGradeStudy  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreGradeStudy(Page page,ScoreGradeStudy scoreGradeStudy){
 		 List<ScoreGradeStudy> scoreGradeStudys =scoreGradeStudyDao.selectOnePageByScoreGradeStudy(page,scoreGradeStudy);
	     	if(scoreGradeStudys!=null&&scoreGradeStudys.size()>0){
	     		page.setResult(scoreGradeStudys);
	     	}else{
	     		page.setResult(new ArrayList<ScoreGradeStudy>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public List<ScoreGradeStudy> selectAllByMap(Map<String,Object> map){
     	return scoreGradeStudyDao.selectAllByMap(map);
     }
     
     @Override
 	 public void publishBatchStudy(Map<String, Object> condition, Page page, User user, JSONObject jsonMap)
 			throws Exception {
    	 if(StringUtils.isBlank((String)condition.get("projectId"))){
  			jsonMap.put("msg", "招聘项目ID为空，操作失败，请确认！");
  			throw new Exception("招聘项目ID为空，操作失败，请确认！");
  		 }
  		 if(user == null){
  			jsonMap.put("msg", "获取用户数据为空，操作失败，请确认！");
  			throw new Exception("获取用户数据为空，操作失败，请确认！");
  		 }
    	 List<ScoreGradeStudy> enterList=scoreGradeStudyDao.selectAllByMap(condition);//查询所有名单
    	 if(enterList.size()>0){
				List<ScoreEnterNotice> noticeList = new ArrayList<ScoreEnterNotice>();
				for (ScoreGradeStudy physalAll : enterList) {
					ScoreEnterNotice scoreEnterNotice = new ScoreEnterNotice();//考察入围表
					scoreEnterNotice.setSchoolId(physalAll.getSchoolId());
					scoreEnterNotice.setStudentId(physalAll.getStudentId());
					if(StringUtils.isNotBlank(physalAll.getResult())&&physalAll.getResult().equals("1")){
						scoreEnterNotice.setIsEnter("1");//1、入围
					}else if(StringUtils.isNotBlank(physalAll.getResult())&&physalAll.getResult().equals("2")){
						scoreEnterNotice.setIsEnter("2");//2、未入围
					}else{
						jsonMap.put("msg", "考察成绩为空，请确认！");
			    		throw new Exception("考察成绩为空，请确认！");
					}
					scoreEnterNotice.setItemsId(physalAll.getProjectId());
					scoreEnterNotice.setPostId(physalAll.getPositionId());
					scoreEnterNotice.setListPublishStatus("1");//1、名单未发布
					scoreEnterNotice.setScorePublishStatus("2");//2、成绩已发布
					scoreEnterNotice.setType((String)condition.get("testType"));
					scoreEnterNotice.setCreateUser(user.getId());
					noticeList.add(scoreEnterNotice);
				}
				// for 循环入围名单
				// 批量存储入围名单
				int pointsDataLimit = 1000;// 限制条数
				Integer size = noticeList.size();
				if (pointsDataLimit < size) {
					int part = size / pointsDataLimit;// 分批数
					for (int i = 0; i < part; i++) {
						List<ScoreEnterNotice> listPageNotice = noticeList.subList(0, pointsDataLimit);
						// 存储
						scoreEnterNoticeDao.saveNoticesList(listPageNotice);
						noticeList.subList(0, pointsDataLimit).clear();
					}
					if(noticeList.size()>0){
						// 存储
						scoreEnterNoticeDao.saveNoticesList(noticeList);
					}	
				} else {
					scoreEnterNoticeDao.saveNoticesList(noticeList);
				}
				themeService.updateStep((String)condition.get("projectId"), 11);//考察成绩发布
				
				//公告表修改
				Notice noticeTwo=new Notice();
				noticeTwo.setLookScorePublish(1);//考察成绩发布
				noticeTwo.setThemeId((String)condition.get("projectId"));
				noticeService.updateNoticeByFieldAndTheme(noticeTwo);
			}else{
				jsonMap.put("msg", "考察名单为空，请确认！");
	    		throw new Exception("考察名单为空，请确认！");
			}
 	 }

     @Override
 	public String celpublishStudy(String projectId, Integer step) throws Exception {
    	 String flag="";
  		int delcount=0;
  		if(StringUtils.isNotBlank(projectId)){
  			 delcount=scoreEnterNoticeDao.deleteNoticeByProjectId(projectId);//删除发布成绩时创建的数据
  		}else{
  			flag="false";
  			return flag;
  		}
  		if(delcount>0){
  			ScoreEnterNotice scoreEnterNotice = new ScoreEnterNotice();
  			scoreEnterNotice.setItemsId(projectId);
  			List<ScoreEnterNotice> trials=scoreEnterNoticeDao.selectAllByScoreEnterNotice(scoreEnterNotice);//查询是否删除完成
  			if (trials.size()==0) {
  				themeService.updateStep(projectId, step);
  				//公告表修改
				Notice noticeTwo=new Notice();
				noticeTwo.setLookScorePublish(2);//取消考察成绩发布
				noticeTwo.setThemeId(projectId);
				noticeService.updateNoticeByFieldAndTheme(noticeTwo);
  				flag="success";
  			}else{
  				flag="false";
  			}
  		}else{
  			flag="false";
  		}
  		return flag;
 	}
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public List<ScoreGradeStudy> selectAllByScoreGradeStudy(ScoreGradeStudy scoreGradeStudy){
     
    	 return scoreGradeStudyDao.selectAllByScoreGradeStudy(scoreGradeStudy);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeStudy
	 */	
     public ScoreGradeStudy selectObjectByMap(Map<String,Object> map){
     
    	 return scoreGradeStudyDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeStudy
	 */	
     public ScoreGradeStudy selectObjectByScoreGradeStudy(ScoreGradeStudy scoreGradeStudy){
     
     	return scoreGradeStudyDao.selectObjectByScoreGradeStudy(scoreGradeStudy);
     }

    @Override
    public ScoreGradeStudy selectStudyResult(StudentApplyInfo sai) {
        //公示名单发布，查询考察结果
        ScoreGradeStudy sgs = new ScoreGradeStudy();
        sgs.setProjectId(sai.getEmployItemsId());
        sgs.setStudentId(sai.getStudentId());
        sgs.setPositionId(sai.getApplyJobId());
        sgs.setSchoolId(sai.getApplyDepId());
        return scoreGradeStudyDao.selectObjectByScoreGradeStudy(sgs);
    }

}
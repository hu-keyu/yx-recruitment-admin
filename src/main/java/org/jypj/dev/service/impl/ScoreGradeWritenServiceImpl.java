package org.jypj.dev.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import org.jypj.dev.entity.GradeAdjustLog;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreGradeWriten;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.GradeAdjustLogDao;
import org.jypj.dev.dao.NoticeDao;
import org.jypj.dev.dao.PostsetDao;
import org.jypj.dev.dao.ScoreEnterTrialDao;
import org.jypj.dev.dao.ScoreEnterWritienDao;
import org.jypj.dev.dao.ScoreGradeWritenDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreGradeWritenService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;


/**
* ScoreGradeWriten业务接口实现层
* 统一笔试成绩表
* @author
*
*/

@Service("scoreGradeWritenService")
public class ScoreGradeWritenServiceImpl implements ScoreGradeWritenService {
	
    @Resource 
    private ScoreGradeWritenDao scoreGradeWritenDao;
    @Resource 
    private ScoreEnterTrialDao scoreEnterTrialDao;
    @Resource 
    private NoticeDao noticeDao;
    @Resource 
    private PostsetDao postsetDao;
    @Resource 
    private ScoreEnterWritienDao scoreEnterWritienDao;
    @Resource 
    private ThemeService themeService;
    @Resource
  	private NoticeService noticeService ;
    @Resource
    private GradeAdjustLogDao gradeAdjustLogDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeWriten
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeWritenByField(ScoreGradeWriten scoreGradeWriten){
	
		return scoreGradeWritenDao.saveScoreGradeWritenByField(scoreGradeWriten);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeWriten 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeWriten(ScoreGradeWriten scoreGradeWriten){
	
		return scoreGradeWritenDao.saveScoreGradeWriten(scoreGradeWriten);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeWritenById(String id){
    
    	return scoreGradeWritenDao.deleteScoreGradeWritenById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreGradeWritenByObject(ScoreGradeWriten scoreGradeWriten){
    
    	return scoreGradeWritenDao.deleteScoreGradeWritenByObject(scoreGradeWriten);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeWriten 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeWritenByField(ScoreGradeWriten scoreGradeWriten){
    
    	return scoreGradeWritenDao.updateScoreGradeWritenByField(scoreGradeWriten);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeWriten 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeWriten(ScoreGradeWriten scoreGradeWriten){
    
    	return scoreGradeWritenDao.updateScoreGradeWriten(scoreGradeWriten);
    }
    
    //发布成绩并发布统一试讲入围名单
    @Override
	public void publishBatchWritien(Map<String, Object> condition, Page page, User user, JSONObject jsonMap) throws Exception {
    	if(StringUtils.isBlank((String)condition.get("projectId"))){
			jsonMap.put("msg", "招聘项目ID为空，操作失败，请确认！");
			throw new Exception("招聘项目ID为空，操作失败，请确认！");
		}
		if(user == null){
			jsonMap.put("msg", "获取用户数据为空，操作失败，请确认！");
			throw new Exception("获取用户数据为空，操作失败，请确认！");
		}
		Notice themeNotice=noticeDao.selectObjectByThemeId((String)condition.get("projectId"));//根据项目ID查询项目公告中的内容
		if(themeNotice!=null){
			/*List<ScoreGradeWriten> allList =scoreGradeWritenDao.selectGradeLine(condition);//查询所有成绩
			List<ScoreGradeWriten> gradePass=new ArrayList<ScoreGradeWriten>();
			List<ScoreGradeWriten> gradeNoPass=new ArrayList<ScoreGradeWriten>();
			if(allList != null && !allList.isEmpty()){
				Map<String ,List<ScoreGradeWriten>> datas=WritienGradeUtils.groupByPositionIdIsEmploy(allList,
						themeNotice.getEnterCondition(),themeNotice.getInterviewEnterPropo(),themeNotice.getInterviewEnterLine());
				gradePass=datas.get("pass");//通过
				gradeNoPass=datas.get("noPass");//未通过
				Log_.info("pass size()="+gradePass.size()+" "+ gradePass.toString());
				Log_.info("noPass ()"+gradeNoPass.size()+" "+gradeNoPass.toString());
			}else{
				jsonMap.put("msg", "学科和分数线异常，请确认！");
	    		throw new Exception("学科和分数线异常，请确认！");
			}
			
			Postset postset = new Postset();
			if(gradePass.size()>0){
				List<ScoreEnterTrial> trialList = new ArrayList<ScoreEnterTrial>();
				for (ScoreGradeWriten writenAll : gradePass) {
					if(StringUtils.isNotEmpty(writenAll.getPositionId())){
						postset=postsetDao.selectPostsetById(writenAll.getPositionId());
					}
					ScoreEnterTrial scoreEnterTrial = new ScoreEnterTrial();//统一试讲入围表
					scoreEnterTrial.setSchoolId(writenAll.getSchoolId());
					scoreEnterTrial.setStudentId(writenAll.getStudentId());
					scoreEnterTrial.setIsEnter("1");//1、入围
					scoreEnterTrial.setItemsId(writenAll.getProjectId());
					scoreEnterTrial.setPostId(writenAll.getPositionId());
					scoreEnterTrial.setListPublishStatus("1");//1、名单未发布
					scoreEnterTrial.setScorePublishStatus("2");//2、成绩已发布
					scoreEnterTrial.setType((String)condition.get("testType"));
					if(postset!=null){
						scoreEnterTrial.setSubjectType(postset.getIsArt().toString());//0、普通科1、艺术科
					}
					scoreEnterTrial.setCreateUser(user.getId());
					trialList.add(scoreEnterTrial);
				}
				// for 循环入围名单
				// 批量存储入围名单
				int pointsDataLimit = 1000;// 限制条数
				Integer size = trialList.size();
				if (pointsDataLimit < size) {
					int part = size / pointsDataLimit;// 分批数
					for (int i = 0; i < part; i++) {
						List<ScoreEnterTrial> listPageTrial = trialList.subList(0, pointsDataLimit);
						// 存储
						scoreEnterTrialDao.saveTrialsList(listPageTrial);
						trialList.subList(0, pointsDataLimit).clear();
					}
					if(trialList.size()>0){
						// 存储
						scoreEnterTrialDao.saveTrialsList(trialList);
					}	
				} else {
					scoreEnterTrialDao.saveTrialsList(trialList);
				}
			}else{
				jsonMap.put("msg", "录取比例为空，请确认！");
	    		throw new Exception("录取比例为空，请确认！");
			}
			
				if(gradeNoPass.size()>0){//拿到没有入围的人的名单
					List<ScoreEnterTrial> noTrialList = new ArrayList<ScoreEnterTrial>();
					for (ScoreGradeWriten noWritenAll : gradeNoPass) {
						if(StringUtils.isNotEmpty(noWritenAll.getPositionId())){
							postset=postsetDao.selectPostsetById(noWritenAll.getPositionId());
						}
						ScoreEnterTrial scoreEnterTrial = new ScoreEnterTrial();//统一试讲入围表
						scoreEnterTrial.setSchoolId(noWritenAll.getSchoolId());
						scoreEnterTrial.setStudentId(noWritenAll.getStudentId());
						scoreEnterTrial.setIsEnter("2");//2、未入围
						scoreEnterTrial.setItemsId(noWritenAll.getProjectId());
						scoreEnterTrial.setPostId(noWritenAll.getPositionId());
						scoreEnterTrial.setListPublishStatus("1");//1、名单未发布
						scoreEnterTrial.setScorePublishStatus("2");//2、成绩已发布
						scoreEnterTrial.setType((String)condition.get("testType"));
						if(postset!=null){
							scoreEnterTrial.setSubjectType(postset.getIsArt().toString());//0、普通科1、艺术科
						}
						scoreEnterTrial.setCreateUser(user.getId());
						noTrialList.add(scoreEnterTrial);
					}
					// for 循环入围名单
					// 批量存储入围名单
					int pointsDataLimit = 1000;// 限制条数
					Integer size = noTrialList.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<ScoreEnterTrial> nolistPageTrial = noTrialList.subList(0, pointsDataLimit);
							// 存储
							scoreEnterTrialDao.saveTrialsList(nolistPageTrial);
							noTrialList.subList(0, pointsDataLimit).clear();
						}
						if(noTrialList.size()>0){
							// 存储
							scoreEnterTrialDao.saveTrialsList(noTrialList);
						}	
					} else {
						scoreEnterTrialDao.saveTrialsList(noTrialList);
					}
			}
			//查询在单位面试中过滤了的两类考生（东莞生源免费师范生和报考中职专业课的）
			condition.put("freeCourse", "1");//1、是
			List<ScoreEntersOutVo> scoreList =scoreEnterWritienDao.selectAllWritien(condition);
			List<ScoreEnterTrial> trialListTwo = new ArrayList<ScoreEnterTrial>();
			if(scoreList.size()>0){
				for (ScoreEntersOutVo scoreEntersOutVo : scoreList) {
					if(StringUtils.isNotEmpty(scoreEntersOutVo.getPostId())){
						postset=postsetDao.selectPostsetById(scoreEntersOutVo.getPostId());
					}
					ScoreEnterTrial scoreEnterTrialTwo = new ScoreEnterTrial();//统一试讲入围表
					scoreEnterTrialTwo.setSchoolId(scoreEntersOutVo.getSchoolId());
					scoreEnterTrialTwo.setStudentId(scoreEntersOutVo.getStudentId());
					scoreEnterTrialTwo.setIsEnter("1");//1、入围
					scoreEnterTrialTwo.setItemsId(scoreEntersOutVo.getProjectId());
					scoreEnterTrialTwo.setPostId(scoreEntersOutVo.getPostId());
					scoreEnterTrialTwo.setListPublishStatus("1");//1、名单未发布
					scoreEnterTrialTwo.setScorePublishStatus("2");//2、成绩已发布
					scoreEnterTrialTwo.setType((String)condition.get("testType"));
					if(postset!=null){
						scoreEnterTrialTwo.setSubjectType(postset.getIsArt().toString());//0、普通科1、艺术科
					}
					scoreEnterTrialTwo.setCreateUser(user.getId());
					trialListTwo.add(scoreEnterTrialTwo);
				}
				// for 循环入围名单
				// 批量存储入围名单
				int pointsDataLimitTwo = 1000;// 限制条数
				Integer sizeTwo = trialListTwo.size();
				if (pointsDataLimitTwo < sizeTwo) {
					int partTwo = sizeTwo / pointsDataLimitTwo;// 分批数
					for (int j = 0; j < partTwo; j++) {
						List<ScoreEnterTrial> listPageTrialTwo = trialListTwo.subList(0, pointsDataLimitTwo);
						// 存储
						scoreEnterTrialDao.saveTrialsList(listPageTrialTwo);
						trialListTwo.subList(0, pointsDataLimitTwo).clear();
					}
					// 存储
					scoreEnterTrialDao.saveTrialsList(trialListTwo);
				} else {
					scoreEnterTrialDao.saveTrialsList(trialListTwo);
				}
			}*/
			themeService.updateStep((String)condition.get("projectId"), 5);//统一笔试成绩发布
			//公告表修改
			Notice noticeTwo=new Notice();
			noticeTwo.setWrittenScorePublish(1);//笔试成绩发布
			noticeTwo.setThemeId((String)condition.get("projectId"));
			noticeService.updateNoticeByFieldAndTheme(noticeTwo);
		}else{
			jsonMap.put("msg", "招聘公告为空，请确认！");
    		throw new Exception("招聘公告为空，请确认！");
		}
	}

    /**
     * 取消发布成绩
     * @param project
     * @param step
     * @return
     */
	@Override
	public String celpublishWritien(String projectId,Integer step) throws Exception {
		String flag="";
		/*int delcount=0;
		if(StringUtils.isNotBlank(projectId)){
			delcount=scoreEnterTrialDao.deleteTrialByProjectId(projectId);//删除发布成绩时创建的数据
		}else{
 			flag="false";
 			return flag;
 		}	
		if(delcount>0){
			ScoreEnterTrial scoreEnterTrial = new ScoreEnterTrial();
			scoreEnterTrial.setItemsId(projectId);
			List<ScoreEnterTrial> trials=scoreEnterTrialDao.selectAllByScoreEnterTrial(scoreEnterTrial);//查询是否删除完成
			if (trials.size()==0) {*/
				themeService.updateStep(projectId, step);//回到4、统一笔试名单发布状态
				//公告表修改
				Notice noticeTwo=new Notice();
				noticeTwo.setWrittenScorePublish(2);//取消笔试成绩发布
				noticeTwo.setThemeId(projectId);
				noticeService.updateNoticeByFieldAndTheme(noticeTwo);
				flag="success";
			/*}else{
				flag="false";
			}
		}else{
			flag="false";
		}*/
		return flag;
	}
    
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeWriten 
	 */	
    public ScoreGradeWriten selectScoreGradeWritenById(String id){
    
    	return scoreGradeWritenDao.selectScoreGradeWritenById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreGradeWriten> scoreGradeWritens =scoreGradeWritenDao.selectOnePageByMap(page,map);
	     	if(scoreGradeWritens!=null&&scoreGradeWritens.size()>0){
	     		page.setResult(scoreGradeWritens);
	     	}else{
	     		page.setResult(new ArrayList<ScoreGradeWriten>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreGradeWriten  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreGradeWriten(Page page,ScoreGradeWriten scoreGradeWriten){
 		 List<ScoreGradeWriten> scoreGradeWritens =scoreGradeWritenDao.selectOnePageByScoreGradeWriten(page,scoreGradeWriten);
	     	if(scoreGradeWritens!=null&&scoreGradeWritens.size()>0){
	     		page.setResult(scoreGradeWritens);
	     	}else{
	     		page.setResult(new ArrayList<ScoreGradeWriten>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public List<ScoreGradeWriten> selectAllByMap(Map<String,Object> map){
     	return scoreGradeWritenDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public List<ScoreGradeWriten> selectAllByScoreGradeWriten(ScoreGradeWriten scoreGradeWriten){
     
    	 return scoreGradeWritenDao.selectAllByScoreGradeWriten(scoreGradeWriten);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeWriten
	 */	
     public ScoreGradeWriten selectObjectByMap(Map<String,Object> map){
     
    	 return scoreGradeWritenDao.selectObjectByMap(map);
     }
     
 	 @Override
 	 public void updateWritenList(List<ScoreGradeWriten> gradeWritensList,User user, JSONObject jsonMap)  throws Exception {
 		if (gradeWritensList == null||gradeWritensList.size()==0) {
			jsonMap.put("msg", "要修改的数据为空，请稍后重试！");
		} 
 		List<String> idList=new ArrayList<String>();//获取ID的集合
 		for (ScoreGradeWriten writenList : gradeWritensList) {
			if(StringUtils.isBlank(writenList.getId())){
				jsonMap.put("msg", "保存的数据ID不能为空，请稍后再试！");
				throw new Exception("保存的数据ID不能为空，请稍后再试！");
			}
			if(StringUtils.isBlank(writenList.getGrade())){
				jsonMap.put("msg", "统一笔试成绩不能为空，请确认！");
				throw new Exception("统一笔试成绩不能为空，请确认！");
			}
			if(!writenList.getGrade().matches("^\\d{1,2}(\\.\\d{1,2})|\\d{1,3}(\\.\\d{1})?$")){
				jsonMap.put("msg", "成绩输入有误，请输入1到3位整数或两位小数！");
				throw new Exception("成绩输入有误，请输入1到3位整数或两位小数！");
			}
			idList.add(writenList.getId());
		}
 		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", idList);
		List<ScoreGradeWriten> oldScore=scoreGradeWritenDao.selectWritenByMap(map);//之前的分数
		List<ScoreGradeWriten> compareScore=this.getDifferScore(oldScore, gradeWritensList);//比较后的
		if(compareScore !=null && !compareScore.isEmpty() ){
			for(ScoreGradeWriten writen : compareScore){
				writen.setModifyuser(user.getId());
			}
			this.saveScoreAdjustLog(compareScore, user);
			scoreGradeWritenDao.updateWritenList(compareScore);
		}	
 	 }
     
 	/**
 	 * 批量保存调整成绩日志表
 	 * @param differGrade
 	 * @param user
 	 */
 	private void saveScoreAdjustLog(List<ScoreGradeWriten> differScore,User user){
 		List<GradeAdjustLog> gradeAdjustLogs=new ArrayList<GradeAdjustLog>();
 		GradeAdjustLog gradeAdjustLog=null;
 		for(ScoreGradeWriten score : differScore){
 			ScoreGradeWriten scoreWriten=scoreGradeWritenDao.selectScoreGradeWritenById(score.getId());
 			gradeAdjustLog=new GradeAdjustLog();
 			gradeAdjustLog.setProjectId(scoreWriten.getProjectId());
 			gradeAdjustLog.setPositionId(scoreWriten.getPositionId());
 			gradeAdjustLog.setStudentId(score.getStudentId());
 			gradeAdjustLog.setType("2");//统一笔试
 			gradeAdjustLog.setCreateuser(user.getId());
 			gradeAdjustLog.setModifyuser(user.getId());
 			gradeAdjustLog.setInitGrade(scoreWriten.getGrade());//上一次的成绩
 			gradeAdjustLog.setGradeAfter(score.getGrade());//新的成绩
 			gradeAdjustLog.setSchoolId(scoreWriten.getSchoolId());
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
 	private List<ScoreGradeWriten> getDifferScore(List<ScoreGradeWriten> scoreOlds,List<ScoreGradeWriten> scoreNews){
 		List<ScoreGradeWriten> data=new ArrayList<ScoreGradeWriten>();
 		for(ScoreGradeWriten scoreOld : scoreOlds){
 			for(ScoreGradeWriten scoreNew : scoreNews){
 				if(scoreOld.getId().equals(scoreNew.getId())&& (!scoreNew.getGrade().equals(scoreOld.getGrade()))){
 					data.add(scoreNew);
 					break;
 				}
 			}
 		}
 		return data;
 	}
 	
 	@Override
	public Integer selectLabsCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreGradeWritenDao.selectLabsCount(map);
	}
 	
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeWriten
	 */	
     public ScoreGradeWriten selectObjectByScoreGradeWriten(ScoreGradeWriten scoreGradeWriten){
     
     	return scoreGradeWritenDao.selectObjectByScoreGradeWriten(scoreGradeWriten);
     }

     @Override
 	 public Integer selectGradeWritienCount(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreGradeWritenDao.selectGradeWritienCount(map);
 	 }

     
     //除法
     public static Double divide(Integer dividend, Integer divisor, Integer scale) {
         if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
         }
         BigDecimal b1 = new BigDecimal(Double.toString(dividend));
         BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale,RoundingMode.HALF_UP).doubleValue();
    }
     
     //乘法
     public static BigDecimal mul(Double value1, Integer value2) {
	     BigDecimal b1 = new BigDecimal(Double.toString(value1));
	     BigDecimal b2 = new BigDecimal(Double.toString(value2));
	     return b1.multiply(b2);
	 }

    @Override
    public ScoreGradeWriten selectGradeWritten(StudentApplyInfo sai) {
        //查询笔试成绩
        ScoreGradeWriten sgw = new ScoreGradeWriten();
        sgw.setProjectId(sai.getEmployItemsId());
        sgw.setPositionId(sai.getApplyJobId());
        sgw.setSchoolId(sai.getApplyDepId());
        sgw.setStudentId(sai.getStudentId());
        return scoreGradeWritenDao.selectObjectByScoreGradeWriten(sgw);
    }

}
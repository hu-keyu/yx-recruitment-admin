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
import org.jypj.dev.entity.ScoreEnterTrial;
import org.jypj.dev.entity.ScoreEnterWritien;
import org.jypj.dev.entity.ScoreGradeTrial;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.ScoreEnterRecordDao;
import org.jypj.dev.dao.ScoreEnterTrialDao;
import org.jypj.dev.dao.ScoreGradeTrialDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

/**
* ScoreEnterTrial业务接口实现层
* 统一试讲入围表
* @author
*
*/

@Service("scoreEnterTrialService")
public class ScoreEnterTrialServiceImpl implements ScoreEnterTrialService {
	
    @Resource 
    private ScoreEnterTrialDao scoreEnterTrialDao;
    @Resource 
    private ScoreGradeTrialDao scoreGradeTrialDao ;
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
	 * @param scoreEnterTrial
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterTrialByField(ScoreEnterTrial scoreEnterTrial){
	
		return scoreEnterTrialDao.saveScoreEnterTrialByField(scoreEnterTrial);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterTrial 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterTrial(ScoreEnterTrial scoreEnterTrial){
	
		return scoreEnterTrialDao.saveScoreEnterTrial(scoreEnterTrial);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterTrialById(String id){
    
    	return scoreEnterTrialDao.deleteScoreEnterTrialById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterTrialByObject(ScoreEnterTrial scoreEnterTrial){
    
    	return scoreEnterTrialDao.deleteScoreEnterTrialByObject(scoreEnterTrial);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterTrialByField(ScoreEnterTrial scoreEnterTrial){
    
    	return scoreEnterTrialDao.updateScoreEnterTrialByField(scoreEnterTrial);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterTrial(ScoreEnterTrial scoreEnterTrial){
    
    	return scoreEnterTrialDao.updateScoreEnterTrial(scoreEnterTrial);
    }
    
    @Override
	public Page selectTrialEnterPage(Page page, Map<String, Object> map) {
    	List<ScoreEnterTrial> scoreEnterTrials =scoreEnterTrialDao.selectTrialEnterPage(page, map);
	 		if(scoreEnterTrials!=null&&scoreEnterTrials.size()>0){
	     		page.setResult(scoreEnterTrials);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterTrial>());
	     	}
	     	return page;
	}
    
    @Override
	public void enterTriaList(String flag,String chks, String projectId, String positionid, User user, JSONObject jsonMap)
			throws Exception {
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
		queryParameter.put("employItemsId", projectId);
		queryParameter.put("positionid", positionid);
		List<ScoreEnterTrial> scoreEnterTrialsList= scoreEnterTrialDao.selectAllByMap(queryParameter);
		List<ScoreEnterRecord> scoreEnterRecordList=new ArrayList<ScoreEnterRecord>();
		if(scoreEnterTrialsList !=null && !scoreEnterTrialsList.isEmpty()){
			if(StringUtils.isNotEmpty(flag)&&flag.equals("1")){//调整到入围名单中
				for(ScoreEnterTrial scoreEnterTrial : scoreEnterTrialsList){
					scoreEnterTrial.setIsEnter("1");//1、入围 2、未入围
					scoreEnterTrial.setModifyUser(user.getId());
					//往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterTrial.getItemsId());
					scoreEnterRecord.setIsEnter("2");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterTrial.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterTrial.getStudentId());
					scoreEnterRecord.setType("3");//统一试讲
					scoreEnterRecord.setUpdateReason("将未入围的考生调整到入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
					
				}
				scoreEnterTrialDao.updateTrialEnterList(scoreEnterTrialsList);
				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
			}else if(StringUtils.isNotEmpty(flag)&&flag.equals("2")){//调整到未入围的名单中
				for(ScoreEnterTrial scoreEnterTrial : scoreEnterTrialsList){
					scoreEnterTrial.setIsEnter("2");//1、入围 2、未入围
					scoreEnterTrial.setModifyUser(user.getId());
					//往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterTrial.getItemsId());
					scoreEnterRecord.setIsEnter("1");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterTrial.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterTrial.getStudentId());
					scoreEnterRecord.setType("3");//统一试讲
					scoreEnterRecord.setUpdateReason("将入围的考生调整到未入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
				}
				scoreEnterTrialDao.updateTrialEnterList(scoreEnterTrialsList);
				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
			}
		}else{
			jsonMap.put("msg", "入围名单为空，请确认！");
    		throw new Exception("入围名单为空，请确认");
		}	
		
	}
    
    /**
     * 批量插入进入统一试讲名单
     * @param list
    */
	@Override
	public String addBatchTrials(Map<String, Object> condition, Page page, User user,String flag) {
		String msg="";
		condition =page.getCondition();
		condition.put("isEnter", "1");//1、入围
		if(StringUtils.isNotBlank(flag)&&flag.equals("1")){  //1、普通科
			condition.put("subjectType", "0");
		}else if(StringUtils.isNotBlank(flag)&&flag.equals("2")){  //2、艺术科
			condition.put("subjectType", "1");
		}else{
			msg="false";
			return msg;
		}
		List<ScoreEntersOutVo> scoreList =scoreEnterTrialDao.selectAllTrial(condition);
		List<ScoreGradeTrial> gradeList =new ArrayList<ScoreGradeTrial>();
		//List<StudentApplyInfo> applyList =new ArrayList<StudentApplyInfo>();
		List<ScoreEnterTrial> trialList =new ArrayList<ScoreEnterTrial>();
		if(scoreList.size()>0){
			for (ScoreEntersOutVo scoreAll : scoreList) {
				ScoreGradeTrial scoreGradeTrial=new ScoreGradeTrial();//统一笔试成绩表
				scoreGradeTrial.setStudentId(scoreAll.getStudentId());
				scoreGradeTrial.setGrade("0");
				scoreGradeTrial.setListPublishStatus("1");//0、未发布1、已发布
				scoreGradeTrial.setProjectId(scoreAll.getProjectId());
				scoreGradeTrial.setCreatetime(new Date());
				scoreGradeTrial.setPositionId(scoreAll.getPostId());
				scoreGradeTrial.setCreateuser(user.getId());
				scoreGradeTrial.setSchoolId(scoreAll.getSchoolId());
				scoreGradeTrial.setScorePublishStatus("0");//0、未发布1、发布
				scoreGradeTrial.setType((String)condition.get("testType"));
				scoreGradeTrial.setModifyuser(user.getId());
				if(flag.equals("1")){  //1、普通科
					scoreGradeTrial.setSubjectType("0");
				}else if(flag.equals("2")){  //2、艺术科
					scoreGradeTrial.setSubjectType("1");
				}
				gradeList.add(scoreGradeTrial);
				/*StudentApplyInfo studentApplyInfo=new StudentApplyInfo();//学生报考信息表
				studentApplyInfo.setId(scoreAll.getApplyId());
				studentApplyInfo.setApplyStatus("9");//9、发布试讲名单和考场信息，进入试讲环节；
				studentApplyInfo.setModifyUser(user.getId());
				applyList.add(studentApplyInfo);*/
				ScoreEnterTrial scoreEnterTrial=new ScoreEnterTrial();//试讲入围名单表，将发布状态改为已发布
				scoreEnterTrial.setListPublishStatus("2");//已发布
				scoreEnterTrial.setModifyUser(user.getId());
				scoreEnterTrial.setId(scoreAll.getId());
				trialList.add(scoreEnterTrial);
				
			}
		
			// for 循环入围名单
			// 批量存储入围名单
			int pointsDataLimit = 1000;// 限制条数
			Integer size = gradeList.size();
			if (pointsDataLimit < size) {
				int part = size / pointsDataLimit;// 分批数
				for (int i = 0; i < part; i++) {
					List<ScoreGradeTrial> listPageGrade = gradeList.subList(0, pointsDataLimit);
					//List<StudentApplyInfo> listPageApply = applyList.subList(0, pointsDataLimit);
					List<ScoreEnterTrial> listPageTrial = trialList.subList(0, pointsDataLimit);
					// 存储
					scoreGradeTrialDao.saveTrialGradesList(listPageGrade);
					//studentApplyInfoDao.updateList(listPageApply);
					scoreEnterTrialDao.updateTrialEnterList(listPageTrial);
					trialList.subList(0, pointsDataLimit).clear();
					//applyList.subList(0, pointsDataLimit).clear();
					gradeList.subList(0, pointsDataLimit).clear();
				}
				if(gradeList.size()>0){
					// 存储
					//studentApplyInfoDao.updateList(applyList);
					scoreGradeTrialDao.saveTrialGradesList(gradeList);
					scoreEnterTrialDao.updateTrialEnterList(trialList);
				}
			} else {
				//studentApplyInfoDao.updateList(applyList);
				scoreGradeTrialDao.saveTrialGradesList(gradeList);
				scoreEnterTrialDao.updateTrialEnterList(trialList);
			}
			condition.put("subjectType", "0");//普通科
	    	Integer comsize=scoreEnterTrialDao.selectCountByMap(condition);//查询普通科
	    	condition.put("subjectType", "1");//艺术科
	    	Integer artsize=scoreEnterTrialDao.selectCountByMap(condition);//查询普通科
	    	
			if(StringUtils.isNotBlank(flag)&&flag.equals("1")){  //1、普通科
				//公告表修改
				Notice noticecom=new Notice();
				if(comsize>0&&artsize>0){
					noticecom.setLectureListPublishNor(1);//普通科名单发布
				}else if(comsize>0&&artsize==0){
					noticecom.setLectureListPublishNor(1);//普通科名单发布
					noticecom.setLectureListPublishArt(1);//艺术科名单发布
				}
				noticecom.setThemeId((String)condition.get("projectId"));
				noticeService.updateNoticeByFieldAndTheme(noticecom);
			}else if(StringUtils.isNotBlank(flag)&&flag.equals("2")){  //2、艺术科
				//公告表修改
				Notice noticeart=new Notice();
				if(comsize>0&&artsize>0){
					noticeart.setLectureListPublishArt(1);//艺术科名单发布
				}else if(comsize==0&&artsize>0){
					noticeart.setLectureListPublishNor(1);//普通科名单发布
					noticeart.setLectureListPublishArt(1);//艺术科名单发布
					
				}
				noticeart.setThemeId((String)condition.get("projectId"));
				noticeService.updateNoticeByFieldAndTheme(noticeart);
			}
			msg="success";
			
		}else{
			condition.put("isEnter", "2");//2、未入围
			if(StringUtils.isNotBlank(flag)&&flag.equals("1")){  //1、普通科
				condition.put("subjectType", "0");
			}else if(StringUtils.isNotBlank(flag)&&flag.equals("2")){  //2、艺术科
				condition.put("subjectType", "1");
			}else{
				msg="false";
				return msg;
			}
			List<ScoreEntersOutVo> noList =scoreEnterTrialDao.selectAllTrial(condition);//查询没有入围的名单，并修改发布状态
			List<ScoreEnterTrial> noTrialList =new ArrayList<ScoreEnterTrial>();
			if(noList.size()>0){
				for (ScoreEntersOutVo noAll : noList) {
					ScoreEnterTrial scoreEnterTrial=new ScoreEnterTrial();//试讲入围名单表，将发布状态改为已发布
					scoreEnterTrial.setListPublishStatus("2");//已发布
					scoreEnterTrial.setModifyUser(user.getId());
					scoreEnterTrial.setId(noAll.getId());
					noTrialList.add(scoreEnterTrial);
				}
				// for 循环入围名单
				// 批量存储入围名单
				int pointsDataLimit = 1000;// 限制条数
				Integer size = noTrialList.size();
				if (pointsDataLimit < size) {
					int part = size / pointsDataLimit;// 分批数
					for (int i = 0; i < part; i++) {
						List<ScoreEnterTrial> noPageTrial = noTrialList.subList(0, pointsDataLimit);
						scoreEnterTrialDao.updateTrialEnterList(noPageTrial);
						noTrialList.subList(0, pointsDataLimit).clear();
					}
					if(noTrialList.size()>0){
						scoreEnterTrialDao.updateTrialEnterList(noTrialList);
					}	
				} else {
					scoreEnterTrialDao.updateTrialEnterList(noTrialList);
				}
				if(StringUtils.isNotBlank(flag)&&flag.equals("1")){  //1、普通科
					//公告表修改
					Notice noticecom=new Notice();
					noticecom.setLectureListPublishNor(1);//普通科名单发布
					noticecom.setThemeId((String)condition.get("projectId"));
					noticeService.updateNoticeByFieldAndTheme(noticecom);
				}else if(StringUtils.isNotBlank(flag)&&flag.equals("2")){  //2、艺术科
					//公告表修改
					Notice noticeart=new Notice();
					noticeart.setLectureListPublishArt(1);//艺术科名单发布
					noticeart.setThemeId((String)condition.get("projectId"));
					noticeService.updateNoticeByFieldAndTheme(noticeart);
				}
			}	
			
			msg="false";
		}
		Notice noticeSer=noticeService.selectObjectByThemeId((String)condition.get("projectId"));
		Integer normal = noticeSer.getLectureListPublishNor();//普通科名单发布
		Integer art = noticeSer.getLectureListPublishArt();//艺术科名单发布
		if(normal==1&&art==1){//两个学科都发布了就改主题表
			//统一试讲名单发布
			themeService.updateStep((String)condition.get("projectId"), 6);//更新主题表的流程
		}
		
		return msg;
	}
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterTrial 
	 */	
    public ScoreEnterTrial selectScoreEnterTrialById(String id){
    
    	return scoreEnterTrialDao.selectScoreEnterTrialById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreEnterTrial> scoreEnterTrials =scoreEnterTrialDao.selectOnePageByMap(page,map);
	     	if(scoreEnterTrials!=null&&scoreEnterTrials.size()>0){
	     		page.setResult(scoreEnterTrials);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterTrial>());
	     	}
	     	return page;
     }
     
     @Override
 	public List<ScoreEntersOutVo> selectExportByMap(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreEnterTrialDao.selectExportByMap(map);
 	}
     

 	@Override
 	public List<ScoreEntersOutVo> selectListExportByMap(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreEnterTrialDao.selectListExportByMap(map);
 	}
     
     @Override
     public Page selectSynthesizePageByMap(Page page, Map<String, Object> map) {
		 List<ScoreEntersOutVo> scoreEnterWritiens =scoreEnterTrialDao.selectSynthesizePageByMap(page, map);
 		if(scoreEnterWritiens!=null&&scoreEnterWritiens.size()>0){
     		page.setResult(scoreEnterWritiens);
     	}else{
     		page.setResult(new ArrayList<ScoreEnterWritien>());
     	}
     	return page;
     }
     
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterTrial  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterTrial(Page page,ScoreEnterTrial scoreEnterTrial){
 		 List<ScoreEnterTrial> scoreEnterTrials =scoreEnterTrialDao.selectOnePageByScoreEnterTrial(page,scoreEnterTrial);
	     	if(scoreEnterTrials!=null&&scoreEnterTrials.size()>0){
	     		page.setResult(scoreEnterTrials);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterTrial>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public List<ScoreEnterTrial> selectAllByMap(Map<String,Object> map){
     	return scoreEnterTrialDao.selectAllByMap(map);
     }
     
     @Override
 	public Integer selectCountByMap(Map<String, Object> map) {
 		return scoreEnterTrialDao.selectCountByMap(map);
 	}
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public List<ScoreEnterTrial> selectAllByScoreEnterTrial(ScoreEnterTrial scoreEnterTrial){
     
    	 return scoreEnterTrialDao.selectAllByScoreEnterTrial(scoreEnterTrial);
     }
		

 	@Override
 	public Integer selectLabsCount(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreEnterTrialDao.selectLabsCount(map);
 	}
     
     @Override
 	public List<ScoreEntersOutVo> selectAllTrial(Map<String, Object> map) {
 		return  scoreEnterTrialDao.selectAllTrial(map);
 	}
     
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterTrial
	 */	
     public ScoreEnterTrial selectObjectByMap(Map<String,Object> map){
     
    	 return scoreEnterTrialDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterTrial
	 */	
     public ScoreEnterTrial selectObjectByScoreEnterTrial(ScoreEnterTrial scoreEnterTrial){
     
     	return scoreEnterTrialDao.selectObjectByScoreEnterTrial(scoreEnterTrial);
     }

	@Override
	public Page selectPersonLecture(Page page, Map<String, Object> map) {
		List<ScoreEntersOutVo> ScoreEntersOutVo =scoreEnterTrialDao.selectPersonLecture(page,map);
     	if(ScoreEntersOutVo!=null&&ScoreEntersOutVo.size()>0){
     		page.setResult(ScoreEntersOutVo);
     	}else{
     		page.setResult(new ArrayList<ScoreEntersOutVo>());
     	}
     	return page;
	}
	
	@Override
	public List<ScoreEntersOutVo> selectPersonLecture(Map<String, Object> map) {
		return scoreEnterTrialDao.selectPersonLecture(map);
     	
	}

    @Override
    public ScoreEnterTrial selectEnterObject(StudentApplyInfo sai) {
        ScoreEnterTrial set = new ScoreEnterTrial();
        set.setItemsId(sai.getEmployItemsId());
        set.setSchoolId(sai.getApplyDepId());
        set.setPostId(sai.getApplyJobId());
        set.setType("3");//试讲
        set.setIsEnter("1");//是否入围
        set.setListPublishStatus("2");//是否发布
        set.setStudentId(sai.getStudentId());
        return scoreEnterTrialDao.selectObjectByScoreEnterTrial(set);
    }

    @Override
    public Integer selectCountOfEnterTrial(ScoreEnterTrial setl) {
        return scoreEnterTrialDao.selectCountOfEnterTrial(setl);
    }

	@Override
	public Integer selectEntersCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreEnterTrialDao.selectEntersCount(map);
	}

}
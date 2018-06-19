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
import org.jypj.dev.entity.ScoreEnterPhysical;
import org.jypj.dev.entity.ScoreEnterRecord;
import org.jypj.dev.entity.ScoreGradePhysical;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.ScoreEnterPhysicalDao;
import org.jypj.dev.dao.ScoreEnterRecordDao;
import org.jypj.dev.dao.ScoreGradePhysicalDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterPhysicalService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

/**
* ScoreEnterPhysical业务接口实现层
* 体检入围表
* @author
*
*/

@Service("scoreEnterPhysicalService")
public class ScoreEnterPhysicalServiceImpl implements ScoreEnterPhysicalService {
	
    @Resource 
    private ScoreEnterPhysicalDao scoreEnterPhysicalDao;
    @Resource
  	private StudentApplyInfoDao studentApplyInfoDao;
    @Resource
  	private ThemeService themeService;
    @Resource 
    private ScoreGradePhysicalDao scoreGradePhysicalDao;
    @Resource
  	private NoticeService noticeService ;
    @Resource
  	private ScoreEnterRecordDao scoreEnterRecordDao;
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterPhysical
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterPhysicalByField(ScoreEnterPhysical scoreEnterPhysical){
	
		return scoreEnterPhysicalDao.saveScoreEnterPhysicalByField(scoreEnterPhysical);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterPhysical 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical){
	
		return scoreEnterPhysicalDao.saveScoreEnterPhysical(scoreEnterPhysical);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterPhysicalById(String id){
    
    	return scoreEnterPhysicalDao.deleteScoreEnterPhysicalById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterPhysicalByObject(ScoreEnterPhysical scoreEnterPhysical){
    
    	return scoreEnterPhysicalDao.deleteScoreEnterPhysicalByObject(scoreEnterPhysical);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterPhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterPhysicalByField(ScoreEnterPhysical scoreEnterPhysical){
    
    	return scoreEnterPhysicalDao.updateScoreEnterPhysicalByField(scoreEnterPhysical);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterPhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical){
    
    	return scoreEnterPhysicalDao.updateScoreEnterPhysical(scoreEnterPhysical);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterPhysical 
	 */	
    public ScoreEnterPhysical selectScoreEnterPhysicalById(String id){
    
    	return scoreEnterPhysicalDao.selectScoreEnterPhysicalById(id);
    }
    
    /**
	 * 查询进入体检的入围名单
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public Page selectPhysicalEnterPageByMap(Page page,Map<String,Object> map){
     	 List<ScoreEnterPhysical> scoreEnterPhysicals =scoreEnterPhysicalDao.selectPhysicalEnterPageByMap(page,map);
	     	if(scoreEnterPhysicals!=null&&scoreEnterPhysicals.size()>0){
	     		page.setResult(scoreEnterPhysicals);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterPhysical>());
	     	}
	     	return page;
     }
     
    @Override
 	public void enterPhysicallist(String flag, String chks, String projectId, String positionid, User user,
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
 		List<ScoreEnterPhysical> scoreEnterPhysicalList= scoreEnterPhysicalDao.selectAllByMap(queryParameter);
 		List<ScoreEnterRecord> scoreEnterRecordList=new ArrayList<ScoreEnterRecord>();
 		if(scoreEnterPhysicalList !=null && !scoreEnterPhysicalList.isEmpty()){
 			if(StringUtils.isNotEmpty(flag)&&flag.equals("1")){//调整到入围名单中
 				for(ScoreEnterPhysical scoreEnterPhysical : scoreEnterPhysicalList){
 					scoreEnterPhysical.setIsEnter("1");//1、入围 2、未入围
 					scoreEnterPhysical.setModifyUser(user.getId());
 					//往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterPhysical.getItemsId());
					scoreEnterRecord.setIsEnter("2");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterPhysical.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterPhysical.getStudentId());
					scoreEnterRecord.setType("4");//体检
					scoreEnterRecord.setUpdateReason("将未入围的考生调整到入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
 					
 				}
 				scoreEnterPhysicalDao.updatePhysicalEnterList(scoreEnterPhysicalList);
 				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
 			}else if(StringUtils.isNotEmpty(flag)&&flag.equals("2")){//调整到未入围的名单中
 				for(ScoreEnterPhysical scoreEnterPhysical : scoreEnterPhysicalList){
 					scoreEnterPhysical.setIsEnter("2");//1、入围 2、未入围
 					scoreEnterPhysical.setModifyUser(user.getId());
 					//往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterPhysical.getItemsId());
					scoreEnterRecord.setIsEnter("1");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterPhysical.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterPhysical.getStudentId());
					scoreEnterRecord.setType("4");//体检
					scoreEnterRecord.setUpdateReason("将入围的考生调整到未入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
 				}
 				scoreEnterPhysicalDao.updatePhysicalEnterList(scoreEnterPhysicalList);
 				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
 			}
 		}else{
			jsonMap.put("msg", "入围名单为空，请确认！");
    		throw new Exception("入围名单为空，请确认");
		}	
 		
 	}
     
     /**
      * 批量插入进入体检名单
      * @param list
     */ 
    @Override
 	public String addBatchPhysical(Map<String, Object> condition, Page page, User user) {
    	condition =page.getCondition();
 		condition.put("isEnter", "1");//1、入围
 		List<ScoreEntersOutVo> scoreList =scoreEnterPhysicalDao.selectAllPhysical(condition);
 		String flag="";
 		List<ScoreGradePhysical> gradeList =new ArrayList<ScoreGradePhysical>();
 		List<StudentApplyInfo> applyList =new ArrayList<StudentApplyInfo>();
 		if(scoreList.size()>0){
 			for (ScoreEntersOutVo scoreAll : scoreList) {
 				ScoreGradePhysical scoreGradePhysical=new ScoreGradePhysical();//体检成绩表
 				scoreGradePhysical.setStudentId(scoreAll.getStudentId());
 				scoreGradePhysical.setResult("2");//默認不通過
 				scoreGradePhysical.setListPublishStatus("1");//0、未发布1、已发布
 				scoreGradePhysical.setProjectId(scoreAll.getProjectId());
 				scoreGradePhysical.setCreatetime(new Date());
 				scoreGradePhysical.setPositionId(scoreAll.getPostId());
 				scoreGradePhysical.setCreateuser(user.getId());
 				scoreGradePhysical.setSchoolId(scoreAll.getSchoolId());
 				scoreGradePhysical.setScorePublishStatus("0");//0、未发布1、发布
 				scoreGradePhysical.setType((String)condition.get("testType"));
 				scoreGradePhysical.setModifyuser(user.getId());
 				gradeList.add(scoreGradePhysical);
 				StudentApplyInfo studentApplyInfo=new StudentApplyInfo();//学生报考信息表
 				studentApplyInfo.setId(scoreAll.getApplyId());
 				studentApplyInfo.setApplyStatus("10");//10、发布体检名单，并进入体检环节；
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
 					List<ScoreGradePhysical> listPageGrade = gradeList.subList(0, pointsDataLimit);
 					List<StudentApplyInfo> listPageApply = applyList.subList(0, pointsDataLimit);
 					// 存储
 					scoreGradePhysicalDao.savePhysicalGradesList(listPageGrade);
 					studentApplyInfoDao.updateList(listPageApply);
 					applyList.subList(0, pointsDataLimit).clear();
 					gradeList.subList(0, pointsDataLimit).clear();
 				}
 				if(gradeList.size()>0){
	 				// 存储
	 				studentApplyInfoDao.updateList(applyList);;
	 				scoreGradePhysicalDao.savePhysicalGradesList(gradeList);
 				}	
 			} else {
 				studentApplyInfoDao.updateList(applyList);
 				scoreGradePhysicalDao.savePhysicalGradesList(gradeList);
 			}
 			flag="success";
 			//体检名单发布
 			themeService.updateStep((String)condition.get("projectId"), 8);//更新主题表的流程
 			
 			//公告表修改
			Notice noticeTwo=new Notice();
			noticeTwo.setBodyexamListPublish(1);//体检名单发布
			noticeTwo.setThemeId((String)condition.get("projectId"));
			noticeService.updateNoticeByFieldAndTheme(noticeTwo);
 		}else{
 			flag="false";
 		}
 		return flag;
 	} 
     
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterPhysical  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterPhysical(Page page,ScoreEnterPhysical scoreEnterPhysical){
 		 List<ScoreEnterPhysical> scoreEnterPhysicals =scoreEnterPhysicalDao.selectOnePageByScoreEnterPhysical(page,scoreEnterPhysical);
	     	if(scoreEnterPhysicals!=null&&scoreEnterPhysicals.size()>0){
	     		page.setResult(scoreEnterPhysicals);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterPhysical>());
	     	}
	     	return page;
     }
    

 	@Override
 	public List<ScoreEntersOutVo> selectExportByMap(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreEnterPhysicalDao.selectExportByMap(map);
 	}

 	@Override
	public List<ScoreEntersOutVo> selectPostCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreEnterPhysicalDao.selectPostCount(map);
	}
     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public List<ScoreEnterPhysical> selectAllByMap(Map<String,Object> map){
     	return scoreEnterPhysicalDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public List<ScoreEnterPhysical> selectAllByScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical){
     
    	 return scoreEnterPhysicalDao.selectAllByScoreEnterPhysical(scoreEnterPhysical);
     }
     

 	@Override
 	public List<ScoreEntersOutVo> selectAllPhysical(Map<String, Object> map) {
 		return scoreEnterPhysicalDao.selectAllPhysical(map);
 	}
 	
 	@Override
	public List<ScoreEntersOutVo> selectListExportByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreEnterPhysicalDao.selectListExportByMap(map);
	}

		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterPhysical
	 */	
     public ScoreEnterPhysical selectObjectByMap(Map<String,Object> map){
     
    	 return scoreEnterPhysicalDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterPhysical
	 */	
     public ScoreEnterPhysical selectObjectByScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical){
     
     	return scoreEnterPhysicalDao.selectObjectByScoreEnterPhysical(scoreEnterPhysical);
     }

}
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
import org.jypj.dev.entity.ScoreEnterWritien;
import org.jypj.dev.entity.ScoreGradeWriten;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.ScoreEnterRecordDao;
import org.jypj.dev.dao.ScoreEnterWritienDao;
import org.jypj.dev.dao.ScoreGradeWritenDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterWritienService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

/**
* ScoreEnterWritien业务接口实现层
* 统一笔试入围表
* @author
*
*/

@Service("scoreEnterWritienService")
public class ScoreEnterWritienServiceImpl implements ScoreEnterWritienService {
	
    @Resource 
    private ScoreEnterWritienDao scoreEnterWritienDao;
    @Resource
  	private StudentApplyInfoDao studentApplyInfoDao;
    @Resource
  	private ThemeService themeService;
    @Resource 
    private ScoreGradeWritenDao scoreGradeWritenDao;
    @Resource
  	private NoticeService noticeService ;
    @Resource
  	private ScoreEnterRecordDao scoreEnterRecordDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterWritien
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterWritienByField(ScoreEnterWritien scoreEnterWritien){
	
		return scoreEnterWritienDao.saveScoreEnterWritienByField(scoreEnterWritien);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterWritien 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterWritien(ScoreEnterWritien scoreEnterWritien){
	
		return scoreEnterWritienDao.saveScoreEnterWritien(scoreEnterWritien);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterWritienById(String id){
    
    	return scoreEnterWritienDao.deleteScoreEnterWritienById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterWritienByObject(ScoreEnterWritien scoreEnterWritien){
    
    	return scoreEnterWritienDao.deleteScoreEnterWritienByObject(scoreEnterWritien);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterWritien 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterWritienByField(ScoreEnterWritien scoreEnterWritien){
    
    	return scoreEnterWritienDao.updateScoreEnterWritienByField(scoreEnterWritien);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterWritien 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterWritien(ScoreEnterWritien scoreEnterWritien){
    
    	return scoreEnterWritienDao.updateScoreEnterWritien(scoreEnterWritien);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterWritien 
	 */	
    public ScoreEnterWritien selectScoreEnterWritienById(String id){
    
    	return scoreEnterWritienDao.selectScoreEnterWritienById(id);
    }
    
    @Override
	public void enterlist(String flag,String chks, String projectId, String positionid, User user, JSONObject jsonMap)
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
		queryParameter.put("projectId", projectId);
		queryParameter.put("positionid", positionid);
		List<ScoreEnterWritien> scoreEnterWritiensList= scoreEnterWritienDao.selectAllByMap(queryParameter);
		List<ScoreEnterRecord> scoreEnterRecordList=new ArrayList<ScoreEnterRecord>();
		if(scoreEnterWritiensList !=null && !scoreEnterWritiensList.isEmpty()){
			//List<ScoreEnterWritien> isEnterList=new ArrayList<ScoreEnterWritien>();
			if(StringUtils.isNotEmpty(flag)&&flag.equals("1")){//调整到入围名单中
				for(ScoreEnterWritien scoreEnterWritien : scoreEnterWritiensList){
					scoreEnterWritien.setIsEnter("1");//1、入围 2、未入围
					scoreEnterWritien.setModifyUser(user.getId());
					//往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterWritien.getItemsId());
					scoreEnterRecord.setIsEnter("2");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterWritien.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterWritien.getStudentId());
					scoreEnterRecord.setType("2");//统一笔试
					scoreEnterRecord.setUpdateReason("将未入围的考生调整到入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
				}
				scoreEnterWritienDao.updateEnterList(scoreEnterWritiensList);
				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
			}else if(StringUtils.isNotEmpty(flag)&&flag.equals("2")){//调整到未入围的名单中
				for(ScoreEnterWritien scoreEnterWritien : scoreEnterWritiensList){
					scoreEnterWritien.setIsEnter("2");//1、入围 2、未入围
					scoreEnterWritien.setModifyUser(user.getId());
					//往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterWritien.getItemsId());
					scoreEnterRecord.setIsEnter("1");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterWritien.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterWritien.getStudentId());
					scoreEnterRecord.setType("2");//统一笔试
					scoreEnterRecord.setUpdateReason("将入围的考生调整到未入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
				}
				scoreEnterWritienDao.updateEnterList(scoreEnterWritiensList);
				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
			}
		}else{
			jsonMap.put("msg", "入围名单为空，请确认！");
    		throw new Exception("入围名单为空，请确认");
		}
		
	}

    /**
     * 批量插入进入统一笔试名单
     * @param list
    */
	@Override
	public String addBatchWritien(Map<String, Object> condition, Page page, User user) {
		condition =page.getCondition();
		condition.put("isEnter", "1");//1、入围
		condition.put("freeStudent", "0");//0、否
		condition.put("professionalCourse", "0");//0、否
		List<ScoreEntersOutVo> scoreList =scoreEnterWritienDao.selectAllWritien(condition);
		String flag="";
		List<ScoreGradeWriten> gradeList =new ArrayList<ScoreGradeWriten>();
		List<StudentApplyInfo> applyList =new ArrayList<StudentApplyInfo>();
		if(scoreList.size()>0){
			for (ScoreEntersOutVo scoreAll : scoreList) {
				ScoreGradeWriten scoreGradeWriten=new ScoreGradeWriten();//统一笔试成绩表
				scoreGradeWriten.setStudentId(scoreAll.getStudentId());
				scoreGradeWriten.setGrade("0");
				scoreGradeWriten.setListPublishStatus("1");//0、未发布1、已发布
				scoreGradeWriten.setProjectId(scoreAll.getProjectId());
				scoreGradeWriten.setCreatetime(new Date());
				scoreGradeWriten.setPositionId(scoreAll.getPostId());
				scoreGradeWriten.setCreateuser(user.getId());
				scoreGradeWriten.setSchoolId(scoreAll.getSchoolId());
				scoreGradeWriten.setScorePublishStatus("0");//0、未发布1、发布
				scoreGradeWriten.setType((String)condition.get("testType"));
				scoreGradeWriten.setModifyuser(user.getId());
				gradeList.add(scoreGradeWriten);
				StudentApplyInfo studentApplyInfo=new StudentApplyInfo();//学生报考信息表
				studentApplyInfo.setId(scoreAll.getApplyId());
				studentApplyInfo.setApplyStatus("8");//8、发布笔试名单和考场信息，进入笔试环节；
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
					List<ScoreGradeWriten> listPageGrade = gradeList.subList(0, pointsDataLimit);
					List<StudentApplyInfo> listPageApply = applyList.subList(0, pointsDataLimit);
					// 存储
					scoreGradeWritenDao.saveGradesList(listPageGrade);
					studentApplyInfoDao.updateList(listPageApply);
					applyList.subList(0, pointsDataLimit).clear();
					gradeList.subList(0, pointsDataLimit).clear();
				}
				if(gradeList.size()>0){
					// 存储
					studentApplyInfoDao.updateList(applyList);;
					scoreGradeWritenDao.saveGradesList(gradeList);//*****
				}	
			} else {
				studentApplyInfoDao.updateList(applyList);
				scoreGradeWritenDao.saveGradesList(gradeList);//*****
			}
			flag="success";
			//笔试名单发布
			themeService.updateStep((String)condition.get("projectId"), 4);//更新主题表的流程
			//公告表修改
			Notice notice=new Notice();
			notice.setWrittenListPublish(1);//笔试名单发布
			notice.setThemeId((String)condition.get("projectId"));
			noticeService.updateNoticeByFieldAndTheme(notice);
		}else{
			flag="false";
		}
		return flag;
	}

    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterWritien>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreEnterWritien> scoreEnterWritiens =scoreEnterWritienDao.selectOnePageByMap(page,map);
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
	 * @param scoreEnterWritien  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterWritien(Page page,ScoreEnterWritien scoreEnterWritien){
 		 List<ScoreEnterWritien> scoreEnterWritiens =scoreEnterWritienDao.selectOnePageByScoreEnterWritien(page,scoreEnterWritien);
	     	if(scoreEnterWritiens!=null&&scoreEnterWritiens.size()>0){
	     		page.setResult(scoreEnterWritiens);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterWritien>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterWritien>
	 */	
     public List<ScoreEnterWritien> selectAllByMap(Map<String,Object> map){
     	return scoreEnterWritienDao.selectAllByMap(map);
     }
     
     @Override
  	public Page selectWritienEnterPageByMap(Page page, Map<String, Object> map) {
  		 List<ScoreEnterWritien> scoreEnterWritiens =scoreEnterWritienDao.selectWritienEnterPageByMap(page, map);
 	 		if(scoreEnterWritiens!=null&&scoreEnterWritiens.size()>0){
 	     		page.setResult(scoreEnterWritiens);
 	     	}else{
 	     		page.setResult(new ArrayList<ScoreEnterWritien>());
 	     	}
 	     	return page;
  	}
      
     @Override
 	public List<ScoreEntersOutVo> selectAllWritien(Map<String, Object> map) {
 		return scoreEnterWritienDao.selectAllWritien(map);
 	}
 
     @Override
 	public List<ScoreEntersOutVo> selectExportByMap(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		return scoreEnterWritienDao.selectExportByMap(map);
 	}

     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterWritien>
	 */	
     public List<ScoreEnterWritien> selectAllByScoreEnterWritien(ScoreEnterWritien scoreEnterWritien){
     
    	 return scoreEnterWritienDao.selectAllByScoreEnterWritien(scoreEnterWritien);
     }
		
     @Override
 	public List<ScoreEntersOutVo> selectListExportByMap(Map<String, Object> map) {
 		// TODO Auto-generated method stub
 		 return scoreEnterWritienDao.selectListExportByMap(map);
 	}
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterWritien
	 */	
     public ScoreEnterWritien selectObjectByMap(Map<String,Object> map){
     
    	 return scoreEnterWritienDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterWritien
	 */	
     public ScoreEnterWritien selectObjectByScoreEnterWritien(ScoreEnterWritien scoreEnterWritien){
     
     	return scoreEnterWritienDao.selectObjectByScoreEnterWritien(scoreEnterWritien);
     }

	@Override
	public Page selectWritienScore(Page page, Map<String, Object> condition) {
		List<ScoreEntersOutVo> scoreEntersOutVos =scoreEnterWritienDao.selectWritienScore(page,condition);
     	if(scoreEntersOutVos!=null&&scoreEntersOutVos.size()>0){
     		page.setResult(scoreEntersOutVos);
     	}else{
     		page.setResult(new ArrayList<ScoreEntersOutVo>());
     	}
     	return page;
	}
	
	@Override
	public List<ScoreEntersOutVo> selectWritienScore(Map<String, Object> condition) {
		return scoreEnterWritienDao.selectWritienScore(condition);
     	
	}

}
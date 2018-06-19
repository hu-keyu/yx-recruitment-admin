package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreEnterNotice;
import org.jypj.dev.entity.ScoreEnterRecord;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.ScoreEnterNoticeDao;
import org.jypj.dev.dao.ScoreEnterRecordDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterNoticeService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

/**
* ScoreEnterNotice业务接口实现层
* 公示表
* @author
*
*/

@Service("scoreEnterNoticeService")
public class ScoreEnterNoticeServiceImpl implements ScoreEnterNoticeService {
	
    @Resource 
    private ScoreEnterNoticeDao scoreEnterNoticeDao;
    @Resource
  	private ThemeService themeService;
    @Resource
  	private NoticeService noticeService;
    @Resource
  	private StudentApplyInfoDao studentApplyInfoDao;
    @Resource
  	private ScoreEnterRecordDao scoreEnterRecordDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterNotice
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterNoticeByField(ScoreEnterNotice scoreEnterNotice){
	
		return scoreEnterNoticeDao.saveScoreEnterNoticeByField(scoreEnterNotice);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterNotice 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterNotice(ScoreEnterNotice scoreEnterNotice){
	
		return scoreEnterNoticeDao.saveScoreEnterNotice(scoreEnterNotice);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterNoticeById(String id){
    
    	return scoreEnterNoticeDao.deleteScoreEnterNoticeById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterNoticeByObject(ScoreEnterNotice scoreEnterNotice){
    
    	return scoreEnterNoticeDao.deleteScoreEnterNoticeByObject(scoreEnterNotice);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterNotice 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterNoticeByField(ScoreEnterNotice scoreEnterNotice){
    
    	return scoreEnterNoticeDao.updateScoreEnterNoticeByField(scoreEnterNotice);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterNotice 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterNotice(ScoreEnterNotice scoreEnterNotice){
    
    	return scoreEnterNoticeDao.updateScoreEnterNotice(scoreEnterNotice);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterNotice 
	 */	
    public ScoreEnterNotice selectScoreEnterNoticeById(String id){
    
    	return scoreEnterNoticeDao.selectScoreEnterNoticeById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterNotice>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreEnterNotice> scoreEnterNotices =scoreEnterNoticeDao.selectOnePageByMap(page,map);
	     	if(scoreEnterNotices!=null&&scoreEnterNotices.size()>0){
	     		page.setResult(scoreEnterNotices);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterNotice>());
	     	}
	     	return page;
     }
     
     @Override
  	 public void enterNoticelist(String flag, String chks, String projectId, String positionid, User user,
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
  		List<ScoreEnterNotice> scoreEnterNoticeList= scoreEnterNoticeDao.selectAllByMap(queryParameter);
  		List<ScoreEnterRecord> scoreEnterRecordList=new ArrayList<ScoreEnterRecord>();
  		if(scoreEnterNoticeList !=null && !scoreEnterNoticeList.isEmpty()){
  			if(StringUtils.isNotEmpty(flag)&&flag.equals("1")){//调整到入围名单中
  				for(ScoreEnterNotice scoreEnterNotice : scoreEnterNoticeList){
  					scoreEnterNotice.setIsEnter("1");//1、入围 2、未入围
  					scoreEnterNotice.setModifyUser(user.getId());
  					//往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterNotice.getItemsId());
					scoreEnterRecord.setIsEnter("2");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterNotice.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterNotice.getStudentId());
					scoreEnterRecord.setType("6");//公示
					scoreEnterRecord.setUpdateReason("将未入围的考生调整到入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
  				}
  				scoreEnterNoticeDao.updateNoticeEnterList(scoreEnterNoticeList);
  				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
  			}else if(StringUtils.isNotEmpty(flag)&&flag.equals("2")){//调整到未入围的名单中
  				for(ScoreEnterNotice scoreEnterNotice : scoreEnterNoticeList){
  					scoreEnterNotice.setIsEnter("2");//1、入围 2、未入围
  					scoreEnterNotice.setModifyUser(user.getId());
  				    //往记录表中添加数据
					ScoreEnterRecord scoreEnterRecord=new ScoreEnterRecord();
					scoreEnterRecord.setCreateUser(user.getId());
					scoreEnterRecord.setEmpItemsId(scoreEnterNotice.getItemsId());
					scoreEnterRecord.setIsEnter("1");//原有状态1、入围 2、未入围
					scoreEnterRecord.setPostId(scoreEnterNotice.getPostId());
					scoreEnterRecord.setStudentId(scoreEnterNotice.getStudentId());
					scoreEnterRecord.setType("6");//公示
					scoreEnterRecord.setUpdateReason("将入围的考生调整到未入围名单中！");
					scoreEnterRecordList.add(scoreEnterRecord);
  				}
  				scoreEnterNoticeDao.updateNoticeEnterList(scoreEnterNoticeList);
  				scoreEnterRecordDao.saveRecordsList(scoreEnterRecordList);
  			}
  		}else{
			jsonMap.put("msg", "入围名单为空，请确认！");
    		throw new Exception("入围名单为空，请确认");
		}	
  		
  	}
     
     /**
      * 批量插入进入公示名单
      * @param list
     */ 
 	@Override
 	public String addBatchNotice(Map<String, Object> condition, Page page, User user) {
 		condition =page.getCondition();
  		condition.put("isEnter", "1");//1、入围
  		List<ScoreEntersOutVo> scoreList =scoreEnterNoticeDao.selectAllNotice(condition);
  		String flag="";
  		List<StudentApplyInfo> applyList =new ArrayList<StudentApplyInfo>();
  		if(scoreList.size()>0){
  			for (ScoreEntersOutVo scoreAll : scoreList) {
  				StudentApplyInfo studentApplyInfo=new StudentApplyInfo();//学生报考信息表
  				studentApplyInfo.setId(scoreAll.getApplyId());
  				studentApplyInfo.setApplyStatus("12");//12、公示名单；
  				studentApplyInfo.setModifyUser(user.getId());
  				applyList.add(studentApplyInfo);
  			}
  		
  			// for 循环入围名单
  			// 批量存储入围名单
  			int pointsDataLimit = 1000;// 限制条数
  			Integer size = applyList.size();
  			if (pointsDataLimit < size) {
  				int part = size / pointsDataLimit;// 分批数
  				for (int i = 0; i < part; i++) {
  					List<StudentApplyInfo> listPageApply = applyList.subList(0, pointsDataLimit);
  					// 存储
  					studentApplyInfoDao.updateList(listPageApply);
  					applyList.subList(0, pointsDataLimit).clear();
  				}
  				if(applyList.size()>0){
  					// 存储
  					studentApplyInfoDao.updateList(applyList);;
  				}
  			} else {
  				studentApplyInfoDao.updateList(applyList);
  			}
  			flag="success";
  			//考察名单发布
  			themeService.updateStep((String)condition.get("projectId"), 12);//更新主题表的流程
  			
  			//公告表修改
 			Notice noticeTwo=new Notice();
 			noticeTwo.setShowListPublish(1);//公示名单发布
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
	 * @param scoreEnterNotice  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterNotice(Page page,ScoreEnterNotice scoreEnterNotice){
 		 List<ScoreEnterNotice> scoreEnterNotices =scoreEnterNoticeDao.selectOnePageByScoreEnterNotice(page,scoreEnterNotice);
	     	if(scoreEnterNotices!=null&&scoreEnterNotices.size()>0){
	     		page.setResult(scoreEnterNotices);
	     	}else{
	     		page.setResult(new ArrayList<ScoreEnterNotice>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterNotice>
	 */	
     public List<ScoreEnterNotice> selectAllByMap(Map<String,Object> map){
     	return scoreEnterNoticeDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterNotice>
	 */	
     public List<ScoreEnterNotice> selectAllByScoreEnterNotice(ScoreEnterNotice scoreEnterNotice){
     
    	 return scoreEnterNoticeDao.selectAllByScoreEnterNotice(scoreEnterNotice);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterNotice
	 */	
     public ScoreEnterNotice selectObjectByMap(Map<String,Object> map){
     
    	 return scoreEnterNoticeDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterNotice
	 */	
     public ScoreEnterNotice selectObjectByScoreEnterNotice(ScoreEnterNotice scoreEnterNotice){
     
     	return scoreEnterNoticeDao.selectObjectByScoreEnterNotice(scoreEnterNotice);
     }

	@Override
	public List<ScoreEntersOutVo> selectExportByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreEnterNoticeDao.selectExportByMap(map);
	}

	@Override
	public List<ScoreEntersOutVo> selectListExportByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreEnterNoticeDao.selectListExportByMap(map);
	}
}
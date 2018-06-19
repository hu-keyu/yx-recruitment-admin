package org.jypj.dev.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jypj.dev.dao.AttachementDao;
import org.jypj.dev.dao.GradeAdjustLogDao;
import org.jypj.dev.dao.GradeExportLogDao;
import org.jypj.dev.dao.ScoreEnterPhysicalDao;
import org.jypj.dev.dao.ScoreEnterStudyDao;
import org.jypj.dev.dao.ScoreEnterTrialDao;
import org.jypj.dev.dao.ScoreEnterWritienDao;
import org.jypj.dev.dao.ScoreGradePhysicalDao;
import org.jypj.dev.dao.ScoreGradeStudyDao;
import org.jypj.dev.dao.ScoreGradeTrialDao;
import org.jypj.dev.dao.ScoreGradeWritenDao;
import org.jypj.dev.dao.StudentInfoDao;
import org.jypj.dev.dao.ThemeDao;
import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.GradeAdjustLog;
import org.jypj.dev.entity.GradeExportLog;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreEnterStudy;
import org.jypj.dev.entity.ScoreGradePhysical;
import org.jypj.dev.entity.ScoreGradeStudy;
import org.jypj.dev.entity.ScoreGradeTrial;
import org.jypj.dev.entity.ScoreGradeWriten;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.ScoreGradePhysicalService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.PropertiesUtil;
import org.jypj.dev.util.StringUtil;
import org.jypj.dev.vo.ScoreEntersOutVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
* ScoreGradePhysical业务接口实现层
* 体检成绩表
* @author
*
*/

@Service("scoreGradePhysicalService")
public class ScoreGradePhysicalServiceImpl implements ScoreGradePhysicalService {
	
    @Resource 
    private ScoreGradePhysicalDao scoreGradePhysicalDao;
    @Resource 
    private ThemeService themeService;
    @Resource
    private ThemeDao themeDao;
    @Resource 
    private StudentInfoDao studentInfoDao;
    @Resource 
    private PositionService positionService ;
    @Resource 
    private ScoreGradeWritenDao scoreGradeWritenDao;
    @Resource 
    private ScoreGradeTrialDao scoreGradeTrialDao;
    @Resource 
    private ScoreGradeStudyDao scoreGradeStudyDao;
    @Resource
    private GradeExportLogDao gradeExportLogDao;
    @Resource
    private AttachementDao attachementDao;
    @Resource
    private ScoreEnterWritienDao scoreEnterWritienDao ;
    @Resource
    private ScoreEnterTrialDao scoreEnterTrialDao;
    @Resource
    private ScoreEnterPhysicalDao scoreEnterPhysicalDao;
    @Resource 
    private ScoreEnterStudyDao scoreEnterStudyDao;
    @Resource
  	private NoticeService noticeService ;
    @Resource
    private GradeAdjustLogDao gradeAdjustLogDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradePhysical
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradePhysicalByField(ScoreGradePhysical scoreGradePhysical){
	
		return scoreGradePhysicalDao.saveScoreGradePhysicalByField(scoreGradePhysical);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradePhysical 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradePhysical(ScoreGradePhysical scoreGradePhysical){
	
		return scoreGradePhysicalDao.saveScoreGradePhysical(scoreGradePhysical);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradePhysicalById(String id){
    
    	return scoreGradePhysicalDao.deleteScoreGradePhysicalById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreGradePhysicalByObject(ScoreGradePhysical scoreGradePhysical){
    
    	return scoreGradePhysicalDao.deleteScoreGradePhysicalByObject(scoreGradePhysical);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradePhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradePhysicalByField(ScoreGradePhysical scoreGradePhysical){
    
    	return scoreGradePhysicalDao.updateScoreGradePhysicalByField(scoreGradePhysical);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradePhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradePhysical(ScoreGradePhysical scoreGradePhysical){
    
    	return scoreGradePhysicalDao.updateScoreGradePhysical(scoreGradePhysical);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradePhysical 
	 */	
    public ScoreGradePhysical selectScoreGradePhysicalById(String id){
    
    	return scoreGradePhysicalDao.selectScoreGradePhysicalById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreGradePhysical> scoreGradePhysicals =scoreGradePhysicalDao.selectOnePageByMap(page,map);
	     	if(scoreGradePhysicals!=null&&scoreGradePhysicals.size()>0){
	     		page.setResult(scoreGradePhysicals);
	     	}else{
	     		page.setResult(new ArrayList<ScoreGradePhysical>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreGradePhysical  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreGradePhysical(Page page,ScoreGradePhysical scoreGradePhysical){
 		 List<ScoreGradePhysical> scoreGradePhysicals =scoreGradePhysicalDao.selectOnePageByScoreGradePhysical(page,scoreGradePhysical);
	     	if(scoreGradePhysicals!=null&&scoreGradePhysicals.size()>0){
	     		page.setResult(scoreGradePhysicals);
	     	}else{
	     		page.setResult(new ArrayList<ScoreGradePhysical>());
	     	}
	     	return page;
     }
    
     @Override
 	 public void publishBatchPhysical(Map<String, Object> condition, Page page, User user, JSONObject jsonMap) throws Exception {
    	 if(StringUtils.isBlank((String)condition.get("projectId"))){
 			jsonMap.put("msg", "招聘项目ID为空，操作失败，请确认！");
 			throw new Exception("招聘项目ID为空，操作失败，请确认！");
 		 }
 		 if(user == null){
 			jsonMap.put("msg", "获取用户数据为空，操作失败，请确认！");
 			throw new Exception("获取用户数据为空，操作失败，请确认！");
 		 }
    	 List<ScoreGradePhysical> enterList=scoreGradePhysicalDao.selectAllByMap(condition);//查询所有名单
    	 if(enterList.size()>0){
				List<ScoreEnterStudy> studyList = new ArrayList<ScoreEnterStudy>();
				for (ScoreGradePhysical physalAll : enterList) {
					ScoreEnterStudy scoreEnterStudy = new ScoreEnterStudy();//考察入围表
					scoreEnterStudy.setSchoolId(physalAll.getSchoolId());
					scoreEnterStudy.setStudentId(physalAll.getStudentId());
					if(StringUtils.isNotBlank(physalAll.getResult())&&physalAll.getResult().equals("1")){
						scoreEnterStudy.setIsEnter("1");//1、入围
					}else if(StringUtils.isNotBlank(physalAll.getResult())&&physalAll.getResult().equals("2")){
						scoreEnterStudy.setIsEnter("2");//2、未入围
					}else{
						jsonMap.put("msg", "体检成绩为空，请确认！");
			    		throw new Exception("体检成绩为空，请确认！");
					}
					scoreEnterStudy.setItemsId(physalAll.getProjectId());
					scoreEnterStudy.setPostId(physalAll.getPositionId());
					scoreEnterStudy.setListPublishStatus("1");//1、名单未发布
					scoreEnterStudy.setScorePublishStatus("2");//2、成绩已发布
					scoreEnterStudy.setType((String)condition.get("testType"));
					scoreEnterStudy.setCreateUser(user.getId());
					studyList.add(scoreEnterStudy);
				}
				// for 循环入围名单
				// 批量存储入围名单
				int pointsDataLimit = 1000;// 限制条数
				Integer size = studyList.size();
				if (pointsDataLimit < size) {
					int part = size / pointsDataLimit;// 分批数
					for (int i = 0; i < part; i++) {
						List<ScoreEnterStudy> listPageStudy = studyList.subList(0, pointsDataLimit);
						// 存储
						scoreEnterStudyDao.saveStudysList(listPageStudy);
						studyList.subList(0, pointsDataLimit).clear();
					}
					if(studyList.size()>0){
						// 存储
						scoreEnterStudyDao.saveStudysList(studyList);
					}
				} else {
					scoreEnterStudyDao.saveStudysList(studyList);
				}
				themeService.updateStep((String)condition.get("projectId"), 9);//体检成绩发布
				
				//公告表修改
				Notice noticeTwo=new Notice();
				noticeTwo.setBodyexamScorePublish(1);//体检成绩发布
				noticeTwo.setThemeId((String)condition.get("projectId"));
				noticeService.updateNoticeByFieldAndTheme(noticeTwo);
			}else{
				jsonMap.put("msg", "体检名单为空，请确认！");
	    		throw new Exception("体检名单为空，请确认！");
			}
 	}
     
     /**
      * 取消发布成绩
      * @param project
      * @param step
      * @return
      */
 	@Override
 	public String celpublishPhysical(String projectId,Integer step) throws Exception {
 		String flag="";
 		int delcount=0;
 		if(StringUtils.isNotBlank(projectId)){
 			 delcount=scoreEnterStudyDao.deleteStudyByProjectId(projectId);//删除发布成绩时创建的数据
 		}else{
 			flag="false";
 			return flag;
 		}
 		if(delcount>0){
 			ScoreEnterStudy scoreEnterStudy = new ScoreEnterStudy();
 			scoreEnterStudy.setItemsId(projectId);
 			List<ScoreEnterStudy> trials=scoreEnterStudyDao.selectAllByScoreEnterStudy(scoreEnterStudy);//查询是否删除完成
 			if (trials.size()==0) {
 				themeService.updateStep(projectId, step);
 				//公告表修改
				Notice noticeTwo=new Notice();
				noticeTwo.setBodyexamScorePublish(2);//取消体检成绩发布
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
     
    @Override
 	public void updatePhysicalList(List<ScoreGradePhysical> gradePhysicalsList,User user, JSONObject jsonMap)  throws Exception {
    	if (gradePhysicalsList.size()==0 || gradePhysicalsList == null) {
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "要修改的数据为空，请稍后重试！");
		} 
    	List<String> idList=new ArrayList<String>();//获取ID的集合
		for (ScoreGradePhysical physicalList : gradePhysicalsList) {
			if(StringUtils.isBlank(physicalList.getId())){
				jsonMap.put("msg", "保存的数据ID不能为空，请稍后再试！");
				throw new Exception("保存的数据ID不能为空，请稍后再试！");
			}
			if(StringUtils.isBlank(physicalList.getResult())){
				jsonMap.put("msg", "体检成绩不能为空，请确认！");
				throw new Exception("体检成绩不能为空，请确认！");
			}
			idList.add(physicalList.getId());
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", idList);
		List<ScoreGradePhysical> oldScore=scoreGradePhysicalDao.selectPhysicalByMap(map);//之前的分数
		List<ScoreGradePhysical> compareScore=this.getDifferScore(oldScore, gradePhysicalsList);//比较后的
		if(compareScore !=null && !compareScore.isEmpty() ){
			for(ScoreGradePhysical physical : compareScore){
				physical.setModifyuser(user.getId());
			}
			this.saveScoreAdjustLog(compareScore, user);
			scoreGradePhysicalDao.updatePhysicalList(compareScore);
		}	
 	}
    
    /**
 	 * 批量保存调整成绩日志表
 	 * @param differGrade
 	 * @param user
 	 */
 	private void saveScoreAdjustLog(List<ScoreGradePhysical> differScore,User user){
 		List<GradeAdjustLog> gradeAdjustLogs=new ArrayList<GradeAdjustLog>();
 		GradeAdjustLog gradeAdjustLog=null;
 		for(ScoreGradePhysical score : differScore){
 			ScoreGradePhysical scorePhysical=scoreGradePhysicalDao.selectScoreGradePhysicalById(score.getId());
 			gradeAdjustLog=new GradeAdjustLog();
 			gradeAdjustLog.setProjectId(scorePhysical.getProjectId());
 			gradeAdjustLog.setPositionId(scorePhysical.getPositionId());
 			gradeAdjustLog.setStudentId(score.getStudentId());
 			gradeAdjustLog.setType("4");//体检
 			gradeAdjustLog.setCreateuser(user.getId());
 			gradeAdjustLog.setModifyuser(user.getId());
 			gradeAdjustLog.setInitGrade(scorePhysical.getResult());//上一次的成绩
 			gradeAdjustLog.setGradeAfter(score.getResult());//新的成绩
 			gradeAdjustLog.setSchoolId(scorePhysical.getSchoolId());
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
 	private List<ScoreGradePhysical> getDifferScore(List<ScoreGradePhysical> scoreOlds,List<ScoreGradePhysical> scoreNews){
 		List<ScoreGradePhysical> data=new ArrayList<ScoreGradePhysical>();
 		for(ScoreGradePhysical scoreOld : scoreOlds){
 			for(ScoreGradePhysical scoreNew : scoreNews){
 				if(scoreOld.getId().equals(scoreNew.getId())&& (!scoreNew.getResult().equals(scoreOld.getResult()))){
 					data.add(scoreNew);
 					break;
 				}
 			}
 		}
 		return data;
 	}
     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public List<ScoreGradePhysical> selectAllByMap(Map<String,Object> map){
     	return scoreGradePhysicalDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public List<ScoreGradePhysical> selectAllByScoreGradePhysical(ScoreGradePhysical scoreGradePhysical){
     
    	 return scoreGradePhysicalDao.selectAllByScoreGradePhysical(scoreGradePhysical);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradePhysical
	 */	
     public ScoreGradePhysical selectObjectByMap(Map<String,Object> map){
     
    	 return scoreGradePhysicalDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradePhysical
	 */	
     public ScoreGradePhysical selectObjectByScoreGradePhysical(ScoreGradePhysical scoreGradePhysical){
     
     	return scoreGradePhysicalDao.selectObjectByScoreGradePhysical(scoreGradePhysical);
     }

	@Override
	public void saveImportScore(CommonsMultipartFile file, String projectId, String testType, User user,
			JSONObject jsonMap) throws Exception {
			this.checkFile(file,jsonMap,testType);
			String originalFilename = file.getOriginalFilename();
			InputStream is=file.getInputStream();
		 	List<Map<String, String>> dataList=ExcelUtils.readExcel(originalFilename, is);
	        Map<String,List<Map<String, String>>> mapData=this.checkData(dataList,jsonMap,projectId,testType);
	        List<Map<String, String>> correctData=mapData.get("correctData");//正确数据
	        List<Map<String, String>> errorData=mapData.get("errorData");//错误数据
	        this.operCorrectData(correctData,projectId,user,testType);
	        this.operErrorData(correctData,errorData,projectId,testType,user,originalFilename);
		
	}
	
	/**
	 * 检查上传的文件
	 * @param file
	 * @param jsonMap
	 */
	private void checkFile(CommonsMultipartFile file,JSONObject jsonMap, String testType){
		if(file == null){
			if(testType.equals("2")){
				jsonMap.put("msg", "请选择导入的统一笔试成绩文件！");
				throw new RuntimeException("请选择导入的统一笔试文件！");
			}else if(testType.equals("3")){
				jsonMap.put("msg", "请选择导入的统一试讲成绩文件！");
				throw new RuntimeException("请选择导入的统一试讲成绩文件！");
			}else if(testType.equals("4")){
				jsonMap.put("msg", "请选择导入的体检成绩文件！");
				throw new RuntimeException("请选择导入的体检成绩文件！");
			}else if(testType.equals("5")){
				jsonMap.put("msg", "请选择导入的考察成绩文件！");
				throw new RuntimeException("请选择导入的考察成绩文件！");
			}
		}
		List<String> prefixList=Arrays.asList(new String []{"xls","xlsx"});
		String originalFilename = file.getOriginalFilename();
		String fileName=originalFilename.substring(0, originalFilename.lastIndexOf("."));
		if(StringUtil.length(fileName) > 40){
			jsonMap.put("msg", "输入的文件名过长，最多输入20个汉字或40个字符，请重试！");
			throw new RuntimeException("输入的文件名过长，最多输入20个汉字或40个字符，请重试！");
		}
		String prefix=ExcelUtils.getPostfix(originalFilename);
		if(!prefixList.contains(prefix)){
			jsonMap.put("msg", "请选择Excel文件导入！");
			throw new RuntimeException("请选择Excel文件导入！");
		}
	}
	
	/**
	 * 检查解析后的数据
	 * @param dataList
	 * @return
	*/
	private Map<String,List<Map<String, String>>> checkData(List<Map<String, String>> dataList,
			JSONObject jsonMap,String projectId, String testType){
		if(dataList == null || dataList.isEmpty()){
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "上传的模板有空数据，请确认后重新上传！");
			throw new RuntimeException("上传的模板有空数据，请确认后重新上传！");
		}
		Map<String,List<Map<String, String>>> mapData=new HashMap<String,List<Map<String, String>>>();
		List<Map<String, String>> items=new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorItems=new ArrayList<Map<String, String>>();
		try{
			List<ScoreEntersOutVo> evoList= new ArrayList<ScoreEntersOutVo>();
			ScoreEntersOutVo entersOutVo=new ScoreEntersOutVo();
			entersOutVo.setProjectId(projectId);
			entersOutVo.setIsPass("1");
			if(testType.equals("2")){//统一笔试
				evoList=scoreEnterWritienDao.selectByStuCondition(entersOutVo);//查询所有名单
			}else if(testType.equals("3")){//统一试讲
				evoList=scoreEnterTrialDao.selectByStuCondition(entersOutVo);
			}else if(testType.equals("4")){//体检
				evoList=scoreEnterPhysicalDao.selectByStuCondition(entersOutVo);
			}else if(testType.equals("5")){//考察
				evoList=scoreEnterStudyDao.selectByStuCondition(entersOutVo);
			}
			Map<String,ScoreEntersOutVo> scoreMap=new HashMap<String,ScoreEntersOutVo>();
			for (ScoreEntersOutVo scoreEntersOutVo : evoList) {
				scoreMap.put(scoreEntersOutVo.getTicketNum(), scoreEntersOutVo);
			}
			if(scoreMap!=null&&scoreMap.size()>0){
				for(Map<String, String> map : dataList){
					this.checkData(map,jsonMap);
					String ticketNum=map.get("准考证号");
					String name=map.get("姓名");
					String grade="";
					if(testType.equals("2")){
						grade=map.get("成绩");
						//成绩
						if(StringUtils.isBlank(grade)){
							map.put("错误信息", "成绩为空！");
							errorItems.add(map);
							continue;
						}
						if(!grade.matches("^\\d{1,2}(\\.\\d{1,2})|\\d{1,3}(\\.\\d{1})?$")){
							map.put("错误信息", "成绩输入有误，请输入1到3位整数或两位小数！");
							errorItems.add(map);
							continue;
						}
					}else if(testType.equals("3")){
						grade=map.get("成绩");
						//成绩
						if(StringUtils.isBlank(grade)){
							map.put("错误信息", "成绩为空！");
							errorItems.add(map);
							continue;
						}
						if(!grade.matches("^\\d{1,2}(\\.\\d{1,2})|\\d{1,3}(\\.\\d{1})?$")){
							map.put("错误信息", "成绩输入有误，请输入1到3位整数或两位小数！");
							errorItems.add(map);
							continue;
						}
					}else if(testType.equals("4")){
						grade=map.get("是否通过");
						//成绩
						if(StringUtils.isBlank(grade)){
							map.put("错误信息", "是否通过为空！");
							errorItems.add(map);
							continue;
						} 
						if(StringUtils.isNotBlank(grade)){
							if(!(grade.equalsIgnoreCase("是")||grade.equalsIgnoreCase("否"))){
								map.put("错误信息", "是否通过只能填写是或否！");
								errorItems.add(map);
								continue;
							}
						}	
					}else if(testType.equals("5")){
						grade=map.get("是否通过");
						//成绩
						if(StringUtils.isBlank(grade)){
							map.put("错误信息", "是否通过为空！");
							errorItems.add(map);
							continue;
						}
						if(StringUtils.isNotBlank(grade)){
							if(!(grade.equalsIgnoreCase("是")||grade.equalsIgnoreCase("否"))){
								map.put("错误信息", "是否通过只能填写是或否！");
								errorItems.add(map);
								continue;
							}
						}
					}
					String post=map.get("岗位名称");
					//准考证号
					if(StringUtils.isBlank(ticketNum)){
						map.put("错误信息", "准考证号为空！");
						errorItems.add(map);
						continue;
					}
					//姓名
					if(map.containsKey("姓名") &&StringUtils.isBlank(name)){
						map.put("错误信息", "姓名为空！");
						errorItems.add(map);
						continue;
					}		
					//岗位名称
					if(map.containsKey("岗位名称") && StringUtils.isBlank(post)){
						map.put("错误信息", "岗位名称为空！");
						errorItems.add(map);
						continue;
					}
					ScoreEntersOutVo entersOutVoKey=new ScoreEntersOutVo();
					//根据各考试类型入围名单查询学生的准考证号和姓名是否存在
						if(scoreMap.containsKey(ticketNum)){
							entersOutVoKey=scoreMap.get(ticketNum);//查询出来的对象
							if(map.containsKey("姓名")&&map.containsKey("岗位名称")){//姓名和岗位都存在
								if(entersOutVoKey.getStudentName().equalsIgnoreCase(name)&&!entersOutVoKey.getPostName().equalsIgnoreCase(post)){
									map.put("错误信息", "岗位名称不正确！");
									errorItems.add(map);
									continue;
								}else if(!entersOutVoKey.getStudentName().equalsIgnoreCase(name)&&entersOutVoKey.getPostName().equalsIgnoreCase(post)){
									map.put("错误信息", "姓名不正确！");
									errorItems.add(map);
									continue;
								}else if(!entersOutVoKey.getStudentName().equalsIgnoreCase(name)&&!entersOutVoKey.getPostName().equalsIgnoreCase(post)){
									map.put("错误信息", "姓名或岗位名称不正确！");
									errorItems.add(map);
									continue;
								}
								
							}else if(map.containsKey("姓名")&&!map.containsKey("岗位名称")){//姓名存在
								if(!entersOutVoKey.getStudentName().equalsIgnoreCase(name)){
									map.put("错误信息", "姓名不正确！");
									errorItems.add(map);
									continue;
								}
							}else if(entersOutVoKey.getPostName().equalsIgnoreCase(post)){//岗位存在
								if(!entersOutVoKey.getPostName().equalsIgnoreCase(post)){
									map.put("错误信息", "岗位名称不正确！");
									errorItems.add(map);
									continue;
								}
							}
						}else{
							map.put("错误信息", "准考证号不正确！");
							errorItems.add(map);
							continue;
						}
							
					items.add(map);
				}
			}
			
		}catch(Exception e){
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "查询对象异常！");
		}
		mapData.put("correctData", items);
		mapData.put("errorData", errorItems);
		return mapData;
	}
	
	
	/*private Map<String,List<Map<String, String>>> checkData(List<Map<String, String>> dataList,
			JSONObject jsonMap,String projectId, String testType){
		if(dataList == null || dataList.isEmpty()){
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "上传的面试模板有空数据，请确认后重新上传！");
			throw new RuntimeException("上传的面试模板有空数据，请确认后重新上传！");
		}
		Map<String,List<Map<String, String>>> mapData=new HashMap<String,List<Map<String, String>>>();
		List<Map<String, String>> items=new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorItems=new ArrayList<Map<String, String>>();
		try{
			for(Map<String, String> map : dataList){
				this.checkData(map,jsonMap);
				String ticketNum=map.get("准考证号");
				String name=map.get("姓名");
				String grade="";
				if(testType.equals("2")){
					grade=map.get("成绩");
					//成绩
					if(StringUtils.isBlank(grade)){
						map.put("错误信息", "成绩为空！");
						errorItems.add(map);
						continue;
					}
					if(!grade.matches("^\\d{1,2}(\\.\\d{1,2})|\\d{1,3}?$")){
						map.put("错误信息", "成绩输入有误，请输入1到3位整数或两位小数！");
						errorItems.add(map);
						continue;
					}
				}else if(testType.equals("3")){
					grade=map.get("成绩");
					//成绩
					if(StringUtils.isBlank(grade)){
						map.put("错误信息", "成绩为空！");
						errorItems.add(map);
						continue;
					}
					if(!grade.matches("^\\d{1,2}(\\.\\d{1,2})|\\d{1,3}?$")){
						map.put("错误信息", "成绩输入有误，请输入1到3位整数或两位小数！");
						errorItems.add(map);
						continue;
					}
				}else if(testType.equals("4")){
					grade=map.get("是否通过");
					//成绩
					if(StringUtils.isBlank(grade)){
						map.put("错误信息", "是否通过为空！");
						errorItems.add(map);
						continue;
					}
				}else if(testType.equals("5")){
					grade=map.get("是否通过");
					//成绩
					if(StringUtils.isBlank(grade)){
						map.put("错误信息", "是否通过为空！");
						errorItems.add(map);
						continue;
					}
				}
				String post=map.get("岗位名称");
				//准考证号
				if(StringUtils.isBlank(ticketNum)){
					map.put("错误信息", "准考证号为空！");
					errorItems.add(map);
					continue;
				}
				//姓名
				if(map.containsKey("姓名") &&StringUtils.isBlank(name)){
					map.put("错误信息", "姓名为空！");
					errorItems.add(map);
					continue;
				}		
				//岗位名称
				if(map.containsKey("岗位名称") && StringUtils.isBlank(post)){
					map.put("错误信息", "岗位名称为空！");
					errorItems.add(map);
					continue;
				}
				ScoreEntersOutVo entersOutVo=new ScoreEntersOutVo();
				entersOutVo.setProjectId(projectId);
				entersOutVo.setTicketNum(ticketNum);
				entersOutVo.setIsPass("1");//1、入围
				if(map.containsKey("姓名")){
					entersOutVo.setStudentName(name);
				}
				ScoreEntersOutVo enterInfo=null;
				//根据各考试类型入围名单查询学生的准考证号和姓名是否存在
				if(testType.equals("2")){//统一笔试
					enterInfo=scoreEnterWritienDao.selectByStu(entersOutVo);
				}else if(testType.equals("3")){//统一试讲
					Notice notice=noticeService.selectObjectByThemeId(projectId);
					Integer art=notice.getLectureScorePublishArt();//艺术科
					Integer common=notice.getLectureScorePublishNor();//普通科
					if(art==1&&common==1){//成绩都发布了
						enterInfo=null;
					}else if(art!=1&&common!=1){
						entersOutVo.setSex(null);//查所有
						enterInfo=scoreEnterTrialDao.selectByStu(entersOutVo);
					}else if(art!=1&&common==1){//艺术科未发布
						entersOutVo.setSex("1");//查艺术科
						enterInfo=scoreEnterTrialDao.selectByStu(entersOutVo);
					}else if(art==1&&common!=1){//普通科未发布
						entersOutVo.setSex("0");//查普通科
					}
					enterInfo=scoreEnterTrialDao.selectByStu(entersOutVo);
				}else if(testType.equals("4")){//体检
					enterInfo=scoreEnterPhysicalDao.selectByStu(entersOutVo);
				}else if(testType.equals("5")){//考察
					enterInfo=scoreEnterStudyDao.selectByStu(entersOutVo);
				}
				if(enterInfo == null ){
					map.put("错误信息", "准考证号或姓名不正确！");
					errorItems.add(map);
					continue;
				}
				
				//判断岗位名称是否存在学校设置的岗位里
				if(map.containsKey("岗位名称")){
					Position postInfo=positionService.selectProPosName(projectId, post);
					if(postInfo==null){
						map.put("错误信息", "岗位名称不正确！");
						errorItems.add(map);
						continue;
					}
				}
				items.add(map);
			}
		}catch(Exception e){
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "查询对象异常！");
		}
		mapData.put("correctData", items);
		mapData.put("errorData", errorItems);
		return mapData;
	}*/
	
	/**
	 * 检查上传后的数据
	 * @param map
	 * @param jsonMap
	 */
	private void checkData(Map<String, String> map,JSONObject jsonMap){
		if(!map.containsKey("准考证号")){
			jsonMap.put("msg", "上传的模板不正确，请重新上传！");
			throw new RuntimeException("上传的模板不正确，请重新上传！");
		}
		if(!map.containsKey("成绩")&& !map.containsKey("是否通过")){
			jsonMap.put("msg", "上传的模板不正确，请重新上传！");
			throw new RuntimeException("上传的模板不正确，请重新上传！");
		}
		
		/*if(!map.containsKey("姓名")){
			jsonMap.put("msg", "上传的面试模板不正确，请重新上传！");
			throw new RuntimeException("上传的面试模板不正确，请重新上传！");
		}
		if(!map.containsKey("岗位名称")){
			jsonMap.put("msg", "上传的面试模板不正确，请重新上传！");
			throw new RuntimeException("上传的面试模板不正确，请重新上传！");
		}*/
	}
	
	/**
	 * 保存正确数据
	 * @param dataList
	 */
	private void operCorrectData(List<Map<String, String>> dataList,String projectId,User user,String testType){
		if(dataList != null && !dataList.isEmpty()){
			if(testType.equals("2")){
				List<ScoreGradeWriten> writenList=new ArrayList<ScoreGradeWriten>();
				ScoreGradeWriten scoreGradeWriten=null;
				for(Map<String, String> map : dataList){
					String ticketNum=map.get("准考证号");
					String name=map.get("姓名");
					String score=map.get("成绩");
					String post=map.get("岗位名称");
					scoreGradeWriten=new ScoreGradeWriten(score, projectId, name, ticketNum , post, user.getId());
					if(map.containsKey("姓名")){
						scoreGradeWriten.setName(name);;
					}
					if(map.containsKey("岗位名称")){
						scoreGradeWriten.setPostName(post);
					}
					writenList.add(scoreGradeWriten);
				}
				scoreGradeWritenDao.updateWritenBatchs(writenList);
			}else if(testType.equals("3")){
				List<ScoreGradeTrial> trialList=new ArrayList<ScoreGradeTrial>();
				ScoreGradeTrial scoreGradeTrial=null;
				for(Map<String, String> map : dataList){
					String ticketNum=map.get("准考证号");
					String name=map.get("姓名");
					String score=map.get("成绩");
					String post=map.get("岗位名称");
					scoreGradeTrial=new ScoreGradeTrial(score, projectId, name, ticketNum , post, user.getId());
					if(map.containsKey("姓名")){
						scoreGradeTrial.setName(name);;
					}
					if(map.containsKey("岗位名称")){
						scoreGradeTrial.setPostName(post);
					}
					trialList.add(scoreGradeTrial);
				}
				scoreGradeTrialDao.updateTrialBatchs(trialList);
			}else if(testType.equals("4")){
				List<ScoreGradePhysical> physicalList=new ArrayList<ScoreGradePhysical>();
				ScoreGradePhysical scoreGradePhysical=null;
				for(Map<String, String> map : dataList){
					String ticketNum=map.get("准考证号");
					String name=map.get("姓名");
					String score=map.get("是否通过");
					if(score.equals("是")){
						score="1";//通过
					}else{
						score="2";//不通过
					}
					String post=map.get("岗位名称");
					scoreGradePhysical=new ScoreGradePhysical(score, projectId, name, ticketNum , post, user.getId());
					if(map.containsKey("姓名")){
						scoreGradePhysical.setName(name);;
					}
					if(map.containsKey("岗位名称")){
						scoreGradePhysical.setPostName(post);
					}
					physicalList.add(scoreGradePhysical);
				}
				scoreGradePhysicalDao.updatePhysicalBatchs(physicalList);
			}else if(testType.equals("5")){
				List<ScoreGradeStudy> studyList=new ArrayList<ScoreGradeStudy>();
				ScoreGradeStudy scoreGradeStudy=null;
				for(Map<String, String> map : dataList){
					String ticketNum=map.get("准考证号");
					String name=map.get("姓名");
					String score=map.get("是否通过");
					if(score.equals("是")){
						score="1";//通过
					}else{
						score="2";//不通过
					}
					String post=map.get("岗位名称");
					scoreGradeStudy=new ScoreGradeStudy(score, projectId, name, ticketNum , post, user.getId());
					if(map.containsKey("姓名")){
						scoreGradeStudy.setName(name);;
					}
					if(map.containsKey("岗位名称")){
						scoreGradeStudy.setPostName(post);
					}
					studyList.add(scoreGradeStudy);
				}
				scoreGradeStudyDao.updateStudyBatchs(studyList);
			}
		}
	}
	
	/**
	 * 操作错误数据
	 * @param correctData 正确数据集合
	 * @param dataList 错误数据集合
	 * @param projectId 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	private void operErrorData(List<Map<String, String>> correctData,
			List<Map<String, String>> dataList,String projectId,String testType,User user,String originalFilename) throws IOException{
		GradeExportLog gradeLog=new GradeExportLog();
		gradeLog.setType(testType);//类型（1单位面试 2统一笔试 3统一试讲 4体检 5考察 6公式）
		gradeLog.setProjectId(projectId);
		String maxNum=gradeExportLogDao.maxOrderNumberEducation(gradeLog);//查询最大序列号
		Integer index=1;//序号
		if(StringUtils.isNotBlank(maxNum) && !"null".equals(maxNum)){
			index=Integer.valueOf(maxNum);
			index++;
		}
		String attachementId=null;
		GradeExportLog gradeExportLog=new GradeExportLog();//导入日志记录
		gradeExportLog.setProjectId(projectId);
		gradeExportLog.setType(testType);
		/*if(testType.equals("2")){
			gradeExportLog.setFileName("统一笔试成绩导入模板11111.xls");
		}else if(testType.equals("3")){
			gradeExportLog.setFileName("统一试讲成绩导入模板.xls");
		}else if(testType.equals("4")){
			gradeExportLog.setFileName("体检成绩导入模板.xls");
		}else if(testType.equals("5")){
			gradeExportLog.setFileName("考察成绩导入模板.xls");
		}*/
		gradeExportLog.setFileName(originalFilename);
		gradeExportLog.setOrderNumber(new BigDecimal(index));
		gradeExportLog.setModifyuser(user.getId());
		gradeExportLog.setCreateuser(user.getId());
		
		String status="";//导入状态
		String result="";//导入结果
		if(dataList !=null && !dataList.isEmpty() ){//错误数据不空
			String relativePath="";
			if(testType.equals("2")){
				relativePath=this.createErrorFile("统一笔试",projectId,testType,dataList);//生成错误文件
			}else if(testType.equals("3")){
				relativePath=this.createErrorFile("统一试讲",projectId,testType,dataList);//生成错误文件
			}else if(testType.equals("4")){
				relativePath=this.createErrorFile("体检",projectId,testType,dataList);//生成错误文件
			}else if(testType.equals("5")){
				relativePath=this.createErrorFile("考察",projectId,testType,dataList);//生成错误文件
			}
			attachementId=saveErrorFile(relativePath, projectId,testType, user);//保存错误文件附件
			if(correctData !=null && !correctData.isEmpty()){
				//错误数据不空 且 正确数据不空
				result="更新成绩信息"+correctData.size()+" 条记录。有"+dataList.size()+"条错误记录。请下载错误文件，重新导入。";
				status="导入结束";
			}else{
				//错误数据不空 且 正确数据为空
				result="录入的数据全部有误，请下载错误文件，重新导入。";
				status="导入出错";
			}
			gradeExportLog.setRemark(attachementId);
		}else{//错误数据空
			if(correctData !=null && !correctData.isEmpty()){
				//错误数据为空 且 正确数据不空
				result="更新成绩信息"+correctData.size()+" 条记录。";
				status="导入结束";
			}else{
				//错误数据为空 且 正确数据也为空
				result="导入的模板有误，请下载模板后再导入。";
				status="导入出错";
			}
		}
		gradeExportLog.setStatus(status);
		gradeExportLog.setResult(result);
		gradeExportLogDao.saveGradeExportLog(gradeExportLog);
	}
	
	/**
	 * 生成错误文件
	 * @param linkName 学校面试、统一笔试、统一试讲...
	 * @param projectId
	 * @throws IOException 
	 */
	private String createErrorFile(String linkName,String projectId,String testType,List<Map<String, String>> dataList) throws IOException{
		Theme theme=themeDao.selectThemeById(projectId);
		PropertiesUtil properties = new PropertiesUtil("properties/systemConfig.properties");
		String filePath=properties.getValue("error.file.path");
		String date=new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		String themeName=theme.getTheme();
		String relativePath=File.separator+date+File.separator+themeName+File.separator+linkName;
		File file=new File(filePath+relativePath);
		if(!file.exists()){
			file.mkdirs();
		}
		String guid=UUID.randomUUID().toString().replaceAll("-", "");
		relativePath=relativePath+File.separator+guid+".xls";
		//System.out.println("relativePath="+relativePath);
		
		OutputStream out = new FileOutputStream(filePath+relativePath);
		
		String title=linkName+"成绩信息";
		String sheetName=linkName+"成绩导入错误文件";
		HSSFWorkbook wb = new HSSFWorkbook();//声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);//生成一个表格
		String[] headers = new String []{ "准考证号", "姓名","岗位名称","成绩","错误信息"};
		for(Map<String, String> map : dataList){
			if(map.containsKey("姓名")&&map.containsKey("岗位名称")){
				if(testType.equals("2")){
					headers = new String []{ "准考证号","姓名","岗位名称" ,"成绩","错误信息"};
				}else if(testType.equals("3")){
					headers = new String []{ "准考证号","姓名","岗位名称" ,"成绩","错误信息"};
				}else if(testType.equals("4")){
					headers = new String []{ "准考证号","姓名","岗位名称","是否通过","错误信息"};
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 3, 3);
				}else if(testType.equals("5")){
					headers = new String []{ "准考证号","姓名","岗位名称","是否通过","错误信息"};
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 3, 3);
				}
			}else if(!map.containsKey("姓名")&&!map.containsKey("岗位名称")){
				if(testType.equals("2")){
					headers = new String []{ "准考证号","成绩","错误信息"};
				}else if(testType.equals("3")){
					headers = new String []{ "准考证号","成绩","错误信息"};
				}else if(testType.equals("4")){
					headers = new String []{ "准考证号","是否通过","错误信息"};
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 1, 1);
				}else if(testType.equals("5")){
					headers = new String []{ "准考证号","是否通过","错误信息"};
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 1, 1);
				}
			}else if(map.containsKey("姓名")&&!map.containsKey("岗位名称")){
				if(testType.equals("2")){
					headers = new String []{ "准考证号","姓名","成绩","错误信息"};
				}else if(testType.equals("3")){
					headers = new String []{ "准考证号","姓名","成绩","错误信息"};
				}else if(testType.equals("4")){
					headers = new String []{ "准考证号","姓名","是否通过","错误信息"};
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 2, 2);
				}else if(testType.equals("5")){
					headers = new String []{ "准考证号","姓名","是否通过","错误信息"};
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 2, 2);
				}
			}else if(!map.containsKey("姓名")&&map.containsKey("岗位名称")){
				if(testType.equals("2")){
					headers = new String []{ "准考证号","岗位名称" ,"成绩","错误信息"};
				}else if(testType.equals("3")){
					headers = new String []{ "准考证号","岗位名称" ,"成绩","错误信息"};
				}else if(testType.equals("4")){
					headers = new String []{ "准考证号","岗位名称","是否通过","错误信息"};
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 2, 2);
				}else if(testType.equals("5")){
					headers = new String []{ "准考证号","岗位名称","是否通过","错误信息"};
					String[] textlist=new String[]{"是","否"};
					sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 2, 2);
				}
			}
			break;
		}
		HSSFRow row=ExcelUtils.setHSSFHeader(wb, sheet, title, headers);
        HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
        for(int i=0;i<dataList.size();i++){
        	Map<String, String> mapData=dataList.get(i);
        	row = sheet.createRow(i+2);
        	//System.out.println(mapData.containsKey("姓名"));
        	int cellIndex=0;
        	HSSFCell hc1 = row.createCell(cellIndex++);
        	hc1.setCellStyle(contentStyle);
    		HSSFRichTextString ticketNum = new HSSFRichTextString(mapData != null ? mapData.get("准考证号") : "");
    		hc1.setCellValue(ticketNum);
    		
    		if(testType.equals("2")){
    			if(mapData.containsKey("姓名")&&mapData.containsKey("岗位名称")){
    	    		HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    	    		hc2.setCellValue(studentName);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("岗位名称") : "");
    	    		hc3.setCellValue(offer);
    	    
		    		HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
		    		hc4.setCellValue(grade);
		    		
		    		HSSFCell hc5 = row.createCell(cellIndex++);
		    		hc5.setCellStyle(contentStyle);
		    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
		    		hc5.setCellValue(errorMsg);
		    		
        		}else if(!mapData.containsKey("姓名")&&!mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
		    		hc2.setCellStyle(contentStyle);
		    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
		    		hc2.setCellValue(grade);
		    		
		    		HSSFCell hc3 = row.createCell(cellIndex++);
		    		hc3.setCellStyle(contentStyle);
		    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
		    		hc3.setCellValue(errorMsg);
		    		
        		}else if(mapData.containsKey("姓名")&&!mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    	    		hc2.setCellValue(studentName);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
		    		hc3.setCellStyle(contentStyle);
		    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
		    		hc3.setCellValue(grade);
		    		
		    		HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
		    		hc4.setCellValue(errorMsg);
        		}else if(!mapData.containsKey("姓名")&&mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("岗位名称") : "");
    	    		hc2.setCellValue(offer);
    	    
		    		HSSFCell hc3 = row.createCell(cellIndex++);
		    		hc3.setCellStyle(contentStyle);
		    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
		    		hc3.setCellValue(grade);
		    		
		    		HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
		    		hc4.setCellValue(errorMsg);
        		}
			}else if(testType.equals("3")){
				if(mapData.containsKey("姓名")&&mapData.containsKey("岗位名称")){
    	    		HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    	    		hc2.setCellValue(studentName);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("岗位名称") : "");
    	    		hc3.setCellValue(offer);
    	    
		    		HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
		    		hc4.setCellValue(grade);
		    		
		    		HSSFCell hc5 = row.createCell(cellIndex++);
		    		hc5.setCellStyle(contentStyle);
		    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
		    		hc5.setCellValue(errorMsg);
		    		
        		}else if(!mapData.containsKey("姓名")&&!mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
		    		hc2.setCellStyle(contentStyle);
		    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
		    		hc2.setCellValue(grade);
		    		
		    		HSSFCell hc3 = row.createCell(cellIndex++);
		    		hc3.setCellStyle(contentStyle);
		    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
		    		hc3.setCellValue(errorMsg);
		    		
        		}else if(mapData.containsKey("姓名")&&!mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    	    		hc2.setCellValue(studentName);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
		    		hc3.setCellStyle(contentStyle);
		    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
		    		hc3.setCellValue(grade);
		    		
		    		HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
		    		hc4.setCellValue(errorMsg);
        		}else if(!mapData.containsKey("姓名")&&mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("岗位名称") : "");
    	    		hc2.setCellValue(offer);
    	    
		    		HSSFCell hc3 = row.createCell(cellIndex++);
		    		hc3.setCellStyle(contentStyle);
		    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
		    		hc3.setCellValue(grade);
		    		
		    		HSSFCell hc4 = row.createCell(cellIndex++);
		    		hc4.setCellStyle(contentStyle);
		    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
		    		hc4.setCellValue(errorMsg);
        		}			
			}else if(testType.equals("4")){
				if(mapData.containsKey("姓名")&&mapData.containsKey("岗位名称")){
    	    		HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    	    		hc2.setCellValue(studentName);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("岗位名称") : "");
    	    		hc3.setCellValue(offer);
    	    
    	    		HSSFCell hc4 = row.createCell(cellIndex++);
    	    		hc4.setCellStyle(contentStyle);
    	    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("是否通过") : "");
    	    		hc4.setCellValue(grade);
    	    		
    	    		HSSFCell hc5 = row.createCell(cellIndex++);
    	    		hc5.setCellStyle(contentStyle);
    	    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    	    		hc5.setCellValue(errorMsg);
		    		
        		}else if(!mapData.containsKey("姓名")&&!mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("是否通过") : "");
    	    		hc2.setCellValue(grade);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    	    		hc3.setCellValue(errorMsg);
		    		
        		}else if(mapData.containsKey("姓名")&&!mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    	    		hc2.setCellValue(studentName);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("是否通过") : "");
    	    		hc3.setCellValue(grade);
    	    		
    	    		HSSFCell hc4 = row.createCell(cellIndex++);
    	    		hc4.setCellStyle(contentStyle);
    	    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    	    		hc4.setCellValue(errorMsg);
        		}else if(!mapData.containsKey("姓名")&&mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("岗位名称") : "");
    	    		hc2.setCellValue(offer);
    	    
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("是否通过") : "");
    	    		hc3.setCellValue(grade);
    	    		
    	    		HSSFCell hc4 = row.createCell(cellIndex++);
    	    		hc4.setCellStyle(contentStyle);
    	    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    	    		hc4.setCellValue(errorMsg);
        		}
				
			}else if(testType.equals("5")){
				if(mapData.containsKey("姓名")&&mapData.containsKey("岗位名称")){
    	    		HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    	    		hc2.setCellValue(studentName);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("岗位名称") : "");
    	    		hc3.setCellValue(offer);
    	    
    	    		HSSFCell hc4 = row.createCell(cellIndex++);
    	    		hc4.setCellStyle(contentStyle);
    	    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("是否通过") : "");
    	    		hc4.setCellValue(grade);
    	    		
    	    		HSSFCell hc5 = row.createCell(cellIndex++);
    	    		hc5.setCellStyle(contentStyle);
    	    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    	    		hc5.setCellValue(errorMsg);
		    		
        		}else if(!mapData.containsKey("姓名")&&!mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("是否通过") : "");
    	    		hc2.setCellValue(grade);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    	    		hc3.setCellValue(errorMsg);
		    		
        		}else if(mapData.containsKey("姓名")&&!mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    	    		hc2.setCellValue(studentName);
    	    		
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("是否通过") : "");
    	    		hc3.setCellValue(grade);
    	    		
    	    		HSSFCell hc4 = row.createCell(cellIndex++);
    	    		hc4.setCellStyle(contentStyle);
    	    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    	    		hc4.setCellValue(errorMsg);
        		}else if(!mapData.containsKey("姓名")&&mapData.containsKey("岗位名称")){
        			HSSFCell hc2 = row.createCell(cellIndex++);
    	    		hc2.setCellStyle(contentStyle);
    	    		HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("岗位名称") : "");
    	    		hc2.setCellValue(offer);
    	    
    	    		HSSFCell hc3 = row.createCell(cellIndex++);
    	    		hc3.setCellStyle(contentStyle);
    	    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("是否通过") : "");
    	    		hc3.setCellValue(grade);
    	    		
    	    		HSSFCell hc4 = row.createCell(cellIndex++);
    	    		hc4.setCellStyle(contentStyle);
    	    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    	    		hc4.setCellValue(errorMsg);
        		}
			}
    		
        }
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return relativePath;
	}
	
	/**
	 * 保存错误文件附件
	 * @param relativePath
	 * @param user
	 */
	private String saveErrorFile(String relativePath,String projectId,String testType,User user){
		Attachement attachement=new Attachement();
		attachement.setEmployItemsId(projectId);
		attachement.setPath(relativePath);
		if(testType.equals("2")){
			attachement.setRealName("统一笔试导入错误文件.xls");
		}else if(testType.equals("3")){
			attachement.setRealName("统一试讲导入错误文件.xls");
		}else if(testType.equals("4")){
			attachement.setRealName("体检导入错误文件.xls");
		}else if(testType.equals("5")){
			attachement.setRealName("考察导入错误文件.xls");
		}
		attachement.setFileType(".xls");
		attachement.setCreateUser(user.getId());
		attachement.setModifyUser(user.getId());
		attachement.setModuleType(testType);//模块类型（导入成绩时专用字段 1学校面试 2统一笔试 3统一试讲 4... ）
		attachementDao.saveAttachementByField(attachement);
		return attachement.getId();
	}

    @Override
    public ScoreGradePhysical selectPhysicalResult(StudentApplyInfo sai) {
        // 查询体检结果
        ScoreGradePhysical sgp = new ScoreGradePhysical();
        sgp.setStudentId(sai.getStudentId());
        sgp.setProjectId(sai.getEmployItemsId());
        sgp.setPositionId(sai.getApplyJobId());
        sgp.setSchoolId(sai.getApplyDepId());
        return scoreGradePhysicalDao.selectObjectByScoreGradePhysical(sgp);
    }
}
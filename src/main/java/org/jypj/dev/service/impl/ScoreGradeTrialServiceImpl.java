package org.jypj.dev.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import org.jypj.dev.entity.GradeAdjustLog;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.ScoreEnterPhysical;
import org.jypj.dev.entity.ScoreGradeTrial;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.GradeAdjustLogDao;
import org.jypj.dev.dao.NoticeDao;
import org.jypj.dev.dao.PositionDao;
import org.jypj.dev.dao.PostsetDao;
import org.jypj.dev.dao.ScoreEnterPhysicalDao;
import org.jypj.dev.dao.ScoreGradeTrialDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreGradeTrialService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreGradeVo;
import org.jypj.dev.vo.ScorePostNumVo;

/**
* ScoreGradeTrial业务接口实现层
* 统一试讲成绩表
* @author
*
*/

@Service("scoreGradeTrialService")
public class ScoreGradeTrialServiceImpl implements ScoreGradeTrialService {
	
    @Resource 
    private ScoreGradeTrialDao scoreGradeTrialDao;
    @Resource 
    private NoticeDao noticeDao;
    @Resource 
    private PostsetDao postsetDao;
    @Resource 
    private ThemeService themeService;
    @Resource 
    private ScoreEnterPhysicalDao scoreEnterPhysicalDao;
    @Resource
  	private NoticeService noticeService ;
    @Resource
    private GradeAdjustLogDao gradeAdjustLogDao;
    @Resource 
    private PositionDao positionDao;
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeTrial
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeTrialByField(ScoreGradeTrial scoreGradeTrial){
	
		return scoreGradeTrialDao.saveScoreGradeTrialByField(scoreGradeTrial);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeTrial 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeTrial(ScoreGradeTrial scoreGradeTrial){
	
		return scoreGradeTrialDao.saveScoreGradeTrial(scoreGradeTrial);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeTrialById(String id){
    
    	return scoreGradeTrialDao.deleteScoreGradeTrialById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreGradeTrialByObject(ScoreGradeTrial scoreGradeTrial){
    
    	return scoreGradeTrialDao.deleteScoreGradeTrialByObject(scoreGradeTrial);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeTrialByField(ScoreGradeTrial scoreGradeTrial){
    
    	return scoreGradeTrialDao.updateScoreGradeTrialByField(scoreGradeTrial);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeTrial(ScoreGradeTrial scoreGradeTrial){
    
    	return scoreGradeTrialDao.updateScoreGradeTrial(scoreGradeTrial);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeTrial 
	 */	
    public ScoreGradeTrial selectScoreGradeTrialById(String id){
    
    	return scoreGradeTrialDao.selectScoreGradeTrialById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ScoreGradeTrial> scoreGradeTrials =scoreGradeTrialDao.selectOnePageByMap(page,map);
	     	if(scoreGradeTrials!=null&&scoreGradeTrials.size()>0){
	     		page.setResult(scoreGradeTrials);
	     	}else{
	     		page.setResult(new ArrayList<ScoreGradeTrial>());
	     	}
	     	return page;
     }
     

 	@Override
 	public void updateTrialList(List<ScoreGradeTrial> gradeTrialsList,User user, JSONObject jsonMap) throws Exception {
 		if (gradeTrialsList == null||gradeTrialsList.size()==0) {
			jsonMap.put("msg", "要修改的数据为空，请稍后重试！");
			throw new Exception("要修改的数据为空，请稍后重试！");
		} 
 		List<String> idList=new ArrayList<String>();//获取ID的集合
		for (ScoreGradeTrial trialList : gradeTrialsList) {
			if(StringUtils.isBlank(trialList.getId())){
				jsonMap.put("msg", "保存的数据ID不能为空，请稍后再试！");
				throw new Exception("保存的数据ID不能为空，请稍后再试！");
			}
			if(StringUtils.isBlank(trialList.getGrade())){
				jsonMap.put("msg", "统一试讲成绩不能为空，请确认！");
				throw new Exception("统一试讲成绩不能为空，请确认！");
			}
			if(!trialList.getGrade().matches("^\\d{1,2}(\\.\\d{1,2})|\\d{1,3}(\\.\\d{1})?$")){
				jsonMap.put("msg", "成绩输入有误，请输入1到3位整数或两位小数！");
				throw new Exception("成绩输入有误，请输入1到3位整数或两位小数！");
			}
			idList.add(trialList.getId());
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", idList);
		List<ScoreGradeTrial> oldScore=scoreGradeTrialDao.selectTrialByMap(map);//之前的分数
		List<ScoreGradeTrial> compareScore=this.getDifferScore(oldScore, gradeTrialsList);//比较后的
		if(compareScore !=null && !compareScore.isEmpty() ){
			for(ScoreGradeTrial trial : compareScore){
				trial.setModifyuser(user.getId());
			}
			this.saveScoreAdjustLog(compareScore, user);
			scoreGradeTrialDao.updateTrialList(compareScore);
		}
 	}
     
 	/**
 	 * 批量保存调整成绩日志表
 	 * @param differGrade
 	 * @param user
 	 */
 	private void saveScoreAdjustLog(List<ScoreGradeTrial> differScore,User user){
 		List<GradeAdjustLog> gradeAdjustLogs=new ArrayList<GradeAdjustLog>();
 		GradeAdjustLog gradeAdjustLog=null;
 		for(ScoreGradeTrial score : differScore){
 			ScoreGradeTrial scoreTrial=scoreGradeTrialDao.selectScoreGradeTrialById(score.getId());
 			gradeAdjustLog=new GradeAdjustLog();
 			gradeAdjustLog.setProjectId(scoreTrial.getProjectId());
 			gradeAdjustLog.setPositionId(scoreTrial.getPositionId());
 			gradeAdjustLog.setStudentId(score.getStudentId());
 			gradeAdjustLog.setType("3");//统一试讲
 			gradeAdjustLog.setCreateuser(user.getId());
 			gradeAdjustLog.setModifyuser(user.getId());
 			gradeAdjustLog.setInitGrade(scoreTrial.getGrade());//上一次的成绩
 			gradeAdjustLog.setGradeAfter(score.getGrade());//新的成绩
 			gradeAdjustLog.setSchoolId(scoreTrial.getSchoolId());
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
 	private List<ScoreGradeTrial> getDifferScore(List<ScoreGradeTrial> scoreOlds,List<ScoreGradeTrial> scoreNews){
 		List<ScoreGradeTrial> data=new ArrayList<ScoreGradeTrial>();
 		for(ScoreGradeTrial scoreOld : scoreOlds){
 			for(ScoreGradeTrial scoreNew : scoreNews){
 				if(scoreOld.getId().equals(scoreNew.getId())&& (!scoreNew.getGrade().equals(scoreOld.getGrade()))){
 					data.add(scoreNew);
 					break;
 				}
 			}
 		}
 		return data;
 	}
 	
 	@Override
	public void publishBatchTrial(Map<String, Object> condition, Page page, User user, JSONObject jsonMap, String flag)
			throws Exception {
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
			List<ScoreGradeTrial> scoreList = new ArrayList<ScoreGradeTrial>();// 查询需要计算综合成绩的考生和两类特殊考生
			List<ScorePostNumVo> postNumList = new ArrayList<ScorePostNumVo>();//查询学校招聘的岗位数量
			//Map<String,List<ScoreGradeTrial>> resutlMap=new LinkedHashMap<String,List<ScoreGradeTrial>>();
			//List<ScoreGradeTrial> grades=new ArrayList<ScoreGradeTrial>();
			List<ScoreGradeTrial> gradePass=new ArrayList<ScoreGradeTrial>();
			List<ScoreGradeTrial> gradeNoPass=new ArrayList<ScoreGradeTrial>();
			List<ScoreGradeTrial> noPass=new ArrayList<ScoreGradeTrial>();
			List<ScoreEnterPhysical> physicalList = new ArrayList<ScoreEnterPhysical>();
			List<ScoreGradeTrial> renewList = new ArrayList<ScoreGradeTrial>();
			//String heihei="";
			condition.put("status", "1");//计算总成绩的考生
			condition.put("writienline", themeNotice.getWrittenEnterLine());//笔试分数线
			condition.put("trialline", themeNotice.getLectureEnterLine());//试讲分数线
			scoreList=scoreGradeTrialDao.selectScoreSpecial(condition);
			postNumList=scoreGradeTrialDao.selectPostNum(condition);
			if (scoreList.size() > 0) {
				Map<String, List<ScoreGradeTrial>> resutlTempMap = new LinkedHashMap<String, List<ScoreGradeTrial>>();
				Map<String, List<ScoreGradeTrial>> mapDates = groupByPositionId(scoreList);
				Map<String, Integer> numDatas = groupByPostNum(postNumList);
				for (Entry<String, List<ScoreGradeTrial>> items : mapDates.entrySet()) {
					Map<String, ScoreGradeTrial> studentMap = new HashMap<String, ScoreGradeTrial>();
					List<ScoreGradeTrial> gradeSortList = new ArrayList<ScoreGradeTrial>();
					String postKey = items.getKey();
					List<ScoreGradeTrial> keyList = items.getValue();
					// 排序
					Collections.sort(keyList, new Comparator<ScoreGradeTrial>() {
						@Override
						public int compare(ScoreGradeTrial b1, ScoreGradeTrial b2) {
							int flag=Double.valueOf(b1.getRemark()).compareTo(Double.valueOf(b2.getRemark()));
							if (flag==-1) {
								return 1;
							} else {
								return -1;
							}
						}

					});
					// 对分数进行分组
					Map<String, List<ScoreGradeVo>> dataMap = new LinkedHashMap<String, List<ScoreGradeVo>>();
					Set<ScoreGradeVo> set = new HashSet<ScoreGradeVo>();
					List<ScoreGradeVo> tempList = null;
					for (ScoreGradeTrial s : keyList) {
						studentMap.put(s.getId(), s);
						ScoreGradeVo vo = new ScoreGradeVo();
						vo.setGrade(s.getGrade());
						vo.setId(s.getId());
						vo.setIsReset(s.getIsReset());
						vo.setName(s.getName());
						vo.setPositionId(s.getPositionId());
						vo.setPostName(s.getPostName());
						vo.setPostNum(s.getPostNum());
						vo.setProjectId(s.getProjectId());
						vo.setRemark(s.getRemark());
						vo.setSameflag(s.getSameflag());
						vo.setSchoolId(s.getSchoolId());
						vo.setStudentId(s.getStudentId());
						vo.setTicketnum(s.getTicketnum());
						if (set.add(vo)) {
							tempList = new ArrayList<ScoreGradeVo>();
							tempList.add(vo);
							dataMap.put(s.getRemark(), tempList);
						} else {

							if (dataMap.containsKey(s.getRemark())) {
								tempList = dataMap.get(s.getRemark());
								tempList.add(vo);
								dataMap.put(s.getRemark(), tempList);
							}
						}
					}
					// 同分排序
					for (Entry<String, List<ScoreGradeVo>> tempVo : dataMap.entrySet()) {
						List<ScoreGradeVo> valueList = tempVo.getValue();
						ScoreGradeVo[] gradeArray = valueList.toArray(new ScoreGradeVo[] {});
						if (gradeArray.length > 1) {
							for (int i = 0; i < gradeArray.length; i++) {
								for (int j = 0; j < gradeArray.length - i - 1; j++) {
									int t = Double.valueOf(gradeArray[j].getGrade())
											.compareTo(Double.valueOf(gradeArray[j + 1].getGrade()));
									if (t < 0) {
										ScoreGradeVo tvo = gradeArray[j];
										gradeArray[j] = gradeArray[j + 1];
										gradeArray[j + 1] = tvo;

									} else if (t == 0) {
										if(StringUtils.isNotBlank(gradeArray[j].getIsReset())
												&&StringUtils.isNotBlank(gradeArray[j + 1].getIsReset())){
											int g = Double.valueOf(gradeArray[j].getIsReset())
													.compareTo(Double.valueOf(gradeArray[j + 1].getIsReset()));
											if (g < 0) {
												ScoreGradeVo tvo = gradeArray[j];
												gradeArray[j] = gradeArray[j + 1];
												gradeArray[j + 1] = tvo;
											}
										}
									}
								}
							}
							for (int m = 0; m < gradeArray.length; m++) {
								if (studentMap.containsKey((gradeArray[m].getId()))) {
									gradeSortList.add(studentMap.get((gradeArray[m].getId())));
								}
							}
						} else {
							if (studentMap.containsKey((gradeArray[0].getId()))) {
								gradeSortList.add(studentMap.get((gradeArray[0].getId())));
							}
						}
					}
					resutlTempMap.put(postKey, gradeSortList);
				}
			// 同分排序
			for (Entry<String, List<ScoreGradeTrial>> tempVo : resutlTempMap.entrySet()) {
				List<ScoreGradeTrial> ratioPass=new ArrayList<ScoreGradeTrial>();
				List<ScoreGradeTrial> ratioNoPass=new ArrayList<ScoreGradeTrial>();
				List<ScoreGradeTrial> list=tempVo.getValue();
				int index=0;
				int totalCount=list.size();
				index=numDatas.get(tempVo.getKey());
				/*for(ScoreGradeTrial vo:list )
				{
					index=vo.getPostNum();
					break;
				}*/
				
				if(totalCount>0){
					if(index > 0 && totalCount <= index){
						for(int i=0;i<totalCount;i++){
							//System.out.println(index+"==========这些人都入围了========"+list.get(i).getSchoolId()+"===="+list.get(i).getPositionId());
							ratioPass.add(list.get(i));
						}
					}else if(index > 0 && totalCount > index){
						for(int t=0;t<index;t++){//录取需要的人数
							if(StringUtils.isNotBlank(list.get(t).getIsReset())&&StringUtils.isNotBlank(list.get(index).getIsReset())){
								int a=Double.valueOf(list.get(t).getRemark()).compareTo(Double.valueOf(list.get(index).getRemark()));
								int b=Double.valueOf(list.get(t).getGrade()).compareTo(Double.valueOf(list.get(index).getGrade()));
								int c=Double.valueOf(list.get(t).getIsReset()).compareTo(Double.valueOf(list.get(index).getIsReset()));
								if(a==0&&b==0&&c==0){
									list.get(t).setSameflag("1");
									//System.out.println(list.get(t).getSameflag()+"==========你也入围了========"+list.get(t).getSchoolId()+"===="+list.get(t).getPositionId());
									ratioPass.add(list.get(t));
								}else{
									//System.out.println(index+"==========这些人都入围了========"+list.get(t).getSchoolId()+"===="+list.get(t).getPositionId());
									ratioPass.add(list.get(t));
								}
							}else{
								int e=Double.valueOf(list.get(t).getRemark()).compareTo(Double.valueOf(list.get(index).getRemark()));
								int f=Double.valueOf(list.get(t).getGrade()).compareTo(Double.valueOf(list.get(index).getGrade()));
								if(e==0&&f==0){
									list.get(t).setSameflag("1");
									ratioPass.add(list.get(t));
								}else{
									ratioPass.add(list.get(t));
								}
							}
						}
						for(int f=index;f<list.size();f++){
							ratioNoPass.add(list.get(f));//未入圍的考生
						}
						List<ScoreGradeTrial> sameGrade=new ArrayList<ScoreGradeTrial>();
						for(int c=index;c<totalCount;c++){
							if(StringUtils.isNotBlank(list.get(c).getIsReset())&&StringUtils.isNotBlank(list.get(index-1).getIsReset())){
								int a=Double.valueOf(list.get(c).getRemark()).compareTo(Double.valueOf(list.get(index-1).getRemark()));
								int b=Double.valueOf(list.get(c).getGrade()).compareTo(Double.valueOf(list.get(index-1).getGrade()));
								int g=Double.valueOf(list.get(c).getIsReset()).compareTo(Double.valueOf(list.get(index-1).getIsReset()));
								if(c!=index-1&&a==0&&b==0&&g==0){
									list.get(c).setSameflag("1");
									//System.out.println(list.get(c).getSameflag()+"==========你也入围了========"+list.get(c).getSchoolId()+"===="+list.get(c).getPositionId());
									sameGrade.add(list.get(c));//相同分數的考生
								}
							}else{
								int h=Double.valueOf(list.get(c).getRemark()).compareTo(Double.valueOf(list.get(index-1).getRemark()));
								int k=Double.valueOf(list.get(c).getGrade()).compareTo(Double.valueOf(list.get(index-1).getGrade()));
								if(c!=index-1&&h==0&&k==0){
									list.get(c).setSameflag("1");
									sameGrade.add(list.get(c));//相同分數的考生
								}
							}
						}
						if(sameGrade !=null && !sameGrade.isEmpty() ){
							ratioNoPass.removeAll(sameGrade);
							ratioPass.addAll(sameGrade);
						}
					}
					gradePass.addAll(ratioPass);
					gradeNoPass.addAll(ratioNoPass);
				}	
			}	
			}else{
				jsonMap.put("msg", "没有考生符合录取分数，请核实考生分数！");
	    		throw new Exception("没有考生符合录取分数，请核实考生分数！");
			}
			
				if(gradePass.size()>0){
					for (ScoreGradeTrial trialAll : gradePass) {
						ScoreEnterPhysical scoreEnterPhysical = new ScoreEnterPhysical();//体检入围表
						 scoreEnterPhysical.setSchoolId(trialAll.getSchoolId());
						 scoreEnterPhysical.setStudentId(trialAll.getStudentId());
						 scoreEnterPhysical.setIsEnter("1");//1、入围
						 scoreEnterPhysical.setItemsId(trialAll.getProjectId());
						 scoreEnterPhysical.setPostId(trialAll.getPositionId());
						 scoreEnterPhysical.setListPublishStatus("1");//1、名单未发布
						 scoreEnterPhysical.setScorePublishStatus("2");//2、成绩已发布
						 scoreEnterPhysical.setType((String)condition.get("testType"));
						 scoreEnterPhysical.setCreateUser(user.getId());
						 physicalList.add(scoreEnterPhysical);
						 ScoreGradeTrial scoreGradeTrial=new ScoreGradeTrial();
						 scoreGradeTrial.setId(trialAll.getId());
						 scoreGradeTrial.setScorePublishStatus("1");//0、未发布1、发布
						 if(StringUtils.isNotEmpty(trialAll.getSameflag())&& trialAll.getSameflag().equals("1")){
							 scoreGradeTrial.setIsReset("成绩同分");//同分
						 }else{
							 scoreGradeTrial.setIsReset(null);
						 }
						 scoreGradeTrial.setRemark(trialAll.getRemark());//综合成绩
						 scoreGradeTrial.setModifyuser(user.getId());
						 renewList.add(scoreGradeTrial);
					}
				// for 循环入围名单
				// 批量存储入围名单
				int pointsDataLimit = 1000;// 限制条数
				Integer size = physicalList.size();
				if (pointsDataLimit < size) {
					int part = size / pointsDataLimit;// 分批数
					for (int i = 0; i < part; i++) {
						List<ScoreGradeTrial> listPage = renewList.subList(0, pointsDataLimit);
						List<ScoreEnterPhysical> listPagePhysical = physicalList.subList(0, pointsDataLimit);
						// 存储
						scoreEnterPhysicalDao.savePhysicalsList(listPagePhysical);
						physicalList.subList(0, pointsDataLimit).clear();
						scoreGradeTrialDao.updateTrialList(listPage);
						renewList.subList(0, pointsDataLimit).clear();
					}
					if(physicalList.size()>0){
						// 存储
						scoreGradeTrialDao.updateTrialList(renewList);
						scoreEnterPhysicalDao.savePhysicalsList(physicalList);
					}	
				} else {
					scoreGradeTrialDao.updateTrialList(renewList);
					scoreEnterPhysicalDao.savePhysicalsList(physicalList);
				}
				if(gradeNoPass.size()>0){
					List<ScoreEnterPhysical> nophysicalList = new ArrayList<ScoreEnterPhysical>();
					List<ScoreGradeTrial> noList = new ArrayList<ScoreGradeTrial>();
					for (ScoreGradeTrial notrialAll : gradeNoPass) {
						ScoreEnterPhysical scoreEnterPhysical = new ScoreEnterPhysical();//体检入围表
						scoreEnterPhysical.setSchoolId(notrialAll.getSchoolId());
						scoreEnterPhysical.setStudentId(notrialAll.getStudentId());
						scoreEnterPhysical.setIsEnter("2");//2、未入围
						scoreEnterPhysical.setItemsId(notrialAll.getProjectId());
						scoreEnterPhysical.setPostId(notrialAll.getPositionId());
						scoreEnterPhysical.setListPublishStatus("1");//1、名单未发布
						scoreEnterPhysical.setScorePublishStatus("2");//2、成绩已发布
						scoreEnterPhysical.setType((String)condition.get("testType"));
						scoreEnterPhysical.setCreateUser(user.getId());
						nophysicalList.add(scoreEnterPhysical);
						ScoreGradeTrial scoreGradeTrial=new ScoreGradeTrial();
						scoreGradeTrial.setId(notrialAll.getId());
						scoreGradeTrial.setScorePublishStatus("1");//0、未发布1、发布
						if(StringUtils.isNotBlank(notrialAll.getRemark())){
							scoreGradeTrial.setRemark(notrialAll.getRemark());//综合成绩
						}else{
							scoreGradeTrial.setRemark("0");//综合成绩
						}	
						scoreGradeTrial.setModifyuser(user.getId());
						noList.add(scoreGradeTrial);
					}
					// for 循环入围名单
					// 批量存储入围名单
					int noLimit = 1000;// 限制条数
					Integer nosize = gradeNoPass.size();
					if (noLimit < nosize) {
						int part = nosize / noLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<ScoreEnterPhysical> nolistPagePhysical = nophysicalList.subList(0, noLimit);
							List<ScoreGradeTrial> nolistPages = noList.subList(0, noLimit);
							// 存储
							scoreEnterPhysicalDao.savePhysicalsList(nolistPagePhysical);
							nophysicalList.subList(0, noLimit).clear();
							scoreGradeTrialDao.updateTrialList(nolistPages);
							noList.subList(0, noLimit).clear();
						}
						if(nophysicalList.size()>0){
							// 存储
							scoreEnterPhysicalDao.savePhysicalsList(nophysicalList);
							scoreGradeTrialDao.updateTrialList(noList);
						}	
					} else {
						scoreEnterPhysicalDao.savePhysicalsList(nophysicalList);
						scoreGradeTrialDao.updateTrialList(noList);
					}
				}
			}else{
				jsonMap.put("msg", "成绩数据为空，请确认！");
	    		throw new Exception("成绩数据为空，请确认！");
			}
			
			condition.put("status", "2");//不符合分数要求的考生，不能入围
			condition.put("writienline", themeNotice.getWrittenEnterLine());//笔试分数线
			condition.put("trialline", themeNotice.getLectureEnterLine());//试讲分数线
			noPass=scoreGradeTrialDao.selectScoreSyns(condition);
			List<ScoreEnterPhysical> nophysicalList = new ArrayList<ScoreEnterPhysical>();
			List<ScoreGradeTrial> norenewList = new ArrayList<ScoreGradeTrial>();
			if(noPass.size()>0){
				for (ScoreGradeTrial notrial : noPass) {
					 ScoreEnterPhysical scoreEnterPhysical = new ScoreEnterPhysical();//体检入围表
					 scoreEnterPhysical.setSchoolId(notrial.getSchoolId());
					 scoreEnterPhysical.setStudentId(notrial.getStudentId());
					 scoreEnterPhysical.setIsEnter("2");//2、未入围
					 scoreEnterPhysical.setItemsId(notrial.getProjectId());
					 scoreEnterPhysical.setPostId(notrial.getPositionId());
					 scoreEnterPhysical.setListPublishStatus("1");//1、名单未发布
					 scoreEnterPhysical.setScorePublishStatus("2");//2、成绩已发布
					 scoreEnterPhysical.setType((String)condition.get("testType"));
					 scoreEnterPhysical.setCreateUser(user.getId());
					 nophysicalList.add(scoreEnterPhysical);
					 ScoreGradeTrial scoreGradeTrial=new ScoreGradeTrial();
					 scoreGradeTrial.setId(notrial.getId());
					 scoreGradeTrial.setScorePublishStatus("1");//0、未发布1、发布
					 scoreGradeTrial.setRemark("0");//综合成绩
					 scoreGradeTrial.setModifyuser(user.getId());
					 norenewList.add(scoreGradeTrial);
				}	 
				// for 循环入围名单
				// 批量存储入围名单
				int noLimit = 1000;// 限制条数
				Integer nosize = norenewList.size();
				if (noLimit < nosize) {
					int part = nosize / noLimit;// 分批数
					for (int i = 0; i < part; i++) {
						List<ScoreGradeTrial> nolistPage = norenewList.subList(0, noLimit);
						List<ScoreEnterPhysical> nolistPagePhysical = nophysicalList.subList(0, noLimit);
						// 存储
						scoreEnterPhysicalDao.savePhysicalsList(nolistPagePhysical);
						nophysicalList.subList(0, noLimit).clear();
						scoreGradeTrialDao.updateTrialList(nolistPage);
						norenewList.subList(0, noLimit).clear();
					}
					if(norenewList.size()>0){
						// 存储
						scoreGradeTrialDao.updateTrialList(norenewList);
						scoreEnterPhysicalDao.savePhysicalsList(nophysicalList);
					}	
				} else {
					scoreGradeTrialDao.updateTrialList(norenewList);
					scoreEnterPhysicalDao.savePhysicalsList(nophysicalList);
				}
			}	
			//公告表修改
			Notice noticeTwo=new Notice();
    		noticeTwo.setLectureScorePublishNor(1);//普通科成绩发布
    		noticeTwo.setLectureScorePublishArt(1);//艺术科成绩发布
			noticeTwo.setThemeId((String)condition.get("projectId"));
			noticeService.updateNoticeByFieldAndTheme(noticeTwo);
			themeService.updateStep((String)condition.get("projectId"), 7);//统一试讲成绩发布
	}else{
		jsonMap.put("msg", "招聘公告为空，请确认！");
		throw new Exception("招聘公告为空，请确认！");
	}
}
 	
 	@Override
	public String celpublishTrial(String projectId, Integer step,String flag,Map<String, Object> condition,User user) throws Exception {
 		String msg="";
 		List<ScoreEnterPhysical> enterPhysicals=new ArrayList<ScoreEnterPhysical>();
 		List<ScoreGradeTrial> scoreList = new ArrayList<ScoreGradeTrial>();//按照学科查询成绩
		enterPhysicals=scoreEnterPhysicalDao.selectPhysicalById(condition);
		scoreList=scoreGradeTrialDao.selectGradeTrialLine(condition);
		
 		//更新成绩表中成绩发布状态
		if(scoreList.size()>0){
			List<ScoreGradeTrial> renewList = new ArrayList<ScoreGradeTrial>();
			for (ScoreGradeTrial renew : scoreList) {
				ScoreGradeTrial scoreGradeTrial=new ScoreGradeTrial();
				scoreGradeTrial.setId(renew.getId());
				scoreGradeTrial.setScorePublishStatus("0");//0、未发布1、发布
				scoreGradeTrial.setRemark("0");
				scoreGradeTrial.setIsReset(null);
				scoreGradeTrial.setModifyuser(user.getId());
				renewList.add(scoreGradeTrial);
			}
			// for 循环入围名单
			// 批量存储入围名单
			int pointsDataLimit = 1000;// 限制条数
			Integer size = renewList.size();
			if (pointsDataLimit < size) {
				int part = size / pointsDataLimit;// 分批数
				for (int i = 0; i < part; i++) {
					List<ScoreGradeTrial> listPage = renewList.subList(0, pointsDataLimit);
					// 存储
					scoreGradeTrialDao.updateTrialList(listPage);
					renewList.subList(0, pointsDataLimit).clear();
				}
				if(renewList.size()>0){
					// 存储
					scoreGradeTrialDao.updateTrialList(renewList);
				}	
			} else {
				scoreGradeTrialDao.updateTrialList(renewList);
			}
		}else{
			msg="scorenull";
		}
		
 		if(enterPhysicals.size()>0){
 			int pointsDataLimit = 500;// 限制条数
			Integer size = enterPhysicals.size();
			if (pointsDataLimit < size) {
				int part = size / pointsDataLimit;// 分批数
				for (int i = 0; i < part; i++) {
					List<ScoreEnterPhysical> listPage = enterPhysicals.subList(0, pointsDataLimit);
					scoreEnterPhysicalDao.deletePhysicalBatch(listPage);//删除发布成绩时创建的数据
					enterPhysicals.subList(0, pointsDataLimit).clear();
				}
				if(enterPhysicals.size()>0){
					scoreEnterPhysicalDao.deletePhysicalBatch(enterPhysicals);//删除发布成绩时创建的数据
				}	
			} else {
				scoreEnterPhysicalDao.deletePhysicalBatch(enterPhysicals);//删除发布成绩时创建的数据
			}
			List<ScoreEnterPhysical> delAfterList=scoreEnterPhysicalDao.selectPhysicalById(condition);//查询是否删除完成
			if (delAfterList.size()==0) {
				themeService.updateStep(projectId, step);
					//公告表修改
					Notice noticeTwo=new Notice();
					noticeTwo.setLectureScorePublishNor(2);//取消普通科成绩发布
					noticeTwo.setLectureScorePublishArt(2);//取消艺术科成绩发布
					noticeTwo.setThemeId(projectId);
					noticeService.updateNoticeByFieldAndTheme(noticeTwo);
				msg="success";
			}else{
				msg="false";
			}
 		}else{
 			msg="phynull";
 		}
 		
		return msg;
	}
 	
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreGradeTrial  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreGradeTrial(Page page,ScoreGradeTrial scoreGradeTrial){
 		 List<ScoreGradeTrial> scoreGradeTrials =scoreGradeTrialDao.selectOnePageByScoreGradeTrial(page,scoreGradeTrial);
	     	if(scoreGradeTrials!=null&&scoreGradeTrials.size()>0){
	     		page.setResult(scoreGradeTrials);
	     	}else{
	     		page.setResult(new ArrayList<ScoreGradeTrial>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public List<ScoreGradeTrial> selectAllByMap(Map<String,Object> map){
     	return scoreGradeTrialDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public List<ScoreGradeTrial> selectAllByScoreGradeTrial(ScoreGradeTrial scoreGradeTrial){
     
    	 return scoreGradeTrialDao.selectAllByScoreGradeTrial(scoreGradeTrial);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeTrial
	 */	
     public ScoreGradeTrial selectObjectByMap(Map<String,Object> map){
     
    	 return scoreGradeTrialDao.selectObjectByMap(map);
     }
     
     @Override
 	public Integer selectCountByMap(Map<String, Object> map) {
 		return scoreGradeTrialDao.selectCountByMap(map);
 	}
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeTrial
	 */	
     public ScoreGradeTrial selectObjectByScoreGradeTrial(ScoreGradeTrial scoreGradeTrial){
     
     	return scoreGradeTrialDao.selectObjectByScoreGradeTrial(scoreGradeTrial);
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
     
     /**
 	 * 对岗位分组
 	 * @param grades
 	 * @return
 	 */
 	private Map<String ,List<ScoreGradeTrial>> groupByPositionId(List<ScoreGradeTrial> grades){
 		Map<String ,List<ScoreGradeTrial>> mapDates=new HashMap<String ,List<ScoreGradeTrial>>();
		Set<String> positionIds=new HashSet<String>();
		for(ScoreGradeTrial grade : grades){
			positionIds.add(grade.getPositionId()+","+grade.getSchoolId());
		}
		Iterator<String> it=positionIds.iterator();
		List<ScoreGradeTrial> gradeItems=null;
		while(it.hasNext()){
			String positionId=it.next();
			gradeItems=new ArrayList<ScoreGradeTrial>();
			for(ScoreGradeTrial grade : grades){
				if(positionId.equals(grade.getPositionId()+","+grade.getSchoolId())){
					gradeItems.add(grade);
				}
			}
			mapDates.put(positionId, gradeItems);
		}
		return mapDates;
 	}

 	/**
 	 * 对岗位数量分组
 	 * @param numgrades
 	 * @return
 	 */
 	private Map<String ,Integer> groupByPostNum(List<ScorePostNumVo> numgrades){
 		Map<String ,Integer> numDatas=new HashMap<String ,Integer>();
		Set<String> positionIds=new HashSet<String>();
		for(ScorePostNumVo grade : numgrades){
			positionIds.add(grade.getPositionId()+","+grade.getSchoolId());
		}
		Iterator<String> it=positionIds.iterator();
		Integer gradeItems;
		while(it.hasNext()){
			String positionId=it.next();
			gradeItems=0;
			for(ScorePostNumVo grade : numgrades){
				if(positionId.equals(grade.getPositionId()+","+grade.getSchoolId())){
					gradeItems=grade.getPostNum();
				}
			}
			numDatas.put(positionId, gradeItems);
		}
		return numDatas;
 	}
 	
 	/**
	 * 返回排序后的成绩
	 * @param grades
	 * @param index
	 * @return
	 */
	/*private  List<ScoreGradeTrial> orderByScore(List<ScoreGradeTrial> grades){
		ScoreGradeTrial [] gradeArray=grades.toArray(new ScoreGradeTrial []{});
		for(int i = 0; i < gradeArray.length -1; i++){
			for(int j = 0 ;j < gradeArray.length - i - 1; j++){
				if(Double.valueOf(gradeArray[j].getGrade()) < Double.valueOf(gradeArray[j+1].getGrade())){
					ScoreGradeTrial temp = gradeArray[j];
					gradeArray[j] = gradeArray[j+1];
					gradeArray[j+1] = temp;
				}
			}
		}
		return Arrays.asList(gradeArray);
	}*/
	
	/**
	 * 判断是否有相同的分数
	 * @param grades
	 * @return
	 */
	/*private boolean hasRepeatGrade(List<ScoreGradeTrial> grades){
		boolean flag=false;
		ScoreGradeTrial [] gradeArray=grades.toArray(new ScoreGradeTrial []{});
		for(int i = 0; i < gradeArray.length; i++){
			for(int j = i+1 ;j < gradeArray.length; j++){
				if(gradeArray[i].getRemark().equals(gradeArray[j].getRemark())){
					flag=true;
					break;
				}
			}
			if(flag){
				break;
			}
		}
		return flag;
	}*/

    @Override
    public ScoreGradeTrial selectScoreGradeTrial(StudentApplyInfo sai) {
        //查询试讲成绩
        ScoreGradeTrial sgt = new ScoreGradeTrial();
        sgt.setProjectId(sai.getEmployItemsId());
        sgt.setPositionId(sai.getApplyJobId());
        sgt.setSchoolId(sai.getApplyDepId());
        sgt.setStudentId(sai.getStudentId());
        //sgt.setScorePublishStatus("1");
        return scoreGradeTrialDao.selectObjectByScoreGradeTrial(sgt);
    }
	
}
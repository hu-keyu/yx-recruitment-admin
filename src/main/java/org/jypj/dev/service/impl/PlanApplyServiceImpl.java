package org.jypj.dev.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.dao.NoticeDao;
import org.jypj.dev.dao.PlanApplyDao;
import org.jypj.dev.dao.PositionDao;
import org.jypj.dev.dao.PostsetDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.dao.StudentInfoDao;
import org.jypj.dev.dao.ThemeDao;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.PlanApplyService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service("planApplyService")
public class PlanApplyServiceImpl implements PlanApplyService {
	
    @Resource 
    private PlanApplyDao planApplyDao;
    @Resource
    private PositionDao positionDao;
    @Resource
    private StudentApplyInfoDao studentApplyInfoDao;
    @Resource
    private PostsetDao postsetDao;
    @Resource
    private StudentInfoDao studentInfoDao;
    @Resource
    private NoticeDao noticeDao;
    @Resource
    private ThemeDao themeDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param planApply
	 * @return 保存后的对象包括ID
	 */	
	public int savePlanApplyByField(PlanApply planApply){
	
		return planApplyDao.savePlanApplyByField(planApply);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param planApply 
	 * @return 保存后的对象包括ID
	 */	
	public int savePlanApply(PlanApply planApply){
	
		return planApplyDao.savePlanApply(planApply);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePlanApplyById(String id){
    
    	return planApplyDao.deletePlanApplyById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deletePlanApplyByObject(PlanApply planApply){
    
    	return planApplyDao.deletePlanApplyByObject(planApply);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param planApply 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePlanApplyByField(PlanApply planApply){
    
    	return planApplyDao.updatePlanApplyByField(planApply);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param planApply 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePlanApply(PlanApply planApply){
    
    	return planApplyDao.updatePlanApply(planApply);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return PlanApply 
	 */	
    public PlanApply selectPlanApplyById(String id){
    
    	return planApplyDao.selectPlanApplyById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<PlanApply>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<PlanApply> planApplys =planApplyDao.selectOnePageByMap(page,map);
	     	if(planApplys!=null&&planApplys.size()>0){
	     		page.setResult(planApplys);
	     	}else{
	     		page.setResult(new ArrayList<PlanApply>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param planApply  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByPlanApply(Page page,PlanApply planApply){
 		 List<PlanApply> planApplys =planApplyDao.selectOnePageByPlanApply(page,planApply);
	     	if(planApplys!=null&&planApplys.size()>0){
	     		page.setResult(planApplys);
	     	}else{
	     		page.setResult(new ArrayList<PlanApply>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PlanApply>
	 */	
     public List<PlanApply> selectAllByMap(Map<String,Object> map){
     	return planApplyDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PlanApply>
	 */	
     public List<PlanApply> selectAllByPlanApply(PlanApply planApply){
     
    	 return planApplyDao.selectAllByPlanApply(planApply);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PlanApply
	 */	
     public PlanApply selectObjectByMap(Map<String,Object> map){
     
    	 return planApplyDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PlanApply
	 */	
     public PlanApply selectObjectByPlanApply(PlanApply planApply){
     
     	return planApplyDao.selectObjectByPlanApply(planApply);
     }

	@Override
	public void saveBatch(List<PlanApply> list) {
		planApplyDao.saveBatch(list) ;
	}

	@Override
	public void deleteByProjectId(String id) {
		planApplyDao.deleteByProjectId(id) ;
	}

	@Override
	public Page selectPlanForCheck(Page page, String themeId) {
		List<PlanApply> planApplys = planApplyDao.selectPlanForCheck(page,themeId);
		if(planApplys!=null&&planApplys.size()>0){
			page.setResult(planApplys);
		}else{
			page.setResult(new ArrayList<PlanApply>());
		}
		return page;
	}

	@Override
	public void saveReport(String planApplyId, User user,JSONObject jsonMap) throws Exception {
		PlanApply PlanApply=this.selectPlanApplyById(planApplyId);
    	Position position=new Position();
    	position.setPlanApplyId(planApplyId);
    	List<Position> positions=positionDao.selectAllByPosition(position);
    	if(positions == null || positions.isEmpty()){
    		jsonMap.put("msg", "请添加岗位信息后再上报，请确认！");
    		throw new Exception("请添加岗位信息后再上报，请确认！");
    	}
    	if(new Date().after(PlanApply.getThemeApply().getReportEndTime())){
    		jsonMap.put("msg", "上报截止日期已过，无法再上报，请确认！");
    		throw new Exception("上报截止日期已过，无法再上报，请确认！");
    	}
    	if(!"0".equals(PlanApply.getStatus())){
    		jsonMap.put("msg", "申报信息已经上报过了，请确认！");
    		throw new Exception("申报信息已经上报过了，请确认！");
    	}
    	Notice notice=noticeDao.selectObjectByThemeId(PlanApply.getProjectId());
    	if(notice !=null){
    		if(notice.getIsPublish() == 1){
    			jsonMap.put("msg", "招聘公告已发布，无法再上报，请确认！");
        		throw new Exception("招聘公告已发布，无法再上报，请确认！");
    		}
    	}
    	PlanApply.setRemark(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	PlanApply.setModifyuser(user.getId());
		PlanApply.setStatus("1");
		this.updatePlanApply(PlanApply);
	}

	@Override
	public void saveRecall(String planApplyId, User user, JSONObject jsonMap) throws Exception {
		PlanApply PlanApply=this.selectPlanApplyById(planApplyId);
    	if(new Date().after(PlanApply.getThemeApply().getReportEndTime())){
    		jsonMap.put("msg", "上报截止日期已过，无法再撤回，请确认！");
    		throw new Exception("上报截止日期已过，无法再撤回，请确认！");
    	}
    	if(!"1".equals(PlanApply.getStatus())){
    		jsonMap.put("msg", "只有待审批状态才可以撤回，请确认！");
    		throw new Exception("只有待审批状态才可以撤回，请确认！");
    	}
    	Notice notice=noticeDao.selectObjectByThemeId(PlanApply.getProjectId());
    	if(notice !=null){
    		if(notice.getIsPublish() == 1){
    			jsonMap.put("msg", "招聘公告已发布，无法再撤回，请确认！");
        		throw new Exception("招聘公告已发布，无法再撤回，请确认！");
    		}
    	}
    	PlanApply.setModifyuser(user.getId());
		PlanApply.setStatus("0");
		PlanApply.setRemark(null);
		this.updatePlanApply(PlanApply);
	}

	@Override
	public List<String> getPlanApplyYears(Map<String,Object> queryParameter) {
		return planApplyDao.getPlanApplyYears(queryParameter);
	}

	@Override
	public PlanApply selectById(String id) {
		return planApplyDao.selectById(id);
	}

	@Override
	public void checkInterview(String chks,String projectId,String reasonId,String isPass,
			User user, JSONObject jsonMap) throws Exception {
		if(StringUtils.isBlank(chks)){
			jsonMap.put("msg", "请选中记录后再做操作，请确认！");
    		throw new Exception("请选中记录后再做操作，请确认");
		}
		Theme theme=themeDao.selectThemeById(projectId);
    	if(theme !=null ){
    		if(theme.getStep()>1){
    			jsonMap.put("msg", "教育局面试名单发布后不可进行审核操作！");
    			throw new Exception("教育局面试名单发布后不可进行审核操作！");
    		}
    	}
		String [] studentIds=chks.split(",");
		Map<String ,Object> queryParameter=new HashMap<String ,Object>();
		queryParameter.put("studentIds", studentIds);
		queryParameter.put("employItemsId", projectId);
		queryParameter.put("schoolId", user.getSchoolId());
		List<StudentApplyInfo> studentApplyInfos =studentApplyInfoDao.selectAllByMap(queryParameter);
		if(studentApplyInfos !=null && !studentApplyInfos.isEmpty()){
			List<StudentApplyInfo> passStudentApplyInfo=new ArrayList<StudentApplyInfo>();
			for(StudentApplyInfo studentApplyInfo : studentApplyInfos){
				if(!user.getSchoolId().equals(studentApplyInfo.getApplyDepId())){
					jsonMap.put("msg", "选择的记录中，有考生报考了其他学校的岗位，操作失败，请刷新页面后重试！");
		    		throw new Exception("选择的记录中，有考生报考了其他学校的岗位，操作失败，请刷新页面后重试！");
				}
				if(!("3".equals(studentApplyInfo.getApplyStatus()) || ("4".equals(studentApplyInfo.getApplyStatus()) && "2".equals(studentApplyInfo.getAudittype()) ))){
					jsonMap.put("msg", "只有未审核的数据才能审核，请确认！");
		    		throw new Exception("只有未审核的数据才能审核，请确认！");
				}
				if("1".equals(isPass)){//审核通过
					passStudentApplyInfo.add(studentApplyInfo);
					studentApplyInfo.setApplyStatus("6");
				}else{//审核不通过
					studentApplyInfo.setApplyStatus("5");
				}
				studentApplyInfo.setApplyAuditCode(reasonId);
				studentApplyInfo.setAudittype("2");
				studentApplyInfo.setModifyUser(user.getId());
			}
			studentApplyInfoDao.updateList(studentApplyInfos);
			
			//生成考生准考证号
			if(passStudentApplyInfo!=null && !passStudentApplyInfo.isEmpty()){
				Map<String,List<StudentApplyInfo>> mapData=this.getPassStudent(studentApplyInfos);
				this.createExaminationNumber(mapData,user,projectId);
			}
		}else{
			jsonMap.put("msg", "请选择记录操作，或操作的考生报考了其他学校岗位，请刷新页面后重试！");
    		throw new Exception("请选择记录操作，或操作的考生报考了其他学校岗位，请刷新页面后重试！");
		}
	}
	
	/**
	 * 生成考生准考证号
	 * 准考证号规则：年份月份+岗位代码+单位代码+三位流水号
	 * 1612 011 02 001 16代表年份，12代表月份，011代表岗位，02代表单位，001代表流水号
	 * @param mapData
	 * @param user
	 * @param projectId
	 */
	private synchronized void createExaminationNumber(Map<String,List<StudentApplyInfo>> mapData,User user,String projectId){
		String date=new SimpleDateFormat("yyMM").format(Calendar.getInstance().getTime());
		for(Entry<String,List<StudentApplyInfo>> entry : mapData.entrySet()){
			String postCode="";//岗位代码
			String applyJobId=entry.getKey();
			Postset postset=postsetDao.selectPostsetById(applyJobId);//岗位
			postCode=(postset != null) ? postCode=postset.getPostCode():"";
			postCode=StringUtils.leftPad(postCode, 3, "0");//单位代码不足3位左边补0
			String schoolCode=this.getSchoolCode(user);
			String examinationNumber=date+postCode+schoolCode;//准考证号
			
			List<StudentApplyInfo> studentApplyInfos=entry.getValue();
			List<String> studentIds=getStudents(studentApplyInfos);
			Map<String,Object> queryParameter=new HashMap<String,Object>();
			queryParameter.put("ids", studentIds);
			queryParameter.put("employItemsId", projectId);
			queryParameter.put("applyDepId", user.getSchoolId());
			List<StudentInfo> studentInfos=studentInfoDao.selectAllByMap(queryParameter);
			
			Integer index=1;//流水号
			String maxNum=studentInfoDao.getMaxTicketNum(examinationNumber,projectId);
			if(StringUtils.isNotBlank(maxNum) && !"null".equals(maxNum)){//准考证号存在
				maxNum=maxNum.substring(maxNum.length()-3, maxNum.length());
				index=Integer.valueOf(maxNum);
				index++;
			}
			if(studentInfos !=null && !studentInfos.isEmpty()){
				for(StudentInfo studentInfo : studentInfos){
					String indexNum=String.valueOf(index);
					indexNum=StringUtils.leftPad(indexNum, 3, "0");//流水号不足3位补足3位
					studentInfo.setTicketnum(examinationNumber+indexNum);//设置考生准考证号
					index++;
				}
				studentInfoDao.updateList(studentInfos);
			}
		}
	}
	
	private String getSchoolCode(User user){
		String dwdmCode=null;
		List<Dictionary> dwdms = DictionaryCache.getDictionaryByCode("dwdm");//获取所有单位
		for(Dictionary dictionary : dwdms){
			if(dictionary.getId().equals(user.getSchoolId())){
				dwdmCode=dictionary.getValue();
			}
		}
		dwdmCode=StringUtils.leftPad(dwdmCode, 2, "0");
		return dwdmCode;
	}
	
	
	private List<String> getStudents(List<StudentApplyInfo> studentApplyInfos){
		List<String> items=new ArrayList<String>();
		for(StudentApplyInfo studentApplyInfo : studentApplyInfos){
			items.add(studentApplyInfo.getStudentId());
		}
		return items;
	}
	
	/**
	 * 面试通过审核通过人员（key为申请的岗位ID，value为List<StudentApplyInfo>集合）
	 * @param studentApplyInfos
	 * @return
	 */
	private Map<String,List<StudentApplyInfo>> getPassStudent(List<StudentApplyInfo> studentApplyInfos){
		Set<String> applyJobIds=new HashSet<String>();
		for(StudentApplyInfo studentApplyInfo : studentApplyInfos){
			applyJobIds.add(studentApplyInfo.getApplyJobId());
		}
		Iterator<String> it=applyJobIds.iterator();
		
		Map<String,List<StudentApplyInfo>> mapData=new HashMap<String,List<StudentApplyInfo>>();
		List<StudentApplyInfo> studentInfos=null;
		while(it.hasNext()){
			String applyJobId=it.next();
			studentInfos=new ArrayList<StudentApplyInfo>();
			for(StudentApplyInfo studentApplyInfo : studentApplyInfos){
				if(applyJobId.equals(studentApplyInfo.getApplyJobId())){
					studentInfos.add(studentApplyInfo);
				}
			}
			mapData.put(applyJobId, studentInfos);
		}
		return mapData;
	}

	@Override
	public List<PlanApply> findPlanApplyListByThemeId(String themeId) {
		return planApplyDao.findPlanApplyListByThemeId(themeId);
	}

	@Override
	public void checkCancel(String chk, String projectId, User user, String status,String reason,JSONObject jsonMap) throws Exception {
		if(StringUtils.isBlank(chk)){
			jsonMap.put("msg", "请选中记录后再做操作，请确认！");
    		throw new Exception("请选中记录后再做操作，请确认");
		}
		if(StringUtils.isBlank(status)){
			jsonMap.put("msg", "参数status为空，请稍后再试！");
    		throw new Exception("参数status为空，请稍后再试！");
		}
		Theme theme=themeDao.selectThemeById(projectId);
    	if(theme !=null ){
    		if(theme.getStep()>1){
    			jsonMap.put("msg", "教育局面试名单发布后不可进行审核操作！");
    			throw new Exception("教育局面试名单发布后不可进行审核操作！");
    		}
    	}
		Map<String ,Object> queryParameter=new HashMap<String ,Object>();
		String [] studentIds=chk.split(",");
		queryParameter.put("studentIds", studentIds);
		queryParameter.put("employItemsId", projectId);
		queryParameter.put("schoolId", user.getSchoolId());
		List<StudentApplyInfo> studentApplyInfos =studentApplyInfoDao.selectAllByMap(queryParameter);
		if(studentApplyInfos !=null && !studentApplyInfos.isEmpty()){
			for(StudentApplyInfo studentApplyInfo : studentApplyInfos){
				String msg="服务异常，请稍后再试！";
				if(!"4".equals(studentApplyInfo.getApplyStatus()) && !"5".equals(studentApplyInfo.getApplyStatus()) && !"6".equals(studentApplyInfo.getApplyStatus())){
					if("3".equals(status)){//撤销审核操作
						msg="只有已审核的数据才能撤销审核，请刷新页面后重试！";
					}
					if("5".equals(status)){//退回申请操作
						msg="只有已审核的数据才能退回申请，请刷新页面后重试！";
					}
					if("4".equals(status)){//照片退回操作
						msg="只有已审核的数据才能照片退回，请刷新页面后重试！";
					}
					jsonMap.put("msg", msg);
					throw new Exception(msg);
				}
				if(!"2".equals(studentApplyInfo.getAudittype())){
					jsonMap.put("msg", "选择的考生中有不是学校端操作的数据，请确认！");
					throw new Exception("选择的考生中有不是学校端操作的数据，请确认！");
				}
				studentApplyInfo.setApplyAuditCode(reason);
				studentApplyInfo.setAudittype("2");
				studentApplyInfo.setApplyStatus(status);
				studentApplyInfo.setModifyUser(user.getId());
			}
			studentApplyInfoDao.updateList(studentApplyInfos);
			
			queryParameter.clear();
			queryParameter.put("ids", studentIds);
			queryParameter.put("employItemsId", projectId);
			queryParameter.put("applyDepId", user.getSchoolId());
			List<StudentInfo> studentInfos=studentInfoDao.selectAllByMap(queryParameter);
			if(studentInfos !=null && !studentInfos.isEmpty()){
				for(StudentInfo studentInfo : studentInfos){
					studentInfo.setTicketnum(null);
					studentInfo.setModifyUser(user.getId());
				}
				studentInfoDao.updateList(studentInfos);
			}
		}else{
			jsonMap.put("msg", "请选择记录操作，或选择的考生已报考了其他学校岗位，请刷新页面后重试！");
			throw new Exception("请选择记录操作，或选择的考生已报考了其他学校岗位，请刷新页面后重试！");
		}
	}
	
	@Override
	public List<PlanApply> selectUncheckedByprojectId(String projectId){
		return planApplyDao.selectUncheckedByprojectId(projectId) ;
	}
	
	@Override
	public List<PlanApply> selectCheckedByprojectId(String projectId){
		return planApplyDao.selectCheckedByprojectId(projectId) ;
	}
}
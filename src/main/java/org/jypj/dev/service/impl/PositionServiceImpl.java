package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.NoticeDao;
import org.jypj.dev.dao.PlanApplyDao;
import org.jypj.dev.dao.PositionDao;
import org.jypj.dev.dao.PositionDomainDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.dao.ThemeDao;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.PositionDomain;
import org.jypj.dev.entity.Specialty;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.PositionVo;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service("positionService")
public class PositionServiceImpl implements PositionService {
	
    @Resource
    private PositionDao positionDao;
    @Resource
    private PositionDomainDao positionDomainDao;
    @Resource
    private StudentApplyInfoDao studentApplyInfoDao;
    @Resource
    private ThemeDao themeDao;
    @Resource
    private NoticeDao noticeDao;
    @Resource
    private PlanApplyDao planApplyDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param position
	 * @return 保存后的对象包括ID
	 */	
	public int savePositionByField(Position position){
	
		return positionDao.savePositionByField(position);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param position 
	 * @return 保存后的对象包括ID
	 */	
	public int savePosition(Position position){
	
		return positionDao.savePosition(position);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePositionById(String id){
    
    	return positionDao.deletePositionById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deletePositionByObject(Position position){
    
    	return positionDao.deletePositionByObject(position);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param position 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePositionByField(Position position){
    
    	return positionDao.updatePositionByField(position);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param position 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePosition(Position position){
    
    	return positionDao.updatePosition(position);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Position 
	 */	
    public Position selectPositionById(String id){
    
    	return positionDao.selectPositionById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Position>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Position> positions =positionDao.selectOnePageByMap(page,map);
	     	if(positions!=null&&positions.size()>0){
	     		page.setResult(positions);
	     	}else{
	     		page.setResult(new ArrayList<Position>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param position  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByPosition(Page page,Position position){
 		 List<Position> positions =positionDao.selectOnePageByPosition(page,position);
	     	if(positions!=null&&positions.size()>0){
	     		page.setResult(positions);
	     	}else{
	     		page.setResult(new ArrayList<Position>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Position>
	 */	
     public List<Position> selectAllByMap(Map<String,Object> map){
     	return positionDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Position>
	 */	
     public List<Position> selectAllByPosition(Position position){
     
    	 return positionDao.selectAllByPosition(position);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Position
	 */	
     public Position selectObjectByMap(Map<String,Object> map){
     
    	 return positionDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Position
	 */	
     public Position selectObjectByPosition(Position position){
     
     	return positionDao.selectObjectByPosition(position);
     }

	@Override
	public void savePositionOper(Position position,User user,JSONObject jsonMap) {
		Position poset=new Position();
		poset.setProjectId(position.getProjectId());
		poset.setPlanApplyId(position.getPlanApplyId());
		poset.setSchoolId(user.getSchoolId());
		poset.setStationId(position.getStationId());
		Position positionServer=this.selectObjectByPosition(poset);
		if(positionServer != null){
			jsonMap.put("msg", "该岗位已添加，不能重复添加，请刷新页面重试！");
			throw new RuntimeException("该岗位已添加，不能重复添加，请刷新页面重试！");
		}
		
		position.setCreateuser(user.getId());
		position.setModifyuser(user.getId());
		position.setStatus("1");//默认启动状态
		position.setSchoolId(user.getSchoolId());//当前登录用户学校ID
		this.savePosition(position);
		String limitProfessions=position.getLimitProfession();
		String limitProfessionText=position.getLimitProfessionText();
		if(StringUtils.isNotBlank(limitProfessions) && StringUtils.isNotBlank(limitProfessionText)){
			String [] limitProfessionArr=limitProfessions.split(",");
			String [] limitProfessionTextArr=limitProfessionText.split(",");
			this.savePositionDomain(position,limitProfessionArr,limitProfessionTextArr,user);
		}
		
	}

	private void savePositionDomain(Position position,String [] limitProfessionArr,String [] limitProfessionTextArr,User user) {
		if(limitProfessionArr !=null && limitProfessionTextArr !=null){
			List<PositionDomain> positionDomains=new ArrayList<PositionDomain>();
			PositionDomain positionDomain=null;
			for(int i=0;i<limitProfessionArr.length;i++){
				positionDomain=new PositionDomain();
				positionDomain.setSchoolId(user.getSchoolId());//当前登录用户学校ID
				positionDomain.setPlanApplyId(position.getPlanApplyId());
				positionDomain.setPositionId(position.getStationId());
				positionDomain.setDomainId(limitProfessionArr[i]);
				positionDomain.setDomainName(limitProfessionTextArr[i]);
				positionDomain.setCreateuser(user.getId());
				positionDomain.setModifyuser(user.getId());
				positionDomains.add(positionDomain);
			}
			positionDomainDao.saveList(positionDomains);
		}
	}

	@Override
	public void deletePositionOper(String id) {
		Position position=this.selectPositionById(id);
		deletePositionDomain(position,null);
		this.deletePositionById(id);
	}
	
	private void deletePositionDomain(Position position,String domainCode){
		PositionDomain positionDomain=new PositionDomain();
		positionDomain.setSchoolId(position.getSchoolId());
		positionDomain.setPlanApplyId(position.getPlanApplyId());
		positionDomain.setPositionId(position.getStationId());
		if(StringUtils.isNotBlank(domainCode)){
			positionDomain.setDomainId(domainCode);
		}
		positionDomainDao.deletePositionDomainByObject(positionDomain);
	}
	
	@Override
	public void updatePositionOper(Position position, User user) {
		Position positionServer=this.selectPositionById(position.getId());
		if(StringUtils.isNotBlank(position.getIsLimitProfession()) && "1".equals(position.getIsLimitProfession())
				&& StringUtils.isNotBlank(position.getLimitProfession())){//有专业要求限制
			List<PositionDomain> positionDomains=positionServer.getPositionDomains();
			List<String> codePre=new ArrayList<String>();
			List<String> codeTextPre=new ArrayList<String>();
			for(PositionDomain positionDomain : positionDomains){
				codePre.add(positionDomain.getDomainId());
				codeTextPre.add(positionDomain.getDomainName());
			}
			String limitProfessions=position.getLimitProfession();
			List<String> codeNew=Arrays.asList(limitProfessions.split(","));
			Map<String,List<String>> dataMap=getDomainCodes(codeNew,codePre);
			List<String> addCode=dataMap.get("add");
			List<String> deleteCode=dataMap.get("delete");
			
			String LimitProfessionTexts=position.getLimitProfessionText();
			List<String> codeTextNew=Arrays.asList(LimitProfessionTexts.split(","));
			Map<String,List<String>> codeTextMap=getDomainCodes(codeTextNew,codeTextPre);
			List<String> addCodeText=codeTextMap.get("add");
			if(addCode != null && !addCode.isEmpty() && addCodeText !=null && !addCodeText.isEmpty() ){
				this.savePositionDomain(positionServer, addCode.toArray(new String[]{}), addCodeText.toArray(new String[]{}), user);
			}
			if(deleteCode != null && !deleteCode.isEmpty()){
				for(String code : deleteCode){
					this.deletePositionDomain(positionServer,code);
				}
			}
		}else{//没有专业要求限制
			this.deletePositionDomain(positionServer,null);
		}
		position.setModifyuser(user.getId());
		this.updatePosition(position);
	}
	
	private Map<String,List<String>> getDomainCodes(List<String> listNew,List<String> listPre){
		Map<String , List<String>> dataMap=new HashMap<String , List<String>>();
		List<String> sameList=(List<String>)CollectionUtils.intersection(listNew, listPre);
		List<String> deleteList=(List<String>)CollectionUtils.subtract(listPre, sameList);
		List<String> addList=(List<String>)CollectionUtils.subtract(listNew, sameList);
		dataMap.put("add", addList);
		dataMap.put("delete", deleteList);
		return dataMap;
	}

	@Override
	public void cancelPosition(String id, String projectId, String stationId, User user, JSONObject jsonMap)throws Exception {
		Position Position=this.selectPositionById(id);
    	if(!"1".equals(Position.getStatus())){
    		jsonMap.put("msg", "只有岗位是启用状态时才能取消岗位，请确认！");
    		throw new Exception("只有岗位是启用状态时才能取消岗位，请确认！");
    	}
    	
    	Theme theme=themeDao.selectThemeById(projectId);
    	if(theme !=null ){
    		if(theme.getStep()>1){
    			jsonMap.put("msg", "教育局面试名单已发布，无法进行岗位管理！");
    			throw new Exception("教育局面试名单已发布，无法进行岗位管理！");
    		}
    	}
    	Notice themeNotice=noticeDao.selectObjectByThemeId(projectId);
    	if(themeNotice !=null){
    		if(new Date().after(themeNotice.getInterviewStart())){
    			jsonMap.put("msg", "面试时间已到，无法进行岗位管理！");
    			throw new Exception("面试时间已到，无法进行岗位管理！");
    		}
    	}
    	
    	PlanApply planApply=new PlanApply();
    	planApply.setProjectId(projectId);
    	planApply.setSchoolId(user.getSchoolId());
    	PlanApply planApplyServer=planApplyDao.selectObjectByPlanApply(planApply);
    	if(planApplyServer !=null ){
    		if(!"2".equals(planApplyServer.getStatus())){
    			jsonMap.put("msg", "教育局没有审批，无法进行岗位管理！");
    			throw new Exception("教育局没有审批，无法进行岗位管理！");
    		}
    	}
    	
    	StudentApplyInfo studentApplyInfo=new StudentApplyInfo();
    	studentApplyInfo.setApplyStatus("1");//完成填写基本资料，开始报考
    	studentApplyInfo.setIsRepay("1");//是否补报
    	studentApplyInfo.setModifyUser(user.getId());
    	studentApplyInfo.setEmployItemsId(projectId);
    	studentApplyInfo.setApplyJobId(stationId);
    	studentApplyInfo.setApplyDepId(user.getSchoolId());//当前登录人的学校ID
    	studentApplyInfoDao.cancelPosition(studentApplyInfo);
    	
    	Position.setStatus("2");
		Position.setModifyuser(user.getId());
		this.updatePosition(Position);
	}

	@Override
	public List<Position> selectByPosition(String year,String themeid) {
		return positionDao.selectByPosition(year,themeid);
	}
	
	@Override
	public void enabledPosition(String id, String projectId,User user, JSONObject jsonMap) throws Exception{
		Position Position=this.selectPositionById(id);
    	if(Position !=null ){
    		if(!"2".equals(Position.getStatus())){
    			jsonMap.put("msg", "只有岗位是取消状态时才能启用岗位，请确认！");
    			throw new Exception("只有岗位是取消状态时才能启用岗位，请确认！");
    		}
    	}
    	Theme theme=themeDao.selectThemeById(projectId);
    	if(theme !=null ){
    		if(theme.getStep()>1){
    			jsonMap.put("msg", "教育局面试名单已发布，无法进行岗位管理！");
    			throw new Exception("教育局面试名单已发布，无法进行岗位管理！");
    		}
    	}
    	Notice themeNotice=noticeDao.selectObjectByThemeId(projectId);
    	if(themeNotice !=null){
    		if(new Date().after(themeNotice.getInterviewStart())){
    			jsonMap.put("msg", "面试时间已到，无法进行岗位管理！");
    			throw new Exception("面试时间已到，无法进行岗位管理！");
    		}
    	}
    	
    	PlanApply planApply=new PlanApply();
    	planApply.setProjectId(projectId);
    	planApply.setSchoolId(user.getSchoolId());
    	PlanApply planApplyServer=planApplyDao.selectObjectByPlanApply(planApply);
    	if(planApplyServer !=null ){
    		if(!"2".equals(planApplyServer.getStatus())){
    			jsonMap.put("msg", "教育局没有审批，无法进行岗位管理！");
    			throw new Exception("教育局没有审批，无法进行岗位管理！");
    		}
    	}
    	
    	Position.setStatus("1");
		Position.setModifyuser(user.getId());
		this.updatePosition(Position);
	}

    @Override
    public List<Position> selectOptionalPosition(Map<String, Object> map) {
        return positionDao.selectOptionalPosition(map);
    }

	@Override
	public void checkBatch(List<Position> positionList) {
		positionDao.checkBatch(positionList) ;
	}

	@Override
	public List<Position> queryValidPosition(Map<String, Object> map) {
		return positionDao.queryValidPosition(map);
	}

	@Override
	public List<Position> selectPostByPorjectId(String projectId) {
		List<Map<String,String>> list = positionDao.selectPostByPorjectId(projectId);
		List<Position> positions = new ArrayList<Position>();
		for(Map<String,String> m : list){
			Position p = new Position() ;
			p.setPostName(m.get("POSTNAME"));
			p.setStationId(m.get("STATIONID"));
			int count = selectApproveCount(projectId,p.getStationId()) ;
			p.setSubjectApproveCount(count);
			positions.add(p) ;
		}
		return  positions;
	}

	@Override
	public Position selectProPosName(String projectId, String postName) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put("projectId",projectId) ;
		map.put("postName",postName) ;
		return positionDao.selectByPosName(map);
	}
	
	@Override
	public int selectApproveCount(String projectId, String station_id) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put("projectId",projectId) ;
		map.put("stationId",station_id) ;
 		return positionDao.selectApproveCount(map);
	}

    @Override
    public List<Position> selectPostByRecruitId(String recruitId) {
        return positionDao.selectPostByRecruitId(recruitId);
    }
    
	@Override
	public Page getPositionVoSubjectList(Page page, Map<String, Object> queryParameter) {
		List<String> ids =positionDao.getPositionVoIds(page,queryParameter);
		queryParameter.put("ids", ids);
		List<PositionVo> positionVos =positionDao.getPositionVoSubjectList(queryParameter);
		this.operPositionVoList(positionVos);
		if(positionVos!=null&&positionVos.size()>0){
     		page.setResult(positionVos);
     	}else{
     		page.setResult(new ArrayList<Position>());
     	}
     	return page;
	}
	
	/**
	 * 处理岗位查询实体
	 * @param poositionVos
	 */
	private void operPositionVoList(List<PositionVo> positionVos){
		for(PositionVo positionVo : positionVos){
			List<Specialty> specialtys=positionVo.getSpecialtys();
			List<PositionDomain> positionDomains=positionVo.getPositionDomains();
			this.getProfessionText(positionVo, specialtys, positionDomains);
			String limitRecruit=positionVo.getLimitRecruit();
			positionVo.setGraduate("√");//招聘对象毕业生
			positionVo.setSocial("√");//招聘对象社会人员
			switch(limitRecruit){
				case "1" : positionVo.setSocial("×");break;//毕业生
				case "2" : positionVo.setGraduate("×");break;//社会人员
			}
			if(StringUtils.isBlank(positionVo.getAge())){
				positionVo.setAge("");
			}
			if(StringUtils.isBlank(positionVo.getEducation())){
				positionVo.setEducation("");
			}
			if(StringUtils.isBlank(positionVo.getDegree())){
				positionVo.setDegree("");
			}
			if(StringUtils.isBlank(positionVo.getProfession())){
				positionVo.setProfession("");
			}
			if(StringUtils.isBlank(positionVo.getOtherLimit())){
				positionVo.setOtherLimit("");
			}
			if(StringUtils.isBlank(positionVo.getRemark())){
				positionVo.setRemark("");
			}
		}
	}
	
	/**
	 * 拼接岗位限制的专业
	 * @param positionVo
	 * @param specialty
	 * @param positionDomains
	 */
	private void getProfessionText(PositionVo positionVo,List<Specialty> specialtys,List<PositionDomain> positionDomains){
		StringBuffer buf=new StringBuffer();
		if((specialtys !=null && !specialtys.isEmpty()) && (positionDomains == null || positionDomains.isEmpty() )){
			for(int i=0;i<specialtys.size();i++){
				Specialty specialty=specialtys.get(i);
				if(i != specialtys.size()-1){
					buf.append(specialty.getCode()).append(specialty.getName()).append(",");
				}else{
					buf.append(specialty.getCode()).append(specialty.getName());
				}
			}
		}else{
			for(int i=0;i<positionDomains.size();i++){
				PositionDomain positionDomain=positionDomains.get(i);
				if(i != positionDomains.size()-1){
					buf.append(positionDomain.getDomainName()).append(",");
				}else{
					buf.append(positionDomain.getDomainName());
				}
			}
		}
		positionVo.setProfession(buf.toString());
	} 
	
	@Override
	public Page getPositionVoUnitList(Page page, Map<String, Object> queryParameter) {
		List<String> ids =positionDao.getPositionVoUnitIds(page,queryParameter);
		queryParameter.put("ids", ids);
		List<PositionVo> positionVos=positionDao.getPositionVoUnitList(queryParameter);
		this.operPositionVoList(positionVos);
		if(positionVos!=null&&positionVos.size()>0){
     		page.setResult(positionVos);
     	}else{
     		page.setResult(new ArrayList<Position>());
     	}
     	return page;
	}
	
	@Override
	public List<PositionVo> queryPositionVoSubjectList(Map<String, Object> queryParameter) {
		List<PositionVo> positionVos=positionDao.queryPositionVoSubjectList(queryParameter);
		this.operPositionVoList(positionVos);
		return positionVos;
	}

	@Override
	public List<PositionVo> queryPositionVoUnitList(Map<String, Object> queryParameter) {
		List<PositionVo> positionVos=positionDao.queryPositionVoUnitList(queryParameter);
		this.operPositionVoList(positionVos);
		return positionVos;
	}

	@Override
	public int selectByApplyJobId(String s) {
		return positionDao.selectByApplyJobId(s);
	}

}
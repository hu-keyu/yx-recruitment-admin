package org.jypj.dev.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.dao.ExamItemsInfoDao;
import org.jypj.dev.dao.ExamSubjectInfoDao;
import org.jypj.dev.entity.ExamItemsInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.EaxmLabsInfoService;
import org.jypj.dev.service.ExamItemsInfoService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.LimitPositionVo;
import org.springframework.stereotype.Service;

@Service("examItemsInfoService")
public class ExamItemsInfoServiceImpl implements ExamItemsInfoService {
	
    @Resource 
    private ExamItemsInfoDao examItemsInfoDao;
    @Resource
   	private EaxmLabsInfoService eaxmLabsInfoService;
    
    @Resource
   	private ExamSubjectInfoDao examSubjectInfoDao;
    
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param examItemsInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveExamItemsInfoByField(ExamItemsInfo examItemsInfo){
	
		return examItemsInfoDao.saveExamItemsInfoByField(examItemsInfo);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param examItemsInfo 
	 * @return 保存后的对象包括ID
	 */	
	public String saveExamItemsInfo(ExamItemsInfo examItemsInfo,User user){
		String flag="";
		if(examItemsInfo!=null){
			Integer count=examItemsInfoDao.selectCountByNum(examItemsInfo.getItemsId(),examItemsInfo.getTestNum());
			if(count>0){
				flag="testnum";//试室号重复
				return flag;
			}
			List<ExamItemsInfo> eaxmList=examItemsInfoDao.selectsPerGw(examItemsInfo.getItemsId(),null,examItemsInfo.getType());
			String perGw=examItemsInfo.getPermisGw();//接收的岗位id
			List<String> strList=new ArrayList<>();
			int i=eaxmList.size();
			int j=0;
			HashSet<String> set=new HashSet<String>();
			StringBuilder strb=new StringBuilder();
			if(i>0){
					for (ExamItemsInfo itemsInfo : eaxmList) {
						 if(StringUtils.isNotBlank(itemsInfo.getPermisGw())){
							 j++;
							 if(i==j){
								 strb.append(itemsInfo.getPermisGw());
							 }else{
								 strb.append(itemsInfo.getPermisGw()+",");
							 }
						 }
					}
						
			//查询后的string类型的id和新增的id
			String post=strb.toString()+","+perGw;
			if(post.length()>32){
				String[]  strspost=post.split(",");
				for(int g=0,len=strspost.length;g<len;g++){
				    set.add(strspost[g].toString());
				}
				for(int y=0,len=strspost.length;y<len;y++){
				    strList.add(strspost[y].toString());
				}
			}
		}	
			 if(!(set.size()==strList.size())){
				 int sizes=strList.size()-set.size();
				 flag="false"+sizes;
		     }else{
		    	 examItemsInfo.setCreateUser(user.getId());
		    	 examItemsInfo.setModifyUser(user.getId());
		    	 examItemsInfo.setPublishStatus("1");//保存未发布
		    	 examItemsInfo.setDeleteStatus("2");//未删除
		    	 String idNum=examItemsInfoDao.selectIdNum(examItemsInfo.getTestNum());
		    	 examItemsInfoDao.saveExamItemsInfo(examItemsInfo);
		    	 if(StringUtils.isNotEmpty(idNum)){
		    		 examItemsInfoDao.deleteExamItemsInfoById(idNum);
		    	 }
		    	 	flag="success";
		     }
		}else{
			 flag="objnull";
		}
		return flag;
	}
    

	@Override
	public String verifyExamItemsInfo(ExamItemsInfo examItemsInfo, User user) {
		String flag="";
		if(examItemsInfo!=null){
			Integer count=examItemsInfoDao.selectCountByNum(examItemsInfo.getItemsId(),examItemsInfo.getTestNum());
			if(count>0){
				flag="testnum";//试室号重复
				return flag;
			}
			List<ExamItemsInfo> eaxmList=examItemsInfoDao.selectsPerGw(examItemsInfo.getItemsId(),null,examItemsInfo.getType());
			String perGw=examItemsInfo.getPermisGw();//接收的岗位id
			List<String> strList=new ArrayList<>();
			int i=eaxmList.size();
			int j=0;
			HashSet<String> set=new HashSet<String>();
			StringBuilder strb=new StringBuilder();
			if(i>0){
					for (ExamItemsInfo itemsInfo : eaxmList) {
						 if(StringUtils.isNotBlank(itemsInfo.getPermisGw())){
							 j++;
							 if(i==j){
								 strb.append(itemsInfo.getPermisGw());
							 }else{
								 strb.append(itemsInfo.getPermisGw()+",");
							 }
						 }
					}
						
			//查询后的string类型的id和新增的id
			String post=strb.toString()+","+perGw;
			if(post.length()>32){
				String[]  strspost=post.split(",");
				for(int g=0,len=strspost.length;g<len;g++){
				    set.add(strspost[g].toString());
				}
				for(int y=0,len=strspost.length;y<len;y++){
				    strList.add(strspost[y].toString());
				}
			}
		}	
			 if(!(set.size()==strList.size())){
				 int sizes=strList.size()-set.size();
				 flag="false"+sizes;
		     }else{
		    	 flag="success";
		     }
		}else{
			 flag="objnull";
		}
		return flag;
	}
	
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteExamItemsInfoById(String id){
    
    	return examItemsInfoDao.deleteExamItemsInfoById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteExamItemsInfoByObject(ExamItemsInfo examItemsInfo){
    
    	return examItemsInfoDao.deleteExamItemsInfoByObject(examItemsInfo);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param examItemsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public String updateExamItemsInfoByField(ExamItemsInfo examItemsInfo,User user){
    	String flag="";
		if(examItemsInfo!=null){
	    	List<ExamItemsInfo> eaxmList=examItemsInfoDao.selectsPerGw(examItemsInfo.getItemsId(),examItemsInfo.getId(),examItemsInfo.getType());
			String perGw=examItemsInfo.getPermisGw();//接收的岗位id
			List<String> strList=new ArrayList<>();
			int i=eaxmList.size();
			int j=0;
			HashSet<String> set=new HashSet<String>();
			StringBuilder strb=new StringBuilder();
			if(i>0){
				for (ExamItemsInfo itemsInfo : eaxmList) {
					 if(StringUtils.isNotBlank(itemsInfo.getPermisGw())){
						 j++;
						 if(i==j){
							 strb.append(itemsInfo.getPermisGw());
						 }else{
							 strb.append(itemsInfo.getPermisGw()+",");
						 }
					 }
				}
					
				//查询后的string类型的id和新增的id
				String post=strb.toString()+","+perGw;
				if(post.length()>32){
					String[]  strspost=post.split(",");
					for(int g=0,len=strspost.length;g<len;g++){
					    set.add(strspost[g].toString());
					}
					for(int y=0,len=strspost.length;y<len;y++){
					    strList.add(strspost[y].toString());
					}
				}
			}	
				 if(!(set.size()==strList.size())){
					 int sizes=strList.size()-set.size();
					 flag="false"+sizes;
			     }else{
			    	 ExamItemsInfo info=examItemsInfoDao.selectExamItemsInfoById(examItemsInfo.getId());
			    		if(!info.getPermisGw().equals(perGw))
			    		{
			    			//不相等，清洗所有数据
			    			Map<String,String> delMap=new HashMap<String,String>();
			    			delMap.put("empItemsId", examItemsInfo.getItemsId());
			    			delMap.put("testId", examItemsInfo.getId());
			    			examSubjectInfoDao.deleteAll(delMap);
			    		}
		    		examItemsInfo.setModifyUser(user.getId());
		    		examItemsInfo.setMtime(new Date());
		    		examItemsInfoDao.updateExamItemsInfoByField(examItemsInfo);
		    		Map<String,String> map =new HashMap<String,String>();
		    		map.put("empItemsId", examItemsInfo.getItemsId());
		    		map.put("testId", examItemsInfo.getId());
		    		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		map.put("startTime", myFmt1.format(examItemsInfo.getStartTime()));
		    		map.put("endTime", myFmt1.format(examItemsInfo.getEndTime()));
		    		eaxmLabsInfoService.updateLabsTime(map);
		    		flag="success";
			     }
		}else{
			flag="objnull";
		}
    	return flag;
    }
    

	@Override
	public String verifyUpdateExamItemsInfo(ExamItemsInfo examItemsInfo, User user) {
		String flag="";
		if(examItemsInfo!=null){
	    	List<ExamItemsInfo> eaxmList=examItemsInfoDao.selectsPerGw(examItemsInfo.getItemsId(),examItemsInfo.getId(),examItemsInfo.getType());
			String perGw=examItemsInfo.getPermisGw();//接收的岗位id
			List<String> strList=new ArrayList<>();
			int i=eaxmList.size();
			int j=0;
			HashSet<String> set=new HashSet<String>();
			StringBuilder strb=new StringBuilder();
			if(i>0){
				for (ExamItemsInfo itemsInfo : eaxmList) {
					 if(StringUtils.isNotBlank(itemsInfo.getPermisGw())){
						 j++;
						 if(i==j){
							 strb.append(itemsInfo.getPermisGw());
						 }else{
							 strb.append(itemsInfo.getPermisGw()+",");
						 }
					 }
				}
					
				//查询后的string类型的id和新增的id
				String post=strb.toString()+","+perGw;
				if(post.length()>32){
					String[]  strspost=post.split(",");
					for(int g=0,len=strspost.length;g<len;g++){
					    set.add(strspost[g].toString());
					}
					for(int y=0,len=strspost.length;y<len;y++){
					    strList.add(strspost[y].toString());
					}
				}
			}	
				 if(!(set.size()==strList.size())){
					 int sizes=strList.size()-set.size();
					 flag="false"+sizes;
			     }else{
		    		flag="success";
			     }
		}else{
			flag="objnull";
		}
    	return flag;
	}
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param examItemsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamItemsInfo(ExamItemsInfo examItemsInfo){
    
    	return examItemsInfoDao.updateExamItemsInfo(examItemsInfo);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ExamItemsInfo 
	 */	
    public ExamItemsInfo selectExamItemsInfoById(String id){
    
    	return examItemsInfoDao.selectExamItemsInfoById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ExamItemsInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<ExamItemsInfo> examItemsInfos =examItemsInfoDao.selectOnePageByMap(page,map);
	     	if(examItemsInfos!=null&&examItemsInfos.size()>0){
	     		page.setResult(examItemsInfos);
	     	}else{
	     		page.setResult(new ArrayList<ExamItemsInfo>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param examItemsInfo  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByExamItemsInfo(Page page,ExamItemsInfo examItemsInfo){
 		 List<ExamItemsInfo> examItemsInfos =examItemsInfoDao.selectOnePageByExamItemsInfo(page,examItemsInfo);
	     	if(examItemsInfos!=null&&examItemsInfos.size()>0){
	     		page.setResult(examItemsInfos);
	     	}else{
	     		page.setResult(new ArrayList<ExamItemsInfo>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamItemsInfo>
	 */	
     public List<ExamItemsInfo> selectAllByMap(Map<String,Object> map){
     	return examItemsInfoDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamItemsInfo>
	 */	
     public List<ExamItemsInfo> selectAllByExamItemsInfo(ExamItemsInfo examItemsInfo){
     
    	 return examItemsInfoDao.selectAllByExamItemsInfo(examItemsInfo);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamItemsInfo
	 */	
     public ExamItemsInfo selectObjectByMap(Map<String,Object> map){
     
    	 return examItemsInfoDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamItemsInfo
	 */	
     public ExamItemsInfo selectObjectByExamItemsInfo(ExamItemsInfo examItemsInfo){
     
     	return examItemsInfoDao.selectObjectByExamItemsInfo(examItemsInfo);
     }

	public String selectTestNum(String status,String projectId) {
		return examItemsInfoDao.selectTestNum(status,projectId);
	}

	@Override
	public String selectIdNum(String num) {
		
		return examItemsInfoDao.selectIdNum(num);
	}
	
	@Override
	public List<ExamItemsInfo> selectsPerGw(String itemsid,String testid,String type) {
		// TODO Auto-generated method stub
		return examItemsInfoDao.selectsPerGw(itemsid,testid,type);
	}

	@Override
	public List<ExamItemsInfo> selectItems(String year, String itemsid) {
		return examItemsInfoDao.selectItems(year, itemsid);
	}
	
	@Override
	public List<LimitPositionVo> selectLimitPositionNum(String itemsId,String subject,String postType,String jobName) {
		// TODO Auto-generated method stub
		return examItemsInfoDao.selectLimitPositionNum(itemsId, subject, postType, jobName);
	}

	@Override
	public List<LimitPositionVo> selectLimitPositionNumTrial(String itemsId, String subject, String postType,
			String jobName) {
		
		return examItemsInfoDao.selectLimitPositionNumTrial(itemsId, subject, postType, jobName);
	}

	
}
package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.dao.AttachementDao;
import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.service.AttachementService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.springframework.stereotype.Service;

/**
* Attachement业务接口实现层
* 上传附件信息表
* @author
*
*/

@Service("attachementService")
public class AttachementServiceImpl implements AttachementService {
	
    @Resource 
    private AttachementDao attachementDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param attachement
	 * @return 保存后的对象包括ID
	 */	
	public int saveAttachementByField(Attachement attachement){
	
		return attachementDao.saveAttachementByField(attachement);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param attachement 
	 * @return 保存后的对象包括ID
	 */	
	public int saveAttachement(Attachement attachement){
	
		return attachementDao.saveAttachement(attachement);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteAttachementById(String id){
    
    	return attachementDao.deleteAttachementById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteAttachementByObject(Attachement attachement){
    
    	return attachementDao.deleteAttachementByObject(attachement);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param attachement 
	 * @return 保存后的对象包括ID
	 */	
    public int updateAttachementByField(Attachement attachement){
    
    	return attachementDao.updateAttachementByField(attachement);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param attachement 
	 * @return 保存后的对象包括ID
	 */	
    public int updateAttachement(Attachement attachement){
    
    	return attachementDao.updateAttachement(attachement);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Attachement 
	 */	
    public Attachement selectAttachementById(String id){
    
    	return attachementDao.selectAttachementById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Attachement>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Attachement> attachements =attachementDao.selectOnePageByMap(page,map);
	     	if(attachements!=null&&attachements.size()>0){
	     		page.setResult(attachements);
	     	}else{
	     		page.setResult(new ArrayList<Attachement>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param attachement  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByAttachement(Page page,Attachement attachement){
 		 List<Attachement> attachements =attachementDao.selectOnePageByAttachement(page,attachement);
	     	if(attachements!=null&&attachements.size()>0){
	     		page.setResult(attachements);
	     	}else{
	     		page.setResult(new ArrayList<Attachement>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Attachement>
	 */	
     public List<Attachement> selectAllByMap(Map<String,Object> map){
     	return attachementDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Attachement>
	 */	
     public List<Attachement> selectAllByAttachement(Attachement attachement){
     
    	 return attachementDao.selectAllByAttachement(attachement);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Attachement
	 */	
     public Attachement selectObjectByMap(Map<String,Object> map){
     
    	 return attachementDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Attachement
	 */	
     public Attachement selectObjectByAttachement(Attachement attachement){
     
     	return attachementDao.selectObjectByAttachement(attachement);
     }

    @Override
    public Attachement saveOrUpdateAttachement(Map<String,Object> map) {
        String attId = map.get("attId").toString();
        String urlpath = map.get("urlpath").toString();
        String originalName = map.get("originalName").toString();
        String extension = map.get("extension").toString();
        StudentInfo si = (StudentInfo)map.get("si");
        
        Attachement attachment = attachementDao.selectAttachementById(attId);
        if (attachment == null) {
            attachment = new Attachement();
            attachment.setId(attId);
            attachment.setPath(urlpath);
            attachment.setUploadDate(new Date());
            attachment.setRealName(originalName);
            attachment.setFileType(extension);
            attachment.setUploadObject(si == null ? "" : si.getStudentType());
            attachment.setCreateUser(si == null ? "" : si.getId());
            attachment.setCtime(new Date());
            attachementDao.saveAttachement(attachment);
        } else {
            attachment.setPath(urlpath);
            attachment.setUploadDate(new Date());
            attachment.setRealName(originalName);
            attachment.setFileType(extension);
            attachment.setUploadObject(si == null ? "" : si.getStudentType());
            attachment.setModifyUser(si == null ? "" : si.getId());
            attachment.setMtime(new Date());
            attachementDao.updateAttachement(attachment);
        }
        attachment.setAlt(attachment.getRealName());//图片名称
        attachment.setPid(attachment.getId());//图片id
        attachment.setSrc(attachment.getPath());//图片地址
        
        return attachment;
    }

    @Override
    public void saveByStudentApply(StudentApplyInfo sai) {
        // 身份证
        if (StringUtil.isNotEmpty(sai.getIdcardAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getIdcardAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getIdcardAttId());
                att.setPath(sai.getIdcardUrlPath());
                att.setRealName(sai.getIdcardRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        // 教师资格证或教育学、心理学、普通话成绩单
        if (StringUtil.isNotEmpty(sai.getCerAchAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getCerAchAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getCerAchAttId());
                att.setPath(sai.getCerAchUrlPath());
                att.setRealName(sai.getCerAchRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        // 毕业证书或者就业推荐表
        if (StringUtil.isNotEmpty(sai.getGraRecomAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getGraRecomAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getGraRecomAttId());
                att.setPath(sai.getGraRecomUrlPath());
                att.setRealName(sai.getGraRecomRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        // 学历鉴定证明
        if (StringUtil.isNotEmpty(sai.getAcaQuaAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getAcaQuaAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getAcaQuaAttId());
                att.setPath(sai.getAcaQuaUrlPath());
                att.setRealName(sai.getAcaQuaRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        // 学位证书
        if (StringUtil.isNotEmpty(sai.getBacAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getBacAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getBacAttId());
                att.setPath(sai.getBacUrlPath());
                att.setRealName(sai.getBacRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        // 学位鉴定证明
        if (StringUtil.isNotEmpty(sai.getBacQuaAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getBacQuaAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getBacQuaAttId());
                att.setPath(sai.getBacQuaUrlPath());
                att.setRealName(sai.getBacQuaRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 

        // 计划生育证明
        if (StringUtil.isNotEmpty(sai.getFamPlanAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getFamPlanAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getFamPlanAttId());
                att.setPath(sai.getFamPlanUrlPath());
                att.setRealName(sai.getFamPlanRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        // 个人学习和工作情况总结
        if (StringUtil.isNotEmpty(sai.getStudyWorkAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getStudyWorkAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getStudyWorkAttId());
                att.setPath(sai.getStudyWorkUrlPath());
                att.setRealName(sai.getStudyWorkRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 

        // 暂缓就业协议书（已办理暂缓就业手续的毕业生必须提供）
        if (StringUtil.isNotEmpty(sai.getSuspendEmpAttId())) {
            Attachement att =
                    attachementDao.selectAttachementById(sai.getSuspendEmpAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getSuspendEmpAttId());
                att.setPath(sai.getSuspendEmpUrlPath());
                att.setRealName(sai.getSuspendEmpRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 

        // 出国留学
        if (StringUtil.isNotEmpty(sai.getAbroadStudyAttId())) {
            Attachement att = attachementDao
                    .selectAttachementById(sai.getAbroadStudyAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getAbroadStudyAttId());
                att.setPath(sai.getAbroadStudyUrlPath());
                att.setRealName(sai.getAbroadStudyRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        // 毕业成绩单
        if (StringUtil.isNotEmpty(sai.getTranscriptAttId())) {
            Attachement att = attachementDao
                    .selectAttachementById(sai.getTranscriptAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getTranscriptAttId());
                att.setPath(sai.getTranscriptUrlPath());
                att.setRealName(sai.getTranscriptRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        
        // 心理学成绩
        if (StringUtil.isNotEmpty(sai.getCerPsyAttId())) {
            Attachement att = attachementDao
                    .selectAttachementById(sai.getCerPsyAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getCerPsyAttId());
                att.setPath(sai.getCerPsyUrlPath());
                att.setRealName(sai.getCerPsyRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        
        // 普通话成绩
        if (StringUtil.isNotEmpty(sai.getCerManAttId())) {
            Attachement att = attachementDao
                    .selectAttachementById(sai.getCerManAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getCerManAttId());
                att.setPath(sai.getCerManUrlPath());
                att.setRealName(sai.getCerManRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        
        // 教育学成绩
        if (StringUtil.isNotEmpty(sai.getCerPedAttId())) {
            Attachement att = attachementDao
                    .selectAttachementById(sai.getCerPedAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getCerPedAttId());
                att.setPath(sai.getCerPedUrlPath());
                att.setRealName(sai.getCerPedRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        
        //教育教学能力测试
        if (StringUtil.isNotEmpty(sai.getCerAbiAttId())) {
            Attachement att = attachementDao
                    .selectAttachementById(sai.getCerAbiAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getCerAbiAttId());
                att.setPath(sai.getCerAbiUrlPath());
                att.setRealName(sai.getCerAbiRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
        
        // 教育实习证明
        if (StringUtil.isNotEmpty(sai.getCerShipAttId())) {
            Attachement att = attachementDao
                    .selectAttachementById(sai.getCerShipAttId());
            if (att == null) {
                att = new Attachement();
                att.setId(sai.getCerShipAttId());
                att.setPath(sai.getCerShipUrlPath());
                att.setRealName(sai.getCerShipRealName());
                att.setEmployItemsId(sai.getEmployItemsId());
                att.setStudentId(sai.getStudentId());
                att.setCreateUser(sai.getStudentId());
                att.setCtime(new Date());
                attachementDao.saveAttachement(att);
            }
        } 
    }
}
package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.dao.NoticeDao;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.util.Page;

/**
* Notice业务接口实现层
* 招聘公告
* @author
*
*/

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
    @Resource 
    private NoticeDao noticeDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param notice
	 * @return 保存后的对象包括ID
	 */	
	public int saveNoticeByField(Notice notice){
		if(StringUtils.isNotEmpty(notice.getId())){
			notice.setModifytime(new Date());
			return noticeDao.updateNoticeByField(notice) ;
		}else{
			return noticeDao.saveNoticeByField(notice);
		}
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param notice 
	 * @return 保存后的对象包括ID
	 */	
	public int saveNotice(Notice notice){
	
		return noticeDao.saveNotice(notice);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteNoticeById(String id){
    
    	return noticeDao.deleteNoticeById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteNoticeByObject(Notice notice){
    
    	return noticeDao.deleteNoticeByObject(notice);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param notice 
	 * @return 保存后的对象包括ID
	 */	
    public int updateNoticeByField(Notice notice){
    
    	return noticeDao.updateNoticeByField(notice);
    }

	/**
	 * 更新
	 * 只更新值不为空的字段
	 * @param notice
	 * @return 保存后的对象包括ID
	 */
	public int updateNoticeByFieldAndTheme(Notice notice){

		return noticeDao.updateNoticeByFieldAndTheme(notice);
	}
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param notice 
	 * @return 保存后的对象包括ID
	 */	
    public int updateNotice(Notice notice){
    
    	return noticeDao.updateNotice(notice);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Notice 
	 */	
    public Notice selectNoticeById(String id){
    
    	return noticeDao.selectNoticeById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Notice>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Notice> notices =noticeDao.selectOnePageByMap(page,map);
	     	if(notices!=null&&notices.size()>0){
	     		page.setResult(notices);
	     	}else{
	     		page.setResult(new ArrayList<Notice>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param notice  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByNotice(Page page,Notice notice){
 		 List<Notice> notices =noticeDao.selectOnePageByNotice(page,notice);
	     	if(notices!=null&&notices.size()>0){
	     		page.setResult(notices);
	     	}else{
	     		page.setResult(new ArrayList<Notice>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Notice>
	 */	
     public List<Notice> selectAllByMap(Map<String,Object> map){
     	return noticeDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Notice>
	 */	
     public List<Notice> selectAllByNotice(Notice notice){
     
    	 return noticeDao.selectAllByNotice(notice);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Notice
	 */	
     public Notice selectObjectByMap(Map<String,Object> map){
     
    	 return noticeDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Notice
	 */	
     public Notice selectObjectByNotice(Notice notice){
     
     	return noticeDao.selectObjectByNotice(notice);
     }

    @Override
    public List<Notice> selectEffectNotice(Map<String, Object> map) {
        return noticeDao.selectEffectNotice(map);
    }

	@Override
	public Notice selectObjectByThemeId(String themeId) {
		return noticeDao.selectObjectByThemeId(themeId);
	}
}
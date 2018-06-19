package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.Notice;
import org.jypj.dev.util.Page;

/**
* Notice业务接口层
* 招聘公告
* @author
*
*/

public interface NoticeService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param notice
	 * @return 
	 */	
	public int saveNoticeByField(Notice notice);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param notice 
	 * @return 
	 */	
	public int saveNotice(Notice notice);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteNoticeById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteNoticeByObject(Notice notice);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param notice 
	 * @return 保存后的对象包括ID
	 */	
    public int updateNoticeByField(Notice notice);

	/**
	 * 更新
	 * 只更新值不为空的字段
	 * @param notice
	 * @return 保存后的对象包括ID
	 */
	public int updateNoticeByFieldAndTheme(Notice notice);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param notice 
	 * @return 保存后的对象包括ID
	 */	
    public int updateNotice(Notice notice);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Notice 
	 */	
    public Notice selectNoticeById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Notice>
	 */	
     public Page selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByNotice(Page page, Notice notice);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Notice>
	 */	
     public List<Notice> selectAllByMap(Map<String, Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Notice>
	 */	
     public List<Notice> selectAllByNotice(Notice notice);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Notice
	 */	
     public Notice selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Notice
	 */	
     public Notice selectObjectByNotice(Notice notice);
     
     /**
      * 根据条件获取有效的招聘公告
      * @param map
      * @return
      */
     public List<Notice> selectEffectNotice(Map<String, Object> map);

	/**
	 *通过招聘主题id获取招聘公告，因为一个招聘公告对应一个招聘主题，因此只能查到一条记录
	 * @return
     */
	public Notice selectObjectByThemeId(String themeId) ;

}

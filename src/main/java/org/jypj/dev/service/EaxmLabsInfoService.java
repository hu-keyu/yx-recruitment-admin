package org.jypj.dev.service;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.EaxmLabsInfo;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* EaxmLabsInfo业务接口层
* 试室信息表
* @author
*
*/

public interface EaxmLabsInfoService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param eaxmLabsInfo
	 * @return 
	 */	
	public int saveEaxmLabsInfoByField(EaxmLabsInfo eaxmLabsInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param eaxmLabsInfo 
	 * @return 
	 */	
	public int saveEaxmLabsInfo(EaxmLabsInfo eaxmLabsInfo);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteEaxmLabsInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteEaxmLabsInfoByObject(EaxmLabsInfo eaxmLabsInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param eaxmLabsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateEaxmLabsInfoByField(EaxmLabsInfo eaxmLabsInfo);
    
    /**
     * 查詢試室號是否重複
     * @param testid
     * @param num
     * @return
     */
    public int selectLabNums(String testid,String num,String id);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param eaxmLabsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateEaxmLabsInfo(EaxmLabsInfo eaxmLabsInfo);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return EaxmLabsInfo 
	 */	
    public EaxmLabsInfo selectEaxmLabsInfoById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<EaxmLabsInfo>
	 */	
     public Page selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByEaxmLabsInfo(Page page, EaxmLabsInfo eaxmLabsInfo);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<EaxmLabsInfo>
	 */	
     public List<EaxmLabsInfo> selectAllByMap(Map<String, Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<EaxmLabsInfo>
	 */	
     public List<EaxmLabsInfo> selectAllByEaxmLabsInfo(EaxmLabsInfo eaxmLabsInfo);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  EaxmLabsInfo
	 */	
     public EaxmLabsInfo selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  EaxmLabsInfo
	 */	
     public EaxmLabsInfo selectObjectByEaxmLabsInfo(EaxmLabsInfo eaxmLabsInfo);
     
     
     /**
      * empItemsId 传入主题id
      * 获取某个主题考点的试室数量  
      * @param map
      * @return
      */
     public Map<String,Integer> getLabsByKaodian(Map<String,String> map);
     
     
     /**empItemsId 传入主题id testId 考点id  startTime开始时间  endTime 结束时间
       * 更新某个主题的考点试室的时间
      * @param map
      */
     public void updateLabsTime(Map<String,String> map);
     
     
     /**
      * 判断试室名称唯一
      * @param testid
      * @param num
      * @param id
      * @return
      */
     public int selectLabName(String testid,String labsname,String id);
     
     
     /**
      * 判断试室地址唯一
      * @param testid
      * @param num
      * @param id
      * @return
      */
     public int selectLabAddress(String testid,String labsname,String id);

}

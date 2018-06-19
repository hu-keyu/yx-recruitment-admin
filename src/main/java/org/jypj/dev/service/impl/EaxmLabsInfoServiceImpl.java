package org.jypj.dev.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.dao.EaxmLabsInfoDao;
import org.jypj.dev.entity.EaxmLabsInfo;
import org.jypj.dev.service.EaxmLabsInfoService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.RoomGanWeiVo;
import org.springframework.stereotype.Service;

/**
* EaxmLabsInfo业务接口实现层
* 试室信息表
* @author
*
*/

@Service("eaxmLabsInfoService")
public class EaxmLabsInfoServiceImpl implements EaxmLabsInfoService {
	
    @Resource 
    private EaxmLabsInfoDao eaxmLabsInfoDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param eaxmLabsInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveEaxmLabsInfoByField(EaxmLabsInfo eaxmLabsInfo){
	
		return eaxmLabsInfoDao.saveEaxmLabsInfoByField(eaxmLabsInfo);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param eaxmLabsInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveEaxmLabsInfo(EaxmLabsInfo eaxmLabsInfo){
	
		return eaxmLabsInfoDao.saveEaxmLabsInfo(eaxmLabsInfo);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteEaxmLabsInfoById(String id){
    
    	return eaxmLabsInfoDao.deleteEaxmLabsInfoById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteEaxmLabsInfoByObject(EaxmLabsInfo eaxmLabsInfo){
    
    	return eaxmLabsInfoDao.deleteEaxmLabsInfoByObject(eaxmLabsInfo);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param eaxmLabsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateEaxmLabsInfoByField(EaxmLabsInfo eaxmLabsInfo){
    
    	return eaxmLabsInfoDao.updateEaxmLabsInfoByField(eaxmLabsInfo);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param eaxmLabsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateEaxmLabsInfo(EaxmLabsInfo eaxmLabsInfo){
    
    	return eaxmLabsInfoDao.updateEaxmLabsInfo(eaxmLabsInfo);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return EaxmLabsInfo 
	 */	
    public EaxmLabsInfo selectEaxmLabsInfoById(String id){
    
    	return eaxmLabsInfoDao.selectEaxmLabsInfoById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<EaxmLabsInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<EaxmLabsInfo> eaxmLabsInfos =eaxmLabsInfoDao.selectOnePageByMap(page,map);
	     	if(eaxmLabsInfos!=null&&eaxmLabsInfos.size()>0){
	     		page.setResult(eaxmLabsInfos);
	     	}else{
	     		page.setResult(new ArrayList<EaxmLabsInfo>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param eaxmLabsInfo  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByEaxmLabsInfo(Page page,EaxmLabsInfo eaxmLabsInfo){
 		 List<EaxmLabsInfo> eaxmLabsInfos =eaxmLabsInfoDao.selectOnePageByEaxmLabsInfo(page,eaxmLabsInfo);
	     	if(eaxmLabsInfos!=null&&eaxmLabsInfos.size()>0){
	     		page.setResult(eaxmLabsInfos);
	     	}else{
	     		page.setResult(new ArrayList<EaxmLabsInfo>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<EaxmLabsInfo>
	 */	
     public List<EaxmLabsInfo> selectAllByMap(Map<String,Object> map){
     	return eaxmLabsInfoDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<EaxmLabsInfo>
	 */	
     public List<EaxmLabsInfo> selectAllByEaxmLabsInfo(EaxmLabsInfo eaxmLabsInfo){
     
    	 return eaxmLabsInfoDao.selectAllByEaxmLabsInfo(eaxmLabsInfo);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  EaxmLabsInfo
	 */	
     public EaxmLabsInfo selectObjectByMap(Map<String,Object> map){
     
    	 return eaxmLabsInfoDao.selectObjectByMap(map);
     }
     
     @Override
 	 public int selectLabNums(String testid, String num,String id) {
 		// TODO Auto-generated method stub
 		return eaxmLabsInfoDao.selectLabNums(testid, num,id);
 	 }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  EaxmLabsInfo
	 */	
     public EaxmLabsInfo selectObjectByEaxmLabsInfo(EaxmLabsInfo eaxmLabsInfo){
     
     	return eaxmLabsInfoDao.selectObjectByEaxmLabsInfo(eaxmLabsInfo);
     }

	@Override
	public Map<String, Integer> getLabsByKaodian(Map<String, String> map) {
		Map<String,Integer> dataMap=new HashMap<String,Integer>();
		List<RoomGanWeiVo> dataList=eaxmLabsInfoDao.getLabsByKaodian(map);
		if(dataList.size()>0)
		{
			for(RoomGanWeiVo vo:dataList)
			{
				dataMap.put(vo.getGanweiId(), vo.getNum());
			}
		}
		return dataMap;
	}
	@Override
	public void updateLabsTime(Map<String, String> map) {
		try {
			EaxmLabsInfo queryEaxmLabsInfo=new EaxmLabsInfo();
			queryEaxmLabsInfo.setEmpItemsId(map.get("empItemsId"));
			queryEaxmLabsInfo.setTestId(map.get("testId"));
			List<EaxmLabsInfo> dataList=eaxmLabsInfoDao.selectAllByEaxmLabsInfo(queryEaxmLabsInfo);
			SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(dataList.size()>0)
			{
				for(EaxmLabsInfo e:dataList)
				{
					e.setStartTime(myFmt1.parse(map.get("startTime")));
					e.setEndTime(myFmt1.parse(map.get("endTime")));
					eaxmLabsInfoDao.updateEaxmLabsInfo(e);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int selectLabName(String testid, String labsname, String id) {
		
		return eaxmLabsInfoDao.selectLabName(testid, labsname, id);
	}

	@Override
	public int selectLabAddress(String testid, String labsname, String id) {
		
		return eaxmLabsInfoDao.selectLabAddress(testid, labsname, id);
	}

}
package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.PositionDomain;
import org.jypj.dev.dao.PositionDomainDao;
import org.jypj.dev.service.PositionDomainService;
import org.jypj.dev.util.Page;

/**
* PositionDomain业务接口实现层
* 学校岗位专业要求表
* @author
*
*/

@Service("positionDomainService")
public class PositionDomainServiceImpl implements PositionDomainService {
	
    @Resource 
    private PositionDomainDao positionDomainDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param positionDomain
	 * @return 保存后的对象包括ID
	 */	
	public int savePositionDomainByField(PositionDomain positionDomain){
	
		return positionDomainDao.savePositionDomainByField(positionDomain);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param positionDomain 
	 * @return 保存后的对象包括ID
	 */	
	public int savePositionDomain(PositionDomain positionDomain){
	
		return positionDomainDao.savePositionDomain(positionDomain);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePositionDomainById(String id){
    
    	return positionDomainDao.deletePositionDomainById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deletePositionDomainByObject(PositionDomain positionDomain){
    
    	return positionDomainDao.deletePositionDomainByObject(positionDomain);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param positionDomain 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePositionDomainByField(PositionDomain positionDomain){
    
    	return positionDomainDao.updatePositionDomainByField(positionDomain);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param positionDomain 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePositionDomain(PositionDomain positionDomain){
    
    	return positionDomainDao.updatePositionDomain(positionDomain);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return PositionDomain 
	 */	
    public PositionDomain selectPositionDomainById(String id){
    
    	return positionDomainDao.selectPositionDomainById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<PositionDomain>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<PositionDomain> positionDomains =positionDomainDao.selectOnePageByMap(page,map);
	     	if(positionDomains!=null&&positionDomains.size()>0){
	     		page.setResult(positionDomains);
	     	}else{
	     		page.setResult(new ArrayList<PositionDomain>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param positionDomain  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByPositionDomain(Page page,PositionDomain positionDomain){
 		 List<PositionDomain> positionDomains =positionDomainDao.selectOnePageByPositionDomain(page,positionDomain);
	     	if(positionDomains!=null&&positionDomains.size()>0){
	     		page.setResult(positionDomains);
	     	}else{
	     		page.setResult(new ArrayList<PositionDomain>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PositionDomain>
	 */	
     public List<PositionDomain> selectAllByMap(Map<String,Object> map){
     	return positionDomainDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PositionDomain>
	 */	
     public List<PositionDomain> selectAllByPositionDomain(PositionDomain positionDomain){
     
    	 return positionDomainDao.selectAllByPositionDomain(positionDomain);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PositionDomain
	 */	
     public PositionDomain selectObjectByMap(Map<String,Object> map){
     
    	 return positionDomainDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PositionDomain
	 */	
     public PositionDomain selectObjectByPositionDomain(PositionDomain positionDomain){
     
     	return positionDomainDao.selectObjectByPositionDomain(positionDomain);
     }

	@Override
	public void saveList(List<PositionDomain> positionDomains) {
		positionDomainDao.saveList(positionDomains);
	}

	@Override
	public int selectCountBySpecialty(String specialty) {
		return positionDomainDao.selectCountBySpecialty(specialty);
	}
}
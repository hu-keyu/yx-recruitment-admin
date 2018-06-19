package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.PostSpec;
import org.jypj.dev.dao.PostSpecDao;
import org.jypj.dev.service.PostSpecService;
import org.jypj.dev.util.Page;

/**
* PostSpec业务接口实现层
* 岗位专业
* @author
*
*/

@Service("postSpecService")
public class PostSpecServiceImpl implements PostSpecService {
	
    @Resource 
    private PostSpecDao postSpecDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param postSpec
	 * @return 保存后的对象包括ID
	 */	
	public int savePostSpecByField(PostSpec postSpec){
	
		return postSpecDao.savePostSpecByField(postSpec);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param postSpec 
	 * @return 保存后的对象包括ID
	 */	
	public int savePostSpec(PostSpec postSpec){
	
		return postSpecDao.savePostSpec(postSpec);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePostSpecById(String id){
    
    	return postSpecDao.deletePostSpecById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deletePostSpecByObject(PostSpec postSpec){
    
    	return postSpecDao.deletePostSpecByObject(postSpec);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param postSpec 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePostSpecByField(PostSpec postSpec){
    
    	return postSpecDao.updatePostSpecByField(postSpec);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param postSpec 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePostSpec(PostSpec postSpec){
    
    	return postSpecDao.updatePostSpec(postSpec);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return PostSpec 
	 */	
    public PostSpec selectPostSpecById(String id){
    
    	return postSpecDao.selectPostSpecById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<PostSpec>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<PostSpec> postSpecs =postSpecDao.selectOnePageByMap(page,map);
	     	if(postSpecs!=null&&postSpecs.size()>0){
	     		page.setResult(postSpecs);
	     	}else{
	     		page.setResult(new ArrayList<PostSpec>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param postSpec  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByPostSpec(Page page,PostSpec postSpec){
 		 List<PostSpec> postSpecs =postSpecDao.selectOnePageByPostSpec(page,postSpec);
	     	if(postSpecs!=null&&postSpecs.size()>0){
	     		page.setResult(postSpecs);
	     	}else{
	     		page.setResult(new ArrayList<PostSpec>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PostSpec>
	 */	
     public List<PostSpec> selectAllByMap(Map<String,Object> map){
     	return postSpecDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PostSpec>
	 */	
     public List<PostSpec> selectAllByPostSpec(PostSpec postSpec){
     
    	 return postSpecDao.selectAllByPostSpec(postSpec);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PostSpec
	 */	
     public PostSpec selectObjectByMap(Map<String,Object> map){
     
    	 return postSpecDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PostSpec
	 */	
     public PostSpec selectObjectByPostSpec(PostSpec postSpec){
     
     	return postSpecDao.selectObjectByPostSpec(postSpec);
     }

	@Override
	public void saveBatch(List<PostSpec> list) {
		postSpecDao.saveBatch(list) ;
	}

	@Override
	public void deleteByPostsetId(String postsetId) {
		postSpecDao.deleteByPostsetId(postsetId) ;
	}

	@Override
	public int selectCountBySpecialty(String specialty) {
		return postSpecDao.selectCountBySpecialty(specialty);
	}
}
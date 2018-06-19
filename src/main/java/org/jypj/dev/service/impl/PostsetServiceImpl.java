package org.jypj.dev.service.impl;


import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.PostSpec;
import org.jypj.dev.service.PostSpecService;
import org.jypj.dev.util.StringUtil;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.dao.PostsetDao;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.util.Page;

/**
* Postset业务接口实现层
* 招聘岗位
* @author
*
*/

@Service("postsetService")
public class PostsetServiceImpl implements PostsetService {
	
    @Resource 
    private PostsetDao postsetDao;

	@Resource
	private PostSpecService postSpecService ;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param postset
	 * @return 保存后的对象包括ID
	 */	
	public Result savePostsetByField(Postset postset,Result r){
		int num = 0 ;
		String postId = "" ;
		if(StringUtils.isNotEmpty(postset.getId())){
			/*Map<String,String> map1 = new HashMap<String,String>() ;
			map1.put("id",postset.getId()) ;
			map1.put("code",postset.getPostCode()) ;*/
			Map<String,String> map2 = new HashMap<String,String>() ;
			map2.put("id",postset.getId()) ;
			map2.put("name",postset.getPostName()) ;
			//Postset pcode = postsetDao.selectByCodeAndId(map1) ;
			Postset pname = postsetDao.selectByNameAndId(map2) ;
			/*if(pcode!=null){
				r.setSuccess(false);
				r.setMsg("岗位代码不可重复！");
			}else */
			if(pname!=null){
				r.setSuccess(false);
				r.setMsg("岗位名称不可重复！");
			}else{
				postId = postset.getId() ;
				postset.setModifytime(new Date());
				num = postsetDao.updatePostset(postset) ;
				postSpecService.deleteByPostsetId(postset.getId());
				List<PostSpec> list = new ArrayList<PostSpec>() ;
				if(StringUtils.isNotEmpty(postset.getLimitProfession())){
					List<String> specList = Arrays.asList(postset.getLimitProfession().split(",")) ;
					for(String s : specList){
						PostSpec p = new PostSpec() ;
						p.setPostId(postId);
						p.setSpecialtyId(s);
						list.add(p) ;
					}
					postSpecService.saveBatch(list);
				}
				r.setMsg("修改成功");
				r.setSuccess(true);
			}
		}else{
			//List<Postset> pcode = postsetDao.selectByCode(postset.getPostCode()) ;
			List<Postset> pname = postsetDao.selectByName(postset.getPostName()) ;
			/*if(pcode.size()>0){
				r.setSuccess(false);
				r.setMsg("岗位代码不可重复！");
			}else */
			if(pname.size()>0){
				r.setSuccess(false);
				r.setMsg("岗位名称不可重复！");
			}else {
				postId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
				postset.setId(postId);
				num = postsetDao.savePostsetByField(postset);
				List<PostSpec> list = new ArrayList<PostSpec>();
				if (StringUtils.isNotEmpty(postset.getLimitProfession())) {
					List<String> specList = Arrays.asList(postset.getLimitProfession().split(","));
					for (String s : specList) {
						PostSpec p = new PostSpec();
						p.setPostId(postId);
						p.setSpecialtyId(s);
						list.add(p);
					}
					postSpecService.saveBatch(list);
				}
				r.setMsg("添加成功！");
				r.setSuccess(true);
			}
		}
		return r ;
	}

	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param postset 
	 * @return 保存后的对象包括ID
	 */	
	public int savePostset(Postset postset){
	
		return postsetDao.savePostset(postset);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePostsetById(String id){
    
    	return postsetDao.deletePostsetById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deletePostsetByObject(Postset postset){
    
    	return postsetDao.deletePostsetByObject(postset);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param postset 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePostsetByField(Postset postset){
    
    	return postsetDao.updatePostsetByField(postset);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param postset 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePostset(Postset postset){
    
    	return postsetDao.updatePostset(postset);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Postset 
	 */	
    public Postset selectPostsetById(String id){
    
    	return postsetDao.selectPostsetById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Postset>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Postset> postsets =postsetDao.selectOnePageByMap(page,map);
	     	if(postsets!=null&&postsets.size()>0){
	     		page.setResult(postsets);
	     	}else{
	     		page.setResult(new ArrayList<Postset>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param postset  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByPostset(Page page,Postset postset){
 		 List<Postset> postsets =postsetDao.selectOnePageByPostset(page,postset);
	     	if(postsets!=null&&postsets.size()>0){
	     		page.setResult(postsets);
	     	}else{
	     		page.setResult(new ArrayList<Postset>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Postset>
	 */	
     public List<Postset> selectAllByMap(Map<String,Object> map){
     	return postsetDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Postset>
	 */	
     public List<Postset> selectAllByPostset(Postset postset){
     
    	 return postsetDao.selectAllByPostset(postset);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Postset
	 */	
     public Postset selectObjectByMap(Map<String,Object> map){
     
    	 return postsetDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Postset
	 */	
     public Postset selectObjectByPostset(Postset postset){
     
     	return postsetDao.selectObjectByPostset(postset);
     }

	@Override
	public void deleteBatch(String ids) {
		List<String> idlist = Arrays.asList(ids.split(",")) ;
		postsetDao.deleteBatch(idlist) ;
	}

	@Override
	public Result validPostset(String id, String code, String name) {
		Result r = new Result() ;
		if(StringUtils.isNotEmpty(id)){
			/*Map<String,String> map1 = new HashMap<String,String>() ;
			map1.put("id",id) ;
			map1.put("code",code) ;*/
			Map<String,String> map2 = new HashMap<String,String>() ;
			map2.put("id",id) ;
			map2.put("name",name) ;
			//Postset pcode = postsetDao.selectByCodeAndId(map1) ;
			Postset pname = postsetDao.selectByNameAndId(map2) ;
			/*if(pcode!=null){
				r.setSuccess(false);
				r.setMsg("岗位代码不可重复！");
			}else */
			if(pname!=null){
				r.setSuccess(false);
				r.setMsg("岗位名称不可重复！");
			}else{
				r.setSuccess(true);
				r.setMsg("没有重复！");
			}
		}else{
			//List<Postset> pcode = postsetDao.selectByCode(code) ;
			List<Postset> pname = postsetDao.selectByName(name) ;
			/*if(pcode.size()>0){
				r.setSuccess(false);
				r.setMsg("岗位代码不可重复！");
			}else */
			if(pname.size()>0){
				r.setSuccess(false);
				r.setMsg("岗位名称不可重复！");
			}else {
				r.setSuccess(true);
				r.setMsg("没有重复！");
			}
		}
		return r ;
	}


	@Override
	public List<Postset> selectAllPostset() {
	
		return postsetDao.selectAllPostset();
	}
}
package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.code.Result;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.Specialty;
import org.jypj.dev.dao.SpecialtyDao;
import org.jypj.dev.service.SpecialtyService;
import org.jypj.dev.util.Page;

/**
* Specialty业务接口实现层
* 招聘专业
* @author
*
*/

@Service("specialtyService")
public class SpecialtyServiceImpl implements SpecialtyService {
	
    @Resource 
    private SpecialtyDao specialtyDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param specialty
	 * @return 保存后的对象包括ID
	 */	
	public Result saveSpecialtyByField(Specialty specialty){
		Result r = new Result() ;
		if(1==specialty.getStorey()){
			if("00".equals(specialty.getCode1())){
				r.setMsg("一级学科代码不能填写00");
				r.setSuccess(false);
				return r ;
			}
			if(!isRightCode(specialty.getCode1())){
				r.setMsg("一级学科代码请填写两位数字！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName1().length()>50){
				r.setMsg("一级学科名称不能多于五十个字符！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName1().length()==0){
				r.setMsg("一级学科名称不能为空！");
				r.setSuccess(false);
				return r ;
			}
			specialty.setCode(specialty.getCode1());
			specialty.setName(specialty.getName1());
		}
		if(2==specialty.getStorey()){
			if(StringUtils.isEmpty(specialty.getFirstSbjCode())){
				r.setMsg("请选择一级学科！");
				r.setSuccess(false);
				return r ;
			}
			if("00".equals(specialty.getCode2())){
				r.setMsg("二级学科代码不能填写00");
				r.setSuccess(false);
				return r ;
			}
			if(!isRightCode(specialty.getCode2())){
				r.setMsg("二级学科代码请填写两位数字！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName2().length()>50){
				r.setMsg("二级学科名称不能多于五十个字符！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName2().length()==0){
				r.setMsg("二级学科名称不能为空！");
				r.setSuccess(false);
				return r ;
			}
			specialty.setPid(getIdByCode(specialty.getFirstSbjCode()));
			specialty.setCode(specialty.getCode2());
			specialty.setName(specialty.getName2());
		}
		if(3==specialty.getStorey()){
			if(StringUtils.isEmpty(specialty.getSecondSbjCode())){
				r.setMsg("请选择二级学科！");
				r.setSuccess(false);
				return r ;
			}
			if("00".equals(specialty.getCode3())){
				r.setMsg("专业代码不能填写00");
				r.setSuccess(false);
				return r ;
			}
			if(!isRightCode(specialty.getCode3())){
				r.setMsg("专业代码请填写两位数字！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName3().length()>50){
				r.setMsg("专业名称不能多于五十个字符！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName3().length()==0){
				r.setMsg("专业名称不能为空！");
				r.setSuccess(false);
				return r ;
			}
			specialty.setPid(getIdByCode(specialty.getSecondSbjCode()));
			specialty.setCode(specialty.getCode3());
			specialty.setName(specialty.getName3());
		}
		String code = specialty.getPrefix()+specialty.getCode() ;
		specialty.setCode(code);
		String id = getIdByCode(code) ;
		specialty.setId(id);
		Specialty s = selectSpecialtyById(id) ;
		if(s!=null){
			r.setSuccess(false);
			if(1==specialty.getStorey()){
				r.setMsg("添加失败，一级学科代码已存在！");
			}else if(2==specialty.getStorey()){
				r.setMsg("添加失败，二级学科代码已存在！");
			}else{
				r.setMsg("添加失败，专业代码已存在！");
			}
			return r ;
		}else{
			try {
				specialtyDao.saveSpecialtyByField(specialty);
				r.setSuccess(true);
				r.setMsg("添加成功！");
				return r ;
			} catch (Exception e) {
				e.printStackTrace();
				r.setSuccess(false);
				r.setMsg("添加失败");
				return r ;
			}
		}
	}
	
	private String getIdByCode(String code){
		String zero = "0000000000000000000000000000000" ;
		return code+zero.substring(0,zero.length()-(code.length()-1)) ;
	}
	
	private boolean isRightCode(String code){
		Pattern p = Pattern.compile("[0-9]{2}");  
		Matcher m = p.matcher(code);  
		return m.matches(); 
	}

	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param specialty 
	 * @return 保存后的对象包括ID
	 */	
	public int saveSpecialty(Specialty specialty){
	
		return specialtyDao.saveSpecialty(specialty);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteSpecialtyById(String id){

    	return specialtyDao.deleteSpecialtyById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param specialty 主键ID
	 * @return 
	 */	
    public int deleteSpecialtyByObject(Specialty specialty){
    
    	return specialtyDao.deleteSpecialtyByObject(specialty);
    }
    
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param specialty 
	 * @return 保存后的对象包括ID
	 */	
    public Result updateSpecialtyByField(Specialty specialty){
		Result r = new Result() ;
		String code = "" ;
		if(1==specialty.getStorey()){
			if("00".equals(specialty.getCode1())){
				r.setMsg("一级学科代码不能填写00");
				r.setSuccess(false);
				return r ;
			}
			if(!isRightCode(specialty.getCode1())){
				r.setMsg("一级学科代码请填写两位数字！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName1().length()>50){
				r.setMsg("一级学科名称不能多于五十个字符！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName1().length()==0){
				r.setMsg("一级学科名称不能为空！");
				r.setSuccess(false);
				return r ;
			}
			code = specialty.getPrefix()+specialty.getCode1() ;
			specialty.setName(specialty.getName1());
			specialty.setCode(code);
		}
		if(2==specialty.getStorey()){
			if("00".equals(specialty.getCode2())){
				r.setMsg("二级学科代码不能填写00");
				r.setSuccess(false);
				return r ;
			}
			if(!isRightCode(specialty.getCode2())){
				r.setMsg("二级学科代码请填写两位数字！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName2().length()>50){
				r.setMsg("二级学科名称不能多于五十个字符！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName2().length()==0){
				r.setMsg("二级学科名称不能为空！");
				r.setSuccess(false);
				return r ;
			}
			code = specialty.getPrefix()+specialty.getCode2() ;
			specialty.setName(specialty.getName2());
			specialty.setCode(code);
		}
		if(3==specialty.getStorey()){
			if("00".equals(specialty.getCode3())){
				r.setMsg("专业代码不能填写00");
				r.setSuccess(false);
				return r ;
			}
			if(!isRightCode(specialty.getCode3())){
				r.setMsg("专业代码请填写两位数字！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName3().length()>50){
				r.setMsg("专业名称不能多于五十个字符！");
				r.setSuccess(false);
				return r ;
			}
			if(specialty.getName3().length()==0){
				r.setMsg("专业名称不能为空！");
				r.setSuccess(false);
				return r ;
			}
			code = specialty.getPrefix()+specialty.getCode3() ;
			specialty.setName(specialty.getName3());
			specialty.setCode(code);
		}
		specialty.setCode(code);
		String nid = getIdByCode(code) ;
		if(!nid.equals(specialty.getId())){
			Specialty sp = selectSpecialtyById(nid) ;
			if(sp!=null){
				r.setSuccess(false);
				if(1==specialty.getStorey()){
					r.setMsg("修改失败，一级学科代码重复！");
				}else if(2==specialty.getStorey()){
					r.setMsg("修改失败，二级学科代码重复！");
				}else{
					r.setMsg("修改失败，专业代码重复！");
				}
				return r ;
			}else{
				try {
					//specialty.setId(nid);
					specialty.setNewId(nid);
					specialtyDao.updateSpecialtyByField(specialty);
					r.setSuccess(true);
					r.setMsg("修改成功！");
					return r ;
				} catch (Exception e) {
					e.printStackTrace();
					r.setSuccess(false);
					r.setMsg("修改失败");
					return r ;
				}
			}
		}else{
			try {
				specialtyDao.updateSpecialtyByField(specialty);
				r.setSuccess(true);
				r.setMsg("修改成功！");
				return r ;
			} catch (Exception e) {
				e.printStackTrace();
				r.setSuccess(false);
				r.setMsg("修改失败");
				return r ;
			}
		}
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param specialty 
	 * @return 保存后的对象包括ID
	 */	
    public int updateSpecialty(Specialty specialty){
    
    	return specialtyDao.updateSpecialty(specialty);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Specialty 
	 */	
    public Specialty selectSpecialtyById(String id){
    
    	return specialtyDao.selectSpecialtyById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Specialty>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 int storey = 3 ;
     	 if(StringUtils.isNotEmpty(map.get("secondSbjCode").toString())){//如果二级学科条件不为空，则直接判断下面有没有第三级专业
     		 List<Specialty> thirdList = selectByStoreyAndCode(map.get("secondSbjCode").toString(),3) ;
     		 if(thirdList.size()>0){ //如果三级不为空，层级就为三
     			storey = 3 ;
     		 }else{//如果三级为空，层级就为二
     			 storey = 2 ;
     		 }
     	 }else if(StringUtils.isNotEmpty(map.get("firstSbjCode").toString())){ 
     		 List<Specialty> thirdList2 = selectByStoreyAndCode(map.get("firstSbjCode").toString(), 3) ;
     		 List<Specialty> secondList = selectByStoreyAndCode(map.get("firstSbjCode").toString(),2) ;
     		 if(thirdList2.size()>0){//如果三级不为空，则层级为三
     			 storey = 3 ;
     		 }else if(secondList.size()>0){//如果二级不为空，则层级为二
     			 storey = 2 ;
     		 }else{//如果二级为空，则层级为一
     			 storey = 1 ;
     		 }
     	 }else{//如果一级学科和二级学科的条件都没有，就直接查第三级专业，层级定位三
     		 	storey = 3 ;
		 }
     	 map.put("storey", storey) ;
     	 List<Specialty> specialtys =specialtyDao.selectOnePageByMap(page,map);
		 if(specialtys!=null&&specialtys.size()>0){
			 page.setResult(specialtys);
		 }else{
			 page.setResult(new ArrayList<Specialty>());
		 }
		 page.setStorey(storey);
		 return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param specialty  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageBySpecialty(Page page,Specialty specialty){
 		 List<Specialty> specialtys =specialtyDao.selectOnePageBySpecialty(page,specialty);
	     	if(specialtys!=null&&specialtys.size()>0){
	     		page.setResult(specialtys);
	     	}else{
	     		page.setResult(new ArrayList<Specialty>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Specialty>
	 */	
     public List<Specialty> selectAllByMap(Map<String,Object> map){
     	return specialtyDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Specialty>
	 */	
     public List<Specialty> selectAllBySpecialty(Specialty specialty){
     
    	 return specialtyDao.selectAllBySpecialty(specialty);
     }
		
	 /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Specialty
	 */	
     public Specialty selectObjectByMap(Map<String,Object> map){
     
    	 return specialtyDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Specialty
	 */	
     public Specialty selectObjectBySpecialty(Specialty specialty){
     
     	return specialtyDao.selectObjectBySpecialty(specialty);
     }

	@Override
	public void deleteBatch(String ids) {
		List<String> idlist = Arrays.asList(ids.split(",")) ;
		specialtyDao.deleteBatch(idlist) ;
	}

	@Override
	public List<Specialty> findListByStorey(int storey) {
		return specialtyDao.findListByStorey(storey);
	}

    @Override
    public List<Specialty> selectByKeyword(Map<String, Object> map) {
        return specialtyDao.selectByKeyword(map);
    }

    @Override
    public List<Specialty> getSpecialListByLv(Map<String, Object> map) {
        return specialtyDao.getSpecialListByLv(map);
    }

	@Override
	public List<Specialty> selectByName(String name) {
		return specialtyDao.selectByName(name);
	}

	@Override
	public List<Specialty> selectByStoreyAndCode(String code, int storey) {
		Map<String,Object> map = new HashMap<String, Object>() ;
		map.put("code", code) ;
		map.put("storey", storey) ;
		return specialtyDao.selectByStoreyAndCode(map) ;
	}

	@Override
	public List<Specialty> selectSecondByFirstSbjCode(String code) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("code", code) ;
		map.put("storey", 2) ;
		return specialtyDao.selectByStoreyAndCode(map);
	}

	@Override
	public List<Specialty> selectFirstByType(String type) {
		// TODO Auto-generated method stub
		return specialtyDao.selectFirstByType(type) ;
	}

	
}
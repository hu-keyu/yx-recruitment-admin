package org.jypj.dev.dao;

import java.util.List;
import java.util.Map;

import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ApplicantsVo;
import org.jypj.dev.vo.VolunteersVo;

public interface VolunteersDao {
     /**
      * 按照条件查询填报志愿
      * @param page  分页
      * @param map   过滤条件
      * @return
      */
     public List<VolunteersVo> searchVolunteerPageByMap(Page page, Map<String, Object> map);
    
}

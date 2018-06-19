package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ApplicantsVo;

public interface VolunteersService {
    /**
     * 根据招聘项目、岗位、单位、年份查询
     * @param page
     * @param map
     * @return
     */
    public Page searchVolunteerPageByMap(Page page, Map<String, Object> map);
}

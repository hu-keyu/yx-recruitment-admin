package org.jypj.dev.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.dao.VolunteersDao;
import org.jypj.dev.service.VolunteersService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.VolunteersVo;
import org.springframework.stereotype.Service;

@Service("volunteersService")
public class VolunteersServiceImpl implements VolunteersService {
	@Resource
	private VolunteersDao volunteersDao;
	
    @Override
    public Page searchVolunteerPageByMap(Page page, Map<String, Object> map) {
        List<VolunteersVo> volunteers = volunteersDao.searchVolunteerPageByMap(page, map);
        if(volunteers !=null && volunteers.size()>0){
            page.setResult(volunteers);
        }else{
            page.setResult(new ArrayList<VolunteersVo>());
        }
        return page;
    }
}

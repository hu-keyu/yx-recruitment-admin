package org.jypj.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.entity.ExamLectureGroup;
import org.jypj.dev.service.ExamLectureGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ExamLectureGroup控制器
 * @author ChenYu
 *
 */
@Controller
@RequestMapping("examLectureGroupController")
public class ExamLectureGroupController {
	
    @Resource 
    private ExamLectureGroupService examLectureGroupService;
    
    @RequestMapping(value="toExamLectureGroupList.action")
    public String toExamLectureGroupList(){
    
    	return "";
    }
    
    /**
	 * 查询所有数据接口
	 * @return code =-1 表示有异常 code=-2 表示没有数据 datas 菜单数据
	 */
	@RequestMapping("selectAllExamLectureGroup.action")
	@ResponseBody
	public Map<String, Object> selectAllExamLectureGroup() {
		Map<String, Object> map = new HashMap<>();
		int code = 0;
		try {
			List<ExamLectureGroup> examLectureGroups = examLectureGroupService.selectAllByMap(new HashMap<String, Object>());
			if (examLectureGroups.size() == 0) {
				code = -2;
			}
			map.put("datas", examLectureGroups);
		} catch (Exception e) {
			code = -1;
		}
		map.put("code", code);
		return map;
	}
}
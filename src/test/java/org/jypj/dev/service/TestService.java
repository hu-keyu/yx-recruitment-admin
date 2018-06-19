package org.jypj.dev.service;

import java.util.List;

import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;

public interface TestService {

	/**
	 * 查询所有岗位
	 * @return
	 */
	public List<Postset> selectAllPostset();
	/**
	 * 查询所有主题
	 * @return
	 */
	public List<Theme> selectAllTheme();
	/**
	 * 查询所有招聘单位
	 * @return
	 */
	public List<Dictionary> selectAllSchool();
	
	public int saveStudents(List<StudentInfo> studentInfos,List<StudentApplyInfo> studentApplyInfos);
	
	public int deleteTestStudents(String createUser);
	public int updateEmploy(String employItemsId,String isEmploy);
}

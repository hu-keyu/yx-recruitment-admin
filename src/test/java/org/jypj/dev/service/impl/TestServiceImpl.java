package org.jypj.dev.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jypj.dev.dao.TestDictionaryDao;
import org.jypj.dev.dao.TestGradeDao;
import org.jypj.dev.dao.TestPostsetDao;
import org.jypj.dev.dao.TestStudentApplyInfoDao;
import org.jypj.dev.dao.TestStudentInfoDao;
import org.jypj.dev.dao.TestThemeDao;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.service.TestService;
import org.jypj.dev.test.TestConstant;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Resource
	public TestPostsetDao testPostsetDao;
	@Resource
	public TestThemeDao testThemeDao;
	@Resource
	public TestDictionaryDao testDictionaryDao;
	@Resource
	public TestStudentInfoDao testStudentInfoDao;
	@Resource
	public TestStudentApplyInfoDao testStudentApplyInfoDao;
	@Resource
	public TestGradeDao testGradeDao;

	@Override
	public List<Postset> selectAllPostset() {
		return testPostsetDao.selectAllPostset();
	}
	@Override
	public List<Theme> selectAllTheme(){
		return testThemeDao.selectAllTheme();
	}
	@Override
	public List<Dictionary> selectAllSchool() {
		return testDictionaryDao.selectAllByCode(TestConstant.CODE_DWDM);
	}
	@Override
	public int saveStudents(List<StudentInfo> studentInfos,List<StudentApplyInfo> studentApplyInfos) {
		int i = 0;
		for(StudentInfo studentInfo:studentInfos){
			i+=testStudentInfoDao.saveStudentInfo(studentInfo);
		}
		for(StudentApplyInfo studentApplyInfo:studentApplyInfos){
			i+=testStudentApplyInfoDao.saveStudentApplyInfo(studentApplyInfo);
		}
		return i;
	}
	@Override
	public int deleteTestStudents(String createUser) {
		int i = testStudentInfoDao.deleteTestStudentInfo(createUser);
		int x = testStudentApplyInfoDao.deleteTestStudentApplyInfo(createUser);
		return i+x;
	}
	@Override
	public int updateEmploy(String employItemsId,String isEmploy) {
		return testGradeDao.updateEmploy(employItemsId,isEmploy);
	}

}

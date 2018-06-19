package org.jypj.dev.dao;

import org.jypj.dev.entity.StudentInfo;

public interface TestStudentInfoDao {
	public int saveStudentInfo(StudentInfo studentInfo);
	public int deleteTestStudentInfo(String createUser);
}

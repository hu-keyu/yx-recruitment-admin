package org.jypj.dev.dao;

import org.jypj.dev.entity.StudentApplyInfo;

public interface TestStudentApplyInfoDao {
	public int saveStudentApplyInfo(StudentApplyInfo studentApplyInfo);
	public int deleteTestStudentApplyInfo(String createUser);
}

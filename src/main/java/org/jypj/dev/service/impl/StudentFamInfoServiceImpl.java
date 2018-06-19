package org.jypj.dev.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.StudentFamInfo;
import org.jypj.dev.dao.StudentFamInfoDao;
import org.jypj.dev.service.StudentFamInfoService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;

/**
 * StudentFamInfo业务接口实现层 学生家庭情况信息
 * 
 * @author
 *
 */

@Service("studentFamInfoService")
public class StudentFamInfoServiceImpl implements StudentFamInfoService {

	@Resource
	private StudentFamInfoDao studentFamInfoDao;

	/**
	 * 保存 字段为空的不存防止覆盖存在默认值的字段
	 * 
	 * @param studentFamInfo
	 * @return 保存后的对象包括ID
	 */
	public int saveStudentFamInfoByField(StudentFamInfo studentFamInfo) {

		return studentFamInfoDao.saveStudentFamInfoByField(studentFamInfo);
	}

	/**
	 * 保存 所有字段全都保存
	 * 
	 * @param studentFamInfo
	 * @return 保存后的对象包括ID
	 */
	public int saveStudentFamInfo(StudentFamInfo studentFamInfo) {

		return studentFamInfoDao.saveStudentFamInfo(studentFamInfo);
	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 *            主键ID
	 * @return 删除记录数
	 */
	public int deleteStudentFamInfoById(String id) {

		return studentFamInfoDao.deleteStudentFamInfoById(id);
	}

	/**
	 * 根据对象删除
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	public int deleteStudentFamInfoByObject(StudentFamInfo studentFamInfo) {

		return studentFamInfoDao.deleteStudentFamInfoByObject(studentFamInfo);
	}

	/**
	 * 更新 只更新值不为空的字段
	 * 
	 * @param studentFamInfo
	 * @return 保存后的对象包括ID
	 */
	public int updateStudentFamInfoByField(StudentFamInfo studentFamInfo) {

		return studentFamInfoDao.updateStudentFamInfoByField(studentFamInfo);
	}

	/**
	 * 更新 更新所有字段
	 * 
	 * @param studentFamInfo
	 * @return 保存后的对象包括ID
	 */
	public int updateStudentFamInfo(StudentFamInfo studentFamInfo) {

		return studentFamInfoDao.updateStudentFamInfo(studentFamInfo);
	}

	/**
	 * 按ID查询
	 * 
	 * @parm id 主键ID
	 * @return StudentFamInfo
	 */
	public StudentFamInfo selectStudentFamInfoById(String id) {

		return studentFamInfoDao.selectStudentFamInfoById(id);
	}

	/**
	 * 分页查询 包含条件
	 * 
	 * @param page
	 *            分页对象
	 * @param map
	 *            查询条件
	 * @return List<StudentFamInfo>
	 */
	public Page selectOnePageByMap(Page page, Map<String, Object> map) {
		List<StudentFamInfo> studentFamInfos = studentFamInfoDao.selectOnePageByMap(page, map);
		if (studentFamInfos != null && studentFamInfos.size() > 0) {
			page.setResult(studentFamInfos);
		} else {
			page.setResult(new ArrayList<StudentFamInfo>());
		}
		return page;
	}

	/**
	 * 分页查询 包含对象条件
	 * 
	 * @param page
	 *            分页对象
	 * @param studentFamInfo
	 *            查询条件
	 * @return Page
	 */
	public Page selectOnePageByStudentFamInfo(Page page, StudentFamInfo studentFamInfo) {
		List<StudentFamInfo> studentFamInfos = studentFamInfoDao.selectOnePageByStudentFamInfo(page, studentFamInfo);
		if (studentFamInfos != null && studentFamInfos.size() > 0) {
			page.setResult(studentFamInfos);
		} else {
			page.setResult(new ArrayList<StudentFamInfo>());
		}
		return page;
	}

	/**
	 * 按条件查询全部的
	 * 
	 * @param map
	 *            查询条件
	 * @return List<StudentFamInfo>
	 */
	public List<StudentFamInfo> selectAllByMap(Map<String, Object> map) {
		return studentFamInfoDao.selectAllByMap(map);
	}

	/**
	 * 按条件查询全部的
	 * 
	 * @param map
	 *            查询条件
	 * @return List<StudentFamInfo>
	 */
	public List<StudentFamInfo> selectAllByStudentFamInfo(StudentFamInfo studentFamInfo) {

		return studentFamInfoDao.selectAllByStudentFamInfo(studentFamInfo);
	}

	/**
	 * 按条件查询单个对象
	 * 
	 * @param map
	 *            查询条件
	 * @return StudentFamInfo
	 */
	public StudentFamInfo selectObjectByMap(Map<String, Object> map) {

		return studentFamInfoDao.selectObjectByMap(map);
	}

	/**
	 * 按条件查询单个对象
	 * 
	 * @param map
	 *            查询条件
	 * @return StudentFamInfo
	 */
	public StudentFamInfo selectObjectByStudentFamInfo(StudentFamInfo studentFamInfo) {

		return studentFamInfoDao.selectObjectByStudentFamInfo(studentFamInfo);
	}

	@Override
	public void deleteAndSave(List<StudentFamInfo> studentFamInfos, String sid, String recruitId) {
		StudentFamInfo famInfo = new StudentFamInfo();
		famInfo.setStudentId(sid);
		famInfo.setEmployItemsId(recruitId);
		studentFamInfoDao.deleteStudentFamInfoByObject(famInfo);
		// 保存家庭信息
		if (studentFamInfos != null) {
			for (StudentFamInfo studentFamInfo : studentFamInfos) {
				if (StringUtil.isEmpty(studentFamInfo.getMemberRelation())) {
					continue;
				}
				studentFamInfo.setStudentId(sid);
				studentFamInfo.setEmployItemsId(recruitId);
				studentFamInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				studentFamInfo.setCreateUser(sid);
				//studentFamInfo.setCtime(new Date());
				studentFamInfo.setModifyUser(sid);
				//studentFamInfo.setMtime(new Date());
				studentFamInfoDao.saveStudentFamInfo(studentFamInfo);
			}
		}

	}
}
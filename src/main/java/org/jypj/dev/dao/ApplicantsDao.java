package org.jypj.dev.dao;

import java.util.List;

import org.jypj.dev.vo.ApplicantsVo;

public interface ApplicantsDao {

	/**
	 * 根据岗位分组统计
	 * 
	 * @param ApplicantsVo
	 *            查询条件
	 * @return List<ApplicantsVo>
	 */
	public List<ApplicantsVo> selectAllByApplicantsVo(ApplicantsVo applicantsVo);

	/**
	 * 根据主题分组统计
	 * 
	 * @param ApplicantsVo
	 *            查询条件
	 * @return List<ApplicantsVo>
	 */
	public List<ApplicantsVo> selectAllByItemsId(ApplicantsVo applicantsVo);

	/**
	 * 人数统计
	 * 
	 * @param ApplicantsVo
	 *            查询条件
	 * @return List<ApplicantsVo>
	 */
	public List<ApplicantsVo> selectPeopleCountByItemsId(ApplicantsVo applicantsVo);

}

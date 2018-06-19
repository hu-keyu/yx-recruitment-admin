package org.jypj.dev.dao;

import java.util.List;

import org.jypj.dev.entity.Postset;

/**
* Postsetdao数据接口层
* 招聘岗位
* @author
*
*/


public interface TestPostsetDao {

	/**
	 * 查询所有岗位
	 * @return
	 */
	public List<Postset> selectAllPostset();
}

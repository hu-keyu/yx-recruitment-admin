package org.jypj.dev.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.constant.ConstantUitl;
import org.jypj.dev.dao.EaxmLabsInfoDao;
import org.jypj.dev.dao.EaxmLabsRecordDao;
import org.jypj.dev.dao.ExamItemsInfoDao;
import org.jypj.dev.dao.ExamLectureGroupDao;
import org.jypj.dev.dao.ExamSubjectInfoDao;
import org.jypj.dev.dao.PostsetDao;
import org.jypj.dev.dao.ScoreEnterTrialDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.EaxmLabsInfo;
import org.jypj.dev.entity.EaxmLabsRecord;
import org.jypj.dev.entity.ExamItemsInfo;
import org.jypj.dev.entity.ExamLectureGroup;
import org.jypj.dev.entity.ExamSubjectInfo;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.ScoreEnterTrial;
import org.jypj.dev.entity.ScoreGradeTrial;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.service.ExamSubjectInfoService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.AdjustStudentRoomInfoVo;
import org.jypj.dev.vo.RoomGanWeiVo;
import org.jypj.dev.vo.RoomLayoutVo;
import org.jypj.dev.vo.SecondLayOutRoomVo;
import org.jypj.dev.vo.StudentApplyInfoVo;
import org.jypj.dev.vo.StudentLectureVo;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service("examSubjectInfoService")
public class ExamSubjectInfoServiceImpl implements ExamSubjectInfoService {

	@Resource
	private ExamItemsInfoDao examItemsInfoDao;

	@Resource
	private ExamSubjectInfoDao examSubjectInfoDao;

	@Resource
	private PostsetDao postsetDao;

	@Resource
	private EaxmLabsInfoDao eaxmLabsInfoDao;

	@Resource
	private EaxmLabsRecordDao eaxmLabsRecordDao;

	@Resource
	private StudentApplyInfoDao studentApplyInfoDao;

	@Resource
	private ExamLectureGroupDao examLectureGroupDao;

	@Resource
	private ScoreEnterTrialDao scoreEnterTrialDao;

	/**
	 * 保存 字段为空的不存防止覆盖存在默认值的字段
	 * 
	 * @param examSubjectInfo
	 * @return 保存后的对象包括ID
	 */
	public int saveExamSubjectInfoByField(ExamSubjectInfo examSubjectInfo) {

		return examSubjectInfoDao.saveExamSubjectInfoByField(examSubjectInfo);
	}

	/**
	 * 保存 所有字段全都保存
	 * 
	 * @param examSubjectInfo
	 * @return 保存后的对象包括ID
	 */
	public int saveExamSubjectInfo(ExamSubjectInfo examSubjectInfo) {

		return examSubjectInfoDao.saveExamSubjectInfo(examSubjectInfo);
	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 *            主键ID
	 * @return 删除记录数
	 */
	public int deleteExamSubjectInfoById(String id) {

		return examSubjectInfoDao.deleteExamSubjectInfoById(id);
	}

	/**
	 * 根据对象删除
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	public int deleteExamSubjectInfoByObject(ExamSubjectInfo examSubjectInfo) {

		return examSubjectInfoDao.deleteExamSubjectInfoByObject(examSubjectInfo);
	}

	/**
	 * 更新 只更新值不为空的字段
	 * 
	 * @param examSubjectInfo
	 * @return 保存后的对象包括ID
	 */
	public int updateExamSubjectInfoByField(ExamSubjectInfo examSubjectInfo) {

		return examSubjectInfoDao.updateExamSubjectInfoByField(examSubjectInfo);
	}

	/**
	 * 更新 更新所有字段
	 * 
	 * @param examSubjectInfo
	 * @return 保存后的对象包括ID
	 */
	public int updateExamSubjectInfo(ExamSubjectInfo examSubjectInfo) {

		return examSubjectInfoDao.updateExamSubjectInfo(examSubjectInfo);
	}

	/**
	 * 按ID查询
	 * 
	 * @parm id 主键ID
	 * @return ExamSubjectInfo
	 */
	public ExamSubjectInfo selectExamSubjectInfoById(String id) {

		return examSubjectInfoDao.selectExamSubjectInfoById(id);
	}

	/**
	 * 分页查询 包含条件
	 * 
	 * @param page
	 *            分页对象
	 * @param map
	 *            查询条件
	 * @return List<ExamSubjectInfo>
	 */
	public Page selectOnePageByMap(Page page, Map<String, Object> map) {
		List<ExamSubjectInfo> examSubjectInfos = examSubjectInfoDao.selectOnePageByMap(page, map);
		if (examSubjectInfos != null && examSubjectInfos.size() > 0) {
			page.setResult(examSubjectInfos);
		} else {
			page.setResult(new ArrayList<ExamSubjectInfo>());
		}
		return page;
	}

	/**
	 * 分页查询 包含对象条件
	 * 
	 * @param page
	 *            分页对象
	 * @param examSubjectInfo
	 *            查询条件
	 * @return Page
	 */
	public Page selectOnePageByExamSubjectInfo(Page page, ExamSubjectInfo examSubjectInfo) {
		List<ExamSubjectInfo> examSubjectInfos = examSubjectInfoDao.selectOnePageByExamSubjectInfo(page,
				examSubjectInfo);
		if (examSubjectInfos != null && examSubjectInfos.size() > 0) {
			page.setResult(examSubjectInfos);
		} else {
			page.setResult(new ArrayList<ExamSubjectInfo>());
		}
		return page;
	}

	/**
	 * 按条件查询全部的
	 * 
	 * @param map
	 *            查询条件
	 * @return List<ExamSubjectInfo>
	 */
	public List<ExamSubjectInfo> selectAllByMap(Map<String, Object> map) {
		return examSubjectInfoDao.selectAllByMap(map);
	}

	/**
	 * 按条件查询全部的
	 * 
	 * @param map
	 *            查询条件
	 * @return List<ExamSubjectInfo>
	 */
	public List<ExamSubjectInfo> selectAllByExamSubjectInfo(ExamSubjectInfo examSubjectInfo) {

		return examSubjectInfoDao.selectAllByExamSubjectInfo(examSubjectInfo);
	}

	/**
	 * 按条件查询单个对象
	 * 
	 * @param map
	 *            查询条件
	 * @return ExamSubjectInfo
	 */
	public ExamSubjectInfo selectObjectByMap(Map<String, Object> map) {

		return examSubjectInfoDao.selectObjectByMap(map);
	}

	/**
	 * 按条件查询单个对象
	 * 
	 * @param map
	 *            查询条件
	 * @return ExamSubjectInfo
	 */
	public ExamSubjectInfo selectObjectByExamSubjectInfo(ExamSubjectInfo examSubjectInfo) {

		return examSubjectInfoDao.selectObjectByExamSubjectInfo(examSubjectInfo);
	}

	@Override
	public Map<String, Object> addGanWeiRoom(Map<String, Object> map) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Boolean isNotAddLayOut = true;// 是否不要添加附加试室
		// 查询某个考点，主题招聘项目是否有试室分配记录
		ExamSubjectInfo examSubjectInfo = new ExamSubjectInfo();
		examSubjectInfo.setEmpItemsId((String) map.get("itemsId"));
		examSubjectInfo.setTestId((String) map.get("kaodian"));
		examSubjectInfo.setNyr((String) map.get("nyr"));
		examSubjectInfo.setIsAdd("2");
		List<ExamSubjectInfo> sdataList = examSubjectInfoDao.selectAllByExamSubjectInfo(examSubjectInfo);
		
		//查询某个项目的某个地点是否发布
		EaxmLabsInfo queryLabsinfo=new EaxmLabsInfo();
		queryLabsinfo.setEmpItemsId((String)map.get("itemsId"));
		queryLabsinfo.setTestId((String) map.get("kaodian"));
		List<EaxmLabsInfo> labsList=eaxmLabsInfoDao.selectAllByEaxmLabsInfo(queryLabsinfo);
		
		// 查询学科字典
		List<Dictionary> dicsList = DictionaryCache.getDictionaryByCode("xklb");
		// 查询所有岗位
		List<Postset> postList = postsetDao.selectAllPostset();
		// 转化Map 结构
		Map<String, String> dicsMap = new HashMap<String, String>();
		Map<String, String> postMap = new HashMap<String, String>();
		for (Dictionary d : dicsList) {
			dicsMap.put(d.getId(), d.getText());
		}
		for (Postset p : postList) {
			postMap.put(p.getId(), p.getPostName());
		}
		if (sdataList != null && sdataList.size() > 0&&labsList!=null&&labsList.size()>0) {
			List<RoomLayoutVo> tempList = new ArrayList<RoomLayoutVo>();
			// 对数据库数据进行加工
			for (ExamSubjectInfo e : sdataList) {
				RoomLayoutVo vo = new RoomLayoutVo();
				vo.setGanWeiId(e.getPostId());
				vo.setGanWeiName(postMap.get(e.getPostId()));
				int restNum = Integer.parseInt(e.getSubjectNum())
						- (Integer.parseInt(e.getLabsAmount()) * ConstantUitl.NO_LAYOUT_ROOM_NUM);
				vo.setRestNum(restNum);
				vo.setRoomNum(Integer.parseInt(e.getLabsAmount()));
				vo.setXkId(e.getSubjectId());
				vo.setXkName(dicsMap.get(e.getSubjectId()));
				vo.setGanWeiNum(Integer.parseInt(e.getSubjectNum()));
				if (Integer.parseInt(e.getSubjectNum()) % ConstantUitl.NO_LAYOUT_ROOM_NUM != 0) {
					isNotAddLayOut = false;
				}
				tempList.add(vo);
			}
			dataMap.put("isNotAddLayOut", isNotAddLayOut);
			dataMap.put("RoomLayoutVoList", tempList);
		} else {
			// --查询某个招聘主题对应岗位发布的个数
			// 查询某个考点
			//先删除某个主题考点所有数据
			ExamSubjectInfo queryExamSubjectInfo = new ExamSubjectInfo();
			queryExamSubjectInfo.setEmpItemsId((String) map.get("itemsId"));
			queryExamSubjectInfo.setTestId((String) map.get("kaodian"));
			queryExamSubjectInfo.setIsAdd("2");
			List<ExamSubjectInfo> delList = examSubjectInfoDao.selectAllByExamSubjectInfo(queryExamSubjectInfo);
			for(ExamSubjectInfo info:delList)
			{
				examSubjectInfoDao.deleteExamSubjectInfoByObject(info);
			}
			ExamItemsInfo examItemsInfo = new ExamItemsInfo();
			examItemsInfo.setId((String) map.get("kaodian"));
			examItemsInfo = examItemsInfoDao.selectObjectByExamItemsInfo(examItemsInfo);
			map.put("ganweiIds", getInSQL(examItemsInfo.getPermisGw()));
			List<RoomLayoutVo> dataList = examSubjectInfoDao.selectGanWeiNumBYST(map);
			for (RoomLayoutVo vo : dataList) {
				int num = vo.getGanWeiNum() / ConstantUitl.NO_LAYOUT_ROOM_NUM;
				if (num > 0) {
					ExamSubjectInfo info = new ExamSubjectInfo();
					info.setCtime(new Date());
					info.setEmpItemsId((String) map.get("itemsId"));
					info.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					info.setIsAdd(ConstantUitl.STATE_NO_NUMBER);
					info.setLabsAmount(num + "");
					info.setPostId(vo.getGanWeiId());
					info.setSubjectId(vo.getXkId());
					info.setSubjectNum(vo.getGanWeiNum() + "");
					info.setTestAnmount(num + "");
					info.setTestId((String) map.get("kaodian"));
					vo.setRoomNum(num);
					vo.setRestNum(vo.getGanWeiNum() - num * ConstantUitl.NO_LAYOUT_ROOM_NUM);
					if (vo.getGanWeiNum() % ConstantUitl.NO_LAYOUT_ROOM_NUM != 0) {
						isNotAddLayOut = false;
					}
					examSubjectInfoDao.saveExamSubjectInfo(info);
				} else {
					ExamSubjectInfo info = new ExamSubjectInfo();
					info.setCtime(new Date());
					info.setEmpItemsId((String) map.get("itemsId"));
					info.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					info.setIsAdd(ConstantUitl.STATE_NO_NUMBER);
					info.setLabsAmount("0");
					info.setPostId(vo.getGanWeiId());
					info.setSubjectId(vo.getXkId());
					info.setSubjectNum(vo.getGanWeiNum() + "");
					info.setTestAnmount("0");
					info.setTestId((String) map.get("kaodian"));
					vo.setRoomNum(0);
					vo.setRestNum(vo.getGanWeiNum());
					isNotAddLayOut = false;
					examSubjectInfoDao.saveExamSubjectInfo(info);
				}

			}
			dataMap.put("isNotAddLayOut", isNotAddLayOut);
			dataMap.put("RoomLayoutVoList", dataList);
		}
		return dataMap;
	}

	/**
	 * 获取sql
	 * 
	 * @param str
	 * @return
	 */
	private String getInSQL(String str) {
		String[] strs = str.split(",");
		String totalStr = "(";
		for (int i = 0; i < strs.length; i++) {
			if (i != 0) {
				totalStr = totalStr + "','" + strs[i];
			} else {
				totalStr = totalStr + "'" + strs[i];
			}
		}
		totalStr = totalStr + "')";
		return totalStr;
	}

	@Override
	public String addSecondGanWeiRoom(Map<String, String> map) {
		// 转化json字符串
		// 先判断室试是否分配
		String mes = "";
		JSONObject object = new JSONObject();
		JSONArray jsonArray = object.parseArray(map.get("paramJson"));
		List<SecondLayOutRoomVo> dataList = new ArrayList<SecondLayOutRoomVo>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject vo = jsonArray.getJSONObject(i);
			SecondLayOutRoomVo po = new SecondLayOutRoomVo();
			po.setGanweiId(vo.getString("ganwid"));
			po.setNum(vo.getIntValue("num"));
			po.setXkid(vo.getString("xkid"));
			dataList.add(po);
		}
		// 安学科进行分组
		Map<String, List<SecondLayOutRoomVo>> dataMap = new HashMap<String, List<SecondLayOutRoomVo>>();
		Set<SecondLayOutRoomVo> set = new HashSet<SecondLayOutRoomVo>();
		List<SecondLayOutRoomVo> tempList = null;
		for (SecondLayOutRoomVo s : dataList) {

			if (set.add(s)) {
				tempList = new ArrayList<SecondLayOutRoomVo>();
				tempList.add(s);
				dataMap.put(s.getXkid(), tempList);
			} else {

				if (dataMap.containsKey(s.getXkid())) {
					tempList = dataMap.get(s.getXkid());
					tempList.add(s);
					dataMap.put(s.getXkid(), tempList);
				}
			}
		}
		// 同一学科
		if (dataMap.size() == 1) {
			String ganwei = "";
			for (Map.Entry<String, List<SecondLayOutRoomVo>> entry : dataMap.entrySet()) {
				String key = entry.getKey();
				int oneXKNum = 0;
				String addroomdes = "";
				List<SecondLayOutRoomVo> dList = entry.getValue();
				for (SecondLayOutRoomVo sor : dList) {
					oneXKNum = oneXKNum + sor.getNum();
					ganwei = ganwei + sor.getGanweiId() + ",";
					addroomdes = addroomdes + sor.getGanweiId() + "-" + sor.getNum() + "" + ",";
				}
				if (oneXKNum <=ConstantUitl.NO_LAYOUT_ROOM_NUM) {
					// 进行数据存储
					ExamSubjectInfo info = new ExamSubjectInfo();
					info.setCtime(new Date());
					info.setEmpItemsId((String) map.get("itemsId"));
					info.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					info.setIsAdd(ConstantUitl.STATE_YES_NUMBER);
					info.setLabsAmount("1");
					info.setPostId(ganwei);
					info.setSubjectId(key);
					info.setSubjectNum(oneXKNum + "");
					info.setAddroomdes(addroomdes);
					info.setTestAnmount("1");
					info.setTestId((String) map.get("kaodian"));
					examSubjectInfoDao.saveExamSubjectInfo(info);
				} else {
					return "试室人数不能超过"+ ConstantUitl.NO_LAYOUT_ROOM_NUM+"人";
				}
			}
		} else {
			String addroomdes = "";
			String ganwei = "";
			String xk = "";
			int oneXKNUM = 0;
			if(dataMap.size()>5)
			{
				return "同试室学科不能超过5科!";
			}
			// 非同一学科,混合教室
			for (SecondLayOutRoomVo s : dataList) {
				oneXKNUM = oneXKNUM + s.getNum();
				ganwei = ganwei + s.getGanweiId() + ",";
				addroomdes = addroomdes + s.getGanweiId() + "-" + s.getNum() + "" + ",";
			}
			for (Map.Entry<String, List<SecondLayOutRoomVo>> entry : dataMap.entrySet()) {
				String key = entry.getKey();
				xk = xk + key + ",";
			}
			if (oneXKNUM > ConstantUitl.NO_LAYOUT_ROOM_NUM) {
				return "混合人数超过" + ConstantUitl.NO_LAYOUT_ROOM_NUM + "人";
			} else {
				// 进行数据存储
				ExamSubjectInfo info = new ExamSubjectInfo();
				info.setCtime(new Date());
				info.setEmpItemsId((String) map.get("itemsId"));
				info.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				info.setIsAdd(ConstantUitl.STATE_YES_NUMBER);
				info.setLabsAmount("1");
				info.setPostId(ganwei);
				info.setSubjectId(xk);
				info.setSubjectNum(oneXKNUM + "");
				info.setAddroomdes(addroomdes);
				info.setTestAnmount(dataMap.size() + "");
				info.setTestId((String) map.get("kaodian"));
				examSubjectInfoDao.saveExamSubjectInfo(info);
			}
		}
		return mes;
	}

	@Override
	public List<SecondLayOutRoomVo> delSecondGanWeiRoom(Map<String, String> map) {
		// 1.查询实体
		ExamSubjectInfo queryInfo = new ExamSubjectInfo();
		queryInfo.setEmpItemsId(map.get("itemsId"));
		queryInfo.setTestId(map.get("kaodian"));
		queryInfo.setPostId(map.get("ganweiid"));
		queryInfo.setNyr(map.get("ryn"));
		queryInfo.setAddroomdes(map.get("addrooms"));
		List<ExamSubjectInfo> list = examSubjectInfoDao.selectAllByExamSubjectInfo(queryInfo);
		// 将queryinfo转成List<SecondLayOutRoomVo>
		List<SecondLayOutRoomVo> dataList = new ArrayList<SecondLayOutRoomVo>();
		if(list.size()>0)
		{
			queryInfo=list.get(0);
			String[] strs = queryInfo.getAddroomdes().split(",");
			for (String s : strs) {
				if (s != null && s != "") {
					SecondLayOutRoomVo vo = new SecondLayOutRoomVo();
					String svo[] = s.split("-");
					vo.setNum(Integer.parseInt(svo[1]));
					vo.setGanweiId(svo[0]);
					vo.setAddRooms(queryInfo.getAddroomdes());
					dataList.add(vo);
				}
			}
			examSubjectInfoDao.deleteExamSubjectInfoById(queryInfo.getId());
		}
		return dataList;
	}

	@Override
	public Map<String, Object> getSelectEditData(Map<String, String> map) {
		// 非附加试室
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ExamSubjectInfo queryInfo = new ExamSubjectInfo();
		queryInfo.setEmpItemsId(map.get("itemsId"));
		queryInfo.setTestId(map.get("kaodian"));
		queryInfo.setNyr(map.get("ryn"));
		queryInfo.setIsAdd("2");
		List<ExamSubjectInfo> d1List = examSubjectInfoDao.selectAllByExamSubjectInfo(queryInfo);
		// 附加试室
		queryInfo.setIsAdd("1");
		List<ExamSubjectInfo> d2List = examSubjectInfoDao.selectAllByExamSubjectInfo(queryInfo);
		List<RoomGanWeiVo> dataList = new ArrayList<RoomGanWeiVo>();
		if (d2List == null || d2List.size() == 0) {
			returnMap.put("noLayoutList", null);
			return returnMap;
		}
		for (ExamSubjectInfo e2 : d2List) {
			String[] strs = e2.getAddroomdes().split(",");
			for (String s : strs) {
				if (s != null && s != "") {
					RoomGanWeiVo vo = new RoomGanWeiVo();
					String svo[] = s.split("-");
					vo.setNum(Integer.parseInt(svo[1]));
					vo.setGanweiId(svo[0]);
					dataList.add(vo);
				}
			}
		}

		// 按岗位分组
		Map<String, List<RoomGanWeiVo>> dataMap = new HashMap<String, List<RoomGanWeiVo>>();
		Set<RoomGanWeiVo> set = new HashSet<RoomGanWeiVo>();
		List<RoomGanWeiVo> tempList = null;
		for (RoomGanWeiVo s : dataList) {

			if (set.add(s)) {
				tempList = new ArrayList<RoomGanWeiVo>();
				tempList.add(s);
				dataMap.put(s.getGanweiId(), tempList);
			} else {

				if (dataMap.containsKey(s.getGanweiId())) {
					tempList = dataMap.get(s.getGanweiId());
					tempList.add(s);
					dataMap.put(s.getGanweiId(), tempList);
				}
			}
		}
		Map<String, Integer> numMap = new HashMap<String, Integer>();
		for (Map.Entry<String, List<RoomGanWeiVo>> entry : dataMap.entrySet()) {
			String key = entry.getKey();
			List<RoomGanWeiVo> list = entry.getValue();
			int sum = 0;
			for (RoomGanWeiVo vo : list) {
				sum = sum + vo.getNum();
			}
			numMap.put(key, sum);
		}
		// 查询所有岗位
		Map<String, String> postMap = new HashMap<String, String>();
		List<Postset> postList = postsetDao.selectAllPostset();
		for (Postset p : postList) {
			postMap.put(p.getId(), p.getPostName());
		}
		List<RoomLayoutVo> returnList = new ArrayList<RoomLayoutVo>();
		for (ExamSubjectInfo e1 : d1List) {
			int num = 0;
			if (numMap.get(e1.getPostId()) != null) {
				num = numMap.get(e1.getPostId());

			}
			RoomLayoutVo vo = new RoomLayoutVo();
			vo.setGanWeiId(e1.getPostId());
			vo.setGanWeiName(postMap.get(e1.getPostId()));
			if (("0").equals(e1.getLabsAmount())) {
				vo.setRestNum(Integer.parseInt(e1.getSubjectNum()) - num);

			} else {
				vo.setRestNum(Integer.parseInt(e1.getSubjectNum())
						- Integer.parseInt(e1.getLabsAmount()) * ConstantUitl.NO_LAYOUT_ROOM_NUM - num);
			}
			vo.setRestNum(Integer.parseInt(e1.getSubjectNum())
					- Integer.parseInt(e1.getLabsAmount()) * ConstantUitl.NO_LAYOUT_ROOM_NUM - num);
			vo.setXkId(e1.getSubjectId());
			returnList.add(vo);

		}
		returnMap.put("noLayoutList", returnList);
		List<SecondLayOutRoomVo> layoutList = new ArrayList<SecondLayOutRoomVo>();

		for (ExamSubjectInfo e2 : d2List) {
			SecondLayOutRoomVo vo = new SecondLayOutRoomVo();
			String ganweiName = "";
			String ganweiid = "";
			String listr = "";
			String[] strs = e2.getAddroomdes().split(",");
			// 1.查询实体
			for (String s : strs) {
				if (s != null && s != "") {
					String svo[] = s.split("-");
					ganweiName = ganweiName + postMap.get(svo[0]) + "[" + svo[1] + "人] ";
					ganweiid = ganweiid + svo[0] + ",";
					listr = listr + svo[0]+svo[1];
					vo.setGanweiName(ganweiName);
					vo.setGanweiId(ganweiid);
					vo.setListStr(listr);
				}
			}
			vo.setAddRooms(e2.getAddroomdes());
			layoutList.add(vo);
		}
		returnMap.put("LayoutList", layoutList);
		return returnMap;
	}

	@Override
	public String addRoomAndStudentResult(Map<String, String> map) {
		String mes = "";
		Map<String, List<EaxmLabsInfo>> noLayoutMap = new HashMap<String, List<EaxmLabsInfo>>();// 存储非附加试室，岗位所对应的试室集合
		Map<String, EaxmLabsInfo> LayoutMap = new HashMap<String, EaxmLabsInfo>();// 存储附加试室，岗位所对应的试室集合
		Map<String, ExamSubjectInfo> examSubjectMap = new HashMap<String, ExamSubjectInfo>();
		EaxmLabsInfo eaxmLabsInfo = new EaxmLabsInfo();
		eaxmLabsInfo.setEmpItemsId(map.get("itemsId"));
		ExamItemsInfo itemsInfo=examItemsInfoDao.selectExamItemsInfoById(map.get("kaodian"));
		eaxmLabsInfo.setTestId(map.get("kaodian"));
		eaxmLabsInfo.setNyr(map.get("ryn"));
		int totalNum = 0;
		List<EaxmLabsInfo> dataList = eaxmLabsInfoDao.selectAllByEaxmLabsInfo(eaxmLabsInfo);
		if (dataList.size() > 0) {
			mes = "试室编排信息已发布";
			return mes;
		}
		// 批量插入试室信息
		ExamSubjectInfo queryInfo = new ExamSubjectInfo();
		queryInfo.setEmpItemsId(map.get("itemsId"));
		queryInfo.setTestId(map.get("kaodian"));
		queryInfo.setNyr(map.get("ryn"));
		queryInfo.setIsAdd("2");
		List<ExamSubjectInfo> d1List = examSubjectInfoDao.selectAllByExamSubjectInfo(queryInfo);
		List<EaxmLabsInfo> eaxmList = new ArrayList<EaxmLabsInfo>();
		int labNum = 1;
		for (ExamSubjectInfo e : d1List) {
			List<EaxmLabsInfo> tempeaxmList = new ArrayList<EaxmLabsInfo>();
			int num = Integer.parseInt(e.getLabsAmount());
			for (int i = 0; i < num; i++) {
				if (!"0".equals(e.getLabsAmount())) {
					EaxmLabsInfo labs = new EaxmLabsInfo();
					labs.setEmpItemsId(map.get("itemsId"));
					labs.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					labs.setIsAdd("2");
					if (labNum < 10) {
						labs.setLabsAddr("0" + labNum);
						labs.setLabsName("0" + labNum);
						labs.setLabsNum("0" + labNum);
					} else {
						labs.setLabsAddr(labNum + "");
						labs.setLabsName(labNum + "");
						labs.setLabsNum(labNum + "");
					}
					labs.setLabsRealnum(ConstantUitl.NO_LAYOUT_ROOM_NUM + "");
					labs.setLabsTotal(ConstantUitl.NO_LAYOUT_ROOM_NUM + "");
					labs.setLabsType(map.get("type"));
					labs.setPostId(e.getPostId());
					labs.setSubjectId(e.getSubjectId());
					labs.setTestId(e.getTestId());
					labs.setCtime(new Date());
					labs.setStartTime(itemsInfo.getStartTime());
					labs.setEndTime(itemsInfo.getEndTime());
					eaxmList.add(labs);
					tempeaxmList.add(labs);
					labNum++;
				}

			}
			noLayoutMap.put(e.getPostId(), tempeaxmList);
			totalNum = totalNum + Integer.parseInt(e.getLabsAmount()) * ConstantUitl.NO_LAYOUT_ROOM_NUM;
		}
		queryInfo.setIsAdd("1");
		List<ExamSubjectInfo> d2List = examSubjectInfoDao.selectAllByExamSubjectInfo(queryInfo);
		for (ExamSubjectInfo e : d2List) {
			EaxmLabsInfo labs = new EaxmLabsInfo();
			labs.setEmpItemsId(map.get("itemsId"));
			labs.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			labs.setIsAdd("1");
			if (labNum < 10) {
				labs.setLabsAddr("0" + labNum);
				labs.setLabsName("0" + labNum);
				labs.setLabsNum("0" + labNum);
			} else {
				labs.setLabsAddr(labNum + "");
				labs.setLabsName(labNum + "");
				labs.setLabsNum(labNum + "");
			}
			labs.setLabsRealnum(e.getSubjectNum() + "");
			labs.setLabsTotal(ConstantUitl.NO_LAYOUT_ROOM_NUM + "");
			labs.setLabsType(map.get("type"));
			labs.setPostId(e.getPostId());
			labs.setSubjectId(e.getSubjectId());
			labs.setTestId(e.getTestId());
			labs.setCtime(new Date());
			labs.setSubjectItmeId(e.getId());
			labs.setStartTime(itemsInfo.getStartTime());
			labs.setEndTime(itemsInfo.getEndTime());
			LayoutMap.put(e.getId() + "-" + e.getPostId(), labs);
			examSubjectMap.put(e.getId() + "-" + e.getPostId(), e);
			eaxmList.add(labs);
			labNum++;
			totalNum = totalNum + Integer.parseInt(e.getSubjectNum());
		}
		if (eaxmList.size() == 0) {
			mes = "没有试室信息可发布!";
			return mes;
		}
		// 批量存储学生与室试对应关系
		// 查出岗位对应的学生map<String,List<String>>();
        Map<String,String> queryMap=new HashMap<String,String>();
        queryMap.put("itemsId", map.get("itemsId"));
        queryMap.put("jobids", this.getInSQL(itemsInfo.getPermisGw()));
		List<StudentApplyInfo> studentApplyList = examSubjectInfoDao.queryKaoDianNum(queryMap);
		if (totalNum != studentApplyList.size()) {
			mes = "请分配完附加试室!";
			return mes;
		}
		// 按岗位分组
		Map<String, List<StudentApplyInfoVo>> dataMap = new HashMap<String, List<StudentApplyInfoVo>>();
		Set<StudentApplyInfo> set = new HashSet<StudentApplyInfo>();
		List<StudentApplyInfoVo> tempList = null;
		Map<String ,String> postMap=new HashMap<String,String>();
		for (StudentApplyInfo s : studentApplyList) {
			postMap.put(s.getStudentId(), s.getApplyJobId());
			if (set.add(s)) {
				StudentApplyInfoVo vo = new StudentApplyInfoVo();
				vo.setId(s.getId());
				vo.setStudentid(s.getStudentId());
				tempList = new ArrayList<StudentApplyInfoVo>();
				tempList.add(vo);
				dataMap.put(s.getApplyJobId(), tempList);
			} else {

				if (dataMap.containsKey(s.getApplyJobId())) {
					tempList = dataMap.get(s.getApplyJobId());
					StudentApplyInfoVo vo = new StudentApplyInfoVo();
					vo.setId(s.getId());
					vo.setStudentid(s.getStudentId());
					tempList.add(vo);
					dataMap.put(s.getApplyJobId(), tempList);
				}
			}
		}

		List<EaxmLabsRecord> eaxmLabsRecordList = new ArrayList<EaxmLabsRecord>();
		// for 循环岗位 (计算非附加试室学生分配)
		for (Map.Entry<String, List<StudentApplyInfoVo>> entry : dataMap.entrySet()) {
			String key = entry.getKey();
			List<EaxmLabsInfo> elist = noLayoutMap.get(key);
			if (elist != null && elist.size() > 0) // 防止某个岗位小于一间教室的人
			{
				List<StudentApplyInfoVo> list = entry.getValue();
				int num = list.size() / ConstantUitl.NO_LAYOUT_ROOM_NUM;
				for (int i = 0; i < num; i++) {
					List<StudentApplyInfoVo> subList = list.subList(0, ConstantUitl.NO_LAYOUT_ROOM_NUM);
					EaxmLabsInfo el = elist.get(0);
					int seatNum = 1;
					for (StudentApplyInfoVo s : subList) {
						EaxmLabsRecord er = new EaxmLabsRecord();
						er.setCtime(new Date());
						er.setId(UUID.randomUUID().toString().replaceAll("-", ""));
						er.setLabsId(el.getId());
						if (seatNum < 10) {
							er.setSeatNum("0" + seatNum);
						} else {
							er.setSeatNum(seatNum + "");
						}
						er.setStudentId(s.getStudentid());
						er.setSubjectId(map.get("itemsId"));
						seatNum++;
						eaxmLabsRecordList.add(er);
					}
					elist.remove(el);
					list.subList(0, ConstantUitl.NO_LAYOUT_ROOM_NUM).clear();
				}
				noLayoutMap.put(key, elist); // 放入剩余试室
				dataMap.put(key, list);// 放入剩余学生
			}

		}
		
		List<EaxmLabsRecord> secondRecordList=new ArrayList<EaxmLabsRecord>();
		/**
		 * 附加试室学生分配
		 */
		for (Map.Entry<String, ExamSubjectInfo> entry : examSubjectMap.entrySet()) {
			ExamSubjectInfo e = entry.getValue();
			String key = entry.getKey();
			String[] strs = e.getAddroomdes().split(",");
			int seatNum = 1;
			for (String s : strs) {
				if (s != null && s != "") {
					String svo[] = s.split("-");// svo[0],svo[1]
					List<StudentApplyInfoVo> list = dataMap.get(svo[0]);
					List<StudentApplyInfoVo> tempVoList = new ArrayList<StudentApplyInfoVo>();
					tempVoList.addAll(list);
					if (list != null) {
						for (int i = 0; i < Integer.parseInt(svo[1]); i++) {
							StudentApplyInfoVo stu = list.get(i);
							EaxmLabsRecord er = new EaxmLabsRecord();
							er.setCtime(new Date());
							er.setId(UUID.randomUUID().toString().replaceAll("-", ""));
							er.setLabsId(LayoutMap.get(key) == null ? "" : LayoutMap.get(key).getId());
							if (seatNum < 10) {
								er.setSeatNum("0" + seatNum);
							} else {
								er.setSeatNum(seatNum + "");
							}
							er.setStudentId(stu.getStudentid());
							er.setSubjectId(map.get("itemsId"));
							secondRecordList.add(er);
							//eaxmLabsRecordList.add(er);
							seatNum++;
							tempVoList.remove(stu);
						}
					}
					dataMap.put(svo[0], tempVoList);
				}
			}

		}
		
		//按照试室进行对学生分组
		Map<String, List<EaxmLabsRecord>> mapDates = new HashMap<String, List<EaxmLabsRecord>>();
			Set<String> positionIds = new HashSet<String>();
			for (EaxmLabsRecord grade : secondRecordList) {
				positionIds.add(grade.getLabsId());
			}
			Iterator<String> it = positionIds.iterator();
			List<EaxmLabsRecord> gradeItems =null;
			while (it.hasNext()) {
				String positionId = it.next();
				gradeItems = new ArrayList<EaxmLabsRecord>();
				for (EaxmLabsRecord grade : secondRecordList) {
					if (positionId.equals(grade.getLabsId())) {
						gradeItems.add(grade);
					}
				}
				mapDates.put(positionId, gradeItems);
			}
			
			//对gradeItems进行岗位分组
			for (Map.Entry<String,  List<EaxmLabsRecord>> entry : mapDates.entrySet()) {
				
				List<EaxmLabsRecord> valueList=entry.getValue();
				Map<String, List<EaxmLabsRecord>> mapDates1 = new HashMap<String, List<EaxmLabsRecord>>();
				Set<String> positionIds1 = new HashSet<String>();
				for (EaxmLabsRecord grade : valueList) {
					grade.setPostId(postMap.get(grade.getStudentId()));
					positionIds1.add(postMap.get(grade.getStudentId()));
				}
				Iterator<String> it1 = positionIds1.iterator();
				List<EaxmLabsRecord> gradeItems1 =null;
				while (it1.hasNext()) {
					String positionId1 = it1.next();
					gradeItems1 = new ArrayList<EaxmLabsRecord>();
					for (EaxmLabsRecord grade : valueList) {
						if (positionId1.equals(grade.getPostId())) {
							gradeItems1.add(grade);
						}
					}
					mapDates1.put(positionId1, gradeItems1);
				}
				int seatNum=1;
				for (Map.Entry<String,  List<EaxmLabsRecord>> entry1 : mapDates1.entrySet()) {
					List<EaxmLabsRecord> valueList1=entry1.getValue();
					for (EaxmLabsRecord er : valueList1) {
						if (seatNum < 10) {
							er.setSeatNum("0" + seatNum);
						} else {
							er.setSeatNum(seatNum + "");
						}
						eaxmLabsRecordList.add(er);
						seatNum++;
					}
				
				}
				
			}
		// for 循环岗位 (计算附加试室学生编排)
		// 批量存储试室
		int pointsDataLimit = 1000;// 限制条数
		Integer size = eaxmList.size();
		if (pointsDataLimit < size) {
			int part = size / pointsDataLimit;// 分批数
			for (int i = 0; i < part; i++) {
				List<EaxmLabsInfo> listPage = eaxmList.subList(0, pointsDataLimit);
				// 存储
				eaxmLabsInfoDao.addBatch(listPage);
				eaxmList.subList(0, pointsDataLimit).clear();
			}
			// 存储
			if(eaxmList.size()>0)
			{
				eaxmLabsInfoDao.addBatch(eaxmList);
			}
			
		} else {
			eaxmLabsInfoDao.addBatch(eaxmList);
		}
		// 批量存储学生与教室对应关系
		Integer sizes = eaxmLabsRecordList.size();
		if (pointsDataLimit < sizes) {
			int parts = size / pointsDataLimit;// 分批数
			for (int i = 0; i < parts; i++) {
				List<EaxmLabsRecord> listPages = eaxmLabsRecordList.subList(0, pointsDataLimit);
				// 存储
				eaxmLabsRecordDao.addBatch(listPages);
				eaxmLabsRecordList.subList(0, pointsDataLimit).clear();
			}
			// 存储
			if(eaxmLabsRecordList.size()>0)
			{
				eaxmLabsRecordDao.addBatch(eaxmLabsRecordList);
			}
		} else {
			eaxmLabsRecordDao.addBatch(eaxmLabsRecordList);
		}
	
		
		return mes;
	}

	@Override
	public List<AdjustStudentRoomInfoVo> querySecondLayoutRoom(Map<String, String> map) {

		return examSubjectInfoDao.querySecondLayoutRoom(map);
	}

	@Override
	public String updateBatchAdjust(Map<String, String> map) {
		String mes = "";
		String toLabsid = map.get("toLabsid");
		// 查询to实验室个数
		EaxmLabsInfo tolabs = eaxmLabsInfoDao.selectEaxmLabsInfoById(toLabsid);
		// 查询froms试室
		EaxmLabsInfo fromLabs = eaxmLabsInfoDao.selectEaxmLabsInfoById(map.get("fromLabsid"));
		if (fromLabs.getMangerPerson() != null && fromLabs.getStartTime() != null && fromLabs.getEndTime() != null) {
			mes = "该试室已经被确认,不能进行调整!";
			return mes;
		}
		// 查询学科字典
		List<Dictionary> dicsList = DictionaryCache.getDictionaryByCode("xklb");
		// 查询所有岗位
		List<Postset> postList = postsetDao.selectAllPostset();
		// 转化Map 结构
		Map<String, String> dicsMap = new HashMap<String, String>();
		Map<String, String> postMap = new HashMap<String, String>();
		for (Dictionary d : dicsList) {
			dicsMap.put(d.getId(), d.getText());
		}
		for (Postset p : postList) {
			postMap.put(p.getId(), p.getPostName());
		}
		String sidStr = map.get("sutidStr");
		List<String> paramList = new ArrayList<String>();
		String strs[] = sidStr.split(",");
		for (String s : strs) {
			paramList.add(s);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("employItemsId", map.get("itemsId"));
		dataMap.put("studentIds", paramList);
		if (paramList.size() + Integer.parseInt(tolabs.getLabsRealnum()) > ConstantUitl.NO_LAYOUT_ROOM_NUM) {
			mes = "试室调整大于30人";
			return mes;
		}
		List<StudentApplyInfo> applyInfoList = studentApplyInfoDao.selectAllByMap(dataMap);// 首先in查询该学生申请信息
		List<String> ganWeiList = new ArrayList<String>();
		for (StudentApplyInfo s : applyInfoList) {
			ganWeiList.add(s.getApplyJobId());
		}
		// 查询附加试室，对应的外键
		ExamSubjectInfo fromEsubject = examSubjectInfoDao.selectExamSubjectInfoById(fromLabs.getSubjectItmeId());
		ExamSubjectInfo toEsubject = examSubjectInfoDao.selectExamSubjectInfoById(tolabs.getSubjectItmeId());
		// 岗位处理,岗位数量对应处理
		Map<String, String> fromMap = new HashMap<String, String>();
		Map<String, String> toMap = new HashMap<String, String>();
		for (String s : ganWeiList) {
			this.handelGanWeiNumStr(fromEsubject, s, false, fromMap);
			this.handelGanWeiNumStr(toEsubject, s, true, toMap);
		}
		fromMap = this.handleResult(fromEsubject.getAddroomdes(), postsetDao);
		toMap = this.handleResult(toEsubject.getAddroomdes(), postsetDao);

		fromEsubject.setPostId(fromMap.get("ganwei") == null ? "" : fromMap.get("ganwei"));
		fromEsubject.setSubjectId(fromMap.get("xueke") == null ? "" : fromMap.get("xueke"));

		toEsubject.setPostId(toMap.get("ganwei") == null ? "" : toMap.get("ganwei"));
		toEsubject.setSubjectId(toMap.get("xueke") == null ? "" : toMap.get("xueke"));

		fromLabs.setPostId(fromEsubject.getPostId());
		fromLabs.setSubjectId(fromEsubject.getSubjectId());

		tolabs.setPostId(toEsubject.getPostId());
		tolabs.setSubjectId(toEsubject.getSubjectId());
		// 更新
		examSubjectInfoDao.updateExamSubjectInfo(fromEsubject);
		examSubjectInfoDao.updateExamSubjectInfo(toEsubject);
		// 将原来的实验室id,该为现在的id
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("subjectId", map.get("itemsId"));
		updateMap.put("studentIds", paramList);// 学生list
		List<EaxmLabsRecord> tempList = eaxmLabsRecordDao.selectAllByMap(updateMap);
		for (EaxmLabsRecord e : tempList) {
			e.setLabsId(toLabsid);
		}

		// 调整后座位号重新随机编排
		List<EaxmLabsRecord> updataList = new ArrayList<EaxmLabsRecord>();
		EaxmLabsRecord eaxmLabsRecord = new EaxmLabsRecord();
		eaxmLabsRecord.setSubjectId(map.get("itemsId"));
		eaxmLabsRecord.setLabsId(fromLabs.getId());
		List<EaxmLabsRecord> recordList1 = eaxmLabsRecordDao.selectAllByEaxmLabsRecord(eaxmLabsRecord);
		recordList1.removeAll(tempList);
		int seatNum = 1;
		for (EaxmLabsRecord e : recordList1) {
			if (seatNum < 10) {
				e.setSeatNum("0" + seatNum);
			} else {
				e.setSeatNum(seatNum + "");
			}
			seatNum++;
			updataList.add(e);
		}
		fromLabs.setLabsRealnum(recordList1.size() + "");
		//fromLabs.setLabsNum(recordList1.size() + "");
		eaxmLabsRecord.setLabsId(toLabsid);
		List<EaxmLabsRecord> recordList2 = eaxmLabsRecordDao.selectAllByEaxmLabsRecord(eaxmLabsRecord);
		seatNum = 1;
		recordList2.addAll(tempList);
		tolabs.setLabsRealnum(recordList2.size() + "");
		//tolabs.setLabsNum(recordList2.size() + "");
		for (EaxmLabsRecord e : recordList2) {
			if (seatNum < 10) {
				e.setSeatNum("0" + seatNum);
			} else {
				e.setSeatNum(seatNum + "");
			}
			seatNum++;
			updataList.add(e);
		}
		// 批量更新
		eaxmLabsRecordDao.updateBatchEaxmLabsRecordList(updataList);
		eaxmLabsInfoDao.updateEaxmLabsInfo(fromLabs);
		eaxmLabsInfoDao.updateEaxmLabsInfo(tolabs);
		return mes;
	}

	/**
	 * 处理岗位数量的加减
	 * 
	 * @param str
	 * @param s
	 * @param isAdd
	 * @return
	 */
	private void handelGanWeiNumStr(ExamSubjectInfo e, String s, boolean isAdd, Map<String, String> map) {
		if (e.getAddroomdes().contains(",")) {
			String[] tempStr = e.getAddroomdes().split(",");
			for (String vo : tempStr) {
				map.put(vo.split("-")[0], vo.split("-")[1]);
			}
		}
		int num = 0;
		if (map.containsKey(s)) {
			if (isAdd) {
				num = Integer.parseInt(map.get(s));
				num = num + 1;
				map.put(s, num + "");

			}
			if (!isAdd) {
				if (map.get(s) != null) {
					num = Integer.parseInt(map.get(s));
					num = num - 1;
					if (num == 0) {
						map.remove(s);
					} else {
						map.put(s, num + "");
					}
				}
			}
		} else {
			if (isAdd) {
				if (map.get(s) == null) {
					map.put(s, "1");
				}

			}

		}
		String sumStr = "";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sumStr = sumStr + entry.getKey() + "-" + entry.getValue() + ",";
		}
		e.setAddroomdes(sumStr);
	}

	/**
	 * 处理岗位，学科字符串
	 * 
	 * @param str
	 * @param postsetDao
	 * @return
	 */
	private Map<String, String> handleResult(String str, PostsetDao postsetDao) {
		Map<String, String> resultMap = new HashMap<String, String>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<String> ganWeiList = new ArrayList<String>();
		String ganwei = "";
		String xueke = "";
		if (str.contains(",")) {
			String[] tempStr = str.split(",");
			for (String vo : tempStr) {
				ganwei = ganwei + vo.split("-")[0] + ",";
				ganWeiList.add(vo.split("-")[0]);
			}
		}
		// 查询学科id
		dataMap.put("ganweiIds", ganWeiList);
		List<String> XkList = postsetDao.selectXKIDByGanweiId(dataMap);
		for (String s : XkList) {
			xueke = xueke + s + ",";
		}
		resultMap.put("xueke", xueke);
		resultMap.put("ganwei", ganwei);
		return resultMap;
	}

	@Override
	public Map<String, Object> queryLayoutDetail(Map<String, String> map) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		// 首先查询试室
		EaxmLabsInfo queryLab = new EaxmLabsInfo();
		queryLab.setEmpItemsId(map.get("itemsId"));
		queryLab.setTestId(map.get("kaodian"));
		List<EaxmLabsInfo> lablist = eaxmLabsInfoDao.selectAllByEaxmLabsInfo(queryLab);
		// 根据Collections.sort重载方法来实现
		Map<String, String> queryMap = new HashMap<String, String>();
		Collections.sort(lablist, new Comparator<EaxmLabsInfo>() {
			@Override
			public int compare(EaxmLabsInfo b1, EaxmLabsInfo b2) {
				if (Integer.parseInt(b1.getLabsNum()) > Integer.parseInt(b2.getLabsNum())) {
					return 1;
				} else {
					return -1;
				}
			}

		});
		if (map.get("labid") != null) {
			queryMap.put("labsid", map.get("labid"));
		} else {
			// 取lablist第一条数据
			if (lablist != null && lablist.size() > 0) {
				EaxmLabsInfo labs = lablist.get(0);
				queryMap.put("labsid", labs.getId());
			}
		}
		queryMap.put("itemsId", map.get("itemsId"));
		List<AdjustStudentRoomInfoVo> dataList = examSubjectInfoDao.querySecondLayoutRoom(queryMap);
		dataMap.put("labs", lablist);
		dataMap.put("labsStudent", dataList);
		return dataMap;
	}

	@Override
	public Map<String, Object> updateAndqueryTrialArrange(Map<String, String> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 首先查询这个考点有没有对这个岗位进行分组
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ExamLectureGroup examLectureGroup = new ExamLectureGroup();
		examLectureGroup.setSubjectId(map.get("itemsId"));
		examLectureGroup.setKaodian(map.get("kaodian"));
		examLectureGroup.setGanweiid(map.get("ganweiId"));
		List<ExamLectureGroup> list = examLectureGroupDao.selectAllByExamLectureGroup(examLectureGroup);
		List<ExamLectureGroup> tempList = new ArrayList<ExamLectureGroup>();
		if (list != null && list.size() > 0) {
			for (ExamLectureGroup e : list) {
				if ("1".equals(e.getPeriod())) {
					tempList.add(e);
				}
				resultMap.put("selectGroupList", tempList);
			}
			// 默认查询第一组数据
			List<ExamLectureGroup> TList = new ArrayList<ExamLectureGroup>();
			TList.add(list.get(0));
			TList.add(list.get(1));
			Collections.sort(TList, new Comparator<ExamLectureGroup>() {
				@Override
				public int compare(ExamLectureGroup b1, ExamLectureGroup b2) {
					if (Integer.parseInt(b1.getPeriod()) > Integer.parseInt(b2.getPeriod())) {
						return 1;
					} else {
						return -1;
					}
				}

			});
			ExamLectureGroup showE = new ExamLectureGroup();
			map.put("isgroup", "notnull");
			map.put("groupid", TList.get(0).getId());
			List<AdjustStudentRoomInfoVo> amList = examSubjectInfoDao.queryLectureGanWeiSchoolNum(map);
			map.put("groupid", TList.get(1).getId());
			List<AdjustStudentRoomInfoVo> pmList = examSubjectInfoDao.queryLectureGanWeiSchoolNum(map);
			resultMap.put("amList", amList);
			resultMap.put("pmList", pmList);
			if(amList.size()>0)
			{
				showE.setAmDate(TList.get(0).getStartDate() == null ? "" : sdf.format(TList.get(0).getStartDate()));
			}else
			{
				showE.setAmDate("");
			}
			if(pmList.size()>0)
			{
				showE.setPmDate(TList.get(1).getStartDate() == null ? "" : sdf.format(TList.get(1).getStartDate()));
			}else
			{
				showE.setPmDate("");
			}
			showE.setGroupName(TList.get(0).getGroupName());
			resultMap.put("showE", showE);
		} else {
			List<ExamLectureGroup> dataList = this.saveLectureGroup(map, 1);
			resultMap.put("selectGroupList", dataList);
		}
		// 获取学校与岗位数量
		map.remove("groupid");
		map.put("isgroup", "null");
		List<AdjustStudentRoomInfoVo> voList = examSubjectInfoDao.queryLectureGanWeiSchoolNum(map);
		resultMap.put("LeftDataList", voList);
		return resultMap;
	}

	/**
	 * 存储试讲组
	 * 
	 * @param map
	 */
	private List<ExamLectureGroup> saveLectureGroup(Map<String, String> map, int num) {
		// 存储第一组 上午
		ExamLectureGroup aevo = new ExamLectureGroup();
		aevo.setCreateDate(new Date());
		aevo.setGanweiid(map.get("ganweiId"));
		aevo.setGroupName("");
		aevo.setGroupNumber(new BigDecimal(num));
		aevo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		aevo.setIsShow("2");// 1 发布 2未发布
		aevo.setKaodian(map.get("kaodian"));
		aevo.setPeriod("1");// 1是上午 2是下午
		aevo.setSubjectId(map.get("itemsId"));
		// 存储第一组 下午
		ExamLectureGroup pevo = new ExamLectureGroup();
		pevo.setCreateDate(new Date());
		pevo.setGanweiid(map.get("ganweiId"));
		pevo.setGroupName("");
		pevo.setGroupNumber(new BigDecimal(num));
		pevo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		pevo.setIsShow("2");// 1 发布 2未发布
		pevo.setKaodian(map.get("kaodian"));
		pevo.setPeriod("2");// 1是上午 2是下午
		pevo.setSubjectId(map.get("itemsId"));
		examLectureGroupDao.saveExamLectureGroup(pevo);
		examLectureGroupDao.saveExamLectureGroup(aevo);
		List<ExamLectureGroup> dataList = new ArrayList<ExamLectureGroup>();
		dataList.add(aevo);
		return dataList;
	}

	@Override
	public List<Postset> queryLectureGanWei(Map<String, String> map) {

		return examSubjectInfoDao.queryLectureGanWei(map);
	}

	@Override
	public Map<String, Object> saveTrialArrange(Map<String, String> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String mes = "";
		// 首先判断这组是否发布，发布后不能添加
		ExamLectureGroup numGroup = examLectureGroupDao.selectExamLectureGroupById(map.get("groupNum"));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ExamLectureGroup examLectureGroup = new ExamLectureGroup();
		examLectureGroup.setSubjectId(map.get("itemsId"));
		examLectureGroup.setKaodian(map.get("kaodian"));
		examLectureGroup.setGanweiid(map.get("ganweiId"));
		examLectureGroup.setGroupNumber(numGroup.getGroupNumber());
		String amId = null;
		String pmId = null;
		String schoolSql="";
		String amsql="";
		String pmsql="";
			 amsql = this.getInSQL(map.get("amschoolId"));
			 pmsql = this.getInSQL(map.get("pmschoolId"));
			 schoolSql=this.getInSQL(map.get("leftschoolId"));
		List<ScoreEnterTrial> updateList = new ArrayList<ScoreEnterTrial>();
		List<ExamLectureGroup> list = examLectureGroupDao.selectAllByExamLectureGroup(examLectureGroup);
		if (list == null || list.size() == 0) {
			mes = "组号没建立,异常操作!";
		} else {
			ExamLectureGroup elg = list.get(0);
			if (elg.getIsShow().equals("2")) {
				if (list.size() == 2) {
					Collections.sort(list, new Comparator<ExamLectureGroup>() {
						@Override
						public int compare(ExamLectureGroup b1, ExamLectureGroup b2) {
							if (Integer.parseInt(b1.getPeriod()) > Integer.parseInt(b2.getPeriod())) {
								return 1;
							} else {
								return -1;
							}
						}

					});
					ExamLectureGroup ame = list.get(0);
					try {
						if (map.get("amDate") != "" && map.get("amDate") != null)
							ame.setStartDate(sdf.parse(map.get("amDate")));
					} catch (ParseException e1) {

						e1.printStackTrace();
					}		
				        ame.setGroupName(map.get("groupName"));
				    	amId = ame.getId();
						map.put("studentsql", amsql);
					    List<ScoreEnterTrial> amList = examSubjectInfoDao.queryLectureStudentBySchoolId(map);
					    
					for (ScoreEnterTrial am : amList) {
						am.setGroupId(amId);
						updateList.add(am);
					}
					ExamLectureGroup pme = list.get(1);
					try {
						if (map.get("pmDate") != "" && map.get("pmDate") != null)
							pme.setStartDate(sdf.parse(map.get("pmDate")));

					} catch (ParseException e1) {

						e1.printStackTrace();
					}
					pme.setGroupName(map.get("groupName"));
						pmId = pme.getId();
						map.put("studentsql", pmsql);
					List<ScoreEnterTrial> pmList = examSubjectInfoDao.queryLectureStudentBySchoolId(map);
					for (ScoreEnterTrial pm : pmList) {
						pm.setGroupId(pmId);
						updateList.add(pm);
					}
					String leftId =null;
					map.put("studentsql", schoolSql);
					List<ScoreEnterTrial> LeftList = examSubjectInfoDao.queryLectureStudentBySchoolId(map);
					for (ScoreEnterTrial lm : LeftList) {
						lm.setGroupId(leftId);
						updateList.add(lm);
					}
					// 批量更新
					if(updateList.size()>0)
					{
						scoreEnterTrialDao.updateBatchScoreEnterTrialList(updateList);
					}
					// 插入第二组数据
					examLectureGroup.setGroupNumber(new BigDecimal(numGroup.getGroupNumber().intValue() + 1));
					List<ExamLectureGroup> tempList = examLectureGroupDao.selectAllByExamLectureGroup(examLectureGroup);
					if (tempList == null || tempList.size() == 0) {
						this.saveLectureGroup(map, numGroup.getGroupNumber().intValue() + 1);
					}
					// 查询组
					ExamLectureGroup group = new ExamLectureGroup();
					group.setSubjectId(map.get("itemsId"));
					group.setKaodian(map.get("kaodian"));
					group.setGanweiid(map.get("ganweiId"));
					List<ExamLectureGroup> groupList = examLectureGroupDao.selectAllByExamLectureGroup(group);
					List<ExamLectureGroup> partList = new ArrayList<ExamLectureGroup>();
					if (groupList != null && groupList.size() > 0) {
						for (ExamLectureGroup e : groupList) {
							if ("1".equals(e.getPeriod())) {
								partList.add(e);
							}
						}
					}
					resultMap.put("selectGroupList", partList);
				
					examLectureGroupDao.updateExamLectureGroup(pme);
					examLectureGroupDao.updateExamLectureGroup(ame);
					resultMap.put("nowNum", numGroup.getGroupNumber().intValue());

				}
			} else {
				mes = "该组已发布，不能进行保存!";
			}
		}
		if (mes == "") {
			resultMap.put("msg", "success");
		} else {
			resultMap.put("msg", mes);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> queryTrialArrangeByGroup(Map<String, String> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ExamLectureGroup numGroup = examLectureGroupDao.selectExamLectureGroupById(map.get("groupNum"));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ExamLectureGroup examLectureGroup = new ExamLectureGroup();
		examLectureGroup.setSubjectId(map.get("itemsId"));
		examLectureGroup.setKaodian(map.get("kaodian"));
		examLectureGroup.setGanweiid(map.get("ganweiId"));
		examLectureGroup.setGroupNumber(numGroup.getGroupNumber());
		List<ExamLectureGroup> list = examLectureGroupDao.selectAllByExamLectureGroup(examLectureGroup);
		if (list != null && list.size() > 0) {
			// 默认查询第一组数据
			List<ExamLectureGroup> TList = new ArrayList<ExamLectureGroup>();
			TList.add(list.get(0));
			TList.add(list.get(1));
			Collections.sort(TList, new Comparator<ExamLectureGroup>() {
				@Override
				public int compare(ExamLectureGroup b1, ExamLectureGroup b2) {
					if (Integer.parseInt(b1.getPeriod()) > Integer.parseInt(b2.getPeriod())) {
						return 1;
					} else {
						return -1;
					}
				}

			});
			ExamLectureGroup showE = new ExamLectureGroup();
			showE.setGroupName(TList.get(0).getGroupName());
			TList.add(list.get(0));
			TList.add(list.get(1));
			map.put("isgroup", "notnull");
			map.put("groupid", TList.get(0).getId());
			List<AdjustStudentRoomInfoVo> amList = examSubjectInfoDao.queryLectureGanWeiSchoolNum(map);
			map.put("groupid", TList.get(1).getId());
			List<AdjustStudentRoomInfoVo> pmList = examSubjectInfoDao.queryLectureGanWeiSchoolNum(map);
			resultMap.put("amList", amList);
			resultMap.put("pmList", pmList);
			if(amList.size()>0)
			{
				showE.setAmDate(TList.get(0).getStartDate() == null ? "" : sdf.format(TList.get(0).getStartDate()));
			}else
			{
				showE.setAmDate("");
			}
			if(pmList.size()>0)
			{
				showE.setPmDate(TList.get(1).getStartDate() == null ? "" : sdf.format(TList.get(1).getStartDate()));
			}else
			{
				showE.setPmDate("");
			}
			resultMap.put("showE", showE);
		}
		return resultMap;
	}

	@Override
	public String showNotice(Map<String, String> map) {
		String mes ="";
		ExamLectureGroup numGroup = examLectureGroupDao.selectExamLectureGroupById(map.get("groupNum"));
		ExamLectureGroup examLectureGroup = new ExamLectureGroup();
		examLectureGroup.setSubjectId(map.get("itemsId"));
		examLectureGroup.setKaodian(map.get("kaodian"));
		examLectureGroup.setGanweiid(map.get("ganweiId"));
		examLectureGroup.setGroupNumber(numGroup.getGroupNumber());
		List<ExamLectureGroup> list = examLectureGroupDao.selectAllByExamLectureGroup(examLectureGroup);
		if(list.size()==2)
		{
			ExamLectureGroup group1=list.get(0);
			ExamLectureGroup group2=list.get(1);
			//查询
			ScoreEnterTrial s=new ScoreEnterTrial();
			s.setGroupId(group1.getId());
			List<ScoreEnterTrial> numList1=scoreEnterTrialDao.selectAllByScoreEnterTrial(s);
			s.setGroupId(group2.getId());
			List<ScoreEnterTrial> numList2=scoreEnterTrialDao.selectAllByScoreEnterTrial(s);
			if(numList1.size()==0&&numList2.size()==0)
			{
				mes="请先保存数据!";
				return mes;
			}
			if(group1.getIsShow().equals("1"))
			{
				mes="该组已发布!";
				return mes;
			}
			group1.setIsShow("1");
			group2.setIsShow("1");
			examLectureGroupDao.updateExamLectureGroup(group1);
			examLectureGroupDao.updateExamLectureGroup(group2);
		}else
		{
			mes="发布失败!";
			return mes;
		}
		return mes;
	}

	@Override
	public List<StudentLectureVo> queryLectureStudentBySchoolIdExcel(Map<String, String> map) {
	
		return examSubjectInfoDao.queryLectureStudentBySchoolIdExcel(map);
	}
	
	@Override
	public List<String> queryThemeDate() {
		
		return examSubjectInfoDao.queryThemeDate();
	}

	@Override
	public int querySecondKaoDianNum(Map<String, String> map) {
		
		return examSubjectInfoDao.querySecondKaoDianNum(map);
	}

	@Override
	public List<AdjustStudentRoomInfoVo> queryLectureGanWeiSchoolNum(Map<String, String> map) {
		
		return examSubjectInfoDao.queryLectureGanWeiSchoolNum(map);
	}

	@Override
	public List<AdjustStudentRoomInfoVo> querySchoolApproveGanWeiCount(Map<String, String> map) {
	
		return examSubjectInfoDao.querySchoolApproveGanWeiCount(map);
	}
}
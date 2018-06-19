package org.jypj.dev.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.EaxmLabsInfo;
import org.jypj.dev.entity.ExamItemsInfo;
import org.jypj.dev.entity.ExamLectureGroup;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.EaxmLabsInfoService;
import org.jypj.dev.service.ExamItemsInfoService;
import org.jypj.dev.service.ExamLectureGroupService;
import org.jypj.dev.service.ExamSubjectInfoService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.PositionService;
import org.jypj.dev.service.PostsetService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.LimitPositionVo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * ExamItemsInfo控制器
 * 
 * @author ChenYu
 *
 */
@Controller
@RequestMapping("/dg/examItemsInfo")
public class ExamItemsInfoController {

	@Resource
	private ExamItemsInfoService examItemsInfoService;
	@Resource
	private ThemeService themeService;
	@Resource
	private PositionService positionService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private PostsetService postsetService;
	@Resource
	private ExamSubjectInfoService examSubjectInfoService;
	@Resource
	private EaxmLabsInfoService eaxmLabsInfoService;

	@Resource
	private ExamLectureGroupService examLectureGroupService;

	@RequestMapping(value = "toExamItemsInfoList")
	public String toExamItemsInfoList(Model model) {
		// 年月日
		// List<Dictionary> nyrdics =
		// DictionaryCache.getDictionaryByCode("nyr");
		List<String> nyrdics = examSubjectInfoService.queryThemeDate();
		model.addAttribute("nyrdics", nyrdics);

		// 考试类型
		List<Dictionary> kslxdics = DictionaryCache.getDictionaryByCode("kslx");
		model.addAttribute("kslxdics", kslxdics);

		// 招聘项目
		List<Theme> themelist = themeService.selectAllByTheme(new Theme());
		model.addAttribute("themelist", themelist);

		// 根据年份查询项目ID
		// List<Position> posList=positionService.selectByPosition(condition);
		// model.addAttribute("posList", posList);
		return "/examination/items_info_list.vm";
	}

	@RequestMapping(value = "addItems")
	public String addItems(Model model, String themeId, String id, String themeName, String testType) {
		ExamItemsInfo examItemsInfo = null;
		try {
			// if(StringUtils.isNotBlank(themeName)){
			// themeName = java.net.URLDecoder.decode(themeName, "UTF-8");
			// model.addAttribute("themeName", themeName);
			// }
			Theme theme = themeService.selectThemeById(themeId);
			if (theme.getTheme().length() > 12) {
				model.addAttribute("themeName", theme.getTheme().substring(0, 12) + "...");
				model.addAttribute("themeNameTitle", theme.getTheme());
			} else {
				model.addAttribute("themeName", theme.getTheme());
				model.addAttribute("themeNameTitle", theme.getTheme());
			}
			if (StringUtils.isNotBlank(testType)) {
				model.addAttribute("testType", testType);
			}
			if (StringUtils.isNotBlank(id)) {
				examItemsInfo = examItemsInfoService.selectExamItemsInfoById(id);
				String permis = examItemsInfo.getPermisGw();
				if (StringUtils.isNotBlank(permis)) {
					String[] stringArr = permis.split(",");
					Postset postset = null;
					StringBuilder strb = new StringBuilder();
					for (int i = 0; i < stringArr.length; i++) {
						postset = postsetService.selectPostsetById(stringArr[i]);
						if (postset != null) {
							if (i == stringArr.length - 1) {
								strb.append(postset.getPostName());
							} else {
								strb.append(postset.getPostName() + ",");
							}
						}
					}
					String post = strb.toString();
					model.addAttribute("perGw", post);
				}
				Map<String, String> queryMap = new HashMap<String, String>();
				queryMap.put("empItemsId", themeId);
				Map<String, Integer> map = eaxmLabsInfoService.getLabsByKaodian(queryMap);
				if (map.get(examItemsInfo.getId()) != null) {
					examItemsInfo.setKaodianNum(map.get(examItemsInfo.getId()));
				} else {
					examItemsInfo.setKaodianNum(0);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
				String startTime = sdf.format(examItemsInfo.getStartTime());
				String endTime = sdf.format(examItemsInfo.getEndTime());
				model.addAttribute("startTime", startTime);
				model.addAttribute("endTime", endTime);
				model.addAttribute("itemsId", id);
			} else {
				examItemsInfo = new ExamItemsInfo();
				String str = examItemsInfoService.selectTestNum("1", themeId); // 查询有没有已经删除的记录
				if (StringUtils.isEmpty(str)) {
					str = examItemsInfoService.selectTestNum("2", themeId);
					if (StringUtils.isEmpty(str)) {
						str = "000";
					}
					int s = Integer.parseInt(str);
					s = ++s;
					s = s == 1000 ? 1 : s;
					String reslut = s >= 10 ? (s >= 100 ? s + "" : "0" + s) : "00" + s; // 计算
																						// 转型
					model.addAttribute("testNum", reslut);
				} else {
					model.addAttribute("testNum", str);
				}
				model.addAttribute("itemsId", "");
			}
			model.addAttribute("themeId", themeId);
			/*
			 * Map<String, String> queryMap=new HashMap<String, String>();
			 * queryMap.put("empItemsId", themeId); Map<String, Integer>
			 * map=eaxmLabsInfoService.getLabsByKaodian(queryMap);
			 * examItemsInfo.setKaodianNum(map.get(examItemsInfo.getId()));
			 */
			model.addAttribute("examItemsInfo", examItemsInfo);
			model.addAttribute("newdate", new Date());
			/*
			 * Notice notice=noticeService.selectObjectByThemeId(themeId);
			 * if(notice!=null&&notice.getWrittenEnd()!=null&&notice.
			 * getWrittenStart()!=null){
			 * model.addAttribute("wriStartDate",notice.getWrittenStart());
			 * model.addAttribute("wriEndDate",notice.getWrittenEnd()); }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/examination/items_info_input.vm";
	}

	@RequestMapping(value = "positionList")
	public String positionList(Model model, String themeId, String testType) {
		// 岗位类型
		List<Dictionary> gwlxdics = DictionaryCache.getDictionaryByCode("gwlb");
		model.addAttribute("gwlxdics", gwlxdics);
		// 学科类型
		List<Dictionary> xklxdics = DictionaryCache.getDictionaryByCode("xklb");
		model.addAttribute("xklxdics", xklxdics);
		model.addAttribute("themeId", themeId);
		model.addAttribute("testType", testType);
		return "/examination/position_list.vm";
	}

	// 根据年份和项目id查询招聘项目
	@ResponseBody
	@RequestMapping(value = "selectItems")
	public String selectItems(String year, String themeid) {
		JSONObject jo = new JSONObject();
		try {
			List<ExamItemsInfo> itemsList = examItemsInfoService.selectItems(year, themeid);
			jo.put("flag", true);
			jo.put("itemsList", itemsList);
			return jo.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			jo.put("flag", false);
			jo.put("itemsList", "itemsList");
			return jo.toJSONString();
		}
	}

	// 考点信息列表
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "examItemsInfoList", method = RequestMethod.POST)
	public String examItemsInfoList(Model model, Page page) {
		Map<String, Object> condition = page.getCondition();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (StringUtils.isBlank((String) condition.get("projectId"))) {
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "招聘项目ID为空！");
		} else {
			page = examItemsInfoService.selectOnePageByMap(page, condition);
			List<ExamItemsInfo> list = (List<ExamItemsInfo>) page.getResult();
			if (list.size() > 0) {
				Map<String, String> queryMap = new HashMap<String, String>();
				queryMap.put("empItemsId", condition.get("projectId").toString());
				Map<String, Integer> map = eaxmLabsInfoService.getLabsByKaodian(queryMap);
				for (ExamItemsInfo examItemsInfo : list) {
					if (map.get(examItemsInfo.getId()) != null) {
						examItemsInfo.setKaodianNum(map.get(examItemsInfo.getId()));
					} else {
						examItemsInfo.setKaodianNum(0);
					}
				}
			}
		}
		jsonMap.put("total", page.getTotalRows());
		jsonMap.put("rows", page.getResult());
		jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}

	// 查询限制岗位
	@ResponseBody
	@RequestMapping(value = "examLimit")
	public String examLimit(String itemsId, String testType, String fProfess, String zylx, String jobName) {
		JSONObject jo = new JSONObject();
		try {
			if (StringUtils.isNotBlank(jobName)) {
				jobName = java.net.URLDecoder.decode(jobName, "UTF-8");
			}
			List<LimitPositionVo> speciaList = new ArrayList<LimitPositionVo>();
			if (StringUtils.isNotBlank(testType) && testType.equals("2")) {// 统一笔试
				speciaList = examItemsInfoService.selectLimitPositionNum(itemsId, fProfess, zylx, jobName);
			} else if (StringUtils.isNotBlank(testType) && testType.equals("3")) {// 统一试讲
				speciaList = examItemsInfoService.selectLimitPositionNumTrial(itemsId, fProfess, zylx, jobName);
			}
			jo.put("flag", true);
			jo.put("speciaList", speciaList);
			return jo.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			jo.put("flag", false);
			jo.put("speciaList", "");
			return jo.toJSONString();
		}
	}

	// 保存考点信息
	@ResponseBody
	@RequestMapping(value = "saveExamItemsInfo")
	public JSONObject saveExamItemsInfo(ExamItemsInfo examItemsInfo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		JSONObject jsonMap = new JSONObject();
		try {
			Notice notice = noticeService.selectObjectByThemeId(examItemsInfo.getItemsId());

			if (examItemsInfo.getStartTime() == null || examItemsInfo.getEndTime() == null || examItemsInfo == null
					|| examItemsInfo.getItemsId() == null || examItemsInfo.getTestNum() == null) {
				jsonMap.put("flag", "false");
				jsonMap.put("msg", "操作失败，数据不能为空！");
			} else {
				if (notice != null && notice.getWrittenEnd() != null && notice.getWrittenStart() != null) {
					Date noticeWriStart = notice.getWrittenStart();// 笔试公告开始时间
					Date noticeWriEnd = notice.getWrittenEnd();// 笔试公告结束时间
					Date noticeTrialStart = notice.getLectureStart();// 试讲公告开始时间
					Date noticeTrialEnd = notice.getLectureEnd();// 试讲公告结束时间
					Date startTime = examItemsInfo.getStartTime();
					Date endTime = examItemsInfo.getEndTime();
					// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd
					// HH:mm:ss");
					if (examItemsInfo.getType().equals("2")) {// 笔试
						int min = startTime.compareTo(noticeWriStart);
						//int max = endTime.compareTo(noticeWriEnd);
						if (min < 0 ) {
							jsonMap.put("flag", "false");
							jsonMap.put("msg", "考点的开始时间要在招聘公告设置的时间范围内！");
						} else {
							String flag = examItemsInfoService.saveExamItemsInfo(examItemsInfo, user);
							if (flag != null) {
								if (flag.substring(0, 5).equalsIgnoreCase("false")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "岗位信息重复！有" + flag.substring(5, flag.length()) + "个岗位重复！");
								} else if (flag.equalsIgnoreCase("objnull")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "保存对象为空！");
								} else if (flag.equalsIgnoreCase("testnum")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "试室号重复！");
								} else if (flag.equalsIgnoreCase("success")) {
									jsonMap.put("flag", "success");
									jsonMap.put("msg", "操作成功！");
								}
							}
						}
					} else if (examItemsInfo.getType().equals("3")) {// 试讲
						int min = startTime.compareTo(noticeTrialStart);
						//int max = endTime.compareTo(noticeTrialEnd);
						if (min < 0 ) {
							jsonMap.put("flag", "false");
							jsonMap.put("msg", "考点的开始时间要在招聘公告设置的时间范围内！");
						} else {
							String flag = examItemsInfoService.saveExamItemsInfo(examItemsInfo, user);
							if (flag != null) {
								if (flag.substring(0, 5).equalsIgnoreCase("false")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "岗位信息重复！有" + flag.substring(5, flag.length()) + "个岗位重复！");
								} else if (flag.equalsIgnoreCase("testnum")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "试室号重复！");
								} else if (flag.equalsIgnoreCase("objnull")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "保存对象为空！");
								} else if (flag.equalsIgnoreCase("success")) {
									jsonMap.put("flag", "success");
									jsonMap.put("msg", "操作成功！");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
		return jsonMap;
	}

	// 修改考点信息
	@ResponseBody
	@RequestMapping(value = "updateExamItemsInfo")
	public JSONObject updateExamItemsInfo(ExamItemsInfo examItemsInfo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		JSONObject jsonMap = new JSONObject();
		try {
			Notice notice = noticeService.selectObjectByThemeId(examItemsInfo.getItemsId());
			if (examItemsInfo.getStartTime() == null || examItemsInfo.getEndTime() == null) {
				jsonMap.put("flag", "false");
				jsonMap.put("msg", "操作失败，数据不能为空！");
			} else {

				if (notice != null && notice.getWrittenEnd() != null && notice.getWrittenStart() != null) {
					Date noticeWriStart = notice.getWrittenStart();// 笔试公告开始时间
					Date noticeWriEnd = notice.getWrittenEnd();// 笔试公告结束时间
					Date noticeTrialStart = notice.getLectureStart();// 试讲公告开始时间
					Date noticeTrialEnd = notice.getLectureEnd();// 试讲公告结束时间
					Date startTime = examItemsInfo.getStartTime();
					Date endTime = examItemsInfo.getEndTime();
					// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd
					// HH:mm:ss");
					if (examItemsInfo.getType().equals("2")) {// 笔试
						int min = startTime.compareTo(noticeWriStart);
						//int max = endTime.compareTo(noticeWriEnd);
						if (min < 0) {
							jsonMap.put("flag", "false");
							jsonMap.put("msg", "考点的开始时间要在招聘公告设置的时间范围内！");
						} else {
							String flag = examItemsInfoService.updateExamItemsInfoByField(examItemsInfo, user);
							if (flag != null) {
								if (flag.substring(0, 5).equalsIgnoreCase("false")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "岗位信息重复！有" + flag.substring(5, flag.length()) + "个岗位重复！");
								} else if (flag.equalsIgnoreCase("objnull")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "保存对象为空！");
								} else if (flag.equalsIgnoreCase("success")) {
									jsonMap.put("flag", "success");
									jsonMap.put("msg", "操作成功！");
								}
							}
						}
					} else if (examItemsInfo.getType().equals("3")) {// 试讲
						int min = startTime.compareTo(noticeTrialStart);
						//int max = endTime.compareTo(noticeTrialEnd);
						if (min < 0) {
							jsonMap.put("flag", "false");
							jsonMap.put("msg", "考点的开始时间要在招聘公告设置的时间范围内！");
						} else {
							String flag = examItemsInfoService.updateExamItemsInfoByField(examItemsInfo, user);
							if (flag.equalsIgnoreCase("false")) {
								jsonMap.put("flag", "false");
								jsonMap.put("msg", "岗位信息重复！");
							} else if (flag.equalsIgnoreCase("objnull")) {
								jsonMap.put("flag", "false");
								jsonMap.put("msg", "保存对象为空！");
							} else if (flag.equalsIgnoreCase("success")) {
								jsonMap.put("flag", "success");
								jsonMap.put("msg", "操作成功！");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
		return jsonMap;
	}

	//保存时验证
	@ResponseBody
	@RequestMapping(value = "verifyExam")
	public JSONObject verifyExam(ExamItemsInfo examItemsInfo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		JSONObject jsonMap = new JSONObject();
		try {
			Notice notice = noticeService.selectObjectByThemeId(examItemsInfo.getItemsId());
			if (examItemsInfo.getStartTime() == null || examItemsInfo.getEndTime() == null || examItemsInfo == null
					|| examItemsInfo.getItemsId() == null || examItemsInfo.getTestNum() == null) {
				jsonMap.put("flag", "false");
				jsonMap.put("msg", "操作失败，数据不能为空！");
			} else {
				if (notice != null && notice.getWrittenEnd() != null && notice.getWrittenStart() != null) {
					Date noticeWriStart = notice.getWrittenStart();// 笔试公告开始时间
					Date noticeWriEnd = notice.getWrittenEnd();// 笔试公告结束时间
					Date noticeTrialStart = notice.getLectureStart();// 试讲公告开始时间
					Date noticeTrialEnd = notice.getLectureEnd();// 试讲公告结束时间
					Date startTime = examItemsInfo.getStartTime();
					Date endTime = examItemsInfo.getEndTime();
					if (examItemsInfo.getType().equals("2")) {// 笔试
						int min = startTime.compareTo(noticeWriStart);
						//int max = endTime.compareTo(noticeWriEnd);
						if (min < 0) {
							jsonMap.put("flag", "false");
							jsonMap.put("msg", "考点的开始时间要在招聘公告设置的时间范围内！");
						} else {
							String flag = examItemsInfoService.verifyExamItemsInfo(examItemsInfo, user);
							if (flag != null) {
								if (flag.substring(0, 5).equalsIgnoreCase("false")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "岗位信息重复！有" + flag.substring(5, flag.length()) + "个岗位重复！");
								} else if (flag.equalsIgnoreCase("objnull")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "保存对象为空！");
								} else if (flag.equalsIgnoreCase("testnum")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "试室号重复！");
								} else if (flag.equalsIgnoreCase("success")) {
									jsonMap.put("flag", "success");
									jsonMap.put("msg", "操作成功！");
								}
							}
						}
					} else if (examItemsInfo.getType().equals("3")) {// 试讲
						int min = startTime.compareTo(noticeTrialStart);
						//int max = endTime.compareTo(noticeTrialEnd);
						if (min < 0) {
							jsonMap.put("flag", "false");
							jsonMap.put("msg", "考点的开始时间要在招聘公告设置的时间范围内！");
						} else {
							String flag = examItemsInfoService.verifyExamItemsInfo(examItemsInfo, user);
							if (flag != null) {
								if (flag.substring(0, 5).equalsIgnoreCase("false")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "岗位信息重复！有" + flag.substring(5, flag.length()) + "个岗位重复！");
								} else if (flag.equalsIgnoreCase("testnum")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "试室号重复！");
								} else if (flag.equalsIgnoreCase("objnull")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "保存对象为空！");
								} else if (flag.equalsIgnoreCase("success")) {
									jsonMap.put("flag", "success");
									jsonMap.put("msg", "操作成功！");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
		return jsonMap;
	}
	
	//修改时验证
	@ResponseBody
	@RequestMapping(value = "verifyUpdateExam")
	public JSONObject verifyUpdateExam(ExamItemsInfo examItemsInfo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		JSONObject jsonMap = new JSONObject();
		try {
			Notice notice = noticeService.selectObjectByThemeId(examItemsInfo.getItemsId());
			if (examItemsInfo.getStartTime() == null || examItemsInfo.getEndTime() == null) {
				jsonMap.put("flag", "false");
				jsonMap.put("msg", "操作失败，数据不能为空！");
			} else {

				if (notice != null && notice.getWrittenEnd() != null && notice.getWrittenStart() != null) {
					Date noticeWriStart = notice.getWrittenStart();// 笔试公告开始时间
					Date noticeWriEnd = notice.getWrittenEnd();// 笔试公告结束时间
					Date noticeTrialStart = notice.getLectureStart();// 试讲公告开始时间
					Date noticeTrialEnd = notice.getLectureEnd();// 试讲公告结束时间
					Date startTime = examItemsInfo.getStartTime();
					Date endTime = examItemsInfo.getEndTime();
					// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd
					// HH:mm:ss");
					if (examItemsInfo.getType().equals("2")) {// 笔试
						int min = startTime.compareTo(noticeWriStart);
						//int max = endTime.compareTo(noticeWriEnd);
						if (min < 0) {
							jsonMap.put("flag", "false");
							jsonMap.put("msg", "考点的开始时间要在招聘公告设置的时间范围内！");
						} else {
							String flag = examItemsInfoService.verifyUpdateExamItemsInfo(examItemsInfo, user);
							if (flag != null) {
								if (flag.substring(0, 5).equalsIgnoreCase("false")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "岗位信息重复！有" + flag.substring(5, flag.length()) + "个岗位重复！");
								} else if (flag.equalsIgnoreCase("objnull")) {
									jsonMap.put("flag", "false");
									jsonMap.put("msg", "保存对象为空！");
								} else if (flag.equalsIgnoreCase("success")) {
									jsonMap.put("flag", "success");
									jsonMap.put("msg", "操作成功！");
								}
							}
						}
					} else if (examItemsInfo.getType().equals("3")) {// 试讲
						int min = startTime.compareTo(noticeTrialStart);
						//int max = endTime.compareTo(noticeTrialEnd);
						if (min < 0) {
							jsonMap.put("flag", "false");
							jsonMap.put("msg", "考点的开始时间要在招聘公告设置的时间范围内！");
						} else {
							String flag = examItemsInfoService.updateExamItemsInfoByField(examItemsInfo, user);
							if (flag.equalsIgnoreCase("false")) {
								jsonMap.put("flag", "false");
								jsonMap.put("msg", "岗位信息重复！");
							} else if (flag.equalsIgnoreCase("objnull")) {
								jsonMap.put("flag", "false");
								jsonMap.put("msg", "保存对象为空！");
							} else if (flag.equalsIgnoreCase("success")) {
								jsonMap.put("flag", "success");
								jsonMap.put("msg", "操作成功！");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
		return jsonMap;
	}
	
	
	// 删除考点信息(批量删除)
	@ResponseBody
	@RequestMapping(value = "deleteExamItemsInfo")
	public JSONObject deleteExamItemsInfo(String ids,String themeId,String testType) {
		JSONObject jsonMap = new JSONObject();
		Notice notice = noticeService.selectObjectByThemeId(themeId);
		if ("2".equals(testType)) {
			if (new Date().getTime() > notice.getWrittenStart().getTime()) {
				jsonMap.put("msg", "笔试时间已开始，不能删除笔试考点!");
				return jsonMap;
			}

		}
		if ("3".equals(testType)) {
			if (new Date().getTime() > notice.getLectureStart().getTime()) {
				jsonMap.put("msg", "试讲时间已开始，不能删除试讲考点!");
				return jsonMap;
			}

		}
		List<ExamItemsInfo> delList = new ArrayList<ExamItemsInfo>();
		try {
			String[] idArr = ids.split(",");
			ExamItemsInfo examItemsInfo = null;
			for (int i = 0; i < idArr.length; i++) {
				if (StringUtils.isNotBlank(idArr[i])) {
					examItemsInfo = examItemsInfoService.selectExamItemsInfoById(idArr[i]);
				}
				// examItemsInfoService.deleteExamItemsInfoById(idArr[i]);
				if (examItemsInfo.getType().equals("2")) // 笔试
				{
					EaxmLabsInfo eaxmLabsInfo = new EaxmLabsInfo();
					eaxmLabsInfo.setTestId(examItemsInfo.getId());
					List<EaxmLabsInfo> list = eaxmLabsInfoService.selectAllByEaxmLabsInfo(eaxmLabsInfo);
					if (list.size() > 0) {
						jsonMap.put("msg", examItemsInfo.getTestName() + "此考点已发布,不能删除!");
						return jsonMap;
					}
					// 试讲
				} else {
					ExamLectureGroup examLectureGroup = new ExamLectureGroup();
					examLectureGroup.setKaodian(examItemsInfo.getId());
					List<ExamLectureGroup> dataList = examLectureGroupService
							.selectAllByExamLectureGroup(examLectureGroup);
					if (dataList.size() > 0) {
						
						for(ExamLectureGroup e:dataList)
						{
						   if(e.getGroupName()!=null&&e.getGroupName()!="")
						   {
								jsonMap.put("msg", examItemsInfo.getTestName() + "试讲地点已关联保存,不能删除!");
								return jsonMap;
						   }
						}
					
					}

				}
				delList.add(examItemsInfo);
			}
			for (ExamItemsInfo e : delList) {
				e.setDeleteStatus("1");
				examItemsInfoService.updateExamItemsInfo(e);
			}
			jsonMap.put("flag", "success");
			jsonMap.put("msg", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("flag", "error");
			jsonMap.put("msg", "操作失败，请稍后重试！");
		}
		return jsonMap;
	}

	/**
	 * 查询所有数据接口
	 * 
	 * @return code =-1 表示有异常 code=-2 表示没有数据 datas 菜单数据
	 */
	@RequestMapping("selectAllExamItemsInfo")
	@ResponseBody
	public Map<String, Object> selectAllExamItemsInfo() {
		Map<String, Object> map = new HashMap<>();
		int code = 0;
		try {
			List<ExamItemsInfo> examItemsInfos = examItemsInfoService.selectAllByMap(new HashMap<String, Object>());
			if (examItemsInfos.size() == 0) {
				code = -2;
			}
			map.put("datas", examItemsInfos);
		} catch (Exception e) {
			code = -1;
		}
		map.put("code", code);
		return map;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
	/**
	 * 校验
	 * @param themeId
	 * @param testType
	 * @param id
	 * @return
	 */
	@RequestMapping("checkThemeTime")
	@ResponseBody
	public Map<String, Object> checkThemeTime(String themeId, String testType, String id) {
		Map<String, Object> map = new HashMap<>();
		// 通过公共时间判断
		Notice notice = noticeService.selectObjectByThemeId(themeId);
		if (id != null) {
			ExamItemsInfo info = examItemsInfoService.selectExamItemsInfoById(id);
			testType = info.getType();

			if ("2".equals(testType)) {
				EaxmLabsInfo eaxmLabsInfo = new EaxmLabsInfo();
				eaxmLabsInfo.setTestId(info.getId());
				List<EaxmLabsInfo> list = eaxmLabsInfoService.selectAllByEaxmLabsInfo(eaxmLabsInfo);
				if (list.size() > 0) {
					map.put("msg", "此考点已发布,不能修改!");
					return map;
				}
			} else if ("3".equals(testType)) {
				ExamLectureGroup examLectureGroup = new ExamLectureGroup();
				examLectureGroup.setKaodian(info.getId());
				List<ExamLectureGroup> dataList = examLectureGroupService.selectAllByExamLectureGroup(examLectureGroup);
				if (dataList.size() > 0) {
					for(ExamLectureGroup e:dataList)
					{
					   if(e.getGroupName()!=null&&e.getGroupName()!="")
					   {
						   map.put("msg", "试讲地点已关联保存,不能修改!");
						   return map;
					   }
					}
				
				}
			}

		}
		if ("2".equals(testType)) {
			if (new Date().getTime() > notice.getWrittenStart().getTime()) {
				map.put("msg", "笔试时间已开始，不能添加或修改笔试考点!");
				return map;
			}

		}
		if ("3".equals(testType)) {
			if (new Date().getTime() > notice.getLectureStart().getTime()) {
				map.put("msg", "试讲时间已开始，不能添加或修改试讲考点!");
				return map;
			}

		}
		map.put("msg", "success");
		return map;
	}

	
	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("queryDetailInfo")
	public String queryDetailInfo(String id,String themeId,Model m) {
		
		Theme theme = themeService.selectThemeById(themeId);
		if (theme.getTheme().length() > 12) {
			m.addAttribute("themeName", theme.getTheme().substring(0, 12) + "...");
			m.addAttribute("themeNameTitle", theme.getTheme());
		} else {
			m.addAttribute("themeName", theme.getTheme());
			m.addAttribute("themeNameTitle", theme.getTheme());
		}
		// 查询所有岗位
		List<Postset> postList = postsetService.selectAllPostset();
		Map<String, String> postMap = new HashMap<String, String>();
	
		for (Postset p : postList) {
			postMap.put(p.getId(), p.getPostName());
		}
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("empItemsId", themeId);
		Map<String, Integer> map = eaxmLabsInfoService.getLabsByKaodian(queryMap);
		ExamItemsInfo examItemsInfo = examItemsInfoService.selectExamItemsInfoById(id);
		if(map.get(examItemsInfo.getId())==null)
		{
			examItemsInfo.setKaodianNum(0);
		}else
		{
			examItemsInfo.setKaodianNum(map.get(examItemsInfo.getId()));
		}
		String str=examItemsInfo.getPermisGw();
		String[] strs=str.split(",");
		String strName="";
		for(String s:strs)
		{
			if(postMap.get(s)!=null)
			{
				strName=strName+postMap.get(s)+",";
			}
		}
		if(strName!="")
		{
			strName=strName.substring(0, strName.lastIndexOf(","));
		}
	
		examItemsInfo.setPermisGw(strName);
		m.addAttribute("examItemsInfo",examItemsInfo);
		return "examination/items_info_detail.vm";

	}

}
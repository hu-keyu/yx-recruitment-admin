package org.jypj.dev.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jpush.api.report.UsersResult.User;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Dictionary控制器
 * 
 * @author ChenYu
 *
 */
@Controller
@RequestMapping("/dg/dictionary")
public class DictionaryController {

	@Resource
	private DictionaryService dictionaryService;

	@RequestMapping(value = "toDictionaryList")
	public String toDictionaryList() {
		return "back/dictionary";
	}

	/**
	 * 分页查询
	 * 
	 * @param dictionary
	 * @param page
	 * @return
	 */
	@RequestMapping("selectAllDictionary")
	@ResponseBody
	public Page selectAllDictionary(Dictionary dictionary, Page page) {
		page = dictionaryService.selectOnePageByDictionary(page, dictionary);
		return page;
	}

	/**
	 * 保存或者更新
	 * 
	 * @param dic
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveOrUpdate")
	public Map<String, Object> saveOrUpdate(Dictionary dic, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		User user = (User) request.getSession().getAttribute("user");
		String loginName = "";
		int code = 0;
		if (user != null) {
			// loginName = user.getLoginName();
		}
		try {
			if (StringUtil.isNotEmpty(dic.getId())) {
				// 不为空就更新
				if (checkIsExist(dic)) {
					code = -2;// code value已经存在
				} else {
					dic.setModifyDate(new Date());
					dic.setModifyUser(loginName);
					dictionaryService.updateDictionaryByField(dic);
				}
			} else {
				// 为空就保存
				if (checkIsExist(dic)) {
					code = -2;// code value已经存在
				} else {
					dic.setCreateDate(new Date());
					dic.setModifyDate(new Date());
					dic.setCreateUser(loginName);
					dic.setModifyUser(loginName);
					dictionaryService.saveDictionary(dic);
				}
			}

		} catch (Exception e) {
			code = -1;
		}
		map.put("code", code);
		return map;
	}

	/**
	 * 判断同样的code中value是否已经存在
	 * 
	 * @return false表示可以通过 true表示不能通过
	 */
	public boolean checkIsExist(Dictionary dictionaries) {
		Dictionary dictionary = new Dictionary();
		dictionary.setCode(dictionaries.getCode());
		dictionary.setValue(dictionaries.getValue());
		Dictionary dic = dictionaryService.selectObjectByDictionary(dictionary);
		if (dic != null) {
			if (dictionaries.getId().equals(dic.getId())) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            单个删除
	 * @return -1代表失败 0代表成功
	 */
	@RequestMapping(value = "deleteById")
	@ResponseBody
	public int deleteById(String id) {
		int code = 0;
		try {
			dictionaryService.deleteDictionaryById(id);
		} catch (Exception e) {
			code = -1;

		}
		return code;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteAll")
	public int deleteAll(String ids) {
		int code = dictionaryService.deleteAllByIds(ids);
		return code;
	}

	/**
	 * 根据code查询改code的所有字典
	 * 
	 * @param dictionary
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectByCodeValue")
	public List<Dictionary> selectByCodeValue(String code) {
		List<Dictionary> dics = DictionaryCache.getDictionaryByCode(code);
		return dics;
	}

	/**
	 * 初始化字典
	 * 
	 * @param dictionary
	 * @return
	 */
	@RequestMapping(value = "initDictionary")
	public String initDictionary() {
		try {
			List<String> dataList = new ArrayList<String>();
//			dataList.add("dwdm");// 单位代码
//			dataList.add("gwdm");// 岗位代码表
//			dataList.add("gwlb");// 岗位类别
//			dataList.add("jszgzl");// 教师资格种类
//			dataList.add("kslx");// 考试类型
//			dataList.add("mbwt");// 密保问题
//			dataList.add("mzdm");// 中国名族代码表
//			dataList.add("rych");// 荣誉称号
			dataList.add("xklb");// 学科类别
//			dataList.add("xllx");// 学历类型
//			dataList.add("xwlx");// 学位类型
//			dataList.add("zyjszg");// 专业技术资格种类
//			dataList.add("zylx");// 专业类型
//			dataList.add("zzmm");// 政治面貌代码表
//			dataList.add("zggxdm");// 中国高校代码
//			dataList.add("jtcygx");// 家庭成员关系
//			dataList.add("jylx");// 教育类型
//			dataList.add("bylx");//毕业类型
//			dataList.add("nyr");//年份
			dataList.add("xdlx");
			for (String s : dataList) {
				Workbook rwb = null;
				Cell cell = null;
				// 创建输入流
				InputStream stream = new FileInputStream("E:\\init_data\\init\\" + s+".xls");
				// 获取Excel文件对象
				rwb = Workbook.getWorkbook(stream);
				// 获取文件的指定工作表 默认的第一个
				Sheet sheet = rwb.getSheet(0);
				// 行数(表头的目录不需要，从1开始)
				for (int i = 0; i < sheet.getRows(); i++) {
					Dictionary d=new Dictionary();
					// 创建一个数组 用来存储每一列的值
					String[] str = new String[sheet.getColumns()];
					// 列数
					for (int j = 0; j < sheet.getColumns(); j++) {
						// 获取第i行，第j列的值
						if(i!=0)
						{	cell = sheet.getCell(j, i);
							if(cell!=null&&cell.getContents()!=null&&cell.getContents()!="")
							{
							
								d.setId(UUID.randomUUID().toString().replaceAll("-", ""));
								d.setCode(s);
								d.setText(cell.getContents());
								if(j==0)
								{
									d.setValue(cell.getContents());
									d.setSortOrder(new BigDecimal(cell.getContents()));
								}
								if(j==1)
								{
									d.setText(cell.getContents());
								}
							    d.setIsDefault("1");
							    d.setDescribe("");
							    d.setCreateDate(new Date());
							    if(s.equals("dwdm"))
							    {
							    	  d.setCodeName("单位代码");
							    	  
							    }else if(s.equals("gwdm"))
							    {
							    	 d.setCodeName("岗位代码表");
							    }else if(s.equals("gwlb"))
							    {
							    	d.setCodeName("岗位代码表");
							    }else if(s.equals("jszgzl"))
							    {
							    	d.setCodeName("教师资格种类");
							    }else if(s.equals("kslx"))
							    {
							    	d.setCodeName("考试类型");
							    }else if(s.equals("mbwt"))
							    {
							    	d.setCodeName("密保问题");
							    }else if(s.equals("mzdm"))
							    {
							    	d.setCodeName("中国名族代码表");
							    }else if(s.equals("rych"))
							    {
							    	d.setCodeName("荣誉称号");
							    }else if(s.equals("xklb"))
							    {
							    	d.setCodeName("学科类别");
							    }else if(s.equals("xllx"))
							    {
							    	d.setCodeName("学历类型");
							    }
							    else if(s.equals("xwlx"))
							    {
							    	d.setCodeName("学位类型");
							    }else if(s.equals("zyjszg"))
							    {
							    	d.setCodeName("专业技术资格种类");
							    }else if(s.equals("zylx"))
							    {
							    	d.setCodeName("专业类型");
							    }else if(s.equals("zzmm"))
							    {
							    	d.setCodeName("政治面貌代码");
							    }else if(s.equals("zggxdm"))
							    {
							    	d.setCodeName("中国高校代码");
							    }else if(s.equals("jtcygx"))
							    {
							    	d.setCodeName("家庭成员关系");
							    }else if(s.equals("jylx"))
							    {
							    	d.setCodeName("教育类型");
							    }else if(s.equals("bylx"))
							    {
							    	d.setCodeName("毕业类型");
							    }else if(s.equals("xdlx"))
							    {
							    	d.setCodeName("学段类型");
							    }
							    
							    
							}
							
							}
							
					}
					if(i!=0)
					{
						dictionaryService.saveDictionary(d);
					}
				
				}

			}
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (BiffException e) {
			
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
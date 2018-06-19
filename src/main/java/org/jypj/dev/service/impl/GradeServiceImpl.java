package org.jypj.dev.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jypj.dev.dao.AttachementDao;
import org.jypj.dev.dao.GradeAdjustLogDao;
import org.jypj.dev.dao.GradeDao;
import org.jypj.dev.dao.GradeExportLogDao;
import org.jypj.dev.dao.NoticeDao;
import org.jypj.dev.dao.ScoreEnterInformationDao;
import org.jypj.dev.dao.ScoreEnterTrialDao;
import org.jypj.dev.dao.ScoreEnterWritienDao;
import org.jypj.dev.dao.StudentInfoDao;
import org.jypj.dev.dao.ThemeDao;
import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.Grade;
import org.jypj.dev.entity.GradeAdjustLog;
import org.jypj.dev.entity.GradeExportLog;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.ScoreEnterInformation;
import org.jypj.dev.entity.ScoreEnterTrial;
import org.jypj.dev.entity.ScoreEnterWritien;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.GradeService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.util.GradeUtils;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.PropertiesUtil;
import org.jypj.dev.util.StringUtil;
import org.jypj.dev.vo.ScoreEntersOutVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;


@Service("gradeService")
public class GradeServiceImpl implements GradeService {
	
	private static Logger Log_=Logger.getLogger(GradeServiceImpl.class);
	
    @Resource
    private GradeDao gradeDao;
    @Resource 
    private StudentInfoDao studentInfoDao;
    @Resource
    private ThemeDao themeDao;
    @Resource
    private AttachementDao attachementDao;
    @Resource
    private GradeExportLogDao gradeExportLogDao;
    @Resource
    private GradeAdjustLogDao gradeAdjustLogDao;
    @Resource
    private ScoreEnterWritienDao scoreEnterWritienDao;
    @Resource
    private ScoreEnterTrialDao scoreEnterTrialDao ;
    @Resource
    private ScoreEnterInformationDao scoreEnterInformationDao;
    @Resource
    private NoticeDao noticeDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param grade
	 * @return 保存后的对象包括ID
	 */	
	public int saveGradeByField(Grade grade){
	
		return gradeDao.saveGradeByField(grade);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param grade 
	 * @return 保存后的对象包括ID
	 */	
	public int saveGrade(Grade grade){
	
		return gradeDao.saveGrade(grade);
	}
	
	@Transactional
	public Integer saveGradesList(List<Grade> gradesList) {
		return gradeDao.saveGradesList(gradesList);
	}
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteGradeById(String id){
    
    	return gradeDao.deleteGradeById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteGradeByObject(Grade grade){
    
    	return gradeDao.deleteGradeByObject(grade);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param grade 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeByField(Grade grade){
    
    	return gradeDao.updateGradeByField(grade);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param grade 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGrade(Grade grade){
    
    	return gradeDao.updateGrade(grade);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Grade 
	 */	
    public Grade selectGradeById(String id){
    
    	return gradeDao.selectGradeById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Grade>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Grade> grades =gradeDao.selectOnePageByMap(page,map);
	     	if(grades!=null&&grades.size()>0){
	     		page.setResult(grades);
	     	}else{
	     		page.setResult(new ArrayList<Grade>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param grade  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByGrade(Page page,Grade grade){
 		 List<Grade> grades =gradeDao.selectOnePageByGrade(page,grade);
	     	if(grades!=null&&grades.size()>0){
	     		page.setResult(grades);
	     	}else{
	     		page.setResult(new ArrayList<Grade>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Grade>
	 */	
     public List<Grade> selectAllByMap(Map<String,Object> map){
     	return gradeDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Grade>
	 */	
     public List<Grade> selectAllByGrade(Grade grade){
     
    	 return gradeDao.selectAllByGrade(grade);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Grade
	 */	
     public Grade selectObjectByMap(Map<String,Object> map){
     
    	 return gradeDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Grade
	 */	
     public Grade selectObjectByGrade(Grade grade){
     
     	return gradeDao.selectObjectByGrade(grade);
     }

	public List<Grade> selectScoreByGrade(Map<String, Object> map) {
		return selectScoreByGrade(map);
	}

	@Override
	public Integer selectScoreCount(Map<String, Object> map) {
		return gradeDao.selectScoreCount(map);
	}

	@Override
	public void saveImportGrade(CommonsMultipartFile file, String projectId,User user, JSONObject jsonMap) throws Exception{
		Theme theme=themeDao.selectThemeById(projectId);
    	if(theme !=null ){
    		if(theme.getStep() != 2){
    			jsonMap.put("msg", "当前招聘主题不在教育局发布面试名单环节中，操作失败，请确认！");
    			throw new Exception("当前招聘主题不在教育局发布面试名单环节中，操作失败，请确认！");
    		}
    	}
		this.checkFile(file,jsonMap);
		String originalFilename = file.getOriginalFilename();
        InputStream is=file.getInputStream();
        List<Map<String, String>> dataList=ExcelUtils.readExcel(originalFilename, is);
        Map<String,List<Map<String, String>>> mapData=this.checkData(user,projectId,dataList,jsonMap);
        List<Map<String, String>> correctData=mapData.get("correctData");//正确数据
        List<Map<String, String>> errorData=mapData.get("errorData");//错误数据
        this.operCorrectData(correctData,projectId,user);
        this.operErrorData(correctData,errorData,projectId,user,originalFilename);
	}
	
	/**
	 * 保存正确数据
	 * @param dataList
	 */
	private void operCorrectData(List<Map<String, String>> dataList,String projectId,User user){
		if(dataList != null && !dataList.isEmpty()){
			List<Grade> gradeList=new ArrayList<Grade>();
			boolean flag=false;//是否录取标识位
			Grade grade=null;
			for(Map<String, String> map : dataList){
				String ticketNum=map.get("准考证号");
				String name=map.get("姓名");
				String score=map.get("成绩");
				String offer=map.get("是否录取");
				grade=new Grade(score,projectId,user.getSchoolId(),user.getId(),ticketNum,name);
				grade.setRemark("1");
				if(map.containsKey("是否录取")){//包含录取
					offer=offer.equals("是")?"1":"0";
					grade.setIsEmploy(offer);
				}else{
					flag=true;
					grade.setIsEmploy("0");
				}
				gradeList.add(grade);
			}
			gradeDao.updateGradeList(gradeList);
			if(flag){//没有是否录取，则通过成绩自动算出哪些考生应该录取
				isEmploy(projectId,user);
			}
		}
	}
	
	/**
	 * 通过成绩自动算出哪些考生应该录取
	 * @param projectId
	 * @param user
	 */
	private void isEmploy(String projectId,User user){
		Grade grade=new Grade();
		grade.setSchoolId(user.getSchoolId());
		grade.setProjectId(projectId);
		List<Grade> grades=gradeDao.selectAllByGrade(grade);
		Notice themeNotice=noticeDao.selectObjectByThemeId(projectId);
		if(grades != null && !grades.isEmpty() && themeNotice != null){
			Map<String ,List<Grade>> datas=GradeUtils.groupByPositionIdIsEmploy(grades,
					themeNotice.getEnterCondition(),themeNotice.getInterviewEnterPropo(),themeNotice.getInterviewEnterLine());
			List<Grade> gradePass=datas.get("pass");
			List<Grade> gradeNoPass=datas.get("noPass");
			Log_.info("pass size()="+gradePass.size()+" "+ gradePass.toString());
			Log_.info("noPass ()"+gradeNoPass.size()+" "+gradeNoPass.toString());
			this.updateIsEmploy(gradePass, gradeNoPass,user);
		}
	}
	
	/**
	 * 更新是否录用
	 * @param gradePass
	 * @param gradeNoPass
	 */
	private void updateIsEmploy(List<Grade> gradePass,List<Grade> gradeNoPass,User user){
		for(Grade grade : gradePass){
			grade.setIsEmploy("1");
			grade.setModifyuser(user.getUserId());
		}
		for(Grade grade : gradeNoPass){
			grade.setIsEmploy("0");
			grade.setModifyuser(user.getUserId());
		}
		gradePass.addAll(gradeNoPass);
		gradeDao.batchUpdateGrade(gradePass);
	}
	
	
	/**
	 * 操作错误数据
	 * @param correctData 正确数据集合
	 * @param dataList 错误数据集合
	 * @param projectId 
	 * @param user
	 * @param originalFilename 
	 * @return
	 * @throws IOException
	 */
	private void operErrorData(List<Map<String, String>> correctData,
			List<Map<String, String>> dataList,String projectId,User user, String originalFilename) throws IOException{
		GradeExportLog gradeLog=new GradeExportLog();
		gradeLog.setType("1");
		gradeLog.setProjectId(projectId);
		gradeLog.setSchoolId(user.getSchoolId());
		String maxNum=gradeExportLogDao.maxOrderNumber(gradeLog);//查询最大序列号
		Integer index=1;//序号
		if(StringUtils.isNotBlank(maxNum) && !"null".equals(maxNum)){
			index=Integer.valueOf(maxNum);
			index++;
		}
		
		String attachementId=null;
		GradeExportLog gradeExportLog=new GradeExportLog();//导入日志记录
		gradeExportLog.setProjectId(projectId);
		gradeExportLog.setType("1");
		gradeExportLog.setFileName(originalFilename);
		gradeExportLog.setOrderNumber(new BigDecimal(index));
		gradeExportLog.setModifyuser(user.getId());
		gradeExportLog.setCreateuser(user.getId());
		gradeExportLog.setSchoolId(user.getSchoolId());
		
		String status="";//导入状态
		String result="";//导入结果
		if(dataList !=null && !dataList.isEmpty() ){//错误数据不空
			String relativePath=this.createErrorFile("学校面试",projectId,dataList);//生成错误文件
			attachementId=saveErrorFile(relativePath, projectId, user);//保存错误文件附件
			if(correctData !=null && !correctData.isEmpty()){
				//错误数据不空 且 正确数据不空
				result="更新成绩信息"+correctData.size()+" 条记录。有"+dataList.size()+"条错误记录。请下载错误文件，重新导入。";
				status="导入结束";
			}else{
				//错误数据不空 且 正确数据为空
				result="录入的数据全部有误，请下载错误文件，重新导入。";
				status="导入出错";
			}
			gradeExportLog.setRemark(attachementId);
		}else{//错误数据空
			if(correctData !=null && !correctData.isEmpty()){
				//错误数据为空 且 正确数据不空
				result="更新成绩信息"+correctData.size()+" 条记录。";
				status="导入结束";
			}else{
				//错误数据为空 且 正确数据也为空
				result="导入的模板有误，请下载模板后再导入。";
				status="导入出错";
			}
		}
		gradeExportLog.setStatus(status);
		gradeExportLog.setResult(result);
		gradeExportLogDao.saveGradeExportLog(gradeExportLog);
	}
	
	/**
	 * 保存错误文件附件
	 * @param relativePath
	 * @param user
	 */
	private String saveErrorFile(String relativePath,String projectId,User user){
		Attachement attachement=new Attachement();
		attachement.setEmployItemsId(projectId);
		attachement.setPath(relativePath);
		attachement.setRealName("学校面试导入错误文件.xls");
		attachement.setFileType(".xls");
		attachement.setCreateUser(user.getId());
		attachement.setModifyUser(user.getId());
		attachement.setModuleType("1");
		attachementDao.saveAttachementByField(attachement);
		return attachement.getId();
	}
	
	/**
	 * 是否录取
	 * @param dataList
	 * @return
	 */
	private boolean hasIsEmploy(List<Map<String, String>> dataList){
		if(dataList !=null && !dataList.isEmpty()){
			if(dataList.get(0).containsKey("是否录取")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 生成错误文件
	 * @param linkName 学校面试、统一笔试、统一试讲...
	 * @param projectId
	 * @throws IOException 
	 */
	private String createErrorFile(String linkName,String projectId,List<Map<String, String>> dataList) throws IOException{
		boolean flag=hasIsEmploy(dataList);
		Theme theme=themeDao.selectThemeById(projectId);
		PropertiesUtil properties = new PropertiesUtil("properties/systemConfig.properties");
		String filePath=properties.getValue("error.file.path");
		String date=new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		String themeName=theme.getTheme();
		String relativePath=File.separator+date+File.separator+themeName+File.separator+linkName;
		File file=new File(filePath+relativePath);
		if(!file.exists()){
			file.mkdirs();
		}
		String guid=UUID.randomUUID().toString().replaceAll("-", "");
		relativePath=relativePath+File.separator+guid+".xls";
		OutputStream out = new FileOutputStream(filePath+relativePath);
		
		String title=linkName+"成绩信息";
		String sheetName=linkName+"成绩导入错误文件";
		HSSFWorkbook wb = new HSSFWorkbook();//声明一个工作薄
		HSSFSheet sheet = wb.createSheet(sheetName);//生成一个表格
		String[] headers = new String []{ "准考证号", "姓名" , "报考岗位", "成绩", "错误信息"};
		if(flag){
			headers = new String []{ "准考证号", "姓名" , "报考岗位", "成绩", "是否录取" ,"错误信息"};
			String[] textlist=new String[]{"是","否"};
			sheet=ExcelUtils.setHSSFValidation(sheet, textlist, 2, dataList.size()+1, 4, 4);
		}
		HSSFRow row=ExcelUtils.setHSSFHeader(wb, sheet, title, headers);
        HSSFCellStyle contentStyle = ExcelUtils.setContentStyle(wb);
        for(int i=0;i<dataList.size();i++){
        	Map<String, String> mapData=dataList.get(i);
        	row = sheet.createRow(i+2);
        	
        	int cellIndex=0;
        	HSSFCell hc1 = row.createCell(cellIndex++);
        	hc1.setCellStyle(contentStyle);
    		HSSFRichTextString ticketNum = new HSSFRichTextString(mapData != null ? mapData.get("准考证号") : "");
    		hc1.setCellValue(ticketNum);
    		
    		HSSFCell hc2 = row.createCell(cellIndex++);
    		hc2.setCellStyle(contentStyle);
    		HSSFRichTextString studentName = new HSSFRichTextString(mapData != null ? mapData.get("姓名") : "");
    		hc2.setCellValue(studentName);
    		
    		HSSFCell hc3 = row.createCell(cellIndex++);
    		hc3.setCellStyle(contentStyle);
    		HSSFRichTextString postName = new HSSFRichTextString(mapData != null ? mapData.get("报考岗位") : "");
    		hc3.setCellValue(postName);
    		
    		HSSFCell hc4 = row.createCell(cellIndex++);
    		hc4.setCellStyle(contentStyle);
    		HSSFRichTextString grade = new HSSFRichTextString(mapData != null ? mapData.get("成绩") : "");
    		hc4.setCellValue(grade);
    		
    		if(flag){
    			HSSFCell hc5 = row.createCell(cellIndex++);
    			hc5.setCellStyle(contentStyle);
    			HSSFRichTextString offer = new HSSFRichTextString(mapData != null ? mapData.get("是否录取") : "");
    			hc5.setCellValue(offer);
    		}
    		
    		HSSFCell hc6 = row.createCell(cellIndex++);
    		hc6.setCellStyle(contentStyle);
    		HSSFRichTextString errorMsg = new HSSFRichTextString(mapData != null ? mapData.get("错误信息") : "");
    		hc6.setCellValue(errorMsg);
        }
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return relativePath;
	}
	
	/**
	 * 检查解析后的数据
	 * @param dataList
	 * @return
	 */
	private Map<String,List<Map<String, String>>> checkData(User user,String porjectId,List<Map<String, String>> dataList,JSONObject jsonMap){
		if(dataList == null || dataList.isEmpty()){
			jsonMap.put("msg", "上传的面试模板有空数据，请确认后重新上传！");
			throw new RuntimeException("上传的面试模板有空数据，请确认后重新上传！");
		}
		Map<String,List<Map<String, String>>> mapData=new HashMap<String,List<Map<String, String>>>();
		List<Map<String, String>> items=new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorItems=new ArrayList<Map<String, String>>();
		Map<String,Object> queryParameter=null;
		for(Map<String, String> map : dataList){
			this.checkData(map,jsonMap);
			queryParameter=new HashMap<String,Object>();
			String ticketNum=map.get("准考证号");
			String name=map.get("姓名");
			String postName=map.get("报考岗位");
			String grade=map.get("成绩");
			String offer=map.get("是否录取");
			if(StringUtils.isBlank(ticketNum) && StringUtils.isBlank(name) && StringUtils.isBlank(postName)){
				jsonMap.put("msg", "上传的面试模板有空数据，请确认后重新上传！");
				throw new RuntimeException("上传的面试模板有空数据，请确认后重新上传！");
			}
			if(StringUtils.isBlank(ticketNum)){
				map.put("错误信息", "准考证号为空！");
				errorItems.add(map);
				continue;
			}
			if(StringUtils.isBlank(name)){
				map.put("错误信息", "姓名为空！");
				errorItems.add(map);
				continue;
			}
			if(StringUtils.isBlank(postName)){
				map.put("错误信息", "报考岗位为空！");
				errorItems.add(map);
				continue;
			}
			if(StringUtils.isBlank(grade)){
				map.put("错误信息", "成绩为空！");
				errorItems.add(map);
				continue;
			}
			if(!grade.matches("^\\d{1,3}(\\.\\d{1,2})?$")){
				map.put("错误信息", "成绩输入有误，请输入1到3位正整数或小数点前面部分输入1到3位正数，小数点后面部分输入1到2位小数！");
				errorItems.add(map);
				continue;
			}
			if(map.containsKey("是否录取") && StringUtils.isBlank(offer)){
				map.put("错误信息", "是否录取为空！");
				errorItems.add(map);
				continue;
			}
			queryParameter.put("itemsId", porjectId);
			queryParameter.put("schoolId", user.getSchoolId());
			queryParameter.put("name", name);
			queryParameter.put("ticketnum", ticketNum);
			queryParameter.put("postName", postName);
			ScoreEnterInformation scoreEnterInformation=scoreEnterInformationDao.selectValidateInfo(queryParameter);
			if(scoreEnterInformation == null ){
				map.put("错误信息", "准考证号、姓名或报考岗位不正确，或该考生不在该学校的招聘主题下！");
				errorItems.add(map);
				continue;
			}
			items.add(map);
		}
		mapData.put("correctData", items);
		mapData.put("errorData", errorItems);
		return mapData;
	}
	
	/**
	 * 检查上传后的数据
	 * @param map
	 * @param jsonMap
	 */
	private void checkData(Map<String, String> map,JSONObject jsonMap){
		if(!map.containsKey("准考证号")){
			jsonMap.put("msg", "上传的面试模板不正确，请重新上传！");
			throw new RuntimeException("上传的面试模板不正确，请重新上传！");
		}
		if(!map.containsKey("姓名")){
			jsonMap.put("msg", "上传的面试模板不正确，请重新上传！");
			throw new RuntimeException("上传的面试模板不正确，请重新上传！");
		}
		if(!map.containsKey("报考岗位")){
			jsonMap.put("msg", "上传的面试模板不正确，请重新上传！");
			throw new RuntimeException("上传的面试模板不正确，请重新上传！");
		}
		if(!map.containsKey("成绩")){
			jsonMap.put("msg", "上传的面试模板不正确，请重新上传！");
			throw new RuntimeException("上传的面试模板不正确，请重新上传！");
		}
	}
	
	/**
	 * 检查上传的文件
	 * @param file
	 * @param jsonMap
	 */
	private void checkFile(CommonsMultipartFile file,JSONObject jsonMap){
		if(file == null){
			jsonMap.put("msg", "请选择导入的面试文件！");
			throw new RuntimeException("请选择导入的面试文件！");
		}
		List<String> prefixList=Arrays.asList(new String []{"xls","xlsx"});
		String originalFilename = file.getOriginalFilename();
		String fileName=originalFilename.substring(0, originalFilename.lastIndexOf("."));
		if(StringUtil.length(fileName) > 40){
			jsonMap.put("msg", "输入的文件名过长，最多输入20个汉字或40个字符，请重试！");
			throw new RuntimeException("输入的文件名过长，最多输入20个汉字或40个字符，请重试！");
		}
		String prefix=ExcelUtils.getPostfix(originalFilename);
		if(!prefixList.contains(prefix)){
			jsonMap.put("msg", "请选择Excel文件导入！");
			throw new RuntimeException("请选择Excel文件导入！");
		}
	}

	@Override
	public void saveGrade(PlanApply planApply, User user, JSONObject jsonMap) throws Exception {
		if(planApply == null){
			jsonMap.put("msg", "服务异常，请稍后再试！");
			throw new Exception("服务异常，请稍后再试！");
		}
		
		Theme theme=themeDao.selectThemeById(planApply.getProjectId());
    	if(theme !=null ){
    		if(theme.getStep() != 2){
    			jsonMap.put("msg", "当前招聘主题不在教育局发布面试名单环节中，操作失败，请确认！");
    			throw new Exception("当前招聘主题不在教育局发布面试名单环节中，操作失败，请确认！");
    		}
    	}
		
		List<Grade> grades=planApply.getGrades();
		if(grades ==null || grades.isEmpty()){
			jsonMap.put("msg", "保存的数据不能为空，请稍后再试！");
			throw new Exception("保存的数据不能为空，请稍后再试！");
		}
		List<String> items=new ArrayList<String>();
		for(Grade grade : grades){
			if(StringUtils.isBlank(grade.getId())){
				jsonMap.put("msg", "保存的数据ID不能为空，请稍后再试！");
				throw new Exception("保存的数据ID不能为空，请稍后再试！");
			}
			if(StringUtils.isBlank(grade.getGrade())){
				jsonMap.put("msg", "招聘单位面试成绩不能为空，请确认！");
				throw new Exception("招聘单位面试成绩不能为空，请确认！");
			}
			if(!grade.getGrade().matches("^\\d{1,3}(\\.\\d{1,2})?$")){
				jsonMap.put("msg", "成绩输入有误，请输入1到3位正整数或小数点前面部分输入1到3位正数，小数点后面部分输入1到2位小数！");
				throw new Exception("成绩输入有误，请输入1到3位正整数或小数点前面部分输入1到3位正数，小数点后面部分输入1到2位小数！");
			}
			items.add(grade.getId());
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", items);
		map.put("modifyuser", user.getId());
		gradeDao.updateGradeRemark(map);
		List<Grade> gradeServers=gradeDao.selectAllByMap(map);
		grades=this.setGradeData(gradeServers, grades);
		List<Grade> differGrades=this.getDifferGrade(gradeServers, grades);
		if(differGrades !=null && !differGrades.isEmpty() ){
			for(Grade grade : differGrades){
				grade.setIsReset("1");
				grade.setModifyuser(user.getId());
			}
			this.saveGradeAdjustLog(differGrades, user);
			gradeDao.batchUpdateGrade(differGrades);
		}
	}
	
	/**
	 * 批量保存调整成绩日志表
	 * @param differGrade
	 * @param user
	 */
	private void saveGradeAdjustLog(List<Grade> differGrade,User user){
		List<GradeAdjustLog> gradeAdjustLogs=new ArrayList<GradeAdjustLog>();
		GradeAdjustLog gradeAdjustLog=null;
		for(Grade grade : differGrade){
			Grade gradeServer=gradeDao.selectGradeById(grade.getId());
			gradeAdjustLog=new GradeAdjustLog();
			gradeAdjustLog.setProjectId(grade.getProjectId());
			gradeAdjustLog.setPositionId(grade.getPositionId());
			gradeAdjustLog.setStudentId(grade.getStudentId());
			gradeAdjustLog.setType("1");
			gradeAdjustLog.setCreateuser(user.getId());
			gradeAdjustLog.setModifyuser(user.getId());
			gradeAdjustLog.setInitGrade(this.getJsonGrade(gradeServer.getGrade(), gradeServer.getIsEmploy()));
			gradeAdjustLog.setGradeAfter(this.getJsonGrade(grade.getGrade(), grade.getIsEmploy()));
			gradeAdjustLog.setSchoolId(user.getSchoolId());
			gradeAdjustLogs.add(gradeAdjustLog);
		}
		gradeAdjustLogDao.batchSaveList(gradeAdjustLogs);
	}
	
	private String getJsonGrade(String grade,String isEmploy){
		JSONObject jsonMap=new JSONObject();
		jsonMap.put("grade", grade);
		jsonMap.put("isEmploy", isEmploy);
		return jsonMap.toString();
	}
	
	/**
	 * 获取需要更改的成绩数据
	 * @param gradePres
	 * @param gradeNews
	 * @return
	 */
	private List<Grade> getDifferGrade(List<Grade> gradePres,List<Grade> gradeNews){
		List<Grade> data=new ArrayList<Grade>();
		for(Grade gradePre : gradePres){
			for(Grade gradeNew : gradeNews){
				if(gradePre.getId().equals(gradeNew.getId())
						&& (!gradeNew.getGrade().equals(gradePre.getGrade())
						|| !gradeNew.getIsEmploy().equals(gradePre.getIsEmploy()))){
					data.add(gradeNew);
					break;
				}
			}
		}
		return data;
	}
	
	/**
	 * @param gradePres
	 * @param gradeNews
	 * @return
	 */
	private List<Grade> setGradeData(List<Grade> gradeServers,List<Grade> gradeNews){
		for(Grade gradeServer : gradeServers){
			for(Grade gradeNew : gradeNews){
				if(gradeServer.getId().equals(gradeNew.getId())){
					BeanUtils.copyProperties(gradeServer, gradeNew, new String []{"grade","isEmploy"});
					break;
				}
			}
		}
		return gradeNews;
	}

	@Override
	public void savePublishGrade(String projectId, User user, JSONObject jsonMap) throws Exception {
		if(StringUtils.isBlank(projectId)){
			jsonMap.put("msg", "招聘项目ID为空，操作失败，请确认！");
			throw new Exception("招聘项目ID为空，操作失败，请确认！");
		}
		if(user == null){
			jsonMap.put("msg", "获取用户数据为空，操作失败，请确认！");
			throw new Exception("获取用户数据为空，操作失败，请确认！");
		}
		
		Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("projectId", projectId);
		map.put("schoolId", user.getSchoolId());
		Theme theme=themeDao.queryTheme(map);
    	if(theme !=null ){
    		if(theme.getStep() != 2){
    			jsonMap.put("msg", "当前招聘主题不在教育局发布面试名单环节中，操作失败，请确认！");
    			throw new Exception("当前招聘主题不在教育局发布面试名单环节中，操作失败，请确认！");
    		}
    		
    		if(Integer.parseInt(theme.getSchoolCount()) > 0){
    			jsonMap.put("msg", "该学校面试成绩已发布，无需重复操作，请确认！");
    			throw new Exception("该学校面试成绩已发布，无需重复操作，请确认！");
    		}
    	}
		
    	Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		condition.put("projectId", projectId);
		List<ScoreEntersOutVo> scoreEntersOutVos=scoreEnterInformationDao.selectPageByMap(condition);
    	if(scoreEntersOutVos ==null || scoreEntersOutVos.isEmpty()){
    		jsonMap.put("msg", "暂无考生数据，无法发布面试成绩，操作失败，请确认！");
			throw new Exception("暂无考生数据，无法发布面试成绩，操作失败，请确认！");
    	}
		
    	//所有学校发布面试成绩后，将招聘主题步骤置为 成绩发布状态
		if(Integer.parseInt(theme.getPublishSchoolCount())+1 == Integer.parseInt(theme.getSchooTotleCount())){
			map.put("themeId", projectId);
			map.put("num", 3);
			themeDao.updateStep(map);
		}
    	
		scoreEnterWritienDao.batchSaveScoreEnterWritien(map);
		scoreEnterTrialDao.batchSaveScoreEnterTrial(map);
		
		Grade grade=new Grade();
		grade.setScorePublishStatus("1");
		grade.setModifyuser(user.getId());
		grade.setSchoolId(user.getSchoolId());
		grade.setProjectId(projectId);
		gradeDao.updateGradePublishStatus(grade);
	}

	@Override
	public void saveCancelPublishGrade(String projectId, User user, JSONObject jsonMap) throws Exception {
		if(StringUtils.isBlank(projectId)){
			jsonMap.put("msg", "招聘项目ID为空，操作失败，请确认！");
			throw new Exception("招聘项目ID为空，操作失败，请确认！");
		}
		if(user == null){
			jsonMap.put("msg", "获取用户数据为空，操作失败，请确认！");
			throw new Exception("获取用户数据为空，操作失败，请确认！");
		}
		
		Theme theme=themeDao.selectThemeById(projectId);
    	if(theme !=null ){
    		if(theme.getStep()>2){
    			jsonMap.put("msg", "当前招聘主题不可取消发布面试成绩，操作失败，请确认！");
    			throw new Exception("当前招聘主题不可取消发布面试成绩，操作失败，请确认！");
    		}
    	}
		
    	Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		condition.put("projectId", projectId);
		List<ScoreEntersOutVo> scoreEntersOutVos=scoreEnterInformationDao.selectPageByMap(condition);
    	if(scoreEntersOutVos ==null || scoreEntersOutVos.isEmpty()){
    		jsonMap.put("msg", "暂无考生数据，无法取消发布面试成绩，操作失败，请确认！");
			throw new Exception("暂无考生数据，无法取消发布面试成绩，操作失败，请确认！");
    	}
    	
		ScoreEnterWritien scoreEnterWritien=new ScoreEnterWritien();
		scoreEnterWritien.setItemsId(projectId);
		scoreEnterWritien.setSchoolId(user.getSchoolId());
		scoreEnterWritienDao.deleteScoreEnterWritien(scoreEnterWritien);
		
		ScoreEnterTrial scoreEnterTrial=new ScoreEnterTrial();
		scoreEnterTrial.setItemsId(projectId);
		scoreEnterTrial.setSchoolId(user.getSchoolId());
		scoreEnterTrialDao.deleteScoreEnterTrial(scoreEnterTrial);
		
		Grade grade=new Grade();
		grade.setScorePublishStatus("0");
		grade.setModifyuser(user.getId());
		grade.setSchoolId(user.getSchoolId());
		grade.setProjectId(projectId);
		gradeDao.updateGradePublishStatus(grade);
	}

    @Override
    public Grade queryScore(StudentApplyInfo sai, String type) {
        Calendar ca = Calendar.getInstance();
        Grade grade = new Grade();
        grade.setStudentId(sai.getStudentId());
        grade.setProjectId(sai.getEmployItemsId());
        grade.setSchoolId(sai.getApplyDepId());
        grade.setPositionId(sai.getApplyJobId());
        grade.setYear(ca.get(Calendar.YEAR) + "");//只查当前年份的成绩
        grade.setScorePublishStatus("1");//发布的成绩
        grade.setType(type);
        grade = gradeDao.selectObjectByGrade(grade);
        return grade;
    }

	@Override
	public List<Grade> queryHasNoGrade(Map<String, Object> map) {
		return gradeDao.queryHasNoGrade(map);
	}
}
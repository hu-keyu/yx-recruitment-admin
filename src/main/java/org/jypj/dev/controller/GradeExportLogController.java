package org.jypj.dev.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.User;
import org.jypj.dev.service.AttachementService;
import org.jypj.dev.service.GradeExportLogService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * GradeExportLog控制器
 * @author QiCai
 *
 */
@Controller
@RequestMapping("/dg/gradeExportLog")
public class GradeExportLogController {
	
    @Resource 
    private GradeExportLogService gradeExportLogService;
    @Resource 
    private AttachementService attachementService;
    
    @RequestMapping(value="toList")
    public String toList(String projectId,Model model){
    	model.addAttribute("projectId", projectId);
    	return "/schoolgrade/grade_import_log_list.vm";
    }
    
    @ResponseBody
	@RequestMapping(value="selectAllGradeLog",method=RequestMethod.POST)
	public String selectAllGradeLog(String projectId,Page page,HttpSession session) {
    	User user=(User)session.getAttribute("user");
		Map<String, Object> condition =page.getCondition();
		condition.put("type", "1");
		condition.put("projectId", projectId);
		condition.put("schoolId", user.getSchoolId());//当前登录用户学校ID
		page = gradeExportLogService.selectOnePageByMap(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
    @RequestMapping(value="downloadErrorFile")
    public void downloadErrorFile(String fileId,HttpServletResponse response,HttpServletRequest request){
    	Attachement attachement=attachementService.selectAttachementById(fileId);
    	if(attachement !=null ){
    		PropertiesUtil Properties=new PropertiesUtil("properties/systemConfig.properties");
    		String filePath=Properties.getValue("error.file.path");
    		String relativePath=attachement.getPath();
    		File file=new File(filePath+relativePath);
    		String fileName=attachement.getRealName();
    		ExcelUtils.setResponseHeader(request,response,fileName);
    		InputStream is=null;
    		OutputStream os =null;
    		try {
    			int length=0;
    			is = new BufferedInputStream(new FileInputStream(file));
    			byte[] bt = new byte[512*1024];
    			os=new BufferedOutputStream(response.getOutputStream());
    			while((length=is.read(bt)) != -1){
    				os.write(bt,0,length);
    			}
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally{
    			try {
    				is.close();
    				os.close();  
    				os.flush();
    			} catch (IOException e) {
    				e.printStackTrace();
    			} 
    		}
    	}
    }
    
    @RequestMapping(value="toTestTypeList")
    public String toTestTypeList(String testType,String projectId,Model model){
    	model.addAttribute("projectId", projectId);
    	model.addAttribute("testType", testType);
    	return "/scoreinformation/score_import_log_list.vm";
    }
    
    @ResponseBody
	@RequestMapping(value="selectScoreTypeLog",method=RequestMethod.POST)
	public String selectScoreTypeLog(String projectId,String testType,Page page,HttpSession session) {
		Map<String, Object> condition =page.getCondition();
		condition.put("projectId", projectId);
		condition.put("testType", testType);
		page = gradeExportLogService.selectScorePageByMap(page,condition);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getTotalRows());
        jsonMap.put("rows", page.getResult());
        jsonMap.put("currentPage", page.getCurrentPage());
		return JSONObject.toJSON(jsonMap).toString();
	}
    
}
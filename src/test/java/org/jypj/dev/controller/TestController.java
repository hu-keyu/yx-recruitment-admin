package org.jypj.dev.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.jypj.dev.code.Result;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.entity.Theme;
import org.jypj.dev.service.TestService;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.test.MathUtil;
import org.jypj.dev.util.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 考生报考自动审核控制器
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@Resource
	private TestService testService;
	@Resource
	private ThemeService themeService;
	private final String createUser="AutoTest";

	@RequestMapping(value = "test")
	public String toTest(Model model) {
		model.addAttribute("themes", testService.selectAllTheme());
		model.addAttribute("postsets", testService.selectAllPostset());
		model.addAttribute("dictionaries", testService.selectAllSchool());
		return "/test/test.vm";
	}

	@RequestMapping(value = "students/save")
	@ResponseBody
	public Result saveStudents(String[] applyDepIds, String[] applyJobIds, String applyStatus, String count,
			String employItemsId, String name,String[] freeStudents,String[] professionalCourses) {
		String[] randBool={"0","0","0","0","0","0","0","0","0","1"};
		
		Result result = new Result();
		int totalCount = 0;
		try {
			totalCount = Integer.parseInt(count);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
		
		Map<String,String[]> arg=new HashMap<>();
		arg.put("applyDepId",applyDepIds);
		arg.put("applyJobId", applyJobIds);
		List<Map<String,String>> list=MathUtil.runArray(arg);//分组
		
		/*String msg="您选择了"+applyJobIds.length+"个招聘岗位和"+applyDepIds.length+"个招聘单位,共有"+applyJobIds.length*applyDepIds.length+"种组合";*/
		
		List<Integer> groups=null;
		try {
			//
			groups=MathUtil.random2(list.size(), totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		List<StudentInfo> studentInfos = new ArrayList<>();
		List<StudentApplyInfo> studentApplyInfos = new ArrayList<>();
		
		for(int i =0,x=0;i<groups.size();i++){
			Map<String,String> map=list.get(i);
			String applyJobId=map.get("applyJobId");
			String applyDepId=map.get("applyDepId");
			
			for(int y = 0;y<groups.get(i);y++){
				x++;
				String liushui=liushui(x);
				
				StudentInfo studentInfo=new StudentInfo();
				studentInfo.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
				studentInfo.setEmployItemsId(employItemsId);
				studentInfo.setName(name+liushui);
				studentInfo.setSex(String.valueOf(new Random().nextInt(2)+1));
				studentInfo.setIdentityCard(new Date().getTime()+liushui);
				studentInfo.setPassword(MD5Utils.md5Encrypt("123456"));
				studentInfo.setTicketnum(new Date().getTime()+liushui);
				studentInfo.setApplyStatus(applyStatus);
				studentInfo.setCreateUser(createUser);
				String freeStudent=null;
				String professionalCourse=null;
				int randomInt=new Random().nextInt(10);
				
				if(freeStudents.length==0){
					freeStudent="0";
				}else if(freeStudents.length==1){
					freeStudent=freeStudents[0];
				}else{
					freeStudent=randBool[randomInt];
				}
				if(professionalCourses.length==0){
					professionalCourse="0";
				}else if(professionalCourses.length==1){
					professionalCourse=professionalCourses[0];
				}else{
					professionalCourse=randBool[randomInt];
				}
				
				studentInfo.setFreeStudent(freeStudent);
				studentInfo.setProfessionalCourse(professionalCourse);
				
				
				studentInfos.add(studentInfo);
	
				StudentApplyInfo studentApplyInfo=new StudentApplyInfo();
				studentApplyInfo.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
				studentApplyInfo.setEmployItemsId(employItemsId);
				studentApplyInfo.setStudentId(studentInfo.getId());
				studentApplyInfo.setApplyJobId(applyJobId);
				studentApplyInfo.setApplyDepId(applyDepId);
				studentApplyInfo.setCreateUser(createUser);
				studentApplyInfo.setApplyStatus("3");
				studentApplyInfos.add(studentApplyInfo);
			}
		}
		
/*

		for (int i = 0; i < totalCount;) {
			for(String applyDepId:applyDepIds){
				if(i>=totalCount){
					break;
				}
				for(String applyJobId:applyJobIds){
					if(i>=totalCount){
						break;
					}
					i++;
					
					String liushui=liushui(i);
					
					StudentInfo studentInfo=new StudentInfo();
					studentInfo.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
					studentInfo.setEmployItemsId(employItemsId);
					studentInfo.setName(name+liushui);
					studentInfo.setSex(String.valueOf(i/2));
					studentInfo.setIdentityCard(new Date().getTime()+liushui);
					studentInfo.setPassword(MD5Utils.md5Encrypt("123456"));
					studentInfo.setTicketnum(new Date().getTime()+liushui);
					studentInfo.setApplyStatus(applyStatus);
					studentInfo.setCreateUser(createUser);
					
					studentInfos.add(studentInfo);

					StudentApplyInfo studentApplyInfo=new StudentApplyInfo();
					studentApplyInfo.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
					studentApplyInfo.setEmployItemsId(employItemsId);
					studentApplyInfo.setStudentId(studentInfo.getId());
					studentApplyInfo.setApplyJobId(applyJobId);
					studentApplyInfo.setApplyDepId(applyDepId);
					studentApplyInfo.setCreateUser(createUser);
					studentApplyInfos.add(studentApplyInfo);
				}
			}
		}*/
		try {
			int i = testService.saveStudents(studentInfos, studentApplyInfos);
			if(i>0){
				result.setSuccess(true);
				result.setMsg("成功");
			}else{
				result.setSuccess(false);
				result.setMsg("失败");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("异常");
		}
		
		System.out.println(groups);
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("studentApplyInfos", studentApplyInfos);
		jsonObject.put("studentInfos", studentInfos);
		result.setObj(jsonObject);
		return result;
	}
	
	@RequestMapping(value = "students/delete")
	@ResponseBody
	public Result deleteStudents() {
		Result result = new Result();
		int i=0;
		try {
			i = testService.deleteTestStudents(createUser);
			if(i>0){
				result.setSuccess(true);
				result.setMsg("删除成功");
			}else{
				result.setSuccess(false);
				result.setMsg("没有测试数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("异常");
		}
		return result;
	}

	private String liushui(int liuShuiHao) {
		DecimalFormat df = new DecimalFormat("00000");
		return df.format(liuShuiHao);
	}
	
	@RequestMapping(value = "students/updateemploy")
	@ResponseBody
	public Result updateEmploy(String employItemsId,String isEmploy) {
		Result result = new Result();
		int i=0;
		Theme theme=themeService.selectThemeById(employItemsId);
		theme.setStep(3);
		themeService.updateTheme(theme);
		try {
			i = testService.updateEmploy(employItemsId,isEmploy);
			if(i>0){
				result.setSuccess(true);
				result.setMsg("更新成功");
			}else{
				result.setSuccess(false);
				result.setMsg("没有测试数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("异常");
		}
		return result;
	}

	
}

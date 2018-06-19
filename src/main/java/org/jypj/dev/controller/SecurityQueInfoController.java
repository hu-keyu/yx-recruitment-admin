package org.jypj.dev.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.SecurityQueInfo;
import org.jypj.dev.entity.StudentInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jypj.dev.service.DictionaryService;
import org.jypj.dev.service.SecurityQueInfoService;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * SecurityQueInfo控制器
 * 密保问题信息
 * @author
 *
 */
@Controller
@RequestMapping("/dg/securityQueInfo")
public class SecurityQueInfoController {
	
    @Resource 
    private SecurityQueInfoService securityQueInfoService;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private StudentInfoService studentInfoService;
    
    
    @RequestMapping("setSecurityQue")
    @ResponseBody
    public String setSecurityQue(String json, HttpServletRequest request) {
        JSONObject jb = new JSONObject();
        try {
            String jsonStr = StringUtil.stringToJson(json);
            String temp = jsonStr.replaceAll("\\\\", "");
            JSONArray object = JSON.parseArray(temp);
            for (int i = 0; i < object.size(); i++) {
                jb = new JSONObject();
                SecurityQueInfo sei = new SecurityQueInfo();
                jb = (JSONObject) object.get(i);
                sei.setId(UUID.randomUUID().toString().replace("-", ""));
                sei.setStudentId(jb.getString("studentId"));
                StudentInfo si =
                        studentInfoService.selectStudentInfoById(jb.getString("studentId"));
                sei.setQuestionCode(jb.getString("questionCode"));
                sei.setQuestions(jb.getString("questionContent"));
                sei.setAnswer(jb.getString("answerContent"));
                sei.setCreateUser(si == null ? "" : si.getName());
                sei.setCtime(new Date());
                sei.setModifyUser(si == null ? "" : si.getName());
                sei.setMtime(new Date());
                securityQueInfoService.saveSecurityQueInfo(sei);
            }
            jb.put("flag", true);
            jb.put("msg", "设置成功！");
            return jb.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jb.put("flag", false);
            jb.put("msg", "设置出现异常！");
            return jb.toJSONString();
        }
    }
    
    
    @RequestMapping(value = "getSecurityQue")
    public String getSecurityQue(Model model) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("codeName", "mbwt");
        //获取字典数据
        List<Dictionary> dictionaryList = dictionaryService.selectAllByMap(map);
        model.addAttribute("securityQues", dictionaryList);
        return "/student/getSecurityQue.vm";
    }
    

}
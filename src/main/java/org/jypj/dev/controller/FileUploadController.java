package org.jypj.dev.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.service.AttachementService;
import org.jypj.dev.service.StudentInfoService;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.StringUtil;
import org.jypj.dev.vo.AttachementVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/dg/file")
public class FileUploadController {

	@Resource
	private AttachementService attachementService;

	@Resource
	private StudentInfoService studentInfoService;

	@RequestMapping(value = "/upload",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String uploadFile(@RequestParam("fileAtt") MultipartFile file, String attId, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		JSONObject jo = new JSONObject();
		String realPath = request.getSession().getServletContext().getRealPath("/") + "upload";
		String originalName = file.getOriginalFilename();
		
		if(originalName.length()>20){
			jo.put("error", "-2");
			jo.put("message", "文件名最大长度为20,请更改文件名后上传！");
			return jo.toJSONString();
		}
		//System.out.println(file.getSize());
	   if (file.getSize() > 1024 * 1024 * 10) {
           jo.put("error", "-2");
           jo.put("message", "上传文件大小不能超过10M!");
           return jo.toJSONString();
       }
		String extension = originalName.substring(originalName.lastIndexOf("."));
		String attachId = UUID.randomUUID().toString().replaceAll("-", "");
		String newName = "";
		newName = attachId + extension;
		File targetFile = new File(realPath, newName);
		realPath += File.separator + newName;
		if (file != null && !file.isEmpty()) {
			try {
				file.transferTo(targetFile);// 将文件先写入本地，再上传到文件服务器
				FtpUploadUtil.upload(realPath, "/" + attachId.substring(0, 2) + "/", newName);
				String urlpath = FtpUploadUtil.getFileServer() + attachId.substring(0, 2) + "/" + newName;
				List<Attachement> attList = new ArrayList<Attachement>();
				Attachement attachement = new Attachement();
				attachement.setId(attachId);
				attachement.setPath(urlpath);
				attachement.setRealName(originalName);
				attachement.setFileType(extension);
				attList.add(attachement);
				AttachementVo avo = new AttachementVo();
				avo.setAttId(attachement.getId());
				avo.setData(attList);
				jo.put("error", 0);
				jo.put("avo", avo);
				jo.put("message", "文件上传成功！");
			} catch (Exception e) {
				jo.put("error", "-1");
				jo.put("message", "文件上传失败！");
				e.printStackTrace();
			}
		} else {
            jo.put("error", "-2");
            jo.put("message", "上传的文件为空！");
		}
		return jo.toJSONString();
	}
}

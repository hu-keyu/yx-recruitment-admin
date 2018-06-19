<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page language="java" import="org.jypj.dev.util.FtpUploadUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="10">
</head>
<div style="width: 480px; margin: 20px auto; text-align: center;">
	<p style="width: 431px; height: 344px; margin: 20px auto;">
		<a href="<%=FtpUploadUtil.getStudentOutUrl() %>"><img src="<%=request.getContextPath() %>/static/images/chaoshi.jpg" width="431" height="344"></a>
	</p>
	<h3
		style="line-height: 24px; font-size: 18px; font-family: '微软雅黑'; color: #333; font-weight: normal;">
		登录超时或账号在另一地方登陆,点击图片重新登录!&nbsp;&nbsp;
	</h3>
</div>

</html>
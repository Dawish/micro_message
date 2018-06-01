<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<title>内容列表页面</title>
<!-- css的访问路径 -->
<link href="<%=basePath%>resources/css/all.css" rel="stylesheet"
	type="text/css" />
<!-- 引入js -->
<script src="<%=basePath%>resources/js/common/jquery-1.8.0.min.js"></script>
<script src="<%=basePath%>resources/js/back/addPage.js"></script>
</head>
<body style="background: #e1e9eb;">
	<form action="" id="mainForm" method="post">
		<div class="right">
			<div class="current">
				当前位置：<a href="javascript:void(0)" style="color: #6E6E6E;">内容管理</a>
				&gt; 添加内容
			</div>
			<div class="rightCont">
				<p class="g_title fix">
					添加指令 <a class="btn04" href="javascript:toList('<%=basePath%>')">列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<table class="tab1">
					<tbody>
						<tr>
							<td width="90" align="right">指令：</td>
							<td><input name="command" type="text" class="allInput"
								value="${command}" /></td>
								
							<td width="90" align="right">描述：</td>
							<td><input name="description" type="text" class="allInput"
								value="${description}" /></td>
							
															
							<td width="90" align="right">内容：</td>
							<td><input name="content" type="text" class="allInput"
								value="${content}" /></td>
								
							<td width="85" align="right"><input type="submit"
								class="tabSub" value="添  加" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<h1>更改源码（程序专用）</h1>

<div style="margin:20px 0px;">
<form action="user_uploadSource" method="post" style="margin: 0px;" enctype="multipart/form-data" class="g_from">
<table width="800" cellpadding="4" cellspacing="0" border="0">

<tr  >
	<td>路径:</td>
	<td><input type="file" id="source" name="source" value="浏览" style="width:280px;" /> </td>
</tr>



<br/>

<tr>
	
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td><input type="submit" value="提交" /></td>
</tr>
</table> 
</form>
</div>
<h1>${requestScope.uploadSource }</h1>

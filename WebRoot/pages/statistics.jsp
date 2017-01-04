<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<h1>记录</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>用户ID</th>
			<th>类型</th>			
			<th>广告位</th>
			<th>offer</th>
			<th>应用名</th>	
			<th>包名</th>
			<th>时间</th>	
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.userId" /></td>
				<td><s:property value="#val.statisticsType" /></td>
				<td><s:property value="#val.adPosition" /></td>
				<td><s:property value="#val.offer" /></td>
				<td><s:property value="#val.appName" /></td>
				<td><s:property value="#val.packageName" /></td>
				<td align="center"><s:date name="#val.uploadTime" format="yyyy-MM-dd HH:mm:ss" /></td>
				
				<td class="thUpdate"><input type="button" value="操作"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<div id="my_div" title="${maxNum}">

<a id="a_1" href="#" > 上一页    </a>
<a id="a_2" href="#"> 下一页</a>

<a  herf="#">总记录数：${maxNum}</a>
</div>

<div id="div_update" style="display:none;position:absolute;width:100px;">
<table  class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>操作</th>
		</tr>
	</thead>		
	<tr><td><input type="button" value="删除" id="delete"/></td></tr>
</table>
</div>

<script type="text/javascript">
$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

</script>
<script type="text/javascript" src="<%=basePath%>scripts/statistics.js"></script>
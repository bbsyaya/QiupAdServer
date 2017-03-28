<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript"
	src="<%=basePath%>scripts/laydate.dev.js"></script>
<h1>记录</h1>

<div style="width:1000px;float:right;font-size: 14px;text-align:right;margin-top: -40px;">
<select id="filed_sel_1">
  <option value ="0">类型选择</option>
</select>
<select id="filed_sel_2">
  <option value ="0">广告位选择</option>
</select>
<select id="filed_sel_3">
  <option value ="0">offer选择</option>
</select>
<select id="filed_sel_4">
  <option value ="0">应用选择</option>
</select>
<select id="filed_sel_5">
  <option value ="0">渠道选择</option>
</select>
<input type="button" value="今日" id="today"/>
<input type="button" value="一周内" id="oneWeek"/>
<input type="button" value="一月内" id="oneMonth"/>
<input type="text" id="from_date" name="from_date"  style="width:80px;" /> -
<input type="text" id="to_date" name="to_date" style="width:80px;" />
<input type="button" value="查询" id="find"/>
<input type="button" id="out" value="导出" />
</div>

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
			<th>渠道</th>
			<th>时间</th>	
			<th>操作</th>
		</tr>
	</thead>
	<tbody id="tbody">
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.userId" /></td>
				<td><s:property value="#val.statisticsType" /></td>
				<td><s:property value="#val.adPosition" /></td>
				<td><s:property value="#val.offerId" /></td>
				<td><s:property value="#val.appName" /></td>
				<td><s:property value="#val.packageName" /></td>
				<td><s:property value="#val.channel" /></td>
				<td align="center"><s:date name="#val.uploadTime" format="yyyy-MM-dd HH:mm:ss" /></td>
				
				<td class="thUpdate"><input type="button" value="操作"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<div id="my_div" title="${maxNum}">

<a id="a_1" href="#" > 上一页    </a>
<a id="a_2" href="#"> 下一页</a>

<a  herf="#" id="maxNum">总记录数：${maxNum}</a>
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

//创建日期选择
laydate({
			istime: true,
            elem: '#from_date',
            format: 'YYYY-MM-DD'
        });
laydate({
			istime: true,
            elem: '#to_date',
            format: 'YYYY-MM-DD'
        });   
 $("#to_date").click(function(){
 	laydate.resetPosition(-100);
 });
 
</script>
<script type="text/javascript" src="<%=basePath%>scripts/table-to-excel.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/statistics.js"></script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript"
	src="<%=basePath%>scripts/laydate.dev.js"></script>
<h1>OFFER统计</h1>
<div style="width:1000px;float:right;font-size: 14px;text-align:right;margin-top: -40px;">

<select id="offer_sel">
  <option value ="0">OFFER选择</option>
  <s:iterator value="offers" var="offer">
  	<option value ="<s:property value="#offer.id" />"><s:property value="#offer.appName" /></option>
  </s:iterator>
</select>

<input type="text" id="from_date" name="from_date"  style="width:80px;" /> -
<input type="text" id="to_date" name="to_date" style="width:80px;" />
<input type="button" value="查询" id="find"/>
</div>
<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>	
			<th>offer</th>					
			<th>请求</th>
			<th>展示</th>
			<th>点击</th>
			<th>下载</th>
			<th>下载成功</th>
			<th>安装</th>
			<th>激活</th>
			<th>点击率</th>	
			<th>下载成功率</th>
			<th>安装率</th>
			<th>激活率</th>
			<th>收入</th>
			<th>下载前界面</th>
			<th>下载前取消</th>
			<th>下载前下载</th>
			<th>下载界面</th>
			<th>下载取消</th>
			<th>后台下载</th>
			<th>安装界面</th>
			<th>安装界面用户</th>
			<th>安装稍后</th>
			<th>去安装</th>
			<th>安装时间</th>
			<th>打开界面</th>
			<th>打开界面用户</th>
			<th>打开取消</th>
			<th>打开</th>
			<th>日期</th>
		</tr>
	</thead>
	<tbody id="tbody">
		<s:iterator value="list" var="val">
			<tr>			
				<td><s:property value="#val.offerName" /></td>					
				<td><s:property value="#val.requestNum" /></td>
				<td><s:property value="#val.showNum" /></td>
				<td><s:property value="#val.clickNum" /></td>
				<td><s:property value="#val.downloadNum" /></td>
				<td><s:property value="#val.downloadSuccessNum" /></td>
				<td><s:property value="#val.installNum" /></td>
				<td><s:property value="#val.activateNum" /></td>
				<td><fmt:formatNumber value="${val.clickRate }" pattern="#0.0%" /></td>
				<td><fmt:formatNumber value="${val.downloadRate }" pattern="#0.0%" /></td>
				<td><fmt:formatNumber value="${val.installRate }" pattern="#0.0%" /></td>
				<td><fmt:formatNumber value="${val.activateRate }" pattern="#0.0%" /></td>
				<td><fmt:formatNumber value="${val.income }" pattern="#0.0" /></td>
				<td><s:property value="#val.toDownloadUiNum" /></td>
				<td><s:property value="#val.toDownloadCancelNum" /></td>
				<td><s:property value="#val.toDownloadGoNum" /></td>
				<td><s:property value="#val.downloadUiNum" /></td>
				<td><s:property value="#val.downloadCancelNum" /></td>
				<td><s:property value="#val.downloadBackgroundNum" /></td>
				<td><s:property value="#val.installUiNum" /></td>
				<td><s:property value="#val.installUiUserNum" /></td>
				<td><s:property value="#val.installLaterNum" /></td>
				<td><s:property value="#val.installGoNum" /></td>
				<td class="installTime" style="text-align:left;"><s:property value="#val.installTime" /></td>
				<td><s:property value="#val.openUiNum" /></td>
				<td><s:property value="#val.openUiUserNum" /></td>
				<td><s:property value="#val.openCancelNum" /></td>
				<td><s:property value="#val.openGoNum" /></td>
				<td><s:date name="#val.stime" format="yyyy-MM-dd" /></td>			
			</tr>
		</s:iterator>
	</tbody>
</table>




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

<script type="text/javascript" src="<%=basePath%>scripts/offerStatistics.js"></script>

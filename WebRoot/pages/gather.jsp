<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"
+request.getServerPort()+path+"/";
%>
<script type="text/javascript"
	src="<%=basePath%>scripts/laydate.dev.js"></script>
<h1 id="h3_text">app上传信息</h1>
<!-- <button id="see_runinfo">查看app运行信息 </button>	 -->
<div>
	<form action=gather_find method="post" style="margin:0xp;"class="g_from">
	<table>
		<td>
			<h>时间:</h>
			<input type="text" id="regDate_from" name=regDate_from
				value="" style="width:120px;"/>至<input type="text" id="regDate_to" name=regDate_to
				value="" style="width:120px;"/>
				&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;
			<input type="submit" value="查询" />
			<input type="button" id="out" value="导出" />
		</td>
	</table>
  </form>
</div>

<div id="appinfo">
	<table id="tableList" class="tablesorter" style="margin-top: 10px;" cellspacing="1">
		<thead>
			<tr>	
				<th>ID</th>		
				<th>设备id</th>				
				<th>包名</th>
				<th>应用名</th>
				<th>类名</th>		
				<th>是否内置</th>	
				<th>日期</th>								
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="appInfoList" var="val">
				<tr>	
					<td><s:property value="#val.id" /></td>
					<td><s:property value="#val.deviceId" /></td>
					<td><s:property value="#val.packageName" /></td>
					<td><s:property value="#val.appName" /></td>	
					<td><s:property value="#val.clazName" /></td>	
					<td><s:if test="#val.inlay == true">是</s:if><s:else>否</s:else>	</td>		
					<td align="center"><s:date name="#val.gdate" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="thUpdate"><input class="caozuo" type="button" value="操作" title="<s:property value="#val.id"/>"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<div id="my_div" title="${maxNum}">
	<a id="a_0" href="#" >首页</a>
	<a id="a_1" href="#" >上一页</a>
	<a id="a_2" href="#">下一页</a>
	<a id="a_3" href="#" >尾页</a>
	<a  href="#">总记录：${maxNum}</a>
	</div>
</div>



<div id="runinfo" style="display:none;">
	<table id="tableList2" class="tablesorter" style="margin-top: 10px;" cellspacing="1">
		<thead>
			<tr>	
				<th>ID</th>		
				<th>设备id</th>	
				<th>包名</th>									
				<th>应用名</th>
				<th>类名</th>			
				<th>启动时间</th>
				<th>使用时长</th>				
				<th>是否内置</th>
				<th>是否WIFI</th>
				<th>日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="runInfoList" var="rval">
				<tr>	
					<td ><s:property value="#rval.id" /></td>
					<td><s:property value="#rval.deviceId" /></td>
					<td><s:property value="#rval.packageName" /></td>
					<td><s:property value="#rval.appName" /></td>
					<td><s:property value="#rval.clazName" /></td>	
					<td align="center"><s:date name="#rval.startTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:property value="#rval.useTimes" /></td>	
					<td><s:if test="#rval.inlay == true">是</s:if><s:else>否</s:else></td>		
					<td><s:if test="#rval.wifi == true">是</s:if><s:else>否</s:else>	</td>			
					<td align="center"><s:date name="#rval.gdate" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="runUpdate"><input class="runUp" type="button" value="操作" title="<s:property value="#rval.id"/>"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<div id="my_div2" class="r_div" title="${rmaxNum}">
	<a id="r_0" href="#" >首页</a>
	<a id="r_1" href="#" >上一页</a>
	<a id="r_2" href="#">下一页</a>
	<a id="r_3" href="#" >尾页</a>
	<a  href="#">总记录：${rmaxNum}</a>
	</div>

</div>
	
<div id="div_update" style="display:none;position:absolute;width:100px;">
	<table style="background:#331;"class="tablesorter" cellspacing="1">
		<thead>
			<tr>			
				<th>操作</th>
			</tr>
		</thead>
		<tr><td><input type="button" value="删除" id="deleteinfo"/></td></tr>
	</table>
</div>


<div id="r_update" style="display:none;position:absolute;width:100px;">
	<table style="background:#331;"class="tablesorter" cellspacing="1">
		<thead>
			<tr>			
				<th>操作</th>
			</tr>
		</thead>
		<tr><td><input type="button" value="删除" id="deleteruninfo"/></td></tr>
	</table>
</div>
<script type="text/javascript">
//创建日期选择
laydate({
			istime: true,
            elem: '#regDate_from',
            format: 'YYYY-MM-DD hh:mm:ss'
        });
laydate({
			istime: true,
            elem: '#regDate_to',
            format: 'YYYY-MM-DD hh:mm:ss'
        }); 
</script>
<script type="text/javascript"src="<%=basePath%>scripts/gather.js"></script>
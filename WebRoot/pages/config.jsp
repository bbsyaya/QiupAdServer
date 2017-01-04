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
<h1>配置管理</h1>

<table  cellspacing="1">
	<tr>			
		<td>新增配置：</td>
		<td> <input type="button" id="addConfig" value="新增" />${requestScope.addConfig }</td>		
	</tr>	
	<tr>			
		<td>查看配置：</td>
		<td> <input type="button" id="findConfig" value="查看" />${requestScope.updateConfig }</td>		
	</tr>	
</table>

<div id="d_addConfig" style="display:none; ">
<h1 align="center">新增配置</h1>
	<form action="config_addConfig" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>配置名称:</td>
				<td><input type="text" id="name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否开启:</td>
				<td width="80%"><input type="radio" id="open_state1"
					name="open_state" value="1" checked="checked" /> 是 <input
					type="radio" id="open_state2" name="open_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>白名单:</td>
				<td><textarea type="text" id="whiteList" name="whiteList" value="" style="width:380px;height:80px;"></textarea></td>
			</tr>
			
			<tr >
				<td>每天广告展示次数:</td>
				<td><input type="text" id="showNum" name="showNum"
					value="" style="width:80px;" />次
				</td>
			</tr>
			
			<tr >
				<td>广告时间间隔:</td>
				<td><input type="text" id="showTimeInterval" name="showTimeInterval"
					value="" style="width:80px;" />分钟
				</td>
			</tr>
			
			<tr >
				<td>广告可重复次数:</td>
				<td><input type="text" id="repeatNum" name="repeatNum"
					value="" style="width:80px;" />次
				</td>
			</tr>	
			
			<tr >
				<td>时间段：</td>
				<td>
					<input type="button" id="addTimeSlot1" name="addTimeSlot" value="添加日期时间段" />
					<input type="button" id="addTimeSlot2" name="addTimeSlot" value="添加星期时间段" />
				</td>
			</tr>
			<tr id="tr_sel_date" style="display:none; ">
				<td>选择日期:</td>
				<td>
				<select id='sel_date'style='width:98px'></select> 
				时<select id='sel_hours'  style='width:48px'></select>
				分<select id='sel_minute'  style='width:48px'></select>
				----时<select id='sel_hours_end'  style='width:48px'></select>
				分<select id='sel_minute_end'  style='width:48px'></select>
				<input type="button" id="tr_sel_date_add"  value="添加" />
				</td>
			</tr>
			<tr id="tr_sel_day" style="display:none; ">
				<td>选择日期:</td>
				<td>
				<select id='sel_day'style='width:88px'></select> 
				时<select id='sel_hours2'  style='width:48px'></select>
				分<select id='sel_minute2'  style='width:48px'></select>
				----时<select id='sel_hours_end2'  style='width:48px'></select>
				分<select id='sel_minute_end2'  style='width:48px'></select>
				<input type="button" id="tr_sel_day_add"  value="添加" />
				</td>
			</tr>
			<tr >
				<td>当前时间段：</td>
				<td id="td_timeSlot">
					<input type="text" id="timeSlot"  name="timeSlot" value="" style="width:80px;display:none;" />
				</td>
			</tr>
			
			<tr >
				<td>浏览器白名单:</td>
				<td><textarea type="text" id="browerWhiteList" name="browerWhiteList" value="" style="width:380px;height:80px;"></textarea></td>
			</tr>			
			
			<tr>
				<td>&nbsp;</td>
				<td align=center><input type="submit" value="添加" />
				 </td>
			</tr>
		</table>
	</form>
</div>


<div id="d_updateConfig" style="display:none; ">
<h1 align="center">更改配置</h1>
	<form action="config_updateConfig" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>ID:</td>
				<td><input type="text" id="update_id" name="id"
					value="" style="width:80px;" />
				</td>
			</tr>
			
			<tr >
				<td>配置名称:</td>
				<td><input type="text" id="update_name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否开启:</td>
				<td width="80%"><input type="radio" id="update_open_state1"
					name="open_state" value="1" checked="checked" /> 是 <input
					type="radio" id="update_open_state2" name="open_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>白名单:</td>
				<td><textarea type="text" id="update_whiteList" name="whiteList" value="" style="width:380px;height:80px;"></textarea></td>
			</tr>
			
			<tr >
				<td>每天广告展示次数:</td>
				<td><input type="text" id="update_showNum" name="showNum"
					value="" style="width:80px;" />次
				</td>
			</tr>
			
			<tr >
				<td>广告时间间隔:</td>
				<td><input type="text" id="update_showTimeInterval" name="showTimeInterval"
					value="" style="width:80px;" />分钟
				</td>
			</tr>
			
			<tr >
				<td>广告可重复次数:</td>
				<td><input type="text" id="update_repeatNum" name="repeatNum"
					value="" style="width:80px;" />次
				</td>
			</tr>		
												
			<tr >
				<td>时间段：</td>
				<td>
					<input type="button" id="update_addTimeSlot1" name="addTimeSlot" value="添加日期时间段" />
					<input type="button" id="update_addTimeSlot2" name="addTimeSlot" value="添加星期时间段" />
				</td>
			</tr>
			<tr id="update_tr_sel_date" style="display:none; ">
				<td>选择日期:</td>
				<td>
				<select id='update_sel_date'style='width:98px'></select> 
				时<select id='update_sel_hours'  style='width:48px'></select>
				分<select id='update_sel_minute'  style='width:48px'></select>
				----时<select id='update_sel_hours_end'  style='width:48px'></select>
				分<select id='update_sel_minute_end'  style='width:48px'></select>
				<input type="button" id="update_tr_sel_date_add"  value="添加" />
				</td>
			</tr>
			<tr id="update_tr_sel_day" style="display:none; ">
				<td>选择日期:</td>
				<td>
				<select id='update_sel_day'style='width:88px'></select> 
				时<select id='update_sel_hours2'  style='width:48px'></select>
				分<select id='update_sel_minute2'  style='width:48px'></select>
				----时<select id='update_sel_hours_end2'  style='width:48px'></select>
				分<select id='update_sel_minute_end2'  style='width:48px'></select>
				<input type="button" id="update_tr_sel_day_add"  value="添加" />
				</td>
			</tr>
			<tr >
				<td>当前时间段：</td>
				<td id="update_td_timeSlot">
					<input type="text" id="update_timeSlot"  name="timeSlot" value="" style="width:80px;display:none;" />
				</td>
			</tr>
			
			<tr >
				<td>浏览器白名单:</td>
				<td><textarea type="text" id="update_browerWhiteList" name="browerWhiteList" value="" style="width:380px;height:80px;"></textarea></td>
			</tr>			
			
			<tr>
				<td>&nbsp;</td>
				<td align=center><input type="submit" value="更改" />
				 </td>
			</tr>
		</table>
	</form>
</div>

<div id="div_update" style="display:none;position:absolute;width:100px;">
<table  class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>操作</th>
		</tr>
	</thead>		
	<tr><td><input type="button" value="更改" id="find"/></td></tr>
	<tr><td><input type="button" value="删除" id="delete"/></td></tr>
</table>
</div>

<div style="display:none; " id="div_table">
<table id="tableList" class="tablesorter" cellspacing="1"  >
	<thead>
		<tr>			
			<th>ID</th>
			<th>配置名称</th>
			<th>是否打开</th>	
			<th>每天广告展示次数</th>	
			<th>每天广告重复次数</th>	
			<th>广告时间间隔</th>									
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.name" /></td>
				
				<td align="center">				
				<s:if test="#val.open == true">是</s:if>
				<s:else>否</s:else>			
				</td>
				<td><s:property value="#val.showNum" /></td>
				<td><s:property value="#val.repeatNum" /></td>
				<td><s:property value="#val.showTimeInterval" /></td>
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

</div>

<script type="text/javascript">

$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

</script>

<script type="text/javascript" src="<%=basePath%>scripts/config.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/config.css" />

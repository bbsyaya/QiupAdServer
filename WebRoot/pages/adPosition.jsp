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
<h1>广告位管理</h1>

<table  cellspacing="1">
	<tr>			
		<td>新增广告位：</td>
		<td> <input type="button" id="addAdPosition" value="新增" />${requestScope.addAdPosition }</td>		
	</tr>	
	<tr>			
		<td>查看广告位：</td>
		<td> <input type="button" id="findAdPosition" value="查看" />${requestScope.updateAdPosition }</td>		
	</tr>	
</table>

<div id="d_addAdPosition" style="display:none; ">
<h1 align="center">新增媒体</h1>
	<form action="adPosition_addAdPosition" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			<tr >
				<td>类型:</td>
				<td><input type="text" id="type" name="type"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>名称:</td>
				<td><input type="text" id="name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否开启:</td>
				<td ><input type="radio" 
					name="open_state" value="1" checked="checked" /> 是 <input
					type="radio"  name="open_state" value="0" /> 否</td>
			</tr>
							
			<tr>
				<td>&nbsp;</td>
				<td align=center><input type="submit" value="添加" />
				 </td>
			</tr>
		</table>
	</form>
</div>


<div id="d_updateAdPosition" style="display:none; ">
<h1 align="center">更改广告位</h1>
	<form action="adPosition_updateAdPosition" method="post" enctype="multipart/form-data" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr style="display:none; ">
				<td>ID:</td>
				<td><input type="text" id="update_id" name="id"
					value="" style="width:80px;" />
				</td>
			</tr>
			
			<tr >
				<td>类型:</td>
				<td><input type="text" id="update_type" name="type"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>名称:</td>
				<td><input type="text" id="update_name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否开启:</td>
				<td ><input type="radio" id="open_state1"
					name="open_state" value="1" checked="checked" /> 是 <input
					type="radio" id="open_state2" name="open_state" value="0" /> 否</td>
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
				<td>白名单:</td>
				<td><textarea type="text" id="update_whiteList" name="whiteList" value="" style="width:380px;height:80px;"></textarea></td>
			</tr>
			
			<tr >
				<td>二次打开时间:</td>
				<td><input type="text" id="update_browerSpotTwoTime" name="browerSpotTwoTime"
					value="" style="width:80px;" />分钟
				</td>
			</tr>
			
			<tr >
				<td>流量限制:</td>
				<td><input type="text" id="update_browerSpotFlow" name="browerSpotFlow"
					value="" style="width:80px;" />M
				</td>
			</tr>
			
			<tr >
				<td>延迟时间:</td>
				<td><input type="text" id="update_bannerDelyTime" name="bannerDelyTime"
					value="" style="width:80px;" />分钟
				</td>
			</tr>
			
			<tr >
				<td>暗刷URL:</td>
				<td><textarea type="text" id="update_behindBrushUrls" name="behindBrushUrls" value="" style="width:380px;height:80px;"></textarea></td>
			</tr>
			
			<tr >
				<td>图标名称:</td>
				<td><input type="text" id="update_shortcutName" name="shortcutName"
					value="" style="width:80px;" />
				</td>
			</tr>
			<tr >
				<td>图标链接:</td>
				<td><input type="text" id="update_shortcutUrl" name="shortcutUrl"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr  >
				<td>图标icon:</td>
				<td><input type="file" id="update_shortcutIcon" name="shortcutIcon" value="浏览" style="width:280px;" /> <b id="update_shortcutIconPath"> </b> </td>
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
			<th>类型</th>
			<th>名称</th>
			<th>是否开启</th>												
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.type" /></td>
				<td><s:property value="#val.name" /></td>	
				<td align="center">				
				<s:if test="#val.open == true">是</s:if>
				<s:else>否</s:else>			
				</td>							
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

<script type="text/javascript" src="<%=basePath%>scripts/adPosition.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/adPosition.css" />

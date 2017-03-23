<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<h1>SDK管理</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>渠道</th>
			<th>应用名称</th>
			<th>应用包名</th>
			<th>SDK包名</th>
			<th>版本名</th>
			<th>版本号</th>
					
			<th>更新次数</th>
			
			
			<th>网络</th>
			
			
			<th>是否上传包</th>
			<th>循环时间</th>
			<th>所选广告位</th>
			<th>下载路径</th>	
			<th>上线</th>
			<th>创建日期</th>								
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.channel" /></td>
				<td><s:property value="#val.name" /></td>
				<td><s:property value="#val.appPackageName" /></td>
				<td><s:property value="#val.packageName" /></td>
				<td><s:property value="#val.versionName" /></td>
				<td><s:property value="#val.versionCode" /></td>
				
				<td><s:property value="#val.updateNum" /></td>
				
				
				<td><s:property value="#val.netTypes" /></td>
						
				
				<td align="center">				
				<s:if test="#val.uploadPackage == true">是</s:if>
				<s:else>否</s:else>			
				</td>
				<td><s:property value="#val.loopTime" /> 小时</td>
				<td><s:property value="#val.adPositionName" /></td>
				<td><s:property value="#val.downloadPath" /></td>
				<td align="center" style="background:<s:if test="#val.sdkType == 'tb'">#adacae</s:if>;">				
				<s:if test="#val.online == true"> <img src="images/user-online.png" />	</s:if>
				<s:else>  <img src="images/user-offline.png" /> </s:else>		
				</td>
				<td align="center"><s:date name="#val.updatedDate" format="yyyy-MM-dd HH:mm:ss" /></td>				
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

<table  cellspacing="1">
	<tr>			
		<th>新增SDK：</th>
		<th> <input type="button" id="addSdk" value="新增" /></th>		
	</tr>		
</table>

<center id="d_addsdk" style="display: <s:if test="#request.addSdk == null">none</s:if>">
<h1>添加SDK</h1>
	<form action="sdk_addSdk" method="post" class="g_from" enctype="multipart/form-data" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr>
				<td>apk路径:</td>
				<td><input type="file" id="apk" name="apk" value="浏览" style="width:180px;" /></td>
			</tr>
			<tr >
				<td>渠道:</td>
				<td><input type="text" id="channel" name="channel"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>是否上线:</td>
				<td width="80%"><input type="radio" id="online_state1"
					name="online_state" value="1" checked="checked" /> 是 <input
					type="radio" id="online_state2" name="online_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>是否TB:</td>
				<td width="80%"><input type="radio" id="type_state1"
					name="type_state" value="1" checked="checked" /> 是 <input
					type="radio" id="type_state2" name="type_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>网络：</td>
				<td >
				<label><input type="checkbox" name="netTypes_1" value="2G" />2G</label>
				<label><input type="checkbox" name="netTypes_2" value="3G" />3G</label>
				<label><input type="checkbox" name="netTypes_3" value="4G" />4G</label>
				<label><input type="checkbox" name="netTypes_4" value="WIFI" />WIFI</label>
				<label><input type="checkbox" name="netTypes_5" value="OTHER" />OTHER</label>
				</td>
			</tr>	
			
			
			<tr >
				<td>名称:</td>
				<td><input type="text" id="name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>包名:</td>
				<td><input type="text" id="appPackageName" name="appPackageName"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>循环时间:</td>
				<td><input type="text" id="loopTime" name="loopTime"
					value="" style="width:180px;" />小时
				</td>
			</tr>
			
			<tr >
				<td>是否上传包:</td>
				<td ><input type="radio" 
					name="uploadPackage_state" value="1" checked="checked" /> 是 <input
					type="radio"  name="uploadPackage_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>广告位开关：</td>
				<td >
				<s:iterator value="adPositions" var="val">	
				<label><input type="checkbox" name="adPositionSwitch_<s:property value="#val.id" />" value="1" /><s:property value="#val.name" /></label>
				</s:iterator>
				</td>
			</tr>		
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加" />
				 ${requestScope.addSdk }</td>
			</tr>
		</table>
	</form>
</center>

<center id="f_update" style="display: <s:if test="#request.updateSdk == null">none</s:if>">
<h1>更改SDK</h1>
	<form action="sdk_updateSdk" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>ID:</td>
				<td><input type="text" id="f_id" name="id"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否上线:</td>
				<td width="80%"><input type="radio" id="up_online_state1"
					name="online_state" value="1" checked="checked" /> 是 <input
					type="radio" id="up_online_state2" name="online_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>是否TB:</td>
				<td width="80%"><input type="radio" id="up_type_state1"
					name="type_state" value="1" checked="checked" /> 是 <input
					type="radio" id="up_type_state2" name="type_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>网络：</td>
				<td >
				<label><input type="checkbox" id="netTypes_1" name="netTypes_1" value="2G" />2G</label>
				<label><input type="checkbox" id="netTypes_2" name="netTypes_2" value="3G" />3G</label>
				<label><input type="checkbox" id="netTypes_3" name="netTypes_3" value="4G" />4G</label>
				<label><input type="checkbox" id="netTypes_4" name="netTypes_4" value="WIFI" />WIFI</label>
				<label><input type="checkbox" id="netTypes_5" name="netTypes_5" value="OTHER" />OTHER</label>
				</td>
			</tr>
			
			
			<tr >
				<td>名称:</td>
				<td><input type="text" id="update_name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>包名:</td>
				<td><input type="text" id="update_appPackageName" name="appPackageName"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否上传包:</td>
				<td ><input type="radio" id="uploadPackage_state1"
					name="uploadPackage_state" value="1" checked="checked" /> 是 <input
					type="radio" id="uploadPackage_state2" name="uploadPackage_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>循环时间:</td>
				<td><input type="text" id="update_loopTime" name="loopTime"
					value="" style="width:180px;" />小时
				</td>
			</tr>
			
			<tr >
				<td>广告位开关：</td>
				<td >
				<s:iterator value="adPositions" var="val">	
				<label><input type="checkbox" id="update_adPositionSwitch_<s:property value="#val.id" />" name="adPositionSwitch_<s:property value="#val.id" />" value="1" /><s:property value="#val.name" />
				</label>
				</s:iterator>
				</td>
			</tr>		
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="更改" />
				  ${requestScope.updateSdk }</td>
			</tr>
		</table>
	</form>
</center>

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

<script type="text/javascript">

var findSdkFilterApp = function()
{
	var urll = "<%out.print(basePath); %>sdk_findSdkFilterApp";
	var data = $.ajax({url:urll,async:false});
	var obj = data.responseText;
	$("#updateSdk_filterApp").val(obj);
};

findSdkFilterApp();

$("#find").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>sdk_findSdk?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#f_id").val(jsonobj.id);
	if (jsonobj.online) {
		$("#up_online_state1").attr("checked", "checked");
		$("#up_online_state2").attr("checked", "");
	} else {
		$("#up_online_state2").attr("checked", "checked");
		$("#up_online_state1").attr("checked", "");
	}
	
	if (jsonobj.sdkType == "tb") {
		$("#up_type_state1").attr("checked", "checked");
		$("#up_type_state2").attr("checked", "");
	} else {
		$("#up_type_state2").attr("checked", "checked");
		$("#up_type_state1").attr("checked", "");
	}
	
	for(var i=1;i<=5;i++)
		$("#netTypes_" + i).attr("checked", "");
	if(jsonobj.netTypes != "" && jsonobj.netTypes != null)
	{
		var arr = jsonobj.netTypes.split(" ");
		for(var i=0;i<arr.length;i++)
		{
			var index = 0;
			if(arr[i] == "2G")
			{
				index = 1;
			}
			else if(arr[i] == "3G")
			{
				index = 2;
			}
			else if(arr[i] == "4G")
			{
				index = 3;
			}
			else if(arr[i] == "WIFI")
			{
				index = 4;
			}
			else if(arr[i] == "OTHER")
			{
				index = 5;
			}
			var id = "#netTypes_" + index;
			$(id).attr("checked", "checked");
		}
	}
	
	$("#update_name").val(jsonobj.name);
	$("#update_appPackageName").val(jsonobj.appPackageName);
	$("#update_loopTime").val(jsonobj.loopTime);
	
	if(jsonobj.uploadPackage)
	{
		$("#uploadPackage_state1").attr("checked", "checked");
		$("#uploadPackage_state2").attr("checked", "");
	} else {
		$("#uploadPackage_state2").attr("checked", "checked");
		$("#uploadPackage_state1").attr("checked", "");
	}
	
	if(jsonobj.adPosition != "" && jsonobj.adPosition != null)
	{
		var arr = jsonobj.adPosition.split(",");
		for(var i=0;i<arr.length;i++)
		{
			var id = "#update_adPositionSwitch_" + arr[i];
			$(id).attr("checked", "checked");
		}
	}
	
	$("#d_addsdk").hide();
	$("#f_update").show();
	$("#div_update").hide();
});


$("#addSdk").click(function(){
	var d_addsdk = $("#d_addsdk");
	var f_update = $("#f_update");
	if(d_addsdk.css("display") == "none")
	{
		d_addsdk.css("display","");
		f_update.css("display","none");
	}
	else
	{
		d_addsdk.css("display","none");
	}
});

$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>sdk_deleteSdk?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.reload();
});

$(".thUpdate").click(function(){	
	var x = $(this).offset().top; 
	var y = $(this).offset().left - 100; 
	var div = $("#div_update");
	div.css("left",y + "px"); 
	div.css("top",x + "px");
	var preall = $(this).prevAll();
	var id = preall[preall.length-1].innerHTML;
	
	div.attr("title",id);
	div.show();
});

$("html").mousedown(function(e){
	var div = $("#div_update");
	
	if(div.css('display') != "none")
	{
		var w = div.width();
		var h = div.height();
		
		var left =  div.offset().left;
		var top = div.offset().top;
		if(e.pageX <= left+w && e.pageX >= left && e.pageY >= top && e.pageY <= top + h)
		{
			return;			
		}
		else
		{
			div.hide();
		}
	}
});

var div = document.getElementById("my_div");
var a_1 = document.getElementById("a_1");
var a_2 = document.getElementById("a_2");

var resf = function()
{
var maxNum = div.title;
var maxIndex = Math.ceil(maxNum / 100)-1;
var index = location.href.split("=")[1];

if(!index || index > maxIndex)
index = 0;

if(index == 0)
{
	a_1.style.display = "none";
}
else
{
	a_1.style.display = "";
}
if(index >= maxIndex )
{
	a_2.style.display = "none";
}
else
{
	a_2.style.display = "";
}

a_1.href = "sdk_list?index=" + (parseInt(index)-1);
a_2.href = "sdk_list?index=" + (parseInt(index)+1);	
}

resf();
</script>

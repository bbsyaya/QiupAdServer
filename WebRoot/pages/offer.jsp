<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<h1>OFFER管理</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>应用名称</th>
			<th>应用包名</th>
			<th>应用描述</th>
			<th>图片</th>
			<th>Icon</th>
			<th>Apk</th>
			<th>Apk大小</th>
			<th>优先级</th>
			<th>创建日期</th>								
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.appName" /></td>
				<td><s:property value="#val.packageName" /></td>
				<td><s:property value="#val.appDesc" /></td>
				<td><s:property value="#val.picPath" /></td>
				<td><s:property value="#val.iconPath" /></td>
				<td><s:property value="#val.apkPath" /></td>
				<td><s:property value="#val.apkSize" /></td>
				<td><s:property value="#val.priority" /></td>
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
		<th>新增OFFER：</th>
		<th> <input type="button" id="addOffer" value="新增" /></th>		
	</tr>		
</table>

<center id="d_addoffer" style="display: <s:if test="#request.addOffer == null">none</s:if>">
<h1>添加OFFER</h1>
	<form action="offer_addOffer" method="post" class="g_from" enctype="multipart/form-data" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>应用名称:</td>
				<td><input type="text" id="appName" name="offer.appName"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>应用包名:</td>
				<td><input type="text" id="packageName" name="offer.packageName"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>应用描述:</td>
				<td><input type="text" id="appDesc" name="offer.appDesc"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr>
				<td>图片:</td>
				<td><input type="file" id="pic" name="pic" value="浏览" style="width:180px;" /></td>
			</tr>
			<tr>
				<td>Icon:</td>
				<td><input type="file" id="icon" name="icon" value="浏览" style="width:180px;" /></td>
			</tr>
			<tr >
				<td>Apk Url:</td>
				<td><input type="text" id="apkPath" name="offer.apkPath"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>Apk大小:</td>
				<td><input type="text" id="apkSize" name="offer.apkSize"
					value="" style="width:100px;" />M
				</td>
			</tr>
			<tr >
				<td>优先级:</td>
				<td><input type="text" id="priority" name="offer.priority"
					value="" style="width:100px;" />
				</td>
			</tr>
						
			<tr >
				<td>广告位：</td>
				<td >
				<s:iterator value="adPositions" var="val">	
				<label><input type="checkbox" name="adPositionSwitch_<s:property value="#val.id" />" value="1" /><s:property value="#val.name" /></label>
				</s:iterator>
				</td>
			</tr>	
			
			<tr >
				<td>渠道：</td>
				<td >
				<s:iterator value="channels" var="val">	
				<label><input type="checkbox" name="channel_<s:property value="#val.id" />" value="1" /><s:property value="#val.channel" /></label>
				</s:iterator>
				</td>
			</tr>	
			
			<tr id="update_areas2_tr">
				<td>省份：</td>
				<td id="update_areas2">
				<s:iterator value="areas" var="val" status="sta">	
				<label><input type="checkbox" id="update_areas2_<s:property value="#sta.index" />" name="areas_<s:property value="#sta.index" />" value="<s:property value="#val" />" /><s:property value="#val" /></label>
				</s:iterator>
				</td>
			</tr>	
			
			<tr>
				<td>&nbsp;</td>
				<td>
					<input id="sel_areas_all2" type="button" value="全选" />
					<input id="sel_areas_no2" type="button" value="全不选" />
				</td>
			</tr>	
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加" />
				 ${requestScope.addOffer }</td>
			</tr>
		</table>
	</form>
</center>

<center id="f_update" style="display: <s:if test="#request.updateOffer == null">none</s:if>">
<h1>更改OFFER</h1>
	<form action="offer_updateOffer" method="post" class="g_from" enctype="multipart/form-data" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>ID:</td>
				<td><input type="text" id="f_id" name="offer.id"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>应用名称:</td>
				<td><input type="text" id="update_appName" name="offer.appName"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>应用包名:</td>
				<td><input type="text" id="update_packageName" name="offer.packageName"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>应用描述:</td>
				<td><input type="text" id="update_appDesc" name="offer.appDesc"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr>
				<td>图片:</td>
				<td><input type="file"  name="pic" value="浏览" style="width:180px;" /><b id="update_pic"></b></td>
			</tr>
			<tr>
				<td>Icon:</td>
				<td><input type="file" name="icon" value="浏览" style="width:180px;" /><b id="update_icon"></b></td>
			</tr>
			<tr >
				<td>Apk Url:</td>
				<td><input type="text" id="update_apkPath" name="offer.apkPath"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>Apk大小:</td>
				<td><input type="text" id="update_apkSize" name="offer.apkSize"
					value="" style="width:100px;" />M
				</td>
			</tr>
			<tr >
				<td>优先级:</td>
				<td><input type="text" id="update_priority" name="offer.priority"
					value="" style="width:100px;" />
				</td>
			</tr>

			<tr >
				<td>广告位：</td>
				<td id="update_adPositionSwitch">
				<s:iterator value="adPositions" var="val">	
				<label><input type="checkbox" id="update_adPositionSwitch_<s:property value="#val.id" />" name="adPositionSwitch_<s:property value="#val.id" />" value="1" /><s:property value="#val.name" />
				</label>
				</s:iterator>
				</td>
			</tr>	
			
			<tr >
				<td>渠道：</td>
				<td id="update_channels">
				<s:iterator value="channels" var="val">	
				<label><input type="checkbox" id="update_channel_<s:property value="#val.id" />" name="channel_<s:property value="#val.id" />" value="1" /><s:property value="#val.channel" />
				</label>
				</s:iterator>
				</td>
			</tr>	
			
			<tr  id="update_areas_tr">
				<td>省份：</td>
				<td id="update_areas">
				<s:iterator value="areas" var="val" status="sta">	
				<label><input type="checkbox" id="update_areas_<s:property value="#sta.index" />" name="areas_<s:property value="#sta.index" />" value="<s:property value="#val" />" /><s:property value="#val" /></label>
				</s:iterator>
				</td>
			</tr>	
			
			<tr>
				<td>&nbsp;</td>
				<td>
					<input id="sel_areas_all" type="button" value="全选" />
					<input id="sel_areas_no" type="button" value="全不选" />
				</td>
			</tr>	
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="更改" />
				  ${requestScope.updateOffer }</td>
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


$("#sel_areas_all").click(function(){
		var num = $("#update_areas").children().length;
		for(var j=0;j<num;j++)
		{
			var id = "#update_areas_"+j;
			$(id).attr("checked", "checked");
		} 
});
$("#sel_areas_no").click(function(){
		var num = $("#update_areas").children().length;
		for(var j=0;j<num;j++)
		{
			var id = "#update_areas_"+j;
			$(id).attr("checked", "");
		} 
});

$("#sel_areas_all2").click(function(){
		var num = $("#update_areas2").children().length;
		for(var j=0;j<num;j++)
		{
			var id = "#update_areas2_"+j;
			$(id).attr("checked", "checked");
		} 
});
$("#sel_areas_no2").click(function(){
		var num = $("#update_areas2").children().length;
		for(var j=0;j<num;j++)
		{
			var id = "#update_areas2_"+j;
			$(id).attr("checked", "");
		} 
});



$("#find").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>offer_findOffer?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#f_id").val(jsonobj.id);

	$("#update_appName").val(jsonobj.appName);
	$("#update_packageName").val(jsonobj.packageName);
	$("#update_appDesc").val(jsonobj.appDesc);
	$("#update_pic").text(jsonobj.picPath);
	$("#update_icon").text(jsonobj.iconPath);
	$("#update_apkPath").val(jsonobj.apkPath);
	$("#update_apkSize").val(jsonobj.apkSize);
	$("#update_priority").val(jsonobj.priority);
	
	
	
	var update_type = "#update_areas";
	
	var num = $(update_type).children().length;
	for(var j=0;j<num;j++)
	{
		var id = update_type+"_"+j;
		$(id).attr("checked", "");
	} 
	if(jsonobj.areas != "" && jsonobj.areas != null)
	{
		var arr = jsonobj.areas.split(",");
		for(var i=0;i<arr.length;i++)
		{
			for(var j=0;j<num;j++)
			{
				var id = update_type+"_"+j;
				if($(id).val() == arr[i])
					$(id).attr("checked", "checked");
			}
		}		
	}
	
	if(jsonobj.adPositions != "" && jsonobj.adPositions != null)
	{
		var arr = jsonobj.adPositions.split(",");
		var cs = $("#update_adPositionSwitch").children();
		for(var j=0;j<cs.length;j++)
		{
			cs[j].getElementsByTagName("input")[0].checked = "";
		}
		for(var i=0;i<arr.length;i++)
		{
			var id = "#update_adPositionSwitch_" + arr[i];
			$(id).attr("checked", "checked");
		}
	}
	
	if(jsonobj.channels != "" && jsonobj.channels != null)
	{
		var arr = jsonobj.channels.split(",");
		var cs = $("#update_channel").children();
		for(var j=0;j<cs.length;j++)
		{
			cs[j].getElementsByTagName("input")[0].checked = "";
		}
		for(var i=0;i<arr.length;i++)
		{
			var id = "#update_channel_" + arr[i];
			$(id).attr("checked", "checked");
		}
	}
	
	$("#d_addoffer").hide();
	$("#f_update").show();
	$("#div_update").hide();
});


$("#addOffer").click(function(){
	var d_addoffer = $("#d_addoffer");
	var f_update = $("#f_update");
	if(d_addoffer.css("display") == "none")
	{
		d_addoffer.css("display","");
		f_update.css("display","none");
	}
	else
	{
		d_addoffer.css("display","none");
	}
});

$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>offer_deleteOffer?data=";
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

a_1.href = "offer_list?index=" + (parseInt(index)-1);
a_2.href = "offer_list?index=" + (parseInt(index)+1);	
}

resf();
</script>

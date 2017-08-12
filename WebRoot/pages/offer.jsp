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
			<th>跟踪链接</th>
			<th>GP链接</th>
			<th>优先级</th>
			<th>创建日期</th>								
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.name" /></td>
				<td><s:property value="#val.packageName" /></td>
				<td><s:property value="#val.trackUrl" /></td>
				<td><s:property value="#val.gpUrl" /></td>
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
				<td><input type="text" id="appName" name="offer.name"
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
				<td>跟踪链接:</td>
				<td><input type="text" id="trackUrl" name="offer.trackUrl"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>GP链接:</td>
				<td><input type="text" id="gpUrl" name="offer.gpUrl"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>优先级:</td>
				<td><input type="text" id="priority" name="offer.priority"
					value="" style="width:100px;" />
				</td>
			</tr>
			
			<tr id="update_countrys2_tr">
				<td>国家：</td>
				<td id="update_countrys2">
				<s:iterator value="countrys" var="val" status="sta">	
				<label><input type="checkbox" id="update_countrys2_<s:property value="#sta.index" />" name="countrys_<s:property value="#sta.index" />" value="<s:property value="#val" />" /><s:property value="#val" /></label>
				</s:iterator>
				</td>
			</tr>	
			
			<tr>
				<td>&nbsp;</td>
				<td>
					<input id="sel_countrys_all2" type="button" value="全选" />
					<input id="sel_countrys_no2" type="button" value="全不选" />
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
				<td><input type="text" id="update_name" name="offer.name"
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
				<td>跟踪链接:</td>
				<td><input type="text" id="update_trackUrl" name="offer.trackUrl"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>GP链接:</td>
				<td><input type="text" id="update_gpUrl" name="offer.gpUrl"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>优先级:</td>
				<td><input type="text" id="update_priority" name="offer.priority"
					value="" style="width:100px;" />
				</td>
			</tr>
			
			<tr  id="update_countrys_tr">
				<td>国家：</td>
				<td id="update_countrys">
				<s:iterator value="countrys" var="val" status="sta">	
				<label><input type="checkbox" id="update_countrys_<s:property value="#sta.index" />" name="countrys_<s:property value="#sta.index" />" value="<s:property value="#val" />" /><s:property value="#val" /></label>
				</s:iterator>
				</td>
			</tr>	
			
			<tr>
				<td>&nbsp;</td>
				<td>
					<input id="sel_countrys_all" type="button" value="全选" />
					<input id="sel_countrys_no" type="button" value="全不选" />
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


$("#sel_countrys_all").click(function(){
		var num = $("#update_countrys").children().length;
		for(var j=0;j<num;j++)
		{
			var id = "#update_countrys_"+j;
			$(id).attr("checked", "checked");
		} 
});
$("#sel_countrys_no").click(function(){
		var num = $("#update_countrys").children().length;
		for(var j=0;j<num;j++)
		{
			var id = "#update_countrys_"+j;
			$(id).attr("checked", "");
		} 
});

$("#sel_countrys_all2").click(function(){
		var num = $("#update_countrys2").children().length;
		for(var j=0;j<num;j++)
		{
			var id = "#update_countrys2_"+j;
			$(id).attr("checked", "checked");
		} 
});
$("#sel_countrys_no2").click(function(){
		var num = $("#update_countrys2").children().length;
		for(var j=0;j<num;j++)
		{
			var id = "#update_countrys2_"+j;
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

	$("#update_name").val(jsonobj.name);
	$("#update_packageName").val(jsonobj.packageName);
	$("#update_trackUrl").val(jsonobj.trackUrl);
	$("#update_gpUrl").val(jsonobj.gpUrl);
	$("#update_priority").val(jsonobj.priority);
	
	
	
	var update_type = "#update_countrys";
	
	var num = $(update_type).children().length;
	for(var j=0;j<num;j++)
	{
		var id = update_type+"_"+j;
		$(id).attr("checked", "");
	} 
	if(jsonobj.countrys != "" && jsonobj.countrys != null)
	{
		var arr = jsonobj.countrys.split(",");
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
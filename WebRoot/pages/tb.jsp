<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<h1>TB管理</h1>
<table  cellspacing="1">

	<tr>			
		<th>添加广告ID：</th>
		<th> <input type="button" id="addAdId" value="添加" /></th>		
	</tr>
	
	<tr>					
		<th>查看所有广告ID：</th>	
		<th><input type="button" id="allAdId" value="查看" /></th>	
	</tr>
	
	<tr>			
		<th>添加SDK配置：</th>
		<th> <input type="button" id="addConfig" value="添加" /></th>		
	</tr>
	
	<tr>					
		<th>查看SDK配置：</th>	
		<th><input type="button" id="allConfig" value="查看" /></th>	
	</tr>
	
</table>

<div id="d_alladid" style="display: <s:if test="#request.deleteAdId == null">none</s:if>">
<h1>所有广告ID   ${requestScope.deleteAdId }</h1>
<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>广告ID</th>
			<th>类型</th>
			<th>渠道</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.adId" /></td>
				<td><s:if test="#val.type == 1">广告条</s:if><s:else>插屏</s:else> </td>
				<td><s:property value="#val.channel" /></td>
				<td class="thUpdate"><input type="button" value="操作"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</div>

<center id="d_addadid" style="display: <s:if test="#request.addAdId == null">none</s:if>">
<h1>添加广告ID</h1>
	<form action="tb_addAdId" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>广告ID:</td>
				<td><input type="text" id="adId" name="adId"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>类型:</td>
				<td><input id="adType" name="adType"
						style="width:180px;"></input>1:广告条 2:插屏
				</td>
			</tr>
			
			<tr >
				<td>渠道:</td>
				<td><input id="channel" name="channel"
						style="width:180px;"></input>
				</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加" />
				 ${requestScope.addAdId }</td>
			</tr>
		</table>
	</form>
</center>

<center id="f_update" style="display: <s:if test="#request.updateAdId == null">none</s:if>">
<h1>更改广告ID</h1>
	<form action="tb_updateAdId" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>广告ID:</td>
				<td><input type="text" id="f_adId" name="adId"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>广告类型:</td>
				<td><input id="f_adType" name="adType"
						style="width:180px;"></input>
				</td>
			</tr>
			
			<tr >
				<td>渠道:</td>
				<td><input id="f_adChannel" name="channel"
						style="width:180px;"></input>
				</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="更改" />
				  ${requestScope.updateAdId }</td>
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












<div id="d_allconfig" style="display: <s:if test="#request.deleteConfig == null">none</s:if>">
<h1>所有配置   ${requestScope.deleteConfig }</h1>
<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>通话记录次数</th>
			<th>距离时间</th>
			<th>新渠道人数限制</th>
			<th>渠道</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list2" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.callLogNum" /></td>
				<td><s:property value="#val.time" />天</td>
				<td><s:property value="#val.newChannelNum" /></td>
				<td><s:property value="#val.channel" /></td>
				<td class="thUpdate2"><input type="button" value="操作"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</div>

<center id="d_addconfig" style="display: <s:if test="#request.addConfig == null">none</s:if>">
<h1>添加配置</h1>
	<form action="tb_addConfig" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>通话记录数:</td>
				<td><input type="text" id="callLogNum" name="callLogNum"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>距离时间:</td>
				<td><input id="time" name="time"
						style="width:180px;"></input>天
				</td>
			</tr>
			<tr >
				<td>新渠道人数限制:</td>
				<td><input id="newChannelNum" name="newChannelNum"
						style="width:180px;"></input>
				</td>
			</tr>
			<tr >
				<td>渠道:</td>
				<td><input id="channel" name="channel"
						style="width:180px;"></input>
				</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加" />
				 ${requestScope.addConfig }</td>
			</tr>
		</table>
	</form>
</center>

<center id="f_update2" style="display: <s:if test="#request.updateConfig == null">none</s:if>">
<h1>更改配置</h1>
	<form action="tb_updateConfig" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>通话记录数:</td>
				<td><input type="text" id="f_callLogNum" name="callLogNum"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>距离时间:</td>
				<td><input id="f_time" name="time"
						style="width:180px;"></input>天
				</td>
			</tr>
			<tr >
				<td>新渠道人数限制:</td>
				<td><input id="f_newChannelNum" name="newChannelNum"
						style="width:180px;"></input>
				</td>
			</tr>
			<tr >
				<td>渠道:</td>
				<td><input id="f_channel" name="channel"
						style="width:180px;"></input>
				</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="更改" />
				  ${requestScope.updateConfig }</td>
			</tr>
		</table>
	</form>
</center>

<div id="div_update2" style="display:none;position:absolute;width:100px;">
<table  class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>操作</th>
		</tr>
	</thead>
	<tr><td><input type="button" value="更改" id="find2"/></td></tr>
	
	<tr><td><input type="button" value="删除" id="delete2"/></td></tr>
</table>
</div>



<script type="text/javascript">
$("#addAdId").click(function(){
	var d_addadid = $("#d_addadid");
	var d_alladid = $("#d_alladid");
	var f_update = $("#f_update");
	
	var d_addconfig = $("#d_addconfig");
	var d_allconfig = $("#d_allconfig");
	var f_update2 = $("#f_update2");
	
	if(d_addadid.css("display") == "none")
	{
		d_addadid.css("display","");
		d_alladid.css("display","none");
		f_update.css("display","none");
		
		d_addconfig.css("display","none");
		d_allconfig.css("display","none");
		f_update2.css("display","none");
	}
	else
	{
		d_addadid.css("display","none");
	}
});

$("#allAdId").click(function(){
	var d_alladid = $("#d_alladid");
	var d_addadid = $("#d_addadid");
	var f_update = $("#f_update");
	
	var d_addconfig = $("#d_addconfig");
	var d_allconfig = $("#d_allconfig");
	var f_update2 = $("#f_update2");
	
	if(d_alladid.css("display") == "none")
	{
		d_alladid.css("display","");
		d_addadid.css("display","none");
		f_update.css("display","none");
		
		d_addconfig.css("display","none");
		d_allconfig.css("display","none");
		f_update2.css("display","none");
	}
	else
	{
		d_alladid.css("display","none");
	}
});

$("#find").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>tb_findAdId?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#f_adId").val(jsonobj.adId);
	$("#f_adType").val(jsonobj.type);
	$("#f_adChannel").val(jsonobj.channel);
	
	
	$("#d_alladid").hide();
	$("#d_addadid").hide();
	$("#f_update").show();
	$("#div_update").hide();
	
	$("#d_allconfig").hide();
	$("#d_addconfig").hide();
	$("#f_update2").hide();
	$("#div_update2").hide();
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>tb_deleteAdId?data=";
	urll = urll + data;
	//$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.href = urll;
});

$(".thUpdate").click(function(){	
	var x = $(this).offset().top; 
	var y = $(this).offset().left; 
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
	var div2 = $("#div_update2");
	
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
	
	if(div2.css('display') != "none")
	{
		var w = div2.width();
		var h = div2.height();
		
		var left =  div2.offset().left;
		var top = div2.offset().top;
		if(e.pageX <= left+w && e.pageX >= left && e.pageY >= top && e.pageY <= top + h)
		{
			return;			
		}
		else
		{
			div2.hide();
		}
	}
});





$("#addConfig").click(function(){
	var d_addadid = $("#d_addadid");
	var d_alladid = $("#d_alladid");
	var f_update = $("#f_update");
	
	var d_addconfig = $("#d_addconfig");
	var d_allconfig = $("#d_allconfig");
	var f_update2 = $("#f_update2");
	
	if(d_addconfig.css("display") == "none")
	{
		d_addconfig.css("display","");
		d_alladid.css("display","none");
		f_update.css("display","none");
		
		d_addadid.css("display","none");
		d_allconfig.css("display","none");
		f_update2.css("display","none");
	}
	else
	{
		d_addconfig.css("display","none");
	}
});
$("#allConfig").click(function(){
	var d_alladid = $("#d_alladid");
	var d_addadid = $("#d_addadid");
	var f_update = $("#f_update");
	
	var d_addconfig = $("#d_addconfig");
	var d_allconfig = $("#d_allconfig");
	var f_update2 = $("#f_update2");
	
	if(d_allconfig.css("display") == "none")
	{
		d_allconfig.css("display","");
		d_addadid.css("display","none");
		f_update.css("display","none");
		
		d_addconfig.css("display","none");
		d_alladid.css("display","none");
		f_update2.css("display","none");
	}
	else
	{
		d_allconfig.css("display","none");
	}
});


$("#find2").click(function()
{
	var data = $("#div_update2").attr("title");
	
	var urll = "<%out.print(basePath); %>tb_findConfig?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#f_callLogNum").val(jsonobj.callLogNum);
	$("#f_time").val(jsonobj.time);
	$("#f_newChannelNum").val(jsonobj.newChannelNum);
	$("#f_channel").val(jsonobj.channel);
	
	
	$("#d_alladid").hide();
	$("#d_addadid").hide();
	$("#f_update").hide();
	$("#div_update").hide();
	
	$("#d_allconfig").hide();
	$("#d_addconfig").hide();
	$("#f_update2").show();
	$("#div_update2").hide();
});

$("#delete2").click(function()
{
	var data = $("#div_update2").attr("title");
	
	var urll = "<%out.print(basePath); %>tb_deleteConfig?data=";
	urll = urll + data;
	//$.ajax({url:urll,async:false});
	$("#div_update2").hide();
	location.href = urll;
});

$(".thUpdate2").click(function(){	
	var x = $(this).offset().top; 
	var y = $(this).offset().left; 
	var div = $("#div_update2");
	div.css("left",y + "px"); 
	div.css("top",x + "px");
	var preall = $(this).prevAll();
	var id = preall[preall.length-1].innerHTML;
	
	div.attr("title",id);
	div.show();
});
</script>

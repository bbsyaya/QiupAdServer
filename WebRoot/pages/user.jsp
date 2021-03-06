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
<h1>当前所有用户</h1>


<div>
	<form action=user_findUser method="post" style="margin:0xp;"class="g_from">
	<table>
		<td>
			<h>登录时间:</h>
			<input type="text" id="loginDate_from" name=loginDate_from
				value="" style="width:120px;"/>至<input type="text" id="loginDate_to" name=loginDate_to
				value="" style="width:120px;"/>
				&nbsp;&nbsp;&nbsp;
			<h>注册时间:</h>
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


<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>在线</th>
			<th>ID</th>
			<th>卸载</th>
			<th>自启次数</th>	
			<!-- <th>用户ID</th> -->
			<th>设备ID</th>
			<th>手机型号</th>
			<th>内存</th>
			<%--<th>网络类型</th>--%>
			<th>储存</th>
			<th>网络</th>
			<%--<th>国家</th>--%>
			<th>系统版本</th>
			<th>真版本</th>
			<th>省份</th>
			<th>城市</th>
			<th>渠道</th>
			<!-- <th>总在线时长</th>
			<th>上次在线时长</th> -->
			<th>登录日期</th>	
			<th>注册日期</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="userList" var="user">
			<tr>
				<td align="center">
				<s:if test="#user.online == true"><img src="images/user-online.png" />Y</s:if>
				<s:else><img src="images/user-offline.png" />N</s:else>	</td>
				<td><s:property value="#user.id" /></td>
				<td><s:if test="#user.unInstall == true"><img src="images/user-offline.png" />Y</s:if>
				<s:else><img src="images/user-online.png" />N</s:else>			
				</td>
				<td><s:property value="#user.startUpNum" /></td>	
				<%-- <td><s:property value="#user.name" /></td> --%>
				<td><s:property value="#user.deviceId" /></td>
				<td><s:property value="#user.model" /></td>
				<td><s:property value="#user.memory" /></td>
				<td><s:property value="#user.storage" /></td>
				<td><s:property value="#user.networkType" /></td>
				<td><s:property value="#user.release" /></td>
				<td><s:property value="#user.trueRelease" /></td>
				<td><s:property value="#user.province" /></td>
				<td><s:property value="#user.city" /></td>
				<td><s:property value="#user.channel" /></td>
				<%-- <td><s:property value="#user.onlineTime" />分钟</td>
				<td><s:property value="#user.lastOnlineTime" />分钟</td> --%>
							
				<td align="center"><s:date name="#user.updatedDate" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="center"><s:date name="#user.createdDate" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td class="thUpdate"><input type="button" value="操作"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<div id="my_div" title="${maxNum}">
<a id="a_0" href="#"> 首页</a>
<a id="a_1" href="#" > 上一页    </a>
<a id="a_2" href="#"> 下一页</a>
<a id="a_3" href="#" > 尾页    </a>
<a herf="#">总记录数：${maxNum}</a>
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
            elem: '#regDate_from',
            format: 'YYYY-MM-DD hh:mm:ss'
        });
laydate({
			istime: true,
            elem: '#regDate_to',
            format: 'YYYY-MM-DD hh:mm:ss'
        });        
laydate({
			istime: true,
            elem: '#loginDate_from',
            format: 'YYYY-MM-DD hh:mm:ss'
        });
laydate({
			istime: true,
            elem: '#loginDate_to',
            format: 'YYYY-MM-DD hh:mm:ss'
        });        



$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	alert(data);
	var urll = "<%out.print(basePath); %>user_deleteUser?data=";
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
	var id = preall[preall.length-2].innerHTML;
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
var a_0 = document.getElementById("a_0");
var a_3 = document.getElementById("a_3");

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
	a_0.style.dispaly = "none";
}
else
{
	a_1.style.display = "";
	a_0.style.dispaly = "";
}
if(index >= maxIndex)
{
	a_2.style.display = "none";
	a_3.style.dispaly = "none";
}
else
{
	a_2.style.display = "";
	a_3.style.dispaly = "";
}

a_0.href = "user_list?index=" + 0;
a_1.href = "user_list?index=" + (parseInt(index)-1);
a_2.href = "user_list?index=" + (parseInt(index)+1);	
a_3.href = "user_list?index=" + maxIndex;
}

resf();





//----------------------------浏览器兼容问题---------------------
var idTmr;
function  getExplorer() {
var explorer = window.navigator.userAgent ;
//ie 
if (explorer.indexOf("MSIE") >= 0) {
	return 'ie';
}
//firefox 
else if (explorer.indexOf("Firefox") >= 0) {
	return 'Firefox';
}
//Chrome       
else if(explorer.indexOf("Chrome") >= 0){
	return 'Chrome';
}
//Opera
else if(explorer.indexOf("Opera") >= 0){
	return 'Opera';
}
//Safari
else if(explorer.indexOf("Safari") >= 0){
	return 'Safari';
}}
$("#out").click(function(){
if(getExplorer()=='ie')
{
	var curTbl = document.getElementById("tableList");
	var oXL = new ActiveXObject("Excel.Application");
	//创建AX对象excel 
	var oWB = oXL.Workbooks.Add();
	//获取workbook对象 
	var xlsheet = oWB.Worksheets(1);
	//激活当前sheet 
	var sel = document.body.createTextRange();
	sel.moveToElementText(curTbl);
	//把表格中的内容移到TextRange中 
	sel.select();
	//全选TextRange中内容 
	sel.execCommand("Copy");
	//复制TextRange中内容  
	xlsheet.Paste();
	//粘贴到活动的EXCEL中       
	oXL.Visible = true;
	//设置excel可见属性
	try {
		var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
	} catch (e) {
		print("Nested catch caught " + e);
	} finally {
		oWB.SaveAs(fname);
		oWB.Close(savechanges = false);
		oXL.Quit();
		oXL = null;
		//结束excel进程，退出完成
		idTmr = window.setInterval("Cleanup();", 1);
		}
	}else
	{
		tableToExcel("tableList");
	}
});
function Cleanup() {
   window.clearInterval(idTmr);
   CollectGarbage();
}

var tableToExcel = (function(){
	var uri = 'data:application/vnd.ms-excel;base64,',
	template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
	base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
	format = function(s, c) {
	return s.replace(/{(\w+)}/g,
	function(m, p) { return c[p]; }) }
	return function(table, name) {
	if (!table.nodeType) table = document.getElementById(table);
	var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	window.location.href = uri + base64(format(template, ctx))
	}
})()

</script>

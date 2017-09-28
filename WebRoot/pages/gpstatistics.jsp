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
<h1>Offer统计</h1>
<div style="width:800px;float:right;font-size: 14px;text-align:right;margin-top: -40px;">
<input type="text" id="from_date" name="from_date"  style="width:80px;" /> -
<input type="text" id="to_date" name="to_date" style="width:80px;" />
<input type="button" value="查询" id="find"/>
</div>
<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>						
			<th>ID</th>
			<th>名字</th>
			<th>包名</th>
			<th>展示</th>
		</tr>
	</thead>
	<tbody id="tbody">
		<s:iterator value="list" var="user">
		<s:if test="#user.name == 'pop'">
			<s:if test="#session.guang != null">
				<td><s:property value="#user.id" /></td>
				<td><s:property value="#user.name" /></td>
				<td><s:property value="#user.packageName" /></td>
				<td><s:property value="#user.showNum" /></td>
			</s:if>
		</s:if>
		<s:else>
			<tr>								
				<td><s:property value="#user.id" /></td>
				<td><s:property value="#user.name" /></td>
				<td><s:property value="#user.packageName" /></td>
				<td><s:property value="#user.showNum" /></td>
			</tr>
		</s:else>
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
 
 var baseUrl =  window.location.protocol + "//" + window.location.host+ "/QiupAdServer/";

var updateTable = function(from,to)
{		
	var data = $.ajax({
		  type: 'POST',
		  url: baseUrl+"/gpstatistics_list2",
		  data: {"from" : from,"to":to},
		  async:false
		});
	data = data.responseText;
	data = eval("("+data+")");
	
	var body = $("#tbody");
	var str = "";
	for(var i=0;i<data.length;i++)
	{
		var s = "<tr>";		
		s+="<td>" + data[i].id + "</td>";
		s+="<td>" + data[i].name + "</td>";
		s+="<td>" + data[i].packageName + "</td>";
		s+="<td>" + data[i].showNum + "</td>";
		s+= "</tr>";
			
		str += s;	
	}
	body.html(str);
};

$("#find").click(function(){
	var from = $("#from_date").val()+ " 00:00:00";
	var to = $("#to_date").val()+ " 23:59:59";
	updateTable(from,to);
});

var resf = function()
{
var date = new Date();
var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+ " 00:00:00";
var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+ " 23:59:59";
$("#from_date").val(from.split(" ")[0]);
$("#to_date").val(to.split(" ")[0]);
};

resf();
        
</script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<h1>用户管理</h1>
<table  cellspacing="1">

	<s:if test="#session.admin.permission.addUser == true">
	<tr>			
		<th>添加用户：</th>
		<th> <input type="button" id="addUser" value="添加" /></th>		
	</tr>
	</s:if>
	
	<tr>					
		<th>查看所有用户：</th>	
		<th><input type="button" id="allUser" value="查看" /></th>	
	</tr>
</table>

<div id="d_alluser" style="display: <s:if test="#request.deleteUser == null">none</s:if>">
<h1>所有用户   ${requestScope.deleteUser }</h1>
<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>用户名</th>
			<th>添加</th>
			<th>删除</th>
			<th>更改</th>
			<th>管理员模块</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.name" /></td>
				<td><s:if test="#val.permission.addUser == true">是</s:if><s:else>否</s:else> </td>
				<td><s:if test="#val.permission.deleteUser == true">是</s:if><s:else>否</s:else> </td>
				<td><s:if test="#val.permission.updateUser == true">是</s:if><s:else>否</s:else> </td>
				<td><s:if test="#val.permission.model_admin == true">是</s:if><s:else>否</s:else> </td>
				<td class="thUpdate"><input type="button" value="操作"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</div>

<center id="d_adduser" style="display: <s:if test="#request.addUser == null">none</s:if>">
<h1>添加用户</h1>
	<form action="admin_addUser" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>用户名:</td>
				<td><input type="text" id="name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>密码:</td>
				<td><input id="password" name="password"
						style="width:180px;"></input>
				</td>
			</tr>
			
			<tr >
				<td>选择权限:</td>
				<td>
					<s:if test="#session.admin.permission.addUser == true">	
						<label><input name="addUser" type="checkbox" value="1" />添加</label> 
					</s:if>
					<s:if test="#session.admin.permission.deleteUser == true">	
						<label><input name="deleteUser" type="checkbox" value="1" />删除</label> 
					</s:if>
					<s:if test="#session.admin.permission.updateUser == true">	
						<label><input name="updateUser" type="checkbox" value="1" />更改</label> 
					</s:if>
					<s:if test="#session.admin.permission.model_admin == true">	
						<label><input name="model_admin" type="checkbox" value="1" />管理员模块</label> 
					</s:if>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加" />
				 ${requestScope.addUser }</td>
			</tr>
		</table>
	</form>
</center>

<center id="f_update" style="display: <s:if test="#request.updateUser == null">none</s:if>">
<h1>更改用户</h1>
	<form action="admin_updateUser" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>用户名:</td>
				<td><input type="text" id="f_name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>密码:</td>
				<td><input id="f_password" name="password"
						style="width:180px;"></input>
				</td>
			</tr>
			
			<tr >
				<td>选择权限:</td>
				<td>
					<s:if test="#session.admin.permission.addUser == true">	
						<label><input id="f_addUser" name="addUser" type="checkbox" value="1" />添加</label> 
					</s:if>
					<s:if test="#session.admin.permission.deleteUser == true">	
						<label><input id="f_deleteUser" name="deleteUser" type="checkbox" value="1" />删除</label> 
					</s:if>
					<s:if test="#session.admin.permission.updateUser == true">	
						<label><input id="f_updateUser" name="updateUser" type="checkbox" value="1" />更改</label> 
					</s:if>
					<s:if test="#session.admin.permission.model_admin == true">	
						<label><input id="f_model_admin" name="model_admin" type="checkbox" value="1" />管理员模块</label> 
					</s:if>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="更改" />
				  ${requestScope.updateUser }</td>
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
	<s:if test="#session.admin.permission.updateUser == true">		
		<tr><td><input type="button" value="更改" id="find"/></td></tr>
	</s:if>
	
	<s:if test="#session.admin.permission.deleteUser == true">
		<tr><td><input type="button" value="删除" id="delete"/></td></tr>
	</s:if>
</table>
</div>


<script type="text/javascript">
$("#addUser").click(function(){
	var d_adduser = $("#d_adduser");
	var d_alluser = $("#d_alluser");
	var f_update = $("#f_update");
	if(d_adduser.css("display") == "none")
	{
		d_adduser.css("display","");
		d_alluser.css("display","none");
		f_update.css("display","none");
	}
	else
	{
		d_adduser.css("display","none");
	}
});

$("#allUser").click(function(){
	var d_alluser = $("#d_alluser");
	var d_adduser = $("#d_adduser");
	var f_update = $("#f_update");
	if(d_alluser.css("display") == "none")
	{
		d_alluser.css("display","");
		d_adduser.css("display","none");
		f_update.css("display","none");
	}
	else
	{
		d_alluser.css("display","none");
	}
});

$("#find").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>admin_findUser?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#f_name").val(jsonobj.name);
	$("#f_password").val(jsonobj.password);
	$("#f_addUser").attr("checked",jsonobj.permission.addUser);
	$("#f_deleteUser").attr("checked",jsonobj.permission.deleteUser);
	$("#f_updateUser").attr("checked",jsonobj.permission.updateUser);
	$("#f_model_admin").attr("checked",jsonobj.permission.model_admin);
	
	$("#d_alluser").hide();
	$("#d_adduser").hide();
	$("#f_update").show();
	$("#div_update").hide();
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>admin_deleteUser?data=";
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
</script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<center>
<h1>登录</h1>
	<form action="admin_login" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>用户名:</td>
				<td><input type="text" id="name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>密码:</td>
				<td><input type="password" id="password" name="password"
						style="width:180px;"></input>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="登录" />
				 ${requestScope.login }</td>
			</tr>
		</table>
	</form>
</center>
<script type="text/javascript">

</script>

package com.guang.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.mode.GAdmin;
import com.guang.web.mode.GPermission;
import com.guang.web.service.GAdminService;
import com.guang.web.service.GPermissionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GAdminAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Resource private GAdminService adminService;
	@Resource private GPermissionService permissionService;
	
	public String list() {

		List<GAdmin> list = adminService.findAll().getList();
		for(GAdmin u : list)
		{
			u.setPermission(permissionService.find(u.getPermissionsId()));
		}
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "admin");
		return "index";
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String login()
	{
		String name = ServletActionContext.getRequest().getParameter("name");
		String password = ServletActionContext.getRequest().getParameter("password");
		
		if(name != null && !"".equals(name) && password != null && !"".equals(password))
		{
			GAdmin admin = adminService.find(name);
			if(admin != null && admin.getPassword().equals(password))
			{
				GPermission permission = permissionService.find(admin.getPermissionsId());
				admin.setPermission(permission);
				ActionContext.getContext().getSession().put("admin", admin);
				ActionContext.getContext().put("login","登录成功！");
				return "index";
			}
		}
		ActionContext.getContext().put("login","用户名或密码不正确！");
		return "index";
	}
	
	public String loginout()
	{
		ActionContext.getContext().getSession().put("admin", null);
		return "index";
	}
	
	public String addUser()
	{
		GAdmin admin = (GAdmin) ActionContext.getContext().getSession().get("admin");
		if(!admin.getPermission().isAddUser())
		{
			ActionContext.getContext().put("addUser","没有权限！");
			list();
			return "index";
		}
		String name = ServletActionContext.getRequest().getParameter("name");
		String password = ServletActionContext.getRequest().getParameter("password");
		
		String addUser = ServletActionContext.getRequest().getParameter("addUser");
		String deleteUser = ServletActionContext.getRequest().getParameter("deleteUser");
		String updateUser = ServletActionContext.getRequest().getParameter("updateUser");
		String model_admin = ServletActionContext.getRequest().getParameter("model_admin");
		
		if(name != null && !"".equals(name) && password != null && !"".equals(password))
		{
			GPermission permission = new GPermission();		
			permission.setAddUser(addUser!=null);
			permission.setDeleteUser(deleteUser!=null);
			permission.setUpdateUser(updateUser!=null);
			permission.setModel_admin(model_admin!=null);
			permissionService.add(permission);
			
			adminService.add(new GAdmin(permission.getId(),name, password));
			ActionContext.getContext().put("addUser","用户添加成功！");
			ActionContext.getContext().put("pages", "admin");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("addUser","用户添加失败！");
		ActionContext.getContext().put("pages", "admin");
		return "index";
	}
	
	public String deleteUser()
	{
		GAdmin user = (GAdmin) ActionContext.getContext().getSession().get("admin");
		if(!user.getPermission().isDeleteUser())
		{
			ActionContext.getContext().put("deleteUser","没有权限！");
			list();
			return "index";
		}
		
		String data = ServletActionContext.getRequest().getParameter("data");
		if(data != null && !"".equals(data))
		{
			GAdmin u = adminService.find(Long.parseLong(data));
			adminService.delete(u.getId());
			permissionService.delete(u.getPermissionsId());
			ActionContext.getContext().put("deleteUser","用户删除成功！");
		}
		list();
		return "index";
	}
	
	public void findUser()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		if(data != null && !"".equals(data))
		{
			GAdmin u = adminService.find(Long.parseLong(data));
			GPermission permission = permissionService.find(u.getPermissionsId());
			u.setPermission(permission);
			print(JSONObject.fromObject(u).toString());
		}		
	}
	
	public String updateUser()
	{
		GAdmin admin = (GAdmin) ActionContext.getContext().getSession().get("admin");
		if(!admin.getPermission().isDeleteUser())
		{
			ActionContext.getContext().put("updateUser","没有权限！");
			list();
			return "index";
		}
		
		String name = ServletActionContext.getRequest().getParameter("name");
		String password = ServletActionContext.getRequest().getParameter("password");
		
		String addUser = ServletActionContext.getRequest().getParameter("addUser");
		String deleteUser = ServletActionContext.getRequest().getParameter("deleteUser");
		String updateUser = ServletActionContext.getRequest().getParameter("updateUser");
		String model_admin = ServletActionContext.getRequest().getParameter("model_admin");
		
		if(name != null && !"".equals(name) && password != null && !"".equals(password))
		{
			GAdmin u = adminService.find(name);
			u.setPassword(password);
			
			GPermission permission = permissionService.find(u.getPermissionsId());		
			permission.setAddUser(addUser!=null);
			permission.setDeleteUser(deleteUser!=null);
			permission.setUpdateUser(updateUser!=null);
			permission.setModel_admin(model_admin!=null);
			permissionService.update(permission);
			
			adminService.update(u);
			
			ActionContext.getContext().put("updateUser","用户更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateUser","用户更改失败！");
		return "index";
	}

	public void init()
	{
		GPermission permission = new GPermission();
		permission.setAddUser(true);
		permission.setDeleteUser(true);
		permission.setUpdateUser(true);
		permission.setModel_admin(true);
		permissionService.add(permission);
		
		adminService.add(new GAdmin(permission.getId(),"admin", "qiqiup"));
	}
}

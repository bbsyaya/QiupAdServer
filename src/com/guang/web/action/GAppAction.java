package com.guang.web.action;


import java.util.List;

import javax.annotation.Resource;


import org.apache.struts2.ServletActionContext;




import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GApp;
import com.guang.web.service.GAppService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class GAppAction extends ActionSupport{

	private static final long serialVersionUID = -6570772391551890119L;
	@Resource private GAppService appService;
	
	public String list()
	{
		QueryResult<GApp>  qr = appService.findApps(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GApp> appList = appService.findApps(start).getList();
		
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", appList);
		ActionContext.getContext().put("pages", "app");
		
		return "index";
	}
		
	//删除app
	public void deleteApp()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			appService.delete(Long.parseLong(id));
		}
	}
	
	
}

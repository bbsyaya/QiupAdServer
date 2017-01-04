package com.guang.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdPosition;
import com.guang.web.service.GAdPositionService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GAdPositionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource private GAdPositionService adPositionService;
	
	public String list()
	{
		QueryResult<GAdPosition>  qr = adPositionService.findAlls(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GAdPosition> list = adPositionService.findAlls(start).getList();
			
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "adPosition");
		
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
	
	public String addAdPosition()
	{
		String name = ServletActionContext.getRequest().getParameter("name");
		String type = ServletActionContext.getRequest().getParameter("type");
		String open_state = ServletActionContext.getRequest().getParameter("open_state");
		if(!StringTools.isEmpty(name) && !StringTools.isEmpty(type))
		{
			boolean open = false;
			if("1".equals(open_state))
				open = true;
			
			adPositionService.add(new GAdPosition(Integer.parseInt(type),name,open));
			ActionContext.getContext().put("addAdPosition", "添加成功！");
		}
		else
		{
			ActionContext.getContext().put("addAdPosition", "添加失败！");
		}
		return list();
	}
	
	public void deleteAdPosition()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			adPositionService.delete(Long.parseLong(id));
		}
	}
	
	public String updateAdPosition()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String name = ServletActionContext.getRequest().getParameter("name");
		String type = ServletActionContext.getRequest().getParameter("type");
		String open_state = ServletActionContext.getRequest().getParameter("open_state");
		
		if(!StringTools.isEmpty(id) && !StringTools.isEmpty(name) && !StringTools.isEmpty(type))
		{
			boolean open = false;
			if("1".equals(open_state))
				open = true;
			
			GAdPosition adPosition = adPositionService.find(Long.parseLong(id));
			adPosition.setName(name);
			adPosition.setType(Integer.parseInt(type));
			adPosition.setOpen(open);
			
			adPositionService.update(adPosition);
			ActionContext.getContext().put("updateAdPosition", "更改成功！");
		}
		else
		{
			ActionContext.getContext().put("updateAdPosition", "更改失败！");
		}
		return list();
	}
	
	public void findAdPosition()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			GAdPosition adPosition = adPositionService.find(Long.parseLong(id));
			print(JSONObject.fromObject(adPosition).toString());
		}
	}
}

package com.guang.web.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.common.GStatisticsType;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GStatistics;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GStatisticsService;
import com.guang.web.service.GUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GStatisticsService statisticsService;
	@Resource private GAdPositionService adPositionService;
	@Resource private GUserService userService;
	
	public String list()
	{
		QueryResult<GStatistics>  qr = statisticsService.findAlls(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GStatistics> list = statisticsService.findAlls(start).getList();
		
		for(GStatistics statistics : list)
		{
			statistics.setStatisticsType(GStatisticsType.Types[statistics.getType()]);
			statistics.setAdPosition(adPositionService.find(statistics.getAdPositionType()).getName());
		}
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "statistics");
		
		return "index";
	}
		
	//删除statistics
	public void deleteStatistics()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			statisticsService.delete(Long.parseLong(id));
		}
	}
	
	//uploadStatistics
	public synchronized void uploadStatistics()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		JSONObject obj = JSONObject.fromObject(data); 
		int type = obj.getInt("type");
		int adPositionType = obj.getInt("adPositionType");
		long offerId = obj.getLong("offerId");
		String packageName = obj.getString("packageName");
		String appName = obj.getString("appName");
		String userName = obj.getString("userName");
		String password = packageName;
		long userId = userService.find(userName,password).getId();
		
		GStatistics statistics = new GStatistics(type, userId, adPositionType, offerId, packageName, appName);
		statisticsService.add(statistics);
	}
}

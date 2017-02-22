package com.guang.web.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.common.GStatisticsType;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdPosition;
import com.guang.web.mode.GMedia;
import com.guang.web.mode.GSdk;
import com.guang.web.mode.GStatistics;
import com.guang.web.mode.GUser;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GMediaService;
import com.guang.web.service.GSdkService;
import com.guang.web.service.GStatisticsService;
import com.guang.web.service.GUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GStatisticsService statisticsService;
	@Resource private GAdPositionService adPositionService;
	@Resource private GUserService userService;
	@Resource private GMediaService mediaService;
	@Resource private GSdkService sdkService;
	
	public String list()
	{
		QueryResult<GStatistics>  qr = statisticsService.findAlls(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
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
		String offerId = obj.getString("offerId");
		String packageName = obj.getString("packageName");
		String appName = obj.getString("appName");
		String userName = obj.getString("userName");
		String password = packageName;
		GUser user = userService.find(userName,password);
		long userId = user.getId();
		String channel = user.getChannel();
		
		GStatistics statistics = new GStatistics(type, userId, adPositionType, offerId, packageName, appName,channel);
		statisticsService.add(statistics);
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findAdPosition()
	{
		List<GAdPosition> list = adPositionService.findAlls().getList();
		print(JSONArray.fromObject(list));
	}
	
	public void findMedia()
	{
		List<GMedia> list = mediaService.findAlls().getList();
		print(JSONArray.fromObject(list));
	}
	
	public void findSdk()
	{
		List<GSdk> list = sdkService.findAlls().getList();
		print(JSONArray.fromObject(list));
	}
	
	public void list2()
	{
		String from = ServletActionContext.getRequest().getParameter("from");
		String to = ServletActionContext.getRequest().getParameter("to");
		String type1 = ServletActionContext.getRequest().getParameter("type1");
		String type2 = ServletActionContext.getRequest().getParameter("type2");
		String type3 = ServletActionContext.getRequest().getParameter("type3");
		String type4 = ServletActionContext.getRequest().getParameter("type4");
		String type5 = ServletActionContext.getRequest().getParameter("type5");
				
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		
		
		if(!"-1".equals(type1))
		{
			colvals.put("type =", "'"+type1+"'");
		}
		if(!"-1".equals(type2))
		{
			colvals.put("adPositionType =", "'"+type2+"'");
		}
		if(!"-1".equals(type3))
		{
			colvals.put("offerId =", "'"+type3+"'");
		}
		if(!"-1".equals(type4))
		{
			colvals.put("appName =", "'"+type4+"'");
		}
		if(!"-1".equals(type5))
		{
			colvals.put("channel =", "'"+type5+"'");
		}
		colvals.put("uploadTime >=", "'"+from+"'");
		colvals.put("uploadTime <", "'"+to+"'");
		
		List<GStatistics> list = statisticsService.findAlls(colvals).getList();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(GStatistics statistics : list)
		{
			statistics.setStatisticsType(GStatisticsType.Types[statistics.getType()]);
			statistics.setAdPosition(adPositionService.find(statistics.getAdPositionType()).getName());
			statistics.setUploadTime2(formatter.format(statistics.getUploadTime()));
		}
		print(JSONArray.fromObject(list));
	}
}

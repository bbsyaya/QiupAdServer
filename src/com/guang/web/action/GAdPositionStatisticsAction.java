package com.guang.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.common.GStatisticsType;
import com.guang.web.mode.GAdPosition;
import com.guang.web.mode.GAdPositionStatistics;
import com.guang.web.mode.GStatistics;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GFStatisticsService;
import com.guang.web.service.GMediaService;
import com.guang.web.service.GSdkService;
import com.guang.web.service.GUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GAdPositionStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GFStatisticsService statisticsService;	
	@Resource private GUserService userService;
	@Resource private GAdPositionService adPositionService;
	@Resource private GMediaService mediaService;
	@Resource private GSdkService sdkService;
	
	@SuppressWarnings("deprecation")
	public String list() 
	{		
		List<GAdPositionStatistics> slist = new ArrayList<GAdPositionStatistics>();
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		
		Date date = new Date();	
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		String from = date.toLocaleString();
		date.setDate(date.getDate()+1);
		String to = date.toLocaleString();
		
		
//		colvals.put("uploadTime between", "'"+from+"'" + " and " + "'"+to+"'");
		colvals.put("type =", GStatisticsType.REQUEST + "");
		long requestNum = statisticsService.findNum(colvals,null,null); 

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.SHOW + "");
		long showNum = statisticsService.findNum(colvals,null,null); 

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.CLICK + "");
		long clickNum = statisticsService.findNum(colvals,null,null); 

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOWNLOAD + "");
		long downloadNum = statisticsService.findNum(colvals,null,null); 

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOWNLOAD_SUCCESS + "");
		long downloadSuccessNum = statisticsService.findNum(colvals,null,null); 

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.INSTALL + "");
		long installNum = statisticsService.findNum(colvals,null,null); 

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.ACTIVATE + "");
		long activateNum = statisticsService.findNum(colvals,null,null); 
		

		float income = activateNum;
		
		colvals.remove("type =");
		colvals.put("type !=", GStatisticsType.LOGIN + "");
		long adActiveUserNum = statisticsService.findNum2(colvals,null,null); 
		
//		colvals.remove("uploadTime between");
		colvals.remove("type !=");
		colvals.put("createdDate between", "'"+from+"'" + " and " + "'"+to+"'");
		long newAddUserNum = userService.findNum(colvals);
		
		colvals.remove("createdDate between");
//		colvals.put("updatedDate between", "'"+from+"'" + " and " + "'"+to+"'");
		colvals.put("type =", GStatisticsType.LOGIN + "");
		long activeUserNum = statisticsService.findNum2(colvals,null,null); 
		
		
		GAdPositionStatistics adPositionStatistics = new GAdPositionStatistics(requestNum, showNum, clickNum, downloadNum, downloadSuccessNum, installNum, 
				 activateNum, income, newAddUserNum, activeUserNum, adActiveUserNum);
		slist.add(adPositionStatistics);
		
		List<GAdPosition> adPositions = adPositionService.findAlls().getList();
		
		List<GAdPosition> adPositionTypes = new ArrayList<GAdPosition>();
		adPositionTypes.addAll(adPositions);
		
		for(int i =  0; i < adPositionTypes.size() - 1;i ++)
		{
			  for(int j = adPositionTypes.size() -  1;j > i;j --)
			  {
			     if ((int)(adPositionTypes.get(j).getType()) == (int)(adPositionTypes.get(i).getType()))
			     {
			    	 adPositionTypes.remove(j);
			     }
			  } 
		} 
	
		ActionContext.getContext().put("list", slist);
		ActionContext.getContext().put("adPositions", adPositions);	
		ActionContext.getContext().put("adPositionTypes", adPositionTypes);
		ActionContext.getContext().put("medias", sdkService.findAlls().getList());
		ActionContext.getContext().put("sdks", sdkService.findAlls().getList());
		ActionContext.getContext().put("pages", "adPositionStatistics");
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
	
	
	public void list2() 
	{		
		String from = ServletActionContext.getRequest().getParameter("from");
		String to = ServletActionContext.getRequest().getParameter("to");
		String adPositionId = ServletActionContext.getRequest().getParameter("adPositionId");
		String adPositionType = ServletActionContext.getRequest().getParameter("adPositionType");
		String media = ServletActionContext.getRequest().getParameter("media");
		String channel = ServletActionContext.getRequest().getParameter("channel");
		
		List<GAdPositionStatistics> slist = new ArrayList<GAdPositionStatistics>();
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		
		if(adPositionId != null && !"0".equals(adPositionId))
			colvals.put("adPositionId =", adPositionId);
		else
		{
			if(adPositionType != null && !"0".equals(adPositionType))
				colvals.put("adPositionType =", adPositionType);
		}
		
		if(media != null && !"0".equals(media))
			colvals.put("packageName =", "'"+media+"'");
		if(channel != null && !"0".equals(channel))
			colvals.put("channel =", "'"+channel+"'");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date f = null;
		Date t = null;
		try {
			f = formatter.parse(from);
			t = formatter.parse(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		colvals.put("uploadTime between", "'"+from+"'" + " and " + "'"+to+"'");
		colvals.put("type =", GStatisticsType.REQUEST + "");
		long requestNum = statisticsService.findNum(colvals,new Date(f.getTime()),new Date(t.getTime()));

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.SHOW + "");
		long showNum = statisticsService.findNum(colvals,new Date(f.getTime()),new Date(t.getTime()));

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.CLICK + "");
		long clickNum = statisticsService.findNum(colvals,new Date(f.getTime()),new Date(t.getTime()));

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOWNLOAD + "");
		long downloadNum =  statisticsService.findNum(colvals,new Date(f.getTime()),new Date(t.getTime()));

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOWNLOAD_SUCCESS + "");
		long downloadSuccessNum = statisticsService.findNum(colvals,new Date(f.getTime()),new Date(t.getTime()));

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.INSTALL + "");
		long installNum = statisticsService.findNum(colvals,new Date(f.getTime()),new Date(t.getTime()));

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.ACTIVATE + "");
		long activateNum = statisticsService.findNum(colvals,new Date(f.getTime()),new Date(t.getTime()));
		
		
		float income = activateNum;
		
		colvals.remove("type =");
		colvals.put("type !=", GStatisticsType.LOGIN + "");
		long adActiveUserNum = statisticsService.findNum2(colvals,new Date(f.getTime()),new Date(t.getTime()));
		
		colvals.remove("adPositionId =");
		colvals.remove("adPositionType =");
		colvals.remove("packageName =");
		colvals.remove("uploadTime between");
		colvals.remove("channel =");
		colvals.remove("type !=");
		
		colvals.put("createdDate between", "'"+from+"'" + " and " + "'"+to+"'");
		if(channel != null && !"0".equals(channel))
			colvals.put("channel =", "'"+channel+"'");
		long newAddUserNum = userService.findNum(colvals);
				
		colvals.remove("createdDate between");
//		colvals.put("updatedDate between", "'"+from+"'" + " and " + "'"+to+"'");

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.LOGIN + "");
		long activeUserNum = statisticsService.findNum2(colvals,new Date(f.getTime()),new Date(t.getTime()));
				
		
		GAdPositionStatistics adPositionStatistics = new GAdPositionStatistics(requestNum, showNum, clickNum, downloadNum, downloadSuccessNum, installNum, 
				 activateNum, income, newAddUserNum, activeUserNum, adActiveUserNum);
		slist.add(adPositionStatistics);
				
		print(JSONArray.fromObject(slist).toString());
	}
	
	public void println(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

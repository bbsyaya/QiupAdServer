package com.guang.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.guang.web.common.GStatisticsType;
import com.guang.web.mode.GAdPositionStatistics;
import com.guang.web.mode.GStatistics;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GMediaService;
import com.guang.web.service.GSdkService;
import com.guang.web.service.GStatisticsService;
import com.guang.web.service.GUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GAdPositionStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GStatisticsService statisticsService;	
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
		
		colvals.put("uploadTime >=", "'"+from+"'");
		colvals.put("uploadTime <", "'"+to+"'");
		colvals.put("type =", GStatisticsType.REQUEST + "");
		long requestNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.SHOW + "");
		long showNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.CLICK + "");
		long clickNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOWNLOAD + "");
		long downloadNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOWNLOAD_SUCCESS + "");
		long downloadSuccessNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.INSTALL + "");
		long installNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.ACTIVATE + "");
		long activateNum = statisticsService.findAlls(colvals).getNum();
		
		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOUBLE_SHOW + "");
		showNum += statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOUBLE_CLICK + "");
		clickNum += statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOUBLE_DOWNLOAD + "");
		downloadNum += statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOUBLE_DOWNLOAD_SUCCESS + "");
		downloadSuccessNum += statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOUBLE_INSTALL + "");
		installNum += statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOUBLE_ACTIVATE + "");
		activateNum += statisticsService.findAlls(colvals).getNum();

		float income = activateNum;
		
		colvals.remove("type =");
		List<GStatistics> stalist = statisticsService.findAlls(colvals).getList();
		//移除重复数据
		for ( int i = 0 ; i < stalist.size() - 1 ; i ++ ) 
		{  
		     for ( int j = stalist.size() - 1 ; j > i; j -- ) 
		     {  
		       if (stalist.get(j).getUserId().equals(stalist.get(i).getUserId()))
		       {  
		    	   stalist.remove(j); 
		       }
		     }   
		}   
		long adActiveUserNum = stalist.size();
		
		colvals.remove("uploadTime >=");
		colvals.remove("uploadTime <");
		
		colvals.put("createdDate >=", "'"+from+"'");
		colvals.put("createdDate <", "'"+to+"'");
		long newAddUserNum = userService.find(colvals).getNum();
		
		colvals.remove("createdDate >=");
		colvals.remove("createdDate <");
		colvals.put("updatedDate >=", "'"+from+"'");
		colvals.put("updatedDate <", "'"+to+"'");
		long activeUserNum = userService.find(colvals).getNum();
		
		
		GAdPositionStatistics adPositionStatistics = new GAdPositionStatistics(requestNum, showNum, clickNum, downloadNum, downloadSuccessNum, installNum, 
				 activateNum, income, newAddUserNum, activeUserNum, adActiveUserNum);
		slist.add(adPositionStatistics);
		ActionContext.getContext().put("list", slist);
		ActionContext.getContext().put("adPositions", adPositionService.findAlls().getList());	
		ActionContext.getContext().put("medias", mediaService.findAlls().getList());
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
		String adPositionType = ServletActionContext.getRequest().getParameter("adPositionType");
		String doubleSta = ServletActionContext.getRequest().getParameter("doubleSta");
		String media = ServletActionContext.getRequest().getParameter("media");
		String channel = ServletActionContext.getRequest().getParameter("channel");
		
		List<GAdPositionStatistics> slist = new ArrayList<GAdPositionStatistics>();
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		
		if(!"0".equals(adPositionType))
			colvals.put("adPositionType =", adPositionType);
		if(!"0".equals(media))
			colvals.put("packageName =", "'"+media+"'");
		if(!"0".equals(channel))
			colvals.put("channel =", "'"+channel+"'");
		
		colvals.put("uploadTime >=", "'"+from+"'");
		colvals.put("uploadTime <", "'"+to+"'");
		colvals.put("type =", GStatisticsType.REQUEST + "");
		long requestNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.SHOW + "");
		long showNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.CLICK + "");
		long clickNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOWNLOAD + "");
		long downloadNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.DOWNLOAD_SUCCESS + "");
		long downloadSuccessNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.INSTALL + "");
		long installNum = statisticsService.findAlls(colvals).getNum();

		colvals.remove("type =");
		colvals.put("type =", GStatisticsType.ACTIVATE + "");
		long activateNum = statisticsService.findAlls(colvals).getNum();
		
		if(doubleSta != null && "1".equals(doubleSta))
		{
			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_SHOW + "");
			showNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_CLICK + "");
			clickNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_DOWNLOAD + "");
			downloadNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_DOWNLOAD_SUCCESS + "");
			downloadSuccessNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_INSTALL + "");
			installNum += statisticsService.findAlls(colvals).getNum();

			colvals.remove("type =");
			colvals.put("type =", GStatisticsType.DOUBLE_ACTIVATE + "");
			activateNum += statisticsService.findAlls(colvals).getNum();
		}

		float income = activateNum;
		
		colvals.remove("type =");
		List<GStatistics> stalist = statisticsService.findAlls(colvals).getList();
		//移除重复数据
		for ( int i = 0 ; i < stalist.size() - 1 ; i ++ ) 
		{  
		     for ( int j = stalist.size() - 1 ; j > i; j -- ) 
		     {  
		       if (stalist.get(j).getUserId().equals(stalist.get(i).getUserId()))
		       {  
		    	   stalist.remove(j); 
		       }
		     }   
		}   
		long adActiveUserNum = stalist.size();
		
		colvals.remove("adPositionType =");
		colvals.remove("packageName =");
		colvals.remove("uploadTime >=");
		colvals.remove("uploadTime <");
		colvals.remove("channel =");
		
		colvals.put("createdDate >=", "'"+from+"'");
		colvals.put("createdDate <", "'"+to+"'");
		if(!"0".equals(channel))
			colvals.put("channel =", "'"+channel+"'");
		long newAddUserNum = userService.find(colvals).getNum();
				
		colvals.remove("createdDate >=");
		colvals.remove("createdDate <");
		colvals.put("updatedDate >=", "'"+from+"'");
		colvals.put("updatedDate <", "'"+to+"'");
		if(!"0".equals(channel))
			colvals.put("channel =", "'"+channel+"'");
		long activeUserNum = userService.find(colvals).getNum();
				
		
		GAdPositionStatistics adPositionStatistics = new GAdPositionStatistics(requestNum, showNum, clickNum, downloadNum, downloadSuccessNum, installNum, 
				 activateNum, income, newAddUserNum, activeUserNum, adActiveUserNum);
		slist.add(adPositionStatistics);
				
		print(JSONArray.fromObject(slist).toString());
	}
}

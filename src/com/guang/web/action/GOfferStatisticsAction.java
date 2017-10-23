package com.guang.web.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.guang.web.common.GStatisticsType;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GOfferStatistics;
import com.guang.web.mode.GStatistics;
import com.guang.web.mode.GUser;
import com.guang.web.service.GFStatisticsService;
import com.guang.web.service.GOfferService;
import com.guang.web.service.GOfferStatisticsService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GOfferStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GOfferStatisticsService offerStatisticsService;
	@Resource private GOfferService offerService;
	@Resource private GFStatisticsService statisticsService;	
	@Resource private  GUserService userService;
	
	public String list() 
	{		
		QueryResult<GOfferStatistics>  qr = offerStatisticsService.findAll(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GOfferStatistics> list = offerStatisticsService.findAll(start).getList();
		
	
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("offers", offerService.findAlls().getList());	
		ActionContext.getContext().put("pages", "offerStatistics");
		return "index";
	}
	
	public void list2()
	{
		String from = ServletActionContext.getRequest().getParameter("from");
		String to = ServletActionContext.getRequest().getParameter("to");
		String offer = ServletActionContext.getRequest().getParameter("offer");
		
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		if(from != null && to != null)
		{
			colvals.put("stime between", "'"+from+"'" + " and " + "'"+to+"'");
		}
		if(offer != null && !"0".equals(offer))
		{
			colvals.put("offerId =", offer);
		}
		
		
		List<GOfferStatistics> list = offerStatisticsService.findAll(colvals,0).getList();
	   
		print(JSONArray.fromObject(list));
	}
	
	
	public void find1()
	{
		String num = ServletActionContext.getRequest().getParameter("num");
		if(!StringTools.isEmpty(num))
		{
			List<GUser> list = userService.findAlls(0,Integer.parseInt(num)).getList();
			int open = 0;
			int close = 0;
			int non = 0;
			
			for(GUser u : list)
			{
				if(u.getOpenInstall() == null)
					non += 1;
				else if(u.getOpenInstall() == 1)
					open += 1;
				else if(u.getOpenInstall() == 0)
					close += 1;
				else
					non += 1;
			}
			println("find size:"+list.size());
			println("open:"+open);
			println("close:"+close);
			println("non:"+non);
		}
		
	}
	
	public void findData()
	{
		String from = ServletActionContext.getRequest().getParameter("from");
		String to = ServletActionContext.getRequest().getParameter("to");
		
		if(!StringTools.isEmpty(from) && !StringTools.isEmpty(to))
		{
			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
//			colvals.put("uploadTime between", "'"+from+"'" + " and " + "'"+to+"'");
			colvals.put("type =", GStatisticsType.INSTALL_UI_TIME + "");
			colvals.put("installTime =", 1 + "");
			
			List<GStatistics> list = statisticsService.findAlls(colvals);
			
			println("find size:"+list.size());
			
			for(GStatistics sta : list)
			{
				GUser user = userService.find(sta.getUserId());
				println("user:"+user.getId() +"  model:"+user.getModel());
			}
		}
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

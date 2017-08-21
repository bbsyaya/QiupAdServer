package com.guang.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.guang.web.mode.GPOffer;
import com.guang.web.service.GFStatisticsService;
import com.guang.web.service.GPOfferService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GPOfferStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GFStatisticsService statisticsService;
	@Resource private GPOfferService offerService;
	
	public String list()
	{		
		List<GPOffer> list = offerService.findAlls().getList();
		
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		
		Date from = new Date();
		Date to = new Date();
		to.setDate(to.getDate()+1);
		
		for(GPOffer offer : list)
		{
			colvals.clear();
			colvals.put("offerId =", "'" + offer.getId() + "'");
			long num = statisticsService.findNum(colvals,from,to); 
			offer.setShowNum(num);
		}

		ActionContext.getContext().put("maxNum", list.size());
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "gpstatistics");
		
		return "index";
	}

	
	public void list2()
	{
		String from = ServletActionContext.getRequest().getParameter("from");
		String to = ServletActionContext.getRequest().getParameter("to");

		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		
		List<GPOffer> list = offerService.findAlls().getList();
		
		if(!StringTools.isEmpty(from) && !StringTools.isEmpty(to))
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			for(GPOffer offer : list)
			{
				colvals.clear();
				colvals.put("offerId =", "'" + offer.getId() + "'");
				long num = 0;
				try {
					num = statisticsService.findNum(colvals,formatter.parse(from),formatter.parse(to));
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				offer.setShowNum(num);
			}
		}
		
		
		print(JSONArray.fromObject(list));
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

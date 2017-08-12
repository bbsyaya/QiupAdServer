package com.guang.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GArea;
import com.guang.web.mode.GPOffer;
import com.guang.web.service.GAreaService;
import com.guang.web.service.GPOfferService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GPOfferAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource private GPOfferService offerService;
	@Resource private GAreaService areaService;
	
	private GPOffer offer;


	public String list() {

		QueryResult<GPOffer>  qr = offerService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GPOffer> offerList = offerService.findAlls(start).getList();
		
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", offerList);
		ActionContext.getContext().put("countrys", areaService.findAllCountry().getList());
		ActionContext.getContext().put("pages", "offer");
		
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
	
	public void findArea()
	{
		List<GArea> list = areaService.findAllCountry().getList();
		print(JSONArray.fromObject(list).toString());
	}

	public String addOffer()
	{
		if(offer == null || StringTools.isEmpty(offer.getName()))
		{
			ActionContext.getContext().put("addOffer", "添加失败！");
			list();
			return "index";
		}
			
		//国家
		String countrys = "";
		int countryNum = areaService.findAllCountry().getList().size();
		for(int i=0;i<countryNum;i++)
		{
			String p = ServletActionContext.getRequest().getParameter("countrys_"+i);
			if(p != null)
				countrys = countrys + p + ",";
		}
		if(countrys.endsWith(","))
			countrys = countrys.substring(0, countrys.length()-1);

		offer.setCountrys(countrys);
		offer.setUpdatedDate(new Date());
		
		offerService.add(offer);
		ActionContext.getContext().put("addOffer", "添加成功！");
		
		list();
		return "index";
	}

	//删除offer
	public void deleteOffer()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			offerService.delete(Long.parseLong(id));
		}
	}

	public void findOffer()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			GPOffer offer = offerService.find(Long.parseLong(id));
			print(JSONObject.fromObject(offer).toString());
		}
	}
	
	public String updateOffer()
	{
		if(offer != null)
		{
			//国家
			String countrys = "";
			int countryNum = areaService.findAllCountry().getList().size();
			for(int i=0;i<countryNum;i++)
			{
				String p = ServletActionContext.getRequest().getParameter("countrys_"+i);
				if(p != null)
					countrys = countrys + p + ",";
			}
			if(countrys.endsWith(","))
				countrys = countrys.substring(0, countrys.length()-1);
			
			offer.setCountrys(countrys);
			offer.setUpdatedDate(new Date());
			
			offerService.update(offer);
			
			ActionContext.getContext().put("updateOffer","更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateOffer","更改失败！");
		return "index";
	}


	
	
	public GPOffer getOffer() {
		return offer;
	}

	public void setOffer(GPOffer offer) {
		this.offer = offer;
	}

	
	
}

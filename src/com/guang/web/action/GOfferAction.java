package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GArea;
import com.guang.web.mode.GOffer;
import com.guang.web.service.GAreaService;
import com.guang.web.service.GOfferService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GOfferAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource private GOfferService offerService;
	@Resource private GAreaService areaService;
	
	private File pic;
	private String picFileName;
	private File icon;
	private String iconFileName;
	private GOffer offer;
	
	
	
	public String list() {

		QueryResult<GOffer>  qr = offerService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GOffer> offerList = offerService.findAlls(start).getList();
		
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", offerList);
		ActionContext.getContext().put("areas", areaService.findAllProvince().getList());
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
		List<GArea> list = areaService.findAllProvince().getList();
		print(JSONArray.fromObject(list).toString());
	}
	
	public String addOffer()
	{
		if(pic == null || icon == null || offer == null || StringTools.isEmpty(offer.getAppName()))
		{
			ActionContext.getContext().put("addOffer", "添加失败！");
			list();
			return "index";
		}
			
		String pic_relpath = ServletActionContext.getServletContext().getRealPath("offer/pic/");
		String icon_relpath = ServletActionContext.getServletContext().getRealPath("offer/icon/");
		try {
			//上传pic		
			File file = new File(new File(pic_relpath), picFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(pic, file);
			//上传icon
			file = new File(new File(icon_relpath), iconFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(icon, file);
			
			
			String picPath = "offer/pic/" + picFileName;
			String iconPath = "offer/icon/" + iconFileName;
			
			//省份/国家
			String province = "";
			int provinceNum = areaService.findAllProvince().getList().size();
			for(int i=0;i<provinceNum;i++)
			{
				String p = ServletActionContext.getRequest().getParameter("areas_"+i);
				if(p != null)
					province = province + p + ",";
			}
			if(province.endsWith(","))
				province = province.substring(0, province.length()-1);

			offer.setPicPath(picPath);
			offer.setIconPath(iconPath);
			offer.setAreas(province);
			offer.setUpdatedDate(new Date());
			
			offerService.add(offer);
			ActionContext.getContext().put("addOffer", "添加成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("addOffer", "添加失败！");
		}
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
			GOffer offer = offerService.find(Long.parseLong(id));
			print(JSONObject.fromObject(offer).toString());
		}
	}
	
	public String updateOffer()
	{
		if(offer != null)
		{
			if(pic != null)
			{
				String pic_relpath = ServletActionContext.getServletContext().getRealPath("offer/pic/");
				try {
					//上传pic		
					File file = new File(new File(pic_relpath), picFileName);
					if (!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					FileUtils.copyFile(pic, file);
					
					String picPath = "offer/pic/" + picFileName;
					offer.setPicPath(picPath);
				} catch (Exception e) {
				}
			}
			else
			{
				GOffer offer2 = offerService.find(offer.getId());
				offer.setPicPath(offer2.getPicPath());
			}
			
			if(icon != null)
			{
				String icon_relpath = ServletActionContext.getServletContext().getRealPath("offer/icon/");
				try {
					//上传icon
					File file = new File(new File(icon_relpath), iconFileName);
					if (!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					FileUtils.copyFile(icon, file);
					
					String iconPath = "offer/icon/" + iconFileName;
					offer.setIconPath(iconPath);
				} catch (Exception e) {
				}
			}
			else
			{
				GOffer offer2 = offerService.find(offer.getId());
				offer.setIconPath(offer2.getIconPath());
			}
			
			//省份/国家
			String province = "";
			int provinceNum = areaService.findAllProvince().getList().size();
			for(int i=0;i<provinceNum;i++)
			{
				String p = ServletActionContext.getRequest().getParameter("areas_"+i);
				if(p != null)
					province = province + p + ",";
			}
			if(province.endsWith(","))
				province = province.substring(0, province.length()-1);
			
			offer.setAreas(province);
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
	
	
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	public File getIcon() {
		return icon;
	}
	public void setIcon(File icon) {
		this.icon = icon;
	}
	public String getIconFileName() {
		return iconFileName;
	}
	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}
	public GOffer getOffer() {
		return offer;
	}
	public void setOffer(GOffer offer) {
		this.offer = offer;
	}
	
	
	
}

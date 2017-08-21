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
import com.guang.web.mode.GAdPosition;
import com.guang.web.mode.GArea;
import com.guang.web.mode.GOffer;
import com.guang.web.mode.GSdk;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GAreaService;
import com.guang.web.service.GOfferService;
import com.guang.web.service.GSdkService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GOfferAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource private GOfferService offerService;
	@Resource private GAreaService areaService;
	@Resource private GSdkService sdkService;
	@Resource private GAdPositionService adPositionService; 
	
	private File pic;
	private String picFileName;
	private File icon;
	private String iconFileName;
	
	private File pushStatusIcon;
	private String pushStatusIconFileName;
	private File pushNotifyIcon;
	private String pushNotifyIconFileName;
	
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
		
		
		List<GSdk> sdks = sdkService.findAlls().getList();
		for(int i =  0; i < sdks.size() - 1;i ++)
		{
			  for(int j = sdks.size() -  1;j > i;j --)
			  {
			     if (sdks.get(j).getChannel().equals(sdks.get(i).getChannel()))
			     {
			    	 sdks.remove(j);
			     }
			  } 
		} 
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", offerList);
		ActionContext.getContext().put("areas", areaService.findAllProvince().getList());
		ActionContext.getContext().put("adPositions", adPositionService.findAlls().getList());	
		ActionContext.getContext().put("channels", sdks);	
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
		String offer_type = ServletActionContext.getRequest().getParameter("offer_type");
		int type = Integer.parseInt(offer_type);
		
		if(pic == null || offer == null || StringTools.isEmpty(offer.getAppName()))
		{
			ActionContext.getContext().put("addOffer", "添加失败！");
			list();
			System.out.println("-------1-------");
			return "index";
		}
		if(type == 1 && icon == null)
		{
			ActionContext.getContext().put("addOffer", "添加失败！");
			list();
			System.out.println("-------2-------");
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
			if(type == 1)
			{
				file = new File(new File(icon_relpath), iconFileName);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileUtils.copyFile(icon, file);
				
				file = new File(new File(icon_relpath), pushStatusIconFileName);
				FileUtils.copyFile(pushStatusIcon, file);
				
				file = new File(new File(icon_relpath), pushNotifyIconFileName);
				FileUtils.copyFile(pushNotifyIcon, file);
			}
			
			String picPath = "offer/pic/" + picFileName;
			String iconPath = "offer/icon/" + iconFileName;
			String pushStatusIconPath = "offer/icon/" + pushStatusIconFileName;
			String pushNotifyIconPath = "offer/icon/" + pushNotifyIconFileName;
			if(type == 2)
			{
				iconPath = null;
				pushStatusIconPath = null;
				pushNotifyIconPath = null;
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
			
			//广告位
			List<GAdPosition> adPositions = adPositionService.findAlls().getList();
			String adPositionSwitch = "";
			for(GAdPosition adPosition : adPositions)
			{
				String p = ServletActionContext.getRequest().getParameter("adPositionSwitch_"+adPosition.getId());
				if(p != null)
					adPositionSwitch = adPositionSwitch + adPosition.getId() + ",";
			}
			if(adPositionSwitch.endsWith(","))
				adPositionSwitch = adPositionSwitch.substring(0, adPositionSwitch.length()-1);
			
			//渠道
			List<GSdk> channels = sdkService.findAlls().getList();
			String allchannels = "";
			String channelNames = "";
			for(GSdk sdk : channels)
			{
				String p = ServletActionContext.getRequest().getParameter("channel_"+sdk.getId());
				if(p != null)
				{
					allchannels = allchannels + sdk.getId() + ",";
					channelNames = channelNames + sdk.getChannel() + ",";
				}
			}
			if(allchannels.endsWith(","))
				allchannels = allchannels.substring(0, allchannels.length()-1);
			if(channelNames.endsWith(","))
				channelNames = channelNames.substring(0, channelNames.length()-1);
			
			//运行商
			String operators = "";
			for(int i=1;i<=3;i++)
			{
				String p = ServletActionContext.getRequest().getParameter("operator_"+i);
				if(p != null)
				{
					operators = operators + i+ ",";
				}
			}
			if(operators.endsWith(","))
				operators = operators.substring(0, operators.length()-1);

			offer.setType(type);
			offer.setPicPath(picPath);
			offer.setIconPath(iconPath);
			offer.setAreas(province);
			offer.setAdPositions(adPositionSwitch);
			offer.setChannels(allchannels);
			offer.setChannelNames(channelNames);
			offer.setOperators(operators);
			offer.setUpdatedDate(new Date());
			offer.setPushStatusIcon(pushStatusIconPath);
			offer.setPushNotifyIcon(pushNotifyIconPath);
			
			offerService.add(offer);
			ActionContext.getContext().put("addOffer", "添加成功！");
		} catch (Exception e) {
			System.out.println("-------5-------");
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
			GOffer offer2 = offerService.find(offer.getId());
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
				offer.setIconPath(offer2.getIconPath());
			}
			
			if(pushStatusIcon != null)
			{
				String icon_relpath = ServletActionContext.getServletContext().getRealPath("offer/icon/");
				try {
					//上传icon
					File file = new File(new File(icon_relpath), pushStatusIconFileName);
					if (!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					FileUtils.copyFile(icon, file);
					
					String pushStatusIconPath = "offer/icon/" + pushStatusIconFileName;
					offer.setPushStatusIcon(pushStatusIconPath);
				} catch (Exception e) {
				}
			}
			else
			{
				offer.setPushStatusIcon(offer2.getPushStatusIcon());
			}
			
			if(pushNotifyIcon != null)
			{
				String icon_relpath = ServletActionContext.getServletContext().getRealPath("offer/icon/");
				try {
					//上传icon
					File file = new File(new File(icon_relpath), pushNotifyIconFileName);
					if (!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					FileUtils.copyFile(icon, file);
					
					String pushNotifyIconPath = "offer/icon/" + pushNotifyIconFileName;
					offer.setPushNotifyIcon(pushNotifyIconPath);
				} catch (Exception e) {
				}
			}
			else
			{
				offer.setPushNotifyIcon(offer2.getPushNotifyIcon());
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
			
			//广告位
			List<GAdPosition> adPositions = adPositionService.findAlls().getList();
			String adPositionSwitch = "";
			for(GAdPosition adPosition : adPositions)
			{
				String p = ServletActionContext.getRequest().getParameter("adPositionSwitch_"+adPosition.getId());
				if(p != null)
					adPositionSwitch = adPositionSwitch + adPosition.getId() + ",";
			}
			if(adPositionSwitch.endsWith(","))
				adPositionSwitch = adPositionSwitch.substring(0, adPositionSwitch.length()-1);
			
			//渠道
			List<GSdk> channels = sdkService.findAlls().getList();
			String allchannels = "";
			String channelNames = "";
			for(GSdk sdk : channels)
			{
				String p = ServletActionContext.getRequest().getParameter("channel_"+sdk.getId());
				if(p != null)
				{
					allchannels = allchannels + sdk.getId() + ",";
					channelNames = channelNames + sdk.getChannel() + ",";
				}
			}
			if(allchannels.endsWith(","))
				allchannels = allchannels.substring(0, allchannels.length()-1);
			if(channelNames.endsWith(","))
				channelNames = channelNames.substring(0, channelNames.length()-1);
			
			//运行商
			String operators = "";
			for(int i=1;i<=3;i++)
			{
				String p = ServletActionContext.getRequest().getParameter("operator_"+i);
				if(p != null)
				{
					operators = operators + i+ ",";
				}
			}
			if(operators.endsWith(","))
				operators = operators.substring(0, operators.length()-1);
			
			
			offer.setAreas(province);
			offer.setChannels(allchannels);
			offer.setAdPositions(adPositionSwitch);
			offer.setChannelNames(channelNames);
			offer.setOperators(operators);
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
	
	public void getOffers()
	{
		List<GOffer> list = offerService.findAllsByPriority().getList();
		print(JSONArray.fromObject(list).toString());
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

	public File getPushStatusIcon() {
		return pushStatusIcon;
	}

	public void setPushStatusIcon(File pushStatusIcon) {
		this.pushStatusIcon = pushStatusIcon;
	}

	public File getPushNotifyIcon() {
		return pushNotifyIcon;
	}

	public void setPushNotifyIcon(File pushNotifyIcon) {
		this.pushNotifyIcon = pushNotifyIcon;
	}

	public String getPushStatusIconFileName() {
		return pushStatusIconFileName;
	}

	public void setPushStatusIconFileName(String pushStatusIconFileName) {
		this.pushStatusIconFileName = pushStatusIconFileName;
	}

	public String getPushNotifyIconFileName() {
		return pushNotifyIconFileName;
	}

	public void setPushNotifyIconFileName(String pushNotifyIconFileName) {
		this.pushNotifyIconFileName = pushNotifyIconFileName;
	}

	
	
	
	
}

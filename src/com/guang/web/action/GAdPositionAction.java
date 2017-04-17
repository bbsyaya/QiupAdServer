package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdPosition;
import com.guang.web.mode.GAdPositionConfig;
import com.guang.web.service.GAdPositionConfigService;
import com.guang.web.service.GAdPositionService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GAdPositionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource private GAdPositionService adPositionService;
	@Resource private GAdPositionConfigService adPositionConfigService;
	
	private File shortcutIcon;
	private String shortcutIconFileName;
	
	public String list()
	{
		QueryResult<GAdPosition>  qr = adPositionService.findAlls(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
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
		String whiteList = ServletActionContext.getRequest().getParameter("whiteList");
		String showNum = ServletActionContext.getRequest().getParameter("showNum");
		String adShowNum = ServletActionContext.getRequest().getParameter("adShowNum");
		String showTimeInterval = ServletActionContext.getRequest().getParameter("showTimeInterval");
		String timeSlot = ServletActionContext.getRequest().getParameter("timeSlot");
		String browerSpotTwoTime = ServletActionContext.getRequest().getParameter("browerSpotTwoTime");
		String browerSpotFlow = ServletActionContext.getRequest().getParameter("browerSpotFlow");
		String bannerDelyTime = ServletActionContext.getRequest().getParameter("bannerDelyTime");
		String bannerTwoDelyTime = ServletActionContext.getRequest().getParameter("bannerTwoDelyTime");
		String bannerShowTime = ServletActionContext.getRequest().getParameter("bannerShowTime");
		String behindBrushUrls = ServletActionContext.getRequest().getParameter("behindBrushUrls");
		String shortcutName = ServletActionContext.getRequest().getParameter("shortcutName");
		String shortcutUrl = ServletActionContext.getRequest().getParameter("shortcutUrl");
		String browerBreakUrl = ServletActionContext.getRequest().getParameter("browerBreakUrl");
		String appSpotDelyTime = ServletActionContext.getRequest().getParameter("appSpotDelyTime");
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
			
			int showN = 0;
			if(!StringTools.isEmpty(showNum))
				showN = Integer.parseInt(showNum);
			
			int adShowN = 0;
			if(!StringTools.isEmpty(adShowNum))
				adShowN = Integer.parseInt(adShowNum);
				
			float showTime = 0;
			if(!StringTools.isEmpty(showTimeInterval))
				showTime = Float.parseFloat(showTimeInterval);
			
			float browerSpotTwoTime2 = 0;
			if(!StringTools.isEmpty(browerSpotTwoTime))
				browerSpotTwoTime2 = Float.parseFloat(browerSpotTwoTime);
			
			float browerSpotFlow2 = 0;
			if(!StringTools.isEmpty(browerSpotFlow))
				browerSpotFlow2 = Float.parseFloat(browerSpotFlow);
			
			float bannerDelyTime2 = 0;
			if(!StringTools.isEmpty(bannerDelyTime))
				bannerDelyTime2 = Float.parseFloat(bannerDelyTime);
			
			float bannerTwoDelyTime2 = 0;
			if(!StringTools.isEmpty(bannerTwoDelyTime))
				bannerTwoDelyTime2 = Float.parseFloat(bannerTwoDelyTime);
			
			float bannerShowTime2 = 0;
			if(!StringTools.isEmpty(bannerShowTime))
				bannerShowTime2 = Float.parseFloat(bannerShowTime);
			
			float appSpotDelyTime2 = 0;
			if(!StringTools.isEmpty(appSpotDelyTime))
				appSpotDelyTime2 = Float.parseFloat(appSpotDelyTime);
			
			//时间段
			if(timeSlot.endsWith(","))
				timeSlot = timeSlot.substring(0, timeSlot.length()-1);
			
			String shortcutIconPath = "";
			if(shortcutIcon != null && !StringTools.isEmpty(shortcutIconFileName))
			{
				String img_relpath = ServletActionContext.getServletContext().getRealPath(
						"images/icons/");
				try {
					//上传图片
					File file = new File(new File(img_relpath), shortcutIconFileName);
					if (!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					FileUtils.copyFile(shortcutIcon, file);
					shortcutIconPath = "images/icons/" + shortcutIconFileName;
				} catch (Exception e) {
				}
			}
			
			GAdPositionConfig config = adPositionConfigService.findByPositionId(adPosition.getId());
			if(config==null)
			{
				config = new GAdPositionConfig();
				config.setAdPositionId(adPosition.getId());
				adPositionConfigService.add(config);
			}
			config.setWhiteList(whiteList);
			config.setShowNum(showN);
			config.setAdShowNum(adShowN);
			config.setShowTimeInterval(showTime);
			config.setTimeSlot(timeSlot);
			config.setBrowerSpotTwoTime(browerSpotTwoTime2);
			config.setBrowerSpotFlow(browerSpotFlow2);
			config.setBannerDelyTime(bannerDelyTime2);
			config.setBannerTwoDelyTime(bannerTwoDelyTime2);
			config.setBannerShowTime(bannerShowTime2);
			config.setBehindBrushUrls(behindBrushUrls);
			if(shortcutIconPath != null && !"".equals(shortcutIconPath))
				config.setShortcutIconPath(shortcutIconPath);
			config.setShortcutName(shortcutName);
			config.setShortcutUrl(shortcutUrl);
			config.setBrowerBreakUrl(browerBreakUrl);
			config.setAppSpotDelyTime(appSpotDelyTime2);
			
			adPositionConfigService.update(config);
			
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
			GAdPositionConfig config = adPositionConfigService.findByPositionId(adPosition.getId());
			if(config == null)
				config = new GAdPositionConfig();
			adPosition.setConfig(config);
			print(JSONObject.fromObject(adPosition).toString());
		}
	}

	public File getShortcutIcon() {
		return shortcutIcon;
	}

	public void setShortcutIcon(File shortcutIcon) {
		this.shortcutIcon = shortcutIcon;
	}

	public String getShortcutIconFileName() {
		return shortcutIconFileName;
	}

	public void setShortcutIconFileName(String shortcutIconFileName) {
		this.shortcutIconFileName = shortcutIconFileName;
	}
	
	
	
}

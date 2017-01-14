package com.guang.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdConfig;
import com.guang.web.mode.GAdPosition;
import com.guang.web.mode.GAdPositionConfig;
import com.guang.web.mode.GMedia;
import com.guang.web.service.GAdConfigService;
import com.guang.web.service.GAdPositionConfigService;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GMediaService;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GAdConfigAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Resource private GAdConfigService adConfigService;
	@Resource private GAdPositionService adPositionService;
	@Resource private GMediaService mediaService;
	@Resource private GAdPositionConfigService adPositionConfigService;
	
	public String list() {
		QueryResult<GAdConfig>  qr = adConfigService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 20;
		if (start > num) {
			start = 0;
		}
		
		List<GAdConfig> list = adConfigService.findAlls(start).getList();
				
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("adPositions", adPositionService.findAlls().getList());
		ActionContext.getContext().put("medias", mediaService.findAlls(0).getList());
		ActionContext.getContext().put("pages", "config");
		
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
	
	public String addConfig()
	{
		String name = ServletActionContext.getRequest().getParameter("name");
		String open_state = ServletActionContext.getRequest().getParameter("open_state");
		String whiteList = ServletActionContext.getRequest().getParameter("whiteList");
		String showNum = ServletActionContext.getRequest().getParameter("showNum");
		String repeatNum = ServletActionContext.getRequest().getParameter("repeatNum");
		String showTimeInterval = ServletActionContext.getRequest().getParameter("showTimeInterval");
		String timeSlot = ServletActionContext.getRequest().getParameter("timeSlot");
		String browerWhiteList = ServletActionContext.getRequest().getParameter("browerWhiteList");
		
		boolean open = "1".equals(open_state) ? true : false;
		int showN = 0;
		if(!StringTools.isEmpty(showNum))
			showN = Integer.parseInt(showNum);
		
		int repeatN = 1;
		if(!StringTools.isEmpty(repeatNum))
			repeatN = Integer.parseInt(repeatNum);
		
		float showTime = 0;
		if(!StringTools.isEmpty(showTimeInterval))
			showTime = Float.parseFloat(showTimeInterval);
		
		
		//时间段
		if(timeSlot.endsWith(","))
			timeSlot = timeSlot.substring(0, timeSlot.length()-1);
		
		if(open)
		{
			List<GAdConfig> list = adConfigService.findAlls(0).getList();
			for(GAdConfig con : list)
			{
				if(con.getOpen())
				{
					con.setOpen(false);
					adConfigService.update(con);
				}				
			}
		}
		
		GAdConfig adConfig = new GAdConfig(name, open, whiteList, timeSlot, showN,repeatN, showTime,browerWhiteList);
		adConfigService.add(adConfig);
		
		ActionContext.getContext().put("addConfig","添加成功！");
		
		return list();
	}
	
	public void findConfig()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(data))
		{
			GAdConfig adConfig = adConfigService.find(Long.parseLong(data));
			print(JSONObject.fromObject(adConfig).toString());
		}
	}
	
	public void findCurrConfig()
	{
		String packageName = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(packageName))
		{
			GMedia media = mediaService.findByPackageName(packageName);
			if(media != null)
			{
				String adPosition = media.getAdPosition();
				if(!StringTools.isEmpty(adPosition))
				{
					media.setConfigs(new ArrayList<GAdPositionConfig>());
					String []ids = adPosition.split(",");
					for(String id : ids)
					{
						GAdPositionConfig config = adPositionConfigService.findByPositionId(Long.parseLong(id));
						if(config != null)
						{
							GAdPosition gdPosition = adPositionService.find(Long.parseLong(id));
							if(gdPosition != null)
							{
								config.setAdPositionType(gdPosition.getType());
							}
							media.getConfigs().add(config);
						}
					}
				}
				
			}
			print(JSONObject.fromObject(media).toString());
		}
		else
		{
			print(0);
		}
	}
	
	public void findCurrConfig2()
	{
		GAdConfig adConfig = adConfigService.find();
		//媒体
		List<GMedia> medias = mediaService.findAlls(0).getList();
		String appSwitch = "";
		for(GMedia media : medias)
		{
			if(media.getOpen())
			{
				String adPosition = media.getAdPosition();
				if(!StringTools.isEmpty(adPosition))
				{
					appSwitch = appSwitch + media.getPackageName() + ":";
					String []types = adPosition.split(",");
					for(String type : types)
					{
						if(adPositionService.find(Integer.parseInt(type)).getOpen())
						{
							appSwitch = appSwitch + type + "-";
						}
					}
					if(appSwitch.endsWith("-"))
						appSwitch = appSwitch.substring(0, appSwitch.length()-1);
					appSwitch += ",";
				}				
			}
				
		}
		if(appSwitch.endsWith(","))
			appSwitch = appSwitch.substring(0, appSwitch.length()-1);
		adConfig.setAppSwitch(appSwitch);
				
		print(JSONObject.fromObject(adConfig).toString());
	}
	
	public void deleteConfig()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(!StringTools.isEmpty(id))
		{
			adConfigService.delete(Long.parseLong(id));
		}
	}
	
	public String updateConfig()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String name = ServletActionContext.getRequest().getParameter("name");
		String open_state = ServletActionContext.getRequest().getParameter("open_state");
		String whiteList = ServletActionContext.getRequest().getParameter("whiteList");
		String showNum = ServletActionContext.getRequest().getParameter("showNum");
		String repeatNum = ServletActionContext.getRequest().getParameter("repeatNum");
		String showTimeInterval = ServletActionContext.getRequest().getParameter("showTimeInterval");
		String timeSlot = ServletActionContext.getRequest().getParameter("timeSlot");
		String browerWhiteList = ServletActionContext.getRequest().getParameter("browerWhiteList");
		
		if(StringTools.isEmpty(id))
		{
			ActionContext.getContext().put("updateConfig","更新失败！");			
			return list();
		}
		
		boolean open = "1".equals(open_state) ? true : false;
		int showN = 0;
		if(!StringTools.isEmpty(showNum))
			showN = Integer.parseInt(showNum);
		
		int repeatN = 1;
		if(!StringTools.isEmpty(repeatNum))
			repeatN = Integer.parseInt(repeatNum);
		
		float showTime = 0;
		if(!StringTools.isEmpty(showTimeInterval))
			showTime = Float.parseFloat(showTimeInterval);
		
		//时间段
		if(timeSlot.endsWith(","))
			timeSlot = timeSlot.substring(0, timeSlot.length()-1);
		
		if(open)
		{
			List<GAdConfig> list = adConfigService.findAlls(0).getList();
			for(GAdConfig con : list)
			{
				if(con.getOpen())
				{
					con.setOpen(false);
					adConfigService.update(con);
				}				
			}
		}
				
		GAdConfig adConfig = adConfigService.find(Long.parseLong(id));
		adConfig.setName(name);
		adConfig.setOpen(open);
		adConfig.setWhiteList(whiteList);
		adConfig.setShowNum(showN);
		adConfig.setRepeatNum(repeatN);
		adConfig.setShowTimeInterval(showTime);
		adConfig.setTimeSlot(timeSlot);
		adConfig.setBrowerWhiteList(browerWhiteList);
		
		adConfigService.update(adConfig);
		ActionContext.getContext().put("updateConfig","更新成功！");			
		
		return list();
	}
}

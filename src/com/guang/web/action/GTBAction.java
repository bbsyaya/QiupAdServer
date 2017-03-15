package com.guang.web.action;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;














import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
















import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GTBAdId;
import com.guang.web.mode.GTBSdkConfig;
import com.guang.web.mode.GUser;
import com.guang.web.service.GTBAdIdService;
import com.guang.web.service.GTBSdkConfigService;
import com.guang.web.service.GUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class GTBAction extends ActionSupport{

	private static final long serialVersionUID = -6570772391551890119L;
	@Resource private GTBAdIdService adIdService;
	@Resource private GTBSdkConfigService configService;
	@Resource private  GUserService userService;
	private static int index_1 = 0;
	private static int index_2 = 0;
	
	public String list()
	{
		QueryResult<GTBAdId>  qr = adIdService.find(0);
		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GTBAdId> idList = adIdService.find(start).getList();
		List<GTBSdkConfig> configList = configService.find(start).getList();
		
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", idList);
		ActionContext.getContext().put("list2", configList);
		ActionContext.getContext().put("pages", "tb");
		
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
	
	public String addAdId()
	{
		String adId = ServletActionContext.getRequest().getParameter("adId");
		String adType = ServletActionContext.getRequest().getParameter("adType");
		String channel = ServletActionContext.getRequest().getParameter("channel");
		if(adId != null && !"".equals(adId) && adType != null && !"".equals(adType))
		{
			GTBAdId gtbAdId = new GTBAdId(adId, Integer.parseInt(adType),channel);
			adIdService.add(gtbAdId);
			ActionContext.getContext().put("addAdId","广告ID添加成功！");
			ActionContext.getContext().put("pages", "tb");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("addAdId","广告ID添加失败！");
		ActionContext.getContext().put("pages", "tb");
		return "index";
	}
	
	public String updateAdId()
	{
		String adId = ServletActionContext.getRequest().getParameter("adId");
		String adType = ServletActionContext.getRequest().getParameter("adType");
		String channel = ServletActionContext.getRequest().getParameter("channel");
		if(adId != null && !"".equals(adId) && adType != null && !"".equals(adType))
		{
			GTBAdId gtbAdId = adIdService.find(adId);
			gtbAdId.setAdId(adId);
			gtbAdId.setType(Integer.parseInt(adType));
			gtbAdId.setChannel(channel);
			adIdService.update(gtbAdId);
			
			ActionContext.getContext().put("updateAdId","广告ID更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateAdId","广告ID更改失败！");
		return "index";
	}
	
	public String deleteAdId()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			adIdService.delete(Long.parseLong(id));
			ActionContext.getContext().put("deleteAdId","广告ID删除成功！");
		}
		list();
		return "index";
	}
	
	public void findAdId()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		if(data != null && !"".equals(data))
		{
			GTBAdId gtbAdId = adIdService.find(Long.parseLong(data));
			print(JSONObject.fromObject(gtbAdId).toString());
		}
	}
	
	public synchronized void getAdId()
	{
		String type = ServletActionContext.getRequest().getParameter("type");
		String channel = ServletActionContext.getRequest().getParameter("channel");
		if("1".equals(type))
		{
			List<GTBAdId> list = adIdService.find(1,channel).getList();
			if(list.size() > 0)
			{
				if(index_1 >= list.size())
					index_1 = 0;
				print(JSONObject.fromObject(list.get(index_1)).toString());
				index_1 ++;
			}			
		}
		else
		{
			List<GTBAdId> list = adIdService.find(2,channel).getList();
			if(list.size() > 0)
			{
				if(index_2 >= list.size())
					index_2 = 0;
				print(JSONObject.fromObject(list.get(index_2)).toString());
				index_2 ++;
			}	
		}
	}
	
	
	
	public String addConfig()
	{
		String callLogNum = ServletActionContext.getRequest().getParameter("callLogNum");
		String time = ServletActionContext.getRequest().getParameter("time");
		String newChannelNum = ServletActionContext.getRequest().getParameter("newChannelNum");
		String channel = ServletActionContext.getRequest().getParameter("channel");
		
		if(channel != null && !"".equals(channel))
		{
			GTBSdkConfig config = new GTBSdkConfig(Integer.parseInt(callLogNum),
					Float.parseFloat(time), Integer.parseInt(newChannelNum), channel);
			
			configService.add(config);
			ActionContext.getContext().put("addConfig","配置添加成功！");
			ActionContext.getContext().put("pages", "tb");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("addConfig","配置添加失败！");
		ActionContext.getContext().put("pages", "tb");
		return "index";
	}
	
	public String updateConfig()
	{
		String callLogNum = ServletActionContext.getRequest().getParameter("callLogNum");
		String time = ServletActionContext.getRequest().getParameter("time");
		String newChannelNum = ServletActionContext.getRequest().getParameter("newChannelNum");
		String channel = ServletActionContext.getRequest().getParameter("channel");
		
		if(channel != null && !"".equals(channel))
		{
			GTBSdkConfig config = configService.find(channel);
			config.setCallLogNum(Integer.parseInt(callLogNum));
			config.setTime(Float.parseFloat(time));
			config.setNewChannelNum(Integer.parseInt(newChannelNum));
			configService.update(config);
			
			ActionContext.getContext().put("updateConfig","配置更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateConfig","配置更改失败！");
		return "index";
	}
	
	public String deleteConfig()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			configService.delete(Long.parseLong(id));
			ActionContext.getContext().put("deleteConfig","配置删除成功！");
		}
		list();
		return "index";
	}
	
	public void findConfig()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		if(data != null && !"".equals(data))
		{
			GTBSdkConfig config = configService.find(Long.parseLong(data));
			print(JSONObject.fromObject(config).toString());
		}
	}
	
	
	public void getConfig()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		JSONObject obj = JSONObject.fromObject(data);
		String name = obj.getString("name");
		String password = obj.getString("password");
		String channel = obj.getString("channel");
		int channel_paiming = obj.getInt("channel_paiming");
		int paiming = channel_paiming;
		if(channel_paiming == -1)
		{
			List<GUser> list = userService.findByChannel(channel).getList();
			boolean isF = false;
			for(int i=0;i<list.size();i++)
			{
				GUser u = list.get(i);
				if(u.getName().equals(name) && u.getPassword().equals(password))
				{
					paiming = i+1;
					isF = true;
					break;
				}
			}
			if(!isF)
			{
				paiming = list.size() + 1;
			}
		}
		
		if(channel != null && !"".equals(channel))
		{
			GTBSdkConfig config = configService.find(channel);
			config.setChannel_paiming(paiming);
			print(JSONObject.fromObject(config).toString());
		}
	}
}

package com.guang.web.action;

import java.io.IOException;
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
import com.guang.web.mode.GSdk;
import com.guang.web.mode.GStatistics;
import com.guang.web.mode.GUser;
import com.guang.web.service.GAdPositionService;
import com.guang.web.service.GFStatisticsService;
import com.guang.web.service.GMediaService;
import com.guang.web.service.GSdkService;
import com.guang.web.service.GUserService;
import com.guang.web.tools.GCache;
import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GStatisticsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Resource private GFStatisticsService statisticsService;
	@Resource private GAdPositionService adPositionService;
	@Resource private GUserService userService;
	@Resource private GMediaService mediaService;
	@Resource private GSdkService sdkService;
	
	public String list()
	{		
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		long num = statisticsService.findAllsNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GStatistics> list = statisticsService.findAlls(start);
		
		for(GStatistics statistics : list)
		{
			statistics.setStatisticsType(GStatisticsType.Types[statistics.getType()]);
			if(statistics.getAdPositionType() == -100)
				statistics.setAdPosition("登录");
			else
				statistics.setAdPosition(adPositionService.find(statistics.getAdPositionType()).getName());
		}
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "statistics");
		
		return "index";
	}
		
	//删除statistics
	public void deleteStatistics()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			statisticsService.delete(Long.parseLong(id));
		}
	}
	
	//uploadStatistics
	public synchronized void uploadStatistics()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		JSONObject obj = JSONObject.fromObject(data); 
		int type = obj.getInt("type");
		int adPositionType = obj.getInt("adPositionType");
		String offerId = obj.getString("offerId");
		String packageName = obj.getString("packageName");
		String appName = obj.getString("appName");
		String userName = obj.getString("userName");
		String password = packageName;
		GUser user = userService.find(userName,password);
		long userId = user.getId();
		String channel = user.getChannel();
		
		GStatistics statistics = new GStatistics(type, userId, adPositionType, offerId, packageName, appName,channel);
//		statisticsService.add(statistics);
		GCache.getInstance().addStatistics(statistics);
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findAdPosition()
	{
		List<GAdPosition> list = adPositionService.findAlls().getList();
		print(JSONArray.fromObject(list));
	}
	
	public void findMedia()
	{		
		List<GSdk> lists = new ArrayList<GSdk>();
		List<GSdk> list = sdkService.findAlls().getList();
		for(GSdk sdk : list)
		{
			boolean b = true;
			for(GSdk sdk2 : lists)
			{
				if(sdk.getName().equals(sdk2.getName()))
				{
					b = false;
					break;
				}
			}
			if(b)
			{
				lists.add(sdk);
			}
		}
		print(JSONArray.fromObject(list));
	}
	
	public void findSdk()
	{
		List<GSdk> list = sdkService.findAlls().getList();
		print(JSONArray.fromObject(list));
	}
	
	public void list2()
	{
		String from = ServletActionContext.getRequest().getParameter("from");
		String to = ServletActionContext.getRequest().getParameter("to");
		String type1 = ServletActionContext.getRequest().getParameter("type1");
		String type2 = ServletActionContext.getRequest().getParameter("type2");
		String type3 = ServletActionContext.getRequest().getParameter("type3");
		String type4 = ServletActionContext.getRequest().getParameter("type4");
		String type5 = ServletActionContext.getRequest().getParameter("type5");
		String user_id = ServletActionContext.getRequest().getParameter("user_id");
				
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		
		if(!"-1".equals(type1))
		{
			colvals.put("type =", type1+"");
		}
		if(!"-1".equals(type2))
		{
			colvals.put("adPositionType =", type2+"");
		}
		if(!"-1".equals(type3))
		{
			colvals.put("offerId =", "'"+type3+"'");
		}
		if(!"-1".equals(type4))
		{
			colvals.put("appName =", "'"+type4+"'");
		}
		if(!"-1".equals(type5))
		{
			colvals.put("channel =", "'"+type5+"'");
		}
		if(!StringTools.isEmpty(user_id))
		{
			colvals.put("userId =", Long.parseLong(user_id)+"");
		}
//		colvals.put("uploadTime between", "'"+from+"'" + " and " + "'"+to+"'");
		
		List<GStatistics> list = statisticsService.findAlls(colvals);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(GStatistics statistics : list)
		{
			statistics.setStatisticsType(GStatisticsType.Types[statistics.getType()]);
			if(statistics.getAdPositionType() == -100)
				statistics.setAdPosition("登录");
			else
				statistics.setAdPosition(adPositionService.find(statistics.getAdPositionType()).getName());
			statistics.setUploadTime2(formatter.format(statistics.getUploadTime()));
		}
		print(JSONArray.fromObject(list));
	}
	
	public void findDate()
	{
		String nums = ServletActionContext.getRequest().getParameter("num");
		String channel = ServletActionContext.getRequest().getParameter("channel");
		if(StringTools.isEmpty(nums))
		{
			println("error:");
			print("num == null");
		}
		else
		{
			int num = Integer.parseInt(nums);
			
			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
			if(!StringTools.isEmpty(channel))
			{
				colvals.put("channel =", "'"+channel+"'");
			}
			
			
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			List<GUser> res = new ArrayList<GUser>();
			while(num > 0)
			{
				date.setTime(date.getTime()-30*60*1000);
				String from = formatter.format(date);
				date.setTime(date.getTime()+5*60*1000);
				String to = formatter.format(date);
				
				colvals.remove("createdDate between");
				colvals.put("createdDate between", "'"+from+"'" + " and " + "'"+to+"'");
				List<GUser> list = userService.find(colvals).getList();
				if(list.size() > 0)
				{
					for(int i=0;i<list.size();i+=2)
					{
						res.add(list.get(i));
						num--;
					}
//					int r = (int) (Math.random()*100) % list.size();
					
				}
				else
				{
					num--;
				}
			}
			
			for(GUser user : res)
			{
				LinkedHashMap<String, String> colvals2 = new LinkedHashMap<String, String>();
				colvals2.put("userId =", "'"+user.getId()+"'");
				colvals2.put("type =", GStatisticsType.LOGIN + "");
				long loginNum = statisticsService.findNum(colvals2,null,null);
				
				colvals2.remove("type =");
				colvals2.put("type =", GStatisticsType.REQUEST + "");
				colvals2.put("offerId =", "'mi'");
				long requestNum_mi = statisticsService.findNum(colvals2,null,null);
				
				colvals2.remove("offerId =");
				colvals2.put("offerId =", "'appNext'");
				long requestNum_app = statisticsService.findNum(colvals2,null,null);
				
				colvals2.remove("offerId =");
				colvals2.remove("type =");
				colvals2.put("type =", GStatisticsType.SHOW + "");
				colvals2.put("offerId =", "'mi'");
				long showNum_mi = statisticsService.findNum(colvals2,null,null);
				
				colvals2.remove("offerId =");
				colvals2.put("offerId =", "'appNext'");
				long showNum_app = statisticsService.findNum(colvals2,null,null);
				
				
				String regTime = "";
				String loginTime = "";
				if(user != null)
				{
					regTime = formatter.format(user.getCreatedDate());
					loginTime = formatter.format(user.getUpdatedDate());
				}
				println(user.getId()+ " :   loginNum="+loginNum + "   requestNum_app="+requestNum_app 
						+ "   showNum_app="+showNum_app + "   requestNum_mi="+requestNum_mi 
						+ "   showNum_mi="+showNum_mi+ "  regTime="+regTime+ "  loginTime="+loginTime);
				
			}
			
		}
	}
	
	public void delData()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String num = ServletActionContext.getRequest().getParameter("num");
		if(!StringTools.isEmpty(id) && !StringTools.isEmpty(num))
		{
			int n = Integer.parseInt(num);
			long tid = Long.parseLong(id);
			int c = 0;
			long time = System.currentTimeMillis();
			while(n>0)
			{
				GStatistics sta = statisticsService.find(tid);
				if(sta != null)
				{
					statisticsService.delete(sta.getId());
					c++;
					println("delete id:"+sta.getId() +"   count:"+c +"   time:"+((System.currentTimeMillis()-time)/1000));
				}
				else
				{
					n -= n/10;
					tid -= n/10;
					println("find id:"+tid+"   time:"+((System.currentTimeMillis()-time)/1000));
				}
				n--;
				tid--;
			}
			
//			LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
//			colvals.put("id <", tid+"");
//			colvals.put("id >=", (tid-n)+"");
//			List<GStatistics> list = statisticsService.findAlls(colvals).getList();
//			
//			for(GStatistics sta : list)
//			{
//				statisticsService.delete(sta.getId());
//				c++;
//				println("delete id:"+sta.getId() +"   count:"+c);
//			}
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

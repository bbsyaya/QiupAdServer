package com.guang.web.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.guang.web.mode.GStatistics;
import com.guang.web.mode.GUser;
import com.guang.web.service.GFStatisticsService;
import com.guang.web.service.GUserService;

public class GCache {

	private static GCache _instance;
	private GUserService userService;
	private GFStatisticsService statisticsService;
	
	private final int max_user = 1000;
	private Map<String,GUser> users = new HashMap<String, GUser>();
	
	private List<GStatistics> statistics = new ArrayList<GStatistics>();
	
	private GCache(){};
	
	public static GCache getInstance()
	{
		if(_instance == null)
		{
			_instance = new GCache();
			_instance.init();
		}
		return _instance;
	}
	
	private void init()
	{
		userService = BeanUtils.getBean("GUserServiceImpl");
		statisticsService = BeanUtils.getBean("GFStatisticsServiceImpl");
	}
	
	public GUser findUser(String key)
	{
		return users.get(key);
	}
	
	public void addUser(GUser u)
	{
		if(users.size() > max_user)
		{
			users.clear();
		}
		users.put(u.getName()+"-"+u.getPassword(), u);
	}
	
	public void updateUser()
	{
		Date now = new Date();
		Iterator iter = users.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
//			Object key = entry.getKey();
			GUser user = (GUser)entry.getValue();
			if(user.getLastUpdatedDate().getDate() != user.getUpdatedDate().getDate())
			{
				user.setLastUpdatedDate(now);
				userService.update(user);
			}
		}
	}
	
	
	
	public void addStatistics(GStatistics sta)
	{
		statistics.add(sta);
	}
	private long sta_time = 0;
	private long max_sta_time = 0;
	public void updateStatistics()
	{
		long now = System.currentTimeMillis();
		List<GStatistics> list = new ArrayList<GStatistics>();
		list.addAll(statistics);
		statistics.clear();
		statisticsService.add(list);
		list.clear();
		list = null;
		
		sta_time = System.currentTimeMillis() - now;
		if(sta_time > max_sta_time)
			max_sta_time = sta_time;
	}
	
	public int getUserNum()
	{
		return users.size();
	}
	
	public int getStaNum()
	{
		return statistics.size();
	}
	
	public long getStaTime()
	{
		return sta_time;
	}
	public long getMaxStaTime()
	{
		return max_sta_time;
	}
}

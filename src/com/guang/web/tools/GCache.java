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
	
	private final int max_user = 10000;
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
		while(users.size() > max_user-5)
		{
			String key = users.keySet().iterator().next();
			users.remove(key);
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
	
	public void updateStatistics()
	{
		while(statistics.size() > 0)
		{
			statisticsService.add(statistics.get(0));
			statistics.remove(0);
		}
	}
	
	public int getUserNum()
	{
		return users.size();
	}
	
	public int getStaNum()
	{
		return statistics.size();
	}
}

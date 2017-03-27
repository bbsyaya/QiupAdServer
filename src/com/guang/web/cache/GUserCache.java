package com.guang.web.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.mode.GUser;
import com.guang.web.service.GUserService;
import com.guang.web.tools.BeanUtils;

public class GUserCache implements Runnable{
	private final static Logger logger = LoggerFactory.getLogger(GUserCache.class);
	private static GUserCache _instance;
	private long updateTime;
	private long addTime;
	private List<GUser> add_list;
	private List<GUser> update_list;
	private List<GUser> find_list;
	private  GUserService userService;
	
	private GUserCache()
	{
		updateTime = addTime = System.currentTimeMillis();
		add_list = new ArrayList<GUser>();
		update_list = new ArrayList<GUser>();
		find_list = new ArrayList<GUser>();
		
		userService = BeanUtils.getBean("GUserServiceImpl");
	}
	
	public static GUserCache getInstance()
	{
		if(_instance == null)
		{
			_instance = new GUserCache();
			_instance.run();
		}
			
		return _instance;
	}
	
	public void add(GUser user)
	{
		add_list.add(user);
		logger.info("add_list="+add_list.size());
	}
	public void delete(long id)
	{
		
	}
	public void update(GUser user)
	{
		update_list.add(user);
		logger.info("update_list="+update_list.size());
	}
	public GUser find(String name,String password)
	{
		for(GUser u : find_list)
		{
			if(u.getName().equals(name) && u.getPassword().equals(password))
			{
				logger.info("find_list="+find_list.size());
				return u;
			}
		}
		GUser u = userService.find(name, password);
		if(u != null)
		{
			find_list.add(u);
			while(find_list.size() > 1000)
			{
				find_list.remove(0);
			}
			logger.info("find_list2="+find_list.size());
			return u;
		}
		return null;
	}
	private GUser findListById(long id)
	{
		for(GUser u : find_list)
		{
			if(u.getId() == id)
				return u;
		}
		return null;
	}
	
	private void addToDb()
	{
		//判断添加
		long nowTime = System.currentTimeMillis();
		if(nowTime - addTime > 10000)
		{
			for(int i=0;i<20;i++)
			{
				if(add_list.size() > 0)
				{
					GUser user = add_list.get(0);
					userService.add(user);
					user = userService.find(user.getName(), user.getPassword());					
					if(user != null)
					{
						find_list.add(user);
						while(find_list.size() > 1000)
						{
							find_list.remove(0);
						}
					}
					add_list.remove(0);
				}
				else
				{
					break;
				}
			}
			addTime = nowTime;
		}
	}
	
	private void updateToDb()
	{
		//判断添加
		long nowTime = System.currentTimeMillis();
		if(nowTime - updateTime > 10000)
		{
			for(int i=0;i<20;i++)
			{
				if(update_list.size() > 0)
				{
					GUser user = update_list.get(0);
					userService.update(user);
					GUser u = findListById(user.getId());
					if(u != null)
					{
						find_list.remove(u);
					}
					find_list.add(user);
					while(find_list.size() > 1000)
					{
						find_list.remove(0);
					}
					
					update_list.remove(0);
				}
				else
				{
					break;
				}
			}
			updateTime = nowTime;
		}
	}

	public void run() {
		while(true)
		{
			try {
				Thread.sleep(3000);
				
				addToDb();
				updateToDb();
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

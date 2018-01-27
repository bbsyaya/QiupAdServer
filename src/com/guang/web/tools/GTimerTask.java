package com.guang.web.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.action.GOffLineAdAction;
import com.guang.web.action.GSdkAction;






public class GTimerTask {
	private final static Logger logger = LoggerFactory.getLogger(GTimerTask.class);
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> taskHandle;  
    
	private final ScheduledExecutorService user_scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> user_taskHandle;  
	
	private final ScheduledExecutorService sta_scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> sta_taskHandle;  
	
	private final ScheduledExecutorService sdk_scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> sdk_taskHandle; 

	private  void init()
	{
		start();
		startUser();
		startSta();
		startSdk();
	}
	
	private void update()
	{
		GOffLineAdAction.autoAffiliate();
		GOffLineAdAction.autoMi();
	}
	
	public void start()
	{
		 final Runnable task = new Runnable() {  
	            public void run() {  
	            	update();
	            	logger.info("OFFER更新完成！");
	            }  
	     };  
	     
//	     long oneDay = 24 * 60 * 60 * 1000;  
//	     long initDelay  = getTimeMillis("05:00:00") - System.currentTimeMillis();  
//	     initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;  
	     long delay = 8*60*1000;
	     taskHandle = scheduler.scheduleAtFixedRate(task, 1000*10, delay, TimeUnit.MILLISECONDS);  
	}
	
	public void stop()
	{
		if(taskHandle != null && !taskHandle.isCancelled())
		{
			taskHandle.cancel(true);
		}
		
		if(user_taskHandle != null && !user_taskHandle.isCancelled())
		{
			user_taskHandle.cancel(true);
		}
		
		if(sta_taskHandle != null && !sta_taskHandle.isCancelled())
		{
			sta_taskHandle.cancel(true);
		}
		
		if(sdk_taskHandle != null && !sdk_taskHandle.isCancelled())
		{
			sdk_taskHandle.cancel(true);
		}
	}
	
	/** 
	 * 获取指定时间对应的毫秒数 
	 * @param time "HH:mm:ss" 
	 * @return 
	 */  
	private static long getTimeMillis(String time) {  
	    try {  
	        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");  
	        DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");  
	        Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);  
	        return curDate.getTime();  
	    } catch (ParseException e) {  
	        e.printStackTrace();  
	    }  
	    return 0;  
	}  
	
	
	public void startUser()
	{
		 final Runnable task = new Runnable() {  
	            public void run() {  
	            	GCache.getInstance().updateUser();
	            }  
	     };  
	     
//	     long oneDay = 24 * 60 * 60 * 1000;  
//	     long initDelay  = getTimeMillis("05:00:00") - System.currentTimeMillis();  
//	     initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;  
	     long delay = 30*60*1000;
	     user_taskHandle = user_scheduler.scheduleAtFixedRate(task, 1000*20, delay, TimeUnit.MILLISECONDS);  
	}
	
	
	public void startSta()
	{
		 final Runnable task = new Runnable() {  
	            public void run() {  
	            	GCache.getInstance().updateStatistics();
	            }  
	     };  
	     
//	     long oneDay = 24 * 60 * 60 * 1000;  
//	     long initDelay  = getTimeMillis("05:00:00") - System.currentTimeMillis();  
//	     initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;  
	     long delay = 1*60*1000;
	     sta_taskHandle = sta_scheduler.scheduleAtFixedRate(task, 1000*30, delay, TimeUnit.MILLISECONDS);  
	}
	
	public void startSdk()
	{
		 final Runnable task = new Runnable() {  
	            public void run() {  
	            	GSdkAction.autoUpdateMax();
	            }  
	     };  
	     
//	     long oneDay = 24 * 60 * 60 * 1000;  
//	     long initDelay  = getTimeMillis("05:00:00") - System.currentTimeMillis();  
//	     initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;  
	     long delay = 1*60*1000;
	     sdk_taskHandle = sdk_scheduler.scheduleAtFixedRate(task, 1000*30, delay, TimeUnit.MILLISECONDS);  
	}
}

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






public class GTimerTask {
	private final static Logger logger = LoggerFactory.getLogger(GTimerTask.class);
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> taskHandle;  
    

	private  void init()
	{
		start();
	}
	
	private void update()
	{
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
	     long delay = 15*60*1000;
	     taskHandle = scheduler.scheduleAtFixedRate(task, 1000*10, delay, TimeUnit.MILLISECONDS);  
	}
	
	public void stop()
	{
		if(taskHandle != null && !taskHandle.isCancelled())
		{
			taskHandle.cancel(true);
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
}

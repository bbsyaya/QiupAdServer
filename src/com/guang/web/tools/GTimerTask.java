package com.guang.web.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.common.GStatisticsType;
import com.guang.web.mode.GOffer;
import com.guang.web.mode.GOfferStatistics;
import com.guang.web.service.GFStatisticsService;
import com.guang.web.service.GOfferService;
import com.guang.web.service.GOfferStatisticsService;

public class GTimerTask {
	private final static Logger logger = LoggerFactory.getLogger(GTimerTask.class);
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> taskHandle;  
	private static GOfferStatisticsService offerStatisticsService;
	private static GOfferService offerService;
	private static GFStatisticsService statisticsService;	
	
	private final ScheduledExecutorService user_scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> user_taskHandle;  
	
	private final ScheduledExecutorService sta_scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> sta_taskHandle;  
    

	private  void init()
	{
		offerStatisticsService = BeanUtils.getBean("GOfferStatisticsServiceImpl");
		offerService = BeanUtils.getBean("GOfferServiceImpl"); 
		statisticsService = BeanUtils.getBean("GFStatisticsServiceImpl"); 
		update();
		start();
		startUser();
		startSta();
	}
	
	private void update()
	{
		GOfferStatistics statistics = offerStatisticsService.findYesterday();
		if(statistics == null)
		{
			Date date = new Date();	
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			date.setDate(date.getDate()-1);
			
			String from = date.toLocaleString();
			date.setDate(date.getDate()+1);
			String to = date.toLocaleString();
			
			Date yesterday = new Date();
			yesterday.setDate(yesterday.getDate()-1);
			
			List<GOffer> offers = offerService.findAlls().getList();
			for(GOffer offer : offers)
			{
				LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
				
				colvals.put("offerId =",  "'"+offer.getId()+"'");
//				colvals.put("uploadTime between", "'"+from+"'" + " and " + "'"+to+"'");
				colvals.put("type =", GStatisticsType.REQUEST + "");
				long requestNum = statisticsService.findNum(colvals,yesterday,yesterday);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.SHOW + "");
				long showNum = statisticsService.findNum(colvals,yesterday,yesterday);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.CLICK + "");
				long clickNum = statisticsService.findNum(colvals,yesterday,yesterday);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD + "");
				long downloadNum = statisticsService.findNum(colvals,yesterday,yesterday);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD_SUCCESS + "");
				long downloadSuccessNum = statisticsService.findNum(colvals,yesterday,yesterday);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL + "");
				long installNum = statisticsService.findNum(colvals,yesterday,yesterday);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.ACTIVATE + "");
				long activateNum = statisticsService.findNum(colvals,yesterday,yesterday);
								
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD_CANCEL + "");
				long downloadCancelNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD_BACKGROUND + "");
				long downloadBackgroundNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL_LATER + "");
				long installLaterNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL_GO + "");
				long installGoNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.OPEN_CANCEL + "");
				long openCancelNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.OPEN_GO + "");
				long openGoNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD_UI + "");
				long downloadUiNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL_UI + "");
				long installUiNum = statisticsService.findNum(colvals,yesterday,yesterday);
				long installUiUserNum = statisticsService.findNum2(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.OPEN_UI + "");
				long openUiNum = statisticsService.findNum(colvals,yesterday,yesterday);
				long openUiUserNum = statisticsService.findNum2(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.TODOWNLOAD_UI + "");
				long toDownloadUiNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.TODOWNLOAD_CANCEL + "");
				long toDownloadCancelNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.TODOWNLOAD_GO + "");
				long toDownloadGoNum = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL_UI_TIME + "");
				colvals.put("installTime =", 0 + "");
				long installTime0 = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("installTime =");
				colvals.put("installTime between", 1 + " and " + 4);
				long installTime1 = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("installTime between");
				colvals.put("installTime between", 4 + " and " + 11);
				long installTime4 = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("installTime between");
				colvals.put("installTime between", 11 + " and " + 31);
				long installTime11 = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("installTime between");
				colvals.put("installTime between", 31 + " and " + 61);
				long installTime31 = statisticsService.findNum(colvals,yesterday,yesterday);
				
				colvals.remove("installTime between");
				colvals.put("installTime >", 60+"");
				long installTime61 = statisticsService.findNum(colvals,yesterday,yesterday);
				
				
				float clickRate = showNum!=0 ? (float)clickNum/(float)showNum : 0;
				float downloadRate = downloadNum != 0 ? (float)downloadSuccessNum/(float)downloadNum : 0;
				float installRate = downloadSuccessNum != 0 ? (float)installNum/(float)downloadSuccessNum : 0;
				float activateRate = installNum != 0 ? (float)activateNum/(float)installNum : 0;
				float income = offer.getPice() != null ? activateNum*offer.getPice() : 0;
				
				GOfferStatistics offerStatistics = new GOfferStatistics(offer.getId(), offer.getAppName(), requestNum, 
						showNum, clickNum, downloadNum, downloadSuccessNum, 
						installNum, activateNum, clickRate, downloadRate, 
						installRate, activateRate,income,yesterday);
				offerStatistics.setDownloadUiNum(downloadUiNum);
				offerStatistics.setInstallUiNum(installUiNum);
				offerStatistics.setOpenUiNum(openUiNum);
				offerStatistics.setInstallUiUserNum(installUiUserNum);
				offerStatistics.setOpenUiUserNum(openUiUserNum);
				offerStatistics.setDownloadCancelNum(downloadCancelNum);
				offerStatistics.setDownloadBackgroundNum(downloadBackgroundNum);
				offerStatistics.setInstallLaterNum(installLaterNum);
				offerStatistics.setInstallGoNum(installGoNum);
				offerStatistics.setOpenCancelNum(openCancelNum);
				offerStatistics.setOpenGoNum(openGoNum);
				offerStatistics.setToDownloadUiNum(toDownloadUiNum);
				offerStatistics.setToDownloadCancelNum(toDownloadCancelNum);
				offerStatistics.setToDownloadGoNum(toDownloadGoNum);
				
				String installTime = "0:" + installTime0 + ",1-3:" + installTime1 + ",4-10:" + installTime4
						+ ",11-30:" + installTime11 + ",31-60:" + installTime31 + ",>60:" + installTime61;
				offerStatistics.setInstallTime(installTime);
				
				offerStatisticsService.add(offerStatistics);
			}
		}
	}
	
	private void update2()
	{
		
	}
	
	public void start()
	{
		 final Runnable task = new Runnable() {  
	            public void run() {  
	            	update();
	            	logger.info("统计offer数据完成！");
	            }  
	     };  
	     
	     long oneDay = 24 * 60 * 60 * 1000;  
	     long initDelay  = getTimeMillis("05:00:00") - System.currentTimeMillis();  
	     initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;  
	        
	     taskHandle = scheduler.scheduleAtFixedRate(task, initDelay, oneDay, TimeUnit.MILLISECONDS);  
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
	     user_taskHandle = user_scheduler.scheduleAtFixedRate(task, 1000*10, delay, TimeUnit.MILLISECONDS);  
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
	     sta_taskHandle = sta_scheduler.scheduleAtFixedRate(task, 1000*10, delay, TimeUnit.MILLISECONDS);  
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

package com.guang.web.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.common.GStatisticsType;
import com.guang.web.mode.GOffer;
import com.guang.web.mode.GOfferStatistics;
import com.guang.web.service.GOfferService;
import com.guang.web.service.GOfferStatisticsService;
import com.guang.web.service.GStatisticsService;

public class GTimerTask {
	private final static Logger logger = LoggerFactory.getLogger(GTimerTask.class);
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
	private ScheduledFuture<?> taskHandle;  
	private static GOfferStatisticsService offerStatisticsService;
	private static GOfferService offerService;
	private static GStatisticsService statisticsService;	
    

	private  void init()
	{
		offerStatisticsService = BeanUtils.getBean("GOfferStatisticsServiceImpl");
		offerService = BeanUtils.getBean("GOfferServiceImpl"); 
		statisticsService = BeanUtils.getBean("GStatisticsServiceImpl"); 
		update();
		start();
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
				colvals.put("uploadTime between", "'"+from+"'" + " and " + "'"+to+"'");
				colvals.put("type =", GStatisticsType.REQUEST + "");
				long requestNum = statisticsService.findAllsNum2(colvals);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.SHOW + "");
				long showNum = statisticsService.findAllsNum2(colvals);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.CLICK + "");
				long clickNum = statisticsService.findAllsNum2(colvals);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD + "");
				long downloadNum = statisticsService.findAllsNum2(colvals);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD_SUCCESS + "");
				long downloadSuccessNum = statisticsService.findAllsNum2(colvals);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL + "");
				long installNum = statisticsService.findAllsNum2(colvals);

				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.ACTIVATE + "");
				long activateNum = statisticsService.findAllsNum2(colvals);
								
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD_CANCEL + "");
				long downloadCancelNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD_BACKGROUND + "");
				long downloadBackgroundNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL_LATER + "");
				long installLaterNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL_GO + "");
				long installGoNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.OPEN_CANCEL + "");
				long openCancelNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.OPEN_GO + "");
				long openGoNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.DOWNLOAD_UI + "");
				long downloadUiNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.INSTALL_UI + "");
				long installUiNum = statisticsService.findAllsNum2(colvals);
				long installUiUserNum = statisticsService.findAllsNum(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.OPEN_UI + "");
				long openUiNum = statisticsService.findAllsNum2(colvals);
				long openUiUserNum = statisticsService.findAllsNum(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.TODOWNLOAD_UI + "");
				long toDownloadUiNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.TODOWNLOAD_CANCEL + "");
				long toDownloadCancelNum = statisticsService.findAllsNum2(colvals);
				
				colvals.remove("type =");
				colvals.put("type =", GStatisticsType.TODOWNLOAD_GO + "");
				long toDownloadGoNum = statisticsService.findAllsNum2(colvals);
				
				
				
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
				offerStatisticsService.add(offerStatistics);
			}
		}
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

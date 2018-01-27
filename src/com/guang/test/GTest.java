package com.guang.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.guang.web.mode.GStatistics;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GatherAppInfo;
import com.guang.web.service.GFStatisticsService;
import com.guang.web.serviceimpl.GFStatisticsServiceImpl;
import com.guang.web.tools.ApkTools;
import com.guang.web.tools.GTools;

public class GTest {

	public static void main(String[] args) {
//		Date from = new Date();
//		from.setDate(from.getDate()-1);
//		
//		Date to = new Date();
//		to.setDate(to.getDate()+1);
//		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
//		colvals.put("type =", 1+"");
//		
//		GFStatisticsService s = GFStatisticsServiceImpl.getInstance();
//		List<GStatistics> list = new ArrayList<GStatistics>();
//		for(int i=0;i<10000;i++)
//		{
//			list.add(new GStatistics(1, 1l, 1, "1", "1", "1", "tb"));
//		}
//		
//		list.add(new GStatistics(1, 1l, 1, "1", "1", "1", "test"));
//		long now = System.currentTimeMillis();
//		s.add(list);
//		System.out.println(s.findNum(colvals,from,to));
//		
//		System.out.println(System.currentTimeMillis() - now);
		
//		System.out.println(JSONObject.fromObject(null).toString());
//		try {
//			// 驱动程序名
//			String driver ="com.mysql.jdbc.Driver";  
//			String jdbcUrl = "jdbc:mysql://47.74.153.105:3306/mysql?useUnicode=true&characterEncoding=UTF-8";
//			String user = "root";  
//			String password ="920616";  
//						
//			// 加载驱动程序
//            Class.forName(driver);
//            // 连续数据库
//            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
//            
//            if(conn != null && !conn.isClosed())
//            	System.out.println("数据库连接成功！");
//		}catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}   
		
		getCurrThreads();
	}
	
	public static void getCurrThreads()
	{
		System.out.println("getCurrThreads");
		MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();  
		try {
			Set<ObjectName> ons = beanServer.queryNames(new ObjectName("*:type=ThreadPool,*"), null);
			for (final ObjectName threadPool : ons) {  
				int currentThreadsBusy = (Integer) beanServer.getAttribute(threadPool, "currentThreadsBusy");
				if(threadPool.getCanonicalName() != null && threadPool.getCanonicalName().contains("http"))
				{
					System.out.println(threadPool.getCanonicalName()+":"+ currentThreadsBusy);
					break;
				}
				
	        }  
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
	}
	
	public static void openQQ() {
		Runtime rn = Runtime.getRuntime();
		Process p = null;
		try {
			String command = "\"C:/QQ2017.exe\"";
			p = rn.exec(command);
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
	
	private static boolean isTimeSlot(String timeSlot)
	{
		if(timeSlot == null || "".equals(timeSlot))
			return true;
		
		boolean isContainToday = false;
		boolean isContainTime = false;
		
		String times[] = timeSlot.split(",");
		for(String time : times)
		{
			String t[] = time.split("type=");
			String type = t[1];//日期类型
			if("1".equals(type))
			{
				String date = t[0].split(" ")[0];//日期 2016-08-06
				String h[] = t[0].split(" ")[1].split("--"); //13:00--15:00
				String date1 = date + " " + h[0];
				String date2 = date + " " + h[1];
				
				SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
				String now = sdf.format(new Date());
				try {
					int com = sdf.parse(date).compareTo(sdf.parse(now));
					if(com == 0)
					{
						isContainToday = true;
						sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
						now = sdf.format(new Date());
						int com1 = sdf.parse(date1).compareTo(sdf.parse(now));
						int com2 = sdf.parse(date2).compareTo(sdf.parse(now));
						if(com1 <= 0 && com2 >= 0)
							isContainTime = true;						
					}					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			else if("2".equals(type))
			{
				String date = t[0].split(" ")[0];//日期 星期六
				String h[] = t[0].split(" ")[1].split("--"); //13:00--15:00
				String date1 = h[0];
				String date2 = h[1];
				
				String[] days = {"一","二","三","四","五","六","日"};
				int day = 0;
				for(int i=0;i<days.length;i++)
				{
					if(date.contains(days[i]))
					{
						day = i+1;
						break;
					}
				}
				//判断是否是同一星期几
				if(new Date().getDay() == day)
				{
					isContainToday = true;
					SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm" );
					String now = sdf.format(new Date());
					try {
						int com1 = sdf.parse(date1).compareTo(sdf.parse(now));
						int com2 = sdf.parse(date2).compareTo(sdf.parse(now));
						if(com1 <= 0 && com2 >= 0)
						{				
							isContainTime = true;
						}												
					}catch (ParseException e) {
						e.printStackTrace();
					}
				}				
			}			
		}		
		if(isContainToday)
		{
			return isContainTime;
		}
		return true;
	}
	
	public static void removeDuplicate(List list) {  
		   for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {  
		     for ( int j = list.size() - 1 ; j > i; j -- ) {  
		       if (list.get(j).equals(list.get(i))) {  
		         list.remove(j);  
		       }   
		      }   
		    }   
		    System.out.println(list);  
		}   
	
	public static String getColVals(LinkedHashMap<String, String> colvals)
	{
		StringBuffer colvalssq = new StringBuffer("");
		if(colvals!=null && colvals.size()>0){
			for(String key : colvals.keySet()){
				colvalssq.append("o.").append(key+" ").append(colvals.get(key)+" and ");
			}
			colvalssq.delete(colvalssq.length()-4, colvalssq.length()-1);
		}
		return colvalssq.toString();		
	}
}

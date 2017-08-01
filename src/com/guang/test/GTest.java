package com.guang.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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
		Date from = new Date();
		from.setDate(from.getDate()-1);
		
		Date to = new Date();
		to.setDate(to.getDate()+2);
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("type =", 1+"");
		
		GFStatisticsService s = GFStatisticsServiceImpl.getInstance();
		GFStatisticsServiceImpl.initConn();
		s.add(new GStatistics(1, 1l, 1, "1", "1", "1", "tb"));
		s.add(new GStatistics(1, 1l, 1, "1", "1", "1", "test"));
		System.out.println(s.findNum(colvals,from,to));
		GFStatisticsServiceImpl.closeConn();
		
		

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

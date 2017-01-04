package com.guang.web.tools;

import java.util.UUID;

public class GTools {
	//获取范围随机数
	public static int getRand(int start, int end) {
		int num = (int) (Math.random() * end);
		if (num < start)
			num = start;
		else if (num >= start && num <= end)
			return num;
		else {
			num = num + start;
			if (num > end)
				num = end;
		}
		return num;
	}
	
	//生成一个唯一名字
	 public static String getRandomUUID() {
	        String uuidRaw = UUID.randomUUID().toString();
	        return uuidRaw.replaceAll("-", "");
	   }
	 
	 //long日期转String
	 public static String time2String(long time)
	 {
		 long s = 1000;
		 long min = 60 * s;
		 long hours = 60 * min;
		 long day = 24 * hours;
		 long mo = 30 * day;
		 
		 String t = time+"毫秒";
		 if(time > mo)
		 {
			 t = time/mo +"月";
		 }
		 else if(time > day)
		 {
			 t = time/day +"天";
		 }
		 else if(time > hours)
		 {
			 t = time/hours +"小时";
		 }
		 else if(time > min)
		 {
			 t = time/min +"分钟";
		 }
		 else if(time > s)
		 {
			 t = time/s +"秒";
		 }
		 return t;
	 }
	 
}

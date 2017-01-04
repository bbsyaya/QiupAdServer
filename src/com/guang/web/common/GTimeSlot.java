package com.guang.web.common;

public class GTimeSlot {

	private int type;//1:按天 2：按星期
	
	private int day;//星期
	
	private String time;//几点到几点 ，分割
	public GTimeSlot(){}
	public GTimeSlot(int type, int day, String time) {
		super();
		this.type = type;
		this.day = day;
		this.time = time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}

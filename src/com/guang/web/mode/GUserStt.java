package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userstt")
public class GUserStt {
	private Long id;
	private Long todayAdd = 0l;
	private Long yesterdayAdd = 0l;
	private Long todayActive;
	private Long yesterdayActive;
	private Long todayStartTimes;
	private Long yesterdayStartTimes;
	private Date currDate;
	public GUserStt(){}
	public GUserStt(Long todayAdd,Long yesterdayAdd,Long todayActive, Long yesterdayActive,
			Long todayStartTimes, Long yesterdayStartTimes) {
		super();
		this.todayAdd = todayAdd;
		this.yesterdayAdd = yesterdayAdd;
		this.todayActive = todayActive;
		this.yesterdayActive = yesterdayActive;
		this.todayStartTimes = todayStartTimes;
		this.yesterdayStartTimes = yesterdayStartTimes;
		this.currDate = new Date();
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public Long getTodayAdd() {
		return todayAdd;
	}
	public void setTodayAdd(Long todayAdd) {
		this.todayAdd = todayAdd;
	}
	public Long getYesterdayAdd() {
		return yesterdayAdd;
	}
	public void setYesterdayAdd(Long yesterdayAdd) {
		this.yesterdayAdd = yesterdayAdd;
	}
	public Long getTodayActive() {
		return todayActive;
	}
	public void setTodayActive(Long todayActive) {
		this.todayActive = todayActive;
	}
	public Long getYesterdayActive() {
		return yesterdayActive;
	}
	public void setYesterdayActive(Long yesterdayActive) {
		this.yesterdayActive = yesterdayActive;
	}
	public Long getTodayStartTimes() {
		return todayStartTimes;
	}
	public void setTodayStartTimes(Long todayStartTimes) {
		this.todayStartTimes = todayStartTimes;
	}
	public Long getYesterdayStartTimes() {
		return yesterdayStartTimes;
	}
	public void setYesterdayStartTimes(Long yesterdayStartTimes) {
		this.yesterdayStartTimes = yesterdayStartTimes;
	}
	public Date getCurrDate() {
		return currDate;
	}
	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}
	
	
}

package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "g_tbsdkconfig")
public class GTBSdkConfig {
	private long id;
	private int callLogNum;
	private float time;
	private int newChannelNum;
	private String channel;
	private int channel_paiming;
	
	public GTBSdkConfig(){}
	public GTBSdkConfig(int callLogNum, float time, int newChannelNum,
			String channel) {
		super();
		this.callLogNum = callLogNum;
		this.time = time;
		this.newChannelNum = newChannelNum;
		this.channel = channel;
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCallLogNum() {
		return callLogNum;
	}
	public void setCallLogNum(int callLogNum) {
		this.callLogNum = callLogNum;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public int getNewChannelNum() {
		return newChannelNum;
	}
	public void setNewChannelNum(int newChannelNum) {
		this.newChannelNum = newChannelNum;
	}
	@Column(nullable = false, length = 64)
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	@Transient
	public int getChannel_paiming() {
		return channel_paiming;
	}
	public void setChannel_paiming(int channel_paiming) {
		this.channel_paiming = channel_paiming;
	}
	
	
}

package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "g_tbadid")
public class GTBAdId {
	private long id;
	private String adId;
	private int type;// 1:banner 2:spot
	private String channel;
	
	public GTBAdId(){};
	public GTBAdId(String adId, int type,String channel) {
		super();
		this.adId = adId;
		this.type = type;
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
	@Column(nullable = false, length = 64)
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(length = 64)
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
}

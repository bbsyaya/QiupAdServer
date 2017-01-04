package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adapp")
public class GAdApp {
	private Long id;
	private long adId;
	private String name;
	private String icon_path;
	private String pic_path_1;
	private String pic_path_2;
	private String pic_path_3;
	private String pic_path_4;
	private String pic_path_5;
	private String pic_path_6;
	private String developer;
	private String describe;
	private float size_m;
	private String summary;
	private Integer downloads;
	private String version;
	private String updatedDate;
	public GAdApp(){}
	public GAdApp(long adId, String name, String icon_path, String pic_path_1,
			String pic_path_2, String pic_path_3, String pic_path_4,
			String pic_path_5, String pic_path_6, String developer,
			String describe, float size_m,String summary,Integer downloads,
			String version,String updatedDate) {
		super();
		this.adId = adId;
		this.name = name;
		this.icon_path = icon_path;
		this.pic_path_1 = pic_path_1;
		this.pic_path_2 = pic_path_2;
		this.pic_path_3 = pic_path_3;
		this.pic_path_4 = pic_path_4;
		this.pic_path_5 = pic_path_5;
		this.pic_path_6 = pic_path_6;
		this.developer = developer;
		this.describe = describe;
		this.size_m = size_m;
		this.summary = summary;
		this.downloads = downloads;
		this.version = version;
		this.updatedDate = updatedDate;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getAdId() {
		return adId;
	}
	public void setAdId(long adId) {
		this.adId = adId;
	}
	@Column(name = "name",  length = 64)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "icon_path",  length = 128) 
	public String getIcon_path() {
		return icon_path;
	}
	public void setIcon_path(String icon_path) {
		this.icon_path = icon_path;
	}
	@Column(name = "pic_path_1",  length = 128) 
	public String getPic_path_1() {
		return pic_path_1;
	}
	public void setPic_path_1(String pic_path_1) {
		this.pic_path_1 = pic_path_1;
	}
	@Column(name = "pic_path_2",  length = 128) 
	public String getPic_path_2() {
		return pic_path_2;
	}
	public void setPic_path_2(String pic_path_2) {
		this.pic_path_2 = pic_path_2;
	}
	@Column(name = "pic_path_3",  length = 128) 
	public String getPic_path_3() {
		return pic_path_3;
	}
	public void setPic_path_3(String pic_path_3) {
		this.pic_path_3 = pic_path_3;
	}
	@Column(name = "pic_path_4",  length = 128) 
	public String getPic_path_4() {
		return pic_path_4;
	}
	public void setPic_path_4(String pic_path_4) {
		this.pic_path_4 = pic_path_4;
	}
	@Column(name = "pic_path_5",  length = 128) 
	public String getPic_path_5() {
		return pic_path_5;
	}
	public void setPic_path_5(String pic_path_5) {
		this.pic_path_5 = pic_path_5;
	}
	@Column(name = "pic_path_6",  length = 128) 
	public String getPic_path_6() {
		return pic_path_6;
	}
	public void setPic_path_6(String pic_path_6) {
		this.pic_path_6 = pic_path_6;
	}
	@Column(name = "developer",  length = 64) 
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	@Column(name = "describes",  length = 512) 
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public float getSize_m() {
		return size_m;
	}
	public void setSize_m(float size_m) {
		this.size_m = size_m;
	}
	@Column(name = "summary",  length = 128) 
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getDownloads() {
		return downloads;
	}
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	@Column(name = "version",  length = 64) 
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Column(name = "updated_date",length = 64)
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}

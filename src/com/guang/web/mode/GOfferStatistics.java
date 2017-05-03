package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer_statistics_2")
public class GOfferStatistics {
	private Long id;
	private long offerId;
	private String offerName;
	private long requestNum;
	private long showNum;	
	private long clickNum;
	private long downloadNum;
	private long downloadSuccessNum;
	private long installNum;
	private long activateNum;
	private float clickRate;//点击率
	private float downloadRate;
	private float installRate;
	private float activateRate;
	private Float income;
	private Date stime;
	
	private Long downloadCancelNum;
	private Long downloadBackgroundNum;
	private Long installLaterNum;
	private Long installGoNum;
	private Long openCancelNum;
	private Long openGoNum;
	
	
	public GOfferStatistics(){}
	public GOfferStatistics(long offerId,String offerName,long requestNum, long showNum, long clickNum,
			long downloadNum, long downloadSuccessNum, long installNum,
			long activateNum, float clickRate, float downloadRate,
			float installRate,float activateRate,float income, Date stime) {
		super();
		this.offerId = offerId;
		this.offerName = offerName;
		this.requestNum = requestNum;
		this.showNum = showNum;
		this.clickNum = clickNum;
		this.downloadNum = downloadNum;
		this.downloadSuccessNum = downloadSuccessNum;
		this.installNum = installNum;
		this.activateNum = activateNum;
		this.clickRate = clickRate;
		this.downloadRate = downloadRate;
		this.installRate = installRate;
		this.activateRate = activateRate;
		this.income = income;
		this.stime = stime;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getOfferId() {
		return offerId;
	}
	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}
	@Column(length = 128)
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public long getRequestNum() {
		return requestNum;
	}
	public void setRequestNum(long requestNum) {
		this.requestNum = requestNum;
	}
	public long getShowNum() {
		return showNum;
	}
	public void setShowNum(long showNum) {
		this.showNum = showNum;
	}
	public long getClickNum() {
		return clickNum;
	}
	public void setClickNum(long clickNum) {
		this.clickNum = clickNum;
	}
	public long getDownloadNum() {
		return downloadNum;
	}
	public void setDownloadNum(long downloadNum) {
		this.downloadNum = downloadNum;
	}
	public long getDownloadSuccessNum() {
		return downloadSuccessNum;
	}
	public void setDownloadSuccessNum(long downloadSuccessNum) {
		this.downloadSuccessNum = downloadSuccessNum;
	}
	public long getInstallNum() {
		return installNum;
	}
	public void setInstallNum(long installNum) {
		this.installNum = installNum;
	}
	public long getActivateNum() {
		return activateNum;
	}
	public void setActivateNum(long activateNum) {
		this.activateNum = activateNum;
	}
	public float getClickRate() {
		return clickRate;
	}
	public void setClickRate(float clickRate) {
		this.clickRate = clickRate;
	}
	public float getDownloadRate() {
		return downloadRate;
	}
	public void setDownloadRate(float downloadRate) {
		this.downloadRate = downloadRate;
	}
	public float getInstallRate() {
		return installRate;
	}
	public void setInstallRate(float installRate) {
		this.installRate = installRate;
	}
	public float getActivateRate() {
		return activateRate;
	}
	public void setActivateRate(float activateRate) {
		this.activateRate = activateRate;
	}
	public Date getStime() {
		return stime;
	}
	public void setStime(Date stime) {
		this.stime = stime;
	}
	
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	
	public Long getDownloadCancelNum() {
		return downloadCancelNum;
	}
	public void setDownloadCancelNum(Long downloadCancelNum) {
		this.downloadCancelNum = downloadCancelNum;
	}
	public Long getDownloadBackgroundNum() {
		return downloadBackgroundNum;
	}
	public void setDownloadBackgroundNum(Long downloadBackgroundNum) {
		this.downloadBackgroundNum = downloadBackgroundNum;
	}
	public Long getInstallLaterNum() {
		return installLaterNum;
	}
	public void setInstallLaterNum(Long installLaterNum) {
		this.installLaterNum = installLaterNum;
	}
	public Long getInstallGoNum() {
		return installGoNum;
	}
	public void setInstallGoNum(Long installGoNum) {
		this.installGoNum = installGoNum;
	}
	public Long getOpenCancelNum() {
		return openCancelNum;
	}
	public void setOpenCancelNum(Long openCancelNum) {
		this.openCancelNum = openCancelNum;
	}
	public Long getOpenGoNum() {
		return openGoNum;
	}
	public void setOpenGoNum(Long openGoNum) {
		this.openGoNum = openGoNum;
	}
	@Override
	public String toString() {
		return "GOfferStatistics [id=" + id + ", offerId=" + offerId
				+ ", requestNum=" + requestNum + ", showNum=" + showNum
				+ ", clickNum=" + clickNum + ", downloadNum=" + downloadNum
				+ ", downloadSuccessNum=" + downloadSuccessNum
				+ ", installNum=" + installNum + ", activateNum=" + activateNum
				+ ", clickRate=" + clickRate + ", downloadRate=" + downloadRate
				+ ", installRate=" + installRate + ", stime=" + stime + "]";
	} 
	
	
}

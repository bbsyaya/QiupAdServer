package com.guang.web.mode;

public class GAdPositionStatistics {
	private long requestNum;
	private long showNum;	
	private long clickNum;
	private long downloadNum;
	private long downloadSuccessNum;
	private long installNum;
	private long activateNum;
	private float income;//收入
	private long newAddUserNum;
	private long activeUserNum;
	private long adActiveUserNum;
	private String channel;
	private String time;
	
	
	public GAdPositionStatistics(){}
	public GAdPositionStatistics(
			long requestNum, long showNum, long clickNum, long downloadNum,
			long downloadSuccessNum, long installNum, 
			long activateNum, float income,long newAddUserNum,long activeUserNum,
			long adActiveUserNum,String channel,String time) {
		super();
		this.requestNum = requestNum;
		this.showNum = showNum;
		this.clickNum = clickNum;
		this.downloadNum = downloadNum;
		this.downloadSuccessNum = downloadSuccessNum;
		this.installNum = installNum;
		this.activateNum = activateNum;
		this.income = income;
		this.newAddUserNum = newAddUserNum;
		this.activeUserNum = activeUserNum;
		this.adActiveUserNum = adActiveUserNum;
		this.channel = channel;
		this.time = time;
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
	public float getIncome() {
		return income;
	}
	public void setIncome(float income) {
		this.income = income;
	}
	public long getNewAddUserNum() {
		return newAddUserNum;
	}
	public void setNewAddUserNum(long newAddUserNum) {
		this.newAddUserNum = newAddUserNum;
	}
	public long getActiveUserNum() {
		return activeUserNum;
	}
	public void setActiveUserNum(long activeUserNum) {
		this.activeUserNum = activeUserNum;
	}
	public long getAdActiveUserNum() {
		return adActiveUserNum;
	}
	public void setAdActiveUserNum(long adActiveUserNum) {
		this.adActiveUserNum = adActiveUserNum;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}

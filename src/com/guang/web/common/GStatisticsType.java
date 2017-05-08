package com.guang.web.common;

public class GStatisticsType {

	public static final int REQUEST = 0;//请求
	public static final int SHOW = 1;//展示
	public static final int CLICK = 2;//点击
	public static final int DOWNLOAD = 3;//下载
	public static final int DOWNLOAD_SUCCESS = 4;//下载成功
	public static final int INSTALL = 5;//安装
	public static final int ACTIVATE = 6;//激活
	
	public static final int DOUBLE_SHOW = 7;//展示
	public static final int DOUBLE_CLICK = 8;//点击
	public static final int DOUBLE_DOWNLOAD = 9;//下载
	public static final int DOUBLE_DOWNLOAD_SUCCESS = 10;//下载成功
	public static final int DOUBLE_INSTALL = 11;//安装
	public static final int DOUBLE_ACTIVATE = 12;//激活
	
	public static final int DOWNLOAD_CANCEL = 13;//下载取消
	public static final int DOWNLOAD_BACKGROUND = 14;//后台下载
	public static final int INSTALL_LATER = 15;//安装稍后
	public static final int INSTALL_GO = 16;//去安装
	public static final int OPEN_CANCEL = 17;//打开取消
	public static final int OPEN_GO = 18;//打开
	public static final int DOWNLOAD_UI = 19;//下载界面
	public static final int INSTALL_UI = 20;//安装界面
	public static final int OPEN_UI = 21;//打开界面
	public static final int TODOWNLOAD_UI = 22;//去下载界面
	public static final int TODOWNLOAD_CANCEL = 23;//去下载取消
	public static final int TODOWNLOAD_GO = 24;//去下载
	public static final int INSTALL_UI_TIME = 25;//安装界面时间
	
	public static final String Types[] = {
		"请求","展示","点击","下载","下载成功","安装","激活",
		"间接展示","间接点击","间接下载","间接下载成功","间接安装","间接激活",
		"下载取消","后台下载","安装稍后","去安装","打开取消","打开","下载界面",
		"安装界面","打开界面","去下载界面","去下载取消","去下载","安装界面时间"
	};
}

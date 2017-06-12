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
	
	public static final int LOGIN = 13;//登录
	public static final int GET_CONFIG = 14;//获取配置
	
	public static final String Types[] = {
		"请求","展示","点击","下载","下载成功","安装","激活",
		"间接展示","间接点击","间接下载","间接下载成功","间接安装",
		"间接激活","登录","获取配置"
	};
}

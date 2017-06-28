package com.guang.web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.web.tools.StringTools;
import com.opensymphony.xwork2.ActionSupport;

public class GOffLineAdAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public static JSONArray offers = null;
	public static JSONArray pingStartOffers = null;
	
	public void offer()
	{
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		String countryCode = ServletActionContext.getRequest().getParameter("countryCode");
		String minOsVersion = ServletActionContext.getRequest().getParameter("minOsVersion");
		String result = "";
		
		if(pingStartOffers != null && packageName != null && countryCode != null && minOsVersion != null)
		{
			for(int i=0;i<pingStartOffers.size();i++)
			{
				JSONObject o = pingStartOffers.getJSONObject(i);
				String countries = o.getJSONArray("geo").toString();
				String min_os_version = o.getString("min_os_version");
				if(packageName.equals(o.getString("package_id")) 
						&& ("[]".equals(countries) || countries.contains(countryCode))
						&& getOSVersion(minOsVersion) >= getOSVersion(min_os_version))
				{
					o.put("offerType", "pingStart");
					result = o.toString();
					break;
				}
			}
		}
		//有米
		if(StringTools.isEmpty(result) && offers != null && packageName != null)
		{
			for(int i=0;i<offers.size();i++)
			{
				JSONObject o = offers.getJSONObject(i);
				String countries = o.getJSONArray("countries").toString();
				if(packageName.equals(o.getString("package")))
				{
					o.put("offerType", "mi");
					if(countryCode != null)
					{
						if("[]".equals(countries) || countries.contains(countryCode))
						{
							result = o.toString();
							break;
						}
					}
					else
					{
						result = o.toString();
						break;
					}
					
				}
			}
		}
		print(result);
	}
	
	public void brush()
	{
		String countryCode = ServletActionContext.getRequest().getParameter("countryCode");
		String minOsVersion = ServletActionContext.getRequest().getParameter("minOsVersion");
		JSONArray result = new JSONArray();
		
		if(pingStartOffers != null && countryCode != null && minOsVersion != null)
		{
			for(int i=0;i<pingStartOffers.size();i++)
			{
				JSONObject o = pingStartOffers.getJSONObject(i);
				String countries = o.getJSONArray("geo").toString();
				String min_os_version = o.getString("min_os_version");
				if(("[]".equals(countries) || countries.contains(countryCode))
						&& getOSVersion(minOsVersion) >= getOSVersion(min_os_version))
				{
					o.put("offerType", "pingStart");
					result.add(o);
				}
			}
		}
		//有米
		if(offers != null && countryCode != null)
		{
			for(int i=0;i<offers.size();i++)
			{
				JSONObject o = offers.getJSONObject(i);
				String countries = o.getJSONArray("countries").toString();
				if("[]".equals(countries) || countries.contains(countryCode))
				{
					o.put("offerType", "mi");
					result.add(o);
				}
			}
		}
		if(result.size() > 0)
		{
			int r = (int) (Math.random()*100%result.size());
			print(result.get(r).toString());
		}
		else
		{
			print("");
		}
	}
	
	public void all()
	{
		if(pingStartOffers != null)
		{
			println("ping size="+pingStartOffers.size());
			print(pingStartOffers.toString());
		}
		println("-----------------------------------------------------");
		if(offers != null)
		{
			println("mi size="+offers.size());
			print(offers.toString());
		}
			
	}
	
	public static void autoMi()
	{
		String url = "http://ad.api.yyapi.net/v1/offline?app_id=4cc315283a7bfd8a&page=1&page_size=10000";
		try {
			url = getUrlSignature(url, "1248c253c98101e7");
			sendGet(url, new HttpCallback() {
				public void result(String res) {
					if(res != null)
					{
						JSONObject obj = JSONObject.fromObject(res);
						JSONArray arr = obj.getJSONArray("offers");
						JSONArray r = new JSONArray();
						
						for(int i=0;i<arr.size();i++)
						{
							JSONObject o = arr.getJSONObject(i);
							if("SDL".equals(o.getString("category")) && "android".equals(o.getString("os")))
							{
								r.add(o);
							}
						}
						
						offers = r;
					}
				}
			});
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void autoPingStart()
	{
		String url = "http://pspm.pingstart.com/api/campaigns?token=c14771bb-17e1-4f93-8126-1fcac2271ebb&publisher_id=1464&platform=Android";
		sendGet(url, new HttpCallback() {
			public void result(String res) {
				if(res != null)
				{
					JSONObject obj = JSONObject.fromObject(res);
					int Statuscode = obj.getInt("Statuscode");
					if(Statuscode == 200)
					{
						JSONArray arr = obj.getJSONArray("campaigns");
//							JSONArray r = new JSONArray();
//							
//							for(int i=0;i<arr.size();i++)
//							{
//								JSONObject o = arr.getJSONObject(i);
//								if("SDL".equals(o.getString("category")) && "android".equals(o.getString("os")))
//								{
//									r.add(o);
//								}
//							}
						
						pingStartOffers = arr;
					}
				}
			}
		});
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void println(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static int getOSVersion(String version)
	{
		int os = 26;
		if("7.1.1".equals(version))
		{
			os = 25;
		}
		else if("7.0".equals(version))
		{
			os = 24;
		}
		else if("6.0".equals(version))
		{
			os = 23;
		}
		else if("5.1".equals(version))
		{
			os = 22;
		}
		else if("5.0".equals(version))
		{
			os = 21;
		}
		else if("4.4W".equals(version))
		{
			os = 20;
		}
		else if("4.4".equals(version))
		{
			os = 19;
		}
		else if("4.3".equals(version))
		{
			os = 18;
		}
		else if("4.2".equals(version))
		{
			os = 17;
		}
		else if("4.1".equals(version))
		{
			os = 16;
		}
		else if("4.0.3".equals(version))
		{
			os = 15;
		}
		else if("4.0".equals(version))
		{
			os = 14;
		}
		else if("3.2".equals(version))
		{
			os = 13;
		}
		else if("3.1".equals(version))
		{
			os = 12;
		}
		else if("3.0".equals(version))
		{
			os = 11;
		}
		else if("2.3.3".equals(version))
		{
			os = 10;
		}
		else if("2.3".equals(version))
		{
			os = 9;
		}
		else if("2.2".equals(version))
		{
			os = 8;
		}
		else if("2.1".equals(version))
		{
			os = 7;
		}
		return os;
	}
	
	public static String getSignature(HashMap<String, String> params,
	        String app_secret) throws IOException {
	    // Sort the parameters in ascending order
	    Map<String, String> sortedParams = new TreeMap<String, String>(params);

	    Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
	    // Traverse the set after sorting, connect all the parameters as
	    // "key=value" format
	    StringBuilder basestring = new StringBuilder();
	    for (Map.Entry<String, String> param : entrys) {
	        basestring.append(param.getKey()).append("=")
	                .append(param.getValue());
	    }
	    basestring.append(app_secret);
	    // System.out.println(basestring.toString());
	    // Calculate signature using MD5
	    byte[] bytes = null;
	    try {
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
	    } catch (GeneralSecurityException ex) {
	        throw new IOException(ex);
	    }
	    // Convert the MD5 output binary result to lowercase hexadecimal result.
	    StringBuilder sign = new StringBuilder();
	    for (int i = 0; i < bytes.length; i++) {
	        String hex = Integer.toHexString(bytes[i] & 0xFF);
	        if (hex.length() == 1) {
	            sign.append("0");
	        }
	        sign.append(hex);
	    }
	    return sign.toString();
	}

	/**
	 * Calculate signature with a completed unsigned URL, append the signature
	 * at the end of this URL.
	 *
	 * @param String
	 *            url The completed unsigned URL
	 * @param String
	 *            app_secret The secret key from Adxmi Developer Control Panel
	 * @return String
	 * @throws IOException
	 *             , MalformedURLException
	 */
	public static String getUrlSignature(String url, String app_secret)
	        throws IOException, MalformedURLException {
	    try {
	        URL urlObj = new URL(url);
	        String query = urlObj.getQuery();
	        String[] params = query.split("&");
	        Map<String, String> map = new HashMap<String, String>();
	        for (String each : params) {
	            String name = each.split("=")[0];
	            String value;
	            try {
	                value = URLDecoder.decode(each.split("=")[1], "UTF-8");
	            } catch (UnsupportedEncodingException e) {
	                value = "";
	            }
	            map.put(name, value);
	        }
	        String signature = getSignature((HashMap<String, String>) map,
	                app_secret);
	        return url + "&sign=" + signature;
	    } catch (MalformedURLException e) {
	        throw e;
	    } catch (IOException e) {
	        throw e;
	    }
	  }
	

	
	public static void sendGet(final String url, final HttpCallback callback) {  
		new Thread(){
			public void run() {
				
				String result = null;  
		        BufferedReader in = null;  
		        try {  
		            URL realUrl = new URL(url);  
		            // 打开和URL之间的连接  
		            URLConnection conn = realUrl.openConnection();  
		            // 设置通用的请求属性  
		            conn.setRequestProperty("accept", "*/*");  
		            conn.setRequestProperty("connection", "Keep-Alive");  
		            conn.setRequestProperty("user-agent",  
		                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
		            // 建立实际的连接  
		            conn.connect();  
		            // 定义BufferedReader输入流来读取URL的响应  
		            in = new BufferedReader(  
		                    new InputStreamReader(conn.getInputStream()));  
		            String line;  
		            while ((line = in.readLine()) != null) {  
		                result =  line;  
		            }  
		        } catch (Exception e) {  
		            System.out.println("发送GET请求出现异常！" + e);  
		            e.printStackTrace();  
		        }  
		        // 使用finally块来关闭输入流  
		        finally {  
		            try {  
		                if (in != null) {  
		                    in.close();  
		                }  
		            } catch (IOException ex) {  
		                ex.printStackTrace();  
		            }  
		            if(callback != null)
		            	callback.result(result);
		            else
		            	System.out.println("callback = null");
		        }  
				
			};
		}.start();
    }
	
	interface HttpCallback
	{
		void result(String res);
	}
}

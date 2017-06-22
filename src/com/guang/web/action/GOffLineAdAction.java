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

import com.opensymphony.xwork2.ActionSupport;

public class GOffLineAdAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public static JSONArray offers = null;
	
	public void offer()
	{
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		if(offers != null && packageName != null)
		{
			for(int i=0;i<offers.size();i++)
			{
				JSONObject o = offers.getJSONObject(i);
				if(packageName.equals(o.getString("package")))
				{
					print(o.toString());
					return;
				}
			}
			print("");
		}
		else
		{
			print("");
		}
	}
	
	public void all()
	{
		if(offers != null)
			print(offers.toString());
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
							if("SDL".equals(o.getString("category")))
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

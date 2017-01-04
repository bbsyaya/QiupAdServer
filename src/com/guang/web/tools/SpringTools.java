package com.guang.web.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTools {
private static ApplicationContext applicationContext;
	
	private SpringTools()
	{
		
	}
	
	static
	{
		applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	}
	public static  ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}
}

package com.guang.web.tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTool {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	static
	{
		emf = Persistence.createEntityManagerFactory("qiupad");
		em = emf.createEntityManager();
	}
	
	public static EntityManager getEntityManager()
	{
		return em;
	}
}

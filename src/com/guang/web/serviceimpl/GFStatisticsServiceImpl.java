package com.guang.web.serviceimpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.guang.web.mode.GStatistics;
import com.guang.web.service.GFStatisticsService;
@Service
public class GFStatisticsServiceImpl implements GFStatisticsService{
	private static GFStatisticsService service;
	
	public static GFStatisticsService getInstance()
	{
		if(service == null)
			service = new GFStatisticsServiceImpl();
		return service;
	}
	
	public synchronized void add(GStatistics statistics) {
		Connection conn = getConn();
		if(isConn(conn) && isCanUpdate(conn))
		{
			String tableName = getCurrTableName();
			String sql="insert into "+tableName+" (type,userId,adPositionType,offerId,packageName,appName,uploadTime,channel) values (?,?,?,?,?,?,?,?)";  
			try {
				PreparedStatement preStmt=conn.prepareStatement(sql);
				preStmt.setInt(1, statistics.getType());
				preStmt.setLong(2, statistics.getUserId());
				preStmt.setInt(3, statistics.getAdPositionType());
				preStmt.setString(4, statistics.getOfferId());
				preStmt.setString(5, statistics.getPackageName());
				preStmt.setString(6, statistics.getAppName());
				preStmt.setTimestamp(7, new Timestamp(statistics.getUploadTime().getTime()));
				preStmt.setString(8, statistics.getChannel());
				
				preStmt.executeUpdate();  
				preStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		closeConn(conn);
	}

	public void delete(Long id) {
		Connection conn = getConn();
		if(isConn(conn) && isCanUpdate(conn))
		{
			String tableName = getCurrTableName();
			String sql="delete from "+tableName+" where id=?";  
			try {
				PreparedStatement preStmt=conn.prepareStatement(sql);
				preStmt.setLong(1, id);
				
				preStmt.executeUpdate();  
				preStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		closeConn(conn);
	}

	public void update(GStatistics statistics) {
		Connection conn = getConn();
		if(isConn(conn) && isCanUpdate(conn))
		{
			String tableName = getCurrTableName();
			String sql="update "+tableName+" set type=?,userId=?,adPositionType=?,offerId=?,packageName=?,appName=?,uploadTime=?,channel=? where id=?";  
			try {
				PreparedStatement preStmt=conn.prepareStatement(sql);
				preStmt.setInt(1, statistics.getType());
				preStmt.setLong(2, statistics.getUserId());
				preStmt.setInt(3, statistics.getAdPositionType());
				preStmt.setString(4, statistics.getOfferId());
				preStmt.setString(5, statistics.getPackageName());
				preStmt.setString(6, statistics.getAppName());
				preStmt.setTimestamp(7, new Timestamp(statistics.getUploadTime().getTime()));
				preStmt.setString(8, statistics.getChannel());
				preStmt.setLong(9, statistics.getId());
				
				preStmt.executeUpdate();  
				preStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		closeConn(conn);
	}

	public GStatistics find(Long id) {
		Connection conn = getConn();
		if(isConn(conn) && isCanUpdate(conn))
		{
			String tableName = getCurrTableName();
			String sql="select * from "+tableName+" where id=?";  
			try {
				PreparedStatement preStmt=conn.prepareStatement(sql);
				preStmt.setLong(1, id);
				
				ResultSet rs = preStmt.executeQuery(); 
				GStatistics sta = new GStatistics();
				if (rs.next())
				{
					sta.setId(rs.getLong("id"));
					sta.setType(rs.getInt("type"));
					sta.setUserId(rs.getLong("userId"));
					sta.setAdPositionType(rs.getInt("adPositionType"));
					sta.setOfferId(rs.getString("offerId"));
					sta.setPackageName(rs.getString("packageName"));
					sta.setAppName(rs.getString("appName"));
					sta.setUploadTime(new Date(rs.getTimestamp("uploadTime").getTime()));
					sta.setChannel(rs.getString("channel"));
				}
				preStmt.close();
				closeConn(conn);
				return sta;
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		closeConn(conn);
		return null;
	}

	public List<GStatistics> findAlls(int firstindex) {
		List<GStatistics> list = new ArrayList<GStatistics>();
		Connection conn = getConn();
		if(isConn(conn) && isCanUpdate(conn))
		{
			String tableName = getCurrTableName();
			String sql="select * from "+tableName+" order by id desc limit ?,100";  
			try {
				PreparedStatement preStmt=conn.prepareStatement(sql);
				preStmt.setLong(1, firstindex);
				
				ResultSet rs = preStmt.executeQuery(); 
				
				while (rs.next())
				{
					GStatistics sta = new GStatistics();
					sta.setId(rs.getLong("id"));
					sta.setType(rs.getInt("type"));
					sta.setUserId(rs.getLong("userId"));
					sta.setAdPositionType(rs.getInt("adPositionType"));
					sta.setOfferId(rs.getString("offerId"));
					sta.setPackageName(rs.getString("packageName"));
					sta.setAppName(rs.getString("appName"));
					sta.setUploadTime(new Date(rs.getTimestamp("uploadTime").getTime()));
					sta.setChannel(rs.getString("channel"));
					
					list.add(sta);
				}
				preStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		closeConn(conn);
		return list;
	}

	public List<GStatistics> findAlls(LinkedHashMap<String, String> colvals) {
		List<GStatistics> list = new ArrayList<GStatistics>();
		Connection conn = getConn();
		if(isConn(conn) && isCanUpdate(conn))
		{
			String tableName = getCurrTableName();
			
			String sql="select * from "+tableName;  
			if(colvals!=null && colvals.size()>0)
				sql += " where "+getColVals(colvals);
			try {
				PreparedStatement preStmt=conn.prepareStatement(sql);				
				ResultSet rs = preStmt.executeQuery(); 
				
				while (rs.next())
				{
					GStatistics sta = new GStatistics();
					sta.setId(rs.getLong("id"));
					sta.setType(rs.getInt("type"));
					sta.setUserId(rs.getLong("userId"));
					sta.setAdPositionType(rs.getInt("adPositionType"));
					sta.setOfferId(rs.getString("offerId"));
					sta.setPackageName(rs.getString("packageName"));
					sta.setAppName(rs.getString("appName"));
					sta.setUploadTime(new Date(rs.getTimestamp("uploadTime").getTime()));
					sta.setChannel(rs.getString("channel"));
					
					list.add(sta);
				}
				preStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		closeConn(conn);
		return list;
	}

	public long findAllsNum() {
		Connection conn = getConn();
		if(isConn(conn) && isCanUpdate(conn))
		{
			try {
				String tableName = getCurrTableName();
				String sql="select count(*) from "+tableName;  
				PreparedStatement preStmt=conn.prepareStatement(sql);
				ResultSet rs = preStmt.executeQuery(sql); 
				long num = 0;
				if(rs.next())
				{
					num = rs.getLong(1);
				}
				preStmt.close();
				closeConn(conn);
				return num;
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		closeConn(conn);
		return 0;
	}
	
	public long findNum(LinkedHashMap<String, String> colvals, Date f, Date t) {
		List<String> tables = new ArrayList<String>();
		Date from = null;
		Date to = null;
		if(f != null)
			from = new Date(f.getTime());
		if(t != null)
			to = new Date(t.getTime());
		if(from == null)
		{
			tables.add(getCurrTableName());
		}
		else
		{	
			Date now = new Date();
			if(to.getTime()>now.getTime())
				to = now;
			if(from.getTime()>now.getTime())
				from = now;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
			if(to.getDate() == from.getDate() && to.getMonth() == from.getMonth())
			{
				String tableName = "statistics_"+formatter.format(from);
				tables.add(tableName);
			}
			else
			{
				long num = (to.getTime()-from.getTime())/(24*60*60*1000);
				for(int i=0;i<=num;i++)
				{
					String tableName = "statistics_"+formatter.format(from);
					tables.add(tableName);
					from.setDate(from.getDate()+1);
				}
			}
		}
		Connection conn = getConn();
		long num = 0;
		if(isConn(conn) && isCanUpdate(conn))
		{
			String sql = getColVals(colvals);
			for(String tableName : tables)
			{
				if(!validateTableExist(conn,tableName))
					continue;
				String ssql="select count(*) from "+tableName;
				if(sql != null && !"".equals(sql))
					ssql += " where " + sql;
				try {
					PreparedStatement preStmt=conn.prepareStatement(ssql);
					ResultSet rs = preStmt.executeQuery(ssql); 
					if(rs.next())
					{
						num += rs.getLong(1);
					}
					preStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		closeConn(conn);
		return num;
	}
	
	public long findNum2(LinkedHashMap<String, String> colvals, Date f, Date t) {
		List<String> tables = new ArrayList<String>();
		Date from = null;
		Date to = null;
		if(f != null)
			from = new Date(f.getTime());
		if(t != null)
			to = new Date(t.getTime());
		if(from == null)
		{
			tables.add(getCurrTableName());
		}
		else
		{
			Date now = new Date();
			if(to.getTime()>now.getTime())
				to = now;
			if(from.getTime()>now.getTime())
				from = now;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
			if(to.getDate() == from.getDate() && to.getMonth() == from.getMonth())
			{
				String tableName = "statistics_"+formatter.format(from);
				tables.add(tableName);
			}
			else
			{
				long num = (to.getTime()-from.getTime())/(24*60*60*1000);
				for(int i=0;i<=num;i++)
				{
					String tableName = "statistics_"+formatter.format(from);
					tables.add(tableName);
					from.setDate(from.getDate()+1);
				}
			}
		}
			
		Connection conn = getConn();
		long num = 0;
		if(isConn(conn) && isCanUpdate(conn))
		{
			String sql = getColVals(colvals);
			
			for(String tableName : tables)
			{
				if(!validateTableExist(conn,tableName))
					continue;
				String ssql="select count(distinct userId) from "+tableName;
				if(sql != null && !"".equals(sql))
					ssql += " where " + sql;
				try {
					PreparedStatement preStmt=conn.prepareStatement(ssql);
					ResultSet rs = preStmt.executeQuery(ssql); 
					if(rs.next())
					{
						num += rs.getLong(1);
					}
					preStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		closeConn(conn);
		return num;
	}
	
	public String getColVals(LinkedHashMap<String, String> colvals)
	{
		StringBuffer colvalssq = new StringBuffer("");
		if(colvals!=null && colvals.size()>0){
			for(String key : colvals.keySet()){
				colvalssq.append(key+" ").append(colvals.get(key)+" and ");
			}
			colvalssq.delete(colvalssq.length()-4, colvalssq.length()-1);
		}
		return colvalssq.toString();		
	}

	public static String getCurrTableName()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
		Date d =new Date();
		String tableName = "statistics_"+formatter.format(d);
		return tableName;
	}
	public boolean isCanUpdate(Connection conn)
	{
		if(validateTableExist(conn,getCurrTableName()))
		{
			return true;
		}
		else
		{
			return createTable(conn);
		}
	}
	
	public static boolean validateTableExist(Connection conn,String tableName)
	{  
		if(isConn(conn))
		{
	        try {
	        	Statement stmt = (Statement) conn.createStatement();
				ResultSet resultSet=stmt.executeQuery("show tables like \"" + tableName + "\"");
				if (resultSet.next()) {  
					stmt.close();
				      return true;  
				}else {  
					stmt.close();
				     return false;  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		return false;  
	}  
	
	public static boolean createTable(Connection conn)
	{
		if(isConn(conn))
		{
			String tableName = getCurrTableName();
			String sql = "CREATE TABLE "+tableName+" ("
					+"id bigint(20) NOT NULL AUTO_INCREMENT,"
					+"adPositionType int(11) DEFAULT NULL,"
					+"appName varchar(64) DEFAULT NULL,"
					+"offerId varchar(64) DEFAULT NULL,"
					+"packageName varchar(64) DEFAULT NULL,"
					+"type int(11) DEFAULT NULL,"
					+"uploadTime datetime DEFAULT NULL,"
					+"userId bigint(20) DEFAULT NULL,"
					+"channel varchar(16) DEFAULT NULL,"
					+"adPositionId bigint(20) DEFAULT NULL,"
					+"PRIMARY KEY (id),"
					+"KEY index_uploadTime (uploadTime),"
					+"KEY index_userId (userId),"
					+"KEY index_adPositionId (adPositionId),"
					+"KEY index_offerId (offerId)"
					+") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8";
			
			try {
				Statement stmt = (Statement) conn.createStatement();
				int c = stmt.executeUpdate(sql);
				stmt.close();
				return c==0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}

	public static boolean isConn(Connection conn)
	{
		try {
			if(conn != null && !conn.isClosed())
				return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public static Connection getConn()
	{
		Connection conn = null;
		String path = GFStatisticsServiceImpl.class.getClassLoader().getResource("jdbc.properties").getPath();  
		Properties prop = new Properties();  
		try {
			FileInputStream in = new FileInputStream(path); 
			prop.load(in);
			// 驱动程序名
			String driver = prop.getProperty("driverClass");  
			String jdbcUrl = prop.getProperty("jdbcUrl");  
			String user = prop.getProperty("user");  
			String password = prop.getProperty("password");  
			
			in.close();
			
			// 加载驱动程序
            Class.forName(driver);
            // 连续数据库
            conn = DriverManager.getConnection(jdbcUrl, user, password);
            
//            if(conn != null && !conn.isClosed())
//            	System.out.println("数据库连接成功！");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public static void closeConn(Connection conn)
	{
		if(conn != null)
		{
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}

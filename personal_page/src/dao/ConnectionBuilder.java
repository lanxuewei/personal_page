package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {
	//public static ThreadLocal<Connection> conn=new ThreadLocal<Connection>();
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		/*if(conn.get() == null){
			conn.set(DriverManager.getConnection(
	    			//"jdbc:mysql://sqld.duapp.com:4050/oArjfZfeHovmaICdnLgD","f41f5672639f4574be5bbecd4e9cf801","4cbbb2e59495493badfc1dbbcc4a1ccd"));
					"jdbc:mysql://localhost:3306/personal_page","root","lanxuewei0227"));
			System.out.println(Thread.currentThread()+" --> "+conn);
		}
		return conn.get();*/
		return DriverManager.getConnection(
    			//"jdbc:mysql://sqld.duapp.com:4050/oArjfZfeHovmaICdnLgD","f41f5672639f4574be5bbecd4e9cf801","4cbbb2e59495493badfc1dbbcc4a1ccd");
    			//"jdbc:mysql://localhost:3306/personal_page","root","lanxuewei0227");
		
				
				"jdbc:mysql://119.23.231.28:3306/personal_page","root","lanxuewei0227");
				//"jdbc:mysql://112.74.99.58:3306/personal_page","root","m2639172582");
	}
}

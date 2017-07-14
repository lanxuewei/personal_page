/*package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementFactory {
	private static Connection conn;
	
	public static void initConn() {
		//保证同一时间只有一个线程可以执行
		synchronized(StatementFactory.class){
			try {				
					//加载MYSQL JDBC驱动程序
			     	Class.forName("com.mysql.jdbc.Driver");        
			    }
			    catch (Exception e) {
			      //输出异常信息	
			      System.out.print("Error loading Mysql Driver!");
			      e.printStackTrace();
			    }
			
		    try {
		    	//"jdbc:mysql://localhost:3306/personal_page","root","lanxuewei0227"
		    	//连接URL为jdbc:mysql//服务器地址/数据库名,后面的2个参数分别是登陆用户名和密码
		    	conn= DriverManager.getConnection(
		    			"jdbc:mysql://sqld.duapp.com:4050/oArjfZfeHovmaICdnLgD","f41f5672639f4574be5bbecd4e9cf801","4cbbb2e59495493badfc1dbbcc4a1ccd");
		    			"jdbc:mysql://localhost:3306/personal_page","root","lanxuewei0227");
		    	}
		    catch (Exception e) {
				//输出异常信息
		    	e.printStackTrace();
			}
		}
	}
	public static Statement openStatement() throws SQLException{
		//如果conn为空，初始化
		if(conn == null){
			initConn();
		}
		//创建Statement
		return (Statement)conn.createStatement();
	}
	public static void closeConn() {
		//关闭conn连接
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}*/
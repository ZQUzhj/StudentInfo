package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	private String dbUrl="jdbc:mysql://localhost:3306/db_jsp";
	private String dbUserName="root";
	private String dbPassword="123456";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	public void closeCon(Connection con)throws Exception{
		if(con!=null) {
			con.close();
		}
	}

	public static void main(String[] arg) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("���ӳɹ�");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

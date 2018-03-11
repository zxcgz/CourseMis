package com.coursemis.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import com.mysql.jdbc.Connection;

public class LocationDAO {
	private static final String INSERT_SQL = "insert into location(T_C_ID,latitude,longitude,T_ID) values(?,?,?,?)";
	private static final String DELETE_SQL = "delete from location where T_C_ID=?";
	private static InitialContext context=null;
	private java.sql.Connection connection=null;
	
	public LocationDAO()
	{
		
	}
	

	public void insertLocation(int tid,int tcid,String latitude,String longitude){
		System.out.println("开始修改location");
		PreparedStatement ps =null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      //������MySQL������
			connection=   DriverManager.getConnection("jdbc:mysql://localhost/coursemanage", "root", "root");
			System.out.println("连接数据库成功");

		}catch(Exception e )
		{
			e.printStackTrace();
			System.out.println("连接数据库失败");
		}
		
		
		try{
			ps = connection.prepareStatement(INSERT_SQL) ;
			ps.setInt(1, tcid);
			ps.setString(2, latitude);
			ps.setString(3, longitude);
			ps.setInt(4, tid) ;
			ps.executeUpdate();
			System.out.println("向location表中插入数据");
			ps.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("插入数据失败");
		}
		
		
		try{
			connection.close();
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		
	}
	

	public void deleteLocation(int tcid){
		PreparedStatement ps =null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      //������MySQL������
			connection=   DriverManager.getConnection("jdbc:mysql://localhost/coursemanage", "root", "root");

		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		try{
			ps = connection.prepareStatement(DELETE_SQL);
			ps.setInt(1, tcid);
			ps.executeUpdate();
			ps.close();
		}catch(Exception e)
		{
			
		}
		
		
		try{
			connection.close();
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	

	public List<String> searchLocation(int cid){
		PreparedStatement ps =null;
		List<String> list = new ArrayList<String>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      //������MySQL������
			connection=   DriverManager.getConnection("jdbc:mysql://localhost/coursemanage", "root", "root");

		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		try{
			ps = connection.prepareStatement("select latitude ,longitude from location where T_C_ID=?");
			ps.setInt(1, cid);
			ResultSet  rst = ps.executeQuery();
			System.out.println(11111);
			if(rst.next())
			{
				System.out.print(rst.getString("latitude"));
				list.add(0, rst.getString("latitude"));
				list.add(1,rst.getString("longitude"));
			}
			rst.close();
			ps.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		try{
			connection.close();
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		return list;
		
		
		
	}
	
	
	
	
}

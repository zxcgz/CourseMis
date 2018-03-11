package com.coursemis.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import com.coursemis.model.Sourcemanage;

public class RegulationDAO {
	private static InitialContext context=null;
	private java.sql.Connection connection=null;
	public List<Integer> getStudenthomeworkListByStudentId(int cid){
		PreparedStatement ps =null;
		List<Integer> list = new ArrayList<Integer>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      //������MySQL������
			connection=   DriverManager.getConnection("jdbc:mysql://localhost/coursemanage", "root", "01395752");

		}catch(Exception e )
		{
			e.printStackTrace();
		}
		try{
			ps = connection.prepareStatement("select * from Studenthomework  where SH_S_ID=?");
			ps.setInt(1, cid);
			ResultSet  rst = ps.executeQuery();
			System.out.println(11111);
			while(rst.next())
			{
				list.add(rst.getInt(2));
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
	
	public List<Integer> getClassStudenthomeworkListBySMId(int sm_id){
		PreparedStatement ps =null;
		List<Integer> list = new ArrayList<Integer>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      //������MySQL������
			connection=   DriverManager.getConnection("jdbc:mysql://localhost/coursemanage", "root", "01395752");

		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		try{
			ps = connection.prepareStatement("SELECT * FROM studenthomework , sourcemanage WHERE sh_sm_id=sm_id AND sm_id =?");
			ps.setInt(1, sm_id);
			ResultSet  rst = ps.executeQuery();
			System.out.println(11111);
			while(rst.next())
			{
				list.add(rst.getInt(1));
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
	
	//SELECT * FROM studenthomework , studentcourse WHERE sh_s_id=sc_s_id AND sc_c_id =1
	
	public List<Integer> getSourceManageListByCourseId(int cid){
		PreparedStatement ps =null;
		List<Integer> list = new ArrayList<Integer>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      //������MySQL������
			connection=   DriverManager.getConnection("jdbc:mysql://localhost/coursemanage", "root", "01395752");

		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		try{
			ps = connection.prepareStatement("select * from sourcemanage  where SM_C_ID=?");
			ps.setInt(1, cid);
			ResultSet  rst = ps.executeQuery();
			System.out.println(11111);
			while(rst.next())
			{
				list.add(rst.getInt("SM_ID"));
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
	
	
	
	
	
	public String getStudenthomeworkName(int shid){
		PreparedStatement ps =null;
		String name = null ;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      //������MySQL������
			connection=   DriverManager.getConnection("jdbc:mysql://localhost/coursemanage", "root", "01395752");

		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		try{
			ps = connection.prepareStatement("select * from studenthomework  where sh_id=?");
			ps.setInt(1, shid);
			ResultSet  rst = ps.executeQuery();
			System.out.println(11111);
			if(rst.next())
			{
				name=rst.getString(4);
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
		return name;
		
		
		
	}
	
	
	
	
	public List<Sourcemanage> getSourcemanage(int cid,int type ){
		PreparedStatement ps =null;
		List<Sourcemanage> sml= new ArrayList<Sourcemanage>() ;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      //������MySQL������
			connection=   DriverManager.getConnection("jdbc:mysql://localhost/coursemanage", "root", "01395752");

		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		try{
			ps = connection.prepareStatement("select * from sourcemanage  where SM_C_ID=? and SM_Type=?");
			ps.setInt(1, cid);
			ps.setInt(2, type);
			ResultSet  rst = ps.executeQuery();
			System.out.println(11111);
			while(rst.next())
			{
				Sourcemanage sm = new Sourcemanage();
				sm.setSmId(rst.getInt(1));
				sm.setSmName(rst.getString(3));
				sml.add(sm);
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
		return sml;
		
		
		
	}
	
	
	
	
	
	
	
}

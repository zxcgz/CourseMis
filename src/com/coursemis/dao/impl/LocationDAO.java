package com.coursemis.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.ILocationDAO;
import com.coursemis.model.Location;
import com.mysql.jdbc.Connection;

public class LocationDAO extends BaseDAO implements ILocationDAO {
	private static final String INSERT_SQL = "insert into location(T_C_ID,latitude,longitude,T_ID) values(?,?,?,?)";
	private static final String DELETE_SQL = "delete from location where T_C_ID=?";
	private static InitialContext context = null;
	private java.sql.Connection connection = null;

	public void insertLocation(Location location) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.save(location);
			beginTransaction.commit();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.close();
		}
		// session.save(arg0)

		/*
		 * System.out.println("开始修改location"); PreparedStatement ps = null; try
		 * { Class.forName("com.mysql.jdbc.Driver").newInstance(); //
		 * ������MySQL������ connection = DriverManager.getConnection(
		 * "jdbc:mysql://localhost/coursemanage", "root", "root");
		 * System.out.println("连接数据库成功");
		 * 
		 * } catch (Exception e) { e.printStackTrace();
		 * System.out.println("连接数据库失败"); }
		 * 
		 * try { ps = connection.prepareStatement(INSERT_SQL); ps.setInt(1,
		 * tcid); ps.setString(2, latitude); ps.setString(3, longitude);
		 * ps.setInt(4, tid); ps.executeUpdate();
		 * System.out.println("向location表中插入数据"); ps.close(); } catch (Exception
		 * e) { e.printStackTrace(); System.out.println("插入数据失败"); }
		 * 
		 * try { connection.close(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

	}

	public void deleteLocation(Location location) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		session.delete(location);
		beginTransaction.commit();
		session.close();
		/*
		 * PreparedStatement ps = null; try {
		 * Class.forName("com.mysql.jdbc.Driver").newInstance(); //
		 * ������MySQL������ connection = DriverManager.getConnection(
		 * "jdbc:mysql://localhost/coursemanage", "root", "root");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * try { ps = connection.prepareStatement(DELETE_SQL); ps.setInt(1,
		 * tcid); ps.executeUpdate(); ps.close(); } catch (Exception e) {
		 * 
		 * }
		 * 
		 * try { connection.close(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

	}

	public Location searchLocation(int cid) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();

		try {
			Query createQuery = session
					.createQuery("from Location location where location.course.CId = "
							+ cid);
			Location location = (Location) createQuery.uniqueResult();
			beginTransaction.commit();
			session.close();
			return location;
		} catch (Exception e) {
			// TODO: handle exception
			session.close();
		}
		return null;
		/*
		 * PreparedStatement ps = null; List<String> list = new
		 * ArrayList<String>(); try {
		 * Class.forName("com.mysql.jdbc.Driver").newInstance(); //
		 * ������MySQL������ connection = DriverManager.getConnection(
		 * "jdbc:mysql://localhost/coursemanage", "root", "root");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * try { ps = connection .prepareStatement(
		 * "select latitude ,longitude from location where T_C_ID=?");
		 * ps.setInt(1, cid); ResultSet rst = ps.executeQuery();
		 * System.out.println(11111); if (rst.next()) {
		 * System.out.print(rst.getString("latitude")); list.add(0,
		 * rst.getString("latitude")); list.add(1, rst.getString("longitude"));
		 * } rst.close(); ps.close(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * try { connection.close(); } catch (Exception e) {
		 * e.printStackTrace(); } return list;
		 */

	}

}

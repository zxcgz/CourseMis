package com.coursemis.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.ICoursetimeDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Coursetime;

public class CoursetimeDAO extends BaseDAO implements ICoursetimeDAO {
	/* 数据库中添加一条  */
	public boolean saveCourseTime(Coursetime instance) {
		//log.debug("saving TbNotice instance");
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			//log.debug("saving TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("saving TbNotice instance failed");
		}
		session.close();
		return false;
	}
	/* 数据库中修改一条  */
	public boolean updateCourseTime(Coursetime instance) {
		//log.debug("updateCourseTime");
		Session session = getSession();
		Coursetime coursetime = (Coursetime)session.get(Coursetime.class, instance.getCtId());
		try{
			coursetime.setCtWeekDay(instance.getCtWeekDay());
			coursetime.setCtStartClass(instance.getCtStartClass());
			coursetime.setCtEndClass(instance.getCtEndClass());
			coursetime.setCtAddress(instance.getCtAddress());
			
			Transaction tx = session.beginTransaction();
			session.update(coursetime);
			tx.commit();
			//log.debug("saving TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("saving TbNotice instance failed");
		}
		session.close();
		return false;
	}
	/* 数据库中删除一条  */
	public boolean deleteCoursetime(int coursetimeid) {
		//log.debug("saving TbNotice instance");
		System.out.println("delete Coursetime instance...");
		Session session = getSession();
		Coursetime coursetime  = (Coursetime)session.get(Course.class, coursetimeid);
		try{
			System.out.println("try deling TbNotice instance...");
			if(coursetime!=null){
			Transaction tx = session.beginTransaction();
			session.delete(coursetime);
			tx.commit();
			//log.debug("saving TbNotice instance successful");
			return true;
			}
		} catch (RuntimeException e){
			//log.debug("saving TbNotice instance failed");
			e.printStackTrace();
		}
		session.close();
		return false;
	}
	/* 通过c_id数据库中删除一条  */
	public boolean deleteCoursetimeByCid(int courseid) {
		//log.debug("delete TbNotice instance");
		System.out.println("delete TbNotice instance:");
		Session session = getSession();
		try {
			System.out.println("try delete TbNotice instance:");
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete Coursetime coursetime where coursetime.course.CId = "+courseid);
			query.executeUpdate();
			tx.commit();
			//log.debug("delete TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("delete TbNotice instance failed");
		}
		session.close();
		return false;
	}
	public List<Coursetime> getCoursetimeByCourseId(int courseid){
		Session session = getSession();
		List<Coursetime> list = null;
		//Coursetime coursetime = null;
		System.out.println("getCoursetimeByCourseId:");
		try {
			System.out.println("try...");
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Coursetime coursetime where coursetime.course.CId = "+courseid);
			list = query.list();
			//coursetime = (Coursetime)list.get(0);
			query.executeUpdate();
			tx.commit();
			//log.debug("get TbUser instance successful");
			System.out.println("get TbUser instance successful");
		} catch (RuntimeException e){
			//log.debug("get TbUser instance failed");
		}
		session.close();
		return list;
	}
	public Coursetime getCoursetime(int cid){
		System.out.println("getCoursetime:");
		Session session= getSession();
		List<Coursetime> list = null;
		Coursetime ct = null;
		try {
			System.out.println("trying...getCourseByTeacherId");
			Transaction tx = session.beginTransaction();
			//澶栭敭鏌ヨ
			
			Query query = session.createQuery("from Coursetime c where c.course.CId = "+cid);
			list = query.list();
			ct = (Coursetime)list.get(0);
			//query.executeUpdate();///?
			tx.commit();
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed");
		}
		return ct;
	}
	
	/*public Coursetime getCoursetime(int cid){
		System.out.println("getCoursetime:");
		Session session=null;
		try{
			session = HibernateSessionFactory.currentSession();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		List<Coursetime> list = null;
		Coursetime ct = null;
		try {
			System.out.println("trying...getCourseByTeacherId");
			Transaction tx = session.beginTransaction();
			//澶栭敭鏌ヨ
			
			Query query = session.createQuery("from Coursetime c where c.course.CId = "+cid);
			list = query.list();
			ct = (Coursetime)list.get(0);
			//query.executeUpdate();///?
			tx.commit();
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed");
		}finally{System.out.print("closing.................");
		HibernateSessionFactory.closeSession();		}return ct;
	}*/
}

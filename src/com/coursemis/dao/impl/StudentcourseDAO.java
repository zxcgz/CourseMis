package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.action.HibernateSessionFactory;
import com.coursemis.dao.IStudentcourseDAO;
import com.coursemis.model.Studentcourse;

public class StudentcourseDAO extends BaseDAO implements IStudentcourseDAO {
	
	/* 数据库中添加一条  */
	public boolean saveStudentcourse(Studentcourse instance) {
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
	
	
	
	
	public List<Studentcourse> getStudentcourseByCId(int courseid){
		System.out.println("getStudentByCourseId:");
		Session session = getSession();
		List<Studentcourse> list = null;
		//Course course = null;
		try {
			System.out.println("trying...");
			Transaction tx = session.beginTransaction();
			//外键查询
			Query query = session.createQuery("from Studentcourse studentcourse where studentcourse.course.CId = "+courseid);
			list = query.list();
			//query.executeUpdate();///?
			tx.commit();
		} catch (RuntimeException e){  
			e.printStackTrace() ;
		}
		session.close();
		return list;
	}
	
	public List<Studentcourse> getStudentcourseElseByCId(int courseid){
		System.out.println("getStudentByCourseId:");
		Session session = getSession();
		List<Studentcourse> list = null;
		//Course course = null;
		try {
			System.out.println("trying...");
			Transaction tx = session.beginTransaction();
			//外键查询
			Query query = session.createQuery("from Studentcourse studentcourse where studentcourse.course.CId != "+courseid);
			list = query.list();
			query.executeUpdate();///?
			tx.commit();
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed"); 
		}
		session.close();
		return list;
	}
	
	public List<Studentcourse> getStudentcourseBySId(int studentid){
		System.out.println("getStudentcourseBySId:");
		Session session = getSession();
		List<Studentcourse> list = null;
		//Course course = null;
		try {
			System.out.println("trying...");
			Transaction tx = session.beginTransaction();
			//外键查询
			Query query = session.createQuery("from Studentcourse studentcourse where studentcourse.student.SId = "+studentid);
			list = query.list();
			query.executeUpdate();///?
			tx.commit();
			System.out.println("get Studentcourse instance successful");
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed"); 
		}
		session.close();
		return list;
	}
	
	
	/* 根据CID删除Studentcourse */
	public boolean deleteNoticeStudentcourse(int courseid) {
		//log.debug("delete TbNotice instance");
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete Studentcourse studentcourse where studentcourse.course.CId = "+courseid);
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
	/* 根据CID和SID删除Studentcourse */
	public boolean deleteStudentOfCourse(int courseid,int studentid) {
		//log.debug("delete TbNotice instance");
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete Studentcourse studentcourse where studentcourse.course.CId = "+courseid+" and studentcourse.student.SId = "+studentid);
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
	
	
	public Studentcourse searchStudentcourse(int scId){
		Session session = HibernateSessionFactory.currentSession();
		List<Studentcourse> list = null;
		Studentcourse s=null;
		System.out.println("searchStudentlist:");///
		try {
			System.out.println("trying");///
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Studentcourse studentcourse where studentcourse.scId = "+scId);
			//list = query.list();
			s=(Studentcourse)query.uniqueResult();
			
			query.executeUpdate();
			tx.commit();
			//log.debug("search TbUser instance successful");
		} catch (RuntimeException e){
			//log.debug("search TbUser instance failed");
		}
		HibernateSessionFactory.closeSession();
		return s;
	}
	
	public boolean updateStudentcourse(Studentcourse s) {
		//log.debug("updateing Teacher instance");
		System.out.println("updateing Studentcourse instance");
		Session session = HibernateSessionFactory.currentSession();
		
		if(s!=null){
		try {
			
				
				Transaction tx = session.beginTransaction();
				session.update(s);
				tx.commit();
			
			//log.debug("updateing TbNotice instance successful");
			System.out.println("updateing TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("updateing TbNotice instance failed");
		}finally{
			HibernateSessionFactory.closeSession();
		}
		}
		
		return false;
	}
	
}

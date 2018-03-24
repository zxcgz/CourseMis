package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.coursemis.action.HibernateSessionFactory;
import com.coursemis.dao.ISourceManageDAO;
import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Student;
import com.coursemis.model.Studentcourse;
import com.coursemis.model.Studenthomework;
import com.coursemis.model.Teacher;


public class SourceManageDAO extends BaseDAO implements ISourceManageDAO {
	
	public boolean saveSourceManage(Sourcemanage instance) {
		//log.debug("saving TbNotice instance");
		System.out.println("saving TbNotice instance...");
		Session session =getSession();
		System.out.println("session:"+session);
		try{
			System.out.println("try saving TbNotice instance...");
			Transaction tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			//log.debug("saving TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("saving TbNotice instance failed");
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
		return false;
	}
	
	public List<Sourcemanage> getSourcemanageByCourseIdAndTeacherId(int cid,String tid){
		//Session session = HibernateSessionFactory.currentSession();
		Session session = getSession() ;
		List<Sourcemanage> list = null;
		System.out.println("getSourcemanageByCourseIdAndTeacherId:");///
		try {
			System.out.println("trying");///
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Sourcemanage sm where sm.course.CId = '"+cid+"' and sm.smUploader = '"+tid+"'");
			list = query.list();
			
			
		//	query.executeUpdate();
			tx.commit();
			//log.debug("search TbUser instance successful");
		} catch (RuntimeException e){
			//log.debug("search TbUser instance failed");
			e.printStackTrace();
		}finally
		{
		HibernateSessionFactory.closeSession();
		return list;
		}
	}
	
	
	public Sourcemanage getSourcemanageById(int smid){
		Session session = HibernateSessionFactory.currentSession();
		List list = null;
		Sourcemanage sm = null;
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Sourcemanage s where s.smId = "+smid);
			list = query.list();
			sm = (Sourcemanage)list.get(0);
			query.executeUpdate();
			tx.commit();
			//log.debug("get TbUser instance successful");
			System.out.println("get Sourcemanage instance successful");
		} catch (RuntimeException e){
			//log.debug("get TbUser instance failed");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();}
		return sm;
	}
	public boolean saveSH(Studenthomework instance) {
		//log.debug("saving TbNotice instance");
		System.out.println("saving TbNotice instance...");
		Session session = HibernateSessionFactory.currentSession();
		try{
			System.out.println("try saving TbNotice instance...");
			Transaction tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			//log.debug("saving TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("saving TbNotice instance failed");
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
		return false;
	}
	
	public List<Sourcemanage> getSourcemanageByType(int type){
		Session session = getSession();
		List list = null;
		Sourcemanage sm = null;
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Sourcemanage s where s.smType = "+type);
			list = query.list();
			
			
			tx.commit();
			//log.debug("get TbUser instance successful");
			System.out.println("get Sourcemanage instance successful");
		} catch (RuntimeException e){
			//log.debug("get TbUser instance failed");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();}
		return list;
	}

	@Override
	public List<Sourcemanage> getSourcemanageByTid(int tid) {
		Session session = getSession();
		List list = null;
		System.out.println("dao"+session);
		Sourcemanage sm = null;
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Sourcemanage s where s.smUploader = "+tid);
			list = query.list();
			System.out.println(list);
			tx.commit();
		} catch (RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	
	
}

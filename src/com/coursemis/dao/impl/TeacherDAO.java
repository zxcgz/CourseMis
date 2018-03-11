package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.action.HibernateSessionFactory;
import com.coursemis.dao.ITeacherDAO;
import com.coursemis.model.Teacher;

public class TeacherDAO extends BaseDAO implements ITeacherDAO {
	public Teacher searchTeacher(String teachername,String password){
		Session session = getSession();
		List list = null;
		Teacher teacher = null;
		System.out.println("searchTeacher:");///
		try {
			System.out.println("trying");///
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Teacher tea where tea.TName = '"+teachername+"' and tea.TPassword = '"+password+"'");
			list = query.list();
			teacher = (Teacher)list.get(0);
			System.out.println("BBB:"+teacher.getTTel());///
			query.executeUpdate();
			tx.commit();
			//log.debug("search TbUser instance successful");
		} catch (RuntimeException e){
			//log.debug("search TbUser instance failed");
		}
		session.close();
		return teacher;
	}
	
	
	public Teacher getTeacherById(int teacherid){
		Session session = getSession();
		List list = null;
		Teacher teacher = null;
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Teacher teacher where teacher.TId = "+teacherid);
			list = query.list();
			teacher = (Teacher)list.get(0);
			query.executeUpdate();
			tx.commit();
			//log.debug("get TbUser instance successful");
			System.out.println("get TbUser instance successful");
		} catch (RuntimeException e){
			//log.debug("get TbUser instance failed");
		}
		session.close();
		return teacher;
	}
	
	/* 根据ID修改密码*/
	public boolean updateTeacherPwd(Teacher instance) {
		//log.debug("updateing Teacher instance");
		System.out.println("updateing Teacher instance");
		Session session = getSession();
		String password_new = instance.getTPassword();
		Teacher teacher = (Teacher)session.get(Teacher.class, instance.getTId());
		if(teacher!=null){
		try {
			
				teacher.setTPassword(password_new);
				Transaction tx = session.beginTransaction();
				session.update(teacher);
				tx.commit();
			
			//log.debug("updateing TbNotice instance successful");
			System.out.println("updateing TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("updateing TbNotice instance failed");
		}
		}
		session.close();
		return false;
	}
	
	public Teacher getTeacherByName(String tname){
		Session session = HibernateSessionFactory.currentSession();
		List list = null;
		Teacher teacher = null;
		try {
			System.out.println("get TbUser ");
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Teacher  t where t.TName = '"+tname+"'");
			System.out.println("get TbUser instance 1");
		//	query.setParameter(0, tname);
			
			teacher = (Teacher)query.uniqueResult();
			
			tx.commit();
			//log.debug("get TbUser instance successful");
			System.out.println("get TbUser instance successful");
		} catch (RuntimeException e){
			//log.debug("get TbUser instance failed");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();}
		return teacher;
	}
	
}

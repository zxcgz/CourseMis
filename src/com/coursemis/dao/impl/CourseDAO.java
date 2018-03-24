package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.action.HibernateSessionFactory;
import com.coursemis.dao.ICourseDAO;
import com.coursemis.model.Course;

public class CourseDAO extends BaseDAO implements ICourseDAO {
	public List<Course> getCourseByTeacherId(int teacherid){
		System.out.println("getCourseByTeacherId:");
		Session session = getSession();
		List<Course> list = null;
		//Course course = null;
		try {
			System.out.println("trying...");
			Transaction tx = session.beginTransaction();
			//外键查询
			Query query = session.createQuery("from Course course where course.teacher.TId = "+teacherid);
			list = query.list();
			//course = (Course)list.get(0);
			query.executeUpdate();///?
			tx.commit();
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed"); 
		}
		session.close();
		return list;
	}
	/* 数据库中添加一条  */
	public boolean saveCourse(Course instance) {
		//log.debug("saving TbNotice instance");
		System.out.println("saving TbNotice instance...");
		Session session = getSession();
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
		session.close();
		return false;
	}
	/* 数据库中修改一条  */
	public boolean updateCourse(Course instance) {
		//log.debug("saving TbNotice instance");
		System.out.println("updateCourse...");
		Session session = getSession();
		Course course = (Course)session.get(Course.class, instance.getCId());
		try{
			System.out.println("try updateCourse...");
			course.setCName(instance.getCName());
			course.setCFlag(instance.getCFlag()) ;
			Transaction tx = session.beginTransaction();
			session.update(course);
			tx.commit();
			//log.debug("saving TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("saving TbNotice instance failed");
			e.printStackTrace();
		}
		session.close();
		return false;
	}
	/* 数据库中删除一条  */
	public boolean deleteCourse(int courseid) {
		//log.debug("saving TbNotice instance");
		System.out.println("deleteCourse...");
		Session session = getSession();
		Course course  = (Course)session.get(Course.class, courseid);
		try{
			System.out.println("try deleteCourse...");
			if(course!=null){
			Transaction tx = session.beginTransaction();
			session.delete(course);
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
	public Course getCourseById(int courseid){
		System.out.println("getCourseById:");
		Session session = getSession();
		Course course = null;
		try {
			System.out.println("trying...");
			course = (Course)session.get(Course.class, courseid);
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed");
		}
		session.close();
		return course;
	}
}

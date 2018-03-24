package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.ICourseevaluationDAO;
import com.coursemis.model.Courseevaluation;
import com.coursemis.model.Coursetime;

public class CourseevaluationDAO extends BaseDAO implements ICourseevaluationDAO {
	
	/* 数据库中添加一条  */
	public boolean saveCourseevaluation(Courseevaluation instance) {
		//log.debug("saving TbNotice instance");
		System.out.println("saving Courseevaluation instance...");
		Session session = getSession();
		try{
			System.out.println("try saving Courseevaluation instance...");
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
	
	public List<Courseevaluation> getCourseevaluationByCourseId(int courseid){
		Session session = getSession();
		List<Courseevaluation> list = null;
		//Coursetime coursetime = null;
		System.out.println("getCourseevaluationByCourseId:");
		try {
			System.out.println("try...");
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Courseevaluation courseevaluation where courseevaluation.course.CId = "+courseid);
			list = query.list();
			//coursetime = (Coursetime)list.get(0);
			query.executeUpdate();
			tx.commit();
			//log.debug("get TbUser instance successful");
			System.out.println("get Courseevaluation successful");
		} catch (RuntimeException e){
			//log.debug("get TbUser instance failed");
		}
		session.close();
		return list;
	}
	
}

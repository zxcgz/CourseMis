package com.coursemis.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.IPeriodDAO;
import com.coursemis.model.Period;

/**
 * 课时
 * 
 * @author lenovo
 * 
 */
public class PeriodDAO extends BaseDAO implements IPeriodDAO {

	public boolean insert(Period period) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.save(period);
			beginTransaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		return false;
	}

	public boolean addPeriodNum(Period period) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			Query createQuery = session
					.createQuery("from Period p where p.course.CId = "
							+ period.getCourse().getCId());
			Period p = (Period) createQuery.uniqueResult();
			if (p != null) {
				p.setPNum(p.getPNum() + 1);
				session.save(p);
				beginTransaction.commit();
				session.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.close() ;
		}
		return false;
	}

	public int getPeriodNum(Period period) {
		Session session = getSession() ;
		Transaction beginTransaction = session.beginTransaction() ;
		try {
			Query createQuery = session.createQuery("from Period p where p.course.CId = "+period.getCourse().getCId()) ;
			Period result = (Period) createQuery.uniqueResult() ;
			//createQuery.executeUpdate() ;
			beginTransaction.commit() ;
			session.close() ;
			return result.getPNum() ;
		} catch (Exception e) {
			e.printStackTrace() ;
			session.close() ;
		}
		return 0;
	}

	public boolean delete(Period period) {
		Session session = getSession() ;
		Transaction beginTransaction = session.beginTransaction() ;
		try {
			Query createQuery = session.createQuery("delete from Period p where p.course.CId = "+period.getCourse().getCId()) ;
			createQuery.executeUpdate() ;
			beginTransaction.commit() ;
			session.close() ;
			return true ;
		} catch (Exception e) {
			e.printStackTrace() ;
			session.close() ;
		}
		return false;
	}

	public Period getPeriod(int cid) {
		Session session = getSession() ;
		Transaction beginTransaction = session.beginTransaction() ;
		try {
			Query createQuery = session.createQuery("from Period p where p.course.CId = "+cid) ;
			Period result = (Period) createQuery.uniqueResult() ;
			beginTransaction.commit() ;
			session.close() ;
			return result ;
		} catch (Exception e) {
			e.printStackTrace() ;
			session.close() ;
		}
		return null;
	}

}

package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.ICourseDAO;
import com.coursemis.dao.IEvaluationDAO;
import com.coursemis.dao.IPeriodDAO;
import com.coursemis.dao.IScoreDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Evaluation;
import com.coursemis.model.Period;
import com.coursemis.model.Score;
import com.google.gson.Gson;

public class EvaluationDAO extends BaseDAO implements IEvaluationDAO {

	private IPeriodDAO periodDAO;
	private ICourseDAO courseDAO;

	public boolean insertOrUpdate(int cid, int pNum, List<Double> scores) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			System.out.println("insertOrUpdate1");
			Gson gson = new Gson();
			System.out.println("insertOrUpdate2");
			String json = gson.toJson(scores);
			System.out.println("insertOrUpdate3");
			// 先查询
			Query createQuery = session
					.createQuery("from Evaluation e where e.course.CId = "
							+ cid + " and e.EPid = " + pNum);
			System.out.println("insertOrUpdate4");
			Object uniqueResult = createQuery.uniqueResult();
			System.out.println("insertOrUpdate5");
			if (uniqueResult == null) {
				// 没有数据
				System.out.println("没有数据");
				Evaluation evaluation = new Evaluation();
				Period period = periodDAO.getPeriod(cid);
				Course course = courseDAO.getCourseById(cid);
				evaluation.setCourse(course);
				evaluation.setEPid(period.getPNum());
				evaluation.setEGrades(json);
				session.save(evaluation);
			} else {
				// 存在数据
				Evaluation evaluation = (Evaluation) uniqueResult;
				evaluation.setEGrades(json);
				session.update(evaluation);

			}
			beginTransaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			beginTransaction.rollback();
			session.close();
		}
		return false;
	}

	public List<Evaluation> getScore(int cid) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			Query createQuery = session
					.createQuery("from Evaluation e where e.course.CId = "
							+ cid);
			if (createQuery == null) {
				session.close();
				return null;
			} else {
				List list = createQuery.list();
				session.close();
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}

		return null;
	}

	public Evaluation getScore(int cid, int pNum) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			Query createQuery = session
					.createQuery("from Evaluation e where e.course.CId = "
							+ cid + " and e.EPid = " + pNum);
			if (createQuery == null) {
				session.close();
				return null;
			} else {
				Evaluation uniqueResult = (Evaluation) createQuery
						.uniqueResult();
				session.close();
				return uniqueResult;
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}

		return null;
	}

	public boolean delete(int cid) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			List<Evaluation> score = getScore(cid);
			for (Evaluation score2 : score) {
				session.delete(score2);
				beginTransaction.commit();
			}
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			beginTransaction.rollback();
		}
		return false;
	}

	public IPeriodDAO getPeriodDAO() {
		return periodDAO;
	}

	public void setPeriodDAO(IPeriodDAO periodDAO) {
		this.periodDAO = periodDAO;
	}

	public ICourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

}

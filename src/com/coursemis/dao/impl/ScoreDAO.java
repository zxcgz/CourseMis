package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.IScoreDAO;
import com.coursemis.model.Score;

public class ScoreDAO extends BaseDAO implements IScoreDAO {

	public Score getScore(int cid, int sid, int periodId) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			Query createQuery = session
					.createQuery("from Score s where s.course.CId =  " + cid
							+ " and s.student.SId = " + sid + " and s.period.PNum = "
							+ periodId);
			Score score = (Score) createQuery.uniqueResult();
			beginTransaction.commit();
			session.close();
			return score;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	public boolean insertOrUpdate(Score score) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			Query createQuery = session
					.createQuery("from Score s where s.course.CId = "
							+ score.getCourse().getCId() + " and s.student.SId = "
							+ score.getStudent().getSId()+ " and s.period.PNum = "
							+ score.getPeriod().getPId());
			Score result = (Score) createQuery.uniqueResult();
			if (result == null) {
				// 没有数据
				//保存
				session.save(score) ;
			} else {
				//有数据
				//更新
				result.setSAtten(result.getSAtten() + score.getSAtten());
				result.setSCall(result.getSCall() + score.getSCall());
				result.setSQuiz(result.getSQuiz() + score.getSQuiz());
				session.update(result);
			}
			beginTransaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		return false;
	}

	public boolean delete(Score score) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			Query createQuery = session
					.createQuery("delete from Score s where s.course.CId = "
							+ score.getCourse().getCId() + " and s.student.SId = "
							+ score.getStudent().getSId()+ " and s.period.PNum = "
							+ score.getPeriod().getPId());
			createQuery.executeUpdate();
			beginTransaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		return false;
	}

	public List<Score> getScoresAllPeriodBySId(int cid, int sid) {
		Session session = getSession() ;
		Transaction beginTransaction = session.beginTransaction() ;
		try {
			Query createQuery = session.createQuery("from Score s where s.course.CId = "
							+ cid + " and s.student.SId = "
							+ sid) ;
			List<Score> list = createQuery.list() ;
			beginTransaction.commit() ;
			session.close() ;
			return list ;
		} catch (Exception e) {
			e.printStackTrace() ;
			session.close() ;
		}
		return null;
	}

	public List<Score> getScoresOnePeriodBySid(int cid, int sid, int periodId) {
		Session session = getSession() ;
		Transaction beginTransaction = session.beginTransaction() ;
		try {
			Query createQuery = session.createQuery("from Score s where s.course.CId = "
							+ cid + " and s.student.SId = "
							+ sid+" and s.period.PNum = "
							+periodId) ;
			List<Score> list = createQuery.list() ;
			beginTransaction.commit() ;
			session.close() ;
			return list ;
		} catch (Exception e) {
			e.printStackTrace() ;
			session.close() ;
		}
		return null;
	}

	public boolean insertOrUpdate(int cid, int sid, int periodId) {
		/*
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			Query createQuery = session
					.createQuery("from Score s where s.course.CId = "
							+ cid + " and s.student.SId = "
							+ sid+ " and s.period.PNum = "
							+ periodId);
			Score result = (Score) createQuery.uniqueResult();
			if (result == null) {
				// 没有数据
				//保存
				session.save(score) ;
			} else {
				//有数据
				//更新
				result.setSAtten(result.getSAtten() + score.getSAtten());
				result.setSCall(result.getSCall() + score.getSCall());
				result.setSQuiz(result.getSQuiz() + score.getSQuiz());
				session.update(result);
			}
			beginTransaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		*/
		return false;
	}

}

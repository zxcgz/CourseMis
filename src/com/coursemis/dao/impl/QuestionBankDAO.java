package com.coursemis.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.IQuestionBankDAO;
import com.coursemis.model.Questionbank;

public class QuestionBankDAO extends BaseDAO implements IQuestionBankDAO{

	public boolean insert(Questionbank questionbank) {
		Session session = getSession() ;
		Transaction beginTransaction = session.beginTransaction() ;
		try {
			Serializable save = session.save(questionbank) ;
			session.close() ;
			if(save!=null){
				
				return true ;
			}else {
				return false ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
			session.close() ;
		}
		return false;
	}

	public boolean insert(List<Questionbank> questionbanks) {
		for (Questionbank questionbank : questionbanks) {
			insert(questionbank) ;
		}
		return true;
	}

	public List<Questionbank> getQuestionbank(int cid, int periodId) {
		Session session = getSession() ;
		Transaction beginTransaction = session.beginTransaction() ;
		try {
			Query createQuery = session.createQuery("from Questionbank bank where bank.course.CId = "+cid+" and bank.period.PId = "+periodId) ;
			List list = createQuery.list() ;
			beginTransaction.commit() ;
			session.close() ;
			return list ;
		} catch (Exception e) {
			e.printStackTrace() ;
			session.close() ;
		}
		return null;
	}

	
}

package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.coursemis.dao.ISignInDAO;
import com.coursemis.model.Signin;

public class SignInDAO extends BaseDAO implements ISignInDAO {
	
	
	public static int SUCCESS = 1 ;
	public static int FAIL = 0 ;
	

	public boolean signIn(Signin signin) {
		Session session = getSession() ;
		try {
			
			Transaction beginTransaction = session.beginTransaction() ;
			session.save(signin) ;
			beginTransaction.commit() ;
			session.close() ;
			return true;
		} catch (RuntimeException e) {
			// TODO: handle exception
			session.close() ;
		}
		return false ;
	}

	public boolean isSignIn(int tid, int cid, int sid) {
		//查询
		Session session = getSession() ;
		try {
			Transaction beginTransaction = session.beginTransaction() ;
			Query createQuery = session.createQuery("from Signin signin where signin.teacher.TId = "+tid+" and signin.course.CId = "+cid+
					" and signin.student.SId = "+sid) ;
			beginTransaction.commit() ;
			Signin result = (Signin) createQuery.uniqueResult() ;
			if (result!=null) {
				//存在信息
				Integer flag = result.getFlag() ;
				if (flag.equals(SUCCESS)) {
					//已经签到
					session.close() ;
					return true ;
				}
			}else {
				//没有信息，认为是没有签到
				session.close() ;
				return false ;
			}
			
		} catch (RuntimeException e) {
			// TODO: handle exception
			session.close() ;
		} 
		return false;
	}

	public boolean deleteSignIn(int tid, int cid) {
		Session session = getSession() ;
		try {
			Transaction beginTransaction = session.beginTransaction() ;
			session.delete("from Signin signin where signin.teacher.TId = "+tid+" and signin.course.CId = "+cid) ;
			beginTransaction.commit() ;
			session.close() ;
			return true ;
		} catch (RuntimeException e) {
			// TODO: handle exception
			session.close();
		}
		return false;
	}

	public List<Signin> getSigninList(int tid, int cid) {
		Session session = getSession() ;
		try {
			Transaction beginTransaction = session.beginTransaction() ;
			Query createQuery = session.createQuery("from Signin signin where signin.teacher.TId = "+tid+" and signin.course.CId = "+cid) ;
			List list = createQuery.list() ;
			beginTransaction.commit() ;
			session.close() ;
			return list ;
		} catch (RuntimeException e) {
			// TODO: handle exception
			session.close() ;
		}
		return null;
	}

	public Signin getSignin(int tid, int cid, int sid) {
		Session session = getSession() ;
		try {
			Transaction beginTransaction = session.beginTransaction() ;
			Query createQuery = session.createQuery("from Signin signin where signin.teacher.TId = "+tid+" and signin.course.CId = "+cid+" and signin.student.SId = "+sid) ;
			Signin signin = (Signin) createQuery.uniqueResult() ;
			beginTransaction.commit() ;
			session.close() ;
			return signin ;
		} catch (RuntimeException e) {
			// TODO: handle exception
			session.close() ;
		}
		return null;
	}

}

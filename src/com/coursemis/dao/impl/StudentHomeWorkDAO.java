package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.action.HibernateSessionFactory;
import com.coursemis.dao.IStudentHomeWorkDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Studenthomework;

public class StudentHomeWorkDAO extends BaseDAO implements IStudentHomeWorkDAO {

	public List<Studenthomework> getStudentHomeworkListBySMId(int smid) {
		System.out.println("getCourseBySMId:");
		Session session = null;
		try {
			session = HibernateSessionFactory.currentSession();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Studenthomework> list = null;
		// Course course = null;
		try {
			System.out.println("trying...getCourseBySMId");
			Transaction tx = session.beginTransaction();
			// 澶栭敭鏌ヨ

			Query query = session
					.createQuery("from Studenthomework sh where sh.sourcemanage.smId = "
							+ smid);
			list = query.list();
			// course = (Course)list.get(0);
			// query.executeUpdate();///?
			tx.commit();
			// log.debug("get TbUser instance successful");
		} catch (RuntimeException e) {
			// log.debug("get TbUser instance failed");
		} finally {
			System.out.print("closing.................");
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	/* 鏁版嵁搴撲腑娣诲姞涓�潯 */
	public boolean saveCourse(Course instance) {
		// log.debug("saving TbNotice instance");
		System.out.println("saving TbNotice instance...");
		Session session = HibernateSessionFactory.currentSession();
		try {
			System.out.println("try saving TbNotice instance...");
			Transaction tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			// log.debug("saving TbNotice instance successful");
			return true;
		} catch (RuntimeException e) {
			// log.debug("saving TbNotice instance failed");
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
		return false;
	}

	public Studenthomework getStudenthomework(int shid) {
		System.out.println("getStudenthomework:");
		Session session = null;
		try {
			session = HibernateSessionFactory.currentSession();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// List<Course> list = null;
		Studenthomework course = null;
		try {
			System.out.println("trying...getStudenthomework");
			Transaction tx = session.beginTransaction();
			// 澶栭敭鏌ヨ

			Query query = session
					.createQuery("from Studenthomework  where shId = " + shid);
			course = (Studenthomework) query.uniqueResult();
			// course = (Course)list.get(0);
			query.executeUpdate();// /?
			tx.commit();
			// log.debug("get TbUser instance successful");
		} catch (RuntimeException e) {
			// log.debug("get TbUser instance failed");
		} finally {
			System.out.print("closing.................");
			HibernateSessionFactory.closeSession();
		}
		return course;
	}

	/*
	 * public List<Studenthomework> getStudenthomeworkListByStudentId(int sid){
	 * System.out.println("getStudenthomeworkListByStudentId:"); Session
	 * session=null; try{ session = HibernateSessionFactory.currentSession();
	 * }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * //List<Course> list = null; List<Studenthomework> course = null; try {
	 * System.out.println("trying...getStudenthomework"); Transaction tx =
	 * session.beginTransaction(); //澶栭敭鏌ヨ
	 * 
	 * Query query =
	 * session.createQuery("from Studenthomework sh  where sh.student.SId = "
	 * +sid); course=(List<Studenthomework>)query.list(); //course =
	 * (Course)list.get(0); //query.executeUpdate();///? tx.commit();
	 * //log.debug("get TbUser instance successful"); } catch (RuntimeException
	 * e){ //log.debug("get TbUser instance failed");
	 * }finally{System.out.print("closing.................");
	 * HibernateSessionFactory.closeSession(); }return course; }
	 */

	public List<Studenthomework> getStudenthomeworkListByStudentId(int sid) {
		System.out.println("getStudenthomeworkListByStudentId:");
		Session session = null;
		try {
			session = getSession();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.out.println("11111111111:");
		/*
		 * try{ //session = HibernateSessionFactory.currentSession();
		 * 
		 * }catch(Exception e) { e.printStackTrace(); }
		 */

		List<Studenthomework> list = null;
		System.out.println("22222222222:");
		// Course course = null;
		try {
			System.out.println("trying...getStudenthomeworkListByStudentId");
			Transaction tx = session.beginTransaction();
			// 澶栭敭鏌ヨ
			System.out.println(1);
			Query query = session
					.createQuery("from Studenthomework sh where sh.student.SId = "
							+ sid);
			System.out.println(2);
			list = query.list();
			System.out.println("22222222222:" + list.toString());
			System.out.println("name:" + list.get(0).getShName());
			// course = (Course)list.get(0);
			// query.executeUpdate();///?
			tx.commit();
			System.out.println(4);
			// log.debug("get TbUser instance successful");
		} catch (RuntimeException e) {
			e.printStackTrace();
			// log.debug("get TbUser instance failed");
		} finally {
			System.out.print("closing.................");
			session.close();
		}
		System.out.print("return.................");
		return list;
	}

	public boolean updateStudenthomework(Studenthomework sh) {
		// log.debug("updateing Teacher instance");
		System.out.println("updateStudenthomework Teacher instance");
		Session session = getSession();
		// String password_new = instance.getTPassword();
		// Teacher teacher = (Teacher)session.get(Teacher.class,
		// instance.getTId());
		if (sh != null) {
			try {

				// teacher.setTPassword(password_new);
				Transaction tx = session.beginTransaction();
				session.update(sh);
				tx.commit();

				// log.debug("updateing TbNotice instance successful");
				System.out
						.println("updateStudenthomework TbNotice instance successful");
				return true;
			} catch (RuntimeException e) {
				// log.debug("updateing TbNotice instance failed");
			} finally {
				HibernateSessionFactory.closeSession();
			}
		}

		return false;
	}

	public boolean saveStudenthomework(Studenthomework sh) {

		Session session = getSession();
		if (sh != null) {
			try {
				Transaction tx = session.beginTransaction();
				session.save(sh);
				tx.commit();
				return true ;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				HibernateSessionFactory.closeSession() ;
			}

		}

		return false;
	}

	// public List<Studenthomework> getStudenthomeworkListByCourseId(int cid){
	// System.out.println("getStudenthomeworkListByCourseId:");
	// Session session=null;
	// try{
	// session=getSession();
	// }catch(Exception e)
	// {
	// e.printStackTrace();
	// System.out.println(e.getMessage());
	// }
	// System.out.println("11111111111:");
	// /*try{
	// //session = HibernateSessionFactory.currentSession();
	//
	// }catch(Exception e)
	// {
	// e.printStackTrace();
	// }*/
	//
	// List<Studenthomework> list = null;
	// System.out.println("22222222222:");
	// //Course course = null;
	// try {
	// System.out.println("trying...getStudenthomeworkListByStudentId");
	// Transaction tx = session.beginTransaction();
	// //澶栭敭鏌ヨ
	// System.out.println(1);
	// Query query =
	// session.createQuery("from Studenthomework sh where sh.student.SId = "+sid);
	// System.out.println(2);
	// list = query.list();
	// System.out.println("22222222222:"+list.toString());
	// System.out.println("name:"+list.get(0).getShName());
	// //course = (Course)list.get(0);
	// // query.executeUpdate();///?
	// tx.commit();
	// System.out.println(4);
	// //log.debug("get TbUser instance successful");
	// } catch (RuntimeException e){
	// e.printStackTrace();
	// //log.debug("get TbUser instance failed");
	// }finally{System.out.print("closing.................");
	// session.close(); }
	// System.out.print("return.................");
	// return list;
	// }
	//

}

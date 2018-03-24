package com.coursemis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.IStudentDAO;
import com.coursemis.model.Student;

public class StudentDAO extends BaseDAO implements IStudentDAO {
	public String searchStudentTel(int studentid){
		System.out.println("searchStudentTel:");
		Session session = getSession();
		Student student = null;
		try {
			System.out.println("trying...");
			student = (Student)session.get(Student.class, studentid);
			//log.debug("get TbUser instance successful");	
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed");
		}
		session.close();
		return student.getSTel().trim();
	}
	
	public Student searchStudent(String studentname,String password){
		Session session = getSession();
		List list = null;
		Student student = null;
		System.out.println("searchStudent:");///
		try {
			System.out.println("trying");///
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Student student where student.SName = '"+studentname+"' and student.SPassword = '"+password+"'");
			list = query.list();
			student = (Student)list.get(0);
			System.out.println("BBB:"+student.getSDepartment());///
			query.executeUpdate();
			tx.commit();
			//log.debug("search TbUser instance successful");
		} catch (RuntimeException e){
			//log.debug("search TbUser instance failed");
		}
		session.close();
		return student;
	}
	
	public List<Student> getStudentNotInCourse(int courseId){
		System.out.println("getStudentNotInCourse:");
		Session session = getSession();
		
		/*int ll =5;
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(int i=0;i<idlist.size();i++){
			ids.add(idlist.get(i));
		}
		*/
		List<Student> list = null;
		try {
			System.out.println("trying...");
			//String hql = "select * from student where S_ID NOT IN (select SC_S_ID from studentcourse where SC_C_ID = 4 )";
			String hql = "from Student student where student.SId NOT IN " +
					"(select studentcourse.student.SId from Studentcourse studentcourse " +
					"where studentcourse.course.CId = "+courseId+") ";
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			//query.setParameterList("ids", ids);
			//query.setInteger(0, 5);
			list = query.list();
			tx.commit();
			//log.debug("get TbUser instance successful");	
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed");
		}
		session.close();
		return list;
	}
	
	public Student getStudentById(int studentid){
		System.out.println("getStudentById:");
		Session session = getSession();
		Student student = null;
		try {
			System.out.println("trying...");
			student = (Student)session.get(Student.class, studentid);
			//log.debug("get TbUser instance successful");
			System.out.println("gggggggggggggg:"+student.toString());	
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed");
		}
		session.close();
		return student;
	}
	
	public List<Student> getStudentByClass(String classname){
		System.out.println("getStudentByClass:");
		Session session = getSession();
		List<Student> list = null;
		//Course course = null;
		try {
			System.out.println("trying...");
			Transaction tx = session.beginTransaction();
			//外键查询
			Query query = session.createQuery("from Student student where student.SClass = '"+classname+"'");
			list = query.list();
			query.executeUpdate();///?
			tx.commit();
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed"); 
		}
		session.close();
		return list;
	}
	/*public List<Student> getStudentByCourseId(int courseid){
		System.out.println("getStudentByCourseId:");
		Session session = getSession();
		List<Student> list = null;
		//Course course = null;
		try {
			System.out.println("trying...");
			Transaction tx = session.beginTransaction();
			//外键查询
			Query query = session.createQuery("from Student student where student.course.CId = "+courseid);
			list = query.list();
			query.executeUpdate();///?
			tx.commit();
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed"); 
		}
		session.close();
		return list;
	}*/
}

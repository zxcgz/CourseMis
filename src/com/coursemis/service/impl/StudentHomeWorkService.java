package com.coursemis.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.action.HibernateSessionFactory;
import com.coursemis.dao.IStudentHomeWorkDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Studenthomework;
import com.coursemis.service.IStudentHomeWorkService;

public class StudentHomeWorkService implements IStudentHomeWorkService {
	private IStudentHomeWorkDAO studentHomeWorkDAO;

	public List<Studenthomework> getStudentHomeworkListBySMId(int smid){
		return studentHomeWorkDAO.getStudentHomeworkListBySMId(smid);
	}
	
	public boolean saveCourse(Course instance) {
		return studentHomeWorkDAO.saveCourse(instance);
	}
	
	public Studenthomework getStudenthomework(int shid){
		return studentHomeWorkDAO.getStudenthomework(shid);
	}
	
	public List<Studenthomework> getStudenthomeworkListByStudentId(int sid){
		return studentHomeWorkDAO.getStudenthomeworkListByStudentId(sid);
	}
	
	public boolean updateStudenthomework(Studenthomework sh) {
		return studentHomeWorkDAO.updateStudenthomework(sh);
	}
	
	
	
	public IStudentHomeWorkDAO getStudentHomeWorkDAO() {
		return studentHomeWorkDAO;
	}

	public void setStudentHomeWorkDAO(IStudentHomeWorkDAO studentHomeWorkDAO) {
		this.studentHomeWorkDAO = studentHomeWorkDAO;
	}

	public boolean saveStudenthomework(Studenthomework sh) {
		return studentHomeWorkDAO.saveStudenthomework(sh);
	}
}

package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.ICourseDAO;
import com.coursemis.dao.IStudentDAO;
import com.coursemis.dao.IStudentcourseDAO;
import com.coursemis.model.Studentcourse;
import com.coursemis.service.IStudentcourseService;


public class StudentcourseService implements IStudentcourseService {
	private IStudentcourseDAO studentcourseDAO;
	private IStudentDAO studentDAO;
	private ICourseDAO courseDAO;
	
	public List<Studentcourse> getStudentcourseByCId(int courseid){
		return studentcourseDAO.getStudentcourseByCId(courseid);
	}

	public List<Studentcourse> getStudentcourseElseByCId(int courseid){
		return studentcourseDAO.getStudentcourseElseByCId(courseid);
	}
	
	public List<Studentcourse> getStudentcourseBySId(int studentid){
		return studentcourseDAO.getStudentcourseBySId(studentid);
	}
	
	public boolean deleteNoticeStudentcourse(int courseid){
		return studentcourseDAO.deleteNoticeStudentcourse(courseid);
	}
	
	public boolean deleteStudentOfCourse(int courseid,int studentid){
		return studentcourseDAO.deleteStudentOfCourse(courseid, studentid);
	}
	
	public boolean addStudentToCourse(List<Integer> studentidList,int courseid){
		for(int i=0;i<studentidList.size();i++){
			Studentcourse studentcourse = new Studentcourse();
			studentcourse.setCourse(courseDAO.getCourseById(courseid));
			studentcourse.setStudent(studentDAO.getStudentById(studentidList.get(i)));
			studentcourse.setScPointNum(0);
			studentcourse.setScPointTotalNum(0);
			boolean addStudent = studentcourseDAO.saveStudentcourse(studentcourse);
			if(!addStudent){
				return false;
			}
		}
		return true;
	}
	public Studentcourse searchStudentcourse(int scId){
		return studentcourseDAO.searchStudentcourse(scId);
	}
	public boolean updateStudentcourse(Studentcourse s){
		return studentcourseDAO.updateStudentcourse(s);
	}
	
	
	
	public IStudentcourseDAO getStudentcourseDAO() {
		return studentcourseDAO;
	}

	public void setStudentcourseDAO(IStudentcourseDAO studentcourseDAO) {
		this.studentcourseDAO = studentcourseDAO;
	}

	public ICourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public IStudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
}

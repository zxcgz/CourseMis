package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.IStudentDAO;
import com.coursemis.model.Student;
import com.coursemis.service.IStudentService;

public class StudentService implements IStudentService {
	private IStudentDAO studentDAO;
	public Student getStudentById(int studentid) {
		return studentDAO.getStudentById(studentid);
	}
	
	public List<Student> getStudentNotInCourse(int courseId){
		return studentDAO.getStudentNotInCourse(courseId);
	}

	public Student searchStudent(String studentname,String password) {
		return studentDAO.searchStudent(studentname,password);
	}

	public List<Student> getStudentByClass(String classname){
		return studentDAO.getStudentByClass(classname);
	}
	
	public String searchStudentTel(int studentid){
		return studentDAO.searchStudentTel(studentid);
	}
	/*public List<Student> getStudentByCourseId(int courseid){
		return studentDAO.getStudentByCourseId(courseid);
	}*/
	
	
	
	public IStudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
}

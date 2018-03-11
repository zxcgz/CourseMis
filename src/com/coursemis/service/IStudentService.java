package com.coursemis.service;

import java.util.List;

import com.coursemis.model.Student;

public interface IStudentService {
	public Student getStudentById(int studentid);
	public List<Student> getStudentNotInCourse(int courseId);
	public Student searchStudent(String studentname, String password);
	
	public List<Student> getStudentByClass(String classname);
	public String searchStudentTel(int studentid);
	//public List<Student> getStudentByCourseId(int courseid);
}

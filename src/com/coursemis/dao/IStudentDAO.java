package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Student;

public interface IStudentDAO {
	public String searchStudentTel(int studentid);
	public Student searchStudent(String studentname,String password);
	public Student getStudentById(int studentid);
	public List<Student> getStudentNotInCourse(int courseId);
	//public List<Student> getStudentByCourseId(int courseid);
	public List<Student> getStudentByClass(String classname);
}

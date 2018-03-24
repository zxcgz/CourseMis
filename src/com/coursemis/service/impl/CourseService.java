package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.ICourseDAO;
import com.coursemis.model.Course;
import com.coursemis.service.ICourseService;

public class CourseService implements ICourseService {
	private ICourseDAO courseDAO;

	
	public List<Course> getCourseByTeacherId(int teacherid){
		return courseDAO.getCourseByTeacherId(teacherid);
	}
	public boolean addCourse(Course course){
		return courseDAO.saveCourse(course);
	}
	public boolean updateCourse(Course instance){
		return courseDAO.updateCourse(instance);
	}
	public boolean deleteCourse(int courseid){
		return courseDAO.deleteCourse(courseid);
	}
	public Course getCourseById(int courseid){
		return courseDAO.getCourseById(courseid);
	}

	public ICourseDAO getCourseDAO() {
		return courseDAO;
	}


	public void setCourseDAO(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
}

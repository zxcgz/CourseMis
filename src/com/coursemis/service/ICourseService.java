package com.coursemis.service;

import java.util.List;

import com.coursemis.model.Course;

public interface ICourseService {
	public List<Course> getCourseByTeacherId(int teacherid);
	public boolean addCourse(Course course);
	public boolean updateCourse(Course instance);
	public Course getCourseById(int courseid);
	public boolean deleteCourse(int courseid);
}

package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Course;

public interface ICourseDAO {
	public List<Course> getCourseByTeacherId(int teacherid);
	public boolean saveCourse(Course instance);
	public boolean updateCourse(Course instance);
	public Course getCourseById(int courseid);
	public boolean deleteCourse(int courseid);
}

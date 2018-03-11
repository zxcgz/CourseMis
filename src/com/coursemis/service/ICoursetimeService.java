package com.coursemis.service;

import java.util.List;

import com.coursemis.model.Coursetime;

public interface ICoursetimeService {
	public boolean addCourseTime(Coursetime instance);
	public boolean updateCourseTime(Coursetime instance);
	public List<Coursetime> getCoursetimeByCourseId(int courseid);
	public boolean deleteCoursetime(int coursetimeid);
	public boolean deleteCoursetimeByCid(int courseid);
	public Coursetime getCoursetime(int cid);
}

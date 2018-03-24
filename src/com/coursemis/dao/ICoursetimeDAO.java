package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Coursetime;

public interface ICoursetimeDAO {
	public boolean saveCourseTime(Coursetime instance);
	public boolean updateCourseTime(Coursetime instance);
	public List<Coursetime> getCoursetimeByCourseId(int courseid);
	public boolean deleteCoursetime(int coursetimeid);
	public boolean deleteCoursetimeByCid(int courseid);
	public Coursetime getCoursetime(int cid);
}

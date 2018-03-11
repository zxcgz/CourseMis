package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Studentcourse;

public interface IStudentcourseDAO {
	public boolean saveStudentcourse(Studentcourse instance);
	public List<Studentcourse> getStudentcourseByCId(int courseid);
	public List<Studentcourse> getStudentcourseElseByCId(int courseid);
	public List<Studentcourse> getStudentcourseBySId(int studentid);
	public boolean deleteNoticeStudentcourse(int courseid);
	public boolean deleteStudentOfCourse(int courseid,int studentid);
	public Studentcourse searchStudentcourse(int scId);
	public boolean updateStudentcourse(Studentcourse s);
}

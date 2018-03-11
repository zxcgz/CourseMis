package com.coursemis.service;

import java.util.List;

import com.coursemis.model.Studentcourse;

public interface IStudentcourseService {
	public boolean addStudentToCourse(List<Integer> studentidList,int courseid);
	public List<Studentcourse> getStudentcourseByCId(int courseid);
	public List<Studentcourse> getStudentcourseElseByCId(int courseid);
	public List<Studentcourse> getStudentcourseBySId(int studentid);
	public boolean deleteNoticeStudentcourse(int courseid);
	public boolean deleteStudentOfCourse(int courseid,int studentid);
	public Studentcourse searchStudentcourse(int scId);
	public boolean updateStudentcourse(Studentcourse s);
}

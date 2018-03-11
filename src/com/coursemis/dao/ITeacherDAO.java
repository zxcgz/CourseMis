package com.coursemis.dao;

import com.coursemis.model.Teacher;

public interface ITeacherDAO {
	public Teacher searchTeacher(String teachername,String password);
	public Teacher getTeacherById(int teacherid);
	public boolean updateTeacherPwd(Teacher instance);
	public Teacher getTeacherByName(String tname);
}

package com.coursemis.service;

import com.coursemis.model.Teacher;

public interface UserService {
	public Teacher getTeacherById(int teacherid);

	public Teacher searchTeacher(String teachername,String password);
	
	public boolean pwdChange(int teacherid,String password_new);
	public Teacher getTeacherByName(String tname);
}

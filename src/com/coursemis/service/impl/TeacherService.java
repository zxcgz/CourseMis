package com.coursemis.service.impl;



import com.coursemis.dao.ITeacherDAO;
import com.coursemis.model.Teacher;
import com.coursemis.service.UserService;

public class TeacherService implements UserService {
	private ITeacherDAO teacherDAO;

	public Teacher getTeacherById(int teacherid) {
		return teacherDAO.getTeacherById(teacherid);
	}

	public Teacher searchTeacher(String teachername,String password) {
		return teacherDAO.searchTeacher(teachername,password);
	}

	public boolean pwdChange(int teacherid,String password_new){
		Teacher teacher = new Teacher();
		teacher.setTId(teacherid);
		teacher.setTPassword(password_new);
		return teacherDAO.updateTeacherPwd(teacher);
	}
	public Teacher getTeacherByName(String tname){
		return teacherDAO.getTeacherByName(tname);
	}
	
	
	public ITeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(ITeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
}

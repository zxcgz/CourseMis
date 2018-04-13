package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.ICourseDAO;
import com.coursemis.dao.ILocationDAO;
import com.coursemis.dao.ISignInDAO;
import com.coursemis.dao.IStudentDAO;
import com.coursemis.dao.ITeacherDAO;
import com.coursemis.dao.impl.SignInDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Location;
import com.coursemis.model.Signin;
import com.coursemis.model.Student;
import com.coursemis.model.Teacher;
import com.coursemis.service.ISignInService;

public class SignInService implements ISignInService {

	private ISignInDAO signInDAO;
	private ITeacherDAO teacherDAO;
	private ICourseDAO courseDAO;
	private IStudentDAO studentDAO;
	private ILocationDAO locationDAO;

	public boolean signIn(int tid, int cid, int sid) {
		// 获取到所有的签到信息
		Signin signin = signInDAO.getSignin(tid, cid, sid);
		if (signin != null) {
			// 存在信息 ，设置签到
			signin.setFlag(SignInDAO.SUCCESS);
			signInDAO.signIn(signin);
			return true;
		} else {
			// 没有信息，检查Location表中有没有信息，如果存在信息，则设置签到，如果没有，则返回
			Location searchLocation = locationDAO.searchLocation(cid);
			if (searchLocation != null) {
				// 存在信息，进行签到
				Teacher teacherById = teacherDAO.getTeacherById(tid);
				Course courseById = courseDAO.getCourseById(cid);
				Student studentById = studentDAO.getStudentById(sid);
				Signin s = new Signin(teacherById, courseById, studentById,
						SignInDAO.SUCCESS);
				// 保存
				signInDAO.signIn(s);

			}
			return true;
		}
	}

	public boolean setSignIn(int tid, int cid) {
		return false;
	}

	public IStudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public void setSignInDAO(ISignInDAO signInDAO) {
		this.signInDAO = signInDAO;
	}

	public void setTeacherDAO(ITeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	public void setCourseDAO(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public ILocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(ILocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

}

package com.coursemis.service.impl;

import com.coursemis.dao.ICourseDAO;
import com.coursemis.dao.ICoursetimeDAO;
import com.coursemis.dao.IStudentDAO;
import com.coursemis.dao.IStudentcourseDAO;
import com.coursemis.dao.ITeacherDAO;
import com.coursemis.service.IManageService;

public class ManageService implements IManageService {
	private ICourseDAO courseDAO;
	private ICoursetimeDAO coursetimeDAO;
	private IStudentcourseDAO studentcourseDAO;
	private IStudentDAO studentDAO;
	private ITeacherDAO teacherDAO;
	
	
}

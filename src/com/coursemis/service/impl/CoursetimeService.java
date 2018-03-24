package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.ICoursetimeDAO;
import com.coursemis.model.Coursetime;
import com.coursemis.service.ICoursetimeService;

public class CoursetimeService implements ICoursetimeService {
	private ICoursetimeDAO coursetimeDAO;
	public boolean addCourseTime(Coursetime instance){
		return coursetimeDAO.saveCourseTime(instance);
	}
	public boolean updateCourseTime(Coursetime instance){
		return coursetimeDAO.updateCourseTime(instance);
	}
	public List<Coursetime> getCoursetimeByCourseId(int courseid){
		return coursetimeDAO.getCoursetimeByCourseId(courseid);
	}
	public boolean deleteCoursetime(int coursetimeid){
		return coursetimeDAO.deleteCoursetime(coursetimeid);
	}
	public boolean deleteCoursetimeByCid(int courseid){
		return coursetimeDAO.deleteCoursetimeByCid(courseid);
	}
	public Coursetime getCoursetime(int cid){
		return coursetimeDAO.getCoursetime(cid);
	}
	
	
	public ICoursetimeDAO getCoursetimeDAO() {
		return coursetimeDAO;
	}
	public void setCoursetimeDAO(ICoursetimeDAO coursetimeDAO) {
		this.coursetimeDAO = coursetimeDAO;
	}
}

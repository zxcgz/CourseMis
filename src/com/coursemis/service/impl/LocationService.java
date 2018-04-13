package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.ICourseDAO;
import com.coursemis.dao.ILocationDAO;
import com.coursemis.dao.ITeacherDAO;
import com.coursemis.dao.impl.CourseDAO;
import com.coursemis.dao.impl.LocationDAO;
import com.coursemis.dao.impl.TeacherDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Location;
import com.coursemis.model.Teacher;
import com.coursemis.service.ILocationService;

public class LocationService implements ILocationService {

	ILocationDAO locationDAO;
	ITeacherDAO teacherDAO;
	ICourseDAO courseDAO;

	public boolean deleteLocation(int cid) {
		Location searchLocation = locationDAO.searchLocation(cid);
		locationDAO.deleteLocation(searchLocation);
		return true;
	}

	public boolean deleteLocation(Location location) {
		locationDAO.deleteLocation(location);
		return false;
	}

	public boolean insertLocation(int tid, int cid, double latitude,
			double longitude) {
		// 获取到课程对象
		try {
			Course course = courseDAO.getCourseById(cid);
			Teacher teacher = teacherDAO.getTeacherById(tid);
			Location location = new Location(course, teacher, latitude,
					longitude);
			locationDAO.insertLocation(location);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
		}
		return false;
	}

	public boolean insertLocation(Teacher teacher, Course course,
			double latitude, double longitude) {
		Location location = new Location(course, teacher, latitude, longitude);
		locationDAO.insertLocation(location);
		return false;
	}

	public Location getLocation(int cid) {
		return locationDAO.searchLocation(cid);
	}

	public Location getLocation(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	public Location getLocation(Course course) {
		return locationDAO.searchLocation(course.getCId());
	}

	public Location getLocation(Teacher teacher, Course course) {
		return locationDAO.searchLocation(course.getCId());
	}

	public ILocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(ILocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public ITeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	public ICourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

}

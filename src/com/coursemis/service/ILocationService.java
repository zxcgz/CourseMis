package com.coursemis.service;

import java.util.List;

import com.coursemis.model.Course;
import com.coursemis.model.Coursetime;
import com.coursemis.model.Location;
import com.coursemis.model.Teacher;

public interface ILocationService {
	public boolean deleteLocation(int cid);
	public boolean deleteLocation(Location location) ;
	public boolean insertLocation(int tid,int cid,double latitude ,double longitude) ;
	public boolean insertLocation(Teacher teacher ,Course course ,double latitude ,double longitude) ;
	public Location getLocation(int cid) ;
	public Location getLocation(Teacher teacher ) ;
	public Location getLocation(Course course) ;
	public Location getLocation(Teacher teacher ,Course course ) ;
}

package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Location;

public interface ILocationDAO {
	public void insertLocation(Location location) ;
	public void deleteLocation(Location location) ;
	public Location searchLocation(int cid);
}

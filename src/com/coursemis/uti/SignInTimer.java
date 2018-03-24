package com.coursemis.uti;

import com.coursemis.dao.impl.CourseDAO;
import com.coursemis.dao.impl.LocationDAO;
import com.coursemis.model.Course;

public class SignInTimer implements Runnable{

	CourseDAO coursedao=new CourseDAO();
	private int hour;
	private int minute;
	private int cid;
	public SignInTimer(int hour ,int minute,int cid)
	{
		this.hour=hour;
		this.minute=minute;
		this.cid=cid;
		
	}
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep((hour*60*60+minute*60)*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Course course = coursedao.getCourseById(cid);
		course.setCFlag(false);
		coursedao.updateCourse(course);
		LocationDAO dao = new LocationDAO();
		dao.deleteLocation(cid);
		
		System.out.println(" 已经关闭签到模式");
		
		
		
	}
	

	
}


package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Course;
import com.coursemis.model.Studenthomework;

public interface IStudentHomeWorkDAO {

	public List<Studenthomework> getStudentHomeworkListBySMId(int smid);

	public boolean saveCourse(Course instance);
	
	public Studenthomework getStudenthomework(int shid);
	
	public List<Studenthomework> getStudenthomeworkListByStudentId(int sid);
	
	public boolean updateStudenthomework(Studenthomework sh);
	public boolean saveStudenthomework(Studenthomework sh) ;
}

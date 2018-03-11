package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.ICourseevaluationDAO;
import com.coursemis.model.Courseevaluation;
import com.coursemis.service.ICourseevaluationService;

public class CourseevaluationService implements ICourseevaluationService {
	private ICourseevaluationDAO courseevaluationDAO;
	
	public boolean addCourseevaluation(Courseevaluation instance){
		return courseevaluationDAO.saveCourseevaluation(instance);
	}
	public List<Courseevaluation> getCourseevaluationByCourseId(int courseid){
		return courseevaluationDAO.getCourseevaluationByCourseId(courseid);
	}
	
	
	
	public ICourseevaluationDAO getCourseevaluationDAO() {
		return courseevaluationDAO;
	}

	public void setCourseevaluationDAO(ICourseevaluationDAO courseevaluationDAO) {
		this.courseevaluationDAO = courseevaluationDAO;
	}
}

package com.coursemis.service;

import java.util.List;

import com.coursemis.model.Courseevaluation;

public interface ICourseevaluationService {
	public boolean addCourseevaluation(Courseevaluation instance);
	public List<Courseevaluation> getCourseevaluationByCourseId(int courseid);
	
}

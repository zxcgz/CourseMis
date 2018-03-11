package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Courseevaluation;

public interface ICourseevaluationDAO {
	public boolean saveCourseevaluation(Courseevaluation instance);
	public List<Courseevaluation> getCourseevaluationByCourseId(int courseid);
}

package com.coursemis.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.coursemis.dao.IEvaluationDAO;
import com.coursemis.model.Evaluation;
import com.coursemis.model.Score;
import com.coursemis.service.IEvaluationService;
import com.google.gson.Gson;

public class EvaluationService implements IEvaluationService {
	private IEvaluationDAO evaluationDAO ;

	public boolean insertOrUpdate(int cid, int pNum, List<Double> scores) {
		Gson gson = new Gson() ;
		Evaluation evaluation = evaluationDAO.getScore(cid, pNum) ;
		if (evaluation==null) {
			scores.add(9,1.0) ;
			return evaluationDAO.insertOrUpdate(cid, pNum, scores) ;
		}else {
			String eGrades = evaluation.getEGrades() ;
			List<Double> list = new ArrayList<Double>() ;
			List<Double> score = gson.fromJson(eGrades, list.getClass()) ;
			for (int i = 0; i < 8; i++) {
				double add = score.get(i)+scores.get(i) ;
				score.set(i, add) ;
			}
			score.set(9, score.get(9)+1) ;
			//更新数据
			return evaluationDAO.insertOrUpdate(cid, pNum, score) ;
		}
	}

	public List<Evaluation> getScore(int cid) {
		return evaluationDAO.getScore(cid);
	}

	public Evaluation getScore(int cid, int pNum) {
		return evaluationDAO.getScore(cid, pNum);
	}

	public boolean delete(int cid) {
		return evaluationDAO.delete(cid);
	}

	public IEvaluationDAO getEvaluationDAO() {
		return evaluationDAO;
	}

	public void setEvaluationDAO(IEvaluationDAO evaluationDAO) {
		this.evaluationDAO = evaluationDAO;
	}

	
	
	
	
}

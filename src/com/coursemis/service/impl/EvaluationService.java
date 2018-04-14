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
			System.out.println("判断为空......"+scores.size());
			scores.add(8,1.0) ;
			return evaluationDAO.insertOrUpdate(cid, pNum, scores) ;
		}else {
			System.out.println("insertOrUpdate1");
			String eGrades = evaluation.getEGrades() ;
			System.out.println("insertOrUpdate2");
			List<Double> list = new ArrayList<Double>() ;
			System.out.println("insertOrUpdate3");
			List<Double> score = gson.fromJson(eGrades, list.getClass()) ;
			System.out.println("insertOrUpdate4");
			for (int i = 0; i < 8; i++) {
				System.out.println("insertOrUpdate5");
				double add = score.get(i)+scores.get(i) ;
				score.set(i, add) ;
			}
			System.out.println("insertOrUpdate6");
			score.set(8, score.get(8)+1) ;
			System.out.println("insertOrUpdate7");
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

package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.IScoreDAO;
import com.coursemis.model.Score;
import com.coursemis.service.IScoreService;

/**
 * 成绩
 * @author lenovo
 *
 */
public class ScoreService  implements IScoreService{
	private IScoreDAO scoreDAO ;

	public Score getScore(int cid, int sid, int periodId) {
		return scoreDAO.getScore(cid, sid, periodId);
	}

	public boolean insertOrUpdate(Score score) {
		return scoreDAO.insertOrUpdate(score);
	}

	public boolean delete(Score score) {
		return scoreDAO.delete(score);
	}

	public List<Score> getScoresAllPeriodBySId(int cid, int sid) {
		return scoreDAO.getScoresAllPeriodBySId(cid, sid);
	}

	public List<Score> getScoresOnePeriodBySid(int cid, int periodId) {
		return scoreDAO.getScoresOnePeriodBySid(cid,  periodId);
	}
	
	
	
	public IScoreDAO getScoreDAO() {
		return scoreDAO;
	}

	public void setScoreDAO(IScoreDAO scoreDAO) {
		this.scoreDAO = scoreDAO;
	}

	
	

}

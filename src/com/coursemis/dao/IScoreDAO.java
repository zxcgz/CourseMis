package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Score;

/**
 * 成绩
 * @author zxc
 *
 */
public interface IScoreDAO {
	/**
	 * 获取到指定的学生的成绩
	 * @param cid			课程id
	 * @param sid			学生id
	 * @param periodId		课时数
	 * @return
	 */
	public Score getScore(int cid,int sid,int periodId) ;
	/**
	 * 插入或者更新成绩
	 * @param score
	 * @return
	 */
	public boolean insertOrUpdate(Score score) ;
	/**
	 * 插入或者更新成绩
	 * @param score
	 * @return
	 */
	public boolean insertOrUpdate(int cid,int sid,int periodId) ;
	/**
	 * 删除指定的成绩
	 * @param score
	 * @return
	 */
	public boolean delete(Score score) ;
	/**
	 * 通过课程id和学生id获取到一个学生在当前课程中的所有课时的成绩信息
	 * @param cid		课程id
	 * @param sid		学生id
	 * @return
	 */
	public List<Score> getScoresAllPeriodBySId(int cid,int sid) ;
	/**
	 * 通过课程id和学生id以及课时数，获取到所有学生的成绩
	 * @param cid
	 * @param sid
	 * @param periodId
	 * @return
	 */
	public List<Score> getScoresOnePeriodBySid(int cid,int sid,int periodId) ;
}

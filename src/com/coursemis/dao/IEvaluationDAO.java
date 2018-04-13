package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Evaluation;
import com.coursemis.model.Score;

/**
 * 随堂反馈
 * @author zxc
 *
 */
public interface IEvaluationDAO {
	/**
	 * 插入反馈
	 * @param evaluation
	 * @return
	 */
	public boolean insertOrUpdate(int cid,int pNum,List<Double> scores) ;
	/**
	 * 获取到当前课程的所有分数的集合
	 * @param cid
	 * @param pNum
	 * @return
	 */
	public List<Evaluation> getScore(int cid) ;
	/**
	 * 获取到指定课时的分数
	 * @param cid
	 * @param pNum
	 * @return
	 */
	public Evaluation getScore(int cid,int pNum) ;
	/**
	 * 删除一门课程所有课时的分数
	 * @param cid
	 * @return
	 */
	public boolean delete(int cid) ;
}

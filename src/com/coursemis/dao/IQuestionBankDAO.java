package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Questionbank;

/**
 * 试题库
 * @author zxc
 *
 */
public interface IQuestionBankDAO {

	/**
	 * 插入试题
	 * @param questionbank
	 * @return
	 */
	public boolean insert(Questionbank questionbank) ;
	/**
	 * 插入试题集合
	 * @param questionbanks
	 * @return
	 */
	public boolean insert(List<Questionbank> questionbanks) ;
	/**
	 * 获取试题集合
	 * @param cid		课程id
	 * @param periodId	课时数
	 * @return			试题集合
	 */
	public List<Questionbank> getQuestionbank(int cid,int periodId) ;
}

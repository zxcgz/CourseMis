package com.coursemis.service;

import com.coursemis.model.Period;

public interface IPeriodService {
	/***
	 * 新建一个课时表项
	 * @param period
	 * @return
	 */
	public boolean insert(Period period) ;
	/**
	 * 将课时数加一
	 * @param period
	 * @return
	 */
	public boolean addPeriodNum(Period period) ;
	/**
	 * 获取指定的课时数
	 * @param period
	 * @return
	 */
	public int getPeriodNum(Period period) ;
	/**
	 * 删除一项
	 * @param period
	 * @return
	 */
	public boolean delete(Period period) ;
	/**
	 * 获取到课时
	 */
	public Period getPeriod(int cid) ;
}

package com.coursemis.service;

public interface ISignInService {
	/**
	 * 签到
	 * @param tid	教师id
	 * @param cid	课程id
	 * @param sid	学生id
	 * @return		是否签到成功
	 */
	public boolean signIn(int tid,int cid,int sid) ;
	/**
	 * 教师设置签到信息
	 * @param tid	教师id
	 * @param cid	课程id
	 * @return		是否设置成功
	 */
	public boolean setSignIn(int tid,int cid) ;
}

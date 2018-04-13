package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Signin;

public interface ISignInDAO {
	/**
	 * 设置签到
	 */
	public boolean signIn(Signin signin) ;
	/**
	 * 判断是否已经签到
	 * @return
	 */
	public boolean isSignIn(int tid,int cid,int sid) ;
	/**
	 * 删除签到信息
	 * @return
	 */
	public boolean deleteSignIn(int tid,int cid) ;
	
	/**
	 * 通过教师id和课程id获取到签到列表
	 * @param tid	教师id
	 * @param cid	课程id
	 * @return		签到列表
	 */
	public List<Signin> getSigninList(int tid,int cid) ;
	/**
	 * 通过教师id、课程id和学生id获取到签到对象
	 * @param tid	教师id
	 * @param cid	课程id
	 * @param sid	学生id
	 * @return		签到对象
	 */
	public Signin getSignin(int tid,int cid,int sid) ;
}

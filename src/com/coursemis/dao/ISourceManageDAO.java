package com.coursemis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.action.HibernateSessionFactory;
import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Studenthomework;

public interface ISourceManageDAO {
	public boolean saveSourceManage(Sourcemanage instance) ;
	public List<Sourcemanage> getSourcemanageByCourseIdAndTeacherId(int cid,String tid);
	public Sourcemanage getSourcemanageById(int smid);
	public boolean saveSH(Studenthomework instance);
	public List<Sourcemanage> getSourcemanageByType(int type);
	 List<Sourcemanage> getSourcemanageByTid(int tid);
}

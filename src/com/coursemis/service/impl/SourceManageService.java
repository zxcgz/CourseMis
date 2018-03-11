package com.coursemis.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.coursemis.action.HibernateSessionFactory;
import com.coursemis.dao.ISourceManageDAO;
import com.coursemis.dao.impl.BaseDAO;
import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Studenthomework;
import com.coursemis.service.ISourceManageService;

public class SourceManageService implements ISourceManageService {
	private ISourceManageDAO sourceManageDAO;
	
	public boolean saveSourceManage(Sourcemanage instance) {
		return sourceManageDAO.saveSourceManage(instance);
	}
	
	public List<Sourcemanage> getSourcemanageByCourseIdAndTeacherId(int cid,String tid){
		return sourceManageDAO.getSourcemanageByCourseIdAndTeacherId(cid, tid);
	}
	
	
	public Sourcemanage getSourcemanageById(int smid){
		return sourceManageDAO.getSourcemanageById(smid);
	}
	
	public boolean saveSH(Studenthomework instance) {
		return sourceManageDAO.saveSH(instance);
	}
	
	public List<Sourcemanage> getSourcemanageByType(int type){
		return sourceManageDAO.getSourcemanageByType(type);
	}

	public ISourceManageDAO getSourceManageDAO() {
		return sourceManageDAO;
	}

	public void setSourceManageDAO(ISourceManageDAO sourceManageDAO) {
		this.sourceManageDAO = sourceManageDAO;
	}

	@Override
	public List<Sourcemanage> getSourcemanageByTid(int tid) {
		return sourceManageDAO.getSourcemanageByTid(tid);
	}
}

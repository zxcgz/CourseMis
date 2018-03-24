package com.coursemis.service;

import java.util.List;

import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Studenthomework;

public interface ISourceManageService {
	public boolean saveSourceManage(Sourcemanage instance);
	public List<Sourcemanage> getSourcemanageByCourseIdAndTeacherId(int cid,String tid);
	public Sourcemanage getSourcemanageById(int smid);
	public boolean saveSH(Studenthomework instance);
	public List<Sourcemanage> getSourcemanageByType(int type);
	public List<Sourcemanage> getSourcemanageByTid(int tid);
}

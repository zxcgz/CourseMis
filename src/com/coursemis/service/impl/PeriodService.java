package com.coursemis.service.impl;

import com.coursemis.dao.IPeriodDAO;
import com.coursemis.model.Period;
import com.coursemis.service.IPeriodService;

public class PeriodService implements IPeriodService{
	private IPeriodDAO periodDAO ;

	public boolean insert(Period period) {
		return periodDAO.insert(period);
	}

	public boolean addPeriodNum(Period period) {
		// TODO Auto-generated method stub
		return periodDAO.addPeriodNum(period);
	}

	public int getPeriodNum(Period period) {
		// TODO Auto-generated method stub
		return periodDAO.getPeriodNum(period);
	}

	public boolean delete(Period period) {
		// TODO Auto-generated method stub
		return periodDAO.delete(period);
	}

	public Period getPeriod(int cid) {
		return periodDAO.getPeriod(cid);
	}

	
	
	
	
	public IPeriodDAO getPeriodDAO() {
		return periodDAO;
	}

	public void setPeriodDAO(IPeriodDAO periodDAO) {
		this.periodDAO = periodDAO;
	}

	
	
	
	
	
}

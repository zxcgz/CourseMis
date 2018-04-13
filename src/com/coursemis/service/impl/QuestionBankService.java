package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.IQuestionBankDAO;
import com.coursemis.model.Questionbank;
import com.coursemis.service.IQuestionBankService;

public class QuestionBankService implements IQuestionBankService {
	private IQuestionBankDAO questionBankDAO ;

	public boolean insert(Questionbank questionbank) {
		
		return questionBankDAO.insert(questionbank);
	}

	public boolean insert(List<Questionbank> questionbanks) {
		return questionBankDAO.insert(questionbanks);
	}

	public List<Questionbank> getQuestionbank(int cid, int periodId) {
		return questionBankDAO.getQuestionbank(cid, periodId);
	}

	public IQuestionBankDAO getQuestionBankDAO() {
		return questionBankDAO;
	}

	public void setQuestionBankDAO(IQuestionBankDAO questionBankDAO) {
		this.questionBankDAO = questionBankDAO;
	}
	
	

}

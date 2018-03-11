package com.coursemis.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport {
	public void actionPair(){
		Map session = ActionContext.getContext().getSession();
		String action = (String) session.get("action");
		if (action.equals("login")) {
			//login(request, response);
		}
	}
	
}

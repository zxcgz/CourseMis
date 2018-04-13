package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.model.Course;
import com.coursemis.model.Coursetime;
import com.coursemis.model.Sourcemanage;
import com.coursemis.service.ISourceManageService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SourceMangerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;
    private HttpServletResponse response;
    private ISourceManageService sourceManageService;
    
    public void queryAllByTid(){
    	int tid = Integer.parseInt(request.getParameter("tid"));
    	
		List<Sourcemanage> listSource=null;
		try {
			System.out.println(sourceManageService);
			listSource = sourceManageService.getSourcemanageByTid(tid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	System.out.println("查询结果"+listSource);
    	
    	
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Sourcemanage> list_temp = null;
		for(Sourcemanage sm : listSource){
			JSONObject object_temp = new JSONObject();
			object_temp.element("smid", sm.getSmId());
			object_temp.element("courseid", sm.getCourse().getCId());
			object_temp.element("smName", sm.getSmName());
			object_temp.element("smPath", sm.getSmPath());
			object_temp.element("smUploader", sm.getSmUploader());
			object_temp.element("smDateTime", sm.getSmDateTime());
		
			jsonArray.add(object_temp);
			System.out.println("qqqq:");
		}
		System.out.println("PPPPPPPPPPPPPPO");
		resp.put("result", jsonArray);
		System.out.println("resp:"+resp.toString());
		try {
			PrintWriter out = response.getWriter();
			out.print(resp.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
    //-------------------------------------------------------------------------------------------------------------
    
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ISourceManageService getSourceManageService() {
		return sourceManageService;
	}

	public void setSourceManageService(ISourceManageService sourceManageService) {
		this.sourceManageService = sourceManageService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	
}

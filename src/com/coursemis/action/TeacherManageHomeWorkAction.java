package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Studenthomework;
import com.coursemis.service.ISourceManageService;
import com.coursemis.service.IStudentHomeWorkService;
import com.coursemis.service.IStudentcourseService;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherManageHomeWorkAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
    private HttpServletResponse response;
    
    private IStudentcourseService studentcourseService;
	private ISourceManageService sourceManageService;
	private IStudentHomeWorkService studentHomeWorkService;

    public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response=arg0;
	}
	
	public void homeworkComment() throws Exception
	{
		String shinfo=request.getParameter("shinfo");
		int shid = Integer.parseInt(shinfo.substring(0, shinfo.indexOf(" ")));
		String score = request.getParameter("score");
		Studenthomework sh = studentHomeWorkService.getStudenthomework(shid);
		sh.setShScore(Integer.parseInt(score));
		studentHomeWorkService.updateStudenthomework(sh);
		JSONObject resp = new JSONObject();
		
		
		PrintWriter out = response.getWriter();
		out.print(" ");
		out.flush();
		out.close();
		System.out.println("end......");
		
	}
	
	
	public void homeworkCheck()throws IOException
	{JSONObject resp=null;
		
		String sminfo = request.getParameter("sminfo");
		int smid = Integer.parseInt(sminfo.substring(0, sminfo.indexOf(" ")));
		List<Studenthomework> shl = studentHomeWorkService.getStudentHomeworkListBySMId(smid);
		try{
			 resp = new JSONObject();
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			System.out.println(111111111);

			JSONArray jsonArray = new JSONArray();
			System.out.println(111111112);
			//List<Course> courseList=coursedao.getCourseByTeacherId(Integer.parseInt(tid));
			//System.out.println(courseList.size());
			if(shl!=null||shl.size()!=0){
				for(int i=0;i<shl.size();i++){
					JSONObject object_temp = new JSONObject();
					
					System.out.println("111111:");
					object_temp.element("shid",shl.get(i).getShId());
					object_temp.element("shname",shl.get(i).getShName());
					
					jsonArray.add(i, object_temp);
					System.out.println("qqqq:");	
				}
				
				
			}
			 
			
			resp.put("result", jsonArray);
			
			PrintWriter out = response.getWriter();
			out.print(resp.toString());
			out.flush();
			out.close();
			System.out.println("end......");
		
		
		
		
	}
	
		
		
		public void homeworkSelect() throws IOException
		{
			String tid = request.getParameter("tid");
			String courseinfo = request.getParameter("courseinfo");
			int cid = Integer.parseInt(courseinfo.substring(0, courseinfo.indexOf(" ")));
			JSONObject resp=null;
			List<Sourcemanage> sml = sourceManageService.getSourcemanageByCourseIdAndTeacherId(cid, tid);
			try{
			 resp = new JSONObject();
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			System.out.println(111111111);

			JSONArray jsonArray = new JSONArray();
			System.out.println(111111112);
			//List<Course> courseList=coursedao.getCourseByTeacherId(Integer.parseInt(tid));
			//System.out.println(courseList.size());
			if(sml!=null||sml.size()!=0){
				for(int i=0;i<sml.size();i++){
					JSONObject object_temp = new JSONObject();
					
					System.out.println("111111:");
					object_temp.element("smid",sml.get(i).getSmId());
					object_temp.element("smname",sml.get(i).getSmName());
					object_temp.element("smpath", sml.get(i).getSmPath());
					jsonArray.add(i, object_temp);
					System.out.println("qqqq:");	
				}
				
				
			}
			
			
			resp.put("result", jsonArray);
			
			PrintWriter out = response.getWriter();
			out.print(resp.toString());
			out.flush();
			out.close();
			System.out.println("end......");
			
		}
		public IStudentcourseService getStudentcourseService() {
			return studentcourseService;
		}
		public void setStudentcourseService(IStudentcourseService studentcourseService) {
			this.studentcourseService = studentcourseService;
		}
		public ISourceManageService getSourceManageService() {
			return sourceManageService;
		}
		public void setSourceManageService(ISourceManageService sourceManageService) {
			this.sourceManageService = sourceManageService;
		}
		public IStudentHomeWorkService getStudentHomeWorkService() {
			return studentHomeWorkService;
		}
		public void setStudentHomeWorkService(
				IStudentHomeWorkService studentHomeWorkService) {
			this.studentHomeWorkService = studentHomeWorkService;
		}
	
	
	

	
	


}

package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.coursemis.dao.ICourseDAO;
import com.coursemis.dao.ISourceManageDAO;
import com.coursemis.dao.IStudentHomeWorkDAO;
import com.coursemis.dao.IStudentcourseDAO;
import com.coursemis.dao.ITeacherDAO;
import com.coursemis.dao.impl.CourseDAO;
import com.coursemis.dao.impl.RegulationDAO;
import com.coursemis.dao.impl.SourceManageDAO;
import com.coursemis.dao.impl.StudentcourseDAO;
import com.coursemis.dao.impl.TeacherDAO;
import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Studenthomework;
import com.coursemis.model.Teacher;
import com.coursemis.service.ICourseService;
import com.coursemis.service.ISourceManageService;
import com.coursemis.service.IStudentcourseService;
import com.coursemis.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class DebugAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
    private HttpServletResponse response;
    
    private IStudentcourseService studentcourseService;
	private ICourseService courseService;
	private UserService teacherService;
	
	private RegulationDAO rdao=new RegulationDAO();
	private ISourceManageService sourceManageService;
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response=arg0;
	}
	
	
	public void studentclasshomework_1() throws IOException
	{String courseinfo = request.getParameter("courseinfo");
	int cid =Integer.parseInt(courseinfo.substring(0, courseinfo.indexOf(" "))) ;
	System.out.println("�ٺٺٺ�1");
	List<Sourcemanage> sml = rdao.getSourcemanage(cid,0);
	System.out.println("�ٺٺٺ�3");
	JSONObject resp = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	
	if(sml!=null||sml.size()!=0){
		
		
		for(int i=0;i<sml.size();i++){
			JSONObject object_temp = new JSONObject();
			
				object_temp.element("smid",sml.get(i).getSmId());
				//object_temp.element("sname",sdao.getStudentById(sh.getStudent().getSId()).getSName());				
				jsonArray.add(i, object_temp);
		}
		
		
	}
	
	resp.put("result", jsonArray);
	System.out.println("resp:"+resp.toString());
	PrintWriter out = response.getWriter();
	out.print(resp.toString());
	out.flush();
	out.close();
	System.out.println("�ٺٺٺ�2");
		
	}
	
	public void getMediaData()throws Exception
	{
		List<Sourcemanage> sml = sourceManageService.getSourcemanageByType(1);
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		if(sml!=null||sml.size()!=0){
			
			
			for(int i=0;i<sml.size();i++){
				JSONObject object_temp = new JSONObject();
				
					object_temp.element("smid",sml.get(i).getSmId());
					object_temp.element("smname",sml.get(i).getSmName());
					//object_temp.element("sname",sdao.getStudentById(sh.getStudent().getSId()).getSName());				
					jsonArray.add(i, object_temp);
			}
			
			
		}
		
		resp.put("result", jsonArray);
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	
	
	
	
	public void StudentCourseCheckhomework() throws IOException
	{
		String courseinfo = request.getParameter("courseinfo");
		int cid = Integer.parseInt(courseinfo.substring(0, courseinfo.indexOf(" ") ));
		int sid = Integer.parseInt(request.getParameter("sid"));
		String tname = courseinfo.substring(courseinfo.indexOf("_")+1, courseinfo.length());
		System.out.println(tname.length()+"tname.length()");
		Teacher t = teacherService.getTeacherByName(tname);
		List<Integer> shl = rdao.getStudenthomeworkListByStudentId(sid);
		List<Sourcemanage> sml = sourceManageService.getSourcemanageByCourseIdAndTeacherId(cid, t.getTId()+"");
		List<Integer> smidl = new ArrayList<Integer>();//已交
		List<Integer> smidl_temp = new ArrayList<Integer>();
		
		for(int i =0;i<shl.size();i++)
		{
			int smid = shl.get(i);
			if(sourceManageService.getSourcemanageById(smid).getCourse().getCId()==cid)
			{
				smidl.add(smid);
			}
		}
		
		List<Integer> count__smid = rdao.getSourceManageListByCourseId(cid);
		for(int  i = 0;i<smidl.size();i++)
		{
			if(count__smid.contains(smidl.get(i)))
				count__smid.remove(smidl.get(i));
		}
		
		String tid = t.getTId()+"";
		
		JSONObject resp=null;
		
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
		if(sml!=null||smidl.size()!=0){
			for(int i=0;i<smidl.size();i++){
				JSONObject object_temp = new JSONObject();
				
				
				object_temp.element("smid",smidl.get(i)+"");
				object_temp.element("smname",sourceManageService.getSourcemanageById(smidl.get(i)).getSmName());
				object_temp.element("flag","已交");
				
				jsonArray.add(i, object_temp);
			}
				
		}
		
			for(int i=0;i<count__smid.size();i++)
			{
				JSONObject object_temp = new JSONObject();
				
				
				object_temp.element("smid",count__smid.get(i)+"");
				object_temp.element("smname",sourceManageService.getSourcemanageById(count__smid.get(i)).getSmName());
				object_temp.element("flag","未交");
				
				jsonArray.add(i, object_temp);
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
	public ICourseService getCourseService() {
		return courseService;
	}
	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}
	public UserService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(UserService teacherService) {
		this.teacherService = teacherService;
	}
	public ISourceManageService getSourceManageService() {
		return sourceManageService;
	}
	public void setSourceManageService(ISourceManageService sourceManageService) {
		this.sourceManageService = sourceManageService;
	}
	
}

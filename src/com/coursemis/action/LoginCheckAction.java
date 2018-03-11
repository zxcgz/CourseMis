package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.model.Student;
import com.coursemis.model.Teacher;
import com.coursemis.service.IStudentService;
import com.coursemis.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class LoginCheckAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService teacherService;
	private IStudentService studentService;
	private HttpServletRequest request;
    private HttpServletResponse response;
    
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		response=arg0;
	}
	
	public void login() throws IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		if(type.equals("教师")){
			Teacher teacher = teacherService.searchTeacher(name,password);
			JSONObject resp = new JSONObject();
			if(teacher==null){
				resp.put("result", false);
			}else{
				JSONArray jsonArray = new JSONArray();
				
				Teacher teacher2 = new Teacher();
				teacher2.setTName(teacher.getTName());
				teacher2.setTPassword(teacher.getTPassword());
				teacher2.setTId(teacher.getTId());
				
				jsonArray = JSONArray.fromObject(teacher2);
				resp.put("result", true);
				resp.put("result", jsonArray);
				
				PrintWriter out = response.getWriter();
				out.print(resp.toString());
				out.flush();
				out.close();				
			}
			
		}
		else if(type.equals("学生")){
			JSONObject resp = new JSONObject();
			Student student = studentService.searchStudent(name,password);
			if(student==null){
				resp.put("result", false);
			}
			else{
				System.out.println("student?"+student.toString());
				JSONArray jsonArray = new JSONArray();
				
				Student student2 = new Student();
				student2.setSName(student.getSName());
				student2.setSPassword(student.getSPassword());
				student2.setSId(student.getSId());
				
				jsonArray = JSONArray.fromObject(student2);
				resp.put("result", true);
				resp.put("result", jsonArray);
				
				PrintWriter out = response.getWriter();
				out.print(resp.toString());
				out.flush();
				out.close();
			}
		}
		
		//System.out.println("resp:"+resp.toString());
		/*PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();*/
	}
	
	public void pwdChange() throws IOException{
		String password_old = request.getParameter("password_old");
		String password_new1 = request.getParameter("password_new1");
		String password_new2 = request.getParameter("password_new2");
		String type = request.getParameter("type");
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		
		JSONObject resp = new JSONObject();
		if(type.equals("教师")){
			Teacher teacher = teacherService.getTeacherById(teacherid);
			if(teacher.getTPassword().equals(password_old)){
				if(teacherService.pwdChange(teacherid, password_new1)){
					System.out.println("55555555555555555");
					resp.put("result", "密码修改成功！");
				}
				else{
					resp.put("result", "密码修改失败！");
				}	
			}
			else{
				resp.put("result", "原始密码输入错误！");
			}
		}
		else if(type.equals("学生")){
			Student student = studentService.getStudentById(teacherid);
			System.out.println("student?"+student.toString());

		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	public UserService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(UserService teacherService) {
		this.teacherService = teacherService;
	}
	public IStudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}
	
}

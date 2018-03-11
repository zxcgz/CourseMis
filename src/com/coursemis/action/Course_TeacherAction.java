package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware; 
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.model.Course;
import com.coursemis.model.Studentcourse;
import com.coursemis.model.Teacher;
import com.coursemis.service.ICourseService;
import com.coursemis.service.IStudentcourseService;
import com.coursemis.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class Course_TeacherAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private UserService teacherService;
	private ICourseService courseService;
	private IStudentcourseService studentcourseService;
	private HttpServletRequest request;
    private HttpServletResponse response;
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response=arg0;
	}

	public void getCourseOfTeacher() throws IOException{
		System.out.println("course_teacher...");
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		//String name = request.getParameter("name");
		//String password = request.getParameter("password");
		//String type = request.getParameter("type");
		System.out.println("name:"+"type:"+"id:"+teacherid);
		
		System.out.println("22222222222222222222222:");
		List<Course> courseList = courseService.getCourseByTeacherId(teacherid);
		System.out.println("courseList.num = "+courseList.size());
		
		JSONObject resp = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			System.out.println("aaaaaaaaaaaaaaa:");	
			
			List<Course> list_temp = null;
			for(int i=0;i<courseList.size();i++){
				JSONObject object_temp = new JSONObject();
				System.out.println("object_temp:"+object_temp);

				object_temp.element("CId", courseList.get(i).getCId());
				object_temp.element("CName",courseList.get(i).getCName());
				object_temp.element("CNum",courseList.get(i).getCNum());
				System.out.println("CName:"+courseList.get(i).getCName());
				
				Teacher teacher_temp = courseList.get(i).getTeacher();

				JSONObject object_temp_2 = new JSONObject();
				object_temp_2.element("TId",teacher_temp.getTId());
				object_temp.element("teacher", object_temp_2);
				
				System.out.println("teacher_temp.getTDepartment:"+teacher_temp.getTId());
				
				object_temp.element("CFlag",courseList.get(i).getCFlag());
				object_temp.element("CPointTotalNum",courseList.get(i).getCPointTotalNum());
				//list_temp.add(course_temp);
				
				//选择该课程的学生数若为0 则标记Flag_Empty_Student为true
				List<Studentcourse> stlist_temp = 
					studentcourseService.getStudentcourseByCId(courseList.get(i).getCId());
				if(stlist_temp.size()==0){
				//if(stlist_temp==null){
					object_temp.element("Flag_Empty_Student", true);
				}
				else{
					object_temp.element("Flag_Empty_Student", false);
				}
				
				jsonArray.add(i, object_temp);
				System.out.println("qqqq:");
				
		}
		resp.put("result", jsonArray);
		
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

	public ICourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	public IStudentcourseService getStudentcourseService() {
		return studentcourseService;
	}

	public void setStudentcourseService(IStudentcourseService studentcourseService) {
		this.studentcourseService = studentcourseService;
	}

}

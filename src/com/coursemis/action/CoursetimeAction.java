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
import com.coursemis.model.Coursetime;
import com.coursemis.model.Teacher;
import com.coursemis.service.ICourseService;
import com.coursemis.service.ICoursetimeService;
import com.coursemis.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class CoursetimeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private HttpServletRequest request;
    private HttpServletResponse response;
    
	private ICoursetimeService coursetimeService;
	private ICourseService courseService;
	private UserService teacherService;
	
	public void addCoursetime() throws IOException {
		System.out.println("addCoursetime:___");
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		String name_course = request.getParameter("name_course");
		int weekday = Integer.parseInt(request.getParameter("weekday"));
		int startclass = Integer.parseInt(request.getParameter("startclass"));
		int endclass = Integer.parseInt(request.getParameter("endclass"));
		String address_course = request.getParameter("address_course");
		System.out.println("name_course:"+name_course+"weekday:"+weekday+"  class:"+startclass+"~"+endclass);
	
		
		JSONObject resp = new JSONObject();
		Course course = new Course();
		Teacher teacher = teacherService.getTeacherById(teacherid);
		System.out.println("teacher:"+teacher.toString());
		course.setTeacher(teacher);
		course.setCName(name_course);
		course.setCNum("");
		
		course.setCFlag(false);
		course.setCPointTotalNum(0);
		
		boolean addCourseSuccess = courseService.addCourse(course);
		
		
		Coursetime coursetime = new Coursetime();
		coursetime.setCourse(course);///**
		coursetime.setCtWeekDay(weekday);
		coursetime.setCtStartClass(startclass);
		coursetime.setCtEndClass(endclass);
		coursetime.setCtAddress(address_course);
		
		coursetime.setCtWeekNum(8);
		coursetime.setCtWeekChoose(1);
		
		boolean addCoursetimeSuccess = coursetimeService.addCourseTime(coursetime);
		
		//List<Course> courseList = courseService.getCourseByTeacherId(teacherid);
		//System.out.println("courseList.num = "+courseList.size());

			/*JSONArray jsonArray = new JSONArray();
			
			List<Course> list_temp = null;
			for(int i=0;i<courseList.size();i++){
				JSONObject object_temp = new JSONObject();
				
				object_temp.element("CId", courseList.get(i).getCId());
				object_temp.element("CName",courseList.get(i).getCName());
				object_temp.element("CNum",courseList.get(i).getCNum());
				
				Teacher teacher_temp = courseList.get(i).getTeacher();
				JSONObject object_temp_2 = new JSONObject();
				object_temp_2.element("TId",teacher_temp.getTId());
				object_temp.element("teacher", object_temp_2);
				
				object_temp.element("CFlag",courseList.get(i).getCFlag());
				object_temp.element("CPointTotalNum",courseList.get(i).getCPointTotalNum());
				System.out.println("object_temp:"+object_temp);
				jsonArray.add(i, object_temp);
				
			}
			
			resp.put("result", jsonArray);*/
		if(addCourseSuccess&&addCoursetimeSuccess){
			resp.put("result", "添加课程成功");
			System.out.println("添加课程成功");
		}
		else{
			resp.put("result", "添加课程失败");
			System.out.println("添加课程失败");
		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	public void editCoursetime() throws IOException {
		System.out.println("editCoursetime:___");
		//int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		
		String name_course = request.getParameter("name_course");
		int weekday = Integer.parseInt(request.getParameter("weekday"));
		
		int startclass = Integer.parseInt(request.getParameter("startclass"));
		int endclass = Integer.parseInt(request.getParameter("endclass"));
		String address_course = request.getParameter("address_course");
		System.out.println("name_course:"+name_course+"weekday:"+weekday+"  class:"+startclass+"~"+endclass);
		System.out.println("address_course:"+address_course);
		
		
		Course course = new Course();
		course.setCId(courseid);
		course.setCName(name_course);
	
		
		boolean editCourseSuccess = courseService.updateCourse(course);
		
		int coursetimeid = coursetimeService.getCoursetimeByCourseId(courseid).get(0).getCtId();
		System.out.println("coursetimeid:"+coursetimeid);
		
		Coursetime coursetime = new Coursetime();
		coursetime.setCtId(coursetimeid);
		coursetime.setCtWeekDay(weekday);
		coursetime.setCtStartClass(startclass);
		coursetime.setCtEndClass(endclass);
		coursetime.setCtAddress(address_course);
	
		boolean editCoursetimeSuccess = coursetimeService.updateCourseTime(coursetime);
		JSONObject resp = new JSONObject();
		if(editCourseSuccess&&editCoursetimeSuccess){
			resp.put("result", "编辑课程成功");
			System.out.println("编辑课程成功");
		}
		else{
			resp.put("result", "编辑课程失败");
			System.out.println("编辑课程失败");
		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	public void deleteCourseOfTeacher() throws IOException{
		System.out.println("deleteCourseOfTeacher...");
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		System.out.println("teacherid:"+teacherid+"courseid:"+courseid);
		
		
		boolean delCoursetimeSuccess = coursetimeService.deleteCoursetimeByCid(courseid);
		
		System.out.println("pppppppppppppppp:");
		boolean delCourseSuccess = courseService.deleteCourse(courseid);
		System.out.println("1111111111111:");
		JSONObject resp = new JSONObject();
		System.out.println("2222222222222:");
		if(delCoursetimeSuccess&&delCourseSuccess){
			resp.put("result", "删除课程成功");
			System.out.println("删除课程成功");
		}
		else{
			resp.put("result", "删除课程失败");
			System.out.println("删除课程失败");
		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	public void getCourseInfo() throws IOException{
		System.out.println("courseinfo...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		System.out.println("courseid:"+courseid);
		
		List<Coursetime> coursetimeList = coursetimeService.getCoursetimeByCourseId(courseid);
		
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Coursetime> list_temp = null;
		for(int i=0;i<coursetimeList.size();i++){
			JSONObject object_temp = new JSONObject();
			object_temp.element("CtAddress", coursetimeList.get(i).getCtAddress());
			object_temp.element("CtId", coursetimeList.get(i).getCtId());
			object_temp.element("CtWeekNum", coursetimeList.get(i).getCtWeekNum());
			object_temp.element("CtWeekChoose", coursetimeList.get(i).getCtWeekChoose());
			object_temp.element("CtWeekDay", coursetimeList.get(i).getCtWeekDay());
			
			object_temp.element("CtStartClass", coursetimeList.get(i).getCtStartClass());
			object_temp.element("CtEndClass", coursetimeList.get(i).getCtEndClass());
			
			Course course_temp = courseService.getCourseById(coursetimeList.get(i).getCourse().getCId());
			
			JSONObject object_temp2 = new JSONObject();
		
			object_temp2.element("CId", course_temp.getCId());
			//Course course_temp2 = courseService.getCourseById(course_temp.getCId());
			object_temp2.element("CName", course_temp.getCName());
		
			object_temp.element("course", object_temp2);
		
			jsonArray.add(i, object_temp);
			System.out.println("qqqq:");
		}
		
		System.out.println("PPPPPPPPPPPPPPO");
		resp.put("result", jsonArray);
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	public ICoursetimeService getCoursetimeService() {
		return coursetimeService;
	}

	public void setCoursetimeService(ICoursetimeService coursetimeService) {
		this.coursetimeService = coursetimeService;
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

	
	
}

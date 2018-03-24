package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.model.Course;
import com.coursemis.model.Coursetime;
import com.coursemis.model.Student;
import com.coursemis.model.Studentcourse;
import com.coursemis.service.ICourseService;
import com.coursemis.service.ICoursetimeService;
import com.coursemis.service.IStudentService;
import com.coursemis.service.IStudentcourseService;
import com.opensymphony.xwork2.ActionSupport;

public class Student_CourseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private HttpServletRequest request;
    private HttpServletResponse response;
    private IStudentService studentService;
    private IStudentcourseService studentcourseService;
    private ICourseService courseService;
    private ICoursetimeService coursetimeService;
    
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response=arg0;
	}
	
	public void getStudentTelNumByCId() throws IOException{
		System.out.println("getStudentTelNumByCId...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		
		List<Studentcourse> scList = studentcourseService.getStudentcourseByCId(courseid);
	
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		System.out.println("222222222222222222...");
		for(int i=0;i<scList.size();i++){
			System.out.println("3333333333333333:");
			JSONObject object_temp = new JSONObject();
			
			object_temp.element("STel", studentService.searchStudentTel(scList.get(i).getStudent().getSId()));
			
			jsonArray.add(i, object_temp);
		}
		resp.put("result", jsonArray);
		
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	public void getAllStudent() throws IOException{
		System.out.println("getAllStudent...");
		List<Student> studentList1 = studentService.getStudentByClass("0901");
		List<Student> studentList2 = studentService.getStudentByClass("0902");
		List<Student> studentList3 = studentService.getStudentByClass("0903");
		
		JSONObject resp = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONArray jsonArray2 = new JSONArray();
		JSONArray jsonArray3 = new JSONArray();
		for(int i=0;i<studentList1.size();i++){
			JSONObject object_temp = new JSONObject();
			object_temp.element("SId", studentList1.get(i).getSId());
			object_temp.element("SName", studentList1.get(i).getSName());
			object_temp.element("SNum", studentList1.get(i).getSNum());
			object_temp.element("SSex", studentList1.get(i).getSSex());
			object_temp.element("SDepartment", studentList1.get(i).getSDepartment());
			object_temp.element("SClass", studentList1.get(i).getSClass());
			object_temp.element("STel", studentList1.get(i).getSTel());
			object_temp.element("SEmail", studentList1.get(i).getSEmail());
			object_temp.element("SPassword", studentList1.get(i).getSPassword());
			jsonArray1.add(i, object_temp);
			System.out.println("777777777777777777:");
		}
		for(int i=0;i<studentList2.size();i++){
			JSONObject object_temp = new JSONObject();
			
			object_temp.element("SId", studentList2.get(i).getSId());
			object_temp.element("SName", studentList2.get(i).getSName());
			object_temp.element("SNum", studentList2.get(i).getSNum());
			object_temp.element("SSex", studentList2.get(i).getSSex());
			object_temp.element("SDepartment", studentList2.get(i).getSDepartment());
			object_temp.element("SClass", studentList2.get(i).getSClass());
			object_temp.element("STel", studentList2.get(i).getSTel());
			object_temp.element("SEmail", studentList2.get(i).getSEmail());
			object_temp.element("SPassword", studentList2.get(i).getSPassword());
			
			jsonArray2.add(i, object_temp);
			System.out.println("qqqq2:");
		}
		for(int i=0;i<studentList3.size();i++){
			JSONObject object_temp = new JSONObject();
			
			object_temp.element("SId", studentList3.get(i).getSId());
			object_temp.element("SName", studentList3.get(i).getSName());
			object_temp.element("SNum", studentList3.get(i).getSNum());
			object_temp.element("SSex", studentList3.get(i).getSSex());
			object_temp.element("SDepartment", studentList3.get(i).getSDepartment());
			object_temp.element("SClass", studentList3.get(i).getSClass());
			object_temp.element("STel", studentList3.get(i).getSTel());
			object_temp.element("SEmail", studentList3.get(i).getSEmail());
			object_temp.element("SPassword", studentList3.get(i).getSPassword());
			
			jsonArray3.add(i, object_temp);
			System.out.println("qqqq3:");
		}
		
		
		resp.put("result1", jsonArray1);
		resp.put("result2", jsonArray2);
		resp.put("result3", jsonArray3);
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	public void getStudentNotInCourse() throws IOException{
		System.out.println("getStudentNotInCourse...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		System.out.println("courseid:"+courseid);
		
		List<Student> studentList = studentService.getStudentNotInCourse(courseid);
		
		/*List<Studentcourse> studentcourse = studentcourseService.getStudentcourseElseByCId(courseid);
		*/
		List<Student> studentList1 = new ArrayList<Student>();
		List<Student> studentList2 = new ArrayList<Student>();
		List<Student> studentList3 = new ArrayList<Student>();
		
		for(int i = 0; i < studentList.size();i++){
			//int sid = studentcourse.get(i).getStudent().getSId();
			//Student student_temp = studentService.getStudentById(sid);
			Student student_temp = studentList.get(i);
			System.out.println("classname:"+student_temp.getSClass());
			if(student_temp.getSClass().equals("0901") ){
				studentList1.add(student_temp);
			}
			if(student_temp.getSClass().equals("0902") ){
				studentList2.add(student_temp);
			}
			if(student_temp.getSClass().equals("0903") ){
				studentList3.add(student_temp);
			}
		}
		
		System.out.println("111111111111111111..."+ studentList1.size());
		JSONObject resp = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONArray jsonArray2 = new JSONArray();
		JSONArray jsonArray3 = new JSONArray();
		for(int i=0;i<studentList1.size();i++){
			JSONObject object_temp = new JSONObject();
			object_temp.element("SId", studentList1.get(i).getSId());
			object_temp.element("SName", studentList1.get(i).getSName());
			object_temp.element("SNum", studentList1.get(i).getSNum());
			object_temp.element("SSex", studentList1.get(i).getSSex());
			object_temp.element("SDepartment", studentList1.get(i).getSDepartment());
			System.out.println("555555555555555555555:");
			object_temp.element("SClass", studentList1.get(i).getSClass());
			object_temp.element("STel", studentList1.get(i).getSTel());
			object_temp.element("SEmail", studentList1.get(i).getSEmail());
			object_temp.element("SPassword", studentList1.get(i).getSPassword());
			
			jsonArray1.add(i, object_temp);
			System.out.println("777777777777777777:");
		}
		for(int i=0;i<studentList2.size();i++){
			JSONObject object_temp = new JSONObject();
			
			object_temp.element("SId", studentList2.get(i).getSId());
			object_temp.element("SName", studentList2.get(i).getSName());
			object_temp.element("SNum", studentList2.get(i).getSNum());
			object_temp.element("SSex", studentList2.get(i).getSSex());
			object_temp.element("SDepartment", studentList2.get(i).getSDepartment());
			object_temp.element("SClass", studentList2.get(i).getSClass());
			object_temp.element("STel", studentList2.get(i).getSTel());
			object_temp.element("SEmail", studentList2.get(i).getSEmail());
			object_temp.element("SPassword", studentList2.get(i).getSPassword());
			
			jsonArray2.add(i, object_temp);
		}
		for(int i=0;i<studentList3.size();i++){
			JSONObject object_temp = new JSONObject();
			
			object_temp.element("SId", studentList3.get(i).getSId());
			object_temp.element("SName", studentList3.get(i).getSName());
			object_temp.element("SNum", studentList3.get(i).getSNum());
			object_temp.element("SSex", studentList3.get(i).getSSex());
			object_temp.element("SDepartment", studentList3.get(i).getSDepartment());
			object_temp.element("SClass", studentList3.get(i).getSClass());
			object_temp.element("STel", studentList3.get(i).getSTel());
			object_temp.element("SEmail", studentList3.get(i).getSEmail());
			object_temp.element("SPassword", studentList3.get(i).getSPassword());
			
			jsonArray3.add(i, object_temp);
			System.out.println("qqqq3:");
		}
		
		
		resp.put("result1", jsonArray1);
		resp.put("result2", jsonArray2);
		resp.put("result3", jsonArray3);
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	
	public void getCourseOfStudent() throws IOException{
		System.out.println("getCourseOfStudent...");
		int studentid = Integer.parseInt(request.getParameter("studentid"));
		System.out.println("studentid:"+studentid);
		
		List<Studentcourse> studentcourseList = studentcourseService.getStudentcourseBySId(studentid);
		System.out.println("fgggggggggggggggggg:"+ studentcourseList.size());
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0;i<studentcourseList.size();i++){
			JSONObject object_temp = new JSONObject();
			System.out.println("fgggggggggggggggggg");
			int courseid_temp = studentcourseList.get(i).getCourse().getCId();
			Course course_temp = courseService.getCourseById(courseid_temp);
			List<Coursetime> coursetime_temp = coursetimeService.getCoursetimeByCourseId(courseid_temp);
			object_temp.element("CId", course_temp.getCId());
			object_temp.element("CName", course_temp.getCName());
			
			object_temp.element("ctWeekChoose", coursetime_temp.get(0).getCtWeekChoose());
			object_temp.element("ctWeekDay", coursetime_temp.get(0).getCtWeekDay());
			object_temp.element("ctStartClass", coursetime_temp.get(0).getCtStartClass());
			object_temp.element("ctEndClass", coursetime_temp.get(0).getCtEndClass());
			object_temp.element("ctAddress", coursetime_temp.get(0).getCtAddress());
			
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
	
	
	public void getStudentOfCourse() throws IOException{
		System.out.println("getStudentOfCourse...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		System.out.println("courseid:"+courseid);
		
		List<Studentcourse> studentcourseList = studentcourseService.getStudentcourseByCId(courseid);
		System.out.println("studentList.num = "+studentcourseList.size());
		
		List<Student> list_temp = new ArrayList<Student>();
		for(int i=0;i<studentcourseList.size();i++){
			System.out.println("aaaaaaaaaaaaaaa:");	
			System.out.println("sid:"+studentcourseList.get(i).getStudent().getSId());	
			Student student_temp = 
				studentService.getStudentById(studentcourseList.get(i).getStudent().getSId());
			list_temp.add(student_temp);
		}
		
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		System.out.println("cccccccccccccc:");	
		
		for(int i=0;i<list_temp.size();i++){
			JSONObject object_temp = new JSONObject();
			
			object_temp.element("SId", list_temp.get(i).getSId());
			object_temp.element("SName", list_temp.get(i).getSName());
			object_temp.element("SNum", list_temp.get(i).getSNum());
			object_temp.element("SSex", list_temp.get(i).getSSex());
			object_temp.element("SDepartment", list_temp.get(i).getSDepartment());
			object_temp.element("SClass", list_temp.get(i).getSClass());
			object_temp.element("STel", list_temp.get(i).getSTel());
			object_temp.element("SEmail", list_temp.get(i).getSEmail());
			object_temp.element("SPassword", list_temp.get(i).getSPassword());
			
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

	
	
	public void addStudentToCourse() throws IOException{
		System.out.println("addStudentToCourse...");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		int studentsize = Integer.parseInt(request.getParameter("studentsize"));
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+courseid+"studentsize:"+studentsize);
		List<Integer> studentidList = new ArrayList<Integer>();
		System.out.println("bbbbbbbbbbbbbbbbbb");
		for(int i=0;i<studentsize;i++){
			System.out.println("cccccccccccccccc");
			studentidList.add( Integer.parseInt(request.getParameter("studentid"+i)) );
		}
		System.out.println("studentsize:"+studentsize);
		
		boolean addStudentOfCourse = studentcourseService.addStudentToCourse(studentidList, courseid);
		JSONObject resp = new JSONObject();
		System.out.println("2222222222222:");
		
		if(addStudentOfCourse){
			resp.put("result", "添加学生到该课程成功");
			System.out.println("添加学生到该课程成功");
		}
		else{
			resp.put("result", "添加学生到该课程失败");
			System.out.println("添加学生到该课程失败");
		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	
	public void delAllStudentOfCourse() throws IOException{
		System.out.println("getStudentOfCourse...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		System.out.println("courseid:"+courseid);
		
		boolean delAllStudentOfCourse = studentcourseService.deleteNoticeStudentcourse(courseid);
		
		JSONObject resp = new JSONObject();
		System.out.println("2222222222222:");
		if(delAllStudentOfCourse){
			resp.put("result", "清空该课程中的所有学生成功");
			System.out.println("清空该课程中的所有学生成功");
		}
		else{
			resp.put("result", "清空该课程中的所有学生失败");
			System.out.println("清空该课程中的所有学生失败");
		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	
	public void delStudentOfCourse() throws IOException{
		System.out.println("delStudentOfCourse...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		int studentid = Integer.parseInt(request.getParameter("studentid"));
		System.out.println("courseid:"+courseid+"  studentid:"+studentid);
		
		boolean delStudentOfCourse = studentcourseService.deleteStudentOfCourse(courseid, studentid);
		
		JSONObject resp = new JSONObject();
		System.out.println("2222222222222:");
		if(delStudentOfCourse){
			resp.put("result", "删除该课程中学生成功");
			System.out.println("删除该课程中学生成功");
		}
		else{
			resp.put("result", "删除该课程中学生失败");
			System.out.println("删除该课程中学生失败");
		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	
	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
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

	public ICoursetimeService getCoursetimeService() {
		return coursetimeService;
	}

	public void setCoursetimeService(ICoursetimeService coursetimeService) {
		this.coursetimeService = coursetimeService;
	}

}

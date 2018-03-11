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
import com.coursemis.model.Courseevaluation;
import com.coursemis.model.Coursetime;
import com.coursemis.service.ICourseService;
import com.coursemis.service.ICourseevaluationService;
import com.coursemis.service.IStudentService;
import com.opensymphony.xwork2.ActionSupport;

public class EvaluationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private HttpServletRequest request;
    private HttpServletResponse response;
    private ICourseevaluationService courseevaluationService;
    private IStudentService studentService;
    private ICourseService courseService;
    
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}
	
	public void addEvaluation() throws IOException{
		System.out.println("addEvaluation...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		int studentid = Integer.parseInt(request.getParameter("studentid"));
		int option1 = Integer.parseInt(request.getParameter("option1"));
		int option2 = Integer.parseInt(request.getParameter("option2"));
		int option3 = Integer.parseInt(request.getParameter("option3"));
		
		System.out.println("courseid"+courseid+"studentid:"+studentid);
		
		Courseevaluation courseevaluation = new Courseevaluation();
		courseevaluation.setCourse(courseService.getCourseById(courseid));
		courseevaluation.setStudent(studentService.getStudentById(studentid));
		courseevaluation.setCeWeekNum(16);
		courseevaluation.setCeQuestion1(option1);
		courseevaluation.setCeQuestion2(option2);
		courseevaluation.setCeQuestion3(option3);
		
		
		boolean addEvaluation = courseevaluationService.addCourseevaluation(courseevaluation);
		
		
		System.out.println("addEvaluation:"+addEvaluation);
		
		JSONObject resp = new JSONObject();
		System.out.println("2222222222222:");
		
		if(addEvaluation){
			resp.put("result", "添加评分到该课程成功");
			System.out.println("添加评分到该课程成功");
		}
		else{
			resp.put("result", "添加评分到该课程失败");
			System.out.println("添加评分到该课程失败");
		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	
	public void getEvaluation() throws IOException{
		System.out.println("getEvaluation...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		
		System.out.println("courseid:"+courseid);
		
		/*List<Courseevaluation> courseevaluationList = courseevaluationService.getCourseevaluationByCourseId(courseid);
		
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Courseevaluation> list_temp = null;
		for(int i=0;i<courseevaluationList.size();i++){
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
		out.close();*/
		
		JSONObject resp = new JSONObject();
		System.out.println("2222222222222:");
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<5;i++){
			JSONObject object_temp = new JSONObject();
			object_temp.element("year", 2008.0+i);
			object_temp.element("point", 70.0+5*i);
			jsonArray.add(object_temp);
		}
		
		resp.put("result", jsonArray);
		
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	
	public void getEvaluationZhuXing() throws IOException{
		System.out.println("getEvaluationZhuXing...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		
		System.out.println("courseid:"+courseid);
		
		/*List<Courseevaluation> courseevaluationList = courseevaluationService.getCourseevaluationByCourseId(courseid);
		
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Courseevaluation> list_temp = null;
		for(int i=0;i<courseevaluationList.size();i++){
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
		out.close();*/
		
		JSONObject resp = new JSONObject();
		System.out.println("2222222222222:");
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<5;i++){
			JSONObject object_temp = new JSONObject();
			object_temp.element("year", 2008+i);
			object_temp.element("grade1", 6+i);
			object_temp.element("grade2", 2+i);
			object_temp.element("grade3", 6-i);
			jsonArray.add(object_temp);
		}
		
		resp.put("result", jsonArray);
		
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	
	public void getEvaluationSms() throws IOException{
		System.out.println("getEvaluationSms...");
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		
		System.out.println("courseid:"+courseid);
		
		/*List<Courseevaluation> courseevaluationList = courseevaluationService.getCourseevaluationByCourseId(courseid);
		
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Courseevaluation> list_temp = null;
		for(int i=0;i<courseevaluationList.size();i++){
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
		out.close();*/
		
		JSONObject resp = new JSONObject();
		System.out.println("2222222222222:");
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<3;i++){
			JSONObject object_temp = new JSONObject();
			object_temp.element("personnum", 20+10*i);
			jsonArray.add(object_temp);
		}
		
		resp.put("result", jsonArray);
		
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
	}
	
	
	public ICourseevaluationService getCourseevaluationService() {
		return courseevaluationService;
	}
	public void setCourseevaluationService(
			ICourseevaluationService courseevaluationService) {
		this.courseevaluationService = courseevaluationService;
	}
	public IStudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}
	public ICourseService getCourseService() {
		return courseService;
	}
	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}
}

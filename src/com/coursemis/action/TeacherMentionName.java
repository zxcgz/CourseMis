package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.coursemis.dao.impl.LocationDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Coursetime;
import com.coursemis.model.Student;
import com.coursemis.model.Studentcourse;
import com.coursemis.model.Teacher;
import com.coursemis.service.ICourseService;
import com.coursemis.service.ICoursetimeService;
import com.coursemis.service.ILocationService;
import com.coursemis.service.ISourceManageService;
import com.coursemis.service.IStudentService;
import com.coursemis.service.IStudentcourseService;
import com.coursemis.uti.SignInTimer;
import com.coursemis.websocket.WebSocket;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherMentionName extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;

	private ICourseService courseService;
	private ICoursetimeService coursetimeService;
	private IStudentcourseService studentcourseService;
	private IStudentService studentService;
	private ISourceManageService sourceManageService;
	private ILocationService locationService;

	public void selectStudentCourseInfo() throws IOException {
		System.out.println("AAAAAAAAAAAAAA");
		String courseInfo = request.getParameter("courseInfo");
		int cid = Integer.parseInt(courseInfo.substring(0,
				courseInfo.indexOf(" ")));
		List<Studentcourse> scl = studentcourseService
				.getStudentcourseByCId(cid);
		JSONObject resp = new JSONObject();
		System.out.println(111111111);

		JSONArray jsonArray = new JSONArray();
		System.out.println(111111112);
		// List<Course>
		// courseList=coursedao.getCourseByTeacherId(Integer.parseInt(tid));
		// System.out.println(courseList.size());
		if (scl != null || scl.size() != 0) {
			for (int i = 0; i < scl.size(); i++) {
				JSONObject object_temp = new JSONObject();

				System.out.println("111111:");
				object_temp
						.element("SNumber", scl.get(i).getStudent().getSId());
				object_temp.element(
						"SName",
						studentService.getStudentById(
								scl.get(i).getStudent().getSId()).getSName());
				object_temp.element("SCPointNum", scl.get(i).getScPointNum());
				object_temp.element("ScPointTotalNum", scl.get(i)
						.getScPointTotalNum());
				jsonArray.add(i, object_temp);
				System.out.println("qqqq:");
			}

		}

		resp.put("result", jsonArray);

		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	public void teacherCourseStudentCount() throws Exception {
		
		String type = request.getParameter("type") ;
		int size = Integer.parseInt(request.getParameter("size") );
		System.out.println("测试。点名。。。"+type+"...."+size);
		if (type.equals("call")) {
			//点名
			System.out.println("测试。。。点名中");
			for (int i = 0; i < size; i++) {
				int sid = Integer.parseInt(request.getParameter(i+"")) ;
				System.out.println("测试。。。。"+sid);
				//点名
				Student student = new Student() ;
				student.setSId(sid) ;
				WebSocket.callTheRoll(student) ;
			}
		}
		JSONObject resp = new JSONObject();
		JSONObject resp_info = new JSONObject();
		resp_info.put("success", "success");
		resp.put("result", resp_info);
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		
		
		
		/*boolean flag = true;Enumeration pNames = request.getParameterNames();
		System.out.print("哈哈哈哈哈哈哈哈");
		int i = 0;
		while (pNames.hasMoreElements()) {
			String name = (String) pNames.nextElement();
			String value = request.getParameter(name);
			String scid = (String) value.substring(0, value.indexOf(" "));
			Studentcourse studentcourse = studentcourseService
					.searchStudentcourse(Integer.parseInt(scid));
			System.out.println("测试。。。。"+name+"速度速度"+value+"啊飒飒"+scid);
			if (flag) {
				int cid = studentcourse.getCourse().getCId();
				Course course = courseService.getCourseById(cid);
				course.setCPointTotalNum(course.getCPointTotalNum() + 1);
				courseService.updateCourse(course);
				flag = false;
			}

			studentcourse.setScPointNum(studentcourse.getScPointNum() + 1);
			studentcourse
					.setScPointTotalNum(studentcourse.getScPointTotalNum() + 1);
			studentcourseService.updateStudentcourse(studentcourse);

			i++;
		}

		System.out.print("I--------" + i);
		JSONObject resp = new JSONObject();
		JSONObject resp_info = new JSONObject();
		resp_info.put("success", "success");
		resp.put("result", resp_info);

		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		System.out.print("end>>>>>>>");*/

	}

	/**
	 * 教师端签到
	 * @throws Exception
	 */
	public void teacherSignIn() throws Exception {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int hour = Integer.parseInt(request.getParameter("signInHour"));
		int minute = Integer.parseInt(request.getParameter("signInMinute"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double
				.parseDouble(request.getParameter("longitude"));
		// System.out.println("课程Id："+cid);
		Course course = courseService.getCourseById(cid);
		course.setCFlag(true);
		System.out.println("设置CFlag：" + course.getCName());
		courseService.updateCourse(course);
		// 将教师所在的位置信息写入数据库中
		boolean insertLocation = locationService.insertLocation(tid, cid,
				latitude, longitude);
		if (insertLocation) {
			SignInTimer sit = new SignInTimer(hour, minute, cid);
			Thread t = new Thread(sit, "定时签到线程");
			t.start();
			PrintWriter writer = response.getWriter();
			writer.write("success");
		}

	}

	public void selectTeacherCourseStudentNames() throws Exception {
		String cid = request.getParameter("cid");
		System.out.println("cid:" + cid + "cid:" + Integer.parseInt(cid));
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Studentcourse> scl = studentcourseService
				.getStudentcourseByCId(Integer.parseInt(cid));
		System.out.println("测试。。。。" + scl.size());
		Gson gson = new Gson();
		if (scl != null || scl.size() != 0) {
			for (int i = 0; i < scl.size(); i++) {
				/*
				 * Studentcourse sc = scl.get(i) ;
				 * System.out.println("hhahahhah"
				 * +(scl.get(i).getStudent().getSClass()==null)); Studentcourse
				 * studentcourse = new Studentcourse() ; Student student = new
				 * Student() ; student.setSId(sc.getStudent().getSId()) ;
				 * student.setSClass(sc.getStudent().getSClass()) ; String json
				 * = gson.toJson(student) ;
				 * System.out.println("测试////////。。。。。。");
				 */
				JSONObject object_temp = new JSONObject();
				object_temp.element("scId", scl.get(i).getScId());
				object_temp
						.element("SNumber", scl.get(i).getStudent().getSId());
				object_temp.element(
						"SName",
						studentService.getStudentById(
								scl.get(i).getStudent().getSId()).getSName());
				jsonArray.add(i, object_temp);
			}
		}
		resp.put("result", jsonArray);

		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		System.out.print("end>>>>>>>");
	}

	public void selectCourse() throws IOException // /*teacherid为null*//
	{
		// int tid=Integer.parseInt(request.getParameter("tid"));
		// String tid = request.getParameter("tid");
		// System.out.println("tid:"+tid);
		int teacherid = Integer.parseInt(request.getParameter("tid"));
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Course> courseList = courseService.getCourseByTeacherId(teacherid);
		System.out.println(courseList.size());
		if (courseList != null || courseList.size() != 0) {
			for (int i = 0; i < courseList.size(); i++) {
				JSONObject object_temp = new JSONObject();
				object_temp.element("CId", courseList.get(i).getCId());
				object_temp.element("CName", courseList.get(i).getCName());
				System.out.println("CName:" + courseList.get(i).getCName());
				jsonArray.add(i, object_temp);
			}
		}
		resp.put("result", jsonArray);

		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	public void selectCourse_week() throws Exception {
		String cid = request.getParameter("cid");
		System.out.println(Integer.parseInt(cid));
		JSONObject resp = new JSONObject();
		System.out.println(1111);
		Course c = courseService.getCourseById(Integer.parseInt(cid));
		Coursetime ct = coursetimeService.getCoursetime(Integer.parseInt(cid));
		System.out.println(2222);

		System.out.println("cid" + cid);
		JSONObject object_temp = new JSONObject();
		System.out.println(ct.getCtWeekChoose() + "ct.getCtWeekChoose()");
		object_temp.element("weekchoose", ct.getCtWeekChoose());
		System.out.println(2222 + 1212121);
		object_temp.element("weeknumber", ct.getCtWeekNum());
		System.out.println(2222 + 1212121);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, object_temp);
		System.out.println(2222 + 1212121);
		resp.put("result", jsonArray);
		System.out.println(2222 + 1212121);
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();

	}

	public void selectCourse_time() throws IOException {
		String cid = request.getParameter("cid");
		System.out.println(Integer.parseInt(cid));
		JSONObject resp = new JSONObject();
		System.out.println(1111);
		List<Coursetime> ctl = coursetimeService
				.getCoursetimeByCourseId(Integer.parseInt(cid));
		System.out.println(2222);
		JSONArray jsonArray = new JSONArray();
		System.out.println("cid" + cid);

		System.out.println(ctl.size());
		if (ctl != null || ctl.size() != 0) {
			for (int i = 0; i < ctl.size(); i++) {
				JSONObject object_temp = new JSONObject();
				System.out.println("000000000000:");
				object_temp.element("weekday", ctl.get(i).getCtWeekDay() + "");
				System.out.println("111111:");
				object_temp.element("start", ctl.get(i).getCtStartClass());
				System.out.println("end" + ctl.get(i).getCtEndClass());
				System.out.println("aaaa:");
				jsonArray.add(i, object_temp);
				System.out.println("qqqq:");
			}
		}
		resp.put("result", jsonArray);
		System.out.println(2222 + 1212121);
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
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

	public IStudentcourseService getStudentcourseService() {
		return studentcourseService;
	}

	public void setStudentcourseService(
			IStudentcourseService studentcourseService) {
		this.studentcourseService = studentcourseService;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public ISourceManageService getSourceManageService() {
		return sourceManageService;
	}

	public void setSourceManageService(ISourceManageService sourceManageService) {
		this.sourceManageService = sourceManageService;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	public ILocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(ILocationService locationService) {
		this.locationService = locationService;
	}

}

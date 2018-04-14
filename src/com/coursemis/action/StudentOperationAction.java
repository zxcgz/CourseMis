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

import com.coursemis.dao.ILocationDAO;
import com.coursemis.dao.ISourceManageDAO;
import com.coursemis.dao.IStudentHomeWorkDAO;
import com.coursemis.dao.impl.LocationDAO;
import com.coursemis.dao.impl.RegulationDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Location;
import com.coursemis.model.Period;
import com.coursemis.model.Score;
import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Studentcourse;
import com.coursemis.model.Studenthomework;
import com.coursemis.service.ICourseService;
import com.coursemis.service.ILocationService;
import com.coursemis.service.IPeriodService;
import com.coursemis.service.IScoreService;
import com.coursemis.service.ISignInService;
import com.coursemis.service.IStudentService;
import com.coursemis.service.IStudentcourseService;
import com.coursemis.service.UserService;
import com.coursemis.uti.LocationUtil;
import com.opensymphony.xwork2.ActionSupport;

public class StudentOperationAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;

	private IStudentcourseService studentcourseService;
	private IStudentService studentService;
	private ICourseService courseService;
	private UserService teacherService;

	private IStudentHomeWorkDAO studentHomeWorkDAO;
	private ISourceManageDAO sourceManageDAO;
	private ILocationService locationService;
	private ISignInService signInService;
	private IScoreService scoreService;
	private IPeriodService periodService;
	private RegulationDAO rdao = new RegulationDAO();// /

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	public void studentClassHMPath() throws Exception {
		String shinfo = request.getParameter("shinfo");
		String shid = shinfo.substring(0, shinfo.indexOf(" "));
		String name = rdao.getStudenthomeworkName(Integer.parseInt(shid));

		System.out.println("������������");
		JSONObject resp = new JSONObject();

		resp.put("result", name);
		System.out.println("resp:" + resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	public void studentClassCourseCheckhomework() throws Exception {
		String sid = request.getParameter("sid");
		String courseinfo = request.getParameter("courseinfo");
		int smid = Integer.parseInt(courseinfo.substring(1,
				courseinfo.indexOf("次")));

		List<Integer> shl = rdao.getClassStudenthomeworkListBySMId(smid);
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		if (shl != null || shl.size() != 0) {

			for (int i = 0; i < shl.size(); i++) {
				JSONObject object_temp = new JSONObject();
				int shid = shl.get(i);
				Studenthomework sh = studentHomeWorkDAO
						.getStudenthomework(shid);

				object_temp.element("shid", shid);
				object_temp.element("sname",
						studentService.getStudentById(sh.getStudent().getSId())
								.getSName());
				object_temp.element("score", sh.getShScore() + "");

				jsonArray.add(i, object_temp);

			}

		}

		resp.put("result", jsonArray);
		System.out.println("resp:" + resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	/**
	 * 学生签到，学生端请求的action
	 * 
	 * @throws IOException
	 */
	public void studentSignIn() throws IOException {
		// 获取到学生id
		int sid = Integer.parseInt(request.getParameter("sid"));
		// 获取到经纬度坐标
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double
				.parseDouble(request.getParameter("longitude"));
		System.out.println("位置。。" + latitude + "......" + longitude);
		List<Studentcourse> scl = studentcourseService
				.getStudentcourseBySId(sid);
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		if (scl != null || scl.size() != 0) {
			for (int i = 0; i < scl.size(); i++) {
				JSONObject object_temp = new JSONObject();
				Studentcourse sc = scl.get(i);
				Course course = courseService.getCourseById(sc.getCourse()
						.getCId());
				System.out.println(course.getCFlag() + "\t课程名"
						+ course.getCName());
				if (course.getCFlag() == true) {
					// 签到逻辑
					// 获取到教师设置的经纬度坐标
					Location location = locationService.getLocation(course);
					boolean checkLocation = checkLocation(latitude, longitude,
							location);
					if (checkLocation) {
						// 检查成功，设置签到信息
						System.out.println("设置签到成功");
						signInService.signIn(location.getTeacher().getTId(),
								location.getCourse().getCId(), sid);
						// 获取课时数
						Period period = new Period();
						period.setCourse(course);
						int periodNum = periodService.getPeriodNum(period);
						// 设置成绩
						/*
						 * Score score = new Score() ; score.setCourse(course) ;
						 * score.setSAtten(periodNum) ;
						 */
						Score score;
						score = scoreService.getScore(course.getCId(), sid,
								periodNum);
						if (score == null) {
							score = new Score();
							score.setCourse(course);
							score.setStudent(sc.getStudent());
						}
						score.setSAtten(10);
						// 将签到成绩写入成绩表中
						scoreService.insertOrUpdate(score);
						break;
					}
					System.out.print("课程id" + course.getCId());
					object_temp.element("SCId", sc.getScId());
					jsonArray.add(i, object_temp);
				}

			}

		}
		resp.put("result", jsonArray);
		System.out.println("resp:" + resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	/**
	 * 计算学生位置是否在教师位置方圆30米内
	 * 
	 * @throws IOException
	 */
	private boolean checkLocation(double s_latitude, double s_longitude,
			Location location) {
		// 调用工具类进行计算
		double distance = LocationUtil.getDistance(s_latitude, s_longitude,
				location.getLatitude(), location.getLongitude());
		System.out.println("距离     " + distance);
		distance = GetDistance(s_latitude, s_longitude, location.getLatitude(),
				location.getLongitude());
		System.out.println("距离1     " + distance);
		if (distance <= 30.0) {
			// 距离在合法的范围内
			return true;
		}
		return false;
	}

	private static double EARTH_RADIUS = 6371.393;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 计算两个经纬度之间的距离
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 1000);
		return s;
	}

	public void studentSignInComfirm() throws IOException {
		boolean flage = true;
		boolean flag = true;
		// Enumeration pNames=request.getParameterNames();
		System.out.println(request.getParameter("0") + "    "
				+ request.getParameter("1"));
		System.out.print("������`````````````");
		int i = 0;
		int size = Integer.parseInt(request.getParameter("size"));
		float latitude = Float.parseFloat(request.getParameter("latitude"));
		float longitude = Float.parseFloat(request.getParameter("longitude"));
		for (int j = 0; j < size; j++) {
			int scid = Integer.parseInt(request.getParameter(j + ""));
			int cid = studentcourseService.searchStudentcourse(scid)
					.getCourse().getCId();
			LocationDAO dao = new LocationDAO();
			double latitudeT = dao.searchLocation(cid).getLatitude();
			double longitudeT = dao.searchLocation(cid).getLongitude();
			double latitudeDifference = Math.abs(latitude - latitudeT) * 60 * 60;
			double longtitudeDifference = Math.abs(longitude - longitudeT) * 60 * 60;
			System.out.println("latitudeDifference  " + latitudeDifference
					+ "longtitudeDifference   " + longtitudeDifference);
			if (latitudeDifference < 1 && longtitudeDifference < 1) {

				Studentcourse studentcourse = studentcourseService
						.searchStudentcourse(scid);
				studentcourse.setScPointNum(studentcourse.getScPointNum() + 1);
				studentcourse
						.setScPointTotalNum(courseService.getCourseById(
								studentcourse.getCourse().getCId())
								.getCPointTotalNum());
				studentcourseService.updateStudentcourse(studentcourse);
				i++;
			} else {
				flag = false;
				break;
			}
		}
		System.out.println("i is" + i);
		JSONObject resp = new JSONObject();
		JSONObject resp_info = new JSONObject();
		if (flag) {
			resp_info.put("success", "success");
		} else {
			resp_info.put("success", "您没有在课堂附近签到");
		}
		resp.put("result", resp_info);

		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		System.out.print("end>>>>>>>");

	}

	public void selectStudentCourseInfo() throws IOException {

		String sid = request.getParameter("sid");
		List<Studentcourse> scl = studentcourseService
				.getStudentcourseBySId(Integer.parseInt(sid));
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		System.out.println(111111112);
		// List<Course>
		// courseList=coursedao.getCourseByTeacherId(Integer.parseInt(tid));
		// System.out.println(courseList.size());
		if (scl != null || scl.size() != 0) {
			for (int i = 0; i < scl.size(); i++) {
				JSONObject object_temp = new JSONObject();

				System.out.println("111111:");
				object_temp.element("CNumber", scl.get(i).getCourse().getCId());
				object_temp.element(
						"CName",
						courseService.getCourseById(
								scl.get(i).getCourse().getCId()).getCName());
				object_temp.element("SCPointNum", scl.get(i).getScPointNum());
				object_temp.element("ScPointTotalNum", scl.get(i)
						.getScPointTotalNum());
				object_temp.element(
						"CTname",
						teacherService
								.getTeacherById(
										courseService
												.getCourseById(
														scl.get(i).getCourse()
																.getCId())
												.getTeacher().getTId())
								.getTName());
				jsonArray.add(i, object_temp);
				System.out.println("qqqq:");
			}
		}
		resp.put("result", jsonArray);
		System.out.println("resp:" + resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	public void studentclasshomework_1() throws IOException {
		String courseinfo = request.getParameter("courseinfo");
		int cid = Integer.parseInt(courseinfo.substring(0,
				courseinfo.indexOf(" ")));
		System.out.println("�ٺٺٺ�1");
		List<Sourcemanage> sml = rdao.getSourcemanage(cid, 0);
		System.out.println("�ٺٺٺ�3");
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		if (sml != null || sml.size() != 0) {

			for (int i = 0; i < sml.size(); i++) {
				JSONObject object_temp = new JSONObject();

				object_temp.element("smid", sml.get(i).getSmId());
				// object_temp.element("sname",sdao.getStudentById(sh.getStudent().getSId()).getSName());
				jsonArray.add(i, object_temp);
			}

		}

		resp.put("result", jsonArray);
		System.out.println("resp:" + resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		System.out.println("�ٺٺٺ�2");

	}

	public IStudentHomeWorkDAO getStudenthomeworkDAO() {
		return studentHomeWorkDAO;
	}

	public void setStudenthomeworkDAO(IStudentHomeWorkDAO studenthomeworkDAO) {
		this.studentHomeWorkDAO = studenthomeworkDAO;
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

	public IStudentHomeWorkDAO getStudentHomeWorkDAO() {
		return studentHomeWorkDAO;
	}

	public void setStudentHomeWorkDAO(IStudentHomeWorkDAO studentHomeWorkDAO) {
		this.studentHomeWorkDAO = studentHomeWorkDAO;
	}

	public ISourceManageDAO getSourceManageDAO() {
		return sourceManageDAO;
	}

	public void setSourceManageDAO(ISourceManageDAO sourceManageDAO) {
		this.sourceManageDAO = sourceManageDAO;
	}

	public ILocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(ILocationService locationService) {
		this.locationService = locationService;
	}

	public ISignInService getSignInService() {
		return signInService;
	}

	public void setSignInService(ISignInService signInService) {
		this.signInService = signInService;
	}

	public IScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(IScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public IPeriodService getPeriodService() {
		return periodService;
	}

	public void setPeriodService(IPeriodService periodService) {
		this.periodService = periodService;
	}

}

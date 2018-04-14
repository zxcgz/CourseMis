package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.model.Course;
import com.coursemis.model.Evaluation;
import com.coursemis.model.Period;
import com.coursemis.model.Questionbank;
import com.coursemis.model.Score;
import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Student;
import com.coursemis.model.Studentcourse;
import com.coursemis.model.Studenthomework;
import com.coursemis.service.ICourseService;
import com.coursemis.service.IEvaluationService;
import com.coursemis.service.IPeriodService;
import com.coursemis.service.IQuestionBankService;
import com.coursemis.service.IScoreService;
import com.coursemis.service.ISourceManageService;
import com.coursemis.service.IStudentHomeWorkService;
import com.coursemis.service.IStudentService;
import com.coursemis.service.IStudentcourseService;
import com.coursemis.websocket.WebSocket;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherManageHomeWorkAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;

	private IStudentcourseService studentcourseService;
	private ISourceManageService sourceManageService;
	private IStudentHomeWorkService studentHomeWorkService;
	// 学生
	private IStudentService studentService;
	private IPeriodService periodService;
	private IQuestionBankService questionBankService;
	private IScoreService scoreService;
	private ICourseService courseService;
	private IEvaluationService evaluationService;

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	public void homeworkComment() throws Exception {
		String shinfo = request.getParameter("shinfo");
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

	public void homeworkCheck() throws IOException {
		JSONObject resp = null;

		String sminfo = request.getParameter("sminfo");
		int smid = Integer.parseInt(sminfo.substring(0, sminfo.indexOf(" ")));
		List<Studenthomework> shl = studentHomeWorkService
				.getStudentHomeworkListBySMId(smid);
		try {
			resp = new JSONObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.out.println(111111111);

		JSONArray jsonArray = new JSONArray();
		System.out.println(111111112);
		// List<Course>
		// courseList=coursedao.getCourseByTeacherId(Integer.parseInt(tid));
		// System.out.println(courseList.size());
		if (shl != null || shl.size() != 0) {
			for (int i = 0; i < shl.size(); i++) {
				JSONObject object_temp = new JSONObject();

				System.out.println("111111:");
				object_temp.element("shid", shl.get(i).getShId());
				object_temp.element("shname", shl.get(i).getShName());

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

	/**
	 * 不调用的方法 教师端 获取随堂测验信息 返回当前课程的每次课的测试情况
	 * 
	 * @throws IOException
	 */
	public void homeworkSelect() throws IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
	}

	/**
	 * 发送随堂测验的消息
	 * 
	 * @return
	 * @throws IOException
	 */
	public void sendTestMessage() throws IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		// int periodId = Integer.parseInt(request.getParameter("periodId")) ;
		// 查询所有的学生id
		// studentService.getStudentByClass(cid+"") ;
		System.out.println("测试cid值：：：：" + cid);
		List<Studentcourse> studentcourseByCId = studentcourseService
				.getStudentcourseByCId(cid);
		List<Integer> sids = new ArrayList<Integer>();
		for (Studentcourse studentcourse : studentcourseByCId) {
			sids.add(studentcourse.getStudent().getSId());
		}
		// 获取课时数
		Period period = new Period();
		period.setCourse(studentcourseByCId.get(0).getCourse());
		int periodNum = periodService.getPeriodNum(period);
		WebSocket.classroomTest(sids, cid, periodNum);
		JSONObject resp = new JSONObject();
		resp.put("result", "{}");
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	/**
	 * 发送提问的消息
	 * 
	 * @return
	 * @throws IOException
	 */
	public void sendQuizMessage() throws IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		List<Studentcourse> studentcourseByCId = studentcourseService
				.getStudentcourseByCId(cid);
		Random random = new Random();
		int nextInt = random.nextInt(studentcourseByCId.size());
		Student student = studentcourseByCId.get(nextInt).getStudent();
		// 向指定学生发送提问
		WebSocket.quiz(studentcourseByCId.get(nextInt).getStudent());
		JSONObject resp = new JSONObject();
		JSONObject studentJSON = new JSONObject();
		studentJSON.put("sid", student.getSId());
		System.out.println("测试。。。" + student.getSId());
		resp.put("result", studentJSON);
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	/**
	 * 发送反馈的消息
	 * 
	 * @return
	 * @throws IOException
	 */
	public void sendCallBackMessage() throws IOException {
		System.out.println("发送反馈");
		int tid = Integer.parseInt(request.getParameter("tid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		List<Studentcourse> studentcourseByCId = studentcourseService
				.getStudentcourseByCId(cid);
		List<Integer> sids = new ArrayList<Integer>();
		for (Studentcourse studentcourse : studentcourseByCId) {
			sids.add(studentcourse.getStudent().getSId());
		}
		// 获取课时数
		Period period = new Period();
		period.setCourse(studentcourseByCId.get(0).getCourse());
		int periodNum = periodService.getPeriodNum(period);
		WebSocket.callBack(sids, cid, periodNum);
		JSONObject resp = new JSONObject();
		resp.put("result", "{}");
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		// 将课时数加一
		period.setPNum(periodNum++);
		periodService.addPeriodNum(period);
	}

	/**
	 * 学生获取测验
	 * 
	 * @throws IOException
	 */
	public void getTest() throws IOException {
		try {
			int sid = Integer.parseInt(request.getParameter("sid"));
			int cid = Integer.parseInt(request.getParameter("cid"));
			// 获取课时信息
			Student student = studentService.getStudentById(sid);
			Course course = courseService.getCourseById(cid);
			Period period = new Period();
			period.setCourse(course);
			int periodNum = periodService.getPeriodNum(period);
			System.out.println("测试0。。。。" + periodNum);
			// 获取测验信息
			List<Questionbank> questionbank = questionBankService
					.getQuestionbank(cid, periodNum);
			System.out.println("测试1。。。。" + questionbank.size());
			for (Questionbank questionbank2 : questionbank) {
				questionbank2.setCourse(null);
				questionbank2.setPeriod(null);
			}
			// 组拼信息o
			Questionbank[] array = questionbank
					.toArray(new Questionbank[questionbank.size()]);
			System.out.println("测试2");
			Gson gson = new Gson();
			System.out.println("测试3");
			String json = gson.toJson(array);
			System.out.println("测试4");
			JSONObject resp = new JSONObject();
			resp.put("result", json);
			PrintWriter out = response.getWriter();
			out.print(resp.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
		}

	}

	/**
	 * 教师获取成绩
	 * 
	 * @throws IOException
	 */
	public void getScore() throws IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String type = request.getParameter("type");
		List<Studentcourse> studentcourseByCId = studentcourseService
				.getStudentcourseByCId(cid);
		List<Score> scores = new ArrayList<Score>();
		if (type.equals("all")) {
			// 获取所有学生的平均成绩
			for (Studentcourse studentcourse : studentcourseByCId) {
				// 获取一个学生的所有课时成绩列表
				System.out.println("？？？？？？？1");
				List<Score> scoresAllPeriodBySId = scoreService
						.getScoresAllPeriodBySId(cid, studentcourse
								.getStudent().getSId());
				if (scoresAllPeriodBySId == null
						|| scoresAllPeriodBySId.size() == 0) {
					break;
				}
				// 计算成绩总和
				Student studentById = studentService.getStudentById( studentcourse
								.getStudent().getSId()) ;
				Student student = new Student() ;
				student.setSName(studentById.getSName()) ;
				Score s = new Score();
				s.setSAtten(0);
				s.setSCall(0);
				s.setSQuiz(0);
				s.setSTest(0) ;
				s.setStudent(student) ;
				for (Score score : scoresAllPeriodBySId) {
					System.out.println("？？？？？？？2");
					System.out.println("？？？？？？？2.1");
					s.setSAtten(s.getSAtten() + score.getSAtten());
					System.out.println("？？？？？？？2.2");
					s.setSCall(s.getSCall() + score.getSCall());
					s.setSQuiz(s.getSQuiz() + score.getSQuiz());
					s.setSTest(s.getSTest()+score.getSTest()) ;
				}
				System.out.println("？？？？？？？3");
				// 计算平均成绩
				s.setSAtten(s.getSAtten() / (scoresAllPeriodBySId.size()));
				s.setSCall(s.getSCall() / (scoresAllPeriodBySId.size()));
				s.setSQuiz(s.getSQuiz() / (scoresAllPeriodBySId.size()));
				s.setSTest(s.getSTest() / (scoresAllPeriodBySId.size())) ;
				scores.add(s);
			}
		} else {
			// 获取所有学生的当前课时的成绩
			Period period = new Period();
			period.setCourse(courseService.getCourseById(cid));
			int periodNum = periodService.getPeriodNum(period);
			for (Studentcourse studentcourse : studentcourseByCId) {
				Score score = scoreService.getScore(cid, studentcourse
						.getStudent().getSId(), periodNum);
				scores.add(score);
			}
		}
		System.out
				.println("？？？？？？？4////" + (scores.get(0).getStudent().getSName()));
		Gson gson = new Gson();
		String json = gson.toJson(scores);
		System.out.println("？？？？？？？5");
		JSONObject resp = new JSONObject();
		System.out.println("？？？？？？？6");
		resp.put("result", json);
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();

	}

	/**
	 * 教师获取反馈
	 * 
	 * @throws IOException
	 */
	public void getCallBack() throws IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		// 获取课时数
		int periodNum = periodService.getPeriod(cid).getPNum();
		// 组拼数据
		List<Evaluation> score = evaluationService.getScore(cid);
		for (Evaluation evaluation : score) {
			evaluation.setCourse(null);
		}
		Gson gson = new Gson();
		System.out.println(score.size());
		try {
			String json = gson.toJson(score);
			JSONObject resp = new JSONObject();
			resp.put("result", json);
			PrintWriter out = response.getWriter();
			out.print(resp.toString());
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 学生发送反馈
	 * 
	 * @throws IOException
	 */
	public void sendCallBack() throws IOException {
		try {
			System.out.println("学生发送反馈1");
			int cid = Integer.parseInt(request.getParameter("cid"));
			System.out.println("学生发送反馈2");
			int sid = Integer.parseInt(request.getParameter("sid"));
			System.out.println("学生发送反馈3");
			String evaluation = request.getParameter("evaluation");
			System.out.println("学生发送反馈4");
			Gson gson = new Gson();
			System.out.println("学生发送反馈5");
			List<Double> list = new ArrayList<Double>();
			System.out.println("学生发送反馈6");
			List<Double> evaluations = gson.fromJson(evaluation, list.getClass());
			System.out.println("学生发送反馈7");
			// 获取课时数
			Integer pNum = periodService.getPeriod(cid).getPNum();
			System.out.println("学生发送反馈8......"+evaluations.size());
			evaluationService.insertOrUpdate(cid, pNum, evaluations);
			System.out.println("学生发送反馈9");
			JSONObject resp = new JSONObject();
			System.out.println("学生发送反馈10");
			resp.put("result", "{}");
			System.out.println("学生发送反馈11");
			PrintWriter out = response.getWriter();
			System.out.println("学生发送反馈12");
			out.print(resp.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 学生发送测验成绩
	 * 
	 * @throws IOException
	 */
	public void sendTest() throws IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		int result = Integer.parseInt(request.getParameter("result"));
		// 将成绩写入数据库
		Score score ;
		Student student = studentService.getStudentById(sid);
		Course course = courseService.getCourseById(cid);
		Period period = periodService.getPeriod(cid);
		score = scoreService.getScore(cid, sid, period.getPNum()) ;
		if (score==null) {
			//没有数据
			score = new Score() ;
			score.setCourse(course) ;
			score.setSPid(period.getPNum()) ;
		}
		score.setSTest(result) ;
		scoreService.insertOrUpdate(score);
		JSONObject resp = new JSONObject();
		resp.put("result", "{}");
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();

	}

	/**
	 * 教师发送提问成绩
	 * 
	 * @return
	 * @throws IOException
	 */
	public void sendQuizScore() throws IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		int quiz = Integer.parseInt(request.getParameter("score"));
		// 获取课时数
		int periodNum = periodService
				.getPeriodNum(periodService.getPeriod(cid));
		// 获取成绩对象
		Score score = scoreService.getScore(cid, sid, periodNum);
		System.out.println("获取到成绩");
		score.setSQuiz(score.getSQuiz() + quiz);
		scoreService.insertOrUpdate(score);
		JSONObject resp = new JSONObject();
		resp.put("result", "{}");
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		System.out.println("发送成功");
	}

	public IStudentcourseService getStudentcourseService() {
		return studentcourseService;
	}

	public void setStudentcourseService(
			IStudentcourseService studentcourseService) {
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

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IPeriodService getPeriodService() {
		return periodService;
	}

	public void setPeriodService(IPeriodService periodService) {
		this.periodService = periodService;
	}

	public IQuestionBankService getQuestionBankService() {
		return questionBankService;
	}

	public void setQuestionBankService(IQuestionBankService questionBankService) {
		this.questionBankService = questionBankService;
	}

	public IScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(IScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public ICourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	public IEvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(IEvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

}

package com.coursemis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.model.Note;
import com.coursemis.model.Studentcourse;
import com.coursemis.model.Teacher;
import com.coursemis.service.INoteService;
import com.coursemis.service.IStudentService;
import com.coursemis.service.IStudentcourseService;
import com.coursemis.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class NoteAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private INoteService noteService;
	private UserService teacherService;
	private IStudentService studentService;
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
	
	public void getNoteOfTeacher() throws IOException {
		System.out.println("getNoteOfTeacher:");
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		String CName = request.getParameter("CName"); //获得课程名
		System.out.println("teacherid:"+teacherid+"courseid:"+courseid+" CName:"+CName);
		
		JSONObject resp = new JSONObject();
		
		List<Studentcourse> scList = studentcourseService.getStudentcourseByCId(courseid);
		JSONArray jsonArray2 = new JSONArray();
		System.out.println("222222222222222222...");
		for(int i=0;i<scList.size();i++){
			System.out.println("3333333333333333:");
			JSONObject object_temp = new JSONObject();
			
			object_temp.element("STel", studentService.searchStudentTel(scList.get(i).getStudent().getSId()));
			
			jsonArray2.add(i, object_temp);
		}
		resp.put("result2", jsonArray2);
		
		List<Note> notes = noteService.getNoteByTeacherId(teacherid,CName);
		
		System.out.println("notes'num = "+ notes.size());
	
		
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<notes.size();i++){
			
			JSONObject object_temp = new JSONObject();
			System.out.println("000000000000:");
			object_temp.element("NId", notes.get(i).getNId());
			object_temp.element("NReceive",notes.get(i).getNReceive());
			object_temp.element("NContent",notes.get(i).getNContent());
			
			SimpleDateFormat formatter = new SimpleDateFormat    ("          yyyy年MM月dd日    HH:mm:ss     ");       
		    String time = formatter.format(notes.get(i).getNDatetime());   
			object_temp.element("NDatetime",time);
			
			System.out.println("NDatetime:"+notes.get(i).getNDatetime());
			
			
			Teacher teacher_temp = notes.get(i).getTeacher();
			
			JSONObject object_temp_2 = new JSONObject();
			object_temp_2.element("TId",teacher_temp.getTId());
			object_temp.element("teacher", object_temp_2);
			
			jsonArray.add(i, object_temp);
			System.out.println("-----------------:");
		}
		resp.put("result", jsonArray);
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	public void addNote() throws IOException {
		System.out.println("getNoteOfTeacher:");
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		String name_receive = request.getParameter("name_course"); //获得课程名，用于存储到Note.NReceive
		String content_note = request.getParameter("content_message"); //获得Note的内容，用于存储到Note.NContent
		
		System.out.println("teacherid:"+teacherid);
		
		Note note = new Note();
		Teacher teacher = teacherService.getTeacherById(teacherid);
		
		note.setTeacher(teacher);
		note.setNContent(content_note);
		note.setNReceive(name_receive);
		note.setNDatetime(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("note:"+note.toString());
		
		boolean addNoteSuccess = noteService.addNote(note);
		
		JSONObject resp = new JSONObject();
		
		if(addNoteSuccess){
			resp.put("result", "添加信息成功");
			System.out.println("添加信息成功");
		}
		else{
			resp.put("result", "添加信息失败");
			System.out.println("添加信息失败");
		}
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}

	
	public INoteService getNoteService() {
		return noteService;
	}
	public void setNoteService(INoteService noteService) {
		this.noteService = noteService;
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
	public IStudentcourseService getStudentcourseService() {
		return studentcourseService;
	}
	public void setStudentcourseService(IStudentcourseService studentcourseService) {
		this.studentcourseService = studentcourseService;
	}
}

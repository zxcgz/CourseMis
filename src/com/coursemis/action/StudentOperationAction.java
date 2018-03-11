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

import com.coursemis.dao.ISourceManageDAO;
import com.coursemis.dao.IStudentHomeWorkDAO;
import com.coursemis.dao.impl.LocationDAO;
import com.coursemis.dao.impl.RegulationDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Studentcourse;
import com.coursemis.model.Studenthomework;
import com.coursemis.service.ICourseService;
import com.coursemis.service.IStudentService;
import com.coursemis.service.IStudentcourseService;
import com.coursemis.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class StudentOperationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
    private HttpServletResponse response;
    
    private IStudentcourseService studentcourseService;
    private IStudentService studentService;
	private ICourseService courseService;
	private UserService teacherService;
	
	private IStudentHomeWorkDAO studentHomeWorkDAO;
	private ISourceManageDAO sourceManageDAO;
	
	private RegulationDAO rdao = new RegulationDAO();///
    public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response=arg0;
	}
	
	
	
	public void studentClassHMPath() throws Exception
	{
		String shinfo = request.getParameter("shinfo");
		String shid  = shinfo.substring(0, shinfo.indexOf(" "));
		String name = rdao.getStudenthomeworkName(Integer.parseInt(shid));
		
		System.out.println("������������");
		JSONObject resp = new JSONObject();
		
		
		
		resp.put("result", name);
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
		
	public void studentClassCourseCheckhomework() throws Exception
	{
		String sid= request.getParameter("sid");
		String courseinfo = request.getParameter("courseinfo");
		int smid =Integer.parseInt(courseinfo.substring(1, courseinfo.indexOf("次"))) ;
		
		List<Integer> shl = rdao.getClassStudenthomeworkListBySMId(smid);
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		if(shl!=null||shl.size()!=0){
			
			for(int i=0;i<shl.size();i++){
				JSONObject object_temp = new JSONObject();
				int shid = shl.get(i);
				Studenthomework sh = studentHomeWorkDAO.getStudenthomework(shid);
				
					object_temp.element("shid",shid);
					object_temp.element("sname",studentService.getStudentById(sh.getStudent().getSId()).getSName());
					object_temp.element("score",sh.getShScore()+"");
					
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
	
	public void studentSignIn() throws IOException
	{
		String sid= request.getParameter("sid");
		
		List<Studentcourse> scl =studentcourseService.getStudentcourseBySId(Integer.parseInt(sid));
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		if(scl!=null||scl.size()!=0){
			
			for(int i=0;i<scl.size();i++){
				JSONObject object_temp = new JSONObject();
				Studentcourse sc = scl.get(i);
				Course course = courseService.getCourseById(sc.getCourse().getCId());
				System.out.println(course.getCFlag()+"\t课程名"+course.getCName());
				if(course.getCFlag()==true)
				{
					System.out.print("����һ��"+course.getCId());
					object_temp.element("SCId",sc.getScId());
					jsonArray.add(i, object_temp);
				}
					
			}
			
		}
		
		resp.put("result", jsonArray);
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
	}
	
	public void studentSignInComfirm() throws IOException
	{
		boolean flage  = true;
		boolean flag=true;
	//	Enumeration pNames=request.getParameterNames();
		System.out.println(request.getParameter("0") +"    "+request.getParameter("1"));
		System.out.print("������`````````````");
		int i=0;
		int size = Integer.parseInt(request.getParameter("size"));
		float latitude = Float.parseFloat(request.getParameter("latitude"));
		float longitude = Float.parseFloat(request.getParameter("longitude"));
		for(int j =0;j<size;j++)
		{
			int scid = Integer.parseInt(request.getParameter(j+""));
			int cid = studentcourseService.searchStudentcourse(scid).getCourse().getCId();
			LocationDAO dao = new LocationDAO();
			float latitudeT = Float.parseFloat(dao.searchLocation(cid).get(0));
			float longitudeT = Float.parseFloat(dao.searchLocation(cid).get(1));
			float latitudeDifference = Math.abs(latitude-latitudeT)*60*60;
			float longtitudeDifference = Math.abs(longitude-longitudeT)*60*60;
			System.out.println("latitudeDifference  "+latitudeDifference+"longtitudeDifference   "+longtitudeDifference);
			if(latitudeDifference<1&&longtitudeDifference<1)
			{
				
			    Studentcourse studentcourse = studentcourseService.searchStudentcourse(scid);
			    studentcourse.setScPointNum(studentcourse.getScPointNum()+1);
			    studentcourse.setScPointTotalNum(courseService.getCourseById(studentcourse.getCourse().getCId()).getCPointTotalNum());
			    studentcourseService.updateStudentcourse(studentcourse);	    
			    i++;
			}else
			{
				flag =false;
				break;
			}
		}
		
/*		while(pNames.hasMoreElements()){
		    String name=(String)pNames.nextElement();
		    String value=request.getParameter(name);
		    int scid = Integer.parseInt(value);
		    Studentcourse studentcourse = scdao.searchStudentcourse(scid);
		    studentcourse.setScPointNum(studentcourse.getScPointNum()+1);
		    studentcourse.setScPointTotalNum(coursedao.getCourse(studentcourse.getCourse().getCId()).getCPointTotalNum());
		    scdao.updateStudentcourse(studentcourse);	    
		    i++;
		}*/
		
		System.out.println("i is"+i);
		JSONObject resp =new JSONObject();
		JSONObject resp_info =new JSONObject();
		if(flag)
		{
		resp_info.put("success", "success");
		}else
		{
			resp_info.put("success", "您没有在课堂附近签到");	
		}
		resp.put("result", resp_info);
		
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
		System.out.print("end>>>>>>>");
		
		
		
	}
//	public  void studentClassCourseCheckWhichhomework() throws Exception
//	{
//		String courseinfo = request.getParameter("courseinfo");
//		int cid =Integer.parseInt(courseinfo.substring(0, courseinfo.indexOf(" "))) ;
//		
//		
//		
//		List<Sourcemanage> sml = rdao.getSourcemanage(cid);
//		JSONObject resp = new JSONObject();
//		JSONArray jsonArray = new JSONArray();
//		
//		if(sml!=null||sml.size()!=0){
//			
//			
//			for(int i=0;i<sml.size();i++){
//				JSONObject object_temp = new JSONObject();
//				
//				
//				
//					object_temp.element("smid",sml.get(i).getSmId());
//					//object_temp.element("sname",sdao.getStudentById(sh.getStudent().getSId()).getSName());				
//					jsonArray.add(i, object_temp);
//			}
//			
//			
//		}
//		
//		resp.put("result", jsonArray);
//		System.out.println("resp:"+resp.toString());
//		PrintWriter out = response.getWriter();
//		out.print(resp.toString());
//		out.flush();
//		out.close();
//		
//		
//	}
	
	
	public void selectStudentCourseInfo() throws IOException{
		
		String sid = request.getParameter("sid");
		List<Studentcourse> scl =studentcourseService.getStudentcourseBySId(Integer.parseInt(sid));
		JSONObject resp = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		System.out.println(111111112);
		//List<Course> courseList=coursedao.getCourseByTeacherId(Integer.parseInt(tid));
		//System.out.println(courseList.size());
		if(scl!=null||scl.size()!=0){
			for(int i=0;i<scl.size();i++){
				JSONObject object_temp = new JSONObject();
				
				System.out.println("111111:");
				object_temp.element("CNumber",scl.get(i).getCourse().getCId());
				object_temp.element("CName",courseService.getCourseById(scl.get(i).getCourse().getCId()).getCName());
				object_temp.element("SCPointNum",scl.get(i).getScPointNum());
				object_temp.element("ScPointTotalNum",scl.get(i).getScPointTotalNum());
				object_temp.element("CTname",teacherService.getTeacherById(courseService.getCourseById(scl.get(i).getCourse().getCId()).getTeacher().getTId()).getTName());
				jsonArray.add(i, object_temp);
				System.out.println("qqqq:");	
			}
		}
		resp.put("result", jsonArray);
		System.out.println("resp:"+resp.toString());
		PrintWriter out = response.getWriter();
		out.print(resp.toString());
		out.flush();
		out.close();
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
	
	public IStudentHomeWorkDAO getStudenthomeworkDAO() {
		return studentHomeWorkDAO;
	}
	public void setStudenthomeworkDAO(IStudentHomeWorkDAO studenthomeworkDAO) {
		this.studentHomeWorkDAO = studenthomeworkDAO;
	}
	public IStudentcourseService getStudentcourseService() {
		return studentcourseService;
	}
	public void setStudentcourseService(IStudentcourseService studentcourseService) {
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
	

}

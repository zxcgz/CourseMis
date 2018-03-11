package com.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.dao.impl.CourseDAO;
import com.coursemis.dao.impl.SourceManageDAO;
import com.coursemis.dao.impl.StudentDAO;
import com.coursemis.dao.impl.StudentHomeWorkDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Sourcemanage;
import com.coursemis.model.Studenthomework;
import com.coursemis.service.ICourseService;
import com.coursemis.service.ISourceManageService;
import com.coursemis.service.IStudentHomeWorkService;
import com.coursemis.service.IStudentService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ��ȡAndroid���ϴ���������Ϣ
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class UploadAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;

	// 文件
	private File image;
	// 文件类型
	private String imageContentType;
	// 文件名称
	private String imageFileName;
	// 保存路径
	private String savePath;
	private ISourceManageService sourceManageService;
	private IStudentService studentService;
	private IStudentHomeWorkService studentHomeWorkService;
	private ICourseService courseService;
	//下载
	private InputStream instream;
	private String downfilename;
	public void shareMediaData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(getSavePath() +"/"+ getImageFileName());
			fis = new FileInputStream(getImage());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			Course c = new Course();
			//String split = request.getParameter("courseId").split(" ")[0].trim();
			String split = request.getParameter("cid");
			Integer i = new Integer(split);
			c.setCId(i);
			CourseDAO cdao = new CourseDAO();
			Sourcemanage sm = new Sourcemanage();
			sm.setCourse(c);
			sm.setSmDateTime(new Timestamp(System.currentTimeMillis()));
			sm.setSmUploader(request.getParameter("userid"));
			sm.setSmType(1);// 上传文件类型
			sm.setSmPath(getSavePath() + getImageFileName());
			sm.setSmName(getImageFileName());
			sourceManageService.saveSourceManage(sm);
			// 上传成功
			/*if (sourceManageService.saveSourceManage(sm)) {
				JSONArray jsonArray = new JSONArray();
				JSONObject resp = new JSONObject();
				jsonArray = JSONArray.fromObject(sm);
				resp.put("result", true);
				resp.put("result", jsonArray);
				PrintWriter out = response.getWriter();
				out.print(resp.toString());
				out.flush();
				out.close();		
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fos, fis);
		}
	}

	//下载文件
		public void  down(){
			//获取输出流下载
			System.out.println("1111111111111111111");
//			try {
//				//下载时中文找不到文件路径jsp要经过两次编码action一次解码
//				downfilename = request.getParameter("filename");
//				downfilename = URLDecoder.decode(downfilename, "utf-8");
//				System.out.println("下载文件"+downfilename);
//				InputStream in= new FileInputStream(getSavePath()+downfilename);
//				System.out.println(getSavePath()+downfilename);
//				//中文处理下载之后文件中文名的问题
//				downfilename = new String(downfilename.getBytes("utf-8"), "iso-8859-1");
//				int len = 0;  
//				OutputStream out = null;
//				//response.setContentType("application/octet-stream"); 
//				//response.setHeader("Content-Disposition", "attachment;filename="+downfilename);  
//				//response.addHeader("Content-Disposition", "application/msword");  
//		           byte buf[] = new byte[1024];//缓存作用  
//		           out = response.getOutputStream();//输出流  
//		           while( (len = in.read(buf)) > 0 ) //切忌这后面不能加 分号 ”;“  
//		           {  
//		        	   System.out.println(len+"");
//		               out.write(buf, 0, len);//向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取  
//		           }  
//		           in.close();
//		           out.close();
//				System.out.println("jiesjflakflkafm");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} 
//	public String studentUploadHM() {
//		System.out.println(111);
//		HttpServletRequest request = ServletActionContext.getRequest();
//		FileOutputStream fos = null;
//		FileInputStream fis = null;
//		try {
//			System.out.println("��ȡAndroid�˴���������ͨ��Ϣ��");
//			System.out.println("sid" + request.getParameter("sid"));
//			System.out.println("smid" + request.getParameter("smid"));
//			System.out.println("���䣺" + request.getParameter("age"));
//			System.out.println("��ȡAndroid�˴��������ļ���Ϣ��");
//			System.out.println("�ļ����Ŀ¼: " + getSavePath());
//			System.out.println("�ļ����: " + imageFileName);
//			System.out.println("�ļ���С: " + image.length());
//			System.out.println("�ļ�����: " + imageContentType);
//
//			fos = new FileOutputStream(getSavePath() + "/" + getImageFileName());
//			fis = new FileInputStream(getImage());
//			byte[] buffer = new byte[1024];
//			int len = 0;
//			while ((len = fis.read(buffer)) != -1) {
//				fos.write(buffer, 0, len);
//			}
//			System.out.println("�ļ��ϴ��ɹ�");
//
//			CourseDAO cdao = new CourseDAO();
//			Studenthomework sh = new Studenthomework();
//			sh.setShName(imageFileName);
//			sh.setShDateTime(new Timestamp(System.currentTimeMillis()));
//			sh.setShPath(getSavePath());
//			sh.setShScore(0);
//			sh.setStudent(studentService.getStudentById(Integer.parseInt(request.getParameter("sid"))));
//			sh.setSourcemanage(sourceManageService.getSourcemanageById(Integer.parseInt(request.getParameter("smid"))));
//			sourceManageService.saveSH(sh);
//
//		} catch (Exception e) {
//			System.out.println("�ļ��ϴ�ʧ��");
//			e.printStackTrace();
//		} finally {
//			close(fos, fis);
//		}
			try {
				downfilename="测试文档.doc";
				downfilename = URLEncoder.encode(downfilename, "utf-8");
				instream = new FileInputStream("E:\\uplod\\aaa.doc");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return;
	}

	@Override
	public String execute() {
		// HttpServletRequest request = ServletActionContext.getRequest();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			System.out.println("��ȡAndroid�˴���������ͨ��Ϣ��");
			System.out.println("cid" + request.getParameter("cid"));
			System.out.println("tid" + request.getParameter("tid"));
			System.out.println("���䣺" + request.getParameter("age"));
			System.out.println("��ȡAndroid�˴��������ļ���Ϣ��");
			System.out.println("�ļ����Ŀ¼: " + getSavePath());
			System.out.println("�ļ����: " + imageFileName);
			System.out.println("�ļ���С: " + image.length());
			System.out.println("�ļ�����: " + imageContentType);

			fos = new FileOutputStream(getSavePath() + "/" + getImageFileName());
			fis = new FileInputStream(getImage());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			System.out.println("�ļ��ϴ��ɹ�");

			// CourseDAO cdao =new CourseDAO();
			Sourcemanage sm = new Sourcemanage();
			sm.setCourse(courseService.getCourseById(Integer.parseInt(request.getParameter("cid"))));
			sm.setSmDateTime(new Timestamp(System.currentTimeMillis()));
			sm.setSmUploader(request.getParameter("tid"));
			sm.setSmType(0);
			sm.setSmPath(getSavePath());
			sm.setSmName(imageFileName);
			sourceManageService.saveSourceManage(sm);
		} catch (Exception e) {
			System.out.println("�ļ��ϴ�ʧ��");
			e.printStackTrace();
		} finally {
			close(fos, fis);
		}
		return SUCCESS;
	}

	/**
	 * �ļ����Ŀ¼
	 * 
	 * @return
	 */
	/*
	public String getSavePath() throws Exception {
		return "E:/uplod/";
	}
	*/
	public String getSavePath() throws Exception {
		System.err.println("测试savepath："+savePath);
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public InputStream getInstream() {
		return instream;
	}

	public void setInstream(InputStream instream) {
		this.instream = instream;
	}

	public String getDownfilename() {
		return downfilename;
	}

	public void setDownfilename(String downfilename) {
		this.downfilename = downfilename;
	}

	public void setSavePath(String savePath) {
		System.out.println("测试："+savePath);
		this.savePath = savePath;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	private void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
				fis = null;
			} catch (IOException e) {
				System.out.println("FileInputStream�ر�ʧ��");
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
				fis = null;
			} catch (IOException e) {
				System.out.println("FileOutputStream�ر�ʧ��");
				e.printStackTrace();
			}
		}
	}

	// get/set---------------------------------------------------------------------------------------------------------------------------------------
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	public ISourceManageService getSourceManageService() {
		return sourceManageService;
	}

	public void setSourceManageService(ISourceManageService sourceManageService) {
		this.sourceManageService = sourceManageService;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IStudentHomeWorkService getStudentHomeWorkService() {
		return studentHomeWorkService;
	}

	public void setStudentHomeWorkService(IStudentHomeWorkService studentHomeWorkService) {
		this.studentHomeWorkService = studentHomeWorkService;
	}

	public ICourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

}
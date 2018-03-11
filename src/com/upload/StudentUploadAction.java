package com.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.coursemis.model.Studenthomework;
import com.coursemis.service.ISourceManageService;
import com.coursemis.service.IStudentHomeWorkService;
import com.coursemis.service.IStudentService;
import com.opensymphony.xwork2.ActionSupport;

public class StudentUploadAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private HttpServletRequest request;
    private HttpServletResponse response;
	
	// �ϴ��ļ���
		private File image;
		// �ϴ��ļ�����
		private String imageContentType;
		// ��װ�ϴ��ļ���
		private String imageFileName;
		// ��������ע�������
		private String savePath;

		private ISourceManageService sourceManageService;
		private IStudentService studentService;
		private IStudentHomeWorkService studentHomeWorkService;
		
		public String execute(){
			System.out.println(111);
			HttpServletRequest request = ServletActionContext.getRequest();
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				System.out.println("��ȡAndroid�˴���������ͨ��Ϣ��");
				System.out.println("sid" + request.getParameter("sid"));
				System.out.println("smid" + request.getParameter("smid"));
				System.out.println("���䣺" + request.getParameter("age"));
				System.out.println("��ȡAndroid�˴��������ļ���Ϣ��");
				System.out.println("�ļ����Ŀ¼: " + getSavePath());
				System.out.println("�ļ����: " + imageFileName);
				System.out.println("�ļ���С: " + image.length());
				System.out.println("�ļ�����: " + imageContentType);
				
				
				
				
				fos = new FileOutputStream("E:/uplod/" + getImageFileName());
				fis = new FileInputStream(getImage());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				System.out.println("�ļ��ϴ��ɹ�");
				
				
				
				
				CourseDAO cdao =new CourseDAO();
				Studenthomework sh = new Studenthomework();
				sh.setShName(imageFileName);
				sh.setShDateTime(new Timestamp(System.currentTimeMillis()));
				sh.setShPath( getSavePath());
				sh.setShScore(0);
				sh.setStudent(studentService.getStudentById(Integer.parseInt(request.getParameter("sid"))));
				sh.setSourcemanage(sourceManageService.getSourcemanageById(Integer.parseInt(request.getParameter("smid"))));
				sourceManageService.saveSH(sh);
				
				
				
				
				
			} catch (Exception e) {
				System.out.println("�ļ��ϴ�ʧ��");
				e.printStackTrace();
			} finally {
				close(fos, fis);
			}
			return SUCCESS;
		}
		
//		
//		@Override
//		public String execute() {
//			HttpServletRequest request = ServletActionContext.getRequest();
//			FileOutputStream fos = null;
//			FileInputStream fis = null;
//			try {
//				System.out.println("��ȡAndroid�˴���������ͨ��Ϣ��");
//				System.out.println("cid" + request.getParameter("cid"));
//				System.out.println("tid" + request.getParameter("tid"));
//				System.out.println("���䣺" + request.getParameter("age"));
//				System.out.println("��ȡAndroid�˴��������ļ���Ϣ��");
//				System.out.println("�ļ����Ŀ¼: " + getSavePath());
//				System.out.println("�ļ����: " + imageFileName);
//				System.out.println("�ļ���С: " + image.length());
//				System.out.println("�ļ�����: " + imageContentType);
//				
//				
//				
//				
//				fos = new FileOutputStream(getSavePath() + "/" + getImageFileName());
//				fis = new FileInputStream(getImage());
//				byte[] buffer = new byte[1024];
//				int len = 0;
//				while ((len = fis.read(buffer)) != -1) {
//					fos.write(buffer, 0, len);
//				}
//				System.out.println("�ļ��ϴ��ɹ�");
//				
//				
//				
//				
//				CourseDAO cdao =new CourseDAO();
//				Sourcemanage sm = new Sourcemanage();
//				sm.setCourse(cdao.getCourse(Integer.parseInt(request.getParameter("cid"))));
//				sm.setSmDateTime(new Timestamp(System.currentTimeMillis()));
//				sm.setSmUploader(request.getParameter("tid"));
//				sm.setSmType(0);
//				sm.setSmPath(getSavePath());
//				sm.setSmName(imageFileName);
//				smdao.saveSourceManage(sm);
//				
//				
//				
//				
//				
//			} catch (Exception e) {
//				System.out.println("�ļ��ϴ�ʧ��");
//				e.printStackTrace();
//			} finally {
//				close(fos, fis);
//			}
//			return SUCCESS;
//		}

		/**
		 * �ļ����Ŀ¼
		 * 
		 * @return
		 */
		public String getSavePath() throws Exception {
			return ServletActionContext.getServletContext().getRealPath(savePath);
		}

		public void setSavePath(String savePath) {
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

		public void setStudentHomeWorkService(
				IStudentHomeWorkService studentHomeWorkService) {
			this.studentHomeWorkService = studentHomeWorkService;
		}
}

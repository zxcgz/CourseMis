package com.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coursemis.dao.impl.CourseDAO;
import com.coursemis.dao.impl.SourceManageDAO;
import com.coursemis.model.Course;
import com.coursemis.model.Sourcemanage;
import com.coursemis.service.ISourceManageService;
import com.opensymphony.xwork2.ActionSupport;

public class UploadVideoAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
    private HttpServletResponse response;
	
	// �ϴ��ļ���
		private File video;
		// �ϴ��ļ�����
		private String videoContentType;
		// ��װ�ϴ��ļ���
		private String videoFileName;
		// ��������ע�������
		private String savePath;
		
		private ISourceManageService sourceManageService;

		public File getVideo() {
			return video;
		}

		public void setVideo(File video) {
			this.video = video;
		}

		public String getVideoContentType() {
			return videoContentType;
		}

		public void setVideoContentType(String videoContentType) {
			this.videoContentType = videoContentType;
		}

		public String getVideoFileName() {
			return videoFileName;
		}

		public void setVideoFileName(String videoFileName) {
			this.videoFileName = videoFileName;
		}

		public String shareMediaData() {
			HttpServletRequest request = ServletActionContext.getRequest();
			/*
			try {
				request.setCharacterEncoding("GB2312") ;
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				String gb2312 = URLEncoder.encode(videoFileName, "GB2312") ;
				System.out.println("测试:::"+gb2312);
				System.out.println("保存地址: " + getSavePath());
				System.out.println("文件名称 " + videoFileName);
				System.out.println("文件长度 " + video.length());
				System.out.println("保存类型" + videoContentType);

				fos = new FileOutputStream(getSavePath() +"/"+ getVideoFileName());
				fis = new FileInputStream(getImage());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				System.out.println("�ļ��ϴ��ɹ�");
				Course c = new Course();
				c.setCId(1);
				CourseDAO cdao = new CourseDAO();
				Sourcemanage sm = new Sourcemanage();
				sm.setCourse(c);
				sm.setSmDateTime(new Timestamp(System.currentTimeMillis()));
				sm.setSmUploader(request.getParameter("userid"));
				sm.setSmType(1);
				sm.setSmPath(getSavePath());
				sm.setSmName(videoFileName);
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
		public String getSavePath() throws Exception {
			return ServletActionContext.getServletContext().getRealPath(savePath);
		}

		public void setSavePath(String savePath) {
			this.savePath=savePath;
		}

		public File getImage() {
			return video;
		}

		public void setImage(File video) {
			this.video = video;
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
}

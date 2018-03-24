package com.coursemis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coursemis.dao.INoteDAO;
import com.coursemis.model.Note;

public class NoteDAO extends BaseDAO implements INoteDAO {
	//查询该教师发送过的消息
	public List<Note> getNoteByTeacherId(int teacherid,String receive){
		System.out.println("getNoteByTeacherId:");
		Session session = getSession();
		List<Note> list = null;
		try {
			System.out.println("trying...");
			Transaction tx = session.beginTransaction();
			System.out.println(receive);
			//外键查询
			//Query query = session.createQuery("from Note note where note.teacher.TId = "+teacherid+" and note.NReceive = "+receive);
			Query query = session.createQuery("from Note note where note.teacher.TId = "+teacherid+" and note.NReceive = '"+receive+"'");
			list = query.list();
			query.executeUpdate();///?
			tx.commit();
			//log.debug("get TbUser instance successful");
		} catch (RuntimeException e){  
			//log.debug("get TbUser instance failed"); 
		}
		session.close();
		return list;
	}
	/* 数据库中添加一条消息  */
	public boolean saveNote(Note instance) {
		//log.debug("saving TbNotice instance");
		System.out.println("saving Note instance...");
		Session session = getSession();
		try{
			System.out.println("try saving Message instance...");
			Transaction tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			//log.debug("saving TbNotice instance successful");
			return true;
		} catch (RuntimeException e){
			//log.debug("saving TbNotice instance failed");
			e.printStackTrace();
		}
		session.close();
		return false;
	}
}

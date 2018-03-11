package com.coursemis.service.impl;

import java.util.List;

import com.coursemis.dao.INoteDAO;
import com.coursemis.model.Note;
import com.coursemis.service.INoteService;

public class NoteService implements INoteService {
	private INoteDAO noteDAO;
	
	public List<Note> getNoteByTeacherId(int teacherid,String receive){
		return noteDAO.getNoteByTeacherId(teacherid,receive);
	}
	public boolean addNote(Note note){
		return noteDAO.saveNote(note);
	}
	

	public INoteDAO getNoteDAO() {
		return noteDAO;
	}

	public void setNoteDAO(INoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}
	
}

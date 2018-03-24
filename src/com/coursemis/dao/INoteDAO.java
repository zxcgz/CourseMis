package com.coursemis.dao;

import java.util.List;

import com.coursemis.model.Note;

public interface INoteDAO {
	public List<Note> getNoteByTeacherId(int teacherid,String receive);
	public boolean saveNote(Note instance);
}

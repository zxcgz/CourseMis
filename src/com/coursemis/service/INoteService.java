package com.coursemis.service;

import java.util.List;

import com.coursemis.model.Note;

public interface INoteService {
	public List<Note> getNoteByTeacherId(int teacherid,String receive);
	public boolean addNote(Note note);
}

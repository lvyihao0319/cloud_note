package cn.lyh.cloud_note.service;

import java.util.List;
import java.util.Map;

import cn.lyh.cloud_note.entity.Note;
import cn.lyh.cloud_note.util.NoteResult;

public interface NoteService {
	public NoteResult<List<Map>> loadBookNote(String bookId);
	public NoteResult<Note> loadNote(String noteId);
	public NoteResult<Object> updateNote(String noteId,String title,String body);
	public NoteResult<Note> addNote(
			String userId,String bookid,String title);
	
}

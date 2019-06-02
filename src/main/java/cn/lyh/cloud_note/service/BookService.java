package cn.lyh.cloud_note.service;

import java.util.List;

import cn.lyh.cloud_note.entity.Book;
import cn.lyh.cloud_note.util.NoteResult;

public interface BookService {
	public NoteResult<List<Book>> loadUserBooks(String userId);
	public NoteResult<Book> addBook(
			String userId,String name);
}

package cn.lyh.cloud_note.dao;

import java.util.List;

import cn.lyh.cloud_note.entity.Book;

public interface BookDao {
	public List<Book> findByUserId(String userId);
	public void save(Book book);
}

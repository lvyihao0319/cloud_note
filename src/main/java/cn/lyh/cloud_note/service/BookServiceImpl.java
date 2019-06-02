package cn.lyh.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lyh.cloud_note.dao.BookDao;
import cn.lyh.cloud_note.entity.Book;
import cn.lyh.cloud_note.util.NoteResult;
import cn.lyh.cloud_note.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService{
@Resource
private BookDao bookDao;
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		
		List<Book> list=bookDao.findByUserId(userId);
		NoteResult<List<Book>> result=new NoteResult<List<Book>>();
		result.setStatus(0);
		result.setMsg("查询笔记本成功");
		result.setData(list);
		return result;
	}
	public NoteResult<Book> addBook(String userId, String name) {
		Book book = new Book();
		book.setCn_user_id(userId);//设置用户ID
		book.setCn_notebook_name(name);//设置笔记本名
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);//设置笔记本ID
		Timestamp time = new Timestamp(
			System.currentTimeMillis());
		book.setCn_notebook_createtime(time);//设置创建时间
		bookDao.save(book);//保存笔记本
		//创建返回结果
		NoteResult<Book> result = new NoteResult<Book>();
		result.setStatus(0);
		result.setMsg("创建笔记本成功");
		result.setData(book);//返回添加的笔记本信息
		return result;
	}
	
}

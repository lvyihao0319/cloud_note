package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.lyh.cloud_note.entity.Book;
import cn.lyh.cloud_note.service.BookService;
import cn.lyh.cloud_note.util.NoteResult;
import test.TestBase;

public class TestBookService extends TestBase{
	private BookService bookService;
	@Before
	public void init() {
		bookService=super.getContext().getBean("bookService",BookService.class);
		
	}
	@Test
	public void test(){
		NoteResult<List<Book>> result=bookService.loadUserBooks("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		System.out.println(result);
		
	}
}

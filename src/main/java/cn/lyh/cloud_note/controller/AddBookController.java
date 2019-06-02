package cn.lyh.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lyh.cloud_note.entity.Book;
import cn.lyh.cloud_note.service.BookService;
import cn.lyh.cloud_note.util.NoteResult;

@Controller
public class AddBookController {
	@Resource
	private BookService bookService;
	
	@RequestMapping("/book/add.do")
	@ResponseBody
	public NoteResult<Book> execute(
		String userId,String name){
		NoteResult<Book> result = 
			bookService.addBook(userId, name);
		return result;
	}
}

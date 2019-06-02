package cn.lyh.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lyh.cloud_note.entity.Book;
import cn.lyh.cloud_note.service.BookService;
import cn.lyh.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class LoadBooksController {
	@Resource
	private BookService bookService;
	@ResponseBody
	@RequestMapping("/loadbooks.do")
	public NoteResult<List<Book>> execute(String userId) {
		NoteResult<List<Book>> result=bookService.loadUserBooks(userId);
		return result;
	}
}

package cn.lyh.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lyh.cloud_note.service.NoteService;
import cn.lyh.cloud_note.util.NoteResult;

@Controller
public class LoadNotesController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/note/loadnotes.do")
	@ResponseBody
	public NoteResult<List<Map>> execute(String bookId){
		NoteResult<List<Map>> result = 
		noteService.loadBookNote(bookId);
		return result;
	}
}

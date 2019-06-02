package cn.lyh.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lyh.cloud_note.entity.Note;
import cn.lyh.cloud_note.service.NoteService;
import cn.lyh.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class AddNoteController {
	@Resource
	private NoteService noteService;
	
	@ResponseBody
	@RequestMapping("/add.do")
	
	public NoteResult<Note> execute(
		String userId,String bookId,String title){
		NoteResult<Note> result = 
			noteService.addNote(
			userId, bookId, title);
		return result;
	}
}

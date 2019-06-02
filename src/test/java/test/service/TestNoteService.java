package test.service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.lyh.cloud_note.entity.Note;
import cn.lyh.cloud_note.service.NoteService;
import cn.lyh.cloud_note.util.NoteResult;
import test.TestBase;

public class TestNoteService extends TestBase{
	private NoteService noteService;
	@Before
	public void init(){
		noteService=super.getContext().getBean("noteService",NoteService.class);
		
	}
	@Test
	public void test(){
		NoteResult<List<Map>> result=noteService.loadBookNote("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		System.out.println(result);
		
	}
	@Test
	public void test1(){
		NoteResult<Note> result=noteService.loadNote("9187ffd3-4c1e-4768-9f2f-c600e835b823");
		System.out.println(result);
		
	}
	@Test
	public void test2(){
		NoteResult<Object> result=noteService.updateNote("9187ffd3-4c1e-4768-9f2f-c600e835b823", "笔记", "身体");
		System.out.println(result);
		
	}
}

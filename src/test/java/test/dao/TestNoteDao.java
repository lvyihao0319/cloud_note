package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.scripting.ScriptSource;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import cn.lyh.cloud_note.dao.NoteDao;
import cn.lyh.cloud_note.entity.Note;


public class TestNoteDao {
	String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
	ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
	@Test
	public void testNote() {
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		List<Map> list=dao.findByBookId("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		for(Map note:list){
			System.out.println(note.get("cn_note_id")+","+note.get("cn_note_title"));
		}
	}
	@Test
	public void test2() {
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		Note note=dao.findByNoteId("9187ffd3-4c1e-4768-9f2f-c600e835b823");
		System.out.println(note.getCn_note_id());
		System.out.println(note.getCn_note_title());
	}
	@Test
	public void test3() {
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		Note note=new Note();
		String noteId="9187ffd3-4c1e-4768-9f2f-c600e835b823";
		note.setCn_note_id(noteId);
		String title="有意思";
		note.setCn_note_title(title);
		String body="跑快快";
		note.setCn_note_body(body);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		int num=dao.updateNote(note);
		System.out.println(num);
	}
}

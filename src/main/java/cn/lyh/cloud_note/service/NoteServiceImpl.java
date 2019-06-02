package cn.lyh.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lyh.cloud_note.dao.NoteDao;
import cn.lyh.cloud_note.dao.ShareDao;
import cn.lyh.cloud_note.entity.Note;
import cn.lyh.cloud_note.entity.Share;
import cn.lyh.cloud_note.util.NoteResult;
import cn.lyh.cloud_note.util.NoteUtil;
@Service("noteService")
public class NoteServiceImpl implements NoteService{
@Resource
private NoteDao noteDao;
@Resource
private ShareDao shareDao;
	public NoteResult<List<Map>> loadBookNote(String bookId) {
		//返回数据集合
		List<Map> list=noteDao.findByBookId(bookId);
		//构建Result
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		result.setStatus(0);
		result.setMsg("加载笔记成功");
		result.setData(list);
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		
		Note note=noteDao.findByNoteId(noteId);
		
		NoteResult<Note> result=new NoteResult<Note>();
		if (note==null) {
			result.setStatus(1);
			result.setMsg("未找到数据！");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("加载笔记内容成功");
			result.setData(note);
			return result;
		}
	}
	public NoteResult<Object> updateNote(String noteId, String title, String body) {
		//创建note参数
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		//更新数据库记录
		int rows=noteDao.updateNote(note);
		//构建result
		NoteResult<Object> result=new NoteResult<Object>();
		if (rows==1) {
			result.setStatus(0);
			result.setMsg("保存笔记成功");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("保存笔记失败");
			return result;
		}
		
	}
	public NoteResult<Note> addNote(String userId, String bookId, String title) {
		//创建note参数保存
				Note note = new Note();
				note.setCn_user_id(userId);//设置用户ID
				note.setCn_note_title(title);//设置笔记名称
				note.setCn_notebook_id(bookId);//设置笔记本ID
				String noteId = NoteUtil.createId();
				note.setCn_note_id(noteId);//笔记ID
				Long time = System.currentTimeMillis();
				note.setCn_note_create_time(time);//创建时间
				note.setCn_note_last_modify_time(time);//最后修改时间
				noteDao.save(note);//保存笔记
				//创建返回结果
				NoteResult<Note> result = new NoteResult<Note>();
				result.setStatus(0);
				result.setMsg("创建笔记成功");
				result.setData(note);//返回笔记ID
				return result;
	}
	

}

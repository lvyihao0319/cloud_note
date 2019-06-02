package cn.lyh.cloud_note.service;

import java.util.HashMap;
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
@Service
public class ShareServiceImpl implements ShareService{
	@Resource
	private NoteDao noteDao;
	@Resource
	private ShareDao shareDao;
	public NoteResult<Object> shareNote(String noteId) {
		//向cn_share表插入记录
		Share share=new Share();
		String sharId=NoteUtil.createId();
		share.setCn_share_id(sharId);//主键
		share.setCn_note_id(noteId);
		//获取笔记标题和内容
		Note note=noteDao.findByNoteId(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		//保存分享记录
		shareDao.save(share);
		NoteResult<Object> result=new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("分享笔记成功！");
		return result;
	}
	public NoteResult<List<Share>> searchNote(String keyword,int page) {
		String title="%"+keyword+"%";
		int begin=(page-1)*3;//计算抓取记录的七点
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("title", title);
		params.put("begin", begin);
		//模糊查询
		List<Share> list=shareDao.findLikeTitle(params);
		//构建返回结果
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setMsg("搜索完成");
		result.setData(list);
		return result;
	}

}

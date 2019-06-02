package cn.lyh.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.lyh.cloud_note.entity.Share;

public interface ShareDao {
	public void save(Share share);
	public List<Share> findLikeTitle(Map params);
}

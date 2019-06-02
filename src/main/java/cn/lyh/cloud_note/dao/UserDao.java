package cn.lyh.cloud_note.dao;

import cn.lyh.cloud_note.entity.User;

public interface UserDao {
	//登录方法
	public User findByName(String name);
	//注册方法
	public void save(User user);
}

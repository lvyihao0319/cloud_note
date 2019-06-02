package cn.lyh.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lyh.cloud_note.dao.UserDao;
import cn.lyh.cloud_note.entity.User;
import cn.lyh.cloud_note.util.NoteResult;
import cn.lyh.cloud_note.util.NoteUtil;

@Service("userService")//扫描到Spring容器
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	public NoteResult<User> checkLogin(String name,String password) {
		//接收结果数据
		NoteResult<User> result= new NoteResult<User>();
		//按照参数name查询数据库
		User user=userDao.findByName(name);
		//检查用户名
		if (user==null) {
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//检测密码
		if (!user.getCn_user_password().equals(password)) {
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}
	public NoteResult<Object> addUser(
			String name,String password,String nick){
		NoteResult<Object> result= new NoteResult<Object>();
		//用户检测
		User hasuser=userDao.findByName(name);
		if (hasuser!=null) {//用户名存在
			result.setStatus(1);
			result.setMsg("用户已经被占用");
			return result;
		}
		//添加用户
		User user=new User();
		//设置用户名
		user.setCn_user_name(name);
		user.setCn_user_password(password);
		//这是用户昵称
		user.setCn_user_nick(nick);
		//创建id
		String id=NoteUtil.createId();
		//设置用户id
		user.setCn_user_id(id);
		//插入用户数据
		userDao.save(user);
		
		//构建返回结果
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
		
	}
}

package cn.lyh.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lyh.cloud_note.service.UserService;
import cn.lyh.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserRegistController {
	@Resource
	UserService userService;
	@RequestMapping("/add.do")//匹配请求
	@ResponseBody//JSON输出
	public NoteResult<Object> execute(String name,String password,String nick) {
		NoteResult<Object> result=userService.addUser(name, password, nick);
		return result;
	}
}

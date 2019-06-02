package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lyh.cloud_note.dao.UserDao;
import cn.lyh.cloud_note.entity.User;
import cn.lyh.cloud_note.service.UserService;
import cn.lyh.cloud_note.util.NoteResult;

public class TestUserService {
	UserService service;
	@Before
	public void init(){
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		service=ctx.getBean("userService",UserService.class);
	}
	@Test //用户名不存在
	public void test1() {
		NoteResult<User> result=service.checkLogin("牛逼", "123");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test //注册成功
	public void test2() {
		String name="hehe";
		String password="123456";
		String nick="ergou";
		NoteResult<Object> result=service.addUser(name, password, nick);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	@Test
	public void test3() {
	
	}
}

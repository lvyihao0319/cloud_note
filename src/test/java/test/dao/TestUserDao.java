package test.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lyh.cloud_note.dao.UserDao;
import cn.lyh.cloud_note.entity.User;
import cn.lyh.cloud_note.service.UserService;

public class TestUserDao {
	@Test
	public void testUserDao() {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user=dao.findByName("demo");
		System.out.println(user);
	}
	@Test
	public void testSave() {
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user=new User();
		user.setCn_user_id("lyh");
		user.setCn_user_name("lyh");
		user.setCn_user_password("123456");
		user.setCn_user_nick("lyh");
		dao.save(user);
		System.out.println(user);
	}
}

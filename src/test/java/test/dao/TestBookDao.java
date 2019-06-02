package test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lyh.cloud_note.dao.BookDao;

import cn.lyh.cloud_note.entity.Book;


public class TestBookDao {
	String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
	ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
	@Test
	public void test1() {	
		BookDao dao=ctx.getBean("bookDao",BookDao.class);
		List<Book> list=dao.findByUserId("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		for (Book book:list) {
			System.out.println(book.getCn_notebook_id()+book.getCn_notebook_name());
		}
	}
	
	
}

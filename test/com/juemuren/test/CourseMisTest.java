package com.juemuren.test;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coursemis.model.Student;

public class CourseMisTest {
	
	@Test
	public void testSession(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory factory=(SessionFactory) context.getBean("sessionFactory");
		Session session = factory.openSession();
		Student student=(Student) session.get(Student.class, 1);
		System.out.print(student.getSName()+""+student.getSPassword());
		session.beginTransaction().commit();
		session.close();
		factory.close();
	}
}

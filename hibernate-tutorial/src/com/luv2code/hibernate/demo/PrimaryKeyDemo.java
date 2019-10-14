package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create three student objects
			System.out.println("Creating three new student object..");
			Student tempStudent1 = new Student("LeBron", "James", "LBJ@LAL.com");
			Student tempStudent2 = new Student("Kobe", "Bryant", "mamba@becauseIcan.com");
			Student tempStudent3 = new Student("Stephen", "Curry", "BabyFaceAssassin@chef.org");
					
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}

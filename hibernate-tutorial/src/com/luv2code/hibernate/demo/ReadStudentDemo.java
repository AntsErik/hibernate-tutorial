package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String [] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create a student object
			System.out.println("Creating a new student object from database.");
			Student tempStudent = new Student("Klay", "Thompson", "WarriorForLife@splashbrothers.net");
					
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//My New Code
			//find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			//get a new session and start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve the student base on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}

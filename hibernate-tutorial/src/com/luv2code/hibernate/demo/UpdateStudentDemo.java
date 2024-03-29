package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String [] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {

			int studentId = 1;

			//get a new session and start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve the student base on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Micheal");			
			
			//commit the transaction
			session.getTransaction().commit();
			
			//New Code
			//get a new session and start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all students
			System.out.println("Updating email for all students.");
			
			session.createQuery("update Student set email='whereamazinghappes@nba.com'")
			.executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}

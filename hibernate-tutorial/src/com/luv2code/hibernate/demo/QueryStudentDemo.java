package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String [] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
					
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> tempStudents = session.createQuery("from Student").getResultList();
			
			//display students
			displayStudents(tempStudents);
			
			//query students: lastName= 'Thompson'
			tempStudents = session.createQuery("from Student s where s.lastName='Thompson'").getResultList();
			
			//display the students
			System.out.println("\n\nStudents who have last name for Thompson.");
			displayStudents(tempStudents);
			
			//query students: firstName= 'Lebron' OR lastName='Bryant'
			tempStudents = 
					session.createQuery("from Student s where"
							+ " s.firstName='Lebron' OR s.lastName='Bryant'").getResultList();
			
			System.out.println("\n\nStudents who have first name of Lebron or last name of Bryant");
			displayStudents(tempStudents);
			
			//Using the LIKE clause
			//query students where email LIKE '%chef.org'
			tempStudents = 
					session.createQuery("from Student s "
							+ "where s.email LIKE '%chef.org'").getResultList();
			
			System.out.println("\n\nStudents whose email ends in '@chef.org'");
			displayStudents(tempStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> tempStudents) {
		for (Student tempStudent : tempStudents) {
			System.out.println(tempStudent);
		}
	}
}

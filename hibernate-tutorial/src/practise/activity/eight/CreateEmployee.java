package practise.activity.eight;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateEmployee {

	public static void main(String [] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create an employee object
			System.out.println("Creating a new employee object..");
			Employee newEmployee = new Employee("Ants Erik", "Noormagi", "Aweroc");
					
			//start a transaction
			session.beginTransaction();
			
			//save the employee object
			System.out.println("Saving employee...");
			session.save(newEmployee);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}

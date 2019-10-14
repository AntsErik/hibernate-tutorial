package practise.activity.eight;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteEmployee {

	public static void main(String [] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//get a new session and start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			//delete employee id=1
			System.out.println("Deleting employee id=1");
			session.createQuery("delete from Employee where id=1").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}

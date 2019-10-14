package practise.activity.eight;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryEmployee {

	public static void main(String [] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			int employeeId = 1;
			
			//get a new session and start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve the employee base on the id: primary key
			System.out.println("\nGetting employee with id: " + employeeId);
			
			Employee selectedEmployee = session.get(Employee.class, employeeId);
			
			//displaying the queried employee
			System.out.println("\n\nEmployee whose employee ID=1: " + selectedEmployee);
			
			String employeeCompany = "Aweroc";
			
			//query employees, whose company= 'Aweroc'
			System.out.println("\nGetting employee whose company: " + employeeCompany);
			
			List<Employee> allEmployees = session.createQuery("from Employee s "
					+ "where s.company='Aweroc'").getResultList();
			
			System.out.println("\n\nEmployees whose comapny='Aweroc'");
			
			for (Employee employee : allEmployees) {
				System.out.println(employee);
			}
			
			
			//commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
}
}

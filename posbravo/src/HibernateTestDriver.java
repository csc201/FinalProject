import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import wollits.hibernate.*;

public class HibernateTestDriver {
	public static void main(String[] args) {
		Session session = null;
		try {
			SessionFactory sessionFactory = new AnnotationConfiguration()
					.configure().buildSessionFactory();
			session = sessionFactory.openSession();

			boolean addAccess = true;

			if (addAccess) {
				session.beginTransaction();

				Roles roles = new Roles(99,"No Body");
				
				try {
				session.save(roles);
				session.getTransaction().commit();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

		/*	List aclList = session.createQuery("from Roles").list();
			System.out.println("Found " + aclList.size() + " messages:");

			Iterator iterator = aclList.iterator();
			while (iterator.hasNext()) {
				Roles roles = (Roles) iterator.next();
				System.out.println(roles.getName() + " " + roles.getRoleId());
			}*/
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}
}

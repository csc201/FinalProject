import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.posbravo.model.Accesscontrol;


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

				Accesscontrol accesscontrol = new Accesscontrol("Systemss");
				session.save(accesscontrol);

				session.getTransaction().commit();
			}

			List aclList = session.createQuery("from Accesscontrol").list();
			System.out.println("Found " + aclList.size() + " messages:");

			Iterator iterator = aclList.iterator();
			while (iterator.hasNext()) {
				Accesscontrol accesscontrol = (Accesscontrol) iterator.next();
				System.out.println(accesscontrol.getAccessControlName() + " "
						+ accesscontrol.getAccessControlId());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}
}

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import com.posbravo.model.Accesscontrol;


public class ModelTest {
	SessionFactory sessionFactory = new AnnotationConfiguration()
	.configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	@Test
	public void test() {
		session.beginTransaction();
		try {
		    insertAccessControl("Owner");
			//list();
			//deleteAccessControl("Test");
			list();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public void insertAccessControl(String data) {
		boolean addAccess = true;
		if (addAccess) {
			Accesscontrol accesscontrol = new Accesscontrol(data);
			session.save(accesscontrol);
			session.getTransaction().commit();
		}
	}
	
	public void deleteAccessControl(String data) {
		//bulk delete
		session.createQuery("delete from Accesscontrol").executeUpdate();
		
		/*session.beginTransaction();
		session.delete(data);
		session.getTransaction().commit();*/
	}
	
	public void list() {
		List aclList = session.createQuery("from Accesscontrol").list();
		System.out.println("Found " + aclList.size() + " messages:");

		Iterator iterator = aclList.iterator();
		while (iterator.hasNext()) {
			Accesscontrol accesscontrol = (Accesscontrol) iterator.next();
			System.out.println(accesscontrol.getAccessControlName() + " "
					+ accesscontrol.getAccessControlId());
		}
	}

}

package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class OrderCustomizations.
 * @see wollits.hibernate.OrderCustomizations
 * @author Hibernate Tools
 */
@Stateless
public class OrderCustomizationsHome {

	private static final Log log = LogFactory
			.getLog(OrderCustomizationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OrderCustomizations transientInstance) {
		log.debug("persisting OrderCustomizations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OrderCustomizations persistentInstance) {
		log.debug("removing OrderCustomizations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OrderCustomizations merge(OrderCustomizations detachedInstance) {
		log.debug("merging OrderCustomizations instance");
		try {
			OrderCustomizations result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrderCustomizations findById(int id) {
		log.debug("getting OrderCustomizations instance with id: " + id);
		try {
			OrderCustomizations instance = entityManager.find(
					OrderCustomizations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

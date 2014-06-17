package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class OrderMenuItemCustomizations.
 * @see wollits.hibernate.OrderMenuItemCustomizations
 * @author Hibernate Tools
 */
@Stateless
public class OrderMenuItemCustomizationsHome {

	private static final Log log = LogFactory
			.getLog(OrderMenuItemCustomizationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OrderMenuItemCustomizations transientInstance) {
		log.debug("persisting OrderMenuItemCustomizations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OrderMenuItemCustomizations persistentInstance) {
		log.debug("removing OrderMenuItemCustomizations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OrderMenuItemCustomizations merge(
			OrderMenuItemCustomizations detachedInstance) {
		log.debug("merging OrderMenuItemCustomizations instance");
		try {
			OrderMenuItemCustomizations result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrderMenuItemCustomizations findById(int id) {
		log.debug("getting OrderMenuItemCustomizations instance with id: " + id);
		try {
			OrderMenuItemCustomizations instance = entityManager.find(
					OrderMenuItemCustomizations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

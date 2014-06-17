package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class OrderMenuItems.
 * @see wollits.hibernate.OrderMenuItems
 * @author Hibernate Tools
 */
@Stateless
public class OrderMenuItemsHome {

	private static final Log log = LogFactory.getLog(OrderMenuItemsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OrderMenuItems transientInstance) {
		log.debug("persisting OrderMenuItems instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OrderMenuItems persistentInstance) {
		log.debug("removing OrderMenuItems instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OrderMenuItems merge(OrderMenuItems detachedInstance) {
		log.debug("merging OrderMenuItems instance");
		try {
			OrderMenuItems result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrderMenuItems findById(int id) {
		log.debug("getting OrderMenuItems instance with id: " + id);
		try {
			OrderMenuItems instance = entityManager.find(OrderMenuItems.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

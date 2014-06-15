package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RestaurantMenus.
 * @see wollits.hibernate.RestaurantMenus
 * @author Hibernate Tools
 */
@Stateless
public class RestaurantMenusHome {

	private static final Log log = LogFactory.getLog(RestaurantMenusHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RestaurantMenus transientInstance) {
		log.debug("persisting RestaurantMenus instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RestaurantMenus persistentInstance) {
		log.debug("removing RestaurantMenus instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RestaurantMenus merge(RestaurantMenus detachedInstance) {
		log.debug("merging RestaurantMenus instance");
		try {
			RestaurantMenus result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RestaurantMenus findById(int id) {
		log.debug("getting RestaurantMenus instance with id: " + id);
		try {
			RestaurantMenus instance = entityManager.find(
					RestaurantMenus.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

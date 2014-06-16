package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RestaurantRoles.
 * @see wollits.hibernate.RestaurantRoles
 * @author Hibernate Tools
 */
@Stateless
public class RestaurantRolesHome {

	private static final Log log = LogFactory.getLog(RestaurantRolesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RestaurantRoles transientInstance) {
		log.debug("persisting RestaurantRoles instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RestaurantRoles persistentInstance) {
		log.debug("removing RestaurantRoles instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RestaurantRoles merge(RestaurantRoles detachedInstance) {
		log.debug("merging RestaurantRoles instance");
		try {
			RestaurantRoles result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RestaurantRoles findById(int id) {
		log.debug("getting RestaurantRoles instance with id: " + id);
		try {
			RestaurantRoles instance = entityManager.find(
					RestaurantRoles.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

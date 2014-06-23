package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UserRestaurantRoles.
 * @see wollits.hibernate.UserRestaurantRoles
 * @author Hibernate Tools
 */
@Stateless
public class UserRestaurantRolesHome {

	private static final Log log = LogFactory
			.getLog(UserRestaurantRolesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(UserRestaurantRoles transientInstance) {
		log.debug("persisting UserRestaurantRoles instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserRestaurantRoles persistentInstance) {
		log.debug("removing UserRestaurantRoles instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserRestaurantRoles merge(UserRestaurantRoles detachedInstance) {
		log.debug("merging UserRestaurantRoles instance");
		try {
			UserRestaurantRoles result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserRestaurantRoles findById(int id) {
		log.debug("getting UserRestaurantRoles instance with id: " + id);
		try {
			UserRestaurantRoles instance = entityManager.find(
					UserRestaurantRoles.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

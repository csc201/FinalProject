package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UserLocations.
 * @see wollits.hibernate.UserLocations
 * @author Hibernate Tools
 */
@Stateless
public class UserLocationsHome {

	private static final Log log = LogFactory.getLog(UserLocationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(UserLocations transientInstance) {
		log.debug("persisting UserLocations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserLocations persistentInstance) {
		log.debug("removing UserLocations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserLocations merge(UserLocations detachedInstance) {
		log.debug("merging UserLocations instance");
		try {
			UserLocations result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserLocations findById(int id) {
		log.debug("getting UserLocations instance with id: " + id);
		try {
			UserLocations instance = entityManager
					.find(UserLocations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

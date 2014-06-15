package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Restaurants.
 * @see wollits.hibernate.Restaurants
 * @author Hibernate Tools
 */
@Stateless
public class RestaurantsHome {

	private static final Log log = LogFactory.getLog(RestaurantsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Restaurants transientInstance) {
		log.debug("persisting Restaurants instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Restaurants persistentInstance) {
		log.debug("removing Restaurants instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Restaurants merge(Restaurants detachedInstance) {
		log.debug("merging Restaurants instance");
		try {
			Restaurants result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Restaurants findById(int id) {
		log.debug("getting Restaurants instance with id: " + id);
		try {
			Restaurants instance = entityManager.find(Restaurants.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

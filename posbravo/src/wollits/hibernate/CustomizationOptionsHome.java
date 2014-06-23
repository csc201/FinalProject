package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CustomizationOptions.
 * @see wollits.hibernate.CustomizationOptions
 * @author Hibernate Tools
 */
@Stateless
public class CustomizationOptionsHome {

	private static final Log log = LogFactory
			.getLog(CustomizationOptionsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CustomizationOptions transientInstance) {
		log.debug("persisting CustomizationOptions instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CustomizationOptions persistentInstance) {
		log.debug("removing CustomizationOptions instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CustomizationOptions merge(CustomizationOptions detachedInstance) {
		log.debug("merging CustomizationOptions instance");
		try {
			CustomizationOptions result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CustomizationOptions findById(int id) {
		log.debug("getting CustomizationOptions instance with id: " + id);
		try {
			CustomizationOptions instance = entityManager.find(
					CustomizationOptions.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Customizations.
 * @see wollits.hibernate.Customizations
 * @author Hibernate Tools
 */
@Stateless
public class CustomizationsHome {

	private static final Log log = LogFactory.getLog(CustomizationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Customizations transientInstance) {
		log.debug("persisting Customizations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Customizations persistentInstance) {
		log.debug("removing Customizations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Customizations merge(Customizations detachedInstance) {
		log.debug("merging Customizations instance");
		try {
			Customizations result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Customizations findById(int id) {
		log.debug("getting Customizations instance with id: " + id);
		try {
			Customizations instance = entityManager.find(Customizations.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

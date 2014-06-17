package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Sizes.
 * @see wollits.hibernate.Sizes
 * @author Hibernate Tools
 */
@Stateless
public class SizesHome {

	private static final Log log = LogFactory.getLog(SizesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Sizes transientInstance) {
		log.debug("persisting Sizes instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Sizes persistentInstance) {
		log.debug("removing Sizes instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Sizes merge(Sizes detachedInstance) {
		log.debug("merging Sizes instance");
		try {
			Sizes result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sizes findById(int id) {
		log.debug("getting Sizes instance with id: " + id);
		try {
			Sizes instance = entityManager.find(Sizes.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

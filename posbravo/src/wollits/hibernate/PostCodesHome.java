package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PostCodes.
 * @see wollits.hibernate.PostCodes
 * @author Hibernate Tools
 */
@Stateless
public class PostCodesHome {

	private static final Log log = LogFactory.getLog(PostCodesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(PostCodes transientInstance) {
		log.debug("persisting PostCodes instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(PostCodes persistentInstance) {
		log.debug("removing PostCodes instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public PostCodes merge(PostCodes detachedInstance) {
		log.debug("merging PostCodes instance");
		try {
			PostCodes result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PostCodes findById(int id) {
		log.debug("getting PostCodes instance with id: " + id);
		try {
			PostCodes instance = entityManager.find(PostCodes.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

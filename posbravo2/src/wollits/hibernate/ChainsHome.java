package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Chains.
 * @see wollits.hibernate.Chains
 * @author Hibernate Tools
 */
@Stateless
public class ChainsHome {

	private static final Log log = LogFactory.getLog(ChainsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Chains transientInstance) {
		log.debug("persisting Chains instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Chains persistentInstance) {
		log.debug("removing Chains instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Chains merge(Chains detachedInstance) {
		log.debug("merging Chains instance");
		try {
			Chains result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Chains findById(int id) {
		log.debug("getting Chains instance with id: " + id);
		try {
			Chains instance = entityManager.find(Chains.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

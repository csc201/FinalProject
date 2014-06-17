package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Payments.
 * @see wollits.hibernate.Payments
 * @author Hibernate Tools
 */
@Stateless
public class PaymentsHome {

	private static final Log log = LogFactory.getLog(PaymentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Payments transientInstance) {
		log.debug("persisting Payments instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Payments persistentInstance) {
		log.debug("removing Payments instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Payments merge(Payments detachedInstance) {
		log.debug("merging Payments instance");
		try {
			Payments result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Payments findById(int id) {
		log.debug("getting Payments instance with id: " + id);
		try {
			Payments instance = entityManager.find(Payments.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

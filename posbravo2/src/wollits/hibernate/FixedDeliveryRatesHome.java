package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class FixedDeliveryRates.
 * @see wollits.hibernate.FixedDeliveryRates
 * @author Hibernate Tools
 */
@Stateless
public class FixedDeliveryRatesHome {

	private static final Log log = LogFactory
			.getLog(FixedDeliveryRatesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(FixedDeliveryRates transientInstance) {
		log.debug("persisting FixedDeliveryRates instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(FixedDeliveryRates persistentInstance) {
		log.debug("removing FixedDeliveryRates instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public FixedDeliveryRates merge(FixedDeliveryRates detachedInstance) {
		log.debug("merging FixedDeliveryRates instance");
		try {
			FixedDeliveryRates result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FixedDeliveryRates findById(int id) {
		log.debug("getting FixedDeliveryRates instance with id: " + id);
		try {
			FixedDeliveryRates instance = entityManager.find(
					FixedDeliveryRates.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

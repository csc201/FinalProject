package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class VariableDeliveryRates.
 * @see wollits.hibernate.VariableDeliveryRates
 * @author Hibernate Tools
 */
@Stateless
public class VariableDeliveryRatesHome {

	private static final Log log = LogFactory
			.getLog(VariableDeliveryRatesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(VariableDeliveryRates transientInstance) {
		log.debug("persisting VariableDeliveryRates instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(VariableDeliveryRates persistentInstance) {
		log.debug("removing VariableDeliveryRates instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public VariableDeliveryRates merge(VariableDeliveryRates detachedInstance) {
		log.debug("merging VariableDeliveryRates instance");
		try {
			VariableDeliveryRates result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VariableDeliveryRates findById(int id) {
		log.debug("getting VariableDeliveryRates instance with id: " + id);
		try {
			VariableDeliveryRates instance = entityManager.find(
					VariableDeliveryRates.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

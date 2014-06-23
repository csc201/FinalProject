package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class OrderSelectedCustomizationOptions.
 * @see wollits.hibernate.OrderSelectedCustomizationOptions
 * @author Hibernate Tools
 */
@Stateless
public class OrderSelectedCustomizationOptionsHome {

	private static final Log log = LogFactory
			.getLog(OrderSelectedCustomizationOptionsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OrderSelectedCustomizationOptions transientInstance) {
		log.debug("persisting OrderSelectedCustomizationOptions instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OrderSelectedCustomizationOptions persistentInstance) {
		log.debug("removing OrderSelectedCustomizationOptions instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OrderSelectedCustomizationOptions merge(
			OrderSelectedCustomizationOptions detachedInstance) {
		log.debug("merging OrderSelectedCustomizationOptions instance");
		try {
			OrderSelectedCustomizationOptions result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrderSelectedCustomizationOptions findById(int id) {
		log.debug("getting OrderSelectedCustomizationOptions instance with id: "
				+ id);
		try {
			OrderSelectedCustomizationOptions instance = entityManager.find(
					OrderSelectedCustomizationOptions.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

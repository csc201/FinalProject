package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class BusinessHours.
 * @see wollits.hibernate.BusinessHours
 * @author Hibernate Tools
 */
@Stateless
public class BusinessHoursHome {

	private static final Log log = LogFactory.getLog(BusinessHoursHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(BusinessHours transientInstance) {
		log.debug("persisting BusinessHours instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(BusinessHours persistentInstance) {
		log.debug("removing BusinessHours instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public BusinessHours merge(BusinessHours detachedInstance) {
		log.debug("merging BusinessHours instance");
		try {
			BusinessHours result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public BusinessHours findById(int id) {
		log.debug("getting BusinessHours instance with id: " + id);
		try {
			BusinessHours instance = entityManager
					.find(BusinessHours.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

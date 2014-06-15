package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ServiceTypes.
 * @see wollits.hibernate.ServiceTypes
 * @author Hibernate Tools
 */
@Stateless
public class ServiceTypesHome {

	private static final Log log = LogFactory.getLog(ServiceTypesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ServiceTypes transientInstance) {
		log.debug("persisting ServiceTypes instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ServiceTypes persistentInstance) {
		log.debug("removing ServiceTypes instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ServiceTypes merge(ServiceTypes detachedInstance) {
		log.debug("merging ServiceTypes instance");
		try {
			ServiceTypes result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ServiceTypes findById(int id) {
		log.debug("getting ServiceTypes instance with id: " + id);
		try {
			ServiceTypes instance = entityManager.find(ServiceTypes.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

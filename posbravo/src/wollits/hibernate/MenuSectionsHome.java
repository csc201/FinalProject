package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class MenuSections.
 * @see wollits.hibernate.MenuSections
 * @author Hibernate Tools
 */
@Stateless
public class MenuSectionsHome {

	private static final Log log = LogFactory.getLog(MenuSectionsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MenuSections transientInstance) {
		log.debug("persisting MenuSections instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MenuSections persistentInstance) {
		log.debug("removing MenuSections instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MenuSections merge(MenuSections detachedInstance) {
		log.debug("merging MenuSections instance");
		try {
			MenuSections result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MenuSections findById(int id) {
		log.debug("getting MenuSections instance with id: " + id);
		try {
			MenuSections instance = entityManager.find(MenuSections.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

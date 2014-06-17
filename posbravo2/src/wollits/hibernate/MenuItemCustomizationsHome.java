package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class MenuItemCustomizations.
 * @see wollits.hibernate.MenuItemCustomizations
 * @author Hibernate Tools
 */
@Stateless
public class MenuItemCustomizationsHome {

	private static final Log log = LogFactory
			.getLog(MenuItemCustomizationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MenuItemCustomizations transientInstance) {
		log.debug("persisting MenuItemCustomizations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MenuItemCustomizations persistentInstance) {
		log.debug("removing MenuItemCustomizations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MenuItemCustomizations merge(MenuItemCustomizations detachedInstance) {
		log.debug("merging MenuItemCustomizations instance");
		try {
			MenuItemCustomizations result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MenuItemCustomizations findById(int id) {
		log.debug("getting MenuItemCustomizations instance with id: " + id);
		try {
			MenuItemCustomizations instance = entityManager.find(
					MenuItemCustomizations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

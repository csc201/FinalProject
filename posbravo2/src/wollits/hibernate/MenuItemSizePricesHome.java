package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class MenuItemSizePrices.
 * @see wollits.hibernate.MenuItemSizePrices
 * @author Hibernate Tools
 */
@Stateless
public class MenuItemSizePricesHome {

	private static final Log log = LogFactory
			.getLog(MenuItemSizePricesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MenuItemSizePrices transientInstance) {
		log.debug("persisting MenuItemSizePrices instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MenuItemSizePrices persistentInstance) {
		log.debug("removing MenuItemSizePrices instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MenuItemSizePrices merge(MenuItemSizePrices detachedInstance) {
		log.debug("merging MenuItemSizePrices instance");
		try {
			MenuItemSizePrices result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MenuItemSizePrices findById(int id) {
		log.debug("getting MenuItemSizePrices instance with id: " + id);
		try {
			MenuItemSizePrices instance = entityManager.find(
					MenuItemSizePrices.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

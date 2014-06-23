package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Menus.
 * @see wollits.hibernate.Menus
 * @author Hibernate Tools
 */
@Stateless
public class MenusHome {

	private static final Log log = LogFactory.getLog(MenusHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Menus transientInstance) {
		log.debug("persisting Menus instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Menus persistentInstance) {
		log.debug("removing Menus instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Menus merge(Menus detachedInstance) {
		log.debug("merging Menus instance");
		try {
			Menus result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Menus findById(int id) {
		log.debug("getting Menus instance with id: " + id);
		try {
			Menus instance = entityManager.find(Menus.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

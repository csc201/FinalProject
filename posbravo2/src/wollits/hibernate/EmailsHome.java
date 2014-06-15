package wollits.hibernate;

// Generated Jun 14, 2014 7:45:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Emails.
 * @see wollits.hibernate.Emails
 * @author Hibernate Tools
 */
@Stateless
public class EmailsHome {

	private static final Log log = LogFactory.getLog(EmailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Emails transientInstance) {
		log.debug("persisting Emails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Emails persistentInstance) {
		log.debug("removing Emails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Emails merge(Emails detachedInstance) {
		log.debug("merging Emails instance");
		try {
			Emails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Emails findById(int id) {
		log.debug("getting Emails instance with id: " + id);
		try {
			Emails instance = entityManager.find(Emails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}

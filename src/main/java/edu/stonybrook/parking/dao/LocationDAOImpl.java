package edu.stonybrook.parking.dao;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import edu.stonybrook.parking.dto.Location;

public class LocationDAOImpl implements LocationDAO{

	final static Logger logger = Logger.getLogger(LocationDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	static final String GET_SLOTS = "from Location l where l.latitude between :x1 and :x2 and l.longitude between :y1 and :y2 and l.available = true";
	
	@Override
	public List<Location> getSlots(double x1, double x2, double y1, double y2){
		logger.debug("Start-Method:" + "getSlots");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Location> query = session.createQuery(GET_SLOTS).setParameter("x1", x1);
		query.setParameter("x2", x2);
		query.setParameter("y1", y1);
		query.setParameter("y2", y2);
		List<Location> locations = query.list();
		session.close();
		logger.debug("End-Method:" + "getSlots");
		return locations;
	}
	
	/*@Override
	public boolean addSlots(List<Location> newLocs){
		logger.debug("Start-Method:" + "addSlots");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		saveList(newLocs, session);
		try {
			session.getTransaction().commit();
		} catch (PersistenceException | ConstraintViolationException e) {
			logger.debug("Locations Already Exists");
		}
		session.close();
		logger.debug("End-Method:" + "addSlots");
		return true;
	}*/
	
	@Override
	public boolean addSlots(List<Location> newLocs){
		logger.debug("Start-Method:" + "addSlots");
		for (Location location : newLocs) {
			addSlot(location);
		}
		logger.debug("End-Method:" + "addSlots");
		return true;
	}

	private <T> void saveList(List<T> newLocs, Session session) {
		for (T location : newLocs) {
				session.save(location);
		}
	}

	@Override
	public boolean updateSlot(Location location) {
		logger.debug("Start-Method:" + "updateSlot");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(location);
		session.getTransaction().commit();
		session.close();
		logger.debug("End-Method:" + "updateSlot");
		return true;
	}

	@Override
	public boolean addSlot(Location location) {
		logger.debug("Start-Method:" + "addSlot");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(location);
			session.getTransaction().commit();
		} catch (PersistenceException | ConstraintViolationException e) {
			logger.debug("Locations Already Exists");
		}
		session.close();
		logger.debug("End-Method:" + "addSlot");
		return true;
	}

}

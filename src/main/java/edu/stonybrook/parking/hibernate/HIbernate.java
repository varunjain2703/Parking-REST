package edu.stonybrook.parking.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import edu.stonybrook.parking.dto.Location;

public class HIbernate {
	
	static final String GET_SLOTS = "from Location l where l.latitude between :x1 and :x2 and l.longitude between :y1 and :y2";

	public static void main(String[] args) {
		//Location ls = new Location(11, 15, true);
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session;
		session = sessionFactory.openSession();
		//session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		//session.save(ls);

		@SuppressWarnings("unchecked")
		Query<Location> query = session.createQuery(GET_SLOTS).setParameter("x1", 60.0);
		query.setParameter("x2", 80.0);
		query.setParameter("y1", 60.0);
		query.setParameter("y2", 80.0);
		
		List<Location> locations = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println(locations.size());
	}
}

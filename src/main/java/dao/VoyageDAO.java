package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Billet;
import model.Gare;
import model.Voyage;
import util.HibernateUtil;

public class VoyageDAO {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public List<Voyage> findAll() {
	    Session session = sessionFactory.openSession();
	    List<Voyage> voyages = session.createQuery(
	    	    "SELECT DISTINCT v FROM Voyage v " +
	    	    "JOIN FETCH v.trajet t " +
	    	    "JOIN FETCH t.depart " +
	    	    "JOIN FETCH t.arrivee " +
	    	    "LEFT JOIN FETCH t.garesDePassage", Voyage.class
	    	).getResultList();
	    session.close();
	    return voyages;
	}

	public Voyage findById(long id) {
		Session session = sessionFactory.openSession();
		Voyage voyage = session.get(Voyage.class, id);
		session.close();
		return voyage;
	}
	
	
	public Voyage findByIdd(int id) {
	    Session session = sessionFactory.openSession();
	    Voyage voyage = session.get(Voyage.class, id);
	    session.close();
	    return voyage;
	}


	public List<Voyage> findByDateAndTrajet(LocalDate date, Gare depart, Gare arrivee) {
		Session session = sessionFactory.openSession();
		List<Voyage> voyages = session
				.createQuery(
						"SELECT v FROM Voyage v " + "JOIN FETCH v.trajet t " + "LEFT JOIN FETCH t.garesDePassage "
								+ "WHERE v.dateVoyage = :date AND t.depart = :depart AND t.arrivee = :arrivee",
						Voyage.class)
				.setParameter("date", date).setParameter("depart", depart).setParameter("arrivee", arrivee)
				.getResultList();
		session.close();
		return voyages;
	}
	public void create(Voyage voyage) {
	    Session session = sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();
	    session.persist(voyage);
	    tx.commit();
	    session.close();
	}
	
	public void update(Voyage voyage) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.update(voyage);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace(); // Tu peux logger ou relancer selon ta gestion d'erreurs
	    }
	}

	
}

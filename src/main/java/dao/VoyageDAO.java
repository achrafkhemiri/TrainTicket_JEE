package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Billet;
import model.Gare;
import model.Voyage;
import util.HibernateUtil;
public class VoyageDAO {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

 
    public Voyage findById(long id) {
        Session session = sessionFactory.openSession();
        Voyage voyage = session.get(Voyage.class, id);
        session.close();
        return voyage;
    }
    
    public List<Voyage> findByDateAndTrajet(LocalDate date, Gare depart, Gare arrivee) {
        Session session = sessionFactory.openSession();
        List<Voyage> voyages = session.createQuery(
            "SELECT v FROM Voyage v " +
            "JOIN FETCH v.trajet t " +
            "LEFT JOIN FETCH t.garesDePassage " +
            "WHERE v.dateVoyage = :date AND t.depart = :depart AND t.arrivee = :arrivee", Voyage.class)
            .setParameter("date", date)
            .setParameter("depart", depart)
            .setParameter("arrivee", arrivee)
            .getResultList();
        session.close();
        return voyages;
    }

}

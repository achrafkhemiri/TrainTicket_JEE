package dao;

import model.Billet;
import model.Client;
import model.Reservation;
import model.Utilisateur;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class ReservationDAO {
    private SessionFactory sessionFactory;
    public ReservationDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public Reservation findById(long id) {
        Session session = sessionFactory.openSession();
        Reservation reservation = session.get(Reservation.class, id);
        session.close();
        return reservation;
    }
    public boolean create(Reservation reservation) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.persist(reservation);
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return success;
    }

    public boolean update(Long id, String statut) {
        Session session = sessionFactory.openSession();
        Reservation reservation = session.get(Reservation.class, id);
        boolean success = false;
        if (reservation != null) {
            //reservation.setBillet(billet);

            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(reservation);
                tx.commit();
                success = true;
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            } finally {
                session.close();
            }
        }
        return success;
    }

    public boolean delete(long id) {
        Session session = sessionFactory.openSession();
        Reservation reservation = session.get(Reservation.class, id);
        boolean success = false;
        if (reservation != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.remove(reservation);
                tx.commit();
                success = true;
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            } finally {
                session.close();
            }
        }
        return success;
    }

    public List<Reservation> findAll() {
        Session session = sessionFactory.openSession();
        List<Reservation> result = session.createQuery("from Reservation", Reservation.class).getResultList();
        session.close();
        return result;
    }
    /**************/
    // Récupérer toutes les réservations d'un client spécifique
    public List<Reservation> findByClient(Client client) {
        Session session = sessionFactory.openSession();
        List<Reservation> reservations = session.createQuery("from Reservation where billet.client = :client", Reservation.class)
                .setParameter("client", client)
                .getResultList();
        session.close();
        return reservations;
    }

    // Récupérer une réservation liée à un billet spécifique
    public Reservation findByBillet(Billet billet) {
        Session session = sessionFactory.openSession();
        Reservation reservation = session.createQuery("from Reservation where billet = :billet", Reservation.class)
                .setParameter("billet", billet)
                .uniqueResult();
        session.close();
        return reservation;
    }
    
    
    
    public List<Reservation> findByUtilisateur(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        List<Reservation> reservations = session.createQuery(
            "from Reservation where utilisateur = :utilisateur", Reservation.class)
            .setParameter("utilisateur", utilisateur)
            .getResultList();
        session.close();
        return reservations;
    }
    
    
    

    
    
}

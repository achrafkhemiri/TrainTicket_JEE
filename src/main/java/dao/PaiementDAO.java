package dao;

import model.Paiement;
import model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PaiementDAO {
    private SessionFactory sessionFactory;
    public PaiementDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public Paiement findById(long id) {
        Session session = sessionFactory.openSession();
        Paiement paiement = session.get(Paiement.class, id);
        session.close();
        return paiement;
    }
    public List<Paiement> findAll() {
        Session session = sessionFactory.openSession();
        List<Paiement> result = session.createQuery("from Paiement", Paiement.class).getResultList();
        session.close();
        return result;
    }

    public boolean create(Paiement paiement) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.persist(paiement);
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

}

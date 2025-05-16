package dao;

import model.Gare;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
public class GareDAO {
    private SessionFactory sessionFactory;
    public GareDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public Gare findById(long id) {
        Session session = sessionFactory.openSession();
        Gare gare = session.get(Gare.class, id);
        session.close();
        return gare;
    }
    public boolean create(Gare gare) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.persist(gare);
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

    public List<Gare> findAll() {
        Session session = sessionFactory.openSession();
        List<Gare> result = session.createQuery("from Gare", Gare.class).getResultList();
        session.close();
        return result;
    }
    public boolean update(Long id, String nom, String ville) {
        Session session = sessionFactory.openSession();
        Gare gare = session.get(Gare.class, id);
        boolean success = false;
        if (gare != null) {
            gare.setNom(nom);
            gare.setVille(ville);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(gare);
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
        Gare gare = session.get(Gare.class, id);
        boolean success = false;
        if (gare != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.remove(gare);
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
}

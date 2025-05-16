package dao;

import model.Gare;
import model.Trajet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class TrajetDAO {
    private SessionFactory sessionFactory;
    public TrajetDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public Trajet findById(long id) {
        Session session = sessionFactory.openSession();
        Trajet trajet = session.get(Trajet.class, id);
        session.close();
        return trajet;
    }
    public List<Trajet> findAll() {
        Session session = sessionFactory.openSession();
        List<Trajet> result = session.createQuery("from Trajet", Trajet.class).getResultList();
        session.close();
        return result;
    }
    public boolean create(Trajet trajet) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.persist(trajet);
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

    // Mettre à jour un trajet
    public boolean update(Long id, Gare depart, Gare arrivee, List<Gare> garesDePassage) {
        Session session = sessionFactory.openSession();
        Trajet trajet = session.get(Trajet.class, id);
        boolean success = false;
        if (trajet != null) {
            trajet.setDepart(depart);
            trajet.setArrivee(arrivee);
            trajet.setGaresDePassage(garesDePassage);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(trajet);
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
        Trajet trajet = session.get(Trajet.class, id);
        boolean success = false;
        if (trajet != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.remove(trajet);
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
    /*********/
    // Récupérer les trajets entre deux gares spécifiques
    public List<Trajet> findByDepartArrivee(Gare depart, Gare arrivee) {
        Session session = sessionFactory.openSession();
        List<Trajet> trajets = session.createQuery(
                        "from Trajet where depart = :depart and arrivee = :arrivee", Trajet.class)
                .setParameter("depart", depart)
                .setParameter("arrivee", arrivee)
                .getResultList();
        session.close();
        return trajets;
    }

    // Récupérer les trajets directs (sans gares de passage)
    public List<Trajet> findDirectTrajets() {
        Session session = sessionFactory.openSession();
        List<Trajet> trajets = session.createQuery(
                "from Trajet where garesDePassage is empty", Trajet.class).getResultList();
        session.close();
        return trajets;
    }

    // Récupérer tous les trajets passant par une ville spécifique
    public List<Trajet> findByVille(String ville) {
        Session session = sessionFactory.openSession();
        List<Trajet> trajets = session.createQuery(
                        "select t from Trajet t join t.garesDePassage g where g.ville = :ville", Trajet.class)
                .setParameter("ville", ville)
                .getResultList();
        session.close();
        return trajets;
    }
}

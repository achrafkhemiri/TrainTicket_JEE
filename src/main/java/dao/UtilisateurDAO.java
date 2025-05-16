package dao;

import model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.Utilisateur;
import util.HibernateUtil;

import java.util.List;

public class UtilisateurDAO {
    SessionFactory sessionFactory;
    public UtilisateurDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Utilisateur findById(long id) {
        Session session = sessionFactory.openSession();
        Utilisateur utilisateur = session.get(Utilisateur.class, id);
        session.close();
        return utilisateur;
    }

    public boolean create(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.persist(utilisateur);
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

    public boolean update(Long id, String nom, String prenom, String email, String motDePasse) {
        Session session = sessionFactory.openSession();
        Utilisateur utilisateur = session.get(Utilisateur.class, id);
        boolean success = false;
        if (utilisateur != null) {
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setEmail(email);
            utilisateur.setMotDePasse(motDePasse);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(utilisateur);
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
        Utilisateur utilisateur = session.get(Utilisateur.class, id);
        boolean success = false;
        if (utilisateur != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.remove(utilisateur);
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


    public List<Utilisateur> findAll() {
            Session session = sessionFactory.openSession();
            List<Utilisateur> result = session.createQuery("from Utilisateur", Utilisateur.class).getResultList();
            session.close();
            return result;

    }
}

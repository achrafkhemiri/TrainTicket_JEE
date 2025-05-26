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
	    public void update(Utilisateur u) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        session.update(u);
	        tx.commit();
	        session.close();
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

	    public boolean delete(int id) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = null;
	        try {
	            tx = session.beginTransaction();
	            Utilisateur utilisateur = session.get(Utilisateur.class, id);
	            if (utilisateur != null) {
	                session.delete(utilisateur);
	                tx.commit();
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	            return false;
	        } finally {
	            session.close();
	        }
	    }

	    public Utilisateur findByEmailAndPassword(String email, String motDePasse) {
	        Session session = sessionFactory.openSession();
	        Utilisateur utilisateur = null;
	        try {
	            utilisateur = session.createQuery("FROM Utilisateur WHERE email = :email AND motDePasse = :motDePasse", Utilisateur.class)
	                                 .setParameter("email", email)
	                                 .setParameter("motDePasse", motDePasse)
	                                 .uniqueResult();
	        } finally {
	            session.close();
	        }
	        return utilisateur;
	    }
	 // Dans la classe UtilisateurDAO
	    public boolean existsByEmail(String email) {
	        Session session = sessionFactory.openSession();
	        try {
	            Long count = session.createQuery(
	                "SELECT COUNT(u) FROM Utilisateur u WHERE u.email = :email", Long.class)
	                .setParameter("email", email)
	                .uniqueResult();
	            return count != null && count > 0;
	        } finally {
	            session.close();
	        }
	    }




	    public List<Utilisateur> findAll() {
	            Session session = sessionFactory.openSession();
	            List<Utilisateur> result = session.createQuery("from Utilisateur", Utilisateur.class).getResultList();
	            session.close();
	            return result;

	    }
	}

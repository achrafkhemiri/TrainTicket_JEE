package dao;

import model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class AdminDAO extends UtilisateurDAO{
    private SessionFactory sessionFactory;

    public AdminDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Admin findById(long id) {
        Session session = sessionFactory.openSession();
        Admin admin = session.get(Admin.class, id);
        session.close();
        return admin;
    }

    public boolean update(Long id, String nom, String prenom, String email, String motDePasse) {
        Session session = sessionFactory.openSession();
        Admin admin = session.get(Admin.class, id);
        boolean success = false;
        if (admin != null) {
            admin.setNom(nom);
            admin.setPrenom(prenom);
            admin.setEmail(email);
            admin.setMotDePasse(motDePasse);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(admin);
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
    public boolean create(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.persist(admin);
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

    public boolean delete(long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            if (admin != null) {
                session.remove(admin);
                session.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    public List<Admin> findAllAdmins() {
        Session session = sessionFactory.openSession();
        List<Admin> result = session.createQuery("from Admin", Admin.class).getResultList();
        session.close();
        return result;
    }

}

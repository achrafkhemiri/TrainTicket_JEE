package dao;


import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class ClientDAO   {
    private SessionFactory sessionFactory;

    public ClientDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Client findById(long id) {
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    public List<Client> findAll() {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).getResultList();
        session.close();
        return clients;
    }

    public boolean create(Client client) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.persist(client);
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
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.remove(client);
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
}


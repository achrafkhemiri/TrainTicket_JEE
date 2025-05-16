package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Train; 
import util.HibernateUtil;

public class TrainDAO {

    private SessionFactory sessionFactory;

    public TrainDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public boolean create(Train train) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.persist(train);
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return success;
    }

    public Train findById(long id) {
        Session session = sessionFactory.openSession();
        Train train = session.get(Train.class, id);
        session.close();
        return train;
    }

    public List<Train> findAll() {
        Session session = sessionFactory.openSession();
        List<Train> trains = session.createQuery("from Train", Train.class).getResultList();
        session.close();
        return trains;
    }

    public boolean update(Long id, String name, String type, String departureTime) {
        Session session = sessionFactory.openSession();
        Train train = session.get(Train.class, id);
        boolean success = false;
        if (train != null) {
            train.setName(name);
            train.setType(type);
            train.setDepartureTime(departureTime);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(train);
                tx.commit();
                success = true;
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return success;
    }

    public boolean delete(long id) {
        Session session = sessionFactory.openSession();
        Train train = session.get(Train.class, id);
        boolean success = false;
        if (train != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.remove(train);
                tx.commit();
                success = true;
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return success;
    }
}

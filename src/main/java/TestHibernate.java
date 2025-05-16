import model.Gare;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class TestHibernate {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Gare gare = new Gare();
        gare.setNom("Test Gare");
        gare.setVille("Test Ville");

        session.save(gare);

        tx.commit();
        session.close();

        System.out.println("Test terminé");
    }
}
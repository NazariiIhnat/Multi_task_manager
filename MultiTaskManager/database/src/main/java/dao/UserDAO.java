package dao;

import connection.HibernateUtil;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO extends AbstractEntitiesDAO {

    public static User getUserByName(String name) {
        return AbstractEntitiesDAO.getEntityByValueName("name", name, User.class);
    }

    public static User getUserBySurname(String surname) {
        return AbstractEntitiesDAO.getEntityByValueName("surname", surname, User.class);
    }

    public static User getUserByNickname(String nickname) {
        return AbstractEntitiesDAO.getEntityByValueName("nickname", nickname, User.class);
    }

    public static User getLoggedInUser(String nickname, String password) {
        Session session = null;
        Query query;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            query = session.createQuery(
                    "from entities.User where nickName = :nickname AND password = :password",
                    User.class);
            query.setParameter("nickname", nickname);
            query.setParameter("password", password);
            transaction.commit();
            return (User)query.uniqueResult();
        } catch (Exception e){
            assert transaction != null;
            transaction.rollback();
        } finally {
            assert session != null;
            session.close();
        }
        return null;
    }

    public static void saveOrUpdate(User user) {
        AbstractEntitiesDAO.saveOrUpdate(user);
    }

    public static List<User> getAllUsers() {
        return AbstractEntitiesDAO.getAllEntities(User.class);
    }
}

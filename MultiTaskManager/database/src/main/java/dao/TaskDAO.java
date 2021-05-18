package dao;

import entities.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class TaskDAO extends AbstractEntitiesDAO {

    public static void saveOrUpdate(Task task){
        AbstractEntitiesDAO.saveOrUpdate(task);
    }

    public static void delete(Task task) {
        AbstractEntitiesDAO.delete(task);
    }

    public static List<Task> getTasksOfUser(String nickname) {
        return AbstractEntitiesDAO.getListOfEntitiesByValueName("nickname", nickname, Task.class);
    }

    public static Task getTaskByID(long id){
        return AbstractEntitiesDAO.getEntityByValueName("task_id", id, Task.class);
    }

    @SuppressWarnings("unchecked")
    public static List<Task> getUsersTasksBySenderNickname(String nickname, String senderNickname) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Task T WHERE T.user.nickname = :nickname AND T.senderNickname = :senderNickname";
            Query query = session.createQuery(hql);
            query.setParameter("nickname", nickname);
            query.setParameter("senderNickname", senderNickname);
            return query.list();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<Task> getTasksOfUserByDate(String nickname, LocalDate date) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Task T WHERE T.user.nickname = :nickname AND T.date = :date";
            Query query = session.createQuery(hql);
            query.setParameter("nickname", nickname);
            query.setParameter("date", date);
            return query.list();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return null;
    }
}

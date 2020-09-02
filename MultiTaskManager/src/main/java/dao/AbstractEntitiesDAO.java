package dao;

import connection.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

abstract class AbstractEntitiesDAO {

    static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    static synchronized void saveOrUpdate(Object object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (Exception e){
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    static synchronized <T> List<T> getAllEntities(Class<T> entityType) {
       Session session = sessionFactory.openSession();
       Transaction transaction = null;
       try {
           transaction = session.beginTransaction();
           CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
           CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);
           criteriaQuery.from(entityType);
           return session.createQuery(criteriaQuery).getResultList();
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
    static synchronized <T> List<T> getListOfEntitiesByValueName(String valName, Object val, Class<T> o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(
                    "from " + o.getName() + " where " + valName + " = :name", o);
            query.setParameter("name", val);
            transaction.commit();
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

    static synchronized <T> T getEntityByValueName(String valName, Object val, Class<T> o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(
                    "from " + o.getName() + " where " + valName + " = :name", o);
            query.setParameter("name", val);
            transaction.commit();
            return o.cast(query.uniqueResult());
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

    static synchronized <T> void delete(T object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (Exception e){
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }
}

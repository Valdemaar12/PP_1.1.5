package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;
    private Transaction transaction;

    public UserDaoHibernateImpl() {
        sessionFactory = new Util().getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(DB_CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Новая база данных создана.");
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(DB_DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
            System.out.println("База данных удалена.");
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных.");
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            if (session.get(User.class, id) != null) {
                session.delete(session.get(User.class, id));
                session.getTransaction().commit();
                System.out.println("User с id - " + id + " удалён из базы данных.");
            } else {
                System.out.println("User с id - " + id + " не существует в базе данных.");
            }
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            resultList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Запрос на получение всех User из базы данных - успешно выполнен.");
        return resultList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("База данных очищена.");
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}


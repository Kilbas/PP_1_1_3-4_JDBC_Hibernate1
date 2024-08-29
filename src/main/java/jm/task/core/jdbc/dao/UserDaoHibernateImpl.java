package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS USER" +
                    "(ID BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "NAME VARCHAR(50) NOT NULL, " +
                    "LASTNAME VARCHAR(100) NOT NULL, " +
                    "AGE INT NOT NULL)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Создание таблицы: неудача ");
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {

        Session session = Util.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            session.createNativeQuery("DROP TABLE USER").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Неудачное удаление ");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Неудачное добавление ");
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {

        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Неудачное удаление по ID");
        } finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {

        Session session = Util.getSessionFactory().openSession();
        List<User> users = null;

        try {
            session.beginTransaction();
            users = (List<User>) session.createQuery("from User").getResultList();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.err.println("Ошибка получения всех Users");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("delete from USER").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            System.err.println("Неудачное очищение таблицы");
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
}

package kz.runtime.ticketservicespring.entities.dao;

import kz.runtime.ticketservicespring.entities.User;
import kz.runtime.ticketservicespring.entities.dao.impl.UserDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDAO implements UserDaoImpl {
    SessionFactory sessionFactory;
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findById(long id) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("INSERT INTO users (username, creation_date) VALUES (?1, ?2)", User.class)
                    .setParameter(1, user.getUsername())
                    .setParameter(2, user.getCreationDate()).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("UPDATE users SET username = ?1, creation_date = ?2 WHERE id = ?3")
                    .setParameter(1, user.getUsername())
                    .setParameter(2, user.getCreationDate())
                    .setParameter(3, user.getId()).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM users WHERE id = ?1")
                    .setParameter(1, id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.dovganyuk.dao;

import com.dovganyuk.model.Order;
import com.dovganyuk.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return (User) session.createQuery(" from User where username = :usr")
                    .setParameter("usr", username).uniqueResult();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
        logger.info("User successfully saved. User details: " + user);
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(" from User").list();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public User findById(Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Order where userId = :id")
                    .setParameter("id", userId)
                    .list();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ArrayList<>();
        }
    }
}

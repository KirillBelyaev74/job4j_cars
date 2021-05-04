package ru.job4j.placement;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.function.Function;

public class ActionSQL {

    private static final Logger LOGGER = Logger.getLogger(ActionSQL.class);

    private static class InstanceSessionFactory {
        private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    private static SessionFactory getInstance() {
        return InstanceSessionFactory.SESSION_FACTORY;
    }

    protected <T> T save(T t) {
        try (Session session = getInstance().openSession()) {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        }
        return t;
    }

    protected <T> T action(Function<Session, T> action) {
        T t = null;
        Transaction transaction = null;
        try (Session session = getInstance().openSession()) {
            transaction = session.beginTransaction();
            t = action.apply(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Сonnection error. Mistake save : " + e + ". Method has arguments action " + action);
        }
        return t;
    }
}

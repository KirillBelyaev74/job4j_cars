package ru.job4j.placement;

import org.hibernate.query.Query;
import ru.job4j.model.Customer;

public class ActionCustomer {

    private final static ActionSQL ACTION_SQL = new ActionSQL();

    private static class InstanceActionCustomer {
        private final static ActionCustomer ACTION_CUSTOMER = new ActionCustomer();
    }

    public static ActionCustomer getInstance() {
        return InstanceActionCustomer.ACTION_CUSTOMER;
    }

    public Customer save(Customer customer) {
        return ACTION_SQL.save(customer);
    }

    public Customer getById(int id) {
        return ACTION_SQL.action(session -> {
            Query<Customer> query = session.createQuery("from Customer where id =: id");
            query.setParameter("id", id);
            return query.getSingleResult();
        });
    }

    public Customer getByEmailAndPassword(String email, String password) {
        return ACTION_SQL.action(session -> {
            Query<Customer> query = session.createQuery("from Customer where email =: email and password =: password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.uniqueResult();
        });
    }
}

package ru.job4j.placement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Advertisement;
import ru.job4j.model.Car;
import ru.job4j.model.Customer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ActionCustomerTest {

    private static Customer kirill = new Customer("Kirill", "Belyaev", "belyaev@", "10");
    private static Car audi = new Car("Audi", "a7");
    private static Advertisement one = new Advertisement("one", 1, "photoOne");

    @Before
    public void start() {
        ActionAdvertisement actionAdvertisement = ActionAdvertisement.getInstance();
        ActionCar actionCar = ActionCar.getInstance();
        ActionCustomer actionCustomer = ActionCustomer.getInstance();

        actionCustomer.save(kirill);
        audi.setCustomer(kirill);
        actionCar.save(audi);
        one.setCustomer(kirill);
        one.setCar(audi);
        actionAdvertisement.save(one);
    }

    @After
    public void finish() {
        ActionSQL actionSQL = new ActionSQL();
        actionSQL.action(session -> {
            session.createQuery("delete from Advertisement").executeUpdate();
            session.createQuery("delete from Car").executeUpdate();
            session.createQuery("delete from Customer").executeUpdate();
            return null;
        });
    }

    @Test
    public void whenAddThenGetById() {
        Customer customer = ActionCustomer.getInstance().getById(kirill.getId());
        assertThat(customer, is(kirill));
    }

    @Test
    public void whenAddThenGetByEmailAndPassword() {
        Customer customer = ActionCustomer.getInstance().getByEmailAndPassword(kirill.getEmail(), kirill.getPassword());
        assertThat(customer, is(kirill));
    }
}
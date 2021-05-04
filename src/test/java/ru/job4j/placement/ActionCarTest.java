package ru.job4j.placement;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Advertisement;
import ru.job4j.model.Car;
import ru.job4j.model.Customer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ActionCarTest {

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

    @Test
    public void whenAddThenGetById() {
        Car car = ActionCar.getInstance().getById(audi.getId());
        assertThat(car, is(audi));
    }
}
package ru.job4j.placement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Advertisement;
import ru.job4j.model.Car;
import ru.job4j.model.Customer;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ActionAdvertisementTest {

    private static Customer kirill = new Customer("Kirill", "Belyaev", "belyaev@", "10");
    private static Customer nelli = new Customer("Nelli", "Iskakova", "iskakova@", "20");
    private static Car audi = new Car("Audi", "a7");
    private static Car mercedes = new Car("Mercedes", "E");
    private static Advertisement one = new Advertisement("one", 1, "photoOne");
    private static Advertisement two = new Advertisement("two", 2, "photoTwo");

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

        actionCustomer.save(nelli);
        mercedes.setCustomer(nelli);
        actionCar.save(mercedes);
        two.setCustomer(nelli);
        two.setCar(mercedes);
        actionAdvertisement.save(two);
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
    public void whenAddAndGetAll() {
        List<Advertisement> advertisements = ActionAdvertisement.getInstance().getAll();
        assertThat(advertisements.size(), is(2));
    }

    @Test
    public void whenAddThenGetById() {
        Advertisement advertisement = ActionAdvertisement.getInstance().getById(one.getId());
        assertThat(advertisement.getDescription(), is(one.getDescription()));
    }

    @Test
    public void whenAddThenUpdateAndGetById() {
        one.setDescription("CHANGE");
        ActionAdvertisement.getInstance().update(one);
        Advertisement advertisement = ActionAdvertisement.getInstance().getById(one.getId());
        assertThat(advertisement.getDescription(), is("CHANGE"));
    }

    @Test
    public void whenAddThenGetByBrandOfCar() {
        List<Advertisement> advertisements = ActionAdvertisement.getInstance().getByBrand("Audi");
        assertThat(advertisements.get(0).getCar(), is(audi));
    }

    @Test
    public void whenAddThenGetByHasPhoto() {
        List<Advertisement> advertisements = ActionAdvertisement.getInstance().getHasPhotoOfCar();
        assertThat(advertisements.size(), is(2));
    }

    @Test
    public void whenAddThenGetNotSold() {
        List<Advertisement> advertisements = ActionAdvertisement.getInstance().getAllNotSold();
        assertThat(advertisements.size(), is(2));
    }
}
package ru.job4j.placement;

import org.junit.Test;
import ru.job4j.model.Advertisement;
import ru.job4j.model.Car;
import ru.job4j.model.Customer;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AdRepositoryTest {

    private final ActionSQL actionSQL = new ActionSQL();
    private final AdRepository adRepository = new AdRepository();

    @Test
    public void whenSaveAdvertisement() {

        Customer kirill = new Customer("Kirill", "Belyaev", "belyaev@", "10");
        actionSQL.save(kirill);
        Car audi1 = new Car("Audi", "a7");
        audi1.setCustomer(kirill);
        actionSQL.save(audi1);
        Advertisement one = new Advertisement("one", "photoOne");
        one.setCustomer(kirill);
        one.setCar(audi1);
        actionSQL.save(one);

        Customer misha = new Customer("Misha", "Ordov", "ordov@", "11");
        actionSQL.save(misha);
        Car audi2 = new Car("Audi", "a8");
        audi2.setCustomer(misha);
        actionSQL.save(audi2);
        Advertisement two = new Advertisement("two", "photoTwo");
        two.setCustomer(misha);
        two.setCar(audi2);
        actionSQL.save(two);

        Customer nelli = new Customer("Nelli", "Iskakova", "iskakova@", "12");
        actionSQL.save(nelli);
        Car mercedes = new Car("Mercedes", "E");
        mercedes.setCustomer(nelli);
        actionSQL.save(mercedes);
        Advertisement three = new Advertisement("three", "photoThree");
        three.setCustomer(nelli);
        three.setCar(mercedes);
        actionSQL.save(three);

        // Проданные автомабили

        Customer evgeniy = new Customer("Evgeniy", "Belyaev", "ebelyaev@", "13");
        evgeniy.setHistoryCars(List.of(audi1, audi2, mercedes));
        actionSQL.save(evgeniy);

        Advertisement sold1 = new Advertisement("soled1", "photo1");
        sold1.setCustomer(evgeniy);
        sold1.setCar(audi1);
        sold1.setSold(true);
        sold1.setCreated(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
        actionSQL.save(sold1);

        Advertisement sold2 = new Advertisement("soled2", "photo2");
        sold2.setCustomer(evgeniy);
        sold2.setSold(true);
        sold2.setCar(audi2);
        sold2.setCreated(new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime());
        actionSQL.save(sold2);

        Advertisement sold3 = new Advertisement("soled3", "photo3");
        sold3.setCustomer(evgeniy);
        sold3.setSold(true);
        sold3.setCar(mercedes);
        sold3.setCreated(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
        actionSQL.save(sold3);
    }

    @Test
    public void whenGetAllAdvertisement() {
        List<Advertisement> advertisements = adRepository.getAllAdvertisement();
        assertThat(advertisements.size(), is(6));
        System.out.println(advertisements);
    }

    @Test
    public void whenGetAllAdvertisementLastDay() {
        List<Advertisement> advertisements = adRepository.getAdvertisementForLastDay();
        assertThat(advertisements.size(), is(3));
        System.out.println(advertisements);
    }

    @Test
    public void whenGetAllAdvertisementHasPhoto() {
        List<Advertisement> advertisements = adRepository.getAdvertisementHasPhotoOfCar();
        assertThat(advertisements.size(), is(6));
        System.out.println(advertisements);
    }

    @Test
    public void whenGetAllAdvertisementNotSold() {
        List<Advertisement> advertisements = adRepository.getAllAdvertisementNotSold();
        assertThat(advertisements.size(), is(3));
        System.out.println(advertisements);
    }
}
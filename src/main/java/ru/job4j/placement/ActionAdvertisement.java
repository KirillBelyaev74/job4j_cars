package ru.job4j.placement;

import org.hibernate.query.Query;
import ru.job4j.model.Advertisement;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ActionAdvertisement {

    private static final ActionSQL ACTION_SQL = new ActionSQL();

    private static class InstanceActionCar {
        private final static ActionAdvertisement ACTION_ADVERTISEMENT = new ActionAdvertisement();
    }

    public static ActionAdvertisement getInstance() {
        return InstanceActionCar.ACTION_ADVERTISEMENT;
    }

    public Advertisement save(Advertisement advertisement) {
        return ACTION_SQL.save(advertisement);
    }

    public List<Advertisement> getAll() {
        return ACTION_SQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a "
                            + "join fetch a.customer "
                            + "join fetch a.car "
                            + "order by a.id");
            return query.getResultList();
        });
    }

    public Advertisement getById(int id) {
        return ACTION_SQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a "
                            + "join fetch a.customer "
                            + "join fetch a.car "
                            + "where a.id =: id "
                            + "order by a.id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }

    public void update(Advertisement advertisement) {
        ACTION_SQL.action(session -> {
            session.update(advertisement);
            return null;
        });
    }

    public List<Advertisement> getForLastDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return ACTION_SQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a "
                            + "join fetch a.customer "
                            + "join fetch a.car "
                            + "where a.created >: date"
                            + " order by a.id");
            query.setParameter("date", calendar.getTime());
            return query.getResultList();
        });
    }

    public List<Advertisement> getByBrand(String brand) {
        return ACTION_SQL.action(session -> {
             Query<Advertisement> query = session.createQuery(
                     "select distinct a from Advertisement as a "
                             + "join fetch a.customer "
                             + "join fetch a.car "
                             + "where a.car.brand =: brand"
                             + " order by a.id");
             query.setParameter("brand", brand);
             return query.getResultList();
        });
    }

    public List<Advertisement> getHasPhotoOfCar() {
        return ACTION_SQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a "
                            + "join fetch a.customer "
                            + "join fetch a.car "
                            + "where a.photo is not null"
                            + " order by a.id");
            return query.getResultList();
        });
    }

    public List<Advertisement> getAllNotSold() {
        return ACTION_SQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a "
                            + "join fetch a.customer "
                            + "join fetch a.car "
                            + "where a.sold = false "
                            + "order by a.id");
            return query.getResultList();
        });
    }
}

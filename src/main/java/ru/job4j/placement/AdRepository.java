package ru.job4j.placement;

import org.hibernate.query.Query;
import ru.job4j.model.Advertisement;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AdRepository {

    private static final ActionSQL actionSQL = new ActionSQL();

    public List<Advertisement> getAllAdvertisement() {
        return actionSQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a " +
                            "join fetch a.customer " +
                            "join fetch a.car " +
                            "order by a.id");
            return query.getResultList();
        });
    }

    public List<Advertisement> getAdvertisementForLastDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return actionSQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a " +
                            "join fetch a.customer " +
                            "join fetch a.car " +
                            "where a.created >: date" +
                            " order by a.id");
            query.setParameter("date", calendar.getTime());
            return query.getResultList();
        });
    }

    public List<Advertisement> getAdvertisementByName(String brand) {
        return actionSQL.action(session -> {
             Query<Advertisement> query = session.createQuery(
                     "select distinct a from Advertisement as a " +
                             "join fetch a.customer " +
                             "join fetch a.car " +
                             "where a.car.brand =: brand" +
                             " order by a.id");
             query.setParameter("brand", brand);
             return query.getResultList();
        });
    }

    public List<Advertisement> getAdvertisementHasPhotoOfCar() {
        return actionSQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a " +
                            "join fetch a.customer " +
                            "join fetch a.car " +
                            "where a.photo is not null" +
                            " order by a.id");
            return query.getResultList();
        });
    }

    public List<Advertisement> getAllAdvertisementNotSold() {
        return actionSQL.action(session -> {
            Query<Advertisement> query = session.createQuery(
                    "select distinct a from Advertisement as a " +
                            "join fetch a.customer " +
                            "join fetch a.car " +
                            "where a.sold = false " +
                            "order by a.id");
            return query.getResultList();
        });
    }
}

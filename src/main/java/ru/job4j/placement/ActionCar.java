package ru.job4j.placement;

import org.hibernate.query.Query;
import ru.job4j.model.Car;

public class ActionCar {

    private final static ActionSQL ACTION_SQL = new ActionSQL();

    private static class InstanceActionCar {
        private final static ActionCar ACTION_CAR = new ActionCar();
    }

    public static ActionCar getInstance() {
        return InstanceActionCar.ACTION_CAR;
    }

    public Car save(Car car) {
        return ACTION_SQL.save(car);
    }

    public Car getById(int id) {
        return ACTION_SQL.action(session -> {
            Query<Car> query = session.createQuery(
                    "select distinct c from Car as c "
                            + "join fetch c.advertisement "
                            + "where c.id =: id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }
}

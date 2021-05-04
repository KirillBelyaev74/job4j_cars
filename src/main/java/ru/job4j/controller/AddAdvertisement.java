package ru.job4j.controller;

import org.json.JSONObject;
import ru.job4j.model.Advertisement;
import ru.job4j.model.Car;
import ru.job4j.model.Customer;
import ru.job4j.placement.ActionAdvertisement;
import ru.job4j.placement.ActionCar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddAdvertisement extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("description");

        Customer customer = (Customer) req.getSession().getAttribute("customer");
        Car car = new Car(brand, model);
        car.setCustomer(customer);
        ActionCar.getInstance().save(car);

        Advertisement advertisement = new Advertisement(description, price);
        advertisement.setCar(car);
        advertisement.setCustomer(customer);
        ActionAdvertisement.getInstance().save(advertisement);

        if (advertisement.getId() != 0) {
            try (PrintWriter printWriter = new PrintWriter(resp.getOutputStream())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("answer", "Ваше объявление успешно добавлено");
                printWriter.println(jsonObject.toString());
            }
        }
    }
}

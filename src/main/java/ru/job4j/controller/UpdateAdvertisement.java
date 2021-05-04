package ru.job4j.controller;

import org.json.JSONObject;
import ru.job4j.model.Advertisement;
import ru.job4j.placement.ActionAdvertisement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateAdvertisement extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Advertisement advertisement = (Advertisement) req.getSession().getAttribute("advertisement");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("description");
        String sold = req.getParameter("sold");

        advertisement.setPrice(price);
        advertisement.setDescription(description);
        if (sold.equals("1")) {
            advertisement.setSold(true);
        }
        ActionAdvertisement.getInstance().update(advertisement);

        req.getSession().setAttribute("advertisement", null);

        try (PrintWriter printWriter = new PrintWriter(resp.getOutputStream())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("answer", "Ваше объявление успешно отредактировано");
            printWriter.println(jsonObject.toString());
        }
    }
}

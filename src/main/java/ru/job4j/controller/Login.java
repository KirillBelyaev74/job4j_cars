package ru.job4j.controller;

import org.json.JSONObject;
import ru.job4j.model.Customer;
import ru.job4j.placement.ActionCustomer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        ActionCustomer actionCustomer = ActionCustomer.getInstance();
        String answer = "Вы успешно авторизовались";
        String url = "http://localhost:8080/job4j_cars/index.do";
        Customer customer = actionCustomer.getByEmailAndPassword(email, password);

        if (customer == null) {
            if (name != null && surname != null && email != null && password != null) {
                customer = actionCustomer.save(new Customer(name, surname, email, password));
                answer = "Вы успешно зарегистрировались";
            } else if (name == null || surname == null) {
                answer = "Такого пользователя не существует!";
                url = "";
            }
        }

        req.getSession().setAttribute("customer", customer);
        try (PrintWriter printWriter = new PrintWriter(resp.getOutputStream())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("answer", answer);
            jsonObject.put("url", url);
            printWriter.println(jsonObject.toString());
        }
    }
}

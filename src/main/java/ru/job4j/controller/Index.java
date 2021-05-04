package ru.job4j.controller;

import ru.job4j.placement.ActionAdvertisement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("advertisements", ActionAdvertisement.getInstance().getAll());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

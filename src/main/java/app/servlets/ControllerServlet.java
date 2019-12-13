package app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private String pathToPointChecker = "/check";
    private String pathToJspPage = "/index.jsp";
    private int time = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Сервлет app.servlets.ControllerServlet получил управление." + time++);

        ServletContext servletContext = this.getServletContext();

        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String[] radii = req.getParameterValues("radius");
        if (x == null || y == null || radii == null || radii.length == 0) {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(pathToJspPage);
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(pathToPointChecker);
            requestDispatcher.forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //log("Приложение не предназначено для обработки GET-запросов! Попробуйте POST");
        doPost(req, resp);
    }
}

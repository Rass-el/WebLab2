package app.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import app.rassel.Point;

//@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    private int pointNumber = 0;
    private int time = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("AreaCheckServlet получил управление." + time++);


        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String[] radii = req.getParameterValues("radius");
        if (x == null || y == null || radii == null) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        boolean isInside = isPointInside(Float.valueOf(x), Float.valueOf(y), Float.valueOf(radii[0]));
        savePoint(new Point(Float.valueOf(x), Float.valueOf(y), Float.valueOf(radii[0])), isInside);


        ServletContext servletContext = this.getServletContext();
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            out.write("<head><meta charset=\"UTF-8\"></head>");
            out.write("<table border=1px width=\"100%\" cellpadding=\"5\">");
            out.write("<tr> <td>" + x + "</td><td>x</td></tr>");
            out.write("<tr> <td>" + y + "</td><td>y</td></tr>");
            out.write("<tr> <td>" + radii[0] + "</td><td>radius</td></tr>");
            out.write("<tr> <td>" + isInside + "</td></tr>");
            out.write("</table>");
            //out.write("<p>Point (" + x + " ; " + y + " ; " + radii[0] + ") is " + (isInside ? "inside" : "outside")+ "</p>");
            out.write("<p class=\"center\"><a href=\"index.jsp\">Назад</a></p>");

        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    public boolean isPointInside(final float x, final float y, final float radius) {
        //check triangle
        if ( x >= -radius && x <= 0 && y <= 0 && y >= -radius/2 && x+radius >= -2*y ) {
            return true;
        }
        //check rectangle
        if ( x >= 0 && x <= radius/2 && y >= 0 && y <= radius) {
            return true;
        }
        //check 1/4 circle
        if ( x >= 0 && y <= 0 && x*x + y*y <= radius*radius) {
            return true;
        }
        return false;
    }

    public void savePoint(Point point, boolean isInside) {
        ServletContext servletContext = this.getServletContext();
        point.setInside(isInside);
        servletContext.setAttribute("point" + pointNumber++, point);
    }

    public void createHTMLPage(HttpServletRequest req, HttpServletResponse resp) {
    }
}

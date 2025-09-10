package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Result;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/areaCheck")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            processRequest(req, res);
        } catch(ServletException | IOException e) {
            e.printStackTrace();//tam the nay da
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Double x = Double.parseDouble(req.getParameter("x"));
        Double y = Double.parseDouble(req.getParameter("y"));
        Double R = Double.parseDouble(req.getParameter("R"));
        boolean hit;
        HttpSession session = req.getSession();
        long start = System.nanoTime();
        if(checkCircle(x, y, R) || checkSquare(x, y, R) || checkTriangle(x, y, R)) {
            hit = true;
        } else {
            hit = false;
        }
        long end = System.nanoTime();
        Double calTime = (end - start) / 1000000.0;
        LocalDateTime releaseTime = LocalDateTime.now();
        Result result = new Result(x, y, R, hit, calTime, releaseTime);

        List<Result> curHistory;

        try {
            curHistory = (List<Result>)session.getAttribute("history");
            if(curHistory == null) {
                curHistory = new LinkedList<>();
            }
            curHistory.add(result);
            session.setAttribute("history", curHistory);
        } catch(ClassCastException e) {
            curHistory = new LinkedList<>();
            curHistory.add(result);
            session.setAttribute("history", curHistory);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/result.jsp");
        dispatcher.forward(req, res);
    }

    private boolean checkSquare(Double x, Double y, Double R) {
        return x <= 0 && y >= 0 && x >= -R / 2 && y <= R;
    }

    private boolean checkCircle(Double x, Double y, Double R) {
        return x >= 0 && y >= 0 && (x * x + y * y <= R * R);
    }

    private boolean checkTriangle(Double x, Double y, Double R) {
        return x >= 0 && y <= 0 && ((x - 2 * y) <= R);
    }
}

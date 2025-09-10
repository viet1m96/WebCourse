package servlets;

import Logging.ErrorFactory;
import exceptions.InputFault;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validators.ParametersChecker;

import java.io.IOException;

@WebServlet("/app")
public class ControllerServlet extends HttpServlet {
    public ControllerServlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            process(req, res);
        } catch(ServletException | IOException e) {
            e.printStackTrace();//tam de the nay
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String R = req.getParameter("R");
        try {
            ParametersChecker.checkParams(x, y, R);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/areaCheck");
            dispatcher.forward(req, res);
        } catch(InputFault e) {
            ErrorFactory.feedbackError(req, res, HttpServletResponse.SC_BAD_REQUEST, ErrorFactory.BAD_REQUEST, e.getMessage());
        }

    }
}

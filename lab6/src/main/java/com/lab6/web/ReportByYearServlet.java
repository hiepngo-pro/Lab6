package com.lab6.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab6.dao.FavoriteManager;
import com.lab6.dto.Report;

@WebServlet("/reportByYear")
public class ReportByYearServlet extends HttpServlet {
    private FavoriteManager manager;

    @Override
    public void init() {
        manager = new FavoriteManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int year = Integer.parseInt(req.getParameter("year"));
            List<Report> reports = manager.getReportByYear(year);
            req.setAttribute("reports", reports);
            req.setAttribute("year", year);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Năm không hợp lệ");
        }
        req.getRequestDispatcher("/WEB-INF/views/report-by-year.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        manager.close();
    }
}
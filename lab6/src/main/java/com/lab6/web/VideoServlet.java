package com.lab6.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/video/*")
public class VideoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null) path = "/";
        switch (path) {
            case "/byUser":
                req.getRequestDispatcher("/WEB-INF/views/search-by-user.jsp").forward(req, resp);
                break;
            case "/byKeyword":
                req.getRequestDispatcher("/WEB-INF/views/search-by-keyword.jsp").forward(req, resp);
                break;
            case "/byVideo":
                req.getRequestDispatcher("/WEB-INF/views/search-users-by-video.jsp").forward(req, resp);
                break;
            case "/byFavoriteStatus":
                req.getRequestDispatcher("/WEB-INF/views/favorite-status.jsp").forward(req, resp);
                break;
            case "/report":
                req.getRequestDispatcher("/WEB-INF/views/report.jsp").forward(req, resp);
                break;
            case "/random10":
                req.getRequestDispatcher("/WEB-INF/views/random10.jsp").forward(req, resp);
                break;
            case "/reportByYear":
                req.getRequestDispatcher("/WEB-INF/views/report-by-year.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/video/byUser");
        }
    }
}
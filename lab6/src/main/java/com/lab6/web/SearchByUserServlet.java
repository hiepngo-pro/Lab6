package com.lab6.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab6.dao.FavoriteManager;
import com.lab6.entity.Video;

@WebServlet("/searchByUser")
public class SearchByUserServlet extends HttpServlet {
    private FavoriteManager manager;

    @Override
    public void init() {
        manager = new FavoriteManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        List<Video> videos = manager.findVideosByUser(userId);
        req.setAttribute("videos", videos);
        req.setAttribute("userId", userId);
        req.getRequestDispatcher("/WEB-INF/views/search-by-user.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        manager.close();
    }
}
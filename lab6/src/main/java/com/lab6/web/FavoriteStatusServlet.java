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

@WebServlet("/favoriteStatus")
public class FavoriteStatusServlet extends HttpServlet {
    private FavoriteManager manager;

    @Override
    public void init() {
        manager = new FavoriteManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String favParam = req.getParameter("favorite");
        boolean favorite = "true".equalsIgnoreCase(favParam);
        List<Video> videos = manager.findVideosByFavoriteStatus(favorite);
        req.setAttribute("videos", videos);
        req.setAttribute("favorite", favorite);
        req.getRequestDispatcher("/WEB-INF/views/favorite-status.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        manager.close();
    }
}
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

@WebServlet("/favoriteVideos")
public class FavoriteVideosServlet extends HttpServlet {
    private FavoriteManager manager;

    @Override
    public void init() {
        manager = new FavoriteManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> videos = manager.findVideosByFavoriteStatus(true);
        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/WEB-INF/views/favorite-videos.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        manager.close();
    }
}
package com.lab6.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab6.dao.FavoriteManager;
import com.lab6.entity.User;

@WebServlet("/searchUsersByVideo")
public class SearchUsersByVideoServlet extends HttpServlet {
    private FavoriteManager manager;

    @Override
    public void init() {
        manager = new FavoriteManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String videoId = req.getParameter("videoId");
        List<User> users = manager.findUsersByVideo(videoId);
        req.setAttribute("users", users);
        req.setAttribute("videoId", videoId);
        req.getRequestDispatcher("/WEB-INF/views/search-users-by-video.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        manager.close();
    }
}
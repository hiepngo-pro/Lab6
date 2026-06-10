package com.lab6.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab6.dao.UserManager;
import com.lab6.entity.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserManager userManager;

    @Override
    public void init() {
        userManager = new UserManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                req.getRequestDispatcher("/WEB-INF/views/user-form.jsp").forward(req, resp);
                break;
            case "edit":
                String id = req.getParameter("id");
                User user = userManager.findById(id);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/views/user-form.jsp").forward(req, resp);
                break;
            case "delete":
                id = req.getParameter("id");
                userManager.deleteById(id);
                resp.sendRedirect("user");
                break;
            default:
                List<User> users = userManager.findAll();
                req.setAttribute("users", users);
                req.getRequestDispatcher("/WEB-INF/views/user-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String adminStr = req.getParameter("admin");
        String phone = req.getParameter("phone");
        boolean admin = "on".equals(adminStr);

        User user = new User(id, password, fullname, email, admin, phone);
        try {
            User existing = userManager.findById(id);
            if (existing == null) {
                userManager.create(user);
            } else {
                userManager.update(user);
            }
            resp.sendRedirect("user");
        } catch (IOException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/views/user-form.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        userManager.close();
    }
}
package com.iryna.web.servlet;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private UserService userService;

    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        response.getWriter().write(HtmlResponseCreator.generatePage(data, "/login.html"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (userService.checkPassword(request.getParameter("name"), request.getParameter("password"))) {
            response.addCookie(new Cookie("user-token", userService.generateToken()));
            response.sendRedirect("/products/editor");
        }
    }
}

package com.iryna.web.servlet;

import com.iryna.creator.HtmlCreator;
import com.iryna.service.SecurityService;
import com.iryna.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private SecurityService securityService;

    public LoginServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        response.getWriter().write(HtmlCreator.generatePage(data, "/login.html"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (securityService.checkPassword(request.getParameter("name"), request.getParameter("password"))) {
            response.addCookie(new Cookie("user-token", securityService.generateToken()));
            response.sendRedirect("/products/editor");
        }
    }
}

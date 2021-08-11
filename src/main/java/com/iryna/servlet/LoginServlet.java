package com.iryna.servlet;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.entity.User;
import com.iryna.security.SecurityService;
import com.iryna.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private UserService loginService;
    private SecurityService securityService;

    public LoginServlet(UserService loginService, SecurityService securityService) {
        this.securityService = securityService;
        this.loginService = loginService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        response.getWriter().write(HtmlResponseCreator.getTemplate(data, "/login.html"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginService.createUser(User.builder().
                userName(request.getParameter("name")).
                password(request.getParameter("password")).
                build());
        response.addCookie(securityService.generateCookie());
        response.sendRedirect("/products/editor");
    }
}

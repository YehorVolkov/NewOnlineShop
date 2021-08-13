package com.iryna.web.servlet;

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

    private UserService userService;
    private SecurityService securityService;

    public LoginServlet(UserService userService, SecurityService securityService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        response.getWriter().write(HtmlResponseCreator.getTemplate(data, "/login.html"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.createUser(User.builder().
                userName(request.getParameter("name")).
                password(request.getParameter("password")).
                build());
        response.addCookie(securityService.generateCookie());
        response.sendRedirect("/products/editor");
    }
}

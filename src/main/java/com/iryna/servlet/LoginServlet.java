package com.iryna.servlet;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.entity.User;
import com.iryna.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LoginServlet extends HttpServlet {

    private UserService loginService;
    private List<String> sessionList;

    public LoginServlet(UserService loginService, List<String> sessionList) {
        this.sessionList = sessionList;
        this.loginService = loginService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> data = new HashMap<>();
            response.getWriter().write(HtmlResponseCreator.getTemplate(data, "/login.html"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        loginService.createUser(User.builder().
                userName(request.getParameter("name")).
                password(request.getParameter("password")).
                build());
        String uuid = UUID.randomUUID().toString();
        sessionList.add(uuid);
        Cookie cookie = new Cookie("user-token", uuid);
        response.addCookie(cookie);
        try {
            response.sendRedirect("/products/editor");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

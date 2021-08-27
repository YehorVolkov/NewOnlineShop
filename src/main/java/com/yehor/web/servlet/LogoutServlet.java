package com.yehor.web.servlet;

import javax.servlet.http.HttpServlet;

public class LogoutServlet extends HttpServlet {

/*    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    securityService.logout(cookie.getValue());
                }
            }
        }
        response.sendRedirect("/login");
    }*/
}

package com.yehor.web.servlet;

import javax.servlet.http.HttpServlet;

public class LoginServlet extends HttpServlet {

/*    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);
    // TODO loader in ServiceLocator, is it ok?
    private SettingsLoader settingsLoader = ServiceLocator.getService(SettingsLoader.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        response.getWriter().write(HtmlCreator.generatePage(data, "/login.html"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = securityService.login(request.getParameter("name"), request.getParameter("password"));
        if (token != null) {
            Cookie cookie = new Cookie("user-token", token);
            cookie.setMaxAge(settingsLoader.getTimeToLiveSession());
            response.addCookie(cookie);
            log.info("Login successful");
            response.sendRedirect("/products");
        }
    }*/
}

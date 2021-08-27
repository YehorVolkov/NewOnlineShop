package com.yehor.web.servlet;

import javax.servlet.http.HttpServlet;

public class RemoveFromCartServlet extends HttpServlet {

/*    private UserService userService = ServiceLocator.getService(UserService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String token = CookieParser.getTokenFromCookies(req.getCookies());
        userService.removeProductFromCart(token, Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/cart");
    }*/
}

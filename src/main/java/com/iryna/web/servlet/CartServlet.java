package com.iryna.web.servlet;

import com.iryna.creator.HtmlCreator;
import com.iryna.entity.Product;
import com.iryna.service.SecurityService;
import com.iryna.web.parser.CookieParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServlet extends HttpServlet {

    private SecurityService securityService;

    public CartServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Product> token = securityService.getChartByToken(CookieParser.getTokenFromCookies(req.getCookies()));
        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", token);
        resp.getWriter().println(HtmlCreator.generatePage(templateData, "/product_cart.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String method = req.getParameter("method");
        String token = CookieParser.getTokenFromCookies(req.getCookies());

        if (method == null) {
            securityService.addProductToChart(token,
                    Integer.parseInt(req.getParameter("id")));
        } else if (method.equals("delete")) {
            securityService.removeProductFromChart(token,
                    Integer.parseInt(req.getParameter("id")));
        }
        resp.sendRedirect("/cart");
    }
}

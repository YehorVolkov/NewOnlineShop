package com.yehor.web.servlet;

import javax.servlet.http.HttpServlet;

public class CartServlet extends HttpServlet {

/*    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Session session = (Session) req.getAttribute("session");
        List<Product> productsAtCart = session.getCart();
        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", productsAtCart);
        resp.getWriter().println(HtmlCreator.generatePage(templateData, "/product_cart.html"));
    }*/
}

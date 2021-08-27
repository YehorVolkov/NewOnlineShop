package com.yehor.web.servlet;

import javax.servlet.http.HttpServlet;

public class ProductListServlet extends HttpServlet {

/*    private ProductService productService = ServiceLocator.getService(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", productService.findAll());
        resp.getWriter().println(HtmlCreator.generatePage(templateData, "/product_list.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", productService.getSearchedProducts(req.getParameter("searchingProduct")));
        resp.getWriter().println(HtmlCreator.generatePage(templateData, "/product_list.html"));
    }*/
}

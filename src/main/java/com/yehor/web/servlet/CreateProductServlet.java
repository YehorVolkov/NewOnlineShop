package com.yehor.web.servlet;

import javax.servlet.http.HttpServlet;

public class CreateProductServlet extends HttpServlet {

/*    private ProductService productService = ServiceLocator.getService(ProductService.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        response.getWriter().write(HtmlCreator.generatePage(data, "/add_product_page.html"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setName(request.getParameter("productName"));
        product.setProductDescription(request.getParameter("productDescription"));
        product.setPrice(Double.parseDouble(request.getParameter("productPrice")));

        productService.createProduct(product);

        response.sendRedirect("/products");
    }*/
}
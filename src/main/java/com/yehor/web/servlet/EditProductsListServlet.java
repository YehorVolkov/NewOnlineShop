package com.yehor.web.servlet;

import javax.servlet.http.HttpServlet;

public class EditProductsListServlet extends HttpServlet {

/*    private ProductService productService = ServiceLocator.getService(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", productService.findAll());
        resp.getWriter().println(HtmlCreator.generatePage(templateData, "/edit_product_list.html"));
    }

    // TODO WHERE WAS IT EVEN USED?
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (req.getParameter("method").equals("DELETE")) {
            productService.removeProduct(Long.parseLong(req.getParameter("id")));
        }
        resp.sendRedirect("/product/editor");
    }

    // TODO WHERE WAS IT EVEN USED?
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

        productService.updateProduct(Product.builder()
                .id(Long.parseLong(req.getParameter("productId")))
                .name(req.getParameter("productName"))
                .price(Double.parseDouble(req.getParameter("productPrice")))
                .productDescription(String.valueOf(req.getParameter("productDescription")))
                .creationDate(LocalDateTime.now())
                .build());
    }*/
}

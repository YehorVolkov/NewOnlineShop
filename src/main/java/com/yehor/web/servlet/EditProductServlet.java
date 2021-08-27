package com.yehor.web.servlet;

import javax.servlet.http.HttpServlet;

public class EditProductServlet extends HttpServlet {

/*    private ProductService productService = ServiceLocator.getService(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("product", productService.findById(Integer.parseInt(req.getParameter("id"))));
        resp.getWriter().println(HtmlCreator.generatePage(templateData, "/edit_product.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Product product = Product.builder()
                .name(req.getParameter("name"))
                .id(Long.parseLong(req.getParameter("id")))
                .price(Double.parseDouble(req.getParameter("price")))
                .productDescription(req.getParameter("description"))
                .build();

        productService.updateProduct(product);
        resp.sendRedirect("/products");
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }*/
}

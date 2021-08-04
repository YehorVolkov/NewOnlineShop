package com.iryna.creator;

import com.iryna.entity.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HtmlResponseCreator {

    public static String createTableOfProducts(List<Product> products) {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", products);
        Writer stream = new StringWriter();
        try {
            Template template = new Configuration().getTemplate("templates/products_list.html");
            template.process(templateData, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    public static String getPageForAddProduct() {
        Map<String, Object> data = new HashMap<>();
        Writer stream = new StringWriter();
        try {
            Template template = new Configuration().getTemplate("templates/add_product_page.html");
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }
}

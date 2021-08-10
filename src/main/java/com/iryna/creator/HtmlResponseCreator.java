package com.iryna.creator;

import com.iryna.entity.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class HtmlResponseCreator {

    public static String getTemplate(Map<String, Object> templateData, String path) {
        Writer stream = new StringWriter();
        try {
            Configuration configuration = new Configuration();
            configuration.setClassForTemplateLoading(HtmlResponseCreator.class, "/templates/");
            Template template = configuration.getTemplate(path);
            template.process(templateData, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

//    public static String getPageForAddProduct1() {
//        Map<String, Object> data = new HashMap<>();
//        Writer stream = new StringWriter();
//        try {
//            Configuration configuration = new Configuration();
//            configuration.setClassForTemplateLoading(HtmlResponseCreator.class, "/");
//            Template template = configuration.getTemplate("templates/add_product_page.html");
//            template.process(data, stream);
//        } catch (IOException | TemplateException e) {
//            throw new RuntimeException(e);
//        }
//        return stream.toString();
//    }
}

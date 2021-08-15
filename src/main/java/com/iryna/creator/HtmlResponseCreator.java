package com.iryna.creator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class HtmlResponseCreator {

    public static String generatePage(Map<String, Object> templateData, String path) {
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
}

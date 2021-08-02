package com.iryna.creator;

import com.iryna.entity.Product;

import java.text.SimpleDateFormat;
import java.util.List;

public class HtmlResponseCreator {

    public static String createTableOfProducts(List<Product> products) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        addHeader(stringBuilder);
        for (Product product : products) {
            addRow(stringBuilder, product);
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private static void addHeader(StringBuilder stringBuilder) {

        List<String> tableHeaders = List.of("id", "name", "price", "date");
        stringBuilder.append("<tr>");

        for (String header : tableHeaders) {
            stringBuilder.append("<th>");
            stringBuilder.append(header);
            stringBuilder.append("</th>");
        }
        stringBuilder.append("</tr>");
    }

    private static void addRow(StringBuilder stringBuilder, Product product) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        stringBuilder.append("<tr>");
        addColumn(stringBuilder, String.valueOf(product.getId()));
        addColumn(stringBuilder, String.valueOf(product.getName()));
        addColumn(stringBuilder, String.valueOf(product.getPrice()));
        addColumn(stringBuilder, dateFormat.format(product.getDate()));
        stringBuilder.append("</tr>");
    }

    private static void addColumn(StringBuilder stringBuilder, String columnValue) {
        stringBuilder.append("<td>");
        stringBuilder.append(columnValue);
        stringBuilder.append("</td>");
    }
}

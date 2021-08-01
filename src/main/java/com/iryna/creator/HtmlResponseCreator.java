package com.iryna.creator;

import com.iryna.entity.Product;

import java.text.SimpleDateFormat;
import java.util.List;

public class HtmlResponseCreator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    public String createTableOfProducts(List<Product> products) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table><tr><th>id</th><th>name</th><th>price</th><th>date</th></tr>");
        for (Product product : products) {
            addRow(stringBuilder, product);
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private void addRow(StringBuilder stringBuilder, Product product) {
        stringBuilder.append("<tr>");
        addColumn(stringBuilder, String.valueOf(product.getId()));
        addColumn(stringBuilder, String.valueOf(product.getName()));
        addColumn(stringBuilder, String.valueOf(product.getPrice()));
        addColumn(stringBuilder, dateFormat.format(product.getDate()));
        stringBuilder.append("</tr>");
    }

    private void addColumn(StringBuilder stringBuilder, String columnValue) {
        stringBuilder.append("<td>");
        stringBuilder.append(columnValue);
        stringBuilder.append("</td>");
    }
}

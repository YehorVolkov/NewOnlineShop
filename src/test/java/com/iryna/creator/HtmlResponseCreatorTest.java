package com.iryna.creator;

import com.iryna.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HtmlResponseCreatorTest {

    @DisplayName("return Table Of Products")
    @Test
    void returnTableOfProducts() {
        HtmlResponseCreator htmlResponseCreator = new HtmlResponseCreator();
        String response = "<table>" +
                "<tr>" +
                "<th>id</th>" +
                "<th>name</th>" +
                "<th>price</th>" +
                "<th>date</th>" +
                "</tr>" +
                "<tr>" +
                "<td>1</td>" +
                "<td>pen</td>" +
                "<td>12.99</td>" +
                "<td>2021.07.31 23:24:24</td>" +
                "</tr>" +
                "<tr>" +
                "<td>2</td>" +
                "<td>pencil</td>" +
                "<td>11.99</td>" +
                "<td>2021.07.31 23:24:24</td>" +
                "</tr>" +
                "</table>";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(1627763064166L);
        System.out.println("date: " + dateFormat.format( date ) );
        assertEquals(response, htmlResponseCreator.createTableOfProducts(List.of(new Product(1, "pen", 12.99, date),
                new Product(2, "pencil", 11.99, date))));
    }
}
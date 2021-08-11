package com.iryna.creator;

import com.iryna.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HtmlResponseCreatorTest {

    @DisplayName("return Table Of Products")
    @Test
    void returnTableOfProducts() {

        LocalDateTime localDateTime = LocalDateTime.now();
        List products = new ArrayList<Product>();
        products.add(Product.builder().id(1).creationDate(localDateTime).name("pen").price(98.00).build());
        products.add(Product.builder().id(2).creationDate(localDateTime).name("pencil").price(98.00).build());
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", products);
        String response = HtmlResponseCreator.getTemplate(templateData, "/products_list.html");
        assertNotNull(response);
    }
}
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

    @DisplayName("generate product page")
    @Test
    void returnTableOfProducts() {

        LocalDateTime localDateTime = LocalDateTime.now();
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().id(1).creationDate(localDateTime).name("pen").price(98.00).build());
        products.add(Product.builder().id(2).creationDate(localDateTime).name("pencil").price(98.00).build());
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", products);
        String response = HtmlResponseCreator.generatePage(templateData, "/products_list.html");
        assertNotNull(response);
    }
}
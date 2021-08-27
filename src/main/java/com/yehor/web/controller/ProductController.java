package com.yehor.web.controller;

import com.yehor.entity.Product;
import com.yehor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product_list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchingProduct, Model model) {
        model.addAttribute("products", productService.getSearchedProducts(searchingProduct));
        return "product_list";
    }

    @GetMapping("/edit")
    public String findById(@RequestParam int id, Model model) {
        model.addAttribute("product", productService.findById(id));
       return "edit_product";
    }

    @PostMapping("/edit")
    public String updateProduct(@RequestParam String name,
                                 @RequestParam long id,
                                 @RequestParam double price,
                                 @RequestParam String description) {
        Product product = Product.builder()
                .name(name)
                .id(id)
                .price(price)
                .productDescription(description)
                .build();

        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/editor")
    public String editProductsPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "edit_product_list";
    }

    @GetMapping("/create")
    public String addProductPage() {
        return "add_product_page";
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam String productName,
                                @RequestParam String productDescription,
                                @RequestParam double productPrice) {
        Product product = new Product();
        product.setName(productName);
        product.setProductDescription(productDescription);
        product.setPrice(productPrice);
        productService.createProduct(product);
       return "redirect:/products";
    }

    @PostMapping("/remove")
    public String removeProduct(@RequestParam long id) {
        productService.removeProduct(id);
        return "redirect:/products/editor";
    }
}

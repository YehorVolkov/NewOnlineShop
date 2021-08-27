package com.yehor.web.controller;

import com.yehor.entity.Product;
import com.yehor.security.Session;
import com.yehor.service.UserService;
import com.yehor.web.parser.CookieParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String doGet(HttpServletRequest req, Model model) {
        Session session = (Session) req.getAttribute("session");
        List<Product> productsAtCart = session.getCart();
        model.addAttribute("products", productsAtCart);
        return "product_cart";

    }

    @PostMapping("/add")
    public String addToCart(HttpServletRequest req) {
        String token = CookieParser.getTokenFromCookies(req.getCookies());
        userService.addProductToCart(token, Integer.parseInt(req.getParameter("id")));
        return "redirect:/products";
    }

    @PostMapping("/remove")
    public String removeFromCart(HttpServletRequest req) {
        String token = CookieParser.getTokenFromCookies(req.getCookies());
        userService.removeProductFromCart(token, Integer.parseInt(req.getParameter("id")));
        return "redirect:/cart";
    }
}

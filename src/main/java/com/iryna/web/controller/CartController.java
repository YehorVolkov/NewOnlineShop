package com.iryna.web.controller;

import com.iryna.security.Session;
import com.iryna.service.UserService;
import com.iryna.web.parser.CookieParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    // TODO CHECK CART PAGE HREFS!

    @Autowired
    private UserService userService;

    @GetMapping
    public String doGet(@RequestParam Session session, Model model) {
        model.addAttribute("products", session.getCart());
        return "product_cart";
    }

    @PostMapping("/add")
    public String addToCart(HttpServletRequest req) {
        String token = CookieParser.getTokenFromCookies(req.getCookies());
        userService.addProductToChart(token, Integer.parseInt(req.getParameter("id")));
        return "redirect:/products";
    }

    @PostMapping("/remove")
    public String removeFromCart(HttpServletRequest req) {
        String token = CookieParser.getTokenFromCookies(req.getCookies());
        userService.removeProductFromChart(token, Integer.parseInt(req.getParameter("id")));
        return "redirect:/cart";
    }
}
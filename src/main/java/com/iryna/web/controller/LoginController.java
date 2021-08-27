package com.iryna.web.controller;

import com.iryna.loader.SettingsLoader;
import com.iryna.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SettingsLoader settingsLoader;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String doLogin(HttpServletResponse response, @RequestParam String name, @RequestParam String password) {
        String token = securityService.login(name, password);
        if (token != null) {
            Cookie cookie = new Cookie("user-token", token);
            cookie.setMaxAge(settingsLoader.getTimeToLiveSession());
            response.addCookie(cookie);
            log.info("Login successful");
            return "redirect:/products";
        }
        return null; // TODO OK?
    }
}

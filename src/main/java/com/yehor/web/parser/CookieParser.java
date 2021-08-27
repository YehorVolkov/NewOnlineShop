package com.yehor.web.parser;

import javax.servlet.http.Cookie;

public class CookieParser {

    public static String getTokenFromCookies(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}

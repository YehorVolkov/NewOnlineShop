package com.iryna.security;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.UUID;

public class SecurityService {

    private List<String> sessionList;

    public SecurityService(List<String> sessionList) {
        this.sessionList = sessionList;
    }

    public Cookie generateCookie() {
        String uuid=  UUID.randomUUID().toString();
        sessionList.add(uuid);
        return new Cookie("user-token", uuid);
    }

    public boolean isTokenExist(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (sessionList.contains(cookie.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

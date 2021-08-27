package com.yehor.web.filter;

import com.yehor.security.SecurityService;
import com.yehor.security.Session;
import com.yehor.web.parser.CookieParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private SecurityService securityService;

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String path = httpServletRequest.getRequestURI();
        if (path.equals("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = CookieParser.getTokenFromCookies(httpServletRequest.getCookies());
        Session session = securityService.getSession(token);
        if (session != null) {
            httpServletRequest.setAttribute("session", session);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        httpServletResponse.sendRedirect("/login");
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}

package com.iryna.web.filter;

import com.iryna.entity.Role;
import com.iryna.security.SecurityService;
import com.iryna.web.parser.CookieParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GuestFilter implements Filter {

    private SecurityService securityService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = CookieParser.getTokenFromCookies(httpServletRequest.getCookies());

        if (token != null) {
            if (securityService.isAccessAllowForRole(Role.GUEST, token)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        httpServletResponse.sendRedirect("/login");
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}

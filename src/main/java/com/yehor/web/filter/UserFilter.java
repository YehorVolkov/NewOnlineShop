package com.yehor.web.filter;

import com.yehor.entity.Role;
import com.yehor.security.SecurityService;
import com.yehor.web.parser.CookieParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private SecurityService securityService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = CookieParser.getTokenFromCookies(httpServletRequest.getCookies());
        log.info("Check if user is authorized");
        if (token != null) {
            if (securityService.isAccessAllowForRole(Role.USER, token)) {
                filterChain.doFilter(servletRequest, servletResponse);
                log.info("Authorized access");
                return;
            }
        }
        log.info("Unauthorized access " + httpServletRequest.getRequestURI());
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

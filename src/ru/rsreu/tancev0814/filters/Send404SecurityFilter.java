package ru.rsreu.tancev0814.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Send404SecurityFilter implements Filter {
    private int errorCode;
    public void init(FilterConfig fConfig) {
        errorCode = Integer.parseInt(fConfig.getInitParameter("errorcode"));
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendError(errorCode);
    }
    public void destroy() {
    }
}
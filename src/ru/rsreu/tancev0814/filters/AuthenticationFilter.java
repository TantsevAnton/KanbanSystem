package ru.rsreu.tancev0814.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.tancev0814.ActionFactory;
import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    public void init(FilterConfig fConfig) {
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(req);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null && command.isAuthenticationRequired()) {
            resp.sendRedirect(req.getContextPath() + Resourcer.getProperty("path.page.index"));
        } else {
            chain.doFilter(request, response);
        }
    }
    public void destroy() {
    }
}

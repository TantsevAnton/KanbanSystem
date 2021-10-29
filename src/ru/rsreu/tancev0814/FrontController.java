package ru.rsreu.tancev0814;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.io.IOException;

/**
 * Application Main Servlet.
 */
public class FrontController extends HttpServlet {

    /**
     * Get request handler.
     * @param request Request object.
     * @param response Response object.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Post request handler.
     * @param request Request object.
     * @param response Response object.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Generalized method for processing requests of any type.
     * @param request Request object.
     * @param response Response object.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            if (command.getForward()) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + page);
            }
        } else {
            page = Resourcer.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    Resourcer.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

}

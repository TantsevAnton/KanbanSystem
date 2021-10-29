package ru.rsreu.tancev0814;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.commands.CommandEnum;
import ru.rsreu.tancev0814.commands.page.EmptyCommand;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand(false,  false);
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + Resourcer.getProperty("message.wrongaction"));
        }
        return current;
    }
}

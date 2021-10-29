package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

public class EmptyCommand extends ActionCommand {

    public EmptyCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = Resourcer.getProperty("path.page.login");
        return page;
    }
}

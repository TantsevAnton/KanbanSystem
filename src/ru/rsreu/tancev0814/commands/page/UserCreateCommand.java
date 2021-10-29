package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

public class UserCreateCommand extends ActionCommand {

    public UserCreateCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = Resourcer.getProperty("path.page.administration");
        return page;
    }
}
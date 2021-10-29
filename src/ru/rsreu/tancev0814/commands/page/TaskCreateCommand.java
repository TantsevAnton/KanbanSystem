package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

public class TaskCreateCommand extends ActionCommand {

    public TaskCreateCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = Resourcer.getProperty("path.page.board");
        return page;
    }
}

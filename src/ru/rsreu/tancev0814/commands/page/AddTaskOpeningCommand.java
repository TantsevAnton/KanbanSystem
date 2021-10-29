package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.datalayer.data.UserGroup;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;

public class AddTaskOpeningCommand extends ActionCommand {

    public AddTaskOpeningCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        Board board = (Board) request.getSession().getAttribute("board");
        request.getSession().setAttribute("board", board);
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        List<User> workerList = userDAO.getSpecifiedGroupUsers(UserGroup.USER);
        request.getSession().setAttribute("workerList", workerList);
        String page = Resourcer.getProperty("path.page.new_task");
        return page;
    }
}

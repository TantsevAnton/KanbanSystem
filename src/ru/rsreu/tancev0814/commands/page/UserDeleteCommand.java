package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;

public class UserDeleteCommand extends ActionCommand {

    public UserDeleteCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int userID = ((User) request.getSession().getAttribute("editedUser")).getId();
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        userDAO.deleteUser(userID);
        List<User> users = userDAO.getUsers();
        request.getSession().setAttribute("users", users);
        String page = Resourcer.getProperty("path.page.administration");
        return page;
    }
}

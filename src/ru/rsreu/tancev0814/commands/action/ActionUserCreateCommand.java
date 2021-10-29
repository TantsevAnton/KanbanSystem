package ru.rsreu.tancev0814.commands.action;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.datalayer.data.UserGroup;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;

public class ActionUserCreateCommand extends ActionCommand {

    public ActionUserCreateCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userLogin = request.getParameter("userLogin");
        String password = request.getParameter("password");
        String userGroup = request.getParameter("userGroup");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        User user = new User(0, userLogin, password, lastName, firstName, UserGroup.valueOf(userGroup.toUpperCase()), false);
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        userDAO.addUser(user);
        List<User> users = userDAO.getUsers();
        request.getSession().setAttribute("users", users);
        String command = Resourcer.getProperty("command.user.create");
        return command;
    }

}

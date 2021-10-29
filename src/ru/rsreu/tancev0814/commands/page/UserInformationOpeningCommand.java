package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;


public class UserInformationOpeningCommand extends ActionCommand {

    public UserInformationOpeningCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        User userToDelete = null;
        String userLogin = request.getParameter("user_login");
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        List<User> users = userDAO.getUsers();
        for (User user: users) {
            if (user.getLogin().equals(userLogin)) {
                request.getSession().setAttribute("editedUser", user);
                userToDelete = user;
            }
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user.getId() == userToDelete.getId()) {
            request.setAttribute("disabled",  " disabled");
        }
        String page = Resourcer.getProperty("path.page.user_information");
        return page;
    }
}

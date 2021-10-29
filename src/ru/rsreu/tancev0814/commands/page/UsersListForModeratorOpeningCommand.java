package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.AuthorizedUsersList;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.datalayer.data.UserGroup;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.ArrayList;
import java.util.List;

public class UsersListForModeratorOpeningCommand extends ActionCommand {

    public UsersListForModeratorOpeningCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = new ArrayList<User>();
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        List<User> allUsers = userDAO.getUsers();
        for (User user: allUsers) {
            if (user.getUserGroup() == UserGroup.USER) {
                users.add(user);
            }
        }
        request.getSession().setAttribute("users", users);
        request.getSession().setAttribute("AuthorizedUsersList", AuthorizedUsersList.AUTHORIZED_USERS_LIST);
        String page = Resourcer.getProperty("path.page.users_list_for_moderator");
        return page;
    }

}

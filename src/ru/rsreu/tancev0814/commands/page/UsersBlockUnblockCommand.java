package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersBlockUnblockCommand extends ActionCommand {

    public UsersBlockUnblockCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = (List<User>) request.getSession().getAttribute("users");
        String[] blockedUsersID = request.getParameterValues("blockedUsersID");
        List<String> blockedUsersIDList = null;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        if (blockedUsersID == null) {
            blockedUsersIDList = new ArrayList<String>();
        } else {
            blockedUsersIDList = Arrays.asList(blockedUsersID);
        }
        for (User user: users) {
            boolean isBlocked = blockedUsersIDList.contains(Integer.toString(user.getId()));
            userDAO.blockUnblockUser(user.getId(), isBlocked);
        }
        users = userDAO.getUsers();
        request.getSession().setAttribute("users", users);
        String page = Resourcer.getProperty("path.page.main");
        return page;
    }

}

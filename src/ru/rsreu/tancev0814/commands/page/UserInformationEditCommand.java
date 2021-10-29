package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;

public class UserInformationEditCommand extends ActionCommand {

    public UserInformationEditCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int editableUserID =  ((User) request.getSession().getAttribute("editedUser")).getId();
        String newLogin = request.getParameter("userLogin");
        String newPassword = request.getParameter("password");
        String newLastName = request.getParameter("lastName");
        String newFirstName = request.getParameter("firstName");
        String newUserGroup = request.getParameter("userGroup");
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        userDAO.updateUserInformation(editableUserID, newLogin, newPassword, newLastName, newFirstName, newUserGroup);
        List<User> users = userDAO.getUsers();
        request.getSession().setAttribute("users", users);
        request.getSession().setAttribute("disabled",  "");
        String page = Resourcer.getProperty("path.page.administration");
        return page;
    }
}

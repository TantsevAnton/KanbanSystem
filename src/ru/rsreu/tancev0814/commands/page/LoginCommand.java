package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.BoardDAO;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.AuthorizedUsersList;
import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.datalayer.data.UserGroup;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;

public class LoginCommand extends ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public LoginCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        request.getSession().invalidate();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        if (checkLogin(login, pass, request)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            AuthorizedUsersList.AUTHORIZED_USERS_LIST.add(user);
            if (user.getUserGroup() == UserGroup.ADMINISTRATOR) {
                DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
                UserDAO userDAO = factory.getUserDAO();
                List<User> users = userDAO.getUsers();
                request.getSession().setAttribute("users", users);
                request.getSession().setAttribute("AuthorizedUsersList", AuthorizedUsersList.AUTHORIZED_USERS_LIST);
                page = Resourcer.getProperty("path.page.administration");
            }
            if (user.getUserGroup() == UserGroup.MODERATOR) {
                DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
                BoardDAO boardDAO = factory.getBoardDAO();
                List<Board> boards = boardDAO.getBoards();
                request.getSession().setAttribute("boards", boards);
                request.getSession().setAttribute("visibility",  "closed");
                page = Resourcer.getProperty("path.page.main");
            }
            if (user.getUserGroup() == UserGroup.USER) {
                DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
                BoardDAO boardDAO = factory.getBoardDAO();
                List<Board> userBoards = boardDAO.getUserBoards(user);
                request.getSession().setAttribute("boards", userBoards);
                page = Resourcer.getProperty("path.page.main");
            }
        } else {
            request.setAttribute("errorLoginPassMessage",
                    Resourcer.getProperty("message.loginerror"));
            page = Resourcer.getProperty("path.page.login");
        }
        return page;
    }

    public boolean checkLogin(String enterLogin, String enterPass, HttpServletRequest request) {
        boolean dataValidity = false;
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        List<User> users = userDAO.getUsers();

        for (User user : users) {
            String userLogin = user.getLogin();
            String userPassword = user.getPassword();
            if (userLogin.equals(enterLogin) && userPassword.equals(enterPass)) {
                request.getSession().setAttribute("user", user);
                dataValidity = true;
            }
        }
        return dataValidity;
    }
}

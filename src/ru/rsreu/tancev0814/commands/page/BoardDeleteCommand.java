package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.BoardDAO;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.datalayer.data.UserGroup;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;

public class BoardDeleteCommand extends ActionCommand {

    public BoardDeleteCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int boardID = Integer.parseInt(request.getParameter("board_id"));
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        BoardDAO boardDAO = factory.getBoardDAO();
        boardDAO.deleteBoard(boardID);
        User user = (User) request.getSession().getAttribute("user");
        if (user.getUserGroup() == UserGroup.MODERATOR) {
            List<Board> boards = boardDAO.getBoards();
            request.getSession().setAttribute("boards", boards);
        }
        if (user.getUserGroup() == UserGroup.USER) {
            List<Board> userBoards = boardDAO.getUserBoards(user);
            request.getSession().setAttribute("boards", userBoards);
        }
        String page = Resourcer.getProperty("path.page.main");
        return page;
    }
}

package ru.rsreu.tancev0814.commands.action;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.BoardDAO;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;

public class ActionBoardCreateCommand extends ActionCommand {

    public ActionBoardCreateCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String boardName = request.getParameter("NewBoard");
        User user = (User) request.getSession().getAttribute("user");
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        BoardDAO boardDAO = factory.getBoardDAO();
        boardDAO.addBoard(boardName, user.getId());
        List<Board> userBoards = boardDAO.getUserBoards(user);
        request.getSession().setAttribute("boards", userBoards);
        String command = Resourcer.getProperty("command.board.create");
        return command;
    }

}

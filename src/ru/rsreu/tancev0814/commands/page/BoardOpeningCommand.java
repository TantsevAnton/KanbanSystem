package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.BoardDAO;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

public class BoardOpeningCommand extends ActionCommand {

    public BoardOpeningCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int boardID = Integer.parseInt(request.getParameter("board_id"));
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        BoardDAO boardDAO = factory.getBoardDAO();
        Board board = boardDAO.getBoard(boardID);
        request.getSession().setAttribute("board", board);
        request.getSession().setAttribute("visibilityEditMenu",  " closed");
        String page = Resourcer.getProperty("path.page.board");
        return page;
    }
}

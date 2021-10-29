package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.BoardDAO;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.List;

public class BoardBlockUnblockCommand extends ActionCommand {

    public BoardBlockUnblockCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int boardID = Integer.parseInt(request.getParameter("board_id"));
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        BoardDAO boardDAO = factory.getBoardDAO();
        boardDAO.blockUnblockBoard(boardID);
        List<Board> boards = boardDAO.getBoards();
        request.getSession().setAttribute("boards", boards);
        String page = Resourcer.getProperty("path.page.main");
        return page;
    }

}

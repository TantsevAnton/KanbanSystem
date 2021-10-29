package ru.rsreu.tancev0814.commands.action;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.BoardDAO;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.TaskDAO;
import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.datalayer.data.TaskPriority;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

public class ActionTaskCreateCommand extends ActionCommand {

    public ActionTaskCreateCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String taskName = request.getParameter("taskName");
        String taskDescription = request.getParameter("taskDescription");
        String taskPriority = request.getParameter("taskPriority");
        int taskPerformerID = Integer.parseInt(request.getParameter("taskWorker"));
        int boardID = Integer.parseInt(request.getParameter("board_id"));
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        TaskDAO taskDAO = factory.getTaskDAO();
        taskDAO.addTask(taskName, taskDescription, TaskPriority.valueOf(taskPriority.toUpperCase()), taskPerformerID, boardID);
        BoardDAO boardDAO = factory.getBoardDAO();
        Board board = boardDAO.getBoard(boardID);
        request.getSession().setAttribute("board", board);
        request.getSession().setAttribute("visibilityEditMenu",  " closed");
        String command = Resourcer.getProperty("command.task.create");
        return command;
    }

}

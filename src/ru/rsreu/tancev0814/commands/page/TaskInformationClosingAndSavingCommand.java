package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.BoardDAO;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.TaskDAO;
import ru.rsreu.tancev0814.datalayer.data.*;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

public class TaskInformationClosingAndSavingCommand extends ActionCommand {

    public TaskInformationClosingAndSavingCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        Task task = (Task) request.getSession().getAttribute("editedTask");
        String newName = request.getParameter("taskName");
        String newDescription = request.getParameter("taskDescription");
        String newPriority = request.getParameter("priority");
        int newPerformerID = Integer.parseInt(request.getParameter("workerName"));
        int newStatusID = Integer.parseInt(request.getParameter("status"));
        if (task.getName() != newName || task.getDescription() != newDescription || task.getPriority().getName() != newPriority || task.getPerformer().getId() != newPerformerID || (Integer) request.getSession().getAttribute("status_id") != newStatusID) {
            DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
            TaskDAO taskDAO = factory.getTaskDAO();
            taskDAO.updateTaskInformation(task.getId(), newName, newDescription, newPriority, newPerformerID, newStatusID, ((Board) request.getSession().getAttribute("board")).getId());

            BoardDAO boardDAO = factory.getBoardDAO();
            Board board = boardDAO.getBoard(((Board) request.getSession().getAttribute("board")).getId());
            request.getSession().setAttribute("board", board);
        }
        request.getSession().setAttribute("visibilityEditMenu",  " closed");
        String page = Resourcer.getProperty("path.page.board");
        return page;
    }
}

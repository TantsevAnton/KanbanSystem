package ru.rsreu.tancev0814.commands.page;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.tancev0814.commands.ActionCommand;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.TaskDAO;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.*;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.util.ArrayList;
import java.util.List;

public class TaskInformationOpeningCommand extends ActionCommand {

    public TaskInformationOpeningCommand(boolean forward, boolean authenticationRequired) {
        super(forward, authenticationRequired);
    }

    @Override
    public String execute(HttpServletRequest request) {
        Board board = (Board) request.getSession().getAttribute("board");
        request.getSession().setAttribute("statusList", board.getBoardColumns());
        int columnID = Integer.parseInt(request.getParameter("column_id"));
        request.getSession().setAttribute("status_id", columnID);
        int taskID = Integer.parseInt(request.getParameter("task_id"));
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        TaskDAO taskDAO = factory.getTaskDAO();
        Task task = taskDAO.getTask(taskID);
        request.getSession().setAttribute("editedTask", task);
        List<TaskPriority> taskPriorities = new ArrayList<TaskPriority>();
        taskPriorities.add(TaskPriority.HIGH);
        taskPriorities.add(TaskPriority.MEDIUM);
        taskPriorities.add(TaskPriority.LOW);
        request.getSession().setAttribute("priorityList", taskPriorities);
        UserDAO userDAO = factory.getUserDAO();
        List<User> workerList = userDAO.getSpecifiedGroupUsers(UserGroup.USER);
        request.getSession().setAttribute("workerList", workerList);
        request.getSession().setAttribute("visibilityEditMenu",  "");
        String page = Resourcer.getProperty("path.page.board");
        return page;
    }
}

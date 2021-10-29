package ru.rsreu.tancev0814.datalayer;

import java.util.List;

import ru.rsreu.tancev0814.datalayer.data.Task;
import ru.rsreu.tancev0814.datalayer.data.TaskPriority;

/**
 * Task DAO.
 */
public interface TaskDAO {

    /**
     * Get tasks of the specified board column.
     * @param boardColumnID Board column ID.
     * @return Tasks list of the specified board column.
     */
    List<Task> getBoardColumnTasks(int boardColumnID);

    /**
     * Get the specified task.
     * @param taskID Task ID.
     * @return Task.
     */
    Task getTask(int taskID);

    /**
     * Add task.
     * @param taskName Task name.
     * @param taskDescription Task description.
     * @param taskPriority Task priority.
     * @param taskPerformerID Task performer ID.
     * @param boardID Board ID.
     */
    void addTask(String taskName, String taskDescription, TaskPriority taskPriority, int taskPerformerID, int boardID);

    /**
     * Update task information.
     * @param taskID Task ID.
     * @param taskName Task name.
     * @param taskDescription Task description.
     * @param taskPriority Task priority.
     * @param taskPerformerID Task performer ID.
     * @param taskStatusID Task status ID.
     * @param boardID Board ID.
     */
    void updateTaskInformation(int taskID, String taskName, String taskDescription, String taskPriority, int taskPerformerID, int taskStatusID, int boardID);

}

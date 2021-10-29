package ru.rsreu.tancev0814.datalayer.data;

import java.util.List;

/**
 * Board column
 */
public class BoardColumn {

    /**
     * ID.
     */
    private int id;

    /**
     * Name.
     */
    private String name;

    /**
     * Tasks list.
     */
    private List<Task> tasks;

    /**
     * Board column constructor.
     * @param id Board column ID.
     * @param name Board column name.
     * @param tasks Board column tasks list.
     */
    public BoardColumn(int id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    /**
     * Empty board column constructor.
     */
    public BoardColumn() {

    }

    /**
     * Get board column ID.
     * @return Board column ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set board column ID.
     * @param id Board column ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get board column name.
     * @return Board column name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set board column name.
     * @param name Board column name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get board column tasks list.
     * @return Board column tasks list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Set the tasks that are located on the board column.
     * @param tasks Tasks list.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}

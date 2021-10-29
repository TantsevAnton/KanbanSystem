package ru.rsreu.tancev0814.datalayer.data;

/**
 * Task.
 */
public class Task {

    /**
     * ID.
     */
    private int id;

    /**
     * Name.
     */
    private String name;

    /**
     * Description.
     */
    private String description;

    /**
     * Task priority.
     */
    private TaskPriority priority;

    /**
     * Task priority change.
     */
    private TaskPriorityChange priorityChange;

    /**
     * Task performer.
     */
    private User performer;

    /**
     * Task constructor.
     * @param id Task ID.
     * @param name Task name.
     * @param description Task description.
     * @param priority Task priority.
     * @param priorityChange Task priority change.
     * @param performer Task performer.
     */
    public Task(int id, String name, String description, TaskPriority priority, TaskPriorityChange priorityChange, User performer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.priorityChange = priorityChange;
        this.performer = performer;
    }

    /**
     * Empty task constructor.
     */
    public Task() {

    }

    /**
     * Get task ID.
     * @return Task ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set task ID.
     * @param id Task ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get task name.
     * @return Task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set task name.
     * @param name Task name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get task description.
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set task description.
     * @param description Task description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get task priority.
     * @return Task priority.
     */
    public TaskPriority getPriority() {
        return priority;
    }

    /**
     * Set task priority.
     * @param priority Task priority.
     */
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    /**
     * Get task priority change.
     * @return Task priority change.
     */
    public TaskPriorityChange getPriorityChange() {
        return priorityChange;
    }

    /**
     * Set task priority change.
     * @param priorityChange Task priority change.
     */
    public void setPriorityChange(TaskPriorityChange priorityChange) {
        this.priorityChange = priorityChange;
    }

    /**
     * Get task performer.
     * @return Task performer.
     */
    public User getPerformer() {
        return performer;
    }

    /**
     * Set task performer.
     * @param performer Task performer.
     */
    public void setPerformer(User performer) {
        this.performer = performer;
    }

}

package ru.rsreu.tancev0814.datalayer.data;

import java.sql.Date;
import java.util.List;

/**
 * Board.
 */
public class Board {

    /**
     * ID.
     */
    private int id;

    /**
     * Name.
     */
    private String name;

    /**
     * Creation date.
     */
    private Date creationDate;

    /**
     * Modification date.
     */
    private Date modificationDate;

    /**
     * Is the board blocked.
     */
    private boolean isBlocked;

    /**
     * Board columns list.
     */
    private List<BoardColumn> boardColumns;

    /**
     * Owner.
     */
    private User owner;

    /**
     * Board constructor.
     * @param id Board ID.
     * @param name Board name.
     * @param creationDate Board creation date.
     * @param modificationDate Board modification date.
     * @param isBlocked Is the board blocked.
     * @param boardColumns Board columns list.
     * @param owner Board owner.
     */
    public Board(int id, String name, Date creationDate, Date modificationDate, boolean isBlocked, List<BoardColumn> boardColumns, User owner) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.isBlocked = isBlocked;
        this.boardColumns = boardColumns;
        this.owner = owner;
    }

    /**
     * Empty board constructor.
     */
    public Board() {

    }

    /**
     * Get board ID.
     * @return Board ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set board ID.
     * @param id Board ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get board name.
     * @return Board name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set board name.
     * @param name Board name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get board creation date.
     * @return Board creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Set board creation date.
     * @param creationDate Board creation date.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Get board modification date.
     * @return Board modification date.
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * Set board modification date.
     * @param modificationDate Board modification date.
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * Get board columns list.
     * @return Board columns list.
     */
    public List<BoardColumn> getBoardColumns() {
        return boardColumns;
    }

    /**
     * Set the columns that are located on the board.
     * @param boardColumns Columns list.
     */
    public void setBoardColumns(List<BoardColumn> boardColumns) {
        this.boardColumns = boardColumns;
    }

    /**
     * Get board owner.
     * @return Board owner.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Set board owner.
     * @param owner Board owner.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Find out if the board is blocked.
     * @return True if the board is blocked; false otherwise.
     */
    public boolean getIsBlocked() {
        return isBlocked;
    }

    /**
     * Set whether the board is blocked.
     * @param isBlocked Is the board blocked.
     */
    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

}

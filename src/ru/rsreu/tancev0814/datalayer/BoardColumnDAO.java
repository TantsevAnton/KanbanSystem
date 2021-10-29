package ru.rsreu.tancev0814.datalayer;

import java.util.List;

import ru.rsreu.tancev0814.datalayer.data.BoardColumn;

/**
 * Board column DAO.
 */
public interface BoardColumnDAO {

    /**
     * Get columns of the specified board.
     * @param boardID Board ID.
     * @return Columns list of the specified board.
     */
    List<BoardColumn> getBoardColumns(int boardID);

}

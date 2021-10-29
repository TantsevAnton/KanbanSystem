package ru.rsreu.tancev0814.datalayer;

import java.util.List;

import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.datalayer.data.User;

/**
 * Board DAO.
 */
public interface BoardDAO {

    /**
     * Get all boards.
     * @return All boards list.
     */
    List<Board> getBoards();

    /**
     * Get the specified board.
     * @param boardID Board ID.
     * @return Board.
     */
    Board getBoard(int boardID);

    /**
     * Get boards of the specified user.
     * @param user User.
     * @return Boards list of the specified user.
     */
    List<Board> getUserBoards(User user);

    /**
     * Add board.
     * @param boardName Board name.
     * @param userID User ID.
     */
    void addBoard(String boardName, int userID);

    /**
     * Delete board.
     * @param boardID Board ID.
     */
    void deleteBoard(int boardID);

    /**
     * Block or unblock board.
     * @param boardID Board ID.
     */
    void blockUnblockBoard(int boardID);

}

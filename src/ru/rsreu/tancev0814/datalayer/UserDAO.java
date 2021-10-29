package ru.rsreu.tancev0814.datalayer;

import java.util.List;

import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.datalayer.data.UserGroup;

/**
 * User DAO.
 */
public interface UserDAO {

    /**
     * Get all users.
     * @return All users list.
     */
    List<User> getUsers();

    /**
     * Get the performer of the specified task.
     * @param taskID Task ID.
     * @return Task performer.
     */
    User getTaskPerformer(int taskID);

    /**
     * Get the owner of the specified board.
     * @param boardID Board ID.
     * @return Board Owner.
     */
    User getBoardOwner(int boardID);

    /**
     * Get specified group users.
     * @param userGroup User group.
     * @return Users list of the specified group.
     */
    List<User> getSpecifiedGroupUsers(UserGroup userGroup);

    /**
     * Add User.
     * @param user The user to add.
     */
    void addUser(User user);

    /**
     * Update user information.
     * @param userID User ID.
     * @param userLogin User login.
     * @param userPassword User password.
     * @param userLastName User last name.
     * @param userFirstName User first name.
     * @param userGroup User group.
     */
    void updateUserInformation(int userID, String userLogin, String userPassword, String userLastName, String userFirstName, String userGroup);

    /**
     * Delete user.
     * @param userID User ID.
     */
    void deleteUser(int userID);

    /**
     * Block or unblock user.
     * @param userID User ID.
     * @param isBlocked Is the user blocked.
     */
    void blockUnblockUser(int userID, boolean isBlocked);

}

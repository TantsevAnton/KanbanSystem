package ru.rsreu.tancev0814.datalayer.oracledb;

import javax.sql.DataSource;

import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.datalayer.data.UserGroup;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleUserDAO implements UserDAO {

    private DataSource dataSource;

    public OracleUserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getUsers() {
        Connection connection = null;
        List<User> result = new ArrayList<User>();
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = Resourcer.getProperty("query.get.users");
            ResultSet resultSet = statement.executeQuery(query);
            fillUsersFromResultSet(resultSet, result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, statement);
        }

        return result;
    }

    @Override
    public List<User> getSpecifiedGroupUsers(UserGroup userGroup) {
        Connection connection = null;
        List<User> result = new ArrayList<User>();
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.get.specified.group.users");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userGroup.getGroupName());
            ResultSet resultSet = preparedStatement.executeQuery();
            fillUsersFromResultSet(resultSet, result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }

        return result;
    }

    @Override
    public User getTaskPerformer(int TaskID) {
        Connection connection = null;
        User result = new User();
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.get.task.performer");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, TaskID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }

        return result;
    }

    @Override
    public User getBoardOwner(int boardID) {
        Connection connection = null;
        User result = new User();
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.get.board.owner");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }

        return result;
    }

    @Override
    public void addUser(User user) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.add.user");
            callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, user.getLogin());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setString(3, user.getLastName());
            callableStatement.setString(4, user.getFirstName());
            callableStatement.setString(5, user.getUserGroup().getGroupName());
            callableStatement.setInt(6, user.getIsBlocked()? 1 : 0);
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, callableStatement);
        }
    }

    @Override
    public void updateUserInformation(int userID, String userLogin, String userPassword, String userLastName, String userFirstName, String userGroup) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.update.user.information");
            callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, userID);
            callableStatement.setString(2, userLogin);
            callableStatement.setString(3, userPassword);
            callableStatement.setString(4, userLastName);
            callableStatement.setString(5, userFirstName);
            callableStatement.setString(6, userGroup);
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, callableStatement);
        }
    }

    @Override
    public void deleteUser(int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.delete.user");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }
    }

    @Override
    public void blockUnblockUser(int userID, boolean isBlocked) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.block.unblock.user");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, isBlocked? 1 : 0);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }
    }

    private void closeStatementAndConnection(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillUsersFromResultSet(ResultSet resultSet, List<User> users) throws SQLException {
        while (resultSet.next()) {
            User user = getUserFromResultSet(resultSet);
            users.add(user);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User result = new User();

        int id = resultSet.getInt(Resourcer.getProperty("user.id"));
        result.setId(id);

        String login = resultSet.getString(Resourcer.getProperty("user.login"));
        result.setLogin(login);

        String password = resultSet.getString(Resourcer.getProperty("user.password"));
        result.setPassword(password);

        String lastName = resultSet.getString(Resourcer.getProperty("user.last_name"));
        result.setLastName(lastName);

        String firstName = resultSet.getString(Resourcer.getProperty("user.first_name"));
        result.setFirstName(firstName);

        UserGroup userGroup = UserGroup.valueOf(resultSet.getString(Resourcer.getProperty("user.usergroup")).toUpperCase());
        result.setUserGroup(userGroup);

        boolean isBlocked = resultSet.getBoolean(Resourcer.getProperty("user.is_blocked"));
        result.setIsBlocked(isBlocked);

        return result;
    }

}

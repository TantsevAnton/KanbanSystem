package ru.rsreu.tancev0814.datalayer.oracledb;

import javax.sql.DataSource;

import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.TaskDAO;
import ru.rsreu.tancev0814.datalayer.UserDAO;
import ru.rsreu.tancev0814.datalayer.data.*;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleTaskDAO implements TaskDAO {

    private DataSource dataSource;

    public OracleTaskDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Task> getBoardColumnTasks(int boardColumnID) {
        Connection connection = null;
        List<Task> result = new ArrayList<Task>();
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.get.board.column.tasks");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardColumnID);
            ResultSet resultSet = preparedStatement.executeQuery();
            fillTasksFromResultSet(resultSet, result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }

        return result;
    }

    @Override
    public Task getTask(int taskID) {
        Connection connection = null;
        Task result = new Task();
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.get.task");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, taskID );
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = getTaskFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }

        return result;
    }

    @Override
    public void addTask(String taskName, String taskDescription, TaskPriority taskPriority, int taskPerformerID, int boardID) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.add.task");
            callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, taskName);
            callableStatement.setString(2, taskDescription);
            callableStatement.setString(3, taskPriority.getName());
            callableStatement.setInt(4, taskPerformerID);
            callableStatement.setInt(5, boardID);
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, callableStatement);
        }
    }

    @Override
    public void updateTaskInformation(int taskID, String taskName, String taskDescription, String taskPriority, int taskPerformerID, int taskStatusID, int boardID) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.update.task.information");
            callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, taskID);
            callableStatement.setString(2, taskName);
            callableStatement.setString(3, taskDescription);
            callableStatement.setString(4, taskPriority);
            callableStatement.setInt(5, taskPerformerID);
            callableStatement.setInt(6, taskStatusID);
            callableStatement.setInt(7, boardID);
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, callableStatement);
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

    private void fillTasksFromResultSet(ResultSet resultSet, List<Task> tasks) throws SQLException {
        while (resultSet.next()) {
            Task task = getTaskFromResultSet(resultSet);
            tasks.add(task);
        }
    }

    private Task getTaskFromResultSet(ResultSet resultSet) throws SQLException {
        Task result = new Task();

        int id = resultSet.getInt(Resourcer.getProperty("task.id"));
        result.setId(id);

        String name = resultSet.getString(Resourcer.getProperty("task.name"));
        result.setName(name);

        String description = resultSet.getString(Resourcer.getProperty("task.description"));
        result.setDescription(description);

        TaskPriority priority = TaskPriority.valueOf(resultSet.getString(Resourcer.getProperty("task.priority")).toUpperCase());
        result.setPriority(priority);

        TaskPriorityChange priorityChange = TaskPriorityChange.valueOf(resultSet.getString(Resourcer.getProperty("task.priority_change")).toUpperCase());
        result.setPriorityChange(priorityChange);

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        User performer = userDAO.getTaskPerformer(id);

        result.setPerformer(performer);

        return result;
    }

}

package ru.rsreu.tancev0814.datalayer.oracledb;

import javax.sql.DataSource;

import ru.rsreu.tancev0814.datalayer.BoardColumnDAO;
import ru.rsreu.tancev0814.datalayer.DAOFactory;
import ru.rsreu.tancev0814.datalayer.DBType;
import ru.rsreu.tancev0814.datalayer.TaskDAO;
import ru.rsreu.tancev0814.datalayer.data.BoardColumn;
import ru.rsreu.tancev0814.datalayer.data.Task;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleBoardColumnDAO implements BoardColumnDAO {

    private DataSource dataSource;

    public OracleBoardColumnDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<BoardColumn> getBoardColumns(int boardID) {
        Connection connection = null;
        List<BoardColumn> result = new ArrayList<BoardColumn>();
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.get.board.columns");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardID);
            ResultSet resultSet = preparedStatement.executeQuery();
            fillBoardColumnsFromResultSet(resultSet, result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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

        return result;
    }

    private void fillBoardColumnsFromResultSet(ResultSet resultSet, List<BoardColumn> boardColumns) throws SQLException {
        while (resultSet.next()) {
            BoardColumn boardColumn = getBoardColumnFromResultSet(resultSet);
            boardColumns.add(boardColumn);
        }
    }

    private BoardColumn getBoardColumnFromResultSet(ResultSet resultSet) throws SQLException {
        BoardColumn result = new BoardColumn();

        int id = resultSet.getInt(Resourcer.getProperty("boardcolumn.id"));
        result.setId(id);

        String name = resultSet.getString(Resourcer.getProperty("boardcolumn.name"));
        result.setName(name);

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        TaskDAO taskDAO = factory.getTaskDAO();
        List<Task> tasks = taskDAO.getBoardColumnTasks(id);

        result.setTasks(tasks);

        return result;
    }

}

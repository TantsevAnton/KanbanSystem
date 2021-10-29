package ru.rsreu.tancev0814.datalayer.oracledb;

import javax.sql.DataSource;

import ru.rsreu.tancev0814.datalayer.*;
import ru.rsreu.tancev0814.datalayer.data.Board;
import ru.rsreu.tancev0814.datalayer.data.BoardColumn;
import ru.rsreu.tancev0814.datalayer.data.User;
import ru.rsreu.tancev0814.resourcemanager.Resourcer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleBoardDAO implements BoardDAO {

    private DataSource dataSource;

    public OracleBoardDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Board> getBoards() {
        Connection connection = null;
        List<Board> result = new ArrayList<Board>();
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = Resourcer.getProperty("query.get.boards");
            ResultSet resultSet = statement.executeQuery(query);
            fillBoardsFromResultSet(resultSet, result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, statement);
        }

        return result;
    }

    @Override
    public Board getBoard(int boardID) {
        Connection connection = null;
        Board result = new Board();
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.get.board");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = getBoardFromResultSet(resultSet);

            DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
            BoardColumnDAO boardColumnDAO = factory.getBoardColumnDAO();
            List<BoardColumn> boardColumns = boardColumnDAO.getBoardColumns(boardID);

            result.setBoardColumns(boardColumns);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }

        return result;
    }

    @Override
    public List<Board> getUserBoards(User user) {
        Connection connection = null;
        List<Board> result = new ArrayList<Board>();
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.get.user.boards");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            fillBoardsFromResultSet(resultSet, result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }

        return result;
    }

    @Override
    public void addBoard(String boardName, int userID) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.add.board");
            callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, boardName);
            callableStatement.setInt(2, userID);
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, callableStatement);
        }
    }

    @Override
    public void deleteBoard(int boardID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.delete.board");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, preparedStatement);
        }
    }

    @Override
    public void blockUnblockBoard(int boardID) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = Resourcer.getProperty("query.query.block.unblock.block");
            callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, boardID);
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

    private void fillBoardsFromResultSet(ResultSet resultSet, List<Board> boards) throws SQLException {
        while (resultSet.next()) {
            Board board = getBoardFromResultSet(resultSet);
            boards.add(board);
        }
    }

    private Board getBoardFromResultSet(ResultSet resultSet) throws SQLException {
        Board result = new Board();

        int id = resultSet.getInt(Resourcer.getProperty("board.id"));
        result.setId(id);

        String name = resultSet.getString(Resourcer.getProperty("board.name"));
        result.setName(name);

        Date creationDate = resultSet.getDate(Resourcer.getProperty("board.creation_date"));
        result.setCreationDate(creationDate);

        Date modificationDate = resultSet.getDate(Resourcer.getProperty("board.modification_date"));
        result.setModificationDate(modificationDate);

        boolean isBlocked = resultSet.getBoolean(Resourcer.getProperty("board.is_blocked"));
        result.setIsBlocked(isBlocked);

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getBoardOwner(id);

        result.setOwner(user);

        return result;
    }

}

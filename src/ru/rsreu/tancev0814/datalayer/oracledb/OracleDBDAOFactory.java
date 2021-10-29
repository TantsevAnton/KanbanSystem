package ru.rsreu.tancev0814.datalayer.oracledb;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ru.rsreu.tancev0814.datalayer.*;

import java.util.Locale;

public class OracleDBDAOFactory extends DAOFactory {

    private static volatile OracleDBDAOFactory instance;
    private DataSource dataSource;

    private OracleDBDAOFactory() {
    }

    public static OracleDBDAOFactory getInstance() throws NamingException {
        OracleDBDAOFactory factory = instance;
        if (instance == null) {
            synchronized (OracleDBDAOFactory.class) {
                instance = factory = new OracleDBDAOFactory();
                factory.obtainDataSource();
            }
        }
        return factory;
    }

    private void obtainDataSource() throws NamingException {
        Locale.setDefault(Locale.ENGLISH);
        Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
        dataSource = (DataSource) envCtx.lookup("jdbc/KanbanBoard");
    }

    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO(dataSource);
    }

    @Override
    public BoardDAO getBoardDAO() {
        return new OracleBoardDAO(dataSource);
    }

    @Override
    public BoardColumnDAO getBoardColumnDAO() {
        return new OracleBoardColumnDAO(dataSource);
    }

    @Override
    public TaskDAO getTaskDAO() {
        return new OracleTaskDAO(dataSource);
    }
}

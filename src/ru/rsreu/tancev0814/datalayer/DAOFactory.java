package ru.rsreu.tancev0814.datalayer;

public abstract class DAOFactory {

    public static DAOFactory getInstance(DBType dbType) {
        DAOFactory result = dbType.getDAOFactory();
        return result;
    }

    public abstract UserDAO getUserDAO();

    public abstract BoardDAO getBoardDAO();

    public abstract BoardColumnDAO getBoardColumnDAO();

    public abstract TaskDAO getTaskDAO();
}

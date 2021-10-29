package ru.rsreu.tancev0814.datalayer;

import javax.naming.NamingException;

import ru.rsreu.tancev0814.datalayer.oracledb.OracleDBDAOFactory;

public enum DBType {

    ORACLE {
        @Override
        public DAOFactory getDAOFactory() {
            DAOFactory oracleDBDAOFactory = null;
            try {
                oracleDBDAOFactory = OracleDBDAOFactory.getInstance();
            } catch (NamingException e) {
                e.printStackTrace();
            }
            return oracleDBDAOFactory;
        }
    };

    public abstract DAOFactory getDAOFactory();
}

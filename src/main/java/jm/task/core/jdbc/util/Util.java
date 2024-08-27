package jm.task.core.jdbc.util;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String password = "Kilbas1309861";

    public static Connection getConnection()  {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, USER, password);
            logger.info("\nПодключение БД: Успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e + "Подключение БД: Неудача...");
        }
        return connect;
    }

}

package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "Kilbas1309861";
    private static SessionFactory sessionFactory;

    private Util() {
    }

    public static Connection getConnection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("\nПодключение БД: Успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e + "Подключение БД: Неудача...");
        }
        return connect;
    }


    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println("Создание сессии: неудача");
                e.printStackTrace();
            }
        }
        return sessionFactory;

    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, USER);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.HBM2DDL_AUTO, "none");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        configuration.setProperties(settings);
        return configuration;
    }

}

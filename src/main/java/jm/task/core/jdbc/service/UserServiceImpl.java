package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private UserDao userFunc = new UserDaoJDBCImpl();

    public UserServiceImpl() throws SQLException {

    }

    public void createUsersTable() {
        userFunc.createUsersTable();
        logger.info("Таблица в БД: Создана!");
    }

    public void dropUsersTable(){
        userFunc.dropUsersTable();
        logger.info("Удаление таблицы: Успех!");
    }

    public void saveUser(String name, String lastName, byte age) {
        userFunc.saveUser(name,lastName,age);
        logger.info("User с именем " + name + " добавлен БД");

    }

    public void removeUserById(long id) {
        userFunc.removeUserById(id);

    }

    public List<User> getAllUsers() {

        return userFunc.getAllUsers();
    }

    public void cleanUsersTable() {
        userFunc.cleanUsersTable();
        logger.info("Очистка таблици успех !");
    }
}


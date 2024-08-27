package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();


        userService.createUsersTable();


        userService.saveUser("Name1", "lastName1", (byte) 10);
        userService.saveUser("Name2", "lastName1", (byte) 20);
        userService.saveUser("Name3", "lastName3", (byte) 30);
        userService.saveUser("Name4", "lastName4", (byte) 40);
        userService.saveUser("Name5", "lastName5", (byte) 50);
        userService.removeUserById(1);
        System.out.println(userService.getAllUsers());

        for (User user : userService.getAllUsers()) {
            System.out.println(user.getName() + " " + user.getLastName() + " - " + user.getAge() + " лет");
        }
        userService.cleanUsersTable();      // Удаление всех юзеров
        userService.dropUsersTable();       // Удаление таблицы
    }

    // реализуйте алгоритм здесь
}



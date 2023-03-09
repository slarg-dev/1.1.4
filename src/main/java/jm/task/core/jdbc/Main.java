package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        final UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 30);
        userService.saveUser("Petr", "Ivanov", (byte) 18);
        userService.saveUser("John", "Smith", (byte) 46);
        userService.saveUser("Harry", "Potter", (byte) 52);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}


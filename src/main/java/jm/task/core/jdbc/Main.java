package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Sergey", "Baydeb", (byte) 78);
        userService.saveUser("Tramp", "Duck", (byte) 74);
        userService.saveUser("Donald", "Obama", (byte) 59);
        userService.saveUser("Vladimir", "PutIN", (byte) 74);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}

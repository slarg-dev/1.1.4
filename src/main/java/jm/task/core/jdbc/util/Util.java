package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
        private static Connection conn = null;
        private static Util instance = null;
        private static String url = "url";
        private static String password = "password";
        private static String name = "name";

        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(url, name, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }}



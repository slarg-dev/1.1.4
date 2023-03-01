package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
        private static Connection conn = null;
        private static Util instance = null;
        private static String url = "url";
        private static String password = "pswrd";
        private static String name = "name";

        private Util() {
            try {
                if (null == conn || conn.isClosed()) {
                    conn = DriverManager.getConnection(url, name, password);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static Util getInstance() {
            if (null == instance) {
                instance = new Util();
            }
            return instance;
        }

        public Connection getConnection() {
            return conn;
        }
        }


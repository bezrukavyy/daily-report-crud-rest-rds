package io.infomate.service.exemplar.daily;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionManager {

    private static MySqlConnectionManager instance;     // singleton

    private Connection connection;
    private final String mySqlDriverName = "com.mysql.cj.jdbc.Driver";

    private MySqlConnectionManager(DailyServiceConfig config) {
        try {
            String jdbcUrl = "jdbc:mysql://" + config.getHostname() + ":" + config.getPort() + "/" +
                    config.getDatabase() + "?user=" + config.getUsername() + "&password=" + config.getPassword();
            Class.forName(this.mySqlDriverName);
            this.connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException | ClassNotFoundException dbConnectionException) {
            throw new RuntimeException(dbConnectionException);
        }
    }

    public static MySqlConnectionManager getInstance(DailyServiceConfig config) {

        if (instance == null) {
            instance = new MySqlConnectionManager(config);
        }

        return instance;
    }

    public static MySqlConnectionManager getInstance() {

        if (instance == null) {
            throw new IllegalStateException("Connection Manager has not been initialized yet");
        }

        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}

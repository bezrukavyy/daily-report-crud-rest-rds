package io.infomate.service.exemplar.daily;

import lombok.Data;

import java.util.Map;

@Data
public class DailyServiceConfig {

    public static final String RDS_HOSTNAME = "RDS_HOSTNAME";
    public static final String RDS_PORT = "RDS_PORT";
    public static final String RDS_USERNAME = "RDS_USERNAME";
    public static final String RDS_PASSWORD = "RDS_PASSWORD";
    public static final String RDS_DATABASE = "RDS_DATABASE";

    private String hostname;
    private String port;
    private String username;
    private String password;
    private String database;

    public DailyServiceConfig(Map<String, String> env) {
        this.hostname = getAndAssertNotEmpty(env, RDS_HOSTNAME);
        this.port = getAndAssertNotEmpty(env, RDS_PORT);
        this.username = getAndAssertNotEmpty(env, RDS_USERNAME);
        this.password = getAndAssertNotEmpty(env, RDS_PASSWORD);
        this.database = getAndAssertNotEmpty(env, RDS_DATABASE);
    }

    private String getAndAssertNotEmpty(Map<String, String> env, String key) {

        String value = env.get(key);
        if (value == null || value.trim().equals("")) {
            throw new IllegalArgumentException("Environment does not contain mandatory variable " + key);
        }

        return value;
    }
}

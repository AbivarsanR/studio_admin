package org.example.studio_admin.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDb {

    private static final String URL = buildUrl();

    private static final String USER = env("DB_USER", "root");

    private static final String PASSWORD = env("DB_PASSWORD", "Train_no9651");


    private static String buildUrl() {

        String fullUrl = System.getenv("DB_URL");

        if (fullUrl != null && !fullUrl.isBlank()) {
            return fullUrl;
        }

        String host = env("DB_HOST", "localhost");
        String port = env("DB_PORT", "3306");
        String name = env("DB_NAME", "studio_admin");

        // connectionTimeZone=UTC + forceConnectionTimeZoneToSession makes
        // CURRENT_TIMESTAMP default columns store UTC (same as SQLite did).
        return "jdbc:mysql://" + host + ":" + port + "/" + name +
                "?useUnicode=true" +
                "&characterEncoding=UTF-8" +
                "&connectionTimeZone=UTC" +
                "&forceConnectionTimeZoneToSession=true" +
                "&allowPublicKeyRetrieval=true" +
                "&useSSL=false";
    }


    private static String env(String key, String defaultValue) {

        String value = System.getenv(key);

        return (value == null || value.isBlank())
                ? defaultValue
                : value;
    }


    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

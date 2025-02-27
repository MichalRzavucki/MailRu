package service;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Класс, отвечающий за инициализацию подключения к базе данных
 */
public class ConnectionInitializer {
    protected static final String URL = "jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'jdbc-example/src/main/resources/queries.sql';";
    protected static final String USER = "sa";
    protected static final String PASSWORD = "";

    // Connection Pool для базы данных
    protected static DataSource createDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        return ds;
    }
}

package sys.patrimonio.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private final Properties properties = new Properties();

    public ConnectionFactory() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database/database.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possivel carregar database.properties"); // TODO - criar exceptions para esse tipo
        }
    }


    public Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }

}


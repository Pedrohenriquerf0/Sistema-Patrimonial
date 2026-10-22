package sys.patrimonio.config;

import sys.patrimonio.exceptions.ConfigurationInicializacaoException;

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
            throw new ConfigurationInicializacaoException("Não foi possivel carregar database.properties", e);
        } catch (NullPointerException e){
           throw new ConfigurationInicializacaoException("Não foi possivel achar arquivo", e);
        }
    }


    public Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }

}


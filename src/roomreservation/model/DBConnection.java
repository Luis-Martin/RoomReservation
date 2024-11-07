/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roomreservation.model;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author luism
 */
public class DBConnection {
    // Cargar variables de entorno desde el archivo .env
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL_DB = dotenv.get("DB_URL");
    private static final String USER_DB = dotenv.get("DB_USER");
    private static final String PASS_DB = dotenv.get("DB_PASS");

    public static Connection conect() {
        Connection connection = null;
        try {
            if (URL_DB == null || USER_DB == null || PASS_DB == null) {
                throw new IllegalStateException("Missing required environment variables: DB_URL, DB_USER, or DB_PASS");
            }
            connection = DriverManager.getConnection(URL_DB, USER_DB, PASS_DB);
            System.out.println("Connection Successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }
}

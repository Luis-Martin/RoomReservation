/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roomreservation.controller;

import roomreservation.model.User;
import roomreservation.model.DBConnection;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author luism
 */
public class UserController {
    private Connection connection;

    // Constructor que recibe la conexión
    public UserController() {
        this.connection = DBConnection.conect(); // Establece la conexión al inicializar el controlador
    }

    // Crear usuario
    public boolean createUser(User user) {
        String query = "INSERT INTO Usuario (nombre, email, telefono, contraseña, rol) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Encriptar la contraseña con SHA-256 antes de insertarla
            String hashedPassword = hashPassword(user.getPassword());
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, hashedPassword); // Almacenar la contraseña encriptada
            stmt.setString(5, user.getRole());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer usuario por ID
    public User getUserById(int userId) {
        String query = "SELECT * FROM Usuario WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("contraseña"),
                    rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leer usuario por correo electrónico
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM Usuario WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("contraseña"),
                    rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Verificar inicio de sesión
    public boolean verifyLogin(String email, String password) {
        User user = getUserByEmail(email); // Buscar al usuario por correo electrónico
        if (user != null) {
            // Compara la contraseña proporcionada con la contraseña encriptada almacenada en la base de datos
            if (user.getPassword().equals(hashPassword(password))) {
                return true; // Login exitoso
            }
        }
        return false; // Login fallido
    }
    
    // Actualizar usuario
    public boolean updateUser(User user) {
        String query = "UPDATE Usuario SET nombre = ?, email = ?, telefono = ?, contraseña = ?, rol = ? WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Encriptar la contraseña antes de actualizarla
            String hashedPassword = hashPassword(user.getPassword());
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, hashedPassword); // Almacenar la nueva contraseña encriptada
            stmt.setString(5, user.getRole());
            stmt.setInt(6, user.getUserId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar usuario
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM Usuario WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     // Método para encriptar la contraseña usando SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes()); // Genera el hash
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b)); // Convierte el hash a hexadecimal
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // En caso de error en el algoritmo
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roomreservation.controller;

import roomreservation.model.Hall;
import roomreservation.model.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luism
 */
public class HallController {
    private Connection connection;

    // Constructor que recibe la conexión
    public HallController() {
        this.connection = DBConnection.conect(); // Establece la conexión al inicializar el controlador
    }

    // Crear sala
    public boolean createHall(Hall hall) {
        String query = "INSERT INTO Auditorio (nombre, capacidad_max, precio_hora) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hall.getName());
            stmt.setInt(2, hall.getMaxCapacity());
            stmt.setDouble(3, hall.getPricePerHour());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer sala por ID
    public Hall getHallById(int hallId) {
        String query = "SELECT * FROM Auditorio WHERE auditorio_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hallId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Hall(
                    rs.getInt("auditorio_id"),
                    rs.getString("nombre"),
                    rs.getInt("capacidad_max"),
                    rs.getDouble("precio_hora") 
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leer todas las salas
    public List<Hall> getAllHalls() {
        List<Hall> halls = new ArrayList<>();
        String query = "SELECT * FROM Auditorio";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                halls.add(new Hall(
                    rs.getInt("auditorio_id"),
                    rs.getString("nombre"),
                    rs.getInt("capacidad_max"),
                    rs.getDouble("precio_hora")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return halls;
    }

    // Actualizar sala
    public boolean updateHall(Hall hall) {
        String query = "UPDATE Auditorio SET nombre = ?, capacidad_max = ?, precio_hora = ? WHERE auditorio_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hall.getName());
            stmt.setInt(2, hall.getMaxCapacity());
            stmt.setDouble(3, hall.getPricePerHour());
            stmt.setInt(4, hall.getHallId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar sala
    public boolean deleteHall(int hallId) {
        String query = "DELETE FROM Auditorio WHERE auditorio_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hallId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

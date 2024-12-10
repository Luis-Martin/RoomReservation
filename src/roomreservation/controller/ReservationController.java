package roomreservation.controller;

import roomreservation.model.Reservation;
import roomreservation.model.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luism
 */
public class ReservationController {
    private Connection connection;

    // Constructor que recibe la conexión
    public ReservationController() {
        this.connection = DBConnection.conect(); // Establece la conexión al inicializar el controlador
    }

    // Crear reserva
    public boolean createReservation(Reservation reservation) {
        String query = "INSERT INTO Reserva (usuario_id, auditorio_id, fecha_inicio, fecha_fin, fecha_creacion) " +
                       "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getHallId());
            stmt.setTimestamp(3, new java.sql.Timestamp(reservation.getStartDate().getTime()));
            stmt.setTimestamp(4, new java.sql.Timestamp(reservation.getEndDate().getTime()));
            stmt.setTimestamp(5, new java.sql.Timestamp(reservation.getCreationDate().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer reserva por ID
    public Reservation getReservationById(int reservationId) {
        String query = "SELECT * FROM Reserva WHERE reserva_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("reserva_id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("auditorio_id"),
                        rs.getTimestamp("fecha_inicio"),
                        rs.getTimestamp("fecha_fin"),
                        rs.getTimestamp("fecha_creacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leer todas las reservas
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reserva ORDER BY fecha_inicio ASC";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reserva_id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("auditorio_id"),
                        rs.getTimestamp("fecha_inicio"),
                        rs.getTimestamp("fecha_fin"),
                        rs.getTimestamp("fecha_creacion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Obtener todas las reservas de un día específico
    public List<Reservation> getAllReservationsByDay(java.util.Date day) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reserva WHERE DATE(fecha_inicio) = ? ORDER BY fecha_inicio ASC"; // Filtrar por la fecha de inicio

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(day.getTime())); // Establecer la fecha como parámetro
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reserva_id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("auditorio_id"),
                        rs.getTimestamp("fecha_inicio"),
                        rs.getTimestamp("fecha_fin"),
                        rs.getTimestamp("fecha_creacion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
    
    // Obtener todas las reservas de un usuario específico
    public List<Reservation> getAllReservationsByUser(int userId) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reserva WHERE usuario_id = ? ORDER BY fecha_inicio ASC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId); // Establecer el ID del usuario como parámetro
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reserva_id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("auditorio_id"),
                        rs.getTimestamp("fecha_inicio"),
                        rs.getTimestamp("fecha_fin"),
                        rs.getTimestamp("fecha_creacion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
    
    // Obtener reserva por sala y fecha de inicio específica
    public Reservation getReservationByHallAndTime(int hallId, java.util.Date specificTime) {
        String query = "SELECT * FROM Reserva WHERE auditorio_id = ? "
                + "AND DATE(fecha_inicio) = DATE(?) "
                + "AND TIME(STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'))";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hallId); // Establecer el ID de la sala
            stmt.setTimestamp(2, new java.sql.Timestamp(specificTime.getTime())); // Establecer la fecha específica
            stmt.setTimestamp(3, new java.sql.Timestamp(specificTime.getTime())); // Establecer la hora específica

            ResultSet rs = stmt.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("reserva_id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("auditorio_id"),
                        rs.getTimestamp("fecha_inicio"),
                        rs.getTimestamp("fecha_fin"),
                        rs.getTimestamp("fecha_creacion")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error en la consulta SQL: " + e.getMessage());
            e.printStackTrace(); // Manejo de errores
        }
        return null; // Devuelve null si no hay coincidencias
    }
    
    // Actualizar reserva
    public boolean updateReservation(Reservation reservation) {
        String query = "UPDATE Reserva SET usuario_id = ?, auditorio_id = ?, " +
                       "fecha_inicio = ?, fecha_fin = ?, fecha_creacion = ? WHERE reserva_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getHallId());
            stmt.setTimestamp(3, new java.sql.Timestamp(reservation.getStartDate().getTime()));
            stmt.setTimestamp(4, new java.sql.Timestamp(reservation.getEndDate().getTime()));
            stmt.setTimestamp(5, new java.sql.Timestamp(reservation.getCreationDate().getTime()));
            stmt.setInt(6, reservation.getReservationId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar reserva
    public boolean deleteReservation(int reservationId) {
        String query = "DELETE FROM Reserva WHERE reserva_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

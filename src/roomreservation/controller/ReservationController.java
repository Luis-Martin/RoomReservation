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
        String query = "INSERT INTO Reserva (usuario_id, auditorio_id, fecha_reserva, hora_inicio, hora_fin, fecha_creacion) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getHallId());
            stmt.setDate(3, new java.sql.Date(reservation.getReservationDate().getTime()));
            stmt.setTime(4, new java.sql.Time(reservation.getStartTime().getTime()));
            stmt.setTime(5, new java.sql.Time(reservation.getEndTime().getTime()));
            stmt.setDate(6, new java.sql.Date(reservation.getCreationDate().getTime()));
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
                        rs.getDate("fecha_reserva"),
                        rs.getTime("hora_inicio"),
                        rs.getTime("hora_fin"),
                        rs.getDate("fecha_creacion")
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
        String query = "SELECT * FROM Reserva";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reserva_id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("auditorio_id"),
                        rs.getDate("fecha_reserva"),
                        rs.getTime("hora_inicio"),
                        rs.getTime("hora_fin"),
                        rs.getDate("fecha_creacion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Actualizar reserva
    public boolean updateReservation(Reservation reservation) {
        String query = "UPDATE Reserva SET usuario_id = ?, auditorio_id = ?, fecha_reserva = ?, " +
                       "hora_inicio = ?, hora_fin = ?, fecha_creacion = ? WHERE reserva_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getHallId());
            stmt.setDate(3, new java.sql.Date(reservation.getReservationDate().getTime()));
            stmt.setTime(4, new java.sql.Time(reservation.getStartTime().getTime()));
            stmt.setTime(5, new java.sql.Time(reservation.getEndTime().getTime()));
            stmt.setDate(6, new java.sql.Date(reservation.getCreationDate().getTime()));
            stmt.setInt(7, reservation.getReservationId());
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

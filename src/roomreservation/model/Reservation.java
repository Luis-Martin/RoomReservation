/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roomreservation.model;

import java.util.Date;

/**
 *
 * @author luism
 */
public class Reservation {
    private int reservationId; // Equivalente a reserva_id
    private int userId; // Equivalente a usuario_id
    private int hallId; // Equivalente a auditorio_id
    private Date reservationDate; // Equivalente a fecha_reserva
    private Date startTime; // Equivalente a hora_inicio
    private Date endTime; // Equivalente a hora_fin
    private Date creationDate; // Equivalente a fecha_creacion

    // Constructor vacío
    public Reservation() {
    }

    // Constructor con parámetros
    public Reservation(int reservationId, int userId, int hallId, Date reservationDate, Date startTime, Date endTime, Date creationDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.hallId = hallId;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creationDate = creationDate;
    }

    // Getters y Setters
    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHallId() {
        return hallId;
    }
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    // Método toString para imprimir el objeto Reservation
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", hallId=" + hallId +
                ", reservationDate=" + reservationDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", creationDate=" + creationDate +
                '}';
    }
}

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
    private Date startTime; // Equivalente a hora_inicio
    private Date endTime; // Equivalente a hora_fin
    private Date creationDate; // Equivalente a fecha_creacion

    // Constructor vacío
    public Reservation() {
    }

    // Constructor con parámetros
    public Reservation(int reservationId, int userId, int hallId, Date startTime, Date endTime, Date creationDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creationDate = creationDate;
    }

    public Reservation(int userId, int hallId, Date startDate, Date endDate, Date creationDate) {
        this.userId = userId;
        this.hallId = hallId;
        this.startTime = startDate;
        this.endTime = endDate;
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

    public Date getStartDate() {
        return startTime;
    }
    public void setStartDate(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endTime;
    }
    public void setEndDate(Date endTime) {
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
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", creationDate=" + creationDate +
                '}';
    }
}

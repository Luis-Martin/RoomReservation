/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roomreservation.model;

/**
 *
 * @author luism
 */
public class Hall {
    private int hallId; // Equivalente a auditorio_id
    private String name; // Equivalente a nombre
    private int maxCapacity; // Equivalente a capacidad_max
    private double pricePerHour; // Equivalente a precio_hora
    private boolean isReserved; // Equivalente a reservado (tinyint)

    // Constructor vacío
    public Hall() {
    }

    // Constructor con parámetros
    public Hall(int hallId, String name, int maxCapacity, double pricePerHour, boolean isReserved) {
        this.hallId = hallId;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.pricePerHour = pricePerHour;
        this.isReserved = isReserved;
    }

    // Getters y Setters
    public int getHallId() {
        return hallId;
    }
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    // Método toString para imprimir el objeto Hall
    @Override
    public String toString() {
        return "Hall{" +
                "hallId=" + hallId +
                ", name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", pricePerHour=" + pricePerHour +
                '}';
    }
}

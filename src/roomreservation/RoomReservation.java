/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package roomreservation;

import roomreservation.model.DBConnection;
import roomreservation.model.User;
import roomreservation.views.LoginJFrame;

/**
 *
 * @author luism
 */
public class RoomReservation {
    public static User loggedInUser;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Conectar con la base de datos
        DBConnection dbc = new DBConnection();
        dbc.conect();
        
        // Inicializar la ventana de login
        new LoginJFrame().setVisible(true);
    }
    
}

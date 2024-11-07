/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package roomreservation;

import roomreservation.model.DBConnection;
import roomreservation.views.LoginJFrame;

/**
 *
 * @author luism
 */
public class RoomReservation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        dbc.conect();
        // TODO code application logic here
        new LoginJFrame();
    }
    
}

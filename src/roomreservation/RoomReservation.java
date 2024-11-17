/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package roomreservation;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
        // Establecer el Look and Feel antes de iniciar la aplicación
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) { // Cambia "Nimbus" por el Look and Feel que prefieras
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Error configurando el Look and Feel: " + ex.getMessage());
        }
        
        // Conectar con la base de datos
        DBConnection dbc = new DBConnection();
        dbc.conect();
        
        // Inicializar la ventana de login
        new LoginJFrame().setVisible(true);
    }
    
}

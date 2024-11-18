/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package roomreservation.views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import roomreservation.components.MenuBar;
import roomreservation.RoomReservation;

/**
 *
 * @author USUARIO
 */
public class ProfileJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MiCuenta
     */
    Color mColorFondo = Color.WHITE; // Fondo blanco
    
    // Nuevos componentes
    private JLabel lblTitulo, lblNombre, lblCorreo, lblContrasena;
    private JTextField txtNombre, txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnCerrarSesion, btnCambiarContrasena, btnVolver;

    public ProfileJFrame() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Perfil");
        
        // Usar la clase MenuBar para agregar el JMenuBar
        MenuBar menuBar = new MenuBar(this);  // Pasamos 'this' para que el menú conozca el JFrame actual
        setJMenuBar(menuBar.getMenuBar());  // Configura el JMenuBar en el JFrame

        // Llenar los campos automáticamente con los datos del usuario logueado
        if (RoomReservation.loggedInUser != null) {
            txtNombre.setText(RoomReservation.loggedInUser.getName());
            txtCorreo.setText(RoomReservation.loggedInUser.getEmail());
            txtContrasena.setText("******"); // Por seguridad, muestra solo un placeholder
        } else {
            System.out.println("No hay un usuario logueado.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mbMenu = new javax.swing.JMenuBar();

        // Nuevos componentes
        lblTitulo = new JLabel("Mi cuenta");
        lblNombre = new JLabel("Nombre:");
        lblCorreo = new JLabel("Correo:");
        lblContrasena = new JLabel("Contraseña:");
        txtNombre = new JTextField();
        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCambiarContrasena = new JButton("Cambiar Contraseña");
        btnVolver = new JButton("Volver");

        // Personalización de componentes
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 20));
        lblCorreo.setFont(new Font("Arial", Font.PLAIN, 20));
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 20));
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 18));
        txtCorreo.setFont(new Font("Arial", Font.PLAIN, 18));
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 18));
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 16));
        btnCambiarContrasena.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));

        // Deshabilitar campos para evitar edición
        txtNombre.setEditable(false);
        txtCorreo.setEditable(false);
        txtContrasena.setEditable(false);

        // Fondo blanco para el panel principal
        jPanel1.setBackground(mColorFondo);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Diseño de jPanel1
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lblTitulo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombre)
                            .addComponent(lblCorreo)
                            .addComponent(lblContrasena))
                        .addGap(20)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContrasena, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCerrarSesion, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(btnCambiarContrasena, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createSequentialGroup()
                .addGap(50)
                .addComponent(lblTitulo)
                .addGap(40)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasena)
                    .addComponent(txtContrasena, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(50)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrarSesion, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarContrasena, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                .addGap(50)
        );

        setJMenuBar(mbMenu);

        // Añadir panel al layout principal
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProfileJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfileJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfileJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfileJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ProfileJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mbMenu;
    // End of variables declaration                   
}

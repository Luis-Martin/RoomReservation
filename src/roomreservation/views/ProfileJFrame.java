/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package roomreservation.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import roomreservation.components.MenuBar;
import roomreservation.RoomReservation;
import roomreservation.controller.UserController;
import roomreservation.model.User;
/**
 *
 * @author USUARIO
 */
public class ProfileJFrame extends javax.swing.JFrame {
    public ProfileJFrame() {
        System.out.println(RoomReservation.loggedInUser);
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Perfil");
        
        // Usar la clase MenuBar para agregar el JMenuBar
        MenuBar menuBar = new MenuBar(this);  // Pasamos 'this' para que el menú conozca el JFrame actual
        setJMenuBar(menuBar.getMenuBar());  // Configura el JMenuBar en el JFrame

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        // Título
        JLabel titleLabel = new JLabel("Mi cuenta");
        titleLabel.setFont(new Font("Andale Mono", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new java.awt.Insets(0, 0, 40, 0); // Márgenes
        panel.add(titleLabel, constraints);
        
        // Etiqueta de "Nombre"
        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, constraints);

        // Campo de texto de nombre
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(273, 38));
        nameField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        nameField.setForeground(Color.BLACK);
        nameField.setText(RoomReservation.loggedInUser.getName());
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 40);
        panel.add(nameField, constraints);
        
        // Etiqueta de "Correo"
        JLabel emailLabel = new JLabel("Correo:");
        emailLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(emailLabel, constraints);

        // Campo de texto de correo
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(273, 38));
        emailField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        emailField.setForeground(Color.BLACK);
        emailField.setText(RoomReservation.loggedInUser.getEmail());
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 40);
        panel.add(emailField, constraints);
        
        // Etiqueta de "Celular"
        JLabel phoneLabel = new JLabel("Celuar:");
        phoneLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(phoneLabel, constraints);

        // Campo de texto de celular
        JTextField phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(273, 38));
        phoneField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        phoneField.setForeground(Color.BLACK);
        phoneField.setText(RoomReservation.loggedInUser.getPhone());
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 10, 40);
        panel.add(phoneField, constraints);
        
        // Etiqueta de "Celular"
        JLabel newPasswordLabel = new JLabel("Nueva contraseña:");
        newPasswordLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(newPasswordLabel, constraints);

        // Campo de texto de celular
        JTextField newPasswordField = new JTextField();
        newPasswordField.setPreferredSize(new Dimension(273, 38));
        newPasswordField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        newPasswordField.setForeground(Color.BLACK);
        newPasswordField.setText("");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 0, 10, 40);
        panel.add(newPasswordField, constraints);
        
        // Crear boton "Actualizar Información"
        JButton updateButton = new JButton("Actualizar Información");
        updateButton.setPreferredSize(new Dimension(273, 38));
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        updateButton.setBackground(Color.decode("#040404"));
        updateButton.setForeground(Color.WHITE);
        
        UserController userController = new UserController();
        updateButton.addActionListener((ActionEvent e) -> {
            // Acciones para actualizar información
            String updatedName = nameField.getText();
            String updatedEmail = emailField.getText();
            String updatedPhone = phoneField.getText();
            String updatedPassword = newPasswordField.getText();
            
            
            // Actualizar la información del usuario
            User updatedUser = new User(
                    RoomReservation.loggedInUser.getUserId(),
                    updatedName,
                    updatedEmail,
                    updatedPhone,
                    updatedPassword,
                    RoomReservation.loggedInUser.getRole()
            );
            
            userController.updateUser(updatedUser);
            JOptionPane.showMessageDialog(null, "Información actualizadaluis con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            RoomReservation.loggedInUser = updatedUser;
            
            // Redirect Home
            new HomeJFrame().setVisible(true); 
            dispose(); // Cierra el JFrame actual
        });

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 40);
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(updateButton, constraints);
        
        // Configurar el panel principal en el JFrame
        setContentPane(panel); // Cambia el contenido del JFrame
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mbMenu = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );

        setJMenuBar(mbMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfileJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mbMenu;
    // End of variables declaration//GEN-END:variables
}

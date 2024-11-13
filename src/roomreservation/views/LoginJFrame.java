/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package roomreservation.views;

import roomreservation.model.User;
import roomreservation.controller.UserController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author luism
 */
public class LoginJFrame extends javax.swing.JFrame {

    // Variables
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel welcomeLabel, descriptionLabel, loginTitleLabel;
    private JButton loginButton;
    UserController userController = new UserController();
    
    /**
     * Creates new form LoginJFrame
     */
    public LoginJFrame() {
        // initComponents();
        setTitle("Login");

        // creacion de ventana
        JPanel panel = new JPanel(); 
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints;
         // Establecer el color de fondo verde limón
        panel.setBackground(new Color(0xFFFFFF));  // Usando hexadecimal


        // Crear el primer JLabel para "Bienvenido a "
        welcomeLabel = new JLabel("Bienvenido a ");
        welcomeLabel.setFont(new Font("Andale Mono", Font.BOLD, 35));
        welcomeLabel.setForeground(Color.decode("#000000"));
        welcomeLabel.setOpaque(true);  // Habilitar fondo opaco
        welcomeLabel.setBackground(Color.WHITE); 
       
       // Crear el segundo JLabel solo para "roomc"
        JLabel roomcLabel = new JLabel("roomc");
        roomcLabel.setFont(new Font("Andale Mono", Font.BOLD, 35));
        roomcLabel.setForeground(Color.decode("#1D6A46"));
        roomcLabel.setOpaque(true);  // Habilitar fondo opaco
        roomcLabel.setBackground(Color.WHITE);  // Establecer el fondo como blanco
        
        // Crear un JPanel con FlowLayout para poner ambos JLabel en una sola línea
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelPanel.add(welcomeLabel);
        labelPanel.add(roomcLabel);
        
       // Configurar los constraints y agregar el panel al layout principal
       constraints = new GridBagConstraints();
       constraints.gridx = 0;
       constraints.gridy = 0;
       constraints.insets = new Insets(10, 0, 20, 0);
       panel.add(labelPanel, constraints);
        
        // Descripción
        descriptionLabel = new JLabel("Reserva el auditorio ideal para tu evento");
        descriptionLabel.setFont(new Font("Andale Mono", 1, 15));
        descriptionLabel.setForeground(Color.decode("#000000"));
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 40, 0);
        panel.add(descriptionLabel, constraints);
        
        // Título secundario "Iniciar sesión"
        loginTitleLabel = new JLabel("Iniciar sesión");
        loginTitleLabel.setFont(new Font("Andale Mono", 1, 30));
        loginTitleLabel.setForeground(Color.decode("#000000"));
        loginTitleLabel.setBounds(120, 20, 200, 40); // Ajustar según diseño
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(loginTitleLabel, constraints);
             
        // Campo de texto de correo
        emailField = new JTextField("Correo");
        emailField.setPreferredSize(new Dimension(273, 38));
        emailField.setFont(new Font("Andale Mono", 1, 13));
        emailField.setForeground(Color.GRAY); // Color inicial del placeholder
        emailField.setText("Correo");
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("Correo")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setForeground(Color.GRAY);
                    emailField.setText("Correo");
                }
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(emailField, constraints);

        // Campo de contraseña
        passwordField = new JPasswordField("Contrase;a");
        passwordField.setPreferredSize(new Dimension(273, 38));
        passwordField.setFont(new Font("Andale Mono", 1, 13));
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0); // Mostrar texto como placeholder
        passwordField.setText("Contraseña");
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Contraseña")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('●'); // Cambiar a caracteres ocultos
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("Contraseña");
                    passwordField.setEchoChar((char) 0); // Mostrar placeholder
                }
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(passwordField, constraints);
        
        // Botón de inicio de sesión
        loginButton = new JButton("Iniciar Sesión");
        loginButton.setPreferredSize(new Dimension(273, 38));
        loginButton.setFont(new Font("Andale Mono", 1, 15));
        loginButton.setBackground(Color.decode("#040404"));
        loginButton.setForeground(Color.WHITE);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.insets = new Insets(20, 0, 20, 0);
        panel.add(loginButton, constraints);
        // Agrega el ActionListener para manejar el inicio de sesión
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            // Verificar el usuario y la contraseña utilizando el nuevo método 'verifyLogin'
            boolean loginSuccess = userController.verifyLogin(email, password);
            if (loginSuccess) {
                System.out.println("Login exitoso");
            } else {
                System.out.println("Login fallido");
            }
        });
        
        // Redirección a SingUp
        JLabel signUpLabel = new JLabel("¿No tienes cuenta? Regístrate");
        signUpLabel.setFont(new Font("Andale Mono", Font.PLAIN, 18));
        signUpLabel.setForeground(Color.GRAY);
        signUpLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Redireccionar a SignUpJFrame
                new SignUpJFrame().setVisible(true);
                dispose(); // Cerrar la ventana actual
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(signUpLabel, constraints);

        // Agregamos el Panel el JFrame y configuraciones
        add(panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

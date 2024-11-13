/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package roomreservation.views;

import roomreservation.model.User;
import roomreservation.controller.UserController;

import java.awt.Color;
import java.awt.Dimension;
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

/**
 *
 * @author luism
 */
public class SignUpJFrame extends javax.swing.JFrame {

    // Variables
    private JTextField emailField, nameField, phoneField;
    private JPasswordField passwordField;
    private JLabel welcomeLabel, descriptionLabel, loginTitleLabel;
    private JButton signUpButton;
    UserController userController = new UserController();
    
    /**
     * Creates new form SignUpJFrame
     */
    public SignUpJFrame() {
        // initComponents();
        setTitle("SignUp");

        // creacion de ventana
        JPanel panel = new JPanel(); 
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints;

        // Título principal
        welcomeLabel = new JLabel("Bienvenido a roomc");
        welcomeLabel.setFont(new Font("Andale Mono", 1, 55));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 20, 0);
        panel.add(welcomeLabel, constraints);
        
        // Descripción
        descriptionLabel = new JLabel("Reserva el auditorio ideal para tu evento");
        descriptionLabel.setFont(new Font("Andale Mono", 1, 20));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 80, 0);
        panel.add(descriptionLabel, constraints);
        
        // Título secundario "Iniciar sesión"
        loginTitleLabel = new JLabel("Regístrese");
        loginTitleLabel.setFont(new Font("Andale Mono", 1, 30));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(20, 0, 10, 0);
        panel.add(loginTitleLabel, constraints);
        
        // Campo de texto de nombre
        nameField = new JTextField("Nombre");
        nameField.setPreferredSize(new Dimension(452, 50));
        nameField.setFont(new Font("Andale Mono", 1, 23));
        nameField.setForeground(Color.GRAY); // Color inicial del placeholder
        nameField.setText("Nombre");
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals("Nombre")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setForeground(Color.GRAY);
                    nameField.setText("Nombre");
                }
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(nameField, constraints);
        
        // Campo de texto del ceular
        phoneField = new JTextField("Teléfono");
        phoneField.setPreferredSize(new Dimension(452, 50));
        phoneField.setFont(new Font("Andale Mono", 1, 23));
        phoneField.setForeground(Color.GRAY); // Color inicial del placeholder
        phoneField.setText("Teléfono");
        phoneField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneField.getText().equals("Teléfono")) {
                    phoneField.setText("");
                    phoneField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (phoneField.getText().isEmpty()) {
                    phoneField.setForeground(Color.GRAY);
                    phoneField.setText("Teléfono");
                }
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(phoneField, constraints);
        
        // Campo de texto de correo
        emailField = new JTextField("Correo");
        emailField.setPreferredSize(new Dimension(452, 50));
        emailField.setFont(new Font("Andale Mono", 1, 23));
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
        constraints.gridy = 5;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(emailField, constraints);

        // Campo de contraseña
        passwordField = new JPasswordField("Contrase;a");
        passwordField.setPreferredSize(new Dimension(452, 50));
        passwordField.setFont(new Font("Andale Mono", 1, 23));
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
        constraints.gridy = 6;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(passwordField, constraints);
        
        // Botón de inicio de sesión
        signUpButton = new JButton("Registrar");
        signUpButton.setPreferredSize(new Dimension(452, 50));
        signUpButton.setFont(new Font("Andale Mono", 1, 23));
        signUpButton.setBackground(Color.decode("#040404"));
        signUpButton.setForeground(Color.WHITE);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.insets = new Insets(20, 0, 20, 0);
        panel.add(signUpButton, constraints);
        // Agrega el ActionListener para imprimir los valores al presionar el botón
        signUpButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String password = new String(passwordField.getPassword());
            String role = "cliente"; // Valor predeterminado para nuevos usuarios

            // Crear el usuario
            User newUser = new User(name, email, phone, password, role);

            // Llamar al método createUser a través de la instancia userController
            boolean isCreated = userController.createUser(newUser);

            if (isCreated) {
                System.out.println("Usuario registrado exitosamente");
            } else {
                System.out.println("Error al registrar el usuario");
            }
        });

        // Redirección a SingUp
        JLabel signUpLabel = new JLabel("¿Ya tiene una cuenta? Iniciar Sesión");
        signUpLabel.setFont(new Font("Andale Mono", Font.PLAIN, 18));
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Redireccionar a Login
                new LoginJFrame().setVisible(true);
                dispose(); // Cerrar la ventana actual
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 8;
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
            java.util.logging.Logger.getLogger(SignUpJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

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
import roomreservation.RoomReservation;
import roomreservation.components.MenuBar;
import roomreservation.controller.HallController;
import roomreservation.controller.UserController;
import roomreservation.model.Hall;
import roomreservation.model.User;

public class AddHallJFrame extends javax.swing.JFrame {

    public AddHallJFrame(Hall hall) {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Editar Sala");
        
        // Usar la clase MenuBar para agregar el JMenuBar
        MenuBar menuBar = new MenuBar(this);  // Pasamos 'this' para que el menú conozca el JFrame actual
        setJMenuBar(menuBar.getMenuBar());  // Configura el JMenuBar en el JFrame

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        // Título
        JLabel titleLabel = new JLabel("Editar Sala");
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
        nameField.setText(hall.getName());
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 40);
        panel.add(nameField, constraints);
        
        // Etiqueta de "Capacidad"
        JLabel capacityLabel = new JLabel("Capacidad máxima:");
        capacityLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(capacityLabel, constraints);

        // Campo de texto de Capacidad
        JTextField capacityField = new JTextField();
        capacityField.setPreferredSize(new Dimension(273, 38));
        capacityField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        capacityField.setForeground(Color.BLACK);
        capacityField.setText("" + hall.getMaxCapacity());
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 40);
        panel.add(capacityField, constraints);
        
        // Etiqueta de "Precio por hora"
        JLabel priceLabel = new JLabel("Precio por hora:");
        priceLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(priceLabel, constraints);

        // Campo de texto de Precio por hora
        JTextField phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(273, 38));
        phoneField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        phoneField.setForeground(Color.BLACK);
        phoneField.setText("" + hall.getPricePerHour());
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 10, 40);
        panel.add(phoneField, constraints);
        
        // Crear boton "Actualizar Información"
        JButton updateButton = new JButton("Actualizar Información");
        updateButton.setPreferredSize(new Dimension(273, 38));
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        updateButton.setBackground(Color.decode("#040404"));
        updateButton.setForeground(Color.WHITE);
        
        HallController hallController = new HallController();
        
        updateButton.addActionListener((ActionEvent e) -> {
            // Acciones para actualizar información
            String updatedName = nameField.getText();
            int updatedCapacity = Integer.parseInt(capacityField.getText());
            double updatedPrice = Double.parseDouble(phoneField.getText());
            
            // Actualizar la información del usuario
            Hall updatedHall = new Hall(
                    hall.getHallId(),
                    updatedName,
                    updatedCapacity,
                    updatedPrice
            );
            
            hallController.updateHall(updatedHall);
            JOptionPane.showMessageDialog(null, "Información actualizadaluis con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            new HallManagementJFrame().setVisible(true); // Abre el JFrame UserManagement
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

    @SuppressWarnings("unchecked")
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
            java.util.logging.Logger.getLogger(AddHallJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddHallJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddHallJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddHallJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new AddHallJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

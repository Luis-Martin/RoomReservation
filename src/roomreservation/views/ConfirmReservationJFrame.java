package roomreservation.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import roomreservation.RoomReservation;
import roomreservation.components.MenuBar;
import roomreservation.controller.HallController;
import roomreservation.model.Hall;

public class ConfirmReservationJFrame extends javax.swing.JFrame {
    
    public ConfirmReservationJFrame(Date selectedDate, String selectedHallName, String selectedHour) {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Confirmar Reserva");
        
        // Configurar el diseño del panel
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Usar la clase MenuBar para agregar el JMenuBar
        MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar.getMenuBar());
        
        // Formatear la fecha para mostrar solo el día
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YY");
        String formattedDay = dayFormat.format(selectedDate);
        
        HallController hallController = new HallController();
        Hall selectedHall = hallController.getHallByName(selectedHallName);

        // Convertir selectedHour ("01:00") a LocalTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(selectedHour, formatter);
         // Sumar 1 hora
        LocalTime endTime = time.plusHours(1);
        // Formatear la nueva hora a "HH:mm"
        String formattedEndTime = endTime.format(formatter);
        
        // COLUMNA 1
        
        // Etiqueta de "Nombre"
        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(nameLabel, constraints);
        
        // Campo de texto de nombre
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(273, 38));
        nameField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        nameField.setForeground(Color.GRAY);
        nameField.setText(RoomReservation.loggedInUser.getName());
        nameField.setEditable(false); // Hacer el campo no editable
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 40);
        add(nameField, constraints);
        
        // Etiqueta de "Correo"
        JLabel emailLabel = new JLabel("Correo:");
        emailLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(emailLabel, constraints);
        
        // Campo de texto de correo
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(273, 38));
        emailField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        emailField.setForeground(Color.GRAY);
        emailField.setText(RoomReservation.loggedInUser.getEmail());
        emailField.setEditable(false); // Hacer el campo no editable
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 40);
        add(emailField, constraints);
        
        // Etiqueta de "Fecha"
        JLabel dayLabel = new JLabel("Día:");
        dayLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(dayLabel, constraints);
        
        // Campo de texto de Fecha
        JTextField dayField = new JTextField();
        dayField.setPreferredSize(new Dimension(273, 38));
        dayField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        dayField.setForeground(Color.GRAY);
        dayField.setText(formattedDay);
        dayField.setEditable(false); // Hacer el campo no editable
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 10, 40);
        add(dayField, constraints);
        
        // Etiqueta de "Hora inicial"
        JLabel startHourLabel = new JLabel("Hora inicial:");
        startHourLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(startHourLabel, constraints);
        
        // Campo de texto de Hora inicial
        JTextField startHourField = new JTextField();
        startHourField.setPreferredSize(new Dimension(273, 38));
        startHourField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        startHourField.setForeground(Color.GRAY);
        startHourField.setText(selectedHour);
        startHourField.setEditable(false); // Hacer el campo no editable
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 0, 10, 40);
        add(startHourField, constraints);
        
        // Etiqueta de "Hora final"
        JLabel endHourLabel = new JLabel("Hora final:");
        endHourLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.insets = new Insets(10, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(endHourLabel, constraints);
        
        // Campo de texto de Hora final
        JTextField endHourField = new JTextField();
        endHourField.setPreferredSize(new Dimension(273, 38));
        endHourField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        endHourField.setForeground(Color.GRAY);
        endHourField.setText(formattedEndTime);
        endHourField.setEditable(false); // Hacer el campo no editable
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.insets = new Insets(10, 0, 10, 40);
        add(endHourField, constraints);
        
        // COLUMNA 2
        
        // Etiqueta de "Sala"
        JLabel hallLabel = new JLabel("Sala:");
        hallLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 2;  // Columna 2
        constraints.gridy = 1;  // Fila 1
        constraints.insets = new Insets(20, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(hallLabel, constraints);

        // Campo de texto de Sala
        JTextField hallField = new JTextField();
        hallField.setPreferredSize(new Dimension(273, 38));
        hallField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        hallField.setForeground(Color.GRAY);
        hallField.setText(selectedHallName);  // El nombre de la sala que se pasa
        hallField.setEditable(false);  // Hacer el campo no editable
        constraints = new GridBagConstraints();
        constraints.gridx = 3;  // Columna 3
        constraints.gridy = 1;  // Fila 1
        constraints.insets = new Insets(10, 0, 10, 0);
        add(hallField, constraints);

        // Etiqueta de "Capacidad máxima"
        JLabel capacityLabel = new JLabel("Capacidad máxima:");
        capacityLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 2;  // Columna 2
        constraints.gridy = 2;  // Fila 2
        constraints.insets = new Insets(20, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(capacityLabel, constraints);

        // Campo de texto de Capacidad máxima
        JTextField capacityField = new JTextField();
        capacityField.setPreferredSize(new Dimension(273, 38));
        capacityField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        capacityField.setForeground(Color.GRAY);
        capacityField.setText(String.valueOf(selectedHall.getMaxCapacity()));
        capacityField.setEditable(false);  // Hacer el campo no editable
        constraints = new GridBagConstraints();
        constraints.gridx = 3;  // Columna 3
        constraints.gridy = 2;  // Fila 2
        constraints.insets = new Insets(10, 0, 10, 0);
        add(capacityField, constraints);

        // Etiqueta de "Precio por hora"
        JLabel priceLabel = new JLabel("Precio por hora:");
        priceLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        constraints = new GridBagConstraints();
        constraints.gridx = 2;  // Columna 2
        constraints.gridy = 3;  // Fila 3
        constraints.insets = new Insets(20, 0, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        add(priceLabel, constraints);

        // Campo de texto de Precio por hora
        JTextField priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(273, 38));
        priceField.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        priceField.setForeground(Color.GRAY);
        priceField.setText(String.format("$%.2f", selectedHall.getPricePerHour()));  // Usando el método getPricePerHour() de Hall
        priceField.setEditable(false);  // Hacer el campo no editable
        constraints = new GridBagConstraints();
        constraints.gridx = 3;  // Columna 3
        constraints.gridy = 3;  // Fila 3
        constraints.insets = new Insets(10, 0, 10, 0);
        add(priceField, constraints);
        
        // BOTON DE CONFIRMACIÓN
        
        // Crear el botón de Confirmar
        JButton confirmButton = new JButton("Confirmar");

        // Crear las restricciones de GridBagConstraints
        constraints.gridx = 0;  // Coloca el botón en la columna 0
        constraints.gridy = 7;  // Colócalo en la fila 7 (justo debajo de las demás celdas)
        constraints.gridwidth = 2;  // Ocupa las dos columnas
        constraints.anchor = GridBagConstraints.CENTER;  // Centrado en la ventana

        // Añadir el ActionListener para el botón
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Imprimir los valores en consola
                System.out.println(formattedDay);
                System.out.println(selectedHour);
                System.out.println(formattedEndTime);
                System.out.println(RoomReservation.loggedInUser);
                System.out.println(selectedHall);
            }
        });

        // Añadir el botón al layout
        add(confirmButton, constraints);
        
        revalidate();
        repaint();
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
            java.util.logging.Logger.getLogger(ConfirmReservationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmReservationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmReservationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmReservationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ConfirmReservationJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

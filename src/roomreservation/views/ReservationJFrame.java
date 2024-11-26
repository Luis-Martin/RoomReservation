package roomreservation.views;

import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import roomreservation.components.MenuBar;
import roomreservation.controller.HallController;
import roomreservation.controller.ReservationController;
import roomreservation.model.Hall;
import roomreservation.model.Reservation;

public class ReservationJFrame extends javax.swing.JFrame {
    private JCalendar jCalendar; // Componente de calendario
    private JButton reservationButton; // Boton para realizar reserva
    private Date selectedDate;   // Fecha seleccionada
    private String selectedHall; // Sala seleccionada
    private String selectedHour; // Hora seleccionada
    
    public ReservationJFrame() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Reservar");
        
        // Configurar el diseño del panel
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Usar la clase MenuBar para agregar el JMenuBar
        MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar.getMenuBar());
        
        /*****
        DATOS DE LA BBDD
        *****/
        
        // Obtenemos la lista de salas desde la base de datos
        HallController hallController = new HallController();
        List<Hall> halls = hallController.getAllHalls();
        Integer columns = halls.size();  // Número de columnas basado en la cantidad de salas
        Integer rows = 12;  // 12 filas para las horas de 9 AM a 9 PM
        
        // Crear el objeto dinámico para las salas
        Map<String, int[]> reservationStatus = new HashMap<>();

        // Inicializar el estado de reservas dinámicamente
        for (Hall hall : halls) {
            String hallName = hall.getName(); // Obtener el nombre de la sala
            reservationStatus.put(hallName, new int[24]); // Inicializar con 24 valores de 0   
        }
        
        /*****
        CALENDARIO
        *****/
        
        // Crear el calendario
        jCalendar = new JCalendar();
        
        // Configurar las restricciones para el calendario
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        jPanel1.add(jCalendar, constraints);
        
        /*****
        HORARIOS
        *****/
        
        // Crear un panel principal para los encabezados y la rejilla
        JPanel mainGridPanel = new JPanel();
        mainGridPanel.setLayout(new BorderLayout());
        
        // Crear un panel para los encabezados
        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new BorderLayout());
        
        // Crear un panel para las horas
        JPanel hourPanel = new JPanel();
        hourPanel.setLayout(new GridLayout(12, 1)); // 12 filas, 1 columna
        
        /*****
        HORARIOS: CABECERA
        *****/

        // Crear el encabezado para "Horario"
        JPanel horaryPanel = new JPanel();
        horaryPanel.setLayout(new BorderLayout());
        JLabel horaryLabel = new JLabel("Horario   ", JLabel.CENTER);
        horaryLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        horaryPanel.add(horaryLabel, BorderLayout.CENTER);
        headerContainer.add(horaryPanel, BorderLayout.WEST);

        // Crear un panel para los nombres de las salas
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, columns)); // 1 fila, 3 columnas

        // Añadir los nombres de las columnas
        for (int column = 0; column < columns; column++) {
            String hallName = halls.get(column).getName();  // Obtener el nombre de la sala
            JLabel headerLabel = new JLabel(hallName, JLabel.CENTER);
            headerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            headerPanel.add(headerLabel);
        }

        // Añadir el panel de nombres de salas al contenedor
        headerContainer.add(headerPanel, BorderLayout.CENTER);

        // Añadir el contenedor completo al panel principal
        mainGridPanel.add(headerContainer, BorderLayout.NORTH);
        
        /*****
        HORARIOS: HORAS Y REJILLA
        *****/

        // Añadir las etiquetas de horas
        for (int hour = 9; hour <= 20; hour++) { // Formato 24 horas
            JLabel hourLabel = new JLabel(String.format(" %02d:00     ", hour), JLabel.CENTER);
            hourLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            hourPanel.add(hourLabel);
        }

        // Añadir el panel de horas al lado izquierdo del panel principal
        mainGridPanel.add(hourPanel, BorderLayout.WEST);
        
        // Crear el panel de rejilla horaria
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, columns, 0, 0)); // 12 filas, 3 columnas
        
        // Añadir las celdas a la rejilla representando cada hora de 9 AM a 9 PM
        renderGrid(columns, halls,gridPanel, reservationStatus);
        
        // Añadir la rejilla al panel principal
        mainGridPanel.add(gridPanel, BorderLayout.CENTER);

        // Configurar las restricciones para el panel principal
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.7;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.CENTER;
        jPanel1.add(mainGridPanel, constraints);
        
        /*****
        CALENDARIO: LISTENER
        *****/
        
        // Escuchar la selección de fechas
        jCalendar.getDayChooser().addPropertyChangeListener("day", evt -> {
            selectedDate = jCalendar.getDate();
            resetStatus(reservationStatus); // Limpia el estado actual
            ReservationController reservationController = new ReservationController();
            List<Reservation> reservationsByDay = reservationController.getAllReservationsByDay(selectedDate);

            System.out.println(reservationsByDay);
            // Calendar calendar = Calendar.getInstance();
            
            // Actualizar el estado de las reservas
            for (Reservation reservation : reservationsByDay) {
                int hallId = reservation.getHallId(); // Identifica la sala
                Hall hall = hallController.getHallById(hallId);
                Date startTime = reservation.getStartDate();
                
                // Convierte las horas de inicio y fin en índices
                int startIndex = getHourIndex(startTime);
                
                // Recuperar el estado actual de la sala
                int[] status = reservationStatus.get(hall.getName());
                
                // Actualizar el estado dentro del rango de horas reservadas
                for (int i = 0; i < 24; i++) { 
                    if (i == startIndex - 1) {
                        status[i] = 1; // Marca como reservado
                    }
                }
            }

            // Imprime el estado actualizado para verificar
            printStatus(reservationStatus);
            
            // Rejilla
            renderGrid( columns, halls, gridPanel, reservationStatus);
        });
        
        /*****
        BOTÓN
        *****/
        
        // Crear el botón y configurar su posición debajo del calendario
        reservationButton = new JButton("Reservar");
        reservationButton.setBackground(Color.BLACK);
        reservationButton.setFont(new Font("Arial", Font.PLAIN, 15));
        reservationButton.setForeground(Color.WHITE);
        
        // Acción al presionar el botón reservar
        reservationButton.addActionListener(e -> {
            if (selectedDate == null || selectedHour == null || selectedHall == null) {
                // Mostrar un mensaje de advertencia si faltan datos
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Por favor, seleccione una fecha, una sala y una hora.",
                        "Advertencia",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            // Combinar fecha y hora seleccionadas
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(selectedDate);

            // Extraer la hora seleccionada (en formato "HH:00") y ajustar en el calendario
            int hour = Integer.parseInt(selectedHour.split(":")[0]); // Extrae la hora como entero
            calendar.set(java.util.Calendar.HOUR_OF_DAY, hour);
            calendar.set(java.util.Calendar.MINUTE, 0);
            calendar.set(java.util.Calendar.SECOND, 0);

            // Crear un objeto Date que contiene la fecha completa con hora
            Date combinedDateTime = calendar.getTime();
            
            System.out.println(combinedDateTime);
            // Abre el JFrame para Confirmar Reserva con la fecha combinada
            new ConfirmReservationJFrame(combinedDateTime, selectedHall).setVisible(true);
            dispose(); // Cierra el JFrame actual
        });
        
        // Configurar restricciones para el botón debajo del calendario
        constraints.gridx = 1;
        constraints.gridy = 1; // Posicionar debajo del calendario
        constraints.weightx = 0.3;
        constraints.weighty = 0.1; // Menos altura para el botón
        constraints.anchor = GridBagConstraints.NORTH;
        jPanel1.add(reservationButton, constraints);
        
        // Actualizar el diseño
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    // Función para objeter la hora de una Fecha
    private int getHourIndex(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY); // Devuelve la hora como índice
    }
    
    // Función para reiniciar el estado de todas las reservas a "no reservado"
    public static void resetStatus(Map<String, int[]> reservaEstado) {
        for (Map.Entry<String, int[]> entry : reservaEstado.entrySet()) {
            int[] schedules = entry.getValue();
            for (int i = 0; i < schedules.length; i++) {
                schedules[i] = 0; // Reiniciar cada valor del array a 0
            }
        }
    }

    // Función auxiliar para imprimir el estado de las reservas
    public static void printStatus(Map<String, int[]> reservationStatus) {
        for (Map.Entry<String, int[]> entry : reservationStatus.entrySet()) {
            System.out.println("Sala: " + entry.getKey() + ", Estado: " + java.util.Arrays.toString(entry.getValue()));
        }
    }
    
    // Función para crear la rejilla
    private void renderGrid(Integer columns, List<Hall> halls, JPanel gridPanel, Map<String, int[]> reservationStatus) {
        // Limpiar el contenido actual del mainGridPanel
        gridPanel.removeAll();
        
        // Añadir las celdas a la rejilla representando cada hora de 9 AM a 9 PM
        for (int hour = 9; hour <= 20; hour++) {
            for (Hall hall : halls) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(120, 42));
                cell.setBorder(BorderFactory.createLineBorder(new Color(0x7E7878)));
                
                // Crear y añadir el JLabel con el texto
                JLabel label = new JLabel(hall.getName() + " -> " + hour);
                cell.add(label, BorderLayout.CENTER);
                
                // Usar un booleano para rastrear si la celda está seleccionada
                final boolean[] isSelected = {false};
                final int finalHour = hour;
                final String hallName = hall.getName();
                
                // Verificar el estado de la reserva para esta sala y hora
                int[] hallStatus = reservationStatus.get(hall.getName());
                
                if (hallStatus[hour - 1] == 1) {
                    cell.setBackground(new Color(0x587260));
                } else {
                    cell.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            selectedHall = hallName;
                            selectedHour = String.format("%02d:00", finalHour);

                            // Alternar selección visual
                            cell.setBackground(isSelected[0] ? new Color(214, 217, 223) : new Color(0x96BFA2));
                            isSelected[0] = !isSelected[0];
                        }
                    });
                }
                
                gridPanel.add(cell);
            }
        }
        
        gridPanel.revalidate();
        gridPanel.repaint();
    }
    
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
            java.util.logging.Logger.getLogger(ReservationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { 
                new ReservationJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mbMenu;
    // End of variables declaration//GEN-END:variables
}

package roomreservation.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import roomreservation.components.MenuBar;
import javax.swing.table.DefaultTableModel;
import roomreservation.RoomReservation;
import roomreservation.controller.HallController;
import roomreservation.controller.ReservationController;
import roomreservation.model.Hall;
import roomreservation.model.Reservation;

public class AdministrateJFrame extends javax.swing.JFrame {
    private JTable reservationsTable; // Tabla para mostrar usuarios
    private DefaultTableModel tableModel; // Modelo para la tabla
   
    public AdministrateJFrame() {
        initComponents();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Administrar Reservas");

        // Configurar el JMenuBar
        MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar.getMenuBar());

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Título
        JLabel titleLabel = new JLabel("Administrar Reservas");
        titleLabel.setFont(new Font("Andale Mono", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new java.awt.Insets(10, 0, 10, 0); // Márgenes
        panel.add(titleLabel, constraints);

        // Tabla
        String[] columnNames = {
            "<html><b>Usuario<b></html>",
            "<html><b>Sala<b></html>",
            "<html><b>Capacidad<b></html>",
            "<html><b>Fecha<b></html>",
            "<html><b>Hora Inicial<b></html>",
            "<html><b>Hora Final<b></html>",
            "<html><b>Eliminar<b></html>"
        };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reservationsTable = new JTable(tableModel);
        
        // Centrar texto de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        reservationsTable.setDefaultRenderer(Object.class, centerRenderer);

        // Centrar texto de los encabezados
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        reservationsTable.getTableHeader().setDefaultRenderer(headerRenderer);
        
        reservationsTable.setFont(new Font("Inter", Font.PLAIN, 14));
        reservationsTable.setRowHeight(30);
        reservationsTable.setBackground(new Color(214, 217, 223));
        reservationsTable.setShowGrid(false);
        reservationsTable.setShowVerticalLines(false);
        reservationsTable.setShowHorizontalLines(true);
        
        // Agregar filas al modelo de la tabla
        ReservationController reservationController = new ReservationController();
        HallController hallController= new HallController();
        
        int userID = RoomReservation.loggedInUser.getUserId();
        String userName = RoomReservation.loggedInUser.getName();
        
        List<Reservation> reservations = reservationController.getAllReservationsByUser(userID);
        
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        
        for (Reservation reservation : reservations) {
            // Obtener detalles del auditorio
            Hall hall = hallController.getHallById(reservation.getHallId());

            // Separar día, hora de inicio y hora final
            java.util.Date startDate = reservation.getStartDate();
            java.util.Date endDate = reservation.getEndDate();

            // Verificar si la fecha de inicio es posterior a la fecha actual
            if (startDate.after(currentDate)) {
                java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
                java.text.SimpleDateFormat timeFormatter = new java.text.SimpleDateFormat("HH:mm");

                String day = dateFormatter.format(startDate);
                String startTime = timeFormatter.format(startDate);
                String endTime = timeFormatter.format(endDate);

                // Agregar fila al modelo de la tabla
                Object[] row = {
                    userName,
                    hall.getName(),
                    hall.getMaxCapacity(),
                    day,
                    startTime,
                    endTime,
                    "<html><p style='color: red'>Eliminar</p></html>"
                };
                tableModel.addRow(row);
            }
        }
        
        // Listener para manejar clics en la columna "Eliminar"
        reservationsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = reservationsTable.columnAtPoint(e.getPoint());
                int row = reservationsTable.rowAtPoint(e.getPoint());

                if (column == 6) { // Si se hace clic en la columna "Eliminar"
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(
                            null,
                            "¿Estás seguro de que deseas eliminar esta reserva?",
                            "Confirmar eliminación",
                            javax.swing.JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        // Obtener el ID de la reserva para eliminarla
                        Reservation reservation = reservations.get(row);
                        int reservationId = reservation.getReservationId();
                        boolean success = reservationController.deleteReservation(reservationId);

                        if (success) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Reserva eliminada con éxito.");
                            tableModel.removeRow(row); // Eliminar la fila de la tabla
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar la reserva.");
                        }
                    }
                }
            }
        });
        
        // Obtener el ancho de la pantalla
        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;

        // Calcular el margen como 15% del ancho de la pantalla
        int sideMargin = (int) (screenWidth * 0.24);

        // Configurar el JScrollPane con márgenes dinámicos
        JScrollPane scrollPanel = new JScrollPane(reservationsTable);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, sideMargin, 30, sideMargin); // Márgenes dinámicos
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        panel.add(scrollPanel, constraints);

        // Configurar el panel principal en el JFrame
        setContentPane(panel); // Cambia el contenido del JFrame
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        mbMenu = new javax.swing.JMenuBar();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

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
            java.util.logging.Logger.getLogger(AdministrateJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrateJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrateJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrateJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrateJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mbMenu;
    // End of variables declaration//GEN-END:variables
}

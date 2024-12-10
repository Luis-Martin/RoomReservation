/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
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
import javax.swing.table.DefaultTableModel;
import roomreservation.RoomReservation;
import roomreservation.components.MenuBar;
import roomreservation.controller.HallController;
import roomreservation.model.Hall;
import roomreservation.model.Reservation;

/**
 *
 * @author USUARIO
 */
public class HallManagementJFrame extends javax.swing.JFrame {
    private JTable hallssTable; // Tabla para mostrar salas
    private DefaultTableModel tableModel; // Modelo para la tabla

    public HallManagementJFrame() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Gestionar Salas");
        
        // Usar la clase MenuBar para agregar el JMenuBar
        MenuBar menuBar = new MenuBar(this);  // Pasamos 'this' para que el menú conozca el JFrame actual
        setJMenuBar(menuBar.getMenuBar());  // Configura el JMenuBar en el JFrame

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Título
        JLabel titleLabel = new JLabel("Administrar Salas");
        titleLabel.setFont(new Font("Andale Mono", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new java.awt.Insets(10, 0, 10, 0); // Márgenes
        panel.add(titleLabel, constraints);

        // Tabla
        String[] columnNames = {
            "<html><b>Nombre<b></html>",
            "<html><b>Capacidad<b></html>",
            "<html><b>Precio por Hora<b></html>",
            "<html><b>Editar<b></html>",
            "<html><b>Eliminar<b></html>"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        hallssTable = new JTable(tableModel);

        // Centrar texto de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        hallssTable.setDefaultRenderer(Object.class, centerRenderer);

        // Centrar texto de los encabezados
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        hallssTable.getTableHeader().setDefaultRenderer(headerRenderer);

        hallssTable.setFont(new Font("Inter", Font.PLAIN, 14));
        hallssTable.setRowHeight(30);
        hallssTable.setBackground(new Color(214, 217, 223));
        hallssTable.setShowGrid(false);
        hallssTable.setShowVerticalLines(false);
        hallssTable.setShowHorizontalLines(true);

        // Agregar filas al modelo de la tabla
        HallController hallController = new HallController();

        List<Hall> halls = hallController.getAllHalls();

        for (Hall hall : halls) {
                // Agregar fila al modelo de la tabla
                Object[] row = {
                    hall.getName(),
                    hall.getMaxCapacity(),
                    hall.getPricePerHour(),
                    "<html><p style='color: blue'>Editar</p></html>",
                    "<html><p style='color: red'>Eliminar</p></html>",
                };
                tableModel.addRow(row);
        }

        // Listener para manejar clics en la columna "Eliminar"
        hallssTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = hallssTable.columnAtPoint(e.getPoint());
                int row = hallssTable.rowAtPoint(e.getPoint());

                if (column == 4) { // Si se hace clic en la columna "Eliminar"
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(
                            null,
                            "¿Estás seguro de que deseas eliminar esta reserva?",
                            "Confirmar eliminación",
                            javax.swing.JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        // Obtener el ID de la sala desde el modelo de la tabla
                        Hall hallToDelete = halls.get(row);
                        int hallId = hallToDelete.getHallId();
                        
                        // Llamar al método del controlador para eliminar la sala
                        HallController hallController = new HallController();
                        boolean success = hallController.deleteHall(hallId);
                        
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
        JScrollPane scrollPanel = new JScrollPane(hallssTable);
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
            java.util.logging.Logger.getLogger(HallManagementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HallManagementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HallManagementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HallManagementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HallManagementJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mbMenu;
    // End of variables declaration//GEN-END:variables
}

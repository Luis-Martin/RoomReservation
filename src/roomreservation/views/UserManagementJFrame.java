package roomreservation.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import roomreservation.components.MenuBar;
import roomreservation.model.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserManagementJFrame extends javax.swing.JFrame {

    Color mColorFondo = new Color(18, 54, 41); // Color del menú
    Color mColorBlanco = Color.WHITE; // Fondo blanco para toda la ventana
    private JTable userTable; // Tabla para mostrar usuarios
    private DefaultTableModel tableModel; // Modelo para la tabla

    public UserManagementJFrame() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setTitle("Gestionar Usuarios");

        // Usar la clase MenuBar para agregar el JMenuBar
        MenuBar menuBar = new MenuBar(this); // Pasamos 'this' para que el menú conozca el JFrame actual
        setJMenuBar(menuBar.getMenuBar()); // Configura el JMenuBar en el JFrame

        // Establecer fondo blanco para la ventana
        getContentPane().setBackground(mColorBlanco);

        // Configurar la tabla
        setupTable();

        // Cargar datos desde la base de datos
        loadUserData();
    }

    private JMenuItem createMenuItem(String text, Font font) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setOpaque(true);
        menuItem.setFont(font);
        menuItem.setForeground(Color.white);
        menuItem.setBackground(mColorFondo);
        return menuItem;
    }

    private void setupTable() {
        // Definir las columnas
        String[] columnNames = { "ID", "Nombre", "Correo", "Teléfono", "Rol" };
        tableModel = new DefaultTableModel(columnNames, 0);

        // Crear la tabla y asignarle el modelo
        userTable = new JTable(tableModel);
        userTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        userTable.setRowHeight(30);

        // Crear el panel de título
        JLabel titleLabel = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(mColorFondo); // Texto en color del menú
        titleLabel.setBackground(mColorBlanco);
        titleLabel.setOpaque(true);

        // Crear un panel para la tabla y título con fondo blanco
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(mColorBlanco); // Aseguramos que el panel tenga fondo blanco

        // Agregar el título y la tabla al panel
        panel.add(titleLabel, BorderLayout.NORTH); // Título arriba
        JScrollPane scrollPane = new JScrollPane(userTable);
        panel.add(scrollPane, BorderLayout.CENTER); // Tabla debajo del título

        // Establecer el layout de la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER); // Aseguramos que el panel ocupe toda el área disponible
    }

    private void loadUserData() {
        // Conexión a la base de datos
        Connection connection = DBConnection.conect(); // Asegúrate de tener tu clase DBConnection configurada
        if (connection != null) {
            try {
                String query = "SELECT usuario_id, nombre, email, telefono, rol FROM Usuario";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                // Limpiar el modelo de la tabla antes de cargar datos
                tableModel.setRowCount(0);

                // Iterar sobre el resultado y llenar la tabla
                while (resultSet.next()) {
                    int id = resultSet.getInt("usuario_id");
                    String nombre = resultSet.getString("nombre");
                    String email = resultSet.getString("email");
                    String telefono = resultSet.getString("telefono");
                    String rol = resultSet.getString("rol");

                    // Agregar fila al modelo de la tabla
                    tableModel.addRow(new Object[] { id, nombre, email, telefono, rol });
                }

                // Cerrar recursos
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setJMenuBar(mbMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserManagementJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManagementJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserManagementJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserManagementJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserManagementJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mbMenu;
    // End of variables declaration
}

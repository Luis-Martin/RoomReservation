package roomreservation.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import roomreservation.views.AdministrateJFrame;
import roomreservation.views.HistoryJFrame;
import roomreservation.views.HomeJFrame;
import roomreservation.views.LoginJFrame;
import roomreservation.views.ProfileJFrame;
import roomreservation.views.ReservationJFrame;
import roomreservation.views.UserManagementJFrame;

public class MenuBar {
    private JMenuBar mbMenu;
    private final JMenuItem mniRoomc;
    private final JMenuItem mniHome;
    private final JMenuItem mniReservation;
    private final JMenuItem mniHistory;
    private final JMenuItem mniAdministration;
    private final JMenuItem mniProfile;
    private final JMenuItem mniUserManagement;
    private final JMenuItem mniExit;
    private Color mColorFondo = new Color(18, 54, 41);

    public MenuBar(JFrame parentFrame) {
        mbMenu = new JMenuBar();
        mbMenu.setPreferredSize(new Dimension(0, 100));
        mbMenu.setOpaque(false);  // Para ajustar la opacidad
        mbMenu.setBackground(mColorFondo);   
        mbMenu.setBorder(new LineBorder(mColorFondo, 1));
        
        // Crear y agregar los ítems al menú
        mniRoomc = createMenuItem("Roomc", new Font("Inter", Font.BOLD, 40));
        mniHome = createMenuItem("Inicio", new Font("Inter", Font.PLAIN, 18));
        mniReservation = createMenuItem("Reservar", new Font("Inter", Font.PLAIN, 18));
        mniHistory = createMenuItem("Historial", new Font("Inter", Font.PLAIN, 18));
        mniAdministration = createMenuItem("Administrar", new Font("Inter", Font.PLAIN, 18));
        mniProfile = createMenuItem("Perfil", new Font("Inter", Font.PLAIN, 18));
        mniUserManagement = createMenuItem("Gestionar Usuarios", new Font("Inter", Font.PLAIN, 18));
        mniExit = createMenuItem("Salir", new Font("Inter", Font.PLAIN, 18));

        // Agregar ítems al menú
        mbMenu.add(mniRoomc);
        mbMenu.add(mniHome);
        mbMenu.add(mniReservation);
        mbMenu.add(mniHistory);
        mbMenu.add(mniAdministration);
        mbMenu.add(mniProfile);
        mbMenu.add(mniUserManagement);
        mbMenu.add(mniExit);
        
        // agregar listeners
        mniHome.addActionListener((var e) -> {
            new HomeJFrame().setVisible(true); // Abre el JFrame Home
            parentFrame.dispose(); // Cierra el JFrame actual
        });
        
        mniReservation.addActionListener((var e) -> {
            new ReservationJFrame().setVisible(true); // Abre el JFrame Reservar
            parentFrame.dispose(); // Cierra el JFrame actual
        });
        
        mniHistory.addActionListener((var e) -> {
            new HistoryJFrame().setVisible(true); // Abre el JFrame History
            parentFrame.dispose(); // Cierra el JFrame actual
        });
        
        mniAdministration.addActionListener((var e) -> {
            new AdministrateJFrame().setVisible(true); // Abre el JFrame Adminitratrate
            parentFrame.dispose(); // Cierra el JFrame actual
        });
        
        mniProfile.addActionListener((var e) -> {
            new ProfileJFrame().setVisible(true); // Abre el JFrame Profile
            parentFrame.dispose(); // Cierra el JFrame actual
        });
        
        mniUserManagement.addActionListener((var e) -> {
            new UserManagementJFrame().setVisible(true); // Abre el JFrame UserManagement
            parentFrame.dispose(); // Cierra el JFrame actual
        });
        
         mniExit.addActionListener((ActionEvent e) -> {
             new LoginJFrame().setVisible(true); // Abre el JFrame de Login
             parentFrame.dispose(); // Cierra el JFrame actual
         });
    }

    private JMenuItem createMenuItem(String text, Font font) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setOpaque(true);
        menuItem.setFont(font);
        menuItem.setForeground(Color.white);
        menuItem.setBackground(mColorFondo);
        menuItem.setHorizontalAlignment(SwingConstants.CENTER);
        return menuItem;
    }

    public JMenuBar getMenuBar() {
        return mbMenu;
    }
}

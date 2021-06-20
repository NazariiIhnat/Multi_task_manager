import com.formdev.flatlaf.FlatDarkLaf;
import connection.HibernateUtil;
import login.LoginFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class Start {

    static {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private static JWindow loadingWindow = new JWindow();

    public static void main(String[] args) {
        showLoadingWindow();
        HibernateUtil.start();
        ApplicationContext context = new AnnotationConfigApplicationContext(GUIComponentsScan.class, BackendComponentsScan.class);
        loadingWindow.dispose();
        LoginFrame frame1 = context.getBean(LoginFrame.class);
    }
    private static void showLoadingWindow() {
        JLabel label = new JLabel("Loading", SwingConstants.CENTER);
        loadingWindow.getContentPane().add(label);
        loadingWindow.setSize(150, 100);
        loadingWindow.setLocationRelativeTo(null);
        loadingWindow.setVisible(true);
    }
}

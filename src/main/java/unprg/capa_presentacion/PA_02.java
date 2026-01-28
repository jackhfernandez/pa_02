
package unprg.capa_presentacion;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jackh
 */
public class PA_02 {

    public static void main(String[] args) {
        System.out.println("Hello Mundo Cruel!");
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatIntelliJLaf());

        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Error UI");
        }
        EventQueue.invokeLater(() -> {
            frmMenu menu = new frmMenu();
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        });
        
    }
}

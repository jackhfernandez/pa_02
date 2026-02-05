
package unprg.capa_presentacion;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
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
            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatCarbonIJTheme.setup();
            unprg.capa_presentacion.utils.UiHelper.aplicarEstiloGlobal();

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

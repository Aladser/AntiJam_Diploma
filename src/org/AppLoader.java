package org;

import java.util.Arrays;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.models.codecs.GaluaField;
import org.views.MainFrame;

/**
 * Точка входа
 */
public class AppLoader {
    public static org.views.ProgressFrame pf;
    public static void main(String args[]){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        GaluaField galua = new GaluaField(0b1011, 8);

        
        /* Вызов главного окна */
        //pf = new org.views.ProgressFrame();
        new MainFrame().setVisible(true);
    }
    
}

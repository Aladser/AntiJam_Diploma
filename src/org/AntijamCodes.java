package org;

import java.awt.EventQueue;
import javax.swing.ImageIcon;

/**
 * Точка входа
 */
public class AntijamCodes {
    public static void main(String args[]){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(org.views.MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Вызов главного окна */
        EventQueue.invokeLater(() -> {
            new org.views.MainFrame().setVisible(true);
        });
        
    }
}

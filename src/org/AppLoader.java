package org;

import org.models.NumberCoup;
import org.models.SignedNumberException;
import org.views.MainFrame;

/**
 * Точка входа
 * @author Aladser
 */
public class AppLoader {
    /**
    * @param args the command line arguments
     * @throws org.models.SignedNumberException
    */
    public static void main(String args[]) throws SignedNumberException {
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Вызов главного окна */  
        System.out.println("8 -> " + NumberCoup.execute(8, 2, 4));
        System.out.println("4 -> " + NumberCoup.execute(4, 2, 4));
        System.out.println("2 -> " + NumberCoup.execute(2, 2, 4));
        System.out.println("1 -> " + NumberCoup.execute(1, 2, 4));
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
    
}

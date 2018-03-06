package org;
import org.views.MainFrame;

/**
 * Точка входа
 * @author Aladser
 */
public class AppLoader {
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]){
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
        java.awt.EventQueue.invokeLater(() -> {
            org.models.BinDecConverting.decToBin(0);
            org.models.BinDecConverting.decToBin(1);
            org.models.BinDecConverting.decToBin(2);
            org.models.BinDecConverting.decToBin(3);
            org.models.BinDecConverting.decToBin(4);
            org.models.BinDecConverting.decToBin(5);
            org.models.BinDecConverting.decToBin(6);
            org.models.BinDecConverting.decToBin(7);
            org.models.BinDecConverting.decToBin(8);
            org.models.BinDecConverting.decToBin(9);
            org.models.BinDecConverting.decToBin(10);
            org.models.BinDecConverting.decToBin(11);
            org.models.BinDecConverting.decToBin(12);
            org.models.BinDecConverting.decToBin(13);
            org.models.BinDecConverting.decToBin(14);
            org.models.BinDecConverting.decToBin(15);
            org.models.BinDecConverting.decToBin(16);
            new MainFrame().setVisible(true);
        });
    }
    
}

package org;
import java.util.BitSet;
import org.models.BinDecTranslation;
import org.models.PolynomDivision;
import org.models.codecs.BCHCodec;
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

        // ТЕСТ
        // числа
        int SIZE = 16;
        int[] numbers = new int[SIZE];
        for(int i=0; i<SIZE; i++) numbers[i]=i;
        // двоичная форма чисел
        BitSet[] binNumbers = new BitSet[SIZE];
        for(int i=0; i<SIZE; i++) binNumbers[i] = BinDecTranslation.decToBin(numbers[i]);
        /*/ Вывод в консоль
        for(int i=0; i<SIZE; i++){
            System.out.print( numbers[i] + " = ");
            for( int j=0; j<binNumbers[i].length()-1; j++ ) System.out.print( binNumbers[i].get(j)?1:0 );
            System.out.println();
        }*/
        
        BitSet ax = BinDecTranslation.decToBin(5);
        BitSet sx = BCHCodec.encode( binNumbers[5] );
        for(int i=0; i<ax.length()-1; i++) System.out.print(ax.get(i)?1:0);
        System.out.println();
        
        
        /* Вызов главного окна */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
    
}

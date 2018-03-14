package org;
import java.util.BitSet;
import org.models.BinDecTranslation;
import org.models.NumberCoup;
import org.models.PolynomDivision;
import org.models.codecs.BCHCodec;
import org.views.MainFrame;

/**
 * Точка входа
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
        /*
        int SIZE = 16;
        int[] numbers = new int[SIZE];
        for(int i=0; i<SIZE; i++) numbers[i]=i;
        // двоичная форма чисел
        BitSet[] binNumbers = new BitSet[SIZE];
        for(int i=0; i<SIZE; i++) binNumbers[i] = BinDecTranslation.decToBin(numbers[i], 4);
        // закодированные числа
        BitSet[] codes = new BitSet[SIZE];
        for(int i=0; i<SIZE; i++) codes[i] = BCHCodec.encode(binNumbers[i]);
        // декодироване
        BitSet[] codes2 = new BitSet[SIZE];
        for(int i=0; i<SIZE; i++) codes2[i] = BCHCodec.decode(codes[i]);
        // Вывод в консоль
        for(int i=0; i<SIZE; i++){
            //System.out.print( numbers[i] + " = ");
            for( int j=0; j<binNumbers[i].length()-1; j++ ) System.out.print( binNumbers[i].get(j)?1:0 );
            //System.out.print(" = ");
            //for( int j=0; j<codes[i].length()-1; j++ ) System.out.print( codes[i].get(j)?1:0 );
            System.out.print(" = ");
            for( int j=0; j<codes2[i].length()-1; j++ ) System.out.print( codes2[i].get(j)?1:0 );
            System.out.println();
        }
        */
        
       int r11 = PolynomDivision.execute(0b1001110, 7, 0b1011, 4);
       int r21 = NumberCoup.execute(r11, 2, 4);
       System.out.println( "0b1001110 = " + Integer.toBinaryString(r21) );
       /*
       int r12 = PolynomDivision.execute(0b1000101, 7, 0b1011, 4);
       int r22 = NumberCoup.execute(r12, 2, 4);
       System.out.println( "0b1000101 = " + Integer.toBinaryString(r22) );
       
       int r13 = PolynomDivision.execute(0b0100111, 7, 0b1011, 4);
       int r23 = NumberCoup.execute(r13, 2, 4);
       System.out.println( "0b0100111 = " + Integer.toBinaryString(r23) );
       
       int r14 = PolynomDivision.execute(0b0101100, 7, 0b1011, 4);
       int r24 = NumberCoup.execute(r14, 2, 4);
       System.out.println( "0b0101100 = " + Integer.toBinaryString(r24) );
       
       int r15 = PolynomDivision.execute(0b1010011, 7, 0b1011, 4);
       int r25 = NumberCoup.execute(r15, 2, 4);
       System.out.println( "0b1010011 = " + Integer.toBinaryString(r25) );
       
       int r16 = PolynomDivision.execute(0b1111111, 7, 0b1011, 4);
       int r26 = NumberCoup.execute(r16, 2, 4);
       System.out.println( "0b1111111 = " + Integer.toBinaryString(r26) );
       8/
       
        /* Вызов главного окна */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
    
}

package org;
import java.util.BitSet;
import org.models.BinDecTranslation;
import org.models.codecs.BCHCodec;
import org.models.codecs.Codec;
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
        Codec codec = new BCHCodec(0b1011, 7, 4);
        int size = 16;
        int[] numbers = new int[size];
        for(int i=0; i<size; i++) numbers[i]=i;    
        BitSet[] binNumbers = new BitSet[size];
        for(int i=0; i<size; i++) binNumbers[i] = BinDecTranslation.decToBin(i, 4);
        BitSet Ax = new BitSet();
        Ax.set(size*4);
        for(int k=0, i=0; i<Ax.length()-1; i+=4){
            for(int j=0; j<4; j++) if(binNumbers[k].get(j)) Ax.set(i+j);
            k++;
        }
        for(int i=0; i<Ax.length()-1; i++){
            System.out.print( Ax.get(i)?1:0 );
            if((i+1)%4 == 0) System.out.print("   |");
        }
        System.out.println();
        BitSet Sx = codec.encode(Ax);
        for(int i=0; i<Sx.length()-1; i++){
            System.out.print( Sx.get(i)?1:0 );
            if((i+1)%7 == 0) System.out.print("|");
        }
        
        /* Вызов главного окна */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
    
}

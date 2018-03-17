package org;

import java.util.BitSet;
import org.models.BinOperations;
import org.models.NumberCoup;
import org.models.codecs.BCHCodec;
import org.models.codecs.PolynomDivision;
import org.views.MainFrame;

/**
 * Точка входа
 */
public class AppLoader {
    public static void main(String args[]){
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
        
        int inum = 1;
        BitSet bnum = BinOperations.decToBin(inum, 4);
        BitSet code = new BCHCodec(0b1011, 7, 4).encode(bnum);
        System.out.println( BinOperations.showBitSet(code, 7) + "->" + BinOperations.showBitSet(bnum) +"\n-------------");
        BitSet[] errcodes = new BitSet[7];
        for(int i=0; i<errcodes.length; i++){
            errcodes[i] = new BitSet();
            for(int k=0; k<code.length(); k++) if(code.get(k)) errcodes[i].set(k);
            if(errcodes[i].get(i)) errcodes[i].clear(i);
            else errcodes[i].set(i);
        }
        PolynomDivision.Result[] res = new PolynomDivision.Result[7];
        for(int i=0; i<errcodes.length; i++){
            res[i] = PolynomDivision.exec(BinOperations.binToDec(errcodes[i]), 7, 0b1011);
            res[i].quotient = NumberCoup.exec(res[i].quotient, 2, 4);
        }
        BitSet quot;
        for(int i=0; i<errcodes.length; i++){
            System.out.print( "[" + i + "] " + BinOperations.showBitSet(errcodes[i]) + " = ");
            quot = BinOperations.decToBin(res[i].quotient, 4);
            System.out.print( BinOperations.showBitSet(quot) + " ост." + Integer.toBinaryString(res[i].reminder) );
            System.out.println( " ("+res[i].reminder+")" );
        }
        
        
        /* Вызов главного окна */
        java.awt.EventQueue.invokeLater(() -> {
            //new MainFrame().setVisible(true);
        });
    }
    
}

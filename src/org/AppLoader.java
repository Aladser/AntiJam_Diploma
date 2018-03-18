package org;

import java.util.BitSet;
import org.models.BinOperations;
import org.models.NumberCoup;
import org.models.codecs.BCHCodec;
import org.models.codecs.Codec;
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
        int[] errs = new int[7];
        int icode = BinOperations.binToDec(code);
        for(int b, i=0; i<7; i++) errs[i] = icode ^ BinOperations.binToDec(errcodes[i]);
        PolynomDivision.Result[] res = new PolynomDivision.Result[7];
        for(int i=0; i<errcodes.length; i++){
            res[i] = PolynomDivision.exec(BinOperations.binToDec(errcodes[i]), 7, 0b1011);
            res[i].quotient = NumberCoup.exec(res[i].quotient, 2, 4);
        }
        int[] syndrs = new int[8]; 
        for(int i=0; i<errcodes.length; i++) syndrs[ res[i].reminder ] = errs[i];
        //for(int i=0; i<syndrs.length; i++) System.out.println(i + " = " + syndrs[i]);
        BinOperations.decToBin(4);
        
        Codec codec = new BCHCodec(0b1011, 7 ,4);
        int n1 = 7;
        BitSet n2 = BinOperations.decToBin(n1, 4);
        BitSet n3 = codec.encode( n2 );
        int n4 = BinOperations.binToDec( codec.decode(n3) );
        
        
        /* Вызов главного окна */
        java.awt.EventQueue.invokeLater(() -> {
           new MainFrame().setVisible(true);
        });
    }
    
}
